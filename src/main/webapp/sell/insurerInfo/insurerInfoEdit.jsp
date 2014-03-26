<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
	$("#insurerName").validatebox({required:true});
	$('#insurerName').validatebox('validate');

	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
</script>
<form id="insurerEditForm" style="margin-top: 5px">
	<input type="hidden" name="insurerId" id="insurerId"  />
	<input type="hidden" name="insurerCode" id="insurerCode"  />
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
	<table align="center" >
	<br/>
		<tr>
			<td width="60">名称:</td>
			<td colspan="3"><input type="text" id="insurerName"   name="insurerName"  style="width:293px" class="easyui-validatebox" 
			 data-options="required:true,missingMessage:'名称为必填项'" maxlength="20"/></td>
		</tr>
		<tr>
			<td >地址:</td>
			<td colspan="3"><input type="text"  name="insurerAddress" style="width:293px"  maxlength="50"/></td>
		</tr>
		<tr>
			<td>联系人:</td>
			<td><input type="text" name="insurerPerson"    maxlength="20"/></td>	
			<td>电话:</td>
			<td><input type="text" name="insurerPhone" class="easyui-validatebox" data-options="validType:'phone'"  maxlength="12"/></td>	
		</tr>
		<tr>
			<td>手机:</td>
			<td><input type="text" name="insurerTelephone" class="easyui-validatebox" data-options="validType:'mobile'" maxlength="12" /></td>	
			<td>传真:</td>
			<td><input type="text" name="insurerFax" class="easyui-validatebox" data-options="validType:'faxno'" maxlength="12" /></td>			
		</tr>
		<tr>
			<td>开票电话:</td>
			<td><input type="text" name="insurerInvoicetelephone" class="easyui-validatebox" data-options="validType:'phone'" maxlength="12" /></td>	
		</tr>
		<tr>
			<td >税号:(<span id="taxnumberLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="insurerTaxnumber" maxlength="20"
				 name="insurerTaxnumber" style="width:293px;"
				 onkeyup="customArchivesKeyUp('insurerTaxnumber','taxnumberLength');"/></td>
		</tr>
		<tr>
			<td >开户银行:</td> 
			<td colspan="3"><input type="text" name="insurerBeak" style="width:293px" maxlength="30" /></td>	
		</tr>
		<tr>		
			<td >银行账号:(<span id="beaknumberLength" style="color:#ff3300;">0</span>):</td>
				<td colspan="3"><input type="text" id="insurerBeaknumber" maxlength="20"
				 name="insurerBeaknumber" style="width:293px;"
				 onkeyup="customArchivesKeyUp('insurerBeaknumber','beaknumberLength');"/></td>
		</tr>
		<tr>
			<td>商业险返点:</td>
			<td><input type="text" name="insurerBusinessinsurer" class="easyui-validatebox"  maxlength="20"  data-options="validType:'intOrFloat'"  /></td>
			<td>交强险返点:</td>
			<td><input type="text" name="insurerStronginsurer" class="easyui-validatebox"  maxlength="20" data-options="validType:'intOrFloat'" /></td>
		</tr>
		<tr>
			<td style="vertical-align: top;">备注:</td>
			<td colspan="3" style="text-align: left;"><input name="insurerRemark"	style="width:293px"  maxlength="50"/></td>
		</tr>
	</table>
</form>