<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 前台接车-添加维修配件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain/details/addParts.js"></script>
<!-- 新增维修配件 -->
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="selectionPartsForm" method="post">
	    		<fieldset style="height:62px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>配件编码:</td>
						<td><input type="text" name="partsId"/></td>
						<td>编码二:</td>
						<td><input type="text" name="partsId2"/></td>
						<%--<td>简码:</td>
						<td><input type="text" name="partsSimpleId"/></td>
						--%>
						<td>配件名称:</td>
						<td><input type="text" name="partsName"/></td>
						<td>
							库存:<input type="text" id="isStock" name="isStock" class="easyui-combobox" data-options="
							data:[{'id':'false','text':'查库存'},{'id':'true','text':'查配件档案'}],
   						valueField:'id',  
   					    textField:'text'"/>
						</td>
					</tr>
					<tr>
						<td>配件品牌:</td>
						<td><input type="text" name="pbrdId" class="easyui-combobox" data-options="
						url : 'frtOptionsAction!findAllPartsBrand.action',
   						valueField:'id',  
   					    textField:'text',
   					    mode:'remote'"/></td>
						<td>配件型号:</td>
						<td><input type="text" name="ptypeName"/></td>
						<td>仓别:</td>
						<td><input type="text" name="storeId" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/frtOptionsAction!findAllStorehouse.action'
							,editable:false
							,valueField:'id'   
				    		,textField:'text'"/></td>
				    	<td align="center">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<fieldset style="height: 235px;">
	    	<legend><strong>待选配件</strong></legend>
	    	<table id="selectionParts"></table>
	  	</fieldset>
	</div>
	<div data-options="region:'south',border:false" style="background:#eee;height: 155px;">
		<fieldset style="height: 135px;">
	    	<legend><strong>已选配件 <span id="selectedPartsTotal"></span></strong></legend>
	    	<table id="selectedParts"></table>
	  	</fieldset>
	</div>
</div>