<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  <body>
		<!-- 选择查看弹出窗口-->
	<div id="choice" class="easyui-window" modal="true" title="选择查看" style="width:1250px;height:600px;background: #eee;"closed="true" maximizable="false" minimizable="false" collapsible="fasle">
	</div>
	<!-- 选择查看弹出窗口 -->
		<form id="Car_policy_management_showdialog_id">
			<div class="easyui-layout" style="width:500px;height:400px;background: #eee" fit="true">
				<div region="north" split="false" style="height:180px;background: #eee" border="false">
					<table>
							<tr>
								<td style="width:60px">代保日期</td>
								<td><input type="checkbox" style="width:25px" name="insuranceDate"/></td>
								<td style="width:60px">车船税</td>
								<td><input type="checkbox" style="width:25px" name="travelTax"/></td>
							
								<td style="width:60px">记录编号</td>
								<td><input type="checkbox" style="width:25px" name="recordNumber"/></td>
								<td style="width:60px">车船税号</td>
								<td><input type="checkbox" style="width:25px" name="travelTaxNumber"/></td>
						
								<td style="width:60px">审核情况</td>
								<td><input type="checkbox" style="width:25px" name="auditSituation"/></td>
								<td style="width:60px">上交保费</td>
								<td><input type="checkbox" style="width:25px" name="premiums"/></td>
							
								<td style="width:60px">保险单号</td>
								<td><input type="checkbox" style="width:25px" name="insuranceNumber"/></td>
								<td style="width:60px">实付款</td>
								<td><input type="checkbox" style="width:25px" name="actuallyPaid"/></td>
							
								<td style="width:60px">商保发票号</td>
								<td><input type="checkbox" style="width:25px" name="invoiceNumber"/></td>
								<td style="width:60px">登记年月</td>
								<td><input type="checkbox" style="width:25px" name="registerDate"/></td>
							
								<td style="width:60px">交强单号</td>
								<td><input type="checkbox" style="width:25px" name="jqSingleNumber"/></td>
								<td style="width:60px">客户实付</td>
								<td><input type="checkbox" style="width:25px" name="customerPaid"/></td>
							</tr>
							<tr>
								<td style="width:60px">交强发票号</td>
								<td><input type="checkbox" style="width:25px" name="jqInvoiceNumber"/></td>
								<td style="width:60px">客户汇款</td>
								<td><input type="checkbox" style="width:25px" name="customerBacksection"/></td>
							
								<td style="width:60px">车辆牌照</td>
								<td><input type="checkbox" style="width:25px" name="carBrand"/></td>
								<td style="width:60px">利润</td>
								<td><input type="checkbox" style="width:25px" name="profit"/></td>
						
								<td style="width:60px">VIN码</td>
								<td><input type="checkbox" style="width:25px" name="vinNumber"/></td>
								<td style="width:60px">联系人</td>
								<td><input type="checkbox" style="width:25px" name="contact"/></td>
							
								<td style="width:60px">被保险人</td>
								<td><input type="checkbox" style="width:25px" name="theInsuredPerson"/></td>
								<td style="width:60px">优惠率</td>
								<td><input type="checkbox" style="width:25px" name="discountRate"/></td>
							
								<td style="width:60px">上年保险公司</td>
								<td><input type="checkbox" style="width:25px" name="nextInsuranceCompany"/></td>
								<td style="width:60px">业务提成</td>
								<td><input type="checkbox" style="width:25px" name="commissionBusiness"/></td>
							
								<td style="width:60px">保险公司</td>
								<td><input type="checkbox" style="width:25px" name="insuranceCompany"/></td>
								<td style="width:60px">赠送项目</td>
								<td><input type="checkbox" style="width:25px" name="giftItems"/></td>
							</tr>
							<tr>
								<td style="width:60px">身份证号码</td>
								<td><input type="checkbox" style="width:25px" name="idCard"/></td>
								<td style="width:60px">票券编号</td>
								<td><input type="checkbox" style="width:25px" name="billsNumber"/></td>
							
								<td style="width:60px">被保地址</td>
								<td><input type="checkbox" style="width:25px" name="insuredAddress"/></td>
								<td style="width:60px">赠送价值</td>
								<td><input type="checkbox" style="width:25px" name="presentationValue"/></td>
							
								<td style="width:60px">车辆价格</td>
								<td><input type="checkbox" style="width:25px" name="carPrice"/></td>
								<td style="width:60px">刷卡类型</td>
								<td><input type="checkbox" style="width:25px" name="credirCardTypes"/></td>
							
								<td style="width:60px">联系电话</td>
								<td><input type="checkbox" style="width:25px" name="tel"/></td>
								<td style="width:60px">收款日期</td>
								<td><input type="checkbox" style="width:25px" name="receiptDate"/></td>
							
								<td style="width:60px">发动机号</td>
								<td><input type="checkbox" style="width:25px" name="engineNumber"/></td>
								<td style="width:60px">业务部门</td>
								<td><input type="checkbox" style="width:25px" name="businessUnits"/></td>
							
								<td style="width:60px">厂牌型号</td>
								<td><input type="checkbox" style="width:25px" name="brandModel"/></td>
								<td style="width:60px">经办人</td>
								<td><input type="checkbox" style="width:25px" name="manager"/></td>
							</tr>
							<tr>
								<td style="width:60px">车身颜色</td>
								<td><input type="checkbox" style="width:25px" name="carColor"/></td>
								<td style="width:60px">发票类型</td>
								<td><input type="checkbox" style="width:25px" name="invoiceType"/></td>
							
								<td style="width:60px">商业到期</td>
								<td><input type="checkbox" style="width:25px" name="businessDate"/></td>
								<td style="width:60px">接待人员</td>
								<td><input type="checkbox" style="width:25px" name="receptor"/></td>
						
								<td style="width:60px">交强到期</td>
								<td><input type="checkbox" style="width:25px" name="jqDate"/></td>
								<td style="width:60px">保险分类</td>
								<td><input type="checkbox" style="width:25px" name="insuranceGroup"/></td>
							
								<td style="width:60px">商业险</td>
								<td><input type="checkbox" style="width:25px" name="businessInsurance"/></td>
								<td style="width:60px">备注</td>
								<td><input type="checkbox" style="width:25px" name="memo"/></td>
						
								<td style="width:60px">商保实收</td>
								<td><input type="checkbox" style="width:25px" name="businessPaid"/></td>
								<td style="width:60px">交强实收</td>
								<td><input type="checkbox" style="width:25px" name="jqPaid"/></td>
								<td style="width:60px">交强险</td>
								<td><input type="checkbox" style="width:25px" name="jqInsurance"/></td>
							</tr>
							<tr></tr><tr></tr><tr></tr>
							<tr>
								<td align="left" rowspan="1" colspan="21"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" plain="true" onclick="doSubmit2($('#Car_policy_management_showdialog_id'),'carInsuranceManageAction_doFindByCondition')">确定</a>&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" iconCls="icon-print" href="javascript:void(0);" plain="true" onclick="">打印</a></td>
							</tr>
						</table>
						</div>
						<div region="center" style="background:#eee;" fit="true">
						<table align="left" border="1">
						
						<s:if test="#request.list.size()!=0">
						<tr>
						<s:iterator value="#request.list" var="u" status="1" begin="1" end="1"> 
							<s:if test="#u.insuranceDate!=null">
								<td>代保日期</td>
							</s:if>
							<s:if test="#u.recordNumber!=null">
								<td>记录编号</td>
							</s:if>
							<s:if test="#u.auditSituation!=null">
								<td>审核情况</td>
							</s:if>
							<s:if test="#u.insuranceNumber!=null">
								<td>保险单号</td>
							</s:if>
							<s:if test="#u.jqSingleNumber!=null">
								<td>交强单号</td>
							</s:if>
							<s:if test="#u.carBrand!=null">
								<td>车辆牌照</td>
							</s:if>
							<s:if test="#u.vinNumber!=null">
								<td>vin码</td>
							</s:if>
							
							<s:if test="#u.theInsuredPerson!=null">
								<td>被保险人</td>
							</s:if>
							<s:if test="#u.nextInsuranceCompany!=null">
								<td>上年保险公司</td>
							</s:if>
							<s:if test="#u.insuranceCompany!=null">
								<td>保险公司</td>
							</s:if>
							<s:if test="#u.idCard!=null">
								<td>身份证号码</td>
							</s:if>
							<s:if test="#u.insuredAddress!=null">
								<td>被保地址</td>
							</s:if>
							<s:if test="#u.carPrice!=null">
								<td>车辆价格</td>
							</s:if>
							<s:if test="#u.tel!=null">
								<td>联系电话</td>
							</s:if>
							<s:if test="#u.engineNumber!=null">
								<td>发动机号</td>
							</s:if>
							<s:if test="#u.brandModel!=null">
								<td>厂牌型号</td>
							</s:if>
							<s:if test="#u.carColor!=null">
								<td>车辆颜色</td>
							</s:if>
								<s:if test="#u.jqDate!=null">
								<td>交强到期</td>
							</s:if>
							<s:if test="#u.businessDate!=null">
								<td>商业到期</td>
							</s:if>
						
							<s:if test="#u.businessInsurance!=null">
								<td>商业险</td>
							</s:if>
							<s:if test="#u.jqPaid!=null">
								<td>交强实收</td>
							</s:if>
							
							
							<s:if test="#u.travelTax!=null">
								<td>车船税</td>
							</s:if>
							<s:if test="#u.travelTaxNumber!=null">
								<td>车船税号</td>
							</s:if>
							<s:if test="#u.premiums!=null">
								<td>上交保费</td>
							</s:if>
							<s:if test="#u.actuallyPaid!=null">
								<td>实付款</td>
							</s:if>
							<s:if test="#u.registerDate!=null">
								<td>等级年月</td>
							</s:if>
							<s:if test="#u.customerPaid!=null">
								<td>客户实付</td>
							</s:if>
							<s:if test="#u.customerBacksection!=null">
								<td>客户汇款</td>
							</s:if>
							<s:if test="#u.profit!=null">
								<td>利润</td>
							</s:if>
							<s:if test="#u.contact!=null">
								<td>联系人</td>
							</s:if>
							<s:if test="#u.discountRate!=null">
								<td>优惠率</td>
							</s:if>
							<s:if test="#u.commissionBusiness!=null">
								<td>业务提成</td>
							</s:if>
							<s:if test="#u.giftItems!=null">
								<td>商保发票号</td>
							</s:if>
							<s:if test="#u.billsNumber!=null">
								<td>赠送项目</td>
							</s:if>
							<s:if test="#u.presentationValue!=null">
								<td>票券编号</td>
							</s:if>
							<s:if test="#u.credirCardTypes!=null">
								<td>赠送价值</td>
							</s:if>
							<s:if test="#u.receiptDate!=null">
								<td>刷卡类型</td>
							</s:if>
							<s:if test="#u.businessUnits!=null">
								<td>收款日期</td>
							</s:if>
							<s:if test="#u.manager!=null">
								<td>经办人</td>
							</s:if>
							<s:if test="#u.invoiceType!=null">
								<td>发票类型</td>
							</s:if>
							<s:if test="#u.receptor!=null">
								<td>接待人员</td>
							</s:if>
							<s:if test="#u.insuranceGroup!=null">
								<td>保险分类</td>
							</s:if>
							<s:if test="#u.invoiceNumber!=null">
								<td>商保发票号</td>
							</s:if>
							
							<s:if test="#u.jqInvoiceNumber!=null">
								<td>交强发票号</td>
							</s:if>
							<s:if test="#u.businessPaid!=null">
							<td>商保实收</td>
							</s:if>
							<s:if test="#u.jqInsurance!=null">
								<td>交强险</td>
							</s:if>
							
							<s:if test="#u.memo!=null">
								<td>备注</td>
							</s:if>
							</s:iterator> 
							</tr>
							<s:iterator value="#request.list" var="u"> 
							<tr>
								<s:if test="#u.insuranceDate!=null">
									<td><s:property value="#u.insuranceDate"/></td>
								</s:if>
								<s:if test="#u.recordNumber!=null">
									<td><s:property value="#u.recordNumber"/></td>
								</s:if>
								<s:if test="#u.auditSituation!=null">
									<td><s:property value="#u.auditSituation"/></td>
								</s:if>
								<s:if test="#u.insuranceNumber!=null">
									<td><s:property value="#u.insuranceNumber"/></td>
								</s:if>
								<s:if test="#u.jqSingleNumber!=null">
									<td><s:property value="#u.jqSingleNumber"/></td>
								</s:if>
								<s:if test="#u.carBrand!=null">
									<td><s:property value="#u.carBrand"/></td>
								</s:if>
								<s:if test="#u.vinNumber!=null">
									<td><s:property value="#u.vinNumber"/></td>
								</s:if>
								<s:if test="#u.theInsuredPerson!=null">
									<td><s:property value="#u.theInsuredPerson"/></td>
								</s:if>
								<s:if test="#u.nextInsuranceCompany!=null">
									<td><s:property value="#u.nextInsuranceCompany"/></td>
								</s:if>
								<s:if test="#u.insuranceCompany!=null">
									<td><s:property value="#u.insuranceCompany"/></td>
								</s:if>
								<s:if test="#u.idCard!=null">
									<td><s:property value="#u.idCard"/></td>
								</s:if>
								<s:if test="#u.insuredAddress!=null">
									<td><s:property value="#u.insuredAddress"/></td>
								</s:if>
								<s:if test="#u.carPrice!=null">
									<td><s:property value="#u.carPrice"/></td>
								</s:if>
								<s:if test="#u.tel!=null">
									<td><s:property value="#u.tel"/></td>
								</s:if>
								<s:if test="#u.engineNumber!=null">
									<td><s:property value="#u.engineNumber"/></td>
								</s:if>
								<s:if test="#u.brandModel!=null">
									<td><s:property value="#u.brandModel"/></td>
								</s:if>
								<s:if test="#u.carColor!=null">
									<td><s:property value="#u.carColor"/></td>
								</s:if>
								<s:if test="#u.jqDate!=null">
									<td><s:property value="#u.jqDate"/></td>
								</s:if>
								<s:if test="#u.businessInsurance!=null">
									<td><s:property value="#u.businessInsurance"/></td>
								</s:if>
								<s:if test="#u.jqPaid!=null">
									<td><s:property value="#u.jqPaid"/></td>
								</s:if>
								<s:if test="#u.travelTax!=null">
									<td><s:property value="#u.travelTax"/></td>
								</s:if>
								<s:if test="#u.travelTaxNumber!=null">
									<td><s:property value="#u.travelTaxNumber"/></td>
								</s:if>
								<s:if test="#u.premiums!=null">
									<td><s:property value="#u.premiums"/></td>
								</s:if>
								<s:if test="#u.actuallyPaid!=null">
									<td><s:property value="#u.actuallyPaid"/></td>
								</s:if>
								<s:if test="#u.registerDate!=null">
									<td><s:property value="#u.registerDate"/></td>
								</s:if>
								<s:if test="#u.customerPaid!=null">
									<td><s:property value="#u.customerPaid"/></td>
								</s:if>
								<s:if test="#u.customerBacksection!=null">
									<td><s:property value="#u.customerBacksection"/></td>
								</s:if>
								<s:if test="#u.profit!=null">
									<td><s:property value="#u.profit"/></td>
								</s:if>
								<s:if test="#u.contact!=null">
									<td><s:property value="#u.contact"/></td>
								</s:if>
								<s:if test="#u.discountRate!=null">
									<td><s:property value="#u.discountRate"/></td>
								</s:if>
								<s:if test="#u.commissionBusiness!=null">
									<td><s:property value="#u.commissionBusiness"/></td>
								</s:if>
								<s:if test="#u.giftItems!=null">
									<td><s:property value="#u.giftItems"/></td>
								</s:if>
								<s:if test="#u.billsNumber!=null">
									<td><s:property value="#u.billsNumber"/></td>
								</s:if>
								<s:if test="#u.presentationValue!=null">
									<td><s:property value="#u.presentationValue"/></td>
								</s:if>
								<s:if test="#u.credirCardTypes!=null">
									<td><s:property value="#u.credirCardTypes"/></td>
								</s:if>
								<s:if test="#u.receiptDate!=null">
									<td><s:property value="#u.receiptDate"/></td>
								</s:if>
								<s:if test="#u.businessUnits!=null">
									<td><s:property value="#u.businessUnits"/></td>
								</s:if>
								<s:if test="#u.manager!=null">
									<td><s:property value="#u.manager"/></td>
								</s:if>
								<s:if test="#u.invoiceType!=null">
									<td><s:property value="#u.invoiceType"/></td>
								</s:if>
								<s:if test="#u.receptor!=null">
									<td><s:property value="#u.receptor"/></td>
								</s:if>
								<s:if test="#u.insuranceGroup!=null">
									<td><s:property value="#u.insuranceGroup"/></td>
								</s:if>
								<s:if test="#u.invoiceNumber!=null">
									<td><s:property value="#u.invoiceNumber"/></td>
								</s:if>
								<s:if test="#u.jqInvoiceNumber!=null">
									<td><s:property value="#u.jqInvoiceNumber"/></td>
								</s:if>
								<s:if test="#u.businessPaid!=null">
									<td><s:property value="#u.businessPaid"/></td>
								</s:if>
								<s:if test="#u.jqInsurance!=null">
									<td><s:property value="#u.jqInsurance"/></td>
								</s:if>
								<s:if test="#u.memo!=null">
									<td><s:property value="#u.memo"/></td>
								</s:if>
							</tr>
						</s:iterator>	
						
						</s:if>
						<s:else>
							暂无相关信息!
						</s:else>
						
							
						</table>
						</div>
					</div>
				</form>   


  </body>
</html>
