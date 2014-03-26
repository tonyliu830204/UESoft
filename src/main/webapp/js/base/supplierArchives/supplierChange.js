var accept = function (){
	var val1 = $('#supplierArchives_changeBeforeForm input[name=relcampId]').val();
	var val2 = $('#supplierArchives_changeAfterForm input[name=relcampId]').val();
	if(val1 == val2){
		alertMsg('您输入的供应商代码相同，请重新输入！', 'warning');
	}else if(val2 != ''){
		$.messager.confirm('优亿软件提示','请确认是否将原供应商代码为【' + val1 + '】变更为【' + val2 + '】？',function(r){   
		    if (r){   
		    	$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'bassupplierArchivesAction!changeCustomId.action',
				   data: 'relcampId='+val1 + "&changedRelcampId=" + val2,
				   success: function(r){
					 if(r.success){
						 $('#supplierArchivesDatagrid').datagrid('load');
						 changeDialog.dialog('close');
					 }
				   },
				   error : function (r){
					   alertMsg(r);
				   }
				});
		    }   
		});  
	}else{
		alertMsg('对不起，没有可以变更的资料！', 'warning');
	}
}