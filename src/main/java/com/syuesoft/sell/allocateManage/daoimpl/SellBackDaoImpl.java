package com.syuesoft.sell.allocateManage.daoimpl;

import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.syuesoft.sell.allocateManage.dao.SellBackDao;
import com.syuesoft.sell.allocateManage.vo.SellBackVo;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.FormatTime;

@Repository("sellBackDao")
public class SellBackDaoImpl extends BaseDaoImpl<Object> implements SellBackDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

	/**
	 * 调退单信息查询
	 */
	
	public Datagrid getSellBackInfo(SellBackVo sellBackVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellBackVo> list = new ArrayList<SellBackVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT A.back_id, A.back_code, A.back_date, "
						+ "A.xs_distributor_id, b.xs_distributor_name, A.back_type, A.back_person, "
						+ "A.handback_allocate_amount, A.balance_state,A.remark ,"
						+ "c.allocate_id,f.number_,g.STF_NAME,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.back_type) AS TYPE,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.balance_state) AS state,"
						+ "a.examine,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.examine) AS exa  "
						+ ",A.enterprise_id "
						+ " FROM xs_sell_back A,xs_distributor_info B, "
						+ "xs_sell_allocatel_detail C,xs_sell_allocatel_back D,"
						+ "xs_sell_instorehouse_del E,xs_sell_instorehouse F,bas_stuff G"
						+ " WHERE A.xs_distributor_id=B.xs_distributor_id "
						+ " AND C.allocatel_detail_id=D.allocatel_detail_id "
						+ "AND e.details_id=c.details_id AND "
						+ "F.instorehouse_id=E.instorehouse_id  "
						+ "AND a.back_person=g.STF_ID AND A.back_id=d.back_id "
						+ " and A.enterprise_id="
						+ sellBackVo.getEnterprise_id());

		
		if (sellBackVo.getBackDate() != null
					&& !"".equals(sellBackVo.getBackDate())) {
				sql.append(" and A.back_date>='" + sellBackVo.getBackDate()
						+ "'");
			}
		if (sellBackVo.getBackDate2() != null
					&& !"".equals(sellBackVo.getBackDate2())) {
				sql.append(" and A.back_date<='" + sellBackVo.getBackDate2()
						+ "'");
			
		}
		if((sellBackVo.getBackDate() == null
				||"".equals(sellBackVo.getBackDate()))&&(sellBackVo.getBackDate2() == null
				|| "".equals(sellBackVo.getBackDate2()))) {
			sql
					.append(" and A.back_date between '"
							+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,
								(basCompanyInformationSetDAO.getBasCompanyInformationSet(
								PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,
								sellBackVo.getEnterprise_id()).getCiValue()))
							+ "'" + "and '"
							+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)
							+ "'");
		}
		if (sellBackVo.getXsDistributorId() != null
				&& !"".equals(sellBackVo.getXsDistributorId())) {
			sql.append(" and A.xs_distributor_id='"
					+ sellBackVo.getXsDistributorId() + "'");
		}
		if (sellBackVo.getBackCode() != null
				&& !"".equals(sellBackVo.getBackCode())) {
			sql.append(" and A.back_code like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getBackCode()
							.trim()) + "%'");
		}
		sql.append("  GROUP BY A.back_code ORDER BY A.back_code desc");
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellBackVo
				.getPage(), sellBackVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellBackVo sellBackVo1 = new SellBackVo();
				obj = resultList.get(i);
				sellBackVo1.setBackId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sellBackVo1.setBackCode(obj[1] != null ? obj[1].toString()
						: null);
				sellBackVo1.setBackDate(obj[2] != null ? ((Timestamp) obj[2])
						: null);
				sellBackVo1.setXsDistributorId(obj[3] != null ? Integer
						.parseInt(obj[3].toString()) : null);
				sellBackVo1.setXsDistributorName(obj[4] != null ? obj[4]
						.toString() : null);
				sellBackVo1.setBackType(obj[5] != null ? Integer
						.parseInt(obj[5].toString()) : null);
				sellBackVo1.setBackPerson(obj[6] != null ? Integer
						.parseInt(obj[6].toString()) : null);
				sellBackVo1.setHandbackAllocateAmount(obj[7] != null ? Double
						.parseDouble(obj[7].toString()) : null);
				sellBackVo1.setBalanceState(obj[8] != null ? Integer
						.parseInt(obj[8].toString()) : null);
				sellBackVo1
						.setRemark(obj[9] != null ? obj[9].toString() : null);
				sellBackVo1.setAllocateId(obj[10] != null ? Integer
						.parseInt(obj[10].toString()) : null);
				sellBackVo1.setNumber(obj[11] != null ? Integer
						.parseInt(obj[11].toString()) : null);
				sellBackVo1.setBackPersonName(obj[12] != null ? obj[12]
						.toString() : null);

				sellBackVo1.setBackTypeName(obj[13] != null ? obj[13]
						.toString() : null);
				sellBackVo1.setBalanceStateN(obj[14] != null ? obj[14]
						.toString() : null);

				sellBackVo1.setExamine(obj[15] != null ? Integer
						.parseInt(obj[15].toString()) : null);
				sellBackVo1.setExamineName(obj[16] != null ? obj[16].toString()
						: null);
				sellBackVo1.setEnterprise_id(obj[17] != null ? Integer
						.parseInt(obj[17].toString()) : null);
				list.add(sellBackVo1);

			}

		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 根据id查明细
	 */
	
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

	/**
	 * 调退查询模块：调退汇总信息查询
	 */
	
	public DatagridAnalyze getSellBack(SellBackVo sellBackVo) throws Exception {
		DatagridAnalyze dg = new DatagridAnalyze();
		List<SellBackVo> list = new ArrayList<SellBackVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT A.back_id,A.back_code, A.back_date,"
						+ "	A.xs_distributor_id, b.xs_distributor_name,	A.back_type, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.back_type) AS Ss,"
						+ "a.handback_allocate_amount,"
						+ "COUNT(*) as _sum "
						+ "FROM xs_sell_back A  " +
						" JOIN xs_distributor_info B ON a.xs_distributor_id = b.xs_distributor_id "
						+ "   JOIN  xs_sell_allocatel_back C ON c.back_id = a.back_id"
						+ "   JOIN  xs_sell_allocatel_detail D ON c.allocatel_detail_id = d.allocatel_detail_id"
						+ "   JOIN  xs_sell_instorehouse_del e ON d.details_id = e.details_id "
						+ "   JOIN xs_car_info car ON car.xs_car_id=e.xs_car_id "
						+ " where a.enterprise_id="
						+ sellBackVo.getEnterprise_id()
		/*
		 * +
		 * "  WHERE  e.xs_sell_allocatel_type =(SELECT child.child_id FROM xs_childdictionary  child, "
		 * +
		 * "  xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
		 * + "  AND parent.dataKey='" + Contstants.BASE_SELL.INSTORETYPE +
		 * "' AND " + "  child.dataKey='" + Contstants.BASE_SELL.INSTORETYPE2 +
		 * "'" + ")  "
		 */);
		
		
		if (sellBackVo.getBackDate() != null
					&& !"".equals(sellBackVo.getBackDate())) {
				sql.append(" and A.back_date>='" + sellBackVo.getBackDate() + "'");
			}
		if (sellBackVo.getBackDate2() != null
					&& !"".equals(sellBackVo.getBackDate2())) {
				sql.append(" and A.back_date<='" + sellBackVo.getBackDate2() + "'");
			
		}
		if((sellBackVo.getBackDate() == null
				||"".equals(sellBackVo.getBackDate()))&&(sellBackVo.getBackDate2() == null
				||"".equals(sellBackVo.getBackDate2()))) {
			sql	.append(" and A.back_date between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellBackVo.getEnterprise_id()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");

		}

		if (sellBackVo.getXsDistributorId() != null
				&& !"".equals(sellBackVo.getXsDistributorId())) {
			sql.append(" and A.xs_distributor_id='"
					+ sellBackVo.getXsDistributorId() + "'");
		}
		if (sellBackVo.getBackCode() != null
				&& !"".equals(sellBackVo.getBackCode())) {
			sql.append(" and A.back_code like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getBackCode()
							.trim()) + "%'");
		}
		if (sellBackVo.getBackType() != null
				&& !"".equals(sellBackVo.getBackType())) {
			sql.append(" and A.back_type='" + sellBackVo.getBackType() + "'");
		}

		if (sellBackVo.getCarCode() != null
				&& !"".equals(sellBackVo.getCarCode())) {
			sql.append(" and car.xs_car_code like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getCarCode()
							.trim()) + "%'");
		}
		if (sellBackVo.getCarVinNumber() != null
				&& !"".equals(sellBackVo.getCarVinNumber())) {
			sql.append(" and car.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getCarVinNumber()
							.trim()) + "%'");
		}
		if (sellBackVo.getCarModel() != null
				&& !"".equals(sellBackVo.getCarModel())) {
			sql.append(" and car.xs_car_model_id='" + sellBackVo.getCarModel()
					+ "'");
		}

		sql.append("  GROUP BY A.back_code ORDER BY A.back_code desc");
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellBackVo
				.getPage(), sellBackVo.getRows());
		
		List<SellBackVo> footers = new ArrayList<SellBackVo>();
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			SellBackVo sell = null;
			for (int i = 0; i < resultList.size(); i++) {
				sell = new SellBackVo();
				obj = resultList.get(i);
				sell.setBackId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setBackCode(obj[1] != null ? obj[1].toString() : null);
				sell.setBackDate(obj[2] != null ? ((Timestamp) obj[2]) : null);
				sell.setXsDistributorId(obj[3] != null ? Integer
						.parseInt(obj[3].toString()) : null);
				sell.setXsDistributorName(obj[4] != null ? obj[4].toString()
						: null);
				sell.setBackType(obj[5] != null ? Integer.parseInt(obj[5]
						.toString()) : null);
				sell.setBackTypeName(obj[6] != null ? obj[6].toString() : null);
				sell.setHandbackAllocateAmount(obj[7] != null ? Double
						.parseDouble(obj[7].toString()) : null);
				sell.setNum(obj[8] != null ? Integer
						.parseInt(obj[8].toString()) : null);

				sell.setState("closed");
				sell.setIconCls("icon-blank");
				list.add(sell);
			}
			sell = new SellBackVo();
			sell.setBackCode("汇总");
			sell.setState("open");
			sell.setIconCls("icon-blank");
			int count = 0;
			Double amount = 0.0;
			for (SellBackVo SellBackVo2 : list) {
				if (SellBackVo2.getNum() != null
						&& !"".equals(SellBackVo2.getNum())) {
					count += (SellBackVo2.getNum());
				}
				if (SellBackVo2.getHandbackAllocateAmount() != null
						&& !"".equals(SellBackVo2.getHandbackAllocateAmount())) {
					amount += SellBackVo2.getHandbackAllocateAmount();
				}

			}
			sell.setNum(count);
			sell.setHandbackAllocateAmount(amount);
			footers.add(sell);

		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		dg.setFooter(footers);
		return dg;
	}

	/**
	 * 调退查询模块：调退汇总信息查询子菜单
	 */
	public List<SellBackVo> findBack(SellBackVo sellBackVo) throws Exception {
		List<SellBackVo> list_ = new ArrayList<SellBackVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT CI.xs_car_vin_number, CI.xs_car_model_id,model.xs_model_name,CI.xs_car_code,"
						+ "CI.xs_car_licenseName,CI.xs_car_id,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_brand) AS  Cb,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =CI.xs_car_color) AS Cc ,"
						+ "CI.xs_car_brand,CI.xs_car_color,instorehouse.details_id,de.allocate_amount  "
						+ "FROM xs_car_info CI " +
						" JOIN  xs_car_model model ON CI.xs_car_model_id=model.xs_model_id"
						+ "  JOIN xs_sell_instorehouse_del instorehouse ON CI.xs_car_id= instorehouse.xs_car_id "
						+ "  JOIN xs_sell_allocatel_detail de ON de.details_id=instorehouse.details_id"
						+ "  JOIN  xs_sell_allocatel_back back ON de.allocatel_detail_id=back.allocatel_detail_id"
						+ "  JOIN xs_sell_back ba ON back.back_id=ba.back_id "
						+ "WHERE  ba.back_id='" + sellBackVo.getBackId() + "'");
		
			if (sellBackVo.getBackDate() != null
					&& !"".equals(sellBackVo.getBackDate())) {
				sql.append(" and ba.back_date>='" + sellBackVo.getBackDate() + "'");
			}
			if (sellBackVo.getBackDate2() != null
					&& !"".equals(sellBackVo.getBackDate2())) {
				sql.append(" and ba.back_date<='" + sellBackVo.getBackDate2() + "'");
			
			}
			if((sellBackVo.getBackDate() == null
					||"".equals(sellBackVo.getBackDate()))&&(sellBackVo.getBackDate2() == null
							||"".equals(sellBackVo.getBackDate2()))) {
				sql	.append(" and ba.back_date between '"+ FormatTime.yesTempTady(
					FormatTime.DEFAULT_FORMAT2,Integer.parseInt(
							basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,
									Contstants.COLLIGATES.DEFAULTSHOWDAY,sellBackVo.getEnterprise_id()).getCiValue()))
									+ "' and '"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
	
			}

	if (sellBackVo.getXsDistributorId() != null
			&& !"".equals(sellBackVo.getXsDistributorId())) {
		sql.append(" and ba.xs_distributor_id='"
				+ sellBackVo.getXsDistributorId() + "'");
	}
	if (sellBackVo.getBackCode() != null
			&& !"".equals(sellBackVo.getBackCode())) {
		sql.append(" and ba.back_code like '%"
				+ StringEscapeUtils.escapeSql(sellBackVo.getBackCode()
						.trim()) + "%'");
	}
	if (sellBackVo.getBackType() != null
			&& !"".equals(sellBackVo.getBackType())) {
		sql.append(" and ba.back_type='" + sellBackVo.getBackType() + "'");
	}

	if (sellBackVo.getCarCode() != null
			&& !"".equals(sellBackVo.getCarCode())) {
		sql.append(" and ci.xs_car_code like '%"
				+ StringEscapeUtils.escapeSql(sellBackVo.getCarCode()
						.trim()) + "%'");
	}
	if (sellBackVo.getCarVinNumber() != null
			&& !"".equals(sellBackVo.getCarVinNumber())) {
		sql.append(" and ci.xs_car_vin_number like '%"
				+ StringEscapeUtils.escapeSql(sellBackVo.getCarVinNumber()
						.trim()) + "%'");
	}
	if (sellBackVo.getCarModel() != null
			&& !"".equals(sellBackVo.getCarModel())) {
		sql.append(" and ci.xs_car_model_id='" + sellBackVo.getCarModel()
				+ "'");
	}
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellBackVo wopq = new SellBackVo();
				obj = resultList.get(i);
				wopq.setCarVinNumber(obj[0] != null ? obj[0].toString() : null);
				wopq.setCarModel(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				wopq.setCarModelName(obj[2] != null ? obj[2].toString() : null);
				wopq.setCarCode(obj[3] != null ? obj[3].toString() : null);
				wopq.setCarLicenseName(obj[4] != null ? obj[4].toString()
						: null);
				wopq.setCarId(obj[5] != null ? Integer.parseInt(obj[5]
						.toString()) : null);
				wopq.setCarBrandName(obj[6] != null ? obj[6].toString() : null);
				wopq.setCarColorName(obj[7] != null ? obj[7].toString() : null);
				wopq.setCarBrand(obj[8] != null ? Integer.parseInt(obj[8]
						.toString()) : null);
				wopq.setCarColor(obj[9] != null ? Integer.parseInt(obj[9]
						.toString()) : null);

				wopq.setBackCode(obj[10] != null ? obj[10].toString() : null);
				wopq.setHandbackAllocateAmount(obj[11] != null ? Double
						.parseDouble(obj[11].toString()) : null);
				wopq.setIconCls("icon-blank");
				wopq.setState("open");
				list_.add(wopq);
			}
		}
		return list_;
	}

	/**
	 * 调退车辆增加
	 */
	
	public Datagrid getSellBackByDis(SellBackVo sellBackVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellBackVo> list = new ArrayList<SellBackVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT C.xs_distributor_id,"
						+ "b.allocate_amount,	d.xs_car_id,d.xs_car_code,d.xs_car_vin_number,"
						+ "d.xs_car_brand,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_car_brand) AS brand,"
						+ "d.xs_car_model_id,e.xs_model_name,d.xs_car_color,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.xs_car_color) AS color,"
						+ "a.details_id,d.xs_car_ocn "
						+ "FROM xs_sell_instorehouse_del A,"
						+ "xs_sell_allocatel_detail B,xs_sell_allocatel C,xs_car_info D,"
						+ "xs_car_model E WHERE a.details_id=b.details_id "
						+ "AND b.allocate_id=c.allocate_id AND a.xs_car_id=d.xs_car_id  "
						+ "AND d.xs_car_model_id=E.xs_model_id "
						+ "AND a.xs_sell_allocatel_type=(SELECT child.child_id FROM xs_childdictionary  child, "
						+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
						+ "AND parent.dataKey='"
						+ Contstants.BASE_SELL.INSTORETYPE
						+ "' AND "
						+ "child.dataKey='"
						+ Contstants.BASE_SELL.INSTORETYPE1
						+ "' and child.enterprise_id="+sellBackVo.getEnterprise_id()
						+ ") AND "
						+ "c.examine=(SELECT child.child_id FROM xs_childdictionary  child, "
						+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
						+ "AND parent.dataKey='" + Contstants.BASE_SELL.ADUIT
						+ "' AND " + "child.dataKey='"
						+ Contstants.BASE_SELL.ADUIT1 + "' and child.enterprise_id="+sellBackVo.getEnterprise_id()+") "
						+ " AND C.xs_distributor_id ="
						+ sellBackVo.getXsDistributorId() + " "
						+ " and c.enterprise_id="
						+ sellBackVo.getEnterprise_id());// 调拨，并审核通过
		if (sellBackVo.getCarVinNumber() != null
				&& !"".equals(sellBackVo.getCarVinNumber())) {
			sql.append(" and d.xs_car_vin_number like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getCarVinNumber()
							.trim()) + "%'");
		}
		if (sellBackVo.getCarCode() != null
				&& !"".equals(sellBackVo.getCarCode())) {
			sql.append(" and d.xs_car_code like '%"
					+ StringEscapeUtils.escapeSql(sellBackVo.getCarCode()
							.trim()) + "%'");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(), sellBackVo
				.getPage(), sellBackVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellBackVo back = new SellBackVo();
				obj = resultList.get(i);
				back.setXsDistributorId(obj[0] != null ? Integer
						.parseInt(obj[0].toString()) : null);
				back.setAllAmount(obj[1] != null ? Double.parseDouble(obj[1]
						.toString()) : null);
				back.setCarId(obj[2] != null ? Integer.parseInt(obj[2]
						.toString()) : null);
				back.setCarCode(obj[3] != null ? obj[3].toString() : null);
				back.setCarVinNumber(obj[4] != null ? obj[4].toString() : null);
				back.setCarBrand(obj[5] != null ? Integer.parseInt(obj[5]
						.toString()) : null);
				back.setCarBrandName(obj[6] != null ? obj[6].toString() : null);
				back.setCarModel(obj[7] != null ? Integer.parseInt(obj[7]
						.toString()) : null);
				back.setCarModelName(obj[8] != null ? obj[8].toString() : null);
				back.setCarColor(obj[9] != null ? Integer.parseInt(obj[9]
						.toString()) : null);
				back.setCarColorName(obj[10] != null ? obj[10].toString()
						: null);
				back.setDetailsId(obj[11] != null ? Integer.parseInt(obj[11]
						.toString()) : null);
				back.setCarOcn(obj[12] != null ? obj[12].toString() : null);

				list.add(back);
			}
		}
		int total = this.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
}
