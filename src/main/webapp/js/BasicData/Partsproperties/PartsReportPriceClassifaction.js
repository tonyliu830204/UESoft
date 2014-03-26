		var editRow=undefined;
		$(function() {
	$('#table10').datagrid( {
				url : 'BasPartsReportPriceClassficationAction_view.action',
				pagination : true,
				fit : true,
				rownumbers:true,
			    singleSelect:true,
			    sortName:'partsId',
				sortOrder:'asc',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : true,
				idField : 'partsId',
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ {
					title : '编号',
					field : 'partsId',
					width : 100,
					hidden:true
				}, {
					title : '招标单位',
					field : 'partsInviteBid',
					width : 100,
					sortable:true,
					editor:{
					 	type:'validatebox',
		                options:{
		              		required:true,
		              		validType:'multiple[\'characterDigit\',\'length[0,20]\']',
		              		missingMessage : "招标单位为必填项"
		                }
		           }
				}, {
					title : '备注',
					field : 'partsSuccessfulBid',
					width : 100,
					sortable:true,
					editor:{
						type:'validatebox',
		                options:{
		              		required:false,
		              	    validType:'multiple[\'characterDigit\',\'length[0,20]\']'
		                }
					}
				}] ], /* toolbar:[
				    	         {id:'add',text:'新增',iconCls:'icon-add',handler:function(){
				    	        	 add();
				    	        	 $('#table10').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
				    	        		save();
				    	        	 }},{"text":"取消","iconCls":"icon-undo","handler":function(){
				    	        		 cancel();
				    	        	 }}])
				    	         } },
				    			 {id:'delete',text:'删除',iconCls:'icon-remove',handler:function(){
				    	        	 del();
				    			 } },
				    			 {id:'update',text:'修改',iconCls:'icon-edit',  handler:function(){
				    				 update(); 
				    	        	 $('#table10').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
				    	        		save();
				    	        	 }},{"text":"取消","iconCls":"icon-undo","handler":function(){
				    	        		 cancel();
				    	        	 }}])
				    			 } },
				    			 {id:'print',text:'导出',iconCls:'icon-export',handler:function(){
				    				 _except();
			    	             } }
				               ],*/
				onAfterEdit : function(rowIndex, rowData, changes) {
					if (rowData.partsId == undefined) {
						$.ajax( {
							type : "POST",
							url : "BasPartsReportPriceClassficationAction_add.action",
							data : rowData,
							dataType : "json",
							success : function callback(r) {
								if(r.success){
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid('load');
									cancel();
								}else{
									alerMsg(r);
									 $('#table10').datagrid('beginEdit', rowIndex);
				            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){
								     });
								}
							}
						});
						
					} else {
						$.ajax( {
							type : "POST",
							url : "BasPartsReportPriceClassficationAction_update.action",
							data : rowData,
							dataType : "json",
							success : function callback(r) {
								if(r.success)
				        	   {
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid('load');
				        		   cancel();
				        	   }else
				        	   {
				        		   
				        		   $('#table10').datagrid('beginEdit', rowIndex);
				        		   $.messager.alert('优亿软件提示',r.msg,'info',function(){});
				        	   }
							}
						});
						
						editRow = undefined;
					}
				   }
			  });
            });

			// 添加
			function add() {
				if (editRow != undefined) {
					$('#table10').datagrid('endEdit', editRow);
					$.messager.alert('优亿软件提示','您已经选择了添加按钮！','info',function(){
				    });
				} else {
					$('#add').linkbutton('disable');
					$('#delete').linkbutton('disable');
					$('#update').linkbutton('disable');
					$('#print').linkbutton('disable');
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

			function update() {
				var selects = $('#table10').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#print').linkbutton('disable');
						editRow = $('#table10').datagrid('getRowIndex', selects[0]);
						addButton();
						$('#table10').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#table10'), editRow, 0);
					} else {
						$.messager.alert('优亿软件提示','您选择了多行，只能对一行进行编辑！','info',function(){
							$('#saveOrCancelBtn').empty();
					    });
					}
				} else {
					$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','info',function(){
						$('#saveOrCancelBtn').empty();
				    });
				}
			}

			// 保存
			function save() {
				var isno=$('#table10').datagrid('validateRow',editRow);
				if(isno)
					$('#table10').datagrid('endEdit', editRow);
				else
					 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){});
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

			// 取消
			function cancel() {
				nuDisAbledControl();
				 $('#table10').datagrid('unselectAll');
	    		 $('#table10').datagrid('rejectChanges');
	    	     $('#saveOrCancelBtn').empty();
	    		 $('#add').linkbutton('enable');
				$('#delete').linkbutton('enable');
				$('#update').linkbutton('enable');
				$('#print').linkbutton('enable');
				editRow = undefined;
			}

			function del() {
				var selects = $('#table10').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						$.messager.confirm('优亿软件提示', '请确定是否删除该记录?', function(r) {
							if (r) {
								$.ajax({
									type : "POST",
									url : "BasPartsReportPriceClassficationAction_del.action",
									data : "partsId=" + selects[0].partsId,
									dataType : "json",
									success : function callback(r) {
										if(r.msg=="success"){
											$('#table10').datagrid('clearSelections');
											$('#table10').datagrid('load');
										}else
											$.messager.alert('优亿软件提示',r.msg,'info',function(){ });
									if(r.msg=="success")
									{
										$('#table10').datagrid('clearSelections');
										$('#table10').datagrid( {
											url : 'BasPartsReportPriceClassficationAction_view.action',
											loadMsg : '更新数据中......'
										});
									}else
										$.messager.alert('优亿软件提示',r.msg,'info',function(){});
								  }
								});
							}
						});
					}
				} else 
					$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','info',function(){});
			  }
			  
			  /**
				  * 
				  * 导出excel
				  * @return
				  */
				 function _except(){
					exportEsuyUIExcelFile("PartsReportPriceClassifactionCenter",null,'配件报价分类信息'+getSystemTime());
				 }