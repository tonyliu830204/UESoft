var editRow=undefined;
var $carTypeInfo = $('#carTypeInfo');
		$(function(){
			// 基础资料->车辆型号资料
	  		$carTypeInfo.datagrid({
	  			url : 'basCarTypeInfo_findAll.action',
	  			singleSelect : true,
	  			fit : true,
	  			fitColumns : true,
	  			pagination : true,
	  			rownumbers : true,
	  			sortName : 'ctypeId',
	  			sortOrder : 'asc',
	  			columns : [[
	  			        {
	  						field : 'ctypeId',
	  						title : '编号',
	  						width : 60,
	  						hidden : true
	  					},{
	  						field : 'cbrdId',
	  						title : '车辆品牌',
	  						width : 60,
	  						sortable : true,
	  						editor : {
	  							type : 'combobox',
	  							options : {
	  								url : 'basCarTypeInfo_findAllCarBrand.action',
	  								required : true,
	  								validType:'length[0,20]',
	  								valueField:'id',  
	  							    textField:'text',  
	  								missingMessage : "车辆品牌为必选项"
	  							}
	  						},
	  						formatter: function(value,row,index){
	  							return row.cbrdName;
	  						}
	  					},{
	  						field : 'cbrdName',
	  						title : '车辆品牌',
	  						width : 60,
	  						hidden : true
	  					},{
	  						field : 'ctypeName',
	  						title : '车辆型号',
	  						width : 60,
	  						sortable : true,
	  						editor : {
	  							type : 'validatebox',
	  							options : {
	  								required : true,
	  								 validType:'multiple[\'characterDigit\',\'length[0,50]\']',
	  								missingMessage : "车辆型号为必填项"
	  							}
	  						}
	  					},{
	  						field : 'ctypePrice',
	  						title : '工时单价',
	  						width : 60,
	  						sortable : true,
	  						editor : {
	  							type : 'numberbox',
	  							options : {
	  								min:0,  
	  							    precision:2,
	  							    validType:'length[0,12]',
	  								missingMessage : "工时单价为必填项"
	  							}
	  						}
	  					}
	  	            ]],
	  	    		onAfterEdit:function(rowIndex, rowData, changes)
				    {
				           if(rowData.ctypeId==undefined)
				           {
					             $.ajax({
						             type:"POST",
						             url:"basCarTypeInfo_save.action",
						             data:"ctypeName="+rowData.ctypeName+"&ctypePrice="+rowData.ctypePrice+"&cbrdId="+rowData.cbrdId,
						             dataType:"json",
						             success:function callback(r){
					            	 if(r.success)
					            	 {
					            		 $('#carTypeInfo').datagrid('clearSelections');
					            		 $('#carTypeInfo').datagrid('load');
					            		 cancel();
					            	 }else
					            	 {
					            		 $('#carTypeInfo').datagrid('beginEdit',rowIndex);
					            		 $.messager.alert('优亿软件提示',r.msg,'info'); 
					            	 }
					               }
					             });
						    }else
						    {
						         $.ajax({
						             type:"POST",
						             url:"basCarTypeInfo_edit.action",
						             data:"ctypeId=" + rowData.ctypeId +"&ctypeName="+rowData.ctypeName+"&ctypePrice="+rowData.ctypePrice+"&cbrdId="+rowData.cbrdId,
						             dataType:"json",
						             success:function callback(r){
						        	   if(r.success)
						        	   {
						        		   $('#carTypeInfo').datagrid('clearSelections');
						        		   $('#carTypeInfo').datagrid({
											     url:'basCarTypeInfo_findAll.action',
											     loadMsg:'更新数据中......'
										    });
						        		   cancel();
						        	   }else
						        	   {
						        		   $('#carTypeInfo').datagrid('beginEdit',rowIndex);
						        		   $.messager.alert('优亿软件提示',r.msg,'info');
						        	   }
					                 }
					             });
						       }
				            }
	  		});
		});
			
	     	// 车辆型号添加事件处理
		    function add() {
//		    	$carTypeInfo.datagrid('addEditor', {
//  					field : 'cbrdId',
//  					editor : {
//  						type : 'combobox',
//  						options : {
//								url : 'basCarTypeInfo_findAllCarBrand.action',
//								required : true,
//								mode : 'remote',
//								valueField:'id',  
//							    textField:'text',  
//								missingMessage : "车辆品牌为必选项",
//								onChange:function(newValue,oldValue){
//		  	    					$.ajax({
//		  	    						type : 'post',
//		  	    						dataType : 'json',
//		  	    						url : 'basCarTypeInfo_getCarTypeInfo.action',
//		  	    						data : 'cbrdId='+newValue,
//		  	    						success : function(r) {
//		  	    								var ed = $carTypeInfo.datagrid('getEditor', {index:0,field:'ctypePrice'});
//		  	    								ed.target.numberbox('setValue',r.cbrdPrice);
//		  	    						}
//		  	    					});
//	  	    				    }
//							}
//  					}
//  				});
				if (editRow != undefined) {
					 $('#carTypeInfo').datagrid('endEdit', editRow);
					 $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning');
				}else{
					$('#add').linkbutton('disable');
					$('#delete').linkbutton('disable');
					$('#update').linkbutton('disable');
					$('#export').linkbutton('disable');
					$('#query').linkbutton('disable');
					$('#clear').linkbutton('disable');
					 $('#carTypeInfo').datagrid('insertRow', {
						index : 0, 
						row : {}
					 });
					  addButton();
					 $('#carTypeInfo').datagrid('beginEdit', 0);
					 
					 editRow = 0;
					 bindEnterInCloumn($('#carTypeInfo'), editRow, 0);
				}
	        }
		    
            // 车辆型号保存事件处理
			function save() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#carTypeInfo').datagrid('validateRow',editRow);
					var ed = $('#carTypeInfo').datagrid('getEditor', {index:editRow,field:'cbrdId'});
					var value = $(ed.target).combobox('getValue');
					var text = $(ed.target).combobox('getText');
					if(isno && value != text){
						$('#carTypeInfo').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
	
			 // 车辆型号保存事件处理
			function save1() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#carTypeInfo').datagrid('validateRow',editRow);
					if(isno){
						$('#carTypeInfo').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
			
			// 配件型号删除操作
		    function del() {
		        var data = $('#carTypeInfo').datagrid('getSelected');
		        var index=findSelectRowIndex('carTypeInfo',data);
		       if (data) {
		         $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
					if (r) {
							 $.ajax({
									type : "POST",
									url : "basCarTypeInfo_delete.action",
									data : "ctypeId=" + data.ctypeId,
									dataType : "json",
									success : function callback(r){
										if(r.success)
										{
											$('#carTypeInfo').datagrid('clearSelections');
											$('#carTypeInfo').datagrid( {
												url : 'basCarTypeInfo_findAll.action',
												loadMsg : '更新数据中......'
											});
											setSelectRow('carTypeInfo',index);
										}else
										{
											$.messager.alert('优亿软件提示',r.msg,'info');
										}
									}
								});
					         }
				 });
	          }else{
	        	  $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
	          }
	    }
	
		// 车辆型号修改事件处理
		function update() {
			var selects = $('#carTypeInfo').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1)
				{
					var isno=$('#carTypeInfo').datagrid('validateRow',editRow);
					if(isno)
					{
						$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#export').linkbutton('disable');
						$('#query').linkbutton('disable');
						$('#clear').linkbutton('disable');
						editRow = $('#carTypeInfo').datagrid('getRowIndex', selects[0]);
						$('#carTypeInfo').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#carTypeInfo'), editRow, 0);
						addButton(1);
					}else
					{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
					    });
					}
				}
			}else {
				$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(r){
					$('#saveOrCancelBtn').empty();
				 });
			}
		}
		function queryType(){
			$ctibForm = $('#ctibForm').form();
				_search($ctibForm, $carTypeInfo);
		}
		function _clear(){
			$('#ctibForm').form('clear');
				$ctibForm = $('#ctibForm').form();
				_search($ctibForm, $carTypeInfo);
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
		
		// 取消事件处理
		function cancel()
		{
			 $('#carTypeInfo').datagrid('unselectAll');
    		 $('#carTypeInfo').datagrid('rejectChanges');
    		 $('#saveOrCancelBtn').empty();
    		 $('#add').linkbutton('enable');
			$('#delete').linkbutton('enable');
			$('#update').linkbutton('enable');
			$('#export').linkbutton('enable');
			$('#query').linkbutton('enable');
			$('#clear').linkbutton('enable');
    		 editRow = undefined;
		}
		
		/**
		 * 
		 * 导出excel
		 * @return
		 */
		function _except(){
			exportEsuyUIExcelFile("carTypeInfoDiv",null,'车辆型号信息'+getSystemTime());
		}
