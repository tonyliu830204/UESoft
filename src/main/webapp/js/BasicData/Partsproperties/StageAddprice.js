    var editRow=undefined;
	$(function(){
		$('#StageAddpriceTable').datagrid({
			url : 'StageAddpriceAction!view.action',
			singleSelect : true,
			fit : true,
		    sortName : 'stageId',
			sortOrder : 'asc',
			singleSelect : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			columns : [[ {
				field : 'stageId',
				title : '梯度编号',
				width : 60,
				hidden : true
			}, {
				field : 'startAmont',
				title : '开始金额',
				width : 60,
				sortable : true,
				editor : {
					type : 'numberbox',
					options : {
						required:true,
						min:0,
						precision:2,
						validType:'length[0,12]',
						missingMessage : "开始金额为必填项!"
					}
				}
			},{
				field : 'endAmont',
				title : '结束金额',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						required:true,
						min:0,
						precision:2,
						validType:'length[0,12]',
						missingMessage : "结束金额为必填项!"
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
						required:true,
						precision : 2,
						min : 0,
						max : 100,
						validType:'length[0,4]',
						missingMessage : "维修加价率为必填项!"
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
						precision : 2,
						min : 0,
						max : 100,
						validType:'length[0,4]',
						missingMessage : "销售加价率为必填项!"
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
						precision : 2,
						min : 0,
						max : 100,
						validType:'length[0,4]',	
						missingMessage : "网点加价率为必填项!"
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
						precision : 2,
						min : 0,
						max : 100,
						validType:'length[0,4]',
						missingMessage : "网点加价率为必填项!"
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
						precision : 2,
						min : 0,
						max : 100,
						validType:'length[0,4]',
						missingMessage : "索赔加价率为必填项!"
					}
				}
			}
	         ]],
	 		onAfterEdit : function(rowIndex, rowData, changes) {
			 if(rowData.stageId==undefined) {
		             $.ajax({
			             type:"POST",
			             url:"StageAddpriceAction!add.action",
			             data:"startAmont="+rowData.startAmont +"&endAmont="+rowData.endAmont+"&pointRate="+rowData.pointRate
			             +"&repairRate="+rowData.repairRate+"&sellRate="+rowData.sellRate +"&specialRate="+rowData.specialRate+ "&claimRate="
							+ rowData.claimRate,
			             dataType:"json",
			             success:function callback(r){
		            	 if(r.success){
		            		 $('#StageAddpriceTable').datagrid('clearSelections');
		            		 $('#StageAddpriceTable').datagrid('load');
		            		 cancel();
		            	 }else{
		            		 //$('#StageAddpriceTable').datagrid('beginEdit', rowIndex);
		            		 $.messager.alert('优亿软件提示',r.msg,'info',function(){});
		            	 }
		               }
		             });
			    }else
			    {
			        $.ajax({
			             type:"POST",
			             url:"StageAddpriceAction!update.action",
			             data:"stageId="+rowData.stageId+"&startAmont="+rowData.startAmont +"&endAmont="+rowData.endAmont+"&pointRate="+rowData.pointRate
			             +"&repairRate="+rowData.repairRate+"&sellRate="+rowData.sellRate +"&specialRate="+rowData.specialRate+ "&claimRate="
							+ rowData.claimRate,
			             dataType:"json",
			             success:function callback(r){
			        	  if(r.success){
			        		  $('#StageAddpriceTable').datagrid('clearSelections');
			        		  $('#StageAddpriceTable').datagrid({
								     url:projectPath+'StageAddpriceAction!view.action',
								     loadMsg:'更新数据中......'
							  });
			        		  cancel();
			        	  }else{
			        		  $('#StageAddpriceTable').datagrid('beginEdit', rowIndex);
			        		  $.messager.alert('优亿软件提示',r.msg,'info',function(){});
			        	  }
		                 }
		             });
			      }
	           }
		   });
	    })
		

		//添加
		function add()
		{
			if (editRow != undefined) {
				$('#StageAddpriceTable').datagrid('endEdit', editRow);
				$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
			    });
			} else {
				$('#StageAddpriceTable').datagrid('insertRow', {
					index : 0, 
					row : {}
				});
				 addButton();
				$('#StageAddpriceTable').datagrid('beginEdit', 0);
				editRow = 0;
				bindEnterInCloumn($('#StageAddpriceTable'), editRow, 0);
			}
		}
			
			// 保存
			function save() {
				if (editRow == undefined)
					$.messager.alert('优亿软件提示','当前没有要保存的记录！','warning',function(){ });
				else{
					var isno=$('#StageAddpriceTable').datagrid('validateRow',editRow);
					if(isno){
						$('#StageAddpriceTable').datagrid('endEdit', editRow);
						cancel();
					}else
						 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(){});
				}
			}	

			//删除
			function del() {
				var selected = $('#StageAddpriceTable').datagrid('getSelected');
				if (selected!=null) {
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
							if (r) {
								$.ajax({
									type : "POST",
									url : "StageAddpriceAction!del.action",
									data : "stageId=" + selected.stageId,
									dataType : "json",
									success : function callback(r) {
										if(r.success){
											  $('#StageAddpriceTable').datagrid('clearSelections');
							        		  $('#StageAddpriceTable').datagrid({
												     url:projectPath+'StageAddpriceAction!view.action',
												     loadMsg:'更新数据中......'
											  });
										}else
											$.messager.alert('优亿软件提示',r.msg,'info',function(){});
									}
								});
							}
						});
				}else
						$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning',function(){});
		    }

			//修改
			function update() {
				var selects = $('#StageAddpriceTable').datagrid('getSelections');
				if (selects.length > 0) {
					var isno=$('#StageAddpriceTable').datagrid('validateRow',editRow);
					if(isno){
						editRow = $('#StageAddpriceTable').datagrid('getRowIndex', selects[0]);
						$('#StageAddpriceTable').datagrid('beginEdit', editRow);
						bindEnterInCloumn($('#StageAddpriceTable'), editRow, 0);
						 addButton();
					}else
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(r){ });
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
				$('#StageAddpriceTable').datagrid('unselectAll');
	       		$('#StageAddpriceTable').datagrid('rejectChanges');
	       		$('#saveOrCancelBtn').empty();
	       		editRow = undefined;
			}

			/**
			  * 导出excel
			  * @return
			  */
			 function _except(){
				exportEsuyUIExcelFile("PartsTypeCenter",null,'梯度加价率信息'+getSystemTime());
			 }
			 
			 var bindEnterInCloumn = function (id, rowIndex, i){
					var ed = id.datagrid('getEditors', rowIndex);
					ed[i].target.select();
					if(ed[i].type == 'combobox'){
						ed[i].target.combo('textbox').select();
						ed[i].target.combo('textbox').keydown(function (e){
							if(e.keyCode == '13'){
								if(i != ed.length - 1){
									bindEnterInCloumn(id, rowIndex, i + 1);
								}
							}
						});
					}else{
						ed[i].target.keydown(function (e){
							if(e.keyCode == '13'){
								if(i != ed.length - 1){
									bindEnterInCloumn(id, rowIndex, i + 1);
								}else{
									if(ed[0].type=='combobox'){
										ed[0].target.combo('textbox').select();
									}else{
										ed[0].target.select();
									}
								}
							}
						});
					}
			}
			