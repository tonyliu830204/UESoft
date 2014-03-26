var addrow = undefined;
var editrow = undefined;
var addbar = undefined;
var editbar = undefined;
var editindex = undefined;
var staticDatagridId=null;
//将form表单序列化为对象
function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
}
//新增数据方法
function doAdd(id){
	if(editrow!=undefined){
		alertMsg('对不起,此系统只支持单行新增!', 'warning');
	}else if(editrow!=undefined){
		alertMsg('请先完成当前编辑!', 'warning');	
	}else{
		var rows = id.datagrid('getSelections');
		var index = id.datagrid('getRowIndex',rows[0]);//
		if(addrow == undefined){
			$('#btnadd').linkbutton('disable');
			$('#btnremove').linkbutton('disable');
			$('#btnedit').linkbutton('disable');
			$('#btnexport').linkbutton('disable');
			$('#btnsearch').linkbutton('disable');
			$('#btncancel').linkbutton('disable');
			id.datagrid('insertRow',{
				index: 0,	
				row: {}
			});
			id.datagrid('beginEdit',0);
			addrow = 0;
			editindex = 0;
			if(addbar==undefined){
				addButton(id);	
			}
		}else{
			id.datagrid('endEdit',addrow);
		}
   }
}
function save1(){
	if(staticDatagridId.datagrid('validateRow', editrow)){
		staticDatagridId.datagrid('endEdit', editrow);
	}	
	else{
		alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
	}
		
}
function save(){
	if(staticDatagridId.datagrid('validateRow', addrow)){
		staticDatagridId.datagrid('endEdit', addrow);
	}	
	else{
		alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
	}
		
}

//按钮区域增加 保存和取消按钮
function  addButton(id,i){
	staticDatagridId=id;
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save1();">保存</a>';
	}else{
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	}
	
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}

//删除方法
function doDelete(id,url1,url2){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作.', 'warning');
	}else if(editrow!=undefined){
		alertMsg('请先完成当前编辑!', 'warning');
	}else{
		var delrow = id.datagrid('getSelections');
		if(delrow.length>0){
			$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
				if(b){
					$.ajax({
						url : url1,
						data : delrow[0],
						success : function(data){
							var d = $.parseJSON(data);
							if(d.success){
								id.datagrid({url : url2});//刷新
								//清空选择
								id.datagrid('clearSelections');
							}else{
								id.datagrid('clearSelections');
								alertMsg('对不起，该数据已经被使用，不允许删除！', 'info');
							}
					    }
					});
				}
			});
		}else{
			$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
		}
    }
}
//修改方法
function doUpdate(id){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作!', 'warning');
	}else if(editrow!=undefined){
		alertMsg('对不起,此系统只支持单行编辑操作!', 'warning');
	}else{
		var rows = id.datagrid('getSelections');
		if(rows.length == 1){
			if(editbar==undefined){
				$('#btnadd').linkbutton('disable');
				$('#btnremove').linkbutton('disable');
				$('#btnedit').linkbutton('disable');
				$('#btnexport').linkbutton('disable');
				$('#btnsearch').linkbutton('disable');
				$('#btncancel').linkbutton('disable');
				
				}
			if(editrow != undefined){
				id.datagrid('endEdit',editrow);
			}
			if(editrow == undefined){
				var index = id.datagrid('getRowIndex',rows[0]);//
				id.datagrid('beginEdit',index);
				editrow = index;
				editindex = index;
				bindEnterInCloumn(id, index, 0);
				addButton(id,1);	
				id.datagrid('unselectAll');
			}
		}else{
			alertMsg('对不起，请先选中要修改的记录！', 'warning');
		}
	}
}
var cancel=function(){
	staticDatagridId.datagrid('unselectAll');
	staticDatagridId.datagrid('rejectChanges');
    $('#saveOrCancelBtn').empty();
	$('#btnadd').linkbutton('enable');
	$('#btnremove').linkbutton('enable');
	$('#btnedit').linkbutton('enable');
	$('#btnexport').linkbutton('enable');
	$('#btnsearch').linkbutton('enable');
	$('#btncancel').linkbutton('enable');
	editrow = undefined;
	addrow = undefined;
	editbar=undefined;
}
//条件查询的方法
function doFind(id,formid,url){
	var form =  formid.form();
	var formvalue = serializeObject(form);
		$.ajax({
		type : 'POST',
		url : url,
		data : formvalue,
		dataType : 'json',
		success : function(r){
			//var d = $.parseJSON(r);
			//if(r.success){
				id.datagrid({url : url});
			//}
		}
	   });
}
	
