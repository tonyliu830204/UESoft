<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
	<head>
		<title>盘点单查询</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/inventory_find.js"></script>
	</head>

	<body>
	
	<div id="cc" class="easyui-layout" fit="true" style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
			<privilege:enable code="INVENTORYFINDQUERY">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
			</privilege:enable>
			<privilege:enable code="INVENTORYFINDCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
			<privilege:enable code="INVENTORYFINDEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
			</privilege:enable>
		</div>
		<div region="center"  style="background:#eee;">
			<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:65px;" border="false">
			
			<form id="form_inventory_find_id" method="post">
			<fieldset>
				<legend>查询条件</legend>
				<table border="0" style="text-align: right">
					<tr>
						<td>盘点日期：</td>
							<td><input id="stinvm_Time" name="stinvm_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'stinvm_Time2\',{d:-1})}'})"  style="width: 110px;"/> 至 </td>
							<td><input id="stinvm_Time2" name="stinvm_Time" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'stinvm_Time\',{d:1})}'})" style="width: 110px;"/></td>
						<td>配件名称：</td>
						<td><input type="text" name="Parts_Name" style="width:110px"/></td>
						<td>配件代码：</td>
						<td><input type="text" name="parts_Id" style="width:110px"/></td>
						<td>盘点单号：</td>
						<td><input type="text" name="stinvm_Id" style="width:110px"/></td>
					</tr>
				</table>
				</fieldset>
			</form>
		</div>
		
		<div id="pandian_find_idDiv" region="center" border="false">
			<table id="pandian_find_id"></table>
		</div>
		</div>
		</div>
	</div>
	</body>
</html>
