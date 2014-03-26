        var editRow=undefined;
		$(function(){
	   $('#table11').datagrid({
		  url:projectPath+'BasCarColorAction_view.action',
		  pagination:true,
		  fit:true,
		  pageSize : 10,
		  sortName:'color',
		  sortOrder:'asc',
	      pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		  fitColumns : true,
		  idField:'color',
		  rownumbers:true,
		  singleSelect:true,
		  columns : [ [ {
			title : '编号',
			field : 'color',
			width : 100,
			hidden:true
		}, {
			title : '车身颜色',
			field : 'colorName',
			width : 100,
			sortable:true,
			editor:{
                type:'validatebox',
                options:{
              		required:true,
              		validType:'multiple[\'characterDigit\',\'length[0,10]\']',
              		missingMessage : "车身颜色为必填项"
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
	     } ]],  
		          onAfterEdit:function(rowIndex, rowData, changes)
			      {
			           if(rowData.color==undefined)
			           {
				             $.ajax({
					             type:"POST",
					             url:"BasCarColorAction_add.action",
					             data:'colorName='+rowData.colorName+'&remark='+rowData.remark,
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
				            		 $.messager.alert('优亿软件优亿软件提示',r.msg,'info',function(){   
								     });
				            	 }
				               }
				             });
					    }else
					    {
					        $.ajax({
					             type:"POST",
					             url:"BasCarColorAction_update.action",
					             data:"color="+rowData.color+"&colorName="+rowData.colorName+"&remark="+rowData.remark,
					             dataType:"json",
					             success:function callback(r){
						        	 if(r.msg=="success")
						        	 {
						        		  $('#table11').datagrid('clearSelections');
						        		  $('#table11').datagrid({
											     url:projectPath+'BasCarColorAction_view.action',
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

			//车辆颜色添加事件处理
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

			//车辆颜色删除事件操作
			function del() {
				  var data = $('#table11').datagrid('getSelected');
				  var index=findSelectRowIndex('table11',data);
				  if (data) {
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
								$.ajax({
									type : "POST",
									url : "BasCarColorAction_del.action",
									data : "color=" + data.color,
									dataType : "json",
									success : function callback(r){
										if(r.msg=="success")
										{
										    $('#table11').datagrid('clearSelections');
											$('#table11').datagrid( {
												url : 'BasCarColorAction_view.action',
												loadMsg : '更新数据中......'
										    });
											setSelectRow('table11',index);
										}else
										{
											$.messager.alert('优亿软件提示',r.msg,'info',function(){
											});
										}
									}
								});
					         }
						 });
			   }else{
				   $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
			   }
		  }
			
		  //车辆颜色修改事件处理
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

		//车辆颜色保存事件处理
		function save() {
			if (editRow == undefined) {
				 $.messager.alert('优亿软件提示','当前没有要保存的记录！','info',function(){
					 $('#saveOrCancelBtn').empty();
				 });
			}else
			{
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
		//车辆颜色取消事件处理
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
	
		
		/**
		 * 
		 * 导出excel
		 * @return
		 */
		function _except(){
			exportEsuyUIExcelFile("CarColorCenter",null,'车身颜色信息'+getSystemTime());
		}