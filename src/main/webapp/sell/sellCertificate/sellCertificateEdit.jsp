<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<script type="text/javascript">
		var sgsm_d2;
	function addReceipt()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '承兑汇票选择',   
	    width: 550,   
	    height: 350,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/sellCertificate/addReceipt.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
	var sgsm_d1;
	function addcar()
	{
	 sgsm_d1 = $('<div/>');
	 sgsm_d1.dialog({
		title: '车辆档案选择',   
	    width: 450,   
	    height: 300,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/sellCertificate/addCar.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}  	
</script>
    <form id="certificateEditForm" style="margin-top: 20px">
	<input type="hidden" id="xsCarId" name="xsCarId"/>
	<input type="hidden" id="receipt_Id" name="receiptId"/>
	<input type="hidden" id="certificateId" name="certificateId"/>
	<table align="center" >
		<tr>
			<td  width="75px">VIN号：</td>
			<td><input id="carVinId" name="carVinNumber" readonly="readonly" style="width:150px;background-color:#c0d8d8;"   style="background-color:#c0d8d8;"/>
			</td> 
			<td  width="75px">车辆品牌：</td>
			<td  ><input type="text" name="carBrandName" readonly="readonly"  id="carBrandNameId"  style="width: 175px;background-color:#c0d8d8;" class="easyui-validatebox"  maxlength="20"/>
				<input type="hidden" name="carBrand" id="carBrandId"  />
			</td>
		</tr>
		<tr>
			<td  width="75px">车辆型号：</td>
			<td ><input type="text" name="carModelName" readonly="readonly" id="carModelNameId"  style="width: 150px;background-color:#c0d8d8;" class="easyui-validatebox"  maxlength="20"/>
				<input type="hidden" name="carModelId" id="carModelId_Id"  />
			</td>
			<td  width="75px">颜色：</td>
			<td ><input type="text" name="colorName" readonly="readonly"  id="colorNameId"  style="width: 175px;background-color:#c0d8d8;" class="easyui-validatebox"  maxlength="20"/>
				<input type="hidden" name="carColor" id="carColorId"  />
			</td>
		</tr>
		<tr>
			<td style="75px">合格证：</td>
			<td><input type="text" name="xsCarCertificate" style="width:150px;" maxlength="20" class="easyui-validatebox" data-options="required:true"></td>
			<td style="75px">合格证状态：</td>
			<td><input type="text" name="xsCarCertificateState" id="certificateState" style="width:175px" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CERTIFICATESTATE%>',
								required:true,
								editable:false,
								valueField:'id',   
								textField:'text',
								mode : 'remote',
								validType:'isSelected[\'#certificateState\']',
								invalidMessage : '请从下拉框中选择合格证状态'"></td>
		</tr>
		<tr>
			<td style="75px">票据编号:</td>
	 		<td colspan="3">
	 				<input id="receipt_code" name="receiptCode"   readonly="readonly"  style="width:400px;background-color:#c0d8d8;" 
	 				onkeypress=" if(event.keyCode==13) { addReceipt(); return false;}" class="easyui-validatebox"/>
					<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addReceipt();"/>
			</td> 
		</tr>
		<tr>
			<td width="75px">开票银行:</td>
			<td  colspan="3"><input type="text" name="bankName" readonly="readonly" id="receiptBankId"  style="width: 415px;background-color:#c0d8d8;" 
					class="easyui-validatebox"  maxlength="20"/>
				<input type="hidden"  name="receiptBank"  id="bank_id"/>
			</td>
		</tr>
		<tr>
			<td width="75px">出票日期:</td>
			<td><input type="text" name="receiptStartDate" disabled="disabled" id="receiptStartDateId" style="width:150px " class="Wdate" onfocus="WdatePicker({});" ></td>
			<td width="75px">到期日期:</td>
			<td><input type="text" name="receiptEndDate"  disabled="disabled" id="receiptEndDateId" style="width:150px " class="Wdate" onfocus="WdatePicker({});"></td>
		</tr>
		<tr>
		<td width="75px">赎回期:</td>
			<td><input type="text" name="redemptionDate"  id="redemptionDate"  id="receiptStartDateId" style="width:150px " class="Wdate"  ></td></tr>
		
		<tr>
			<td width="75px">备注:</td>
			<td colspan="3"><textarea  name="remark"  style="width:415px;height:50px;resize:none;" maxlength="50"></textarea></td>
		</tr>
	</table>
</form>
