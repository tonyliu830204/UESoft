var times;
$(function() {
	
	$('#carInfo').datagrid( {
		url : 'carInfoAction!getPage.action',
		fit : true,
		pagination : true,
		rownumbers : true,
		sortOrder : 'desc',
		sortName : 'carId',
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
						title : '车辆编号',
						field : 'carId',
						sortable : true,
						hidden : true
					}, {
						title : '车辆编号',
						field : 'carCode',
						width : 150,
						sortable : true
					}, {
						title : '车辆品牌',
						field : 'carBrand',
						width : 100,
						formatter : function(value, row, index) {
							return row.carBrandName;
						},
						sortable : true
					}, {
						title : '内饰色',
						field : 'carInteriorColor',
						width : 100,
						formatter : function(value, row, index) {
							return row.interiorColorName;
						},
						sortable : true
					}, {
						title : '车辆型号',
						field : 'carModelId',
						width : 100,
						formatter : function(value, row, index) {
							return row.carModelName;
						},
						sortable : true
					}, {
						title : '选装件',
						field : 'carOptional',
						width : 100,
						sortable : true
					}, {
						title : '车牌照',
						field : 'carLicensePlate',
						width : 100,
						sortable : true
			
					}, {
						title : '厂牌名称',
						field : 'carLicenseName',
						width : 100,
						sortable : true
					}, {
						title : 'VIN编号',
						field : 'carVinNumber',
						width : 100,
						sortable : true
					}, {
						title : '发动机号',
						field : 'carMotorNumber',
						width : 100,
						sortable : true
					}, {
						title : 'OCN码',
						field : 'carOcn',
						width : 100,
						sortable : true
					},{
						title : '销售状态',
						field : 'carSellState',
						width : 100,
						formatter : function(value, row, index) {
							return row.sellStateName;
						},
						sortable : true
					},  {
						title : '生产日期',
						field : 'carMakeData',
						width : 100,
						sortable : true
					}, {
						title : '钥匙号',
						field : 'carUnlockingKeyNumber',
						width : 100,
						sortable : true
					}, {
						title : '商检单',
						field : 'carTradeCheckBill',
						width : 100,
						sortable : true
					}, {
						title : '产地',
						field : 'carProductionAddress',
						width : 100,
						sortable : true
					}, {
						title : '代交寄存车地址',
						field : 'carAddress',
						width : 100,
						sortable : true
					}, {
						title : '拷车日期',
						field : 'carCopyData',
						width : 100,
						sortable : true
					}, {
						title : '配车日期',
						field : 'carAssembleData',
						width : 100,
						sortable : true
					}, {
						title : '终检日期',
						field : 'carEndCheckData',
						width : 100,
						sortable : true
					}, {
						title : '首保日期',
						field : 'carFristInsuranceData',
						width : 100,
						sortable : true
					}, {
						title : '预计下线日期',
						field : 'carForecastData',
						width : 100,
						sortable : true
					}, {
						title : '车辆标准价格',
						field : 'carPrice',
						width : 100,
						sortable : true
					}, {
						title : '分销商',
						field : 'distributorId',
						width : 100,
						formatter : function(value, row, index) {
							return row.distributorName;
						},
						sortable : true
					}, {
						title : '规格',
						field : 'norms',
						width : 70,
						formatter : function(value, row, index) {
							return row.normsN;
						},
						sortable : true
					}, {
						title : '证明文件',
						field : 'proveFile',
						width : 100,
						sortable : true
					}, {
						title : '认证书',
						field : 'rzBook',
						width : 100,
						sortable : true
					}, {
						title : '工具包',
						field : 'toolCase',
						width : 70,
						formatter : function(value, row, index) {
							return row.toolCaseN;
						},
						sortable : true
					},{
						title : '脚垫',
						field : 'footd',
						width : 70,
						formatter : function(value, row, index) {
							return row.footdN;
						},
						sortable : true
						}
						,{
							title : '企业编号',
							field : 'enterpriseId',
							width : 100,
							hidden:true
					}] ],onDblClickRow : function(rowIndex, rowData) {
			var cId=rowData.carId;			
			loalDel(cId);
		}
	});
});



function loalDel(cId){
	
	var d = $('<div/>').dialog({
		modal:true,
		closable :false,
		title : '查看明细信息',
		width : 760,
		height :380,
		href : projectPath+'sell/carInfo/carInfoEdit.jsp',
		cache: false, 
		buttons : [{
			 text : '关闭',
			 iconCls : 'icon-undo',						 
			 handler : function (){
				 d.dialog('close');
			}
       }],
			onClose : function (){
	    	d.dialog('destroy');
	      },onLoad : function() {
	    	  $('#carArchives_add_cbrdId'). combobox({required:false});
	    	  $('#carArchives_add_ctypeId'). combobox({required:false});
	    	  $('#car_colorId'). combobox({required:false});
	    	  $('#car_interiorColor').combobox({required:false});
	    	 
	    	  	$("#carInfoEditForm input").prop("disabled", true);
	  			$("#carInfoEditForm select").prop("disabled", true);
	  			$("#carInfoEditForm textarea").prop("disabled",true);
	  			
	    	  
	    	 
	    	  $('#carVinNumber').validatebox({required:false});
	    	  $('#carMotorNumber').validatebox({required:false});
	    	  $('#carOcn').validatebox({required:false});
	    	  $('#carVinNumber').validatebox('validate');
	    	  $('#carMotorNumber').validatebox('validate');
	    	  $('#carOcn').validatebox('validate');

	    	
				var data = $('#carInfo')
				.datagrid('getSelected');
				$('#carInfoEditForm').form('load', data);
		
	  		
		
			}
	});
}

