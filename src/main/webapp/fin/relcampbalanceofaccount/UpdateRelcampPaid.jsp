<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/relcampbalanceofaccount/UpdateRelcampPaid.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">  
	<div region="center" style="background:#eee;"  border="false">
	    <form id="u_RelcampBalanceOfAccountDetailForm">
          <table>
            <tr>
              <td>日期:</td> 
              <td><input id="u_accountDate" name="u_accountDate" style="width:130px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
              <td>单号:</td>
              <td><input id="u_accountIndex" name="u_accountIndex" readonly="readonly" style="background-color: #c0d8d8;" /></td>
              <td>供应商:</td>
              <td><input id="u_relcampId" name="u_relcampId" style="background-color: #c0d8d8;" class="easyui-combobox"
				data-options="
				editable : false,
				url : 'RelcampBalanceOfAccountAction!loadRelcamp.action',
				valueField:'id',  
				textField:'text' "/></td>
              <td>入退单号:</td>
              <td><input id="u_receiptId" name="u_receiptId" readonly="readonly" style="background-color: #c0d8d8;" /></td>
              <td></td>
            </tr>
            <tr>
              <td>经办人:</td>
              <td><input id="u_rboa_stfId" name="u_rboa_stfId" style="background-color: #c0d8d8; width:130px;" class="easyui-combobox"
					data-options="
					editable : false,
					url : 'RelcampBalanceOfAccountAction!loadManager.action',
					valueField:'id',  
					textField:'text' "/></td>
              <td>票据类型</td>
              <td><input id="u_accountReceipt" name="u_accountReceipt" style="background-color: #c0d8d8; width:110px;" class="easyui-combobox"
					data-options="
					editable : false,
					url : 'RelcampBalanceOfAccountAction!loadBillType.action',
					valueField:'id',  
					textField:'text' "/></td>
              <td>付款方式</td>
              <td><input id="u_operType" name="u_operType" style="background-color: #c0d8d8; width:110px;" class="easyui-combobox"
					data-options="
					editable : false,
					url : 'RelcampBalanceOfAccountAction!loadPaidStyle.action',
					valueField:'id',  
					textField:'text' "/></td>
               <td>数量:</td>
              <td><input id="u_rboa_totalNum" name="u_rboa_totalNum" /></td>
            </tr>
            <tr> 
              <td>金额:</td>
              <td><input id="u_rboa_totalAmount" name="u_rboa_totalAmount" style="width:130px;"/></td>
              <td>成本额:</td>
              <td><input id="u_rboa_taxCount" name="u_rboa_taxCount"/></td>
              <td>已付金额</td>
              <td><input id="u_paidAmount" name="u_paidAmount"/></td>
              <td>应付款:</td>
              <td><input id="u_rboa_recAmount" name="u_rboa_recAmount"/></td>
              <td></td>
            </tr>
            <tr>
               <td>本次付款</td>
               <td><input id="u_paidAmount" name="u_paidAmount"  style="width:130px;"/></td>
               <td>备注:</td>
               <td><input id="u_vendorRemark" name="u_vendorRemark"/></td>
             </tr>
             <tr>
	             <td colspan="9" align="center">
		             <a class="easyui-linkbutton" onclick="relcampbalanceofaccount_save();">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
		             <a class="easyui-linkbutton" onclick="relcampbalanceofaccount_cancelSave();">取消</a>
		                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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