package com.syuesoft.sell.sellZhProject.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.model.XsSellZhProject;
import com.syuesoft.sell.sellZhProject.dao.SellZhProjectDao;
import com.syuesoft.sell.sellZhProject.vo.SellZhProjectVo;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.FormatTime;
@Repository("sellZhProjectDao")
public class SellZhProjectDaoImpl extends BaseDaoImpl<Object> implements
		SellZhProjectDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	public Datagrid getZhList(SellZhProjectVo sellZhProjectVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellZhProjectVo> list = new ArrayList<SellZhProjectVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT A.id,A.zh_project_code,	A.xs_car_sel_id,"
						+ "A.zh_project,A.unit_num,A.cost_price,A.selL_price,A.preferential_price,A.decorate_sell,"
						+ "A.decorate_amount,A.zh_project_person,D.STF_NAME,A.zh_project_date,c.xs_project_name,A.zh_project_reckoning ," +
						"(SELECT k.datavalue FROM xs_childdictionary k WHERE A.zh_project_reckoning = k.child_id) as reckName ," +
						"A.remark ,(SELECT k.datavalue FROM xs_childdictionary k WHERE A.remark  = k.child_id) as re "
						+ "FROM xs_sell_zh_project A  " +
						"JOIN  xs_car_sell_info B ON a.xs_car_sel_id = b.xs_car_sel_id" +
						" LEFT JOIN  xs_zs_project C ON a.zh_project = c.xs_zsproject_id" +
						" LEFT JOIN bas_stuff D ON  a.zh_project_person = d.STF_ID" +
						"  WHERE 1=1 " );
		if(sellZhProjectVo.getXs_Car_Sel_Id()!=null&&!sellZhProjectVo.getXs_Car_Sel_Id().equals("")){
			sql.append(" and  A.xs_car_sel_id = '"+ sellZhProjectVo.getXs_Car_Sel_Id()+"'");
		}
		if(sellZhProjectVo.getZhProjectCode()!=null&&!sellZhProjectVo.getZhProjectCode().equals("")){
			sql.append(" and  A.zh_project_code like  '%"+ StringEscapeUtils.escapeSql(sellZhProjectVo.getZhProjectCode().trim())+"%'");
		}
		//企业编号
		if(sellZhProjectVo.getEnterpriseId()!=null && !sellZhProjectVo.getEnterpriseId().equals("")){
			sql.append(" AND B.enterprise_Id="+sellZhProjectVo.getEnterpriseId()+"");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(),sellZhProjectVo.getPage(), sellZhProjectVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellZhProjectVo sell = new SellZhProjectVo();
				obj = resultList.get(i);
				sell.setZhid(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				sell.setZhProjectCode(obj[1] != null ? obj[1].toString() : null);
				sell.setXs_Car_Sel_Id(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
				sell.setZsprojectId(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				sell.setUnitNum(obj[4] != null ? Integer.parseInt(obj[4].toString()) : null);
				sell.setProjectCostamount(obj[5] != null ? Double.parseDouble(obj[5].toString()) : null);
				sell.setProjectSellamount(obj[6] != null ? Double.parseDouble(obj[6].toString()) : null);
				sell.setPreferentialPrice(obj[7] != null ? Double.parseDouble(obj[7].toString()) : null);
				sell.setDecorateSell(obj[8] != null ? Double.parseDouble(obj[8].toString()) : null);
				sell.setDecorateAmount(obj[9] != null ? Double.parseDouble(obj[9].toString()) : null);
				sell.setZhProjectPerson(obj[10] != null ?Integer.parseInt(obj[10].toString()) : null);
				sell.setStfName(obj[11] != null ? obj[11].toString() : null);
				sell.setZhProjectDate(obj[12] != null ? obj[12].toString() : null);
				sell.setProjectName(obj[13] != null ? obj[13].toString() : null);
				sell.setZhProjectReckoning(obj[14] != null ?Integer.parseInt(obj[14].toString()) : null);
				sell.setReckoningName(obj[15] != null ? obj[15].toString() : null);
				sell.setZhRemark(obj[16] != null ? Integer.parseInt(obj[16].toString()) : null);
				sell.setRemark(obj[17] != null ? obj[17].toString() : null);
				list.add(sell);
				}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
public List<Object> findExaminState(SellZhProjectVo sellZhProjectVo)
	throws Exception {

return this.find("from XsSellZhProject  sellPro where sellPro.zhProjectCode='"+sellZhProjectVo.getZhProjectCode()+"'");

}

public Integer getChildId(String pkey,String ckey)throws Exception{
	List  child = this.createSQLQuery("SELECT child.child_id FROM xs_childdictionary  child, xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND parent.dataKey='"+pkey+"' AND child.dataKey='"+ckey+"'");
	if(child!=null && child.size()>0){
	return  (Integer) child.get(0);
	}else{
	return null;
	}
}

public DatagridAnalyze getZhInfor(SellZhProjectVo sellZhProjectVo) throws Exception {
	DatagridAnalyze dg = new DatagridAnalyze();
	List<SellZhProjectVo> list = new ArrayList<SellZhProjectVo>();
	StringBuffer sql = new StringBuffer("SELECT s.STF_NAME,SUM(zh.unit_num),SUM(zh.decorate_amount)," +
			"SUM(zh.selL_price*zh.unit_num),SUM(zh.decorate_sell)," +
			"SUM(zh.decorate_sell-zh.selL_price*zh.unit_num ),zh.zh_project_person " +
			"FROM " +
			"xs_sell_zh_project zh " +
			"JOIN bas_stuff s ON s.STF_ID=zh.zh_project_person " +
			"JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=zh.xs_car_sel_id where 1=1 ");
	//企业编号
	if(sellZhProjectVo.getEnterpriseId()!=null && !sellZhProjectVo.getEnterpriseId().equals("")){
		sql.append(" AND sell.enterprise_Id="+sellZhProjectVo.getEnterpriseId()+"");
	}
	if(sellZhProjectVo.getXs_Car_Sel_Data()!=null&&!"".equals(sellZhProjectVo.getXs_Car_Sel_Data())){
		sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellZhProjectVo.getXs_Car_Sel_Data() + "'");
	}
	if(sellZhProjectVo.getXs_Car_Sel_Data2()!=null&&!"".equals(sellZhProjectVo.getXs_Car_Sel_Data2())){
		sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellZhProjectVo.getXs_Car_Sel_Data2() + "'");
	}	
	
	if((sellZhProjectVo.getXs_Car_Sel_Data()==null||"".equals(sellZhProjectVo.getXs_Car_Sel_Data()))
			&&(sellZhProjectVo.getXs_Car_Sel_Data2()==null||"".equals(sellZhProjectVo.getXs_Car_Sel_Data2()))){
		sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
				FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
						basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
								Contstants.COLLIGATES.DEFAULTSHOWDAY,sellZhProjectVo.getEnterpriseId()).getCiValue()))
								+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}

	if(sellZhProjectVo.getSellCode()!=null&&!"".equals(sellZhProjectVo.getSellCode())){
		sql.append(" and sell.sell_code like '%"+sellZhProjectVo.getSellCode()+"%'");
	}
	if(sellZhProjectVo.getZhRemark()!=null&&!"".equals(sellZhProjectVo.getZhRemark())){
		sql.append(" and zh.remark='"+sellZhProjectVo.getZhRemark()+"'");
	}
	sql.append(" GROUP BY zh.zh_project_person");
			
	List<Object[]> resultList = createSQLQuery(sql.toString(),sellZhProjectVo.getPage(), sellZhProjectVo.getRows());
	List<SellZhProjectVo> footers = new ArrayList<SellZhProjectVo>();
	if (resultList != null && resultList.size() > 0) {
		Object[] obj = null;
		SellZhProjectVo sell=null;
		for (int i = 0; i < resultList.size(); i++) {
			 sell = new SellZhProjectVo();
			obj = resultList.get(i);
			sell.setStfName(obj[0] != null ? obj[0].toString() : null);
			sell.setUnitNum(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
			sell.setDecorateAmount(obj[2] != null ? Double.parseDouble(obj[2].toString()) : null);//成本价
			sell.setProjectSellamount(obj[3] != null ? Double.parseDouble(obj[3].toString()) : null);//标准价
			sell.setDecorateSell(obj[4] != null ? Double.parseDouble(obj[4].toString()) : null);//成交
			sell.setPreferentialPrice(obj[5] != null ? Double.parseDouble(obj[5].toString()) : null);//优惠价
			sell.setZhProjectPerson(obj[6] != null ? Integer.parseInt(obj[6].toString()) : null);
			sell.setState("closed");
			sell.setIconCls("icon-blank");
			list.add(sell);
		}
		// 汇总
		sell = new SellZhProjectVo();
		sell.setStfName("汇总");
		sell.setState("open");
		sell.setIconCls("icon-blank");
		Integer sum1 = 0;
		Double sum2 = 0.0;
		Double sum3= 0.0;
		Double sum4= 0.0;
		Double sum5= 0.0;
		for (SellZhProjectVo vo : list) {
			if (vo.getUnitNum() != null
					&& !"".equals(vo.getUnitNum())) {
				sum1 += (vo.getUnitNum());
			}

			if (vo.getDecorateAmount() != null
					&& !"".equals(vo.getDecorateAmount())) {
				sum2 += (vo.getDecorateAmount());
			}

			if (vo.getProjectSellamount() != null
					&& !"".equals(vo.getProjectSellamount())) {
				sum3+=  vo.getProjectSellamount();
			}
			if (vo.getDecorateSell() != null
					&& !"".equals(vo.getDecorateSell())) {
				sum4+=  vo.getDecorateSell();
			}

			if (vo.getPreferentialPrice() != null
					&& !"".equals(vo.getPreferentialPrice())) {
				sum5+=  vo.getPreferentialPrice();
			}


		}
		sell.setUnitNum(sum1);
		sell.setDecorateAmount(sum2);//成本价
		sell.setProjectSellamount(sum3) ;//标准价
		sell.setDecorateSell(sum4);//成交
		sell.setPreferentialPrice(sum5);//优惠价
		
		footers.add(sell);
	}
	int total = this.getSQLCount(sql.toString(), null);
	dg.setRows(list);
	dg.setFooter(footers);
	dg.setTotal(total);
	return dg;
}

