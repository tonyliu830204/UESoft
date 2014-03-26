$(function(){
			$('#projectData').datagrid({
					url:'${pageContext.request.contextPath}/dbProjectAction!getPagePro.action',
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
							title : '项目代码',
							field : 'projectCode',
							width : 150,
							hidden:true
					    }, {
							title : '项目名称',
							field : 'projectName',
							width : 150
						}, {
							title : '代办成本',
							field : 'projectMomay',
							width : 100
						}, {
							title : '收费金额',
							field : 'projectAmount',
							width : 100
						}, {
							title : '收取部门',
							field : 'projectDept',
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
function addProject(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 500,
		height : 210,
		href : projectPath+'sell/basedata/dbProject/dbProjectEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#proEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/dbProjectAction!savePro.action',
					   data: $('#proEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#projectData').datagrid('load');
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
function removeProject(){
var data = $('#projectData').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'dbProjectAction!deletePro.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#projectData').datagrid('load');
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
var editProject = function (){
	var data = $('#projectData').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 500,
			height : 210,
			href : projectPath+'sell/basedata/dbProject/dbProjectEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#proEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'dbProjectAction!updatePro.action',						   
						   data: $('#proEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#projectData').datagrid('reload');
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
		    	var data = $('#projectData').datagrid('getSelected');
		    	$('#proEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryProject = function (){
		$('#projectData').datagrid('unselectAll');
		$('#projectData').datagrid('load', serializeObject($('#proQueryForm')));
}
function clearSearchCondition(){
		$('#proQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'dbProjectAction!getPagePro.action',
			data:$('#proQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#projectData').datagrid('load',r);
			}
	    });
	}

//导出
function _except(){
	var data =  $("#projectData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("projectData",null,"projectData_id","开始导出","导出配置",0,_callbackExcept);
}
	

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"代办项目"+getSystemTime());
}

function _config(){
	var data =  $("#projectData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("projectData",personnelSumTable,"projectData_id","开始打印","打印配置",2,_print);		
	
}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}