<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 采购单管理-配件信息选择 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPurOrder/StPurOrder_AddParts.js"></script>
<!-- 新增维修配件 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="stpurorder_selectpartsform" method="post">
	    	<input type="hidden" id="pr" name="pr" />
	    		<fieldset style="height:62px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>配件编码:</td>
						<td><input name="partsId"/></td>
						<td>编码二:</td>
						<td><input name="partsId2"/></td>
						<td>配件名称:</td>
						<td><input name="partsName"/></td>
						<td>配件品牌:</td>
						<td><input name="pbrdId" class="easyui-combobox" data-options="
						url : 'basRepairPackageAction!findAllPartsBrand.action',
   						valueField:'id',  
   					    textField:'text',
   					    mode:'remote'"/></td>
					</tr>
					<tr>
						<td>配件型号:</td>
						<td><input name="ptypeName"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
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
		<fieldset style="height: 235px;">
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