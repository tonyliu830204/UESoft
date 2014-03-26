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
	
	var addrow = undefined;
	var editrow = undefined;
	var addbar = undefined;
	var editbar = undefined;
	var staticDatagridId=null;
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
					id.datagrid('insertRow',{
						index: 0,	
						row: {
						}
					});
					id.datagrid('beginEdit',0);
					addrow = 0;
					editindex = 0;
					bindEnterInCloumn(id, 0, 0);
				if(addbar==undefined){
					addButton(id);	
			}else{
				id.datagrid('endEdit',addrow);
			}
		}
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
						var r = $.parseJSON(data);
								if(r.success){
									//清空选择
									id.datagrid('clearSelections');
									id.datagrid({url : url2});//刷新
								}else{
									alertMsg("对不起，该数据已经被使用，不允许删除！",'info');
								}
						
							}
						});
						id.datagrid({
						url : url2
					});
				}
			});
		}else{
			alertMsg('对不起，请先选中要删除的记录！','warning');
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
	var cancel=function(){
		staticDatagridId.datagrid('unselectAll');
		staticDatagridId.datagrid('rejectChanges');
		 $('#saveOrCancelBtn').empty();
		$('#btnadd').linkbutton('enable');
		$('#btnremove').linkbutton('enable');
		$('#btnedit').linkbutton('enable');
		$('#btnexport').linkbutton('enable');
		editrow = undefined;
		addrow = undefined;
		editbar=undefined;
	}
	//查询的方法
	function doFind(id,formid,url){
		var form =  $(formid).form();
		var formvalue = serializeObject(form);
			$.ajax({
			type : 'POST',
			url : url,
			data : formvalue,
			dataType : 'json',
			success : function(r){
				if(r){
					id.datagrid({
						url : url
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
		}
	
	//结束编辑提交方法
	function endEdit(id,url1,url2,url3,rowIndex, rowData, changes){
		var inserted = id.datagrid('getChanges','inserted');
		var updated = id.datagrid('getChanges','updated');
		var url = '';
		if(inserted.length>0){
			url = url1;
			}
		if(updated.length>0){
			url = url2;
		}else{
			cancel()
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
		}//onAfterEdit结束
	
	
	//修改区域 edit_region_set_dialog_i
	$modified_region_set_dialog_id = $('#modified_region_set_dialog_id');
	$modified_region_set_dialog_id.dialog({
		modal:true,
		closed : true,
		title : '修改区域',
		closable : true,
		width : 380,
		height : 250,
		href : projectPath+'base/customer_nature/Edit_region_set.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$modified_region_set_dialog_id.dialog({
				closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$modified_region_set_dialog_id.dialog({
					closed : true
				});
			}
		}]
	});


	//修改技协费分类
	$edit_cost_type_dialog_id = $('#edit_cost_type_dialog_id');
	$edit_cost_type_dialog_id.dialog({
		modal:true,
		closed : true,
		title : '修改技协费分类',
		closable : true,
		width : 380,
		height : 250,
		href : projectPath+'base/customer_nature/Edit_cost_type.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$edit_cost_type_dialog_id.dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$edit_cost_type_dialog_id.dialog({
					closed : true
				});
			}
		}]
	});
