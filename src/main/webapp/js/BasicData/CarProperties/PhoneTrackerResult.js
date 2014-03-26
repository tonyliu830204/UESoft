    var editRow=undefined;
    $(function(){
	$('#table11').datagrid({
		 url:projectPath+'BasCarPhoneTrackerResultAction_view.action',
		  idField:'carPhonetrackerresultId',
		  pagination:true,
		  rownumbers:true,
		  sortName:'carPhonetrackerresultId',
		  sortOrder:'asc',
		  singleSelect:true,
		  fit:true,
		  pageSize : 10,
	      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		  fitColumns : true, 
	    columns:[[
	              {
			title : '编号',
			field : 'carPhonetrackerresultId',
			width : 100,
			hidden:true
		}, {
			title : '电话跟踪结果',
			field : 'carPhonetrackerresultName',
			width : 100,
			sortable:true,
			editor:{
				type:'validatebox',
	            options:{
	          		required:true,
	          		validType:'multiple[\'characterDigit\',\'length[0,20]\']',
	          		missingMessage : "电话跟踪结果为必填项"
	            }
			}
		}, {
			title : '备注',
			field : 'carPhonetrackerresultRemark',
			width : 100,
			sortable:true,
			editor:{
				type:'validatebox',
	            options:{
	          		required:false,
	          		validType:'multiple[\'characterDigit\',\'length[0,50]\']',
	            }
		    }
		}
	              ]],
		          onAfterEdit:function(rowIndex, rowData, changes)
			      {
			           if(rowData.carPhonetrackerresultId==undefined)
			           {
				             $.ajax({
					             type:"POST",
					             url:"BasCarPhoneTrackerResultAction_add.action",
					             data:"carPhonetrackerresultName="+rowData.carPhonetrackerresultName+"&carPhonetrackerresultRemark="+rowData.carPhonetrackerresultRemark,
					             dataType:"json",
					             success:function callback(r){
				            	 if(r.msg=="success")
				            	 {
				            		 $('#table11').datagrid('clearSelections');
				            		 $('#table11').datagrid('load');
				            		 cancel();
				            	 }else
				            	 {
				            		  $('#table11').datagrid('beginEdit', rowIndex);
				            		  $.messager.alert('优亿软件提示',r.msg,'info',function(){
									  }); 
				            	 }
				               }
				             });
					    }else
					    {
					        $.ajax({
					             type:"POST",
					             url:projectPath+"BasCarPhoneTrackerResultAction_update.action",
					             data:"carPhonetrackerresultId="+rowData.carPhonetrackerresultId+"&carPhonetrackerresultName="+rowData.carPhonetrackerresultName+"&carPhonetrackerresultRemark="+rowData.carPhonetrackerresultRemark,
					             dataType:"json",
					             success:function callback(r){
					        	   if(r.msg=="success")
					        	   {
					        		   $('#table11').datagrid('clearSelections');
					        		   $('#table11').datagrid({
										     url:projectPath+'BasCarPhoneTrackerResultAction_view.action',
										     loadMsg:'更新数据中......'
									     });
					        		   cancel();
					        	   }else
					        	   {
					        		   $('#table11').datagrid('beginEdit', rowIndex);
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
					$('#table11').datagrid('endEdit', editRow);
					$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
					});
				} else {
					$('#add').linkbutton('disable');
					$('#delete').linkbutton('disable');
					$('#update').linkbutton('disable');
					$('#export').linkbutton('disable');
					$('#table11').datagrid('insertRow', {
						index : 0, 
						row : {}
					});
					addButton();
					$('#table11').datagrid('beginEdit', 0);
					 editRow = 0;
					 bindEnterInCloumn($('#table11'), editRow, 0);
				}
			}
			
			function update() {
				var selects = $('#table11').datagrid('getSelections');
				if (selects.length > 0) {
					if (selects.length == 1) {
						var isno=$('#table11').datagrid('validateRow',editRow);
						if(isno)
						{
							$('#add').linkbutton('disable');
							$('#delete').linkbutton('disable');
							$('#update').linkbutton('disable');
							$('#export').linkbutton('disable');
							editRow = $('#table11').datagrid('getRowIndex', selects[0]);
							$('#table11').datagrid('beginEdit', editRow);
							bindEnterInCloumn($('#table11'), editRow, 0);
							addButton();
						}else
						{
							$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
						   });
						}
					}
				} else {
					$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
						 $('#saveOrCancelBtn').empty();
					});
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
					var isno=$('#table11').datagrid('validateRow',editRow);
					var isno=$('#table11').datagrid('validateRow',editRow);
					if(isno)
					{
						$('#table11').datagrid('endEdit', editRow);
					}else
					{
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){
						 });
					}
				}
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
			
			//取消
			function cancel() {
				 $('#table11').datagrid('unselectAll');
        		 $('#table11').datagrid('rejectChanges');
        		 $('#saveOrCancelBtn').empty();
        		 $('#add').linkbutton('enable');
				$('#delete').linkbutton('enable');
				$('#update').linkbutton('enable');
				$('#export').linkbutton('enable');
        		 editRow = undefined;
			}
			
			function del() {
			var selects = $('#table11').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1) {
					$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
						if (r) {
							$.ajax( {
								type : "POST",
								url : "BasCarPhoneTrackerResultAction_del.action",
								data : "carPhonetrackerresultId=" + selects[0].carPhonetrackerresultId,
								dataType : "json",
								success : function callback(r) {
								 if(r.msg=="success")
								 {
									    $('#table11').datagrid('clearSelections');
										$('#table11').datagrid( {
											url : 'BasCarPhoneTrackerResultAction_view.action',
											loadMsg : '更新数据中......'
										});
								 }else{
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
			
			
		 /**
		  * 
		  * 导出excel
		  * @return
		  */
		 function _except(){
			exportEsuyUIExcelFile("PhoneTrackerResultCenter",null,'电话跟踪回访信息'+getSystemTime());
		 }