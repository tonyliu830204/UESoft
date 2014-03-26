 var $tree;
 var nodeId;
$(function() {
		//页面初始化禁用按钮
		disableButton();
		$tree=$('#tt');
		$tree.tree({   
	    url:'${pageContext.request.contextPath}/sellTargetAction!loadTree.action',
	    onClick: function(node){
			nodeId=node.id;
			var parent = $tree.tree('getParent', node.target);
			var isNode=$tree.tree('isLeaf', node.target);
			if(isNode==true && parent != null){
				enableButton();
				loadDataGrid( nodeId);
			}else{
				disableButton();
			}

		}
 
	});
		
		$('#sellTarget').datagrid({
			fit:true,
			pagination : true,
			fitColumns : true,
			sortOrder:'asc',
		    sortName:'brandId',
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [ [ {
					title : '编号',
					field : 'brandId',
					width : 100,
					sortable:true,
					hidden:true
				},{
					title : '年月份',
					field : 'data',
					width : 100,
					sortable:true
			    }, {
					title : '车品牌',
					field : 'brandType',
					width : 100,
					sortable:true,
					formatter : function (value,row,index){
					 return row.brandName;
					}	
				}, {
					title : '销售台数',
					field : 'sellNum',
					width : 100,
					sortable:true
				}, {
					title : '销售金额',
					field : 'sellMoney',
					width : 100,
					sortable:true
				}, {
					title : '销售毛利',
					field : 'sellProfit',
					width : 100,
					sortable:true
				}, {
					title : '业务员',
					field : 'stfId',
					width : 100,
					sortable:true,
					hidden:true
				}
				,{
					title : '企业编号',
					field : 'enterprise_id',
					width : 100,
					hidden:true
				}
		        ]]
		});
		
	});
function enableButton(){
	$('#_add').linkbutton('enable');
	$('#_remove').linkbutton('enable');
	$('#_update').linkbutton('enable');
	$('#_print').linkbutton('enable');
	$('#_export').linkbutton('enable');
	$('#_import').linkbutton('enable');
	
}
function disableButton(){
	$('#_add').linkbutton('disable');
	$('#_remove').linkbutton('disable');
	$('#_update').linkbutton('disable');
	$('#_print').linkbutton('disable');
	$('#_export').linkbutton('disable');
	$('#_import').linkbutton('disable');
}
function addSellTarget(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 370,
		height : 260,
		href : projectPath+'sell/sellTarget/sellTargetEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#targetEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/sellTargetAction!saveSellTarget.action',
					   data: $('#targetEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 loadDataGrid( nodeId);
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
	        onLoad : function (){
		 		$("#stfId").val(nodeId);
	    	},
	        onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}
function removeSellTarget(){
	var data = $('#sellTarget').datagrid('getSelected');
	  var index=findSelectRowIndex('sellTarget',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellTargetAction!deleteSellTarget.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						    $('#sellTarget').datagrid('clearSelections');
						    loadDataGrid( nodeId);
							setSelectRow('sellTarget',index);
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
function editSellTarget(){
	var data = $('#sellTarget').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 370,
			height : 260,
			href :  projectPath+'sell/sellTarget/sellTargetEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#targetEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellTargetAction!updateSellTarget.action',						   
						   data: $('#targetEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 d.dialog('close');
								 loadDataGrid();
								 loadDataGrid( nodeId);
								
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
		    	var data = $('#sellTarget').datagrid('getSelected');
		    	$('#targetEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

function loadDataGrid( id){
	$('#sellTarget').datagrid({
		url : 'sellTargetAction!getPager.action',
		queryParams: {stfId:id},
		pagination : true,
	});
	
}

function _except(){
	var data =  $("#sellTarget").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog("sellTarget",null,"sellTarget_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
exportEsuyUIExcelFile(parentId,fieldNames,"销售指标设定"+getSystemTime());
}


function _config(){
var data =  $("#sellTarget").datagrid('getData'); 
 if(data.rows.length==0){
	 alertMsg('数据列表为空，不能打印！', 'warning');
	 return ;
 }
	showEditDialog("sellTarget",personnelSumTable,"sellTarget_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}