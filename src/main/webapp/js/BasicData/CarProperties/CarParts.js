var editRow = undefined;

$(function() {
	  $('#table10').datagrid(
			{
				url : 'BasCarPartsAction_view.action',
				pagination : true,
				fit : true,
				fitColumns : true,
				sortName:'carPartId',
				sortOrder:'asc',
				singleSelect : true,
				rownumbers : true,
				columns : [ [ {
					field : 'carPartId',
					title : '编号',
					width : 60,
					hidden : true
				}, {
					field : 'carPartName',
					title : '车辆部位',
					width : 100,
					sortable : true,
					editor : {
						type:'validatebox',
		                options:{
		              		required:true,
		              		 validType:'multiple[\'characterDigit\',\'length[0,20]\']',
		              		 missingMessage : "车辆部位为必填项"
		                }
					}
				}, {
					field : 'carPartRemark',
					title : '备注',
					width : 60,
					sortable : true,
					editor : {
						type:'validatebox',
			            options:{
		              		required:false,
		              		validType:'length[0,50]'
		                }
					}
				} ] ],
				onAfterEdit : function(rowIndex, rowData, changes) {
					if (rowData.carPartId == undefined) {
						$.ajax( {
							type : "POST",
							url : "BasCarPartsAction_add.action",
							data : "carPartName=" + rowData.carPartName
									+ "&carPartRemark="
									+ rowData.carPartRemark,
							dataType : "json",
							success : function callback(r) {
								if(r.msg=="success")
								{
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid('load');
									cancel();
								}else
								{
									$('#table10').datagrid('beginEdit', rowIndex);
									$.messager.alert('优亿软件提示', r.msg, 'info', function() {
									});
								}
							}
						});
					} else {
						$.ajax({
							type : "POST",
							url : "BasCarPartsAction_update.action",
							data : "carPartId=" + rowData.carPartId
									+ "&carPartName=" + rowData.carPartName
									+ "&carPartRemark="
									+ rowData.carPartRemark,
							dataType : "json",
							success : function callback(r) {
								if(r.msg=="success")
								{
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid( {
										url : 'BasCarPartsAction_view.action',
										loadMsg : '更新数据中......'
									});
									cancel();
								}else
								{
									$('#table10').datagrid('beginEdit', rowIndex);
									$.messager.alert('优亿软件提示', r.msg, 'info', function() {
									});
								}
							}
						});	
					}
				}
			});
         });

		function add() {
			if (editRow != undefined) {
				$('#table10').datagrid('endEdit', editRow);
				 $.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
				  });
			} else {
				$('#add').linkbutton('disable');
				$('#delete').linkbutton('disable');
				$('#update').linkbutton('disable');
				$('#export').linkbutton('disable');
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

		

		  function del() {
			var selects = $('#table10').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1) {
					$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
						if (r) {
							$.ajax( {
								type : "POST",
								url : "BasCarPartsAction_del.action",
								data : "carPartId=" + selects[0].carPartId,
								dataType : "json",
								success : function callback(r) {
								if (r.msg="success") {
									$('#table10').datagrid('clearSelections');
										$('#table10').datagrid( {
											url : 'BasCarPartsAction_view.action',
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
						$('#add').linkbutton('disable');
						$('#delete').linkbutton('disable');
						$('#update').linkbutton('disable');
						$('#export').linkbutton('disable');
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
					 $('#saveOrCancelBtn').empty();
				  });
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
		
		function cancel() {
		$('#table10').datagrid('unselectAll');
   		 $('#table10').datagrid('rejectChanges');
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
			exportEsuyUIExcelFile("CarPartsCenter",null,'车辆部位信息'+getSystemTime());
		}
