var accept = function (){
	var val1 = $('#carArchives_changeBeforeForm input[name=carLicense]').val();
	var val2 = $('#carArchives_changeAfterForm input[name=carLicense]').val();
	if(val1 == val2){
		alertMsg('您输入的车牌照相同，请重新输入！', 'warning');
	}else if(val2 != ''){
		$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: projectPath+'basCarArchivesAction!isExistsCarId.action',
	       data: "&carLicense="+val2,
		   success: function(r){
			 if(r.success){
			 	alertMsg(r);
			 }else{
				 $.messager.confirm('优亿软件提示','请确认是否将原车辆编号为【' + val1 + '】变更为【' + val2 + '】？',function(r){   
				    if (r){   
				    	$.ajax({
						   type: 'post',
						   dataType: 'json',
						  url : projectPath+'basCarArchivesAction!changecarId.action',
						   data: 'carId='+$('#frt_car_id').val() + "&carLicense=" + val2,
						   success: function(r){
							 if(r.success){
							 	 alertMsg(r);
								 $('#carArchivesDatagrid').datagrid('load');
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