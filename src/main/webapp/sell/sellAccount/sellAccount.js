$(function() {

	//初试时间
 	$('#accountDate1').val(getStartDate($('#accountDate1')));
	$('#accountDate2').val(getSystemTime());
	
	$('#account').datagrid( {
		url : 'sellAccountAction!getSellAccountList.action',
		pagination : true,
		fit:true,
		sortOrder:'asc',
	    sortName:'accountId',
		singleSelect : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			title : '结算单编号',
			field : 'accountId',
			width : 70,
			hidden:true,
			sortable : true
		}, {
			field : 'accountCode',
			title : '结算单号',
			width : 170,
			sortable : true

		},
		{
			field : 'xs_Car_Sel_Id',
			title : '车辆销售编号',
			width : 90,
			hidden:true,
			sortable : true

		}, 
		{
			field : 'accountTypeId',
			title : '结算类型单号',
			width : 160,
			sortable : true
			
		},
		{
			field : 'accountTypeName',
			title : '结算类型',
			width : 100,
			sortable : true
			

		},{
			field : 'accountType',
			title : '结算类型',
			width : 70,
			hidden:true,
			sortable : true

		}, {
			field : 'accountMoney',
			title : '结算金额',
			width : 100,
			sortable : true
		}, {
			field : 'accountDate',
			title : '结算日期',
			width : 150,
			sortable : true
			
		}, {
			field : 'accountPerson',
			title : '经办人',
			width : 90,
			hidden:true,
			sortable : true
		},  {
			field : 'person',
			title : '经办人',
			width : 130,
			sortable : true
		},{
			field : 'accountState',
			title : '是否转收银',
			width : 80,
			hidden:true
		},{
			field : 'state',
			title : '是否转收银',
			width : 80,
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : 180,
			sortable : true
		}
		] ],
		onDblClickRow : function(rowIndex, rowData) {
		var type=rowData.accountTypeName;
		if(type=='代办'){
				
				 var d = $('<div/>').dialog({							 
   					href : projectPath+'sell/sellDbProject/sellDbProject.jsp?accountTypeId='+rowData.accountTypeId,
   					modal:true,
   					closable : false,
   					title : '代办项目管理',
   					width : 750,
   					height :500,
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
				      }
				 });
					}
		if(type=='装潢'){
			 var d = $('<div/>').dialog({							 
					href : projectPath+'sell/sellDecorateProject/sellDecorateProject.jsp?accountTypeId='+rowData.accountTypeId,
					modal:true,
					closable : false,
					title : '装潢项目管理',
					width : 700,
					height :420,
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
				      }
				 });
				}
		if(type=='保险'){
			 var d = $('<div/>').dialog({							 
					href : projectPath+'sell/sellAccount/insuranceDetail.jsp?accountTypeId='+rowData.accountTypeId,
					modal:true,
					closable : false,
					title : '保险项目管理',
					width : 700,
					height :420,
					cache: false, 
					buttons : [{
						 text : '关闭',
						 iconCls : 'icon-undo',						 
						 handler : function (){
							 d.dialog('close');
						}
			       }],
			       onClose : function (){
			    	$(this).dialog('destroy');
			 }
				 });
			}
		if(type=='销售'){
			 var d = $('<div/>').dialog({							 
					href : projectPath+'sell/sellAccount/sellCarDetail.jsp?accountTypeId='+rowData.accountTypeId,
					modal:true,
					closable : false,
					title : '销售单管理',
					width : 700,
					height :420,
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
				      }
				 });
				}
		}
	});
});
//回转
function isSum(){
	var data = $('#account').datagrid('getSelected');
	 var index=findSelectRowIndex('account',data);
	if(data!=null){
		
		if(data.state=='是'){
			  $.messager.alert('优亿软件提示', '该结算单已经转收银,不可以删除！', 'warning', function() {});
			  return;
		}
	$.messager.confirm('优亿软件提示', '请确认是否删除结算单？', function(r){
		if (r){
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellAccountAction!modifyAccount.action',
				   data: data,
				 success: function(r){
					 if(r.success){
						 alertMsg('删除结算单成功！', 'info');
						   $('#account').datagrid('clearSelections');
						   $('#account').datagrid('load');
						   setSelectRow('account',index);	 
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
	}
		else{
			alertMsg('对不起，请先选中要删除的记录！', 'warning');
		 }
}
//转收银
function account(){
	var data = $('#account').datagrid('getSelected');
	if(data!=null){
		if(data.state=='是'){
			  $.messager.alert('优亿软件提示', '该结算单已经转收银，不允许重复操作！', 'warning', function() {});
			  return;
		}
	$.messager.confirm('优亿软件提示', '请确认是否转收银？', function(r){
		if (r){
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellAccountAction!saveAccount.action',
				   data: data,
				 success: function(r){
					 if(r.success){
						 alertMsg('转收银成功！', 'info');
						 $('#account').datagrid('load');
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
	}
		else{
			alertMsg('对不起，请先选中要转结算的记录！', 'warning');
		 }
}
//查询
function queryReserve() {

	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load',serializeObject($('#queryForm')));
	addInitDate($('#accountDate1'),$('#accountDate2'));
}
//清空
function clearSearchCondition(){
	$('#queryForm').find('input').val('');
	$('#queryForm').find('select').val('');
	$('#account').datagrid('unselectAll');
	$('#account').datagrid('load', serializeObject($('#queryForm')));
	addInitDate($('#accountDate1'),$('#accountDate2'));

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
	exportEsuyUIExcelFile(parentId,fieldNames,"结算单管理"+getSystemTime());
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