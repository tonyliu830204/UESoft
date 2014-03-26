var editRow=undefined;
		$(function(){
			//基础资料->配件型号资料
	   		$mountingsTypeInfo = $('#mountingsTypeInfo');
	   		
	   		$mountingsTypeInfo.datagrid({
	   			url : 'basMountingsTypeInfo_findAll.action',
	   			singleSelect : true,
	   			fit : true,
	   			fitColumns : true,
	   			pagination : true,
	   			sortName:'ptypeId',
	  		    sortOrder:'asc',
	   			rownumbers : true,
	   			columns : [[
	   	         {
	   				field : 'ptypeId',
	   				title : '编号',
	   				width : 60,
	   				hidden : true
	   			}, {
	   				field : 'pbrdId',
	   				title : '配件品牌',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'combobox',
	   					options : {
	   						url : 'basMountingsTypeInfo_findAllPartsBrand.action',
	   						required : true,
	   						valueField:'id',  
	   					    textField:'text',
	   					    mode:'remote',
	   						missingMessage : '配件品牌为必选项'
	   					}
	   				},
	   				formatter: function(value,row,index){
	   					return row.pbrdName;
	   				}
	   			},{
	   				field : 'pbrdName',
	   				title : '配件品牌',
	   				width : 60,
	   				hidden : true
	   			}, {
	   				field : 'ptypeName',
	   				title : '配件型号',
	   				width : 60,
	   				sortable : true,
	   				editor : {
		   			 	type:'validatebox',
		                options:{
		              		required:true,
		              		validType:'multiple[\'characterDigit\',\'length[0,50]\']',
		              		missingMessage : "配件型号为必填项"
		                }
	   				}
	   			}, {
	   				field : 'repairRate',
	   				title : '维修加价率',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'numberbox',
	   					options : {
	   						precision : 4,
	   						min : 0,
	   						max : 999.99,
	   						missingMessage : "维修加价率为必填项"
	   					}
	   				}
	   			}, {
	   				field : 'sellRate',
	   				title : '销售加价率',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'numberbox',
	   					options : {
	   						precision : 4,
	   						min : 0,
	   						max : 999.99,
	   						missingMessage : "销售加价率为必填项"
	   					}
	   				}
	   			}, {
	   				field : 'pointRate',
	   				title : '网点加价率',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'numberbox',
	   					options : {
	   						precision : 4,
	   						min : 0,
	   						max : 999.99,
	   						missingMessage : "网点加价率为必填项"
	   					}
	   				}
	   			}, {
	   				field : 'specialRate',
	   				title : '特殊加价率',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'numberbox',
	   					options : {
	   						precision : 4,
	   						min : 0,
	   						max : 999.99,
	   						missingMessage : "特殊加价率为必填项"
	   					}
	   				}
	   			}, {
	   				field : 'claimRate',
	   				title : '索赔加价率',
	   				width : 60,
	   				sortable : true,
	   				editor : {
	   					type : 'numberbox',
	   					options : {
	   						precision : 4,
	   						min : 0,
	   						max : 999.99,
	   						missingMessage : "索赔加价率为必填项"
	   					}
	   				}
	   			}]]/*,
	   	         toolbar:[{
	   	 			text:'新增',
	   	 			iconCls:'icon-add',
	   	 			handler: function (){
	   	 				$mountingsTypeInfo.datagrid('addEditor', {
	    					field : 'pbrdId',
	    					editor : {
	    						type : 'combobox',
	    						options : {
	    	   						url : 'basMountingsTypeInfo_findAllPartsBrand.action',
	    	   						required : true,
	    	   						valueField:'id',  
	    	   					    textField:'text',
	    	   					    mode:'remote',
	    	   						missingMessage : '配件品牌为必选项'
	    	   					}
	    					}
	    				});
	   	 				add();
	   	 			}
	   	 		}/,{
	   	 			text:'删除',
	   	 			iconCls:'icon-remove',
	   	 			handler: function (){
	   	 				del();
	   	 			}
	   	 		},{
	   	 			text:'修改',
	   	 			iconCls:'icon-edit',
	   	 			handler: function (){
	   	 				$mountingsTypeInfo.datagrid('removeEditor', [ 'pbrdId' ]);
	   	 				update();
	   	 			}
	   	 		},{
	   	 			text:'查询',
	   	 			iconCls:'icon-search',
	   	 			handler: function (){
	   	 				$mtiForm = $('#mtiForm').form();
	   	 				_search($mtiForm, $mountingsTypeInfo);
	   	 			}
	   	 		},{
	   	 			text:'清空',
	   	 			iconCls:'icon-cancel',
	   	 			handler: function (){
	   	 				$('#mtiForm').form('clear');
	   	 			}
	   	 		},{
	   	 			text:'导出',
	   	 			iconCls:'icon-export',
	   	 			handler: function (){
	   	 		        _except();
	   	 			}
	   	 		}]*/,
	   	 	onAfterEdit:function(rowIndex, rowData, changes)
		       {
		           if(rowData.ptypeId==undefined)
		           {
			             $.ajax({
				             type:"POST",
				             url:"basMountingsTypeInfo_save.action",
				             data:rowData,
				             dataType:"json",
				             success:function callback(r){
			            	 if(r.success)
			            	 {
			            		 $('#mountingsTypeInfo').datagrid('clearSelections');
			            		 $('#mountingsTypeInfo').datagrid('load');
			            			cancel();
			            	 }else
			            	 {
			            		 $('#mountingsTypeInfo').datagrid('beginEdit', rowIndex);
			            		 $.messager.alert('优亿软件提示',r.msg,'info'); 
			            	 }
			               }
			             });
				    }else
				    {
				        $.ajax({
				             type:"POST",
				             url:"basMountingsTypeInfo_edit.action",
				             data:rowData,
				             dataType:"json",
				             success:function callback(r){
				        	   if(r.success)
				        	   {
				        		   $('#mountingsTypeInfo').datagrid('clearSelections');
				        		   $('#mountingsTypeInfo').datagrid({
									     url:projectPath+'basMountingsTypeInfo_findAll.action',
									     loadMsg:'更新数据中......'
								    });
				        			cancel();
				        	   }else
				        	   {
				        		   $('#mountingsTypeInfo').datagrid('beginEdit', rowIndex);
				        		   $.messager.alert('优亿软件提示',r.msg,'info');
				        	   }
			                 }
			             });
				       }
		            }
	   		});
		});
			function query(){
				$mtiForm = $('#mtiForm').form();
	 				_search($mtiForm, $mountingsTypeInfo);
			}
			function clearTypeInfo(){
				$('#mtiForm').form('clear');
				_search($mtiForm, $mountingsTypeInfo);
			}
	     	// 配件型号添加事件处理
		    function add() {
		    	$mountingsTypeInfo.datagrid('addEditor', {
					field : 'pbrdId',
					editor : {
						type : 'combobox',
						options : {
	   						url : 'basMountingsTypeInfo_findAllPartsBrand.action',
	   						required : true,
	   						valueField:'id',  
	   					    textField:'text',
	   					    mode:'remote',
	   						missingMessage : '配件品牌为必选项'
	   					}
					}
				});
				if (editRow != undefined) {
					 $('#mountingsTypeInfo').datagrid('endEdit', editRow);
					 $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning');
				}else{
					 $('#mountingsTypeInfo').datagrid('insertRow', {
						index : 0, 
						row : {}
					 });
					 addButton();
					 $('#mountingsTypeInfo').datagrid('beginEdit', 0);
					 editRow = 0;
					 bindEnterInCloumn($('#mountingsTypeInfo'), editRow, 0);
				}
	        }
		    
            // 配件型号保存事件处理
			function save() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#mountingsTypeInfo').datagrid('validateRow',editRow);
					var ed = $('#mountingsTypeInfo').datagrid('getEditor', {index:editRow,field:'pbrdId'});
					var value = $(ed.target).combobox('getValue');
					var text = $(ed.target).combobox('getText');
					if(isno && value != text){
						$('#mountingsTypeInfo').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
	
			 
            // 配件型号保存事件处理
			function save1() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#mountingsTypeInfo').datagrid('validateRow',editRow);
					if(isno){
						$('#mountingsTypeInfo').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
			
			// 配件型号删除操作
		    function del() {
		        var selects = $('#mountingsTypeInfo').datagrid('getSelections');
		        if (selects.length > 0) {
			       if (selects.length == 1) {
			    	   
			    	   $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
									 $.ajax({
											type : "POST",
											url : "basMountingsTypeInfo_delete.action",
											data : "ptypeId=" + selects[0].ptypeId,
											dataType : "json",
											success : function callback(r){
												if(r.success)
												{
													$('#mountingsTypeInfo').datagrid('clearSelections');
													$('#mountingsTypeInfo').datagrid( {
														url : 'basMountingsTypeInfo_findAll.action',
														loadMsg : '更新数据中......'
													});
												}else
												{
													$.messager.alert('优亿软件提示',r.msg,'info');
												}
											}
										});
							         }
						 });
					    
			          }
			   }else{
				 $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
			   }
	    }
	
		// 配件型号修改事件处理
		function update() {
			$mountingsTypeInfo.datagrid('removeEditor', [ 'pbrdId' ]);
			var selects = $('#mountingsTypeInfo').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1)
				{
					var isno=$('#mountingsTypeInfo').datagrid('validateRow',editRow);
					if(isno)
					{
						editRow = $('#mountingsTypeInfo').datagrid('getRowIndex', selects[0]);
						$('#mountingsTypeInfo').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#mountingsTypeInfo'), editRow, 0);
						 addButton(1);
					}else
					{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
					    });
					}
				}
			}else {
				$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(r){
						$('#mountingsTypeInfo').datagrid('removeToolbarItem', '保存');
						$('#mountingsTypeInfo').datagrid('removeToolbarItem', '取消');
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
		function  addButton(i){
			disAbledControl();
			if(i==1){
				var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save1();">保存</a>';
			}else{
				var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
			}
			var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#saveOrCancelBtn').children('a').length == 0) {
				$('#saveOrCancelBtn').append(save).append(cancel);
				$.parser.parse('#saveOrCancelBtn');
			}
		}

		
		// 取消事件处理
		function cancel()
		{
			 nuDisAbledControl();
			 $('#mountingsTypeInfo').datagrid('unselectAll');
    		 $('#mountingsTypeInfo').datagrid('rejectChanges');
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
		 	showEditDialog("mountingsTypeInfo",null,"mountingsTypeInfoDiv","开始导出","导出配置",0,_callbackExcept);
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
		 	exportEsuyUIExcelFile(parentId,fieldNames,"配件型号信息"+getSystemTime());
		 }
		 
		 
		 