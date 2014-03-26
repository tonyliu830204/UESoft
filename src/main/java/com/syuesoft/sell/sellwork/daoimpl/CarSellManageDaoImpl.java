package com.syuesoft.sell.sellwork.daoimpl;

import java.text.SimpleDateFormat;
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
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.sellwork.dao.CarSellManageDao;
import com.syuesoft.sell.sellwork.vo.CarSellManageVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Repository("carSellManageDao")
public class CarSellManageDaoImpl extends BaseDaoImpl<XsCarSellInfo> implements
		CarSellManageDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

	/**
	 * 通过 预定编号获取 分配的 车辆 客户 财务信息
	 */
	
	public Json getInforById(CarSellManageVo carSellManageVo) throws Exception {
		return null;
	}

	/**
	 * 获取预定单编号
	 */
	
	public List getReserveCode(CarSellManageVo carSellManageVo) throws Exception {
		StringBuffer SQL = new StringBuffer("SELECT A.reserve_Id,A.reserve_Code from Xs_Sell_Car_Reserve A, Xs_Car_Info B WHERE A.xs_Car_Id = B.xs_car_Id and a.allocate_State=1");
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			SQL.append(" AND A.Enterprise_Id="+carSellManageVo.getEnterpriseId()+"");
		}
		return createSQLQuery(SQL.toString());
	}

	
	public void deleteSellInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		
	}

	/**
	 * 获取PDI检测内容(根据销售单号)
	 */
	
	public List getPdiCheck(CarSellManageVo carSellManageVo) throws Exception {
		StringBuffer sql = new StringBuffer("" +
				" SELECT a.child_id,a.datavalue," +
				" b.status,b.remark,b.id, b.status,(SELECT ch.datavalue FROM xs_childdictionary ch WHERE ch.child_id=b.status) AS statusName " +
				" FROM xs_childdictionary a INNER JOIN xs_parentdictionary xp ON xp.parent_id=a.parent_id" +
				" JOIN xs_pdi_check b ON a.child_id = b.check_comtent" +
				"  JOIN xs_car_sell_info c ON c.xs_car_sel_id = b.xs_car_sel_id" +
				" LEFT JOIN xs_sell_car_reserve d ON c.reserve_id = d.reserve_id" +
				"  JOIN xs_car_info e ON e.xs_car_id = c.xs_car_id" +
				" WHERE xp.dataKey='"+Contstants.BASE_SELL.PDICHECK+"' and c.Xs_Car_Sel_Id="+carSellManageVo.getXs_Car_Sel_Id()+"");
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sql.append(" AND c.Enterprise_Id="+carSellManageVo.getEnterpriseId()+"");
		}

		return createSQLQuery(sql.toString());
		
	}
	/**
	 * 获取PDI检测内容
	 */
	
	public List getPdiCheck2(CarSellManageVo carSellManageVo) throws Exception {
		String sql ="SELECT a.child_id,a.datavalue" +
		" FROM xs_childdictionary a " +
		"LEFT JOIN xs_pdi_check b ON a.child_id = b.check_comtent" +
		" INNER JOIN xs_parentdictionary xp ON xp.parent_id=a.parent_id" +
		" LEFT JOIN xs_car_sell_info c ON c.xs_car_sel_id = b.xs_car_sel_id" +
		" LEFT JOIN xs_sell_car_reserve d ON c.reserve_id = d.reserve_id" +
		" LEFT JOIN xs_car_info e ON e.xs_car_id = d.xs_car_id" +
		" WHERE xp.dataKey='"+Contstants.BASE_SELL.PDICHECK+"' ";
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sql+=" AND a.enterprise_Id="+carSellManageVo.getEnterpriseId()+"";
		}
		sql+=" group by a.child_id";
		return createSQLQuery(sql);
		
	}

	
	public List<CarSellManageVo> findChildList(CarSellManageVo carSellManageVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		Contstants.COLLIGATES.DEFAULTSHOWDAY,carSellManageVo.getEnterpriseId()).getCiValue()));

		List<CarSellManageVo> list_ = new ArrayList<CarSellManageVo>();
		StringBuffer sql = new StringBuffer("SELECT A.xs_car_sel_id,b.xs_car_vin_number ,b.xs_car_code,b.xs_car_ocn,b.xs_car_color," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.xs_car_color) AS color," +
				" a.sell_code,c.retreat_date,a.xs_car_sel_data,a.xs_car_stf_id,e.STF_NAME,a.custom_id,f.xs_custom_name," +
				"a.xs_car_sel_transaction_money,g.xs_model_costPrice,H.invoice_date,h.invoice_parce," +
				" b.xs_distributor_id,i.xs_distributor_name  " +
				"FROM xs_car_sell_info A " +
				"join xs_sell_flow_control flow on flow.xsfcontrol_document=a.sell_code " +
				"JOIN xs_car_info B ON b.xs_car_id=a.xs_car_id " +
				"LEFT JOIN xs_sell_retreat C ON c.retreat_id=a.out_id " +
				"LEFT JOIN xs_sell_car_reserve D ON d.reserve_id=a.reserve_id " +
				"JOIN bas_stuff E ON e.STF_ID=a.xs_car_stf_id " +
				"JOIN xs_custom_info F ON f.custom_id=a.custom_id " +
				"LEFT JOIN xs_car_model G ON g.xs_model_id=b.xs_car_model_id " +
				"LEFT JOIN xs_sell_invoice H ON H.xs_car_sel_id=a.xs_car_sel_id " +
				"LEFT JOIN xs_distributor_info I ON i.xs_distributor_id=b.xs_distributor_id " +
				"WHERE b.xs_car_model_id="+carSellManageVo.getXs_Car_Model_Id());
		
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sql.append(" AND A.Enterprise_Id="+carSellManageVo.getEnterpriseId()+"");
		}
		//销售日期
		if(carSellManageVo.getXs_Car_Sel_Data()!=null && !carSellManageVo.getXs_Car_Sel_Data().trim().equals("")){
			sql.append(" and DATE(A.Xs_Car_Sel_Data) >= '"+carSellManageVo.getXs_Car_Sel_Data()+"'");
		}
		if(carSellManageVo.getXs_Car_Sel_Data2()!=null && !carSellManageVo.getXs_Car_Sel_Data2().trim().equals("")){
			sql.append( " and DATE(A.Xs_Car_Sel_Data) <= '"+carSellManageVo.getXs_Car_Sel_Data2()+"'");
		}
		if((carSellManageVo.getXs_Car_Sel_Data()==null ||"".equals(carSellManageVo.getXs_Car_Sel_Data().trim()))&&
				(carSellManageVo.getXs_Car_Sel_Data2()==null ||"".equals(carSellManageVo.getXs_Car_Sel_Data2().trim()))){

		sql.append( " and DATE(A.Xs_Car_Sel_Data) BETWEEN '"+sdate+"' AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(carSellManageVo.getRetreat_date()!=null && !"".equals(carSellManageVo.getRetreat_date().trim())){
				sql.append(" and DATE(C.retreat_date) >= '"+carSellManageVo.getRetreat_date()+"'");
			}
		if(carSellManageVo.getRetreat_date2()!=null && !"".equals(carSellManageVo.getRetreat_date2().trim())){
				sql.append( " and DATE(C.retreat_date) <= '"+carSellManageVo.getRetreat_date2()+"'");
		}
			
		
		if(carSellManageVo.getInvoice_date()!=null && !"".equals(carSellManageVo.getInvoice_date().trim())){
			sql.append(" and DATE(h.invoice_date) >= '"+carSellManageVo.getInvoice_date()+"'");
		}
		if(carSellManageVo.getInvoice_date2()!=null && !"".equals(carSellManageVo.getInvoice_date2().trim())){
			sql.append( " and DATE(h.invoice_date) <= '"+carSellManageVo.getInvoice_date2()+"'");
		}
		if(carSellManageVo.getStf_Id()!=null && !carSellManageVo.getStf_Id().equals("")){
			sql.append( " and A.xs_car_stf_id ="+carSellManageVo.getStf_Id()+"");
		}
		if(carSellManageVo.getXs_Car_Brand()!=null && !carSellManageVo.getXs_Car_Brand().equals("")){
			sql.append( " and B.xs_car_brand="+carSellManageVo.getXs_Car_Brand()+"");
		}
		
		if(carSellManageVo.getXs_Car_Color()!=null && !carSellManageVo.getXs_Car_Color().equals("")){
			sql.append( " and b.xs_car_color ="+carSellManageVo.getXs_Car_Color()+"");
		}
	
		if(carSellManageVo.getXs_Custom_Name()!=null && !carSellManageVo.getXs_Custom_Name().equals("")){
			sql.append( " and F.xs_custom_name like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Custom_Name().trim())+"%'" );
		}
		if(carSellManageVo.getCar_Vin_Number()!=null && !carSellManageVo.getCar_Vin_Number().equals("")){
			sql.append( " and b.xs_car_vin_number like '%"+carSellManageVo.getCar_Vin_Number()+"%'");
		}
		if(carSellManageVo.getXs_Distributor_Id()!=null && !carSellManageVo.getXs_Distributor_Id().equals("")){
			sql.append( " and b.xs_distributor_id ="+carSellManageVo.getXs_Distributor_Id()+"");
		}
		if(carSellManageVo.getPayment_Way()!=null && !carSellManageVo.getPayment_Way().equals("")){
			sql.append( " and d.pay_way ="+carSellManageVo.getPayment_Way()+"");
		}
		if(carSellManageVo.getXs_car_type()!=null && !carSellManageVo.getXs_car_type().equals("")){
			sql.append( " and B.xs_car_type ="+carSellManageVo.getXs_car_type()+"");
		}
		if(carSellManageVo.getXs_Custom_Property()!=null && !carSellManageVo.getXs_Custom_Property().equals("")){
			sql.append( " and F.xs_custom_property ="+carSellManageVo.getXs_Custom_Property()+"");
		}
		if(carSellManageVo.getDeptId()!=null && !carSellManageVo.getDeptId().equals("")){
			sql.append( " and e.DEPT_ID ="+carSellManageVo.getDeptId()+"");
		}
		
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarSellManageVo wopq = new CarSellManageVo();
				obj = resultList.get(i);
				wopq.setXs_Car_Model(obj[0] != null ? obj[0].toString() : null);
				wopq.setCar_Vin_Number(obj[1] != null ? obj[1].toString() : null);
				wopq.setCarCode(obj[2] != null ? obj[2].toString() : null);
				wopq.setXs_Car_Ocn(obj[3] != null ? obj[3].toString() : null);
				wopq.setXs_Car_Color(obj[4] != null ? obj[4].toString() : null);
				wopq.setXs_Car_Color_Name(obj[5] != null ? obj[5].toString() : null);
				wopq.setSell_Code(obj[6] != null ? obj[6].toString() : null);
				wopq.setRetreat_date(obj[7] != null ?  fmt.format(obj[7]) : null);
				wopq.setXs_Car_Sel_Data(obj[8] != null ? obj[8].toString() : null);
				wopq.setStf_Id(obj[9] != null ? obj[9].toString() : null);
				wopq.setStf_Name(obj[10] != null ? obj[10].toString() : null);
				wopq.setXs_Custom_Id(obj[11] != null ? obj[11].toString() : null);
				wopq.setXs_Custom_Name(obj[12] != null ? obj[12].toString() : null);
				wopq.setXs_Car_Sel_Transaction_Money(obj[13] != null ? obj[13].toString() : null);
				wopq.setXs_Model_CostPrice(obj[14] != null ? obj[14].toString() : null);
				wopq.setInvoice_date(obj[15] != null ? obj[15].toString() : null);
				wopq.setInvoice_parce(obj[16] != null ? obj[16].toString() : null);
				wopq.setXs_Distributor_Id(obj[17] != null ? obj[17].toString() : null);
				wopq.setXs_Distributor_Name(obj[18] != null ? obj[18].toString() : null);
				wopq.setIconCls("icon-blank");
				wopq.setState("open");
				list_.add(wopq);
			}
		}
		return list_;
	}
		
	

}
