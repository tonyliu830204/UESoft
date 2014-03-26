$(function() {
	//初试时间
 	$('#xs_Car_Sel_Data').val(getStartDate($('#xs_Car_Sel_Data')));
	$('#xs_Car_Sel_Data2').val(getSystemTime());

	$('#sellCarListTree').treegrid( {
		url : 'carSellManageAction!getFatherInfor.action',
		fit : true,
		fitColumns : true,
		showFooter : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
	    idField:'xs_Car_Model_Id',  
		treeField : 'xs_Car_Model',
		columns : [ [
		   {
			field : 'xs_Car_Model_Id',
			title : '车辆类型',
			width : 50,
			sortable : true,
			hidden:true
		}, {
			field : 'xs_Car_Model',
			title : '车辆类型',
			width :155,
			sortable : true
		}, {
			field : 'car_Vin_Number',
			title : 'VIN码',
			width : 125
		},  {
			field : 'carCode',
			title : '车辆编号',
			width :100
		},  {
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 70
		},  {
			field : 'xs_Car_Color_Name',
			title : '车颜色',
			width : 70
		},{
			field : 'xs_Car_Color',
			title : '车颜色',
			width : 70,
			hidden:true
		}, {
			field : 'sell_Code',
			title : '销售单号',
			width : 100
		},{
			field : 'retreat_date',
			title : '出库日期',
			width : 90
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 90
		},{
			field : 'stf_Name',
			title : '经办人',
			width :130,
			sortable : true
		}, {
			field : 'stf_Id',
			title : '经办人',
			width : 80,
			hidden:true,
			sortable : true
		}, {
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 95,
			sortable : true
		}, {
			
			field : 'numbers',
			title : '数量',
			width : 70,
			sortable : true
		}, {
			field : 'xs_Car_Sel_Transaction_Money',
			title : '销售价',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,index){
			return parseFloat(value);
		}
		}, {
			field : 'xs_Model_CostPrice',
			title : '成本价',
			width : 100,
			sortable : true,
			formatter : function(value,rowData,index){
			return parseFloat(value);
		}
		}, {
			field : 'invoice_date',
			title : '开票日期',
			width : 90,
			sortable : true
		}, {
			field : 'invoice_parce',
			title : '开票金额',
			width : 90,
			sortable : true
		}, {
			
			field : 'xs_Distributor_Id',
			title : '分销商',
			width : 150,
			hidden:true,
			sortable : true
		}, {
			field : 'xs_Distributor_Name',
			title : '分销商',
			width : 130,
			sortable : true
			
		}
		] ],rowStyler:function(row,rowIndex){
		if (row.xs_Car_Model=="汇总"){
			return 'background-color:#c0d8d8;';
		}
	 	},onBeforeExpand:function(rowData){
		//动态设置展开查询的url
		var url = 'carSellManageAction!getChildList.action?xs_Car_Model_Id=' +rowData.xs_Car_Model_Id+'&'+$('#carQueryForm').serialize();
		$('#sellCarListTree').treegrid("options").url = url;
		return true;
	},onLoadSuccess:function(row, data){
    	initTreeGridPager('sellCarListTree', 'carSellManageAction!getFatherInfor.action', true);
    }
	});
});
//查询
function queryCarReserve(){
//	var url="carSellManageAction!getFatherInfor.action";
//	url+=$('#carQueryForm').serialize();
//	$('#sellCarListTree').treegrid({
//		url:url
//	});
	reLoadTreeGrid('sellCarListTree', 'carQueryForm','carSellManageAction!getFatherInfor.action', false, true); 
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));

}

//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	reLoadTreeGrid('sellCarListTree', 'carQueryForm','carSellManageAction!getFatherInfor.action', true, true); 
	

	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
}

//导出
function _except(){
	showEditDialog("sellCarListTree",null,"allocateTree","开始导出","导出配置",0,_callbackExcept);
}
/**
* 导出excel回调函数
* 将筛选出的列导出到Excel文件
* 只支持Microsoft Office,不支持WPS
* @param parentId
* @param fieldNames  要导出的列字段
* @return
*/
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"销售单"+getSystemTime());
}



/**
* 打印字段设置
* 编辑、选择不同分组
* @return
*/
function _config(){
	showEditDialog("sellCarListTree",personnelSumTable,"allocateTree","开始打印","打印配置",2,_print);		
}
/**
* 打印字段设置回调函数
* 将选择的列打印
* @return
*/
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}