//结束编辑提交方法
function endEdit(id,url1,url2,url3,rowIndex, rowData, changes){
	alert("");
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
		}
	if(updated.length>0){
		url = url2;
	}else{
		cancel();
	}
	alert(url);
	if(inserted.length == 0 && updated.length==0){
		editrow = undefined;
		id.datagrid('removeToolbarItem','保存');
		id.datagrid('removeToolbarItem','取消');
	}
	//ajax提交新增内容
	$.ajax({
		type : 'POST',
		url : url,
		data : rowData,
		dataType : 'json',
		success : function (d){
			if(d.success){
				id.datagrid('acceptChanges');
				id.datagrid('clearSelections');
				id.datagrid('removeToolbarItem','保存');
				id.datagrid('removeToolbarItem','取消');
				if(inserted.length>0){
					id.datagrid('load');
				}
				if(updated.length>0){
					id.datagrid('reload');
				}
				addrow = undefined;
				addbar = undefined;
				editrow = undefined;
				cancel(id);
			}else{
				alertMsg(d.msg, 'info');
				id.datagrid('beginEdit', editindex);
			}
		}
	});
}//onAfterEdit结束   datagrid_worksort_type_id
	
//结束编辑提交方法
function endEdit1(id,url1,url2,url3,rowIndex, rowData, changes){
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
		}
	if(updated.length>0){
		url = url2;
	}else{
		cancel();
	}
	
	if(inserted.length == 0 && updated.length==0){
		editrow = undefined;
		id.datagrid('removeToolbarItem','保存');
		id.datagrid('removeToolbarItem','取消');
	}
	//ajax提交新增内容
	$.ajax({
		type : 'POST',
		url : url,
		data : "jsonData="+JSON.stringify(rowData)+"&systemId="+$('#systemId').val(),
		dataType : 'json',
		success : function (d){
			if(d.success){
				id.datagrid('acceptChanges');
				id.datagrid('clearSelections');
				id.datagrid('removeToolbarItem','保存');
				id.datagrid('removeToolbarItem','取消');
				if(inserted.length>0){
					id.datagrid('load');
				}
				if(updated.length>0){
					id.datagrid('reload');
				}
				addrow = undefined;
				addbar = undefined;
				editrow = undefined;
				cancel(id);
			}else{
				alertMsg(d.msg, 'info');
				id.datagrid('beginEdit', editindex);
			}
		}
	});
}//onAfterEdit结束   datagrid_worksort_type_id
$(function(){

	//工时分类
	$('#datagrid_worksort_type_id').datagrid({
		url : projectPath+'basWorkhourSorAction!doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'whSortId',
		sortOrder:'asc',
		idField : 'whSortId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'whSortId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'whSortName',
			title : '工时分类',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : '工时分类为必填项'
				}
			}
		} ,{
			field : 'whMemo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,200]\']'
				}
			}
		} 
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#datagrid_worksort_type_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#datagrid_worksort_type_id'),projectPath+'basWorkhourSorAction!doDelete.action',projectPath+'basWorkhourSorAction!doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#datagrid_worksort_type_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("datagrid_worksort_center","工时分类信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#datagrid_worksort_type_id'),projectPath+'basWorkhourSorAction!doAdd.action',projectPath+'basWorkhourSorAction!doUpdate.action',projectPath+'basWorkhourSorAction!doFindAll.action',rowIndex, rowData, changes);
		
		}//onAfterEdit结束
	});
	//车间部门
	$('#Workshop_department_center_id').datagrid({
		url : projectPath+'basRepairGroupAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'repgrpId',
		sortOrder:'asc',
		idField : 'repgrpId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'repgrpId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'repgrpName',
			title : '车间部门',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '车间部门为必填项'
				}
			}
		} ,{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,50]\']'
				}
			}
		} 
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Workshop_department_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Workshop_department_center_id'),projectPath+'basRepairGroupAction_doDelete.action',projectPath+'basRepairGroupAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Workshop_department_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Workshop_department_center","车间部门信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Workshop_department_center_id'),projectPath+'basRepairGroupAction_doAdd.action',projectPath+'basRepairGroupAction_doUpdate.action',projectPath+'basRepairGroupAction_doFindAll.action',rowIndex, rowData, changes);
		
		}//onAfterEdit结束
	});
	//标准项目工时
	$('#Standard_project_man_hours_center_id').datagrid({
		url : projectPath+'frtRepairItemAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'repitemId',
		sortOrder:'asc',
		idField : 'repitemId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'repitemId',
			title : '项目编号',
			width : 35,
			sortable : true

		} ,{
			field : 'repitemName',
			title : '项目名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,100]\']',
					missingMessage : '项目名称为必填项'
				}
			}
		} ,{
			field : 'repitemCode',
			title : '项目简码',
			width : 50,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : false,
					disabled:true,
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		},{
			field : 'repitemSeries',
			title : '工时分类',
			width : 45,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'frtRepairItemAction!getBasWorkhourSort.action',
					valueField:'id',   
				    textField:'text',
				    mode:'remote',
				  //  required : true,
				    missingMessage : '工时分类为必填项！',
				    onSelect : function (record){
			        //    alert(record.text);
//				    	var row = $('#Standard_project_man_hours_center_id').datagrid('getSelected');
//			    		var index = $('#Standard_project_man_hours_center_id').datagrid('getRowIndex', row);
//			    		alert(index);
//			    		var ed = $('#Standard_project_man_hours_center_id').datagrid('getEditor', {index:index,field:'repitemSeriesName'});
//			    		ed.target.val(record.text);
				    }
				}
			},
			formatter : function (value,row,index){
				return row.repitemSeriesName;
			}
		},{
			field : 'repitemSeriesName',
			title : '工时分类',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'repitemAmount',
			title : '工时费',
			width : 35,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					//required : true,
					min:0,
					precision:2,
					validType:'length[0,12]',
					missingMessage : '工时费为必填项'
				}
			}
		} ,{
			field : 'repitemTime',
			title : '维修工时',
			width : 35,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					//required : true,
					min:0,
					precision:2,
					validType:'length[0,9]',
					missingMessage : '维修工时为必填项'
				}
			}
		} ,{
			field : 'internalTime',
			title : '内部工时',
			width : 35,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					//required : true,
					min:0,
					precision:2,
					validType:'length[0,9]',
					missingMessage : '内部工时为必填项'
				}
			}
		},{
			field : 'claimTime',
			title : '索赔工时',
			width : 35,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					//required : true,
					min:0,
					precision:2,
					validType:'length[0,9]',
					missingMessage : '索赔工时为必填项'
				}
			}
		},{
			field : 'fitCar',
			title : '适合车型',
			width : 80,
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'basPartsArchivesAction!findAllCarType.action',
					mode:'remote',
					//required : true,
					editable : true,
					multiple : true,
					separator:',',
					valueField:'id',  
					validType:'length[0,100]',
				    textField:'text'
				}
			},
			formatter : function (value,row,index){
				return row.fitCarName;
			}
		} ,{
			field : 'repitemRemark',
			title : '备注',
			width : 50,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		}  
		]]
	});
	
	/************************************************************************************************/
	//维修类别
	$('#Maintenance_category_center_id').datagrid({
		url : projectPath+'reptypeAction!doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'reptId',
		sortOrder:'asc',
		idField : 'reptId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'reptId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'reptName',
			title : '维修类别',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '维修类别为必填项'
				}
			}
		} ,{
			field : 'workCreditsRate',
			title : '工时积分比例',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min:0,
					max:100,
					precision:2,
					missingMessage : '工时积分比例为必填项'
				}
			}
		} ,{
			field : 'partCreditsRate',
			title : '配件积分比例',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min:0,
					max:100,
					precision:2,
					missingMessage : '配件积分比例为必填项'
				}
			}
		} ,{
			field : 'sumCreditsRate',
			title : '合计积分比例',
			width : 100,
			sortable : true,
			editor : {
			type : 'numberbox',
				options : {
					required : true,
					min:0,
					max:100,
					precision:2,
					missingMessage : '合计积分比例为必填项'
				}
			}
		} ,{
			field : 'reptClass',
			title : '分类(选中为保养，否则为维修)',
			width : 100,
			sortable : true,
			editor : {
			type : 'checkbox',
				options:{
					on:'1',
					off:'0'
				}
			},
			formatter : function (value,row,index){
				if(value==0){
					return "维修";
				}else if(value==1){					
					return "保养";
				}
			}
		} ,{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,200]\']'
				}
			}
		} 
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Maintenance_category_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Maintenance_category_center_id'),projectPath+'reptypeAction!doDelete.action',projectPath+'reptypeAction!doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Maintenance_category_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Maintenance_category_idCenter","维修类别信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Maintenance_category_center_id'),projectPath+'reptypeAction!doAdd.action',projectPath+'reptypeAction!doUpdate.action',projectPath+'reptypeAction!doFindAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	
	
	});
	//索赔性质
	$('#Claim_nature_center_id').datagrid({
		url : projectPath+'basClaimsTypeAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'claimsId',
		sortOrder:'asc',
		idField : 'claimsId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'claimsId',
			title : '编号',
			width : 40,
			hidden : true
		},{
			field : 'claimsName',
			title : '索赔性质',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,30]\']',
					missingMessage : '索赔性质为必填项'
				}
			}
		},{
			field : 'claimsFlg',
			title : '是否转索赔(选中为是，反则为否)',
			width : 100,
			sortable : true,
			editor : {
			type : 'checkbox',
				options:{
					on:'1',
					off:'0'
				}
			},
			formatter : function (value,row,index){
				if(value==0){
					return "否";
				}else if(value==1){					
					return "是";
				}
			}
		},{
			field : 'claimsToMoney',
			title : '是否收费(选中为是，反则为否)',
			width : 100,
			sortable : true,
			editor : {
			type : 'checkbox',
				options:{
					on:'1',
					off:'0'
				}
			},
			formatter : function (value,row,index){
				if(value==0){
					return "否";
				}else if(value==1){					
					return "是";
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,200]\']'
				}
			}
		}  
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Claim_nature_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Claim_nature_center_id'),projectPath+'basClaimsTypeAction_doDelete.action',projectPath+'basClaimsTypeAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Claim_nature_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Claim_nature_center","索赔性质信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Claim_nature_center_id'),projectPath+'basClaimsTypeAction_doAdd.action',projectPath+'basClaimsTypeAction_doUpdate.action',projectPath+'basClaimsTypeAction_doFindAll.action',rowIndex, rowData, changes);
		
		}//onAfterEdit结束
	});
	//维修工位
	$('#Maintenance_man-bit_center_id').datagrid({
		url : projectPath+'basRepairWorkAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'repwkId',
		sortOrder:'asc',
		idField : 'repwkId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'repwkId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'repwkName',
			title : '维修工位',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '维修工位为必填项'
				}
			}
		} ,{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : false,
					validType:'multiple[\'characterDigit\',\'length[0,200]\']',
					missingMessage : '维修工位为必填项'
				}
			}
		} 
		]],
	/*	toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Maintenance_man-bit_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Maintenance_man-bit_center_id'),projectPath+'basRepairWorkAction_doDelete.action',projectPath+'basRepairWorkAction_doFindAll.action');
		}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Maintenance_man-bit_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Maintenance_man-bit_center","维修工位信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Maintenance_man-bit_center_id'),projectPath+'basRepairWorkAction_doAdd.action',projectPath+'basRepairWorkAction_doUpdate.action',projectPath+'basRepairWorkAction_doFindAll.action',rowIndex, rowData, changes);
	
		}//onAfterEdit结束
	
	});
	//收费性质
	$('#Charges_nature_center_id').datagrid({
		url : projectPath+'basRepairTypeAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		sortName:'reptypId',
		sortOrder:'asc',
		idField : 'reptypId',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'reptypId',
			title : '编号',
			width : 40,
			hidden : true
		} ,{
			field : 'reptypName',
			title : '收费性质',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '收费性质为必填项'
				}
			}
		} ,{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,200]\']'
				}
			}
		} 
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Charges_nature_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Charges_nature_center_id'),projectPath+'basRepairTypeAction_doDelete.action',projectPath+'basRepairTypeAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Charges_nature_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			   _except("Charges_nature_center","收费性质信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Charges_nature_center_id'),projectPath+'basRepairTypeAction_doAdd.action',projectPath+'basRepairTypeAction_doUpdate.action',projectPath+'basRepairTypeAction_doFindAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
	
	//部门编辑
	$('#edit_Department_set_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 210,
		href : projectPath+'base/other_set/Edit_Department_set.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Department_set_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Department_set_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//部门设置
	$('#department_set_id').datagrid({
		url : projectPath+'basDeptAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		sortName:'deptId',
		sortOrder:'asc',
		idField : 'deptId',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'deptId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'deptName',
			title : '部门名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,20]',
					missingMessage : '部门名称为必填项'
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'length[0,200]'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#department_set_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#department_set_id'),projectPath+'basDeptAction_doDelete.action',projectPath+'basDeptAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#department_set_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("department_set_idCenter","部门设置信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#department_set_id'),projectPath+'basDeptAction_doAdd.action',projectPath+'basDeptAction_doUpdate.action',projectPath+'basDeptAction_doFindAll.action',rowIndex, rowData, changes);
		}
	});
	
	//支付方式编辑
	$('#edit_payoff_away_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 210,
		href : projectPath+'base/other_set/Edit_payoff_away.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_payoff_away_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_payoff_away_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//工作属性
	$('#bas_Job_Property_id').datagrid({
		url : projectPath+'basJobPropertyAction!findAll.action',
		queryParams: {systemId: $('#systemId').val()},
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : '',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'jobProId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'jobProName',
			title : '工作属性',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,20]',
					missingMessage : '工作属性为必填项'
				}
			}
		},{
			field : 'jobProNote',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'length[0,200]'
				}
			}
		}
		]],
		onAfterEdit : function(rowIndex, rowData, changes){
			endEdit1($('#bas_Job_Property_id'),projectPath+'basJobPropertyAction!add.action',projectPath+'basJobPropertyAction!update.action',projectPath+'basJobPropertyAction!findAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
	//保险（汽厂）属性
	$('#carCompanyProperties_id').datagrid({
		url : projectPath+'basRelationCampanyAction!findAllCarCompanyProperties.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'attrId',
		sortOrder:'asc',
		idField : 'attrId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'attrId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'attrName',
			title : '保险（汽厂）属性',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '保险（汽厂）属性为必填项'
				}
			}
		},{
			field : 'remark',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'length[0,200]'
				}
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#carCompanyProperties_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#carCompanyProperties_id'),projectPath+'basRelationCampanyAction!removeCarCompanyProperties.action',projectPath+'basRelationCampanyAction!findAllCarCompanyProperties.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#carCompanyProperties_id'));
			}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#carCompanyProperties_id'),projectPath+'basRelationCampanyAction!saveCarCompanyProperties.action',projectPath+'basRelationCampanyAction!editCarCompanyProperties.action',projectPath+'basRelationCampanyAction!findAllCarCompanyProperties.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
	
	$('#add_project_id').datagrid({
		url : projectPath+'frtAddItmeAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'itemId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'itemId',
			title : '项目编号',
			width : 35,
			hidden : true
		},{
			field : 'itemName',
			title : '项目名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : '项目名称为必填项'
				}
			}
		},{
			field : 'itemCost',
			title : '项目成本',
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'monery',
					missingMessage : '项目成本为必填项!正确格式为小数点前1-9位数字,小数点后1-2位数字,如10.00'
				}
			}
		},{
			field : 'itemMoney',
			title : '项目金额',
			width : 60,
			editor : {
			type : 'validatebox',
				options : {
					required : true,
					validType:'monery',
					missingMessage : '项目金额为必填项!正确格式为小数点前1-9位数字,小数点后1-2位数字,如10.00'
				}
			}
		},{
			field : 'remark',
			title : '备注',
			width : 50,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,50]\']'
				}
			}
		}  
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
    			$('#btnadd').linkbutton('disable');
    			$('#btnremove').linkbutton('disable');
    			$('#btnedit').linkbutton('disable');
    			$('#btnexport').linkbutton('disable');
    			$('#btnsearch').linkbutton('disable');
    			$('#btncancel').linkbutton('disable');
    			doAdd($('#add_project_id'));
			}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			    doDelete($('#add_project_id'),projectPath+'frtAddItmeAction!delete.action',projectPath+'frtAddItmeAction!findAll.action');
		    }
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
    			$('#btnadd').linkbutton('disable');
    			$('#btnremove').linkbutton('disable');
    			$('#btnedit').linkbutton('disable');
    			$('#btnexport').linkbutton('disable');
    			$('#btnsearch').linkbutton('disable');
    			$('#btncancel').linkbutton('disable');
    			doUpdate($('#add_project_id'));
		    }
		},{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
				$('#add_project_id').datagrid('load',serializeObject($('#add_project_form_id')));
			}
		},{
			id : 'btncancel',
			text : '清空',
			iconCls : 'icon-cancel',
			handler : function (){
				$('#add_project_form_id').form('clear');
			}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("add_project_center","加装项目");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			endEdit($('#add_project_id'),projectPath+'frtAddItmeAction!doAdd.action',projectPath+'frtAddItmeAction!doUpdate.action',projectPath+'frtAddItmeAction!findAll.action',rowIndex, rowData, changes);
	    }
	});
});
	
/**
 * 
 * 导出excel
 * @return
 */
