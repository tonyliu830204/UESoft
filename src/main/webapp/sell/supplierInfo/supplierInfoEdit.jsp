<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<script type="text/javascript">

	$("#supplierName").validatebox({required:true});
	$('#supplierName').validatebox('validate');
	$("#supplierPhone").validatebox({required:true});
	$('#supplierPhone').validatebox('validate');

	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
</script>
<form id="supplierInfoEditForm" style="margin-top: 10px">
	<input type="hidden" name="supplierId"/>
	<input type="hidden" name="supplierNumber"/>
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
	<table align="center" cellpadding="3px">
		<tr>
			<td>供应商名称:</td>
			<td	colspan="3"><input type="text"  name="supplierName" id="supplierName" style="width:320px;" class="easyui-validatebox"  data-options="required:true,missingMessage:'供应商名称为必填项'" maxlength="20"
				onkeyup="$('#supplierCode').val(makePy($('#supplierName').val()))"/></td>
		</tr>
		<tr>
			<td  >地址:</td>
			<td	colspan="3"><input type="text" name="supplierAddress"  style="width:320px;"  maxlength="50"/></td>
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text"  name="supplierPerson"  style="width:125px;" maxlength="20"/></td>
			<td>手机:</td>
						<td><input type="text" id="supplierPhone" name="supplierPhone"  style="width:125px;" class="easyui-validatebox" data-options="required:true,validType:'mobile'"   maxlength="11"/></td>
			
		</tr>
		<tr>	
			<td>电话二:</td>
						<td><input type="text" name="supplierTelephone" style="width:125px;" class="easyui-validatebox" data-options="validType:'phone'"  maxlength="13" /></td>		
			
			<td>传真号码:</td>
			<td><input type="text" name="supplierFax"   style="width:125px;"class="easyui-validatebox" data-options="validType:'faxno'"   maxlength="13"/></td>	
		</tr>
		<tr>		
			<td>助记码:</td>
			<td><input type="text"  name="supplierCode" style="width:125px;" id="supplierCode" maxlength="20"/></td>
			<td>属性:</td>
			<td><input type="text" name="supplierNature" style="width:125px;" id="supplierNature"  class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SUPPLIERCLASS%>',   
				required:true,
				missingMessage:'属性为必填项',
				valueField:'id',   
				textField:'text',
				mode : 'remote',
				validType:'isSelected[\'#supplierNature\']',
				invalidMessage : '请从下拉框中选择属性' "/></td>
		</tr>
		<tr>
			<td >公司税号:(<span id="revenueLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="supplierRevenue" maxlength="20"
				 name="supplierRevenue" style="width:320px;"
				 onkeyup="customArchivesKeyUp('supplierRevenue','revenueLength');"/></td>
		</tr>
		<tr>
			<td >银行账号:(<span id="bankCodeLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="supplierBankCode" maxlength="19"
				 name="supplierBankCode" style="width:320px;" onkeyup="customArchivesKeyUp('supplierBankCode','bankCodeLength');"/></td>
		</tr>
		<tr>
			<td>开户银行:</td>
			<td colspan="3"><input type="text" name="supplierBank" style="width:320px;" id="supplierBank" maxlength="30" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BANKTYPE%>',   
				valueField:'id',   
				textField:'text',
				mode : 'remote',
				validType:'isSelected[\'#supplierBank\']',
				invalidMessage : '请从下拉框中选择属性' "/></td>
			
		</tr>
		<tr>
			<td>开票电话:</td>
			<td><input type="text" name="supplierMakeInvphone" style="width:125px;" class="easyui-validatebox" data-options="validType:'phone'" maxlength="12" /></td>
			<td>账面金额:</td>
			<td><input type="text" name="supplierMoney" style="width:125px;" precision="2"  data-options="validType:'monery'" class="easyui-numberbox"  maxlength="12" /></td>
		</tr>
		<tr>
			<td style="vertical-align: top;">备注:</td>
			<td colspan="3" style="text-align: left;"><textarea name="supplierRemark" style="width: 320px; height: 70px;resize:none;"  maxlength="50"></textarea></td>
		</tr>
	</table>
</form>