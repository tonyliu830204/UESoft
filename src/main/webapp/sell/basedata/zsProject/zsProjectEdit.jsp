<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#projectName").validatebox({required:true});
	$('#projectName').validatebox('validate');
	
</script>
<form id="zsProEditForm" style="margin-top: 20px">
	<input type="hidden" id="zsprojectId" name="zsprojectId"/>
	<input type="hidden" id="projectCode" name="projectCode"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table>
		<tr>
			<td width="70px">项目名称:</td>
			<td colspan="3"><input type="text" name="projectName" id="projectName"  class="easyui-validatebox"  style="width: 380px"   data-options="required:true,missingMessage:'项目名称为必填项'" maxlength="50"/></td>
		</tr>
		<tr>
			<td width="70px">成本额:</td>
			<td><input type="text" name="projectCostamount" id="projectCostamount"  class="easyui-numberbox" style="width: 150px" data-options="validType:'monery',precision:2"/></td>
			<td width="70px">销售价:</td>
			<td><input type="text" name="projectSellamount" id="projectSellamount" class="easyui-numberbox" style="width: 150px" data-options="validType:'monery',precision:2"/></td>
		</tr>
		<tr>
			<td width="70px">备注:</td>
			<td colspan="3"><textarea  name="projectRemark" id="projectRemark" style="width: 380px;height:50px;resize:none;"  maxlength="100"></textarea></td>
		</tr>
	</table>
</form>