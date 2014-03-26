<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<form id="MenuAddForm">
	<input type="hidden" id="reserveId" name="reserveId" />
	<input type="hidden" name="reserveCode" />
	<input type="hidden" name="reserveDete" />
	<input type="hidden" name="customId" />
	<input type="hidden" name="stfId" />
	<input type="hidden" name="vinCode" />
	<input type="hidden" name="predictTakeDate" />
	<input type="hidden" name="paymentMoney" />
	<input type="hidden" name="sellingprice" />
	<table>
		<tr>
			<td width="100">
				预定单号:
			</td>
			<td width="120">
				<input type="text" id="reserveCode" name="reserveCode"
					readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td>
				车品牌：
			</td>
			<td>
				<input type="text" id="queryBrand" name="carBrand"
					readonly="readonly" class="easyui-combobox"
					data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
						editable:false,
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#queryBrand\']',
						invalidMessage : '请从下拉框中选择车辆品牌'" />
			</td>
		</tr>
		<tr>
			<td>
				车辆型号：
			</td>
			<td>
				<input type="text" id="queryModel" name="carModel"
					class="easyui-combobox"
					data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							editable:false,
							valueField:'id',   
							textField:'text',
							mode : 'remote',
							validType:'isSelected[\'#queryModel\']',
						invalidMessage : '请从下拉框中选择车辆型号'" />
			</td>
		</tr>
		<tr>
			<td>
				车身颜色：
			</td>
			<td>
				<input style="width: 110px" name="carColor" id="carColor"
					class="easyui-combobox"
					data-options="
					url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
					multiple:false,
					valueField:'id',  
					textField:'text'
					" />
			</td>
		</tr>

		<tr>
			<td width="100">
				等级:
			</td>
			<td>
				<input style="width: 110px" name="level" class="easyui-combobox"
					data-options="
				url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.LEVEL %>',
				multiple:false,
				valueField:'id',  
				textField:'text'" />
			</td>
		</tr>
		<tr>
			<td width="100">
				备注:
			</td>
			<td>
				<input type="text" id="remark" name="remark" width="120">

			</td>
		</tr>
	</table>
</form>