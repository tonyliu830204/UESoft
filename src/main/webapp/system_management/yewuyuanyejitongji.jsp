<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'weixiuyejitongji.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/System_manag.js"></script>
  	
   <div class="easyui-layout" border="false" fit="true"
		style="width: 800px; height: 600px; background : #eee">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:40px;" border="false">
			<form id="ff" method="post">
				<table border="0"  style="text-align: right">
					<tr>
						<td>结算日期：</td>
						<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" name="end_Time" id="end_Time" style="width: 85px;"/> 至 </td>
						<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" name="end_Time" id="end_Time2" style="width: 85px;"/></td>
						<td>接待人员：</td>
						<td><input/></td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false" style="background : #eee">
			<table id="yewuyuanyejitongji_id"></table>
		</div>
	</div>
  </body>
</html>
