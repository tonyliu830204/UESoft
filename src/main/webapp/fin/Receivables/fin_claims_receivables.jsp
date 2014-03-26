<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 索赔应收款 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/Receivables/fin_claims_receivables.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height:80px;background:#eee;overflow: hidden;">
		<form id="fin_claims_receivables_form">
			<table>
				<tr>
					<td>结算日期:</td>
					<td><input id="s_fcr_claimantmTimeStart" name="s_fcr_claimantmTimeStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_fcr_claimantmTimeEnd\',{d:-1})}'})"/></td>
					<td>至</td>
					<td><input id="s_fcr_claimantmTimeEnd" name="s_fcr_claimantmTimeEnd"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_fcr_claimantmTimeStart\',{d:0})}'})"/></td>
					<td>结算金额:</td>
					<td><input id="s_fcr_claimantmAmount" name="s_fcr_claimantmAmount"/></td>
					<td>接待员:</td>
					<td><input id="s_fcr_stfName" name="s_fcr_stfName" class="easyui-combobox"
						data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						editable : false,
						valueField:'id',  
						panelHeight:130,
						textField:'text'"/></td>
					<td>车品牌:</td>
					<td><input id="s_fcr_cbrdName" name="s_fcr_cbrdName" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'ReceivablesAction_loadCarBrand.action',
						valueField:'id',  
						textField:'text' "/></td>	
				</tr>
				<tr>
					<td>进厂日期:</td>
					<td><input id="s_fcr_resvRealTimeStart" name="s_fcr_resvRealTimeStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_fcr_resvRealTimeEnd\',{d:0})}'})"/></td>
					<td>至</td>
					<td><input id="s_fcr_resvRealTimeEnd" name="s_fcr_resvRealTimeEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_fcr_resvRealTimeStart\',{d:0})}'})"/></td>
					<td>工单号:</td>
					<td><input id="s_fcr_receptionId" name="s_fcr_receptionId"/></td>
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
					<td>索赔厂商:</td>
					<td><input id="s_fcr_relcampName" name="s_fcr_relcampName" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'ReceivablesAction_loadRelcamp.action',
						valueField:'id',  
						textField:'text' "/></td>
					<td></td>
					<td></td>
					<td>车牌照:</td>
					<td><input id="s_fcr_receLicense" name="s_fcr_receLicense" class="easyui-combobox" data-options="
					 valueField:'id'
					,textField:'text'
					,mode : 'remote'
					,url:'${pageContext.request.contextPath}/StSellPerchargeAction_findAllCarLicense.action'"/></td>
					
					<td>VIN号码:</td>
					<td><input id="s_fcr_carVin" name="s_fcr_carVin"/></td>
					<td>发票号码:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_BillNum.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
					
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<table id="fin_claims_receivablesTable"></table>
	</div>
</div>
