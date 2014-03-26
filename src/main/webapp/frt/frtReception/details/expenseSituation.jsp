<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 前台接车-费用情况  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/expenseSituation.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="west" style="padding:30px;background:#eee;width: 360px;"
		border="false">
		<form id="frtReceptionExpenseSituationForm">
			<table>
				<tr>
					<th>收费项目</th>
					<th>金额</th>
					<th>说明</th>
				</tr>
				<tr>
					<td>工时费:</td>
					<td><input type="text" id="frtReceptionPreclrWktimeAmount"
						name="preclrWktimeAmount" readonly="readonly" />
					</td>
					<td><input type="text" value="工时×工时单价"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>材料费:</td>
					<td><input type="text" id="frtReceptionPreMprMatAmount"
						name="preMprMatAmount" readonly="readonly"/>
					</td>
					<td><input type="text" value="附材料清单"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>管理费:</td>
					<td><input type="text" id="frtReceptionPreclrManagementFee" name="preclrManagementFee"
						onkeyup="frtMoneyAmount();"  readonly="readonly" />
					</td>
					<td><input type="text"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>其他费:</td>
					<td><input type="text" id="frtReceptionOtherAmount" name="otherAmount"
						readonly="readonly" />
					</td>
					<td><input type="text" value="收费项目总合"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>费用合计:</td>
					<td><input type="text" id="frtReceptionAmountTotal" name="amountTotal"
						readonly="readonly" />
					</td>
					<td><input type="text" value="(1+2+3+4+5)"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>实际收款:</td>
					<td><input type="text" id="frtReceptionActualCost" name="actualCost"
						readonly="readonly" />
					</td>
					<td><input type="text" value="费用合计减去优惠价"  readonly="readonly" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div region="center" style="background:#eee;" border="false">
		<div class="easyui-layout" fit="true" border="false">
			<div region="north" style="padding:3px;background:#eee;height:30px;"
				border="false">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>其他费用明细:</strong>
			</div>
			<div region="center" style="padding:3px;background:#eee;"
				border="false">
				<table id="frtReceptionExpenseSituationOtherExpense"></table>
			</div>
		</div>
	</div>
</div>
