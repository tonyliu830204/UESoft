<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 新增保险(汽厂)档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<form id="insuranceCarArchivesAddForm">
	<table>
		<tr>
			<td>编号:</td>
			<td><input type="text" name="relcampId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>简码:</td>
			<td><input type="text" id="insuranceCarArchives_add_relcampJm" name="relcampJm" class="easyui-validatebox"   data-options="validType:'maxLength[100]'" /></td>
		</tr>
		<tr>
			<td>名称:</td>
			<td colspan="3" style="text-align: left;"><input type="text" id="insuranceCarArchives_add_relcampName" name="relcampName" onkeyup="$('#insuranceCarArchives_add_relcampJm').val(makePy($('#insuranceCarArchives_add_relcampName').val()))" class="easyui-validatebox" data-options="required:true, missingMessage:'名称为必填项',validType:'maxLength[50]'" style="width: 282px;"/></td>
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text" name="relcampContact" /></td>
			<td>属性:</td>
			<td><input type="text" id="insuranceCarArchives_add_attrId" name="attrId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath}/basRelationCampanyAction!findAllRelcampAttr.action',
			valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#insuranceCarArchives_add_attrId\']',
    		invalidMessage : '请从下拉框中选择属性',
    		mode:'remote' "/></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td colspan="3" style="text-align: left;"><input type="text" name="relcampAddr" style="width: 282px;"/></td>
		</tr>
		<tr>
			<td>联系电话:</td>
			<td><input type="text" name="relcampTel1" class="easyui-validatebox" data-options="validType:'mobile'"/></td>
			<td>固定电话:</td>
			<td><input type="text" name="relcampTel2" class="easyui-validatebox" data-options="validType:'phone'"/></td>
		</tr>
		<tr>
			<td>传真:</td>
			<td><input type="text" name="relcampFax" class="easyui-validatebox" data-options="validType:'phone'"/></td>
			<td>开户银行:</td>
			<td><input type="text" name="relcampBank" /></td>
		</tr>
		<tr>
			<td>银行账号:</td>
			<td><input type="text" name="relcampAccount" /></td>
			<td>税号:</td>
			<td><input type="text" name="relcampTaxNum" /></td>
		</tr>
		<tr>
			<td>开票地址:</td>
			<td><input type="text" name="invoicingAddress" /></td>
			<td>开票电话:</td>
			<td><input type="text" name="invoicingTel" class="easyui-validatebox" data-options="validType:'phone'"/></td>
		</tr>
		<tr>
			<td>邮政编码:</td>
			<td><input type="text" name="relcampZipCode" class="easyui-validatebox" data-options="validType:'zip'"/></td>
			<td>备注:</td>
			<td><input type="text" name="relcampRemark1"/></td>
		</tr>
	</table>
</form>