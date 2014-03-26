<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 工单综合查询->工单结算情况 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkOrder/settlementSituation.js"></script>
<div class="easyui-layout" style="width:800px; height:600px" fit="true"
	border="false">
	<div region="north" style="overflow: hidden;height:230px;"
		border="false">
		<div class="easyui-layout" style="width:800px; height:600px"
			fit="true" border="false">
			<div region="west" style="width: 400px; background:#eee;"
				border="false">
				<div title="结算明细" class="easyui-panel" fit="true" border="false"
					style="padding : 20px;">
					<form id="frtWorkOrderSettlementSituationBalanceForm">
						<table>
							<tr>
								<th>收费项目</th>
								<th>金额</th>
								<th>说明</th>
							</tr>
							<tr>
								<td>工时费:</td>
								<td><input type="text" id="preclrWktimeAmounts"
									name="preclrWktimeAmount" readonly="readonly" />
								</td>
								<td><input type="text" name="tempName1" id="tempName1" class="easyui-validatebox" />
								</td>
							</tr>
							<tr>
								<td>材料费:</td>
								<td><input type="text" id="preMprMatAmounts"
									name="preMprMatAmount" readonly="readonly" />
								</td>
								<td><input type="text" name="tempName2" id="tempName2" class="easyui-validatebox" " />
								</td>
							</tr>
							<tr>
								<td>管理费:</td>
								<td><input type="text" id="preclrManagementFee"
									name="preclrManagementFee" />
								</td>
								<td><input type="text" />
								</td>
							</tr>
							<tr>
								<td>其他费:</td>
								<td><input type="text" id="preclrOtherAmount" name="preclrOtherAmount"
									readonly="readonly" />
								</td>
								<td><input type="text" />
								</td>
							</tr>
							<tr>
								<td>费用合计:</td>
								<td><input type="text" id="preclrSumAmount" name="preclrSumAmount"
									readonly="readonly" />
								</td>
								<td><input type="text" value="(1+2+3+4+5)" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div region="center" title="工时清单" style="background:#eee;"
				border="false">
				<table id="frtWorkOrderSettlementSituationItemDatagrid"></table>
			</div>
		</div>
	</div>
	<div region="center" title="配件清单" style="background:#eee;"
		border="false">
		<table id="frtWorkOrderSettlementSituationPartsDatagrid"></table>
	</div>
</div>