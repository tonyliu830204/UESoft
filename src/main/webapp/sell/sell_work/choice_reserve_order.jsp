<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
                      
     $(function(){
             $('#datagrid_sell_choice_reserve_order').datagrid({
			 url:'${pageContext.request.contextPath}/carSellManageAction!getInforById.action',
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'relcampId',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : false,
			 rownumbers:true,
			 idField : 'reserve_Id',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[ 
		 			{title : '编号',field : 'reserve_Id',hidden:true,width : 50}, 
		 			{title : '预定单编号',field : 'reserve_Code',sortable:true,width : 100}, 
		 			{title : '车辆编号',field : 'xs_Car_Id',hidden:true,width : 100}, 
		 			{title : 'VIN号',field : 'xs_Car_Vin_Number',sortable:true,width : 100},			 		 
		 			{title : '地盘号',field : 'xs_Car_Motor_Number',sortable:true,width : 100},			 	 
		 			{title : '车辆品牌',field : 'car_Brand_Id',sortable:true,width : 100},			 			 
		 			{title : '车辆型号',field : 'xs_Model_Name',sortable:true,width : 100},			 			 
		 			{title : '车辆型号编号',field : 'xs_Car_Model_Id',hidden:true,width : 100},
		 			{title : '发动机号',field : 'carMotor_Number',sortable:true,width : 100},		 			 
		 			{title : 'OCN码',field : 'xs_Car_Ocn',sortable:true,width : 100},			 			 
		 			{title : '车辆颜色',field : 'car_Color',sortable:true,width : 50},			 			 
		 			{title : '内饰色',field : 'xs_Car_InteriorColor',sortable:true,width : 50},			 			 
		 			{title : '厂牌名称',field : 'xs_Car_LicenseName',sortable:true,width : 50},			 			 
		 			{title : '车牌照',field : 'xs_Car_LicensePlate',sortable:true,width : 50},			 			 
		 			{title : '成本价格',field : 'xs_Model_CostPrice',sortable:true,width : 50},			 			 
		 			{title : '标准价格',field : 'xs_Model_SalesPrice',sortable:true,width : 50},			 			 
		 			{title : '销售限价',field : 'xs_Model_SalesLimitPrice',sortable:true,width : 50},
		 			{title : '销售价格',field : 'xs_Car_Sel_Transaction_Money',sortable:true,width : 50},
		 			{title : '客户名称',field : 'xs_Custom_Name',sortable:true,width : 50},			 			 
		 			{title : '联系电话',field : 'xs_Custom_Phone',sortable:true,width : 100}, 
		 			{title : '手机号码',field : 'xs_Custom_Telephone',sortable:true,width : 100}, 
		 			{title : '代码证',field : 'xs_Custom_Code_Card',sortable:true,width : 100}, 
		 			{title : '客户等级',field : 'xs_Custom_Hide_Level',sortable:true,width : 100}, 
		 			{title : '客户来源',field : 'xs_Custom_Source',sortable:true,width : 100}, 
		 			{title : '证件号码',field : 'xs_Custom_Credentials',sortable:true,width : 100}, 
		 			{title : '客户性质',field : 'xs_Custom_Property',sortable:true,width : 100}, 
		 			{title : '客户地址',field : 'xs_Custom_Address',sortable:true,width : 100}, 
		 			{title : '客户联系人',field : 'xs_Contacts_Person',sortable:true,width : 100}, 
		 			{title : '邮政编码',field : 'xs_Custom_Zipcode',sortable:true,width : 100}, 
		 			{title : '业务员',field : 'stf_Id',sortable:true,width : 100}, 
		 			{title : '预收金额',field : 'payment_Money',sortable:true,width : 100}
		 			]],onDblClickRow:function(rowIndex, rowData){
		 				$('#form_car_sell_manage_id').form('load',rowData);
		 				$('#choice2').linkbutton('disable');
		 				$('#choice3').linkbutton('disable');
						dlog.dialog('close');			
			   }
	    });
   })	
			  
//采购单汇总：供应商信息选择：模糊查询 
function _query()
{
    $.ajax({
		type : 'post',
		url : 'carSellManageAction!getInforById.action',
		data : $('#form_sell_choice_reserve_order').serialize(),
		dataType : 'json',
		success : function(r){
               $('#datagrid_sell_choice_reserve_order').datagrid('loadData',r);
        }
    });
} 	 
				  
//采购单汇总：供应商信息选择：清空查询条件
function _clear()
{
  $('#form_sell_choice_reserve_order').form('clear');
  $('#datagrid_sell_choice_reserve_order').datagrid('load');
}
</script>
               

<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
   <form id="form_sell_choice_reserve_order">
               预订单编号:<input id="" name="reserve_Code"  onkeyup="_query();"/>&nbsp;&nbsp;
	    客户名称:<input id="" name="xs_Custom_Name" onkeyup="_query();"/>
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_sell_choice_reserve_order"></table> 
   </div>
</div>