public List getZhDelInfor(SellZhProjectVo sellZhProjectVo) throws Exception {
	Datagrid dg = new Datagrid();
	List<SellZhProjectVo> list = new ArrayList<SellZhProjectVo>();
	StringBuffer sql = new StringBuffer("SELECT cus.xs_custom_name,sell.xs_car_sel_data,zs.xs_project_name," +
			"zh.unit_num,zh.decorate_amount,zh.selL_price*zh.unit_num,zh.decorate_sell," +
			"(zh.decorate_sell-zh.selL_price*zh.unit_num ) AS youh ," +
			"(SELECT datavalue FROM xs_childdictionary child WHERE child.child_id=zh.remark) AS remark ," +
			"zh.zh_project_code " +
			"FROM xs_sell_zh_project zh " +
			"JOIN bas_stuff s ON s.STF_ID=zh.zh_project_person  " +
			"JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=zh.xs_car_sel_id " +
			"JOIN xs_custom_info cus ON cus.custom_id=sell.custom_id " +
			"JOIN xs_zs_project zs ON zs.xs_zsproject_id=zh.zh_project " +
			"WHERE zh.zh_project_person="+sellZhProjectVo.getZhProjectPerson());
	if(sellZhProjectVo.getEnterpriseId()!=null && !sellZhProjectVo.getEnterpriseId().equals("")){
		sql.append(" AND sell.enterprise_Id="+sellZhProjectVo.getEnterpriseId()+"");
	}
	if(sellZhProjectVo.getXs_Car_Sel_Data()!=null&&!"".equals(sellZhProjectVo.getXs_Car_Sel_Data())){
		sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellZhProjectVo.getXs_Car_Sel_Data() + "'");
	}
	if(sellZhProjectVo.getXs_Car_Sel_Data2()!=null&&!"".equals(sellZhProjectVo.getXs_Car_Sel_Data2())){
		sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellZhProjectVo.getXs_Car_Sel_Data2() + "'");
	}	
	
	if((sellZhProjectVo.getXs_Car_Sel_Data()==null||"".equals(sellZhProjectVo.getXs_Car_Sel_Data()))
			&&(sellZhProjectVo.getXs_Car_Sel_Data2()==null||"".equals(sellZhProjectVo.getXs_Car_Sel_Data2()))){
		sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
				FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
						basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
								Contstants.COLLIGATES.DEFAULTSHOWDAY,sellZhProjectVo.getEnterpriseId()).getCiValue()))
								+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	}
	if(sellZhProjectVo.getSellCode()!=null&&!"".equals(sellZhProjectVo.getSellCode())){
		sql.append(" and sell.sell_code like '%"+sellZhProjectVo.getSellCode()+"%'");
	}
	if(sellZhProjectVo.getZhRemark()!=null&&!"".equals(sellZhProjectVo.getZhRemark())){
		sql.append(" and zh.remark='"+sellZhProjectVo.getZhRemark()+"'");
	}
	List<Object[]> resultList = createSQLQuery(sql.toString());
	if (resultList != null && resultList.size() > 0) {
		Object[] obj = null;
		for (int i = 0; i < resultList.size(); i++) {
			SellZhProjectVo sell = new SellZhProjectVo();
			obj = resultList.get(i);
			sell.setXs_Custom_Name(obj[0] != null ? obj[0].toString() : null);
			sell.setXs_Car_Sel_Data(obj[1] != null ? obj[1].toString() : null);
			sell.setProjectName(obj[2] != null ? obj[2].toString() : null);
			sell.setUnitNum(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
			sell.setDecorateAmount(obj[4] != null ? Double.parseDouble(obj[4].toString()) : null);//成本价
			sell.setProjectSellamount(obj[5] != null ? Double.parseDouble(obj[5].toString()) : null);//标准价
			sell.setDecorateSell(obj[6] != null ? Double.parseDouble(obj[6].toString()) : null);//成交
			sell.setPreferentialPrice(obj[7] != null ? Double.parseDouble(obj[7].toString()) : null);//优惠价
			sell.setRemark(obj[8] != null ? obj[8].toString() : null);
			sell.setStfName(obj[9] != null ? obj[9].toString() : null);
			sell.setState("open");
			sell.setIconCls("icon-blank");
			list.add(sell);
		}
		}
	return list;
}
		
}
