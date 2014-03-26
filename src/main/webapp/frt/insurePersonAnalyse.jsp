<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>保险送修人分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/insurePersonAnalyse.js"></script>
  </head>
  
  <body >
   	<div class="easyui-layout" fit="true" border="false">
   		<div region="north"  split="false" style="height:70px;background: #eee;" border="false">
		    <privilege:enable code="INSUREPERSONANALYSE_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		   </privilege:enable>
			<privilege:enable code="INSUREPERSONANALYSE_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="INSUREPERSONANALYSE_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">Excel导出</a>
		   </privilege:enable>
		   <br/>
		   <form id="insurePersonAnalyseQueryForm">
				<table>
					<tr>
						<td>结算日期:</td>
						<td>
						<input id="beginTime" name="beginTime"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/>
		                                              至<input id="endTime" name="endTime" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'})"/>
						</td>
						<td>保险送修人:</td>
						<td>
							<input type="text" id="insurePerson" name="insurePerson" />
							<input type="hidden" id="flag" name="flag" />
						</td>
					</tr>
				</table>
			</form>
	   </div>  
		<div region="center" style="background:#eee;" border="false">    
	  		
			<div id="insurePersonAnalyseTabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div id="insurePersonAnalyseMainDatagrid_center" style="background:#eee;" data-options="fit:true,border:false,title:'保险送修人统计汇总'">
						<table id="insurePersonAnalyseMainDatagrid"></table>
				</div>
				<div id="insurePersonAnalyseDetailDatagrid_center" style="background:#eee;" data-options="fit:true,border:false,title:'保险送修人统计明细'">
						<table id="insurePersonAnalyseDetailDatagrid"></table>
				</div>
			</div>
	  	</div>
   	</div>
  </body>
</html>
