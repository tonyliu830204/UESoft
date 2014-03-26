<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.bas.daoimpl.BasCompanyInformationSetDAOImpl"%>
<!-- 交车结算汇总 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearing/summary.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="north" id="search"
		style="overflow: hidden;padding:3px; background: #eee; height: 60px;"
		border="false">
		<form id="frtPreClearingQueryForm">
			<table>
	      		<tr>
	      			<td>结算单号:</td>
	      			<td><input type="text" name="preclrId"/></td>
	      			<td>维修工单号:</td>
	      			<td><input type="text" name="receptionId"/></td>
	      			<td>车牌照:</td>
	      			<td><input type="text" id="frtPreClearing_summary_carId" name="carLicense" class="easyui-combobox" 
	      			 data-options="
	      			 	url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote' "/></td>
	      			<td>发动机号:</td>
	      			<td><input type="text" name="carMotorId"/></td>
	      			<td>客户名称:</td>
	      			<td colspan="4"><input type="text" name="customName"  style="width: 280px;"/></td>
	      		</tr>
	      		<tr>
	      			<td>结算时间:</td>
	      			<td colspan="3">
	      			<!--<input type="text" id="preclrTimeBegin" name="preclrTimeBegin" style="width: 140px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'preclrTimeEnd\')}'});"/>
	      			~<input type="text" id="preclrTimeEnd" name="preclrTimeEnd" style="width: 140px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'preclrTimeBegin\')}'});"/>
	      			-->
	      			<input id="preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrTimeBegin\',{d:0})}'})"/>
	      			</td>
	      			<td>开票时间:</td>
	      			<td colspan="3">
	      			<!--<input type="text" id="preclrInvoiceTimeBegin" name="preclrInvoiceTimeBegin" style="width:140px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'preclrInvoiceTimeEnd\')}'});"/>
	      			~<input type="text" id="preclrInvoiceTimeEnd" name="preclrInvoiceTimeEnd" style="width: 140px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'preclrInvoiceTimeBegin\')}'});"/>
	      			-->
	      			<input id="preclrInvoiceTimeBegin" name="preclrInvoiceTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrInvoiceTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="preclrInvoiceTimeEnd" name="preclrInvoiceTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrInvoiceTimeBegin\',{d:0})}'})"/>
	      			</td>
	      			<td>票据类型:</td>
	      			<td><input type="text" id="frtPreClearing_summary_preclrInoiceType" name="preclrInoiceType" class="easyui-combobox"
        			data-options="
					url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
					validType:'isSelected[\'#frtPreClearing_summary_preclrInoiceType\']',
					invalidMessage : '请从下拉框中选择票据类型',
					valueField:'id',  
					textField:'text',
					mode:'remote' "/></td>
	      			<td>票据编号:</td>
	      			<td><input type="text" name="preclrNo"/></td>
	      		</tr>
	      	</table>
		</form>
	</div>
	<div id="frtPreClearingSummaryCenter" region="center" style="background: #eee;" border="false">
		<table id="frtPreClearingSummaryDatagrid"></table>
	</div>
</div>