function  loadInstoreDel(parentId){
	$('#carBrand').datagrid({
					url : 'possibleCustomFollowAction!getTzCustomTrack.action',
					queryParams: {custom_Id:parentId},
					pagination : true
				});
	
}


$(function() {

$('#account').datagrid({
	url:'${pageContext.request.contextPath}/',
	pagination : true,
	fit:true,
	sortOrder:'asc',
    sortName:'custom_Id',
	singleSelect : true,
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	rownumbers : true,
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ 
	{
		field : 'custom_Id',
		title : '客户',
		width :90,
		hidden:true,
		sortable : true,
	},
	{
		field : 'xs_Custom_Name',
		title : '客户',
		width : 90,
		sortable : true,
	},
	{
		field : 'stf_Id_Person',
		title : '业务员',
		width : 130,
		sortable : true
	},
	{
		field : 'deal',
		title : '成交状态',
		width : 80,
		sortable : true
	},
	{
		field : 'xs_Custom_Deal',
		title : '成交状态',
		width : 130,
		hidden:true,
		sortable : true
	},
	{
		field : 'xs_Custom_Hide_Level_Id',
		title : '潜在等级',
		width : 120,
		hidden:true,
		sortable : true,
	},
	{
		field : 'xs_Custom_Hide_Level',
		title : '潜在等级',
		width : 80,
		sortable : true,
	},
	{
		field : 'xs_Custom_Telephone',
		title : '客户电话',
		width : 100,
		sortable : true,
	},
	{
		field : 'cai_Model_Need',
		title : '喜欢车型',
		width : 130,
		hidden:true,
		sortable : true
	},
	{
		field : 'carModelN',
		title : '喜欢车型',
		width : 130,
		sortable : true
	},
	{
		field : 'carColour',
		title : '颜色',
		width : 90,
		sortable : true
	},
	{
		field : 'obstacle',
		title : '成交障碍',
		width : 90,
		hidden:true,
		sortable : true
	},
	{
		field : 'obstacleN',
		title : '成交障碍',
		width : 90,
		sortable : true
	},
	{
		field : 'predict_Buy_Date',
		title : '预购日期',
		width : 90,
		sortable : true
	},
	{
		field : 'payment',
		title : '付款方式',
		width : 90,
		sortable : true
	},
	{
		field : 'buy_probability',
		title : '成交率',
		width : 70,
		hidden:true,
		sortable : true
	},
	{
		field : 'probability',
		title : '成交率',
		width : 60,
		sortable : true
	},
	{
		field : 'custom_application',
		title : '用途',
		width : 90,
		hidden:true,
		sortable : true
	},
	{
		field : 'application',
		title : '用途',
		width : 90,
		sortable : true
	},
	{
		field : 'capability_Need',
		title : '性能要求',
		width : 90,
		sortable : true
	},
	{
		field : 'car_Model',
		title : '试驾车型',
		width : 90,
		hidden:true,
		sortable : true
	},
	{
		field : 'carModelNa',
		title : '试驾车型',
		width : 130,
		sortable : true
	},
	{
		field : 'xs_Custom_Inputdata',
		title : '建档日期',
		width : 90,
		sortable : true
	},
	{
		field : 'customCode',
		title : '客户编号',
		width : 120,
		sortable : true,
	},
	{
		field : 'contactsPerson',
		title : '客户联系人',
		width : 90,
		sortable : true,
	},
	{
		field : 'custom_Property_Id',
		title : '客户性质',
		width : 90,
		hidden:true,
		sortable : true,
	},
	{
		field : 'custom_Property',
		title : '客户性质',
		width : 90,
		sortable : true,
	},
	{
		field : 'tracing_Date',
		title : '跟踪日期',
		width : 130,
		
		sortable : true
	},
	
	{
		field : 'talk_Title',
		title : '讨论主题',
		width : 110,
		sortable : true
	},	
	{
		field : 'tracing_Summary',
		title : '跟踪总结',
		width : 120,
		sortable : true
		
	},{
		field : 'next_Interview_Date',
		title : '下次预约',
		width : 130,
		sortable : true
			
	},
	{
		field : 'tracing',
		title : '跟踪方式',
		width : 80,
		sortable : true
			
	},
	{
		field : 'tracing_way',
		title : '跟踪方式',
		width : 80,
		sortable : true
			
	},{
		field : 'tracing_Address',
		title : '地点',
		width : 80,
		sortable : true
	}
	
    ]],onClickRow : function(rowIndex, rowData){
	var  Id=rowData.custom_Id;
	loadInstoreDel(Id);
}
	
});

$('#carBrand').datagrid({
	pagination : true,
	fit:true,
	sortOrder:'asc',
    sortName:'custom_Id',
	singleSelect : true,
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	rownumbers : true,
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ 
	
	{
		field : 'tracing_Date',
		title : '跟踪日期',
		width : 130,
		sortable : true,
	},
	
	{
		field : 'talk_Title',
		title : '讨论主题',
		width : 110,
		sortable : true
	},
	{
		field : 'hinder_Content',
		title : '障碍描述',
		width : 130,
		sortable : true
	},
	
	
	
	{
		field : 'tracing_Summary',
		title : '跟踪总结',
		width : 120,
		sortable : true
		
	},{
		field : 'next_Interview_Date',
		title : '下次预约',
		width : 130,
		sortable : true
			
	},
	{
		field : 'tracing',
		title : '跟踪方式',
		width : 80,
		sortable : true
			
	}
	 ,{
		field : 'tracing_Address',
		title : '地点',
		width : 120,
		sortable : true
			
	},
	{
		field : 'ambienceN',
		title : '气氛',
		width : 80,
		sortable : true
			
	},
	{
		field : 'carModelNa',
		title : '试驾车型',
		width : 130,
		sortable : true
			
	},
	{
		field : 'tracing_Code',
		title : '跟踪单号',
		width : 110,
		sortable : true
			
	},
	 
	 
	 
    ]]
});
});


//查询
function queryReserve() {
	
	/*var data = $('#account').datagrid('getSelected');
	var url='sellInsuranceAction!getCarInsurance.action?carModel='+data.carModel+'&';
	url+=$('#queryForm').serialize();
	$('#account').datagrid({
		url:url
	});*/
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load',serializeObject($('#queryForm')));
	
	
}
//清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load', serializeObject($('#queryForm')));
	$('#account').datagrid(
			{
				url : 'possibleCustomFollowAction!getTzCustom.action'
			})
			
	$('#car_Brand_id').combobox('reload');
	$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
}

function queryToday(){
	
	$.ajax( { 
		type : 'POST',
		url : 'possibleCustomFollowAction!getTzCustom.action?times=1',
		success : function(r) {		
			if (r) {
			$('#account')
					.datagrid({
								url : 'possibleCustomFollowAction!getTzCustom.action?times=1',
								pagination : true,
								});
			
			} else {
			$.messager.alert('提示',
					'数据提交失败!', 'warning');
		}
	}
	});
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
	exportEsuyUIExcelFile(parentId,fieldNames,"跟踪记录查询"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	

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