<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#repayName").validatebox({required:true});
	$('#repayName').validatebox('validate');
	$("#repayDay").validatebox({required:true});
	$('#repayDay').validatebox('validate');
	
</script>
<form id="repayEditForm" style="margin-top: 20px;padding-top: 15px">
	<input type="hidden" id="repayId" name="repayId"/>
	<input type="hidden" id="repayId" name="repayCode"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table>
		<tr>
			<td width="70">进度名称:</td>
			<td colspan="2"><input type="text" name="repayName" id="repayName" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'进度名称为必填项',validType:'characterDigit'" maxlength="50"/></td>
			<td width="70">间隔天数:</td>
			<td><input type="text" name="repayDay" id="repayDay" class="easyui-numberbox" data-options="required:true" style="width:60px" maxlength="3"/></td>
		</tr>
		<tr>	
			<td width="70">备注:</td>
			<td colspan="4"><textarea name="repayRemark" id="repayRemark" style="height: 50px;width: 340px;resize:none;"  maxlength="100"></textarea></td>
		</tr>
	</table>
</form>