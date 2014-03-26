var editRow=undefined;
$(function(){
   $('#carTypeTable').datagrid({
	  url:projectPath+'frtCarAllowCarTypeAction!view.action',
	  idField:'allowCarTypeId',
	  pagination:true,
	  sortName:'allowCarTypeId',
	  sortOrder:'asc',
	  singleSelect:true,
	  fit:true,
	  fitColumns : true,
	  rownumbers:true,
      columns:[[
              {
		title : '编号',
		field : 'allowCarTypeId',
		width : 100,
		hidden:true
	}, {
		title : '准驾车型',
		field : 'allowCarTypeName',
		width : 100,
		sortable:true,
		editor:{
			 type:'validatebox',
             options:{
           		required:true,
           		validType:'multiple[\'characterDigit\',\'length[0,20]\']',
           		missingMessage : "准驾车型必填项"
             }
        }
	}]],  
	onAfterEdit:function(rowIndex, rowData, changes){
       if(rowData.allowCarTypeId==undefined)
       {
             $.ajax({
	             type:"POST",
	             url:"frtCarAllowCarTypeAction!add.action",
	             data:"allowCarTypeName="+rowData.allowCarTypeName,
	             dataType:"json",
	             success:function callback(r){
	            	 if(r.success)
	            	 {
	            		 $('#carTypeTable').datagrid('clearSelections');
	            		 $('#carTypeTable').datagrid('load');
	            		 cancel();
	            	 }else
	            	 {
	            		 $('#carTypeTable').datagrid('beginEdit', rowIndex);
	            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){
					     });
	            	 }
                 }
             });
	    }else{
	        $.ajax({
	             type:"POST",
	             url:"frtCarAllowCarTypeAction!update.action",
	             data:"allowCarTypeId="+rowData.allowCarTypeId+"&allowCarTypeName="+rowData.allowCarTypeName,
	             dataType:"json",
	             success:function callback(r){
		        	  if(r.success)
		        	  {
		        		  $('#carTypeTable').datagrid('clearSelections');
		        		  $('#carTypeTable').datagrid('load');
		        		  cancel();
		        	  }else
		        	  {
		        		  $('#carTypeTable').datagrid('beginEdit', rowIndex);
		        		  $.messager.alert('优亿软件提示',r.msg,'info',function(){
						  });
		        	  }
                 }
             });
	      }
       }
    });
});

//按钮区域增加 保存和取消按钮
function  addButton(){
	var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}

//取消
function cancel() {
	 $('#carTypeTable').datagrid('unselectAll');
	 $('#carTypeTable').datagrid('rejectChanges');
	 $('#saveOrCancelBtn').empty();
	 $('#add').linkbutton('enable');
	$('#delete').linkbutton('enable');
	$('#update').linkbutton('enable');
	$('#export').linkbutton('enable');
	 editRow = undefined;
}
//添加
function add()
{
	if (editRow != undefined) {
		$('#carTypeTable').datagrid('endEdit', editRow);
		$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
	    });
	} else {
		$('#add').linkbutton('disable');
		$('#delete').linkbutton('disable');
		$('#update').linkbutton('disable');
		$('#export').linkbutton('disable');
		$('#carTypeTable').datagrid('insertRow', {
			index : 0, 
			row : {}
		});
		addButton();
		$('#carTypeTable').datagrid('beginEdit', 0);
		editRow = 0;
		bindEnterInCloumn($('#carTypeTable'), editRow, 0);
	}
}

// 保存
function save() {
	if (editRow == undefined) {
		$.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
	    });
	}else
	{
		var isno=$('#carTypeTable').datagrid('validateRow',editRow);
		if(isno)
		{
			$('#carTypeTable').datagrid('endEdit', editRow);
			
		}else
		{
			 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
			 });
		}
	}
}	

//删除
function del() {
	var data = $('#carTypeTable').datagrid('getSelected');
	var index=findSelectRowIndex('carTypeTable',data);
	if (data) {
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
			if (r) {
				$.ajax({
					type : "POST",
					url : "frtCarAllowCarTypeAction!del.action",
					data : "allowCarTypeId=" + data.allowCarTypeId+"&allowCarTypeName="+data.allowCarTypeName,
					dataType : "json",
					success : function callback(r) {
						if(r.success)
						{
							    $('#carTypeTable').datagrid('clearSelections');
							    $('#carTypeTable').datagrid('load');
							    setSelectRow('carTypeTable',index);
						}else
						{
							$.messager.alert('优亿软件提示',r.msg,'info',function(){
						    });
						}
					}
				});
			}
		});
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
	}
}

//修改
function update() {
	var selects = $('#carTypeTable').datagrid('getSelections');
	if (selects.length > 0) {
		if (selects.length == 1) {
			var isno=$('#carTypeTable').datagrid('validateRow',editRow);
			if(isno)
			{
				$('#add').linkbutton('disable');
				$('#delete').linkbutton('disable');
				$('#update').linkbutton('disable');
				$('#export').linkbutton('disable');
				editRow = $('#carTypeTable').datagrid('getRowIndex', selects[0]);
				$('#carTypeTable').datagrid('beginEdit', editRow);
				bindEnterInCloumn($('#carTypeTable'), editRow, 0);
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
  * 
  * 导出excel
  * @return
  */
function _except(){
	exportEsuyUIExcelFile("carTypeCenter",null,'电话跟踪回访信息'+getSystemTime());
}	