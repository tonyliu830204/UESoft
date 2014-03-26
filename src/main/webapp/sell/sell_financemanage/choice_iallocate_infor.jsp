<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String  payment=request.getParameter("payment");
	String  id=request.getParameter("id");
%>
<script type="text/javascript">
     $(function(){
             $('#datagrid_allocate_infor_id').datagrid({
			 url:'${pageContext.request.contextPath}/sellAllocatelAction!getSellAllocatelList.action?payment='+<%=payment%>+'&xsDistributorId='+<%=id%>,
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'allocateId',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 rownumbers:true,
			 idField : 'allocateId',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[
			 {title : '调拨单',field : 'allocateId',sortable:true,width : 100,hidden:true}, 
			 {title : '调拨单号',field : 'allocatecode',sortable:true,width : 100}, 
			 {title : '日期',field : 'allocateDate',sortable:true,width : 100}, 
			 {title : '数量',field : 'num',sortable:true,width : 70}, 
			 
			 {title : '金额',field : 'allocateAmount',sortable:true,width : 100}
			 ]],onDblClickRow:function(rowIndex, rowData){
	          	$('#form_supplier_bill_detail_id').form('load',rowData);
	          	$('#nowtax').val(rowData.allocateAmount);
	          	$('#tax').val(rowData.allocateAmount);
	          	$('#instorehouseId').val(rowData.allocateId);
	          	
	          	
				dlog.dialog('close');
			   }
	    });
   })	
			  
//模糊查询 
function _querys()
{
    $.ajax({
		type : 'post',
		url : 'sellAllocatelAction!getSellAllocatelList.action',
		data : $('#form_instorehouse_infor').serialize(),
		dataType : 'json',
		success : function(r){
               $('#datagrid_allocate_infor_id').datagrid('loadData',r);
        }
    });
} 	 
				  
//清空查询条件
function _clears()
{
  $('#form_instorehouse_infor').form('clear');
  $('#datagrid_allocate_infor_id').datagrid('unselectAll');
  $('#datagrid_allocate_infor_id').datagrid('load', serializeObject($('#form_instorehouse_infor')));
 
}
</script>
               
<!--窗体 -->
<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:60px;" border="false">
   <form id="form_sell_choice_carinfor">
               调拨日期:<input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instorehouseDate2\',{d:-1})}'})" name="allocateDate" id="instorehouseDate" style="width: 110px;"/> 至 
			<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instorehouseDate\',{d:1})}'})" name="allocateDate" id="instorehouseDate2" style="width: 110px;"/>&nbsp;&nbsp;
	  &nbsp;&nbsp;
	  <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true"  onclick="querys();">查询</a>
	  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clears();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_allocate_infor_id"></table> 
   </div>
</div>

