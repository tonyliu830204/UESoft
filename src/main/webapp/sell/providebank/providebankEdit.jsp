<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
</script>
<form id="providebankEditForm" style="margin-top: 5px">
	<input type="hidden" name="providebankId" id="providebankId"  />
	<input type="hidden" name="providebankCode" id="providebankCode"  />
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
	
	<br/>
	<table align="center">
		<tr>
			<td width="60px">名称:</td>
			<td   colspan="3"><input id="providebankName"  name="providebankName" style="width:285px;" class="easyui-validatebox" 
			 data-options="required:true" maxlength="20" /></td>
		</tr>
		<tr>
			<td>地址:</td>
			<td   colspan="3"><input type="text"  name="providebankAddr" style="width:285px;"  maxlength="50" /></td>
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text" name="providebankPerson"  maxlength="20"/></td>
			<td>电话一:</td>
			<td><input type="text" name="providebankPhone" class="easyui-validatebox" data-options="validType:'phone'"  maxlength="12"/></td>
		</tr>
		<tr>
			<td>手机:</td>
			<td><input type="text" name="providebankTelephone" class="easyui-validatebox" data-options="validType:'mobile'"  maxlength="12"/></td>
			<td>传真:</td>
			<td><input type="text" name="providebankFax" class="easyui-validatebox" data-options="validType:'faxno'" maxlength="12" /></td>
		</tr>
		<tr>	
			<td>开票电话:</td>
			<td><input type="text" name="providebankInvtelephone"  class="easyui-validatebox" data-options="validType:'phone'"  maxlength="12" /></td>
		</tr>	
		<tr>
			<td >税号:(<span id="taxnumberLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="providebankTaxnumber" maxlength="20"
				 name="providebankTaxnumber" style="width:284px;"
				 onkeyup="customArchivesKeyUp('providebankTaxnumber','taxnumberLength');"/></td>
		</tr>
		<tr>
			<td>开户银行:</td>
			<td colspan="3"><input type="text" name="providebankBank"   style="width:285px;"  maxlength="30"/></td>
		</tr>
		<tr>
			<td>银行账号:(<span id="providebankLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="providebankNumber" maxlength="20"
				 name="providebankNumber" style="width:284px;"
				 onkeyup="customArchivesKeyUp('providebankNumber','providebankLength');"/></td>
		</tr>
		<tr>
			<td>贷款利率:</td>
			<td><input type="text" name="providebankLoanrate"  class="easyui-numberbox"  maxlength="12" data-options="precision:2" /></td>
			<td>贷款年限:</td>
			<td><input type="text" name="providebankLimit"   class="easyui-numberbox"   maxlength="12" ></td>
		</tr>
		<tr>
			<td style="vertical-align: top;">备注:</td> 
			<td colspan="3" style="text-align: left;"><input name="providebankRemark" style="width: 285px;"  maxlength="50"/></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$("#providebankName").validatebox({required:true});
	$('#providebankName').validatebox('validate');
	
</script>