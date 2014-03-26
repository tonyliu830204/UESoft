package com.syuesoft.sell.sellInsurance.daoimpl;

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
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsSellInsurance;
import com.syuesoft.sell.sellInsurance.dao.SellInsuranceListDao;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.FormatTime;
@Repository("sellInsuranceListDao")
public class SellInsuranceListDaoImpl extends BaseDaoImpl<BaseBean> implements
		SellInsuranceListDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 保单汇总信息查询
	 */
	
	public Datagrid getSellInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		
		Datagrid dg = new Datagrid();
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT A.insurance_id,D.xs_car_sel_id,A.insurance_policy," +
				"A.insurer_,b.xs_insurer_name,A.numbers,A.dafe_date,A.insurer_start_date," +
				"A.insurer_end_date, A.safe_type, A.record_date, A.distance," +
				"A.Insurance_agent, A.business_charge, A.traffic_charge, " +
				"A.vehiclevessel_tax,A.safe_cost,A.safe_amount,A.buysness_cost," +
				"A.q_cost,A.s_sum,A.custom_cost, A.extract,A.custom_returncost," +
				"A.person,c.STF_NAME ,A.profit,A.prime_cost,A.remark,d.custom_id,f.xs_custom_name," +
				"f.xs_custom_telephone,f.xs_custom_address,f.xs_contacts_person," +
				"f.xs_contacts_phone,f.xs_custom_credentials,f.xs_custom_zipcode,G.xs_car_color," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =G.xs_car_color) AS color," +
				"G.xs_car_vin_number," +
				"e.car_outputVolume,g.xs_car_rideLimit_number,g.xs_car_licensePlate," +
				"g.xs_car_motor_number ,a.examine, " +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE a.examine = k.child_id) as examinName," +
				" a.invoice_reckoning,(SELECT k.datavalue FROM xs_childdictionary k WHERE a.invoice_reckoning = k.child_id) as reckName," +
				" F.xs_custom_application,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = f.xs_custom_application) AS application," +
				"d.sell_code,js.account_code,  b.xs_insurer_code, b.xs_insurer_businessinsurer, b.xs_insurer_stronginsurer,E.use_nature " +
				"  FROM " +
				"  xs_sell_insurance A  JOIN xs_insurer_info B ON a.insurer_=b.xs_insurer_id " +
				"  JOIN bas_stuff C ON  c.STF_ID=a.person " +
				"  JOIN xs_car_sell_info D ON D.xs_car_sel_id=a.xs_car_sel_id" +
				"  LEFT JOIN xs_sell_car_reserve E ON E.reserve_id=d.reserve_id" +
				"  JOIN xs_custom_info F ON D.custom_id=f.custom_id" +
				"  JOIN  xs_car_info G ON g.xs_car_id=d.xs_car_id " +
				"  LEFT JOIN xs_sell_account js on js.account_type_id= A.insurance_policy" +
				"  WHERE d.isinsurance=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE1+"'" +
						" and child.enterprise_id="+sellInsuranceListVo.getEnterpriseId()+
						")  ");//是
		//企业编号
		if(sellInsuranceListVo.getEnterpriseId()!=null&& !"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and  D.enterprise_Id = '"+sellInsuranceListVo.getEnterpriseId()+"'");
		}
	
		if(sellInsuranceListVo.getDafeDate()!=null&& !"".equals(sellInsuranceListVo.getDafeDate())){
		
			sql.append(" and A.dafe_date >='"+sellInsuranceListVo.getDafeDate()+"'");
		}if(sellInsuranceListVo.getDafeDate2()!=null&& !"".equals(sellInsuranceListVo.getDafeDate2())){
			
			sql.append(" and A.dafe_date <='"+sellInsuranceListVo.getDafeDate2()+"'");
		}
		if((sellInsuranceListVo.getDafeDate()==null||"".equals(sellInsuranceListVo.getDafeDate()))&&
					(sellInsuranceListVo.getDafeDate2()==null||"".equals(sellInsuranceListVo.getDafeDate2()))){
			sql.append(" and DATE(A.dafe_date) between '"+ FormatTime.yesTempTady(
				FormatTime.DEFAULT_FORMAT2,(
						basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
								Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
								+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		
		if(sellInsuranceListVo.getInsurancePolicy()!=null&& !"".equals(sellInsuranceListVo.getInsurancePolicy())){
			sql.append(" and A.insurance_policy like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getInsurancePolicy().trim())+"%'");
		}
		if(sellInsuranceListVo.getSafeType()!=null&& !"".equals(sellInsuranceListVo.getSafeType())){
			sql.append(" and  A.safe_type = '"+sellInsuranceListVo.getSafeType()+"'");
		}
		if(sellInsuranceListVo.getInsurer()!=null&& !"".equals(sellInsuranceListVo.getInsurer())){
			sql.append(" and A.insurer_ = '"+sellInsuranceListVo.getInsurer()+"'");
		}
		if(sellInsuranceListVo.getCarLicensePlate()!=null&& !"".equals(sellInsuranceListVo.getCarLicensePlate())){
			sql.append(" and g.xs_car_licensePlate = '"+sellInsuranceListVo.getCarLicensePlate()+"'");
		}
		if(sellInsuranceListVo.getCustomName()!=null&& !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and f.xs_custom_name like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCustomName().trim())+"%'");
		}
		if(sellInsuranceListVo.getVin()!=null&& !"".equals(sellInsuranceListVo.getVin())){
			sql.append(" and G.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getVin().trim())+"%'");
		}

		List<Object[]> resultList = createSQLQuery(sql.toString(),sellInsuranceListVo.getPage(),sellInsuranceListVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setInsuranceId(obj[0] != null ?Integer.parseInt( obj[0].toString()): null);
				sell.setXs_Car_Sel_Id(obj[1] != null ?Integer.parseInt( obj[1].toString()): null);
				sell.setInsurancePolicy(obj[2] != null ? obj[2].toString(): null);
				sell.setInsurer(obj[3] != null ? Integer.parseInt( obj[3].toString()): null);
				sell.setInsurerName(obj[4] != null ? obj[4].toString(): null);
				sell.setNumbers(obj[5] != null ? obj[5].toString(): null);
				sell.setDafeDate(obj[6] != null ? obj[6].toString(): null);
				sell.setInsurerStartDate(obj[7] != null ? obj[7].toString(): null);
				sell.setInsurerEndDate(obj[8] != null ? obj[8].toString(): null);
				sell.setSafeType(obj[9] != null ? obj[9].toString(): null);
				sell.setRecordDate(obj[10] != null ? obj[10].toString(): null);
				sell.setDistance(obj[11] != null ? obj[11].toString(): null);
				sell.setInsuranceAgent(obj[12] != null ? obj[12].toString(): null);
				sell.setBusinessCharge(obj[13] != null ? Double.parseDouble( obj[13].toString()): null);
				sell.setTrafficCharge(obj[14] != null ? Double.parseDouble( obj[14].toString()): null);
				sell.setVehiclevesselTax(obj[15] != null ? Double.parseDouble( obj[15].toString()): null);
				sell.setSafeCost(obj[16] != null ? Double.parseDouble( obj[16].toString()): null);
				sell.setSafeAmount(obj[17] != null ? Double.parseDouble( obj[17].toString()): null);
				sell.setBuysnessCost(obj[18] != null ? obj[18].toString(): null);
				sell.setInCost(obj[19] != null ?  obj[19].toString(): null);
				sell.setSum(obj[20] != null ? Double.parseDouble( obj[20].toString()): null);
				sell.setCustomCost(obj[21] != null ? Double.parseDouble( obj[21].toString()): null);
				sell.setExtract(obj[22] != null ? Double.parseDouble( obj[22].toString()): null);
				sell.setCustomReturncost(obj[23] != null ?obj[23].toString(): null);
				sell.setPerson(obj[24] != null ? Integer.parseInt( obj[24].toString()): null);
				sell.setStfName(obj[25] != null ?obj[25].toString(): null);
				sell.setProfit(obj[26] != null ? Double.parseDouble( obj[26].toString()): null);
				sell.setPrimeCost(obj[27] != null ? Double.parseDouble( obj[27].toString()): null);
				sell.setRemark(obj[28] != null ?obj[28].toString(): null);
				sell.setCustomId(obj[29] != null ? Integer.parseInt( obj[29].toString()): null);
				sell.setCustomName(obj[30] != null ?obj[30].toString(): null);
				sell.setCustomTelephone(obj[31] != null ? obj[31].toString(): null);
				sell.setCustomAddress(obj[32] != null ?  obj[32].toString(): null);
				sell.setContactsPerson(obj[33] != null ?  obj[33].toString(): null);
				sell.setContactsPhone(obj[34] != null ?obj[34].toString(): null);
				sell.setCustomCredentials(obj[35] != null ?  obj[35].toString(): null);
				sell.setCustomZipcode(obj[36] != null ?obj[36].toString(): null);
				sell.setCarColor(obj[37] != null ? Integer.parseInt( obj[37].toString()): null);
				sell.setCarColorName(obj[38] != null ? obj[38].toString(): null);
				sell.setVin(obj[39] != null ?obj[39].toString(): null);
				sell.setOutprint(obj[40] != null ?obj[40].toString(): null);
				sell.setLimitLoadNum(obj[41] != null ?Integer.parseInt(obj[41].toString()): null);
				sell.setCarLicensePlate(obj[42] != null ?obj[42].toString(): null);
				sell.setEngineNumber(obj[43] != null ?obj[43].toString(): null);
				sell.setExamine(obj[44] != null ?Integer.parseInt(obj[44].toString()): null);
				sell.setExamineName(obj[45] != null ?obj[45].toString(): null);
				sell.setInvoiceReckoning(obj[46] != null ?Integer.parseInt( obj[46].toString()): null);
				sell.setReckoningName(obj[47] != null ? obj[47].toString(): null);
				sell.setXsCustomApplication(obj[48] != null ?Integer.parseInt( obj[48].toString()): null);
				sell.setXsCustomApplicationN(obj[49] != null ? obj[49].toString(): null);
				sell.setSell_code(obj[50] != null ? obj[50].toString(): null);
				sell.setAccountCode(obj[51] != null ? obj[51].toString(): null);
				sell.setInsurerCodes(obj[52] != null ? obj[52].toString(): null);
				sell.setBuysnessCost(obj[53] != null ? obj[53].toString(): null);
				sell.setInCost(obj[54] != null ? obj[54].toString(): null);
				sell.setXsCustomApplicationN(obj[55] != null ? obj[55].toString(): null);
		list.add(sell);
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;

	}

	
	public List getinfo(SellInsuranceListVo sellInsuranceListVo) throws Exception {
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT  child.dataKey,child.dataValue ,c.detail_id,c.insurance_id," +
				"c.insurancecost,c.insurancemoney,c.insurancerate " +
				"FROM xs_childdictionary child" +
				" LEFT OUTER JOIN xs_parentdictionary parent ON parent.parent_id = child.parent_id" +
				"	LEFT OUTER JOIN xs_sell_insurdetail C ON c.insurancetype=child.dataKey" +
				" LEFT OUTER JOIN xs_sell_insurance D  ON c.insurance_id=d.insurance_id" +
				"	WHERE parent.dataKey ='"+Contstants.BASE_SELL.INSURANCETYPE+"' and c.insurance_id="+sellInsuranceListVo.getInsuranceId());
		if(sellInsuranceListVo.getEnterpriseId()!=null&& !"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and  child.enterprise_Id = '"+sellInsuranceListVo.getEnterpriseId()+"'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setInsurancetype(obj[0] != null ?obj[0].toString(): null);
				sell.setInsuranceName(obj[1] != null ?obj[1].toString(): null);
				sell.setDetailId(obj[2] != null ?Integer.parseInt( obj[2].toString()): null);
				sell.setInsuranceId(obj[3] != null ?Integer.parseInt( obj[3].toString()): null);
				sell.setInsurancecost(obj[4] != null ?Double.parseDouble( obj[4].toString()): null);
				sell.setInsurancemoney(obj[5] != null ?Double.parseDouble( obj[5].toString()): null);
				sell.setInsurancerate(obj[6] != null ? obj[6].toString(): null);
				
				list.add(sell);
			}
			}
		return list;
	}



//车辆保险模块：车辆保险父查询
	
	public Datagrid getSellInsuranceF(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {		
		Datagrid dg = new Datagrid();
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT A.insurer_,	b.xs_insurer_name,	COUNT(*),SUM(a.s_sum)," +
				"SUM(a.custom_cost),SUM(a.safe_amount),SUM(a.extract),SUM(a.profit)" +
				"	FROM 	xs_sell_insurance A" +
				"	 JOIN xs_insurer_info B ON a.insurer_=b.xs_insurer_id" +
				"	 JOIN xs_car_sell_info C ON c.xs_car_sel_id=a.xs_car_sel_id" +
				"	 JOIN xs_custom_info D ON d.custom_id=c.custom_id" +
				"	 JOIN xs_car_info E ON C.xs_car_id=e.xs_car_id " +
				"where 1=1 ");
		
		//企业编号
		if(sellInsuranceListVo.getEnterpriseId()!=null&& !"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append  (" and  C.enterprise_Id = '"+sellInsuranceListVo.getEnterpriseId()+"'");
		}
		
		if(sellInsuranceListVo.getDafeDate()!=null&& !"".equals(sellInsuranceListVo.getDafeDate())){
		
			sql.append(" and A.dafe_date >='"+sellInsuranceListVo.getDafeDate()+"'");
		}if(sellInsuranceListVo.getDafeDate2()!=null&& !"".equals(sellInsuranceListVo.getDafeDate2())){
			
			sql.append(" and A.dafe_date <='"+sellInsuranceListVo.getDafeDate2()+"'");
		}
		if((sellInsuranceListVo.getDafeDate()==null||"".equals(sellInsuranceListVo.getDafeDate()))&&
					(sellInsuranceListVo.getDafeDate2()==null||"".equals(sellInsuranceListVo.getDafeDate2()))){
			sql.append(" and DATE(A.dafe_date) between '"+ FormatTime.yesTempTady(
				FormatTime.DEFAULT_FORMAT2,(
						basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
								Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
								+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
	
		if(sellInsuranceListVo.getInsurerStartDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate())){
			sql.append(" and a.insurer_start_date>= '"+sellInsuranceListVo.getInsurerStartDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate1())){
			sql.append(" and a.insurer_start_date <= '"+sellInsuranceListVo.getInsurerStartDate1()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate())){
			sql.append(" and a.insurer_end_date >= '"+sellInsuranceListVo.getInsurerEndDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate1())){
			sql.append(" and a.insurer_end_date <= '"+sellInsuranceListVo.getInsurerEndDate1()+"'");
		}
		
		if(sellInsuranceListVo.getInsurerId()!=null&& !"".equals(sellInsuranceListVo.getInsurerId())){
			sql.append(" and A.insurer_ = '"+sellInsuranceListVo.getInsurerId()+"'");
		}
		if(sellInsuranceListVo.getCustomName()!=null && !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and d.xs_custom_name like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCustomName().trim())+"%'");
		}
		if(sellInsuranceListVo.getVin()!=null&& !"".equals(sellInsuranceListVo.getVin())){
			sql.append(" and e.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getVin().trim())+"%'");
		}
		if(sellInsuranceListVo.getCarLicensePlate()!=null&& !"".equals(sellInsuranceListVo.getCarLicensePlate())){
			sql.append(" and e.xs_car_licensePlate like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCarLicensePlate().trim())+"%'");
		}
	    sql.append(" GROUP BY a.insurer_");
		List<Object[]> resultList = createSQLQuery(sql.toString(),sellInsuranceListVo.getPage(),sellInsuranceListVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellInsuranceListVo sell=null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setInsurer(obj[0] != null ?Integer.parseInt( obj[0].toString()): null);
				sell.setInsurerName(obj[1] != null ? obj[1].toString(): null);
				sell.setNum(obj[2] != null ? Integer.parseInt(obj[2].toString()): null);
				sell.setSum(obj[3] != null ? Double.parseDouble(obj[3].toString()): null);
				sell.setCustomCost(obj[4] != null ? Double.parseDouble(obj[4].toString()): null);
				sell.setSafeAmount(obj[5] != null ? Double.parseDouble(obj[5].toString()): null);
				sell.setExtract(obj[6] != null ? Double.parseDouble(obj[6].toString()): null);
				sell.setProfit(obj[7] != null ? Double.parseDouble(obj[7].toString()): null);
				
				sell.setState("closed");
				sell.setIconCls("icon-blank");	
				list.add(sell);
	}
			
			sell = new SellInsuranceListVo();
			
			sell.setInsurerName("汇总");
			sell.setState("open");
			sell.setIconCls("icon-blank");
			int count = 0;
			Double extract =0.0;
			Double customCost=0.0;
			Double sum=0.0;
			Double safeAmount=0.0;
			Double profit=0.0;
			for (SellInsuranceListVo vo2 : list) {
				if(vo2.getNum()!=null&&!"".equals(vo2.getNum())){
					count += (vo2.getNum());
				}
				if(vo2.getExtract()!=null&&!"".equals(vo2.getExtract())){
					extract +=vo2.getExtract();
				}
				
				if(vo2.getCustomCost()!=null&&!"".equals(vo2.getCustomCost())){
					customCost +=vo2.getCustomCost();
				}
				
				if(vo2.getSum()!=null&&!"".equals(vo2.getSum())){
					sum +=vo2.getSum();
				}
				
				if(vo2.getSafeAmount()!=null&&!"".equals(vo2.getSafeAmount())){
					safeAmount +=vo2.getSafeAmount();
				}
				
				if(vo2.getProfit()!=null&&!"".equals(vo2.getProfit())){
					profit +=vo2.getProfit();
				}
				
				
			}
			sell.setNum(count);
			sell.setSum(sum);
			sell.setCustomCost(customCost);
			sell.setSafeAmount(safeAmount);
			sell.setExtract(extract);
			sell.setProfit(profit);
			list.add(sell);
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
	//车辆保险模块：车辆保险子查询
	
	public List getInsuranceDetails(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT  a.insurance_id,a.record_date,a.insurance_policy,d.xs_custom_name," +
				"e.xs_car_licensePlate,a.insurer_start_date,a.insurer_end_date,a.remark,a.safe_type," +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE a.safe_type = k.child_id) AS safe," +
				"a.s_sum,a.custom_cost,a.safe_amount,a.extract,a.profit,a.person,f.STF_NAME,e.xs_car_licenseName," +
				"e.xs_car_motor_number,e.xs_car_vin_number,D.xs_custom_application," +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE D.xs_custom_application = k.child_id) AS application," +
				"e.xs_car_rideLimit_number,d.xs_custom_address,d.xs_custom_credentials,d.xs_custom_telephone," +
				"d.xs_custom_zipcode,d.xs_contacts_person,e.xs_car_color," +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE e.xs_car_color = k.child_id) AS color," +
				"g.car_outputVolume,a.distance,A.dafe_date " +
				"FROM xs_sell_insurance A" +
				" JOIN xs_insurer_info B ON a.insurer_=b.xs_insurer_id" +
				" JOIN xs_car_sell_info C ON c.xs_car_sel_id=a.xs_car_sel_id  " +
				" JOIN xs_custom_info D ON d.custom_id=c.custom_id" +
				" JOIN xs_car_info E ON C.xs_car_id=e.xs_car_id" +
				"	LEFT JOIN bas_stuff F ON f.STF_ID=a.person" +
				"	LEFT JOIN xs_sell_car_reserve g ON g.xs_car_id=e.xs_car_id " +
				" where A.insurer_="+sellInsuranceListVo.getInsurer());
		
		
		if(sellInsuranceListVo.getEnterpriseId()!=null&& !"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and  C.enterprise_Id = '"+sellInsuranceListVo.getEnterpriseId()+"'");
		}
		
		if(sellInsuranceListVo.getDafeDate()!=null&& !"".equals(sellInsuranceListVo.getDafeDate())){
		
			sql.append(" and A.dafe_date >='"+sellInsuranceListVo.getDafeDate()+"'");
		}if(sellInsuranceListVo.getDafeDate2()!=null&& !"".equals(sellInsuranceListVo.getDafeDate2())){
			
			sql.append(" and A.dafe_date <='"+sellInsuranceListVo.getDafeDate2()+"'");
		}
		if((sellInsuranceListVo.getDafeDate()==null||"".equals(sellInsuranceListVo.getDafeDate()))&&
				(sellInsuranceListVo.getDafeDate2()==null||"".equals(sellInsuranceListVo.getDafeDate2()))){
		sql.append(" and DATE(A.dafe_date) between '"+ FormatTime.yesTempTady(
			FormatTime.DEFAULT_FORMAT2,(
					basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
							Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
							+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate())){
			sql.append(" and a.insurer_start_date>= '"+sellInsuranceListVo.getInsurerStartDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate())){
			sql.append(" and a.insurer_end_date <= '"+sellInsuranceListVo.getInsurerEndDate()+"'");
		}
		if(sellInsuranceListVo.getCustomName()!=null && !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and d.xs_custom_name like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCustomName().trim())+"%'");
		}
		if(sellInsuranceListVo.getVin()!=null&& !"".equals(sellInsuranceListVo.getVin())){
			sql.append(" and e.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getVin().trim())+"%'");
		}
		if(sellInsuranceListVo.getCarLicensePlate()!=null&& !"".equals(sellInsuranceListVo.getCarLicensePlate())){
			sql.append(" and e.xs_car_licensePlate like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCarLicensePlate().trim())+"%'");
		}
		
		
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setInsurerName(obj[0] != null ?obj[0].toString(): null);
				sell.setRecordDate(obj[1] != null ?obj[1].toString(): null);
				sell.setInsurancePolicy(obj[2] != null ?obj[2].toString(): null);
				sell.setCustomName(obj[3] != null ?obj[3].toString(): null);
				sell.setCarLicensePlate(obj[4] != null ?obj[4].toString(): null);
				sell.setInsurerStartDate(obj[5] != null ?obj[5].toString(): null);
				sell.setInsurerEndDate(obj[6] != null ?obj[6].toString(): null);
				sell.setRemark(obj[7] != null ?obj[7].toString(): null);
				sell.setSafeType(obj[8] != null ?obj[8].toString(): null);
				sell.setSafeTypeN(obj[9] != null ?obj[9].toString(): null);
				sell.setSum(obj[10] != null ?Double.parseDouble(obj[10].toString()): null);
				sell.setCustomCost(obj[11] != null && !("".equals(obj[11]))?Double.parseDouble(obj[11].toString()): null);
				sell.setSafeAmount(obj[12] != null && !("".equals(obj[12])) ?Double.parseDouble(obj[12].toString()): null);
				sell.setExtract(obj[13] != null&& !("".equals(obj[13])) ?Double.parseDouble(obj[13].toString()): null);
				sell.setProfit(obj[14] != null && !("".equals(obj[14]))?Double.parseDouble(obj[14].toString()): null);
				sell.setPerson(obj[15] != null ?Integer.parseInt( obj[15].toString()): null);
				sell.setStfName(obj[16] != null ?obj[16].toString(): null);
				sell.setCarLicenseName(obj[17] != null ?obj[17].toString(): null);
				sell.setEngineNumber(obj[18] != null ?obj[18].toString(): null);
				sell.setVin(obj[19] != null ?obj[19].toString(): null);
				sell.setXsCustomApplication(obj[20] != null ?Integer.parseInt( obj[20].toString()): null);
				sell.setXsCustomApplicationN(obj[21] != null ? obj[21].toString(): null);
				sell.setLimitLoadNum(obj[22] != null ?Integer.parseInt( obj[22].toString()): null);
				sell.setCustomAddress(obj[23] != null ?obj[23].toString(): null);
				sell.setCustomCredentials(obj[24] != null ?obj[24].toString(): null);
				sell.setCustomTelephone(obj[25] != null ?obj[25].toString(): null);
				sell.setCustomZipcode(obj[26] != null ?obj[26].toString(): null);
				sell.setContactsPerson(obj[27] != null ?obj[27].toString(): null);				
				sell.setCarColor(obj[28] != null ?Integer.parseInt( obj[28].toString()): null);
				sell.setCarColorName(obj[29] != null ?obj[29].toString(): null);
				sell.setOutprint(obj[30] != null ?obj[30].toString(): null);
				sell.setDistance(obj[31] != null ?obj[31].toString(): null);
				sell.setDafeDate(obj[32] != null ?obj[32].toString(): null);
				
				sell.setIconCls("icon-blank");
				sell.setState("open");
				list.add(sell);
			}
		}
		
		return list;
	}

	
	public List getCarBrand(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT  child.child_id,  child.dataValue " +
				" FROM xs_childdictionary child, xs_parentdictionary parent " +
				"WHERE child.parent_id = parent.parent_id  " +
				"AND parent.dataKey ='"+Contstants.BASE_SELL.BASE_CARBRAND+"'");
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setChildId(obj[0] != null ?Integer.parseInt(obj[0].toString()): null);
				sell.setDataValue(obj[1] != null ?obj[1].toString(): null);
				sell.setState("closed");
				sell.setIconCls("icon-blank");
				list.add(sell);
			}
	}
		return list;
	}

	/**
	 * 查询子级
	 */
	
	public List getCarModel(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT xs_model_id, xs_model_name" +
				"	FROM xs_car_model where  xs_car_brand="+sellInsuranceListVo.getChildId());
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setDataValue(obj[0] != null ?obj[0].toString(): null);
				sell.setCarmodelN(obj[1] != null ?obj[1].toString(): null);
				sell.setState("open");
				sell.setIconCls("icon-blank");
				
				list.add(sell);
			}
	}
		return list;
	}

	
	public Datagrid getCarInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT A.insurance_id,D.xs_car_sel_id,A.insurance_policy," +
				"A.insurer_,b.xs_insurer_name,A.numbers,A.dafe_date,A.insurer_start_date," +
				"A.insurer_end_date, A.safe_type, A.record_date, A.distance," +
				"A.Insurance_agent as agent,g.xs_car_brand,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =g.xs_car_brand) AS brand," +
				" g.xs_car_model_id,h.xs_model_name,g.xs_car_licenseName,g.xs_car_code,g.xs_car_motor_number," +
				" f.xs_custom_address as addr,f.xs_custom_phone,J.instorehouse_date,D.xs_car_sel_data,A.person,c.STF_NAME ,A.profit,A.prime_cost,A.remark,d.custom_id,f.xs_custom_name," +
				"f.xs_custom_telephone,f.xs_custom_address,f.xs_contacts_person," +
				"f.xs_contacts_phone,f.xs_custom_credentials,f.xs_custom_zipcode,G.xs_car_color," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =G.xs_car_color) AS color," +
				"G.xs_car_vin_number," +
				"e.car_outputVolume,g.xs_car_rideLimit_number,g.xs_car_licensePlate,g.upData,a.examine, " +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE a.examine = k.child_id) as examinName," +
				" a.invoice_reckoning,(SELECT k.datavalue FROM xs_childdictionary k WHERE a.invoice_reckoning = k.child_id) as reckName," +
				" F.xs_custom_application,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = f.xs_custom_application) AS application," +
				" d.xs_car_sel_transaction_money ,g.xs_car_fristInsurance_data,A.Insurance_agent,g.xs_car_make_data " +
				"   FROM " +
				"  xs_sell_insurance A  " +
				"JOIN xs_insurer_info B ON a.insurer_=b.xs_insurer_id " +
				"  LEFT JOIN bas_stuff C ON  c.STF_ID=a.person " +
				"   JOIN xs_car_sell_info D ON D.xs_car_sel_id=a.xs_car_sel_id" +
				"  LEFT JOIN xs_sell_car_reserve E ON E.reserve_id=d.reserve_id" +
				"   JOIN xs_custom_info F ON D.custom_id=f.custom_id" +
				"   JOIN  xs_car_info G ON g.xs_car_id=d.xs_car_id" +
				"  LEFT JOIN  xs_car_model H on h.xs_model_id=g.xs_car_model_id " +
				" LEFT JOIN  xs_sell_instorehouse_del I on i.xs_car_id=g.xs_car_id" +
				" LEFT JOIN xs_sell_instorehouse J on j.instorehouse_id=i.instorehouse_id " +
				"  WHERE d.isinsurance=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE1+"'" +
						" and child.enterprise_id="+sellInsuranceListVo.getEnterpriseId()+") and I.details_id NOT IN(" +
						"SELECT details_id FROM xs_sell_retreat WHERE instorehouse_type=" +
						" (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.INSTORESTYLE.PARENTINSTORE+"' AND " +
						"child.dataKey='"+Contstants.INSTORESTYLE.BACK+"' and child.enterprise_id="+sellInsuranceListVo.getEnterpriseId()+"))");//是
		
		if(sellInsuranceListVo.getEnterpriseId()!=null && !sellInsuranceListVo.getEnterpriseId().equals("")){
			sql.append(" AND D.enterprise_id="+sellInsuranceListVo.getEnterpriseId()+"");
		}
		if(sellInsuranceListVo.getInsurerStartDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate())){
			sql.append(" and  a.insurer_start_date>='"+sellInsuranceListVo.getInsurerStartDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate())){
			sql.append(" and  a.insurer_end_date<='"+sellInsuranceListVo.getInsurerEndDate()+"'");
		}
		

		if(sellInsuranceListVo.getCarSellData()!=null&& !"".equals(sellInsuranceListVo.getCarSellData())){
				
			sql.append( " and DATE(D.xs_car_sel_data)>= '"+sellInsuranceListVo.getCarSellData()+"'" );
		}
		if(sellInsuranceListVo.getCarSellData2()!=null&& !"".equals(sellInsuranceListVo.getCarSellData2())){
			
			sql.append( " and DATE(D.xs_car_sel_data)<= '"+sellInsuranceListVo.getCarSellData2()+"'" );
		}
		
		if((sellInsuranceListVo.getCarSellData()==null|| "".equals(sellInsuranceListVo.getCarSellData()))&&
				(sellInsuranceListVo.getCarSellData2()==null|| "".equals(sellInsuranceListVo.getCarSellData2()))){
			sql.append(" and DATE(D.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
				FormatTime.DEFAULT_FORMAT2,(
						basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
								Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
								+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellInsuranceListVo.getVin()!=null&& !"".equals(sellInsuranceListVo.getVin())){
			sql.append(" and G.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getVin().trim())+"%'");
		}

		
		if(sellInsuranceListVo.getCarLicensePlate()!=null&& !"".equals(sellInsuranceListVo.getCarLicensePlate())){
			sql.append(" and g.xs_car_licensePlate = '"+sellInsuranceListVo.getCarLicensePlate()+"'");
		}
		if(sellInsuranceListVo.getCustomName()!=null&& !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and f.xs_custom_name like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCustomName().trim())+"%'");
		}
		if(sellInsuranceListVo.getCarBrandId()!=null&& !"".equals(sellInsuranceListVo.getCarBrandId())){
			sql.append(" and g.xs_car_brand= '"+sellInsuranceListVo.getCarBrandId()+"'");
		}
		if(sellInsuranceListVo.getCarCode()!=null&& !"".equals(sellInsuranceListVo.getCarCode())){
			sql.append(" and g.xs_car_code like '%"+StringEscapeUtils.escapeSql(sellInsuranceListVo.getCarCode().trim())+"%'");
		}
		if(sellInsuranceListVo.getPerson()!=null&& !"".equals(sellInsuranceListVo.getPerson())){
			sql.append(" and A.person= '"+sellInsuranceListVo.getPerson()+"'");
		}
		if(sellInsuranceListVo.getCarModel()!=null&& !"".equals(sellInsuranceListVo.getCarModel())){
			sql.append(" and g.xs_car_model_id= '"+sellInsuranceListVo.getCarModel()+"'");
		}
		if(sellInsuranceListVo.getCarMotorNumber()!=null&& !"".equals(sellInsuranceListVo.getCarMotorNumber())){
			sql.append(" and g.xs_car_motor_number= '"+sellInsuranceListVo.getCarMotorNumber()+"'");
		}
		
		List<Object[]> resultList = createSQLQuery(sql.toString(),sellInsuranceListVo.getPage(),sellInsuranceListVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setInsuranceId(obj[0] != null ?Integer.parseInt( obj[0].toString()): null);
				sell.setXs_Car_Sel_Id(obj[1] != null ?Integer.parseInt( obj[1].toString()): null);
				sell.setInsurancePolicy(obj[2] != null ? obj[2].toString(): null);
				sell.setInsurer(obj[3] != null ? Integer.parseInt( obj[3].toString()): null);
				sell.setInsurerName(obj[4] != null ? obj[4].toString(): null);
				sell.setNumbers(obj[5] != null ? obj[5].toString(): null);
				sell.setDafeDate(obj[6] != null ? obj[6].toString(): null);
				sell.setInsurerStartDate(obj[7] != null ? obj[7].toString(): null);
				sell.setInsurerEndDate(obj[8] != null ? obj[8].toString(): null);
				sell.setSafeType(obj[9] != null ? obj[9].toString(): null);
				sell.setRecordDate(obj[10] != null ? obj[10].toString(): null);
				sell.setDistance(obj[11] != null ? obj[11].toString(): null);
				sell.setInsuranceAgent(obj[12] != null ? obj[12].toString(): null);
				
				sell.setCarBrandId(obj[13] != null ?Integer.parseInt( obj[13].toString()): null);
				sell.setCarBrand(obj[14] != null ? obj[14].toString(): null);
				sell.setCarModel(obj[15] != null ?Integer.parseInt( obj[15].toString()): null);
				sell.setCarmodelN(obj[16] != null ? obj[16].toString(): null);
				sell.setCarLicenseName(obj[17] != null ? obj[17].toString(): null);
				sell.setCarCode(obj[18] != null ? obj[18].toString(): null);
				sell.setCarMotorNumber(obj[19] != null ? obj[19].toString(): null);
				sell.setCustomAddress(obj[20] != null ? obj[20].toString(): null);
				sell.setCustomPhone(obj[21] != null ? obj[21].toString(): null);
				sell.setInstorehouseDate(obj[22] != null ? obj[22].toString(): null);
				sell.setCarSellData(obj[23] != null ? obj[23].toString(): null);
				
				
				sell.setPerson(obj[24] != null ? Integer.parseInt( obj[24].toString()): null);
				sell.setStfName(obj[25] != null ?obj[25].toString(): null);
				sell.setProfit(obj[26] != null ? Double.parseDouble( obj[26].toString()): null);
				sell.setPrimeCost(obj[27] != null ? Double.parseDouble( obj[27].toString()): null);
				sell.setRemark(obj[28] != null ?obj[28].toString(): null);
				sell.setCustomId(obj[29] != null ? Integer.parseInt( obj[29].toString()): null);
				sell.setCustomName(obj[30] != null ?obj[30].toString(): null);
				sell.setCustomTelephone(obj[31] != null ? obj[31].toString(): null);
				sell.setCustomAddress(obj[32] != null ?  obj[32].toString(): null);
				sell.setContactsPerson(obj[33] != null ?  obj[33].toString(): null);
				sell.setContactsPhone(obj[34] != null ?obj[34].toString(): null);
				sell.setCustomCredentials(obj[35] != null ?  obj[35].toString(): null);
				sell.setCustomZipcode(obj[36] != null ?obj[36].toString(): null);
				sell.setCarColor(obj[37] != null ? Integer.parseInt( obj[37].toString()): null);
				sell.setCarColorName(obj[38] != null ? obj[38].toString(): null);
				sell.setVin(obj[39] != null ?obj[39].toString(): null);
				sell.setOutprint(obj[40] != null ?obj[40].toString(): null);
				sell.setLimitLoadNum(obj[41] != null ?Integer.parseInt(obj[41].toString()): null);
				sell.setCarLicensePlate(obj[42] != null ?obj[42].toString(): null);
				sell.setAudit_date(obj[43] != null ? obj[43].toString(): null);
				sell.setExamine(obj[44] != null ?Integer.parseInt(obj[44].toString()): null);
				sell.setExamineName(obj[45] != null ?obj[45].toString(): null);
				sell.setInvoiceReckoning(obj[46] != null ?Integer.parseInt( obj[46].toString()): null);
				sell.setReckoningName(obj[47] != null ? obj[47].toString(): null);
				sell.setXsCustomApplication(obj[48] != null ?Integer.parseInt( obj[48].toString()): null);
				sell.setXsCustomApplicationN(obj[49] != null ? obj[49].toString(): null);
				sell.setSellMoney(obj[50] != null ?Double.parseDouble( obj[50].toString()): null);
				sell.setFristInsuranceData(obj[51] != null ? obj[51].toString(): null);
				sell.setInsuranceAgent(obj[52] != null ? obj[52].toString(): null);
				sell.setCarMakeData(obj[53] != null ? obj[53].toString(): null);
			

				list.add(sell);
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;

	}

	
	public List getInsuranceDel(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT a.insurance_id,cus.xs_custom_name,sell.xs_car_sel_data," +
				"ins.xs_insurer_name,a.prime_cost, a.s_sum, a.custom_cost," +
				"(a.s_sum-a.custom_cost ) AS cha ,  a.insurer_start_date, a.insurer_end_date" +
				"  FROM xs_sell_insurance A " +
				"JOIN bas_stuff B ON B.STF_ID=a.person " +
				"	JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=a.xs_car_sel_id " +
				"JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=sell.sell_code " +
				"	AND flow.xsfcontrol_car_id=sell.xs_car_id " +
				"JOIN xs_custom_info cus ON cus.custom_id=sell.custom_id " +
				"JOIN xs_insurer_info ins ON ins.xs_insurer_id=a.insurer_ " +
				"where a.person="+sellInsuranceListVo.getPerson());

		if(sellInsuranceListVo.getEnterpriseId()!=null&&!"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and sell.enterprise_id="+sellInsuranceListVo.getEnterpriseId());	
		}
		
		if(sellInsuranceListVo.getCarSellData()!=null&&!"".equals(sellInsuranceListVo.getCarSellData())){
			sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellInsuranceListVo.getCarSellData() + "'");
		}
		if(sellInsuranceListVo.getCarSellData2()!=null&&!"".equals(sellInsuranceListVo.getCarSellData2())){
			sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellInsuranceListVo.getCarSellData2() + "'");
		}	
		
		if((sellInsuranceListVo.getCarSellData()==null||"".equals(sellInsuranceListVo.getCarSellData()))
				&&(sellInsuranceListVo.getCarSellData2()==null||"".equals(sellInsuranceListVo.getCarSellData2()))){
			sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate())){
			sql.append(" and a.insurer_start_date>= '"+sellInsuranceListVo.getInsurerStartDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate1())){
			sql.append(" and a.insurer_start_date<= '"+sellInsuranceListVo.getInsurerStartDate1()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate())){
			sql.append(" and a.insurer_end_date >= '"+sellInsuranceListVo.getInsurerEndDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate1())){
			sql.append(" and a.insurer_end_date<= '"+sellInsuranceListVo.getInsurerEndDate1()+"'");
		}
		
		if(sellInsuranceListVo.getCustomName()!=null && !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and cus.xs_custom_name like '%"+sellInsuranceListVo.getCustomName()+"%'");
		}
		
		List<Object[]> resultList = createSQLQuery(sql.toString());
				if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellInsuranceListVo sell=null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellInsuranceListVo();
				obj = resultList.get(i);

				sell.setStfName(obj[0] != null ? obj[0].toString(): null);
				sell.setCustomName(obj[1] != null ? obj[1].toString(): null);
				sell.setCarSellData(obj[2] != null ? obj[2].toString(): null);
				sell.setInsuranceName(obj[3] != null ?obj[3].toString(): null);
				sell.setPrimeCost(obj[4] != null ? Double.parseDouble(obj[4].toString()): null);
				sell.setSum(obj[5] != null ? Double.parseDouble(obj[5].toString()): null);
				sell.setCustomCost(obj[6] != null ? Double.parseDouble(obj[6].toString()): null);
				sell.setProfit(obj[7] != null ? Double.parseDouble(obj[7].toString()): null);
				
				sell.setInsurerStartDate(obj[8] != null ? obj[8].toString(): null);
				sell.setInsurerEndDate(obj[9] != null ? obj[9].toString(): null);
				sell.setState("open");
				sell.setIconCls("icon-blank");	
				list.add(sell);
			}
	
			}
		return list;
	}

	
	public DatagridAnalyze getInsuranceInfor(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer("SELECT  a.person,b.STF_NAME,COUNT(*), SUM(a.prime_cost),SUM(a.s_sum)," +
				"SUM(a.custom_cost), SUM(a.s_sum-a.custom_cost) AS youh  " +
				"FROM  xs_sell_insurance A " +
				"JOIN  bas_stuff B ON b.STF_ID=a.person " +
				"JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=a.xs_car_sel_id " +
				"JOIN xs_sell_flow_control flow ON flow.xsfcontrol_car_id=sell.xs_car_id " +
				"AND flow.xsfcontrol_document=sell.sell_code " +
				"JOIN xs_custom_info cus ON cus.custom_id=sell.custom_id " +
				"where 1=1 ");
		if(sellInsuranceListVo.getEnterpriseId()!=null&&!"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and sell.enterprise_id="+sellInsuranceListVo.getEnterpriseId());	
		}
		
		if(sellInsuranceListVo.getCarSellData()!=null&&!"".equals(sellInsuranceListVo.getCarSellData())){
			sql.append(" and DATE(sell.xs_car_sel_data) >= '" +sellInsuranceListVo.getCarSellData() + "'");
		}
		if(sellInsuranceListVo.getCarSellData2()!=null&&!"".equals(sellInsuranceListVo.getCarSellData2())){
			sql.append(" and DATE(sell.xs_car_sel_data) <= '" +sellInsuranceListVo.getCarSellData2() + "'");
		}	
		
		if((sellInsuranceListVo.getCarSellData()==null||"".equals(sellInsuranceListVo.getCarSellData()))
				&&(sellInsuranceListVo.getCarSellData2()==null||"".equals(sellInsuranceListVo.getCarSellData2()))){
			sql	.append(" and DATE(sell.xs_car_sel_data) between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellInsuranceListVo.getEnterpriseId()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate())){
			sql.append(" and a.insurer_start_date>= '"+sellInsuranceListVo.getInsurerStartDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerStartDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerStartDate1())){
			sql.append(" and a.insurer_start_date<= '"+sellInsuranceListVo.getInsurerStartDate1()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate())){
			sql.append(" and a.insurer_end_date >= '"+sellInsuranceListVo.getInsurerEndDate()+"'");
		}
		if(sellInsuranceListVo.getInsurerEndDate1()!=null&& !"".equals(sellInsuranceListVo.getInsurerEndDate1())){
			sql.append(" and a.insurer_end_date<= '"+sellInsuranceListVo.getInsurerEndDate1()+"'");
		}
		if(sellInsuranceListVo.getCustomName()!=null && !"".equals(sellInsuranceListVo.getCustomName())){
			sql.append(" and cus.xs_custom_name like '%"+new String(sellInsuranceListVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8")+"%'");
		}
	
		sql.append(" GROUP BY a.person");
		List<Object[]> resultList = createSQLQuery(sql.toString(),sellInsuranceListVo.getPage(),sellInsuranceListVo.getRows());
		List<SellInsuranceListVo> footers= new ArrayList<SellInsuranceListVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellInsuranceListVo sell=null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell.setPerson(obj[0] != null ?Integer.parseInt( obj[0].toString()): null);
				sell.setStfName(obj[1] != null ? obj[1].toString(): null);
				sell.setNum(obj[2] != null ? Integer.parseInt(obj[2].toString()): null);
				sell.setPrimeCost(obj[3] != null ? Double.parseDouble(obj[3].toString()): null);
				sell.setSum(obj[4] != null ? Double.parseDouble(obj[4].toString()): null);
				sell.setCustomCost(obj[5] != null ? Double.parseDouble(obj[5].toString()): null);
				sell.setProfit(obj[6] != null ? Double.parseDouble(obj[6].toString()): null);
				
				sell.setState("closed");
				sell.setIconCls("icon-blank");	
				list.add(sell);
	}
			
			sell = new SellInsuranceListVo();
			
			sell.setStfName("汇总");
			sell.setState("open");
			sell.setIconCls("icon-blank");
			int count = 0;
			Double primeCost =0.0;
			Double customCost=0.0;
			Double sum=0.0;
			Double profit=0.0;
			for (SellInsuranceListVo vo2 : list) {
				if(vo2.getNum()!=null&&!"".equals(vo2.getNum())){
					count += (vo2.getNum());
				}
				if(vo2.getPrimeCost()!=null&&!"".equals(vo2.getPrimeCost())){
					primeCost +=vo2.getPrimeCost();
				}
				
				if(vo2.getCustomCost()!=null&&!"".equals(vo2.getCustomCost())){
					customCost +=vo2.getCustomCost();
				}
				
				if(vo2.getSum()!=null&&!"".equals(vo2.getSum())){
					sum +=vo2.getSum();
				}
				
				if(vo2.getProfit()!=null&&!"".equals(vo2.getProfit())){
					profit +=vo2.getProfit();
				}
				
				
			}
			sell.setNum(count);
			sell.setPrimeCost(primeCost);
			sell.setSum(sum);
			sell.setCustomCost(customCost);
			sell.setProfit(profit);
			footers.add(sell);
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setFooter(footers);
		dg.setTotal(total);
		return dg;
	}
}
	

	


