var accept = function (){
    var val1 = $('#customArchives_changeBeforeForm input[name=customId]').val();
	var val2 = $('#customArchives_changeAfterForm input[name=customId]').val();
	var val3 = $('#customArchives_changeAfterForm input[name=customName]').val();
	if(val1 == val2){
		alertMsg('您输入的车牌照相同，请重新输入！', 'warning');
	}else if(val2 != ''){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: projectPath+'frtCustomAction!isExistsCustomId.action',
		   data: 'customId='+val2,
		   success: function(r){
			 if(r.success){
			 	alertMsg(r);
			 }else{
				 $.messager.confirm('优亿软件提示','请确认是否将原客户编号为【' + val1 + '】变更为【' + val2 + '】？',function(r){   
				    if (r){   
				    	$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: projectPath+'frtCustomAction!changeCustomId.action',
					       data: 'customIdOld='+val1 + "&customId=" + val2+"&customName="+val3,
						   success: function(r){
							 if(r.success){
							 	 alertMsg(r);
								 $('#customArchivesDatagrid').datagrid('load');
								   changeDialog.dialog('close');
							 }else{
								 alertMsg(r);
							 }
						   },
						   error : function (r){
							   alertMsg(r);
						   }
						});
				    }   
				});  
			 }
		   },
		   error : function (r){
			   alertMsg(r);
		   }
		});
	}else{
	alertMsg('对不起，没有可以变更的资料！', 'warning');
	}
}