$(function() {
	$('#instoreEnd').val(getSystemTime());
	 getStartDate($('#instoreStart'));
	
	
	$('#Tree').treegrid( {
		url : 'sellBackAction!getSellBack.action',
		fit : true,
		fitColumns : true,
		rownumbers:true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		animate:true,
		showFooter:true,
	    idField:'backCode', 
		treeField : 'backCode',
		columns : [ [ 
		              {
			field : 'backId',
			title : '编号',
			width : 40,
			sortable : true,
			hidden:true
		}, {
			field : 'backCode',
			title : '调退单单号',
			width : 155,
			sortable : true
		}, {
			field : 'backDate',
			title : '调退日期',
			width : 80,
			sortable : true
		}, {
			field : 'xsDistributorName',
			title : '分销商',
			width : 180,
			sortable : true
		}, {
			field : 'xsDistributorId',
			title : '分销商id',
			width : 80,
			hidden : true,
			sortable : true
		},
		{
			field : 'num',
			title : '数量',
			width : 60,
			sortable : true
		},
		{
			field : 'carCode',
			title : '车辆编号',
			width : 130,
			sortable : true
		},{
			field : 'carVinNumber',
			title : 'VIN号',
			width : 135,
			sortable : true
		},{
			field : 'carBrandName',
			title : '车辆品牌',
			width : 65,
			
		},{
			field : 'carBrand',
			title : '车辆品牌id',
			width : 60,
			hidden : true,
			sortable : true

		},{ 
			title : '车辆类型',
			field : 'carModelName',
			width : 120,
			sortable : true
		},{
			field : 'carModel',
			title : '车辆类型id',
			width : 60,
			hidden : true,
			sortable : true

		},{
			field : 'carColorName',
			title : '车辆颜色',
			width : 80,
			sortable : true
		}, {
			field : 'carColor',
			title : '车辆颜色id',
			width : 80,
			hidden : true,
			sortable : true
		},  {
			field : 'backTypeName',
			title : '调退分类',
			width : 80,
			sortable : true

		}, {
			field : 'backTypeId',
			title : '调退分类id',
			width : 80,
			hidden : true,
			sortable : true
		}, {
			field : 'handbackAllocateAmount',
			title : '合计金额',
			width : 100,
			sortable : true
		}

		] ],rowStyler:function(row,rowIndex){
		if (row.backCode=="汇总"){
			return 'background-color:#dce8db;';
		}
	 	},onBeforeExpand:function(rowData){
		//动态设置展开查询的url
		var url = 'sellBackAction!getBackDetails.action?backId=' + rowData.backId+"&"+$('#carQueryForm').serialize();
		$('#Tree').treegrid("options").url = url;
		return true;
	},onLoadSuccess:function(row, data){
    	initTreeGridPager('Tree', 'sellBackAction!getSellBack.action', true);
    }
	});
});
//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	reLoadTreeGrid('Tree', 'carQueryForm', 'sellBackAction!getSellBack.action',true,true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));
	
	
}
function queryCarReserve (){

	reLoadTreeGrid('Tree', 'carQueryForm', 'sellBackAction!getSellBack.action', false, true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));



	
}	
function _except(){
	var data =  $("#Tree").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("Tree",null,"backTree","开始导出","导出配置",0,_callbackExcept);
		
	
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"调退单汇总"+getSystemTime());
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
		
		showEditDialog("Tree",personnelSumTable,"backTree","开始打印","打印配置",2,_print);		
	
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}