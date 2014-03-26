$(function (){
	$('#repyDateEnd').val(getSystemTime());
	 getStartDate($('#reDateStart'));

	loadComboTree();
});

var loadComboTree = function(){
	
	$instoreTree=$('#instoreTree');
	$instoreTree.treegrid({
		url :'retreatAction!getQueryBack.action?carState=carOut',
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		fit : true,
		rownumbers:true,
		fitColumns : true,
		animate:true,
		showFooter:true,
		idField : 'carModelId',
		treeField : 'carModelName',
		columns : [[{
			field : 'carModelId',
			title : '车辆型号',
			width : 100,
			hidden:true
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
			title : 'OCN 码',
			width : 100
		},{
			field : 'carColor',
			title : '车身颜色',
			width : 100,
			formatter : function (value,row,index){
			return row.colorName;
		}
		},{
			field : 'instorehouseCode',
			title : '车辆编号',
			sortable : true,
			width :100
		}, {
			field : 'instorehouseDate',
			title : '出库日期',
			sortable:true,
			width :100
		}, {
			field : 'supplierId',
			title : '供应商',
			sortable:true,
			width :100,
			formatter : function (value,row,index){
			return row.supplier;
		}
		},{
			field : 'receipt',
			title : '单据编号',
			sortable:true,
			width : 100
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
	 		
	 		
			var url = 'retreatAction!getBack.action?carState=carOut&carModelId='+rowData.carModelId+'&'+$('#carModelQueryForm').serialize();
			$('#instoreTree').treegrid("options").url = url;
			return true;
	    },onLoadSuccess:function(row, data){
	    	initTreeGridPager('instoreTree', 'retreatAction!getQueryBack.action?carState=carOut', true);
	    } 
	});
}
//清空
function clearaa(){
	$('#carModelQueryForm').form('clear');
	 reLoadTreeGrid('instoreTree', 'carModelQueryForm', 'retreatAction!getQueryBack.action?carState=carOut', true, true);

	 addInitDate($('#reDateStart'),$('#repyDateEnd'));
}
function queryHouse (){	


	reLoadTreeGrid('instoreTree', 'carModelQueryForm', 'retreatAction!getQueryBack.action?carState=carOut', false, true);
	addInitDate($('#reDateStart'),$('#repyDateEnd'));
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
exportEsuyUIExcelFile(parentId,fieldNames,"出库单查询"+getSystemTime());
}


function _config(){
var data =  $("#instoreTree").treegrid('getData');
 if(data.length==0){
	 alertMsg('数据列表为空，不能打印！', 'warning');
	 return ;
 }
	showEditDialog("instoreTree",personnelSumTable,"instoreTree_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}