function _except(div,name){
	exportEsuyUIExcelFile(div,null,name+getSystemTime());
}
/********************************************2013/6/25基础数据操作************************************************/
/**
 * 增加行数据方法
 * */
var standarsId=null;
var standarUrl=null
function commonBaseAdd(id,row,field,begin,end,url){
	standarsId=id;
	standarUrl=url;
	if($('#save').length == 0 && $('#cancel').length == 0){
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="commonBaseSave();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="commonCancelASItemHours()">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
		id.datagrid('insertRow',{
			index : 0,
			row : row
		});
		id.datagrid('beginEdit', 0);
		bindEnterInCloumn(id, 0, 0);
		if(arguments.length == 6){
			editorBindEvent(0, id, begin, end);
		}
		editRow = 0; // 设置编辑行的索引(不可编辑行)
		/*if(arguments.length == 3){
			alert('arguments.length=4');
		}else{
			commonBaseSave();
		}*/
	}
}


function commonBaseAdd1(id,row,field,begin,end,url){
	standarsId=id;
	standarUrl=url;
	if($('#save').length == 0 && $('#cancel').length == 0){
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="commonBaseSave();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="commonCancelASItemHours()">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
		id.datagrid('insertRow',{
			index : 0,
			row : row
		});
		id.datagrid('beginEdit', 0);
		 var ed = $('#Standard_project_man_hours_center_id').datagrid('getEditors', 0);
		 ed[4].target.numberbox('setValue',0);
		 ed[5].target.numberbox('setValue',0);
		 ed[6].target.numberbox('setValue',0);
		 ed[7].target.numberbox('setValue',0);
		
		bindEnterInCloumn(id, 0, 0);
		if(arguments.length == 6){
			editorBindEvent(0, id, begin, end);
		}
		editRow = 0; // 设置编辑行的索引(不可编辑行)
		/*if(arguments.length == 3){
			alert('arguments.length=4');
		}else{
			commonBaseSave();
		}*/
	}
}
//保存单个datagrid的编辑行
function commonBaseSave(){
			if(standarsId.datagrid('validateRow', editRow)){
				standarsId.datagrid('endEdit', editRow);
				var data=updateAll(standarsId);
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : standarUrl,
					data : data,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							//removeToolBar(id);
							commonCancelASItemHours(standarsId);
							standarsId.datagrid('load');
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
				editRow = undefined;
			}else{
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			}
}
var commonCancelASItemHours=function(){
	standarsId.datagrid('unselectAll');
	standarsId.datagrid('rejectChanges');
	  $('#saveOrCancelBtn').empty();
	$('#btnadd').linkbutton('enable');
	$('#btnremove').linkbutton('enable');
	$('#btnedit').linkbutton('enable');
	$('#btnexport').linkbutton('enable');
	$('#btnsearch').linkbutton('enable');
	$('#btncancel').linkbutton('enable');
	editrow = undefined;
	addrow = undefined;
	editbar=undefined;
}
/**
 * 修改行数据方法
 * */
