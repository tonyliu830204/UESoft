package com.syuesoft.frt.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ognl.Ognl;

import org.apache.commons.lang.StringEscapeUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.dao.ReptypeDao;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.service.ServiceDestClerkAnalyseService;
import com.syuesoft.frt.vo.FrtQueryVo;
import com.syuesoft.frt.vo.ReceiveOperationVo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.Reptype;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.systemmanagement.dao.SystemLogDao;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.MyBeanUtils;
import com.syuesoft.util.SnapMap;
/**
 * 维修接待员分析Service
 * */
@Service("serviceDestClerkAnalyseService")
public class ServiceDestClerkAnalyseServiceImpl implements ServiceDestClerkAnalyseService {
	@Autowired
	private SystemLogDao systemLogDao;
	@Autowired
	private BasStuffDao basStuffDao;
	@Autowired
	private ReptypeDao reptypeDao;
	@Autowired
	private FrtService frtService;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 查找维修接待员分析数据
	 * */
	
	public String findServiceDestClerkAnalyse(FrtQueryVo fqVo) throws Exception {
		List<BasStuff> list=basStuffDao.find("from BasStuff bs where bs.enterpriseInfo.enterpriseId="+fqVo.getEnterpriseId());
		isSelected(list,fqVo);
		StringBuffer sb=new StringBuffer("SELECT aa.interDate,");
		if(list!=null&&list.size()>0){
			for (BasStuff bs : list) {
				if(bs!=null)
					sb.append(" COUNT(aa.data"+bs.getStfId()+"),");
			}			
		}else{
			return null;			
		}
		String sql1=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql1);
		sb.append(" FROM ( SELECT Date(frt.INTER_DATE) AS interDate,");
		if(list!=null&&list.size()>0)
		for (BasStuff bs : list) {
			if(bs!=null){
				sb.append(" (SELECT temp.reception_id FROM frt_reception temp");
				sb.append(" WHERE frt.reception_id=temp.reception_id AND temp.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO+" and frt.RECEPTOR="+bs.getStfId()+"  and temp.enterprise_id="+fqVo.getEnterpriseId() +" ) AS  data"+bs.getStfId()+",");				
			}
		}
		String sql=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql);
		sb.append(" FROM frt_reception frt,bas_stuff bs");
		sb.append(" WHERE bs.stf_id=frt.RECEPTOR AND frt.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO+"  and  frt.enterprise_id="+fqVo.getEnterpriseId() +") aa where 1=1");
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0)
			sb.append(" and aa.interDate='"+fqVo.getEnrolTime()+"'");
		if(fqVo.getBeginTime()!=null&&fqVo.getBeginTime().length()>0){
			sb.append(" and aa.interDate>='"+fqVo.getBeginTime()+"'");
		}
		if(fqVo.getEndTime()!=null&&fqVo.getEndTime().length()>0){
			sb.append(" and aa.interDate<='"+fqVo.getEndTime()+"'");
		}
		sb.append(" GROUP BY aa.interDate");
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), fqVo.getPage(), fqVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatServiceReceiveAnalyze(list,rows,total);
		if(fqVo.getFlag()!=null&&fqVo.getFlag()==true){
			return source;
		}
		return weaveDatagrid(source,list);
	}
	private String weaveDatagrid(String source,List<BasStuff> list){
		StringBuffer sb=new StringBuffer("$('#serviceDestClerkAnalyseDatagrid').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/serviceDestClerkAnalyseAction!findServiceDestClerkAnalyse.action?flag=true',");
		sb.append("fit:true,fitColumns:true,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'enrolTime',title:'时间段',width:100,sortable:false},");
		if(list!=null&&list.size()>0)
		for (BasStuff bs : list) {
			if(bs!=null)
				sb.append("{field:'data"+bs.getStfId()+"',title:'"+bs.getStfName()+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append("onSelect:function(rowIndex, rowData){");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findCakeMap.action?page=1&rows=1&enrolTime=\"+rowData.enrolTime+\"&\"");
		sb.append("+$('#serviceDestClerkAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append("},");
		sb.append("onClickCell:function(rowIndex, field, value){");
		sb.append("analyseLoader('analyseLoaderSnapMap', 'snapMapImg');");
		sb.append("var data=$('#serviceDestClerkAnalyseDatagrid').datagrid('options');");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findSnapMap.action?page=\"+data.pageNumber+\"&rows=\"+data.pageSize+\"&\"");
		sb.append("+$('#serviceDestClerkAnalyseQueryForm').serialize()+\"&selectedField=\"+field+\" \\\" />\";");
		sb.append("},");
		sb.append("onLoadSuccess:function(data){");
		sb.append("if(tag){");
		sb.append("if($('#serviceDestClerkAnalyseQueryForm').form('validate')){");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("analyseLoader('analyseLoaderSnapMap', 'snapMapImg');");
		sb.append("var params=$('#serviceDestClerkAnalyseDatagrid').datagrid('options');");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findSnapMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");	
		sb.append("+$('#serviceDestClerkAnalyseQueryForm').serialize()+\" \\\" />\";");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findCakeMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");		
		sb.append("+$('#serviceDestClerkAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append("}else{");
		sb.append("alertMsg('对不起，请输入正确的查询条件！', 'warning');");
		sb.append("}");
		sb.append("}");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	private String formatServiceReceiveAnalyze(List<BasStuff> list ,List<Object[]> rows,Integer total){
		int[] toteCounts=new int[list.size()+1];
		StringBuffer sb1=new StringBuffer("\"rows\":[");
		if(rows!=null&&rows.size()>0){
			for (Object[] obj : rows) {
				sb1.append("{");
				sb1.append("\"enrolTime\":\""+obj[0]+"\",");
				int i=1;
				int sumCount=0;
				if(list!=null&&list.size()>0)
					for (BasStuff bs : list) {
						if(bs!=null){
							sb1.append("\"data"+bs.getStfId()+"\":"+obj[i]+",");
							sumCount+=Integer.parseInt(obj[i].toString());
							toteCounts[i-1]+=Integer.parseInt(obj[i].toString());
							i++;					
						}
					}
				sb1.append("\"sumCount\":"+sumCount+"");
				toteCounts[toteCounts.length-1]+=sumCount;
				sb1.append("},");
			}
			String s1=sb1.substring(0,sb1.length()-1);
			sb1=new StringBuffer(s1);			
		}
		sb1.append("],\"total\":"+total+"}");
		/*****/
		StringBuffer sb2=new StringBuffer("{\"footer\":[{");
		sb2.append("\"enrolTime\":\"合计\",");
		int i=0;
		if(list!=null&&list.size()>0)
		for (BasStuff bs : list) {
			if(bs!=null){
				sb2.append("\"data"+bs.getStfId()+"\":"+toteCounts[i]+",");
				i++;				
			}
		}
		sb2.append("\"sumCount\":\""+toteCounts[toteCounts.length-1]+"\"}],");
		sb2.append(sb1);
		return sb2.toString();
	}
	private void isSelected(List<BasStuff> list,FrtQueryVo fqVo){
		if(fqVo.getSelectedField()!=null&&fqVo.getSelectedField().length()>0){
			int count=fqVo.getSelectedField().lastIndexOf("data");
			String id=fqVo.getSelectedField().substring(count+4);
			BasStuff rep=null;
			if(list!=null&&list.size()>0){
				for (BasStuff bs : list) {
					if(bs!=null)
						if(bs.getStfId().toString().equals(id)){
							rep=bs;
							break;
						}
				}
				if(rep!=null){
					list.clear();
					list.add(rep);				
				}				
			}
		}
	}
	/**
	 * 查找维修接待员分析饼图
	 * */
	
	public JFreeChart findCakeMap(FrtQueryVo fqVo) throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<BasStuff> list=basStuffDao.find("from BasStuff bs where bs.enterpriseInfo.enterpriseId="+fqVo.getEnterpriseId());
		fqVo.setFlag(true);
		String source=findServiceDestClerkAnalyse(fqVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelected(list,fqVo);
			Double value=null;
			for (BasStuff bs : list) {
				value=Double.parseDouble(Ognl.getValue("data"+bs.getStfId(), rows.get(0)).toString());
				cakeHashMap.put(bs.getStfName(), value);
			}			
		}
		String cakeName="维修接待员分析饼图";
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0){
			cakeName="("+fqVo.getEnrolTime()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap, true,fqVo.getIs3D());
	}
	/**
	 * 查找维修接待员分析折线图
	 * */
	
	public JFreeChart findSnapMap(FrtQueryVo fqVo) throws Exception {
		List<SnapMap> snapList=new ArrayList();
		List<BasStuff> list=basStuffDao.find("from BasStuff bs where bs.enterpriseInfo.enterpriseId="+fqVo.getEnterpriseId());
		isSelected(list,fqVo);
		fqVo.setFlag(true);
		String source=findServiceDestClerkAnalyse(fqVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			String tempField="sumCount";
			if(fqVo.getSelectedField()!=null&&fqVo.getSelectedField().length()>0&&(!fqVo.getSelectedField().equals("enrolTime"))){
				tempField=fqVo.getSelectedField();
			}
			SnapMap sm=null;
			if(rows!=null&&rows.size()>0){
				for (Object object : rows) {		
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
					sm.setSnapXData(Ognl.getValue("enrolTime", object).toString());
					if(list!=null&&list.size()==1){
						sm.setSnapLineName(list.get(0).getStfName()+"接待量折线");
					}else{
						sm.setSnapLineName("接待量折线");					}
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}
			}			
		}
		String snapName="维修接待员分析折线图";
		if(list!=null&&list.size()==1){
			snapName="("+list.get(0).getStfName()+")"+snapName;
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "接待量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,fqVo.getIs3D());
	}
	/**
	 * 获取维修类别分析饼图信息
	 * */
	
	public JFreeChart findClassCakeMap(FrtQueryVo fqVo) throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<Reptype> list=reptypeDao.find("from Reptype rt where rt.enterpriseId="+fqVo.getEnterpriseId());
		fqVo.setFlag(true);
		String source=findServiceClassAnalyse(fqVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelectedClass(list,fqVo);
			Double value=null;
			if(list!=null&&list.size()>0)
			for (Reptype rt : list) {
				value=Double.parseDouble(Ognl.getValue("data"+rt.getReptId(), rows.get(0)).toString());
				cakeHashMap.put(rt.getReptName(), value);
			}			
		}
		String cakeName="维修类别分析饼图";
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0){
			cakeName="("+fqVo.getEnrolTime()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,null,null,null,null,true,null,null,null,
				null,null,null,null,null,null,null,null,null,null,true,fqVo.getIs3D());
	}
	/**
	 * 获取维修类别分析折线图信息
	 * */
	
	public JFreeChart findClassSnapMap(FrtQueryVo fqVo) throws Exception {
		List<SnapMap> snapList=new ArrayList();
		List<Reptype> list=reptypeDao.find("from Reptype rt where rt.enterpriseId="+fqVo.getEnterpriseId());
		isSelectedClass(list,fqVo);
		fqVo.setFlag(true);
		String source=findServiceClassAnalyse(fqVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			String tempField="sumCount";
			if(fqVo.getSelectedField()!=null&&fqVo.getSelectedField().length()>0&&(!fqVo.getSelectedField().equals("enrolTime"))){
				tempField=fqVo.getSelectedField();
			}
			SnapMap sm=null;
			if(rows!=null&&rows.size()>0){
				for (Object object : rows) {		
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
					sm.setSnapXData(Ognl.getValue("enrolTime", object).toString());
					if(list!=null&&list.size()==1){
						sm.setSnapLineName(list.get(0).getReptName()+"维修量折线");
					}else{
						sm.setSnapLineName("维修量折线");					}
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}
			}			
		}
		String snapName="维修类别分析折线图";
		if(list!=null&&list.size()==1){
			snapName="("+list.get(0).getReptName()+")"+snapName;
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "维修量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,fqVo.getIs3D());
	}
	/**
	 * 查找维修类别分析数据
	 * */
	
	public String findServiceClassAnalyse(FrtQueryVo fqVo) throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype rt where rt.enterpriseId="+fqVo.getEnterpriseId());
		isSelectedClass(list,fqVo);
		StringBuffer sb=new StringBuffer("SELECT aa.preclrTime,");
		if(list!=null&&list.size()>0){
			for (Reptype rt : list) {
				if(rt!=null)
					sb.append(" COUNT(aa.data"+rt.getReptId()+"),");
			}			
		}else{
			return null;			
		}
		String sql1=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql1);
		sb.append(" FROM ( SELECT Date(fpc.PRECLR_TIME) AS preclrTime,");
		if(list!=null&&list.size()>0)
		for (Reptype rt : list) {
			if(rt!=null){
				sb.append(" (SELECT temp.preclr_id FROM frt_reception frt,frt_pre_clearing temp");
				sb.append(" WHERE frt.reception_id=temp.reception_id and temp.preclr_id=fpc.preclr_id ");
				sb.append(" AND temp.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" and frt.REPT_ID="+rt.getReptId()+"  and  frt.enterprise_id="+fqVo.getEnterpriseId() +" ) AS  data"+rt.getReptId()+",");
			}
		}
		String sql=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql);
		sb.append(" FROM frt_pre_clearing fpc");
		sb.append(" WHERE fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+"  and  fpc.enterprise_id="+fqVo.getEnterpriseId() +") aa where 1=1");
		if(fqVo.getEnrolTime()!=null&&fqVo.getEnrolTime().length()>0)
			sb.append(" and aa.preclrTime='"+fqVo.getEnrolTime()+"'");
		if(fqVo.getBeginTime()!=null&&fqVo.getBeginTime().length()>0){
			sb.append(" and aa.preclrTime>='"+fqVo.getBeginTime()+"'");
		}
		if(fqVo.getEndTime()!=null&&fqVo.getEndTime().length()>0){
			sb.append(" and aa.preclrTime<='"+fqVo.getEndTime()+"'");
		}
		sb.append(" GROUP BY aa.preclrTime");
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), fqVo.getPage(), fqVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatServiceClassAnalyze(list,rows,total);
		if(fqVo.getFlag()!=null&&fqVo.getFlag()==true){
			return source;
		}
		return weaveClassDatagrid(source,list);
	}
	private String weaveClassDatagrid(String source,List<Reptype> list){
		StringBuffer sb=new StringBuffer("$('#serviceClassAnalyseDatagrid').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/serviceDestClerkAnalyseAction!findServiceClassAnalyse.action?flag=true',");
		sb.append("fit:true,fitColumns:true,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'enrolTime',title:'时间段',width:100,sortable:false},");
		if(list!=null&&list.size()>0)
		for (Reptype rt : list) {
			if(rt!=null)
				sb.append("{field:'data"+rt.getReptId()+"',title:'"+rt.getReptName()+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append("onSelect:function(rowIndex, rowData){");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img  onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findClassCakeMap.action?page=1&rows=1&enrolTime=\"+rowData.enrolTime+\"&\"");
		sb.append("+$('#serviceClassAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append("},");
		sb.append("onClickCell:function(rowIndex, field, value){");
		sb.append("analyseLoader('analyseLoaderSnapMap', 'snapMapImg');");
		sb.append("var data=$('#serviceClassAnalyseDatagrid').datagrid('options');");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findClassSnapMap.action?page=\"+data.pageNumber+\"&rows=\"+data.pageSize+\"&\"");
		sb.append("+$('#serviceClassAnalyseQueryForm').serialize()+\"&selectedField=\"+field+\" \\\" />\";");
		sb.append("},");
		sb.append("onLoadSuccess:function(data){");
		sb.append("if(tag){");
		sb.append("if($('#serviceClassAnalyseQueryForm').form('validate')){");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("analyseLoader('analyseLoaderSnapMap', 'snapMapImg');");
		sb.append("var params=$('#serviceClassAnalyseDatagrid').datagrid('options');");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findClassSnapMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");	
		sb.append("+$('#serviceClassAnalyseQueryForm').serialize()+\" \\\" />\";");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"serviceDestClerkAnalyseAction!findClassCakeMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");		
		sb.append("+$('#serviceClassAnalyseQueryForm').serialize()+\" \\\"/>\";");
		sb.append("}else{");
		sb.append("alertMsg('对不起，请输入正确的查询条件！', 'warning');");
		sb.append("}");
		sb.append("}");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	private String formatServiceClassAnalyze(List<Reptype> list ,List<Object[]> rows,Integer total){
		int[] toteCounts=new int[list.size()+1];
		StringBuffer sb1=new StringBuffer("\"rows\":[");
		if(rows!=null&&rows.size()>0){
			for (Object[] obj : rows) {
				sb1.append("{");
				sb1.append("\"enrolTime\":\""+obj[0]+"\",");
				int i=1;
				int sumCount=0;
				if(list!=null&&list.size()>0)
					for (Reptype rt : list) {
						if(rt!=null){
							sb1.append("\"data"+rt.getReptId()+"\":"+obj[i]+",");
							sumCount+=Integer.parseInt(obj[i].toString());
							toteCounts[i-1]+=Integer.parseInt(obj[i].toString());
							i++;					
						}
					}
				sb1.append("\"sumCount\":"+sumCount+"");
				toteCounts[toteCounts.length-1]+=sumCount;
				sb1.append("},");
			}
			String s1=sb1.substring(0,sb1.length()-1);
			sb1=new StringBuffer(s1);			
		}
		sb1.append("],\"total\":"+total+"}");
		/*****/
		StringBuffer sb2=new StringBuffer("{\"footer\":[{");
		sb2.append("\"enrolTime\":\"合计\",");
		int i=0;
		if(list!=null&&list.size()>0)
		for (Reptype rt : list) {
			if(rt!=null){
				sb2.append("\"data"+rt.getReptId()+"\":"+toteCounts[i]+",");
				i++;				
			}
		}
		sb2.append("\"sumCount\":\""+toteCounts[toteCounts.length-1]+"\"}],");
		sb2.append(sb1);
		return sb2.toString();
	}
	private void isSelectedClass(List<Reptype> list,FrtQueryVo fqVo){
		if(fqVo.getSelectedField()!=null&&fqVo.getSelectedField().length()>0){
			int count=fqVo.getSelectedField().lastIndexOf("data");
			String id=fqVo.getSelectedField().substring(count+4);
			Reptype rte=null;
			if(list!=null&&list.size()>0){
				for (Reptype rt : list) {
					if(rt!=null)
						if(rt.getReptId().toString().equals(id)){
							rte=rt;
							break;
						}
				}
				if(rte!=null){
					list.clear();
					list.add(rte);				
				}				
			}
		}
	}
	/**
	 * 查找保险送修人分析明细数据
	 * */
	
	public Datagrid findInsurePersonAnalyseDetail(FrtQueryVo fqVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fr.reception_rep_per,fpc.preclr_id,fpc.preclr_time,fr.reception_id,");
		sb.append(" fcar.car_license,fc.custom_name,fpc.pre_mpr_mat_amount,");
		sb.append(" fpc.preclr_wktime_amount,fpc.preclr_real_amount,");
		sb.append(" fpc.preclr_notax_cost,fpc.preclr_tax_cost");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		if(fqVo.getBeginTime()!=null&&fqVo.getBeginTime().length()>0){
			sb.append(" and fpc.preclr_time>='"+fqVo.getBeginTime()+"'");
		}
		if(fqVo.getEndTime()!=null&&fqVo.getEndTime().length()>0){
			sb.append(" and fpc.preclr_time<='"+fqVo.getEndTime()+"'");
		}
		sb.append(" AND fr.reception_rep_per!=''");
		if(fqVo.getInsurePerson()!=null&&fqVo.getInsurePerson().length()>0){
			if(fqVo.getFlag()!=null&&fqVo.getFlag()==true){
				sb.append(" AND fr.reception_rep_per='"+fqVo.getInsurePerson().trim()+"'");				
			}else{
				sb.append(" AND fr.reception_rep_per like '%"+StringEscapeUtils.escapeSql(fqVo.getInsurePerson().trim())+"%'");
			}
		}
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fpc.pre_flg="+Contstants.DELETE_TAG.DELETENO);
		List<FrtQueryVo> rows=new ArrayList(); 
		List<Object[]> list=systemLogDao.createSQLQuery(sb.toString(), fqVo.getPage(), fqVo.getRows());
		FrtQueryVo fqv=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				fqv=new FrtQueryVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					fqv.setInsurePerson(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					fqv.setPreclrId(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					fqv.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[2].toString()));
				if(obj[3]!=null&&obj[3].toString().length()>0)
					fqv.setReceptionId(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					fqv.setCarLicense(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					fqv.setCustomName(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					fqv.setPartsAmount(Double.parseDouble(obj[6].toString()));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					fqv.setItemAmount(Double.parseDouble(obj[7].toString()));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					fqv.setSumAmount(Double.parseDouble(obj[8].toString()));
				if(obj[9]!=null&&obj[9].toString().length()>0)
					fqv.setNoTaxCost(Double.parseDouble(obj[9].toString()));
				if(obj[10]!=null&&obj[10].toString().length()>0)
					fqv.setTaxCost(Double.parseDouble(obj[10].toString()));
				rows.add(fqv);
			}
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找保险送修人分析汇总数据
	 * */
	
	public DatagridAnalyze findInsurePersonAnalyseMain(FrtQueryVo fqVo) throws Exception {
		setDefaultPreclrTimeSect(fqVo);
		StringBuffer sb=new StringBuffer("SELECT fr.reception_rep_per,COUNT(fpc.preclr_id) AS temp1,SUM(fpc.pre_mpr_mat_amount) AS temp2,");
		sb.append(" SUM(fpc.preclr_wktime_amount) AS temp3,SUM(fpc.preclr_real_amount) AS temp4,");
		sb.append(" SUM(fpc.preclr_notax_cost) AS temp5, SUM(fpc.preclr_tax_cost) AS temp6");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fpc.pre_flg="+Contstants.DELETE_TAG.DELETENO);
		sb.append("  and  fpc.enterprise_id="+fqVo.getEnterpriseId()  );
		if(fqVo.getBeginTime()!=null&&fqVo.getBeginTime().length()>0){
			sb.append(" and fpc.preclr_time>='"+fqVo.getBeginTime()+"'");
		}
		if(fqVo.getEndTime()!=null&&fqVo.getEndTime().length()>0){
			sb.append(" and fpc.preclr_time<='"+fqVo.getEndTime()+"'");
		}
		if(fqVo.getBeginTime()==null && fqVo.getEndTime()!=null){
			sb.append(" and DATE_FORMAT( fpc.preclr_time,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,fqVo.getEnterpriseId()).getCiValue()))+ "'");
			sb.append(" and DATE_FORMAT( fpc.preclr_time,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");	
		}
		sb.append(" and fr.reception_rep_per!=''");
		if(fqVo.getInsurePerson()!=null&&fqVo.getInsurePerson().length()>0){
			sb.append(" AND fr.reception_rep_per like '%"+StringEscapeUtils.escapeSql(fqVo.getInsurePerson().trim())+"%'");
		}
	
		sb.append(" GROUP BY fr.reception_rep_per");
		List<FrtQueryVo> footers=new ArrayList(); 
		List<FrtQueryVo> rows=new ArrayList(); 
		List<Object[]> list=systemLogDao.createSQLQuery(sb.toString(), fqVo.getPage(), fqVo.getRows());
		FrtQueryVo fqv=null;
		FrtQueryVo fqvs=new FrtQueryVo();
		fqvs.setPreclrCount(0);
		fqvs.setPartsAmount(0d);
		fqvs.setItemAmount(0d);
		fqvs.setSumAmount(0d);
		fqvs.setTaxCost(0d);
		fqvs.setNoTaxCost(0d);
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				fqv=new FrtQueryVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					fqv.setInsurePerson(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					fqv.setPreclrCount(Integer.parseInt(obj[1].toString()));
				fqvs.setPreclrCount(fqvs.getPreclrCount()+fqv.getPreclrCount());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					fqv.setPartsAmount(Double.parseDouble(obj[2].toString()));
				fqvs.setPartsAmount(fqvs.getPartsAmount()+fqv.getPartsAmount());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					fqv.setItemAmount(Double.parseDouble(obj[3].toString()));
				fqvs.setItemAmount(fqvs.getItemAmount()+fqv.getItemAmount());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					fqv.setSumAmount(Double.parseDouble(obj[4].toString()));
				fqvs.setSumAmount(fqvs.getSumAmount()+fqv.getSumAmount());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					fqv.setNoTaxCost(Double.parseDouble(obj[5].toString()));
				fqvs.setNoTaxCost(fqvs.getNoTaxCost()+fqv.getNoTaxCost());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					fqv.setTaxCost(Double.parseDouble(obj[6].toString()));
				fqvs.setTaxCost(fqvs.getTaxCost()+fqv.getTaxCost());
				rows.add(fqv);
			}
		fqvs.setInsurePerson("合计");
		footers.add(fqvs);
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	private void setDefaultPreclrTimeSect(FrtQueryVo fqVo)throws Exception{
		if((fqVo.getBeginTime()==null||fqVo.getBeginTime().length()==0)&&
				(fqVo.getEndTime()==null||fqVo.getEndTime().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				fqVo.setEndTime(MyBeanUtils.getInstance().getString(date));
				fqVo.setBeginTime(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
	
}
