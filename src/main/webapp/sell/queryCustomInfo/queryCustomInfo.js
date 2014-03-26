$(function() {
	 addInitDate($('#xsCustomInputdata'),$('#xsCustomInputdata2'));
	$('#queryCustom').datagrid( {
		url : 'customInfoAction!queryCustom.action',
		fit : true,
		pagination : true,
		rownumbers : true,
		sortOrder : 'asc',
		sortName : '',
		singleSelect : true,
		sortable : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
						title : '编号',
						field : 'xsCustomCode',
						sortable : true,
						width : 100,
					}, {
						title : '客户名称',
						field : 'xsCustomName',
						width : 150,
						sortable : true
					}, {
						title : '地址',
						field : 'xsCustomAddress',
						width : 100,
						sortable : true
					}, {
						title : '固定电话',
						field : 'xsCustomPhone',
						width : 100,
						sortable : true
					}, {
						title : '手机',
						field : 'xsCustomTelephone',
						width : 100,
						sortable : true
					}, {
						title : '性别',
						field : 'xsCustomSex',
						width : 100,
						formatter : function(value, row, index) {
							return row.sexName;
						},
						sortable : true
					}, {
						title : '出生年月',
						field : 'xsCustomBirthday',
						width : 100,
						sortable : true
			
					}, {
						title : '收入情况',
						field : 'xsCustomIncome',
						formatter : function(value, row, index) {
							return row.incomeName;
						},
						width : 100,
						sortable : true
					}, {
						title : '从事职业',
						field : 'xsCustomOccupation',
						width : 100,
						formatter : function(value, row, index) {
							return row.occupationName;
						},
						sortable : true
					}, {
						title : '学历程度',
						field : 'xsCustomDiploma',
						width : 100,
						formatter : function(value, row, index) {
							return row.diplomaName;
						},
						sortable : true
					}, {
						title : '客户来源',
						field : 'xsCustomSource',
						width : 100,
						formatter : function(value, row, index) {
							return row.sourceName;
						},
						sortable : true
					}, {
						title : '建档日期',
						field : 'xsCustomInputdata',
						width : 100,
						sortable : true
					}, {
						title : '所在地区',
						field : 'xsCustomArea',
						formatter : function(value, row, index) {
							return row.areaName;
						},
						width : 100,
						sortable : true
					}, {
						title : '单位名称',
						field : 'xsCustomCompany',
						width : 100,
						sortable : true
					}, {
						title : '邮政编码',
						field : 'xsCustomZipcode',
						width : 100,
						sortable : true
					}, {
						title : '业务员',
						field : 'stfId',
						formatter : function(value, row, index) {
							return row.stfName;
						},
						width : 100,
						sortable : true
					}, {
						title : '证件号码',
						field : 'xsCustomCredentials',
						width : 100,
						sortable : true
					}, {
						title : '客户性质',
						field : 'xsCustomProperty',
						width : 100,
						formatter : function(value, row, index) {
							return row.propertyName;
						},
						sortable : true
					}, {
						title : '成交几率',
						field : 'xsCustomDeal',
						formatter : function(value, row, index) {
							return row.customDealName;
						},
						width : 100,
						sortable : true
					}, {
						title : '所从事行业',
						field : 'xsCustomTrade',
						formatter : function(value, row, index) {
							return row.tradeName;
						},
						hidden:true
					}, {
						title : '其他',
						field : 'xsCustomOther',				
						hidden:true
					}, {
						title : '联系人',
						field : 'xsContactsPerson',
						hidden:true
					}
					, {
						title : '车档案编号',
						field : 'xsCarId',
						//hidden:true
					}
				 ] ],onClickRow:function(rowIndex, rowData){
					$('#jBxx').form('clear')				   
				    $('#jBxx').form('load', rowData);
				},onDblClickRow:function(rowIndex, rowData){
					$('#jBxx').form('clear');
				    $('#jBxx').form('load', rowData);
				    var carId=rowData.xsCarId;
				    if(carId!=null && carId!=""){
				    	 $.messager.confirm('优亿软件提示', '是否要浏览客户对应的车档案资料？', function(r) {
						    	slo_d4 = $('<div/>');
						     	 slo_d4.dialog({
						   				title: '车辆档案',   
						   				width: 900,
						   			    height:530,
						   			    cache: false,
						   			    href: projectPath+'sell/carInfo/carInfo.jsp?carId='+carId,
						   			    modal: true,
						   			    buttons : [{
										 text : '关闭',
										 iconCls : 'icon-undo',						 
										 handler : function (){
						   			    	slo_d4.dialog('close');
										}
							       }],
						   				onClose : function (){
						   			    	$(this).dialog('destroy');
						   			    }
						   		});
						    	
						    });
				    }else{
				    	alert("该客户没有对应的车辆档案信息！");
				    }
				   
				}
	});
});
//查询
var queryCust = function() {
	$('#queryCustom').datagrid('unselectAll');
	$('#queryCustom').datagrid('load', serializeObjectByflag($('#queryFormCustom'),false));
	addInitDate($('#xsCustomInputdata'),$('#xsCustomInputdata2'));
}
function clearCust() {
	$('#queryFormCustom').form('clear');
	queryCust();
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
