$(function() {
	getStartDate($('#sms_date1'));
	$('#sms_date2').val(getSystemTime());
	
	$('#noteList').datagrid({
		url : 'sellSmsAction!getSmsInfo.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'sms_id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [
			{
				field : 'sms_id',
				title : '编号',
				width : 100
				
			},
			{
			field : 'custom_Id',
			title : '客户编号',
			width : 100,
			hidden : true
			
		},{
			field : 'carLicensePlate',
			title : '车牌照',
			width : 100
		
		}, {
			field : 'custom_Name',
			title : '联系人',
			width : 90
		
		}, {
			field : 'telephone',
			title : '发送号码',
			width : 100
			
		},{
			field : 'sms',
			title : '发送内容',
			width : 160
		
		},{
			field : 'receive_state',
			title : '成功',
			width : 80,
			formatter : function(value,rowData,rowIndex){
			if(value==0){
				return '成功';
			}else 
				return '<font color="red">'+'失败'+'</font>';
			
		}
			
		}, {
			field : 'sms_date',
			title : '发送时间',
			width : 100
		},
		{
			field : 'sft_id',
			title : '业务员',
			width : 100,
			hidden:true
		},
		{
			field : 'sft_name',
			title : '业务员',
			width : 100
		},
		]],
		onClickRow  : function(rowIndex, rowData){
		
		$('#sumqu').form('load', rowData);	
		
		}
	});

	
	
	
	
	
});


//查询
function queryReserve() {
	$('#noteList').datagrid('unselectAll');
	$('#noteList').datagrid('load',serializeObject($('#queryForm')));
	addInitDate($('#sms_date1'),$('#sms_date2'));
	
	
}
//清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	$('#noteList').datagrid('unselectAll');
	$('#noteList').datagrid('load', serializeObject($('#queryForm')));
	addInitDate($('#sms_date1'),$('#sms_date2'));

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
	var data =  $("#noteList").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("noteList",null,"acc","开始导出","导出配置",0,_callbackExcept);
		
	
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"短信发送查询"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	var data =  $("#noteList").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
	

		showEditDialog("noteList",personnelSumTable,"acc","开始打印","打印配置",2,_print);
		

		
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}