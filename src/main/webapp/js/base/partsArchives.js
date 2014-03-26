var golableId=null;
$(function (){
	
	$partsArchivesDatagrid = $('#partsArchivesDatagrid');			
	$partsArchivesDatagrid.datagrid({
		singleSelect : true,
		url : 'basPartsArchivesAction!datagridPartsArchives.action',
		fit : true,
		fitColumns : true,
		pagination : true,
		pageSize : 50,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍候……",
		rownumbers : true,
			columns : [[{
				field : 'partsId',
				title : '配件编码',
				width : 80,
				sortable : true
			}, {
				field : 'partsId2',
				title : '编码二',
				width : 70,
				sortable : true
			}, {
				field : 'partsName',
				title : '配件名称',
				width : 80,
				sortable : true
			}, {
				field : 'partsSimpleId',
				title : '简码',
				width : 50,
				sortable : true
			}, {
				field : 'pbrdId',
				title : '配件品牌',
				width : 70,
				sortable : true,
				formatter: function(value,row,index){
					return row.pbrdName;
				}
			}, {
				field : 'pbrdName',
				title : '配件品牌名称',
				width : 70,
				hidden : true
			}, {
				field : 'ptypeId',
				title : '配件型号',
				width : 70,
				sortable : true,
				formatter: function(value,row,index){
					return row.ptypeName;
				}
			}, {
				field : 'ptypeName',
				title : '配件型号名称',
				width : 70,
				hidden : true
			}, {
				field : 'partsSpecs',
				title : '配件规格',
				width : 70,
				sortable : false
			},  {
				field : 'fitPtype',
				title : '适合车型',
				width : 150,
				sortable : true,
				formatter: function(value,row,index){
					return row.fitPtypeName;
				}
			}, {
				field : 'fitPtypeName',
				title : '适合车型名称',
				width : 45,
				hidden : true
			}, {
				field : 'posName',
				title : '配件部位',
				width : 45,
				sortable : true,
				formatter: function(value,row,index){
					return row.posName2;
				}
			}, {
				field : 'posName2',
				title : '配件部位名称',
				width : 45,
				hidden : true
			}, {
				field : 'punitName',
				title : '单位',
				width : 30,
				sortable : true,
				formatter: function(value,row,index){
					return row.punitName2;
				}
			}, {
				field : 'punitName2',
				title : '单位名称',
				width : 30,
				hidden : true
			}, {
				field : 'stateName',
				title : '产地',
				width : 30,
				sortable : true,
				formatter: function(value,row,index){
					return row.stateName2;
				}
			}, {
				field : 'stateName2',
				title : '产地名称',
				width : 30,
				hidden : true
			},{
				field : 'partsLibrary',
				title : '配件库位',
				width : 45,
				sortable : false
			},{
				field : 'partsProperty',
				title : '属性',
				width : 45,
				sortable : false
			},{
				field : 'partsAge',
				title : '年份',
				width : 45,
				sortable : false
			}, {
				field : 'partsRemark',
				title : '备注',
				width : 70,
				sortable : true,
				hidden:true
			},{
				field : 'partsNeedPoint',
				title : '现有库存量',
				width : 80,
				hidden : true
			},{
				field : 'partsFlag',
				title : '有效标识位',
				width : 80,
				hidden : true
			},{
				field : 'changePriceId',
				title : '调价编号',
				width : 80,
				hidden : true
			},{
				field : 'storeId',
				title : '仓别编号',
				width : 80,
				hidden : true
			},{
				field : 'partsRepairPrice',
				title : '维修价',
				width : 80,
				hidden : true
			},{
				field : 'partsSellPrice',
				title : '销售价',
				width : 80,
				hidden : true
			},{
				field : 'partsPointPrice',
				title : '网点价',
				width : 80,
				hidden : true
			},{
				field : 'partsSpecialPrice',
				title : '特别价',
				width : 80,
				hidden : true
			},{
				field : 'partsClaimantPrice',
				title : '索赔价',
				width : 80,
				hidden : true
			},{
				field : 'partsLatestTaxprice',
				title : '入库含税价',
				width : 80,
				hidden : true
			},{
				field : 'partsLatestNotaxprice',
				title : '入库未税价',
				width : 80,
				hidden : true
			},{
				field : 'partsNowCount',
				title : '库存量',
				width : 80,
				hidden : true
			},{
				field : 'stockUpper',
				title : '库存上限',
				width : 80,
				hidden : true
			},{
				field : 'stockLower',
				title : '库存下限',
				width : 80,
				hidden : true
			}]],
 		onAfterEdit : function(rowIndex, rowData, changes) {
 			onAfterEdit($mountingsBaseInfomation, 'basMountingsBaseInfomation_save' , 'basMountingsBaseInfomation_edit',rowIndex, rowData, changes);
 		},
 		onDblClickRow:function(rowIndex,rowData){
 			if(validateSelectedRow()){
 				showInfo(rowData);
 			}
 		}
	});
});
var showInfo=function(rowData){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '配件档案查看',
		width : 1000,
		height : 430,
		href : projectPath+'base/partsArchives/addPartsArchives.jsp?flag=false',
		buttons : [{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
        onClose : function (){
	    	$(this).dialog('destroy');
	    },
	    onLoad : function (){
	    	$('#partsArchivesAddForm').form('load', rowData);
	    	if(rowData.fitPtype!= null){
			  setComboboxValues('partsArchivesEditForm_fitPtype',rowData.fitPtype);
			}
	    }
	});
}
var add = function (){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '配件档案新增',
		width : 760,
		height : 330,
		href : projectPath+'base/partsArchives/addPartsArchives.jsp?flag=false&marker=no',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url: 'basPartsArchivesAction!isExistsPartsId.action',
				data : "partsId="+$('#partsArchives_add_partsId').val(),
				success : function(r) {
					if (r.success) {
						$('#partsArchives_add_partsId').val('');
						alertMsg(r);					
						return;
					}
						if($('#partsArchivesAddForm').form('validate')){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'basPartsArchivesAction!save.action',
							   data: $('#partsArchivesAddForm').serialize(),
							   success: function(r){
								 if(r.success){
									 d.dialog('close');
								 	 alertMsg(r);
									 $('#partsArchivesDatagrid').datagrid('load');
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
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   	}
			});
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
	    	//$("#partsArchives_add_partsId").unbind().blur(isExistsPartsId);
	    }

	});
	
}

