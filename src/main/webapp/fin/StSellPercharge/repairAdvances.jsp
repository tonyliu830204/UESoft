<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 维修储备金 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPercharge/repairAdvances.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false"
		style="height:30px;background:#eee;overflow: hidden;">
		<form id="fin_ar_group_form">
			<table>
				<tr>
					<td>收款日期:</td>
					<td><input type="text" id="perchargeDateBegin" class="Wdate"
						name="perchargeDateBegin"
						onfocus="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'perchargeDateEnd\')}'});"/> 
						至
						<input
						type="text" id="perchargeDateEnd" class="Wdate"
						name="perchargeDateEnd"
						onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'perchargeDateBegin\')}'});"
						value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd'/>" />
					</td>
					<td>车辆牌照:</td>
					<td><input type="text" id="ssps_carLicense" name="ssps_carLicense" class="easyui-combobox" data-options="
					 valueField:'id'
					,textField:'text'
					,mode : 'remote'
					,url:'${pageContext.request.contextPath}/StSellPerchargeAction_findAllCarLicense.action'" />
					</td>
					<td>VIN号:</td>
					<td><input type="text" id="ssps_vin" name="ssps_vin" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
		<table id="StSellPercharge_datagrid"></table>
	</div>
	<div region="south" split="false" border="false" style="overflow:hidden;height:110px;background:#eee;">
	  <form id="StSellPerchargeForm">
	   <table>
	     <tr>
		     <td>收款单号:</td>
		     <td><input id="perchargeId" name="perchargeId" style="background-color: #c0d8d8;"/></td>
		     <td>收款日期:</td>
		     <td><input id="perchargeDate" name="perchargeDate" readonly="readonly" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
		     <td>车辆牌照:</td>
		     <td><input id="ssp_carLicense" name="ssp_carLicense" required="true" class="easyui-validatebox" onkeypress=" if(event.keyCode==13) { pre_ssp_CarLicense(); return false;}" style="background-color: #c0d8d8;"/><input type="hidden" id="ssp_carId" name="carId"/></td>
		     <td><img src="js/easyui/themes/icons/choice.png" onclick="pre_ssp_CarLicense();"/></td>
		     <td>车品牌:</td>
		     <td><input id="ssp_cbrdName" name="ssp_cbrdName" readonly="readonly" style="background-color: #c0d8d8;"/></td>
		     <td>车类型:</td>
		     <td><input id="ssp_ctypeName" name="ssp_ctypeName" readonly="readonly" style="background-color: #c0d8d8;"/></td>
	     </tr>
	     <tr>
		     <td>客户名称:</td>
		     <td colspan="3"><input id="ssp_customName" name="ssp_customName" readonly="readonly"  style="background-color: #c0d8d8;width:280px;"/></td>
		     <td>联系人:</td>
		     <td><input id="ssp_carRelationPerson" name="ssp_carRelationPerson" style="background-color: #c0d8d8;"/></td>
		     <td></td>
		     <td>电话一:</td>
		     <td><input id="ssp_carRelationTel1" name="ssp_carRelationTel1" style="background-color: #c0d8d8;"/></td>
		     
		     <td>电话二:</td>
		     <td><input id="ssp_carRelationTel2" name="ssp_carRelationTel2" style="background-color: #c0d8d8;"/></td>
	     </tr>
	     <tr>
		     <td>本次收款:</td>
		     <td><input id="curPercharge" name="curPercharge"/></td>
		     <td>付款方式:</td>
		     <td><input id="paymentId" name="paymentId" class="easyui-combobox" 
		     data-options="url:'${pageContext.request.contextPath}/StSellPerchargeAction!loadPaidStyle.action',
				    editable : false,
				    required:true,
					missingMessage:'付款方式必填',
					valueField:'id',
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
		     <td>收款经办:</td>
		     <td><input id="ssp_stfName" name="ssp_stfName" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"/><input type="hidden" id="ssp_stfId" name="ssp_stfId"/></td>
		     <td>票据类型:</td> 
		     <td><input id="preclrInoiceType" name="preclrInoiceType" class="easyui-combobox" 
		     data-options="url : 'baseAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
													    editable : false,
														valueField:'id',
														required:true,
					                                    missingMessage:'票据类型必填',
														panelHeight:130,
														textField:'text'"/></td>
		     <td>票据编号:</td>
		     <td><input id="preclrNo" name="preclrNo"/></td>
		     <td></td>
		     <td>备注:</td>
		     <td colspan="3"><input id="chargeRemark" name="chargeRemark" style="width:280px;"/></td>
	     </tr>
	   </table>
	  </form>
	</div>  
</div>
