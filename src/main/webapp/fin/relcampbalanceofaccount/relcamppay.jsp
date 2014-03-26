<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 采购单管理-配件信息选择 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/relcampbalanceofaccount/relcamppay.js"></script>
 <!-- 付款界面 -->
<div id="cc" class="easyui-layout" fit="true" border="false">  
	<div region="center" style="background:#eee;"  border="false">
	    <form id="RelcampBalanceOfAccountDetailForm">
          <table>
            <tr>
              <td>日期:</td>
              <td><input id="accountDate" name="accountDate" readonly="readonly" style="width:130px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
              <td>供应商:</td>
              <td><input id="relcampName" name="relcampName" readonly="readonly" style="background-color: #c0d8d8;"/>
                  <input type="hidden" id="relcampId" name="relcampId"/>
              </td>
              <td>入退单号:</td>
              <td><input id="rboa_receiptId" name="receiptId" style="width:130px;"/></td>
              <td>经办人:</td>
              <td><input id="rboa_stfId" name="stfId" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;" /></td>
              <td></td>
            </tr>
            <tr>
              <td>票据类型</td>
              <td><input id="accountReceipt" name="accountReceipt" style="background-color: #c0d8d8; width:130px;" class="easyui-combobox"
					data-options="url : 'baseAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
				    editable : false,
					valueField:'id',
					required:true,
					panelHeight:130,
					missingMessage:'票据类型必填',
					invalidMessage : '请在下拉框中选择付款方式',
					textField:'text',
					mode:'remote'"/></td>
              <td>付款方式</td>
              <td><input id="operType" name="operType" style="background-color: #c0d8d8; width:110px;" class="easyui-combobox" 
		     data-options="url:'${pageContext.request.contextPath}/StSellPerchargeAction!loadPaidStyle.action',
				    editable : false,
				    required:true,
					missingMessage:'付款方式必填',
					valueField:'id',
					panelHeight:130,
					textField:'text'"/></td>
               <td>数量:</td>
              <td><input id="rboa_totalNum" name="totalNum" readonly="readonly" style="width:130px;background-color: #c0d8d8;"/></td>
              <td>金额:</td>
              <td><input id="rboa_totalAmount" name="totalAmount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
            </tr>
            <tr>
              <td>成本额:</td>
              <td><input id="rboa_taxCount" name="taxCount" readonly="readonly" style="background-color: #c0d8d8;width:130px;"/></td>
              <td>已付金额</td>
              <td><input id="rboa_paidAmount" name="paidAmount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
              <td>应付款:</td>
              <td><input id="rboa_recAmount" name="recAmount" readonly="readonly" style="background-color: #c0d8d8;width:130px;"/></td>
               <td>本次付款</td>
               <td><input id="nowPaidAmount" name="nowPaidAmount" class="easyui-validatebox"  required="true" missingMessage="本次付款为必填项" invalidMessage="请填写本次付款" /></td>
            </tr>
            <tr>
               <td>备注:</td>
               <td><input id="vendorRemark" name="vendorRemark" style="width:130px;"/></td>
            </tr>
          </table>
        </form>
	</div>
</div>