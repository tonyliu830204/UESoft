<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 供应商档案变更 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/supplierArchives/supplierChange.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="accept();">变更</a>  
     </div>
     <div data-options="region:'center',border:false" style="background:#eee;">
     	<fieldset>
	    		<legend><strong>编码变更:</strong></legend>
    			<form id="supplierArchives_changeBeforeForm">
    				<table>
    					<tr>
    						<td>原供应商代码:</td>
    						<td><input type="text" name="relcampId" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"/></td>
    					</tr>
    					<tr>
    						<td>原供应商名称:</td>
    						<td><input type="text" name="relcampName" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"></td>
    					</tr>
    				</table>
    			</form>
	   	</fieldset>
	   	<br/>
     	<fieldset>
	    		<legend><strong></strong></legend>
    			<form id="supplierArchives_changeAfterForm">
    				<table>
    					<tr>
    						<td>新供应商代码:</td>
    						<td><input type="text" name="relcampId" style="width: 200px"/></td>
    					</tr>
    					<tr>
    						<td>新供应商名称:</td>
    						<td><input type="text" name="relcampName" style="width: 200px;" /></td>
    					</tr>
    				</table>
    			</form>
	   	</fieldset>
     </div>
     <div data-options="region:'south',border:false" style="background:#eee;height: 90px; color: red;">
     	 &nbsp;&nbsp; 供应商编号修改:<br/>
     	 &nbsp;&nbsp; 主要是供应商档案编号的合并:<br/>
     	 &nbsp;&nbsp;&nbsp;(一)、如果同一供应商在登记过程中，因为称呼不同造成供应商名称重复，
     	 &nbsp;&nbsp;&nbsp;需要合并，在『更改为』输入框中输入合并后的供应商档案编号。
     </div>
</div>