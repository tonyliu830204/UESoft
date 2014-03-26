$(function(){
	var addrow = undefined;
	var editrow = undefined;
	var addbar = undefined;
	var editbar = undefined;
	var editindex = undefined;
	//数据字典父级
	$('#dataWordBookMain').datagrid({
		url : projectPath+'dataWordBookAction!datagridP.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'parentId',
		sortOrder:'asc',
		idField : 'parentId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'parentId',
			title : '编号',
			width : 40,
			hidden : true
			
		} ,{
			field : 'stfId',
			title : '创建人',
			width : 100,
			sortable : true,
			formatter : function (value,row,index){
				return row.stfName;
			}
		},{
			field : 'createTime',
			title : '创建时间',
			width : 122,
			sortable : true
		},{
			field : 'dataKey',
			title : '父级键',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,20]',
					missingMessage : '键为必填项'
				}
			}
		},{
			field : 'dataValue',
			title : '父级值',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,50]',
					missingMessage : '值为必填项'
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			  doAdd($('#dataWordBookMain'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#dataWordBookMain'),projectPath+'dataWordBookAction!removeP.action',projectPath+'dataWordBookAction!datagridP.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			   doUpdate($('#dataWordBookMain'));
		}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			     _except("dataWordBook","数据字典维护");
		    }
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
			endEdit($('#dataWordBookMain'),projectPath+'dataWordBookAction!saveP.action',projectPath+'dataWordBookAction!editP.action',projectPath+'dataWordBookAction!datagridP.action',rowIndex, rowData, changes);
		},
		onDblClickRow : function(rowIndex, rowData){
			var url=projectPath+'base/other_set/dataWordBookDetail.jsp?parentId='+rowData.parentId;
//			var url='base/other_set/dataWordBookDetail.jsp?parentId='+rowData.parentId+'&parentName='+rowData.dataValue;
			openURL(url);
		}
	});
	
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
						row: {}
					});
					id.datagrid('beginEdit',0);
					addrow = 0;
					editindex = 0;
					bindEnterInCloumn(id, 0, 0);
				if(addbar==undefined){
						id.datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
							if(id.datagrid('validateRow', addrow)){
								id.datagrid('endEdit', addrow);
							}else{
								alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
							}
								
					}},{"text":"取消","iconCls":"icon-undo","handler":function(){
//							 id.datagrid('unselectAll');
//	    	        		 id.datagrid('rejectChanges');
//	    	        		 id.datagrid('removeToolbarItem','保存');
//	    	        		 id.datagrid('removeToolbarItem','取消');
//	    	        		 id.datagrid('endEdit',addrow);
//	    	        		 addrow = undefined;
								cancel(id);
						}}]);}
			}else{
				id.datagrid('endEdit',addrow);
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
					var d = $.parseJSON(data);
					if(d.success){
						id.datagrid({url : url2});//刷新
						//清空选择
						id.datagrid('clearSelections');
					}else{
						id.datagrid('clearSelections');
						alertMsg('含有子级数据，请先删除子级数据！', 'info');
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
				id.datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
					if(id.datagrid('validateRow', editrow)){
						id.datagrid('endEdit', editrow);
					}else{
						alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
					}
				}},{"text":"取消","iconCls":"icon-undo","handler":function(){
//					id.datagrid('unselectAll');
//	        		 	id.datagrid('rejectChanges');
//	        		 	id.datagrid('removeToolbarItem','保存');
//	        		 	id.datagrid('removeToolbarItem','取消');
//					id.datagrid('endEdit',editrow);
//					editrow = undefined;
						cancel(id);
					}}]);
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
				id.datagrid('unselectAll');
				}
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	}
function cancel(id){
	id.datagrid('unselectAll');
 	id.datagrid('rejectChanges');
 	id.datagrid('removeToolbarItem','保存');
 	id.datagrid('removeToolbarItem','取消');
	$('#btnadd').linkbutton('enable');
	$('#btnremove').linkbutton('enable');
	$('#btnedit').linkbutton('enable');
	$('#btnexport').linkbutton('enable');
	editrow = undefined;
	addbar=undefined;
	addrow=undefined;
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
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
		}
	if(updated.length>0){
		url = url2;
	}else{
		cancel(id);
	}
	
	if(inserted.length == 0 && updated.length==0){
		editrow = undefined;
		id.datagrid('removeToolbarItem','保存');
		id.datagrid('removeToolbarItem','取消');
		cancel(id);
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
//				id.datagrid('removeToolbarItem','保存');
//				id.datagrid('removeToolbarItem','取消');
				if(inserted.length>0){
					id.datagrid('load');
					}
				if(updated.length>0){
					id.datagrid('reload');
				}
//				addrow = undefined;
//				addbar = undefined;
//				editrow = undefined;
				cancel(id);
			}else{
				alertMsg(d.msg, 'info');
				id.datagrid('beginEdit', editindex);
				}
			}
		});
	}
});