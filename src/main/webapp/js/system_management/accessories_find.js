//将form表单序列化为对象
		function serializeObj(form){
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
		
		//条件查询提交
		function doConditionSubmit(datagridId,formid,url1){
			var form =  formid.form();
			var formvalue = serializeObj(form);
			datagridId.datagrid('load',formvalue);
			//datagridId.datagrid({url : url1});	
		}