$(function() {
	$('#instoreEnd').val(getSystemTime());
	 getStartDate($('#instoreStart'));
	$('#insuranceTable').treegrid( {
		url : 'sellInsuranceAction!getSellInsuranceF.action',
		fit : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
	    idField:'insurerName',  
		treeField : 'insurerName',
		frozenColumns:[[
		                {
		        			field : 'insurer',
		        			title : '保险公司id',
		        			width : 80,
		        			hidden:true,
		        			sortable : true
		        		} ,{
		        			field : 'insurerName',
		        			title : '保险公司',
		        			width : 220,
		        			sortable : true
		        		}, {
		        			field : 'num',
		        			title : '数量',
		        			width :55,
		        			sortable : true
		        		}, {
		        			field : 'recordDate',
		        			title : '登记日期',
		        			width : 70
		        		}, {
		        			field : 'dafeDate',
		        			title : '代保日期',
		        			width : 70
		        		}, {
		        			field : 'insurancePolicy',
		        			title : '保险单号',
		        			width :150,
		        			sortable : true
		        		}, {
		        			field : 'customName',
		        			title : '客户姓名',
		        			width : 80,
		        			sortable : true
		        		}
		                
		                
		                ]],rowStyler:function(row,rowIndex){
					if (row.insurerName=="汇总"){
						return 'background-color:#dce8db;';
					}
					},
	
						columns : [ [
						             
							 {
							field : 'carLicensePlate',
							title : '车牌照',
							width : 45,
							sortable : true
						}, {
						
							field : 'insurerStartDate',
							title : '保险起始',
							width : 90,
							sortable : true
						}, {
							field : 'insurerEndDate',
							title : '保险到期日期',
							width : 90,
							sortable : true
						}, {
							field : 'safeTypeN',
							title : '保单分类',
							width :90,
							sortable : true
						},  {
							field : 'sum',
							title : '应付保费',
							width : 90,
							sortable : true
						}, {
							title : '客户付款',
							field : 'customCost',
							width : 90,
							sortable : true
						}, {
							field : 'safeAmount',
							title : '上交金额',
							width : 90,
							sortable : true
							
						}, {
							field : 'extract',
							title : '业务提成',
							width : 90,
							sortable : true
						} ,{
							field : 'profit',
							title : '保险利润',
							width : 90,
							sortable : true
							
						}, {
							field : 'person',
							title : '经办人',
							width : 80,
							hidden:true
						}, {
							field : 'stfName',
							title : '经办人',
							width : 120,
							sortable : true
							
						}, {
							field : 'carLicenseName',
							title : '厂牌',
							width : 80,
							sortable : true
						}, {
							field : 'engineNumber',
							title : '发动机号',
							width :90,
							sortable : true
							
						}, {
							field : 'vin',
							title : 'VIN码',
							width : 125,
							sortable : true
						}, {
							field : 'xsCustomApplicationN',
							title : '使用性质',
							width : 80,
							sortable : true
						}, {
							field : 'limitLoadNum',
							title : '座位/吨位',
							width : 80,
							sortable : true
						},{
							field : 'customAddress',
							title : '客户地址',
							width : 120,
							sortable : true
						},{
							field : 'customCredentials',
							title : '身份证号',
							width : 140,
							sortable : true
						},{
							field : 'customTelephone',
							title : '联系电话',
							width : 100,
							sortable : true
						},{
							field : 'customZipcode',
							title : '邮政编码',
							width : 80,
							sortable : true
						},{
							field : 'contactsPerson',
							title : '联系人',
							width : 80,
							sortable : true
						},{
							field : 'carColorName',
							title : '车辆颜色',
							width : 80,
							sortable : true
						},{
							field : 'outprint',
							title : '排量/功率',
							width : 80,
							sortable : true
						},{
							field : 'distance',
							title : '已行驶里程',
							width : 80,
							sortable : true
						},{
							field : 'remark',
							title : '备注',
							width : 80,
							sortable : true
						}
						
						
						] ],onBeforeExpand:function(rowData){
						//动态设置展开查询的url
						var url = 'sellInsuranceAction!getInsuranceDetails.action?insurer=' + rowData.insurer+'&'+$('#carQueryForm').serialize();
						$('#insuranceTable').treegrid("options").url = url;
								return true;
						},onLoadSuccess:function(row, data){
						   initTreeGridPager('insuranceTable', 'sellInsuranceAction!getSellInsuranceF.action', true);
						} 
						});
					});
function queryCarReserve (){
	reLoadTreeGrid('insuranceTable', 'carQueryForm', 'sellInsuranceAction!getSellInsuranceF.action', false, true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));
	
}	
//清空
function clearSearchCondition(){
	$('#carQueryForm').form('clear');
	reLoadTreeGrid('insuranceTable', 'carQueryForm', 'sellInsuranceAction!getSellInsuranceF.action', true, true);
	addInitDate($('#instoreStart'),$('#instoreEnd'));
	
}

//导出
function _except(){
	var data =  $('#insuranceTable').treegrid('getData'); 
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
exportEsuyUIExcelFile(parentId,fieldNames,"车辆保单"+getSystemTime());
}



/**
* 打印字段设置
* 编辑、选择不同分组
* @return
*/
function _config(){
	var data =  $('#Tree').treegrid('getData'); 
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