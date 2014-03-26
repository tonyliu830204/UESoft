$(function(){
	
	//初试时间
	addInitDate($('#xsCarSelData'),$('#xsCarSelData2'));
	
	//页面初始化时，设置按钮的状态
	$('#tt').tabs({   
		onSelect:function(title){  
		
		 tbtitle = title;
			if(title =='销售单汇总'){
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					enableBtn();
				}
			}else if(title =='售后回访管理'){
				//disableBtn();		
			}
	    }   
	});

	
	
			$('#hh').datagrid({
				url : 'sellCoverAction!getSellList.action',
				pagination : true,
				fit:true,
				sortOrder:'asc',
			    sortName:'xsCarSelId',
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
			 {
							field : 'xsCarSelId',
							title : '销售编号',
							width : 65,
							sortable : true,
							hidden:true
						},
						{
							field : 'xsCarSelData',
							title : '销售日期',
							width : 120,
							sortable : true
						},{
							field : 'sell_code',
							title : '销售单号',
							width : 100,
							sortable : true
						},{
							field : 'carCode',
							title : '车辆编号',
							width : 120,
							sortable : true
						}, {
							field : 'vinCode',
							title : 'VIN号',
							width : 130,
							sortable : true
						},
						{
							field : 'carBrandName',
							title : '车辆品牌',
							width : 80,
							sortable : true
						},
						{
							field : 'carBrand',
							title : '车辆品牌id',
							hidden:true,
							width : 70,
							sortable : true
						},
						{
							field : 'carModelName',
							title : '车辆类型',
							width : 130,
							sortable : true
						},{
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 80,
							sortable : true
						},
						{
							field : 'carColorName',
							title : '颜色',
							width : 70,
							sortable : true
						},
						 {
							field : 'carColor',
							title : '颜色id',
							hidden:true,
							width : 70,
							sortable : true
						},						
						{	
							field : 'carMotorNumber',
							title : '发动机号',
							width : 90,
							sortable : true
						
						},{	
							field : 'carLicensePlate',
							title : '车牌照',
							width : 70,
							sortable : true
						
						},
						{
							field : 'xsCarSelRemark',
							title : '销售备注',
							width : 130,
							sortable : true
						 },	{
							field : 'consultActualDate',
							title : '最近回访',
							width : 130,
							sortable : true
								
						},
						{	
							field : 'consulTRateN',
							title : '回访进度',
							width : 70,
							sortable : true
							
					    },
						{	
							field : 'consulTRate',
							title : '回访进度id',
							width : 70,
							hidden:true,
							sortable : true
							
					    },{	
							field : 'consultDegreeType',
							title : '满意度类型',
							width : 70,
							hidden:true,
							sortable : true
							
					    },
					    {
							field : 'consultDegree',
							title : '满意度类型id',
							width : 80,
							hidden:true,
							sortable : true
						
						},{
							field : 'auditDate',
							title : '上报日期',
							width : 80,
							sortable : true
					
						},{
							field : 'xsCustomName',
							title : '客户名称',
							width : 80,
							sortable : true
						
						},{
							field : 'xsCustomPhone',
							title : '固定电话',
							width : 90,
							sortable : true
						},{
							field : 'xsCustomTelephone',
							title : '手机',
							width : 90,
							sortable : true
						
						},
						 {
							field : 'xsCustomApplicationN',
							title : '用途',
							width : 70,
							sortable : true
							
						},
						{
							field : 'xsCustomApplication',
							title : '用途',
							width : 60,
							hidden:true,
							sortable : true
							
						},
						{
							field : 'xsCustomAddress',
							title : '家庭住址',
							width : 150,
							sortable : true
						 },
						 {
						 	field : 'xsCustomCredentials',
							title : '身份证号',
							width : 130,
							sortable : true
					
						},{	
							field : 'xsCustomBirthday',
							title : '出生日期',
							width : 80,
							sortable : true
							
					     },
					     {
							field : 'sexName',
							title : '性别',
							width : 60,
							sortable : true
							
						},{
							field : 'xsCustomSex',
							title : '性别id',
							width : 70,
							hidden:true,
							sortable : true
						},{
							field : 'xsCustomOccupationN',
							title : '职业',
							width : 90,
							sortable : true
								
						 },{
							field : 'xsCustomOccupation',
							title : '职业id',
							width : 150,
							hidden:true,
							sortable : true
								
						 },{	
							field : 'xsCustomIncomeN',
							title : '月收入',
							width : 70,
							sortable : true
							
						 },{	
							field : 'xsCustomIncome',
							title : '月收入',
							width : 70,
							hidden:true,
							sortable : true
						
						 },{
							field : 'xsCustomDiplomaN',
							title : '学历',
							width : 70,
							sortable : true
						 },{
							field : 'xsCustomDiploma',
							title : '学历id',
							width : 70,
							hidden:true,
							sortable : true
						},{
							field : 'stfName',
							title : '业务员',
							width : 110,
							sortable : true
						},{
							field : 'stfId',
							title : '业务员',
							width : 80,
							hidden:true,
							sortable : true
						},{
							field : 'carSellStateN',
							title : '销售状态',
							width : 70,
							sortable : true
						},{
							field : 'carSellState',
							title : '销售状态id',
							width : 90,
							hidden:true,
							sortable : true
						},{
							field : 'pactCode',
							title : '合同号',
							width : 100,
							sortable : true
						
					},
					{
						field : 'consultCallStateN',
						title : '通话情况',
						width : 70,
						sortable : true
					},
					 {
						field : 'consultCallState',
						title : '通话情况',
						hidden:true,
						width : 70,
						sortable : true
					},
					 {
						field : 'customId',
						title : '客户id',
						hidden:true,
						width : 70,
						sortable : true
					}
					
		        ]],
		    	onDblClickRow : function(rowIndex, rowData) {
		    		$('#tt').tabs('select','售后回访管理');		    		
		    		/*	$('#StForm').form('load', rowData);// 将现在新选的行重新load在from表单中
*/		    		/*$('#ids1').val(rowData.xsCustomName);
		    		$('#ids11').val(rowData.customId);
		    		$('#ids2').val(rowData.xsCustomPhone);
		    		$('#ids3').val(rowData.xsCustomTelephone);
		    		$('#ids4').val(rowData.xsCustomAddress);
		    		$('#ids5').val(rowData.stfName);
		    		$('#ids6').val(rowData.carLicensePlate);
		    		$('#ids7').val(rowData.carModelName);
		    		$('#ids8').val(rowData.xsCarSelRemark);
		    		
		    		$('#ids9').val(rowData.pactCode);*/
		    		$('#StForm').form('clear');
		    			var  Id=rowData.xsCarSelId;
		    			loadInstoreDel(Id);
				
		}
	});
			
			
					// 售后回访管理
					$mingxiList = $('#mingxiList').datagrid({
						
						pagination : true,
						fit : true,
						singleSelect : true,
						pageSize : 10,
						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
						idField : 'consultId',
						loadMsg : "数据加载中，请稍后……",
						columns : [ [{
							field : 'consultId',
							title : '编号',
							width : 50,
							hidden:true,
							sortable : true,
						},{
							field : 'consultPlanDate',
							title : '计划日期',
							width : 130,
							sortable : true,
						},{
							field : 'consultActualDate',
							title : '实际回访',
							width : 130,
							sortable : true
						},{
							field : 'consultDegreeType',
							title : '满意度',
							width : 80,
							sortable : true
						},{
							field : 'consultDegree',
							title : '满意度Id',
							width : 130,
							hidden:true,
							sortable : true
						},{
							field : 'consulTRateN',
							title : '进度',
							width : 80,
							sortable : true
						},
						{
							field : 'consulTRate',
							title : '进度',
							width : 80,
							hidden:true,
							sortable : true
						},
						{
							field : 'travelCourse',
							title : '里程',
							width : 70,
							sortable : true
						},
						{
							field : 'consultSuggest',
							title : '意见',
							width : 140,
							sortable : true
						},
						{
							field : 'disposeDate',
							title : '处理时间',
							width : 130,
							sortable : true
						},
						{
							field : 'disposeResult',
							title : '处理结果',
							width : 125,
							sortable : true
						},
						{
							field : 'consultCallStateN',
							title : '通话情况',
							width : 70,
							sortable : true
						},
						 {
							field : 'consultCallState',
							title : '通话情况',
							hidden:true
						},
						{
							field : 'xsCustomName',
							title : '客户姓名',
							width : 80,
							sortable : true
						 },
						 {
							field : 'customId',
							title : '客户姓名',
							width : 80,
							hidden:true,
							sortable : true
						},{
							field : 'xsCustomTelephone',
							title : '客户电话',
							width : 90,
							sortable : true
						 },{
							field : 'remark',
							title : '备注',
							width : 120,
							sortable : true
						},{
							field : 'xsCustomBirthday',
							title : '出生日期',
							width : 100,
							hidden:true,
							sortable : true
							
						},{
							field : 'carModelName',
							title : '车辆类型',
							width : 130,
							sortable : true,
							hidden:true
						},{
							field : 'carModel',
							title : '车辆类型id',
							hidden:true,
							width : 80,
							sortable : true
						},{
							field : 'xsCarSelRemark',
							title : '销售备注',
							width : 130,
							hidden:true,
							sortable : true
						 },
						 {
							field : 'pactCode',
							title : '合同号',
							width : 100,
							hidden:true,
							sortable : true
							
						},{	
							field : 'carLicensePlate',
							title : '车牌照',
							width : 70,
							hidden:true,
							sortable : true
						
						},{
							field : 'xsCustomPhone',
							title : '固定电话',
							width : 70,
							hidden:true,
							sortable : true
						},{
							field : 'xsCustomAddress',
							title : '家庭住址',
							width : 150,
							hidden:true,
							sortable : true
						 },
						{
							field : 'stfId',
							title : '业务员',
							width : 80,
							hidden:true,
							sortable : true
						},{
							field : 'stfName',
							title : '业务员',
							width : 110,
							sortable : true
						}
				        ]],
				        onClickRow : function(rowIndex, rowData) {
						$('#StForm').form('clear');
						 
						$('#StForm').form('load', rowData);
						
					}
			   });

});

