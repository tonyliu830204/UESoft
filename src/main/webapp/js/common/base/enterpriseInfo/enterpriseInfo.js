$(function(){
	$('#enterpriseInfo').datagrid({
		url:projectPath+'enterpriseInfoAction!getPager.action',
		fit:true,
		pagination : true,
		fitColumns : true,
		sortOrder:'asc',
	    sortName:'enterpriseId',
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
				title : '企业序号',
				field : 'enterpriseId',
				hidden:true
		    }, {
				title : '父企业序号',
				field : 'parentEnterpriseId',
				hidden:true
		    }, {
				title : '企业编号',
				field : 'enterpriseCode',
				width : 150
		    }, {
				title : '企业名称',
				field : 'enterpriseName',
				width : 200
			}, {
				title : '父企业名称',
				field : 'parentEnterpriseName',
				width : 100
			}, {
				title : '企业简称',
				field : 'enterpriseJm',
				width : 200
			}, {
				title : '企业地址',
				field : 'enterpriseAddress',
				width : 200
			}, {
				title : '邮政编码',
				field : 'enterpriseZipcode',
				width : 120
			},  {
				title : '电话',
				field : 'enterpriseTelephone',
				width : 120
			}, {
				title : '传真',
				field : 'enterpriseFax',
				width : 100
			}, {
				title : '企业法人',
				field : 'enterprisePerson',
				width : 100
			}, {
				title : '开户银行编号',
				field : 'bank',
				width : 100,
				hidden:true
			}, {
				title : '开户银行',
				field : 'bankName',
				width : 100
			}, {
				title : '银行账号',
				field : 'bankNumber',
				width : 100
			}, {
				title : '税号',
				field : 'dutyNumber',
				width : 100
			}, {
				title : '投诉电话',
				field : 'complainTelephone',
				width : 120
			}, {
				title : '销售热线',
				field : 'hotlineTelephone',
				width : 100
			}, {
				title : '网址',
				field : 'enterpriseUrl',
				width : 200
			}, {
				title : '邮箱',
				field : 'enterpriseEmail',
				width : 200
			}
	    ]],
	    onDblClickRow : function(rowIndex, rowData){
		    $('#tt').tabs('select',1);
		    $('#enterpriseId').val(rowData.enterpriseId);
		    $('#enterpriseCode').val(rowData.enterpriseCode);
		    $('#enterpriseName').val(rowData.enterpriseName);
		    $('#enterpriseJm').val(rowData.enterpriseJm);
		    $('#enterpriseAddress').val(rowData.enterpriseAddress);
		    $('#enterpriseZipcode').val(rowData.enterpriseZipcode);
		    $('#enterpriseFax').val(rowData.enterpriseFax);
		    $('#parentEnterpriseName').val(rowData.parentEnterpriseName);
		    $('#enterprisePerson').val(rowData.enterprisePerson);
		    $('#enterpriseTelephone').val(rowData.enterpriseTelephone);
		    $('#enterpriseEmail').val(rowData.enterpriseEmail);
		    $('#bank').combobox('setValue',rowData.bank);
		    $('#bankNumber').val(rowData.bankNumber);
		    $('#dutyNumber').val(rowData.dutyNumber);
		    $('#complainTelephone').val(rowData.complainTelephone);
		    $('#hotlineTelephone').val(rowData.hotlineTelephone);
		    $('#enterpriseUrl').val(rowData.enterpriseUrl);
		    $('#spo_seatchBtn').linkbutton('disable');
		    $('#spo_clearBtn').linkbutton('disable');
		    
		    var detailTable = $('#detailTable');
		    detailTable.datagrid({
				url : projectPath+'enterpriseInfoAction!getEnterpriseWork.action?enterpriseId=' +rowData.enterpriseId
			});
	    }			
	});
	
	$('#detailTable').datagrid({
		url:projectPath+'/',
		fit:true,
		pagination : true,
		fitColumns : false,
		sortOrder:'asc',
	    sortName:'workId',
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
				title : '序号',
				field : 'workId',
				hidden:true
		    }, {
				title : '企业序号',
				field : 'enterpriseId',
				hidden:true
		    }, {
				title : '估价单注脚',
				field : 'enterpriseGjFootnote',
				width : 150
		    }, {
				title : '派工单ISO编号',
				field : 'enterpriseJSISONO',
				width : 150
		    }, {
				title : '派工单抬头',
				field : 'enterprisePgHead',
				width : 150
		    }, {
				title : '索赔单注脚',
				field : 'enterpriseSpFootnot',
				width : 150
		    }, {
				title : '接车单注脚',
				field : 'enterpriseJcFootnote',
				width : 150
		    }, {
				title : '结算单抬头',
				field : 'enterpriseJsHead',
				width : 150
		    }, {
				title : '最大登录上限',
				field : 'enterpriseLoginLimit',
				width : 150
		    }, {
				title : '可用短信条数',
				field : 'enterpriseSMSLimit',
				width : 150
		    }, {
				title : '出库单ISO编号',
				field : 'outboundnumber',
				width : 150
		    }, {
				title : '网点编号',
				field : 'enterprisePorint',
				width : 150
		    }, {
				title : '车档案备注格式',
				field : 'enterpriseRemark',
				width : 150
		    }, {
				title : '最新文件目录',
				field : 'enterprisePath',
				width : 150
		    }, {
				title : '结算单注脚',
				field : 'enterpriseJsFootnote',
				width : 150
		    }
	    ]],
	    onLoadSuccess : function(data){
		   if(data.total > 0){
			   $('#spo_addBtn').linkbutton('disable');
		   }
	    }
	});
	
	$('#tt').tabs({   
		onSelect:function(title,index){  
			if(index == 0){
				$('#spo_seatchBtn').linkbutton('enable');
				$('#spo_clearBtn').linkbutton('enable');
				$('#spo_addBtn').linkbutton('enable');
				$("#spo_addBtn").unbind().click(addEnterprise);
				$("#spo_delectBtn").unbind().click(removeEnterprise);
				$("#spo_editBtn").unbind().click(editEnterprise);
				$('#spo_seatchBtn').unbind().click(queryEnterprise);
				$('#spo_clearBtn').unbind().click(clearSearchCondition);
				$('#spo_exportBtn').unbind().click(exportCondition);
				$('#spo_inportBtn').unbind().click(importCondition);
				$('#ok').unbind().click(_importVisable);
			}else{
				$('#spo_seatchBtn').linkbutton('enable');
				$('#spo_clearBtn').linkbutton('enable');
				$('#spo_addBtn').linkbutton('enable');
				$("#spo_addBtn").unbind().click(addEnterpriseWork);
				$("#spo_delectBtn").unbind().click(removeEnterpriseWork);
				$("#spo_editBtn").unbind().click(editEnterpriseWork);
			}
	    }   
	});
});

