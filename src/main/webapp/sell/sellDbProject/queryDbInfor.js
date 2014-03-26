$(function() {
	$('#xs_Car_Sel_Data2').val(getSystemTime());
	 getStartDate($('#xs_Car_Sel_Data'));
	$('#Tree').treegrid( {
		url : 'sellDbProjectAction!queryDbInfor.action',
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		showFooter:true,
	    idField:'dbPersonName',  
		treeField : 'dbPersonName',
		columns : [ [
		    {
			field : 'dbProjectPerson',
			title : '业务员',
			width : 80,
			hidden:true,
			sortable : true
		} ,{
			field : 'dbPersonName',
			title : '业务员',
			width : 120,
			sortable : true
			
		}, {
			field : 'xs_Custom_Name',
			title : '客户姓名',
			width :95,
			sortable : true
		}, {
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 70
		}, {
			field : 'projectName',
			title : '代办项目',
			width :120,
			sortable : true
		}, {
			field : 'db_project_cost',
			title : '代办金额',
			width : 90,
			sortable : true
		}, {
			field : 'cost_price',
			title : '代办成本',
			width : 90,
			sortable : true
		},  {
			field : 'chae',
			title : '差额',
			width : 90,
			sortable : true
		}
	
		] ],rowStyler:function(row,rowIndex){
		if (row.dbPersonName=="汇总"){
			return 'background-color:#dce8db;';
		}
	 	},onBeforeExpand:function(rowData){
	 		
		//动态设置展开查询的url
		var url = 'sellDbProjectAction!queryDbChildInfor.action?dbProjectPerson=' + rowData.dbProjectPerson+"&"+$('#carQueryForm').serialize();
		$('#Tree').treegrid("options").url = url;
		return true;
	},onLoadSuccess:function(row, data){
    	initTreeGridPager('Tree', 'sellDbProjectAction!queryDbInfor.action', true);
    }
	});
});
function queryCarReserve (){
	
	 reLoadTreeGrid('Tree', 'carQueryForm', 'sellDbProjectAction!queryDbInfor.action', false, true);
	    addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
	
}	
//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	 reLoadTreeGrid('Tree', 'carQueryForm', 'sellDbProjectAction!queryDbInfor.action', true, true);
	    addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));
	
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
exportEsuyUIExcelFile(parentId,fieldNames,"代办项目查询"+getSystemTime());
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