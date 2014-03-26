//将form表单序列化为对象
	function serializeObject2(form){
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

	//获取form表单数据传给后台action
	function doSaveSubmit(id,formid,url){
		var form =  formid.form();
		var formvalue = serializeObject2(form);
			$.ajax({
			type : 'POST',
			url : url,
			data : formvalue,
		    dataType : 'json',
			success : function(r){
					if(r.success){
						var car_Id = $('#carid').val();
						id.datagrid({url : 'customerCareAction!factoryVisit.action?car_Id='+car_Id});
					}
				}
		   });
	}