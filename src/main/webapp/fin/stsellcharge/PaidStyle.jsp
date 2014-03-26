<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/stsellcharge/PaidStyle.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">  
	<div region="center" style="background:#eee;"  border="false">
	   <form>
          <table>
           <tr>
             <td>付款日期:</td>
             <td><input id="sscd_paidDate" name="sscd_paidDate" style="width:140px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" /></td>
             <td>收款人:</td>
             <td><input id="sscd_paidPerson" name="sscd_paidPerson"  style="width:140px;background-color: #c0d8d8;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/StSellChargeAction_loadBasStuff.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
             <td>付款方式:</td>
             <td><input id="sscd_patment" name="sscd_patment" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/ReceivablesAction_loadPaidStyle.action',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/></td>
           </tr>
           <tr>
             <td>销售应收款编号:</td>
             <td><input id="sscd_chargeId" name="sscd_chargeId" readonly="readonly"  style="width:140px;background-color: #c0d8d8;"/>
                 <input type="hidden" name="sscd_PreclrAmount" id="sscd_PreclrAmount"/>
             
             </td>
             <td>付款金额:</td>
             <td><input id="sscd_PaidAmount" name="sscd_PaidAmount" style="width:140px;"/></td>
             <td>备注:</td>
             <td><input id="sscd_remark" name="sscd_remark"/></td>
           </tr>
           <tr>
             <td colspan="6" align="center">
	             <a class="easyui-linkbutton" onclick="st_sell_chargeitem_save();">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <a class="easyui-linkbutton" onclick="st_sell_chargeitem_cancelSave();">取消</a>
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             </td>
           </tr>
          </table>		               
	   </form>
	</div>
</div>