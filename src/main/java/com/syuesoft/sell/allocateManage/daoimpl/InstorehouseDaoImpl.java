package com.syuesoft.sell.allocateManage.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.dao.InstorehouseDao;
import com.syuesoft.sell.allocateManage.vo.InstorehouseVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.util.FormatTime;

@Repository("instDao")
public class InstorehouseDaoImpl extends BaseDaoImpl<BaseBean> implements InstorehouseDao{
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	//库存查询
	
	public Datagrid queryInstorehouse(InstorehouseVo instorehouseVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql=new StringBuffer("SELECT zz.detailsId,zz.instorehouseDate,zz.carInstorageAge,zz.carBrand,zz.carBrandName,zz.carModelId,zz.carModelName,zz.carColor,zz.carColorName,zz.carInteriorColor,");
		sql.append(" zz.carInteriorColorName,zz.carOcn,zz.carVinNumber,zz.xsModelSalesPrice,zz.carMotorNumber,zz.carCertificateState,zz.carCertificateStateN,zz.carCertificate,zz.carSellState,zz.carSellStateN,");
		sql.append(" zz.distributorId,zz.distributorName,zz.customId,zz.customName,zz.carAddress,zz.carUnlockingKeyNumber,zz.xsModelCostPrice,zz.reserveDete,zz.carMakeData,zz.carAssembleData,");
		sql.append(" zz.carForecastData,zz.carEndCheckData,zz.carLicenseName,zz.carPdsData,zz.carPdsPerson,zz.pdsPerson,zz.carPdsResult,zz.carTradeCheckBill,zz.xsSupplierId,zz.xsSupplierName,");
		sql.append(" (SELECT CASE  WHEN zz.temp45!='' THEN zz.temp45 ELSE zz.warehouse END )AS temp41,");
		sql.append(" (SELECT CASE  WHEN zz.temp45!='' THEN zz.temp46 ELSE zz.warehouseN END )AS temp42,");
		sql.append(" zz.reserveCode,zz.carCode	FROM (");
		sql.append(" SELECT aa.details_id AS detailsId,bb.instorehouse_date AS instorehouseDate,"); 
		sql.append(" (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) AS carInstorageAge ,cc.xs_car_brand AS carBrand,"); 
		sql.append(" (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id=cc.xs_car_brand) AS carBrandName,");
		sql.append(" cc.xs_car_model_id AS carModelId,dd.xs_model_name AS carModelName,cc.xs_car_color AS carColor,");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE  ch.child_id =cc.xs_car_color) AS carColorName,cc.xs_car_InteriorColor AS carInteriorColor,"); 
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =cc.xs_car_InteriorColor) AS  carInteriorColorName,");
		sql.append("  cc.xs_car_ocn AS carOcn,cc.xs_car_vin_number AS carVinNumber, dd.xs_model_salesPrice AS xsModelSalesPrice,");
		sql.append("  cc.xs_car_motor_number AS carMotorNumber,cc.xs_car_certificate_state AS carCertificateState,");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id=cc.xs_car_certificate_state) AS carCertificateStateN,");
		sql.append("  cc.xs_car_certificate AS carCertificate,cc.xs_car_sell_state AS carSellState, ");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =cc.xs_car_sell_state) AS carSellStateN,");                                                               
		sql.append(" cc.xs_distributor_id AS distributorId,ee.xs_distributor_name AS distributorName,");
		sql.append(" ff.custom_id AS customId,gg.xs_custom_name AS customName,");
		sql.append(" cc.xs_car_address AS carAddress,cc.xs_car_unlockingKeyNumber AS carUnlockingKeyNumber,");
		sql.append(" aa.vehicle_cost AS xsModelCostPrice,ff.reserve_dete AS reserveDete,");
		sql.append(" cc.xs_car_make_data AS carMakeData,cc.xs_car_assemble_data AS carAssembleData,cc.xs_car_forecast_data AS carForecastData,");
		sql.append(" cc.xs_car_endCheck_data AS carEndCheckData,cc.xs_car_licenseName AS carLicenseName,");
		sql.append(" aa.xs_car_pds_data AS carPdsData,aa.xs_car_pds_person AS carPdsPerson,");
		sql.append(" ii.STF_NAME AS pdsPerson,aa.xs_car_pds_result AS carPdsResult,cc.xs_car_tradeCheck_bill AS carTradeCheckBill,");                                                           
		sql.append(" bb.xs_supplier_id AS xsSupplierId,hh.xs_supplier_name AS xsSupplierName,");
		sql.append(" bb.warehouse AS warehouse, ");
		sql.append(" ( SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =bb.warehouse )AS warehouseN ,");                                                  
		sql.append(" ff.reserve_code AS reserveCode,cc.xs_car_code AS carCode,");
		sql.append(" kk.in_instorehouse AS temp45,");
		sql.append(" ( SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =kk.in_instorehouse ) AS temp46");
		sql.append(" FROM xs_sell_instorehouse_del aa " +
				"INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id " );
		sql.append(" INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code  AND flow.xsfcontrol_car_id=aa.xs_car_id");
		sql.append(" INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id ");
		sql.append(" INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"); 
		sql.append(" LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id"); 
		sql.append(" LEFT OUTER JOIN xs_sell_car_reserve ff ON ff.xs_car_id=cc.xs_car_id ");
		sql.append(" LEFT OUTER JOIN xs_custom_info gg ON gg.custom_id=ff.custom_id ");
		sql.append(" INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id"); 
		sql.append(" LEFT OUTER JOIN bas_stuff ii ON ii.STF_ID=aa.xs_car_pds_person ");                               
		sql.append(" LEFT OUTER JOIN (");
		sql.append(" SELECT xsr.* FROM (SELECT temp.* FROM xs_sell_retreat temp ORDER BY temp.retreat_id DESC ) xsr WHERE xsr.retreat_code LIKE '"+Contstants.SELL_BILLSDEPLOY.INSTOREMOVE+"%' AND xsr.examine="+
				baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE,instorehouseVo.getEnterprise_id())+" GROUP BY xsr.details_id ORDER BY xsr.retreat_id DESC");
		sql.append(" ) kk ON kk.details_id=aa.details_id");
		sql.append(" WHERE bb.examine_state=");
		sql.append(" ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent"); 
		sql.append(" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+instorehouseVo.getEnterprise_id()+")"); 
		sql.append(" and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
				" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent"); 
		sql.append(" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
				"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+instorehouseVo.getEnterprise_id()+")" +
				" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
				""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
						" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" +
						" and bb.enterprise_id ="+instorehouseVo.getEnterprise_id());
		
		if (instorehouseVo.getInstorehouseDate() != null
					&& !"".equals(instorehouseVo.getInstorehouseDate())) {	
				sql.append(" and DATE(bb.instorehouse_date) >= '" + instorehouseVo.getInstorehouseDate() + "'");
			}
		if (instorehouseVo.getInstorehouseDate2() != null
					&& !"".equals(instorehouseVo.getInstorehouseDate2())) {
				sql.append(" and DATE(bb.instorehouse_date) <= '" + instorehouseVo.getInstorehouseDate2() + "'");
				
		}
		if((instorehouseVo.getInstorehouseDate() == null
				||"".equals(instorehouseVo.getInstorehouseDate()))&&
				(instorehouseVo.getInstorehouseDate2() == null
				||"".equals(instorehouseVo.getInstorehouseDate2()))){
				sql.append(" and DATE(bb.instorehouse_date) BETWEEN  " +
						"'" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
								(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instorehouseVo.getEnterprise_id()).getCiValue())) + "' " +
								" and '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
			}
			
			if (instorehouseVo.getCarSellState() != null
					&& !"".equals(instorehouseVo.getCarSellState())) {
				sql.append(" and cc.xs_car_sell_state='"
						+ instorehouseVo.getCarSellState() + "'");
			}
			if (instorehouseVo.getCarBrand() != null
					&& !"".equals(instorehouseVo.getCarBrand())) {
				sql.append(" and cc.xs_car_brand='"
						+ instorehouseVo.getCarBrand() + "'");
			}
			if (instorehouseVo.getCarVinNumber() != null
					&& !"".equals(instorehouseVo.getCarVinNumber())) {
				sql.append(" and cc.xs_car_vin_number like '%"
						+ instorehouseVo.getCarVinNumber() + "%'");
			}
			if (instorehouseVo.getCarModelId() != null
					&& !"".equals(instorehouseVo.getCarModelId())) {
				sql.append(" and cc.xs_car_model_id='"
						+ instorehouseVo.getCarModelId() + "'");
			}
			if (instorehouseVo.getCarMotorNumber() != null
					&& !"".equals(instorehouseVo.getCarMotorNumber())) {
				sql.append(" and cc.xs_car_motor_number like '%"
						+ instorehouseVo.getCarMotorNumber() + "%'");
			}
			if (instorehouseVo.getCarOcn() != null
					&& !"".equals(instorehouseVo.getCarOcn())) {
				sql.append(" and cc.xs_car_ocn like '%"
						+ instorehouseVo.getCarOcn() + "%'");
			}
			if (instorehouseVo.getDistributorId() != null
					&& !"".equals(instorehouseVo.getDistributorId())) {
				sql.append(" and cc.xs_distributor_id='"
						+ instorehouseVo.getDistributorId() + "'");
			}
			if (instorehouseVo.getCarColor() != null
					&& !"".equals(instorehouseVo.getCarColor())) {
				sql.append(" and cc.xs_car_color='"
						+ instorehouseVo.getCarColor() + "'");
			}
			if (instorehouseVo.getCarInteriorColor() != null
					&& !"".equals(instorehouseVo.getCarInteriorColor())) {
				sql.append(" and cc.xs_car_InteriorColor='"
						+ instorehouseVo.getCarInteriorColor() + "'");
			}
			if (instorehouseVo.getCarCertificate() != null
					&& !"".equals(instorehouseVo.getCarCertificate())) {
				sql.append(" and cc.xs_car_certificate='"
						+ instorehouseVo.getCarCertificate() + "'");
			}
			if (instorehouseVo.getCarInstorageAge() != null
					&& !"".equals(instorehouseVo.getCarInstorageAge())) {
				sql.append(" and  (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) >='"
						+ instorehouseVo.getCarInstorageAge() + "'");
			}
			if (instorehouseVo.getCarInstorageAge1() != null
					&& !"".equals(instorehouseVo.getCarInstorageAge1())) {
				sql.append(" and  (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) <='"
						+ instorehouseVo.getCarInstorageAge1() + "'");
			}
			if (instorehouseVo.getWarehouse() != null
					&& !"".equals(instorehouseVo.getWarehouse())) {
				sql.append(" and bb.warehouse='"
						+ instorehouseVo.getWarehouse() + "'");
			}
			if (instorehouseVo.getXsSupplierId() != null
					&& !"".equals(instorehouseVo.getXsSupplierId())) {
				sql.append(" and bb.xs_supplier_id='"
						+ instorehouseVo.getXsSupplierId() + "'");
			}
			if (instorehouseVo.getCarAddress() != null
					&& !"".equals(instorehouseVo.getCarAddress())) {
				sql.append(" and cc.xs_car_address like '%"
						+ instorehouseVo.getCarAddress() + "%'");
			}
			if (instorehouseVo.getCarCode() != null
					&& !"".equals(instorehouseVo.getCarCode())) {
				sql.append(" and cc.xs_car_code like '%"
						+ instorehouseVo.getCarCode() + "%'");
			}
			if (instorehouseVo.getCarAge() != null
					&& "true".equals(instorehouseVo.getCarAge())) {
				sql.append(" and (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) >" +
						"(SELECT pp.CI_VALUE FROM xs_sell_parameter pp WHERE pp.enterprise_id="+instorehouseVo.getEnterprise_id()+" and  pp.CI_NAME= '"+Contstants.SELLPARAM.CARAGEDAY+"' )");
			}
			sql.append(" ) zz ");
			  if (instorehouseVo.getSort() != null && !"".equals(instorehouseVo.getSort().trim())
		                && instorehouseVo.getOrder() != null
		                && !"".equals(instorehouseVo.getOrder().trim()))
		        {
		            sql.append(" order by zz." + instorehouseVo.getSort().trim() + " "
		                    + instorehouseVo.getOrder().trim());
		        }

	    List<Object[]> resultList = createSQLQuery(sql.toString(),
				instorehouseVo.getPage(),instorehouseVo.getRows());
		List<InstorehouseVo> list = new ArrayList<InstorehouseVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < resultList.size(); i++) {
				InstorehouseVo wopq = new InstorehouseVo();
				obj = resultList.get(i);
				wopq.setDetailsId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				wopq.setInstorehouseDate(obj[1] != null ? fmt.format(obj[1]) : null);
				wopq.setCarInstorageAge(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
				wopq.setCarBrand(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				wopq.setCarBrandName(obj[4] != null ? obj[4].toString() : null);
				wopq.setCarModelId(obj[5] != null ? Integer.parseInt(obj[5].toString()) : null);
				wopq.setCarModelName(obj[6] != null ? obj[6].toString() : null);
				wopq.setCarColor(obj[7] != null ? Integer.parseInt(obj[7].toString()) : null);
				wopq.setCarColorName(obj[8] != null ? obj[8].toString() : null);
				wopq.setCarInteriorColor(obj[9] != null ? Integer.parseInt(obj[9].toString()) : null);
				wopq.setCarInteriorColorName(obj[10] != null ? obj[10].toString() : null);
				wopq.setCarOcn(obj[11] != null ? obj[11].toString() : null);
				wopq.setCarVinNumber(obj[12] != null ? obj[12].toString() : null);
				wopq.setXsModelSalesPrice(obj[13] != null ? Double.parseDouble(obj[13].toString()) : null);
				wopq.setCarMotorNumber(obj[14] != null ? obj[14].toString() : null);
				wopq.setCarCertificateState(obj[15] != null ? Integer.parseInt(obj[15].toString()) : null);
				wopq.setCarCertificateStateN(obj[16] != null ?obj[16].toString() : null);
				wopq.setCarCertificate(obj[17] != null ?obj[17].toString() : null);
				wopq.setCarSellState(obj[18] != null ? Integer.parseInt(obj[18].toString()) : null);
				wopq.setCarSellStateN(obj[19] != null ? obj[19].toString() : null);
				wopq.setDistributorId(obj[20] != null ? Integer.parseInt(obj[20].toString()) : null);
				wopq.setDistributorName(obj[21] != null ? obj[21].toString() : null);
				wopq.setCustomId(obj[22] != null ? Integer.parseInt(obj[22].toString()) : null);
				wopq.setCustomName(obj[23] != null ? obj[23].toString() : null);
				wopq.setCarAddress(obj[24] != null ? obj[24].toString() : null);
				wopq.setCarUnlockingKeyNumber(obj[25] != null ? obj[25].toString() : null);
				wopq.setXsModelCostPrice(obj[26] != null ? Double.parseDouble(obj[26].toString()): null);
				wopq.setReserveDete(obj[27] != null ? obj[27].toString() : null);
				wopq.setCarMakeData(obj[28] != null ? obj[28].toString() : null);
				wopq.setCarAssembleData(obj[29] != null ? obj[29].toString() : null);
				wopq.setCarForecastData(obj[30] != null ? obj[30].toString() : null);
				wopq.setCarEndCheckData(obj[31] != null ? obj[31].toString() : null);
				wopq.setCarLicenseName(obj[32] != null ? obj[32].toString() : null);
				wopq.setCarPdsData(obj[33] != null ? obj[33].toString() : null);
				wopq.setCarPdsPerson(obj[34] != null ? Integer.parseInt( obj[34].toString() ): null);
				wopq.setPdsPerson(obj[35] != null ?  obj[35].toString(): null);
				wopq.setCarPdsResult(obj[36] != null ? obj[36].toString() : null);
				wopq.setCarTradeCheckBill(obj[37] != null ? obj[37].toString() : null);
				wopq.setXsSupplierId(obj[38] != null ? Integer.parseInt( obj[38].toString() ): null);
				wopq.setXsSupplierName(obj[39] != null ? obj[39].toString() : null);
				wopq.setWarehouse(obj[40] != null ? Integer.parseInt( obj[40].toString() ): null);
				wopq.setWarehouseN(obj[41] != null ? obj[41].toString() : null);
				wopq.setReserveCode(obj[42] != null ? obj[42].toString() : null);
				wopq.setCarCode(obj[43] != null ? obj[43].toString() : null);
				list.add(wopq);				
			}
			}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	
	public List queryInstorehouseSum(InstorehouseVo instorehouseVo)
			throws Exception {
		StringBuffer sql=new StringBuffer("SELECT zz.temp1,zz.temp2,zz.temp3,zz.temp4,zz.temp5,zz.temp6,zz.temp7,zz.temp8,zz.temp9,zz.temp10,");
		sql.append(" zz.temp11,zz.temp12,zz.temp13,zz.temp14,zz.temp15,zz.temp16,zz.temp17,zz.temp18,zz.temp19,zz.temp20,");
		sql.append(" zz.temp21,zz.temp22,zz.temp23,zz.temp24,zz.temp25,zz.temp26,zz.temp27,zz.temp28,zz.temp29,zz.temp30,");
		sql.append(" zz.temp31,zz.temp32,zz.temp33,zz.temp34,zz.temp35,zz.temp36,zz.temp37,zz.temp38,zz.temp39,zz.temp40,");
		sql.append(" (SELECT CASE  WHEN zz.temp45!='' THEN zz.temp45 ELSE zz.temp41 END )AS temp41,");
		sql.append(" (SELECT CASE  WHEN zz.temp45!='' THEN zz.temp48 ELSE zz.temp49 END )AS temp42,");
		sql.append(" zz.temp43,zz.temp44,zz.temp45,zz.temp46,zz.temp47	FROM (");
		sql.append(" SELECT aa.details_id AS temp1,bb.instorehouse_date AS temp2,"); 
		sql.append(" (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) AS temp3 ,cc.xs_car_brand AS temp4,"); 
		sql.append(" (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id=cc.xs_car_brand) AS temp5,");
		sql.append(" cc.xs_car_model_id AS temp6,dd.xs_model_name AS temp7,cc.xs_car_color AS temp8,");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE  ch.child_id =cc.xs_car_color) AS temp9,cc.xs_car_InteriorColor AS temp10,"); 
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =cc.xs_car_InteriorColor) AS  temp11,");
		sql.append("  cc.xs_car_ocn AS temp12,cc.xs_car_vin_number AS temp13, dd.xs_model_salesPrice AS temp14,");
		sql.append("  cc.xs_car_motor_number AS temp15,cc.xs_car_certificate_state AS temp16,");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id=cc.xs_car_certificate_state) AS  temp17,");
		sql.append("  cc.xs_car_certificate AS temp18,cc.xs_car_sell_state AS temp19, ");
		sql.append("  (SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =cc.xs_car_sell_state) AS temp20,");                                                               
		sql.append(" cc.xs_distributor_id AS temp21,ee.xs_distributor_name AS temp22,");
		sql.append(" ff.custom_id AS temp23,gg.xs_custom_name AS temp24,");
		sql.append(" cc.xs_car_address AS temp25,cc.xs_car_unlockingKeyNumber AS temp26,");
		sql.append(" aa.vehicle_cost AS temp27,ff.reserve_dete AS temp28,");
		sql.append(" cc.xs_car_make_data AS temp29,cc.xs_car_assemble_data AS temp30,cc.xs_car_forecast_data AS temp31,");
		sql.append(" cc.xs_car_endCheck_data AS temp32,cc.xs_car_licenseName AS temp33,");
		sql.append(" aa.xs_car_pds_data AS temp34,aa.xs_car_pds_person AS temp35,");
		sql.append(" ii.STF_NAME AS temp36,aa.xs_car_pds_result AS temp37,cc.xs_car_tradeCheck_bill AS temp38,");                                                           
		sql.append(" bb.xs_supplier_id AS temp39,hh.xs_supplier_name AS temp40,");
		sql.append(" bb.warehouse AS temp41, ");
		sql.append(" ( SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =bb.warehouse )AS temp42,");                                                  
		sql.append(" ff.reserve_code AS temp43,cc.xs_car_code AS temp44,");
		sql.append(" COUNT(*) AS temp45 ,  SUM(dd.xs_model_salesPrice) AS temp46, SUM(aa.vehicle_cost) AS temp47,");
		sql.append(" kk.in_instorehouse AS temp48,");
		sql.append(" ( SELECT datavalue FROM xs_childdictionary ch WHERE ch.child_id =kk.in_instorehouse ) AS temp49");
		sql.append(" FROM xs_sell_instorehouse_del aa " +
				"INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "); 
		sql.append(" INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code  AND flow.xsfcontrol_car_id=aa.xs_car_id");
		sql.append(" INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id ");
		sql.append(" INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"); 
		sql.append(" LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id"); 
		sql.append(" LEFT OUTER JOIN xs_sell_car_reserve ff ON ff.xs_car_id=cc.xs_car_id ");
		sql.append(" LEFT OUTER JOIN xs_custom_info gg ON gg.custom_id=ff.custom_id ");
		sql.append(" INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id"); 
		sql.append(" LEFT OUTER JOIN bas_stuff ii ON ii.STF_ID=aa.xs_car_pds_person ");                               
		sql.append(" LEFT OUTER JOIN (");
		sql.append(" SELECT xsr.* FROM (SELECT temp.* FROM xs_sell_retreat temp ORDER BY temp.retreat_id DESC ) xsr WHERE xsr.retreat_code LIKE '"+Contstants.SELL_BILLSDEPLOY.INSTOREMOVE+"%' AND xsr.examine="+
				baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE,instorehouseVo.getEnterprise_id())+" GROUP BY xsr.details_id ORDER BY xsr.retreat_id DESC");
		sql.append(" ) kk ON kk.details_id=aa.details_id");                                                    
		sql.append(" WHERE bb.examine_state=");
		sql.append(" ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent"); 
		sql.append(" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+instorehouseVo.getEnterprise_id()+")"); 
		sql.append("and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
				" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent"); 
		sql.append(" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
				"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+instorehouseVo.getEnterprise_id()+")" +
				" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
				""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
						"  AND  re.retreat_id NOT IN (SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  " +
						"parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND  " +
						" child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" +
						" and bb.enterprise_id ="+instorehouseVo.getEnterprise_id());		
	
		if (instorehouseVo.getInstorehouseDate() != null
				&& !"".equals(instorehouseVo.getInstorehouseDate())) {
			
					sql.append(" and DATE(bb.instorehouse_date) >= '" + instorehouseVo.getInstorehouseDate() + "'");
		} 
		if (instorehouseVo.getInstorehouseDate2() != null
				&& !"".equals(instorehouseVo.getInstorehouseDate2())) {
			
					sql.append(" and DATE(bb.instorehouse_date) <= '" + instorehouseVo.getInstorehouseDate2() + "'");
		
		}
		if((instorehouseVo.getInstorehouseDate() == null
				||"".equals(instorehouseVo.getInstorehouseDate()))&&
				(instorehouseVo.getInstorehouseDate2() == null
				||"".equals(instorehouseVo.getInstorehouseDate2()))){
				sql.append(" and DATE(bb.instorehouse_date) BETWEEN  " +
						"'" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
								(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instorehouseVo.getEnterprise_id()).getCiValue())) + "' " +
								" and '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
			}
			
	
		if (instorehouseVo.getCarSellState() != null
				&& !"".equals(instorehouseVo.getCarSellState())) {
			sql.append(" and cc.xs_car_sell_state='"
					+ instorehouseVo.getCarSellState() + "'");
		}
		if (instorehouseVo.getCarBrand() != null
				&& !"".equals(instorehouseVo.getCarBrand())) {
			sql.append(" and cc.xs_car_brand='"
					+ instorehouseVo.getCarBrand() + "'");
		}
		if (instorehouseVo.getCarVinNumber() != null
				&& !"".equals(instorehouseVo.getCarVinNumber())) {
			sql.append(" and cc.xs_car_vin_number='"
					+ instorehouseVo.getCarVinNumber() + "'");
		}
		if (instorehouseVo.getCarModelId() != null
				&& !"".equals(instorehouseVo.getCarModelId())) {
			sql.append(" and cc.xs_car_model_id='"
					+ instorehouseVo.getCarModelId() + "'");
		}
		if (instorehouseVo.getCarMotorNumber() != null
				&& !"".equals(instorehouseVo.getCarMotorNumber())) {
			sql.append(" and cc.xs_car_motor_number='"
					+ instorehouseVo.getCarMotorNumber() + "'");
		}
		if (instorehouseVo.getCarOcn() != null
				&& !"".equals(instorehouseVo.getCarOcn())) {
			sql.append(" and cc.xs_car_ocn='"
					+ instorehouseVo.getCarOcn() + "'");
		}
		if (instorehouseVo.getDistributorId() != null
				&& !"".equals(instorehouseVo.getDistributorId())) {
			sql.append(" and cc.xs_distributor_id='"
					+ instorehouseVo.getDistributorId() + "'");
		}
		if (instorehouseVo.getCarColor() != null
				&& !"".equals(instorehouseVo.getCarColor())) {
			sql.append(" and cc.xs_car_color='"
					+ instorehouseVo.getCarColor() + "'");
		}
		if (instorehouseVo.getCarInteriorColor() != null
				&& !"".equals(instorehouseVo.getCarInteriorColor())) {
			sql.append(" and cc.xs_car_InteriorColor='"
					+ instorehouseVo.getCarInteriorColor() + "'");
		}
		if (instorehouseVo.getCarCertificate() != null
				&& !"".equals(instorehouseVo.getCarCertificate())) {
			sql.append(" and cc.xs_car_certificate='"
					+ instorehouseVo.getCarCertificate() + "'");
		}
		if (instorehouseVo.getCarInstorageAge() != null
				&& !"".equals(instorehouseVo.getCarInstorageAge())) {
			sql.append(" and  (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) >='"
					+ instorehouseVo.getCarInstorageAge() + "'");
		}
		if (instorehouseVo.getCarInstorageAge1() != null
				&& !"".equals(instorehouseVo.getCarInstorageAge1())) {
			sql.append(" and  (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) <='"
					+ instorehouseVo.getCarInstorageAge1() + "'");
		}
		if (instorehouseVo.getWarehouse() != null
				&& !"".equals(instorehouseVo.getWarehouse())) {
			sql.append(" and bb.warehouse='"
					+ instorehouseVo.getWarehouse() + "'");
		}
		if (instorehouseVo.getXsSupplierId() != null
				&& !"".equals(instorehouseVo.getXsSupplierId())) {
			sql.append(" and bb.xs_supplier_id='"
					+ instorehouseVo.getXsSupplierId() + "'");
		}
		if (instorehouseVo.getCarAddress() != null
				&& !"".equals(instorehouseVo.getCarAddress())) {
			sql.append(" and cc.xs_car_address='"
					+ instorehouseVo.getCarAddress() + "'");
		}
		if (instorehouseVo.getCarCode() != null
				&& !"".equals(instorehouseVo.getCarCode())) {
			sql.append(" and cc.xs_car_code='"
					+ instorehouseVo.getCarCode() + "'");
		}
		if (instorehouseVo.getCarAge() != null
				&& "true".equals(instorehouseVo.getCarAge())) {
			sql.append(" and (SELECT DATEDIFF(NOW(),bb.instorehouse_date)) >(SELECT pp.CI_VALUE FROM xs_sell_parameter pp WHERE pp.enterprise_id="+instorehouseVo.getEnterprise_id()+" and pp.CI_NAME= '"+Contstants.SELLPARAM.CARAGEDAY+"' )");
		}
		sql.append(" ) zz ");
		

		List<Object[]> resultList = createSQLQuery(sql.toString());
		List<InstorehouseVo> list = new ArrayList<InstorehouseVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < resultList.size(); i++) {
				InstorehouseVo wopq = new InstorehouseVo();
				obj = resultList.get(i);
				wopq.setDetailsId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				wopq.setInstorehouseDate(obj[1] != null ?fmt.format( obj[1]) : null);
				wopq.setCarInstorageAge(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
				wopq.setCarBrand(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				wopq.setCarBrandName(obj[4] != null ? obj[4].toString() : null);
				wopq.setCarModelId(obj[5] != null ? Integer.parseInt(obj[5].toString()) : null);
				wopq.setCarModelName(obj[6] != null ? obj[6].toString() : null);
				wopq.setCarColor(obj[7] != null ? Integer.parseInt(obj[7].toString()) : null);
				wopq.setCarColorName(obj[8] != null ? obj[8].toString() : null);
				wopq.setCarInteriorColor(obj[9] != null ? Integer.parseInt(obj[9].toString()) : null);
				wopq.setCarInteriorColorName(obj[10] != null ? obj[10].toString() : null);
				wopq.setCarOcn(obj[11] != null ? obj[11].toString() : null);
				wopq.setCarVinNumber(obj[12] != null ? obj[12].toString() : null);
				wopq.setXsModelSalesPrice(obj[13] != null ? Double.parseDouble(obj[13].toString()) : null);
				wopq.setCarMotorNumber(obj[14] != null ? obj[14].toString() : null);
				wopq.setCarCertificateState(obj[15] != null ? Integer.parseInt(obj[15].toString()) : null);
				wopq.setCarCertificateStateN(obj[16] != null ?obj[16].toString() : null);
				wopq.setCarCertificate(obj[17] != null ?obj[17].toString() : null);
				wopq.setCarSellState(obj[18] != null ? Integer.parseInt(obj[18].toString()) : null);
				wopq.setCarSellStateN(obj[19] != null ? obj[19].toString() : null);
				wopq.setDistributorId(obj[20] != null ? Integer.parseInt(obj[20].toString()) : null);
				wopq.setDistributorName(obj[21] != null ? obj[21].toString() : null);
				wopq.setCustomId(obj[22] != null ? Integer.parseInt(obj[22].toString()) : null);
				wopq.setCustomName(obj[23] != null ? obj[23].toString() : null);
				wopq.setCarAddress(obj[24] != null ? obj[24].toString() : null);
				wopq.setCarUnlockingKeyNumber(obj[25] != null ? obj[25].toString() : null);
				wopq.setXsModelCostPrice(obj[26] != null ? Double.parseDouble(obj[26].toString()): null);
				wopq.setReserveDete(obj[27] != null ? obj[27].toString() : null);
				wopq.setCarMakeData(obj[28] != null ? obj[28].toString() : null);
				wopq.setCarAssembleData(obj[29] != null ? obj[29].toString() : null);
				wopq.setCarForecastData(obj[30] != null ? obj[30].toString() : null);
				wopq.setCarEndCheckData(obj[31] != null ? obj[31].toString() : null);
				wopq.setCarLicenseName(obj[32] != null ? obj[32].toString() : null);
				wopq.setCarPdsData(obj[33] != null ? obj[33].toString() : null);
				wopq.setCarPdsPerson(obj[34] != null ? Integer.parseInt( obj[34].toString() ): null);
				wopq.setPdsPerson(obj[35] != null ?  obj[35].toString(): null);
				wopq.setCarPdsResult(obj[36] != null ? obj[36].toString() : null);
				wopq.setCarTradeCheckBill(obj[37] != null ? obj[37].toString() : null);
				wopq.setXsSupplierId(obj[38] != null ? Integer.parseInt( obj[38].toString() ): null);
				wopq.setXsSupplierName(obj[39] != null ? obj[39].toString() : null);
				wopq.setWarehouse(obj[40] != null ? Integer.parseInt( obj[40].toString() ): null);
				wopq.setWarehouseN(obj[41] != null ? obj[41].toString() : null);
				wopq.setReserveCode(obj[42] != null ? obj[42].toString() : null);
				wopq.setCarCode(obj[43] != null ? obj[43].toString() : null);
				wopq.setNum(obj[44] != null ? Integer.parseInt( obj[44].toString() ): null);
				wopq.setSalesPrice(obj[45] != null ? Double.parseDouble(obj[45].toString()): null);
				wopq.setCostPrice(obj[46] != null ? Double.parseDouble(obj[46].toString()): null);
				list.add(wopq);				
			}
			}
		return list;
	}

}
