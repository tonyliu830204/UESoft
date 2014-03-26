<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修人员业绩统计汇总</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/customer_performance_statistics/weixiuyejitongjihuizong.js"></script>
  </head>
  
  <body>
    <div class="easyui-layout" border="false" fit="true"
		style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:55px;" border="false">
			<div>
			   	<privilege:enable code="ACHIEVEPERSONMAIN_SEARCH">
				    <a id="_search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryServicePersonMain();">查询</a>
			   </privilege:enable>
				<privilege:enable code="ACHIEVEPERSONMAIN_CLEAR">
				    <a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearServicePersonMain();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="ACHIEVEPERSONMAIN_EXPORT">
				    <a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptServicePersonMain();">Excel导出</a>
			   </privilege:enable>
		   </div>
			<form id="servicePersonMainForm" method="post">
				<table border="0" style="text-align: right">
					<tr>
						<td>结算日期：</td>
						<td>
						   <input id="servicePersonMain_preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'servicePersonMain_preclrTimeEnd\',{d:-1})}'})"/>
		                                                    至<input id="servicePersonMain_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'servicePersonMain_preclrTimeBegin\',{d:0})}'})"/>
						</td>
						<td>工单号：</td>
						<td><input name="receptionId" style="width:140px;"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="servicePersonMainDatagrid_center" region="center" border="false" style="background : #eee">
			<table id="servicePersonMainDatagrid"></table>
		</div>
	</div>
  </body>
</html>
