var editRow=undefined;
$(function(){
   $('#PartsStateTable').datagrid({
	  url:projectPath+'BasPartsStateAction!view.action',
	  idField:'stateId',
	  pagination:true,
	  sortName:'stateId',
	  sortOrder:'asc',
	  singleSelect:true,
	  fit:true,
	  pageSize : 10,
      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	  fitColumns : true,
	  rownumbers:true,
      columns:[[
              {
		title : '编号',
		field : 'stateId',
		width : 100,
		hidden:true
	}, {
		title : '配件产地',
		field : 'stateName',
		width : 100,
		sortable:true,
		editor:{
			 type:'validatebox',
             options:{
           		required:true,
           		validType:'multiple[\'characterDigit\',\'length[0,50]\']',
           		missingMessage : "配件产地必填项"
             }
        }
	}]],  
	/*toolbar:[
         {text:'新增',iconCls:'icon-add',handler:function(){
        	    add();
         } },
		 {text:'删除',iconCls:'icon-remove',handler:function(){
        	 del();
		 } },
		 {text:'修改',iconCls:'icon-edit',  handler:function(){
			    update(); 
		 }},{
			 text:'导出',iconCls:'icon-export',handler:function(){
			 _except();
         }}
    ],  */
	onAfterEdit:function(rowIndex, rowData, changes){
       if(rowData.stateId==undefined)
       {
             $.ajax({
	             type:"POST",
	             url:"BasPartsStateAction!add.action",
	             data:"stateName="+rowData.stateName,
	             dataType:"json",
	             success:function callback(r){
	            	 if(r.msg=="success")
	            	 {
	            		 $('#PartsStateTable').datagrid('clearSelections');
	            		 $('#PartsStateTable').datagrid('load');
	            		 cancel();
	            	 }else
	            	 {
	            		 $('#PartsStateTable').datagrid('beginEdit', rowIndex);
	            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){
					     });
	            	 }
                 }
             });
	    }else{
	        $.ajax({
	             type:"POST",
	             url:"BasPartsStateAction!update.action",
	             data:"stateId="+rowData.stateId+"&stateName="+rowData.stateName,
	             dataType:"json",
	             success:function callback(r){
		        	  if(r.msg=="success")
		        	  {
		        		  $('#PartsStateTable').datagrid('clearSelections');
		        		  $('#PartsStateTable').datagrid('load');
		        		  cancel();
		        	  }else
		        	  {
		        		  $('#PartsStateTable').datagrid('beginEdit', rowIndex);
		        		  $.messager.alert('优亿软件提示',r.msg,'info',function(){
						  });
		        	  }
                 }
             });
	      }
       }
    });
});

//添加
function add()
{
	if (editRow != undefined) {
		$('#PartsStateTable').datagrid('endEdit', editRow);
		$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
	    });
	} else {
		$('#PartsStateTable').datagrid('insertRow', {
			index : 0, 
			row : {}
		});
		 addButton();
		$('#PartsStateTable').datagrid('beginEdit', 0);
		editRow = 0;
		bindEnterInCloumn($('#PartsStateTable'), editRow, 0);
	}
}

// 保存
function save() {
	if (editRow == undefined) {
		$.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
	    });
	}else
	{
		var isno=$('#PartsStateTable').datagrid('validateRow',editRow);
		if(isno)
		{
			$('#PartsStateTable').datagrid('endEdit', editRow);
			
		}else
		{
			 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
			 });
		}
	}
}	

//删除
function del() {
	var selects = $('#PartsStateTable').datagrid('getSelections');
	if (selects.length > 0) {
		if (selects.length == 1) {
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "BasPartsStateAction!del.action",
						data : "stateId=" + selects[0].stateId+"&stateName="+selects[0].stateName,
						dataType : "json",
						success : function callback(r) {
							if(r.msg=="success")
							{
								    $('#PartsStateTable').datagrid('clearSelections');
								    $('#PartsStateTable').datagrid('load');
							}else
							{
								$.messager.alert('优亿软件提示',r.msg,'info',function(){
							    });
							}
						}
					});
				}
			});
		}
	}else{
			$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){
		    });
	}
}

//修改
function update() {
	var selects = $('#PartsStateTable').datagrid('getSelections');
	if (selects.length > 0) {
		if (selects.length == 1) {
			var isno=$('#PartsStateTable').datagrid('validateRow',editRow);
			if(isno)
			{
				editRow = $('#PartsStateTable').datagrid('getRowIndex', selects[0]);
				$('#PartsStateTable').datagrid('beginEdit', editRow);
				bindEnterInCloumn($('#PartsStateTable'), editRow, 0);
				 addButton();
			}else
			{
				$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(r){
			    });
			}
		}
	} else {
		$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
			$('#saveOrCancelBtn').empty();
	    });
	}
}

/**
 * 启用控件
 */
function nuDisAbledControl(){
	$('a.easyui-linkbutton').linkbutton('enable');
}

/**
 * 禁用控件
 */
function disAbledControl(){
	$('a.easyui-linkbutton').linkbutton('disable');
}

function  addButton(){
	disAbledControl();
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}

//取消
function cancel() {
	 nuDisAbledControl();
	 $('#PartsStateTable').datagrid('unselectAll');
	 $('#PartsStateTable').datagrid('rejectChanges');
	 $('#saveOrCancelBtn').empty();
	 editRow = undefined;
}
/**
  * 
  * 导出excel
  * @return
  */
function _except(){
	exportEsuyUIExcelFile("PartsStateCenter",null,'电话跟踪回访信息'+getSystemTime());
}	