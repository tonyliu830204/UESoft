var editRow=undefined;
		$(function(){
			// 基础资料->车辆款式资料
			$carStyleTable = $('#CarStyleTable');
	  		
	  		$carStyleTable.datagrid({
	  			url : 'BasCarStyleAction_view.action',
	  			singleSelect : true,
	  			fit : true,
	  			sortName:'carCstlName',
	  			sortOrder:'asc',
	  			fitColumns : true,
	  			pagination : true,
	  			rownumbers : true,
	  			columns : [[
	  	            {
  						field : 'carCstlName',
  						title : '款式编号',
  						width : 60,
  						hidden : true
  					},{
  						field : 'ctypeId',
  						title : '车辆型号',
  						width : 60,
  						sortable : true,
  						editor : {
  							type : 'combobox',
  							options : {
  								url : 'basCarStyleAction_findAllCarType.action',
  								required : true,
  								validType:'length[0,50]',
  								mode : 'remote',
  								valueField:'id',
  							    textField:'text',  
  								missingMessage : "车辆型号为必选项"
  							}
  						},
  						formatter: function(value,row,index){ 
  							return row.ctypeName;
  						}
  					},{
  						field : 'ctypeName',
  						title : '车辆型号',
  						width : 60,
  						hidden : true
  					},{
  						field : 'cstlName',
  						title : '车辆款式',
  						width : 60,
  						sortable : true,
  						editor : {
  							type : 'validatebox',
  							options : {
  								required : true,
  								validType:'multiple[\'characterDigit\',\'length[0,50]\']',
  								missingMessage : "车辆款式为必填项"
  							}
  						}
  					},{
  						field : 'ctypePrice',
  						title : '工时单价',
  						width : 60,
  						sortable : true,
  						editor:{
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
				           if(rowData.carCstlName==undefined)
				           {
					             $.ajax({
						             type:"POST",
						             url:"BasCarStyleAction_add.action",
						             data:"ctypeId="+rowData.ctypeId+"&cstlName="+rowData.cstlName+"&ctypePrice="+rowData.ctypePrice,
						             dataType:"json",
						             success:function callback(r){
					            	 if(r.success)
					            	 {
					            		 $carStyleTable.datagrid('clearSelections');
					            		 $carStyleTable.datagrid('load');
					            		 cancel();
					            	 }else
					            	 {
					            		 $carStyleTable.datagrid('beginEdit', rowIndex);
					            		 $.messager.alert('优亿软件提示',r.msg,'info'); 
					            	 }
					               }
					             });
						    }else
						    {
						        $.ajax({
						             type:"POST",
						             url:"BasCarStyleAction_update.action",
						             data:"carCstlName=" + rowData.carCstlName +"&ctypeId="+rowData.ctypeId+"&cstlName="+rowData.cstlName+"&ctypePrice="+rowData.ctypePrice,
						             dataType:"json",
						             success:function callback(r){
						        	   if(r.success)
						        	   {
						        		   $carStyleTable.datagrid('clearSelections');
						        		   $carStyleTable.datagrid({
											     url:projectPath+'BasCarStyleAction_view.action',
											     loadMsg:'更新数据中......'
										   });
						        		  
						        		   cancel();
						        	   }else
						        	   {
						        		   $carStyleTable.datagrid('beginEdit', rowIndex);
						        		   $.messager.alert('优亿软件提示',r.msg,'info');
						        	   }
					                 }
					             });
						       }
				            }
	  		});
		});

			
	     	// 车辆款式添加事件处理
		    function add() {
		    	$carStyleTable.datagrid('addEditor', {
  					field : 'ctypeId',
  					editor : {
  						type : 'combobox',
  						options : {
								url : 'BasCarStyleAction_findAllCarType.action',
								required : true,
								mode : 'remote',
								valueField:'id',  
							    textField:'text',  
								missingMessage : "车辆型号为必选项"
							}
  					}
  				});
				if (editRow != undefined) {
					 $carStyleTable.datagrid('endEdit', editRow);
					 $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning');
				}else{
					$('#add').linkbutton('disable');
					$('#delete').linkbutton('disable');
					$('#update').linkbutton('disable');
					$('#export').linkbutton('disable');
					$carStyleTable.datagrid('insertRow', {
						index : 0, 
						row : {ctypePrice:0.0}
					 });
					 addButton();
					$carStyleTable.datagrid('beginEdit', 0);
					editRow = 0;
					bindEnterInCloumn($('#CarStyleTable'), editRow, 0);
					/*var ed = $carStyleTable.datagrid('getEditors', 0);
					ed[0].target.combobox('reload','BasCarStyleAction_findAllCarType.action');*/
				}
	        }
		    
            // 车辆款式保存事件处理
			function save() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#CarStyleTable').datagrid('validateRow',editRow);
					var ed = $('#CarStyleTable').datagrid('getEditor', {index:editRow,field:'ctypeId'});
					var value = $(ed.target).combobox('getValue');
					var text = $(ed.target).combobox('getText');
					if(isno && value != text){
						$('#CarStyleTable').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
			
			 // 车辆款式保存事件处理
			function save1() {
				if (editRow == undefined) {
					 $.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					 });
				}else
				{
					var isno=$('#CarStyleTable').datagrid('validateRow',editRow);
					if(isno){
						$('#CarStyleTable').datagrid('endEdit', editRow);
					}else{
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning');
					}
			    }	
		    }
	
			// 车辆款式删除操作
		function del() {
		       var data = $carStyleTable.datagrid('getSelected');
		       var index=findSelectRowIndex('CarStyleTable',data);
		       if (data) {
	    	    $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
					if (r) {
							 $.ajax({
									type : "POST",
									url : "BasCarStyleAction_del.action",
									data : "carCstlName=" + data.carCstlName,
									dataType : "json",
									success : function callback(r){
										if(r.success){
											$carStyleTable.datagrid('clearSelections');
											$carStyleTable.datagrid('load');
											setSelectRow('CarStyleTable',index);
										}else{
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
	
		// 车辆款式修改事件处理
		function update() {
			$carStyleTable.datagrid('removeEditor', [ 'ctypeId' ]);
			var selects = $carStyleTable.datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1)
				{
					var isno=$carStyleTable.datagrid('validateRow',editRow);
					if(isno)
					{
						$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#export').linkbutton('disable');
						editRow = $carStyleTable.datagrid('getRowIndex', selects[0]);
						$carStyleTable.datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#CarStyleTable'), editRow, 0);
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
		
		//按钮区域增加 保存和取消按钮
		function  addButton(i){
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
			$carStyleTable.datagrid('unselectAll');
			$carStyleTable.datagrid('rejectChanges');
			$('#saveOrCancelBtn').empty();
			$('#add').linkbutton('enable');
			$('#delete').linkbutton('enable');
			$('#update').linkbutton('enable');
			$('#export').linkbutton('enable');
    		editRow = undefined;
		}
		
		/**
		 * 
		 * 导出excel
		 * @return
		 */
		function _except(){
			exportEsuyUIExcelFile("CarStyleCenter",null,'车辆款式信息'+getSystemTime());
		}
	