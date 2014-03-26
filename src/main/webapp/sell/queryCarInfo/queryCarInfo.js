$(function() {
	 //初始化日期
	 addInitDate($('#carSelData'),$('#carSelData2'));
	$('#queryCar').datagrid( {
		url : 'instorehouseAction!getQueryData.action',
		fit : true,
		pagination : true,
		rownumbers : true,
		sortOrder : 'asc',
		sortName : 'carCode',
		singleSelect : true,
		sortable : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
						title : '车辆编号',
						field : 'carCode',
						sortable : true,
						width : 100,
					}, {
						title : 'VIN编号',
						field : 'carVinNumber',
						width : 150,
						sortable : true
					}, {
						title : '厂牌名称',
						field : 'carLicenseName',
						width : 100,
						sortable : true
					}, {
						title : '发动机号',
						field : 'carMotorNumber',
						width : 100,
						sortable : true
					}, {
						title : '品牌',
						field : 'carBrand',
						width : 100,
						formatter : function(value, row, index) {
							return row.carBrandName;
						},
						sortable : true
					}, {
						title : '型号',
						field : 'carModelId',
						width : 100,
						formatter : function(value, row, index) {
							return row.carModelName;
						},
						sortable : true
					}, {
						title : '客户名称',
						field : 'customName',
						width : 100,
						sortable : true
			
					}, {
						title : '供应商',
						field : 'supplierId',
						width : 150,
						sortable : true,
						hidden:true
					}, {
						title : '供应商',
						field : 'supplier',
						width : 100,
						sortable : true
					},
					{
						title : '生产日期',
						field : 'carMakeData',
						width : 100,
						sortable : true
					}, {
						title : '入库日期',
						field : 'instorehouseDate',
						width : 100,
						sortable : true
					}, {
						title : '车身颜色',
						field : 'carColor',
						width : 100,
						formatter : function(value, row, index) {
							return row.colorName;
						},
						sortable : true
					}, {
						title : '销售状态',
						field : 'sellState',
						formatter : function(value, row, index) {
							return row.sellStateName;
						},
						width : 100,
						sortable : true
					}, {
						title : '销售日期',
						field : 'carSelData',
						width : 100,
						sortable : true
					}, {
						title : '销售价格',
						field : 'selTransactionMoney',
						width : 100,
						sortable : true
					}, {
						title : '保险到期日期',
						field : 'insurerEndDate',
						width : 100,
						sortable : true
					}, {
						title : '保险公司',
						field : 'insurerId',
						formatter : function(value, row, index) {
							return row.insurerName;
						},
						width : 100,
						sortable : true
					}, {
						title : '客户地址',
						field : 'customAddress',
						width : 100,
						sortable : true
					}, {
						title : '客户固定电话',
						field : 'customPhone',
						width : 100,
						sortable : true
					}, {
						title : '客户手机',
						field : 'customTelephone',
						width : 100,
						sortable : true
					}, {
						title : '内饰颜色',
						field : 'carInteriorColor',
						formatter : function(value, row, index) {
							return row.interiorName;
						},
						width : 100,
						sortable : true
					}, {
						title : '商检单',
						field : 'carTradeCheckBill',
						width : 100,
						sortable : true
					}
				 ] ],onClickRow:function(rowIndex, rowData){
					$('#carInfoQueryForm').form('clear');
				    var rows = $('#queryCar').datagrid('getSelections');
				    $('#jBxx').form('load', rows[0]);
				    $('#otherInfo').form('load', rows[0]);
     }
	});
});
//查询
var queryCar = function() {
	$('#queryCar').datagrid('unselectAll');
	$('#queryCar').datagrid('load', serializeObjectByflag($('#carInfoQueryForm'),false));
	 //初始化日期
	 addInitDate($('#carSelData'),$('#carSelData2'));
}
//清空
function clearCondition() {
	$('#carInfoQueryForm').form('clear');
	queryCar();
}
//导出
function doexcept(dateGridId,parentId){
	var data =  $('#'+dateGridId+'').datagrid('getData');  
	if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案"+getSystemTime());
}
//打印
function dopoint(dateGridId,parentId){
	alert("34234");
	var data =  $('#'+dateGridId+'').datagrid('getData');  
	if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
	showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
}
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}

