$(function(){
			$('#customLeva').datagrid({
					url:'${pageContext.request.contextPath}/customLevaAction!getPageLeva.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'levaId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [{
							title : '编码',
							field : 'levaId',
							width : 50,
							hidden:true
					    }, {
							title : '等级编码',
							field : 'levaCode',
							width : 50,
							hidden:true
					    }, {
							title : '等级名称',
							field : 'levaName',
							width : 100
						}, {
							title : '跟踪间隔',
							field : 'jianGe',
							width : 100
						}, {
							title : '备注',
							field : 'remark',
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
function addCustom(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 450,
		height : 200,
		href : projectPath+'sell/basedata/custom/customEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#customEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/customLevaAction!saveCustom.action',
					   data: $('#customEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#customLeva').datagrid('reload');
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
function removeCustom(){
var data = $('#customLeva').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'customLevaAction!deleteCustom.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						 $('#customLeva').datagrid('load');
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
	var data = $('#customLeva').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 450,
			height : 200,
			href : projectPath+'sell/basedata/custom/customEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#customEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'customLevaAction!updateCustom.action',						   
						   data: $('#customEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#customLeva').datagrid('reload');
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
		    	var data = $('#customLeva').datagrid('getSelected');
		    	$('#customEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var query = function (){
		$('#customLeva').datagrid('unselectAll');
		$('#customLeva').datagrid('load', serializeObject($('#customQueryForm')));
}
	function clearSearchCondition(){
		$('#customQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'customLevaAction!getPageLeva.action',
			data:$('#customQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#customLeva').datagrid('load',r);
			}
	    });
		
	}
	 //导出
	 function _except(){
		 var data =  $("#customLeva").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
			showEditDialog("customLeva",null,"customLeva_id","开始导出","导出配置",0,_callbackExcept);
	 }
		

	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"潜在客户等级"+getSystemTime());
	}
	

	/**
	* 打印字段设置
	* 编辑、选择不同分组
	* @return
	*/
	function _config(){
		var data =  $("#customLeva").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
			showEditDialog("customLeva",personnelSumTable,"customLeva_id","开始打印","打印配置",2,_print);		
		
	}
	/**
	* 打印字段设置回调函数
	* 将选择的列打印
	* @return
	*/
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}