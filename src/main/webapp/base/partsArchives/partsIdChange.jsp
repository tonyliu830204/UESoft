<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 配件代码变更 -->
<script type="text/javascript">
var parojectPat = "${pageContext.request.contextPath}/";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/partsArchives/partsIdChange.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="accept();">变更</a>  
     </div>
     <div data-options="region:'center',border:false" style="background:#eee;">
     	<fieldset>
	    		<legend><strong>编码变更:</strong></legend>
    			<form id="partsArchives_changeBeforeForm">
    				<table>
    					<tr>
    						<td>原配件编码:</td>
    						<td><input type="text" name="partsId" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"/></td>
    					</tr>
    					<tr>
    						<td>原配件名称:</td>
    						<td><input type="text" name="partsName" readonly="readonly" style="width: 200px;background-color: #c0d8d8;"></td>
    					</tr>
    				</table>
    			</form>
	   	</fieldset>
	   	<br/>
     	<fieldset>
	    		<legend><strong></strong></legend>
    			<form id="partsArchives_changeAfterForm">
    				<table>
    					<tr>
    						<td>新配件编码:</td>
    						<td><input type="text" name="partsId" style="width: 200px"/></td>
    					</tr>
    				</table>
    			</form>
	   	</fieldset>
     </div>
     <div data-options="region:'south',border:false" style="background:#eee;height: 120px; color: red;">
     	 &nbsp;&nbsp; 配件编码修改:<br/>
     	 &nbsp;&nbsp; 主要包扩以下两种情况:<br/>
     	 &nbsp;&nbsp;(一)、配件代码书写错误，需要进行更正，<br/>
     	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 如C12345104(错误)，更正为:C12345114(正确);<br/>
     	 &nbsp;&nbsp;(二)、配件代码的合并，如同一配件在配件档案中重复登记，<br/>
     	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 需要合并;
     </div>
</div>