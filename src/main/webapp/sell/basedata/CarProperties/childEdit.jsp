<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#dataKey").validatebox({required:true});
	$('#dataKey').validatebox('validate');
	$("#dataValue").validatebox({required:true});
	$('#dataValue').validatebox('validate');
	
	
</script>
<form id="childEditForm" style="margin-top: 20px">
	<input type="hidden" id="childId" name="childId" />
	<input type="hidden" id="parentId" name="parentId" />
	<input type="hidden" id="createTime" name="createTime" />
	<input type="hidden" id="stfId" name="stfId" />
	
	<table>
		<tr>
			<td>键名:</td>
			<td><input type="text" name="dataKey" id="dataKey" class="easyui-validatebox" data-options="required:true,missingMessage:'键名为必填项'" maxlength="20"/></td>
			<td>键值:</td>
			<td><input type="text" name="dataValue" id="dataValue" data-options="required:true" class="easyui-validatebox"  maxlength="100"/></td>
		</tr>
	</table>
</form>