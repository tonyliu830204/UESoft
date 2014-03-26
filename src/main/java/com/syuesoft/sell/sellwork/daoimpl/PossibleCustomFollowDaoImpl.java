package com.syuesoft.sell.sellwork.daoimpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsSellCustomTracing;
import com.syuesoft.sell.sellwork.dao.PossibleCustomFollowDao;
import com.syuesoft.sell.sellwork.vo.PossibleCustomFollowVo;
import com.syuesoft.util.FormatTime;

@Repository("possibleCustomFollowDao")
public class PossibleCustomFollowDaoImpl extends BaseDaoImpl implements
		PossibleCustomFollowDao {
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Autowired
	private BaseTagDAO  baseTagDAO;
	/**
	 * 获取客户档案信息 子项
	 */
	@SuppressWarnings("unchecked")
	
	public List findCustomInfor(PossibleCustomFollowVo possibleCustomFollowVo,
			BaseTagDAO baseTagDAO) throws Exception {
		StringBuffer sql = new StringBuffer(
						" SELECT  "
						+ " a.custom_id,"
						+ " a.xs_custom_name,"
						+ " a.xs_custom_inputdata,"
						+ " a.xs_custom_property,"
						+ " (SELECT g.datavalue FROM xs_childdictionary g WHERE g.child_id = a.xs_custom_property ) AS property ,"
						+ " a.xs_custom_hide_level ,"
						+ " d.xs_leva_name,"
						+ " d.xs_leva_jiange,"
						+ " a.xs_custom_code,"
						+ " rs.tracing_date," 
						+ " a.xs_custom_telephone"
						+ " FROM "
						+ " xs_custom_info a " +
								" JOIN xs_custom_leva d ON a.xs_custom_hide_level = d.xs_leva_id " +
								"LEFT JOIN (SELECT ss.* FROM (" +
								"SELECT b.custom_id,b.tracing_date,b.car_model," +
								"b.Buy_Probability,b.Obstacle,b.Need_State," +
								"b.Interview_Date FROM xs_sell_custom_tracing b " +
								"ORDER BY  b.tracing_date DESC) ss GROUP BY ss.custom_id" +
								") rs  ON a.custom_id = rs.custom_id "
						+ " WHERE a.xs_custom_hide_level="
						+ possibleCustomFollowVo.getXs_Custom_Hide_Level_Id()
						+ ""
						+ " AND a.xs_custom_deal="
						+ baseTagDAO.getChildId(
								Contstants.BASE_SELL.BASE_DEALSTATE,
								Contstants.BASE_SELL.SS,possibleCustomFollowVo.getEnterpriseId()) + "");

		//企业编号
		if (possibleCustomFollowVo.getEnterpriseId() != null
				&& !possibleCustomFollowVo.getEnterpriseId().equals("")) {
			sql.append(" and a.Enterprise_Id ="
					+ possibleCustomFollowVo.getEnterpriseId() + "");
		}
		//车品牌
		if (possibleCustomFollowVo.getCar_Model()!= null
				&& !"".equals(possibleCustomFollowVo.getCar_Model())) {
			sql.append(" and rs.car_model="
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getCar_Model().trim()) + "");
		}
		if (possibleCustomFollowVo.getXs_Custom_Name() != null
				&& !possibleCustomFollowVo.getXs_Custom_Name().equals("")) {
			sql.append(" and a.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Name().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Telephone() != null
				&& !possibleCustomFollowVo.getXs_Custom_Telephone().equals("")) {
			sql.append(" and a.xs_Custom_Telephone like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Telephone().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Name() != null
				&& !possibleCustomFollowVo.getXs_Custom_Name().equals("")) {
			sql.append(" and a.Xs_Custom_Name like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Name().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getCustom_Id() != null
				&& !possibleCustomFollowVo.getCustom_Id().equals("")) {
			sql.append(" and a.custom_id =" + possibleCustomFollowVo.getCustom_Id()
					+ "");
		}
		if (possibleCustomFollowVo.getXs_Custom_Deal() != null
				&& !possibleCustomFollowVo.getXs_Custom_Deal().equals("")) {
			sql.append( " and a.xs_Custom_Deal ="
					+ possibleCustomFollowVo.getXs_Custom_Deal() + "");
		}
		if (possibleCustomFollowVo.getStf_Id() != null
				&& !possibleCustomFollowVo.getStf_Id().equals("")) {
			sql.append(" and a.stf_id =" + possibleCustomFollowVo.getStf_Id() + "");
		}
		if (possibleCustomFollowVo.getXs_Contacts_Person() != null
				&& !possibleCustomFollowVo.getXs_Contacts_Person().equals("")) {
			sql.append( " and a.Xs_Contacts_Person like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Contacts_Person().trim()) + "%'");
		}
		// 成交几率  buy_probability
		String pro=formateRate(possibleCustomFollowVo.getBuy_Probability(), possibleCustomFollowVo.getBuy_Probabilitys(), possibleCustomFollowVo.getEnterpriseId());
		if(pro!=null){
			sql.append(" and rs.Buy_Probability in ("+pro+")");
		}
	
		if (possibleCustomFollowVo.getObstacle() != null
				&& !possibleCustomFollowVo.getObstacle().equals("")) {
			sql.append( " and rs.Obstacle =" + possibleCustomFollowVo.getObstacle()
					+ "");
		}
		// 是否跟踪
		if (possibleCustomFollowVo.getNeed_State() != null
				&& !possibleCustomFollowVo.getNeed_State().equals("")) {
			sql.append( " and rs.Need_State ="
					+ possibleCustomFollowVo.getNeed_State() + "");
		}
		//预约日期
		if (possibleCustomFollowVo.getInterview_Date() != null
				&& !possibleCustomFollowVo.getInterview_Date().equals("")) {
					sql.append(" and ( CASE WHEN rs.Interview_Date IS NOT NULL THEN rs.Interview_Date ELSE DATE_ADD(DATE(a.xs_custom_inputdata),INTERVAL d.xs_leva_jiange DAY) END ) >= '" +possibleCustomFollowVo.getInterview_Date() + "'");
		} 
		if (possibleCustomFollowVo.getInterview_Date2() != null
				&& !possibleCustomFollowVo.getInterview_Date2().equals("")) {
					sql.append(" and ( CASE WHEN rs.Interview_Date IS NOT NULL THEN rs.Interview_Date ELSE DATE_ADD(DATE(a.xs_custom_inputdata),INTERVAL d.xs_leva_jiange DAY) END ) <= '" +possibleCustomFollowVo.getInterview_Date2() + "'");
		}
		if((possibleCustomFollowVo.getInterview_Date() == null
				|| possibleCustomFollowVo.getInterview_Date().equals("")) 
				&& (possibleCustomFollowVo.getInterview_Date2() == null
				|| possibleCustomFollowVo.getInterview_Date2().equals(""))){
			sql.append(" and ( CASE WHEN rs.Interview_Date IS NOT NULL THEN rs.Interview_Date ELSE DATE_ADD(DATE(a.xs_custom_inputdata),INTERVAL d.xs_leva_jiange DAY) END ) BETWEEN '" + 
					FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)
					+ "' AND '"
					+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,-1) + "'" +
					"");
		}
		//跟踪日期 
		if (possibleCustomFollowVo.getTracing_Date() != null
				&& !possibleCustomFollowVo.getTracing_Date().equals("")) {
					sql.append(" and DATE(rs.Tracing_Date) >= '" +possibleCustomFollowVo.getTracing_Date() + "'");
				} 
		if (possibleCustomFollowVo.getTracing_Date2() != null
				&& !possibleCustomFollowVo.getTracing_Date2().equals("")) {
					sql.append(" and DATE(rs.Tracing_Date) <= '" +possibleCustomFollowVo.getTracing_Date2() + "'");
		}
		return createSQLQuery(sql.toString());
	}
	public String formateRate(String beginRate,String endRate, int enterpriseId) throws Exception{
		List list=null;
		if(beginRate!=null&&beginRate.length()>0&&endRate!=null&&endRate.length()>0){
			list=new ArrayList();
			beginRate=beginRate.replace("%", "");
			endRate=endRate.replace("%", "");
			int begin=Integer.parseInt(beginRate);
			int end=Integer.parseInt(endRate);
			int temp=end/10-begin/10-1;
			list.add(baseTagDAO.findChildId(begin+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
			for (int i = 0; i < temp; i++) {
				begin+=10;
				list.add(baseTagDAO.findChildId(begin+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
			}
			list.add(baseTagDAO.findChildId(end+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
		}else if(beginRate!=null&&beginRate.length()>0){
			list=new ArrayList();
			beginRate=beginRate.replace("%", "");
			int begin=Integer.parseInt(beginRate);
			int temp=10-begin/10-1;
			list.add(baseTagDAO.findChildId(begin+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
			for (int i = 0; i < temp; i++) {
				begin+=10;
				list.add(baseTagDAO.findChildId(begin+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
			}
			list.add(baseTagDAO.findChildId("100%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
		}else if(endRate!=null&&endRate.length()>0){
			list=new ArrayList();
			endRate=endRate.replace("%", "");
			int begin=0;
			int end=Integer.parseInt(endRate);
			int temp=end/10-1;
			for (int i = 0; i < temp; i++) {
				begin+=10;
				list.add(baseTagDAO.findChildId(begin+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
			}
			list.add(baseTagDAO.findChildId(end+"%", Contstants.BASE_SELL.BUYPROBABILITY, enterpriseId));
		}
		if(list!=null&&list.size()>0){
			return list.toString().replace("[", "").replace("]", "");
		}
		return null;

	}
	
	
	/**
	 * 获取级别 和 按级别统计的数量(父级)
	 */
	@SuppressWarnings("unchecked")
	
	public List findCustomInforCount(
			final PossibleCustomFollowVo possibleCustomFollowVo,
			BaseTagDAO baseTagDAO) throws Exception {

		/*StringBuffer sql0 = new StringBuffer(
				" SELECT  "
				+ " a.custom_id,"
				+ " a.xs_custom_name,"
				+ " a.xs_custom_inputdata,"
				+ " a.xs_custom_property,"
				+ " (SELECT g.datavalue FROM xs_childdictionary g WHERE g.child_id = a.xs_custom_property ) AS property ,"
				+ " a.xs_custom_hide_level ,"
				+ " d.xs_leva_name,"
				+ " d.xs_leva_jiange,"
				+ " a.xs_custom_code,"
				+ " rs.tracing_date," 
				+ " a.xs_custom_telephone"
				+ " FROM "
				+ " xs_custom_info a " +
						"LEFT JOIN (SELECT ss.* FROM (" +
						"SELECT b.custom_id,b.tracing_date,b.car_model," +
						"b.Buy_Probability,b.Obstacle,b.Need_State," +
						"b.Interview_Date FROM xs_sell_custom_tracing b " +
						"ORDER BY  b.tracing_date DESC) ss GROUP BY ss.custom_id" +
						") rs  ON a.custom_id = rs.custom_id "
				+ " JOIN xs_custom_leva d ON a.xs_custom_hide_level = d.xs_leva_id "
				+ " WHERE a.xs_custom_hide_level="
				+ possibleCustomFollowVo.getXs_Custom_Hide_Level_Id()
				+ ""
				+ " AND a.xs_custom_deal="
				+ baseTagDAO.getChildId(
						Contstants.BASE_SELL.BASE_DEALSTATE,
						Contstants.BASE_SELL.SS) + "");
		*/
		StringBuffer sql = new StringBuffer("SELECT COUNT(temp.s4) AS s4,s1,s2,s3,s5 FROM " +
				"(SELECT  a.custom_id AS s4, a.xs_custom_hide_level AS s1," +
				"  d.xs_leva_name AS s2, d.xs_leva_jiange AS s3," +
				"( CASE WHEN rs.Interview_Date IS NOT NULL THEN rs.Interview_Date" +
				" ELSE DATE_ADD(DATE(a.xs_custom_inputdata),INTERVAL d.xs_leva_jiange DAY) END )AS s5 " +
				" FROM xs_custom_info a " +
				" JOIN xs_custom_leva d ON a.xs_custom_hide_level = d.xs_leva_id" +
				"  LEFT JOIN (SELECT  ss.* FROM (SELECT  b.custom_id, b.tracing_date, " +
				"b.car_model,b.Buy_Probability,b.Obstacle,b.Need_State, b.Interview_Date" +
				"  FROM xs_sell_custom_tracing b" +
				"  ORDER BY b.tracing_date DESC) ss" +
				"  GROUP BY ss.custom_id) rs  ON a.custom_id = rs.custom_id " );
		sql.append(" WHERE  a.Xs_Custom_Deal="+baseTagDAO.getChildId(
							Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SS,possibleCustomFollowVo.getEnterpriseId())+""); ////潜在状态
		//企业编号
		if (possibleCustomFollowVo.getEnterpriseId()!= null
				&& !"".equals(possibleCustomFollowVo.getEnterpriseId())) {
			sql.append(" and a.enterprise_id="
					+ possibleCustomFollowVo.getEnterpriseId() + "");
		}
		if (possibleCustomFollowVo.getXs_Custom_Name() != null
				&& !possibleCustomFollowVo.getXs_Custom_Name().equals("")) {
			sql.append(" and a.Xs_Custom_Name like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Name().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Telephone() != null
				&& !possibleCustomFollowVo.getXs_Custom_Telephone().equals("")) {
			sql.append(" and a.Xs_Custom_Telephone like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Telephone().trim()) + "%'");
		}
		
		
		//跟踪日期 
		if (possibleCustomFollowVo.getTracing_Date() != null
				&& !possibleCustomFollowVo.getTracing_Date().equals("")) {
					sql.append(" and DATE(rs.tracing_date) >= '" +possibleCustomFollowVo.getTracing_Date() + "'");
		} 
		if (possibleCustomFollowVo.getTracing_Date2() != null
				&& !possibleCustomFollowVo.getTracing_Date2().equals("")) {
					sql.append(" and DATE(rs.tracing_date) <= '" +possibleCustomFollowVo.getTracing_Date2() + "'");
		}

		//车型
		if (possibleCustomFollowVo.getCar_Model()!= null
				&& !"".equals(possibleCustomFollowVo.getCar_Model())) {
			sql.append(" and rs.car_model="
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getCar_Model().trim()) + "");
		}
		//业务员
		if (possibleCustomFollowVo.getStf_Id()!= null
				&& !"".equals(possibleCustomFollowVo.getStf_Id())) {
			sql.append(" and a.stf_id="
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getStf_Id().trim()) + "");
		}
		//联系人
		if (possibleCustomFollowVo.getXs_Contacts_Person() != null
				&& !possibleCustomFollowVo.getXs_Contacts_Person().equals("")) {
			sql.append(" and a.xs_contacts_person like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Contacts_Person().trim()) + "%'");
		}
		//客户级别
		if (possibleCustomFollowVo.getXs_Custom_Hide_Level() != null
				&& !possibleCustomFollowVo.getXs_Custom_Hide_Level().equals("")) {
			sql.append(" and a.Xs_Custom_Hide_Level = "
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Hide_Level().trim()) + "");
		}
		//成交几率  
		//if(possibleCustomFollowVo.getBuy_Probability()!=null && !possibleCustomFollowVo.getBuy_Probability().equals("")){
			String pro=formateRate(possibleCustomFollowVo.getBuy_Probability(), possibleCustomFollowVo.getBuy_Probabilitys(), possibleCustomFollowVo.getEnterpriseId());
			if(pro!=null){
			sql.append(" and rs.Buy_Probability in ("+pro+")");
			}
		//障碍
		if (possibleCustomFollowVo.getObstacle()!= null
				&& !possibleCustomFollowVo.getObstacle().equals("")) {
			sql.append(" and rs.Obstacle = "
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getObstacle().trim()) + "");
		}
		
		sql.append(" ) temp  where 1=1");
		//预约日期
		if (possibleCustomFollowVo.getInterview_Date() != null
				&& !possibleCustomFollowVo.getInterview_Date().equals("")) {
					sql.append(" and  s5   >= '" +possibleCustomFollowVo.getInterview_Date() + "'");
		} 
		if (possibleCustomFollowVo.getInterview_Date2() != null
				&& !possibleCustomFollowVo.getInterview_Date2().equals("")) {
					sql.append("  and s5 <= '" +possibleCustomFollowVo.getInterview_Date2() + "'");
		}
		if((possibleCustomFollowVo.getInterview_Date() == null
				|| possibleCustomFollowVo.getInterview_Date().equals("")) 
				&& (possibleCustomFollowVo.getInterview_Date2() == null
				|| possibleCustomFollowVo.getInterview_Date2().equals(""))){
			sql.append("  and s5 BETWEEN '" + 
					 FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)
					+ "' AND '"
					+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,-1)+ "'" +
					"");
		}
		sql.append(" GROUP BY temp.s1 HAVING temp.s1 is not null ");
		return createSQLQuery(sql.toString());
	}

	/**
	 * 通过来电客流明细编号查询 潜在客户信息
	 */
	@SuppressWarnings("unchecked")
	
	public List findCustomById(
			final PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		StringBuffer sql = new StringBuffer(
				""
						+ "	SELECT A.tracing_id,A.custom_id, A.tracing_code, A.tracing_date,A.interview_date,"
						+ "A.tracing_day_number,A.tracing_address,A.ambience_, A.tracing_way, A.steer_trial, "
						+ "A.car_model,A.hinder_content, A.talk_title,A.tracing_summary,A.next_interview_date,"
						+ "A.examine_opinion,	A.examine_flag,	A.examine_date,	A.price_need,A.use_need,A.capability_need,"
						+ "A.colour_need,	A.caiModel_need,A.payment_way,A.predict_buy_date,A.buy_probability,A.obstacle,"
						+ "A.bespeak_date,A.need_state,A.need_type,A.remark,b.xs_custom_name,"
						+ "	(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.tracing_way)  AS tracing ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.ambience_)  AS ambience ,"
						+ "c.xs_model_name,b.xs_custom_hide_level,b.xs_custom_inputdata "
						+ "	FROM "
						+ "xs_sell_custom_tracing A "
						+ " JOIN xs_custom_info B ON a.custom_id=b.custom_id "
						+ "LEFT OUTER JOIN xs_car_model c ON a.car_model=c.xs_model_id"
						+ " where 1 = 1 ");
		if(possibleCustomFollowVo.getCustom_Id()!=null && !possibleCustomFollowVo.getCustom_Id().equals("")){
			sql.append(" AND A.custom_id="+possibleCustomFollowVo.getCustom_Id()+"");
		}
		return createSQLQuery(sql.toString());

	}

	
	//获取预约单号
	public int findById(int customid){
		List list = this.getHibernateTemplate().find("select reserveId from XsSellCarReserve where customId="+customid);
		int rid = 0;
		if(list.get(0)!=null){
			rid = Integer.parseInt(list.get(0).toString());
		}
		return rid;
	}

	/**
	 * 删除一条潜在客户记录
	 */
	
	public void deleteCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		XsSellCustomTracing xsSellCustomTracing = new XsSellCustomTracing();
		xsSellCustomTracing.setTracingId(Integer.parseInt(possibleCustomFollowVo.getTracing_Id()));
		delete(xsSellCustomTracing);
	}


	//查询跟踪客户信息
	
	public Datagrid getTzCustom(PossibleCustomFollowVo possibleCustomFollowVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		List<PossibleCustomFollowVo> list = new ArrayList<PossibleCustomFollowVo>();
		StringBuffer sql = new StringBuffer(" SELECT A.custom_id,a.xs_custom_name,a.STF_ID,c.STF_NAME,a.xs_custom_code," +
				"b.tracing_date,b.talk_title,b.obstacle," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.obstacle)  AS obstacles	," +
				"b.tracing_summary,B.next_interview_date,b.tracing_address," +
				"a.xs_custom_hide_level,D.xs_leva_name,a.xs_custom_telephone,B.caiModel_need,(select ww.xs_model_name from xs_car_model ww where ww.xs_model_id=B.caiModel_need )," +
				"b.colour_need ,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.colour_need )  AS colour," +
				"b.predict_buy_date,b.payment_way,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.payment_way)  AS payment," +
				"b.buy_probability,a.xs_custom_application,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.xs_custom_application)  AS application," +
				"b.capability_need,b.car_model,(select w.xs_model_name from xs_car_model w where w.xs_model_id=b.car_model )," +
				"a.xs_custom_inputdata,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.buy_probability)  AS probability," +
				"a.xs_custom_deal,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.xs_custom_deal)  AS deal," +
				"B.tracing_way," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =B.tracing_way)  AS tracing," +
				"a.xs_contacts_person,a.xs_custom_property,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.xs_custom_property)  AS property," +
				"    (SELECT  datavalue   FROM xs_childdictionary A  WHERE A.child_id =   b.buy_probability) AS probabilityName,dtl.lose_date	"+
				"FROM xs_custom_info a " +
				" JOIN xs_sell_custom_tracing b on a.custom_id=b.custom_id " +
				" LEFT JOIN bas_stuff c on c.STF_ID=a.STF_ID" +
				" LEFT JOIN xs_custom_leva D on d.xs_leva_id=a.xs_custom_hide_level " +
				" LEFT JOIN xs_sell_receiveRelation xsr on xsr.custom_id =   A.custom_id  " +
				" LEFT JOIN xs_sell_custom_salesman_detail dtl on dtl.xs_custom_salesman_detail_id = xsr.xs_custom_salesman_detail_id  where 1=1 ");
		
		//企业编号
		if (possibleCustomFollowVo.getEnterpriseId()!= null
				&& !"".equals(possibleCustomFollowVo.getEnterpriseId())) {
			sql.append(" and a.enterprise_id="
					+ possibleCustomFollowVo.getEnterpriseId() + "");
		}
		if (possibleCustomFollowVo.getContactsPerson() != null
				&& !"".equals(possibleCustomFollowVo.getContactsPerson())) {
			sql.append(" and a.xs_contacts_person like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getContactsPerson().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getStf_Id() != null
				&& !"".equals(possibleCustomFollowVo.getStf_Id())) {
			sql.append(" and a.STF_ID = '"
					+ possibleCustomFollowVo.getStf_Id() + "'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Deal() != null
				&& !"".equals(possibleCustomFollowVo.getXs_Custom_Deal())) {
			sql.append(" and a.xs_custom_deal = '"
					+ possibleCustomFollowVo.getXs_Custom_Deal() + "'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Hide_Level_Id() != null
				&& !"".equals(possibleCustomFollowVo.getXs_Custom_Hide_Level_Id())) {
			sql.append(" and a.xs_custom_hide_level = '"
					+ possibleCustomFollowVo.getXs_Custom_Hide_Level_Id() + "'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Name() != null
				&& !"".equals(possibleCustomFollowVo.getXs_Custom_Name())) {
			sql.append(" and a.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(possibleCustomFollowVo.getXs_Custom_Name().trim()) + "%'");
		}
		if (possibleCustomFollowVo.getCar_Model() != null
				&& !"".equals(possibleCustomFollowVo.getCar_Model())) {
			sql.append(" and b.car_model = '"
					+ possibleCustomFollowVo.getCar_Model() + "'");
		}
		if (possibleCustomFollowVo.getCustom_Property_Id() != null
				&& !"".equals(possibleCustomFollowVo.getCustom_Property_Id())) {
			sql.append(" and a.xs_custom_property = '"
					+ possibleCustomFollowVo.getCustom_Property_Id() + "'");
		}
		if (possibleCustomFollowVo.getXs_Custom_Telephone() != null
				&& !"".equals(possibleCustomFollowVo.getXs_Custom_Telephone())) {
			sql.append(" and a.xs_custom_telephone like '%"
					+ possibleCustomFollowVo.getXs_Custom_Telephone() + "%'");
		}
		if (possibleCustomFollowVo.getCai_Model_Need() != null
				&& !"".equals(possibleCustomFollowVo.getCai_Model_Need())) {
			sql.append(" and B.caiModel_need = '"
					+ possibleCustomFollowVo.getCai_Model_Need() + "'");
		}
		if (possibleCustomFollowVo.getBuy_Probability() != null
				&& !"".equals(possibleCustomFollowVo.getBuy_Probability())) {
			sql.append(" and b.buy_probability >= '"
					+ possibleCustomFollowVo.getBuy_Probability() + "'");
		}
		
		//预购日期
		if (possibleCustomFollowVo.getPredict_Buy_Date() != null
				&& !possibleCustomFollowVo.getPredict_Buy_Date().equals("")) {
			sql.append(" and DATE(b.predict_buy_date) >= '" +possibleCustomFollowVo.getPredict_Buy_Date() + "'");
		} 
		if (possibleCustomFollowVo.getPredict_Buy_Date2() != null
				&& !possibleCustomFollowVo.getPredict_Buy_Date2().equals("")) {
			sql.append(" and DATE(b.predict_buy_date) <= '" +possibleCustomFollowVo.getPredict_Buy_Date2() + "'");
		}
		//跟踪日期
		if(possibleCustomFollowVo.getFlag()!=null && possibleCustomFollowVo.getFlag()==true){
			sql.append(" and DATE(b.tracing_date) = '" +new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "'");
		}else{
			if (possibleCustomFollowVo.getTracing_Date() != null
					&& !possibleCustomFollowVo.getTracing_Date().equals("")) {
						sql.append(" and DATE(b.tracing_date) >= '" +possibleCustomFollowVo.getTracing_Date() + "'");
			} 
			if (possibleCustomFollowVo.getTracing_Date2() != null
					&& !possibleCustomFollowVo.getTracing_Date2().equals("")) {
						sql.append(" and DATE(b.tracing_date) <= '" +possibleCustomFollowVo.getTracing_Date2() + "'");
			}
			if((possibleCustomFollowVo.getTracing_Date() == null
					|| possibleCustomFollowVo.getTracing_Date().equals("")) 
					&& (possibleCustomFollowVo.getTracing_Date2() == null
					|| possibleCustomFollowVo.getTracing_Date2().equals(""))){
				sql.append(" and  DATE(b.tracing_date) " +
						"BETWEEN '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,possibleCustomFollowVo.getEnterpriseId()).getCiValue()))
						+ "' AND '" + FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
			}
		}
		
		//建档日期
		if (possibleCustomFollowVo.getXs_Custom_Inputdata() != null
				&& !possibleCustomFollowVo.getXs_Custom_Inputdata().equals("")) {
					sql.append(" and DATE(a.xs_custom_inputdata) >= '" +possibleCustomFollowVo.getXs_Custom_Inputdata() + "'");
				} 
		if (possibleCustomFollowVo.getXs_Custom_Inputdata2() != null
				&& !possibleCustomFollowVo.getXs_Custom_Inputdata2().equals("")) {
					sql.append(" and DATE(a.xs_custom_inputdata) <= '" +possibleCustomFollowVo.getXs_Custom_Inputdata2() + "'");
		}
		//放弃日期
		if (possibleCustomFollowVo.getLose_Date() != null
				&& !possibleCustomFollowVo.getLose_Date().equals("")) {
			sql.append(" and DATE(dtl.lose_date) >= '" +possibleCustomFollowVo.getLose_Date() + "'");
		} 
		if (possibleCustomFollowVo.getLose_Date2() != null
				&& !possibleCustomFollowVo.getLose_Date2().equals("")) {
			sql.append(" and DATE(dtl.lose_date) <= '" +possibleCustomFollowVo.getLose_Date2() + "'");
		}
		sql.append(" GROUP BY  A.custom_id");

		List<Object[]> resultList = createSQLQuery(sql.toString(),possibleCustomFollowVo.getPage(),possibleCustomFollowVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				PossibleCustomFollowVo sell = new PossibleCustomFollowVo();
				obj = resultList.get(i);
				sell.setCustom_Id(obj[0] != null ? obj[0].toString(): null);
				sell.setXs_Custom_Name(obj[1] != null ? obj[1].toString(): null);
				sell.setStf_Id(obj[2] != null ? obj[2].toString(): null);
				sell.setStf_Id_Person(obj[3] != null ? obj[3].toString(): null);
				sell.setCustomCode(obj[4] != null ? obj[4].toString(): null);
				sell.setTracing_Date(obj[5] != null ? obj[5].toString(): null);
				sell.setTalk_Title(obj[6] != null ? obj[6].toString(): null);
				sell.setObstacle(obj[7] != null ? obj[7].toString(): null);
				sell.setObstacleN(obj[8] != null ? obj[8].toString(): null);
				sell.setTracing_Summary(obj[9] != null ? obj[9].toString(): null);
				sell.setNext_Interview_Date(obj[10] != null ? obj[10].toString(): null);
				sell.setTracing_Address(obj[11] != null ? obj[11].toString(): null);
				sell.setXs_Custom_Hide_Level_Id(obj[12] != null ? obj[12].toString(): null);
				sell.setXs_Custom_Hide_Level(obj[13] != null ? obj[13].toString(): null);
				sell.setXs_Custom_Telephone(obj[14] != null ? obj[14].toString(): null);
				sell.setCai_Model_Need(obj[15] != null ? obj[15].toString(): null);
				sell.setCarModelN(obj[16] != null ? obj[16].toString(): null);
				sell.setCarColourId(obj[17] != null ? obj[17].toString(): null);
				sell.setCarColour(obj[18] != null ? obj[18].toString(): null);
				sell.setPredict_Buy_Date(obj[19] != null ? obj[19].toString(): null);
				sell.setPayment_Way(obj[20] != null ? obj[20].toString(): null);
				sell.setPayment(obj[21] != null ? obj[21].toString(): null);
				sell.setBuy_Probability(obj[22] != null ? obj[22].toString(): null);
				sell.setCustom_application(obj[23] != null ? obj[23].toString(): null);
				sell.setApplication(obj[24] != null ? obj[24].toString(): null);
				sell.setCapability_Need(obj[25] != null ? obj[25].toString(): null);
				sell.setCar_Model(obj[26] != null ? obj[26].toString(): null);
				sell.setCarModelNa(obj[27] != null ? obj[27].toString(): null);
				sell.setXs_Custom_Inputdata(obj[28] != null ? obj[28].toString(): null);
				sell.setProbability(obj[29] != null ? obj[29].toString(): null);
				sell.setXs_Custom_Deal(obj[30] != null ? obj[30].toString(): null);
				sell.setDeal(obj[31] != null ? obj[31].toString(): null);
				sell.setTracing_Way(obj[32] != null ? obj[32].toString(): null);
				sell.setTracing(obj[33] != null ? obj[33].toString(): null);
				sell.setContactsPerson(obj[34] != null ? obj[34].toString(): null);
				sell.setCustom_Property_Id(obj[35] != null ? obj[35].toString(): null);
				sell.setCustom_Property(obj[36] != null ? obj[36].toString(): null);
				sell.setBuy_Probabilitys(obj[37] != null ? obj[37].toString(): null);
				sell.setLose_Date(obj[38] != null ? obj[38].toString(): null);
				list.add(sell);
				
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	
	public Datagrid getTzCustomTrack(
			PossibleCustomFollowVo possibleCustomFollowVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<PossibleCustomFollowVo> list = new ArrayList<PossibleCustomFollowVo>();
		StringBuffer sql = new StringBuffer(" SELECT A.tracing_id,a.tracing_code,A.custom_id,A.tracing_date," +
				"A.talk_title,A.hinder_content,A.tracing_summary,A.next_interview_date,	A.tracing_way," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =A.tracing_way)  AS tracing," +
				"A.tracing_address,A.ambience_," +
				"(SELECT datavalue FROM xs_childdictionary bb WHERE bb.child_id =A.ambience_)  AS ambience," +
				"a.car_model,(select w.xs_model_name from xs_car_model w where w.xs_model_id=a.car_model )  " +
				"FROM xs_sell_custom_tracing A,xs_custom_info B " +
				"	WHERE a.custom_id=b.custom_id AND a.custom_id="+possibleCustomFollowVo.getCustom_Id());

		//企业编号
		if (possibleCustomFollowVo.getEnterpriseId()!= null
				&& !"".equals(possibleCustomFollowVo.getEnterpriseId())) {
			sql.append(" and B.enterprise_id="
					+ possibleCustomFollowVo.getEnterpriseId() + "");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(),possibleCustomFollowVo.getPage(),possibleCustomFollowVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				PossibleCustomFollowVo sell = new PossibleCustomFollowVo();
				obj = resultList.get(i);
				sell.setTracing_Id(obj[0] != null ? obj[0].toString(): null);
				sell.setTracing_Code(obj[1] != null ? obj[1].toString(): null);
				sell.setCustom_Id(obj[2] != null ? obj[2].toString(): null);
				sell.setTracing_Date(obj[3] != null ? obj[3].toString(): null);
				sell.setTalk_Title(obj[4] != null ? obj[4].toString(): null);
				sell.setHinder_Content(obj[5] != null ? obj[5].toString(): null);
				sell.setTracing_Summary(obj[6] != null ? obj[6].toString(): null);
				sell.setNext_Interview_Date(obj[7] != null ? obj[7].toString(): null);
				sell.setTracing_Way(obj[8] != null ? obj[8].toString(): null);
				sell.setTracing(obj[9] != null ? obj[9].toString(): null);
				sell.setTracing_Address(obj[10] != null ? obj[10].toString(): null);
				sell.setAmbience(obj[11] != null ? obj[11].toString(): null);
				sell.setAmbienceN(obj[12] != null ? obj[12].toString(): null);
				sell.setCar_Model(obj[13] != null ? obj[13].toString(): null);
				sell.setCarModelNa(obj[14] != null ? obj[14].toString(): null);
				list.add(sell);
				
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}
	
	
}

