<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<!-- 交车结算明细 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearing/details.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false" style="overflow: hidden;width:100%;height:100%;">
	<div style="background: #eee; height:260px;" data-options="region:'north',title:'汇总信息',border:false">
		<form id="frtPreClearingAddForm">
			<table id="frtPreClearing">
			    <tr>
        			<td>车牌照:</td>
        			<td><input type="text" style="width:110px;" id="frtPreClearing_detail_carId" name="carId" class="easyui-combobox" readonly="readonly"
        			 data-options="	url : 'frtOptionsAction!findAllCarLicense.action',
					 required : true,
					 disabled : true,
					 valueField : 'id',
					 textField : 'text', 
					 mode : 'remote'"/></td>
        			<td>VIN码:</td>
        			<td><input type="text" id="frtPreClearing_detail_carVin" name="carVin" class="easyui-validatebox" readonly="readonly"  data-options=" required:true,disabled:true"/></td>
        			<td>客户名称:</td>
        			<td colspan="3"><input type="text" id="frtPreClearing_detail_customName" name="customName" class="easyui-validatebox" readonly="readonly" style="width:380px;" data-options=" required : true,disabled:true"/></td>
        			<td>电话号码:</td>
        			<td><input type="text" id="frtPreClearing_detail_carRelationTel1" name="carRelationTel1" style="width: 140px;" class="easyui-validatebox"  readonly="readonly"
        			data-options="missingMessage : '票据编号为必填项' "/></td>
        		</tr>
        		<tr>
        			<td>维修工单号:</td>
        			<td><input type="text" name="receptionId" id="preClearing_receptionId" class="easyui-validatebox" readonly="readonly" data-options="required : true, disabled:true"/></td>
        			<td>票据类型:</td>
        			<td><input type="text" id="frtPreClearing_detail_preclrInoiceType" name="preclrInoiceType" class="easyui-combobox"
                    style="width:110px;"
                    data-options="
        			required : true,
        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>', 
        			missingMessage : '票据类型为必填项',
        			validType:'isSelected[\'#frtPreClearing_detail_preclrInoiceType\']',
					invalidMessage : '请从下拉框中选择票据类型',
					mode:'remote',
        			valueField : 'id',
        			textField : 'text',
        			onLoadSuccess:function(){
        				var value=$('#frtPreClearing_detail_preclrInoiceType').combobox('getValue');
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
        			}"/></td>
        			<td>票据编号:</td>
        			<td colspan="3"><input type="text" id="frtPreClearing_detail_preclrNo" name="preclrNo" style="width:380px;"/></td>
        			<td>开票时间:</td>
        			<td id="invoiceTime"><input type="text" name="preclrInvoiceTime" id="frtPreClearing_detail_preclrInvoiceTime"
        			 	class="easyui-datetimebox" style="width: 140px;" value="{now}"	data-options="	editable : false "/></td>
        		</tr>
        		<tr>
					<td>其他费用:</td>
        			<td><input type="text" id="addPreclrOtherAmount" name="preclrOtherAmount" readonly="readonly"
        				class="easyui-numberbox" data-options=" groupSeparator:',' "/></td>
        			<td>管理费:</td>
        			<td><input type="text" id="addPreclrManagementFee" name="preclrManagementFee"  readonly="readonly"
        				class="easyui-numberbox" onkeyup="frtPreClearingAddFormKeyUp();" data-options=" precision:2,groupSeparator:',', disabled:true "/></td>
        			<td>接待员:</td>
	       			<td colspan="3"><input type="text" id="frtPreClearing_detail_stfId" name="stfId" style="width:380px;" class="easyui-combobox"  readonly="readonly"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						data-options="
						disabled:true,
						required : true,
						url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						valueField:'id',  
						textField:'text' "/></td>
        			<td>结算时间:</td>
        			<td><input type="text" name="preclrTime" id="addPreclrTime"  readonly="readonly"
        			class="easyui-datetimebox" style="width: 140px;"
						value="{now}" data-options="disabled:true,editable : false "/></td>
        		</tr>
        		<tr>
        			<td>材料费用合计(未折扣):</td>
        			<td><input type="text" id="addPreMprMatAmount" name="preMprMatAmount" class="easyui-numberbox"  readonly="readonly"
        			 style="width: 110px;"
        			data-options=" precision:2,groupSeparator:',', disabled:true"/></td>
        			<td>材料费用合计(已折扣):</td>
        			<td><input type="text" id="addPreMprMatAmountOld" name="preMprMatAmountOld" class="easyui-numberbox"  readonly="readonly"
        			 style="width: 110px;"
        			data-options=" precision:2,groupSeparator:',', disabled:true,
        			required : true "/></td>
        			<td id="materialRateOfText">材料费用合计折扣率(%):</td>
        			<td id="materialRateOfValue"><input type="text" id="addPreclrMaterialRate" name="preclrMaterialRate" class="easyui-numberbox"
        			 style="width: 120px;"
        			data-options="min:0,max:100 ,precision:2,
        			missingMessage: '材料折扣率为必填项' " onblur="frtPreClearingAddFormKeyUp();"/>
        			</td>
   				    <td id="materialRateAsSumOfText" >材料优惠金额:</td>
        			<td id="materialRateAsSumOfValue" ><input type="text" id="addPreclrMaterialRateAsSum" class="easyui-numberbox"
        			 style="width: 120px;" onblur="materialRateOrSumOnBlur();"
        			data-options="min:0,precision:2"/>
        			</td>
        			<td>结算单号:</td>
        			<td><input type="text" id="frtPreClearing_detail_preclrId" name="preclrId" class="easyui-validatebox" readonly="readonly" style="width:140px;" data-options="required : true, disabled:true"/></td>
        		</tr>
        		<tr>
        		    <td>工时费用合计:</td>
        			<td><input type="text" id="addPreclrWktimeAmount" name="preclrWktimeAmount" class="easyui-numberbox"  readonly="readonly"
        			 style="width: 110px;"
        			data-options=" precision:2,groupSeparator:',', disabled:true"/></td>
        			<td>总费用合计:</td>
        			<td><input type="text" id="addPreclrSumAmount" name="preclrSumAmount" readonly="readonly"
        			 style="width: 110px;"	 class="easyui-numberbox"
        			 data-options=" precision:2,groupSeparator:',', disabled:true"/></td> 
        			<td  id="wktimeRateOfText" >工时折合扣率(%):</td>
        			<td  id="wktimeRateOfValue"><input type="text" id="addPreclrWktimeRate" name="preclrWktimeRate" class="easyui-numberbox" style="width: 120px;"
        			data-options="min:0,max:100 ,precision:2,missingMessage: '工时折扣率为必填项' " onblur="frtPreClearingAddFormKeyUp();"/>
        			</td>
        			<td  id="wktimeRateAsSumOfText" >工时优惠金额:</td>
        			<td  id="wktimeRateAsSumOfValue" ><input type="text" id="addPreclrWktimeRateAsSum" class="easyui-numberbox"
        			 style="width: 120px;" onblur="wktimeRateOrSumOnBlur();"
        			data-options="min:0,precision:2" />
        			</td>
        			<td>维修类别:</td>
        			<td><input type="text" id="frtPreClearing_detail_reptId" name="reptId" class="easyui-combobox"  readonly="readonly"
						style="width:140px;"
						data-options="
						disabled:true,
						required : true,
						url : 'frtOptionsAction!findAllReptype.action',
						valueField:'id',  
						textField:'text' "/></td>
        		</tr>
        		<tr>
        			<td>合计折扣率(%):</td>
        			<td><input type="text" id="addPreclrSumRate" name="preclrSumRate"  class="easyui-numberbox"  readonly="readonly"
        			 style="width: 110px;"
        			data-options="min:0,max:100, precision:2, disabled:true"/></td>
        			<td>实际费用:</td>
        			<td><input type="text" id="addPreclrRealAmount" name="preclrRealAmount" readonly="readonly" 
        			 style="width: 110px;" class="easyui-numberbox"
        			data-options=" precision:2,groupSeparator:',', disabled:true"/></td>
        			<td>成本费用(未税):</td>
        			<td><input type="text" id="frtPreClearing_detail_preclrNoTaxCost" name="preclrNoTaxCost" readonly="readonly" class="easyui-numberbox" 
        			style="width: 130px;"  readonly="readonly"
        			data-options=" precision:2,groupSeparator:',', disabled:true"/></td>
        			<td >成本费用(含税):</td>
        			<td><input type="text" id="frtPreClearing_detail_preclrTaxCost" name="preclrTaxCost" readonly="readonly" class="easyui-numberbox"
        			style="width: 130px;"  readonly="readonly"
        			data-options=" precision:2,groupSeparator:',', disabled:true"/></td>
        		</tr>
        		<tr>
        		    <td>结算说明:</td>
        			<td colspan="3">
        				<textarea rows="" cols=""  name="preclrInstr" style="width:400px;height:50px;"></textarea>
        			</td>
        			<td>结算备注:</td>
        			<td colspan="3">
        				<textarea rows="" cols=""  name="preclrRemark" style="width:400px;height:50px;"></textarea>
        			</td>
        		</tr>
			</table>
		</form>
	</div>
	<div style="background: #eee;" data-options="region:'center',border:false">
		<div id="frtPreClearingDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div class="datagrid-toolbar" data-options="title:'收费情况',border:'false',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details/expenseSituation.jsp'">
				
			</div>
			<div data-options="title:'材料清单',border:'false',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details/parts.jsp'">
				
			</div>
			<div data-options="title:'项目清单',border:'false',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details/item.jsp'">

			</div>
			<div data-options="title:'索理赔',border:'false',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details/claimMoney.jsp'">

			</div>
			<div data-options="title:'维修档案',border:'false',href:'${pageContext.request.contextPath}/frt/repairArchives.jsp?carIdInput=frtPreClearing_detail_carId'"></div>
			<div data-options="title:'出料显示',border:'false',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details/ex-warehouseParts.jsp'"></div>
		</div>
	</div>
</div>
