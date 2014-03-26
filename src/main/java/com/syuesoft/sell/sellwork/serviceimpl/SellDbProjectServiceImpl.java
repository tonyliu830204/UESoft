package com.syuesoft.sell.sellwork.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.util.logging.Logger;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellDbProject;
import com.syuesoft.sell.sellwork.dao.SellDbProjectDAO;
import com.syuesoft.sell.sellwork.service.SellDbProjectService;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.sell.util.dao.SellAccountDao;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;

@Service("sellDbProjectService")
public class SellDbProjectServiceImpl extends BaseLogServiceImpl implements
		SellDbProjectService {
	private SellDbProjectDAO sellDbProjectDAO;
	private SellAccountDao accountDao;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	

	private BaseTagDAO baseTagDAO;

	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}

	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public SellAccountDao getAccountDao() {
		return accountDao;
	}

	@Autowired
	public void setAccountDao(SellAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public SellDbProjectDAO getSellDbProjectDAO() {
		return sellDbProjectDAO;
	}

	@Autowired
	public void setSellDbProjectDAO(SellDbProjectDAO sellDbProjectDAO) {
		this.sellDbProjectDAO = sellDbProjectDAO;
	}

	/*
	 * public SellAccountDao getAccountDao() { return accountDao; }
	 * 
	 * @Autowired public void setAccountDao(SellAccountDao accountDao) {
	 * this.accountDao = accountDao; }
	 */
	public Datagrid findSellInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"select  a.xs_car_sel_id,a.xs_car_sel_data,d.xs_custom_name,f.xs_car_vin_number,f.xs_car_ocn,"
						+ "(SELECT k.datavalue FROM xs_childdictionary k WHERE f.xs_car_brand = k.child_id) AS xs_car_brand,g.xs_model_name,a.xs_car_sel_transaction_money,"
						+ " h.xs_distributor_name,(SELECT k.datavalue FROM xs_childdictionary k WHERE a.audit = k.child_id) AS audit, a.out_id,a.xs_car_give_up, "
						+ "a.isdb_project, a.db_project_remark,a.sell_code ");
		
			sql	.append(",sellPro.db_project_code,sellPro.db_project_date,sellPro.db_project_person, sellPro.db_examin,"
							+ "(SELECT k.datavalue FROM xs_childdictionary k WHERE sellPro.db_examin = k.child_id) AS examinName,"
							+ "(SELECT stuff.STF_NAME FROM bas_stuff stuff WHERE stuff.STF_ID = sellPro.db_project_person) AS personName,"
							+ " sellPro.db_project_reckoning,co.account_code,  d.STF_ID,  " +
							"(SELECT  stuff.STF_NAME  FROM bas_stuff stuff  WHERE stuff.STF_ID = d.STF_ID)," +
							"(SELECT k.datavalue FROM xs_childdictionary k WHERE k.child_id = sellPro.db_project_reckoning) AS reck   ");
			sql	.append("  FROM xs_car_sell_info a  "
						+ " join xs_sell_flow_control flow on flow.xsfcontrol_document=a.sell_code "
						+ " JOIN xs_custom_info d  ON a.custom_id = d.custom_id"
						+ " JOIN xs_car_info f  ON a.xs_car_id = f.xs_car_id"
						+ " AND f.xs_car_sell_state IN	(SELECT child.child_id  FROM xs_childdictionary child, xs_parentdictionary parent"
						+ " WHERE child.parent_id = parent.parent_id    AND parent.dataKey = '"
						+ Contstants.SELLSTATE.BASE_SELLSTATE
						+ "'   AND child.dataKey IN('"
						+ Contstants.SELLSTATE.NOTSOLD
						+ "','"
						+ Contstants.SELLSTATE.SELLOUT
						+ "','"
						+ Contstants.SELLSTATE.AFTERSELL
						+ "'))"
						+ " LEFT JOIN xs_car_model g  ON f.xs_car_model_id = g.xs_model_id"
						+ " LEFT JOIN xs_distributor_info h  ON f.xs_distributor_id = h.xs_distributor_id ");
		
				sql	.append("   LEFT JOIN xs_sell_db_project  sellPro ON  sellPro.xs_car_sel_id=a.xs_car_sel_id "
							+ " LEFT JOIN xs_sell_account co ON co.account_type_id = sellPro.db_project_code");
		
		sql.append("  WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " +
		// "and a.audit=(SELECT child.child_id FROM xs_childdictionary  child, "
				// +
				// "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
				// +
				// "AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
				// "child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
				// ") " +
				"");

		// 企业编号
		if (sellDbProjectVo.getEnterpriseId() != null
				&& !sellDbProjectVo.getEnterpriseId().equals("")) {
			sql.append("  and a.Enterprise_Id = "
					+ sellDbProjectVo.getEnterpriseId() + "");
		}

		if (sellDbProjectVo.getXs_Car_Sel_Data() != null
				&& !sellDbProjectVo.getXs_Car_Sel_Data().equals("")) {
			sql.append(" and DATE(A.Xs_Car_Sel_Data) >= '"
					+ sellDbProjectVo.getXs_Car_Sel_Data() + "'");
		}
		if (sellDbProjectVo.getXs_Car_Sel_Data2() != null
				&& !sellDbProjectVo.getXs_Car_Sel_Data2().equals("")) {
			sql.append(" and DATE(A.Xs_Car_Sel_Data) <= '"
					+ sellDbProjectVo.getXs_Car_Sel_Data2() + "'");
		}
		
		if ((sellDbProjectVo.getXs_Car_Sel_Data() == null || sellDbProjectVo
					.getXs_Car_Sel_Data().equals(""))
					&& (sellDbProjectVo.getXs_Car_Sel_Data2() == null || sellDbProjectVo
							.getXs_Car_Sel_Data2().equals(""))) {
			sql.append(" and DATE(A.Xs_Car_Sel_Data) BETWEEN" +
					" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellDbProjectVo.getEnterpriseId()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");	
			
			
		}
		if (sellDbProjectVo.getQueryProjectDate() != null
				&& !sellDbProjectVo.getQueryProjectDate().equals("")) {
			sql.append(" and DATE(sellPro.db_project_date) >= '"
					+ sellDbProjectVo.getQueryProjectDate() + "'");
		}
		if (sellDbProjectVo.getQueryProjectDate2() != null
				&& !sellDbProjectVo.getQueryProjectDate2().equals("")) {
			sql.append(" and DATE(sellPro.db_project_date) <= '"
					+ sellDbProjectVo.getQueryProjectDate2() + "'");
		}
		if (sellDbProjectVo.getXs_Custom_Name() != null
				&& !sellDbProjectVo.getXs_Custom_Name().equals("")) {
			sql.append("  and d.Xs_Custom_Name like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Custom_Name().trim()) + "%'");
		}
		if (sellDbProjectVo.getStf_Name() != null
				&& !sellDbProjectVo.getStf_Name().equals("")) {
			sql.append("  and d.STF_ID =" + sellDbProjectVo.getStf_Name());
		}
		if (sellDbProjectVo.getXs_Car_Vin_Number() != null
				&& !sellDbProjectVo.getXs_Car_Vin_Number().equals("")) {
			sql.append(" and f.Xs_Car_Vin_Number like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Car_Vin_Number().trim()) + "%'");
		}
		if (sellDbProjectVo.getXs_Car_Ocn() != null
				&& !sellDbProjectVo.getXs_Car_Ocn().equals("")) {
			sql.append(" and f.Xs_Car_Ocn like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Car_Ocn().trim()) + "%'");
		}
		if (sellDbProjectVo.getDbExamin() != null
				&& !sellDbProjectVo.getDbExamin().equals("")) {
			sql.append("  and sellPro.db_examin ="
					+ sellDbProjectVo.getDbExamin());
		}
		if (sellDbProjectVo.getDbProjectCode() != null
				&& !sellDbProjectVo.getDbProjectCode().equals("")) {
			sql.append("  and sellPro.db_project_code like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getDbProjectCode().trim()) + "%'");
		}
		// if(sellDbProjectVo.getIsdb_project()!=null &&
		// !("".equals(sellDbProjectVo.getIsdb_project())) &&
		// "false".equals(sellDbProjectVo.getIsdb_project())){
		// sql.append(
		// " and (a.isdb_project IS  NULL  or a.isdb_project=(SELECT child.child_id FROM xs_childdictionary  child, "
		// +
		// "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
		// +
		// "AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
		// "child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE2+"'" +
		// ") )");
		// }
		
			sql.append("  and (a.isdb_project IS NOT NULL  or a.isdb_project=(SELECT child.child_id FROM xs_childdictionary  child, "
							+ "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
							+ "AND parent.dataKey='"
							+ Contstants.BASE_SELL.PAYMENTSTATE
							+ "' AND "
							+ "child.dataKey='"
							+ Contstants.BASE_SELL.PAYMENTSTATE1
							+ "'"
							+ ") ) GROUP BY sellPro.db_project_code ");
		
		// if(sellDbProjectVo.getIs_invoice()!=null &&
		// !("".equals(sellDbProjectVo.getIs_invoice())) &&
		// "false".equals(sellDbProjectVo.getIs_invoice())){
		// sql.append(
		// " and (a.is_invoice IS  NULL  or a.is_invoice=(SELECT child.child_id FROM xs_childdictionary  child, "
		// +
		// "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
		// +
		// "AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
		// "child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE2+"'" +
		// ") )");
		// }
		List<Object[]> lst = sellDbProjectDAO.createSQLQuery(sql.toString(),
				sellDbProjectVo.getPage(), sellDbProjectVo.getRows());
		List<SellDbProjectVo> rows = new ArrayList<SellDbProjectVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] obj : lst) {
				SellDbProjectVo vo = new SellDbProjectVo();
				if (obj[0] != null) {
					vo.setXs_Car_Sel_Id((Integer) obj[0]);
				}
				if (obj[1] != null) {
					vo.setXs_Car_Sel_Data(obj[1].toString());
				}
				if (obj[2] != null) {
					vo.setXs_Custom_Name(obj[2].toString());
				}
				if (obj[3] != null) {
					vo.setXs_Car_Vin_Number(obj[3].toString());
				}
				if (obj[4] != null) {
					vo.setXs_Car_Ocn(obj[4].toString());
				}
				if (obj[5] != null) {
					vo.setXs_Car_Brand(obj[5].toString());
				}

				if (obj[6] != null) {
					vo.setXs_Model_Name(obj[6].toString());
				}
				if (obj[7] != null) {
					vo.setXs_Car_Sel_Transaction_Money(obj[7].toString());
				}
				// if(obj[10]!=null){vo.setUser_Name(obj[10].toString());}

				if (obj[8] != null) {
					vo.setXs_Distributor_Name(obj[8].toString());
				}
				if (obj[9] != null) {
					vo.setExamine(obj[9].toString());
				}

				// if(obj[13]!=null){vo.setLimit_load_num(obj[13].toString());}

				// if(obj[14]!=null){vo.setMobilephone(obj[14].toString());}
				// 出库单 用作判断是否出库
				if (obj[10] != null) {
					vo.setOut_Id(obj[10].toString());
				}
				// if(obj[16]!=null){vo.setReserve_Code(obj[16].toString());}
				if (obj[11] != null) {
					vo.setXs_Car_Give_Up(obj[11].toString());
				}
				if (obj[12] != null) {
					vo.setIsdb_project(obj[12].toString());
				}
				if (obj[13] != null) {
					vo.setDbProjectRemark(obj[13].toString());
				}
				if (obj[14] != null) {
					vo.setSellCode(obj[14].toString());
				}
				
				if (obj[15] != null) {
					vo.setDbProjectCode(obj[15].toString());
				}
				if (obj[16] != null) {
					vo.setDbProjectDate((Date) obj[16]);
				}
				if (obj[17] != null) {
					vo.setDbProjectPerson((Integer) obj[17]);
				}
				if (obj[18] != null) {
					vo.setDbExamin((Integer) obj[18]);
				}
				if (obj[19] != null) {
					vo.setExaminName(obj[19].toString());
				}
				if (obj[20] != null) {
					vo.setDbPersonName(obj[20].toString());
				}
				if (obj[21] != null) {
					vo.setDbProjectReckoning((Integer) obj[21]);
				}
				if (obj[22] != null) {
					vo.setAccountCode(obj[22].toString());
				}
				if (obj[24] != null) {
					vo.setStf_Name(obj[24].toString());
				}
				if (obj[25] != null) {
					vo.setDbProjectReckoningN(obj[25].toString());
				}
				

				rows.add(vo);
			}
		}
		int total = sellDbProjectDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
//开票选销售单
	public Datagrid getInvoSellInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"select  a.xs_car_sel_id,a.xs_car_sel_data,d.xs_custom_name,f.xs_car_vin_number,f.xs_car_ocn,"
						+ "(SELECT k.datavalue FROM xs_childdictionary k WHERE f.xs_car_brand = k.child_id) AS xs_car_brand,g.xs_model_name,a.xs_car_sel_transaction_money,"
						+ " h.xs_distributor_name,(SELECT k.datavalue FROM xs_childdictionary k WHERE a.audit = k.child_id) AS audit, a.out_id,a.xs_car_give_up, "
						+ "a.isdb_project, a.db_project_remark,a.sell_code ");

		sql	.append("  FROM xs_car_sell_info a  "
						+ " join xs_sell_flow_control flow on flow.xsfcontrol_document=a.sell_code "
						+ " JOIN xs_custom_info d  ON a.custom_id = d.custom_id"
						+ " JOIN xs_car_info f  ON a.xs_car_id = f.xs_car_id"
						+ " AND f.xs_car_sell_state IN	(SELECT child.child_id  FROM xs_childdictionary child, xs_parentdictionary parent"
						+ " WHERE child.parent_id = parent.parent_id    AND parent.dataKey = '"
						+ Contstants.SELLSTATE.BASE_SELLSTATE
						+ "'   AND child.dataKey IN('"
						+ Contstants.SELLSTATE.NOTSOLD
						+ "','"
						+ Contstants.SELLSTATE.SELLOUT
						+ "','"
						+ Contstants.SELLSTATE.AFTERSELL
						+ "'))"
						+ " LEFT JOIN xs_car_model g  ON f.xs_car_model_id = g.xs_model_id"
						+ " LEFT JOIN xs_distributor_info h  ON f.xs_distributor_id = h.xs_distributor_id ");

		sql.append("  WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " +
		// "and a.audit=(SELECT child.child_id FROM xs_childdictionary  child, "
				// +
				// "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id "
				// +
				// "AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
				// "child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
				// ") " +
				"");

		// 企业编号
		if (sellDbProjectVo.getEnterpriseId() != null
				&& !sellDbProjectVo.getEnterpriseId().equals("")) {
			sql.append("  and a.Enterprise_Id = "
					+ sellDbProjectVo.getEnterpriseId() + "");
		}

		if (sellDbProjectVo.getXs_Car_Sel_Data() != null
				&& !sellDbProjectVo.getXs_Car_Sel_Data().equals("")) {
			sql.append(" and DATE(A.Xs_Car_Sel_Data) >= '"
					+ sellDbProjectVo.getXs_Car_Sel_Data() + "'");
		}
		if (sellDbProjectVo.getXs_Car_Sel_Data2() != null
				&& !sellDbProjectVo.getXs_Car_Sel_Data2().equals("")) {
			sql.append(" and DATE(A.Xs_Car_Sel_Data) <= '"
					+ sellDbProjectVo.getXs_Car_Sel_Data2() + "'");
		}

		if (sellDbProjectVo.getQueryProjectDate() != null
				&& !sellDbProjectVo.getQueryProjectDate().equals("")) {
			sql.append(" and DATE(sellPro.db_project_date) >= '"
					+ sellDbProjectVo.getQueryProjectDate() + "'");
		}
		if (sellDbProjectVo.getQueryProjectDate2() != null
				&& !sellDbProjectVo.getQueryProjectDate2().equals("")) {
			sql.append(" and DATE(sellPro.db_project_date) <= '"
					+ sellDbProjectVo.getQueryProjectDate2() + "'");
		}
		if (sellDbProjectVo.getXs_Custom_Name() != null
				&& !sellDbProjectVo.getXs_Custom_Name().equals("")) {
			sql.append("  and d.Xs_Custom_Name like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Custom_Name().trim()) + "%'");
		}
		if (sellDbProjectVo.getStf_Name() != null
				&& !sellDbProjectVo.getStf_Name().equals("")) {
			sql.append("  and E.STF_ID =" + sellDbProjectVo.getStf_Name());
		}
		if (sellDbProjectVo.getXs_Car_Vin_Number() != null
				&& !sellDbProjectVo.getXs_Car_Vin_Number().equals("")) {
			sql.append(" and f.Xs_Car_Vin_Number like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Car_Vin_Number().trim()) + "%'");
		}
		if (sellDbProjectVo.getXs_Car_Ocn() != null
				&& !sellDbProjectVo.getXs_Car_Ocn().equals("")) {
			sql.append(" and f.Xs_Car_Ocn like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getXs_Car_Ocn().trim()) + "%'");
		}
		if (sellDbProjectVo.getDbExamin() != null
				&& !sellDbProjectVo.getDbExamin().equals("")) {
			sql.append("  and sellPro.db_examin ="
					+ sellDbProjectVo.getDbExamin());
		}
		if (sellDbProjectVo.getDbProjectCode() != null
				&& !sellDbProjectVo.getDbProjectCode().equals("")) {
			sql.append("  and sellPro.db_project_code like '%"
					+ StringEscapeUtils.escapeSql(sellDbProjectVo
							.getDbProjectCode().trim()) + "%'");
		}

		sql.append("  GROUP BY a.xs_car_sel_id");

		List<Object[]> lst = sellDbProjectDAO.createSQLQuery(sql.toString(),
				sellDbProjectVo.getPage(), sellDbProjectVo.getRows());
		List<SellDbProjectVo> rows = new ArrayList<SellDbProjectVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] obj : lst) {
				SellDbProjectVo vo = new SellDbProjectVo();
				if (obj[0] != null) {
					vo.setXs_Car_Sel_Id((Integer) obj[0]);
				}
				if (obj[1] != null) {
					vo.setXs_Car_Sel_Data(obj[1].toString());
				}
				if (obj[2] != null) {
					vo.setXs_Custom_Name(obj[2].toString());
				}
				if (obj[3] != null) {
					vo.setXs_Car_Vin_Number(obj[3].toString());
				}
				if (obj[4] != null) {
					vo.setXs_Car_Ocn(obj[4].toString());
				}
				if (obj[5] != null) {
					vo.setXs_Car_Brand(obj[5].toString());
				}

				if (obj[6] != null) {
					vo.setXs_Model_Name(obj[6].toString());
				}
				if (obj[7] != null) {
					vo.setXs_Car_Sel_Transaction_Money(obj[7].toString());
				}
				// if(obj[10]!=null){vo.setUser_Name(obj[10].toString());}

				if (obj[8] != null) {
					vo.setXs_Distributor_Name(obj[8].toString());
				}
				if (obj[9] != null) {
					vo.setExamine(obj[9].toString());
				}

				// if(obj[13]!=null){vo.setLimit_load_num(obj[13].toString());}

				// if(obj[14]!=null){vo.setMobilephone(obj[14].toString());}
				// 出库单 用作判断是否出库
				if (obj[10] != null) {
					vo.setOut_Id(obj[10].toString());
				}
				// if(obj[16]!=null){vo.setReserve_Code(obj[16].toString());}
				if (obj[11] != null) {
					vo.setXs_Car_Give_Up(obj[11].toString());
				}
				if (obj[12] != null) {
					vo.setIsdb_project(obj[12].toString());
				}
				if (obj[13] != null) {
					vo.setDbProjectRemark(obj[13].toString());
				}
				if (obj[14] != null) {
					vo.setSellCode(obj[14].toString());
				}
				

				rows.add(vo);
			}
		}
		int total = sellDbProjectDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	
	public Datagrid findSellDb(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"	SELECT sellPro.id,sellPro.xs_car_sel_id,sellPro.db_project_id,sellPro.db_project_cost,sellPro.cost_price,"
						+ "	dbPro.xs_project_name,sellPro.db_project_person, sellPro.db_project_date, sellPro.db_project_code,sellPro.db_examin"
						+ "   FROM  xs_sell_db_project sellPro,"
						+ "	xs_db_project dbPro,"
						+ "	xs_car_sell_info sell "
						+ "   WHERE   sellPro.db_project_id=dbPro.xs_project_id "
						+ "	AND sellPro.xs_car_sel_id = sell.xs_car_sel_id ");

		// 企业编号
		if (sellDbProjectVo.getEnterpriseId() != null
				&& !sellDbProjectVo.getEnterpriseId().equals("")) {
			sql.append("  and sell.Enterprise_Id = "
					+ sellDbProjectVo.getEnterpriseId() + "");
		}
		if (sellDbProjectVo.getXs_Car_Sel_Id() != null
				&& !("".equals(sellDbProjectVo.getXs_Car_Sel_Id()))) {
			sql.append("   and  sellPro.xs_car_sel_id="
					+ sellDbProjectVo.getXs_Car_Sel_Id());
		}
		if (sellDbProjectVo.getDbProjectCode() != null
				&& !("".equals(sellDbProjectVo.getDbProjectCode()))) {
			sql.append("  and   sellPro.db_project_code='"
					+ sellDbProjectVo.getDbProjectCode() + "'");
		}
		List<Object[]> lst = sellDbProjectDAO.createSQLQuery(sql.toString(),
				sellDbProjectVo.getPage(), sellDbProjectVo.getRows());
		List<SellDbProjectVo> rows = new ArrayList<SellDbProjectVo>();
		if (lst != null && lst.size() > 0) {
			for (Object[] obj : lst) {
				SellDbProjectVo sellVo = new SellDbProjectVo();
				if (obj[0] != null && !("".equals(obj[0]))) {
					sellVo.setSellid((Integer) obj[0]);
				}
				if (obj[1] != null && !("".equals(obj[1]))) {
					sellVo.setXs_Car_Sel_Id((Integer) obj[1]);
				}
				if (obj[2] != null && !("".equals(obj[2]))) {
					sellVo.setProjectId((Integer) obj[2]);
				}
				if (obj[3] != null && !("".equals(obj[3]))) {
					sellVo.setProjectAmount((Double) obj[3]);
				}
				if (obj[4] != null && !("".equals(obj[4]))) {
					sellVo.setProjectMomay((Double) obj[4]);
				}
				if (obj[5] != null && !("".equals(obj[5]))) {
					sellVo.setProjectName(obj[5].toString());
				}
				if (obj[6] != null && !("".equals(obj[6]))) {
					sellVo.setDbProjectPerson((Integer) obj[6]);
				}
				if (obj[7] != null && !("".equals(obj[7]))) {
					sellVo.setDbProjectDate((Date) obj[7]);
				}
				if (obj[8] != null && !("".equals(obj[8]))) {
					sellVo.setDbProjectCode(obj[8].toString());
				}
				if (obj[9] != null && !("".equals(obj[9]))) {
					sellVo.setDbExamin((Integer) obj[9]);
				}
				rows.add(sellVo);
			}
		}
		int total = sellDbProjectDAO.getSQLCount(sql.toString(), null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	//
	@Log(systemName = "销售系统", moduleName = "销售代办项目", opertype = "新增")
	
	public void saveSellPro(SellDbProjectVo sellDbProjectVo) throws Exception {
		SellDbProjectVo carReserve = JSON.parseObject(sellDbProjectVo
				.getSellReserve(), SellDbProjectVo.class);
		List<SellDbProjectVo> insertedList = JSON.parseArray(sellDbProjectVo
				.getSellProGrid(), SellDbProjectVo.class);
		XsCarSellInfo sellInfo = (XsCarSellInfo) sellDbProjectDAO
				.get("from  XsCarSellInfo sell where sell.xsCarSelId="
						+ carReserve.getXs_Car_Sel_Id());
		sellInfo.setIsdbProject(baseTagDAO.getChildId(
				Contstants.BASE_SELL.PAYMENTSTATE,
				Contstants.BASE_SELL.PAYMENTSTATE1,sellDbProjectVo.getEnterpriseId()));
		sellInfo.setDbProjectRemark(carReserve.getDbProjectRemark());
		sellDbProjectDAO.save(sellInfo);
		if (insertedList != null && insertedList.size() > 0) {
			String code = CreateID.createId("sellDb",
					Contstants.SELL_BILLSDEPLOY.DBDH);
			String str = "";
			for (SellDbProjectVo dbVo : insertedList) {
				XsSellDbProject sellDb = new XsSellDbProject();
				sellDb.setDbProjectId(dbVo.getProjectId());
				sellDb.setDbProjectCost(dbVo.getProjectAmount());
				sellDb.setCostPrice(dbVo.getProjectMomay());
				sellDb.setXsCarSellInfo(sellInfo);
				sellDb.setDbProjectCode(code);
				sellDb.setDbProjectDate(new Date());
				sellDb.setDbProjectPerson(sellDbProjectVo.getDbProjectPerson());
				sellDb.setDbExamin(baseTagDAO
						.getChildId(Contstants.BASE_SELL.ADUIT,
								Contstants.BASE_SELL.ADUIT2,sellDbProjectVo.getEnterpriseId()));
				sellDb.setDbProjectReckoning(baseTagDAO.getChildId(
						Contstants.BASE_SELL.PAYMENTSTATE,
						Contstants.BASE_SELL.PAYMENTSTATE2,sellDbProjectVo.getEnterpriseId()));
				str += "," + sellDb.getDbProjectCode();
				sellDbProjectDAO.save(sellDb);
			}
			setContent("新增以下编号的代办项目：" + str);
		}
	}

	@Log(moduleName = "销售作业", opertype = "删除", content = "删除代办项目")
	
	public void deleteSellPro(SellDbProjectVo sellDbProjectVo) throws Exception {
		List tempList = sellDbProjectDAO
				.find("from XsSellDbProject xsdp where xsdp.dbProjectCode='"
						+ sellDbProjectVo.getDbProjectCode() + "'");
		List<XsSellDbProject> tlist = tempList;
		String str = "";
		for (XsSellDbProject xsSellDbProject : tlist) {
			str += "," + xsSellDbProject.getDbProjectCode();
			sellDbProjectDAO.delete(xsSellDbProject);
		}
		setContent("删除以下编号的代办项目：" + str);
		List<Object> list = sellDbProjectDAO
				.find("from XsSellDbProject  db where db.xsCarSellInfo.xsCarSelId="
						+ sellDbProjectVo.getXs_Car_Sel_Id());
		if (!(list != null && list.size() > 0)) {
			XsCarSellInfo sellInfo = (XsCarSellInfo) sellDbProjectDAO
					.get("from  XsCarSellInfo sell where sell.xsCarSelId="
							+ sellDbProjectVo.getXs_Car_Sel_Id());
			sellInfo.setIsdbProject(null);
			sellDbProjectDAO.merge(sellInfo);
		}
		// XsSellAccount account=(XsSellAccount)
		// sellDbProjectDAO.get("from  XsSellAccount account where account.accountTypeId='"+sellDbProjectVo.getDbProjectCode()+"'");
		// if(account!=null){
		// sellDbProjectDAO.delete(account);
		// }
	}

	@Log(moduleName = "销售作业", opertype = "修改", content = "修改代办项目")
	
	public void updateSellPro(SellDbProjectVo sellDbProjectVo) throws Exception {
		SellDbProjectVo carReserve = JSON.parseObject(sellDbProjectVo
				.getSellReserve(), SellDbProjectVo.class);
		List<SellDbProjectVo> insertedList = JSON.parseArray(sellDbProjectVo
				.getInserted(), SellDbProjectVo.class);
		List<SellDbProjectVo> updatedList = JSON.parseArray(sellDbProjectVo
				.getUpdated(), SellDbProjectVo.class);
		List<SellDbProjectVo> deletedList = JSON.parseArray(sellDbProjectVo
				.getDeleted(), SellDbProjectVo.class);
		XsCarSellInfo sellInfo = (XsCarSellInfo) sellDbProjectDAO
				.get("from  XsCarSellInfo sell where sell.xsCarSelId="
						+ carReserve.getXs_Car_Sel_Id());
		sellInfo.setDbProjectRemark(carReserve.getDbProjectRemark());
		sellDbProjectDAO.merge(sellInfo);
		String str = "";
		if (insertedList != null && insertedList.size() > 0) {
			for (SellDbProjectVo dbVo : insertedList) {
				XsSellDbProject sellDb = new XsSellDbProject();
				sellDb.setDbProjectId(dbVo.getProjectId());
				sellDb.setDbProjectCost(dbVo.getProjectAmount());
				sellDb.setCostPrice(dbVo.getProjectMomay());
				sellDb.setXsCarSellInfo(sellInfo);
				sellDb.setDbProjectCode(carReserve.getDbProjectCode());
				sellDb.setDbProjectDate(new Date());
				sellDb.setDbProjectPerson(carReserve.getDbProjectPerson());
				sellDb.setDbExamin(baseTagDAO
						.getChildId(Contstants.BASE_SELL.ADUIT,
								Contstants.BASE_SELL.ADUIT2,sellDbProjectVo.getEnterpriseId()));
				sellDb.setDbProjectReckoning(baseTagDAO.getChildId(
						Contstants.BASE_SELL.PAYMENTSTATE,
						Contstants.BASE_SELL.PAYMENTSTATE2,sellDbProjectVo.getEnterpriseId()));
				str += "," + sellDb.getDbProjectCode();
				sellDbProjectDAO.save(sellDb);
			}
			setContent("新增以下编号的代办项目信息：" + str);

		}
		if (updatedList != null && updatedList.size() > 0) {
			for (SellDbProjectVo dbVo : updatedList) {
				XsSellDbProject sellDb = new XsSellDbProject();
				sellDb.setId(dbVo.getSellid());
				sellDb.setDbProjectId(dbVo.getProjectId());
				sellDb.setDbProjectCost(dbVo.getProjectAmount());
				sellDb.setCostPrice(dbVo.getProjectMomay());
				sellDb.setXsCarSellInfo(sellInfo);
				sellDb.setDbProjectCode(dbVo.getDbProjectCode());
				sellDb.setDbProjectDate(dbVo.getDbProjectDate());
				sellDb.setDbProjectPerson(dbVo.getDbProjectPerson());
				sellDb.setDbExamin(dbVo.getDbExamin());
				sellDb.setDbProjectReckoning(dbVo.getDbProjectReckoning());
				str += "," + sellDb.getDbProjectCode();
				sellDbProjectDAO.update(sellDb);
			}
			setContent("修改以下编号的代办项目信息：" + str);
		}
		if (deletedList != null && deletedList.size() > 0) {
			for (SellDbProjectVo dbVo : deletedList) {
				XsSellDbProject sellDb = (XsSellDbProject) sellDbProjectDAO
						.get("from XsSellDbProject sell where sell.id="
								+ dbVo.getSellid());
				sellDbProjectDAO.delete(sellDb);
			}
		}

	}

	/**
	 * 代办项目审核
	 */
	@Log(moduleName = "销售作业", opertype = "审核", content = "代办项目审核")
	
	public void updateExamin(SellDbProjectVo sellDbProjectVo) throws Exception {
		List<Object> lst = sellDbProjectDAO.findExaminState(sellDbProjectVo);
		if (lst != null && lst.size() > 0) {
			String str = "";
			for (Object obj : lst) {
				XsSellDbProject sellPro = (XsSellDbProject) obj;
				if (Integer.parseInt(sellPro.getDbExamin().toString()) == Integer
						.parseInt(baseTagDAO.getChildId(
								Contstants.BASE_SELL.ADUIT,
								Contstants.BASE_SELL.ADUIT2,sellDbProjectVo.getEnterpriseId()).toString())) {
					sellPro.setDbExamin(baseTagDAO.getChildId(
							Contstants.BASE_SELL.ADUIT,
							Contstants.BASE_SELL.ADUIT1,sellDbProjectVo.getEnterpriseId()));
					str += "," + sellPro.getDbProjectCode();
				} else {
					sellPro.setDbExamin(baseTagDAO.getChildId(
							Contstants.BASE_SELL.ADUIT,
							Contstants.BASE_SELL.ADUIT2,sellDbProjectVo.getEnterpriseId()));
					str += "," + sellPro.getDbProjectCode();
				}
				setContent("审核编号为" + str + "代办项目信息！");
				sellDbProjectDAO.merge(sellPro);
			}
		}

	}

	/**
	 * 代办项目转结算
	 */
	@Log(moduleName = "销售作业", opertype = "转结算", content = "代办项目转结算")
	
	public void updateSellAcount(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		// 计算总的代办费用
		List<Object> lst = sellDbProjectDAO.findExaminState(sellDbProjectVo);
		double sum = 0;
		if (lst != null && lst.size() > 0) {
			String str = "";
			
			for (Object obj : lst) {
				XsSellDbProject sellPro = (XsSellDbProject) obj;
				sellPro.setDbProjectReckoning(baseTagDAO.getChildId(
						Contstants.BASE_SELL.PAYMENTSTATE,
						Contstants.BASE_SELL.PAYMENTSTATE1,sellDbProjectVo.getEnterpriseId()));
				sellDbProjectDAO.merge(sellPro);
				sum += sellPro.getDbProjectCost();
			}
			XsSellAccount account = (XsSellAccount) sellDbProjectDAO
					.get(" from  XsSellAccount account where account.accountTypeId='"
							+ sellDbProjectVo.getDbProjectCode() + "'");
			if (account == null) {
				int types = (baseTagDAO.getChildId(
						Contstants.BASE_SELL.ACCOUNTTYPE,
						Contstants.BASE_SELL.ACCOUNTTYPE2,sellDbProjectVo.getEnterpriseId()));
				accountDao.saveSellAccount(sellDbProjectVo.getXs_Car_Sel_Id(),
						sellDbProjectVo.getDbProjectCode(), types, sum,
						sellDbProjectVo.getDbProjectRemark(), sellDbProjectVo
								.getEnterpriseId());
				setContent("将编号为" + sellDbProjectVo.getDbProjectCode()
						+ "的代办项目转结算！");
			} else {
				// account.setAccountGyration(214);
				account.setAccountMoney(sum);
				account.setRemark(sellDbProjectVo.getDbProjectRemark());
				sellDbProjectDAO.merge(account);
			}
		}
	}

	
	public Boolean isNotRefundment(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE,
				Contstants.BASE_SELL.ADUIT,sellDbProjectVo.getEnterpriseId());
		if (sellDbProjectVo.getDbExamin().equals(examine))
			return true;
		return false;
	}

	
	public Boolean isRefundment(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE,
				Contstants.BASE_SELL.ADUIT,sellDbProjectVo.getEnterpriseId());
		if (sellDbProjectVo.getDbExamin().equals(examine))
			return true;
		return false;
	}

	
	public Boolean isUse(SellDbProjectVo sellDbProjectVo) throws Exception {
		String sql = "SELECT * FROM Xs_Sell_Account a, xs_sell_db_project B,Xs_Sell_Flow_Control c "
				+ " where a.account_type_id=b.db_project_code and a.account_code = c.xsfcontrol_document  "
				+ " and a.account_type_id ='"
				+ sellDbProjectVo.getDbProjectCode() + "'";

		List list = sellDbProjectDAO.createSQLQuery(sql);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	
	public List queryDbChildInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		if (sellDbProjectVo.getXs_Custom_Name() != null)
			sellDbProjectVo.setXs_Custom_Name(new String(sellDbProjectVo
					.getXs_Custom_Name().getBytes("ISO-8859-1"), "UTF-8"));
		return sellDbProjectDAO.queryDbChildInfor(sellDbProjectVo);
	}

	/**
	 * 代办项目查询
	 */
	
	public DatagridAnalyze queryDbInfor(SellDbProjectVo sellDbProjectVo)
			throws Exception {
		/*if (sellDbProjectVo.getXs_Custom_Name() != null)
			sellDbProjectVo.setXs_Custom_Name(new String(sellDbProjectVo
					.getXs_Custom_Name().getBytes("ISO-8859-1"), "UTF-8"));*/
		return sellDbProjectDAO.queryDbInfor(sellDbProjectVo);
	}

}
