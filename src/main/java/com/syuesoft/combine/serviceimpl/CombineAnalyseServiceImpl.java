package com.syuesoft.combine.serviceimpl;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ognl.Ognl;

import org.future.util.compareDoubleToString;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.combine.service.CombineAnalyseService;
import com.syuesoft.combine.vo.BalanceRateAnalayseVo;
import com.syuesoft.combine.vo.CombineAnalyseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.systemmanagement.dao.SystemLogDao;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.PoleMap;
import com.syuesoft.util.SnapMap;
/**
 * 集团分析Serivce实现
 * */
@Service("combineAnalyseService")
public class CombineAnalyseServiceImpl implements CombineAnalyseService {
	@Autowired
	private SystemLogDao systemLogDao;
	
	private List<String> formatDateOfListDays(String startTime,String endTime) throws ParseException{
		List<String> list=new ArrayList();
		if(startTime==null||startTime==""||endTime==null||endTime=="")
			return list;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sDate=sdf.parse(startTime);
		Date eDate=sdf.parse(endTime);
		long sDay=sDate.getTime()/1000/60/60/24;
		long eDay=eDate.getTime()/1000/60/60/24;
		int count=Integer.parseInt((eDay-sDay)+"");
		String temp=sdf.format(sDate);
		for (int i = 1; i <= count; i++) {
			list.add(temp);
			temp=formatAddOneDay(temp,sdf);
		}
		list.add(temp);
		return list;
	}
	private String formatAddOneDay(String source,SimpleDateFormat sdf) throws ParseException{
		return formatAddDays(source, sdf, 1);
	}
	private String formatAddDays(String source,SimpleDateFormat sdf,int count) throws ParseException{
		Date date=sdf.parse(source);
		date.setDate(date.getDate()+count);
		return sdf.format(date);
	} 
	private String formatDatetoString(String source){
		return source.replaceAll("-", "");
	}
	/////////////////////////////
	private List<String> formatDateOfListMonths(String startTime,String endTime) throws ParseException{
		List<String> list=new ArrayList();
		if(startTime==null||startTime==""||endTime==null||endTime=="")
			return list;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		Date sDate=sdf.parse(startTime);
		Date eDate=sdf.parse(endTime);
		long sDay=sDate.getTime()/1000/60/60/24/30;
		long eDay=eDate.getTime()/1000/60/60/24/30;
		int count=Integer.parseInt((eDay-sDay)+"");
		String temp=sdf.format(sDate);
		for (int i = 1; i <= count; i++) {
			list.add(temp);
			temp=formatAddOneMonth(temp,sdf);
		}
		list.add(temp);
		return list;
	}
	private String formatAddOneMonth(String source,SimpleDateFormat sdf) throws ParseException{
		return formatAddMonths(source, sdf, 1);
	}
	private String formatAddMonths(String source,SimpleDateFormat sdf,int count) throws ParseException{
		Date date=sdf.parse(source);
		date.setMonth(date.getMonth()+count);
		return sdf.format(date);
	}
	////////////////////////////////////接待台次分析/////////////////////////////////
	/**
	 * 查询接待台次分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	
	public String receptionCountAnalyse(CombineAnalyseVo combineAnalyseVo)
			throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		List<String> list=formatDateOfListDays(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(!(list!=null&&list.size()>0)){
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT bb.enterprise_id,bb.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+")");
				break;
			}else{
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+"),");
			}
		}			
		sb.append(" FROM (");
		sb.append(" SELECT aa.enterprise_id,aa.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" (CASE WHEN aa.inter_date='"+list.get(i)+"' THEN 1 ELSE 0 END) AS temp"+formatDatetoString(list.get(i)));
				break;
			}else{
				sb.append(" (CASE WHEN aa.inter_date='"+list.get(i)+"' THEN 1 ELSE 0 END) AS temp"+formatDatetoString(list.get(i))+",");
			}
		}
		sb.append(" FROM(");
		sb.append(" SELECT ei.enterprise_id,ei.enterprise_name,fr.RECEPTION_ID,DATE(fr.INTER_DATE) AS inter_date");
		sb.append(" FROM frt_reception fr INNER JOIN enterprise_info ei ON ei.enterprise_id=fr.enterprise_id ");
		sb.append(" and ei.parentEnterprise_id="+combineAnalyseVo.getParentEnterpriseId());
		if(combineAnalyseVo.getEnterpriseId()!=null&&combineAnalyseVo.getEnterpriseId().length()>0)
			sb.append(" and ei.enterprise_id in("+combineAnalyseVo.getEnterpriseId()+")");
		sb.append(" where fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO+" and DATE(fr.INTER_DATE)>='"+combineAnalyseVo.getPreclrBeginTime()
							+"' AND DATE(fr.INTER_DATE)<='"+combineAnalyseVo.getPreclrEndTime()+"'");
		sb.append(" ) aa ");
		sb.append(" ) bb GROUP BY bb.enterprise_id");		
		
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), combineAnalyseVo.getPage(), combineAnalyseVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatAnalyze(list,rows,total);
		if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
			return source;
		}
		return weaveReceptionCountAnalyseDatagrid(source,list);
	}
	private String weaveReceptionCountAnalyseDatagrid(String source,List<String> list){
		StringBuffer sb=new StringBuffer("$('#receptionCountAnalyse').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/combineAnalyseAction!findReceptionCountAnalyse.action?flag=true&'+$('#receptionCountAnalyseQueryForm').serialize(),");
		sb.append("fit:true,fitColumns:false,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'enterpriseId',title:'企业序号',width:80,hidden:true},");
		sb.append("{field:'enterpriseName',title:'企业名称',width:80},");
		if(list!=null&&list.size()>0)
		for (String string : list) {
			sb.append("{field:'temp"+formatDatetoString(string)+"',title:'"+string+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append(" onLoadSuccess:function(data){");
		sb.append(" if(staticFlag==true){");
		sb.append(" analyseLoader('analyseLoader','snapMapImg');");
		sb.append(" var stringData=formatLocalData(data);");
		sb.append(" if(stringData.length>10000){");
		sb.append(" var options=$('#receptionCountAnalyse').datagrid('options');");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findReceptionCountAnalyseSnapMap.action?page=\"+options.pageNumber+\"&rows=\"+options.pageSize+\"&\"+$('#receptionCountAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }else{");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findReceptionCountAnalyseSnapMap.action?rowsData=\"+stringData+\"&\"+$('#receptionCountAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }");
		sb.append(" }");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	private String formatAnalyze(List<String> list ,List<Object[]> rows,int total){
		double[] toteCounts=new double[list.size()+1];
		StringBuffer sb1=new StringBuffer("\"rows\":[");
		if(rows!=null&&rows.size()>0)
		for (Object[] obj : rows) {
			sb1.append("{");
			sb1.append("\"enterpriseId\":\""+obj[0]+"\",");
			sb1.append("\"enterpriseName\":\""+obj[1]+"\",");
			int i=2;
			double sumCount=0;
			if(list!=null&&list.size()>0)
			for (String string : list) {
				sb1.append("\"temp"+formatDatetoString(string)+"\":"+obj[i]+",");
				sumCount+=Double.parseDouble(obj[i].toString());
				toteCounts[i-2]+=Double.parseDouble(obj[i].toString());
				i++;
			}
			sb1.append("\"sumCount\":"+sumCount+"");
			toteCounts[toteCounts.length-1]+=sumCount;
			sb1.append("},");
			String s1=sb1.substring(0,sb1.length()-1);
			sb1=new StringBuffer(s1);
		}
		sb1.append("],\"total\":"+total+"}");
		/*****/
		StringBuffer sb2=new StringBuffer("{\"footer\":[{");
		sb2.append("\"enterpriseName\":\"合计\",");
		int i=0;
		if(list!=null&&list.size()>0)
			for (String string : list) {
				sb2.append("\"temp"+formatDatetoString(string)+"\":"+toteCounts[i]+",");
				i++;
			}
		sb2.append("\"sumCount\":\""+toteCounts[toteCounts.length-1]+"\"}],");
		sb2.append(sb1);
		return sb2.toString();
	}
	/**
	 * 获取接待台次分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	
	public JFreeChart findReceptionCountAnalyseSnapMap(
			CombineAnalyseVo combineAnalyseVo) throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		String source=null;
		List<String> list=formatDateOfListDays(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(combineAnalyseVo.getRowsData()==null||combineAnalyseVo.getRowsData().length()==0){
			combineAnalyseVo.setFlag(true);
			source=receptionCountAnalyse(combineAnalyseVo);
		}else{
			source=combineAnalyseVo.getRowsData();
			source=new String(source.getBytes("ISO-8859-1"),"UTF-8");
			source=source.replaceAll("_", "\"");
		}
		List<SnapMap> snapList=new ArrayList();
		String snapName="接待台次分析折线图";
		SnapMap sm=null;
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			Double value=null;
			if(rows!=null&&rows.size()>0)
			for (int i = 0; i < rows.size(); i++) {
				for (String string : list) {
					value=Double.parseDouble(Ognl.getValue("temp"+formatDatetoString(string), rows.get(i)).toString());
					sm=new SnapMap();
					sm.setSnapYData(value);
					sm.setSnapXData(string);
					sm.setSnapLineName(Ognl.getValue("enterpriseName", rows.get(i)).toString());
					snapList.add(sm);
				}			
			}
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "登记量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,combineAnalyseVo.getIs3D());
	}
	////////////////////////////////////结算台次分析/////////////////////////////////
	/**
	 * 查询接待台次分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	
	public String balanceCountAnalyse(CombineAnalyseVo combineAnalyseVo)
			throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		List<String> list=formatDateOfListDays(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(!(list!=null&&list.size()>0)){
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT bb.enterprise_id,bb.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+")");
				break;
			}else{
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+"),");
			}
		}			
		sb.append(" FROM (");
		sb.append(" SELECT aa.enterprise_id,aa.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" (CASE WHEN aa.inter_date='"+list.get(i)+"' THEN 1 ELSE 0 END) AS temp"+formatDatetoString(list.get(i)));
				break;
			}else{
				sb.append(" (CASE WHEN aa.inter_date='"+list.get(i)+"' THEN 1 ELSE 0 END) AS temp"+formatDatetoString(list.get(i))+",");
			}
		}
		sb.append(" FROM(");
		sb.append(" SELECT ei.enterprise_id,ei.enterprise_name,fpc.PRECLR_ID,DATE(fpc.PRECLR_TIME) AS inter_date");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN enterprise_info ei ON ei.enterprise_id=fpc.enterprise_id ");
		sb.append(" and ei.parentEnterprise_id="+combineAnalyseVo.getParentEnterpriseId());
		if(combineAnalyseVo.getEnterpriseId()!=null&&combineAnalyseVo.getEnterpriseId().length()>0)
			sb.append(" and ei.enterprise_id in("+combineAnalyseVo.getEnterpriseId()+")");
		sb.append(" where fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" and DATE(fpc.PRECLR_TIME)>='"+combineAnalyseVo.getPreclrBeginTime()
							+"' AND DATE(fpc.PRECLR_TIME)<='"+combineAnalyseVo.getPreclrEndTime()+"' and fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);
		sb.append(" ) aa ");
		sb.append(" ) bb GROUP BY bb.enterprise_id");		
		
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), combineAnalyseVo.getPage(), combineAnalyseVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatAnalyze(list,rows,total);
		if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
			return source;
		}
		return weaveBalanceCountAnalyseDatagrid(source,list);
	}
	private String weaveBalanceCountAnalyseDatagrid(String source,List<String> list){
		StringBuffer sb=new StringBuffer("$('#balanceCountAnalyse').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/combineAnalyseAction!findBalanceCountAnalyse.action?flag=true&'+$('#balanceCountAnalyseQueryForm').serialize(),");
		sb.append("fit:true,fitColumns:false,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'enterpriseId',title:'企业序号',width:80,hidden:true},");
		sb.append("{field:'enterpriseName',title:'企业名称',width:80},");
		if(list!=null&&list.size()>0)
		for (String string : list) {
			sb.append("{field:'temp"+formatDatetoString(string)+"',title:'"+string+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append(" onLoadSuccess:function(data){");
		sb.append(" if(staticFlag==true){");
		sb.append(" analyseLoader('analyseLoader','snapMapImg');");
		sb.append(" var stringData=formatLocalData(data);");
		sb.append(" if(stringData.length>10000){");
		sb.append(" var options=$('#balanceCountAnalyse').datagrid('options');");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findBalanceCountAnalyseSnapMap.action?page=\"+options.pageNumber+\"&rows=\"+options.pageSize+\"&\"+$('#balanceCountAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }else{");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findBalanceCountAnalyseSnapMap.action?rowsData=\"+stringData+\"&\"+$('#balanceCountAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }");
		sb.append(" }");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	
	/**
	 * 获取结算台次分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	
	public JFreeChart findBalanceCountAnalyseSnapMap(
			CombineAnalyseVo combineAnalyseVo) throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		String source=null;
		List<String> list=formatDateOfListDays(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(combineAnalyseVo.getRowsData()==null||combineAnalyseVo.getRowsData().length()==0){
			combineAnalyseVo.setFlag(true);
			source=receptionCountAnalyse(combineAnalyseVo);
		}else{
			source=combineAnalyseVo.getRowsData();
			source=new String(source.getBytes("ISO-8859-1"),"UTF-8");
			source=source.replaceAll("_", "\"");
		}
		List<SnapMap> snapList=new ArrayList();
		String snapName="结算台次分析折线图";
		SnapMap sm=null;
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			Double value=null;
			if(rows!=null&&rows.size()>0)
			for (int i = 0; i < rows.size(); i++) {
				for (String string : list) {
					value=Double.parseDouble(Ognl.getValue("temp"+formatDatetoString(string), rows.get(i)).toString());
					sm=new SnapMap();
					sm.setSnapYData(value);
					sm.setSnapXData(string);
					sm.setSnapLineName(Ognl.getValue("enterpriseName", rows.get(i)).toString());
					snapList.add(sm);
				}			
			}
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "登记量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,combineAnalyseVo.getIs3D());
	}
	////////////////////////////////////结算费用分析/////////////////////////////////
	/**
	 * 查询结算费用分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return DatagridAnalyze  返回数据表格统计对象
	 * */
	
	public DatagridAnalyze findBalanceRateAnalyse(CombineAnalyseVo combineAnalyseVo)
			throws Exception {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(!(combineAnalyseVo.getPreclrBeginTime()!=null&&combineAnalyseVo.getPreclrBeginTime().length()>0)){
			combineAnalyseVo.setPreclrBeginTime(formatAddDays(date.toLocaleString(), sdf, 7));
		}
		if(!(combineAnalyseVo.getPreclrEndTime()!=null&&combineAnalyseVo.getPreclrEndTime().length()>0)){
			combineAnalyseVo.setPreclrEndTime(formatAddDays(date.toLocaleString(), sdf, 0));
		}
		StringBuffer sb=new StringBuffer("SELECT aa.enterprise_id,aa.enterprise_name,SUM(aa.PRECLR_WKTIME_AMOUNT) AS PRECLR_WKTIME_AMOUNT,SUM(aa.PRE_MPR_MAT_AMOUNT) AS PRE_MPR_MAT_AMOUNT,");
		sb.append(" SUM(aa.PRECLR_OTHER_AMOUNT) AS PRECLR_OTHER_AMOUNT, SUM(aa.PRECLR_TAX_COST) AS PRECLR_TAX_COST,");
		sb.append(" SUM(aa.PRECLR_NOTAX_COST) AS PRECLR_NOTAX_COST,SUM(aa.PRECLR_SUM_AMOUNT) AS PRECLR_SUM_AMOUNT");
		sb.append(" FROM ( SELECT ei.enterprise_id,ei.enterprise_name,fpc.PRECLR_WKTIME_AMOUNT,fpc.PRE_MPR_MAT_AMOUNT,fpc.PRECLR_OTHER_AMOUNT,");
		sb.append(" fpc.PRECLR_TAX_COST,fpc.PRECLR_NOTAX_COST,fpc.PRECLR_SUM_AMOUNT");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN enterprise_info ei ON ei.enterprise_id=fpc.enterprise_id ");
		sb.append(" and ei.parentEnterprise_id="+combineAnalyseVo.getParentEnterpriseId());
		if(combineAnalyseVo.getEnterpriseId()!=null&&combineAnalyseVo.getEnterpriseId().length()>0)
			sb.append(" and ei.enterprise_id in("+combineAnalyseVo.getEnterpriseId()+")");
		sb.append(" WHERE fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" and fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(combineAnalyseVo.getPreclrBeginTime()!=null&&combineAnalyseVo.getPreclrBeginTime().length()>0)
			sb.append(" and fpc.PRECLR_TIME>='"+combineAnalyseVo.getPreclrBeginTime()+"'");
		if(combineAnalyseVo.getPreclrEndTime()!=null&&combineAnalyseVo.getPreclrEndTime().length()>0)
			sb.append(" and fpc.PRECLR_TIME<='"+combineAnalyseVo.getPreclrEndTime()+"'");
		sb.append(" ) aa GROUP BY aa.enterprise_id");
		List<Object[]> list=systemLogDao.createSQLQuery(sb.toString(), combineAnalyseVo.getPage(), combineAnalyseVo.getRows());
		List<BalanceRateAnalayseVo> footers=new ArrayList();
		List<BalanceRateAnalayseVo> rows=new ArrayList();
		BalanceRateAnalayseVo braVos=new BalanceRateAnalayseVo();
		braVos.setPreclrTimeAmount(0d);
		braVos.setPreclrPartsAmount(0d);
		braVos.setPreclrOtherAmount(0d);
		braVos.setPreclrTaxCost(0d);
		braVos.setPreclrNoTaxCost(0d);
		braVos.setPreclrSumAmount(0d);
		
		BalanceRateAnalayseVo braVo=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				braVo=new BalanceRateAnalayseVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					braVo.setEnterpriseId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					braVo.setEnterpriseName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0){
					braVo.setPreclrTimeAmount(Double.parseDouble(obj[2].toString()));
					braVos.setPreclrTimeAmount(braVos.getPreclrTimeAmount()+braVo.getPreclrTimeAmount());
				}
				if(obj[3]!=null&&obj[3].toString().length()>0){
					braVo.setPreclrPartsAmount(Double.parseDouble(obj[3].toString()));
					braVos.setPreclrPartsAmount(braVos.getPreclrPartsAmount()+braVo.getPreclrPartsAmount()); 
				}
				if(obj[4]!=null&&obj[4].toString().length()>0){
					braVo.setPreclrOtherAmount(Double.parseDouble(obj[4].toString()));
					braVos.setPreclrOtherAmount(braVos.getPreclrOtherAmount()+braVo.getPreclrOtherAmount());
				}
				if(obj[5]!=null&&obj[5].toString().length()>0){
					braVo.setPreclrTaxCost(Double.parseDouble(obj[5].toString()));
					braVos.setPreclrTaxCost(braVos.getPreclrTaxCost()+braVo.getPreclrTaxCost());
				}
				if(obj[6]!=null&&obj[6].toString().length()>0){
					braVo.setPreclrNoTaxCost(Double.parseDouble(obj[6].toString()));
					braVos.setPreclrNoTaxCost(braVos.getPreclrNoTaxCost()+braVo.getPreclrNoTaxCost());
				}
				if(obj[7]!=null&&obj[7].toString().length()>0){
					braVo.setPreclrSumAmount(Double.parseDouble(obj[7].toString()));
					braVos.setPreclrSumAmount(braVos.getPreclrSumAmount()+braVo.getPreclrSumAmount());
				}
				braVo.setPartsCompareTime(comparePartsAndTime(braVo.getPreclrPartsAmount(),braVo.getPreclrTimeAmount()));
				rows.add(braVo);
			}
		braVos.setPartsCompareTime(comparePartsAndTime(braVos.getPreclrPartsAmount(),braVos.getPreclrTimeAmount()));
		footers.add(braVos);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setFooter(footers);
		da.setTotal(systemLogDao.getSQLCount(sb.toString(), null));
		return da;
	}
	private String comparePartsAndTime(Double partsAmount,Double timeAmount){
		return new compareDoubleToString().compareDoublePercentum(partsAmount, timeAmount);
	}
	/**
	 * 查询结算费用分析柱状图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	
	public JFreeChart findBalanceRateAnalysePoleMap(CombineAnalyseVo combineAnalyseVo) throws Exception {
		String source=null;
		DatagridAnalyze da=null;
		List<String> list=formatDateOfListDays(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(combineAnalyseVo.getRowsData()==null||combineAnalyseVo.getRowsData().length()==0){
			combineAnalyseVo.setFlag(true);
			//source=receptionCountAnalyse(combineAnalyseVo);
		}else{
			source=combineAnalyseVo.getRowsData();
			source=new String(source.getBytes("ISO-8859-1"),"UTF-8");
			source=source.replaceAll("_", "\"");
		}
		List<PoleMap> poleMapList=new ArrayList();
		SnapMap sm=null;
		PoleMap pm=null;
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List<BalanceRateAnalayseVo> rows= JSON.parseArray(json.get("rows").toString(),BalanceRateAnalayseVo.class);
			Double value=null;
			if(rows!=null&&rows.size()>0)
			for (int i = 0; i < rows.size(); i++) {
				for (BalanceRateAnalayseVo braVo : rows) {
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#FF0000"));
					pm.setPoleXData("结算工时费用");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrTimeAmount());
					poleMapList.add(pm);
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#00FF00"));
					pm.setPoleXData("结算材料费用");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrPartsAmount());
					poleMapList.add(pm);
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#FF00FF"));
					pm.setPoleXData("结算其他费用");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrOtherAmount());
					poleMapList.add(pm);
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#800000"));
					pm.setPoleXData("结算含税成本");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrTaxCost());
					poleMapList.add(pm);
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#008000"));
					pm.setPoleXData("结算未税成本");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrNoTaxCost());
					poleMapList.add(pm);
					pm=new PoleMap();
					//pm.setPoleBarColor(Color.decode("#000080"));
					pm.setPoleXData("结算总金额");
					pm.setPoleBarName(braVo.getEnterpriseName());
					pm.setPoleYData(braVo.getPreclrSumAmount());
					poleMapList.add(pm);
				}
			}
		}
        return CreateJFreeChart.findPoleMap("结算费用分析柱状图", "分类", "金额",poleMapList,null,
        		null,null,null,null,null,null,null,null,null,null,
    			null,null,null,null,null,null,null,null,null,null,
    			null,null,null,null,null,null,CategoryLabelPositions.STANDARD,null,null,null,combineAnalyseVo.getIs3D());
	}
	////////////////////////////////////应收账款分析/////////////////////////////////
	/**
	 * 查询应收账款分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	
	public String findAccountReceivableAnalyse(CombineAnalyseVo combineAnalyseVo)
			throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		List<String> list=formatDateOfListMonths(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(!(list!=null&&list.size()>0)){
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT bb.enterprise_id,bb.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+")");
				break;
			}else{
				sb.append(" SUM(bb.temp"+formatDatetoString(list.get(i))+"),");
			}
		}			
		sb.append(" FROM (");
		sb.append(" SELECT aa.enterprise_id,aa.enterprise_name,");
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1){
				sb.append(" (CASE WHEN aa.preclr_time='"+list.get(i)+"' THEN aa.receive ELSE 0 END) AS temp"+formatDatetoString(list.get(i)));
				break;
			}else{
				sb.append(" (CASE WHEN aa.preclr_time='"+list.get(i)+"' THEN aa.receive ELSE 0 END) AS temp"+formatDatetoString(list.get(i))+",");
			}
		}
		sb.append(" FROM(");
		sb.append(" SELECT ei.enterprise_id,ei.enterprise_name,DATE_FORMAT(fpc.preclr_time,'%Y-%m') AS preclr_time,fmr.MR_RECEIVABLES AS receive");
		sb.append(" FROM fin_maintenance_receivables fmr INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fmr.preclr_id");
		sb.append(" INNER JOIN enterprise_info ei ON ei.enterprise_id=fpc.enterprise_id");
		sb.append(" and ei.parentEnterprise_id="+combineAnalyseVo.getParentEnterpriseId());
		if(combineAnalyseVo.getEnterpriseId()!=null&&combineAnalyseVo.getEnterpriseId().length()>0)
			sb.append(" and ei.enterprise_id in("+combineAnalyseVo.getEnterpriseId()+")");
		sb.append(" where fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" and DATE_FORMAT(fpc.preclr_time,'%Y-%m')>='"+combineAnalyseVo.getPreclrBeginTime()
							+"' AND DATE_FORMAT(fpc.preclr_time,'%Y-%m')<='"+combineAnalyseVo.getPreclrEndTime()+"'");
		sb.append(" ) aa )bb GROUP BY bb.enterprise_id");
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), combineAnalyseVo.getPage(), combineAnalyseVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatAnalyze(list,rows,total);
		if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
			return source;
		}
		return weaveAccountReceivableAnalyseDatagrid(source,list);
	}
	private String weaveAccountReceivableAnalyseDatagrid(String source,List<String> list){
		StringBuffer sb=new StringBuffer("$('#accountReceivableAnalyse').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/combineAnalyseAction!findAccountReceivableAnalyse.action?flag=true&'+$('#accountReceivableAnalyseQueryForm').serialize(),");
		sb.append("fit:true,fitColumns:false,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'enterpriseId',title:'企业序号',width:80,hidden:true},");
		sb.append("{field:'enterpriseName',title:'企业名称',width:80},");
		if(list!=null&&list.size()>0)
		for (String string : list) {
			sb.append("{field:'temp"+formatDatetoString(string)+"',title:'"+string+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append(" onLoadSuccess:function(data){");
		sb.append(" if(staticFlag==true){");
		sb.append(" analyseLoader('analyseLoader','snapMapImg');");
		sb.append(" var stringData=formatLocalData(data);");
		sb.append(" if(stringData.length>10000){");
		sb.append(" var options=$('#accountReceivableAnalyse').datagrid('options');");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findAccountReceivableAnalyseSnapMap.action?page=\"+options.pageNumber+\"&rows=\"+options.pageSize+\"&\"+$('#accountReceivableAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }else{");
		sb.append(" document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append(" \"<img onload=\\\"analyseLoaderHidden('analyseLoader','snapMapImg');\\\" src=\\\"combineAnalyseAction!findAccountReceivableAnalyseSnapMap.action?rowsData=\"+stringData+\"&\"+$('#accountReceivableAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append(" }");
		sb.append(" }");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	/**
	 * 获取应收账款分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	
	public JFreeChart findAccountReceivableAnalyseSnapMap(
			CombineAnalyseVo combineAnalyseVo) throws Exception {
//		combineAnalyseVo.setPreclrBeginTime("2011-10-28");
//		combineAnalyseVo.setPrelcrEndTime("2013-12-04");
		if(combineAnalyseVo.getPreclrBeginTime()==null||combineAnalyseVo.getPreclrBeginTime().length()==0)
			combineAnalyseVo.setPreclrBeginTime(new Date().toLocaleString());
		if(combineAnalyseVo.getPreclrEndTime()==null||combineAnalyseVo.getPreclrEndTime().length()==0)
			combineAnalyseVo.setPreclrEndTime(new Date().toLocaleString());
		String source=null;
		List<String> list=formatDateOfListMonths(combineAnalyseVo.getPreclrBeginTime(),combineAnalyseVo.getPreclrEndTime());
		if(combineAnalyseVo.getRowsData()==null||combineAnalyseVo.getRowsData().length()==0){
			combineAnalyseVo.setFlag(true);
			source=findAccountReceivableAnalyse(combineAnalyseVo);
		}else{
			source=combineAnalyseVo.getRowsData();
			source=new String(source.getBytes("ISO-8859-1"),"UTF-8");
			source=source.replaceAll("_", "\"");
		}
		List<SnapMap> snapList=new ArrayList();
		String snapName="应收账款分析折线图";
		SnapMap sm=null;
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			Double value=null;
			if(rows!=null&&rows.size()>0)
			for (int i = 0; i < rows.size(); i++) {
				for (String string : list) {
					value=Double.parseDouble(Ognl.getValue("temp"+formatDatetoString(string), rows.get(i)).toString());
					sm=new SnapMap();
					sm.setSnapYData(value);
					sm.setSnapXData(string);
					sm.setSnapLineName(Ognl.getValue("enterpriseName", rows.get(i)).toString());
					snapList.add(sm);
				}			
			}
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "金额", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,combineAnalyseVo.getIs3D());
	}
}