function  loadInstoreDel(parentId){
	$('#mingxiList').datagrid({
					url : 'sellCoverAction!getSellCover.action',
					queryParams: {xsCarSelId:parentId},
					pagination : true
				});
	
}

function update(){
	var data = $('#hh').datagrid('getSelected');
	if(data){
		$('#tt').tabs('select','售后回访管理');
		nuDisAbledControl();
		disableBtn();	
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updates();">保存</a>';
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		/*$('#ids1').val(data.xsCustomName);
		$('#ids2').val(data.xsCustomPhone);
		$('#ids3').val(data.xsCustomTelephone);
		$('#ids4').val(data.xsCustomAddress);
		$('#ids5').val(data.stfName);
		$('#ids6').val(data.carLicensePlate);
		$('#ids7').val(data.carModelName);
		$('#ids8').val(data.xsCarSelRemark);
		$('#ids9').val(data.pactCode);
		$('#ids11').val(data.customId);*/
		loadInstoreDel(data.xsCarSelId);
		$('#consultActualDate').val(getSystemTime2());
		//$('#StForm').form('load', data);
	}else{
		   $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning');
 
}
$('#ids1').select();
}


//启用控件
function nuDisAbledControl(){
	$("#StForm input.easyui-combobox").combobox('enable');
	$("#StForm input").prop("disabled", false);
	$("#StForm select").prop("disabled", false);
	$("#StForm textarea").prop("disabled",false);
}
//保存修改
function updates(){
	var data = $('#mingxiList').datagrid('getSelected');
	if(data){
		$.ajax({
		url:'${pageContext.request.contextPath}/sellCoverAction!modifySellCover.action',
		type: 'post',
		dataType: 'json',
		data:serializeObject($('#StForm')),
		success: function(r){
		if(r.success){		
			cancel();
			  $('#tt').tabs('select','销售单汇总');
			 $('#hh').datagrid('load');
			 $('#mingxiList').datagrid('load');
			$('#button').empty();
			
		}else{
			$.messager.alert('提示','数据提交失败!','warning');
		}
	 }
	});
}else{
	   $.messager.alert('优亿软件提示','对不起，请先选中要修改的明细记录！','warning');

}
}

