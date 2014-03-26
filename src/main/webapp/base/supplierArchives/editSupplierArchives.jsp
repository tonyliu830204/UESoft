<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 修改供应商档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<form id="supplierArchivesEditForm">
	<table>
		<tr>
			<td>编号:</td>
			<td><input type="text" name="relcampId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>供应商简码:</td>
			<td><input type="text" id="supplierArchives_edit_relcampJm" name="relcampJm"/></td>
		</tr>
		<tr>	
			<td>供应商名称:</td>
			<td colspan="3" style="text-align: left;"><input type="text" id="supplierArchives_edit_relcampName" name="relcampName" onkeyup="$('#supplierArchives_edit_relcampJm').val(makePy($('#supplierArchives_edit_relcampName').val()))" class="easyui-validatebox" data-options="required:true, missingMessage:'供应商名称为必填项'" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampAddr" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text" name="relcampContact" /></td>
			<td>联系电话:</td>
			<td><input type="text" name="relcampTel1" class="easyui-validatebox" data-options="validType:'mobile'"/></td>
		</tr>
		<tr>
			<td>固定电话:</td>
			<td><input type="text" name="relcampTel2" class="easyui-validatebox" data-options="validType:'phone'"/></td>
			<td>传真:</td>
			<td><input type="text" name="relcampFax" class="easyui-validatebox" data-options="validType:'faxno'"/></td>
		</tr>
		<tr>
			<td>开户银行:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampBank" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>银行账号:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampAccount" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>税号:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampTaxNum" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>开票地址:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="invoicingAddress" style="width: 292px;"/></td>
		</tr>
		<tr>
			<td>开票电话:</td>
			<td><input type="text" name="invoicingTel" class="easyui-validatebox" data-options="validType:'phone'"/></td>
			<td>邮政编码:</td>
			<td><input type="text" name="relcampZipCode" class="easyui-validatebox" data-options="validType:'zip'"/></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampRemark1" style="width: 292px;"/></td>
		</tr>
	</table>
</form>