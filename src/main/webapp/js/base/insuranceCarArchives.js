$(function (){
	//基础资料->保险(汽厂)档案
	$insuranceCarArchivesDatagrid = $('#insuranceCarArchivesDatagrid');
	
	$insuranceCarArchivesDatagrid.datagrid({
		url : 'basRelationCampanyAction!datagridInsuranceCarArchives.action',
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
			title : '名称',
			width : 80,
			sortable : true
		}, {
			field : 'relcampJm',
			title : '简码',
			width : 80,
			sortable : true
		}, {
			field : 'relcampAttr',
			title : '属性',
			width : 80,
			sortable : true,
			formatter : function (value, row, index){
				return row.attrName;
			}
		}, {
			field : 'attrName',
			title : '地址',
			width : 120,
			hidden : true
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
	$insuranceCarArchivesDatagrid.datagrid('unselectAll');
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '保险(汽厂)档案新增',
		width : 375,
		height : 316,
		href : projectPath+'base/insuranceCarArchives/addInsuranceCarArchives.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#insuranceCarArchivesAddForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'basRelationCampanyAction!saveInsuranceCarArchives.action',
					   data: $('#insuranceCarArchivesAddForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $insuranceCarArchivesDatagrid.datagrid('load');
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
	var data = $insuranceCarArchivesDatagrid.datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'basRelationCampanyAction!removeInsuranceCarArchives.action',
				   data: 'relcampId=' + data.relcampId,
				   success: function(r){
					 if(r.success){
						 var index = $insuranceCarArchivesDatagrid.datagrid('getRowIndex', data);
						 $insuranceCarArchivesDatagrid.datagrid('load');
						 $insuranceCarArchivesDatagrid.datagrid({
							 onLoadSuccess : function (){
								$insuranceCarArchivesDatagrid.datagrid('selectRow', index);
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
	var data = $insuranceCarArchivesDatagrid.datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '保险(汽厂)档案修改',
			width : 375,
			height : 316,
			href : projectPath+'base/insuranceCarArchives/editInsuranceCarArchives.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#insuranceCarArchivesEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'basRelationCampanyAction!editInsuranceCarArchives.action',
						   data: $('#insuranceCarArchivesEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $insuranceCarArchivesDatagrid.datagrid('reload');
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
		    	$('#insuranceCarArchivesEditForm').form('load', data);
		    	$('#insuranceCarArchives_edit_relcampName').select();
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var query = function (){
	if($('#insuranceCarArchivesQueryForm').form('validate')){
		$insuranceCarArchivesDatagrid.datagrid('load', serializeObject($('#insuranceCarArchivesQueryForm')));
	}else{
		alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
	}
	
}

var _clear = function (){
	$('#insuranceCarArchivesQueryForm').form('clear');
	$insuranceCarArchivesDatagrid.datagrid('load', serializeObject($('#insuranceCarArchivesQueryForm')));
}
//导出
function _except(){
	showEditDialog("insuranceCarArchivesDatagrid",null,"insuranceCarArchivesDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"保险(汽厂)档案"+getSystemTime());
}