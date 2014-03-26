		$(function (){
			var url='';
			if(carId!=null && carId!="" && carId!='null'){
              url='frtReceptionAction!findFrtResvAdviceByCarId.action?carId='+carId;	
			}else{
				 url='frtReceptionAction!findFrtResvAdviceByCarId.action?carId=-1';	
			}
			//前台接车单->维修建议
			$frtReceptionAdviceDatagrid = $('#frtReceptionAdviceDatagrid');
			$frtReceptionAdviceDatagrid.datagrid({
				url:url,
				singleSelect : true,
				pagination : true,
				fit : true,
				rownumbers : true,
				fitColumns : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'carId',
					title : '车辆牌照',
					hidden : true,
					sortable:true,
					width : 100
				},{
					field : 'carLicense',
					title : '车辆牌照',
					sortable:true,
					width : 100
				},{
					field : 'adviceTime',
					title : '发布日期',
					sortable:true,
					width : 120
				},{
					field : 'writePerson',
					title : '发布人员',
					width : 100,
					sortable:true,
					formatter: function(value,row,index){
						return row.writePersonName;
					}
				},{
					field : 'customName',
					title : '客户名称',
					sortable:true,
					width : 100
				},{
					field : 'adviceClass',
					title : '维修分类',
					sortable:true,
					width : 100,
					formatter: function(value,row,index){
						return row.adviceClassName;
					}
				},{
					field : 'adviceContext',
					title : '维修建议',
					width : 300
				},{
					field : 'procesState',
					title : '处理进度',
					width : 100,
					sortable:true,
					formatter: function(value,row,index){
						return row.procesStateName;
					}
				},{
					field : 'procesContext',
					title : '处理结果',
					width : 300
				},{
					field : 'adviceTimeEnd',
					title : '处理时间',
					sortable:true,
					width : 120
				},{
					field : 'procesProson',
					title : '经办人',
					width : 100,
					sortable:true,
					formatter: function(value,row,index){
						return row.procesProsonName;
					}
				}]],
				toolbar : [{
					id : 'frtReception_service_add',
					text : '新增',
					iconCls : 'icon-add',
					disabled : true,
					handler : function() {
							if(rowDataAndCarId==""){
						 		rowDataAndCarId=$('#frtReception_details_carId').combobox('getValue');
						 		rowDataAndCarLicense=$('#frtReception_details_carId').combobox('getText');
							 }
							$('#scarId').val(rowDataAndCarId);
							$('#scarLicense').val(rowDataAndCarLicense);
							$('#sresvId').val($('#frtReception_details_resvId').val());
							if($('#scarId').val()){
								if($('#sresvId').val()){
									opens(0);				
								}else{
									$.messager.confirm('优亿软件提示','预约单信息缺失，<br/>继续增加将不能通过预约单查询维修建议！',function(r){
										if(r){
											opens(0);
										}
									});
								}
							}else{
								$.messager.alert('优亿软件提示','车辆信息缺失，请刷新后重新操作！','warning',function(){});
							}
							
						}
					},{
					   id : 'frtReception_service_remove',
					   text : '删除',
					   iconCls : 'icon-remove',
					   disabled : true,
					   handler : function (){
						   var row = $frtReceptionAdviceDatagrid.datagrid('getSelected');
							if(row){
								$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
									if (r){
										$.ajax({
										   type: 'post',
										   dataType: 'json',
										   url: 'frtReceptionAction!deleteFrtResvAdvice.action',
										   data: row,
										   success: function(r){
											 if(r.success){
												alertMsg(r);
												 $('#frtReceptionAdviceDatagrid').datagrid('load');
											 }
										   }
										});
									}
								});
							}else{
								alertMsg('对不起，请先选中要删除的记录！', 'warning');
							}
				   	   }
				   },{
					   id : 'frtReception_service_accept',
					   text : '修改',
					   iconCls : 'icon-edit',
					   disabled : true,
					   handler : function (){
					   		opens(1);
				   	   }
				   }],
				   onDblClickRow : function (rowIndex, rowData){
						opens(2);
				   },
				   onLoadSuccess : function (data){
					   if(operation==true  || recpetionFlag==true){
							$('#frtReception_service_add').linkbutton('enable');
							$('#frtReception_service_remove').linkbutton('enable');
							$('#frtReception_service_accept').linkbutton('enable');
						}
				   } 
			});
		});
		var rowDataAndCarId="";
		var rowDataAndCarLicense="";
	var opens=function(i){
				if(i==0){
					 if (rowDataAndCarId) {
						 $('#serviceCarId').val(rowDataAndCarId) ;						  
					 }else {
						 $.messager.alert('优亿软件提示','对不起，操作出现异常，请刷新后再尝试！','warning',function(){});
						 return;
					 }		
					 $('#sadviceTime').datetimebox('setValue','{now}');
					 $('#sadviceTimeEnd').datetimebox('setValue','{now}');
					$(function(){
						$('#ee').dialog({
							buttons:[{
								text:'Ok',
								iconCls:'icon-ok',
								handler:function(){
									if($('#frtResvAdviceForm').form('validate')){
										$.ajax({
										   type: 'post',
										   dataType: 'json',
										   url: 'frtReceptionAction!addFrtResvAdvice.action',
										   data: $('#frtResvAdviceForm').serialize(),
										   success: function(r){
											 if(r.success){
											 	 closes(0);
												 alertMsg(r);
												 $('#frtReceptionAdviceDatagrid').datagrid({
													 url :  'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' +rowDataAndCarId,
													 onLoadSuccess : function (){
														 $('#frtReception_service_add').linkbutton('enable');
														 $('#frtReception_service_remove').linkbutton('enable');
														 $('#frtReception_service_accept').linkbutton('enable');
													 }
												 });
											 }else{
											 	alertMsg(r);
											 }
										   }
										});
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							},{
								text:'Cancel',
								handler:function(){
									closes(0);
								}
							}]
						});
					});					
				}else if(i==1){
					 var selected = $('#frtReceptionAdviceDatagrid').datagrid('getSelected');
					 if (selected) {	
					 	$('#ucarId').val(selected.carId);
					 	$('#ucarLicense').val(selected.carLicense);	
					 	$('#uadviceId').val(selected.adviceId);	
					 	$('#uadviceContext').val(selected.adviceContext);	
					 	$('#uadviceTime').val(selected.adviceTime);
					 	$('#uwritePerson').combobox('setValue',selected.writePerson);	
					 	$('#uprocesContext').val(selected.procesContext);
					 	$('#uprocesState').combobox('setValue',selected.procesState);	
					 	$('#uadviceClass').combobox('setValue',selected.adviceClass);
					 }else {
						 $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
						 return;
					 }	
					 if($('#uprocesState').combobox('getValue')==PROCESSTATEYES){
					 	$.messager.alert('优亿软件提示','维修建议已处理，不能更改！','warning',function(){});
					 	return;
					 }
					 $('#uadviceTimeEnd').datetimebox('setValue','{now}');
					$(function(){
						$('#ff').dialog({
							title:'修改',
							buttons:[{
								text:'Ok',
								iconCls:'icon-ok',
								handler:function(){
									if($('#ufrtResvAdviceForm').form('validate')){
											$.ajax({
											   type: 'post',
											   dataType: 'json',
											   url: 'frtReceptionAction!updateFrtResvAdvice.action',
											   data: $('#ufrtResvAdviceForm').serialize(),
											   success: function(r){
												 if(r.success){
												 		closes(1);
													 alertMsg(r);
													 $('#frtReceptionAdviceDatagrid').datagrid({
														 url :  'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' +rowDataAndCarId,
														 onLoadSuccess : function (){
															 $('#frtReception_service_add').linkbutton('enable');
															 $('#frtReception_service_remove').linkbutton('enable');
															 $('#frtReception_service_accept').linkbutton('enable');
														 }
													 });
												 }else{
												 	alertMsg(r);
												 }
											   }
											});
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							},{
								text:'Cancel',
								handler:function(){
									closes(1);
								}
							}]
						});
					});					
				}else if(i==2){
					 var selected = $('#frtReceptionAdviceDatagrid').datagrid('getSelected');
					 if (selected) {
					 	$('#sscarId').val(selected.carId);
					 	$('#sscarLicense').val(selected.carLicense);	
					 	$('#ssadviceId').val(selected.adviceId);	
					 	$('#ssadviceContext').val(selected.adviceContext);	
					 	$('#ssadviceTime').val(selected.adviceTime);
						$('#ssadviceTimeEnd').val(selected.adviceTimeEnd);
					 	$('#sswritePerson').combobox('setValue',selected.writePerson);
					 	$('#ssprocesContext').val(selected.procesContext);
					 	$('#ssprocesState').combobox('setValue',selected.procesState);
					 	$('#ssprocesProson').combobox('setValue',selected.procesProson);
					 	$('#ssadviceClass').combobox('setValue',selected.adviceClass);
					}
					$(function(){
						$('#dd').dialog({
							buttons:[{
								text:'Ok',
								iconCls:'icon-ok',
								handler:function(){
									closes(2);
								}
							},{
								text:'Cancel',
								handler:function(){
								closes(2);
								}
							}]
						});
					});
			}
		}
		var closes=function(i){
				if(i==0){
					$('#ee').dialog('close');
					$('#sadviceClass').combobox('setValue','');
					$('#sadviceContext').val('');
					$('#sprocesState').combobox('setValue','');
					$('#sprocesContext').val('');
				}else if(i==1){
					$('#ff').dialog('close');
					$('#ufrtResvAdviceForm').form('clear');
					$('#uadviceTimeEnd').datetimebox('setValue','{now}');
					$('#uprocesProson').combobox('setValue',parame1);
					
				}else if(i==2){
					$('#dd').dialog('close');
					$('#sfrtResvAdviceForm').form('clear');
				}
		}