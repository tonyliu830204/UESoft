package com.syuesoft.sell.synthesis_assay.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.dao.DayReportAssayDao;
import com.syuesoft.sell.synthesis_assay.service.DayReportAssayService;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;
import com.syuesoft.util.FormatTime;

@Service("dayReportAssayService")
public class DayReportAssayServiceImpl extends BaseLogServiceImpl implements
		DayReportAssayService {

	@Resource
	private DayReportAssayDao dayReportAssayDao;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 获取该车型作为动态列
	 */
	
	public List getAnimatedColum(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		// List list = new ArrayList();
		// String sql = "";
		// if(dayReportAssayVo.getCarBrand()!=null &&
		// !dayReportAssayVo.getCarBrand().equals("")){
		// sql =
		// "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id AND a.xs_car_brand="+dayReportAssayVo.getCarBrand()+" ";
		// }else if(dayReportAssayVo.getCarModel()!=null &&
		// !dayReportAssayVo.getCarModel().equals("")){
		// //获取型号名称
		// sql =
		// "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id AND b.xs_model_id="+dayReportAssayVo.getCarModel()+" GROUP BY b.xs_model_id";
		// }else{
		// sql =
		// "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id GROUP BY b.xs_model_id";
		// }
		// if(dayReportAssayVo.getXsSellDate()!=null &&
		// !dayReportAssayVo.getXsSellDate().equals("")){
		// list = dayReportAssayDao.createSQLQuery(sql);
		// }
		return null;
	}

	/**
	 * 车获取该车型作为动态列
	 * */
	
	public List doDayreportAssayColumns(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		String source = "";
		/** 日销售分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("dayassay")) {
			source = doDayreportAssay(dayReportAssayVo);
		}
		/** 月销售分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("monthassay")) {
			source = doMonthreportAssay(dayReportAssayVo);
		}
		/** 销售方式分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("sellwayassay")) {
			source = doSellwayAssay(dayReportAssayVo);
		}
		/** 业务员销售分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("workerassay")) {
			source = doWorkerSellAssay(dayReportAssayVo);
		}
		/** 销售部门分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("selldepart")) {
			source = doDeptSellAssay(dayReportAssayVo);
		}
		/** 销售班组分析 */
		if (dayReportAssayVo.getAssayType() != null
				&& dayReportAssayVo.getAssayType().equals("sellteamassay")) {
			source = doSellTeamsAssay(dayReportAssayVo);
		}
		List list = new ArrayList();
		if (source != null && source.length() > 0) {
			source = source.replace("[", "");
			source = source.replace("]", "");
			String[] str = source.split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		return list;
	}

	/**
	 * 获取当天该车型的统计数量
	 */
	
	public String doDayreportAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List<HashMap<String, String>> list = new ArrayList();
		Object[] obj = null;
		String executsql = "";
		/*if (dayReportAssayVo.getXsSellDate() != null
				&& !dayReportAssayVo.getXsSellDate().equals("")) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");*/
			String str0 = " SELECT aa.xs_model_name,DATE(aa.xs_car_sel_data) AS xs_car_sel_data,";
			String publicsql = "  SUM(dayCount)    AS TOTAL"
					+ "	FROM ("
					+ "	SELECT"
					+ "   DATE(xcsi.xs_car_sel_data) AS xs_car_sel_data,"
					+ "   xcm.xs_model_name,"
					+ "   COUNT(*)            AS dayCount"
					+ "   FROM xs_car_sell_info xcsi"
					+ "    JOIN xs_car_info car"
					+ "   ON car.xs_car_id = xcsi.xs_car_id"
					+ "    JOIN xs_sell_flow_control xsfc"
					+ "   ON xsfc.xsfcontrol_document = xcsi.sell_code"
					+ "   AND car.xs_car_id = xsfc.xsfcontrol_car_id"
					+ "   AND xsfc.xsfcontrol_code = "
					+ Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6
					+ "    JOIN xs_car_model xcm"
					+ "   ON xcm.xs_model_id = car.xs_car_model_id"
					+ "   LEFT JOIN  xs_sell_retreat outs"
					+ "   ON outs.retreat_id = xcsi.out_id"
					+ "    JOIN xs_custom_info custom"
					+ "   ON xcsi.custom_id = custom.custom_id"
					+ "   LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar)"
					+ "   LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id"
					+ "   LEFT JOIN xs_sell_car_reserve G ON G.reserve_id = xcsi.reserve_id where 1=1 ";
			String str2 = " GROUP BY xcm.xs_model_name ) AS aa GROUP BY aa.xs_model_name"
					+ "  UNION ";
			String str4 = " SELECT 'model','日销售分析汇总',";

			String str6 = " GROUP BY xcm.xs_model_name ) AS aa ";

			String strcondition = "";

			if(dayReportAssayVo.getEnterprise_id() != null
					&& !dayReportAssayVo.getEnterprise_id().equals("")){
				strcondition+=" and xcsi.enterprise_id ="+dayReportAssayVo.getEnterprise_id();
				
			}
			// 添加查询条件
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
					strcondition += " and DATE(xcsi.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'";
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
					strcondition += " and DATE(xcsi.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'";
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				strcondition += " and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'" ;
			}
			
			// 出库日期
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				strcondition += " and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'";
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				strcondition += " and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'";
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				strcondition += " and car.upData >= '" + dayReportAssayVo.getUpData() + "'";
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				strcondition += " and car.upData <= '" + dayReportAssayVo.getUpData2() + "'";
			}
			
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				strcondition += " and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "";
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				strcondition += " and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'";
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				strcondition += " and cstom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'";
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				strcondition += " and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "";
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				strcondition += " and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "";
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				strcondition += " and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "";
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				strcondition += " and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "";
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				strcondition += " and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "";
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				strcondition += " and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'";
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				strcondition += " and car.upPerson is NULL";
			}
			executsql = "SELECT * FROM (" + str0 + publicsql + strcondition
					+ str2 + str4 + publicsql + strcondition + str6
					+ ") AS BB  ORDER BY BB.xs_car_sel_data asc";

		//}
		return disposeAssayResault(dayReportAssayVo, executsql);
	}

	//
	private String formatList(List<HashMap<String, String>> list) {
		int[] toteCounts = new int[list.size() + 1];
		StringBuffer sb = new StringBuffer("{\"rows\":[");
		for (HashMap<String, String> hashMap : list) {
			sb.append("{");
			Iterator it = hashMap.keySet().iterator();
			String temp = null;
			while (it.hasNext()) {
				temp = it.next().toString();
				sb.append("\"" + temp + "\":\"" + hashMap.get(temp) + "\",");
			}

			String s = sb.substring(0, sb.length() - 1);
			sb = new StringBuffer(s);
			sb.append("},");
		}
		String s1 = sb.substring(0, sb.length() - 1);
		sb = new StringBuffer(s1);
		sb.append("],\"total\":" + Integer.MAX_VALUE + "}");
		String source = sb.toString();

		String sbs = sb.toString().substring(sb.toString().indexOf("[") + 1,
				sb.toString().lastIndexOf("]")).replace("},", "}#");
		String[] s = sbs.toString().split("#");
		Set set = new TreeSet();
		String resaultstr = "";
		for (int i = 0; i < s.length; i++) {
			set.add(s[i]);
		}
		Object[] objs = set.toArray();
		List setlist = new ArrayList();
		for (int i = 0; i < objs.length; i++) {
			setlist.add(objs[i]);
		}
		java.util.Collections.sort(setlist);
		for (int i = 0; i < setlist.size(); i++) {
			resaultstr += "," + setlist.get(i);
		}
		resaultstr.substring(1);
		StringBuffer newstr = new StringBuffer("{\"rows\":[");
		newstr.append(resaultstr.substring(1));
		newstr.append("],\"total\":" + Integer.MAX_VALUE + "}");
		return newstr.toString();
	}

	/**
	 * 获取每月时间段的统计量
	 */
	
	public String doMonthreportAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List<HashMap<String, String>> list = new ArrayList();
		Object[] obj = null;
		
			StringBuilder str0 = new StringBuilder(" SELECT aa.xs_model_name,aa.xs_car_sel_data,SUM(dayCount),");
			StringBuilder publicsql = new StringBuilder(" SUM(dayCount) AS TOTAL FROM ( ");
			publicsql.append(" SELECT DATE_FORMAT(xcsi.xs_car_sel_data,'%y-%m') AS xs_car_sel_data, xcm.xs_model_name, COUNT(*) AS dayCount,custom.xs_custom_name ");
			publicsql.append(" FROM xs_car_sell_info xcsi ");
			publicsql.append(" JOIN xs_car_info car   ON car.xs_car_id = xcsi.xs_car_id ");
			publicsql.append(" JOIN xs_sell_flow_control xsfc   ON xsfc.xsfcontrol_document = xcsi.sell_code   AND car.xs_car_id = xsfc.xsfcontrol_car_id   AND xsfc.xsfcontrol_code = '"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6+"' ");
			publicsql.append(" JOIN xs_car_model xcm   ON xcm.xs_model_id = car.xs_car_model_id ");
			publicsql.append(" LEFT JOIN xs_sell_retreat outs   ON outs.retreat_id = xcsi.out_id ");
			publicsql.append(" JOIN xs_custom_info custom   ON xcsi.custom_id = custom.custom_id ");
			publicsql.append(" LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar) ");
			publicsql.append(" LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id ");
			publicsql.append(" LEFT JOIN xs_sell_car_reserve G ON g.reserve_id = xcsi.reserve_id ");
			publicsql.append(" WHERE 1=1 ");
			
			
			StringBuilder str2 = new StringBuilder(" GROUP BY xcm.xs_model_name) AS aa GROUP BY aa.xs_model_name  UNION  ");
			StringBuilder str4 = new StringBuilder(" SELECT 'model','月销售分析汇总',SUM(dayCount),");
			StringBuilder str6 = new StringBuilder(" GROUP BY xcm.xs_model_name ) AS aa ");

			StringBuilder strcondition = new StringBuilder("");
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				strcondition.append(" and xcsi.enterprise_id="+dayReportAssayVo.getEnterprise_id());
			}
			// 添加查询条件
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
					strcondition.append(" and DATE_FORMAT(xcsi.xs_car_sel_data,'%Y-%m') >= '"+ dayReportAssayVo.getXsSellDate() + "'");
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
					strcondition.append(" and DATE_FORMAT(xcsi.xs_car_sel_data,'%Y-%m') <= '"+ dayReportAssayVo.getXsSellDate2() + "'");
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				strcondition.append(" and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'" );
			}
		
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				strcondition.append(" and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'");
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				strcondition.append(" and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'");
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				strcondition.append(" and car.upData >= '" + dayReportAssayVo.getUpData() + "'");
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				strcondition.append(" and car.upData <= '" + dayReportAssayVo.getUpData2() + "'");
			}
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				strcondition.append(" and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "");
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				strcondition.append(" and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'");
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				strcondition.append(" and custom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'");
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				strcondition.append(" and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "");
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				strcondition.append(" and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "");
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				strcondition.append(" and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "");
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				strcondition.append(" and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "");
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				strcondition.append(" and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "");
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				strcondition.append(" and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'");
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				strcondition.append(" and car.upPerson is NULL");
			}
			//
			StringBuilder executsql = new StringBuilder("");
			executsql.append("SELECT * FROM (" + str0 + publicsql + strcondition
					+ str2 + str4 + publicsql + strcondition + str6
					+ ") AS BB ORDER BY BB.xs_car_sel_data ");

		//}// if
		return disposeAssayResault(dayReportAssayVo, executsql.toString());
	}

	/**
	 * 获取销售方式对应的车型统计数量
	 */
	
	public String doSellwayAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		Object[] obj = null;
		String executsql = "";
		/*if (dayReportAssayVo.getXsSellDate() != null
				&& !dayReportAssayVo.getXsSellDate().equals("")) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");*/
			String str0 = " SELECT aa.xs_model_name,aa.xs_car_sel_type,";
			String publicsql = " SUM(dayCount) AS TOTAL FROM ("
					+ " SELECT xcm.xs_model_name,xsch.datavalue AS xs_car_sel_type, COUNT(*) AS dayCount"
					+ " FROM xs_car_sell_info xcsi "
					+ " JOIN xs_car_info car   ON car.xs_car_id = xcsi.xs_car_id  "
					+ "  JOIN xs_sell_flow_control xsfc   ON xsfc.xsfcontrol_document = xcsi.sell_code   AND car.xs_car_id = xsfc.xsfcontrol_car_id   AND xsfc.xsfcontrol_code ='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6+"' "
					+ "	 JOIN xs_car_model xcm   ON xcm.xs_model_id = car.xs_car_model_id"
					+ "	LEFT JOIN xs_sell_retreat outs   ON outs.retreat_id = xcsi.out_id"
					+ "	 JOIN xs_custom_info custom   ON xcsi.custom_id = custom.custom_id"
					+ "	LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar)"
					+ "	LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id"
					+ "	LEFT JOIN xs_sell_car_reserve G ON g.reserve_id=xcsi.reserve_id"
					+ "	LEFT JOIN xs_childdictionary xsch ON xcsi.xs_car_sel_type = xsch.child_id "
					+ " WHERE 1=1  ";
			String str2 = " GROUP BY xcm.xs_model_name ) AS aa GROUP BY aa.xs_model_name"
					+ "  UNION ";
			String str4 = " SELECT 'MODEL','销售类型分析汇总',";

			String str6 = " GROUP BY xcm.xs_model_name) AS aa ";

			String strcondition = "";
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				strcondition += " and xcsi.enterprise_id="+dayReportAssayVo.getEnterprise_id();
			}
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
					strcondition += " and DATE(xcsi.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'";
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
					strcondition += " and DATE(xcsi.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'";
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				strcondition += " and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'" ;
			}
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				strcondition += " and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'";
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				strcondition += " and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'";
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				strcondition += " and car.upData >= '" + dayReportAssayVo.getUpData() + "'";
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				strcondition += " and car.upData <= '" + dayReportAssayVo.getUpData2() + "'";
			}
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				strcondition += " and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "";
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				strcondition += " and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'";
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				strcondition += " and cstom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'";
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				strcondition += " and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "";
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				strcondition += " and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "";
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				strcondition += " and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "";
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				strcondition += " and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "";
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				strcondition += " and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "";
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				strcondition += " and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'";
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				strcondition += " and car.upPerson is NULL";
			}
			//
			executsql = "SELECT * FROM (" + str0 + publicsql + strcondition
					+ str2 + str4 + publicsql + strcondition + str6
					+ ") AS BB ORDER BY BB.xs_car_sel_type ";
		//}// if

		return disposeAssayResault(dayReportAssayVo, executsql);
	}

	/**
	 * 获取业务员 车型统计数量
	 */
	
	public String doWorkerSellAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		Object[] obj = null;
		String executsql = "";
		/*if (dayReportAssayVo.getXsSellDate() != null
				&& !dayReportAssayVo.getXsSellDate().equals("")) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");*/
			String str0 = " SELECT aa.xs_model_name,aa.STF_NAME,";
			String publicsql = " SUM(dayCount) AS TOTAL FROM ("
					+ " SELECT xsch.STF_NAME AS STF_NAME,xcm.xs_model_name, COUNT(*) AS dayCount"
					+ " FROM xs_car_sell_info xcsi "
					+ "	JOIN xs_car_info car   ON car.xs_car_id = xcsi.xs_car_id"
					+ "	 JOIN xs_sell_flow_control xsfc   ON xsfc.xsfcontrol_document = xcsi.sell_code   AND car.xs_car_id = xsfc.xsfcontrol_car_id   AND xsfc.xsfcontrol_code ='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6+"' "
					+ "	 JOIN xs_car_model xcm   ON xcm.xs_model_id = car.xs_car_model_id"
					+ "	LEFT JOIN xs_sell_retreat outs   ON outs.retreat_id = xcsi.out_id"
					+ "	 JOIN xs_custom_info custom   ON xcsi.custom_id = custom.custom_id"
					+ "	LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar)"
					+ "	LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id"
					+ "	LEFT JOIN xs_sell_car_reserve G ON g.reserve_id=xcsi.reserve_id"
					+ "	LEFT JOIN bas_stuff xsch ON xcsi.xs_car_stf_id = xsch.stf_id "
					+ " WHERE 1=1  ";
			String str2 = " GROUP BY xcm.xs_model_name ) AS aa GROUP BY aa.xs_model_name"
					+ "  UNION ";
			String str4 = " SELECT 'model','【业务员销售分析汇总】',";

			String str6 = " GROUP BY xcm.xs_model_name ) AS aa ";

			String strcondition = "";
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				strcondition += " and xcsi.enterprise_id="+dayReportAssayVo.getEnterprise_id();
			}
			// 添加查询条件
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
					strcondition += " and DATE(xcsi.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'";
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
					strcondition += " and DATE(xcsi.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'";
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				strcondition += " and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'" ;
			}
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				strcondition += " and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'";
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				strcondition += " and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'";
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				strcondition += " and car.upData >= '" + dayReportAssayVo.getUpData() + "'";
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				strcondition += " and car.upData <= '" + dayReportAssayVo.getUpData2() + "'";
			}
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				strcondition += " and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "";
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				strcondition += " and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'";
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				strcondition += " and cstom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'";
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				strcondition += " and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "";
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				strcondition += " and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "";
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				strcondition += " and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "";
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				strcondition += " and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "";
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				strcondition += " and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "";
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				strcondition += " and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'";
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				strcondition += " and car.upPerson is NULL";
			}
			//
			executsql = "SELECT * FROM (" + str0 + publicsql + strcondition
					+ str2 + str4 + publicsql + strcondition + str6
					+ ") AS BB ORDER BY BB.STF_NAME DESC";

		//}// if

		return disposeAssayResault(dayReportAssayVo, executsql);
	}

	/** 销售部门分析 */
	
	public String doDeptSellAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		Object[] obj = null;
		String executsql = "";
		/*if (dayReportAssayVo.getXsSellDate() != null
				&& !dayReportAssayVo.getXsSellDate().equals("")) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");*/
			String str0 = " SELECT aa.xs_model_name,aa.DEPT_NAME,";
			String publicsql = " SUM(dayCount) AS TOTAL FROM ("
					+ " SELECT dept.DEPT_NAME AS DEPT_NAME,xcm.xs_model_name, COUNT(*) AS dayCount"
					+ " FROM xs_car_sell_info xcsi "
					+ " JOIN xs_car_info car ON car.xs_car_id = xcsi.xs_car_id"
					+ "	 JOIN xs_sell_flow_control xsfc   ON xsfc.xsfcontrol_document = xcsi.sell_code   AND car.xs_car_id = xsfc.xsfcontrol_car_id   AND xsfc.xsfcontrol_code = '"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6+"'    "
					+ "  JOIN xs_car_model xcm ON xcm.xs_model_id=car.xs_car_model_id "
					+ " LEFT JOIN bas_stuff xsch ON xcsi.xs_car_stf_id = xsch.stf_id "
					+ " LEFT JOIN xs_sell_retreat outs ON outs.retreat_id = xcsi.out_id"
					+ "  JOIN xs_custom_info cstom ON cstom.custom_id = xcsi.custom_id"
					+ " LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id"
					+ " LEFT JOIN bas_dept dept ON  dept.DEPT_ID= xsch.DEPT_ID "
					+ " LEFT JOIN xs_sell_car_reserve G ON g.reserve_id=xcsi.reserve_id "
					+ " LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar)"
					+ " WHERE 1=1  ";
			String str2 = " GROUP BY xcm.xs_model_name ) AS aa GROUP BY aa.xs_model_name"
					+ "  UNION ";
			String str4 = " SELECT 'model','销售部门汇总',";

			String str6 = " GROUP BY xcm.xs_model_name ) AS aa ";

			String strcondition = "";
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				strcondition += " and xcsi.enterprise_id="+dayReportAssayVo.getEnterprise_id();
			}
			// 添加查询条件
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
					strcondition += " and DATE(xcsi.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'";
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
					strcondition += " and DATE(xcsi.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'";
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				strcondition += " and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'" ;
			}
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				strcondition += " and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'";
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				strcondition += " and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'";
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				strcondition += " and car.upData >= '" + dayReportAssayVo.getUpData() + "'";
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				strcondition += " and car.upData <= '" + dayReportAssayVo.getUpData2() + "'";
			}
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				strcondition += " and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "";
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				strcondition += " and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'";
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				strcondition += " and cstom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'";
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				strcondition += " and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "";
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				strcondition += " and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "";
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				strcondition += " and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "";
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				strcondition += " and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "";
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				strcondition += " and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "";
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				strcondition += " and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'";
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				strcondition += " and car.upPerson is NULL";
			}
			//
			executsql = "SELECT * FROM (" + str0 + publicsql + strcondition
					+ str2 + str4 + publicsql + strcondition + str6
					+ ") AS BB ORDER BY BB.DEPT_NAME";

		//}// if

		return disposeAssayResault(dayReportAssayVo, executsql);
	}

	/** 
	 * 销售班组分析
	 */
	
	public String doSellTeamsAssay(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		Object[] obj = null;
			StringBuilder publicsql = new StringBuilder("");
			publicsql.append(" SUM(dayCount) AS TOTAL FROM ( " );
			publicsql.append(" SELECT IF(ch.datavalue IS NOT NULL,ch.datavalue,'其他') AS teams,xcm.xs_model_name, COUNT(*) AS dayCount " );
			publicsql.append(" FROM xs_car_sell_info xcsi " );
			publicsql.append(" JOIN xs_custom_info cstom ON cstom.custom_id = xcsi.custom_id  " );
			publicsql.append(" JOIN xs_car_info car ON car.xs_car_id=xcsi.xs_car_id " );
			publicsql.append(" JOIN xs_sell_flow_control xsfc   ON xsfc.xsfcontrol_document = xcsi.sell_code " );
			publicsql.append(" AND car.xs_car_id = xsfc.xsfcontrol_car_id " );
			publicsql.append(" AND xsfc.xsfcontrol_code ='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6+"'  " );
			publicsql.append(" JOIN xs_car_model xcm ON xcm.xs_model_id=car.xs_car_model_id  " );
			publicsql.append(" LEFT JOIN bas_stuff xsch ON xcsi.xs_car_stf_id = xsch.stf_id  " );
			publicsql.append(" LEFT JOIN xs_childdictionary ch ON xsch.REPGRP_ID2 =ch.child_id  " );
			publicsql.append(" LEFT JOIN xs_sell_retreat outs ON outs.retreat_id = xcsi.out_id  " );
			publicsql.append(" LEFT JOIN xs_sell_cover cover ON cover.xs_car_sel_id = xcsi.xs_car_sel_id  " );
			publicsql.append(" LEFT JOIN xs_sell_car_reserve G ON g.reserve_id = xcsi.reserve_id  " );
			publicsql.append(" LEFT JOIN xs_sell_exitCar ecar ON xcsi.xs_car_sel_id = ecar.xs_car_sel_id AND xcsi.xs_car_sel_id NOT IN(SELECT xs_car_sel_id FROM xs_sell_exitCar)  " );
			publicsql.append(" WHERE 1=1  " );
			
			if(dayReportAssayVo.getEnterprise_id()!=null && !dayReportAssayVo.getEnterprise_id().equals("")){
				publicsql.append(" and xcsi.enterprise_id="+dayReportAssayVo.getEnterprise_id());
			}
			// 添加查询条件
			if (dayReportAssayVo.getXsSellDate() != null
					&& !dayReportAssayVo.getXsSellDate().equals("")) {
				
				publicsql.append(" and DATE(xcsi.xs_car_sel_data) >= '"
							+ dayReportAssayVo.getXsSellDate() + "'");
				} 
			if (dayReportAssayVo.getXsSellDate2() != null
					&& !dayReportAssayVo.getXsSellDate2().equals("")) {
			
				publicsql.append(" and DATE(xcsi.xs_car_sel_data) <='"
							+ dayReportAssayVo.getXsSellDate2()+ "'");
				}
			if((dayReportAssayVo.getXsSellDate() == null
					||dayReportAssayVo.getXsSellDate().equals(""))&&(dayReportAssayVo.getXsSellDate2()== null
					||dayReportAssayVo.getXsSellDate2().equals(""))){
		
				publicsql.append(" and DATE(xcsi.xs_car_sel_data) between " +
						" '" +FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,dayReportAssayVo.getEnterprise_id()).getCiValue()))+ "'" +
					" AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'") ;
			}
			if (dayReportAssayVo.getRetreatDate() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate())) {	
				publicsql.append(" and outs.retreat_date >= '" + dayReportAssayVo.getRetreatDate() + "'");
			} 
			if (dayReportAssayVo.getRetreatDate2() != null
					&& !"".equals(dayReportAssayVo.getRetreatDate2())) {	
				publicsql.append( " and outs.retreat_date <= '" + dayReportAssayVo.getRetreatDate2() + "'");
			}
			if (dayReportAssayVo.getUpData() != null
					&& !"".equals(dayReportAssayVo.getUpData())) {	
				publicsql.append( " and car.upData >= '" + dayReportAssayVo.getUpData() + "'");
			} 
			if (dayReportAssayVo.getUpData2() != null
					&& !"".equals(dayReportAssayVo.getUpData2())) {	
				publicsql.append(" and car.upData <= '" + dayReportAssayVo.getUpData2() + "'");
			}
			// 分销状态
			if (dayReportAssayVo.getXsCarDistributState() != null
					&& !dayReportAssayVo.getXsCarDistributState().equals("")) {
				publicsql.append(" and car.xs_car_distribut_state="
						+ dayReportAssayVo.getXsCarDistributState() + "");
			}
			if (dayReportAssayVo.getXsCarVinNumber() != null
					&& !dayReportAssayVo.getXsCarVinNumber().equals("")) {
				publicsql.append(" and car.xs_Car_Vin_Number like'%"
						+ dayReportAssayVo.getXsCarVinNumber() + "%'");
			}
			if (dayReportAssayVo.getXsCustomName() != null
					&& !dayReportAssayVo.getXsCustomName().equals("")) {
				publicsql.append(" and cstom.xs_custom_name like'%"
						+ dayReportAssayVo.getXsCustomName() + "%'");
			}
			if (dayReportAssayVo.getXsDistributorId() != null
					&& !dayReportAssayVo.getXsDistributorId().equals("")) {
				publicsql.append(" and car.xs_Distributor_Id ="
						+ dayReportAssayVo.getXsDistributorId() + "");
			}
			if (dayReportAssayVo.getConsultDegree() != null
					&& !dayReportAssayVo.getConsultDegree().equals("")) {
				publicsql.append(" and cover.consult_Degree ="
						+ dayReportAssayVo.getConsultDegree() + "");
			}
			if (dayReportAssayVo.getCarBrand() != null
					&& !dayReportAssayVo.getCarBrand().equals("")) {
				publicsql.append(" and car.xs_car_brand ="
						+ dayReportAssayVo.getCarBrand() + "");
			}
			if (dayReportAssayVo.getCarModel() != null
					&& !dayReportAssayVo.getCarModel().equals("")) {
				publicsql.append(" and car.xs_car_model_id ="
						+ dayReportAssayVo.getCarModel() + "");
			}
			if (dayReportAssayVo.getCarType() != null
					&& !dayReportAssayVo.getCarType().equals("")) {
				publicsql.append(" and car.xs_car_type ="
						+ dayReportAssayVo.getCarType() + "");
			}
			if (dayReportAssayVo.getPact_code() != null
					&& !dayReportAssayVo.getPact_code().equals("")) {
				publicsql.append( " and g.pact_code like '%"
						+ dayReportAssayVo.getPact_code() + "%'");
			}
			if (dayReportAssayVo.getNoUp() != null
					&& !"".equals(dayReportAssayVo.getNoUp())
					&& "yes".equals(dayReportAssayVo.getNoUp())) {
				publicsql.append( " and car.upPerson is NULL");
			}
			//
			StringBuilder executsql = new StringBuilder("SELECT * FROM (" );
			executsql.append(" SELECT aa.xs_model_name,aa.teams,");
			executsql.append(publicsql);
			executsql.append(" GROUP BY xcm.xs_model_name ) AS aa GROUP BY aa.xs_model_name   UNION   ");
			executsql.append(" SELECT 'model','销售班组汇总',  ");
			executsql.append(publicsql);
			executsql.append(" GROUP BY xcm.xs_model_name) AS aa  ");
			executsql.append(") AS BB ORDER BY BB.teams");

		//}// if
		return disposeAssayResault(dayReportAssayVo, executsql.toString());
	}

	// 对分析结果处理返回
	public String disposeAssayResault(DayReportAssayVo dayReportAssayVo,
			String executsql) throws Exception {
		
		List<HashMap<String, String>> list = new ArrayList();
		List exeresault = dayReportAssayDao.createSQLQuery(executsql);
		Object[] robj = null;
		List tempList = new ArrayList();
		if (dayReportAssayVo.getColumnsTag() != null
				&& dayReportAssayVo.getColumnsTag() == true) {
			for (int i = 0; i < exeresault.size() - 1; i++) {
				robj = (Object[]) exeresault.get(i);
				HashMap<String, String> map = new HashMap();
				tempList.add(robj[0]!=null ? robj[0].toString() : "");
			}
			return tempList.toString();
		}
		String date1 = "";
		String date2 = "";
		int total = 0;
		for (int i = 0; i < exeresault.size() - 1; i++) {
			robj = (Object[]) exeresault.get(i);
			HashMap<String, String> map = new HashMap();
			// 判断两个时间是否相等
			date1 = robj[1]!=null ? robj[1].toString() : "";

			if (date1.equals(date2)) {
				HashMap<String, String> m = new HashMap<String, String>();
					if(list!=null && list.size()>0){
						m = list.get(list.size() - 1);
						m.put("carmodel" + robj[0]!=null ? robj[0].toString().replaceAll(" ", "") : "",
								robj[2].toString());
						total += Integer.parseInt(robj[2].toString());
						m.put("TOTAL", total + "");
						list.add(m);
					}
			} else {
				map.put("carmodel" + robj[0]!=null ? robj[0].toString().replaceAll(" ", "") : "",
						robj[2].toString());
				map.put("TOTAL", robj[2].toString());
				map.put("date", robj[1].toString());
				list.add(map);
				total = Integer.parseInt(robj[2].toString());
			}
			date2 = date1;

		}
		HashMap<String, String> map2 = new HashMap();
//		if(exeresault.size()-1>0){
			for (int i = 0; i < exeresault.size(); i++) {
				robj = (Object[]) exeresault.get(i);
				if (robj[2] != null && Integer.parseInt(robj[2].toString()) > 0) {
					map2.put("carmodel" + robj[0]!=null ? robj[0].toString().replaceAll(" ", "") : "",
							robj[2].toString());
					map2.put("TOTAL", robj[2].toString());
				}
			}
			map2.put("date", "【汇总】");
			list.add(map2);
//		}

		return formatList(list);
	}

	
	public Datagrid queryCarAndCustom(DayReportAssayVo dayReportAssayVo)
			throws Exception {

		return dayReportAssayDao.queryCarAndCustom(dayReportAssayVo);
	}

	
	public Datagrid getSellDayReport(DayReportAssayVo dayReportAssayVo)
			throws Exception {
		// TODO Auto-generated method stub
		return dayReportAssayDao.getSellDayReport(dayReportAssayVo);
	}

}
