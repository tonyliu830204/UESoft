$(function (){
	//基础资料->客户档案
	$customArchivesDatagrid = $('#customArchivesDatagrid');
	
	$customArchivesDatagrid.datagrid({
		url : 'frtCustomAction!datagrid.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		rownumbers : true,
		frozenColumns:[[
			{
				field : 'customId',
				title : '客户编号',
				width : 60,
				sortable : true
			}, {
				field : 'customName',
				title : '客户名称',
				width : 60,
				sortable : true
			}
		]],
		columns : [[{
			field : 'customJm',
			title : '客户简码',
			width : 60,
			sortable : true
		},{
			field : 'createDate',
			title : '建档日期',
			width : 80,
			sortable : true
		},{
			field : 'natureId',
			title : '客户性质',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return row.natureName;
			}
		},{
			field : 'natureName',
			title : '客户性质',
			width : 60,
			hidden : true
		},{
			field : 'cstgId',
			title : '客户分类',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstgName;
			}
		},{
			field : 'cstgName',
			title : '客户分类',
			width : 80,
			hidden : true
		},{
			field : 'cstId',
			title : '客户类型',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstName;
			}
		},{
			field : 'cstName',
			title : '客户类型',
			width : 80,
			hidden : true
		},{
			field : 'pareaId',
			title : '所在区域',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.pareaName;
			}
		},{
			field : 'pareaName',
			title : '所在区域',
			width : 80,
			hidden : true
		},{
			field : 'customAddr',
			title : '地址',
			width : 120,
			sortable : true
		},{
			field : 'customTel1',
			title : '联系电话',
			width : 80,
			sortable : true
		},{
			field : 'customTel2',
			title : '固定电话',
			width : 80,
			sortable : true
		},{
			field : 'customEmail',
			title : '电子邮箱',
			width : 100,
			sortable : true
		},{
			field : 'bankOfDeposit',
			title : '开户银行',
			width : 100,
			sortable : true
		},{
			field : 'bankAccount',
			title : '银行账号',
			width : 100,
			sortable : true
		},{
			field : 'taxId',
			title : '税号',
			width : 100,
			sortable : true
		},{
			field : 'invoicingAddress',
			title : '开票地址',
			width : 150,
			sortable : true
		},{
			field : 'invoicingTel',
			title : '开票电话',
			width : 100,
			sortable : true
		},{
			field : 'fax',
			title : '传真',
			width : 100,
			sortable : true
		},{
			field : 'linkman',
			title : '公司联系人',
			width : 100,
			sortable : true
		},{
			field : 'linkmanTel',
			title : '公司联系人电话',
			width : 100,
			sortable : true
		},{
			field : 'customRemark1',
			title : '备注一',
			width : 150,
			sortable : true
		}, {
			field : 'customRemark2',
			title : '备注二',
			width : 150,
			sortable : true
		}]],
  		onAfterEdit : function(rowIndex, rowData, changes) {
  			onAfterEdit($clientArchives, 'frtCustom_save' , 'frtCustom_edit',rowIndex, rowData, changes);
  		},
  		onDblClickRow:function(rowIndex,rowData){
 			showInfo(rowData);
 		}
	});
});
var showInfo=function(rowData){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '客户档案查看',
		width : 800,
		height : 400,
		href : projectPath+'base/customArchives/addCustomArchives.jsp',
		buttons : [{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
		onClose : function (){
	    	d.dialog('destroy');
	    },
	    onLoad : function (){
			$('#customArchivesAddForm').form('load', rowData);
	    }
	});
}
var _add = function (){
	customFlage=true;
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '客户档案新增',
		width : 800,
		height : 400,
		href : projectPath+'base/customArchives/addCustomArchives.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#customArchivesAddForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'frtCustomAction!save.action',
					   data: $('#customArchivesAddForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $customArchivesDatagrid.datagrid('load');
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
					alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
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
	    	d.dialog('destroy');
	    }
	});
}

var _remove = function (){
	var data = $customArchivesDatagrid.datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(r){   
		    if (r){   
		    	$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'frtCustomAction!remove.action',
				   data: 'customId=' + data.customId,
				   success: function(r){
					 if(r.success){
						var index = $customArchivesDatagrid.datagrid('getRowIndex', data);
						$customArchivesDatagrid.datagrid('load');
						$customArchivesDatagrid.datagrid({
							onLoadSuccess : function (){
								$customArchivesDatagrid.datagrid('selectRow', index);
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
var  customFlage=true;
var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	customFlage=false;
	var data = $customArchivesDatagrid.datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '客户档案修改',
			width : 800,
			height : 400,
			href : projectPath+'base/customArchives/addCustomArchives.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#customArchivesAddForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'frtCustomAction!edit.action',
						   data: $('#customArchivesAddForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $customArchivesDatagrid.datagrid('reload');
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
		    	var data = $customArchivesDatagrid.datagrid('getSelected');
				$('#customArchivesAddForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var query = function (){
	if($('#customArchivesQueryForm').form('validate')){
		$customArchivesDatagrid.datagrid('load', serializeObject($('#customArchivesQueryForm')));
	}else{
		alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
	 }
}

var _clear = function (){
	$('#customArchivesQueryForm').form('clear');
	$customArchivesDatagrid.datagrid('load', serializeObject($('#customArchivesQueryForm')));
}

var changeDialog;

var customChange = function (){
	var data = $customArchivesDatagrid.datagrid('getSelected');
	if(data){
		changeDialog = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '客户代码变更',
			width : 380,
			height : 350,
			href : projectPath+'base/customArchives/customChange.jsp',
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $customArchivesDatagrid.datagrid('getSelected');
		    	$('#customArchives_changeBeforeForm').form('load', data);
		    }
		});
	}
}
//导出
function _except(){
	showEditDialog("customArchivesDatagrid",null,"customArchivesDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"客户档案"+getSystemTime());
}