$(function(){
			$('#reProData').datagrid({
					url:'${pageContext.request.contextPath}/reProjectAction!getPageRePro.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'projectId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'projectId',
							width : 50,
							hidden:true
						},{
							title : '项目编码',
							field : 'projectCode',
							width : 150,
							hidden:true
					    }, {
							title : '项目名称',
							field : 'projectName',
							width : 150
						}, {
							title : '回访进度',
							field : 'projectType',
							width : 100,
							 formatter : function (value,row,index){
							 return row.repayName;
						 }
						}, {
							title : '备注',
							field : 'projectRemark',
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
function addRePro(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 500,
		height : 240,
		href : projectPath+'sell/basedata/repayPro/repayProEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#reProEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/reProjectAction!saveRePro.action',
					   data: $('#reProEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#reProData').datagrid('load');
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
function removeRePro(){
var data = $('#reProData').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'reProjectAction!deleteZsPro.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#reProData').datagrid('load');
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
var editRePro = function (){
	var data = $('#reProData').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 500,
			height : 240,
			href : projectPath+'sell/basedata/repayPro/repayProEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#reProEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'reProjectAction!updateRePro.action',						   
						   data: $('#reProEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#reProData').datagrid('reload');
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
		    	var data = $('#reProData').datagrid('getSelected');
		    	$('#reProEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryRePro = function (){
		$('#reProData').datagrid('unselectAll');
		$('#reProData').datagrid('load', serializeObject($('#reProQueryForm')));
}
function clearSearchCondition(){
		$('#reProQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'reProjectAction!getPageRePro.action',
			data:$('#reProQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#reProData').datagrid('load',r);
			}
	    });
	}
//导出
function _except(){
	var data =  $("#reProData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("reProData",null,"reProData_id","开始导出","导出配置",0,_callbackExcept);
}
	

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"客户回访项目"+getSystemTime());
}

function _config(){
	var data =  $("#reProData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("reProData",personnelSumTable,"reProData_id","开始打印","打印配置",2,_print);		
	
}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}