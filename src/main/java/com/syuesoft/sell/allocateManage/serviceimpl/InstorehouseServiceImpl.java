package com.syuesoft.sell.allocateManage.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.dao.InstorehouseDao;
import com.syuesoft.sell.allocateManage.service.InstorehouseService;
import com.syuesoft.sell.allocateManage.vo.InstorehouseVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;
import com.syuesoft.util.FormatTime;

@Service("houseService")
public class InstorehouseServiceImpl implements InstorehouseService {
	private InstorehouseDao instDao;
	@Autowired
	private BaseTagDAO baseTagDAO;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	public InstorehouseDao getInstDao() {
		return instDao;
	}

	@Autowired
	public void setInstDao(InstorehouseDao instDao) {
		this.instDao = instDao;
	}

	
	public Datagrid queryInstorehouse(InstorehouseVo instorehouseVo)
			throws Exception {

		return instDao.queryInstorehouse(instorehouseVo);
	}

	
	public List queryInstorehouseSum(InstorehouseVo instorehouseVo)
			throws Exception {
		return instDao.queryInstorehouseSum(instorehouseVo);
	}

	
	public List doAssayColumns(InstorehouseVo instorehouseVo) throws Exception {
		String source="";
		if(instorehouseVo.getTabType()!=null&&"carColor".equals(instorehouseVo.getTabType())){
			source = getCarColor(instorehouseVo);
		}
		if(instorehouseVo.getTabType()!=null&&"dis".equals(instorehouseVo.getTabType())){
			source = getInforByDis(instorehouseVo);
		}
		if(instorehouseVo.getTabType()!=null&&"sellState".equals(instorehouseVo.getTabType())){
			source = getInforBySellState(instorehouseVo);
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

	
	public String getCarColor(InstorehouseVo instorehouseVo) throws Exception {

		Calendar can = Calendar.getInstance();

		StringBuffer sql1 = new StringBuffer();
		/*if (instorehouseVo.getInstorehouseDate() != null&&
				!"".equals(instorehouseVo.getInstorehouseDate())){

					sql1.append(" and DATE(bb.instorehouse_date) >= '"
							+ instorehouseVo.getInstorehouseDate()+ "'");
		}
		if (instorehouseVo.getInstorehouseDate2() != null&&
				!"".equals(instorehouseVo.getInstorehouseDate2())){

					sql1.append(" and DATE(bb.instorehouse_date) <= '"
							+ instorehouseVo.getInstorehouseDate2()+ "'");
		}
		if((instorehouseVo.getInstorehouseDate() == null
				||"".equals(instorehouseVo.getInstorehouseDate()))&&
				(instorehouseVo.getInstorehouseDate2() == null
				||"".equals(instorehouseVo.getInstorehouseDate2()))){
				sql1.append(" and DATE(bb.instorehouse_date) BETWEEN  " +
						"'" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 
								(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,instorehouseVo.getEnterprise_id()).getCiValue())) + "' " +
								" and '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
			}
			

			if (instorehouseVo.getStfId() != null
					&& !"".equals(instorehouseVo.getStfId())) {
				sql1.append(" and bb.STF_ID=" + instorehouseVo.getStfId());

			}*/
		
		//企业编号
		if(instorehouseVo.getEnterprise_id()!=null && !instorehouseVo.getEnterprise_id().equals("")){
			sql1.append(" and bb.enterprise_id ="+instorehouseVo.getEnterprise_id()+"");
		}
		StringBuffer sql = null;
		/*if (instorehouseVo.getInstorehouseDate() != null
				&& !instorehouseVo.getInstorehouseDate().equals("")) {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");*/
			sql = new StringBuffer(
					"SELECT *  FROM (SELECT dd.xs_model_name,"
							+ "(SELECT datavalue FROM xs_childdictionary ch WHERE  ch.child_id =cc.xs_car_color) AS color,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa  "
							+ "INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ "INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ "  INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ " INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id "
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID  "
							+ " "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1+ "'   and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+") " +
							"and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
						" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
						" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
						" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
						""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
								"AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" +
							" ");
			
			sql.append(sql1);
			sql	.append(" GROUP BY cc.xs_car_model_id    "
							+ "UNION SELECT     'model', '【车辆库存量汇总】',"
							+ "   SUM(NUM)  AS TOTAL"
							+ "  FROM ( SELECT dd.xs_model_name,(SELECT datavalue FROM xs_childdictionary ch WHERE  ch.child_id =cc.xs_car_color) AS color,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa "
							+ " INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ " INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ " INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ "INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id"
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1+ "'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+") " +
							" and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
							" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
							" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
							"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
							""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
							" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" +
							" ");
		
			sql.append(sql1);
			sql.append(" GROUP BY cc.xs_car_model_id )AS DD) AS ss ORDER BY ss.color DESC");

		//}
		return diAssayResault(instorehouseVo, sql);
	}

