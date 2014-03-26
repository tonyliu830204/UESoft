$(function() {
	
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
	//获取form表单数据传给后台action
	function doSubmit(id,formid,url1,url2){
		var form =  $(formid).form();
		var formvalue = serializeObject(form);
		console.info(formvalue);
			$.ajax({
			type : 'POST',
			url : url1,
			data : formvalue,
			dataType : 'json',
			success : function(r){
					id.datagrid({url : url2 });
				}
		   });
	}	
	
	//删除方法
	function doDelete(id,url1,url2){
		var delrow = id.datagrid('getSelections');
		if(delrow.length>0){
			$.messager.confirm('请确认','您确定要删除当前所选项目吗?',function(b){
				if(b){
					$.ajax({
						url : url1,
						data : delrow[0]
					});
					id.datagrid({
						url : url2
					});
				}
			});
		}else{
			$.messager.alert('提示','请选择你要删除的记录!','warning');
		}
	}
	
	
});
