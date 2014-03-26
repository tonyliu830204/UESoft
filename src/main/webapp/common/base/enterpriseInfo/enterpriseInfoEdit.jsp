<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<form id="enterpriseAddForm" style="margin-top: 5px">
	<input type="hidden" name="enterpriseId"  />
	<input type="hidden" name="enterpriseCode" />
	<table align="center" style="margin-top: 10px;">
		<tr>
			<td width="100">企业名称:</td>
			<td colspan="3"><input type="text"  name="enterpriseName"  style="width:350px" class="easyui-validatebox" 
			 data-options="required:true,missingMessage:'企业名称为必填项',validType:'character'" maxlength="100"/></td>
		</tr>
		<tr>
			<td width="100">企业简称:</td>
			<td colspan="3"><input type="text"  name="enterpriseJm" style="width:350px"  maxlength="50"/></td>
		</tr>
		<tr>
			<td width="100">企业地址:</td>
			<td colspan="3"><input type="text" name="enterpriseAddress" style="width:350px"   maxlength="200"/></td>	
		</tr>
		<tr>
			<td width="100">邮政编码:</td>
			<td><input type="text" name="enterpriseZipcode" class="easyui-validatebox" style="width:150px" data-options="validType:'zip'"  maxlength="6"/></td>	
			<td width="70">传真:</td>
			<td><input type="text" name="enterpriseFax" class="easyui-validatebox" style="width:120px" data-options="validType:'faxno'" maxlength="12" /></td>				
		</tr>
		<tr>
			<td width="100">父企业:</td>
			<td><input id="parentEnterpriseId" name="parentEnterpriseId" style="width:150px;" class="easyui-combotree" 
			data-options="url:'${pageContext.request.contextPath}/enterpriseInfoAction!findPEnterprise.action?enterpriseInfoFlag=true',required:true" >
			<td width="70">企业法人:</td>
			<td><input type="text" name="enterprisePerson"  maxlength="30" style="width:120px" /></td>	
		</tr>
		<tr>
			<td width="100">电话:</td>
			<td><input type="text" name="enterpriseTelephone" class="easyui-validatebox" style="width:150px" data-options="validType:'phone'" maxlength="12" /></td>
			<td width="70">邮箱:</td>
			<td colspan="3"><input type="text" name="enterpriseEmail" style="width:120px;"  maxlength="50" class="easyui-validatebox" data-options="validType:'email'"/></td>
		</tr>
		<tr>
			<td  width="100">开户银行:</td> 
			<td colspan="3"><input type="text" name="bank" style="width:350px" id="supplierBank" maxlength="30" class="easyui-combobox" 
			data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BANKTYPE%>',   
				valueField:'id',   
				textField:'text',
				mode : 'remote',
				validType:'isSelected[\'#supplierBank\']',
				invalidMessage : '请从下拉框中选择属性' "/></td>
		</tr>
		<tr>		
			<td width="100">银行账号:(<span id="bankNumberLength" style="color:#ff3500;">0</span>):</td>
				<td colspan="3"><input type="text" id="bankNumberId" maxlength="19"
				 name="bankNumber" style="width:350px;" class="easyui-validatebox" data-options="validType:'bankAccount'"
				 onkeyup="dynamicShowLength('bankNumberId','bankNumberLength');"/></td>
		</tr>
		<tr>
			<td width="100">税号:(<span id="dutyNumberLength" style="color:#ff3500;">0</span>):</td>
				<td colspan="3"><input type="text" id="dutyNumberId" maxlength="30"
				 name="dutyNumber" style="width:350px;"
				 onkeyup="dynamicShowLength('dutyNumberId','dutyNumberLength');"/></td>
		</tr>
		<tr>
			<td width="100">投诉电话:</td>
			<td><input type="text" name="complainTelephone" maxlength="12" style="width:150px" class="easyui-validatebox" data-options="validType:'phone'"/></td>
			<td width="70">销售热线:</td>
			<td><input type="text" name="hotlineTelephone"  maxlength="12" style="width:120px" class="easyui-validatebox" data-options="validType:'phone'"/></td>
		</tr>
		<tr>
			<td width="100">网址:</td>
			<td colspan="3"><input type="text" name="enterpriseUrl"  maxlength="50"  style="width:350px" class="easyui-validatebox" data-options="validType:'interUrl'" /></td>
		</tr>
	</table>
</form>