	public String diAssayResault(InstorehouseVo instorehouseVo, StringBuffer sql)
			throws Exception {
		List<HashMap<String, String>> list = new ArrayList();
		List exeresault = instDao.createSQLQuery(sql.toString());
		Object[] robj = null;
		List tempList = new ArrayList();
		if (instorehouseVo.getColumnsTag() != null
				&& instorehouseVo.getColumnsTag() == true) {
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
			date1 = robj[1].toString();

			if (date1.equals(date2)) {
				HashMap<String, String> m = new HashMap<String, String>();
				m = list.get(list.size() - 1);
				m.put("carmodel" + robj[0].toString().replaceAll(" ", ""),
						robj[2].toString());
				total += Integer.parseInt(robj[2].toString());
				m.put("TOTAL", total + "");
				list.add(m);
			} else {
				map.put("carmodel" + robj[0].toString().replaceAll(" ", ""),
						robj[2].toString());
				map.put("TOTAL", robj[2].toString());
				map.put("date", robj[1].toString());
				list.add(map);
				total = Integer.parseInt(robj[2].toString());
			}
			date2 = date1;

		}
		HashMap<String, String> map2 = new HashMap();
		for (int i = 0; i < exeresault.size(); i++) {
			robj = (Object[]) exeresault.get(i);
			if (robj[2] != null && Integer.parseInt(robj[2].toString()) > 0) {
				map2.put("carmodel" + robj[0].toString().replaceAll(" ", ""),
						robj[2].toString());
				map2.put("TOTAL", robj[2].toString());
			}
		}
		map2.put("date", "【汇总】");
		list.add(map2);

		return formatList(list);
	}

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

	
	public String getInforByDis(InstorehouseVo instorehouseVo) throws Exception {
		
		StringBuffer sql1 = new StringBuffer();
		/*if (instorehouseVo.getInstorehouseDate() != null
				&& !instorehouseVo.getInstorehouseDate().equals("")) {
			String[] xsSellDate = instorehouseVo.getInstorehouseDate().split(
					",");
			if (xsSellDate.length > 1 && xsSellDate[0] != null
					&& xsSellDate[1] != null) {
				sql1.append(" and DATE(bb.instorehouse_date) BETWEEN DATE('"
						+ xsSellDate[0] + "') AND DATE('" + xsSellDate[1]
						+ "')");
			} else {
				if (instorehouseVo.getInstorehouseDate().length() > 10) {
					sql1.append(" and DATE(bb.instorehouse_date) >= '"
							+ xsSellDate[0] + "'");
				} else {
					sql1.append(" and DATE(bb.instorehouse_date) <='"
							+ xsSellDate[0] + "'");
				}

			}
			if (instorehouseVo.getStfId() != null
					&& !"".equals(instorehouseVo.getStfId())) {
				sql1.append(" and bb.STF_ID=" + instorehouseVo.getStfId());

			}
		}*/
		if(instorehouseVo.getEnterprise_id()!=null && !instorehouseVo.getEnterprise_id().equals("")){
			sql1.append(" and bb.enterprise_id ="+instorehouseVo.getEnterprise_id()+"");
		}
		StringBuffer sql = null;
		/*if (instorehouseVo.getInstorehouseDate() != null
				&& !instorehouseVo.getInstorehouseDate().equals("")) {
		//SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
*/			sql = new StringBuffer(
					"SELECT *  FROM (SELECT dd.xs_model_name,"
							+ "( CASE WHEN ee.xs_distributor_name IS NULL THEN '本公司' ELSE ee.xs_distributor_name END )AS xs_distributor_name,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa  "
							+ "INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ "INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ "  INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ " INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id "
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID  "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1
							+ "'   and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+") " +
							"and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
							" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
							" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
							"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
							""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
									" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" );
			sql.append(sql1);
			sql	.append(" GROUP BY cc.xs_car_model_id    "
							+ "UNION SELECT     'model', '【分销商库存量汇总】',"
							+ "   SUM(NUM)  AS TOTAL"
							+ "  FROM ( SELECT dd.xs_model_name,( CASE WHEN ee.xs_distributor_name IS NULL THEN '本公司' ELSE ee.xs_distributor_name END )AS xs_distributor_name,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa "
							+ " INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ " INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ " INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ "INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id"
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1
							+ "'   and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							"and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
							" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
							" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
							"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
							""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
									" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" );
			sql.append(sql1);
			sql.append(" GROUP BY cc.xs_car_model_id )AS DD) AS ss ORDER BY ss.xs_distributor_name DESC ");

