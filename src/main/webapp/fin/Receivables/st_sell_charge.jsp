<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 维修应收款 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/Receivables/st_sell_charge.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height:80px;background:#eee;overflow: hidden;">
		<form id="st_sell_charge_form">
			<table>
				<tr>
					<td>销售日期:</td>
					<td><input id="s_ssc_sellmmDateStart" name="s_ssc_sellmmDateStart"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_ssc_sellmmDateEnd\',{d:-1})}'})"/></td>
					<td>至</td>
					<td><input id="s_ssc_sellmmDateEnd" name="s_ssc_sellmmDateEnd"  class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_ssc_sellmmDateStart\',{d:0})}'})"/></td>
					<td>结算金额:</td>
					<td><input id="s_ssc_PreclrSumAmount" name="s_ssc_PreclrSumAmount"/></td>
					<td>车品牌:</td>
					<td><input id="s_ssc_carBrand" name="s_ssc_carBrand" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'ReceivablesAction_loadCarBrand.action',
						valueField:'id',  
						textField:'text'"/></td>
				    <td></td>
				    <td></td>
				</tr>
				<tr>
					<td>结算日期:</td>
					<td><input id="s_ssc_preclrDateStart" name="s_ssc_preclrDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_ssc_preclrDateEnd\',{d:-1})}'})"/></td>
					<td>至</td>
					<td><input id="s_ssc_preclrDateEnd" name="s_ssc_preclrDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_ssc_preclrDateStart\',{d:0})}'})"/></td>
					<td>销售单号:</td>
					<td><input id="s_ssc_cerNo" name="s_ssc_cerNo"/></td>
					<td>应收账龄:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_loadReceivablesBillAge.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
					
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>客户名称:</td> 
					<td><input id="s_ssc_customName" name="s_ssc_customName" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'frtOptionsAction!findAllCustom.action',
						valueField:'id',  
						textField:'text' "/></td>
					<td>车牌照:</td>
					<td><input id="s_ssc_carLicense" name="s_ssc_carLicense" class="easyui-combobox" data-options="
					 valueField:'id'
					,textField:'text'
					,mode : 'remote'
					,url:'${pageContext.request.contextPath}/StSellPerchargeAction_findAllCarLicense'"/></td>
					<td>VIN号码:</td>
					<td><input id="s_ssc_carVin" name="s_ssc_carVin"/></td>
					<td>接车分布:</td>
					<td><input id="" name="" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_loadReceptParts.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
				 </tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<table id="st_sell_chargeTable"></table>
	</div>
</div>
