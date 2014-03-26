<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 维修预收款 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/strepairprepaid/repairAdvances.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height:30px;background:#eee;overflow: hidden;">
		<form id="pre_fin_ar_group_form">
			<table>
				<tr>
					<td>收款日期:</td>
					<td><input type="text" id="pre_perchargeDateBegin" class="Wdate"
						name="pre_perchargeDateBegin"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'pre_perchargeDateEnd\')}'});"/> 
						至
						<input
						type="text" id="pre_perchargeDateEnd" class="Wdate"
						name="pre_perchargeDateEnd"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'pre_perchargeDateBegin\')}'});"
						value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>" />
					</td>
					<td>车辆牌照:</td>
					<td><input type="text" id="pre_ssps_carLicense" name="pre_ssps_carLicense" class="easyui-combobox" data-options="
					 valueField:'id'
					,textField:'text'
					,mode : 'remote'
					,url:'${pageContext.request.contextPath}/StSellPerchargeAction_findAllCarLicense.action'" />
					</td>
					<td>VIN号:</td>
					<td><input type="text" id="pre_ssps_vin" name="pre_ssps_vin" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="pre_StSellPrePercharge_datagrid_div" data-options="region:'center',border:false" style="background:#eee;" border="false">
		<table id="pre_StSellPrePercharge_datagrid"></table>
	</div>
	<div region="south" split="false" border="false" style="overflow:hidden;height:110px;background:#eee;">
	  <form id="pre_StSellPerchargeForm">
	   <table>
	     <tr>
		     <td>收款单号:</td>
		     <td><input id="pre_perchargeId" name="perchargeId" style="background-color: #c0d8d8;"/></td>
		     <td>收款日期:</td>
		     <td><input id="pre_perchargeDate" name="perchargeDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
		     <td>车辆牌照:</td>
		     <td><input id="pre_ssp_carLicense" name="carLicense" required="true" class="easyui-validatebox" onkeypress=" if(event.keyCode==13) { ssp_CarLicense(); return false;}" style="background-color: #c0d8d8;"/><input type="hidden" id="pre_ssp_carId" name="carId"/></td>
		     <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="ssp_CarLicense();"/></td>
		     <td>车品牌:</td>
		     <td><input id="pre_ssp_cbrdName" name="cbrdName" readonly="readonly" style="background-color: #c0d8d8;"/></td>
		     <td>车类型:</td>
		     <td><input id="pre_ssp_ctypeName" name="ctypeName" readonly="readonly" style="background-color: #c0d8d8;"/></td>
	     </tr>
	     <tr>
		     <td>客户名称:</td>
		     <td colspan="3"><input id="pre_ssp_customName" name="customName" readonly="readonly"  style="background-color: #c0d8d8;width:280px;"/></td>
		     <td>联系人:</td>
		     <td><input id="pre_ssp_carRelationPerson" name="carRelationPerson" style="background-color: #c0d8d8;"/></td>
		     <td></td>
		     <td>电话一:</td>
		     <td><input id="pre_ssp_carRelationTel1" name="carRelationTel1" style="background-color: #c0d8d8;"/></td>
		     
		     <td>电话二:</td>
		     <td><input id="pre_ssp_carRelationTel2" name="carRelationTel2" style="background-color: #c0d8d8;"/></td>
	     </tr>
	     <tr>
		     <td>本次收款:</td>
		     <td><input id="pre_curPercharge" name="curPercharge"  class="easyui-numberbox"  data-options="max:<%=Double.MAX_VALUE%>,precision:2"    /></td>
		     <td>付款方式:</td>
		     <td><input id="pre_paymentId" name="paymentId" class="easyui-combobox" 
		     data-options="url:'${pageContext.request.contextPath}/StSellPerchargeAction!loadPaidStyle.action',
													    editable : false,
														valueField:'id',
														required:true,
					                                    missingMessage:'付款方式必填',
														panelHeight:130,
														textField:'text'"/></td>
		     <td>获赠积分:</td>
		     <td><input id="" name=""/></td>
		     <td></td>
		     <td>获赠维修基金:</td>
		     <td><input id="" name=""/></td>
		     <td>当前积分:</td>
		     <td><input id="" name=""/></td>
	     </tr>
	     <tr>
		     <td>票据类型:<input input type="hidden" id="pre_ssp_stfName" name="pre_ssp_stfName"/><input type="hidden" id="pre_ssp_stfId" name="pre_ssp_stfId"/></td> 
		     <td><input id="pre_preclrInoiceType" name="preclrInoiceType" class="easyui-combobox" 
		     data-options="url : 'baseAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
													    editable : false,
													    required:true,
					                                    missingMessage:'票据类型必填',
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
		     <td>票据编号:</td>
		     <td><input id="pre_preclrNo" name="preclrNo"/></td>
		     <td></td>
		     <td>备注:</td>
		     <td colspan="3"><input id="pre_chargeRemark" name="chargeRemark" style="width:280px;"/></td>
	     </tr>
	   </table>
	  </form>
	</div>  
</div>
