<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<!-- 索理赔结算单明细 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain/details.js"></script>
<div class="easyui-layout" fit="true" border="false" onclick="toteMoney();">
	<div region="north" title="工单信息"
		style="overflow: hidden;padding:3px;background:#eee;height:200px;"
		border="false">
		<form id="finClaimantMainAddForm">
			<table border="0" width="1000px;">
				<tr>
					<td>结算单号:
					</td>
					<td><input type="text" id="finClaimantMain_detail_preclrId"
						 name="preclrId" readonly="readonly" id="preclrId"  style="width: 140px;background-color:#eeeeee;" />
					</td>
					<td> <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="unconfirmed();"/>
						索赔结算时间:</td>
					<td><input type="text" id="finClaimantMain_detail_claimantmTime" name="claimantmTime" data-options="disabled:true"
						readonly="readonly" class="easyui-datetimebox" value="{now}" editable="false" style="width: 140px;" />
					</td>
					<td>票据类型:</td>
					<td><input type="text" id="finClaimantMain_detail_claimantmInvoiceType" name="claimantmInvoiceType"
						class="easyui-combobox"  style="width: 140px;" 
						data-options="
	        			required : true,
	        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>', 
	        			missingMessage : '票据类型为必填项',
	        			validType:'isSelected[\'#finClaimantMain_detail_claimantmInvoiceType\']',
						invalidMessage : '请从下拉框中选择票据类型',
	        			valueField : 'id',
	        			textField : 'text',
	        			onLoadSuccess:function(){
	        				var value=$('#finClaimantMain_detail_claimantmInvoiceType').combobox('getValue');
	        				if(value!=''&&value=='<%=Contstants.RECEIPT_TAG.OTHERTAX %>'){
	        					document.getElementById('invoiceTime').style.display='none';
	        				}else{
	        					document.getElementById('invoiceTime').style.display='block';
	        				}
	        			},	
	        			onChange:function(newValue, oldValue){
	        				if(newValue=='<%=Contstants.RECEIPT_TAG.OTHERTAX %>'){
	        					document.getElementById('invoiceTime').style.display='none';
	        				}else{
	        					document.getElementById('invoiceTime').style.display='block';
	        				}
	        			}  "/></td>
					<td>发票编号:</td>
					<td><input type="text" id="finClaimantMain_detail_claimantmInvoiceNo" name="claimantmInvoiceNo"   style="width: 140px;" />
					</td>
				</tr>
				<tr>
					<td>工单号:</td>
					<td><input type="text" id="finClaimantMain_detail_receptionId" name="receptionId" readonly="readonly"
						 id="receptionId"  style="width: 140px;background-color:#eeeeee;" />
					</td>
					<td>材料费用合计:</td>
					<td><input type="text" id="addClaimantmPartsAmount" name="claimantmPartsAmount" 
						readonly="readonly" class="easyui-numberbox"
						data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
					</td>
					<td>工时费用合计:</td>
					<td><input type="text" id="addClaimantmTimeAmount" name="claimantmTimeAmount" readonly="readonly"
						 class="easyui-numberbox"
						data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
					</td>
					<td>开票时间:</td>
					<td id="invoiceTime">&nbsp;&nbsp;&nbsp;<input type="text"  id="finClaimantMain_detail_claimantmInvoiceTime"
						name="claimantmInvoiceTime" class="easyui-datetimebox" value="{now}"
						editable="false" style="width: 140px;" />
					</td>
				</tr>
				<tr>
					<td>索理赔单号:</td>
					<td>
						<input type="text" id="finClaimantMain_detail_claimantmId" name="claimantmId"
							style="width: 140px;background-color:#eeeeee;"  readonly="readonly">
					</td>
					<td>其他费用合计:</td>
					<td><input type="text" id="addClaimantmOtherAmount" name="claimantmOtherAmount" readonly="readonly"
						 class="easyui-numberbox"
						data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
					</td>
					<td>管理费用:</td>
					<td><input type="text" id="addClaimantmManagementFee" name="claimantmManagementFee" readonly="readonly"
						 class="easyui-numberbox"
						data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
					</td>
					<td>费用总合计:</td>
					<td><input type="text" id="addClaimantmAmount"
						name="claimantmAmount" readonly="readonly" class="easyui-numberbox"
					data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
					</td>				
					</tr>
					<tr>
						<td>结算员:</td>
						<td><input type="text" id="finClaimantMain_detail_claimantmClrStfId" name="claimantmClrStfId"
							 style="width: 140px;" 
							value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
							class="easyui-combobox"
							data-options="
							disabled:true,
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							required : true,
							editable : false,
							missingMessage : '操作员为必填项',  
							valueField:'id',  
							textField:'text' " />
						</td>
						<td>审核人员:</td>
						<td><input type="text" id="finClaimantMain_detail_claimantmCheckStfId" name="claimantmCheckStfId"
							 style="width: 140px;"
							class="easyui-combobox"
							data-options="
							disabled:true,
							url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							required : false,
							valueField:'id',  
							textField:'text' " />
						</td>
						<td>含税成本:</td>
						<td><input type="text" id="claimantmTaxCost" name="claimantmTaxCost" readonly="readonly"
							 class="easyui-numberbox"
							data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
						</td>
						<td>未税成本:</td>
						<td><input type="text" id="claimantmNoTaxCost" name="claimantmNoTaxCost" readonly="readonly" class="easyui-numberbox"
						data-options=" precision:2,groupSeparator:',', disabled:true"  style="width: 140px;" />
						</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td colspan="7">
							<textarea rows="" cols="" name="claimantmRemark"
    						 style="width:910px;height:50px;"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input id="finClaimantMain_detail_claimantmStatus" type="hidden" name="claimantmStatus"/>
						</td>
					</tr>
			</table>
		</form>
	</div>
	<div region="center" style="background:#eee;" border="false">
		<div class="easyui-tabs" data-options="fit:true,border:false">
			<div style="background:#eee;" data-options="title:'费用情况',href:'${pageContext.request.contextPath}/frt/finClaimantMain/details/expenseSituation.jsp'"></div>
			<div data-options="title:'材料清单',href:'${pageContext.request.contextPath}/frt/finClaimantMain/details/parts.jsp'"></div>
			<div data-options="title:'工时清单',href:'${pageContext.request.contextPath}/frt/finClaimantMain/details/item.jsp'"></div>
		</div>
	</div>
</div>