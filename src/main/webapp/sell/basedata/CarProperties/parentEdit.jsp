<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#pdataKey").validatebox({required:true});
	$('#pdataKey').validatebox('validate');
	$("#pdataValue").validatebox({required:true});
	$('#pdataValue').validatebox('validate');
	
	
</script>
<form id="parentEditForm" style="margin-top: 20px;margin-left: 20px">
	<input type="hidden" id="pparentId" name="pparentId"/>
	<table>
		<tr>
			<td>键名:</td>
			<td><input type="text" name="pdataKey" id="pdataKey" class="easyui-validatebox" data-options="required:true,missingMessage:'键名为必填项'" maxlength="20"/></td>
			<td>键值:</td>
			<td><input type="text" name="pdataValue" id="pdataValue" class="easyui-validatebox" data-options="required:true"  maxlength="100"/></td>
		</tr>
	</table>
</form>