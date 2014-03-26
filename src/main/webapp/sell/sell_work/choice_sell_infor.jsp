<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
                      
     $(function(){
             $('#datagrid_sell_choice_sellinfor').datagrid({
			 url:'${pageContext.request.contextPath}/carSellManageAction!findSellInfor.action?flag=false&isCarOut='+<%=request.getParameter("isCarOut")%>,
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'xs_Car_Sel_Id',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : false,
			 rownumbers:true,
			 idField : 'xs_Car_Sel_Id',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[ 
				 {
					 field : 'xs_Car_Sel_Id',
					 title : '编号',
					 width : 100,
					 sortable : true,
					 hidden : true
			
				},{
					 field : 'sell_Code',
					 title : '销售单号',
					 width : 100,
					 sortable : true
			
				},{
					field : 'out_Id',
					title : '出库单号',
					width : 100,
					sortable : true,
					hidden : true
					
				},{
					field : 'reserve_Code',
					title : '预订单编号',
					width : 100,
					sortable : true,
					hidden : true
					
				},{
					field : 'xs_Car_Sel_Data',
					title : '销售日期',
					width : 100,
					sortable : true
					
				},{
					field : 'xs_Custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true
					
				},{
					field : 'stf_Name',
					title : '业务员',
					width : 100,
					sortable : true
					
				},{
					field : 'xs_Car_Vin_Number',
					title : 'VIN编号',
					width : 100,
					sortable : true
				}, {
					field : 'xs_Car_Ocn',
					title : 'OCN码',
					width : 100,
					sortable : true
					
				},{
					field : 'xs_Car_Id',
					title : '车辆编号',
					width : 100,
					sortable : true
					
				},  {
					field : 'xs_Car_Brand',
					title : '车品牌',
					width : 100,
					sortable : true
					
				}, {
					field : 'xs_Model_Name',
					title : '车类型',
					width : 100,
					sortable : true
					
				}, {
					field : 'xs_Car_Sel_Transaction_Money',
					title : '成交价格',
					width : 100,
					sortable : true
				}, {
					field : 'STF_ID_PERSON',
					title : '经办人',
					width : 100,
					sortable : true
				}, {
					field : 'xs_Distributor_Name',
					title : '分销商',
					width : 100,
					sortable : true
				}, {
					field : 'limit_load_num',
					title : '限乘人数',
					width : 100,
					sortable : true
				}, {
					field : 'mobilephone',
					title : '电话',
					width : 100,
					sortable : true
				},{
					field : 'xs_Car_Give_Up',
					title : '购车状态',
					width : 100,
					sortable : true,
					formatter : function(value,rowData,rowIndex){
					if(value=="0"){
						return '正常';
					}else{
						return '已取消购车';
					}
				}
				}
			 ]],
			 onDblClickRow:function(rowIndex, rowData){
	          	$('#form_quitcar_manage_id').form('load',rowData);
				dlog.dialog('close');
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
               $('#datagrid_sell_choice_sellinfor').datagrid('loadData',r);
        }
    });
} 	 
				  
//采购单汇总：供应商信息选择：清空查询条件
function _clear()
{
  $('#form_sell_choice_custominfor').form('clear');
  $('#datagrid_sell_choice_sellinfor').datagrid('load');
}
</script>
               

<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
   <form id="form_sell_choice_custominfor">
               销售单编号:<input id="" name=""  onkeyup="_query();"/>&nbsp;&nbsp;
	    VIN号:<input id="" name="" onkeyup="_query();"/>
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_sell_choice_sellinfor"></table> 
   </div>
</div>

