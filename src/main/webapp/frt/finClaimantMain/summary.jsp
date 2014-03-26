<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<script type="text/javascript">
var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain/summary.js"></script>
<!-- 索赔结算单汇总 -->
<div class="easyui-layout" fit="true" border="false">
	<div region="north" id="search"
		style="overflow: hidden;padding:3px; background: #eee; height: 60px;"
		border="false">
		<form id="finClaimantMainQueryForm">
			<table>
				<tr>
					<td>结算单号:</td>
					<td><input type="text" name="claimantmId"/></td>
					<td>维修工单号:</td>
					<td><input type="text" name="receptionId"/></td>
					<td>车牌照:</td>
					<td><input type="text" id="finClaimant_summary_carId" name="carId" class="easyui-combobox"
					data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
					valueField : 'id',
					textField : 'text',
					mode : 'remote'"/></td>
					<td>VIN号:</td>
					<td><input type="text" name="carVin"/></td>
					<td>底盘号:</td>
					<td><input type="text" name="carMotorId"/></td>
					<td>客户名称:</td>
					<td><input type="text" name="customName"/></td>
				</tr>
				<tr>
					<td>结算时间:</td>
					<td colspan="3">
					<!--<input type="text" name="claimantmTimeBegin" class="easyui-datetimebox" style="width: 140px;"/>
					~<input type="text" name="claimantmTimeEnd" class="easyui-datetimebox" style="width: 140px;"/>
					-->
					<input id="claimantmTimeBegin" name="claimantmTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimantmTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="claimantmTimeEnd" name="claimantmTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimantmTimeBegin\',{d:0})}'})"/>
					</td>
					<td>开票时间:</td>
					<td colspan="3">
					<!--<input type="text" name="claimantmInvoiceTimeBegin" class="easyui-datetimebox" style="width: 140px;"/>
					~<input type="text" name="claimantmInvoiceTimeEnd" class="easyui-datetimebox" style="width: 140px;"/>
					-->
					<input id="claimantmInvoiceTimeBegin" name="claimantmInvoiceTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimantmInvoiceTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="claimantmInvoiceTimeEnd" name="claimantmInvoiceTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimantmInvoiceTimeBegin\',{d:0})}'})"/>
					</td>
					<td>票据类型:</td>
					<td><input type="text" id="finClaimant_summary_claimantmInvoiceType" name="claimantmInvoiceType" class="easyui-combobox"
					data-options="
					url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
					validType:'isSelected[\'#finClaimant_summary_claimantmInvoiceType\']',
					invalidMessage : '请从下拉框中选择票据类型',
					valueField:'id',  
					textField:'text',
					mode:'remote' "/></td>
					<td>发票编号:</td>
					<td><input type="text" name="claimantmInvoiceNo"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="finClaimantMainSummaryCenter" region="center" style="background: #eee;" border="false">
		<table id="finClaimantMainSummaryDatagrid"></table>
	</div>
</div>