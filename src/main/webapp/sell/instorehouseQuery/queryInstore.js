$(function (){

	$('#instoreEnd').val(getSystemTime());
	 getStartDate($('#instoreStart'));
	loadComboTree();
});

var loadComboTree = function(){
	
	$instoreTree=$('#instoreTree');
	$instoreTree.treegrid({
		url :'instorehouseAction!queryInstore.action',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		rownumbers:true,
		fit : true,
		fitColumns : true,
		animate:true,
		showFooter:true,
		idField : 'instorehouseCode',
		treeField : 'instorehouseCode',
		columns : [[
		            {
			field : 'instorehouseCode',
			title : '入库编号',
			width : 160,
			sortable:true
		},{
			field : 'number',
			title : '数量',
			sortable:true,
			width :70
		},  {
			field : 'instorehouseDate',
			title : '入库日期',
			sortable:true,
			width :100
		}, {
			field : 'supplier',
			title : '供应商',
			sortable:true,
			width :130
			
		},{
			field : 'receipt',
			title : '单据编号',
			sortable:true,
			width : 100
		},{
			field : 'carBrandName',
			title : '品牌',
			width : 85
			},{
			field : 'carModelName',
			title : '车辆型号',
			width : 100
		},{
			field : 'carVinNumber',
			title : 'VIN编码',
			width : 100
		},{
			field : 'carMotorNumber',
			title : '发动机',
			width : 100
		},{
			field : 'carOcn',
			title : 'OCN码',
			width : 90
		},{
			field : 'carColor',
			title : '车身颜色',
			width : 80,
			formatter : function (value,row,index){
			return row.colorName;
		}
		},{
			field : 'tax',
			title : '税额',
			sortable:true,
			width : 100
		},{
			field : 'sumTax',
			title : '含税额合计',
			sortable:true,
			width : 100
		},{
			field : 'notax',
			title : '未税额合计',
			sortable:true,
			width : 100
		},{
			field : 'vehicleCost',
			title : '标准销价',
			sortable:true,
			width : 100
		}]],rowStyler:function(row,rowIndex){
		if (row.carModelName=="汇总"){
			return 'background-color:#dce8db;';
		}
	 	},
		onBeforeExpand:function(rowData){
			//动态设置展开查询的url
	 	
			var url = 'instorehouseAction!getTreegridChild.action?instorehouseCode='+rowData.instorehouseCode+'&'+$('#carModelQueryForm').serialize();
			$('#instoreTree').treegrid("options").url = url;
			return true;
	    },onLoadSuccess:function(row, data){
	    	initTreeGridPager('instoreTree', 'instorehouseAction!queryInstore.action', true);

	    }
	});
}
//清空
function clearaa(){
	$('#carModelQueryForm').form('clear');
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	reLoadTreeGrid('instoreTree', 'carModelQueryForm', 'instorehouseAction!queryInstore.action',true, true);
	 addInitDate($('#instoreStart'),$('#instoreEnd'));
}
function queryHouse (){	

	reLoadTreeGrid('instoreTree', 'carModelQueryForm', 'instorehouseAction!queryInstore.action',false, true);
	 addInitDate($('#instoreStart'),$('#instoreEnd'));
}

function _except(){
	var data =  $("#instoreTree").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog("instoreTree",null,"instoreTree_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
exportEsuyUIExcelFile(parentId,fieldNames,"入库单查询"+getSystemTime());
}


function _config(){
var data =  $("#instoreTree").treegrid('getData');
 if(data.length==0){
	 alertMsg('数据列表为空，不能打印！', 'warning');
	 return ;
 }
	showEditDialog("instoreTree",null,"instoreTree_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}