$(function(){
	//客户投诉分类
	$('#Customer_complaints_classification_center_id').datagrid({
		url : projectPath+'basCustomerComplaintsAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'complaintsId',
		sortName:'complaintsId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		singleSelect : true,
		columns : [ [ {
			field : 'complaintsId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'complaintsType',
			title : '客户投诉分类',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '客户投诉分类为必填项'
				}
			}
		},{
			field : 'complaintsContent',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Customer_complaints_classification_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	doDelete($('#Customer_complaints_classification_center_id'),projectPath+'basCustomerComplaintsAction_doDelete.action',projectPath+'basCustomerComplaintsAction_doFindAll.action');
		   	}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Customer_complaints_classification_center_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			     _except("Customer_center","客户投诉分类信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Customer_complaints_classification_center_id'),projectPath+'basCustomerComplaintsAction_doAdd.action',projectPath+'basCustomerComplaintsAction_doUpdate.action',projectPath+'basCustomerComplaintsAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	
	});
	//回访内容
	$('#Visit_content_center_id').datagrid({
		url : projectPath+'basVistContentAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		singleSelect : true,
		columns : [ [ {
			field : 'vistId',
			title : '编码',
			width : 100,
			sortable : true
			
		} ,{
			field : 'vistName',
			title : '名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : '名称为必填项'
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
		}
		]],

		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Visit_content_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Visit_content_center_id'),projectPath+'basVistContentAction_doDelete.action',projectPath+'basVistContentAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Visit_content_center_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Visit_content_center_id'),projectPath+'basVistContentAction_doAdd.action',projectPath+'basVistContentAction_doUpdate.action',projectPath+'basVistContentAction_doFindAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
	
	//技协费分类
	$('#Cost_type_id').datagrid({
		url : projectPath+'basCountassociationTypeAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'typeId',
		sortName:'typeId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		singleSelect : true,
		columns : [ [ {
			field : 'typeId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'typeName',
			title : '技协费分类',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,20]\']',
					missingMessage : '技协费分类为必填项'
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
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Cost_type_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Cost_type_id'),projectPath+'basCountassociationTypeAction_doDelete.action',projectPath+'basCountassociationTypeAction_doFindAll.action');
			}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Cost_type_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Cost_type_idCenter","技协费分类信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#Cost_type_id'),projectPath+'basCountassociationTypeAction_doAdd.action',projectPath+'basCountassociationTypeAction_doUpdate.action',projectPath+'basCountassociationTypeAction_doFindAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});

	//客户分类
	$('#customer_fenlei_id').datagrid({
		url : projectPath+'basCustomGroupAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'cstgId',
		sortName:'cstgId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'cstgId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'cstgName',
			title : '客户分类',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : '客户分类为必填项'
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
			doAdd($('#customer_fenlei_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#customer_fenlei_id'),projectPath+'basCustomGroupAction_doDelete.action',projectPath+'basCustomGroupAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#customer_fenlei_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			     _except("customer_fenlei_idCenter","客户分类信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#customer_fenlei_id'),projectPath+'basCustomGroupAction_doAdd.action',projectPath+'basCustomGroupAction_doUpdate.action',projectPath+'basCustomGroupAction_doFindAll.action',rowIndex, rowData, changes);
	
		}//onAfterEdit结束
	});
	
	//客户类型
	$('#customer_classification_id').datagrid({
		url : projectPath+'basCustomTypeAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'cstId',
		sortName:'cstId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'cstId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'cstName',
			title : '客户类型',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : '客户类型为必填项'
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
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#customer_classification_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#customer_classification_id'),projectPath+'basCustomTypeAction_doDelete.action',projectPath+'basCustomTypeAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#customer_classification_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("customer_classification_idCenter","客户类型信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#customer_classification_id'),projectPath+'basCustomTypeAction_doAdd.action',projectPath+'basCustomTypeAction_doUpdate.action',projectPath+'basCustomTypeAction_doFindAll.action',rowIndex, rowData, changes);
	
		}//onAfterEdit结束
	});
	
	//客户性质
	$('#customer_nature_id').datagrid({
		url : projectPath+'basCustomerNatureAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'natureId',
		sortName:'natureId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'natureId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'natureName',
			title : '客户性质',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,30]\']',
					missingMessage : '客户性质为必填项'
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
					validType:'multiple[\'characterDigit\',\'length[0,100]\']'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#customer_nature_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#customer_nature_id'),projectPath+'basCustomerNatureAction_doDelete.action',projectPath+'basCustomerNatureAction_doFindAll.action');
			}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#customer_nature_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			     _except("customer_nature_idCenter","客户性质信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#customer_nature_id'),projectPath+'basCustomerNatureAction_doAdd.action',projectPath+'basCustomerNatureAction_doUpdate.action',projectPath+'basCustomerNatureAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
	
	//地区设定
	$('#region_set_id').datagrid({
		url : projectPath+'basCustomAreaAction_doFindAll.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'pareaId',
		sortName:'pareaId',
		sortOrder:'asc',
		singleSelect : true,
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'pareaId',
			title : '编号',
			width : 40,
			hidden : true
		} ,{
			field : 'pareaName',
			title : '区域名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : '区域名称为必填项'
				}
			}
		},{
			field : 'pareaZip',
			title : '邮政编码',
			width : 100,
			sortable : true,
			editor : {
				type: 'validatebox',
				options : {
				//	required : true,
					validType: 'zip',
					missingMessage : '邮政编码为6位数字'
				}
			}
		}
		]],

		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#region_set_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#region_set_id'),projectPath+'basCustomAreaAction_doDelete.action',projectPath+'basCustomAreaAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#region_set_id'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			   _except("region_set_idCenter","地区设定信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#region_set_id'),projectPath+'basCustomAreaAction_doAdd.action',projectPath+'basCustomAreaAction_doUpdate.action',projectPath+'basCustomAreaAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
	
	//客户回访项目
	$('#customer_visit_project_id').datagrid({
		url : projectPath+'fbkDcserveyNameAction!doFindAll.action',
		pagination : true,
		fitColumns : true,
		fit : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'dcNameId',
		sortName:'dcNameId',
		sortOrder:'asc',
		singleSelect : true,
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'dcNameId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'serveyName',
			title : '客户回访项目',
			width : 300,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,50]\']',
					missingMessage : "客户回访项目为必填项"
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 200,
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
			doAdd($('#customer_visit_project_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#customer_visit_project_id'),projectPath+'fbkDcserveyNameAction!doDelete.action',projectPath+'fbkDcserveyNameAction!doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#customer_visit_project_id'));
		}
		},{

			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			   _except("customer_visit_project_idCenter","客户回访项目信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
		endEdit($('#customer_visit_project_id'),projectPath+'fbkDcserveyNameAction!doAdd.action',projectPath+'fbkDcserveyNameAction!doUpdate.action',projectPath+'fbkDcserveyNameAction!doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
});

//修改技协费分类方法
function editCostType(){
		var $rowValue = $('#Cost_type_id').datagrid('getSelections')[0];
	if($rowValue){
		$('#edit_cost_type_dialog_id').dialog({
		closed : false
	});
	}else{
		$.messager.show({
  			title : '系统提示',
			msg : '请选择你要修改的行!',
			timeout : 2000
  		});
  	}
	}
//修改区域方法
function modifyPlace(){
	var regionRowValue = $('#region_set_id').datagrid('getSelections')[0];
	if(regionRowValue){
		$('#modified_region_set_dialog_id').dialog({
			closed : false
		});
	}else{
		$.messager.show({
			title : '系统提示',
			msg : '请选择要编辑的行',
			timeout : 2000
		});
	}
}
/**
 * 
 * 导出excel
 * @return
 */
function _except(div,name){
	exportEsuyUIExcelFile(div,null,name+getSystemTime());
}