var _remove = function (){
	var data = $partsArchivesDatagrid.datagrid('getSelected');
	if(data){
		if(!validateSelectedRow()){
			return;
		}
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'basPartsArchivesAction!isExist.action',
					   data: 'partsId=' + data.partsId,
					   success: function(r){
						 if(r.success){
							 $.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'basPartsArchivesAction!remove.action',
								   data: 'partsId=' + data.partsId,
								   success: function(r){
									 if(r.success){
										 $('#partsArchivesDatagrid').datagrid('load');
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
							 alertMsg('对不起，该配件已入库不能删除！', 'warning');
						 }
					   }
					});
			}
		});
	}else
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
}

var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	var data = $('#partsArchivesDatagrid').datagrid('getSelected');
	if(data){
		if(!validateSelectedRow()){
			return;
		}
		var url=projectPath+'base/partsArchives/addPartsArchives.jsp?flag=true';
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: 'basPartsArchivesAction!isExistsJoinCompany.action',
		   data: 'partsId='+data.partsId,
		   success: function(r){
			 if(r.success){
				url+='&marker=true';
				 showUpdateData(url);
			 }else{
			 	url+='&marker=false';
			 	showUpdateData(url);
			 }
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		});
		
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var showUpdateData=function(url){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '配件档案修改',
		width : 760,
		height : 330,
		href : url,
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url: 'basPartsArchivesAction!isExistsPartsIdEdit.action',
				data : "partsId="+$('#partsArchives_add_partsId').val()+"&paramPartId="+golableId,
				success : function(r) {
					if (r.success) {
						$('#partsArchives_add_partsId').val('');
						alertMsg(r);
						return;
					}
					if($('#partsArchivesAddForm').form('validate')){
						
						var value=$('#translationFlag').val();
						if(value=='true'){
							$.messager.confirm('优亿软件提示','价将调整所有仓的配件价格，<br/>且调整后不可恢复，确认调整?', function(r){
								if (r){
									updateData(d);
								}
							});
						}else{
							updateData(d);
						}
					}else{
						alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
					}
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   	}
			});
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
	    	var rowData = $('#partsArchivesDatagrid').datagrid('getSelected');
	    	$('#partsArchivesEditForm').form('load', rowData);
	    	
	    	if(rowData.fitPtype!= null){
			  setComboboxValues('partsArchivesEditForm_fitPtype',rowData.fitPtype);
			}
	    	golableId=$('#partsArchives_add_partsId').val();
	    	//$("#partsArchives_add_partsId").unbind().blur(isExistsPartsIdEdit);
	    }
	});
}

