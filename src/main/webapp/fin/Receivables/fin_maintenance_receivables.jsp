<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 维修应收款 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/Receivables/fin_maintenance_receivables.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height:80px;background:#eee;overflow: hidden;">
		<form id="fin_maintenance_receivables_form">
			<table>
				<tr>
					<td>结算日期:</td>
					<td><input id="s_fmr_preclrTimeStart" name="preclrTimeStart"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_fmr_preclrTimeEnd\',{d:-1})}'})"/></td>
					<td>至</td>
					<td><input id="s_fmr_preclrTimeEnd" name="preclrTimeEnd"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_fmr_preclrTimeStart\',{d:0})}'})"/></td>
					<td>结算金额:</td>
					<td><input id="s_fmr_preclrSumAmount" name="preclrSumAmount"/></td>
					
					<td>接待员:</td>
					<td><input id="s_fmr_stfId" name="stfId" class="easyui-combobox"
						data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						editable : false,
						valueField:'id',  
						panelHeight:130,
						textField:'text'"/></td>
					<td>发票号码:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_BillNum.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
				</tr>
				<tr>
					<td>进厂日期:</td>
					<td><input id="s_fmr_resvRealTimeStart" name="resvRealTimeStart"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_fmr_resvRealTimeEnd\',{d:-1})}'})"/></td>
					<td>至</td>
					<td><input id="s_fmr_resvRealTimeEnd" name="resvRealTimeEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_fmr_resvRealTimeStart\',{d:0})}'})"/></td>
					<td>工单号:</td>
					<td><input id="s_fmr_receptionId" name="receptionId"/></td>
					<td>应收账龄:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_loadReceivablesBillAge.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
					<td>接车分布:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_loadReceptParts.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>客户名称:</td>
					<td><input id="customName" name="s_fmr_customName" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'frtOptionsAction!findAllCustom.action',
						valueField:'id',  
						textField:'text' "/></td>
					<td></td>
					<td></td>
					<td>车牌照:</td>
					<td><input id="carLicense" name="s_fmr_carLicense" class="easyui-combobox" data-options="
					 valueField:'id'
					,textField:'text'
					,mode : 'remote'
					,url:'${pageContext.request.contextPath}/StSellPerchargeAction_findAllCarLicense.action'"/></td>
					<td>VIN号码:</td>
					<td><input id="carVin" name="s_fmr_carVin"/></td>
					
					<td>车品牌:</td>
					<td><input id="carBrand" name="s_fmr_carBrand" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'ReceivablesAction_loadCarBrand.action',
						valueField:'id',  
						textField:'text' "/></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<table id="fin_maintenance_receivablesTable"></table>
	</div>
</div>
