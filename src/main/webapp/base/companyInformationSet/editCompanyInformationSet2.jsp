<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 公司信息设定修改 -->
<form id="companyInfomationSetEditForm">
	<table>
		<tr>
			<td>参数编号:</td>
			<td><input type="text" name="ciId" style="width: 160px;background-color: #c0d8d8;" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>参数名称:</td>
			<td><input type="text" name="ciName" style="width: 160px;background-color: #c0d8d8;" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>参数值:</td>
			<td><input type="text" name="ciValue" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=IsCome',   
		    valueField:'id',   
		    textField:'text',
		    editable : false,
			required : true " style="width: 160px;"/></td>
		</tr>
	</table>
</form>