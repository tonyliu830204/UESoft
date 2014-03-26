<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 采购单管理-配件信息选择 -->
<script type="text/javascript">
//判断页面初始化加载是否完成
function   LoadOk(){
		if(document.readyState   =="complete")
			initFrame();
		else
			setTimeout("LoadOk()",1000);
}
setTimeout("LoadOk()",1000);
		
//判断页面初始化加载完成    执行
function initFrame(){
		var installid=$('#install_id').val();
		if(installid!=null&&installid!=''){ 
		      query();
		}
}
	
$(function (){
	//加装单信息
    $('#so_frtReceptionTable').datagrid({
   	     url:'${pageContext.request.contextPath}/carSellOutAction!getOutInstall.action',
   	     rownumbers : true,
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
	   	 idField : 'receptionId',
	   	 loadMsg : "数据加载中，请稍后……",
	   	 columns : [[ 
	   	  	{title : '加装单号',field : 'install_id',width : 1, hidden : true},
	   	    {title : '加装单号',field : 'install_code',width : 60}, 
   			{title : '车辆档案信息编号',field : 'xs_car_id',width : 1, hidden : true}, 
   			{title : '车辆档案信息编号',field : 'xs_car_code',width : 60}, 
   			{title : '车牌照',field : 'xs_car_licensePlate',width : 60}, 
   			{title : '车辆品牌',field : 'xs_car_brand',width : 1, hidden : true},
   			{title : '车辆品牌',field : 'xs_car_brandName',width : 60}, 
   			{title : '车辆外观色',field : 'xs_car_color',width : 1, hidden : true},
   			{title : '车辆外观色',field : 'xs_car_colorName',width : 60}, 
   			{title : '车辆内饰色',field : 'carInteriorColor',width : 1, hidden : true},
   			{title : '车辆内饰色',field : 'carInteriorColorName',width : 60}, 
   			{title : '车型号',field : 'xs_car_model_id',width : 1, hidden : true},
   			{title : '车型号',field : 'xs_car_modelName',width : 60},
   			{title : 'VIN号',field : 'xs_car_vin_number',width : 60},
   			{title : '发动机号',field : 'xs_car_motor_number',width : 60},
   			{title : 'OCN码',field : 'xs_car_ocn',width : 60},
   			{title : '企业id',field : 'enterpriseId',hidden : true}
   		 ]]
    });
 });
function query(){
		 $('#so_frtReceptionTable').datagrid('unselectAll');
		 $('#so_frtReceptionTable').datagrid('load', serializeObject($('#stout_selectplanpartsform')));
}

function _clear(){
    	$('#stout_selectplanpartsform').form('clear');
    	$('#so_frtReceptionTable').datagrid('load',serializeObject($('#stout_selectplanpartsform')));
}
</script>

<!-- 出库配件选择 --> 
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:60px;">
	    	<form id="stout_selectplanpartsform" method="post">
	    		<fieldset style="height:42px;">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>加装单号:</td>
						<td><input style="width:95px;" id="pinstall_code"  name="install_code"/></td>
						<td>车辆编号:</td>
						<td><input style="width:95px;" id="pxs_car_id" name="xs_car_id" /></td>
						<td>VIN号:</td>
						<td><input style="width:95px;" id="pxs_car_vin_number" name="xs_car_vin_number" /></td>
				    	<td>
				    	    <input type="hidden" id="pinstall_id" name="install_id">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
						</td>
						</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="overflow:hidden;background:#eee;" border="false">
	    <table id="so_frtReceptionTable"></table>
	</div>
</div>