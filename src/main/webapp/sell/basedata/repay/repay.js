$(function(){
			$('#repayData').datagrid({
					url:'${pageContext.request.contextPath}/repayAction!getPageRepay.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'repayId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编码',
							field : 'repayId',
							width : 50,
							hidden:true
					    },{
							title : '进度编码',
							field : 'repayCode',
							width : 50,
							hidden:true
					    }, {
							title : '进度名称',
							field : 'repayName',
							width : 100
						}, {
							title : '间隔天数',
							field : 'repayDay',
							width : 100
						}, {
							title : '备注',
							field : 'repayRemark',
							width : 100
						},{
							title : '企业编号',
							field : 'enterpriseId',
							width : 100,
							hidden:true
						}
				        ]]
				});
		});
function addRepay(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 470,
		height : 210,
		href : projectPath+'sell/basedata/repay/repayEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#repayEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/repayAction!saveRepay.action',
					   data: $('#repayEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#repayData').datagrid('load');
							 d.dialog('close');
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
			}
        },{
        	iconCls : 'icon-undo',
			text : '取消',
			handler : function (){
        		d.dialog('close');
			}
        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}
//删除
function removeRepay(){
var data = $('#repayData').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'repayAction!deleteRepay.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#repayData').datagrid('load');
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
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}		
}
//修改
var edit = function (){
	var data = $('#repayData').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 470,
			height : 210,
			href : projectPath+'sell/basedata/repay/repayEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#repayEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'repayAction!updateRepay.action',						   
						   data: $('#repayEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#repayData').datagrid('reload');
								 d.dialog('close');
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
				}
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
	        		d.dialog('close');
				}
	        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $('#repayData').datagrid('getSelected');
		    	$('#repayEditForm').form('load', data);
		    }
		});
	}else{ 
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var query = function (){
		$('#repayData').datagrid('unselectAll');
		$('#repayData').datagrid('load', serializeObject($('#repayQueryForm')));
}
function clearSearchCondition(){
	$('#repayQueryForm').form('clear');
	$.ajax({                                           
		type : 'POST',
		url : 'repayAction!getPageRepay.action',
		data:$('#repayQueryForm').serialize(),
		dataType : 'json',
		success : function(r){
			$('#repayData').datagrid('load',r);
		}
    });
}
//导出
function _except(){
	var data =  $("#repayData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("repayData",null,"repayData_id","开始导出","导出配置",0,_callbackExcept);
}
	

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"回访进度"+getSystemTime());
}

function _config(){
	var data =  $("#repayData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("repayData",personnelSumTable,"repayData_id","开始打印","打印配置",2,_print);		
	
}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}