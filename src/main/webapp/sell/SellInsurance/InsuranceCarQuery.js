$(function() {
	$('#insurDate2').val(getSystemTime());
	 getStartDate($('#insurDate1'));
/*		

		$('#carBrand').treegrid( {
			url : 'sellInsuranceAction!getCarBrand.action',
			fit : true,
		    fitColumns : false,
		    idField:'dataValue', 
			treeField : 'dataValue',
			columns : [ [{
				field : 'childId',
				title : '编号',
				width : 70,
				hidden:true,
				sortable : true
			}, {
				field : 'dataValue',
				title : '车辆品牌',
				width :100,
				sortable : true
			},
			{
				field : 'carmodelN',
				title : '车辆型号',
				width :150,
				sortable : true
			}
			

			] ],onBeforeExpand:function(rowData){
			//动态设置展开查询的url
			var url = 'sellInsuranceAction!getCarModel.action?childId=' + rowData.childId;
			$('#carBrand').treegrid("options").url = url;
			return true;
		 },onClickRow : function(row){
	    	$('#account').datagrid({url : 'sellInsuranceAction!getCarInsurance.action?carModel='+row.dataValue});
	    	$('#sumqu').form('clear');
	    	$('#qt').form('clear');
	    }
		});*/
	
//保单汇总
$('#account').datagrid({
	url:'${pageContext.request.contextPath}/sellInsuranceAction!getCarInsurance.action',
	pagination : true,
	fit:true,
	sortOrder:'asc',
    sortName:'carCode',
	singleSelect : true,
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	rownumbers : true,
	loadMsg : "数据加载中，请稍后……",
	frozenColumns:[[{
		field : 'carCode',
		title : '车辆编号',
		width : 120,
		sortable : true,
	},
	{
		field : 'vin',
		title : 'VIN号',
		width : 130,
		sortable : true
	},{
		field : 'carLicenseName',
		title : '厂牌名称',
		width : 150,
		sortable : true
	 }, {
		field : 'carMotorNumber',
		title : '发动机号',
		width : 120,			
		sortable : true
		
	}, {
		field : 'carBrand',
		title : '品牌',
		width : 90,
		sortable : true
	},
	 {
		field : 'carmodel',
		title : '型号',
		width : 130,
		hidden:true,
		sortable : true
	},
	 {
		field : 'carmodelN',
		title : '型号',
		width : 130,					
		sortable : true
	}    ]],
	columns : [ [ 
	
	 {
		field : 'carColorName',
		title : '车身颜色',
		width : 90,
		sortable : true
	},
	{
		field : 'carLicensePlate',
		title : '车牌照',
		width : 90,
		sortable : true
	},
	 {
		field : 'insurerStartDate',
		title : '投保日期',
		width : 90,
		sortable : true
	},{
		field : 'insurerEndDate',
		title : '投保到期日期',
		width : 90,
		sortable : true
	},
	{
		field : 'insurerName',
		title : '保险公司',
		width : 100,
		sortable : true
	},
	{
		field : 'insurancePolicy',
		title : '保险单号',
		width : 80,
		sortable : true
	},
	{
		field : 'mc',
		title : '代保',
		width : 80,
		sortable : true
	},
	
	
	 {
		field : 'customName',
		title : '客户姓名',
		width : 100,
		sortable : true
	},
	{
		field : 'customAddress',
		title : '客户地址',
		width : 100,
		sortable : true
	},
	{
		field : 'customPhone',
		title : '客户固定电话',
		width : 100,					
		sortable : true
	},
	{
		field : 'customTelephone',
		title : '客户手机',
		width : 100,					
		sortable : true
	},
	{
		field : 'stfName',
		title : '业务员',
		width : 120,
		sortable : true
	},
	{
		field : 'instorehouseDate',
		title : '入库日期',
		width : 110,
		
		sortable : true
	},
	{
		field : 'carSellData',
		title : '销售日期',
		width : 110,
		sortable : true
	},
	{
		field : 'sellMoney',
		title : '销售价格',
		width : 70,
		hidden:true,
		sortable : true
		
	},{
		field : 'fristInsuranceData',
		title : '首保日期',
		width : 80,
		sortable : true
			
	},
	{
		field : 'insuranceAgent',
		title : '保险员',
		width : 80,
		sortable : true
			
	},
	{
		field : 'remark',
		title : '备注',
		width : 80,
		sortable : true
			
	},{
		field : 'carMakeData',
		title : '生产日期',
		width : 80,
		sortable : true
			
	},{
		field : 'audit_date',
		title : '上报日期',
		width : 80,
		sortable : true
			
	}
	 
	 
    ]],onClickRow: function(rowIndex, rowData){
	
	$('#sumqu').form('load',rowData);
	$('#qt').form('load',rowData);
	}
	
});
});


//查询
function queryReserve() {

	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load',serializeObject($('#queryForm')));
	addInitDate($('#insurDate1'),$('#insurDate2'));

	
	
}
//清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load',serializeObject($('#queryForm')));
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
	addInitDate($('#insurDate1'),$('#insurDate2'));
}



/**
 * 
 * 导出excel
 * 选择要导出的列
 * 参数1   dateGrid控件id属性
 * 参数2   dateGrid控件对应数据库类型
 * 参数3   dateGrid控件上层控件id属性
 * 参数4   执行按钮value文本
 * 参数5   title文本
 * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
 * 参数7   回调函数
 * @return
 */
function _except(){
	var data =  $('#account').datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	
		showEditDialog("account",null,"acc","开始导出","导出配置",0,_callbackExcept);
		
	
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆保险（首保）查询"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	var data =  $('#account').datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }

		showEditDialog("account",personnelSumTable,"acc","开始打印","打印配置",2,_print);
		

		
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}