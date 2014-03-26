<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 维修套餐-添加维修项目 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/repairPackage/addItem.js"></script>
<!-- 新增维修项目 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:52px;">
	    	<form id="selectionItemForm" method="post">
	    		<fieldset style="height:35px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>项目编号:</td>
						<td><input type="text" name="repitemId" /></td>
						<td>项目名称:</td>
						<td><input type="text" name="repitemName" /></td>
						<td>适合车型:</td>
						<td><input type="text" name="fitCar" /></td>
						<td>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));">查询</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="$('#selectionItemForm').form('clear'); $selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<fieldset style="height: 265px;">
	    	<legend><strong>待选维修项目</strong></legend>
	    	<table id="selectionItem"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选维修项目  <span id="selectedItemTotal"></span></strong></legend>
	    	<table id="selectedItem"></table>
	  	</fieldset>
	</div>
</div>