function addCarInfo() {
	var d = $('<div/>')
			.dialog(
					{
						modal : true,
						closable : true,
						title : '新增',
						width : 760,
						height : 380,
						href : projectPath+'sell/carInfo/carInfoEdit.jsp',
						buttons : [
								{
									iconCls : 'icon-save',
									text : '保存',
									handler : function() {
										if ($('#carInfoEditForm').form(
												'validate')) {
											$
													.ajax( {
														type : 'post',
														dataType : 'json',
														url : 'carInfoAction!saveCarInfo.action',
														data : $(
																'#carInfoEditForm')
																.serialize(),
														success : function(r) {
															if (r.success) {
																$('#carInfo')
																		.datagrid(
																				'reload');
																d
																		.dialog('close');
															} else {
																alertMsg(r);
															}
														},
														error : function(r) {
															if (r.readyState == '0'
																	&& r.status == '0') {
																alertMsg(
																		'对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！',
																		'warning');
															}
														}

													});
										}
									}
								}, {
									iconCls : 'icon-undo',
									text : '取消',
									handler : function() {
										d.dialog('close');
									}
								} ],
						onClose : function() {
							$(this).dialog('destroy');
						}
					});
}
// 删除
function removeCarInfo() {
	var data = $('#carInfo').datagrid('getSelected');
	var index=findSelectRowIndex('carInfo',data);
	if (data) {
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
			if (r) {
				$.ajax( {
					type : 'post',
					dataType : 'json',
					url : 'carInfoAction!deleteCarInfo.action',
					data : data,
					success : function(r) {
						if (r.success) {
							  $('#carInfo').datagrid('clearSelections');
							  $('#carInfo').datagrid('load');
							  setSelectRow('carInfo',index);
						} else {
							alertMsg(r);
						}
					},
					error : function(r) {
						if (r.readyState == '0' && r.status == '0') {
							alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！',
									'warning');
						}
					}
				});
			}
		});
	} else {
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
// 修改
var editCarInfo = function() {
	var data = $('#carInfo').datagrid('getSelected');
	if (data) {
		var d = $('<div/>')
				.dialog(
						{
							modal : true,
							closable : true,
							title : '修改',
							width : 760,
							height :380,
							href : projectPath+'sell/carInfo/carInfoEdit.jsp',
							buttons : [
									{
										iconCls : 'icon-save',
										text : '保存',
										handler : function() {
											if ($('#carInfoEditForm').form(
													'validate')) {
												$
														.ajax( {
															type : 'post',
															dataType : 'json',
															url : 'carInfoAction!updateCarInfo.action',
															data : $(
																	'#carInfoEditForm')
																	.serialize(),
															success : function(
																	r) {
																if (r.success) {
																	$(
																			'#carInfo')
																			.datagrid(
																					'reload');
																	d
																			.dialog('close');
																} else {
																	alertMsg(r);
																}
															},
															error : function(r) {
																if (r.readyState == '0'
																		&& r.status == '0') {
																	alertMsg(
																			'对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！',
																			'warning');
																}
															}
														});
											}
										}
									}, {
										iconCls : 'icon-undo',
										text : '取消',
										handler : function() {
											d.dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								var data = $('#carInfo')
										.datagrid('getSelected');
								$('#carInfoEditForm').form('load', data);
								var brandId=$('#carArchives_add_cbrdId').combo('getValue');
								var modelId=$('#carArchives_add_ctypeId').combo('getValue');
								if(brandId!=null && brandId!=""){
									$('#carArchives_add_ctypeId').combo('clear')
									var urlModel='carModelAction!findCarModelByBrand.action?cbrdId='+brandId;
									$('#carArchives_add_ctypeId').combobox('reload',urlModel );
									$('#carArchives_add_ctypeId').combo('setValue',modelId);
								}
								var vin=$("#carVinNumber").val();
								if(vin!=null && vin!=''){
									var len=vin.length;
									document.getElementById("vinNumberLength").innerHTML=len;
								}
								
							}
						});
	} else {
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
var queryCarInfo = function() {
	$('#carInfo').datagrid('unselectAll');
	$('#carInfo').datagrid('load', serializeObject($('#carInfoQueryForm')));
}
function clearSearchCondition() {
	$('#carInfoQueryForm').form('clear');
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'baseTagAction!getDataByChildDataKey.action',
	   data: 'pdataKey='+pkey+'&dataKey='+ckey,
	   success: function(r){
	   		$('#sellState').combobox('select',r);
	   		$('#carInfo').datagrid('load', serializeObject($('#carInfoQueryForm')));
	   },
	   error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   }
	});
}
//导出
function doexcept(dateGridId,parentId){
	var data =  $("#carInfo"
			).datagrid('getData'); 
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
	var data =  $("#carInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	}
	showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
}
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
/*//导入
function doinport(dateGridId,parentId){
	showEditDialog(dateGridId,null,parentId,"开始导入","导入配置",3,null);
}*/

