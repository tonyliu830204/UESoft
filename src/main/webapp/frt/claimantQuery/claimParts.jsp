<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 索赔综合查询-索理赔配件查询-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/claimantQuery/claimParts.js"></script>
<div class="easyui-layout"
	style="overflow: hidden;width:800px;height:600px;" fit="true"
	border="false">
	<div region="north" title="查询条件"
		style="background:#eee;height:102px;padding:3px;" border="false">
		<form id="frtWorkOrderClaimPartsQueryForm">
			<table>
				<tr>
					<td>索赔日期:</td>
					<td colspan="2">
					<!--<input class="Wdate"" name="claimantTimeBegin"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
					至<input type="text" class="Wdate" name="claimantTimeEnd"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
					-->
						<input id="claimantTimeBegin" name="claimantTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimantTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="claimantTimeEnd" name="claimantTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimantTimeBegin\',{d:0})}'})"/>
					</td>
					<td>配件代码:</td>
					<td><input type="text"  name="partsCode" style="width:140px;" /></td>
					<td>付款情况:</td>
					<td>
						<input type="text" id="claimsPaymentThing" name="paymentThing"
						class="easyui-combobox"
						data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.PAYMENTTHING_TAG.PAYMENTTHINGKEY %>',
						valueField:'id',  
						textField:'text',
						validType:'isSelected[\'#claimsPaymentThing\']',
						invalidMessage : '请从下拉框中选择付款情况',
						mode:'remote'  " />
					</td>
				</tr>
				<tr>
					<td>收款日期:</td>
					<td colspan="2">
					<!--<input class="Wdate"  name="BalanceTimeBegin"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
					至<input type="text" class="Wdate" name="BalanceTimeEnd"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
					-->
						<input id="BalanceTimeBegin" name="BalanceTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'BalanceTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="BalanceTimeEnd" name="BalanceTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'BalanceTimeBegin\',{d:0})}'})"/>
					</td>
					<td>配件名称:</td>
					<td><input type="text"  name="partsName" style="width:140px;" /></td>
					<td>车辆牌照:</td>
					<td><input type="text" id="frtWorkOrderClaimPartsQueryCarId" name="carId" class="easyui-combobox"
						data-options="
						url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote'"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="frtWorkOrderClaimPartsDatagrid_center" region="center" style="background:#eee;" border="false">
		<table id="frtWorkOrderClaimPartsDatagrid"></table>
	</div>
</div>
