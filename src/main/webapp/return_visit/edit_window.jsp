<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title></title>
  </head>
  
  <body>
    <!-- 保单汇总修改弹出窗口类容 -->
	<form id="Car_policy_management_form_edit" >
							<table border="0" align="center">
								<tr>
									<td style="display: none"><input style="width:128px" name="carInsuranceManageId" type="text"/></td>
									<td style="width:60px">代保日期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="insuranceDate" type="text"/></td>
									<td style="width:60px">记录编号</td>
									<td><input style="width:128px" name="recordNumber" type="text"/></td>
									<td style="width:60px">审核情况</td>
									<td><select style="width:128px" name="auditSituation"><option>是</option><option>否</option></select></td>
								</tr>
								<tr>
									<td style="width:60px">保险单号</td>
									<td colspan="3"><input  type="text" style="width:280px" name="insuranceNumber" /></td>
									<td style="width:80px">商保发票号</td>
									<td><input style="width:128px" name="invoiceNumber" type="text"/></td>
								</tr>
								<tr>
									<td style="width:60px">交强单号</td>
									<td colspan="3"><input  style="width:280px" name="jqSingleNumber" type="text"/></td>
									<td style="width:80px">交强发票号</td>
									<td><input style="width:128px" name="jqInvoiceNumber" type="text"/></td>
								</tr>
								<tr>
									<td style="width:60px">车辆牌照</td>
									<td colspan="3"><input style="width:280px" name="carBrand" /><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:80px">VIN码</td>
									<td><input style="width:128px" name="vinNumber" /></td>
								</tr>
								<tr>
									<td style="width:60px">被保险人</td>
									<td colspan="3"><input style="width:280px" name="theInsuredPerson"/><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:90px">上年保险公司</td>
									<td><input style="width:128px" name="nextInsuranceCompany" v/></td>
								</tr>
								<tr>
									<td style="width:60px">保险公司</td>
									<td colspan="3"><input style="width:280px" name="insuranceCompany" /><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:90px">省份证号</td>
									<td><input style="width:128px" name="idCard"/></td>
								</tr>
								<tr>
									<td style="width:60px">被保地址</td>
									<td colspan="3"><input style="width:280px" name="insuredAddress" /></td>
									<td style="width:90px">车辆价格</td>
									<td><input style="width:128px" name="carPrice" /></td>
								</tr>
								<tr>
									<td style="width:60px">联系电话</td>
									<td><input style="width:128px" name="tel" /></td>
									<td style="width:60px">发动机号</td>
									<td><input style="width:128px" name="engineNumber" /></td>
									<td style="width:60px">厂牌型号</td>
									<td><input style="width:128px" name="brandModel" /></td>
								</tr>
								<tr>
									<td style="width:60px">车身颜色</td>
									<td><input style="width:128px" name="carColor"/></td>
									<td style="width:60px">商业到期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="businessDate"/></td>
									<td style="width:60px">交强到期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="jqDate"/></td>
								</tr>
								<tr>
									<td style="width:50px">商业险</td>
									<td><input style="width:128px" name="businessInsurance" /></td>
									<td style="width:60px">商保实收</td>
									<td><input style="width:128px" name="businessPaid" /></td>
									<td style="width:50px">交强险</td>
									<td><input style="width:128px" name="jqInsurance" /></td>
								</tr>
								<tr>
									<td style="width:60px">交强实收</td>
									<td><input style="width:128px" name="jqPaid" /></td>
									<td style="width:50px">车船税</td>
									<td><input style="width:128px" name="travelTax" /></td>
									<td style="width:60px">车船税号</td>
									<td><input style="width:128px" name="travelTaxNumber" /></td>
								</tr>
								<tr>
									<td style="width:60px">上交保费</td>
									<td><input style="width:128px" name="premiums"/></td>
									<td style="width:50px">实付款</td>
									<td><input style="width:128px" name="actuallyPaid"/></td>
									<td style="width:60px">登记年月</td>
									<td><input style="width:128px" name="registerDate" /></td>
								</tr>
								<tr>
									<td style="width:60px">客户实付</td>
									<td><input style="width:128px" name="customerPaid"></td>
									<td style="width:60px">客户回款</td>
									<td><input style="width:128px" name="customerBacksection" /></td>
									<td style="width:30px">利润</td>
									<td><input style="width:128px" name="profit" /></td>
								</tr>
								<tr>
									<td style="width:50px">联系人</td>
									<td><input style="width:128px" name="contact" /></td>
									<td style="width:50px">优惠率</td>
									<td><input style="width:128px" name="discountRate"/></td>
									<td style="width:60px">业务提成</td>
									<td><input style="width:128px" name="commissionBusiness" /></td>
								</tr>
								<tr>
									<td style="width:60px">赠送项目</td>
									<td colspan="3"><input style="width:280px" name="giftItems"/></td>
									<td style="width:90px">票券编号</td>
									<td><input style="width:128px" name="billsNumber" /></td>
								</tr>
								<tr>
									<td style="width:60px">赠送价值</td>
									<td><input style="width:128px" name="presentationValue" /></td>
									<td style="width:60px">刷卡类型</td>
									<td><input style="width:128px" name="credirCardTypes"/></td>
									<td style="width:60px">收款日期</td>
									<td><input style="width:128px" name="receiptDate"/></td>
								</tr>
								<tr>
									<td style="width:60px">业务部门</td>
									<td><select style="width:128px" name="businessUnits"><option>服务部</option></select></td>
									<td style="width:50px">经办人</td>
									<td><select style="width:128px" name="manager" ><option>明月心</option></select></td>
									<td style="width:60px">发票类型</td>
									<td><select style="width:128px" name="invoiceType" ><option>机打发票</option></select></td>
								</tr>
								<tr>
									<td style="width:60px">接待人员</td>
									<td><select style="width:128px" name="receptor" ><option>叶开</option></select></td>
									<td style="width:60px">保险分类</td>
									<td><select style="width:128px" name="insuranceGroup" ><option>全保</option></select></td>
								</tr>
								<tr>
									<td style="width:60px">备注</td>
									<td colspan="6" rowspan="3"><textarea style="width:550px;height:100px;overflow:auto;resize:none" name="memo" value="这车将要报废"></textarea></td>
								</tr>
								<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
								<tr>
								<td align="center" rowspan="100" colspan="6"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" onclick="doSubmit('#Car_policy_management_center_id',$('#Car_policy_management_form_edit'),'carInsuranceManageAction_doUpdate','carInsuranceManageAction_doFindAll')">确定</a> <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0);" onclick="doCancel2()">取消</a></td>
								<td> </td>
								</tr>
				</table>
	</form>
  </body>
</html>
