<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 采购单管理-配件信息选择 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPreOut/StPreOut_AddPlanParts.js"></script>
<!-- 出库配件选择 --> 
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:60px;">
	    	<form id="stpreout_selectplanpartsform" method="post">
	    		<fieldset style="height:42px;">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>工单号:</td>
						<td><input style="width:95px;" id="stpreout_addplanparts_receptionId"  name="receptionId"/></td>
						<td>车辆牌照</td>
						<td><input name="carLicense" style="width:95px;"  class="easyui-combobox"
								data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseByReceptionId.action',
							    editable : true,
								valueField:'id',
								panelHeight:130,
								textField:'text'"/></td>
						<td>客户名称:</td>
						<td><input name="customId" style="width:95px;"  class="easyui-combobox"
								data-options="url:'${pageContext.request.contextPath}/StOutAction!findAllCustom.action',
							    editable : true,
								valueField:'id',
								panelHeight:130,
								textField:'text'"/></td>
						<td>VIN号:</td>
						<td><input name="carVan" style="width:95px;"/></td>
				    	<td>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
							<input type="hidden" id="hiddenRecptionId"/>
						</td>
						</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="overflow:hidden;background:#eee;" border="false">
		<fieldset style="height: 210px;">
	    	<legend><strong>工单信息</strong></legend>
	    	<table id="spo_frtReceptionTable"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="overflow:hidden;background:#eee;height: 240px;">
		<fieldset style="height:210px;">
			<legend><strong>计划用料信息</strong></legend>
	    	<table id="spo_planParts"></table>
	  	</fieldset>
	</div>
</div>