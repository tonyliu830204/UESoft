<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%request.setCharacterEncoding("GBK"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>配件查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/accessories_find.js"></script>
  </head>
  <body>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/System_manag.js"></script>
   <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:85px;" border="false">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#datagrid_accessories_find_id'),$('#form_choice_accessories_find_id'),'accesssoriesInventoryAction!doFindAccessInfor');">查询</a>	
						<form id="form_choice_accessories_find_id">
						<fieldset style="height: 35px">
						<legend>查询条件</legend>
							<table>
								<tr>
									<td>配件代码：</td>
									<td><input/></td>
									<td>配件名称：</td>
									<td><input/></td>
								</tr>
							</table>
						</fieldset>
						</form>
				</div>
				<div region="center" border="false" style="background:#eee;">
					<table id="datagrid_accessories_find_id"></table>
				</div>
</div>
  </body>
</html>
