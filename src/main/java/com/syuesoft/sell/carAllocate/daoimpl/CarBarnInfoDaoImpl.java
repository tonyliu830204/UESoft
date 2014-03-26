package com.syuesoft.sell.carAllocate.daoimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.dao.CarBarnInfoDao;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.util.FormatTime;

@Repository("carBarnInfoDao")
public class CarBarnInfoDaoImpl extends BaseDaoImpl<XsCarInfo> implements
		CarBarnInfoDao {
	/**
	 * 查询在库待销车辆信息
	 */
	
	public Datagrid findCarBarn(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		
		Datagrid dg = new Datagrid();
		List<CarBarnInfoVo> list_ = new ArrayList<CarBarnInfoVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_ocn,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_sell_state) AS Ss ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_distribut_state) AS Ds ,"
						+ "CI.xs_car_production_address,instorehouse.details_id,CI.xs_car_copy_data,"
						+ "CI.xs_car_code,CI.xs_car_motor_number,CI.xs_car_licenseName,CI.xs_car_id,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,CI.xs_car_brand,CI.xs_car_color,   "
						+ " model.xs_model_costPrice, model.xs_model_salesPrice, (SELECT DATEDIFF(NOW(),house.instorehouse_date)) As age "
						+ "FROM xs_car_info CI inner " +
						" JOIN  xs_car_model model ON CI.xs_car_model_id=model.xs_model_id " +
						"  JOIN xs_sell_instorehouse_del instorehouse  ON  CI.xs_car_id= instorehouse.xs_car_id " +
						"  JOIN xs_sell_instorehouse house ON  house.instorehouse_id = instorehouse.instorehouse_id"+
						" join xs_sell_flow_control flow on flow.xsfcontrol_car_id=instorehouse.xs_car_id and " +
						" flow.xsfcontrol_document = house.instorehouse_code and " +
						" flow.xsfcontrol_code='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3+"'"+
						" WHERE CI.xs_car_sell_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.SELLSTATE.BASE_SELLSTATE+"' AND " +
						"child.dataKey='"+Contstants.SELLSTATE.FORSALE+"'  and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+" " +
						") and house.examine_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
						" and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+") AND  instorehouse.details_id NOT IN(" +
						"SELECT details_id FROM xs_sell_retreat WHERE instorehouse_type=" +
						" (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.INSTORESTYLE.PARENTINSTORE+"' AND " +
						"child.dataKey='"+Contstants.INSTORESTYLE.BACK+"' and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+"))" +
						"AND instorehouse.details_id NOT IN (" +
						"SELECT del.details_id FROM xs_sell_instorehouse_del del INNER JOIN xs_sell_allocatel_detail de  ON de.details_id = del.details_id " +
						"INNER JOIN xs_sell_allocatel_back back    ON de.allocatel_detail_id = back.allocatel_detail_id " +
						"INNER JOIN xs_sell_back ba    ON back.back_id = ba.back_id " +
						"WHERE ba.examine=(SELECT child.child_id FROM xs_childdictionary  child, " +
								"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT2+"' " +
						"and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+" )) ");// 163=在库待销
		//企业编号
		if (carBarnInfoVo.getEnterprise_id() != null
				&& !"".equals(carBarnInfoVo.getEnterprise_id())) {
			sql.append(" and CI.Enterprise_id=" + carBarnInfoVo.getEnterprise_id()
					+ "");
		}
		if (carBarnInfoVo.getCarColor() != null
				&& !"".equals(carBarnInfoVo.getCarColor())) {
			sql.append(" and CI.xs_car_color='" + carBarnInfoVo.getCarColor()
					+ "'");
		}
		if (carBarnInfoVo.getCarBrand() != null
				&& !"".equals(carBarnInfoVo.getCarBrand())) {
			sql.append(" and CI.xs_car_brand='" + carBarnInfoVo.getCarBrand()
					+ "'");
		}
		if (carBarnInfoVo.getCarModel() != null
				&& !"".equals(carBarnInfoVo.getCarModel())) {
			sql.append(" and CI.xs_car_model_id='"
					+ carBarnInfoVo.getCarModel() + "'");
		}
		if (carBarnInfoVo.getCarVinNumber() != null
				&& !"".equals(carBarnInfoVo.getCarVinNumber())) {
			sql.append(" and CI.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(carBarnInfoVo.getCarVinNumber().trim()) + "%'");
		}

		List<Object[]> resultList = createSQLQuery(sql.toString(),carBarnInfoVo.getPage(),carBarnInfoVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarBarnInfoVo wopq = new CarBarnInfoVo();
				obj = resultList.get(i);
				wopq.setCarVinNumber(obj[0] != null ? obj[0].toString() : null);
				wopq.setCarModel(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				wopq.setCarModelName(obj[2] != null ? obj[2].toString() : null);
				wopq.setCarOcn(obj[3] != null ? obj[3].toString() : null);
				wopq.setCarSellStateName(obj[4] != null ? obj[4].toString()
						: null);
				wopq.setCarDistributStateName(obj[5] != null ? obj[5]
						.toString() : null);
				wopq.setCarProductionAddress(obj[6] != null ? obj[6].toString()
						: null);
				wopq.setDetailsId(obj[7] != null ? Integer.parseInt(obj[7]
				                                 						.toString()) : null);
				
				wopq.setCarCopyData(obj[8] != null ? FormatTime
						.timestamp2Str((Timestamp) obj[8]) : null);
				wopq.setCarCode(obj[9] != null ? obj[9].toString() : null);
				wopq.setCarMotorNumber(obj[10] != null ? obj[10].toString()
						: null);
				wopq.setCarLicenseName(obj[11] != null ? obj[11].toString()
						: null);
				wopq.setCarId(obj[12] != null ? Integer.parseInt(obj[12]
						.toString()) : null);
				wopq.setCarBrandName(obj[13] != null ? obj[13].toString()
						: null);
				wopq.setCarColorName(obj[14] != null ? obj[14].toString()
						: null);
				wopq.setCarBrand(obj[15] != null ? Integer.parseInt(obj[15]
						.toString()) : null);
				wopq.setCarColor(obj[16] != null ? Integer.parseInt(obj[16]
						.toString()) : null);
				wopq.setModelCostPrice(obj[17] != null ? Double
						.parseDouble(obj[17].toString()) : null);
				wopq.setModelSalesPrice(obj[18] != null ? Double
						.parseDouble(obj[18].toString()) : null);
				wopq.setCarInstorageAge(obj[19] != null ? Double
						.parseDouble(obj[19].toString()) : null);
				
				list_.add(wopq);
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list_);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 车辆调配模块：添加车辆功能（查询车辆在库待销，入库，已审核）
	 * 
	 */

	
	public Datagrid findCar(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		
		Datagrid dg = new Datagrid();
		List<CarBarnInfoVo> list_ = new ArrayList<CarBarnInfoVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_ocn,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_sell_state) AS Ss ,"
						+ "CI.xs_car_production_address,instorehouse.details_id ,CI.xs_car_copy_data,"
						+ "CI.xs_car_code,CI.xs_car_motor_number,CI.xs_car_licenseName,CI.xs_car_id,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,CI.xs_car_brand,CI.xs_car_color,   "
						+ " model.xs_model_costPrice, model.xs_model_salesPrice, (SELECT DATEDIFF(NOW(),sell.instorehouse_date)) As age,"
						+ " sell.number_,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =sell.warehouse) AS house "
						+ " ,sell.warehouse" +
						" FROM xs_car_info CI  " +
						" LEFT OUTER JOIN xs_car_model model ON CI.xs_car_model_id = model.xs_model_id " +
						" LEFT OUTER JOIN xs_sell_instorehouse_del instorehouse ON CI.xs_car_id = instorehouse.xs_car_id " +
						" LEFT OUTER JOIN xs_sell_instorehouse sell ON sell.instorehouse_id = instorehouse.instorehouse_id  " +
						" join xs_sell_flow_control flow on flow.xsfcontrol_document = sell.instorehouse_code and " +
						" flow.xsfcontrol_car_id=instorehouse.xs_car_id  AND " +
						" flow.xsfcontrol_code='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE3+"'"  +
						" WHERE sell.examine_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+carBarnInfoVo.getEnterprise_id() +
						" ) and CI.xs_car_sell_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.SELLSTATE.BASE_SELLSTATE+"' AND " +
								"child.dataKey='"+Contstants.SELLSTATE.FORSALE+"' and child.enterprise_id="+carBarnInfoVo.getEnterprise_id() +
						") AND  instorehouse.details_id NOT IN(" +
						"SELECT details_id FROM xs_sell_retreat WHERE instorehouse_type=" +
						" (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.INSTORESTYLE.PARENTINSTORE+"' AND " +
						"child.dataKey='"+Contstants.INSTORESTYLE.BACK+"' and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+")) " +
							" AND instorehouse.details_id NOT IN (" +
							"SELECT del.details_id FROM xs_sell_instorehouse_del del INNER JOIN xs_sell_allocatel_detail de  ON de.details_id = del.details_id " +
							"INNER JOIN xs_sell_allocatel_back back    ON de.allocatel_detail_id = back.allocatel_detail_id " +
							"INNER JOIN xs_sell_back ba    ON back.back_id = ba.back_id " +
							"WHERE ba.examine=(SELECT child.child_id FROM xs_childdictionary  child, " +
									"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
									"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
											"child.dataKey='"+Contstants.BASE_SELL.ADUIT2+"' and child.enterprise_id="+carBarnInfoVo.getEnterprise_id()+")) ");// 163=在库待销 ");//  241=入库   186=审核 163在库待销

		//企业编号
		if (carBarnInfoVo.getEnterprise_id() != null
				&& !"".equals(carBarnInfoVo.getEnterprise_id())) {
			sql.append(" and CI.Enterprise_id=" + carBarnInfoVo.getEnterprise_id()
					+ "");
		}
		if (carBarnInfoVo.getCarColor() != null
				&& !"".equals(carBarnInfoVo.getCarColor())) {
			sql.append(" and CI.xs_car_color='" + carBarnInfoVo.getCarColor()
					+ "'");
		}
		if (carBarnInfoVo.getCarBrand() != null
				&& !"".equals(carBarnInfoVo.getCarBrand())) {
			sql.append(" and CI.xs_car_brand='" + carBarnInfoVo.getCarBrand()
					+ "'");
		}
		if (carBarnInfoVo.getCarModel() != null
				&& !"".equals(carBarnInfoVo.getCarModel())) {
			sql.append(" and CI.xs_car_model_id='"
					+ carBarnInfoVo.getCarModel() + "'");
		}
		if (carBarnInfoVo.getCarVinNumber() != null
				&& !"".equals(carBarnInfoVo.getCarVinNumber())) {
			sql.append(" and CI.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(carBarnInfoVo.getCarVinNumber().trim()) + "%'");
		}

		List<Object[]> resultList = createSQLQuery(sql.toString(),carBarnInfoVo.getPage(),carBarnInfoVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarBarnInfoVo wopq = new CarBarnInfoVo();
				obj = resultList.get(i);
				wopq.setCarVinNumber(obj[0] != null ? obj[0].toString() : null);
				wopq.setCarModel(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				wopq.setCarModelName(obj[2] != null ? obj[2].toString() : null);
				wopq.setCarOcn(obj[3] != null ? obj[3].toString() : null);
				wopq.setCarSellStateName(obj[4] != null ? obj[4].toString()
						: null);
				
				wopq.setCarProductionAddress(obj[5] != null ? obj[5].toString()
						: null);
				wopq.setDetailsId(obj[6] != null ? Integer.parseInt(obj[6].toString()) : null);
				
				wopq.setCarCopyData(obj[7] != null ? FormatTime
						.timestamp2Str((Timestamp) obj[7]) : null);
				wopq.setCarCode(obj[8] != null ? obj[8].toString() : null);
				wopq.setCarMotorNumber(obj[9] != null ? obj[9].toString()
						: null);
				wopq.setCarLicenseName(obj[10] != null ? obj[10].toString()
						: null);
				wopq.setCarId(obj[11] != null ? Integer.parseInt(obj[11]
						.toString()) : null);
				wopq.setCarBrandName(obj[12] != null ? obj[2].toString()
						: null);
				wopq.setCarColorName(obj[13] != null ? obj[13].toString()
						: null);
				wopq.setCarBrand(obj[14] != null ? Integer.parseInt(obj[14]
						.toString()) : null);
				wopq.setCarColor(obj[15] != null ? Integer.parseInt(obj[15]
						.toString()) : null);
				wopq.setModelCostPrice(obj[16] != null ? Double
						.parseDouble(obj[16].toString()) : null);
				wopq.setModelSalesPrice(obj[17] != null ? Double
						.parseDouble(obj[17].toString()) : null);
				wopq.setCarInstorageAge(obj[18] != null ? Double
						.parseDouble(obj[18].toString()) : null);
				wopq.setNum(obj[19] != null ? Integer.parseInt(obj[19]
						.toString()) : null);
				wopq.setHouse(obj[20] != null ? obj[20].toString() : null);
				wopq.setHouseid(obj[21] != null ? Integer.parseInt(obj[21]
						.toString()) : null);
				
				list_.add(wopq);
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list_);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 根据调拨单汇总信息查询明细
	 * 
	 * @param carBarnInfoVo
	 * @return
	 * @throws Exception
	 */
	public List<CarBarnInfoVo> findAllocatel(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		List<CarBarnInfoVo> list_ = new ArrayList<CarBarnInfoVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_ocn,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_sell_state) AS Ss ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_distribut_state) AS Ds ,"
						+ "CI.xs_car_production_address,distributor.xs_distributor_name,CI.xs_car_copy_data,"
						+ "CI.xs_car_code,CI.xs_car_motor_number,CI.xs_car_licenseName,CI.xs_car_id,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,CI.xs_car_brand,CI.xs_car_color,   "
						+ " model.xs_model_costPrice, model.xs_model_salesPrice, instorehouse.xs_car_instorage_age,"
						+ " sell.number_,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =sell.warehouse) AS house "
						+ " ,sell.warehouse,instorehouse.details_id,instorehouse.vehicle_cost,de.allocate_amount   " +
						" FROM xs_car_info CI LEFT OUTER JOIN  xs_car_model model ON " +
						" CI.xs_car_model_id = model.xs_model_id " +
						"  left JOIN xs_distributor_info distributor  ON CI.xs_distributor_id = distributor.xs_distributor_id" +
						"  JOIN xs_sell_instorehouse_del instorehouse ON  CI.xs_car_id = instorehouse.xs_car_id" +
						"  JOIN xs_sell_instorehouse sell ON sell.instorehouse_id = instorehouse.instorehouse_id" +
						"  JOIN xs_sell_allocatel_detail de ON  de.details_id = instorehouse.details_id" +
						"  JOIN xs_sell_allocatel allo ON allo.allocate_id = de.allocate_id " +
						"WHERE 1=1 " 
						/*+"and instorehouse.xs_sell_allocatel_type = (SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.INSTORETYPE+"' AND child.dataKey='"+Contstants.BASE_SELL.INSTORETYPE1+"'" +
						")*/
						+" and allo.allocate_id = '"+ carBarnInfoVo.getAllocateId()+ "'");
	

		//企业编号
		if (carBarnInfoVo.getEnterprise_id() != null
				&& !"".equals(carBarnInfoVo.getEnterprise_id())) {
			sql.append(" and CI.Enterprise_id=" + carBarnInfoVo.getEnterprise_id()
					+ "");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarBarnInfoVo wopq = new CarBarnInfoVo();
				obj = resultList.get(i);
				wopq.setCarVinNumber(obj[0] != null ? obj[0].toString() : null);
				wopq.setCarModel(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				wopq.setCarModelName(obj[2] != null ? obj[2].toString() : null);
				wopq.setCarOcn(obj[3] != null ? obj[3].toString() : null);
				wopq.setCarSellStateName(obj[4] != null ? obj[4].toString()
						: null);
				wopq.setCarDistributStateName(obj[5] != null ? obj[5]
						.toString() : null);
				wopq.setCarProductionAddress(obj[6] != null ? obj[6].toString()
						: null);
				wopq.setDistributorName(obj[7] != null ? obj[7].toString()
						: null);
				wopq.setCarCopyData(obj[8] != null ? FormatTime
						.timestamp2Str((Timestamp) obj[8]) : null);
				wopq.setCarCode(obj[9] != null ? obj[9].toString() : null);
				wopq.setCarMotorNumber(obj[10] != null ? obj[10].toString()
						: null);
				wopq.setCarLicenseName(obj[11] != null ? obj[11].toString()
						: null);
				wopq.setCarId(obj[12] != null ? Integer.parseInt(obj[12]
						.toString()) : null);
				wopq.setCarBrandName(obj[13] != null ? obj[13].toString()
						: null);
				wopq.setCarColorName(obj[14] != null ? obj[14].toString()
						: null);
				wopq.setCarBrand(obj[15] != null ? Integer.parseInt(obj[15]
						.toString()) : null);
				wopq.setCarColor(obj[16] != null ? Integer.parseInt(obj[16]
						.toString()) : null);
				wopq.setModelCostPrice(obj[17] != null ? Double
						.parseDouble(obj[17].toString()) : null);
				wopq.setModelSalesPrice(obj[18] != null ? Double
						.parseDouble(obj[18].toString()) : null);
				wopq.setCarInstorageAge(obj[19] != null ? Double
						.parseDouble(obj[19].toString()) : null);
				wopq.setNum(obj[20] != null ? Integer.parseInt(obj[20]
						.toString()) : null);
				wopq.setHouse(obj[21] != null ? obj[21].toString() : null);
				wopq.setHouseid(obj[22] != null ? Integer.parseInt(obj[22]
						.toString()) : null);
				wopq.setDetailsId(obj[23] != null ? Integer.parseInt(obj[23]
						.toString()) : null);
				wopq.setVehicleCost(obj[24] != null ? Double
						.parseDouble(obj[24].toString()) : null);
				wopq.setAllAmount(obj[25] != null ? Double
						.parseDouble(obj[25].toString()) : null);
				list_.add(wopq);
			}
		}
		return list_;
	}
	/**
	 *  根据调退单汇总信息编号查询明细
	 */
	public List<CarBarnInfoVo> findBack(CarBarnInfoVo carBarnInfoVo)
	throws Exception {
		List<CarBarnInfoVo> list_ = new ArrayList<CarBarnInfoVo>();
		StringBuffer sql = new StringBuffer(
		"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_ocn,"
				+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_sell_state) AS Ss ,"
				+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_distribut_state) AS Ds ,"
				+ "CI.xs_car_production_address,distributor.xs_distributor_name,CI.xs_car_copy_data,"
				+ "CI.xs_car_code,CI.xs_car_motor_number,CI.xs_car_licenseName,CI.xs_car_id,"
				+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
				+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,CI.xs_car_brand,CI.xs_car_color,   "
				+ " model.xs_model_costPrice, model.xs_model_salesPrice, instorehouse.xs_car_instorage_age,"
				+ " sell.number_,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =sell.warehouse) AS house "
				+ " ,sell.warehouse,instorehouse.details_id,instorehouse.vehicle_cost,de.allocate_amount," +
						" back.allocatel_detail_id ,CI.xs_car_ocn  " +
				" FROM xs_car_info CI " +
				" JOIN  xs_car_model model ON " +
				"  CI.xs_car_model_id=model.xs_model_id" +
				"  left JOIN xs_distributor_info distributor ON  CI.xs_distributor_id=distributor.xs_distributor_id " +
				"  JOIN xs_sell_instorehouse_del instorehouse ON CI.xs_car_id= instorehouse.xs_car_id " +
				"  JOIN xs_sell_instorehouse sell  ON sell.instorehouse_id=instorehouse.instorehouse_id " +
				"  JOIN xs_sell_allocatel_detail de ON de.details_id=instorehouse.details_id" +
				"  JOIN  xs_sell_allocatel_back back ON de.allocatel_detail_id=back.allocatel_detail_id" +
				"  JOIN xs_sell_back ba ON back.back_id=ba.back_id " +
				"WHERE  ba.back_id='"+ carBarnInfoVo.getBackId()+ "'");

		//企业编号
		if (carBarnInfoVo.getEnterprise_id() != null
				&& !"".equals(carBarnInfoVo.getEnterprise_id())) {
			sql.append(" and CI.Enterprise_id=" + carBarnInfoVo.getEnterprise_id()
					+ "");
		}
	List<Object[]> resultList = createSQLQuery(sql.toString());
	if (resultList != null && resultList.size() > 0) {
	Object[] obj = null;
	for (int i = 0; i < resultList.size(); i++) {
		CarBarnInfoVo wopq = new CarBarnInfoVo();
		obj = resultList.get(i);
		wopq.setCarVinNumber(obj[0] != null ? obj[0].toString() : null);
		wopq.setCarModel(obj[1] != null ? Integer.parseInt(obj[1]
				.toString()) : null);
		wopq.setCarModelName(obj[2] != null ? obj[2].toString() : null);
		wopq.setCarOcn(obj[3] != null ? obj[3].toString() : null);
		wopq.setCarSellStateName(obj[4] != null ? obj[4].toString()
				: null);
		wopq.setCarDistributStateName(obj[5] != null ? obj[5]
				.toString() : null);
		wopq.setCarProductionAddress(obj[6] != null ? obj[6].toString()
				: null);
		wopq.setDistributorName(obj[7] != null ? obj[7].toString()
				: null);
		wopq.setCarCopyData(obj[8] != null ? FormatTime
				.timestamp2Str((Timestamp) obj[8]) : null);
		wopq.setCarCode(obj[9] != null ? obj[9].toString() : null);
		wopq.setCarMotorNumber(obj[10] != null ? obj[10].toString()
				: null);
		wopq.setCarLicenseName(obj[11] != null ? obj[11].toString()
				: null);
		wopq.setCarId(obj[12] != null ? Integer.parseInt(obj[12]
				.toString()) : null);
		wopq.setCarBrandName(obj[13] != null ? obj[13].toString()
				: null);
		wopq.setCarColorName(obj[14] != null ? obj[14].toString()
				: null);
		wopq.setCarBrand(obj[15] != null ? Integer.parseInt(obj[15]
				.toString()) : null);
		wopq.setCarColor(obj[16] != null ? Integer.parseInt(obj[16]
				.toString()) : null);
		wopq.setModelCostPrice(obj[17] != null ? Double
				.parseDouble(obj[17].toString()) : null);
		wopq.setModelSalesPrice(obj[18] != null ? Double
				.parseDouble(obj[18].toString()) : null);
		wopq.setCarInstorageAge(obj[19] != null ? Double
				.parseDouble(obj[19].toString()) : null);
		wopq.setNum(obj[20] != null ? Integer.parseInt(obj[20]
				.toString()) : null);
		wopq.setHouse(obj[21] != null ? obj[21].toString() : null);
		wopq.setHouseid(obj[22] != null ? Integer.parseInt(obj[22]
				.toString()) : null);
		wopq.setDetailsId(obj[23] != null ? Integer.parseInt(obj[23]
				.toString()) : null);
		wopq.setVehicleCost(obj[24] != null ? Double
				.parseDouble(obj[24].toString()) : null);
		wopq.setAllAmount(obj[25] != null ? Double
				.parseDouble(obj[25].toString()) : null);
		wopq.setAllocatel_detail_id(obj[26] != null ? Integer.parseInt(obj[26]
		                                         				.toString()) : null);
		wopq.setCarOcn(obj[27] != null ? obj[27].toString() : null);
		list_.add(wopq);
	}
}
return list_;
}

}
