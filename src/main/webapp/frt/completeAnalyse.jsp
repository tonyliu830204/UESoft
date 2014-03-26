<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>完工分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/completeAnalyse.js"></script>
  </head>
  
  <body >
   	<div class="easyui-layout" fit="true" border="false">
   		<div region="north"  split="false" style="height:70px;background: #eee;" border="false">
		    <privilege:enable code="COMPLETEANALYSE_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_search();">查询</a>
		   </privilege:enable>
			<privilege:enable code="COMPLETEANALYSE_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="COMPLETEANALYSE_PRINT">
		      	<a id="_print" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">套打模板</a>
		   </privilege:enable>
			<privilege:enable code="COMPLETEANALYSE_SET">
		      	<a id="_set" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true">打印设置</a>
		   </privilege:enable>
			<privilege:enable code="COMPLETEANALYSE_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		   </privilege:enable>
		   <br/>
		   <form id="completeAnalyseQueryForm">
				<table>
					<tr>
						<td>工单号:</td>
						<td><input type="text" name="receptionId" /></td>
						<td>车牌照:</td>
						<td><input type="text" name="carLicense" /></td>
						<td>计划完工时间:</td>
						<td>
						<!--<input type="text" id=receptionEndTimeBegin name="receptionEndTimeBegin" style="width:140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						<input type="text" id="receptionEndTimeEnd" name="receptionEndTimeEnd" style="width: 140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						-->
							<input id="receptionEndTimeBegin" name="receptionEndTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'receptionEndTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="receptionEndTimeEnd" name="receptionEndTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'receptionEndTimeBegin\',{d:0})}'})"/>
						</td>
					</tr>
				</table>
			</form>
	   </div>  
		<div region="center" style="background:#eee;" border="false">
			<div id="completeAnalyseDatagrid_center" style="height:100%;">
				<table id="completeAnalyseDatagrid"></table>
			</div>
	  	</div>
   	</div>
  </body>
</html>
