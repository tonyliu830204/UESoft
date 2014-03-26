var accept = function (){
var val1 = $('#partsArchives_changeBeforeForm input[name=partsId]').val();
var val2 = $('#partsArchives_changeAfterForm input[name=partsId]').val();
if(val1 == val2){
	alertMsg('您输入的配件代码相同，请重新输入！', 'warning');
}else if(val2 != ''){
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: parojectPat+'basPartsArchivesAction!isExistsPartsId.action',
	   data: 'partsId='+val2,
	   success: function(r){
		 if(r.success){
		 	alertMsg(r);
		 }else{
			 $.messager.confirm('优亿软件提示','请确认是否将原配件编号为【' + val1 + '】变更为【' + val2 + '】？',function(r){   
			    if (r){   
			    	$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: parojectPat+'basPartsArchivesAction!changePartsId.action',
					   data: 'partsId='+val1 + "&changedPartsId=" + val2,
					   success: function(r){
						 if(r.success){
							 alertMsg(r);
						 }else{
							 alertMsg(r);
							 $('#partsArchivesDatagrid').datagrid('reload');
							 changeDialog.dialog('close');
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