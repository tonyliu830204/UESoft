package com.syuesoft.sell.repayManage.daoimpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.repayManage.dao.SellCoverDao;
import com.syuesoft.sell.repayManage.vo.SellCoverVo;
import com.syuesoft.util.FormatTime;

@Repository("sellCoverDao")
public class SellCoverDaoImpl extends BaseDaoImpl<BaseBean> implements
		SellCoverDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
/*	public BasCompanyInformationSetDAO getBasCompanyInformationSetDAO() {
		return basCompanyInformationSetDAO;
	}

	public void setBasCompanyInformationSetDAO(
			BasCompanyInformationSetDAO basCompanyInformationSetDAO) {
		this.basCompanyInformationSetDAO = basCompanyInformationSetDAO;
	}*/


	/**
	 * 查询售后回访记录明细
	 */
	
	public Datagrid getSellCover(SellCoverVo sellCoverVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT A.consult_id,A.xs_car_sel_id,"
						+ "A.consult_plan_date,A.consult_actual_date,A.consult_rate,e.xs_repay_name,A.consult_degree, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.consult_degree) AS degree,"
						+ "A.consult_call_state,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.consult_call_state) AS state,"
						+ "A.travel_course, A.consult_suggest,A.dispose_date,A.dispose_result,"
						+ "A.remark,d.xs_custom_name,	d.xs_custom_telephone,d.xs_custom_birthday," +
						"f.xs_car_model_id,g.xs_model_name,F.xs_car_licensePlate,ss.pact_code,D.xs_custom_address,D.xs_custom_phone," +
						"b.xs_car_sel_remark,B.custom_id,d.STF_ID, H.STF_NAME	"
						+ "FROM xs_sell_cover A "
						+ " JOIN xs_car_sell_info B ON  A.xs_car_sel_id=B.xs_car_sel_id"
						+ " JOIN xs_custom_info D ON b.custom_id=d.custom_id "
						+ "  LEFT OUTER JOIN xs_repay E ON e.xs_repay_id=a.consult_rate "
						+ " JOIN xs_car_info F ON b.xs_car_id=f.xs_car_id "
						+ " JOIN xs_car_model G  ON g.xs_model_id=f.xs_car_model_id "
						+ "LEFT OUTER JOIN xs_sell_car_reserve ss ON ss.reserve_id=b.reserve_id "
						+ "  JOIN bas_stuff H ON h.STF_ID=d.STF_ID"
						+ " where A.xs_car_sel_id='"
						+ sellCoverVo.getXsCarSelId() + "'" +
								" and a.enterprise_id="+sellCoverVo.getEnterpriseId());
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setConsultId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setXsCarSelId(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setConsultPlanDate(obj[2] != null ? fmt.format(obj[2]): null);
				sell.setConsultActualDate(obj[3] != null ?  fmt.format(obj[3])
						: null);
				sell.setConsulTRate(obj[4] != null ? Integer.parseInt(obj[4]
						.toString()) : null);
				sell.setConsulTRateN(obj[5] != null ? obj[5].toString() : null);
				sell.setConsultDegree(obj[6] != null ? Integer.parseInt(obj[6]
						.toString()) : null);
				sell.setConsultDegreeType(obj[7] != null ? obj[7].toString()
						: null);
				sell.setConsultCallState(obj[8] != null ? Integer
						.parseInt(obj[8].toString()) : null);
				sell.setConsultCallStateN(obj[9] != null ? obj[9].toString()
						: null);
				sell.setTravelCourse(obj[10] != null ? Double
						.parseDouble(obj[10].toString()) : null);
				sell.setConsultSuggest(obj[11] != null ? obj[11].toString()
						: null);
				sell.setDisposeDate(obj[12] != null ?  fmt.format(obj[12]): null);
				sell.setDisposeResult(obj[13] != null ? obj[13].toString()
						: null);
				sell.setRemark(obj[14] != null ? obj[14].toString() : null);
				sell.setXsCustomName(obj[15] != null ? obj[15].toString()
						: null);
				sell.setXsCustomTelephone(obj[16] != null ? obj[16].toString()
						: null);
				sell.setXsCustomBirthday(obj[17] != null ? ((Timestamp) obj[17])
								: null);
				sell.setCarModel(obj[18] != null ? Integer.parseInt(obj[18]
				                               						.toString()) : null);
				sell.setCarModelName(obj[19] != null ? obj[19].toString() : null);
				sell.setCarLicensePlate(obj[20] != null ? obj[20].toString(): null);
				
				sell.setPactCode(obj[21] != null ? obj[21].toString() : null);
				sell.setXsCustomAddress(obj[22] != null ? obj[22].toString()
						: null);
				sell.setXsCustomPhone(obj[23] != null ? obj[23].toString()
						: null);
			
				sell.setXsCarSelRemark(obj[24] != null ? obj[24].toString()
						: null);
				sell.setCustomId(obj[25] != null ? Integer.parseInt(obj[25]
				                               						.toString()) : null);
				sell.setStfId(obj[26] != null ? Integer.parseInt(obj[26].toString()) : null);
				 sell.setStfName(obj[27] != null ? obj[27].toString() : null);
				list.add(sell);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 查询问卷调查内容
	 */
	
	public List getResearch(SellCoverVo sellCoverVo) throws Exception {
		
		StringBuffer sql = new StringBuffer(
				"SELECT xs_project_name,"
						+ "xs_project_type,xs_project_id, c.project_evaluate,c.project_score,c.remark,b.consult_id," +
						"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =c.project_evaluate) AS evaluate," +
						"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =c.project_score) AS score ," +
						"C.content_id "
						+ " FROM 	xs_repay_project A LEFT OUTER JOIN xs_sell_cover B ON a.xs_project_type=b.consult_rate"
						+ " LEFT OUTER JOIN  xs_sell_cover_content C ON c.project_id=A.xs_project_id AND b.consult_id=c.consult_id "
						+ "WHERE  b.consult_id='" + sellCoverVo.getConsultId()
						+ "'");

		List<Object[]> resultList = createSQLQuery(sql.toString());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setProjectName(obj[0] != null ? obj[0].toString() : null);
				sell.setConsulTRate(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setProjectId(obj[2] != null ? Integer.parseInt(obj[2]
						.toString()) : null);
				sell.setProjectEvaluate(obj[3] != null ? Integer
						.parseInt(obj[3].toString()) : null);
				sell.setProjectScore(obj[4] != null ? Integer.parseInt(obj[4]
						.toString()) : null);
				sell.setRemark(obj[5] != null ? obj[5].toString() : null);
				sell.setConsultId(obj[6] != null ? Integer.parseInt(obj[6]
						.toString()) : null);
				sell.setProjectEvaluateN(obj[7] != null ? obj[7].toString() : null);
				sell.setProjectScoreN(obj[8] != null ?Float.parseFloat(obj[8].toString()) : null);
				sell.setContentId(obj[9] != null ? Integer.parseInt(obj[9].toString()) : null);
			
				

				list.add(sell);

			}
		}
		
		return list;
	}
	/**保险模块：查询车辆销售信息*/
	
	public Datagrid getInSellList(SellCoverVo sellCoverVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT A.reserve_id,f.xs_car_brand, F.xs_car_code,f.xs_car_model_id, F.xs_car_color, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =f.xs_car_brand) AS brand,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_car_color) AS color,"
						+ "g.xs_model_name,F.xs_car_vin_number,B.custom_id,e.xs_custom_name, B.xs_car_sel_id,"
						+ "b.xs_car_sel_data,b.xs_car_sel_remark,D.consult_degree,"
						+ " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.consult_degree) AS degree,"
						+ "E.xs_custom_phone,E.xs_custom_telephone,E.xs_custom_application,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_application) AS application,"
						+ " E.xs_custom_address,  E.xs_custom_occupation,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_occupation) AS occupation,"
						+ " E.xs_custom_income,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =  E.xs_custom_income) AS income,"
						+ " e.xs_custom_diploma,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =  e.xs_custom_diploma) AS diploma,"
						+ " E.xs_custom_birthday,E.xs_custom_credentials,  E.xs_custom_sex,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_sex) AS sex,"
						+ "e.STF_ID, H.STF_NAME,  F.xs_car_motor_number,"
						+ "F.xs_car_licensePlate,F.xs_car_sell_state,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = F.xs_car_sell_state) AS state,"
						+ " A.pact_code ,b.audit_date,D.consult_actual_date,D.consult_rate ," +
						"C.xs_repay_name ,A.car_outputVolume,f.xs_car_rideLimit_number,d.travel_course,E.xs_custom_zipcode," +
						"D.consult_call_state,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.consult_call_state) AS stateaa," +
								"b.sell_code  "
						+ "FROM  xs_car_sell_info B " +
						 " join xs_sell_flow_control flow on flow.xsfcontrol_document=B.sell_code " +
						 " and  flow.xsfcontrol_car_id=B.xs_car_id "
						+ "LEFT OUTER JOIN xs_sell_car_reserve A ON a.reserve_id=b.reserve_id " 
						+ "LEFT OUTER JOIN xs_sell_cover  D ON d.xs_car_sel_id=b.xs_car_sel_id "
						+ " JOIN xs_custom_info E ON e.custom_id=b.custom_id "						
						+ " JOIN xs_car_info F ON b.xs_car_id=f.xs_car_id "
						+" AND f.xs_car_sell_state IN	(SELECT child.child_id  FROM xs_childdictionary child, xs_parentdictionary parent"
	                    +" WHERE child.parent_id = parent.parent_id    AND parent.dataKey = '"+Contstants.SELLSTATE.BASE_SELLSTATE+"'   AND child.dataKey IN('"
	                    +Contstants.SELLSTATE.NOTSOLD+"','"+Contstants.SELLSTATE.SELLOUT+"','"+Contstants.SELLSTATE.AFTERSELL+"'))"
						+ " JOIN xs_car_model G  ON g.xs_model_id=f.xs_car_model_id "
						+ "  JOIN bas_stuff H ON h.STF_ID=e.STF_ID"
						+ " LEFT OUTER JOIN xs_repay  C ON c.xs_repay_id=d.consult_rate"
						+ " WHERE (b.xs_car_give_up IS NULL OR   b.xs_car_give_up=0) " 