//点击取消按钮执行的操作
function cancel() {
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	$('#hh').datagrid('unselectAll');
	$('#StForm').form('clear');  
}
//查询
function queryReserve() {

	$('#hh').datagrid('unselectAll');
	$('#hh').datagrid('load',serializeObjectByflag($('#queryaa'),false));
	//初试时间
	addInitDate($('#xsCarSelData'),$('#xsCarSelData2'));
}
//取消
function cancel() {
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	enableBtn();
	$('#hh').datagrid('unselectAll');
	$('#StForm').form('clear');  
}
//清空
function clearSearchCondition(){
	$('#queryaa').find('input').val('');
	$('#queryaa').find('select').val('');
	queryReserve();
	
}
//设置按钮的状态为可用
function enableBtn(){
	
	$('#_update').linkbutton('enable');
	$('#_search').linkbutton('enable');
	$('#_clear').linkbutton('enable');
	$('#_examine').linkbutton('enable');
	$('#_print').linkbutton('enable');
	$('#_export').linkbutton('enable');
	$('#_import').linkbutton('enable');
	
}
//禁用按钮
function disableBtn(){
	
	$('#_update').linkbutton('disable');
	$('#_search').linkbutton('disable');
	$('#_clear').linkbutton('disable');
	$('#_examine').linkbutton('disable');
	$('#addForeordain').linkbutton('disable');
	$('#deleteForeordain').linkbutton('disable');	
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
		var data =  $('#hh').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("hh",null,"qingdan","开始导出","导出配置",0,_callbackExcept);
		
	}else{
		var data =  $('#mingxiList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
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
		var data =  $('#hh').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog("hh",personnelSumTable,"qingdan","开始打印","打印配置",2,_print);
		
	}else{
		var data =  $('#mingxiList').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
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