		//}
		return diAssayResault(instorehouseVo, sql);
	}

	
	public String getInforBySellState(InstorehouseVo instorehouseVo)
			throws Exception {

		StringBuffer sql1 = new StringBuffer();
		/*if (instorehouseVo.getInstorehouseDate() != null
				&& !instorehouseVo.getInstorehouseDate().equals("")) {
			String[] xsSellDate = instorehouseVo.getInstorehouseDate().split(
					",");
			if (xsSellDate.length > 1 && xsSellDate[0] != null
					&& xsSellDate[1] != null) {
				sql1.append(" and DATE(bb.instorehouse_date) BETWEEN DATE('"
						+ xsSellDate[0] + "') AND DATE('" + xsSellDate[1]
						+ "')");
			} else {
				if (instorehouseVo.getInstorehouseDate().length() > 10) {
					sql1.append(" and DATE(bb.instorehouse_date) >= '"
							+ xsSellDate[0] + "'");
				} else {
					sql1.append(" and DATE(bb.instorehouse_date) <='"
							+ xsSellDate[0] + "'");
				}

			}
			if (instorehouseVo.getStfId() != null
					&& !"".equals(instorehouseVo.getStfId())) {
				sql1.append(" and bb.STF_ID=" + instorehouseVo.getStfId());

			}
		}*/
		if(instorehouseVo.getEnterprise_id()!=null && !instorehouseVo.getEnterprise_id().equals("")){
			sql1.append(" and bb.enterprise_id ="+instorehouseVo.getEnterprise_id()+"");
		}
		StringBuffer sql = null;
		/*if (instorehouseVo.getInstorehouseDate() != null
				&& !instorehouseVo.getInstorehouseDate().equals("")) {
		//SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
*/			sql = new StringBuffer(
					"SELECT *  FROM (SELECT dd.xs_model_name,"
							+ "(SELECT datavalue FROM xs_childdictionary child WHERE child.child_id=cc.xs_car_sell_state) AS sellState,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa  "
							+ "INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ "INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ "  INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ " INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id "
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID  "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1
							+ "'   and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+") " +
							"and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
							" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
							" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
							"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
							""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
									" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))" );
			sql.append(sql1);
			sql	.append(" GROUP BY cc.xs_car_model_id    "
							+ "UNION SELECT     'model', '【车辆销售状态汇总】',"
							+ "   SUM(NUM)  AS TOTAL"
							+ "  FROM ( SELECT dd.xs_model_name,( CASE WHEN ee.xs_distributor_name IS NULL THEN '本公司' ELSE ee.xs_distributor_name END )AS xs_distributor_name,COUNT(*)AS NUM"
							+ " FROM xs_sell_instorehouse_del aa "
							+ " INNER JOIN  xs_sell_instorehouse bb ON bb.instorehouse_id=aa.instorehouse_id "
							+ " INNER JOIN  xs_sell_flow_control flow ON  flow.xsfcontrol_document=bb.instorehouse_code "
							+ " AND flow.xsfcontrol_car_id=aa.xs_car_id "
							+ "INNER JOIN xs_car_info cc ON cc.xs_car_id=aa.xs_car_id"
							+ " INNER JOIN xs_car_model dd ON  dd.xs_model_id=cc.xs_car_model_id"
							+ "  LEFT OUTER JOIN xs_distributor_info ee ON ee.xs_distributor_id=cc.xs_distributor_id "
							+ "INNER JOIN xs_supplier_info hh ON hh.xs_supplier_id=bb.xs_supplier_id"
							+ " JOIN bas_stuff ii ON ii.STF_ID=bb.STF_ID "
							+ "  WHERE bb.examine_state= ( SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent WHERE child.parent_id =  parent.parent_id "
							+ " AND parent.dataKey='"
							+ Contstants.BASE_SELL.ADUIT
							+ "' AND   child.dataKey='"
							+ Contstants.BASE_SELL.ADUIT1
							+ "'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+") " +
							"and aa.details_id not in(SELECT re.details_id FROM xs_sell_retreat re" +
							" WHERE re.examine=(SELECT child.child_id FROM xs_childdictionary child, xs_parentdictionary parent " +
							" WHERE child.parent_id =  parent.parent_id AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
							"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id = "+instorehouseVo.getEnterprise_id()+")" +
							" AND re.instorehouse_type IN("+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.BACK,instorehouseVo.getEnterprise_id())+"," +
							""+baseTagDAO.getChildId( Contstants.INSTORESTYLE.PARENTINSTORE,Contstants.INSTORESTYLE.OUT,instorehouseVo.getEnterprise_id())+" )" +
									" AND  re.retreat_id NOT IN (  SELECT xcsi.out_id FROM xs_car_sell_info xcsi " +
						"INNER JOIN xs_sell_exitCar xse ON xse.xs_car_sel_id=xcsi.xs_car_sel_id " +
						"  WHERE xse.examine=(SELECT child.child_id FROM xs_childdictionary child, " +
						"xs_parentdictionary parent  WHERE child.parent_id =  parent.parent_id AND" +
						" parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND   " +
						"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' AND " +
						"child.enterprise_id="+instorehouseVo.getEnterprise_id()+")))");
			sql.append(sql1);
			sql.append(" GROUP BY cc.xs_car_model_id )AS DD) AS ss ORDER BY ss.sellState DESC ");

		//}
		return diAssayResault(instorehouseVo, sql);
	}
}