//						+" and B.audit=(SELECT child.child_id FROM xs_childdictionary  child, " +
//						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
//						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
//								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
//						") and (b.isinsurance is  NULL or b.isinsurance=(SELECT child.child_id FROM xs_childdictionary  child, " +
//						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
//						"AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
//								"child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE2+"'" +
//						"))
						);	
		if (sellCoverVo.getEnterpriseId() != null
				&& !"".equals(sellCoverVo.getEnterpriseId())) {
			sql.append("  and B.enterprise_id ='" + sellCoverVo.getEnterpriseId()
					+ "'");
		}
		if (sellCoverVo.getVinCode() != null
				&& !"".equals(sellCoverVo.getVinCode())) {
			sql.append(" and F.xs_car_vin_number like '%" + StringEscapeUtils.escapeSql(sellCoverVo.getVinCode().trim()) + "%'");
		}
		if (sellCoverVo.getXsCarSelId() != null
				&& !"".equals(sellCoverVo.getXsCarSelId())) {
			sql.append("  and B.xs_car_sel_id='" + sellCoverVo.getXsCarSelId()
					+ "'");
		}
		if (sellCoverVo.getSell_code() != null
				&& !"".equals(sellCoverVo.getSell_code())) {
			sql.append("  and B.sell_code='" + sellCoverVo.getSell_code()+ "'");
		}
		sql.append(" group by B.xs_car_sel_id");

		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setReserveId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setCarBrand(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setCarCode(obj[2] != null ? obj[2].toString() : null);
				sell.setCarModel(obj[3] != null ? Integer.parseInt(obj[3]
						.toString()) : null);
				sell.setCarColor(obj[4] != null ? Integer.parseInt(obj[4]
						.toString()) : null);
				sell.setCarBrandName(obj[5] != null ? obj[5].toString() : null);
				sell.setCarColorName(obj[6] != null ? obj[6].toString() : null);
				sell.setCarModelName(obj[7] != null ? obj[7].toString() : null);
				sell.setVinCode(obj[8] != null ? obj[8].toString() : null);
				sell.setCustomId(obj[9] != null ? Integer.parseInt(obj[9]
						.toString()) : null);
				sell.setXsCustomName(obj[10] != null ? obj[10].toString()
						: null);
				sell.setXsCarSelId(obj[11] != null ? Integer.parseInt(obj[11]
						.toString()) : null);
				sell.setXsCarSelData(obj[12] != null ? obj[12].toString()
						: null);
				sell.setXsCarSelRemark(obj[13] != null ? obj[13].toString()
						: null);
				sell.setConsultDegree(obj[14] != null ? Integer
						.parseInt(obj[14].toString()) : null);
				sell.setConsultDegreeType(obj[15] != null ? obj[15].toString()
						: null);
				sell.setXsCustomPhone(obj[16] != null ? obj[16].toString()
						: null);
				sell.setXsCustomTelephone(obj[17] != null ? obj[17].toString()
						: null);
				sell.setXsCustomApplication(obj[18] != null ? Integer
						.parseInt(obj[18].toString()) : null);
				sell.setXsCustomApplicationN(obj[19] != null ? obj[19]
						.toString() : null);
				sell.setXsCustomAddress(obj[20] != null ? obj[20].toString()
						: null);
				sell.setXsCustomOccupation(obj[21] != null ? Integer
						.parseInt(obj[21].toString()) : null);
				sell.setXsCustomOccupationN(obj[22] != null ? obj[22]
						.toString() : null);
				sell.setXsCustomIncome(obj[23] != null ? Integer
						.parseInt(obj[23].toString()) : null);
				sell.setXsCustomIncomeN(obj[24] != null ? obj[24].toString()
						: null);
				sell.setXsCustomDiploma(obj[25] != null ? Integer
						.parseInt(obj[25].toString()) : null);
				sell.setXsCustomDiplomaN(obj[26] != null ? obj[26].toString()
						: null);
				sell
						.setXsCustomBirthday(obj[27] != null ? ((Timestamp) obj[27])
								: null);
				sell.setXsCustomCredentials(obj[28] != null ? obj[28]
						.toString() : null);
				sell.setXsCustomSex(obj[29] != null ? Integer.parseInt(obj[29]
						.toString()) : null);
				sell.setSexName(obj[30] != null ? obj[30].toString() : null);
				sell.setStfId(obj[31] != null ? Integer.parseInt(obj[31]
						.toString()) : null);
				sell.setStfName(obj[32] != null ? obj[32].toString() : null);
				/*sell.setXsCarSelId(obj[33] != null ? Integer.parseInt(obj[33]
						.toString()) : null);*/

				sell.setCarMotorNumber(obj[33] != null ? obj[33].toString()
						: null);
				sell.setCarLicensePlate(obj[34] != null ? obj[34].toString()
						: null);
				sell.setCarSellState(obj[35] != null ? obj[35]
						.toString() : null);
				sell.setCarSellStateN(obj[36] != null ? obj[36].toString()
						: null);
				sell.setPactCode(obj[37] != null ? obj[37].toString() : null);
				sell.setAuditDate(obj[38] != null ? obj[38].toString() : null);
				sell.setConsultActualDate(obj[39] != null ?  fmt.format(obj[39])
								: null);
				sell.setConsulTRate(obj[40] != null ? Integer.parseInt(obj[40]
						.toString()) : null);
				sell.setConsulTRateN(obj[41] != null ? obj[41].toString()
						: null);
				sell.setCarOutputVolume(obj[42] != null ? Integer.parseInt(obj[42].toString()) : null);
				sell.setLimitLoadNum(obj[43] != null ? Integer.parseInt(obj[43].toString()) : null);
				sell.setTravelCourse(obj[44] != null ?Double.parseDouble(obj[44].toString()) : null);
				sell.setXsCustomZipcode(obj[45] != null ?obj[45].toString() : null);
				sell.setConsultCallState(obj[46] != null ? Integer
						.parseInt(obj[46].toString()) : null);
				sell.setConsultCallStateN(obj[47] != null ? obj[47].toString()
						: null);
				sell.setSell_code(obj[48] != null ? obj[48].toString()
						: null);
				list.add(sell);

			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
//售后回访分析模块：查询回访记录
	
	public Datagrid getSellCoverMange(SellCoverVo sellCoverVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer("SELECT A.consult_id,A.xs_car_sel_id,A.consult_actual_date," +
				"	A.consult_degree,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.consult_degree) AS degree," +
				"	A.consult_rate,b.xs_repay_name,A.travel_course,A.consult_suggest,A.remark,A.consult_call_state," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.consult_call_state) AS state," +
				"D.xs_car_model_id,E.xs_model_name,	D.xs_car_color," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.xs_car_color) AS color," +
				"D.xs_car_vin_number,d.xs_car_motor_number,d.xs_car_licensePlate,C.xs_car_sel_data,C.xs_car_sel_remark," +
				"C.custom_id,F.xs_custom_name,F.xs_custom_sex," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_custom_sex) AS sex," +
				"F.xs_custom_phone,F.xs_custom_telephone,F.xs_custom_property," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_custom_property) AS application," +
				"F.xs_custom_address,f.xs_custom_trade," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =f.xs_custom_trade) AS trade," +
				"F.xs_custom_income,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_custom_income) AS income," +
				"F.xs_custom_birthday,F.xs_custom_diploma," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_custom_diploma) AS diploma," +
				"f.xs_custom_credentials,f.STF_ID,G.STF_NAME " +
				"FROM 	xs_sell_cover A " +
				"LEFT JOIN xs_repay B ON a.consult_rate=b.xs_repay_id " +
				" join xs_car_sell_info c ON c.xs_car_sel_id=A.xs_car_sel_id " +
				" join xs_car_info D ON D.xs_car_id=c.xs_car_id " +
				"LEFT JOIN xs_car_model E ON E.xs_model_id=D.xs_car_model_id " +
				" JOIN xs_custom_info F ON F.custom_id=C.custom_id	" +
				"LEFT JOIN bas_stuff g ON G.STF_ID=F.STF_ID " +
				"where A.consult_actual_date is not Null");
		if (sellCoverVo.getEnterpriseId() != null
				&& !"".equals(sellCoverVo.getEnterpriseId())) {
			sql.append("  and a.enterprise_id ='" + sellCoverVo.getEnterpriseId()
					+ "'");
		}
		
			if (sellCoverVo.getConsultActualDate() != null
					&& !"".equals(sellCoverVo.getConsultActualDate())) {
						sql.append(" and DATE(A.consult_actual_date) >= '"+sellCoverVo.getConsultActualDate()+"'");
		}
			if (sellCoverVo.getConsultActualDate2() != null
					&& !"".equals(sellCoverVo.getConsultActualDate2())) {
						sql.append(" and DATE(A.consult_actual_date) <= '"+sellCoverVo.getConsultActualDate2()+"'");
	
		}
			if((sellCoverVo.getConsultActualDate() == null
					||"".equals(sellCoverVo.getConsultActualDate()))&&
					(sellCoverVo.getConsultActualDate2() == null
							||"".equals(sellCoverVo.getConsultActualDate2()))){
			sql.append(" and DATE(A.consult_actual_date) BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()))+"'" +
							" AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}
		
		if (sellCoverVo.getXsCarSelData()!= null
				&& !"".equals(sellCoverVo.getXsCarSelData())) {
			
					sql.append( " and  DATE(C.xs_car_sel_data) >= '"+sellCoverVo.getXsCarSelData()+"'" );
				}
		if (sellCoverVo.getXsCarSelData2()!= null
				&& !"".equals(sellCoverVo.getXsCarSelData2())) {
			
					sql.append( " and  DATE(C.xs_car_sel_data) <= '"+sellCoverVo.getXsCarSelData2()+"'" );
				}
	
	
		if (sellCoverVo.getCarModel() != null
				&& !"".equals(sellCoverVo.getCarModel())) {
			sql.append(" and D.xs_car_model_id='" + sellCoverVo.getCarModel()+ "'");
		}
		if (sellCoverVo.getXsCustomName() != null
				&& !"".equals(sellCoverVo.getXsCustomName())) {
			sql.append(" and F.xs_custom_name like '%" + StringEscapeUtils.escapeSql(sellCoverVo.getXsCustomName().trim())+ "%'");
		}
		if (sellCoverVo.getConsulTRate() != null
				&& !"".equals(sellCoverVo.getConsulTRate())) {
			sql.append(" and A.consult_rate='" + sellCoverVo.getConsulTRate()+ "'");
		}
		if (sellCoverVo.getDeptId() != null
				&& !"".equals(sellCoverVo.getDeptId())) {
			sql.append(" and g.DEPT_ID='" + sellCoverVo.getDeptId()+ "'");
		}
		
		if (sellCoverVo.getConsultDegree() != null
				&& !"".equals(sellCoverVo.getConsultDegree())) {
			sql.append(" and A.consult_degree='" + sellCoverVo.getConsultDegree()+ "'");
		}
		if (sellCoverVo.getConsultCallState() != null
				&& !"".equals(sellCoverVo.getConsultCallState())) {
			sql.append(" and A.consult_call_state='" + sellCoverVo.getConsultCallState()+ "'");
		}
		if (sellCoverVo.getStfId() != null
				&& !"".equals(sellCoverVo.getStfId())) {
			sql.append(" and f.STF_ID='" + sellCoverVo.getStfId()+ "'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setConsultId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setXsCarSelId(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setConsultActualDate(obj[2] != null ?  fmt.format(obj[2])
						: null);
				sell.setConsultDegree(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
			    sell.setConsultDegreeType(obj[4] != null ? obj[4].toString(): null);
				sell.setConsulTRate(obj[5] != null ? Integer.parseInt(obj[5].toString()) : null);
				sell.setConsulTRateN(obj[6] != null ? obj[6].toString() : null);
				sell.setTravelCourse(obj[7] != null ? Double.parseDouble(obj[7].toString()) : null);
				sell.setConsultSuggest(obj[8] != null ? obj[8].toString(): null);
				sell.setRemark(obj[9] != null ? obj[9].toString() : null);
				sell.setConsultCallState(obj[10] != null ? Integer
						.parseInt(obj[10].toString()) : null);
				sell.setConsultCallStateN(obj[11] != null ? obj[11].toString()
						: null);
				sell.setCarModel(obj[12] != null ? Integer.parseInt(obj[12].toString()) : null);
				sell.setCarModelName(obj[13] != null ? obj[13].toString() : null);
				sell.setCarColor(obj[14] != null ? Integer.parseInt(obj[14].toString()) : null);
				sell.setCarColorName(obj[15] != null ? obj[15].toString() : null);
				sell.setVinCode(obj[16] != null ? obj[16].toString() : null);
				sell.setCarMotorNumber(obj[17] != null ? obj[17].toString() : null);
				sell.setCarLicensePlate(obj[18] != null ? obj[18].toString() : null);				
				sell.setXsCarSelData(obj[19] != null ?obj[19].toString(): null);
				sell.setXsCarSelRemark(obj[20] != null ? obj[20].toString(): null);
				sell.setCustomId(obj[21] != null ? Integer.parseInt(obj[21].toString()): null);
				sell.setXsCustomName(obj[22] != null ? obj[22].toString(): null);
				sell.setXsCustomSex(obj[23] != null ? Integer.parseInt(obj[23].toString()): null);
				sell.setSexName(obj[24] != null ? obj[24].toString(): null);
				sell.setXsCustomPhone(obj[25] != null ? obj[25].toString(): null);
				sell.setXsCustomTelephone(obj[26] != null ? obj[26].toString(): null);
				sell.setXsCustomProperty(obj[27] != null ? Integer.parseInt(obj[27].toString()): null);
				sell.setCustomPropertyN(obj[28] != null ? obj[28].toString(): null);
				sell.setXsCustomAddress(obj[29] != null ? obj[29].toString(): null);				
				sell.setXsCustomTrade(obj[30] != null ? Integer.parseInt(obj[30].toString()): null);
				sell.setCustomTrade(obj[31] != null ? obj[31].toString(): null);
				sell.setXsCustomIncome(obj[32] != null ? Integer.parseInt(obj[32].toString()): null);
				sell.setXsCustomIncomeN(obj[33] != null ? obj[33].toString(): null);
				sell.setXsCustomBirthday(obj[34] != null ? ((Timestamp) obj[34]): null);
				sell.setXsCustomDiploma(obj[35] != null ? Integer.parseInt(obj[35].toString()): null);
				sell.setXsCustomDiplomaN(obj[36] != null ? obj[36].toString(): null);
				sell.setXsCustomCredentials(obj[37] != null ? obj[37].toString(): null);
				sell.setStfId(obj[38] != null ? Integer.parseInt(obj[38].toString()) : null);
				sell.setStfName(obj[39] != null ? obj[39].toString() : null);
				list.add(sell);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	
	public Datagrid getProjectStatistics(SellCoverVo sellCoverVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(" SELECT dd.temp," +
				" (SELECT CASE WHEN dd.temp1!=0 THEN dd.temp1 ELSE '' END ) AS temp1," +
				" (SELECT CASE WHEN dd.temp2!=0 THEN dd.temp2 ELSE '' END ) AS temp2," +
				" (SELECT CASE WHEN dd.temp3!=0 THEN dd.temp3 ELSE '' END ) AS temp3," +
				" (SELECT CASE WHEN dd.temp4!=0 THEN dd.temp4 ELSE '' END ) AS temp4," +
				" (SELECT CASE WHEN dd.temp5!=0 THEN dd.temp5 ELSE '' END ) AS temp5" +
				" FROM (" +
				" SELECT cc.temp," +
				" SUM(cc.temp1) AS temp1, SUM(cc.temp2) AS temp2," +
				" SUM(cc.temp3) AS temp3,SUM(cc.temp4) AS temp4," +
				"SUM(cc.temp5) AS temp5 " +
				"FROM (" +
				" SELECT   bb.temp," +
				" (SELECT CASE WHEN bb.temp2=(SELECT child.child_id FROM xs_childdictionary  child," +
				"	xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						"AND child.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE1+"'" +
								" and  child.enterprise_id="+sellCoverVo.getEnterpriseId()+" ) " +
						"THEN 1 ELSE 0 END ) AS temp1," +
				" (SELECT CASE WHEN bb.temp2=(SELECT child.child_id FROM xs_childdictionary  child," +
				"	xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						"AND child.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE2+"'" +
						" and  child.enterprise_id="+sellCoverVo.getEnterpriseId()+") " +
						"THEN 1 ELSE 0 END ) AS temp2," +
				" (SELECT CASE WHEN bb.temp2=(SELECT child.child_id FROM xs_childdictionary  child," +
				"	xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						"AND child.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE3+"'" +
						" and  child.enterprise_id="+sellCoverVo.getEnterpriseId()+")" +
						" THEN 1 ELSE 0 END ) AS temp3," +
				" (SELECT CASE WHEN bb.temp2=(SELECT child.child_id FROM xs_childdictionary  child," +
				"	xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						"AND child.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE4+"'" +
						" and  child.enterprise_id="+sellCoverVo.getEnterpriseId()+")" +
						" THEN 1 ELSE 0 END ) AS temp4," +
				" (SELECT CASE WHEN bb.temp2=(SELECT child.child_id FROM xs_childdictionary  child," +
				"	xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						"AND child.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE5+"" +
						" and  child.enterprise_id="+sellCoverVo.getEnterpriseId()+"')" +
						" THEN 1 ELSE 0 END ) AS temp5" +
				" FROM (" +
				" SELECT xrp.xs_project_name AS temp,xscc.content_id temp1,xscc.project_evaluate temp2" +
				" FROM xs_sell_cover_content xscc " +
				"INNER JOIN xs_repay_project xrp ON xrp.xs_project_id=xscc.project_id" +
				" INNER JOIN  xs_sell_cover co ON xscc.consult_id=co.consult_id" +
				" INNER JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=co.xs_car_sel_id" +
				" INNER JOIN xs_car_info car ON car.xs_car_id=sell.xs_car_id " +
				" INNER JOIN xs_custom_info custom ON custom.custom_id = sell.custom_id " +
				" INNER JOIN bas_stuff stf ON stf.STF_ID = custom.STF_ID " +
				" INNER JOIN  (SELECT xc.child_id,xc.dataValue FROM " +
				"xs_parentdictionary xp INNER JOIN xs_childdictionary xc" +
				" ON xp.parent_id=xc.parent_id AND xp.dataKey='"+Contstants.BASE_SELL.PROJECTEVALUATE+"' " +
						" where xc.enterprise_id="+sellCoverVo.getEnterpriseId()+") aa" +
				"	ON aa.child_id=xscc.project_evaluate " +
				" where 1=1 ");
		if(sellCoverVo.getEnterpriseId()!=null&&!"".equals(sellCoverVo.getEnterpriseId())){
			sql.append(" and sell.enterprise_id ="+sellCoverVo.getEnterpriseId());
		}
		if (sellCoverVo.getConsultActualDate() != null
						&& !"".equals(sellCoverVo.getConsultActualDate())) {
							sql.append(" and DATE(co.consult_actual_date) >= '"+sellCoverVo.getConsultActualDate()+"'");
		}
		if (sellCoverVo.getConsultActualDate2() != null
						&& !"".equals(sellCoverVo.getConsultActualDate2())) {
							sql.append(" and DATE(co.consult_actual_date)  <= '"+sellCoverVo.getConsultActualDate2()+"'");
	
		}
		if((sellCoverVo.getConsultActualDate() == null
				||"".equals(sellCoverVo.getConsultActualDate()))&&
				(sellCoverVo.getConsultActualDate2() == null
						||"".equals(sellCoverVo.getConsultActualDate2()))){
			sql.append(" and DATE(co.consult_actual_date)  BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()))+"'" +
							" AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}
		if (sellCoverVo.getConsulTRate() != null
				&& !"".equals(sellCoverVo.getConsulTRate())) {
			sql.append(" and co.consult_rate='" + sellCoverVo.getConsulTRate()+ "'");
		}
		//销售日期
		if(sellCoverVo.getXsCarSelData()!=null && !sellCoverVo.getXsCarSelData().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) >= '"+sellCoverVo.getXsCarSelData()+"'" );
		}
		if(sellCoverVo.getXsCarSelData2()!=null && !sellCoverVo.getXsCarSelData2().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) <= '"+sellCoverVo.getXsCarSelData2()+"'" );
		}
		// 品牌
		if (sellCoverVo.getCarBrand() != null
				&& !sellCoverVo.getCarBrand().equals("")) {
			sql
					.append(" and car.xs_car_brand=" + sellCoverVo.getCarBrand()
							+ "");
		}
		// 业务员
		if (sellCoverVo.getStfId() != null
				&& !sellCoverVo.getStfId().equals("")) {
			sql.append(" and custom.stf_id=" + sellCoverVo.getStfId() + "");
		}
		
		// 部门
		if (sellCoverVo.getDeptId() != null
				&& !sellCoverVo.getDeptId().equals("")) {
			sql.append(" and stf.DEPT_ID=" + sellCoverVo.getDeptId() + "");
		}
		sql.append("" +	") bb  " +
				") cc GROUP BY cc.temp" +
				" ) dd ");
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			int count=0;
			int tempCount=0;
			for (int i = 0; i < resultList.size(); i++) {
				count=0;
				tempCount=0;
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setProjectName(obj[0] != null ? obj[0].toString() : null);
				sell.setVeryGood(obj[1] != null ? obj[1].toString() : null);
				sell.setGood(obj[2] != null ? obj[2].toString() : null);
				sell.setYiBan(obj[3] != null ? obj[3].toString() : null);
				sell.setNotGood(obj[4] != null ? obj[4].toString() : null);
				sell.setVeryNotGood(obj[5] != null ? obj[5].toString() : null);
				if(sell.getVeryGood()!=null&&sell.getVeryGood().length()>0){
					count+=Integer.parseInt(sell.getVeryGood());
					tempCount+=Integer.parseInt(sell.getVeryGood());
				}
				if(sell.getGood()!=null&&sell.getGood().length()>0){
					count+=Integer.parseInt(sell.getGood());
					tempCount+=Integer.parseInt(sell.getGood());
				}
				if(sell.getYiBan()!=null&&sell.getYiBan().length()>0)
					count+=Integer.parseInt(sell.getYiBan());
				if(sell.getNotGood()!=null&&sell.getNotGood().length()>0)
					count+=Integer.parseInt(sell.getNotGood());
				if(sell.getVeryNotGood()!=null&&sell.getVeryNotGood().length()>0)
					count+=Integer.parseInt(sell.getVeryNotGood());
				sell.setSumCount(count);
				sell.setRate(Double.parseDouble(tempCount+"")/Double.parseDouble(count+"")*100);
				list.add(sell);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
	/**查询回访及时性分析信息*/
	
	public Datagrid geTtimelyAnalysis(SellCoverVo sellCoverVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer("SELECT bb.tag,COUNT(bb.temp1),SUM(bb.temp2),SUM(bb.temp3),SUM(bb.temp4),SUM(bb.temp5)," +
				"(SUM(bb.temp3)/COUNT(bb.temp1))*100 FROM (SELECT aa.tag,aa.temp1,(SELECT CASE WHEN ss.sumCount!=''  THEN ss.sumCount ELSE 0 END ) AS temp2," +
				"(SELECT CASE WHEN aa.temp2!='' AND aa.temp2=aa.temp1 THEN 1 ELSE 0 END ) AS temp3," +
				"(SELECT CASE WHEN aa.temp2!='' AND aa.temp2!=aa.temp1 THEN 1 ELSE 0 END ) AS temp4," +
				"(SELECT CASE WHEN aa.temp0=1 THEN 1 ELSE 0 END ) AS temp5" +
				"	FROM (" +
				"	SELECT DATE(xsc.consult_plan_date) AS tag," +
				"	DATE(xsc.consult_plan_date) AS temp1," +
				"	DATE(xsc.consult_actual_date) AS temp2," +
				"   (SELECT CASE WHEN DATE(xsc.consult_actual_date)!=''  THEN 0 ELSE 1 END ) AS temp0" +
				"	FROM xs_sell_cover xsc " +
				" join xs_car_sell_info sell ON sell.xs_car_sel_id=xsc.xs_car_sel_id " +
				" JOIN xs_car_info car ON sell.xs_car_id = car.xs_car_id " +
				" JOIN xs_custom_info custom ON custom.custom_id = sell.custom_id " +
				"JOIN bas_stuff stf ON stf.STF_ID = custom.STF_ID " +
				" WHERE  1=1 ");
		if(sellCoverVo.getEnterpriseId()!=null&&!"".equals(sellCoverVo.getEnterpriseId())){
			sql.append(" and xsc.enterprise_id ="+sellCoverVo.getEnterpriseId());
		}
		if (sellCoverVo.getConsultActualDate() != null
						&& !"".equals(sellCoverVo.getConsultActualDate())) {
							sql.append(" and DATE(xsc.consult_plan_date) >= '"+sellCoverVo.getConsultActualDate()+"'");
		}
		if (sellCoverVo.getConsultActualDate2() != null
						&& !"".equals(sellCoverVo.getConsultActualDate2())) {
							sql.append(" and DATE(xsc.consult_plan_date) <= '"+sellCoverVo.getConsultActualDate2()+"'");
			
		}
		if((sellCoverVo.getConsultActualDate() == null
				||"".equals(sellCoverVo.getConsultActualDate()))&&(sellCoverVo.getConsultActualDate2()== null
						||"".equals(sellCoverVo.getConsultActualDate2()))){
			sql.append(" and DATE(xsc.consult_plan_date) BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()))+"'" +
							" AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}
		// 回访进度
		if (sellCoverVo.getConsulTRate() != null
				&& !"".equals(sellCoverVo.getConsulTRate())) {
			sql.append(" and xsc.consult_rate='" + sellCoverVo.getConsulTRate()+ "'");
		}
		//销售日期
		if(sellCoverVo.getXsCarSelData()!=null && !sellCoverVo.getXsCarSelData().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) >= '"+sellCoverVo.getXsCarSelData()+"'" );
		}
		if(sellCoverVo.getXsCarSelData2()!=null && !sellCoverVo.getXsCarSelData2().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) <= '"+sellCoverVo.getXsCarSelData2()+"'" );
		}
		// 品牌
		if (sellCoverVo.getCarBrand() != null
				&& !sellCoverVo.getCarBrand().equals("")) {
			sql
					.append(" and car.xs_car_brand=" + sellCoverVo.getCarBrand()
							+ "");
		}
		// 业务员
		if (sellCoverVo.getStfId() != null
				&& !sellCoverVo.getStfId().equals("")) {
			sql.append(" and custom.stf_id=" + sellCoverVo.getStfId() + "");
		}
		
		// 部门
		if (sellCoverVo.getDeptId() != null
				&& !sellCoverVo.getDeptId().equals("")) {
			sql.append(" and stf.DEPT_ID=" + sellCoverVo.getDeptId() + "");
		}
		
		
		sql.append(") aa " +
				" LEFT OUTER JOIN " +
				" (SELECT DATE(xscs.consult_actual_date) AS temps,COUNT(xscs.consult_actual_date) AS sumCount" +
				" FROM xs_sell_cover xscs GROUP BY DATE(xscs.consult_actual_date)) ss ON ss.temps=aa.tag" +
				") bb GROUP BY bb.tag ");
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setConsultPlanDate(obj[0] != null ? obj[0].toString() : null);
				sell.setPlanDate(obj[1] != null ? Integer.parseInt(obj[1].toString()): null);
				sell.setActualDate(obj[2] != null ? Integer.parseInt(obj[2].toString()): null);
				sell.setPlanIn(obj[3] != null ? Integer.parseInt(obj[3].toString()): null);
				sell.setPlanOut(obj[4] != null ? Integer.parseInt(obj[4].toString()): null);
				sell.setNottrack(obj[5] != null ? Integer.parseInt(obj[5].toString()): null);
				sell.setTimely(obj[6] != null ? Double.parseDouble(obj[6].toString()): null);
				
				list.add(sell);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	

	}


