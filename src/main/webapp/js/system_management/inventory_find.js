$(function(){
	loadDataGrid();
});

//将form表单序列化为对象
function serializeObj(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
} 

function loadDataGrid(){
	//盘点单查询
	$('#pandian_find_id').treegrid({  
	    url:projectPath+'inventoryFindAction!getFatherInfor.action',  
	    fit : true,
	    fitColumns : true,
	    idField:'parts_Id',  
	    treeField:'parts_Id',  
	    columns:[[  
	              {field:'_parentId',title:'编号',width:80,hidden : true}, 
                  {field:'parts_Id',title:'配件编码',width:80}, 
                  {field:'parts_Name',title:'配件名称',width:80}, 
                  {field:'punit_Name',title:'单位',width:80},  
                  {field:'stinvm_Id',title:'盘点单号',width:100},  
	              {field:'stinvm_Time',title:'日期',width:180},
	              {field:'stinvd_Count',title:'数量',width:100},  
	              {field:'stinvd_Price',title:'单价',width:180},  
	              {field:'stinvd_Cost',title:'合计金额',width:180}  
	             ]],onBeforeExpand:function(rowData){
					var form =  $('#form_inventory_find_id').form();
					var formvalue = serializeObj(form);
					var url = 'inventoryFindAction!getChildInfor.action?parts_Id=' + rowData.parts_Id+'&stinvm_Id='+formvalue.stinvm_Id;
					$('#pandian_find_id').treegrid("options").url = url;
					return true;
			    },onLoadSuccess:function(row, data){
			    	initTreeGridPager('pandian_find_id', projectPath+'inventoryFindAction!getFatherInfor.action', false);
			    }
	});
}
//条件查询提交
function doConditionSubmit(){
	reLoadTreeGrid('pandian_find_id', 'form_inventory_find_id', projectPath+'inventoryFindAction!getFatherInfor.action',false, false);
}

function doClear(){
	$('#form_inventory_find_id').form('clear');
	reLoadTreeGrid('pandian_find_id', 'form_inventory_find_id', projectPath+'inventoryFindAction!getFatherInfor.action',true, false);
}

function _exception(){
	showEditDialog("pandian_find_id",null,"pandian_find_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"盘点单"+getSystemTime());
}