function addEnterprise(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '企业信息新增',
		width : 580,
		height :460,
		href : projectPath+'common/base/enterpriseInfo/enterpriseInfoEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#enterpriseAddForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:projectPath+'enterpriseInfoAction!saveEnterprise.action',
					   data: $('#enterpriseAddForm').serialize(),
					   success: function(r){
						   if(r.success){
							   $('#enterpriseInfo').datagrid('load');
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
        }, {
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

function addEnterpriseWork(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '企业业务信息新增',
		width : 580,
		height :460,
		href : projectPath+'common/base/enterpriseInfo/enterpriseInfoEditWork.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#enterpriseEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:projectPath+'enterpriseInfoAction!saveEnterpriseWork.action',
					   data: $('#enterpriseEditForm').serialize(),
					   success: function(r){
						   if(r.success){
							   $('#detailTable').datagrid('load');
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
        }, {
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
	    	var data = $('#enterpriseInfo').datagrid('getSelected');
	    	$('#enterpriseId').val(data.enterpriseId);
	    }
	});
}
//判断用户级别
function validateLevel(parentId){
	if(staticLoginLevel==staticEmployee){
		return true;
	}
	return false;
}
//删除
function removeEnterprise(){
    var data = $('#enterpriseInfo').datagrid('getSelected');
	if(data){
		if(data.parentEnterpriseId=='-1'){
			alertMsg("此信息禁止删除！","warning");
			return;
		}
		if(validateLevel(data.parentEnterpriseId)){
			alertMsg('缺少权限，不能操作！','warning');
			return;
		}
		if(staticEnterpriseId>=data.enterpriseId){
			alertMsg('缺少权限，不能操作！','warning');
			return;
		}
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.messager.confirm('优亿软件提示', '删除此企业后，与此企业关联的所有信息将一并删除，请慎重操作，<br>确认删除请点击确定？', function(r){
					if (r){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url:projectPath+'enterpriseInfoAction!deleteEnterprise.action?enterpriseDeleteValidateFlag=false',
						   data: data,
						   success: function(r){
							   if(r.success){
								   alertMsg(r);
								   $('#enterpriseInfo').datagrid('reload');
								   $('#detailTable').datagrid('reload');
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
			}
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}		
}

function removeEnterpriseWork(){
	var data = $('#detailTable').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:projectPath+'enterpriseInfoAction!deleteEnterpriseWork.action',
				   data: data,
				   success: function(r){
					   if(r.success){
						   $('#detailTable').datagrid('load');
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
		removeEnterprise();
	}
}


//修改
var editEnterprise = function (){
	var data = $('#enterpriseInfo').datagrid('getSelected');
	if(data){
		if(data.parentEnterpriseId=='-1'){
			alertMsg("此信息禁止修改！","warning");
			return;
		}
		if(validateLevel(data.parentEnterpriseId)){
			alertMsg('缺少权限，不能操作！','warning');
			return;
		}
		if(staticEnterpriseId>=data.enterpriseId){
			alertMsg('缺少权限，不能操作！','warning');
			return;
		}
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '企业信息修改',
			width : 580,
			height :460,
			href : projectPath+'common/base/enterpriseInfo/enterpriseInfoEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#enterpriseAddForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: projectPath+'enterpriseInfoAction!updateEnterprise.action',						   
						   data: $('#enterpriseAddForm').serialize(),
						   success: function(r){
							   if(r.success){
								   $('#enterpriseInfo').datagrid('reload');
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
		    	var data = $('#enterpriseInfo').datagrid('getSelected');
		    	$('#enterpriseAddForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var editEnterpriseWork = function (){
	var data = $('#detailTable').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '企业业务信息修改',
			width : 580,
			height :460,
			href : projectPath+'common/base/enterpriseInfo/enterpriseInfoEditWork.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#enterpriseEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: projectPath+'enterpriseInfoAction!updateEnterpriseWork.action',						   
						   data: $('#enterpriseEditForm').serialize(),
						   success: function(r){
							   if(r.success){
								   $('#detailTable').datagrid('reload');
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
		    	var data = $('#detailTable').datagrid('getSelected');
		    	$('#enterpriseEditForm').form('load', data);
		    }
		});
	}else{
		editEnterprise();
	}
}

var queryEnterprise = function (){
	$('#enterpriseInfo').datagrid('unselectAll');
	$('#enterpriseInfo').datagrid('reload', serializeObject($('#enterpriseQueryForm')) );
}

function clearSearchCondition(){
	$('#enterpriseQueryForm').form('clear');
	$.ajax({                                           
		type : 'POST',
		url:projectPath+'enterpriseInfoAction!getPager.action',
		data:$('#enterpriseQueryForm').serialize(),
		dataType : 'json',
		success : function(r){
			$('#enterpriseInfo').datagrid('load',r);
		}
	});	
}
/**
 * 导出
 * */
function exportCondition(){
	showEditDialog("enterpriseInfo",null,"enterpriseInfoCenter","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"企业管理"+getSystemTime());
}
/**
 * 导入excel
 * 将要导入的Excel文件列顺序与选择对应的列顺序一致
 * @return
 */
function importCondition(){
	$('#spo_clearBtn').linkbutton('disable');
	$('#spo_addBtn').linkbutton('disable');
	$('#spo_delectBtn').linkbutton('disable');
	$('#spo_editBtn').linkbutton('disable');
	$('#spo_seatchBtn').linkbutton('disable');
	$('#spo_exportBtn').linkbutton('disable');
	$('#spo_inportBtn').linkbutton('disable');
	$('#spo_printBtn').linkbutton('disable');
	$('#ok').linkbutton('enable');
	var tag=showEditDialog("enterpriseInfo",null,"enterpriseInfoCenter","开始导入","导入配置",1,null);
}
function validatePartsRows(){
	var rows=$('#enterpriseInfo').datagrid('getRows');
	if(rows!=null&&rows.length>0){
		for(var i=0;i<rows.length;i++){
			var enterpriseId=rows[i].enterpriseId;
			if(enterpriseId!=null&&enterpriseId!=''){
				alertMsg('没有要导入的数据，操作无效！','warning');
				$('#spo_clearBtn').linkbutton('enable');
				$('#spo_addBtn').linkbutton('enable');
				$('#spo_delectBtn').linkbutton('enable');
				$('#spo_editBtn').linkbutton('enable');
				$('#spo_seatchBtn').linkbutton('enable');
				$('#spo_exportBtn').linkbutton('enable');
				$('#spo_inportBtn').linkbutton('enable');
				$('#spo_printBtn').linkbutton('enable');
				$('#ok').linkbutton('disable');
				return false;
			}
		}
	}else{
		alertMsg('没有要导入的数据，操作无效！','warning');
		$('#spo_clearBtn').linkbutton('enable');
		$('#spo_addBtn').linkbutton('enable');
		$('#spo_delectBtn').linkbutton('enable');
		$('#spo_editBtn').linkbutton('enable');
		$('#spo_seatchBtn').linkbutton('enable');
		$('#spo_exportBtn').linkbutton('enable');
		$('#spo_inportBtn').linkbutton('enable');
		$('#spo_printBtn').linkbutton('enable');
		$('#ok').linkbutton('disable');
		return false;
	}
	return true;
}

function _importVisable(){
	if(!validatePartsRows()){
		return;
	}
	jsProbar();
	$.ajax({
		type : 'post',
		dataType : 'json',
		url: 'enterpriseInfoAction!modifyImportEnterprise.action',
		data : "type=enterpriseInfoCenter",
		success : function(r) {
			alertMsg(r);
			jsCloseProbar();
			if (r.success) {
				$('#enterpriseInfo').datagrid('load', serializeObject($('#enterpriseQueryForm')));
			}
		},
		error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
		   jsCloseProbar();
	   	}
	});
	$('#spo_clearBtn').linkbutton('enable');
	$('#spo_addBtn').linkbutton('enable');
	$('#spo_delectBtn').linkbutton('enable');
	$('#spo_editBtn').linkbutton('enable');
	$('#spo_seatchBtn').linkbutton('enable');
	$('#spo_exportBtn').linkbutton('enable');
	$('#spo_inportBtn').linkbutton('enable');
	$('#spo_printBtn').linkbutton('enable');
	$('#ok').linkbutton('disable');
}