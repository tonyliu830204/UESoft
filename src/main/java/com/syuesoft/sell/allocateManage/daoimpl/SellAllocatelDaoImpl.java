package com.syuesoft.sell.allocateManage.daoimpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.dao.SellAllocatelDao;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.model.XsSellAllocatel;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.FormatTime;

@Repository("sellAllocatelDao")
public class SellAllocatelDaoImpl extends BaseDaoImpl<BaseBean> implements
		SellAllocatelDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	// 调拨单查询
	
	public Datagrid querySellAllocatel(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT A.allocate_id, A.allocate_code, A.allocate_date,"
						+ "A.xs_distributor_id,B.xs_distributor_name,A.allocate_type,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.allocate_type) AS TYPE,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.payment_state)  AS state, "
						+ "A.allocate_person, A.consignee,A.costprice, A.allocate_amount, "
						+ "A.payment_state,A.remark, C.details_id,D.xs_car_id,"
						+ "E.xs_car_vin_number,D.xs_sell_allocatel_type ,"
						+ "a.warehouse,g.STF_NAME,a.examine, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.examine)  AS exa,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.warehouse)  AS house,"
						+ "ba.back_code,(SELECT  COUNT(*) FROM  xs_sell_allocatel_detail  del WHERE del.allocate_id=  A.allocate_id)  "
						+ ",a.enterprise_id " +
						" FROM xs_sell_allocatel A "
						+ " JOIN xs_distributor_info B ON B.xs_distributor_id=a.xs_distributor_id "
						+ " JOIN  xs_sell_allocatel_detail C ON C.allocate_id=a.allocate_id "
						+ " JOIN  xs_sell_instorehouse_del D ON d.details_id=c.details_id "
						+ " JOIN xs_car_info E ON E.xs_car_id= d.xs_car_id "
						+ " JOIN  bas_stuff g ON g.STF_ID=a.allocate_person"
						+ " LEFT OUTER JOIN xs_sell_allocatel_back back on back.allocatel_detail_id=c.allocatel_detail_id"
						+ " LEFT OUTER JOIN xs_sell_back ba on ba.back_id=back.back_id  "
						+ "WHERE a.enterprise_id="+sellAllocatelVo.getEnterprise_id());// 287=调拨

		if (sellAllocatelVo.getAllocateId() != null
				&& !"".equals(sellAllocatelVo.getAllocateId())) {
			sql.append(" and A.allocate_id = '"
					+ sellAllocatelVo.getAllocateId() + "'");
		}
		
		if (sellAllocatelVo.getAllocateDate() != null
					&& !"".equals(sellAllocatelVo.getAllocateDate())) {
			sql.append(" and A.allocate_date>='"+sellAllocatelVo.getAllocateDate()+"'");
			}
		if (sellAllocatelVo.getAllocateDate2() != null
					&& !"".equals(sellAllocatelVo.getAllocateDate2())) {
			sql.append(" and A.allocate_date<='"+sellAllocatelVo.getAllocateDate2()+"'");
			
		}
		if((sellAllocatelVo.getAllocateDate() == null
				||"".equals(sellAllocatelVo.getAllocateDate()))&&(sellAllocatelVo.getAllocateDate2() == null
						||"".equals(sellAllocatelVo.getAllocateDate2()))){
			sql.append(" and A.allocate_date BETWEEN" +
					" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellAllocatelVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
		if (sellAllocatelVo.getXsDistributorId() != null
				&& !"".equals(sellAllocatelVo.getXsDistributorId())) {
			sql.append(" and A.xs_distributor_id='"
					+ sellAllocatelVo.getXsDistributorId() + "'");
		}
		if (sellAllocatelVo.getXsCarVinNum() != null
				&& !"".equals(sellAllocatelVo.getXsCarVinNum())) {
			sql.append(" and E.xs_car_vin_number like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getXsCarVinNum().trim()) + "%'");
		}
		if (sellAllocatelVo.getAllocatecode() != null
				&& !"".equals(sellAllocatelVo.getAllocatecode())) {
			sql.append(" and A.allocate_code like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getAllocatecode().trim()) + "%'");
		}

		if (sellAllocatelVo.getPayment() != null
				&& !("".equals(sellAllocatelVo.getPayment()))
				&& "false".equals(sellAllocatelVo.getPayment())) {
			sql
					.append(" and A.payment_state=(SELECT child.child_id FROM xs_childdictionary  child, "
							+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
							+ "AND parent.dataKey='"
							+ Contstants.BASE_SELL.PAYMENTSTATE
							+ "' AND "
							+ "child.dataKey='"
							+ Contstants.BASE_SELL.PAYMENTSTATE2 + "'" + ") ");
		}
		sql.append(" GROUP BY a.allocate_code order by a.allocate_code desc");
		List resultList = createSQLQuery(sql.toString(),
				sellAllocatelVo.getPage(), sellAllocatelVo.getRows());
		List<SellAllocatelVo> list = new ArrayList<SellAllocatelVo>();
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellAllocatelVo sell = new SellAllocatelVo();
				obj = (Object[])resultList.get(i);
				sell.setAllocateId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setAllocatecode(obj[1] != null ? obj[1].toString() : null);
				sell.setAllocateDate(obj[2] != null ? (fmt.format(obj[2]))
						: null);
				sell.setXsDistributorId(obj[3] != null ? Integer
						.parseInt(obj[3].toString()) : null);
				sell.setXsDistributorName(obj[4] != null ? obj[4].toString()
						: null);
				sell.setAllocateType(obj[5] != null ? Integer.parseInt(obj[5]
						.toString()) : null);
				sell.setAllocateTypeName(obj[6] != null ? obj[6].toString()
						: null);
				sell.setPaymentStateName(obj[7] != null ? obj[7].toString()
						: null);
				sell.setAllocatePerson(obj[8] != null ? Integer.parseInt(obj[8]
						.toString()) : null);
				sell.setConsignee(obj[9] != null ? obj[9].toString() : null);
				sell.setCostprice(obj[10] != null ? Double.parseDouble(obj[10]
						.toString()) : null);
				sell.setAllocateAmount(obj[11] != null ? Double
						.parseDouble(obj[11].toString()) : null);
				sell.setPaymentState(obj[12] != null ? Integer.parseInt(obj[12]
						.toString()) : null);
				sell.setRemark(obj[13] != null ? obj[13].toString() : null);
				sell.setDetailsId(obj[14] != null ? Integer.parseInt(obj[14]
						.toString()) : null);
				sell.setXsCarId(obj[15] != null ? Integer.parseInt(obj[15]
						.toString()) : null);
				sell
						.setXsCarVinNum(obj[16] != null ? obj[16].toString()
								: null);
				sell.setXs_sellAllocatelType(obj[17] != null ? Integer
						.parseInt(obj[17].toString()) : null);
				sell.setWarehouse(obj[18] != null ? Integer.parseInt(obj[18]
						.toString()) : null);

				sell.setAllocatePersonName(obj[19] != null ? obj[19].toString()
						: null);
				sell.setExamine(obj[20] != null ? Integer.parseInt(obj[20]
						.toString()) : null);
				sell
						.setExamineName(obj[21] != null ? obj[21].toString()
								: null);
				sell.setHouse(obj[22] != null ? obj[22].toString() : null);
				sell.setBackCode(obj[23] != null ? obj[23].toString() : null);
				sell.setNum(obj[24] != null ? Integer.parseInt(obj[24]
						.toString()) : null);
				sell.setEnterprise_id(obj[25] != null ? Integer.parseInt(obj[25]
				                           						.toString()) : null);

				list.add(sell);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	// 根据id查明细
	
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception {
		return (XsSellInstorehouseDel) this
				.get("from XsSellInstorehouseDel del where 1=1 and  detailsId="
						+ delId);
	}

	
	@SuppressWarnings("unchecked")
	public List getInfor(final String sql) {
		List list = this.getHibernateTemplate().execute(
				new HibernateCallback<List>() {

					
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql).list();
					}

				});
		return list;
	}

	// 根据id查汇总
	public XsSellAllocatel findById(Integer instoreId) throws Exception {
		return (XsSellAllocatel) this
				.get("from XsSellAllocatel tel where tel.allocateId="
						+ instoreId);

	}

	
	public List<SellAllocatelVo> queryAllocatelList(
			SellAllocatelVo sellAllocatelVo) throws Exception {
		List<SellAllocatelVo> list = new ArrayList<SellAllocatelVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT  CI.xs_car_id,CI.xs_car_vin_number, "
						+ "CI.xs_car_model_id, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,"
						+ "CI.xs_car_brand,CI.xs_car_color,"
						+ "model.xs_model_name,CI.xs_car_code,CI.xs_car_licenseName "
						+ ",distributor.xs_distributor_name,distributor.xs_distributor_id,"
						+ "model.xs_model_costPrice ,instorehouse.details_id ,"
						+ "i.number_, s.costprice,s.allocate_amount,s.allocate_type ,"
						+ "s.allocate_date,s.payment_state "
						+ "FROM xs_car_info CI LEFT OUTER JOIN xs_car_model model ON CI.xs_car_model_id=model.xs_model_id "
						+ " JOIN xs_sell_instorehouse_del instorehouse ON CI.xs_car_id= instorehouse.xs_car_id "
						+ " JOIN xs_sell_instorehouse I  ON instorehouse.instorehouse_id=I.instorehouse_id "
						+ " JOIN xs_sell_allocatel_detail H ON instorehouse.details_id=H.details_id "
						+ " JOIN xs_sell_allocatel S ON h.allocate_id=s.allocate_id "
						+ " JOIN xs_distributor_info distributor ON S.xs_distributor_id=distributor.xs_distributor_id ");

		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellAllocatelVo sell = new SellAllocatelVo();
				obj = resultList.get(i);
				sell.setCarId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setCarVinNumber(obj[1] != null ? obj[1].toString() : null);
				sell.setCarModel(obj[2] != null ? Integer.parseInt(obj[2]
						.toString()) : null);
				sell.setCarBrandName(obj[3] != null ? obj[3].toString() : null);
				sell.setCarColorName(obj[4] != null ? (obj[4].toString())
						: null);
				sell.setCarBrand(obj[5] != null ? Integer.parseInt(obj[5]
						.toString()) : null);
				sell.setCarColorName(obj[6] != null ? obj[6].toString() : null);
				sell.setCarModelName(obj[7] != null ? (obj[7].toString())
						: null);
				sell.setCarCode(obj[8] != null ? obj[8].toString() : null);
				sell.setCarLicenseName(obj[9] != null ? obj[9].toString()
						: null);
				sell.setXsDistributorName(obj[10] != null ? obj[10].toString()
						: null);
				sell.setXsDistributorId(obj[11] != null ? Integer
						.parseInt(obj[11].toString()) : null);
				sell.setModelCostPrice(obj[12] != null ? Double
						.parseDouble(obj[12].toString()) : null);
				sell.setDetailsId(obj[13] != null ? Integer.parseInt(obj[13]
						.toString()) : null);
				sell.setNum(obj[14] != null ? Integer.parseInt(obj[14]
						.toString()) : null);
				sell.setCostprice(obj[15] != null ? Double.parseDouble(obj[15]
						.toString()) : null);
				sell.setAllocateAmount(obj[16] != null ? Double
						.parseDouble(obj[16].toString()) : null);
				sell.setAllocateType(obj[17] != null ? Integer.parseInt(obj[17]
						.toString()) : null);
				sell.setAllocateDate(obj[18] != null ? (obj[18]).toString()
						: null);
				sell.setPaymentState(obj[19] != null ? Integer.parseInt(obj[19]
						.toString()) : null);

				list.add(sell);

			}

		}
		return list;
	}

	/**
	 * 调拨单查询模块，查询子菜单信息功能
	 */
	public List<SellAllocatelVo> findAllocatel(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		List<SellAllocatelVo> list_ = new ArrayList<SellAllocatelVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_ocn,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_sell_state) AS Ss ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_distribut_state) AS Ds ,"
						+ "CI.xs_car_production_address,distributor.xs_distributor_name,CI.xs_car_copy_data,"
						+ "CI.xs_car_code,CI.xs_car_motor_number,CI.xs_car_licenseName,CI.xs_car_id,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,CI.xs_car_brand,CI.xs_car_color,   "
						+ "instorehouse.vehicle_cost, model.xs_model_salesPrice, instorehouse.xs_car_instorage_age,"
						+ "  instorehouse.details_id,de.allocate_amount ,"
						+ "CI.xs_car_licenseName " +
						" FROM xs_car_info CI " +
						" JOIN  xs_car_model model ON CI.xs_car_model_id = model.xs_model_id "
						+ " left JOIN xs_distributor_info distributor  ON CI.xs_distributor_id = distributor.xs_distributor_id"
						+ "  JOIN xs_sell_instorehouse_del instorehouse ON  CI.xs_car_id = instorehouse.xs_car_id "
						+ "  JOIN xs_sell_allocatel_detail de ON  de.details_id = instorehouse.details_id"
						+ "  JOIN xs_sell_allocatel allo ON allo.allocate_id = de.allocate_id "
						+ " WHERE 1=1"
						/*
						 * +
						 * " instorehouse.xs_sell_allocatel_type = (SELECT child.child_id FROM xs_childdictionary  child, "
						 * +
						 * "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
						 * + "AND parent.dataKey='" +
						 * Contstants.BASE_SELL.INSTORETYPE +
						 * "' AND child.dataKey='" +
						 * Contstants.BASE_SELL.INSTORETYPE1 + "'" + ")
						 */
						+ " and  allo.allocate_id = '"
						+ sellAllocatelVo.getAllocateId() + "'");// 调拨

		if (sellAllocatelVo.getAllocateDate() != null
				&& !"".equals(sellAllocatelVo.getAllocateDate())) {
			
					sql.append(" and allo.allocate_date >= '" + sellAllocatelVo.getAllocateDate() + "'");
				}
		if (sellAllocatelVo.getAllocateDate2() != null
				&& !"".equals(sellAllocatelVo.getAllocateDate2())) {
			
					sql.append(" and allo.allocate_date <= '" + sellAllocatelVo.getAllocateDate2() + "'");
		
		}
		if((sellAllocatelVo.getAllocateDate() == null
				||"".equals(sellAllocatelVo.getAllocateDate()))&&(sellAllocatelVo.getAllocateDate2() == null
				||"".equals(sellAllocatelVo.getAllocateDate2()))){
		sql.append(" and allo.allocate_date BETWEEN" +
				" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellAllocatelVo.getEnterprise_id()).getCiValue()))+ "'" +
				" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
		
	if (sellAllocatelVo.getXsDistributorId() != null
			&& !"".equals(sellAllocatelVo.getXsDistributorId())) {
		sql.append(" and  allo.xs_distributor_id='"
				+ sellAllocatelVo.getXsDistributorId() + "'");
	}
	if (sellAllocatelVo.getAllocatecode() != null
			&& !"".equals(sellAllocatelVo.getAllocatecode())) {
		sql.append(" and  allo.allocate_code like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getAllocatecode().trim()) + "%'");
	}
	if (sellAllocatelVo.getAllocateType() != null
			&& !"".equals(sellAllocatelVo.getAllocateType())) {
		sql.append(" and  allo.allocate_type='"
				+ sellAllocatelVo.getAllocateType() + "'");
	}
	if (sellAllocatelVo.getWarehouse() != null
			&& !"".equals(sellAllocatelVo.getWarehouse())) {
		sql.append(" and  allo.warehouse='" + sellAllocatelVo.getWarehouse()
				+ "'");
	}
	if (sellAllocatelVo.getPaymentState() != null
			&& !"".equals(sellAllocatelVo.getPaymentState())) {
		sql.append(" and allo.payment_state='"
				+ sellAllocatelVo.getPaymentState() + "'");
	}
	if (sellAllocatelVo.getCarVinNumber() != null
			&& !"".equals(sellAllocatelVo.getCarVinNumber())) {
		sql.append(" and  ci.xs_car_vin_number like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getCarVinNumber().trim()) + "%'");
	}
	if (sellAllocatelVo.getCarCode() != null
			&& !"".equals(sellAllocatelVo.getCarCode())) {
		sql.append(" and ci.xs_car_code like '%"	+ StringEscapeUtils.escapeSql(sellAllocatelVo.getCarCode().trim()) + "%'");
	}
	if (sellAllocatelVo.getCarModel() != null
			&& !"".equals(sellAllocatelVo.getCarModel())) {
		sql.append(" and ci.xs_car_model_id='"
				+ sellAllocatelVo.getCarModel() + "'");
	}
	if (sellAllocatelVo.getCarBrand() != null
			&& !"".equals(sellAllocatelVo.getCarBrand())) {
		sql.append(" and ci.xs_car_brand='"
				+ sellAllocatelVo.getCarBrand() + "'");
	}
		
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellAllocatelVo wopq = new SellAllocatelVo();
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
				wopq.setAllocatecode(obj[20] != null ? obj[20].toString()
						: null);
				
				wopq.setAllAmount(obj[21] != null ? Double.parseDouble(obj[21]
						.toString()) : null);
				wopq.setCarLicenseName(obj[22] != null ? obj[22].toString()
						: null);
				
				wopq.setIconCls("icon-blank");
				wopq.setState("open");
				list_.add(wopq);
			}
		}
		return list_;
	}

	/**
	 * 调拨单查询模块，查询父菜单信息功能
	 */
	
	public DatagridAnalyze queryFather(SellAllocatelVo sellAllocatelVo)
			throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		List<SellAllocatelVo> list = new ArrayList<SellAllocatelVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT    COUNT(*) as  _sum,"
						+ "  A.allocate_id, A.allocate_code, A.allocate_date,"
						+ " sum(c.allocate_amount), sum(d.vehicle_cost), a.xs_distributor_id, b.xs_distributor_name,"
						+ "a.allocate_type,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.allocate_type) AS allo,"
						+ "a.warehouse,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.warehouse) AS house ,"
						+ "a.payment_state,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.payment_state) AS payment "
						+ " FROM xs_sell_allocatel A " +
						" JOIN xs_distributor_info B ON a.xs_distributor_id = b.xs_distributor_id "
						+ " JOIN  xs_sell_allocatel_detail c ON a.allocate_id = c.allocate_id"
						+ "	JOIN  xs_sell_instorehouse_del d ON c.details_id = d.details_id "
						+ "  JOIN  xs_car_info H ON h.xs_car_id=d.xs_car_id "
						+ "WHERE a.enterprise_id="+sellAllocatelVo.getEnterprise_id());
		
			if (sellAllocatelVo.getAllocateDate() != null
					&& !"".equals(sellAllocatelVo.getAllocateDate())) {
				
						sql.append(" and A.allocate_date >= '" + sellAllocatelVo.getAllocateDate() + "'");
					}
			if (sellAllocatelVo.getAllocateDate2() != null
					&& !"".equals(sellAllocatelVo.getAllocateDate2())) {
				
						sql.append(" and A.allocate_date <= '" + sellAllocatelVo.getAllocateDate2() + "'");
				
			}
			if((sellAllocatelVo.getAllocateDate() == null
					||"".equals(sellAllocatelVo.getAllocateDate()))&&(sellAllocatelVo.getAllocateDate2() == null
					||"".equals(sellAllocatelVo.getAllocateDate2()))){
			sql.append(" and A.allocate_date BETWEEN" +
					" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellAllocatelVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
	
		if (sellAllocatelVo.getXsDistributorId() != null
				&& !"".equals(sellAllocatelVo.getXsDistributorId())) {
			sql.append(" and  a.xs_distributor_id='"
					+ sellAllocatelVo.getXsDistributorId() + "'");
		}
		if (sellAllocatelVo.getAllocatecode() != null
				&& !"".equals(sellAllocatelVo.getAllocatecode())) {
			sql.append(" and  A.allocate_code like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getAllocatecode().trim()) + "%'");
		}
		if (sellAllocatelVo.getAllocateType() != null
				&& !"".equals(sellAllocatelVo.getAllocateType())) {
			sql.append(" and  a.allocate_type='"
					+ sellAllocatelVo.getAllocateType() + "'");
		}
		if (sellAllocatelVo.getWarehouse() != null
				&& !"".equals(sellAllocatelVo.getWarehouse())) {
			sql.append(" and  a.warehouse='" + sellAllocatelVo.getWarehouse()
					+ "'");
		}
		if (sellAllocatelVo.getPaymentState() != null
				&& !"".equals(sellAllocatelVo.getPaymentState())) {
			sql.append(" and a.payment_state='"
					+ sellAllocatelVo.getPaymentState() + "'");
		}
		if (sellAllocatelVo.getCarVinNumber() != null
				&& !"".equals(sellAllocatelVo.getCarVinNumber())) {
			sql.append(" and  h.xs_car_vin_number like '%"+ StringEscapeUtils.escapeSql(sellAllocatelVo.getCarVinNumber().trim()) + "%'");
		}
		if (sellAllocatelVo.getCarCode() != null
				&& !"".equals(sellAllocatelVo.getCarCode())) {
			sql.append(" and h.xs_car_code like '%"	+ StringEscapeUtils.escapeSql(sellAllocatelVo.getCarCode().trim()) + "%'");
		}
		if (sellAllocatelVo.getCarModel() != null
				&& !"".equals(sellAllocatelVo.getCarModel())) {
			sql.append(" and h.xs_car_model_id='"
					+ sellAllocatelVo.getCarModel() + "'");
		}
		if (sellAllocatelVo.getCarBrand() != null
				&& !"".equals(sellAllocatelVo.getCarBrand())) {
			sql.append(" and h.xs_car_brand='"
					+ sellAllocatelVo.getCarBrand() + "'");
		}

		sql.append(" GROUP BY a.allocate_code order by a.allocate_code desc");
		List<Object[]> resultList = createSQLQuery(sql.toString(),
				sellAllocatelVo.getPage(), sellAllocatelVo.getRows());
		List<SellAllocatelVo> foots= new ArrayList<SellAllocatelVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellAllocatelVo sell = null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellAllocatelVo();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				obj = resultList.get(i);
				sell.setNum(obj[0] != null ? Integer
						.parseInt(obj[0].toString()) : null);
				sell.setAllocateId(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setAllocatecode(obj[2] != null ? obj[2].toString() : null);
				sell.setAllocateDate(obj[3] != null ? (fmt.format(obj[3]))
						: null);
				sell.setAllAmount(obj[4] != null ? Double.parseDouble(obj[4]
						.toString()) : null);
				sell.setModelCostPrice(obj[5] != null ? Double
						.parseDouble(obj[5].toString()) : null);
				sell.setXsDistributorId(obj[6] != null ? Integer
						.parseInt(obj[6].toString()) : null);
				sell.setXsDistributorName(obj[7] != null ? obj[7].toString()
						: null);
				sell.setAllocateType(obj[8] != null ? Integer.parseInt(obj[8]
						.toString()) : null);
				sell.setAllocateTypeName(obj[9] != null ? obj[9].toString()
						: null);
				sell.setWarehouse(obj[10] != null ? Integer.parseInt(obj[10]
						.toString()) : null);
				sell.setHouse(obj[11] != null ? obj[11].toString() : null);
				sell.setPaymentState(obj[12] != null ? Integer.parseInt(obj[12]
						.toString()) : null);
				sell.setPaymentStateName(obj[13] != null ? obj[13].toString()
						: null);

				sell.setState("closed");
				sell.setIconCls("icon-blank");

				list.add(sell);
			}
			// 汇总
			sell = new SellAllocatelVo();
			sell.setAllocatecode("汇总");
			sell.setState("open");
			sell.setIconCls("icon-blank");
			int count = 0;
			Double modelPrice = 0.0;
			Double amount = 0.0;
			for (SellAllocatelVo sellAllocatelVo2 : list) {
				if (sellAllocatelVo2.getNum() != null
						&& !"".equals(sellAllocatelVo2.getNum())) {
					count += (sellAllocatelVo2.getNum());
				}

				if (sellAllocatelVo2.getModelCostPrice() != null
						&& !"".equals(sellAllocatelVo2.getModelCostPrice())) {
					modelPrice += sellAllocatelVo2.getModelCostPrice();
				}

				if (sellAllocatelVo2.getAllAmount() != null
						&& !"".equals(sellAllocatelVo2.getAllAmount())) {
					amount += sellAllocatelVo2.getAllAmount();
				}

			}

			sell.setNum(count);
			sell.setModelCostPrice(modelPrice);
			sell.setAllAmount(amount);
			foots.add(sell);
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setFooter(foots);
		dg.setTotal(total);
		return dg;
	}
}
