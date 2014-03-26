<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 采购单管理-配件信息选择 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StMoveStorehouse/StMoveStorehouse_AddParts.js"></script>
<!-- 新增维修配件 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="stmovestorhouse_partsselectform" method="post">
	    		<fieldset style="height:62px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>配件编码:</td>
						<td><input style="width:90px;" name="partsId"/></td>
						<td>配件名称:</td>
						<td><input style="width:90px;" name="partsName"/></td>
				    	<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
						</td>
						</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="overflow:hidden;background:#eee;" border="false">
		<fieldset style="height: 255px;">
	    	<legend><strong>待选配件</strong></legend>
	    	<table id="selectionParts"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="overflow:hidden;background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选配件 <span id="selectedPartsTotal"></span></strong></legend>
	    	<table id="selectedParts"></table>
	  	</fieldset>
	</div>
</div>