function commonBaseEdit(id, field, begin, end,url){
	standarsId=id;
	standarUrl=url;
	var $rowData = id.datagrid('getSelected');
	if ($rowData) {
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="commonBaseSave();">保存</a>';
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="commonCancelASItemHours()">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		var rowIndex = id.datagrid('getRowIndex', $rowData);
		editRow = rowIndex;
		id.datagrid('beginEdit', rowIndex);
		bindEnterInCloumn(id, rowIndex, 0);
		if(arguments.length == 5){
			editorBindEvent(rowIndex, id, begin, end);
		}
		/*if(arguments.length == 4){
			alert('arguments.length=4');
		}else{
			commonBaseSave();
		}*/
	} else {
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
/**
 * 删除行数据方法
 * */
function commonBaseDelete(id,url){
	var rowData = id.datagrid('getSelected');
	if (rowData) {
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if(r){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : url,
					data : rowData,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							removeToolBar(id);
							id.datagrid('load');
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
	} else {
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
/**
 *保存变化行
 * */
var updateAll = function (){
	if(standarsId.datagrid('getChanges').length) {
		var changes = standarsId.datagrid('getChanges');
		var effectRow = new Object();
		if(changes){
			effectRow['updateData'] = JSON.stringify(changes);
		}
		return effectRow;
	};
}
function addStandard(){
	$('#btnadd').linkbutton('disable');
	$('#btnremove').linkbutton('disable');
	$('#btnedit').linkbutton('disable');
	$('#btnexport').linkbutton('disable');
	$('#btnsearch').linkbutton('disable');
	$('#btncancel').linkbutton('disable');
		//$('#window_Standard_project_hours_id').dialog('open');
		//add($('#Standard_project_man_hours_center_id'), {}, 'repitemName', 0, 1);
	
		commonBaseAdd1($('#Standard_project_man_hours_center_id'), {}, 'repitemName', 0, 1,projectPath+'frtRepairItemAction!save.action');
}

function addStandard_11(){
	$('#btnadd').linkbutton('disable');
	$('#btnremove').linkbutton('disable');
	$('#btnedit').linkbutton('disable');
	$('#btnexport').linkbutton('disable');
	$('#btnsearch').linkbutton('disable');
	$('#btncancel').linkbutton('disable');
	commonBaseAdd1($('#Standard_project_man_hours_center_id'), {}, 'repitemName', 0, 1,projectPath+'frtRepairItemAction!save.action');
}

function delStandard(){
	//doDelete($('#Standard_project_man_hours_center_id'),projectPath+'frtRepairItemAction_doDelete.action',projectPath+'frtRepairItemAction_doFindAll.action');
	commonBaseDelete($('#Standard_project_man_hours_center_id'),projectPath+'frtRepairItemAction!delete.action');
}
function updateStandard(){
	//datagridLoadToForm($('#window_update_Standard_project_hours_id'),$('#form_update_Standard_project_hours_id'),$('#Standard_project_man-hours_center_id'));
//	edit($('#Standard_project_man_hours_center_id'), projectPath+'frtRepairItemAction_doUpdate.action', projectPath+'frtRepairItemAction_doFindAll.action', 'repitemName', 0, 1);
	commonBaseEdit($('#Standard_project_man_hours_center_id'),'repitemName', 0, 1,projectPath+'frtRepairItemAction!update.action');
}
function queryStandard(){
	$('#Standard_project_man_hours_center_id').datagrid('load',serializeObject($('#Standard_project_form_id')));
}
function clearStandard(){
	$('#Standard_project_form_id').form('clear');
	$('#Standard_project_man_hours_center_id').datagrid('load',serializeObject($('#Standard_project_form_id')));
}