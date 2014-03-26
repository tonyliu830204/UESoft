var parentData; 
var myTitle;
$(function(){
	$('#tt').tabs({   
	onSelect:function(title){  
		myTitle=title;
		if(title =='类型列表'){
			myTitle=title;
			$('#_add').linkbutton('enable');
			$('#_remove').linkbutton('enable');
			$('#_update').linkbutton('enable');
			$('#_search').linkbutton('enable');
			$('#_clear').linkbutton('enable');
			$('#_print').linkbutton('enable');
			$('#_export').linkbutton('enable');
			$("#_add").unbind().click(addStPurOrder);  
			$("#_remove").unbind().click(deleteStPurOrder);
			$("#_update").unbind().click(updateStPurOrder);  
			$("#_search").unbind().click(queryStPurOrder);  
			$("#_clear").unbind().click(clearStPurOrder); 
			$("#_print").unbind().click(_config);
			$("#_export").unbind().click(_except);
		}else if(title =='类型明细'){
			if(parentData==null || parentData==undefined){
				$('#_add').linkbutton('disable');
				$('#_remove').linkbutton('disable');
				$('#_update').linkbutton('disable');
				$('#_search').linkbutton('disable');
				$('#_clear').linkbutton('disable');
				$('#_print').linkbutton('enable');
				$('#_export').linkbutton('enable');
				$("#_add").unbind();  
				$("#_remove").unbind();  
				$("#_update").unbind();  
				$("#_search").unbind();  
				$("#_clear").unbind(); 
				$("#_print").unbind().click(_config);
				$("#_export").unbind().click(_except);
			}else{
				$('#_add').linkbutton('enable');
				$('#_remove').linkbutton('enable');
				$('#_update').linkbutton('enable');
				$('#_search').linkbutton('enable');
				$('#_clear').linkbutton('enable');
				$('#_print').linkbutton('enable');
				$('#_export').linkbutton('enable');
				$("#_add").unbind().click(addStOrderItem);  
				$("#_remove").unbind().click(deleteStOrderItem);  
				$("#_update").unbind().click(updateStOrderItem);  
				$("#_search").unbind().click(queryStOrderItem);  
				$("#_clear").unbind().click(clearStOrderItem);  
				$("#_print").unbind().click(_config);
				$("#_export").unbind().click(_except);
			}		
		}
    }   
});
	$('#stPurOrderTable').datagrid({
		 url:'${pageContext.request.contextPath}/baseTagAction!findAllParentdictionanry.action',
		 fit:true,
		 pagination : true,
		 fitColumns : true,
		 sortOrder:'asc',
	     sortName:'pparentId',
		 singleSelect : true,
		 pageSize : 20,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {
					title : '编号',
					field : 'pparentId',
					width : 105,
					hidden:true
				}, {
					title : '创建日期',
					field : 'pcreateTime',
					width : 75
				}, {
					title : '创建者',
					field : 'stfName',
					width : 90
				}, {
					title : '键名',
					field : 'pdataKey',
					width : 60
				}, {
					title : '键值',
					field : 'pdataValue',
					width : 70
				}
		        ]],
		onDblClickRow:function(rowIndex, rowData){                 //双击类型列表某条记录
				parentData=rowData;
				$('#tt').tabs('select','类型明细');
				 $.ajax({                                               //为类型明细datagrid赋值
					type : 'POST',
					url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+rowData.pparentId,
					success : function(r){
								if(r){
									$('#StOrderItemTable').datagrid({
										url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+rowData.pparentId,
										pagination : true,
										queryParams: {cquerydataKey:"",cquerydataValue:""}
									});
								}else{
										$.messager.alert('提示','数据提交失败!','warning');
								     }
		                      }
			     }); 
			}
		 });	
	 $('#StOrderItemTable').datagrid({
		 fit:true,
		 pagination : true,
		 pageSize : 20,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'childId',
		 singleSelect : true,
		 rownumbers : true,
		 loadMsg : "数据加载中，请稍后……",
		 columns : [ [ {
					title : '编号',
					field : 'childId',
					width : 50,
					hidden:true
			    }, {
					title : '创建者',
					field : 'stfName',
					width : 100
				}, {
					title : '父级编号',
					field : 'parentId',
					width : 100
				}, {
					title : '创建时间',
					field : 'createTime',
					width : 100
				}, {
					title : '键名',
					field : 'dataKey',
					width : 100
				}, {
					title : '键值',
					field : 'dataValue',
					width : 100
				}
		        ]]
	    });
	 
});
//父表新增
function addStPurOrder(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 380,
		height : 150,
		href : projectPath+'sell/basedata/CarProperties/parentEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#parentEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/baseTagAction!addBasParentdictionary.action',
					   data: $('#parentEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#stPurOrderTable').datagrid('load');
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
				}else{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
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
//父表删除
function deleteStPurOrder(){
var data = $('#stPurOrderTable').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'baseTagAction!deleteBasParentdictionary.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#stPurOrderTable').datagrid('load');
						 $('#StOrderItemTable').datagrid({
								url :'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
								pagination : true
							});
						 parentData=null;
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
//父表修改
 function updateStPurOrder(){
	var data = $('#stPurOrderTable').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 380,
			height : 150,
			href : projectPath+'sell/basedata/CarProperties/parentEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#parentEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'baseTagAction!updateBasParentdictionary.action',						   
						   data: $('#parentEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#stPurOrderTable').datagrid('reload');
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
					}else{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
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
		    	var data = $('#stPurOrderTable').datagrid('getSelected');
		    	$('#parentEditForm').form('load', data);
		    	$("#pdataKey").attr('readonly',true);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
 function queryStPurOrder(){
		$('#stPurOrderTable').datagrid('unselectAll');
		$('#stPurOrderTable').datagrid('load', serializeObject($('#StPurOrderSearchForm')));
}
 function clearStPurOrder(){
	 $('#StPurOrderSearchForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'baseTagAction!findAllParentdictionanry.action',
			data:$('#StPurOrderSearchForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#stPurOrderTable').datagrid('load',r);
			}
	    });
 }
 
