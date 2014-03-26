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
		//清空form表单
	 	function doClear(){
	 		$('#form_carlost_infor_id').form('clear');
	 	}
 	
 		//单击回访时弹出窗口	
		function dbLostButton(){
		var values = $('#datagrid_carlost_info_id').datagrid('getSelections');
		if(values != null && values.length !=0){
		var i = $('<div style="overflow:hidden;"/>').dialog({
				width:1200,
				height:600,
				modal:true,
				href : projectPath+'return_visit/window_carlost.jsp',
				title : '车辆流失提醒',
				onLoad:function(){
				alert("加载成功");
					$(this).form('load', values[0]);
					//加载 流失历史记录 
					$('#datagrid_carlost_return_id').datagrid({url : 'customerCareAction!factoryVisit.action?car_Id='+values[0].car_Id});
					
					//查询历史维修项目的方法			
					$('#datagrid_carlost_repair_id').treegrid({url : 'customerCareAction!getFactoryWxRecord.action?car_Id='+values[0].car_Id});
			    },
			    onClose : function (){
			    	$(this).window('destroy');
			    }
			});
		}else{
			$.messager.show({
				title : '系统提示',
				msg : '请选择要回访的记录',
				timeout : 3000
			});
		}
	}
	
	//转准流失  
	function updateToF(){
		var carId = $('#cId').val();
		$.messager.confirm('优亿软件提示','请确认是否要将编号为【'+carId+'】的车辆转为流失车辆？',function(b){
		$.ajax({
				type : 'POST',
				url : 'customerCareAction!updateToF.action?car_Id='+carId
		   });
		 });  
	}