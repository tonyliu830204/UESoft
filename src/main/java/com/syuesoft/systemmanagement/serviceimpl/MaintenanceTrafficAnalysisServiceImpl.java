package com.syuesoft.systemmanagement.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ognl.Ognl;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.dao.ReptypeDao;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.Reptype;
import com.syuesoft.systemmanagement.dao.SystemLogDao;
import com.syuesoft.systemmanagement.service.MaintenanceTrafficAnalysisService;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisServiceLogVo;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisServiceTimeAnalyzeVo;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisServicingCarAnalyzeVo;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisVo;
import com.syuesoft.util.CakeMap;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.MyBeanUtils;
import com.syuesoft.util.PoleMap;
import com.syuesoft.util.SnapMap;
/**
 * 维修客流分析service
 */
@Service("maintenanceTrafficAnalysisService")
public class MaintenanceTrafficAnalysisServiceImpl implements MaintenanceTrafficAnalysisService{
	@Autowired
	private SystemLogDao systemLogDao;
	@Autowired
	private ReptypeDao reptypeDao;
	/**
	 * 查找维修品牌分析信息
	 * */
	
	public DatagridAnalyze findMaintenanceTrafficAnalysisServiceBrandAnalyze(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查找维修记录信息
	 * */
	
	public Datagrid findMaintenanceTrafficAnalysisServiceLog(MaintenanceTrafficAnalysisVo mtaVo) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("SELECT DISTINCT DATE(fr.INTER_DATE) FROM frt_reception fr WHERE fr.enterprise_id="+mtaVo.getEnterpriseId()+" AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
		if(mtaVo.getServiceDateBegin()!=null&&mtaVo.getServiceDateBegin().toString().length()>0){
			sb.append(" AND fr.INTER_DATE>='"+mtaVo.getServiceDateBegin()+"'");
		}
		if(mtaVo.getServiceDateEnd()!=null&&mtaVo.getServiceDateEnd().toString().length()>0){
			sb.append(" AND fr.INTER_DATE<='"+mtaVo.getServiceDateEnd()+"'");
		}
		if(mtaVo.getReceivePerson()!=null&&mtaVo.getReceivePerson().length()>0){
			sb.append(" AND fr.RECEPTOR="+mtaVo.getReceivePerson());
		}
		List rowsList=systemLogDao.createSQLQuery(sb.toString(),null,mtaVo.getPage(),mtaVo.getRows());
		List<MaintenanceTrafficAnalysisServiceLogVo> rows=new ArrayList<MaintenanceTrafficAnalysisServiceLogVo>();
		MaintenanceTrafficAnalysisServiceLogVo mraslv=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object objects : rowsList) {
			mraslv=new MaintenanceTrafficAnalysisServiceLogVo();
			mraslv.setInterDate(objects.toString());
			mraslv.setState("closed");
			rows.add(mraslv);
		}
		int total=systemLogDao.getSQLCount(sb.toString(),null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找维修记录信息-子项
	 * */
	
	public List findMaintenanceTrafficAnalysisServiceLogByInterDate(
			MaintenanceTrafficAnalysisVo mtaVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fr.INTER_DATE,fr.RECEPTION_ID,fcar.CAR_LICENSE,fc.CUSTOM_NAME,");
		sb.append(" bct.CTYPE_NAME,bs.STF_NAME,rt.REPT_NAME,fc.CUSTOM_TEL1,fcar.CAR_MOTOR_ID,");
		sb.append(" fcar.CAR_VIN,fr.RECEPTION_DISTANCE,fcar.CAR_BUY_DATE,");
		sb.append(" fr.PROP_REP_PER,fr.PROP_PHONE,'' AS addr");
		sb.append(" FROM frt_reception fr,frt_custom fc,frt_car fcar,");
		sb.append(" bas_car_type bct,bas_stuff bs,reptype rt");
		sb.append(" WHERE fc.CUSTOM_ID=fr.CUSTOM_ID and fr.enterprise_id="+mtaVo.getEnterpriseId());
		sb.append(" AND fcar.CAR_ID=fr.CAR_ID AND fcar.CTYPE_ID=bct.CTYPE_ID");
		sb.append(" AND bs.STF_ID=fr.RECEPTOR AND rt.REPT_ID=fr.REPT_ID");
		sb.append(" AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
		sb.append(" AND fr.INTER_DATE>='"+MyBeanUtils.getInstance().formatStringAsTime(mtaVo.get_parentId())+"'");
		sb.append(" AND fr.INTER_DATE<'"+MyBeanUtils.getInstance().DateAddOneDay(mtaVo.get_parentId())+"'");
		if(mtaVo.getCtypeId()!=null&&mtaVo.getCtypeId().length()>0){
			sb.append(" AND bct.CTYPE_ID="+mtaVo.getCtypeId());
		}
		if(mtaVo.getReceivePerson()!=null&&mtaVo.getReceivePerson().length()>0){
			sb.append(" AND bs.STF_ID="+mtaVo.getReceivePerson());
		}
		List<Object[]> rowsList=systemLogDao.createSQLQuery(sb.toString());
		List<MaintenanceTrafficAnalysisServiceLogVo> rows=new ArrayList();
		MaintenanceTrafficAnalysisServiceLogVo mraslv=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			mraslv=new MaintenanceTrafficAnalysisServiceLogVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				mraslv.setInterDate(MyBeanUtils.getInstance().formatString(objects[0].toString()));
			if(objects[1]!=null&&objects[1].toString().length()>0)
				mraslv.setReceptionId(objects[1].toString());
			if(objects[2]!=null&&objects[2].toString().length()>0)
				mraslv.setCarLicense(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				mraslv.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				mraslv.setCarTypeName(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				mraslv.setStfName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				mraslv.setReptName(objects[6].toString());
			if(objects[7]!=null&&objects[7].toString().length()>0)
				mraslv.setCustomTel(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				mraslv.setCarMotorId(objects[8].toString());
			if(objects[9]!=null&&objects[9].toString().length()>0)
				mraslv.setCarVin(objects[9].toString());
			if(objects[10]!=null&&objects[10].toString().length()>0)
				mraslv.setReceptionDistance(objects[10].toString());
			if(objects[11]!=null&&objects[11].toString().length()>0)
				mraslv.setCarBuyDate(MyBeanUtils.getInstance().formatString(objects[11].toString()));
			if(objects[12]!=null&&objects[12].toString().length()>0)
				mraslv.setRecommonendPerson(objects[12].toString());
			if(objects[13]!=null&&objects[13].toString().length()>0)
				mraslv.setRecommonendTel(objects[13].toString());
			if(objects[14]!=null&&objects[14].toString().length()>0)
				mraslv.setRecommonendAddr(objects[14].toString());
			mraslv.set_parentId(mtaVo.get_parentId());
			mraslv.setReceptionItems(weaveRepitemName(mraslv.getReceptionId()));
			mraslv.setState("open");
			rows.add(mraslv);
		}
		return rows;
	}
	private String weaveRepitemName(String receptionId) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT fri.REPITEM_NAME,bs.STF_NAME FROM frt_rcpt_item fri,bas_stuff bs");
		sb.append(" WHERE bs.STF_ID=fri.STF_ID  AND fri.RECEPTION_ID='"+receptionId+"'");
		List<Object[]> rowsList = systemLogDao.createSQLQuery(sb.toString());
		StringBuffer sbs=new StringBuffer();
		if(rowsList!=null&&rowsList.size()>0){
			for (Object[] objects : rowsList) {
				sbs.append(objects[0]+"("+objects[1]+"),");
			}			
		}
		if(sbs.length()>0)
			return sbs.substring(0, sbs.length()-1);
		else
		 return "";
	}
	private void isSelected(List<Reptype> list,MaintenanceTrafficAnalysisVo mtaVo){
		if(mtaVo.getSelectedField()!=null&&mtaVo.getSelectedField().length()>0){
			int count=mtaVo.getSelectedField().lastIndexOf("data");
			String id=mtaVo.getSelectedField().substring(count+4);
			Reptype rep=null;
			for (Reptype reptype : list) {
				if(reptype.getReptId().toString().equals(id)){
					rep=reptype;
					break;
				}                                                               
			}
			if(rep!=null){
				list.clear();
				list.add(rep);				
			}
		}
	}
	/**
	 * 查找维修接待员分析信息
	 * */
	
	public String findMaintenanceTrafficAnalysisServiceReceiveAnalyze(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype  where  enterpriseId="+mtaVo.getEnterpriseId());
		isSelected(list,mtaVo);
		StringBuffer sb=new StringBuffer("SELECT aa.stf_id,aa.stf_name,");
		if(list!=null&&list.size()>0){
			for (Reptype reptype : list) {
				sb.append(" COUNT(aa.data"+reptype.getReptId()+"),");
			}			
		}else{
			return null;
		}
		String sql1=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql1);
		sb.append(" FROM ( SELECT bs.stf_id,bs.stf_name,");
		if(list!=null&&list.size()>0)
		for (Reptype reptype : list) {
			sb.append(" (SELECT temp.reception_id FROM frt_reception temp");
			sb.append(" WHERE frt.reception_id=temp.reception_id and frt.rept_id="+reptype.getReptId()+" ) AS  data"+reptype.getReptId()+",");
		}
		String sql=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql);
		sb.append(" FROM frt_reception frt,frt_car fc,bas_stuff bs");
		sb.append(" WHERE frt.CAR_ID=fc.CAR_ID AND bs.stf_id=frt.RECEPTOR and frt.enterprise_id="+mtaVo.getEnterpriseId()+") aa where 1=1");
		if(mtaVo.getReceivePerson()!=null&&mtaVo.getReceivePerson().length()>0)
			sb.append(" and aa.stf_id="+mtaVo.getReceivePerson().trim()+"");
		sb.append(" GROUP BY aa.stf_id");
		List<Object[]> rows=systemLogDao.createSQLQuery(sb.toString(), mtaVo.getPage(), mtaVo.getRows());
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		String source=formatServiceReceiveAnalyze(list,rows,total);
		if(mtaVo.getFlag()!=null&&mtaVo.getFlag()==true){
			return source;
		}
		return weaveDatagrid(source,list);
	}
	private String weaveDatagrid(String source,List<Reptype> list){
		StringBuffer sb=new StringBuffer("$('#maintenanceTrafficAnalysis_serviceReceiveAnalyze').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceReceiveAnalyze.action?flag=true',");
		sb.append("fit:true,fitColumns:true,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'stfId',title:'接待员',width:100,sortable:false,formatter: function(value,row,index){");
		sb.append("return row.stfName;");			
		sb.append("}},");
		sb.append("{field:'stfName',title:'接待员',width:80,hidden:true},");
		if(list!=null&&list.size()>0)
		for (Reptype reptype : list) {
			sb.append("{field:'data"+reptype.getReptId()+"',title:'"+reptype.getReptName()+"',width:80,sortable:false},");
		}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append("onSelect:function(rowIndex, rowData){");
		sb.append("if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"maintenanceTrafficAnalysisAction!findServiceReceiveAnalyzeCakeMap.action?page=1&rows=1&stfId=\"+rowData.stfId+\"&\"");
		sb.append("+$('#maintenanceTrafficAnalysisQueryForm').serialize()+\" \\\"/>\";");
		sb.append("}else{");
		sb.append("alertMsg('对不起，请输入正确的查询条件！', 'warning');");
		sb.append("}");		
		sb.append("},");
		sb.append("onClickCell:function(rowIndex, field, value){");
		sb.append("if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){");
		sb.append("analyseLoader('analyseLoaderPoleMap', 'poleMapImg');");
		sb.append("var data=$('#maintenanceTrafficAnalysis_serviceReceiveAnalyze').datagrid('options');");
		sb.append("document.getElementById(\"poleMapImg\").innerHTML=");
		sb.append("\"<img  onload=\\\"analyseLoaderHidden('analyseLoaderPoleMap','poleMapImg');\\\" src=\\\"maintenanceTrafficAnalysisAction!findServiceReceiveAnalyzePoleMap.action?page=\"+data.pageNumber+\"&rows=\"+data.pageSize+\"&\"");
		sb.append("+$('#maintenanceTrafficAnalysisQueryForm').serialize()+\"&selectedField=\"+field+\" \\\" />\";");
		sb.append("}else{");
		sb.append("alertMsg('对不起，请输入正确的查询条件！', 'warning');");	
		sb.append("}");		
		sb.append("},");
		sb.append("onLoadSuccess:function(data){");
		sb.append("if(staticFlag==true){");
		sb.append("if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){");
		sb.append("var params=$('#maintenanceTrafficAnalysis_serviceReceiveAnalyze').datagrid('options');");
		sb.append("analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');");
		sb.append("analyseLoader('analyseLoaderPoleMap', 'poleMapImg');");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\\\" src=\\\"maintenanceTrafficAnalysisAction!findServiceReceiveAnalyzeCakeMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");		
		sb.append("+$('#maintenanceTrafficAnalysisQueryForm').serialize()+\" \\\"/>\";");
		sb.append("document.getElementById(\"poleMapImg\").innerHTML=");
		sb.append("\"<img onload=\\\"analyseLoaderHidden('analyseLoaderPoleMap','poleMapImg');\\\" src=\\\"maintenanceTrafficAnalysisAction!findServiceReceiveAnalyzePoleMap.action?page=\"+params.pageNumber+\"&rows=\"+params.pageSize+\"&\"");	
		sb.append("+$('#maintenanceTrafficAnalysisQueryForm').serialize()+\" \\\" />\";");
		sb.append("}else{");		
		sb.append("alertMsg('对不起，请输入正确的查询条件！', 'warning');");	
		sb.append("}");
		sb.append("}");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	private String formatServiceReceiveAnalyze(List<Reptype> list ,List<Object[]> rows,Integer total){
		int[] toteCounts=new int[list.size()+1];
		StringBuffer sb1=new StringBuffer("\"rows\":[");
		if(rows!=null&&rows.size()>0)
		for (Object[] obj : rows) {
			sb1.append("{");
			sb1.append("\"stfId\":\""+obj[0]+"\",");
			sb1.append("\"stfName\":\""+obj[1]+"\",");
			int i=2;
			int sumCount=0;
			if(list!=null&&list.size()>0)
			for (Reptype reptype : list) {
				sb1.append("\"data"+reptype.getReptId()+"\":"+obj[i]+",");
				sumCount+=Integer.parseInt(obj[i].toString());
				toteCounts[i-2]+=Integer.parseInt(obj[i].toString());
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
		sb2.append("\"stfName\":\"合计\",");
		int i=0;
		if(list!=null&&list.size()>0)
		for (Reptype reptype : list) {
			sb2.append("\"data"+reptype.getReptId()+"\":"+toteCounts[i]+",");
			i++;
		}
		sb2.append("\"sumCount\":\""+toteCounts[toteCounts.length-1]+"\"}],");
		sb2.append(sb1);
		return sb2.toString();
	}
	/**
	 * 查找维修时间段分析信息
	 * */
	
	public DatagridAnalyze findMaintenanceTrafficAnalysisServiceTimeAnalyze(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT DATE(fr.RESV_REAL_TIME) AS temp,");
		
		if(mtaVo.getSelectedField()!=null&&mtaVo.getSelectedField().length()>0){
			if(mtaVo.getSelectedField().equals("eightBefore")){
				sb.append(" SUM(DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<8),'' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("eightAndNine")){
				sb.append(" '' as temp1,SUM(8<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<9),'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("nineAndTen")){
				sb.append(" '' as temp1,'' as temp2,SUM(9<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<10),'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("tenAndEleven")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,SUM(10<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<11),'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("elevenAndTwelve")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,SUM(11<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<12),'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("tweleveAndThirteen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,SUM(12<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<13),'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("thirteenAndFourteen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,SUM(13<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<14),'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("fourteenAndFifteen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,SUM(14<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<15),'' as temp8,'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("fifteenAndSixteen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,SUM(15<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<16),'' as temp9,'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("sixteenAndSeventeen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,SUM(16<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<17),'' as temp10,'' as temp11");
			}else if(mtaVo.getSelectedField().equals("seventeenAndEighteen")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,SUM(17<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<18),'' as temp11");
			}else if(mtaVo.getSelectedField().equals("enighteenAfter")){
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11,SUM(18<DATE_FORMAT(fr.RESV_REAL_TIME,'%H'))");
			}else{
				sb.append(" '' as temp1,'' as temp2,'' as temp3,'' as temp4,'' as temp5,'' as temp6,'' as temp7,'' as temp8,'' as temp9,'' as temp10,'' as temp11,'' as temp12");
			}
			sb.append(" FROM frt_resevation fr,frt_car fc ");
			sb.append(" WHERE fr.CAR_ID=fc.CAR_ID AND fc.enterprise_id="+mtaVo.getEnterpriseId());
		}else{
			sb.append(" SUM(DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<8),");
			sb.append(" SUM(8<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<9),");
			sb.append(" SUM(9<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<10),");
			sb.append(" SUM(10<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<11),");
			sb.append(" SUM(11<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<12),");
			sb.append(" SUM(12<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<13),");
			sb.append(" SUM(13<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<14),");
			sb.append(" SUM(14<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<15),");
			sb.append(" SUM(15<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<16),");
			sb.append(" SUM(16<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<17),");
			sb.append(" SUM(17<DATE_FORMAT(fr.RESV_REAL_TIME,'%H')<18),");
			sb.append(" SUM(18<DATE_FORMAT(fr.RESV_REAL_TIME,'%H'))");
			sb.append(" FROM frt_resevation fr,frt_car fc ");
			sb.append(" WHERE fr.CAR_ID=fc.CAR_ID");			
		}
		sb.append(" AND fr.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO);
		if(mtaVo.getServiceDateBegin()!=null&&mtaVo.getServiceDateBegin().length()>0){
			sb.append(" AND fr.RESV_REAL_TIME>='"+mtaVo.getServiceDateBegin()+"'");
		}
		if(mtaVo.getServiceDateEnd()!=null&&mtaVo.getServiceDateEnd().length()>0){
			sb.append(" AND fr.RESV_REAL_TIME<='"+mtaVo.getServiceDateEnd()+"'");
		}
		if(mtaVo.getCtypeId()!=null&&mtaVo.getCtypeId().length()>0){
			sb.append(" AND fc.CTYPE_ID="+mtaVo.getCtypeId());
		}
		if(mtaVo.getReceivePerson()!=null&&mtaVo.getReceivePerson().length()>0){
			sb.append(" AND fr.STF_ID="+mtaVo.getReceivePerson());
		}
		sb.append(" GROUP BY DATE(fr.RESV_REAL_TIME)");
		if(mtaVo.getEnrolDate()!=null&&mtaVo.getEnrolDate().length()>0){
			sb.append(" HAVING temp='"+mtaVo.getEnrolDate()+"'");
		}
		List<Object[]> rowsList=systemLogDao.createSQLQuery(sb.toString(),mtaVo.getPage(),mtaVo.getRows());
		List<MaintenanceTrafficAnalysisServiceTimeAnalyzeVo> rows=new ArrayList();
		MaintenanceTrafficAnalysisServiceTimeAnalyzeVo temp=new MaintenanceTrafficAnalysisServiceTimeAnalyzeVo();
		MaintenanceTrafficAnalysisServiceTimeAnalyzeVo timeVo=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			timeVo=new MaintenanceTrafficAnalysisServiceTimeAnalyzeVo();
			int sumCount=0;
			if(objects[0]!=null&&objects[0].toString().length()>0){
				timeVo.setEnrolDate(objects[0].toString());				
			}
			if(objects[1]!=null&&objects[1].toString().length()>0){
				timeVo.setEightBefore(Integer.parseInt(objects[1].toString()));
				sumCount+=timeVo.getEightBefore();
				temp.setEightBefore(temp.getEightBefore()+timeVo.getEightBefore());
			}
			if(objects[2]!=null&&objects[2].toString().length()>0){
				timeVo.setEightAndNine(Integer.parseInt(objects[2].toString()));
				sumCount+=timeVo.getEightAndNine();
				temp.setEightAndNine(temp.getEightAndNine()+timeVo.getEightAndNine());
			}
			if(objects[3]!=null&&objects[3].toString().length()>0){
				timeVo.setNineAndTen(Integer.parseInt(objects[3].toString()));
				sumCount+=timeVo.getNineAndTen();
				temp.setNineAndTen(temp.getNineAndTen()+timeVo.getNineAndTen());
			}
			if(objects[4]!=null&&objects[4].toString().length()>0){
				timeVo.setTenAndEleven(Integer.parseInt(objects[4].toString()));
				sumCount+=timeVo.getTenAndEleven();			
				temp.setTenAndEleven(temp.getTenAndEleven()+timeVo.getTenAndEleven());
			}
			if(objects[5]!=null&&objects[5].toString().length()>0){
				timeVo.setElevenAndTwelve(Integer.parseInt(objects[5].toString()));
				sumCount+=timeVo.getElevenAndTwelve();			
				temp.setElevenAndTwelve(temp.getElevenAndTwelve()+timeVo.getElevenAndTwelve());
			}
			if(objects[6]!=null&&objects[6].toString().length()>0){
				timeVo.setTweleveAndThirteen(Integer.parseInt(objects[6].toString()));
				sumCount+=timeVo.getTweleveAndThirteen();
				temp.setTweleveAndThirteen(temp.getTweleveAndThirteen()+timeVo.getTweleveAndThirteen());
			}
			if(objects[7]!=null&&objects[7].toString().length()>0){
				timeVo.setThirteenAndFourteen(Integer.parseInt(objects[7].toString()));
				sumCount+=timeVo.getThirteenAndFourteen();				
				temp.setThirteenAndFourteen(temp.getThirteenAndFourteen()+timeVo.getThirteenAndFourteen());
			}
			if(objects[8]!=null&&objects[8].toString().length()>0){
				timeVo.setFourteenAndFifteen(Integer.parseInt(objects[8].toString()));
				sumCount+=timeVo.getFourteenAndFifteen();	
				temp.setFourteenAndFifteen(temp.getFourteenAndFifteen()+timeVo.getFourteenAndFifteen());
			}
			if(objects[9]!=null&&objects[9].toString().length()>0){
				timeVo.setFifteenAndSixteen(Integer.parseInt(objects[9].toString()));
				sumCount+=timeVo.getFifteenAndSixteen();				
				temp.setFifteenAndSixteen(temp.getFifteenAndSixteen()+timeVo.getFifteenAndSixteen());
			}
			if(objects[10]!=null&&objects[10].toString().length()>0){
				timeVo.setSixteenAndSeventeen(Integer.parseInt(objects[10].toString()));
				sumCount+=timeVo.getSixteenAndSeventeen();	
				temp.setSixteenAndSeventeen(temp.getSixteenAndSeventeen()+timeVo.getSixteenAndSeventeen());
			}
			if(objects[11]!=null&&objects[11].toString().length()>0){
				timeVo.setSeventeenAndEighteen(Integer.parseInt(objects[11].toString()));
				sumCount+=timeVo.getSeventeenAndEighteen();	
				temp.setSeventeenAndEighteen(temp.getSeventeenAndEighteen()+timeVo.getSeventeenAndEighteen());
			}
			if(objects[12]!=null&&objects[12].toString().length()>0){
				timeVo.setEnighteenAfter(Integer.parseInt(objects[12].toString()));
				sumCount+=timeVo.getEnighteenAfter();
				temp.setEnighteenAfter(temp.getEnighteenAfter()+timeVo.getEnighteenAfter());
			}
			timeVo.setSumCount(sumCount);
			temp.setSumCount(temp.getSumCount()+timeVo.getSumCount());
			rows.add(timeVo);
		}
		temp.setEnrolDate("合计");
		List<MaintenanceTrafficAnalysisServiceTimeAnalyzeVo> footers=new ArrayList();
		footers.add(temp);
		DatagridAnalyze dga=new DatagridAnalyze();
		dga.setRows(rows);
		int total=systemLogDao.getSQLCount(sb.toString(), null);
		dga.setTotal(total);
		dga.setFooter(footers);
		return dga;
	}
	/**
	 * 查找车辆在修情况分析信息
	 * */
	
	public Datagrid findMaintenanceTrafficAnalysisServicingCarAnalyze(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT DISTINCT bcb.CBRD_ID,bcb.CBRD_NAME");
		sb.append(" FROM bas_car_brand bcb,bas_car_type bct,frt_car fcar");
		sb.append(" WHERE bcb.CBRD_ID=bct.CBRD_ID AND fcar.CTYPE_ID=bct.CTYPE_ID and bcb.enterprise_id="+mtaVo.getEnterpriseId());
		if(mtaVo.getCtypeId()!=null&&mtaVo.getCtypeId().length()>0){
			sb.append(" AND bct.CTYPE_ID="+mtaVo.getCtypeId());
		}
		if(mtaVo.getCbrdId()!=null&&mtaVo.getCbrdId().length()>0){
			sb.append(" AND bcb.CBRD_ID="+mtaVo.getCbrdId());
		}
		List<MaintenanceTrafficAnalysisServicingCarAnalyzeVo> rows=new ArrayList<MaintenanceTrafficAnalysisServicingCarAnalyzeVo>();
		List<Object[]> rowsList=systemLogDao.createSQLQuery(sb.toString(),null,mtaVo.getPage(),mtaVo.getRows());
		MaintenanceTrafficAnalysisServicingCarAnalyzeVo mta=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			mta=new MaintenanceTrafficAnalysisServicingCarAnalyzeVo();
			mta.setCbrdId(objects[0].toString());
			mta.setCbrdName(objects[1].toString());
			mta.setState("closed");
			mta.setCarLicense("查看详情");
			rows.add(mta);
		}
		int total=systemLogDao.getSQLCount(sb.toString(),null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找车辆在修情况分析-子项信息
	 * */
	
	public List findMaintenanceTrafficAnalysisServicingCarAnalyzeByCbrdId(
			MaintenanceTrafficAnalysisVo mtaVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fcar.CAR_LICENSE,bct.CTYPE_NAME,");
		sb.append(" fc.CUSTOM_NAME,fcar.CAR_LAST_REPAIR_DATE,fcar.CAR_REPAIR_CNT,");
		sb.append(" fc.CUSTOM_NAME,fc.CUSTOM_ADDR,fc.CUSTOM_TEL1,fc.CUSTOM_TEL2");
		sb.append(" FROM bas_car_brand bcb,bas_car_type bct,frt_car fcar,frt_custom fc");
		sb.append(" WHERE bcb.CBRD_ID=bct.CBRD_ID AND fcar.CTYPE_ID=bct.CTYPE_ID and bcb.enterprise_id="+mtaVo.getEnterpriseId());
		sb.append(" AND fc.CUSTOM_ID=fcar.CUSTOM_ID AND bcb.CBRD_ID="+mtaVo.get_parentId());
		if(mtaVo.getCtypeId()!=null&&mtaVo.getCtypeId().length()>0){
			sb.append(" AND bct.CTYPE_ID="+mtaVo.getCtypeId());
		}
		if(mtaVo.getCbrdId()!=null&&mtaVo.getCbrdId().length()>0){
			sb.append(" AND bcb.CBRD_ID="+mtaVo.getCbrdId());
		}
		List<MaintenanceTrafficAnalysisServicingCarAnalyzeVo> rows=new ArrayList<MaintenanceTrafficAnalysisServicingCarAnalyzeVo>();
		List<Object[]> rowsList=systemLogDao.createSQLQuery(sb.toString());
		MaintenanceTrafficAnalysisServicingCarAnalyzeVo mta=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				mta=new MaintenanceTrafficAnalysisServicingCarAnalyzeVo();
				if(objects[0]!=null&&objects[0].toString().length()>0)
					mta.setCarLicense(objects[0].toString());
				if(objects[1]!=null&&objects[1].toString().length()>0)
					mta.setCtypeName(objects[1].toString());
				if(objects[2]!=null&&objects[2].toString().length()>0)
					mta.setCustomName(objects[2].toString());
				if(objects[3]!=null&&objects[3].toString().length()>0)
					mta.setCarLastRepairDate(MyBeanUtils.getInstance().formatString(objects[3].toString()));
				if(objects[4]!=null&&objects[4].toString().length()>0)
					mta.setCarRepairCnt(objects[4].toString());
				if(objects[5]!=null&&objects[5].toString().length()>0)
					mta.setCustomName(objects[5].toString());
				if(objects[6]!=null&&objects[6].toString().length()>0)
					mta.setCustomAddr(objects[6].toString());
				if(objects[7]!=null&&objects[7].toString().length()>0)
					mta.setCustomTel1(objects[7].toString());
				if(objects[8]!=null&&objects[8].toString().length()>0)
					mta.setCustomTel2(objects[8].toString());
				mta.set_parentId(mtaVo.get_parentId());
				mta.setState("open");
				rows.add(mta);
			}
		return rows;
	}
	/**
	 * 获取维修时间段分析折线图信息 
	 * */
	
	public JFreeChart findServiceTimeAnalyzeSnapMap(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		DatagridAnalyze dga=findMaintenanceTrafficAnalysisServiceTimeAnalyze(mtaVo);
		List<MaintenanceTrafficAnalysisServiceTimeAnalyzeVo> rows=dga.getRows();
		List<SnapMap> snapList=new ArrayList<SnapMap>();
		String snapName="维修时间段分析折线图";
		SnapMap sm=null;
		if(rows!=null&&rows.size()>0){
			if(mtaVo.getSelectedField()!=null&&mtaVo.getSelectedField().length()>0){
				if(mtaVo.getSelectedField().equals("eightBefore")){
					snapName="(08前)"+snapName;
				}else if(mtaVo.getSelectedField().equals("eightAndNine")){
					snapName="(08-09)"+snapName;
				}else if(mtaVo.getSelectedField().equals("nineAndTen")){
					snapName="(09-10)"+snapName;
				}else if(mtaVo.getSelectedField().equals("tenAndEleven")){
					snapName="(10-11)"+snapName;
				}else if(mtaVo.getSelectedField().equals("elevenAndTwelve")){
					snapName="(11-12)"+snapName;
				}else if(mtaVo.getSelectedField().equals("tweleveAndThirteen")){
					snapName="(12-13)"+snapName;
				}else if(mtaVo.getSelectedField().equals("thirteenAndFourteen")){
					snapName="(13-14)"+snapName;
				}else if(mtaVo.getSelectedField().equals("fourteenAndFifteen")){
					snapName="(14-15)"+snapName;
				}else if(mtaVo.getSelectedField().equals("fifteenAndSixteen")){
					snapName="(15-16)"+snapName;
				}else if(mtaVo.getSelectedField().equals("sixteenAndSeventeen")){
					snapName="(16-17)"+snapName;
				}else if(mtaVo.getSelectedField().equals("seventeenAndEighteen")){
					snapName="(17-18)"+snapName;
				}else if(mtaVo.getSelectedField().equals("enighteenAfter")){
					snapName="(18后)"+snapName;
				}
			}			
				for (MaintenanceTrafficAnalysisServiceTimeAnalyzeVo temp : rows) {
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(temp.getSumCount().toString()));
					sm.setSnapXData(temp.getEnrolDate());
					sm.setSnapLineName("接待量折线");
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}	
		}
		/*
		 * snapMapName="无标题";//图表标题
			snapXName="X轴";//目录轴的显示标签 
			snapYName="y轴";//数值轴的显示标签  
			List<SnapMap> snapList=null;//构造折线数据
			snapMapName_font_name="黑体";//标题字体
			snapMapName_font_style=Font.ITALIC;//标题风格
			//snapMapName_font_size=22;//标题大小
			snapMapItem_font_name="宋体";//图例字体
			snapMapItem_font_style=Font.BOLD;//图例风格
			//snapMapItem_font_size=14;//图例大小
			snapX_font_name="宋体";//目录轴的显示标签字体 
			snapX_font_style=Font.BOLD;//目录轴的显示标签风格
			//snapX_font_size=22;//目录轴的显示标签大小
			snapY_font_name="宋体";//数值轴的显示标签字体
			snapY_font_style=Font.BOLD;//数值轴的显示标签风格
			//snapY_font_size=22;//数值轴的显示标签大小
			snapX_font_angle=CategoryLabelPositions.UP_45;//目录轴的显示标签倾斜度
			snapXData_font_name="宋体";//目录轴数据字体 
			snapXData_font_style=Font.BOLD;//目录轴数据风格
			//snapXData_font_size=22;//目录轴数据大小
			fackgroundAlpha=0.9f;//背景透明度（0~1） 
			foregroundAlpha=0.5f;//前景透明度（0~1）
			//flag=false;//2D,3D标示，true为3D
		 * */
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "登记量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,mtaVo.getIs3D());
	}
	/**
	 * 获取维修时间段分析饼图信息 
	 * */
	
	public JFreeChart findServiceTimeAnalyzeCakeMap(MaintenanceTrafficAnalysisVo mtaVo)
			throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap<String,Double>();
		DatagridAnalyze dga=findMaintenanceTrafficAnalysisServiceTimeAnalyze(mtaVo);
		List<MaintenanceTrafficAnalysisServiceTimeAnalyzeVo> list=dga.getFooter();
		List<CakeMap> cmList=new ArrayList<CakeMap>();
		if(list!=null&&list.size()>0){
			cakeHashMap.put("08以前",Double.parseDouble(list.get(0).getEightBefore().toString()));
			cakeHashMap.put("08-09", Double.parseDouble(list.get(0).getEightAndNine().toString()));
			cakeHashMap.put("09-10", Double.parseDouble(list.get(0).getNineAndTen().toString()));
			cakeHashMap.put("10-11", Double.parseDouble(list.get(0).getTenAndEleven().toString()));
			cakeHashMap.put("11-12", Double.parseDouble(list.get(0).getElevenAndTwelve().toString()));
			cakeHashMap.put("12-13", Double.parseDouble(list.get(0).getTweleveAndThirteen().toString()));
			cakeHashMap.put("13-14", Double.parseDouble(list.get(0).getThirteenAndFourteen().toString()));
			cakeHashMap.put("14-15", Double.parseDouble(list.get(0).getFourteenAndFifteen().toString()));
			cakeHashMap.put("15-16", Double.parseDouble(list.get(0).getFifteenAndSixteen().toString()));
			cakeHashMap.put("16-17", Double.parseDouble(list.get(0).getSixteenAndSeventeen().toString()));
			cakeHashMap.put("17-18", Double.parseDouble(list.get(0).getSeventeenAndEighteen().toString()));
			cakeHashMap.put("18后",  Double.parseDouble(list.get(0).getEnighteenAfter().toString()));
			cmList.add(new CakeMap("08以前",Double.parseDouble(list.get(0).getEightBefore().toString()),Color.blue));
			cmList.add(new CakeMap("08-09", Double.parseDouble(list.get(0).getEightAndNine().toString()),Color.cyan));
			cmList.add(new CakeMap("09-10", Double.parseDouble(list.get(0).getNineAndTen().toString()),Color.DARK_GRAY));
			cmList.add(new CakeMap("10-11", Double.parseDouble(list.get(0).getTenAndEleven().toString()),Color.gray));
			cmList.add(new CakeMap("11-12", Double.parseDouble(list.get(0).getElevenAndTwelve().toString()),Color.GREEN));
			cmList.add(new CakeMap("12-13", Double.parseDouble(list.get(0).getTweleveAndThirteen().toString()),Color.LIGHT_GRAY));
			cmList.add(new CakeMap("13-14", Double.parseDouble(list.get(0).getThirteenAndFourteen().toString()),Color.magenta));
			cmList.add(new CakeMap("14-15", Double.parseDouble(list.get(0).getFourteenAndFifteen().toString()),Color.ORANGE));
			cmList.add(new CakeMap("15-16", Double.parseDouble(list.get(0).getFifteenAndSixteen().toString()),Color.PINK));
			cmList.add(new CakeMap("16-17", Double.parseDouble(list.get(0).getSixteenAndSeventeen().toString()),Color.RED));
			cmList.add(new CakeMap("17-18", Double.parseDouble(list.get(0).getSeventeenAndEighteen().toString()),Color.YELLOW));
			cmList.add(new CakeMap("18后", Double.parseDouble(list.get(0).getEnighteenAfter().toString()),Color.WHITE));
		}
		String cakeName="维修时间段分析饼图";
		if(mtaVo.getEnrolDate()!=null&&mtaVo.getEnrolDate().length()>0){
			cakeName="("+mtaVo.getEnrolDate()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cmList,true,mtaVo.getIs3D());
	}
	/**
	 * 获取维修接待员分析饼图信息
	 * */
	
	public JFreeChart findServiceReceiveAnalyzeCakeMap(
			MaintenanceTrafficAnalysisVo mtaVo) throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<Reptype> list=reptypeDao.find("from Reptype r where r.enterpriseId="+mtaVo.getEnterpriseId());
		mtaVo.setFlag(true);
		String source=findMaintenanceTrafficAnalysisServiceReceiveAnalyze(mtaVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelected(list,mtaVo);
			Double value=null;
			for (Reptype reptype : list) {
				value=Double.parseDouble(Ognl.getValue("data"+reptype.getReptId(), rows.get(0)).toString());
				cakeHashMap.put(reptype.getReptName(), value);
			}			
		}
		String cakeName="维修接待员分析饼图";
		if(mtaVo.getEnrolDate()!=null&&mtaVo.getEnrolDate().length()>0){
			cakeName="("+mtaVo.getEnrolDate()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,null,null,null,null,true,null,null,null,
				null,null,null,null,null,null,null,null,null,null,true,mtaVo.getIs3D());
	}
	/**
	 * 获取维修接待员分析柱状图信息  
	 * */
	
	public JFreeChart findServiceReceiveAnalyzePoleMap(
			MaintenanceTrafficAnalysisVo mtaVo) throws Exception {
		List<PoleMap> poleMapList=new ArrayList<PoleMap>();
		List<Reptype> list=reptypeDao.find("from Reptype  r where r.enterpriseId="+mtaVo.getEnterpriseId());
		isSelected(list, mtaVo);
		mtaVo.setFlag(true);
		String source=findMaintenanceTrafficAnalysisServiceReceiveAnalyze(mtaVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			PoleMap pm=null;
			String tempField="sumCount";
			if(mtaVo.getSelectedField()!=null&&mtaVo.getSelectedField().length()>0&&(!mtaVo.getSelectedField().equals("stfId"))&&(!mtaVo.getSelectedField().equals("stfName"))){
				tempField=mtaVo.getSelectedField();
			}
			for (Object object : rows) {
				pm=new PoleMap();
				pm.setPoleBarName("接待量");
				pm.setPoleXData(Ognl.getValue("stfName", object).toString());
				pm.setPoleYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
				//pm.setPoleBarColor(Color.blue);
				poleMapList.add(pm);			
			}			
		}
		String poleName="维修接待员分析柱状图";
		if(list!=null&&list.size()==1){
			poleName="("+list.get(0).getReptName()+")"+poleName;
		}
        return CreateJFreeChart.findPoleMap(poleName, "接待员", "接待量",poleMapList,mtaVo.getIs3D());
	}
	

}
