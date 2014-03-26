<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 维修储备金 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPercharge/stRecharge.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">
	<div data-options="region:'north',border:false" style="height:25px;background:#eee;overflow: hidden;">
		 <form id="spp_stRecharge_form">
			 <table>
			    <tr>
			     <td>车辆牌照:</td>
			     <td><input id="spp_stRecharge_carLicense" name="spp_stRecharge_carLicense"/></td>
			     <td>客户名称:</td>
			     <td><input id="spp_stRecharge_customName" name="spp_stRecharge_customName"/></td>
			    </tr>
			 </table>
		 </form>
	</div>
	<div region="south" split="false" style="height:300px;border:false;">
	    <div id="cc" class="easyui-layout" fit="true" spilt="false" border="false">  
	        <div region="east" split="false" border="false" style="width:550px;background:#eee;">
	          <table id="YE_StSellPerchargeTable1"></table>
	        </div>  
	        <div region="center" style="background:#eee;" border="false">
	          <table id="YE_StSellPerchargeTable"></table>
	        </div>  
	    </div>  
	</div>  
	<div data-options="region:'center'" style="background:#eee;" border="false">
	    <table id="StRechargeTable"></table>
	</div>
</div>