//子表新增
 function addStOrderItem(){
	 
 	var d = $('<div/>').dialog({
 		modal:true,
 		closable : true,
 		title : '新增',
 		width : 380,
 		height : 150,
 		href : projectPath+'sell/basedata/CarProperties/childEdit.jsp',
 		buttons : [{
 			iconCls : 'icon-save',
 			text : '保存',
 			handler : function (){
 				if($('#childEditForm').form('validate')){
 					$.ajax({
 					   type: 'post',
 					   dataType: 'json',
 					   url:'${pageContext.request.contextPath}/baseTagAction!addBasChilddictionary.action',
 					   data: $('#childEditForm').serialize(),
 					   success: function(r){
 						if(r.success){
							$('#StOrderItemTable').datagrid({
								url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
								pagination : true
							});
							 d.dialog('close');
						}else{
								$.messager.alert('提示','数据提交失败!','warning');
						     }
 					   },
 					   error : function (r){
 						   if(r.readyState == '0' && r.status == '0'){
 							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
 						   }
 					   }
 					   
 					});
 				}else{
					 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
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
 		    	
		         $("#parentId").val(parentData.pparentId);
 		    }
 		});
 }
 //子表删除
function deleteStOrderItem(){
 var data = $('#StOrderItemTable').datagrid('getSelected');
 	if(data){
 		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
 			if (r){
 				$.ajax({
 				   type: 'post',
 				   dataType: 'json',
 				   url: 'baseTagAction!deleteBasChilddictionary.action',
 				   data: data,
 				   success: function(r){
 					if(r.success){
						$('#StOrderItemTable').datagrid({
							url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
							pagination : true
						});
						 d.dialog('close');
					}else{
						$.messager.alert('优亿软件提示',r.msg,'warning');
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
 //子表修改
  function updateStOrderItem(){
 	var data = $('#StOrderItemTable').datagrid('getSelected');
 	if(data){
 		var d = $('<div/>').dialog({
 			modal:true,
 			closable : true,
 			title : '修改',
 			width : 380,
 			height : 150,
 			href : projectPath+'sell/basedata/CarProperties/childEdit.jsp',
 			buttons : [{
 				iconCls : 'icon-save',
 				text : '保存',
 				handler : function (){
 					if($('#childEditForm').form('validate')){
 						$.ajax({
 						   type: 'post',
 						   dataType: 'json',
 						   url: 'baseTagAction!updateBasChilddictionary.action',						   
 						   data: $('#childEditForm').serialize(),
 						   success: function(r){
 							if(r){
 								$('#StOrderItemTable').datagrid({
 									url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
 									pagination : true,
 								});
 								 d.dialog('close');
 							}else{
 									$.messager.alert('提示','数据提交失败!','warning');
 							     }
 						   },
 						   error : function (r){
 							   if(r.readyState == '0' && r.status == '0'){
 								   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
 							   }
 						   }
 						});
 					}else{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
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
 		    	var data = $('#StOrderItemTable').datagrid('getSelected');
 		    	$('#childEditForm').form('load', data);
 		    	$("#dataKey").attr('readonly',true);
 		    }
 		});
 	}else{
 		alertMsg('对不起，请先选中要修改的记录！', 'warning');
 	}
 }
  //子表查询
  function queryStOrderItem(){
			$.ajax({                                           
				type : 'POST',
				url :  'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
				data : $('#StPurOrderForm').serialize(),
				dataType : 'json',
				success : function(r){
						if(r){
							$('#StOrderItemTable').datagrid({
								url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
								pagination : true,
								queryParams: {cquerydataKey:$("#cquerydataKey").val(),cquerydataValue:$("#cquerydataValue").val()},
							});
							
						}else{
								$.messager.alert('提示','数据提交失败!','warning');
						     }
				}
		    });
 }
 function clearStOrderItem(){
		 $('#StPurOrderForm').form('clear');
		 $.ajax({                                           
				type : 'POST',
				url :  'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
				data : $('#StPurOrderForm').serialize(),
				dataType : 'json',
				success : function(r){
						if(r){
							$('#StOrderItemTable').datagrid({
								url : 'baseTagAction!findAllBasChilddictionary.action?parentId='+parentData.pparentId,
								pagination : true,
								queryParams: {cquerydataKey:"",cquerydataValue:""}
							});
							
						}else{
								$.messager.alert('提示','数据提交失败!','warning');
						     }
				}
		    });
	 }
 
 
 //导出
 function _except(){
	 
	 if(myTitle =='类型列表'){
		 var data =  $("#stPurOrderTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		}
		showEditDialog("stPurOrderTable",null,"leixin","开始导出","导出配置",0,_callbackExcept);
	 }else if (myTitle =='类型明细'){
		 var data =  $("#StOrderItemTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		}
		 showEditDialog("StOrderItemTable",null,"mingxi","开始导出","导出配置",0,_callbackExcept2);
	 }
 }

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"数据字典"+getSystemTime());
}
function _callbackExcept2(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"数据字典明细"+getSystemTime());
}


/**
* 打印字段设置
* 编辑、选择不同分组
* @return
*/
function _config(){
	 if(myTitle =='类型列表'){
		 var data =  $("#stPurOrderTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		}
		showEditDialog("stPurOrderTable",personnelSumTable,"leixin","开始打印","打印配置",2,_print);		
	 }else if (myTitle =='类型明细'){
		 var data =  $("#StOrderItemTable").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		}
		 showEditDialog("StOrderItemTable",personnelSumTable,"mingxi","开始打印","打印配置",2,_print);	
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