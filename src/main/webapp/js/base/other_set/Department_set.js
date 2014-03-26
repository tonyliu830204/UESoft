var staticIndex=0; 
var staticEnterpriseId;
var staticEnterpriseName;
	//新增数据方法
	function doAdd(){
		var rows = $('#department_set_id').datagrid('getSelections');
		var index = $('#department_set_id').datagrid('getRowIndex',rows[0]);//
		$('#btnadd').linkbutton('disable');
		$('#btnremove').linkbutton('disable');
		$('#btnedit').linkbutton('disable');
		$('#btnexport').linkbutton('disable');
		$('#btnsearch').linkbutton('disable');
		$('#btncancel').linkbutton('disable');
		$('#department_set_id').datagrid('insertRow',{
			index: 0,	
			row: {"enterpriseId":staticEnterpriseId,"enterpriseName":""+staticEnterpriseName+""}
		});
		$('#department_set_id').datagrid('beginEdit',0);
		staticIndex=0;
		addButton();
	}
	//保存操作
	function save(){
		if($('#department_set_id').datagrid('validateRow', staticIndex)){
			$('#department_set_id').datagrid('endEdit', staticIndex);
		}else{
			alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
		}		
	}
	//按钮区域增加 保存和取消按钮
	function  addButton(id,index){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';			
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
	}
	//删除方法
	function doDelete(){
		var delrow = $('#department_set_id').datagrid('getSelected');
		if(delrow){
			$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
				if(b){
					$.ajax({
						url : projectPath+'basDeptAction!doDelete.action',
						type: 'post',
						dataType: 'json',
						data : delrow,
						success : function(r){
							if(r.success){
								$('#department_set_id').datagrid('reload');//刷新
								//清空选择
								$('#department_set_id').datagrid('clearSelections');
							}else{
								alertMsg(r);
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
		}
	}
	//修改方法
	function doUpdate(){
		var row = $('#department_set_id').datagrid('getSelected');
		if(row){
			$('#btnadd').linkbutton('disable');
			$('#btnremove').linkbutton('disable');
			$('#btnedit').linkbutton('disable');
			$('#btnexport').linkbutton('disable');
			$('#btnsearch').linkbutton('disable');
			$('#btncancel').linkbutton('disable');
			var index = $('#department_set_id').datagrid('getRowIndex',row);//
			$('#department_set_id').datagrid('beginEdit',index);
			bindEnterInCloumn($('#department_set_id'), index, 0);
			staticIndex=index;
			addButton();	
		}else{
			alertMsg('对不起，请先选中要修改的记录！', 'warning');
		}
	}
	var cancel=function(){
		$('#department_set_id').datagrid('unselectAll');
		$('#department_set_id').datagrid('rejectChanges');
	    $('#saveOrCancelBtn').empty();
		$('#btnadd').linkbutton('enable');
		$('#btnremove').linkbutton('enable');
		$('#btnedit').linkbutton('enable');
		$('#btnexport').linkbutton('enable');
		$('#btnsearch').linkbutton('enable');
		$('#btncancel').linkbutton('enable');
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
			cancel();
		}
		if(inserted.length == 0 && updated.length==0){
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
					cancel(id);
				}else{
					alertMsg(d);
					id.datagrid('beginEdit', rowIndex);
				}
			}
		});
	}
	$.ajax({
		type : 'POST',
		url : projectPath+'basDeptAction!findBasStuffOfEnterpriseInfo.action',
		dataType : 'json',
		success : function (d){
			if(d.success){
				staticEnterpriseId=d.obj.enterpriseId;
				staticEnterpriseName=d.obj.enterpriseName;
				runs();
			}else{
				alertMsg(d);
			}
		}
	});	
	var runs=function(){
		//部门设置
		$('#department_set_id').datagrid({
			url : projectPath+'basDeptAction!doFindAll.action',
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
						validType:'multiple[\'characterDigit\',\'length[0,20]\']',
						missingMessage : '部门名称为必填项'
					}
				}
			},{
				field : 'enterpriseId',
				title : '编号',
				width : 40,
				hidden : true
			} ,{
				field : 'enterpriseName',
				title : '所属公司',
				width : 80,
				sortable : true
			} ,{
				field : 'deptDesc',
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
			onAfterEdit : function(rowIndex, rowData, changes){
				endEdit($('#department_set_id'),projectPath+'basDeptAction!doAdd.action',projectPath+'basDeptAction!doUpdate.action',projectPath+'basDeptAction!doFindAll.action',rowIndex, rowData, changes);
			}
		});
	}
	
/**
 * 
 * 导出excel
 * @return
 */
function _except(div,name){
	exportEsuyUIExcelFile(div,null,name+getSystemTime());
}