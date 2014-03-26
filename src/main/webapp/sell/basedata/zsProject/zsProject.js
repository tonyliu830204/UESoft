$(function(){
			$('#zsProData').datagrid({
					url:'${pageContext.request.contextPath}/zsProjectAction!getPageZsPro.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'zsprojectId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'zsprojectId',
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
							title : '成本额',
							field : 'projectCostamount',
							width : 100
						}, {
							title : '销售价',
							field : 'projectSellamount',
							width : 100
						}, {
							title : '备注',
							field : 'projectRemark',
							width : 100
						},
						{
							title : '企业编号',
							field : 'enterpriseId',
							width : 100,
							hidden:true
						}
				        ]]
				});
		});
function addZsPro(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 500,
		height : 240,
		href : projectPath+'sell/basedata/zsProject/zsProjectEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#zsProEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/zsProjectAction!saveZsPro.action',
					   data: $('#zsProEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#zsProData').datagrid('load');
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
function removeZsPro(){
var data = $('#zsProData').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'zsProjectAction!deleteZsPro.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#zsProData').datagrid('load');
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
var editZsPro = function (){
	var data = $('#zsProData').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 500,
			height : 240,
			href : projectPath+'sell/basedata/zsProject/zsProjectEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#zsProEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'zsProjectAction!updateZsPro.action',						   
						   data: $('#zsProEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#zsProData').datagrid('reload');
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
		    	var data = $('#zsProData').datagrid('getSelected');
		    	$('#zsProEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryZsPro = function (){
		$('#zsProData').datagrid('unselectAll');
		$('#zsProData').datagrid('load', serializeObject($('#zsProQueryForm')));
}
function clearSearchCondition(){
		$('#zsProQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'zsProjectAction!getPageZsPro.action',
			data:$('#zsProQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#zsProData').datagrid('load',r);
			}
	    });
	}
//导出
function _except(){
	var data =  $("#zsProData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
		showEditDialog("zsProData",null,"zsProData_id","开始导出","导出配置",0,_callbackExcept);
}
	

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"赠送项目"+getSystemTime());
}

function _config(){
	var data =  $("#zsProData").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("zsProData",personnelSumTable,"zsProData_id","开始打印","打印配置",2,_print);		
	
}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}