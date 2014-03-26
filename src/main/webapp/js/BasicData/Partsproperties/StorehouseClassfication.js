var editRow=undefined;
		$(function(){
	  $('#table16').datagrid({
		  url:projectPath+'BasStorehouseAction_view.action',
		  pagination:true,
		  fit:true,
		  pageSize : 10,
		  rownumbers:false,
		  sortName:'storeId',
		  sortOrder:'asc',
		  singleSelect:true,
		  singleSelect:true,
	      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		  fitColumns : true,
		  idField:'storeId',
		  rownumbers:true,
		  columns : [ [ {
			title : '编号',
			field : 'storeId',
			width : 100,
			hidden:true
		}, {
			title : '仓别名称',
			field : 'storeName',
			width : 100,
			sortable:true,
			editor:{
				 type:'validatebox',
	             options:{
	           		required:true,
	           		validType:'multiple[\'characterDigit\',\'length[0,20]\']',
	           		missingMessage : "仓别名称为必填项"
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
			        ]], /* toolbar:[
					    	         {text:'新增',iconCls:'icon-add',handler:function(){
				    	        	   add();
					    	         }},
					    			 {text:'删除',iconCls:'icon-remove',handler:function(){
					    	           del();
					    			 }},
					    			 {text:'修改',iconCls:'icon-edit',  handler:function(){
					    			   update();
					    			 }},{
				    				 text:'导出',iconCls:'icon-export',handler:function(){
					    				 _except();
				    	             }}
					               ],  */
			          onAfterEdit:function(rowIndex, rowData, changes)
				      {
				           if(rowData.storeId==undefined)
				           {
					             $.ajax({
						             type:"POST",
						             url:"BasStorehouseAction_add.action",
						             data:"storeName="+rowData.storeName+"&&remark="+rowData.remark,
						             dataType:"json",
						             success:function callback(r){
					            	 if(r.msg=="success")
					            	 {
					            		 $('#table16').datagrid('clearSelections');
					            		 $('#table16').datagrid({
										     url:projectPath+'BasStorehouseAction_view.action',
										     loadMsg:'更新数据中......'
									     });
					            		 cancel();
					            	 }else
					            	 {
					            		 $('#table16').datagrid('beginEdit', rowIndex);
					            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){
									     });
					            	 }
					               }
					             });
						    }else
						    {
						        $.ajax({
						             type:"POST",
						             url:"BasStorehouseAction_update.action",
						             data:"storeId="+rowData.storeId+"&storeName="+rowData.storeName+"&remark="+rowData.remark,
						             dataType:"json",
						             success:function callback(r){
							        	if(r.msg=="success")
							        	{
							        		$('#table16').datagrid('clearSelections');
							        		$('#table16').datagrid({
											     url:projectPath+'BasStorehouseAction_view.action',
											     loadMsg:'更新数据中......'
										     });
							        		cancel();
							        	}else
							        	{
							        		 $('#table16').datagrid('beginEdit', rowIndex);
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
				if (editRow!= undefined) {
					$('#table16').datagrid('endEdit', editRow);
					$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
				    });
				} else {
					$('#table16').datagrid('insertRow', {
						index : 0,
						row : {}
					});
					addButton();
					$('#table16').datagrid('beginEdit', 0);
					editRow = 0;
					bindEnterInCloumn($('#table16'), editRow, 0);
				}
			}
			
			// 保存
			function save() {
				if (editRow == undefined) {
					$.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						$('#saveOrCancelBtn').empty();
				    });
				}else
				{
					var isno=$('#table16').datagrid('validateRow',editRow);
					if(isno)
					{
						$('#table16').datagrid('endEdit', editRow);
						
					}else
					{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
						 });
					}
				}
			}	
			
			function del() {
				var selects = $('#table16').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
								$.ajax( {
									type : "POST",
									url : "BasStorehouseAction_del.action",
									data : "storeId=" + selects[0].storeId,
									dataType : "json",
									success : function callback(r) {
									     if(r.msg=="success")
									     {
									    	$('#table16').datagrid('clearSelections');
											$('#table16').datagrid( {
												url : 'BasStorehouseAction_view.action',
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
				var selects = $('#table16').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						var isno=$('#table16').datagrid('validateRow',editRow);
						if(isno)
						{
							editRow = $('#table16').datagrid('getRowIndex', selects[0]);
							$('#table16').datagrid('beginEdit', editRow);
							bindEnterInCloumn($('#table16'), editRow, 0);
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

			//取消
			function cancel() {
				nuDisAbledControl();
				 $('#table16').datagrid('unselectAll');
	       		 $('#table16').datagrid('rejectChanges');
	       		$('#saveOrCancelBtn').empty();
	       		 editRow = undefined;
			}
			
			 /**
			  * 
			  * 导出excel
			  * 选择要导出的列
			  * 参数1   dateGrid控件id属性
			  * 参数2   dateGrid控件对应数据库类型
			  * 参数3   dateGrid控件上层控件id属性
			  * 参数4   执行按钮value文本
			  * 参数5   title文本
			  * 参数6   功能类型    0导出   1导入   2打印    3隐藏列
			  * 参数7   回调函数
			  * @return
			  */
			 function _except(){
			 	showEditDialog("table16",null,"StorehouseClassficationCenter","开始导出","导出配置",0,_callbackExcept);
			 }
			 
			 /**
			  * 导出excel回调函数
			  * 将筛选出的列导出到Excel文件
			  * 只支持Microsoft Office,不支持WPS
			  * @param parentId
			  * @param fieldNames  要导出的列字段
			  * @return
			  */
			 function _callbackExcept(parentId,fieldNames){
			 	exportEsuyUIExcelFile(parentId,fieldNames,"仓别分类信息"+getSystemTime());
			 }
		 
			