var updateData=function(id){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'basPartsArchivesAction!edit.action?paramPartId='+golableId,
	   data: $('#partsArchivesAddForm').serialize(),
	   success: function(r){
		 if(r.success){
			 id.dialog('close');
			 alertMsg(r);
			 $('#partsArchivesDatagrid').datagrid('reload');
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
var query = function (){
	if($('#partsArchivesQueryForm').form('validate')){
		$partsArchivesDatagrid.datagrid('load', serializeObject($('#partsArchivesQueryForm')));
	}else{
		alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
	}
}

var _clear = function (){
	$('#partsArchivesQueryForm').form('clear');
	$('#partsArchives_pbrdId').combobox('reload');
	$('#partsArchives_ptypeId').combobox('reload', 'basPartsArchivesAction!findPartsType.action');
	$('#partsArchives_posName').combobox('reload');
	$('#partsArchives_stateName').combobox('reload');
	$partsArchivesDatagrid.datagrid('load', serializeObject($('#partsArchivesQueryForm')));
}

var changeDialog;

var change = function (){
	var data = $('#partsArchivesDatagrid').datagrid('getSelected');
	if(data){
		if(!validateSelectedRow()){
			return;
		}
		changeDialog = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '配件编码变更',
			width : 380,
			height : 350,
			href : projectPath+'base/partsArchives/partsIdChange.jsp',
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $('#partsArchivesDatagrid').datagrid('getSelected');
		    	$('#partsArchives_changeBeforeForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}
//判断配件编码是否存在
/*	function isExistsPartsId(){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url: 'basPartsArchivesAction!isExistsPartsId.action',
			data : "partsId="+$('#partsArchives_add_partsId').val(),
			success : function(r) {
				if (r.success) {
					$('#partsArchives_add_partsId').val('');
					alertMsg(r);					
					return;
				}
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   	}
		});
	}
	//判断配件编码是否存在
	function isExistsPartsIdEdit(){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url: 'basPartsArchivesAction!isExistsPartsIdEdit.action',
			data : "partsId="+$('#partsArchives_add_partsId').val()+"&paramPartId="+golableId,
			success : function(r) {
				if (r.success) {
					$('#partsArchives_add_partsId').val('');
					alertMsg(r);
					return;
				}
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   	}
		});
	}*/

//导出
function _except(){
	showEditDialog("partsArchivesDatagrid",null,"partsArchivesDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"配件档案"+getSystemTime());
}

/**
 * 导入excel
 * 将要导入的Excel文件列顺序与选择对应的列顺序一致
 * @return
 */
function _import(){
	$('a.easyui-linkbutton').linkbutton('disable');
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel_import();">取消导入</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
	$('#ok').linkbutton('enable');
	showEditDialog("partsArchivesDatagrid",null,"partsArchivesDatagrid_center","开始导入","导入配置",1,null);
}

function cancel_import(){
	$('a.easyui-linkbutton').linkbutton('enable');
	$('#saveOrCancelBtn').empty();
}

function validatePartsRows(){
	var rows=$('#partsArchivesDatagrid').datagrid('getRows');
	if(rows!=null&&rows.length>0){
		for(var i=0;i<rows.length;i++){
			var pbrdId=rows[i].pbrdId;
			if(pbrdId!=null&&pbrdId!=''){
				alertMsg('没有要导入的数据，操作无效！','warning');
				$('#ok').linkbutton('disable');
				return false;
			}
		}
	}else{
		alertMsg('没有要导入的数据，操作无效！','warning');
		$('#ok').linkbutton('disable');
		return false;
	}
	return true;
}
function validateSelectedRow(){
	var row=$('#partsArchivesDatagrid').datagrid('getSelected');
	if(!(row.pbrdId!=null&&row.pbrdId!='')){
	    alertMsg('数据不完整，操作无效！','warning');
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
		url: 'basPartsArchivesAction!modifyImportPartsArchives.action',
		data : "type=partsArchivesDatagrid_center",
		success : function(r) {
			alertMsg(r);
			jsCloseProbar();
			cancel_import();
			if (r.success) {
				$partsArchivesDatagrid.datagrid('load', serializeObject($('#partsArchivesQueryForm')));
			}
		},
		error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
		   jsCloseProbar();
	   	}
	});
	$('#ok').linkbutton('disable');
}