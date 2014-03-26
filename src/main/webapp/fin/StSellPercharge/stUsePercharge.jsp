<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 维修储备金 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPercharge/stUsePercharge.js"></script>
<div id="cc" class="easyui-layout" fit="true">
	<div data-options="region:'north',border:false" style="height:25px;background:#eee;overflow: hidden;">
		 <form id="StUsePerchargeForm">
			 <table> 
			   <tr>
			    <td>收款日期:</td>
			    <td><input id="ssp_StUsePercharge_repcollTimeStart" name="ssp_StUsePercharge_repcollTimeStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'ssp_StUsePercharge_repcollTimeEnd\',{d:-1})}'})"/></td>
			    <td>至</td>
			    <td><input id="ssp_StUsePercharge_repcollTimeEnd" name="ssp_StUsePercharge_repcollTimeEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'ssp_StUsePercharge_repcollTimeStart\',{d:0})}'})"/></td>
			    <td>车辆牌照:</td>
			    <td><input id="ssp_StUsePercharge_carLicense" name="ssp_StUsePercharge_carLicense"/></td>
			    <td>客户名称:</td>
			    <td><input id="ssp_StUsePercharge_customName" name="ssp_StUsePercharge_customName"/></td>
			   </tr>
			 </table>
		 </form>
	</div>
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
	   <table id="StUsePerchargeTable"></table>
	</div>
</div>
