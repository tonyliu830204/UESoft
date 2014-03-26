package com.syuesoft.sell.instore.serviceimpl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.instore.dao.SellMonthlyStatementDao;
import com.syuesoft.sell.instore.service.SellMonthlyStatementService;
import com.syuesoft.sell.instore.vo.SellMonthlyStatementVo;
import com.syuesoft.sell.model.XsSellCensus;
import com.syuesoft.sell.model.XsSellCensusCollect;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Service("sellMonthlyStatementService")
public class SellMonthlyStatementServiceImpl implements SellMonthlyStatementService {

	@Resource
	private SellMonthlyStatementDao sellMonthlyStatementDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	/**
	 * 获取库存信息
	 */
	
	public Json getStock(SellMonthlyStatementVo monthlyStatementVo)
			throws Exception {
		Json json = new Json();
		StringBuffer hql = new StringBuffer(
				" SELECT aa.collect_Id,aa.census_Startdate,aa.census_Enddate,aa.census_Backdate,aa.census_Sum,aa.remark " +
				" from Xs_Sell_Census_Collect aa WHERE 1 = 1 ");
		if(monthlyStatementVo.getEnterprise_id()!=null && !monthlyStatementVo.getEnterprise_id().equals("")){
			hql.append(" AND aa.enterprise_Id="+monthlyStatementVo.getEnterprise_id()+"");
		}
		if (monthlyStatementVo.getCensusSdate() != null
				&& !monthlyStatementVo.getCensusSdate().equals("")) {
					hql.append(" and Date(aa.census_Startdate) > ='" + monthlyStatementVo.getCensusSdate() + "'");
		}
		if (monthlyStatementVo.getCensusSdate2() != null
				&& !monthlyStatementVo.getCensusSdate2().equals("")) {
					hql.append(" and Date(aa.census_Startdate) < ='" + monthlyStatementVo.getCensusSdate2() + "'");
		}
		if (monthlyStatementVo.getCensusEdate() != null
				&& !monthlyStatementVo.getCensusEdate().equals("")) {
					hql.append(" and Date(aa.census_Enddate) > ='" + monthlyStatementVo.getCensusEdate() + "'");
		}
		if (monthlyStatementVo.getCensusEdate2() != null
				&& !monthlyStatementVo.getCensusEdate2().equals("")) {
					hql.append(" and Date(aa.census_Enddate) < ='" + monthlyStatementVo.getCensusEdate2() + "'");
		}
		
		List rlist = sellMonthlyStatementDao.createSQLQuery(hql.toString(),
				monthlyStatementVo.getPage(), monthlyStatementVo.getRows());
		List listout = new ArrayList();
		if (rlist != null) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				SellMonthlyStatementVo sellMmonthlyStatementVo2 = new SellMonthlyStatementVo();
				sellMmonthlyStatementVo2.setCollectId(obj[0] != null ? obj[0]
				        .toString() : "");
				sellMmonthlyStatementVo2.setCensusSdate(obj[1] != null ? obj[1]
                        .toString().substring(0, 19) : "");
				sellMmonthlyStatementVo2.setCensusEdate(obj[2] != null ? obj[2]
				        .toString().substring(0, 19) : "");
				sellMmonthlyStatementVo2.setCensusRdate(obj[3] != null ? obj[3]
				        .toString().substring(0, 19) : "");
				sellMmonthlyStatementVo2.setCensusSum(obj[4] != null ? obj[4]
				        .toString() : "");
				sellMmonthlyStatementVo2.setRemark(obj[5] != null ? obj[5]
						.toString() : "");
				listout.add(sellMmonthlyStatementVo2);
			}
		}
		json.setTotal(sellMonthlyStatementDao.getCountBySQL(hql.toString()));
		json.setRows(listout);
		return json;
	}
	/**
	 * 月结
	 */
	
	public boolean doStock(SellMonthlyStatementVo vonthlyStatementVo)
			throws Exception {
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cdate = Calendar.getInstance();
		String stardate = simp.format(System.currentTimeMillis());
		//本次月结结束日期
		String enddate = simp.format(System.currentTimeMillis());
		//获取上次月结结束时间作为本次月结开始时间
		List rlist = sellMonthlyStatementDao
				.createSQLQuery("select max(census_enddate) from xs_sell_census_collect where enterprise_id="+vonthlyStatementVo.getEnterprise_id());
		//如果月结时间相等了择退出本次月结
		if(rlist.get(0) != null && enddate.equals(rlist.get(0))){
			return false;
		}
		if (rlist.get(0)!=null && !("".equals(rlist.get(0))) && vonthlyStatementVo.getCensusEdate() != null
				&& !("".equals(vonthlyStatementVo.getCensusEdate()))) {
			//开始日期
			stardate = FormatTime.timestamp2Str1((Timestamp)rlist.get(0), FormatTime.DEFAULT_FORMAT);
			enddate = FormatTime.timestamp2Str1(FormatTime.str2Timestamp(vonthlyStatementVo.getCensusEdate().toString()), FormatTime.DEFAULT_FORMAT);
		}
		if (rlist.get(0) == null || ("".equals(rlist.get(0))) && vonthlyStatementVo.getCensusEdate() != null
				&& !("".equals(vonthlyStatementVo.getCensusEdate()))){
			enddate = FormatTime.timestamp2Str1(FormatTime.str2Timestamp(vonthlyStatementVo.getCensusEdate().toString()), FormatTime.DEFAULT_FORMAT);
			stardate = enddate;
		}
		//上次月结结束时间到本次月结结束时间的库存量
		//通过车辆编号分类
		//调用算库存量的存储过程获取入库，出库，退厂，退车，月结库存为: 入 - 出 + 退车 - 退厂
		//本次月结库存量    CALL stock_monthly_statement("","","aduit","yishenhe","instoreStyle","02","01","");
		StringBuffer sb = new StringBuffer("{ CALL stock_monthly_statement(?,?,?,?,?,?,?,?,?) }");
		StringBuffer procedureName = new StringBuffer("{ CALL stock_monthly_statement(?,?,?,?,?,?,?,?,?) }" );
				
        List<Object> params = new ArrayList<Object>();
        params.add(0,stardate);
        params.add(1,enddate);
        params.add(2,Contstants.BASE_SELL.ADUIT);
        params.add(3,Contstants.ADUIT.YISHENHE);
        params.add(4,Contstants.INSTORESTYLE.PARENTINSTORE);
        params.add(5,Contstants.INSTORESTYLE.OUT);
        params.add(6,Contstants.INSTORESTYLE.BACK);
        params.add(7,vonthlyStatementVo.getEnterprise_id());
        params.add(8,null);
        List<Object[]> sbslist = sellMonthlyStatementDao.getProcedureQuery(procedureName.toString(),params);
		int thissum = 0; 
		if (sbslist != null && sbslist.size()>0 && sbslist.get(0)!=null) {
			Object[] obj = (Object[])sbslist.get(0);
			int input = 0;
			int output = 0;
			int exitcar = 0;
			int exitpart = 0;
			if(obj[0]!=null && !obj[0].equals("")){
				input = (int)Double.parseDouble(obj[0].toString());
			}
			if(obj[1]!=null && !obj[1].equals("")){
				output = (int)Double.parseDouble(obj[1].toString());
			}
			if(obj[2]!=null && !obj[2].equals("")){
				exitcar = (int)Double.parseDouble(obj[2].toString());
			}
			if(obj[3]!=null && !obj[3].equals("")){
				exitpart = (int)Double.parseDouble(obj[3].toString());
			}
			thissum = input - output + exitcar - exitpart;
		}
		//上次月结时的库存量
		int lastsum = 0;
		//获取上次月结时候的库存量
		List lastlist = sellMonthlyStatementDao
		.createSQLQuery("select H.census_sum from xs_sell_census_collect H WHERE H.census_enddate =(select max(gg.census_enddate) from xs_sell_census_collect gg WHERE gg.enterprise_id="+vonthlyStatementVo.getEnterprise_id()+") ");
		if (lastlist!=null && lastlist.get(0) != null ) {
			lastsum = Integer.parseInt(lastlist.get(0).toString());
		}
		//本次月结量 = 上次月结 + 本次月结
		int allcount = lastsum + (thissum);
		XsSellCensusCollect xsSellCensusCollect = new XsSellCensusCollect();
		xsSellCensusCollect.setCensusEnddate(simp.parse(enddate));
		xsSellCensusCollect.setCensusStartdate(simp.parse(stardate));
		xsSellCensusCollect.setCensusSum(allcount);
		xsSellCensusCollect.setRemark(vonthlyStatementVo.getRemark());
		xsSellCensusCollect.setEnterpriseId(vonthlyStatementVo.getEnterprise_id());
		Set<XsSellCensus> set = new HashSet<XsSellCensus>();
		
		List<Object[]> thislist = sellMonthlyStatementDao.getProcedureQuery(sb.toString(), params);
		params.add(8," GROUP BY R.xs_car_id");
		
		if(thislist!=null && thislist.size()>0){
			Object[] obj = null;
			int in = 0;
			int out = 0;
			int exitc = 0;
			int exitp = 0;
			int everycarsum = 0;
			for (int i = 0; i < thislist.size(); i++) {
				//obj[0]入库量，obj[1]出库量，obj[2]退车量，obj[3]退厂量，obj[4]车辆编号 
				obj = (Object[])thislist.get(i);
				//每辆车的库存量
				if(obj[0]!=null && !obj[0].equals("")){
					in = (int)Double.parseDouble(obj[0].toString());
				}
				if(obj[1]!=null && !obj[1].equals("")){
					out = (int)Double.parseDouble(obj[1].toString());
				}
				if(obj[2]!=null && !obj[2].equals("")){
					exitc = (int)Double.parseDouble(obj[2].toString());
				}
				if(obj[3]!=null && !obj[3].equals("")){
					exitp = (int)Double.parseDouble(obj[3].toString());
				}
				everycarsum = in - out + exitc - exitp;
				XsSellCensus xsSellCensus = new XsSellCensus();
				xsSellCensus.setCensusSum(everycarsum);
				xsSellCensus.setXsCarId(obj[4]!=null ? Integer.parseInt(obj[4].toString()) : null);
				xsSellCensus.setXsSellCensusCollect(xsSellCensusCollect);
				set.add(xsSellCensus);
				}
				xsSellCensusCollect.setXsSellCensuses(set);
			
			/** 将入库明细记录的月结状态改为1    是 */
			String sql = "UPDATE xs_sell_instorehouse_del a SET a.xs_census = "
				+ Contstants.BASE_SELL.month_statement01
				+ " WHERE a.instorehouse_id IN (SELECT b.instorehouse_id FROM xs_sell_instorehouse b" +
				" WHERE b.enterprise_id="+vonthlyStatementVo.getEnterprise_id()+"" +
				") ";
			sellMonthlyStatementDao.createSQLQueryOutFind(sql);
		
		}
		sellMonthlyStatementDao.save(xsSellCensusCollect);
		return true;

	}
	
	/**
	 * 反月结
	 */
	
	public boolean doReturnStock(SellMonthlyStatementVo sellMonthlyStatementVo)
			throws Exception {
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowdate = simp.format(new java.util.Date());
		//如果选择的月节点等于最大月节点是才可反月结
		//获取最后一次月结结束时间
		String sqlss = "SELECT MAX(ct.census_enddate) FROM xs_sell_census_collect ct WHERE ct.enterprise_id="+sellMonthlyStatementVo.getEnterprise_id()+"";
		List sumlists = sellMonthlyStatementDao.createSQLQuery(sqlss);
		if(simp.parse(sellMonthlyStatementVo.getCensusEdate().trim()).before(simp.parse(sumlists.get(0).toString().trim()))){
			return false;
		}
		//获取上一次的月结库存量
		String sqls = "SELECT st.census_sum FROM xs_sell_census_collect st WHERE st.census_enddate='"+sellMonthlyStatementVo.getCensusSdate()+"' AND st.enterprise_id="+sellMonthlyStatementVo.getEnterprise_id()+"";
		List sumlist = sellMonthlyStatementDao.createSQLQuery(sqls);
		if(sumlist!=null && sumlist.get(0)!=null){
			XsSellCensusCollect xsSellCensusCollect = (XsSellCensusCollect)sellMonthlyStatementDao.get("from XsSellCensusCollect x WHERE x.censusEnddate='"+sellMonthlyStatementVo.getCensusEdate()+"' AND x.enterpriseId="+sellMonthlyStatementVo.getEnterprise_id()+"");
			xsSellCensusCollect.setCensusBackdate(simp.parse(nowdate));
			xsSellCensusCollect.setCensusSum(Integer.parseInt(sumlist.get(0).toString()));
			sellMonthlyStatementDao.merge(xsSellCensusCollect);
		}else{
			XsSellCensusCollect xsSellCensusCollect = (XsSellCensusCollect)sellMonthlyStatementDao.get("from XsSellCensusCollect x WHERE x.censusEnddate='"+sellMonthlyStatementVo.getCensusEdate()+"' AND x.enterpriseId="+sellMonthlyStatementVo.getEnterprise_id()+"");
			xsSellCensusCollect.setCensusBackdate(simp.parse(nowdate));
			sellMonthlyStatementDao.merge(xsSellCensusCollect);
		}
		
		String stardate = sellMonthlyStatementVo.getCensusSdate() != null
				&& !sellMonthlyStatementVo.getCensusSdate().equals("") ? sellMonthlyStatementVo
				.getCensusSdate()
				: "";
		String enddate = sellMonthlyStatementVo.getCensusEdate() != null
				&& !sellMonthlyStatementVo.getCensusEdate().equals("") ? sellMonthlyStatementVo
				.getCensusEdate()
				: "";
				List xsSellCensuslist = sellMonthlyStatementDao.find("FROM XsSellCensus xs WHERE xs.xsSellCensusCollect.collectId="+sellMonthlyStatementVo.getCollectId()+"");
				List<XsSellCensus> clist = xsSellCensuslist;
				String carid = "";
				if(clist!=null && clist.get(0)!=null){
				for (XsSellCensus xsSellCensus : clist) {
					carid = xsSellCensus.getXsCarId()!=null ? xsSellCensus.getXsCarId().toString() : null;
					StringBuffer sql = new  StringBuffer("UPDATE xs_sell_instorehouse_del a SET a.xs_census = '"
							+ Contstants.BASE_SELL.month_statement02+"' WHERE a.xs_car_id="+carid+"" 
							+ " AND a.instorehouse_id IN (SELECT HS.instorehouse_id FROM xs_sell_instorehouse HS WHERE HS.enterprise_id="+sellMonthlyStatementVo.getEnterprise_id()+")");
					sellMonthlyStatementDao.createSQLQueryOutFind(sql.toString());
				}
				}
			return true;
	}
	
}
