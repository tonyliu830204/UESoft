	$(function() {
		$('#reserveDete2').val(getSystemTime());
	 	getStartDate($('#reserveDete'));
			$('#carUpInfo').datagrid({
				url : 'carInfoAction!queryCarUpInfor.action',
				pagination : true,
				fit:true,
				fitColumns : true,
				sortOrder : 'desc',
			    sortName:'sellData',
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
				    {
					field : 'sellData',
					title : '销售日期',
					width : 110,
				    sortable : true
				}, {
					field : 'sellCode',
					title : '销售单号',
					width : 120,
					sortable : true,
				}, {
					field : 'retreat_date',
					title : '出库日期',
					width : 90,
					sortable : true
				}, {
					field : 'retreat_code',
					title : '出库单号',
					width : 100,
					sortable : true
				}, {
					field : 'carCode',
					title : '车辆编号',
					width : 110,
					sortable : true
				}, {
					field : 'carVinNumber',
					title : 'VIN号',
					width : 120,
					sortable : true
				}, {
					field : 'carBrand',
					title : '车品牌',
					width : 90,
					hidden:true,
					sortable : true
				},{
					field : 'carBrandName',
					title : '车品牌',
					width :75,
					sortable : true
				},{
					field : 'carModelId',
					title : '车型号',
					width : 130,
					hidden:true,
					sortable : true
				},{
					field : 'carModelName',
					title : '车型号',
					width :120,
					sortable : true
				 },{
					field : 'carColor',
					title : '车颜色',
					width : 130,
					hidden:true,
					sortable : true
				},{
					field : 'colorName',
					title : '车颜色',
					width :75,
					sortable : true
				 },{
					field : 'upData',
					title : '上报时间',
					width : 110,
					sortable : true
			   
			    },{
					field : 'upPerson',
					title : '上报人',
					width : 110,
					hidden:true,
					sortable : true
			   
			    },{
					field : 'upPersonName',
					title : '上报人',
					width : 110,
					sortable : true
			   
			    }, {
					field : 'customName',
					title : '客户姓名',
					width : 90,
					sortable : true
			   
			    }, {
					field : 'tel',
					title : '客户电话',
					width : 100,
					sortable : true,
				}, {
					field : 'supplierId',
					title : '供应商',
					width : 80,
					hidden:true,
					sortable : true
				}, {
					field : 'supplierName',
					title : '供应商',
					width : 120,
					sortable : true,
				}, {
					field : 'outId',
					title : '出库编号',
					width : 80,
					hidden:true,
					sortable : true
				} ]]
	});
	});
	
	
	//
function carUp(){
	var data = $('#carUpInfo').datagrid('getSelected');
	if(data!=null){
							if(data.upData!=null&&data.upData!=''){
								$.messager.alert('优亿软件提示', '该车辆已被上报！', 'warning', function() {});
								return ;
							}
							$.messager.confirm('优亿软件提示', '请确认是否执行此操作？', function(r){
						if (r){
							$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'carInfoAction!updateCarUpInfo.action',
								   data: data,
								 success: function(r){
									 if(r.success){
										 alertMsg('车辆上报成功！', 'info');
										
										 $('#carUpInfo').datagrid('load');
									 }else{
										 alertMsg(r);
									 }
								   },
								   error : function (r){
									   if(r.readyState == '0' && r.status == '0'){
										   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
									   }
								   }
								});
						}
							});

							
	}else{
			alertMsg('对不起，请先选中要上报的记录！', 'warning');
		 }
}
// 查询
var queryCarUp = function() {

	$('#carUpInfo').datagrid('unselectAll');
	$('#carUpInfo').datagrid('load',serializeObject($('#carInfoQueryForm')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));

}

// 清空
function clearSearch(){

	$('#carInfoQueryForm').form('clear');
	$('#carUpInfo').datagrid('unselectAll');
	$('#carUpInfo').datagrid('load', serializeObject($('#carInfoQueryForm')));
	addInitDate($('#reserveDete'),$('#reserveDete2'));



	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','${pageContext.request.contextPath }/carModelAction!findAllCarModel.action');
}

function _except(){
		var data =  $("#carUpInfo").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("carUpInfo",null,"carUpInfo_div","开始导出","导出配置",0,_callbackExcept);
		
	
	
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆上报管理"+getSystemTime());
}


function _config(){
	var data =  $("#carUpInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("carUpInfo",personnelSumTable,"carUpInfo_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}	  
