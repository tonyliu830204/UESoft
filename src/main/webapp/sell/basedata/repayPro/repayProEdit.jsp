<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#projectName").validatebox({required:true});
	$('#projectName').validatebox('validate');
	
	
</script>
<form id="reProEditForm" style="margin-top: 20px">
	<input type="hidden" id="projectId" name="projectId"/>
	<input type="hidden" id="projectCode" name="projectCode"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table>
		<tr>
			<td width="70px">项目名称:</td>
			<td colspan="2"><input type="text" name="projectName" id="projectName" style="width:200px " class="easyui-validatebox" data-options="required:true,missingMessage:'项目名称为必填项',validType:'characterDigit'" maxlength="50"/></td>
			<td width="70px">回访进度:</td>
			<td><input type="text" name="projectType" id="projectType" style="width:120px"  class="easyui-combobox" data-options="
					url:'${pageContext.request.contextPath}/reProjectAction!findAllRepay.action',
					valueField:'id',   
			   		textField:'text',
			   		mode:'remote',
			   		required:true,
			   		missingMessage:'回访进度为必选项',
			   		validType:'isSelected[\'#projectType\']',
			   		invalidMessage : '请从下拉框中选择回访进度'
			"/></td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td width="70px">备注:</td>
			<td colspan="4"><textarea  name="projectRemark" id="projectRemark" style="width:400px;height: 50px;resize:none;" class="easyui-validatebox"  data-options="validType:'characterDigit'" maxlength="100"></textarea></td>
		</tr>
	</table>
</form>
