<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 保险估价单-添加计划项目 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtInsurePrize/details/addItem.js"></script>
<!-- 新增维修项目 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="selectionItemForm" method="post">
	    		<fieldset style="height:63px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>项目编号:</td>
						<td><input type="text" name="repitemId" /></td>
						<td>项目名称:</td>
						<td><input type="text" name="repitemName" /></td>
					</tr>
					<tr>
						<td>适合车型:</td>
						<td><input type="text" name="fitCar" /></td>
						<td>工时分类:</td>
						<td><input type="text" name="repitemSeries" class="easyui-combobox" data-options="
						url : 'frtOptionsAction!findAllWorkHours.action',
   						valueField:'id',  
   					    textField:'text',
   					    mode:'remote'"/></td>
						<td>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="_query();">查询</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="_clear();">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<fieldset style="height: 235px;">
	    	<legend><strong>待选维修项目</strong></legend>
	    	<table id="selectionItem"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选维修项目</strong></legend>
	    	<table id="selectedItem"></table>
	  	</fieldset>
	</div>
</div>
