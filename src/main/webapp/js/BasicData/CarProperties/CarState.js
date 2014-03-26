var editRow=undefined;

$(function(){
	$('#table10').datagrid({
		  url:projectPath+'basCarBodysStatusAction!view.action',
		  idField:'bodyNum',
		  pagination:true,
		  sortName:'bodyNum',
		  sortOrder:'asc',
		  fit:true,
		  pageSize : 10,
		  rownumbers:true,
		  singleSelect:true,
	      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		  fitColumns : true, //
	      columns:[[{
				title : '编号',
				field : 'bodyNum',
				width : 100,
				hidden:true}, {
					title : '车身编码',
					field : 'bodyId',
					width : 100,
					sortable:true,
					editor:{
					type:'validatebox',
		                options:{
		              		required:true,
		              		validType:'multiple[\'characterDigit\',\'length[0,2]\']',
		              		missingMessage : "车身编码为必填项"
		                } 	
				    }
				  }, {
					title : '车身状态',
					field : 'bodyName',
					width : 100,
					sortable:true,
					editor:{
					  	type:'validatebox',
		                options:{
		              		required:true,
		              		validType:'multiple[\'characterDigit\',\'length[0,20]\']',
		              		missingMessage : "车身状态为必填项"
		                }
		            }
				  }, {
					title : '备注',
					field : 'bodyRemark',
					width : 100,
					sortable:true,
					editor:{
					  type:'validatebox',
			            options:{
		              		required:false,
		              		validType:'multiple[\'characterDigit\',\'length[0,50]\']'
		                }
				    }
				}
	              ]], 
		          onAfterEdit:function(rowIndex, rowData, changes)
			      {
		               var inserted = $('#table10').datagrid('getChanges', 'inserted');
		               var updated = $('#table10').datagrid('getChanges', 'updated');
			           if(inserted.length>0)
			           {
				             $.ajax({
					             type:"POST",
					             url:"basCarBodysStatusAction!add.action",
					             data:"bodyId="+rowData.bodyId+"&bodyName="+rowData.bodyName+"&bodyRemark="+rowData.bodyRemark,
					             dataType:"json",
					             success:function callback(r){
				            	 if(r.msg=="success")
				            	 {
				            		 $('#table10').datagrid('clearSelections');
				            		 $('#table10').datagrid('load');
				            		 cancel();
				            	 }else
				            	 {
				            		 $('#table10').datagrid('beginEdit', rowIndex);
				            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){ 
								     });
				            	 }
				               }
				             });
					    }else if(updated.length>0)
					    {
					        $.ajax({
					             type:"POST",
					             url:"basCarBodysStatusAction!update.action",
					             data:"bodyNum="+rowData.bodyNum+"&bodyId="+rowData.bodyId+"&bodyName="+rowData.bodyName+"&bodyRemark="+rowData.bodyRemark,
					             dataType:"json",
					             success:function callback(r){
					        	   if(r.msg=="success")
					        	   {
					        		   $('#table10').datagrid('clearSelections');
					        		   $('#table10').datagrid({
										     url:projectPath+'basCarBodysStatusAction!view.action',
										     loadMsg:'更新数据中......'
									     });
					        		   cancel();
					        	   }else
					        	   {
					        		   $('#table10').datagrid('beginEdit', rowIndex);
					        		   $.messager.alert('优亿软件提示',r.msg,'info',function(){
									   });
					        	   }
				                 }
				             });
					    }
			      }
			});
		});

		function add()
		{
		  if (editRow != undefined) {
				$('#table10').datagrid('endEdit', editRow);
				  $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
				  });

			} else {
				addButton();
				$('#table10').datagrid('insertRow', {
					index : 0,
					row : {}
				});
				$('#table10').datagrid('beginEdit', 0);
				 editRow = 0;
				 bindEnterInCloumn($('#table10'), editRow, 0);
			}
		}

		function save() {
			if (editRow == undefined) {
				 $.messager.alert('优亿软件提示','您当前没有要保存的记录！','warning',function(){
				 });
			}else
			{
				var isno=$('#table10').datagrid('validateRow',editRow);
				if(isno)
				{
					$('#table10').datagrid('endEdit', editRow);
				}else
				{
					 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){});
				}
		    }	
		}	

		function del() {
			var selects = $('#table10').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1) {
					$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
						if (r) {
							$.ajax( {
									type : "POST",
									url : "basCarBodysStatusAction!del.action",
									data : "bodyNum=" + selects[0].bodyNum,
									dataType : "json",
									success : function callback(r) {
										if(r.msg=="success")
										{
											$('#table10').datagrid('clearSelections');
											$('#table10').datagrid( {
												url : 'basCarBodysStatusAction!view.action',
												loadMsg : '更新数据中......'
											});
										}else
										{
											  $.messager.alert('优亿软件提示',r.msg,'info',function(){
											  });
										}
								   }
							});
						}
					});
					}
				} else {
					$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){
					  });
				}
		}


		function update() {
			var selects = $('#table10').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1) {
					var isno=$('#table10').datagrid('validateRow',editRow);
					if(isno)
					{
						editRow = $('#table10').datagrid('getRowIndex', selects[0]);
						$('#table10').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#table10'), editRow, 0);
						addButton();
					}else
					{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
					   });
					}
				}
			} else {
				$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
					$('#table10').datagrid('removeToolbarItem', '保存');
					$('#table10').datagrid('removeToolbarItem', '取消');
				  });
			}
		}
		
		function cancel()
		{
			 $('#table10').datagrid('unselectAll');
    		 $('#table10').datagrid('rejectChanges');
    		 $('#saveOrCancelBtn').empty();
    		 editRow = undefined;
		}
		//按钮区域增加 保存和取消按钮
		function  addButton(){
			var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
			var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#saveOrCancelBtn').children('a').length == 0) {
				$('#saveOrCancelBtn').append(save).append(cancel);
				$.parser.parse('#saveOrCancelBtn');
			}
		}
		
		
		/**
		 * 
		 * 导出excel
		 * @return
		 */
		function _except(){
			exportEsuyUIExcelFile("CarStateCenter",null,'车身状态信息'+getSystemTime());
		}