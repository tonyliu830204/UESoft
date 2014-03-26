<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
     $(function(){
             $('#datagrid_sell_choice_carinfor').datagrid({
			 url:'${pageContext.request.contextPath}/carSellManageAction!getCarInfor.action',
			 pagination:true,
		     fit:true,
		     singleSelect:true,
		     sortOrder:'asc',
		     sortName:'xs_Car_Id',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : false,
			 rownumbers:true,
			 idField : 'xs_Car_Id',
			 loadMsg : "数据加载中，请稍后……",
			 columns : [[
			 {title : '编号',field : 'xs_Car_Id',sortable:true,width : 50}, 
			 {title : '车辆品牌',field : 'xs_Car_Brand',sortable:true,width : 100,
			 formatter : function (value,row,index){
				return row.xs_Car_Brand_Name;
			}}, 
			 {title : '车辆颜色',field : 'xs_Car_Color',sortable:true,width : 100,
			 formatter : function (value,row,index){
				return row.xs_Car_Color_Name;
			}}, 
			 {title : '厂牌名称',field : 'carLicense_Name',sortable:true,width : 100},
			 {title : '车牌照',field : 'carLicense_Plate',sortable:true,width : 100},
			 {title : '车辆型号',field : 'xs_Car_Model_Id',sortable:true,width : 150,
			 formatter : function (value,row,index){
				return row.xs_Car_Model;
			}},
			 {title : '发动机号',field : 'carMotor_Number',sortable:true,width : 100},
			 {title : 'OCN号',field : 'xs_Car_Ocn',sortable:true,width : 150},
			 {title : 'VIN号',field : 'xs_Car_Vin_Number',sortable:true,width : 180},
			 {title : '限乘人数',field : 'xs_Car_RideLimit_Number',sortable:true,width : 100},
			 {title : '标准价格',field : 'xs_Car_Price',sortable:true,width : 100},
			 {title : '分销商',field : 'xs_Distributor_Id',sortable:true,width : 100,
			 formatter : function (value,row,index){
				return row.xs_Distributor_Name;
			}}
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
		url : 'carSellManageAction!getCarInfor.action',
		data : $('#form_sell_choice_carinfor').serialize(),
		dataType : 'json',
		success : function(r){
               $('#datagrid_sell_choice_carinfor').datagrid('loadData',r);
        }
    });
} 	 
				  
//采购单汇总：供应商信息选择：清空查询条件
function _clear()
{
  $('#form_sell_choice_carinfor').form('clear');
  $('#datagrid_sell_choice_carinfor').datagrid('load');
}
</script>
               
<!--采购单汇总   供应商窗体 -->
<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
   <form id="form_sell_choice_carinfor">
               编号:<input id="relcampId11" name="relcampId"  onkeyup="_query();"/>&nbsp;&nbsp;
	    供应商:<input id="relcampName11" name="relcampName" onkeyup="_query();"/>
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="datagrid_sell_choice_carinfor"></table> 
   </div>
</div>

