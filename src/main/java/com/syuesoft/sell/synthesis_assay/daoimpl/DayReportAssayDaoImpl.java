package com.syuesoft.sell.synthesis_assay.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.dao.DayReportAssayDao;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;
import com.syuesoft.util.FormatTime;

@Repository("dayReportAssayDao")
public class DayReportAssayDaoImpl extends BaseDaoImpl implements
		DayReportAssayDao {

	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	public Datagrid queryCarAndCustom(DayReportAssayVo dayReportAssayVo)
			throws Exception {
				Datagrid dg = new Datagrid();
			StringBuffer sql = new StringBuffer("SELECT a.xs_car_brand,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id= a.xs_car_brand)  AS brand," +
					"a.xs_car_model_id,b.xs_model_name,a.xs_car_color,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id= a.xs_car_color)  AS color," +
					"c.xs_car_sel_transaction_money, a.xs_car_vin_number,a.xs_car_motor_number, c.xs_car_sel_data," +
					"c.xs_car_stf_id,d.STF_NAME,a.upData,a.upPerson," +
					"( SELECT SS.STF_NAME FROM   bas_stuff  SS WHERE  SS.STF_ID=a.upPerson) AS upPer," +
					"e.xs_custom_name,e.xs_custom_sex,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_sex) AS sex," +
					"e.xs_custom_diploma,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_diploma)  AS diploma," +
					"e.xs_custom_income,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_income)  AS income," +
					"e.xs_custom_trade,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_trade)  AS trade," +
					"e.xs_custom_area,(SELECT datakey FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_area)  AS ddq," +
					"e.xs_custom_phone,e.xs_custom_telephone,e.xs_custom_birthday,e.xs_custom_company,e.xs_custom_address," +
					"e.xs_custom_zipcode,e.xs_custom_property,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_property)  AS property," +
					"g.pact_code ,f.invoice_code,a.xs_distributor_id,h.xs_distributor_name," +
					"i.consult_actual_date,i.consult_degree,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=i.consult_degree)  AS degree, " +
					"a.xs_car_id " +
					"FROM xs_car_info A" +
					" JOIN xs_car_model B ON a.xs_car_model_id=b.xs_model_id " +
					"JOIN xs_car_sell_info C ON c.xs_car_id=a.xs_car_id " +
					"JOIN bas_stuff D ON d.STF_ID=c.xs_car_stf_id " +
					"JOIN xs_custom_info E ON e.custom_id=c.custom_id " +
					"LEFT JOIN xs_sell_invoice F ON f.xs_car_sel_id=c.xs_car_sel_id " +
					"LEFT JOIN xs_sell_car_reserve G ON g.reserve_id=c.reserve_id " +
					"LEFT JOIN xs_distributor_info H ON h.xs_distributor_id=a.xs_distributor_id " +
					"JOIN xs_sell_retreat J ON j.retreat_id=c.out_id " +
					"LEFT  JOIN (SELECT * FROM  xs_sell_cover k  WHERE k.consult_actual_date IN " +
					" (SELECT MAX(consult_actual_date)FROM xs_sell_cover GROUP BY xs_sell_cover.xs_car_sel_id)) AS I " +
					" ON I.xs_car_sel_id=c.xs_car_sel_id " +
					" WHERE a.xs_car_sell_state IN (SELECT child.child_id FROM xs_childdictionary  child, " +
					"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
					"AND parent.dataKey='"+Contstants.SELLSTATE.BASE_SELLSTATE+"' AND " +
					"child.dataKey IN ('"+Contstants.SELLSTATE.SELLOUT+"','"+Contstants.SELLSTATE.AFTERSELL+"'))");
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				sql .append( " and A.enterprise_id="+dayReportAssayVo.getEnterprise_id());
			}
			if(dayReportAssayVo.getXsCarVinNumber()!=null && !dayReportAssayVo.getXsCarVinNumber().equals("")){
				sql .append( " and a.xs_car_vin_number like'%"+dayReportAssayVo.getXsCarVinNumber()+"%'");
			}
			if(dayReportAssayVo.getXsCustomName()!=null && !dayReportAssayVo.getXsCustomName().equals("")){
				sql .append( " and e.xs_custom_name like'%"+dayReportAssayVo.getXsCustomName()+"%'");
			}
			if(dayReportAssayVo.getXsDistributorId()!=null && !dayReportAssayVo.getXsDistributorId().equals("")){
				sql .append( " and a.xs_distributor_id ="+dayReportAssayVo.getXsDistributorId()+"");
			}
			if(dayReportAssayVo.getConsultDegree()!=null && !dayReportAssayVo.getConsultDegree().equals("")){
				sql .append( " and i.consult_degree ="+dayReportAssayVo.getConsultDegree()+"");
			}
			if(dayReportAssayVo.getCarBrand()!=null && !dayReportAssayVo.getCarBrand().equals("")){
				sql .append( " and a.xs_car_brand ="+dayReportAssayVo.getCarBrand()+"");
			}
			if(dayReportAssayVo.getCarModel()!=null && !dayReportAssayVo.getCarModel().equals("")){
				sql .append( " and a.xs_car_model_id ="+dayReportAssayVo.getCarModel()+"");
			}
			if(dayReportAssayVo.getPact_code()!=null && !dayReportAssayVo.getPact_code().equals("")){
				sql .append( " and g.pact_code like '%"+dayReportAssayVo.getPact_code()+"%'");
			}
			if(dayReportAssayVo.getCarType()!=null && !dayReportAssayVo.getCarType().equals("")){
				sql .append( " and a.xs_car_type ="+dayReportAssayVo.getCarType()+"");
			}
			if(dayReportAssayVo.getNoUp()!=null && !"".equals(dayReportAssayVo.getNoUp())&&"yes".equals(dayReportAssayVo.getNoUp())){
				sql .append( " and a.upPerson is NULL" );
			}
			
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
				sql.append(" and DATE(c.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'");
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
				sql.append(" and DATE(c.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'");
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				sql.append(" and DATE(c.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'") ;
			}
		
	
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
						sql.append(" and J.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'");
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
						sql.append(" and J.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'");
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
						sql.append(" and a.upData >= '" + dayReportAssayVo.getUpData() + "'");
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
						sql.append(" and a.upData <= '" + dayReportAssayVo.getUpData2() + "'");
			}
			
			List<Object[]> resultList = createSQLQuery(sql.toString(),
					dayReportAssayVo.getPage(), dayReportAssayVo.getRows());
			List<DayReportAssayVo> list = new ArrayList<DayReportAssayVo>();
			if (resultList != null && resultList.size() > 0) {
				
				Object[] obj = null;
				for (int i = 0; i < resultList.size(); i++) {
					DayReportAssayVo sell = new DayReportAssayVo();
					obj = resultList.get(i);
					sell.setCarBrand(obj[0] != null ?obj[0].toString() : null);
					sell.setCarBrandName(obj[1] != null ?obj[1].toString() : null);
					sell.setCarModel(obj[2] != null ?obj[2].toString() : null);
					sell.setCarModelName(obj[3] != null ?obj[3].toString() : null);
					sell.setCarColor(obj[4] != null ?Integer.parseInt(obj[4].toString()) : null);
					sell.setCarColorName(obj[5] != null ?obj[5].toString() : null);
					sell.setModelSalesPrice(obj[6] != null ?Double.parseDouble(obj[6].toString()) : null);
					sell.setCarVinNumber(obj[7] != null ?obj[7].toString() : null);
					sell.setCarMotorNumber(obj[8] != null ?obj[8].toString() : null);
					sell.setSellDate(obj[9] != null ?obj[9].toString() : null);
					sell.setStfId(obj[10] != null ?Integer.parseInt(obj[10].toString()) : null);
					sell.setStfName(obj[11] != null ?obj[11].toString() : null);
					sell.setUpData(obj[12] != null ?obj[12].toString() : null);
					sell.setUpPer(obj[13] != null ?Integer.parseInt(obj[13].toString()) : null);
					sell.setUpPerson(obj[14] != null ?obj[14].toString() : null);
					sell.setCustomName(obj[15] != null ?obj[15].toString() : null);
					sell.setSex(obj[16] != null ?Integer.parseInt(obj[16].toString()) : null);
					sell.setCustomSex(obj[17] != null ?obj[17].toString() : null);
					sell.setXsCustomDiploma(obj[18] != null ?Integer.parseInt(obj[18].toString()) : null);
					sell.setXsCustomDiplomaN(obj[19] != null ?obj[19].toString() : null);
					sell.setXsCustomIncome(obj[20] != null ?Integer.parseInt(obj[20].toString()) : null);
					sell.setXsCustomIncomeN(obj[21] != null ?obj[21].toString() : null);
					sell.setXsCustomTrade(obj[22] != null ?Integer.parseInt(obj[22].toString()) : null);
					sell.setXsCustomTradeN(obj[23] != null ?obj[23].toString() : null);
					sell.setXsCustomArea(obj[24] != null ?Integer.parseInt(obj[24].toString()) : null);
					sell.setXsCustomAreaN(obj[25] != null ?obj[25].toString() : null);
					sell.setXsCustomPhone(obj[26] != null ?obj[26].toString() : null);
					sell.setXsCustomTelephone(obj[27] != null ?obj[27].toString() : null);
					sell.setXsCustomBirthday(obj[28] != null ?obj[28].toString() : null);
					sell.setXsCustomCompany(obj[29] != null ?obj[29].toString() : null);
					sell.setXsCustomAddress(obj[30] != null ?obj[30].toString() : null);
					sell.setXsCustomZipcode(obj[31] != null ?obj[31].toString() : null);
					sell.setXsCustomProperty(obj[32] != null ?Integer.parseInt(obj[32].toString()) : null);
					sell.setXsCustomPropertyN(obj[33] != null ?obj[33].toString() : null);
					sell.setPact_code(obj[34] != null ?obj[34].toString() : null);
					sell.setInvoice_code(obj[35] != null ?obj[35].toString() : null);
					sell.setXsDistributorId(obj[36] != null ?obj[36].toString() : null);
					sell.setDistributorName(obj[37] != null ?obj[37].toString() : null);
					sell.setConsult_actual_date(obj[38] != null ?obj[38].toString() : null);
					sell.setConsultDegree(obj[39] != null ?obj[39].toString() : null);
					sell.setConsultDegreeN(obj[40] != null ?obj[40].toString() : null);
					sell.setCarId(obj[41] != null ?Integer.parseInt(obj[4].toString()) : null);
					list.add(sell);

				}
			}
			int total = this.getSQLCount(sql.toString(), null);
			dg.setRows(list);
			dg.setTotal(total);
			return dg;
	}

	
	public Datagrid getSellDayReport(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer("SELECT a.xs_car_brand,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id= a.xs_car_brand)  AS brand," +
				"a.xs_car_model_id,b.xs_model_name,a.xs_car_color,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id= a.xs_car_color)  AS color," +
				"c.xs_car_sel_transaction_money, a.xs_car_vin_number,a.xs_car_motor_number, c.xs_car_sel_data," +
				"c.xs_car_stf_id,d.STF_NAME,a.upData,a.upPerson," +
				"( SELECT SS.STF_NAME FROM   bas_stuff  SS WHERE  SS.STF_ID=a.upPerson) AS upPer," +
				"e.xs_custom_name,e.xs_custom_sex,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_sex) AS sex," +
				"e.xs_custom_diploma,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_diploma)  AS diploma," +
				"e.xs_custom_income,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_income)  AS income," +
				"e.xs_custom_trade,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_trade)  AS trade," +
				"e.xs_custom_area,(SELECT datakey FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_area)  AS ddq," +
				"e.xs_custom_phone,e.xs_custom_telephone,e.xs_custom_birthday,e.xs_custom_company,e.xs_custom_address," +
				"e.xs_custom_zipcode,e.xs_custom_property,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=e.xs_custom_property)  AS property," +
				"g.pact_code ,f.invoice_code,a.xs_distributor_id,h.xs_distributor_name," +
				"i.consult_actual_date,i.consult_degree,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=i.consult_degree)  AS degree, " +
				"a.xs_car_id,a.xs_car_ocn,b.xs_model_costPrice,a.xs_car_price,SUM(zh.decorate_amount),SUM(zh.decorate_sell)," +
				"SUM(db.cost_price),SUM(db.db_project_cost),SUM(ins.prime_cost),SUM(ins.s_sum),SUM(f.invoice_parce)," +
				"e.xs_custom_credentials,heg.xs_car_certificate,heg.xs_car_certificate_state," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id=heg.xs_car_certificate_state) AS state," +
				"a.xs_car_make_data,house.instorehouse_date,che.check_date,che.check_comtent,f.invoice_number  " +
				"FROM xs_car_info A" +
				" JOIN xs_car_model B ON a.xs_car_model_id=b.xs_model_id " +
				"JOIN xs_car_sell_info C ON c.xs_car_id=a.xs_car_id " +
				"JOIN bas_stuff D ON d.STF_ID=c.xs_car_stf_id " +
				"JOIN xs_custom_info E ON e.custom_id=c.custom_id " +
				"LEFT JOIN xs_sell_invoice F ON f.xs_car_sel_id=c.xs_car_sel_id " +
				"LEFT JOIN xs_sell_car_reserve G ON g.reserve_id=c.reserve_id " +
				"LEFT JOIN xs_distributor_info H ON h.xs_distributor_id=a.xs_distributor_id " +
				" LEFT JOIN xs_sell_retreat J ON j.retreat_id=c.out_id " +
				" LEFT JOIN xs_sell_zh_project zh ON zh.xs_car_sel_id=c.xs_car_sel_id " +
				"LEFT JOIN xs_sell_db_project db ON db.xs_car_sel_id=c.xs_car_sel_id " +
				"LEFT JOIN xs_sell_insurance ins ON ins.xs_car_sel_id=c.xs_car_sel_id " +
				"LEFT JOIN xs_sell_certificate heg ON heg.xs_car_id=a.xs_car_id " +
				"JOIN xs_sell_instorehouse_del del ON del.xs_car_id=a.xs_car_id " +
				"JOIN xs_sell_instorehouse house ON house.instorehouse_id=del.instorehouse_id " +
				"LEFT JOIN xs_pdi_check che ON che.xs_car_sel_id=c.xs_car_sel_id " +
				"LEFT  JOIN (SELECT * FROM  xs_sell_cover k  WHERE k.consult_actual_date IN " +
				" (SELECT MAX(consult_actual_date)FROM xs_sell_cover GROUP BY xs_sell_cover.xs_car_sel_id)) AS I " +
				" ON I.xs_car_sel_id=c.xs_car_sel_id " +
				" WHERE a.xs_car_sell_state IN (SELECT child.child_id FROM xs_childdictionary  child, " +
				"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
				"AND parent.dataKey='"+Contstants.SELLSTATE.BASE_SELLSTATE+"' AND " +
				"child.dataKey IN ('"+Contstants.SELLSTATE.SELLOUT+"','"+Contstants.SELLSTATE.AFTERSELL+"'))");
		
		if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
			sql .append( " and A.enterprise_id="+dayReportAssayVo.getEnterprise_id());
		}
		if(dayReportAssayVo.getXsCarVinNumber()!=null && !dayReportAssayVo.getXsCarVinNumber().equals("")){
			sql .append( " and a.xs_car_vin_number like'%"+dayReportAssayVo.getXsCarVinNumber()+"%'");
		}
		if(dayReportAssayVo.getXsCustomName()!=null && !dayReportAssayVo.getXsCustomName().equals("")){
			sql .append( " and e.xs_custom_name like'%"+dayReportAssayVo.getXsCustomName()+"%'");
		}
	
		if(dayReportAssayVo.getCarBrand()!=null && !dayReportAssayVo.getCarBrand().equals("")){
			sql .append( " and a.xs_car_brand ="+dayReportAssayVo.getCarBrand()+"");
		}
		if(dayReportAssayVo.getCarModel()!=null && !dayReportAssayVo.getCarModel().equals("")){
			sql .append( " and a.xs_car_model_id ="+dayReportAssayVo.getCarModel()+"");
		}
		if(dayReportAssayVo.getXs_Car_Sel_Type()!=null && !dayReportAssayVo.getXs_Car_Sel_Type().equals("")){
			sql .append( " and c.xs_car_sel_type ="+dayReportAssayVo.getXs_Car_Sel_Type()+"");
		}
		if(dayReportAssayVo.getStfId()!=null && !dayReportAssayVo.getStfId().equals("")){
			sql .append( " and c.xs_car_stf_id ="+dayReportAssayVo.getStfId()+"");
		}
	
	
		
	
		if (dayReportAssayVo.getXsSellDate() != null
				&& !"".equals(dayReportAssayVo.getXsSellDate())) {

					sql.append(" and DATE(c.xs_car_sel_data) >= '" + dayReportAssayVo.getXsSellDate() + "'");
		}
		if (dayReportAssayVo.getXsSellDate2() != null
				&& !"".equals(dayReportAssayVo.getXsSellDate2())) {

			
					sql.append(" and DATE(c.xs_car_sel_data)<='" +dayReportAssayVo.getXsSellDate2()+ "'");
		}
		if((dayReportAssayVo.getXsSellDate() == null
				||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
				||dayReportAssayVo.getXsSellDate2().equals(""))){
	
			sql.append(" and DATE(c.xs_car_sel_data) between " +
					" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
				" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'") ;
		}
	
		if (dayReportAssayVo.getRetreatDate() != null
				&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
					sql.append(" and J.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'");
		} 
		if (dayReportAssayVo.getRetreatDate2() != null
				&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
					sql.append(" and J.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'");
		} 
		sql.append(" GROUP BY c.xs_car_sel_id");
		List<Object[]> resultList = createSQLQuery(sql.toString(),
				dayReportAssayVo.getPage(), dayReportAssayVo.getRows());
		List<DayReportAssayVo> list = new ArrayList<DayReportAssayVo>();
		if (resultList != null && resultList.size() > 0) {
			DayReportAssayVo sell=null;
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new DayReportAssayVo();
				obj = resultList.get(i);
				sell.setCarBrand(obj[0] != null ?obj[0].toString() : null);
				sell.setCarBrandName(obj[1] != null ?obj[1].toString() : null);
				sell.setCarModel(obj[2] != null ?obj[2].toString() : null);
				sell.setCarModelName(obj[3] != null ?obj[3].toString() : null);
				sell.setCarColor(obj[4] != null ?Integer.parseInt(obj[4].toString()) : null);
				sell.setCarColorName(obj[5] != null ?obj[5].toString() : null);
				sell.setModelSalesPrice(obj[6] != null ?Double.parseDouble(obj[6].toString()) : null);
				sell.setCarVinNumber(obj[7] != null ?obj[7].toString() : null);
				sell.setCarMotorNumber(obj[8] != null ?obj[8].toString() : null);
				sell.setSellDate(obj[9] != null ?obj[9].toString() : null);
				sell.setStfId(obj[10] != null ?Integer.parseInt(obj[10].toString()) : null);
				sell.setStfName(obj[11] != null ?obj[11].toString() : null);
				sell.setUpData(obj[12] != null ?obj[12].toString() : null);
				sell.setUpPer(obj[13] != null ?Integer.parseInt(obj[13].toString()) : null);
				sell.setUpPerson(obj[14] != null ?obj[14].toString() : null);
				sell.setCustomName(obj[15] != null ?obj[15].toString() : null);
				sell.setSex(obj[16] != null ?Integer.parseInt(obj[16].toString()) : null);
				sell.setCustomSex(obj[17] != null ?obj[17].toString() : null);
				sell.setXsCustomDiploma(obj[18] != null ?Integer.parseInt(obj[18].toString()) : null);
				sell.setXsCustomDiplomaN(obj[19] != null ?obj[19].toString() : null);
				sell.setXsCustomIncome(obj[20] != null ?Integer.parseInt(obj[20].toString()) : null);
				sell.setXsCustomIncomeN(obj[21] != null ?obj[21].toString() : null);
				sell.setXsCustomTrade(obj[22] != null ?Integer.parseInt(obj[22].toString()) : null);
				sell.setXsCustomTradeN(obj[23] != null ?obj[23].toString() : null);
				sell.setXsCustomArea(obj[24] != null ?Integer.parseInt(obj[24].toString()) : null);
				sell.setXsCustomAreaN(obj[25] != null ?obj[25].toString() : null);
				sell.setXsCustomPhone(obj[26] != null ?obj[26].toString() : null);
				sell.setXsCustomTelephone(obj[27] != null ?obj[27].toString() : null);
				sell.setXsCustomBirthday(obj[28] != null ?obj[28].toString() : null);
				sell.setXsCustomCompany(obj[29] != null ?obj[29].toString() : null);
				sell.setXsCustomAddress(obj[30] != null ?obj[30].toString() : null);
				sell.setXsCustomZipcode(obj[31] != null ?obj[31].toString() : null);
				sell.setXsCustomProperty(obj[32] != null ?Integer.parseInt(obj[32].toString()) : null);
				sell.setXsCustomPropertyN(obj[33] != null ?obj[33].toString() : null);
				sell.setPact_code(obj[34] != null ?obj[34].toString() : null);
				sell.setInvoice_code(obj[35] != null ?obj[35].toString() : null);
				sell.setXsDistributorId(obj[36] != null ?obj[36].toString() : null);
				sell.setDistributorName(obj[37] != null ?obj[37].toString() : null);
				sell.setConsult_actual_date(obj[38] != null ?obj[38].toString() : null);
				sell.setConsultDegree(obj[39] != null ?obj[39].toString() : null);
				sell.setConsultDegreeN(obj[40] != null ?obj[40].toString() : null);
				sell.setCarId(obj[41] != null ?Integer.parseInt(obj[41].toString()) : null);
				sell.setCarOcn(obj[42] != null ?obj[42].toString() : null);
				sell.setXs_model_costPrice(obj[43] != null ?Double.parseDouble(obj[43].toString()) : null);
				sell.setXs_car_price(obj[44] != null ?Double.parseDouble(obj[44].toString()) : null);
				sell.setDecorate_amount(obj[45] != null ?Double.parseDouble(obj[45].toString()) : null);
				sell.setDecorate_sell(obj[46] != null ?Double.parseDouble(obj[46].toString()) : null);
				sell.setCost_price(obj[47] != null ?Double.parseDouble(obj[47].toString()) : null);
				sell.setDb_project_cost(obj[48] != null ?Double.parseDouble(obj[48].toString()) : null);
				sell.setPrime_cost(obj[49] != null ?Double.parseDouble(obj[49].toString()) : null);
				sell.setS_sum(obj[50] != null ?Double.parseDouble(obj[50].toString()) : null);
				sell.setInvoice_parce(obj[51] != null ?Double.parseDouble(obj[51].toString()): null);
				sell.setXs_culstom_credentials(obj[52] != null ?obj[52].toString() : null);
				sell.setXs_car_certificate(obj[53] != null ?obj[53].toString() : null);
				sell.setXs_car_certificate_state(obj[54] != null ?Integer.parseInt(obj[54].toString()) : null);
				sell.setState(obj[55] != null ?obj[55].toString() : null);
				sell.setXs_car_make_data(obj[56] != null ?obj[56].toString() : null);
				sell.setInstorehouse_date(obj[57] != null ?obj[57].toString() : null);
				sell.setCheck_date(obj[58] != null ?obj[58].toString() : null);
				sell.setCheck_comtent(obj[59] != null ?obj[59].toString() : null);
				sell.setInvoice_number(obj[60] != null ?obj[60].toString() : null);
				
				
				list.add(sell);

			}
			// 汇总
			sell = new DayReportAssayVo();
			sell.setSellDate("汇总");
			
			Double count1 = 0.0;//成本价
			Double count2 = 0.0;//销售价
			Double count3 = 0.0;//市场价
			Double count4 = 0.0;//装潢成本
			Double count5 = 0.0;//装潢销售
			Double count6 = 0.0;//代办成本
			Double count7 = 0.0;//代办销售
			Double count8 = 0.0;//保险成本
			Double count9 = 0.0;//保险金额
			Double count10 = 0.0;//开票金额
			for (DayReportAssayVo dayReportAssayVo2 : list) {
				if (dayReportAssayVo2.getXs_model_costPrice() != null
						&& !"".equals(dayReportAssayVo2.getXs_model_costPrice())) {
							count1 += dayReportAssayVo2.getXs_model_costPrice();
				}
				if (dayReportAssayVo2.getModelSalesPrice() != null
						&& !"".equals(dayReportAssayVo2.getModelSalesPrice())) {
					count2 += (dayReportAssayVo2.getModelSalesPrice());
				}
				if (dayReportAssayVo2.getXs_car_price() != null
						&& !"".equals(dayReportAssayVo2.getXs_car_price())) {
					count3 += dayReportAssayVo2.getXs_car_price();
				}
				if (dayReportAssayVo2.getDecorate_amount() != null
						&& !"".equals(dayReportAssayVo2.getDecorate_amount())) {
					count4 += dayReportAssayVo2.getDecorate_amount();
				}
				if (dayReportAssayVo2.getDecorate_sell() != null
						&& !"".equals(dayReportAssayVo2.getDecorate_sell())) {
					count5 += dayReportAssayVo2.getDecorate_sell();
				}
				if (dayReportAssayVo2.getCost_price() != null
						&& !"".equals(dayReportAssayVo2.getCost_price())) {
					count6 += dayReportAssayVo2.getCost_price();
				}
				if (dayReportAssayVo2.getDb_project_cost() != null
						&& !"".equals(dayReportAssayVo2.getDb_project_cost())) {
					count7 += dayReportAssayVo2.getDb_project_cost();
				}
				if (dayReportAssayVo2.getPrime_cost() != null
						&& !"".equals(dayReportAssayVo2.getPrime_cost())) {
					count8 += dayReportAssayVo2.getPrime_cost();
				}
				if (dayReportAssayVo2.getS_sum() != null
						&& !"".equals(dayReportAssayVo2.getS_sum())) {
					count9 += dayReportAssayVo2.getS_sum();
				}
				if (dayReportAssayVo2.getInvoice_parce() != null
						&& !"".equals(dayReportAssayVo2.getInvoice_parce())) {
					count10 += dayReportAssayVo2.getInvoice_parce();
				}

			}

			sell.setXs_model_costPrice(count1);
			sell.setModelSalesPrice(count2);
			sell.setXs_car_price(count3);
			sell.setDecorate_amount(count4);
			sell.setDecorate_sell(count5);
			sell.setCost_price(count6);
			sell.setDb_project_cost(count7);
			sell.setPrime_cost(count8);
			sell.setS_sum(count9);
			sell.setInvoice_parce(count10);
			list.add(sell);
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
	
}
