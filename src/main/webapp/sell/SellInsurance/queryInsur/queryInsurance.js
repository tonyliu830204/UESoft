$(function() {
	$('#carSellData2').val(getSystemTime());
	 getStartDate($('#carSellData1'));
	$('#insuranceTable').treegrid( {
		url : 'sellInsuranceAction!getInsuranceInfor.action',
		fit : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		fitColumns : true,
		showFooter:true,
	    idField:'stfName',  
		treeField : 'stfName',
		columns :[[
		                {
		        			field : 'person',
		        			title : '业务员',
		        			width : 80,
		        			hidden:true,
		        			sortable : true
		        		} ,{
		        			field : 'stfName',
		        			title : '业务员',
		        			width : 120,
		        			sortable : true
		        		},  {
		        			field : 'num',
		        			title : '数量',
		        			width :55,
		        			sortable : true
		        		},{
		        			field : 'customName',
		        			title : '客户姓名',
		        			width : 80,
		        			sortable : true
		        		}, {
		        			field : 'carSellData',
		        			title : '销售日期',
		        			width :120,
		        			sortable : true
		        		}, {
		        			field : 'insurerStartDate',
		        			title : '保险起始日期',
		        			width :120,
		        			sortable : true
		        		}, {
		        			field : 'insurerEndDate',
		        			title : '保险到期日期',
		        			width :120,
		        			sortable : true
		        		}, {
		        			field : 'insuranceName',
		        			title : '保险公司',
		        			width : 180,
		        			sortable : true
		        		}, {
		        			field : 'primeCost',
		        			title : '保险成本',
		        			width : 80,
		        			sortable : true
		        		}, {
		        			field : 'sum',
		        			title : '保险金额',
		        			width : 80,
		        			sortable : true
		        		}, {
		        			field : 'customCost',
		        			title : '客户付款',
		        			width : 80,
		        			sortable : true
		        		}, {
		        			field : 'profit',
		        			title : '优惠金额',
		        			width : 80,
		        			sortable : true
		        		}
		                
		                
		                ]],rowStyler:function(row,rowIndex){
					if (row.stfName=="汇总"){
						return 'background-color:#dce8db;';
					}
					},onBeforeExpand:function(rowData){
					//动态设置展开查询的url
					
					var url = 'sellInsuranceAction!getInsuranceDel.action?person=' + rowData.person+"&"+$('#carQueryForm').serialize();
					$('#insuranceTable').treegrid("options").url = url;
							return true;
						},onLoadSuccess:function(row, data){
					    	initTreeGridPager('insuranceTable', 'sellInsuranceAction!getInsuranceInfor.action', true);
					    }
						});
					});
function queryCarReserve (){
	
	reLoadTreeGrid('insuranceTable', 'carQueryForm', 'sellInsuranceAction!getInsuranceInfor.action', false, true);
    addInitDate($('#carSellData1'),$('#carSellData2'));
	
	
}	
//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	reLoadTreeGrid('insuranceTable', 'carQueryForm', 'sellInsuranceAction!getInsuranceInfor.action', true, true);
    addInitDate($('#carSellData1'),$('#carSellData2'));
	
}

//导出
function _except(){
	var data =  $("#insuranceTable").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog("insuranceTable",null,"insurance","开始导出","导出配置",0,_callbackExcept);
	



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
exportEsuyUIExcelFile(parentId,fieldNames,"车辆代保查询"+getSystemTime());
}



/**
* 打印字段设置
* 编辑、选择不同分组
* @return
*/
function _config(){
	var data =  $("#insuranceTable").treegrid('getData'); 
	 if(data.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
	showEditDialog("insuranceTable",personnelSumTable,"insurance","开始打印","打印配置",2,_print);		


}
/**
* 打印字段设置回调函数
* 将选择的列打印
* @return
*/
function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}