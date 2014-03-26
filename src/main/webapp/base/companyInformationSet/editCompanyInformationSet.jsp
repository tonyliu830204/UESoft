<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 公司信息设定修改 -->
<form id="companyInfomationSetEditForm">
	<table>
		<tr>
			<td>参数编号:</td>
			<td><input type="text" name="ciId" style="width:160px; background-color: #c0d8d8;" readonly="readonly"/>
			    <input type="hidden" name="ciType"/>
			</td>
			<td>参数名称:</td>
			<td><input type="text" name="ciName" style="width:160px; background-color: #c0d8d8;" readonly="readonly"/></td>
		</tr>
		<tr>
			<td style="vertical-align:top;">参数值:</td>
			<td colspan="3"><textarea style="width: 380px; height: 245px;" name="ciValue"></textarea></td>
		</tr>
	</table>
</form>