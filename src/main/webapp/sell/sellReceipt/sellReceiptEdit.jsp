<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<script type="text/javascript" src="sell/sellReceipt/sellReceipt.js"></script>
    <form id="receiptEditForm" style="margin-top: 20px">
	<input type="hidden" id="receiptId" name="receiptId"/>
	<table>
		<tr>
			<td width="80px">票据编号(<span id="receiptCodeLength" style="color: rgb(255, 51, 0);">0</span>):</td>
			<td  colspan="3"><input type="text" id="receiptCode" name="receiptCode" style="width: 318px" class="easyui-validatebox"  maxlength="20" 
			   onkeyup="customArchivesKeyUp('receiptCode','receiptCodeLength');" data-options="required:true,missingMessage:'票据编号为必填项',validType:'recipt'"/></td>
		</tr>
		<tr>
			<td width="70px">开票银行:</td>
			<td colspan="3"><input type="text" name="receiptBank" id="receiptBank" class="easyui-combobox" style="width: 318px"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BANKTYPE%>',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#receiptBank\']',
								invalidMessage : '请从下拉框中选择审核情况'"/></td>	
		</tr>
		<tr>
			<td width="70px">金额:</td>
		    <td colspan="3"><input type="text" name="receiptMoney"  style="width:320px " class="easyui-numberbox"  
		    data-options="required:true,missingMessage:'金额为必填项',validType:'monery'" maxlength="12"/></td>
		
		</tr>
		<tr>
			<td width="70px">出票日期:</td>
			<td><input type="text" name="receiptStartDate" id="receiptStartDate" style="width:120px " class="Wdate" onfocus="WdatePicker({onpicked:pickedFunc});" ></td>
			<td width="70px">到期日期:</td>
			<td><input type="text" name="receiptEndDate" id="receiptEndDate" style="width:120px " class="Wdate" onfocus="WdatePicker({});"></td>
		
		</tr>
		<tr>
			<td width="70px">备注:</td>
			<td colspan="3"><textarea  name="remark"  style="width:320px;height: 50px;resize:none;" maxlength="50"  class="easyui-validatebox" data-options="validType:'characterDigit'"></textarea></td>
		</tr>
	</table>
</form>
