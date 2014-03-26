    var editRow=undefined;
	$(function(){
	 $('#table10').datagrid({
		  url:projectPath+'BasPartsPositionAction!view.action',
		  idField:'posId',
		  pagination:true,
		  sortName:'posId',
		  sortOrder:'asc',
		  singleSelect:true,
		  fit:true,
		  pageSize : 10,
	      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		  fitColumns : true,
		  rownumbers:true,
	      columns:[[
	              {
			title : '编号',
			field : 'posId',
			width : 100,
			hidden:true
		}, {
			title : '部位名称',
			field : 'posName',
			width : 100,
			sortable:true,
			editor:{
				 type:'validatebox',
	             options:{
	           		required:true,
	           		validType:'multiple[\'characterDigit\',\'length[0,50]\']',
	           		missingMessage : "部位名称为必填项"
	             }
            }
		}, {
			title : '备注',
			field : 'remark',
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
	              ]]/*,  toolbar:[
				    	         {text:'新增',iconCls:'icon-add',handler:function(){
				    	        	    add();
				    	         } },
				    			 {text:'删除',iconCls:'icon-remove',handler:function(){
				    	        	 del();
				    			 } },
				    			 {text:'修改',iconCls:'icon-edit',  handler:function(){
				    				    update(); 
				    			 }},{
				    				 text:'导出',iconCls:'icon-export',handler:function(){
				    				 _except();
				    	         }}
				               ]*/,  
		          onAfterEdit:function(rowIndex, rowData, changes)
			      {
			           if(rowData.posId==undefined)
			           {
				             $.ajax({
					             type:"POST",
					             url:"BasPartsPositionAction!add.action",
					             data:"posName="+rowData.posName+"&&remark="+rowData.remark,
					             dataType:"json",
					             success:function callback(r){
				            	 if(r.msg=="success")
				            	 {
				            		 $('#table10').datagrid('clearSelections');
				            		 $('#table10').datagrid({
										     url:projectPath+'BasPartsPositionAction!view.action',
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
					    }else
					    {
					        $.ajax({
					             type:"POST",
					             url:"BasPartsPositionAction!update.action",
					             data:"posId="+rowData.posId+"&posName="+rowData.posName+"&remark="+rowData.remark,
					             dataType:"json",
					             success:function callback(r){
					        	  if(r.msg=="success")
					        	  {
					        		  $('#table10').datagrid('clearSelections');
					        		  $('#table10').datagrid({
										     url:projectPath+'BasPartsPositionAction!view.action',
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

			//添加
			function add()
			{
				if (editRow != undefined) {
					$('#table10').datagrid('endEdit', editRow);
					$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
				    });
				} else {
					$('#table10').datagrid('insertRow', {
						index : 0, 
						row : {}
					});
					 addButton();
					$('#table10').datagrid('beginEdit', 0);
					editRow = 0;
					bindEnterInCloumn($('#table10'), editRow, 0);
				}
			}
			
			// 保存
			function save() {
				if (editRow == undefined) {
					$.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
				    });
				}else
				{
					var isno=$('#table10').datagrid('validateRow',editRow);
					if(isno)
					{
						$('#table10').datagrid('endEdit', editRow);
						
					}else
					{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
						 });
					}
				}
			}	

			//删除
			function del() {
				var selects = $('#table10').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
								$.ajax({
									type : "POST",
									url : "BasPartsPositionAction!del.action",
									data : "posId=" + selects[0].posId,
									dataType : "json",
									success : function callback(r) {
										if(r.msg=="success")
										{
											    $('#table10').datagrid('clearSelections');
												$('#table10').datagrid( {
													url : 'BasPartsPositionAction!view.action',
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
				}else{
						$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){
					    });
				}
		    }

			//修改
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
							$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(r){
						    });
						}
					}
				} else {
					$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
						$('#saveOrCancelBtn').empty();
				    });
				}
			}
			
			/**
			 * 启用控件
			 */
			function nuDisAbledControl(){
				$('a.easyui-linkbutton').linkbutton('enable');
			}

			/**
			 * 禁用控件
			 */
			function disAbledControl(){
				$('a.easyui-linkbutton').linkbutton('disable');
			}

			//按钮区域增加 保存和取消按钮
			function  addButton(){
				disAbledControl();
				var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
				var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#saveOrCancelBtn').children('a').length == 0) {
					$('#saveOrCancelBtn').append(save).append(cancel);
					$.parser.parse('#saveOrCancelBtn');
				}
			}
			//取消
			function cancel() {
				nuDisAbledControl();
				 $('#table10').datagrid('unselectAll');
	       		 $('#table10').datagrid('rejectChanges');
	       		$('#saveOrCancelBtn').empty();
	       		 editRow = undefined;
			}

			/**
			  * 
			  * 导出excel
			  * @return
			  */
			 function _except(){
				exportEsuyUIExcelFile("PartsPartsCenter",null,'配件部位信息'+getSystemTime());
			 }
			