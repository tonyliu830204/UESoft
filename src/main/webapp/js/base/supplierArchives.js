$(function (){
	//基础资料->供应商档案
	$supplierArchivesDatagrid = $('#supplierArchivesDatagrid');
	
	$supplierArchivesDatagrid.datagrid({
		url : 'basRelationCampanyAction!datagridSupplierArchives.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		rownumbers : true,
        columns : [[{
			field : 'relcampId',
			title : '编号',
			width : 80,
			sortable : true
		}, {
			field : 'relcampName',
			title : '供应商名称',
			width : 80,
			sortable : true
		}, {
			field : 'relcampJm',
			title : '供应商简码',
			width : 80,
			sortable : true
		}, {
			field : 'relcampAddr',
			title : '地址',
			width : 120,
			sortable : true
		}, {
			field : 'relcampContact',
			title : '联系人',
			width : 80,
			sortable : true
		}, {
			field : 'relcampTel1',
			title : '联系电话',
			width : 80,
			sortable : true
		}, {
			field : 'relcampTel2',
			title : '固定电话',
			width : 80,
			sortable : true
		},  {
			field : 'relcampFax',
			title : '传真',
			width : 80,
			sortable : true
		}, {
			field : 'relcampBank',
			title : '开户银行',
			width : 80,
			sortable : true
		}, {
			field : 'relcampAccount',
			title : '银行账号',
			width : 80,
			sortable : true
		}, {
			field : 'relcampTaxNum',
			title : '税号',
			width : 80,
			sortable : true
		}, {
			field : 'invoicingAddress',
			title : '开票地址',
			width : 120,
			sortable : true
		}, {
			field : 'invoicingTel',
			title : '开票电话',
			width : 80,
			sortable : true
		}, {
			field : 'relcampZipCode',
			title : '邮政编码',
			width : 80,
			sortable : true
		}, {
			field : 'relcampRemark1',
			title : '备注',
			width : 160,
			sortable : true
		}, {
			field : 'relcampFlag',
			title : '标志位',
			width : 60,
			hidden : true
		}] ]
	});
});

var add = function (){
	$supplierArchivesDatagrid.datagrid('unselectAll');
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '供应商档案新增',
		width : 390,
		height : 355,
		href : projectPath+'base/supplierArchives/addSupplierArchives.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#supplierArchivesAddForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'basRelationCampanyAction!saveSupplierArchives.action',
					   data: $('#supplierArchivesAddForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $supplierArchivesDatagrid.datagrid('load');
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

var _remove = function (){
	var data = $supplierArchivesDatagrid.datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'basRelationCampanyAction!removeSupplierArchives.action',
				   data: 'relcampId=' + data.relcampId,
				   success: function(r){
					 if(r.success){
						 var index = $supplierArchivesDatagrid.datagrid('getRowIndex', data);
						 $supplierArchivesDatagrid.datagrid('load');
						 $supplierArchivesDatagrid.datagrid({
							 onLoadSuccess : function (){
								$supplierArchivesDatagrid.datagrid('selectRow', index);
							}
						 });
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

var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	var data = $supplierArchivesDatagrid.datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '供应商档案修改',
			width : 390,
			height : 355,
			href : projectPath+'base/supplierArchives/editSupplierArchives.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#supplierArchivesEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'basRelationCampanyAction!editSupplierArchives.action',
						   data: $('#supplierArchivesEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $supplierArchivesDatagrid.datagrid('reload');
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
		    	$('#supplierArchivesEditForm').form('load', data);
		    	$('#supplierArchives_edit_relcampName').select();
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var query = function (){
	$supplierArchivesDatagrid.datagrid('load', serializeObject($('#supplierArchivesQueryForm')));
}

var _clear = function (){
	$('#supplierArchivesQueryForm').form('clear');
	$supplierArchivesDatagrid.datagrid('load', serializeObject($('#supplierArchivesQueryForm')));
}

var changeDialog;

var supplierChange = function (){
	var data = $supplierArchivesDatagrid.datagrid('getSelected');
	if(data){
		changeDialog = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '供应商变更',
			width : 380,
			height : 350,
					href : projectPath+'base/supplierArchives/supplierChange.jsp',
			        onClose : function (){
				    	$(this).dialog('destroy');
				    },
				    onLoad : function (){
				    	var data = $supplierArchivesDatagrid.datagrid('getSelected');
				    	$('#supplierArchives_changeBeforeForm').form('load', data);
				    }
				});
			}
		}
		//导出
		function _except(){
			showEditDialog("supplierArchivesDatagrid",null,"supplierArchivesDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"供应商档案"+getSystemTime());
		}