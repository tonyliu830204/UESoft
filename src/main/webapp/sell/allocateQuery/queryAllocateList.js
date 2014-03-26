$(function() {
	$('#instoreEnd').val(getSystemTime());
	 getStartDate($('#instoreStart'));
	
	 $('#Tree').treegrid( {
		url :'sellAllocatelAction!getFFatherList.action',
		fit : true,
		fitColumns : true,
		rownumbers:true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		showFooter:true,
		animate:true,
	    idField:'allocatecode',  
		treeField : 'allocatecode',
		columns : [ [
		             
		             {
			field : 'carColor',
			title : '车辆颜色id',
			width : 80,
			hidden:true,
			sortable : true
		} ,{
			field : 'allocateId',
			title : '编号',
			width : 50,
			sortable : true,
			hidden:true
		}, {
			field : 'allocatecode',
			title : '调拨单单号',
			width :155,
			sortable : true
		}, {
			field : 'allocateDate',
			title : '调拨日期',
			width : 70
		}, {
			field : 'xsDistributorName',
			title : '分销商',
			width :150,
			sortable : true
		}, {
			field : 'xsDistributorId',
			title : '分销商id',
			width : 80,
			hidden:true,
			sortable : true
		}, {
			field : 'num',
			title : '数量',
			width : 45,
			sortable : true
		}, {
			title : '车辆档案编号',
			field : 'carId',
			width : 100,
			hidden:true,
			sortable : true
		}, {
			field : 'carCode',
			title : '车辆编号',
			width : 130,
			sortable : true
		}, {
			field : 'carVinNumber',
			title : 'VIN编号',
			width : 140,
			sortable : true
		}, {
			field : 'carBrandName',
			title : '车辆品牌',
			width : 80,
			sortable : true
		}, {
			field : 'carBrand',
			title : '车辆品牌id',
			hidden:true,
			width : 90,
			sortable : true
		}, {
			title : '车辆类型',
			field : 'carModelName',
			width : 150,
			sortable : true
		}, {
			field : 'carModel',
			title : '车辆类型id',
			width : 60,
			hidden:true,
			sortable : true
			
		}, {
			field : 'carColorName',
			title : '车辆颜色',
			width : 70,
			sortable : true
		} ,{
			field : 'allocateTypeName',
			title : '调拨分类',
			width : 70,
			sortable : true
			
		}, {
			field : 'allocateType',
			title : '调拨分类id',
			width : 80,
			hidden:true
		}, {
			field : 'house',
			title : '仓库',
			width : 70,
			sortable : true
			
		}, {
			field : 'warehouse',
			title : '仓库',
			width : 80,
			hidden:true
		}, {
			field : 'paymentStateName',
			title : '是否付款',
			width : 70,
			sortable : true
			
		}, {
			field : 'paymentState',
			title : '付款',
			width : 80,
			hidden:true
		}, {
			field : 'modelCostPrice',
			title : '成本价',
			width : 80,
			sortable : true
		}, {
			field : 'allAmount',
			title : '调拨金额',
			width : 80,
			sortable : true
		}
	
		] ],rowStyler:function(row,rowIndex){
		if (row.allocatecode=="汇总"){
			return 'background-color:#dce8db;';
		}
	 	},onBeforeExpand:function(rowData){
		//动态设置展开查询的url
		var url = 'sellAllocatelAction!getAllocatel.action?allocateId=' + rowData.allocateId+"&"+$('#carQueryForm').serialize();
		$('#Tree').treegrid("options").url = url;
		return true;
	},onLoadSuccess:function(row, data){

    	
    	initTreeGridPager('Tree', 'sellAllocatelAction!getFFatherList.action', true);

    }
	});
});
//查询
function queryCarReserve (){

	reLoadTreeGrid('Tree', 'carQueryForm', 'sellAllocatelAction!getFFatherList.action',false,true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));


}	
//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	reLoadTreeGrid('Tree', 'carQueryForm', 'sellAllocatelAction!getFFatherList.action',true,true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');

}

//导出
function _except(){
	var data =  $("#Tree").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog("Tree",null,"allocateTree","开始导出","导出配置",0,_callbackExcept);
	



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
exportEsuyUIExcelFile(parentId,fieldNames,"调拨单汇总"+getSystemTime());
}



/**
* 打印字段设置
* 编辑、选择不同分组
* @return
*/
function _config(){
	var data =  $("#Tree").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
	showEditDialog("Tree",personnelSumTable,"allocateTree","开始打印","打印配置",2,_print);		


}
/**
* 打印字段设置回调函数
* 将选择的列打印
* @return
*/
function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}