$(function(){
	$('#from_test_id2').hide();
	getStartDate($('#xsCustomBirthday'));
	$('#xsCustomBirthday2').val(getSystemTime());
	
			$('#note').datagrid({
				url : 'noteManageAction!getSellBackInfo.action',
				pagination : true,
				fit:true,
				sortOrder:'asc',
			    sortName:'customId',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
						{
							field : 'customId',
							title : '客户姓名',
							width : 65,
							hidden:true,
							sortable : true,
						},
				        {
							field : 'custom_Name',
							title : '客户姓名',
							width : 70,
							sortable : true,
						},
						{
							field : 'xsCustomPhone',
							title : '电话',
							width : 90,
							sortable : true
						},{
							field : 'telephone',
							title : '手机',
							width : 100,
							sortable : true
						}, {
							field : 'xsCustomBirthday',
							title : '生日',
							width : 85,
							sortable : true
						},
						{
							field : 'xsCustomAddress',
							title : '联系地址',
							width : 130,
							sortable : true
						},
						{
							field : 'carsellData',
							title : '销售日期',
							width : 80,
							sortable : true
						},
						{
							field : 'insurerEndDate',
							title : '保险到期日期',
							width : 80,
							sortable : true
						},
						{
							field : 'carBrand',
							title : '车辆品牌',
							width : 80,
							hidden:true,
							sortable : true
						},
						{
							field : 'carBrandN',
							title : '车辆品牌',
							width : 80,
							sortable : true
						},{
							field : 'carModelN',
							title : '车辆类型',
							width : 130,
							sortable : true
						},
						{
							field : 'carModelId',
							title : '车辆类型',
							hidden:true,
							width : 80,
							sortable : true
						},
						{
							field : 'carLicensePlate',
							title : '车牌照',
							width : 90,
							sortable : true
						},
						{
							field : 'carVinNumber',
							title : 'VIN码',
							width : 130,
							sortable : true
						},
						 {
							field : 'xsCustomArea',
							title : '客户区域',
							width : 70,
							hidden:true,
							sortable : true
						},
						 {
							field : 'customArea',
							title : '客户区域',
							width : 90,
							sortable : true
						},
						 {
							field : 'xsCustomHideLevel',
							title : '等级',
							width : 70,
							hidden:true,
							sortable : true
						},
						 {
							field : 'customLevel',
							title : '等级',
							width : 70,
							sortable : true
						},
						{
							field : 'xsCustomDeal',
							title : '成交状态',
							width : 70,
							hidden:true,
							sortable : true
						},
						{
							field : 'customDeal',
							title : '成交状态',
							width : 70,
							sortable : true
						},
						{	
							field : 'xsCustomCompany',
							title : '任职单位',
							width : 90,
							sortable : true
						
						},{	
							field : 'stfId',
							title : '业务员',
							width : 70,
							hidden:true,
							sortable : true
					
						},
						{	
							field : 'stfName',
							title : '业务员',
							width : 70,
							sortable : true
					
						},
						{
							field : 'stfPhone',
							title : '业务员电话',
							width : 100,
							sortable : true
						 }
					
		        ]]
		
	});
			
		
					
					
					//短信发送管理已选发送列表 datagrid_infomation_send_manag_dialg_id
					$('#datagrid_infomation_send_manag_dialg_id').datagrid({

						url : '',
						fit : true,
						fitColumns : true,
						rownumbers : true,
						//pagination : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						idField : 'custom_Id',
						//sortName:'id',
						//sortOrder:'asc',
						loadMsg : "数据加载中，请稍后……",
						columns : [ [	{
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
							return '<font color="red">'+'成功'+'</font>';
						}else 
							return null;
						
					}
						
					}, {
						field : 'sms_date',
						title : '发送时间',
						width : 100
					}
						]],
						onDblClickRow : function(rowIndex, rowData){
						
							$(this).datagrid('deleteRow', rowIndex);
						
						}
					});		
					

});
function sendinformationOne(){
	//$('#swe').height(140);	
	//隐藏div
	$('#from_test_id2').hide();
	$('#from_test_id1').show();
	
	$('#note').datagrid({
		url : 'noteManageAction!getSellBackInfo.action',
		pagination : true,
		fit:true,
		sortOrder:'asc',
	    sortName:'customId',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ 
				{
					field : 'customId',
					title : '客户姓名',
					width : 65,
					hidden:true,
					sortable : true,
				},
		        {
					field : 'custom_Name',
					title : '客户姓名',
					width : 70,
					sortable : true,
				},
				{
					field : 'xsCustomPhone',
					title : '电话',
					width : 90,
					sortable : true
				},{
					field : 'telephone',
					title : '手机',
					width : 100,
					sortable : true
				}, {
					field : 'xsCustomBirthday',
					title : '生日',
					width : 85,
					sortable : true
				},
				{
					field : 'xsCustomAddress',
					title : '联系地址',
					width : 130,
					sortable : true
				},
				{
					field : 'carsellData',
					title : '销售日期',
					width : 80,
					sortable : true
				},
				{
					field : 'insurerEndDate',
					title : '保险到期日期',
					width : 80,
					sortable : true
				},
				{
					field : 'carBrand',
					title : '车辆品牌',
					width : 80,
					hidden:true,
					sortable : true
				},
				{
					field : 'carBrandN',
					title : '车辆品牌',
					width : 80,
					sortable : true
				},{
					field : 'carModelN',
					title : '车辆类型',
					width : 130,
					sortable : true
				},
				{
					field : 'carModelId',
					title : '车辆类型',
					hidden:true,
					width : 80,
					sortable : true
				},
				{
					field : 'carLicensePlate',
					title : '车牌照',
					width : 90,
					sortable : true
				},
				{
					field : 'carVinNumber',
					title : 'VIN码',
					width : 130,
					sortable : true
				},
				 {
					field : 'xsCustomArea',
					title : '客户区域',
					width : 70,
					hidden:true,
					sortable : true
				},
				 {
					field : 'customArea',
					title : '客户区域',
					width : 90,
					sortable : true
				},
				 {
					field : 'xsCustomHideLevel',
					title : '等级',
					width : 70,
					hidden:true,
					sortable : true
				},
				 {
					field : 'customLevel',
					title : '等级',
					width : 70,
					sortable : true
				},
				{
					field : 'xsCustomDeal',
					title : '成交状态',
					width : 70,
					hidden:true,
					sortable : true
				},
				{
					field : 'customDeal',
					title : '成交状态',
					width : 70,
					sortable : true
				},
				{	
					field : 'xsCustomCompany',
					title : '任职单位',
					width : 90,
					sortable : true
				
				},{	
					field : 'stfId',
					title : '业务员',
					width : 70,
					hidden:true,
					sortable : true
			
				},
				{	
					field : 'stfName',
					title : '业务员',
					width : 70,
					sortable : true
			
				},
				{
					field : 'stfPhone',
					title : '业务员电话',
					width : 100,
					sortable : true
				 }
			
        ]],
    	onDblClickRow : function(rowIndex, rowData) {
    		
		
}
});

}
function sendinformationTwo(){
	$('#from_test_id1').hide();
	$('#from_test_id2').show();
	getStartDate($('#register_Date'));
	$('#register_Date2').val(getSystemTime());
	$('#note').datagrid({
		url : 'backRegisterAction!findRecordLook.action',
		pagination : true,
		fit:true,
		sortOrder:'asc',
	    sortName:'xsCarSelId',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ 
				{
					field : 'register_Date',
					title : '登记日期',
					width : 100,
					sortable : true
					
				},
				{
					field : 'inData',
					title : '来电时间',
					width : 125,
					sortable : true
				},
				{
					field : 'custom_Name',
					title : '客户姓名',
					width : 65,
					sortable : true,
				},
				{
					field : 'telephone',
					title : '电话',
					width :100,
					sortable : true
				},{
					field : 'car_Model',
					title : '预购车型编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'car_Model_Name',
					title : '预购车型',
					width : 150,
					sortable : true
				},{
					field : 'stf_Id',
					title : '销售顾问编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'stf_Name',
					title : '销售顾问',
					width : 100,
					sortable : true
				},
				{
					field : 'talk_Content',
					title : '洽谈内容',
					width : 280,
					sortable : true
				},{
					field : 'talk_Way',
					title : '方式编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'talk_Way_Name',
					title : '方式',
					width : 100,
					sortable : true
				},{
					field : 'custom_Level',
					title : '级别编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'custom_Level_Name',
					title : '级别',
					width : 100,
					sortable : true
				},{
					field : 'message_Channel',
					title : '渠道编号',
					width : 100,
					sortable : true,
					hidden : true
				},{
					field : 'message_Channel_Name',
					title : '渠道',
					width : 100,
					sortable : true
				}
			
        ]],
    	onDblClickRow : function(rowIndex, rowData) {
    		
		
}
	});

	
}


	//条件查询提交
	function doConditionSubmit(){
	$('#note').datagrid('unselectAll');
	$('#note').datagrid('load',serializeObject($('#form_infomation_send_manage_id')));
	addInitDate($('#xsCustomBirthday'),$('#xsCustomBirthday2'));
	addInitDate($('#register_Date'),$('#register_Date2'));
	}

		//清空
		function doClear(){
		$('#form_infomation_send_manage_id').form('clear');
		$('#note').datagrid('unselectAll');
		$('#note').datagrid('load',serializeObject($('#form_infomation_send_manage_id')));
		$('#car_Brand_id').combobox('reload');
		$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		addInitDate($('#xsCustomBirthday'),$('#xsCustomBirthday2'));
		addInitDate($('#register_Date'),$('#register_Date2'));
}
		//点击短信发送时将已加载的数据 添加到已选择列表中
		function sendinformation(){
		//加载空的连接清除已选列表的数据
		
		$('#datagrid_infomation_send_manag_dialg_id').datagrid('loadData',{"rows":[],"total":0});
		
		$('#form_infomation_send_manag_dialg_id').form('clear');
		
		
		//获取已加载的数据
		
		var data = $('#note').datagrid('getSelections');
		if(data!=null&&data!=''){
				 $('#infomation_send_manag_dialg_id').dialog('open');	  
			}else{
				$.messager.alert('优亿软件提示', '对不起，请先选择要发送的客户信息！', 'warning', function() {});
				return;
			}
		
		for ( var i = 0; i < data.length; i++) {
			$('#datagrid_infomation_send_manag_dialg_id').datagrid('appendRow',data[i]);
		}
		}
		//当定时发送框被选中的时候触发此方法
		function selected(){
		$('#dingshifasong_id').removeAttr("disabled");
		}
		function selected2(){
		$('#dingshifasong_id').val('');
		$('#dingshifasong_id').attr("disabled","disabled");
		}
		
		
		function sendout(){
			
		//获取文本域短信内容
		var send_Content = $('#send_Content_Id').val();
		//获取定时发送时间
		var other_Send_Date = $('#dingshifasong_id').val();
		//获取测试号码
		var test_Number = $('#test_Number_Id').val();
		if(send_Content!=""){
			$.messager.confirm('优亿软件提示','请确定短信内容！',function(a){
			if(a){
				//获取进度条
				$.messager.progress({
					title : '短信发送',
					msg : '短信发送中，请稍后...',
					text : '',
					interval : 300
				});
				var url1 = 'sellSmsAction!smsSend.action?sms='+send_Content+'&d_date='+other_Send_Date+'&test_Number='+test_Number;
				var effectRow = getChange($('#note'));
				if(effectRow){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: url1,
					   data: effectRow,
					   success: function(r){
					   	if(r.success){
					   		$.messager.progress('close');
					   		$.messager.alert('优亿软件提示',r.msg,'info');
					   		 
					   		$('#datagrid_infomation_send_manag_dialg_id').datagrid({url : 'sellSmsAction!getSmsInfo.action'});
					   	}else{
					   		$.messager.progress('close'); 
					   		$.messager.alert('优亿软件提示',r.msg,'info');
					   	}
					   }
					});
					}
				}
			
		} ); 
		}else{
			$.messager.alert('优亿软件提示','发送内容不能为空！','info'); 
		}
		}
		//获取所有行数据
		function getChange(id){
			
				var inserted = id.datagrid('getData');
			
				var effectRow = new Object();
				if(inserted){
					effectRow['inserted'] = JSON.stringify(inserted);
				}
				return effectRow;
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
	if(tbtitle =='销售单汇总'){
		showEditDialog("hh",null,"qingdan","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		showEditDialog("mingxiList",null,"wowo","开始导出","导出配置",0,_callbackExcept2);	
	}
	
	
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
	exportEsuyUIExcelFile(parentId,fieldNames,"销售单汇总"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"售后回访管理"+getSystemTime());
}

/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	
	if(tbtitle =='销售单汇总'){
		showEditDialog("hh",personnelSumTable,"qingdan","开始打印","打印配置",2,_print);
		
	}else{
		showEditDialog("mingxiList",personnelSumTable,"wowo","开始打印","打印配置",2,_print);	
	}
	
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
