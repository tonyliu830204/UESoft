<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@ taglib uri="/priveliege" prefix="privilege"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
	
			var sgsm_d2;
			function addCarSel()
			{
			 sgsm_d2 = $('<div/>');
			 sgsm_d2.dialog({
				title: '销售单选择',   
			    width: 650,   
			    height: 430,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/sellDbProject/carSellInfoSelect.jsp?isInvoice=true',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
</script>
		<form id="sellInvoiceEditForm" style="margin-top: 20px">
		<input type="hidden" name="id"/>
		<input type="hidden" name="invoiceCode"/>
		<input type="hidden" name="isInvoice"/>
		<input type="hidden" name="invoiceExamin"/>
		<input type="hidden"  id="xs_Car_Sel_Id" name="xs_Car_Sel_Id" />
			<table align="center" >
				<tr>
					<td style="width:80px">发票号码:</td>
					<td colspan="3"><input type="text"  name="invoiceNumber" style="width: 390px"  /></td>
				</tr>
				<tr>
		 			<td style="width:80px">销售单:</td>
			      	 <td><div><input id="sellCode" name="sellCode" readonly="readonly" style="background-color: rgb(192, 216, 216); width: 135px;" class="easyui-validatebox" maxlength="11" data-options="required:true,missingMessage:'销售单号为必填项' " onkeypress=" if(event.keyCode==13) { addCarSel(); return false;}">   
						 <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="sellImage"></div>
					  </td>
					  <td style="width:80px" >开票日期:</td>
				    <td ><input type="text" id="invoiceDate" style="width: 150px;" name="invoiceDate" class="Wdate" onfocus="WdatePicker({});"class="easyui-validatebox" data-options="required:true,missingMessage:'开票日期为必填项'" ></td>		
				</tr>
				<tr>
					<td style="width:80px">收款人:</td>
					<td ><input name="person" class="easyui-combobox" style="width: 150px;"	
						data-options="
						url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						valueField:'id',  
						textField:'text',
						multiple:false  "/>
					</td>
					<td style="width:80px">业绩系数:</td>
					<td><input type="text"  name="achievement"  style="width: 150px" /></td>
				</tr>
				<tr>
					<td style="width:80px">开票金额:</td>
					<td><input type="text"  name="invoiceParce" style="width: 150px;" class="easyui-numberbox" maxlength="12" ></td>
					<td style="width:80px">开票人:</td>
					<td ><input name="invoicePerson" class="easyui-combobox" style="width: 150px"	
					data-options="
						url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						valueField:'id',  
						textField:'text',
						multiple:false  "/>
					</td>
				</tr>
				<tr>
				  <td style="width:80px">发票类型:</td>							    
				  <td ><input type="text" name="invoiceType" id="invoiceType" style="width: 150px;"    class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.INVOICETYPE%>',
					editable:false,
		    		valueField:'id',   
		    		textField:'text',
		    		mode : 'remote',
		    		validType:'isSelected[\'#invoiceType\']',
					invalidMessage : '请从下拉框中选择发票类型'"/></td>
				</tr>
				<tr>
				<td style="width:80px">含税折扣金额:</td>
					<td><input type="text"  name="hsDiscount" style="width: 150px;" class="easyui-numberbox" maxlength="12" ></td>
					<td style="width:80px">未税折扣金额:</td>
					<td><input type="text"  name="wsDiscount" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
				</tr>
				<tr>
				<td style="width:80px">折扣税额:</td>
					<td><input type="text"  name="discount" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
					<td style="width:80px">折后含税金额:</td>
					<td><input type="text"  name="afterHsDiscount" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
				</tr>
				<tr>
				<td style="width:80px">未税采购额:</td>
					<td><input type="text"  name="wsPurchase" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
					<td style="width:80px">采购税额:</td>
					<td><input type="text"  name="purchase" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
				</tr>
				<tr>
				<td style="width:80px">折后未税金额:</td>
					<td><input type="text"  name="afterWsMoney" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
					<td style="width:80px">折后税额:</td>
					<td><input type="text"  name="zhTax" style="width: 150px" class="easyui-numberbox" maxlength="12" ></td>
				</tr>
				<tr>
					<td td style="width:80px">开票备注:</td>
					<td colspan="3" ><textarea name="invoiceRemark" style="width: 386px; height: 30px;resize:none;" maxlength="50"></textarea></td>	
				</tr>
			</table>
		</form>
