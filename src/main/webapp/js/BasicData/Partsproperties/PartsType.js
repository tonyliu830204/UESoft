    var editRow=undefined;
	$(function(){
		$.extend($.fn.datagrid.methods, {  
			addToolbarItem: function(jq, items){  
				return jq.each(function(){  
					var toolbar = $(this).parent().prev("div.datagrid-toolbar");
					for(var i = 0;i<items.length;i++){
						var item = items[i];
						if(item === "-"){
							toolbar.append('<div class="datagrid-btn-separator"></div>');
						}else{
							var btn=$("<a href=\"javascript:void(0)\"></a>");
							btn[0].onclick=eval(item.handler||function(){});
							btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));
						}
					}
					toolbar = null;
				});  
		},
		removeToolbarItem: function(jq, param){  
			return jq.each(function(){  
				var btns = $(this).parent().prev("div.datagrid-toolbar").children("a");
				var cbtn = null;
				if(typeof param == "number"){
					cbtn = btns.eq(param);
				}else if(typeof param == "string"){
					var text = null;
					btns.each(function(){
						text = $(this).data().linkbutton.options.text;
						if(text == param){
							cbtn = $(this);
							text = null;
							return;
						}
					});
				} 
				if(cbtn){
					var prev = cbtn.prev()[0];
					var next = cbtn.next()[0];
					if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
						$(prev).remove();
					}else if(next && next.nodeName == "DIV"){
						$(next).remove();
					}else if(prev && prev.nodeName == "DIV"){
						$(prev).remove();
					}
					cbtn.remove();	
					cbtn= null;
				}						
			});  
		   } 				
	    });
		
		$('#mountingsTypeInfo').datagrid({
			url : 'basMountingsTypeInfo_findAll.action',
			singleSelect : true,
			fit : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			columns : [[
	         {
				field : 'ptypeId',
				title : '型号编号',
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
						missingMessage : '型号品牌为必填项!'
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
					type : 'validatebox',
					options : {
						required : true,
						missingMessage : "名称为必填项!"
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
						max : 100,
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
						precision : 4,
						min : 0,
						max : 100,
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
						precision : 4,
						min : 0,
						max : 100,
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
						precision : 4,
						min : 0,
						max : 100,
						missingMessage : "特殊加价率为必填项!"
					}
				}
			}
	         ]],
	         toolbar:[{
	 			text:'新增',
	 			iconCls:'icon-add',
	 			handler: function (){
	 				add($mountingsTypeInfo, {}, 'pbrdId');
	 			}
	 		},'-',{
	 			text:'删除',
	 			iconCls:'icon-remove',
	 			handler: function (){
	 				remove($mountingsTypeInfo, 'basMountingsTypeInfo_delete.action', 'basMountingsTypeInfo_findAll.action');
	 			}
	 		},'-',{
	 			text:'修改',
	 			iconCls:'icon-edit',
	 			handler: function (){
	 				edit($mountingsTypeInfo, 'basMountingsTypeInfo_edit.action', 'basMountingsTypeInfo_findAll.action', 'pbrdId');
	 			}
	 		},'-',{
	 			text:'查询',
	 			iconCls:'icon-search',
	 			handler: function (){
	 				$mtiForm = $('#mtiForm').form();
	 				_search($mtiForm, $mountingsTypeInfo);
	 			}
	 		},'-',{
	 			text:'清空',
	 			iconCls:'icon-undo',
	 			handler: function (){
	 				$('#mtiForm').form('clear');
	 			}
	 		},'-',{
	 			text:'导出',
	 			iconCls:'icon-export',
	 			handler: function (){
	 			_except();
	 			}
	 		},'-'],
	 		onAfterEdit : function(rowIndex, rowData, changes) {
	 			onAfterEdit($mountingsTypeInfo, 'basMountingsTypeInfo_save.action' , 'basMountingsTypeInfo_edit.action',rowIndex, rowData, changes);
	 		}
		
		});
	})
		

			//添加
			function add()
			{
				if (editRow != undefined) {
					$('#table10').datagrid('endEdit', editRow);
					$.messager.alert('优亿软件提示','您已经选择了添加按钮！','warning',function(){
						$('#table10').datagrid('removeToolbarItem', '保存');
						$('#table10').datagrid('removeToolbarItem', '取消');
				    });
				} else {
					$('#table10').datagrid('insertRow', {
						index : 0, 
						row : {}
					});
				    $('#table10').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
    	        		save();
    	        	}},{"text":"取消","iconCls":"icon-undo","handler":function(){
    	        		 cancel();
    	        	}}])
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
						cancel();
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
							 $('#table10').datagrid('addToolbarItem',[{"text":"保存","iconCls":"icon-save","handler":function(){
			    	        	 save();
			    	        }},{"text":"取消","iconCls":"icon-undo","handler":function(){
			    	        	 cancel();
			    	        }}])
						}else
						{
							$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','warning',function(r){
						    });
						}
					}
				} else {
					$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){
						$('#table10').datagrid('removeToolbarItem', '保存');
						$('#table10').datagrid('removeToolbarItem', '取消');
				    });
				}
			}

			//取消
			function cancel() {
				 $('#table10').datagrid('unselectAll');
	       		 $('#table10').datagrid('rejectChanges');
	       		 $('#table10').datagrid('removeToolbarItem','保存');
	       		 $('#table10').datagrid('removeToolbarItem','取消');
	       		 editRow = undefined;
			}

			/**
			  * 
			  * 导出excel
			  * @return
			  */
			 function _except(){
				exportEsuyUIExcelFile("PartsTypeCenter",null,'配件型号信息'+getSystemTime());
			 }
			