<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  <body>
   <!-- 保单汇总添加弹出窗口类容 -->
	<form id="Car_policy_management_form_add" >
							<table border="0" align="center">
								<tr>
									<td style="width:60px">代保日期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="insuranceDate" /></td>
									<td style="width:60px">记录编号</td>
									<td><input style="width:128px" name="recordNumber" value="jl1241254"/></td>
									<td style="width:60px">审核情况</td>
									<td><select style="width:128px" name="auditSituation"><option>是</option><option>否</option></select></td>
								</tr>
								<tr>
									<td style="width:60px">保险单号</td>
									<td colspan="3"><input  type="text" style="width:280px" name="insuranceNumber" value="fp1235412"/></td>
									<td style="width:80px">商保发票号</td>
									<td><input style="width:128px" name="invoiceNumber" value="sb123541"/></td>
								</tr>
								<tr>
									<td style="width:60px">交强单号</td>
									<td colspan="3"><input  style="width:280px" name="jqSingleNumber" value="jq521462"/></td>
									<td style="width:80px">交强发票号</td>
									<td><input style="width:128px" name="jqInvoiceNumber" value="jqfp825451"/></td>
								</tr>
								<tr>
									<td style="width:60px">车辆牌照</td>
									<td colspan="3"><input style="width:280px" name="carBrand" value="陕F51240"/><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:80px">VIN码</td>
									<td><input style="width:128px" name="vinNumber" value="vin854621 "/></td>
								</tr>
								<tr>
									<td style="width:60px">被保险人</td>
									<td colspan="3"><input style="width:280px" name="theInsuredPerson" value="钟兴通"/><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:90px">上年保险公司</td>
									<td><input style="width:128px" name="nextInsuranceCompany" value="太平洋保险"/></td>
								</tr>
								<tr>
									<td style="width:60px">保险公司</td>
									<td colspan="3"><input style="width:280px" name="insuranceCompany" value="中国保险"/><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a></td>
									<td style="width:90px">省份证号</td>
									<td><input style="width:128px" name="idCard" value="612328185201200334"/></td>
								</tr>
								<tr>
									<td style="width:60px">被保地址</td>
									<td colspan="3"><input style="width:280px" name="insuredAddress" value="西安"/></td>
									<td style="width:90px">车辆价格</td>
									<td><input style="width:128px" name="carPrice" value="500万"/></td>
								</tr>
								<tr>
									<td style="width:60px">联系电话</td>
									<td><input style="width:128px" name="tel" value="13652414523"/></td>
									<td style="width:60px">发动机号</td>
									<td><input style="width:128px" name="engineNumber" value="fd254136"/></td>
									<td style="width:60px">厂牌型号</td>
									<td><input style="width:128px" name="brandModel" value="cp1156656"/></td>
								</tr>
								<tr>
									<td style="width:60px">车身颜色</td>
									<td><input style="width:128px" name="carColor" value="红色"/></td>
									<td style="width:60px">商业到期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="businessDate"/></td>
									<td style="width:60px">交强到期</td>
									<td><input class="easyui-datebox" editable="false" style="width:128px" name="jqDate"/></td>
								</tr>
								<tr>
									<td style="width:50px">商业险</td>
									<td><input style="width:128px" name="businessInsurance" value="20121"/></td>
									<td style="width:60px">商保实收</td>
									<td><input style="width:128px" name="businessPaid" value="3000"/></td>
									<td style="width:50px">交强险</td>
									<td><input style="width:128px" name="jqInsurance" value="200000"/></td>
								</tr>
								<tr>
									<td style="width:60px">交强实收</td>
									<td><input style="width:128px" name="jqPaid" value="456565"/></td>
									<td style="width:50px">车船税</td>
									<td><input style="width:128px" name="travelTax" value="600000"/></td>
									<td style="width:60px">车船税号</td>
									<td><input style="width:128px" name="travelTaxNumber" value="ch216512"/></td>
								</tr>
								<tr>
									<td style="width:60px">上交保费</td>
									<td><input style="width:128px" name="premiums" value="652155"/></td>
									<td style="width:50px">实付款</td>
									<td><input style="width:128px" name="actuallyPaid" value="1152"/></td>
									<td style="width:60px">登记年月</td>
									<td><input style="width:128px" name="registerDate" value="2012-12-01"/></td>
								</tr>
								<tr>
									<td style="width:60px">客户实付</td>
									<td><input style="width:128px" name="customerPaid" value="300000"/></td>
									<td style="width:60px">客户回款</td>
									<td><input style="width:128px" name="customerBacksection" value="20000"/></td>
									<td style="width:30px">利润</td>
									<td><input style="width:128px" name="profit" value="4000"/></td>
								</tr>
								<tr>
									<td style="width:50px">联系人</td>
									<td><input style="width:128px" name="contact" value="中兴通"/></td>
									<td style="width:50px">优惠率</td>
									<td><input style="width:128px" name="discountRate" value="20%"/></td>
									<td style="width:60px">业务提成</td>
									<td><input style="width:128px" name="commissionBusiness" value="2100"/></td>
								</tr>
								<tr>
									<td style="width:60px">赠送项目</td>
									<td colspan="3"><input style="width:280px" name="giftItems" value="免费检测"/></td>
									<td style="width:90px">票券编号</td>
									<td><input style="width:128px" name="billsNumber" value="pq1651551"/></td>
								</tr>
								<tr>
									<td style="width:60px">赠送价值</td>
									<td><input style="width:128px" name="presentationValue" value="2145"/></td>
									<td style="width:60px">刷卡类型</td>
									<td><input style="width:128px" name="credirCardTypes" value="信用卡"/></td>
									<td style="width:60px">收款日期</td>
									<td><input style="width:128px" name="receiptDate" value="2012-12-01"/></td>
								</tr>
								<tr>
									<td style="width:60px">业务部门</td>
									<td><select style="width:128px" name="businessUnits" value="服务部"><option></option></select></td>
									<td style="width:50px">经办人</td>
									<td><select style="width:128px" name="manager" value="赵三"><option></option></select></td>
									<td style="width:60px">发票类型</td>
									<td><select style="width:128px" name="invoiceType" value="机打"><option></option></select></td>
								</tr>
								<tr>
									<td style="width:60px">接待人员</td>
									<td><select style="width:128px" name="receptor" value="赵六"><option></option></select></td>
									<td style="width:60px">保险分类</td>
									<td><select style="width:128px" name="insuranceGroup" value="意外包险"><option></option></select></td>
								</tr>
								<tr>
									<td style="width:60px">备注</td>
									<td colspan="6" rowspan="3"><textarea style="width:550px;height:100px;overflow:auto;resize:none" name="memo" value="这车将要报废"></textarea></td>
								</tr>
								<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
								<tr>
								<td align="center" rowspan="100" colspan="6"><a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0);" onclick="doAddSubmit($('#Car_policy_management_center_id'),'#Car_policy_management_form_add','carInsuranceManageAction_doAdd','carInsuranceManageAction_doFindAll')">保存</a> <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0);" onclick="doCancel()">取消</a></td>
								<td></td>
								</tr>
						</table>
				</form>
  </body>
</html>
