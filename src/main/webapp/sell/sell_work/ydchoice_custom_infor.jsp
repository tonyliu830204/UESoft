<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
     $(function(){
             $('#datagrid_sell_choice_custominfor').datagrid({
			 url:'${pageContext.request.contextPath}/carSellManageAction!getcustomInfor.action',
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'xs_Custom_Id',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : false,
			 rownumbers:true,
			 idField : 'xs_Custom_Id',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[ 
			 {title : '编号',field : 'xs_Custom_Id',sortable:true,width : 50}, 
			 {title : '客户名称',field : 'xs_Custom_Name',sortable:true,width : 100}, 
			 {title : '手机号码',field : 'xs_Custom_Telephone',sortable:true,width : 120}, 
			 {title : '固定电话',field : 'xs_Custom_Phone',sortable:true,width : 120},
			 {title : '联系地址',field : 'xs_Custom_Address',sortable:true,width : 100},
			 {title : '代码证',field : 'xs_Custom_Code_Card',sortable:true,width : 100},
			 {title : '业务员',field : 'stf_Id',sortable:true,width : 100,
				formatter:function(value,rowData,index){
				 		return rowData.stf_Name;
				 	}
			 },
			 {title : '联系人',field : 'xs_Contacts_Person',sortable:true,width : 100},
			 {title : '潜在等级',field : 'xs_Custom_Hide_Level',hidden:true,width : 100},
			 {title : '证件号码',field : 'xs_Custom_Credentials',sortable:true,width : 150},
			 {title : '邮政编码',field : 'Xs_Custom_Zipcode',sortable:true,width : 100}
			 ]],onDblClickRow:function(rowIndex, rowData){
	          	$('#form_car_book_detail_id').form('load',rowData);
				dg.dialog('close');
			   }
	    });
   })	
			  
//采购单汇总：供应商信息选择：模糊查询 
function _query()
{
    $.ajax({
		type : 'post',
		url : 'carSellManageAction!getcustomInfor.action',
		data : $('#form_sell_choice_carinfor').serialize(),
		dataType : 'json',
		success : function(r){
               $('#datagrid_sell_choice_custominfor').datagrid('loadData',r);
        }
    });
} 	 
				  
//采购单汇总：供应商信息选择：清空查询条件
function _clear()
{
  $('#form_sell_choice_custominfor').form('clear');
  $('#datagrid_sell_choice_custominfor').datagrid('load');
}
</script>
               

<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
   <form id="form_sell_choice_custominfor">
               编号:<input id="relcampId11" name="relcampId"  onkeyup="_query();"/>&nbsp;&nbsp;
	    供应商:<input id="relcampName11" name="relcampName" onkeyup="_query();"/>
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_sell_choice_custominfor"></table> 
   </div>
</div>

