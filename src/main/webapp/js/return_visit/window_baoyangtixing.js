var editRow=undefined;
$(function(){
	$('#table_bytx_lishihuifangjilu_id').datagrid({
		pagination : true,
		fit : true,
		singleSelect : true,
		newrap : false,
		striped : true,
		rownumbers : true,
		fitColumns : false, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'g_Id',
			title : '提醒id',
			sortable : true,
			hidden:true
		} ,{
			field : 'return_Visit_Date',
			title : '回访日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'text'
		  }
		} ,{
			field : 'tx_Return_Visit_Date',
			title : '提醒回访日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'text'
		  }
		} ,{
			field : 'group_Name',
			title : '回访类型',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
				return row.group_Name_value;
			}
		},{
			field : 'visit_Content',
			title : '回访内容',
			width : 300,
			sortable : true,
			editor : {
				type : 'text'
		  }
		},{
			field : 'tx_Resault',
			title : '提醒结果',
			width : 100,
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
			       url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=txResault',
					required : true,
					mode : 'remote',
					valueField:'id',  
				    textField:'text',  
				}
		  },
		  formatter: function(value,row,index){
				return row.tx_Resault_value;
			}
		},{
			field : 'car_lost',
			title : '流失去向',
			width : 100,
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
					url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=carLost',
					required : true,
					mode : 'remote',
					valueField:'id',  
				    textField:'text',  
					missingMessage : "车辆品牌为必选项"
				}
		  },
			formatter: function(value,row,index){
				return row.car_lost_value;
			}
		}
		]],
		onClickRow:function(rowIndex, rowData){ 
			$('#form_baoyangtixing_id').form('load',rowData);
	   },onAfterEdit:function(rowIndex, rowData, changes)
	      {
		    if(rowData.g_Id!=null){
		    	$.ajax({
		             type:"POST",
		             url:"customerCareAction!updateReminder.action",
		             data:"return_Visit_Date="+rowData.return_Visit_Date+"&tx_Return_Visit_Date="+rowData.tx_Return_Visit_Date+"&group_Name="+rowData.group_Name
		             +"&visit_Content="+rowData.visit_Content+"&tx_Resault="+rowData.tx_Resault+"&car_lost="+rowData.car_lost+"&g_Id="+rowData.g_Id,
		             dataType:"json",
		             success:function callback(r){
			        	 if(r.msg=="success")
			        	 {
			        		  $('#table_bytx_lishihuifangjilu_id').datagrid('clearSelections');
			        		  $('#table_bytx_lishihuifangjilu_id').datagrid({
									url : 'customerCareAction!getHistoricalVisit.action',
									queryParams: {car_Id: data.car_Id,group_Name: maintenance}
								});
			        		  cancel();
			        	 }else
			        	 {
			        		 $('#table_bytx_lishihuifangjilu_id').datagrid('beginEdit', rowIndex);
			        		 $.messager.alert('优亿软件提示',r.msg,'info',function(){
						     });
			        	 }
	                 }
	             });
		    }
         }
		});
});	
function openAdvice(){
	//var data = $('#customer_genz1').datagrid('getSelected');
		var d = $('<div/>').dialog({
							modal : true,
							closable : true,
							title : '增加',
							width : 850,
							height : 450,

							href : '../return_visit/rushToRepair.jsp',

							href : projectPath+'return_visit/rushToRepair.jsp',
							buttons : [
									{
										iconCls : 'icon-save',
										text : '保存',
										handler : function() {
											if ($('#frtResvAdviceForm').form('validate')) {
												$.ajax({
													   type: 'post',
													   dataType: 'json',
													   url: 'frtReceptionAction!addFrtResvAdvice.action',
													   data: $('#frtResvAdviceForm').serialize(),
													   success: function(r){
														 if(r.success){
															 d.dialog('close');
															 alertMsg(r);															
														 }else{
														 	alertMsg(r);
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
								var carLincen=$("#car_License_id").val();
								var carId=$("#carId").val();
								$("#scarLicense").val(carLincen);
								$("#scarId").val(carId);
							}
						});
}
//按钮区域增加 保存和取消按钮
function  addButton(){
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}

function cancel(){
	 $('#saveOrCancelBtn').empty();
}
function updateMaintenance(){
	var selects = $('#table_bytx_lishihuifangjilu_id').datagrid('getSelections');
	 if (selects.length > 0) {
		if (selects.length == 1) {
			var isno=$('#table_bytx_lishihuifangjilu_id').datagrid('validateRow',editRow);
			if(isno)
			{
				$('#btn_maintanence_delete').linkbutton('disable');
				editRow = $('#table_bytx_lishihuifangjilu_id').datagrid('getRowIndex', selects[0]);
				$('#table_bytx_lishihuifangjilu_id').datagrid('beginEdit', editRow);
				bindEnterInCloumn($('#table_bytx_lishihuifangjilu_id'), editRow, 0);
				  addButton();
			}else
			{
				$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
			   });
			}
		}
	} else {
		 $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
			 $('#saveOrCancelBtn').empty();
		 });
	}
}
function save() {
	if (editRow == undefined) {
		 $.messager.alert('优亿软件提示','当前没有要保存的记录！','info',function(){
			 $('#saveOrCancelBtn').empty();
		 });
	}else
	{
		var isno=$('#table_bytx_lishihuifangjilu_id').datagrid('validateRow',editRow);
		if(isno)
		{
			$('#table_bytx_lishihuifangjilu_id').datagrid('endEdit', editRow);
		}else
		{
			 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
			 });
		}
	}
}	
function deleteMaintenance(){
	 var data = $('#table_bytx_lishihuifangjilu_id').datagrid('getSelected');
	  if (data) {
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "customerCareAction!deleteReminder.action",
						data : "g_Id=" + data.g_Id,
						dataType : "json",
						success : function callback(r){
							if(r.success==true)
							{
								 alertMsg(r);	
								 clearForm();
								 $('#table_bytx_lishihuifangjilu_id').datagrid('clearSelections');
								$('#table_bytx_lishihuifangjilu_id').datagrid({
									url : 'customerCareAction!getHistoricalVisit.action?car_Id='+carId+'&group_Name='+type
								});
							}else
							{
								$.messager.alert('优亿软件提示',r.msg,'info',function(){
								});
							}
						}
					});
		         }
			 });
  }else{
	   $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
  }
}
function clearForm(){
	$("#return_Visit_Date_id").val('');
	$("#visit_Content_id").val('');
	$('#txResault_id').combobox('setValue', '');
	$('#statusName_id').combobox('setValue', '');
	$("#g_Id").val('');
}
