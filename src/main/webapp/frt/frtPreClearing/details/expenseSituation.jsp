<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 前台接车->费用情况 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearing/details/expenseSituation.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="west" style="padding:30px;background:#eee;width: 360px;"
		border="false">
		<form id="frtPreClearingExpenseSituationForm">
			<table>
				<tr>
					<th>收费项目</th>
					<th>金额</th>
					<th>说明</th>
				</tr>
				<tr>
					<td>工时费:</td>
					<td><input type="text" id="frtPreClearingPreclrWktimeAmount"
						name="preclrWktimeAmount" readonly="readonly" />
					</td>
					<td><input type="text" value="工时×工时单价" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>材料费:</td>
					<td><input type="text" id="frtPreClearingPreMprMatAmount"
						name="preMprMatAmount" readonly="readonly" />
					</td>
					<td><input type="text" value="附材料清单" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>管理费:</td>
					<td><input type="text" id="frtPreClearingPreclrManagementFee" name="preclrManagementFee"
						onkeyup="" />
					</td>
					<td><input type="text" value="相关管理费用" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>其他费:</td>
					<td><input type="text" id="frtPreClearingPreclrOtherAmount" name="preclrOtherAmount"
						readonly="readonly" />
					</td>
					<td><input type="text" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>费用合计:</td>
					<td><input type="text" id="frtPreClearingPreclrSumAmount" name="preclrSumAmount"
						readonly="readonly" />
					</td>
					<td><input type="text" value="(1+2+3+4+5)" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>实际收款:</td>
					<td><input type="text" id="frtPreClearingPreclrRealAmount" name="preclrRealAmount"
						readonly="readonly" />
					</td>
					<td><input type="text" readonly="readonly" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div region="center" style="background:#eee;" border="false">
		<div class="easyui-layout" fit="true" border="false">
			<div region="north" style="padding:3px;background:#eee;height: 30px;"
				border="false">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>其他费用明细:</strong>
			</div>
			<div region="center" style="padding:3px;background:#eee;"
				border="false">
				<table id="frtPreClearingExpenseSituationOtherExpense"></table>
			</div>
		</div>
	</div>
</div>
