<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearing/details/claimMoney.js"></script>
    <!-- 索理赔信息 -->
	<form id="claimMoneyForm" >
		<table align="left" >
			<tr>
				<td>是否索理赔</td>
				<td><input  type="checkbox" name="finTag" id="finTag"  value="<%=Contstants.CLAIM_TAG.CLAIMNO %>" onclick="changeSelectedState();"/>
				</td>
				<td>全额（索）理赔:</td>
				<td><input type="checkbox" name="finAllTag" id="finAllTag" disabled="disabled"  value="<%=Contstants.CLAIM_TAG.CLAIMNO %>"/>
				</td>
			</tr>
			<tr>
				<td>保险公司、汽车厂商:</td>
				<td colspan="4">
				<input type="text" id="frtPreClearing_claimMoney_finComId" name="finComId" style="width:240px" class="easyui-combobox"
							data-options="
							url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimManufacturers.action',
							required : true,
							editable : false,
							missingMessage : '索赔厂商编号为必填项',  
							valueField:'id',  
							textField:'text' "/>
				</td>
			</tr>
		</table>
	</form>