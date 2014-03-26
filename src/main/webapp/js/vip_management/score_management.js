$(function(){
				$('#score_management_datil_form_add').hide();
				$('#score_management_datil_form_edit').hide();
				});
			//取消方法
			function doCancel(){
				$('#add').window('close');
			}
			//新增方法
			function doAdd(){
				
			}
			//将form表单序列化为对象
			function serializeObject(form){
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
			//获取form表单数据  
			function submitForm(datagridId,formId,url1,url2){
				 doCancel();
				var form =  formId.form();
				var formvalue = serializeObject(form);
					$.ajax({
					type : 'POST',
					url : url1,
					data : formvalue,
					dataType : 'json',
					success : function(r){
						if(r){
						$.messager.alert('提示',r.msg,'warning');
							datagridId.datagrid({
								url : url2
							});
						}else{
								$.messager.alert('提示','数据提交失败!','warning');
						}
					}
				   });
			}	