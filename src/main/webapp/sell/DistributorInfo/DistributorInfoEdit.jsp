<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
$("#distributorName").validatebox({required:true});
$('#distributorName').validatebox('validate');

	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
</script>
<form id="distributorInfoEditForm" style="margin-top: 10px">
	<input type="hidden" name="distributorId"/>
	<input type="hidden" name="distributorCode"/>
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
		<br/>
	<table align="center">

		<tr>
			<td>名称:</td>
			<td  colspan="3"><input type="text"  name="distributorName" id="distributorName" style="width: 283px"  class="easyui-validatebox"  maxlength="20"
			data-options="required:true,missingMessage:'名称为必填项'" onkeyup="$('#distributorMark').val(makePy($('#distributorName').val()))"/></td>			
		</tr>
		<tr>
			<td>地址:</td>
			<td  colspan="3"><input type="text"  name="distributorAddr"  style="width: 283px"  maxlength="50"/></td>	
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text" name="distributorPerson"    style="width: 122px" class="easyui-validatebox"  maxlength="20"/></td>
			<td>电话一:</td>
			<td><input type="text" name="distributorTelephone"  class="easyui-validatebox" data-options="validType:'phone'" maxlength="12"  /></td>			
		</tr>
		<tr>	
			<td>手机:</td>
			<td><input type="text" name="distributorPhone"  style="width: 122px" class="easyui-validatebox" data-options="validType:'mobile'"  maxlength="12"/></td>
			<td>传真:</td>
			<td><input type="text" name="distributorFax"  class="easyui-validatebox" data-options="validType:'faxno'" maxlength="12" /></td>
			
		</tr>
		<tr>		
			<td >税号:(<span id="taxnumberLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="distributorTaxnumber" maxlength="20"
				 name="distributorTaxnumber" style="width:284px;"
				 onkeyup="customArchivesKeyUp('distributorTaxnumber','taxnumberLength');"/></td>
		</tr>
		<tr>
			<td>开户银行:</td>
			<td colspan="3"><input type="text" name="distributorBeak" style="width: 283px"  maxlength="30"/></td>
		</tr>
		<tr>
			<td >银行账号:(<span id="distributorBeakLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="distributorBeaknumber" maxlength="20"
				 name="distributorBeaknumber" style="width:284px;"
				 onkeyup="customArchivesKeyUp('distributorBeaknumber','distributorBeakLength');"/></td>
		</tr>
		<tr>
			<td>开票电话:</td>
			<td><input type="text" style="width: 122px" name="distributorInvoicetelephone" class="easyui-validatebox" data-options="validType:'phone'" maxlength="12" /></td>		
			<td>简称:</td>
			<td><input type="text" name="distributorMark"  id="distributorMark" maxlength="20"/></td>
			
		</tr>
		<tr>
			<td style="vertical-align: top;">备注:</td>
			<td colspan="3" style="text-align: left;"><input name="distributorRemark" style="width:282px;" maxlength="50"/></td>
		</tr>
	</table>
</form>