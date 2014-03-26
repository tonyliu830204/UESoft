<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<form id="targetEditForm" style="margin-top: 20px">
	<input type="hidden" name="stfId" id="stfId"/>
	<input type="hidden" name="brandId" id="brandId"/>
	<input type="hidden" id="enterprise_id" name="enterprise_id"/>
	<table>
		<tr>
			<td style="width: 100px">年月份:</td>
			<td><input type="text" name="data" class="Wdate" onfocus="WdatePicker({});" style="width:200px;"/></td>
		</tr>
		<tr>
			<td style="width: 100px">车品牌:</td>
			<td><input type="text" name="brandType" id="brandType" style="width:200px;"  class="easyui-combobox" data-options="
						required:true,
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						valueField:'id',   
				   		textField:'text',
				   		mode:'remote',
				   		validType:'isSelected[\'#brandType\']',
				   		invalidMessage : '请从下拉框中选择车辆品牌',
				   		editable:false
				   "/></td>
		</tr>
		<tr>
			<td style="width: 100px">销售台数:</td>
			<td><input type="text" name="sellNum"  id="sellNum" style="width:200px;" class="easyui-numberbox" /></td>
		</tr>
		<tr>
			<td style="width: 100px">销售金额:</td>
			<td><input type="text" name="sellMoney" id="sellMoney" style="width:200px;" class="easyui-numberbox"/></td>
		</tr>
		<tr>
			<td style="width: 100px">销售毛利:</td>
			<td><input type="text" name="sellProfit" class="easyui-numberbox" style="width:200px;"/></td>
		</tr>
	</table>
</form>