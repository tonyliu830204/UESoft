<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
     $(function(){
             $('#datagrid_instorehouse_infor_id').datagrid({
			 url:'${pageContext.request.contextPath}/supplierBillAction!getInterStore.action',
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'instorehouseId',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 rownumbers:true,
			 idField : 'instorehouseId',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[
			 {title : '入库单号',field : 'instorehouseId',sortable:true,width : 100,hidden:true}, 
			 {title : '入库编号',field : 'instorehouseCode',sortable:true,width : 100}, 
			 {title : '入库时间',field : 'instorehouseDate',sortable:true,width : 100}, 
			 {title : '数量',field : 'number',sortable:true,width : 100},
			 {title : '金额',field : 'tax',sortable:true,width : 100} 
			 ]],onDblClickRow:function(rowIndex, rowData){
	          	$('#form_supplier_bill_detail_id').form('load',rowData);
	          	$('#nowtax').val(rowData.tax);
	          	reLoadTreeGrid('datagrid_supplier_bill_detail_id', 'form_supplier_bill_detail_id', 'supplierBillAction!getSupplierBillDetailPrentInfor.action', false, true);
				dlog.dialog('close');
			   }
	    });
   })	
			  
//模糊查询 
function _queryInfo()
{
$('#datagrid_instorehouse_infor_id').datagrid('load',serializeObject($('#form_sell_choice_instore')));
    
} 	 
				  

function _clearForm()
{
  	$('#form_sell_choice_instore').form('clear');
	$('#datagrid_instorehouse_infor_id').datagrid('load',serializeObject($('#form_sell_choice_instore')));
  
}
</script>
               
<!--窗体 -->
<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:60px;" border="false">
   <form id="form_sell_choice_instore">
               入库日期:<input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instorehouseDate2\',{d:-1})}'})" name="instorehouseDate" id="instorehouseDate" style="width: 110px;"/> 至 
			<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instorehouseDate\',{d:1})}'})" name="instorehouseDate2" id="instorehouseDate2" style="width: 110px;"/>&nbsp;&nbsp;
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clearForm();" plain="true">清空</a>
	  	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="_queryInfo();" plain="true">查询</a>
	  
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_instorehouse_infor_id"></table> 
   </div>
</div>

