<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#projectName").validatebox({required:true});
	$('#projectName').validatebox('validate');
	
</script>
<form id="proEditForm" style="margin-top: 20px">
	<input type="hidden" id="projectId" name="projectId"/>
	<input type="hidden" id="projectCode" name="projectCode"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table>
		<tr>
			<td width="70px">项目名称:</td>
			<td colspan="3"><input type="text" name="projectName" id="projectName" style="width: 380px"  class="easyui-validatebox" data-options="required:true,missingMessage:'项目名称为必填项'" maxlength="50"/></td>
		</tr>
		<tr>
			<td width="70px">代办成本:</td>
			<td><input type="text" name="projectMomay" id="projectMomay" class="easyui-numberbox" style="width: 150px" data-options="validType:'monery',precision:2"/></td>
			<td width="70px">收费金额:</td>
			<td><input type="text" name="projectAmount" id="projectAmount" class="easyui-numberbox" style="width: 150px" data-options="validType:'monery',precision:2 "/></td>
		</tr>
		<tr>
			
			
			<td width="70px">收取部门:</td>
			<td colspan="3"><input id="projectDept" name="projectDept"  type="text"  class="easyui-validatebox" style="width: 380px"  maxlength="20"/>
			</td>
		</tr>
	</table>
</form>