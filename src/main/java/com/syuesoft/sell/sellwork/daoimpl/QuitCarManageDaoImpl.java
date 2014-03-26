package com.syuesoft.sell.sellwork.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.sell.model.XsSellExitCar;
import com.syuesoft.sell.sellwork.dao.QuitCarManageDao;
import com.syuesoft.sell.sellwork.vo.QuitCarManageVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;


@Repository("quitCarManageDao")
public class QuitCarManageDaoImpl extends BaseDaoImpl implements
		QuitCarManageDao {

	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 添加退车信息
	 */
	
	public void addQuitInfor(XsSellExitCar xsSellExitCar)throws Exception {
			save(xsSellExitCar);
	}

	/**
	 * 获取退车信息
	 */
	
	public Json getQuitInfor(QuitCarManageVo quitCarManageVo)throws Exception {
		StringBuffer sb=new StringBuffer("SELECT b.exitCar_id,b.exitCar_code,b.exitCar_type,b.examine,b.exitCar_date,");
		sb.append(" (SELECT m.STF_NAME FROM bas_stuff m WHERE m.STF_ID = b.exitCar_person) AS exitCar_person,");
		sb.append(" (SELECT m.STF_NAME FROM bas_stuff m WHERE m.STF_ID = b.exitCar_check_person) AS exitCar_check_person,");
		sb.append(" a.xs_car_sel_id,a.sell_code,c.custom_id,c.xs_custom_name,d.xs_car_id,");
		sb.append(" d.xs_car_code,d.xs_car_ocn,d.xs_car_vin_number,a.xs_Car_Sel_Data,"); 
		sb.append(" b.notice,aa.exitCarTypeName,b.exitCar_remark,"); 
		sb.append(" (SELECT datavalue FROM xs_childdictionary q WHERE q.child_id = b.examine) AS examinename");
		sb.append(" FROM  xs_sell_exitcar b INNER JOIN xs_car_sell_info a ON b.xs_car_sel_id = a.xs_car_sel_id"); 
		sb.append(" INNER JOIN  xs_custom_info c ON a.custom_id = c.custom_id");
		sb.append(" INNER JOIN xs_car_info d  ON a.xs_car_id = d.xs_car_id"); 
		sb.append(" INNER JOIN ( SELECT xc.child_id,xc.dataValue as exitCarTypeName"); 
		sb.append(" FROM xs_childdictionary xc INNER JOIN xs_parentdictionary xp"); 
		sb.append(" ON xp.parent_id=xc.parent_id AND xp.datakey='"+Contstants.QUITCARTYPE.QUITCARTYPEKEY+"'"); 
		sb.append(" ) aa ON aa.child_id=b.exitCar_type WHERE 1=1");
		
		//企业编号
		if(quitCarManageVo.getEnterpriseId()!=null && !quitCarManageVo.getEnterpriseId().equals("")){
			sb.append(" AND b.enterprise_id ="+quitCarManageVo.getEnterpriseId()+"");
		}
		
//		if (quitCarManageVo.getExitCar_Date() != null
//				&& !"".equals(quitCarManageVo.getExitCar_Date())) {
//			String[] str = quitCarManageVo.getExitCar_Date().split(",");
//			if (str.length > 1) {
//				sb.append(" and b.exitCar_date BETWEEN '" + str[0]
//						+ "' AND '" + str[1] + "'");
//			} else {
//				if (quitCarManageVo.getExitCar_Date().length() > 10) {
//					sb.append(" and b.exitCar_date >= '" + str[0] + "'");
//				} else {
//					sb.append(" and b.exitCar_date <='" + str[0] + "'");
//				}
//
//			}
//		}
		if (quitCarManageVo.getExitCar_Date() != null
				&& !quitCarManageVo.getExitCar_Date().equals("")) {
					sb.append(" and DATE(b.exitCar_date) >= '" +quitCarManageVo.getExitCar_Date() + "'");
				} 
		if (quitCarManageVo.getExitCar_Date2() != null
				&& !quitCarManageVo.getExitCar_Date2().equals("")) {
					sb.append(" and DATE(b.exitCar_date) <= '" +quitCarManageVo.getExitCar_Date2() + "'");
		}
		if((quitCarManageVo.getExitCar_Date() == null
				|| quitCarManageVo.getExitCar_Date().equals("")) 
				&& (quitCarManageVo.getExitCar_Date2() == null
				|| quitCarManageVo.getExitCar_Date2().equals(""))){
			sb.append(" and DATE(b.exitCar_date) BETWEEN '" + 
					FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,quitCarManageVo.getEnterpriseId()).getCiValue()) 
					+ "' AND '" + FormatTime.yesTady("yyyy-MM-dd") + "'");
		}
		
		if (quitCarManageVo.getXs_Car_Sel_Date() != null
				&& !quitCarManageVo.getXs_Car_Sel_Date().equals("")) {
					sb.append(" and DATE(a.xs_Car_Sel_Data) >= '" +quitCarManageVo.getXs_Car_Sel_Date() + "'");
				} 
		if (quitCarManageVo.getXs_Car_Sel_Date2() != null
				&& !quitCarManageVo.getXs_Car_Sel_Date2().equals("")) {
					sb.append(" and DATE(a.xs_Car_Sel_Data) <= '" +quitCarManageVo.getXs_Car_Sel_Date2() + "'");
		}
		if(quitCarManageVo.getSell_Code()!=null && !quitCarManageVo.getSell_Code().equals("")){
			sb.append(" and a.sell_code like '%"+StringEscapeUtils.escapeSql(quitCarManageVo.getSell_Code().trim())+"%'");
		}
		if(quitCarManageVo.getXs_Car_Vin_Number()!=null && !quitCarManageVo.getXs_Car_Vin_Number().equals("")){
			sb.append(" and d.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(quitCarManageVo.getXs_Car_Vin_Number().trim())+"%'");
		}
		if(quitCarManageVo.getExitCar_Code()!=null && !quitCarManageVo.getExitCar_Code().equals("")){
			sb.append(" and b.exitCar_code like '%"+StringEscapeUtils.escapeSql(quitCarManageVo.getExitCar_Code().trim())+"%'");
		}
		if(quitCarManageVo.getXs_Custom_Name()!=null && !quitCarManageVo.getXs_Custom_Name().equals("")){
			sb.append(" and c.xs_custom_name like '%"+StringEscapeUtils.escapeSql(quitCarManageVo.getXs_Custom_Name().trim())+"%'");
		}
		if(quitCarManageVo.getExitCar_Type()!=null && !quitCarManageVo.getExitCar_Type().equals("")){
			sb.append(" and b.exitCar_type= '"+quitCarManageVo.getExitCar_Type()+"'");
		}
		if(quitCarManageVo.getExitCar_Check_Person()!=null && !quitCarManageVo.getExitCar_Check_Person().equals("")){
			sb.append(" and b.exitCar_check_person= '"+quitCarManageVo.getExitCar_Check_Person()+"'");
		}
		sb.append(" order by  b.exitCar_id desc");
		List rlist = createSQLQuery(sb.toString(),quitCarManageVo.getPage(),quitCarManageVo.getRows());
		List list = new ArrayList();
		Object[] obj = null;
		if(rlist!=null && rlist.size()>0)
		for (int i = 0; i < rlist.size(); i++) {
			obj = (Object[])rlist.get(i);
			QuitCarManageVo vo = new QuitCarManageVo();
			if(obj[0]!=null&&obj[0].toString().length()>0){
				vo.setExitCar_Id(obj[0].toString());
			}
			if(obj[1]!=null&&obj[1].toString().length()>0){
				vo.setExitCar_Code(obj[1].toString());
			}
			if(obj[2]!=null&&obj[2].toString().length()>0){
				vo.setExitCar_Type(obj[2].toString());
			}
			if(obj[3]!=null&&obj[3].toString().length()>0){
				vo.setExamine(obj[3].toString());
			}
			if(obj[4]!=null&&obj[4].toString().length()>0){
				vo.setExitCar_Date(obj[4].toString());
			}
			if(obj[5]!=null&&obj[5].toString().length()>0){
				vo.setExitCar_Person(obj[5].toString());
			}
			if(obj[6]!=null&&obj[6].toString().length()>0){
				vo.setExitCar_Check_Person(obj[6].toString());
			}
			if(obj[7]!=null&&obj[7].toString().length()>0){
				vo.setXs_Car_Sel_Id(obj[7].toString());
			}
			if(obj[8]!=null&&obj[8].toString().length()>0){
				vo.setSell_Code(obj[8].toString());
			}
			if(obj[9]!=null&&obj[9].toString().length()>0){
				vo.setXs_Custom_Id(obj[9].toString());
			}
			if(obj[10]!=null&&obj[10].toString().length()>0){
				vo.setXs_Custom_Name(obj[10].toString());
			}
			if(obj[11]!=null&&obj[11].toString().length()>0){
				vo.setXs_Car_Id(obj[11].toString());
			}
			if(obj[12]!=null&&obj[12].toString().length()>0){
				vo.setXs_Car_Code(obj[12].toString());
			}
			//出库单 用作判断是否出库
			if(obj[13]!=null&&obj[13].toString().length()>0){
				vo.setXs_Car_Ocn(obj[13].toString());
			}
			if(obj[14]!=null&&obj[14].toString().length()>0){
				vo.setXs_Car_Vin_Number(obj[14].toString());
			}
			if(obj[15]!=null&&obj[15].toString().length()>0){
				vo.setXs_Car_Sel_Date(obj[15].toString());
			}
			if(obj[16]!=null&&obj[16].toString().length()>0){
				vo.setNotice(Integer.parseInt(obj[16].toString()));
			}
			if(obj[17]!=null&&obj[17].toString().length()>0){
				vo.setExitCarTypeName(obj[17].toString());
			}
			if(obj[18]!=null&&obj[18].toString().length()>0){
				vo.setExitCar_Remark(obj[18].toString());
			}
			if(obj[19]!=null&&obj[19].toString().length()>0){
				vo.setExamine_Name(obj[19].toString());
			}
			
			list.add(vo);
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(getCountBySQL(sb.toString()));
		return json;
		
	}


}
