<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$("#levaName").validatebox({required:true});
	$('#levaName').validatebox('validate');
	$("#jianGe").validatebox({required:true});
	$('#jianGe').validatebox('validate');
	
</script>
<form id="customEditForm" style="margin-top: 20px">
	<input type="hidden" id="levaId" name="levaId"/>
	<input type="hidden" id="levaId" name="levaCode"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table>
		<tr>
			<td width="70px">等级名称:</td>
			<td colspan="2"><input type="text" name="levaName" id="levaName" style="width:208px " class="easyui-validatebox" data-options="required:true,missingMessage:'等级名称为必填项',validType:'characterDigit'" maxlength="50"/></td>
			<td width="70px">跟踪间隔:</td>
			<td><input type="text" name="jianGe" id="jianGe" class="easyui-numberbox" 
			 data-options="required:true,missingMessage:'跟踪间隔为必填项'" style="width: 50px" maxlength="3"/></td>
		</tr>
		<tr>
			<td width="70px">备注:</td>
			<td colspan="4"><textarea type="text" name="remark" id="remark" style="width: 340px;resize:none;" maxlength="100"  ></textarea></td>
		</tr>
	</table>
</form>