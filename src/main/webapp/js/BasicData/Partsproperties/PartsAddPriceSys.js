        var editRow=undefined;
		$(function() {
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
	
	$('#table10').datagrid(
			{
				url : 'BasPartsRateAction_view.action',
				idField : 'partsId',
				pagination : true,
				sortName:'partsId',
				sortOrder:'desc',
				fit : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				fitColumns : false,
				rownumbers : true,
				columns : [ [ {
					title : '编号',
					field : 'partsId',
					width : 100,
					hidden:true
				}, {
					title : '方式',
					field : 'partsStyle',
					width : 100,
					sortable:true,
					editor:{
		                type:'validatebox',
		                options:{
		                      		required:true,
		                      		missingMessage : "方式为必填项"
		                        }
		                }
				}, {
					title : '开始金额',
					field : 'partsStartamount',
					width : 100,
					sortable:true,
					editor:{
		                type:'validatebox',
		                options:{
		                      		required:true
		                        }
		                }
				}, {
					title : '结束金额',
					field : 'partsEndamount',
					width : 100,
					sortable:true,
					editor:{
		                type:'validatebox',
		                options:{
		                      		required:true
		                        }
		                }
				}, {
					title : '加价率',
					field : 'partsRate',
					width : 100,
					sortable:true,
					editor:{
		                type:'validatebox',
		                options:{
		                      		required:true
		                        }
		                }
				}, {
					title : '索赔加价率',
					field : 'claimType',
					width : 100,
					sortable:true,
					editor:{
		                type:'validatebox',
		                options:{
		                      		required:true
		                        }
		                }
				}, {
					title : '备注',
					field : 'partsRemark',
					width : 100,
					sortable:true,
					editor:{
		                type:'text'
		                }
				} ] ],  toolbar:[
				    	         {text:'增加',iconCls:'icon-add',handler:function(){
				    	        	 add();
				    	         } },
				    			 {text:'删除',iconCls:'icon-remove',handler:function(){
				    	        	 del();
				    			 } },
				    			 {text:'修改',iconCls:'icon-edit',  handler:function(){
				    				 update(); 
				    			 } },{
				    				 text:'导出',iconCls:'icon-export',handler:function(){
				    				 _except();
			    	             } }
				               ],
				onAfterEdit : function(rowIndex, rowData, changes) {
					if (rowData.partsId == undefined) {
						$.ajax({
							type : "POST",
							url : "BasPartsRateAction_add.action",
							data : "partsStyle=" + rowData.partsStyle + "&partsStartamount="
									+ rowData.partsStartamount+ "&partsEndamount="
									+ rowData.partsEndamount+"&partsRate="
									+ rowData.partsRate+ "&claimRate="
									+ rowData.claimType+ "&partsRemark="
									+ rowData.partsRemark,
							dataType : "json",
							success : function callback(r) {
								if(r.msg=="success")
								{
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid( {
										url : 'BasPartsRateAction_view.action',
										loadMsg : '更新数据中......'
									});
									cancel();
								}else
								{
									$('#table10').datagrid('beginEdit',rowIndex);
									$.messager.alert('优亿软件提示', '配件加价系数添加失败！', 'info', function() {
									});
								}
							}
						});
					} else {
						$.ajax( {
							type : "POST",
							url : "BasPartsRateAction_update.action",
							data : "partsId=" + rowData.partsId + "&partsStyle=" + rowData.partsStyle + "&partsStartamount="
									+ rowData.partsStartamount+ "&partsEndamount="
									+ rowData.partsEndamount+ "&partsRate="
									+ rowData.partsRate+ "&claimRate="
									+ rowData.claimType+"&partsRemark="
									+ rowData.partsRemark,
							dataType : "json",
							success : function callback(r) {
								if(r.msg=="success")
								{
									$('#table10').datagrid('clearSelections');
									$('#table10').datagrid( {
										url : 'BasPartsRateAction_view.action',
										loadMsg : '更新数据中......'
									});
									cancel();
								}else
								{
									$('#table10').datagrid('beginEdit',rowIndex);
									$.messager.alert('优亿软件提示', '配件加价系数修改失败！', 'info', function() {
									});
								}
							}
						});
					}
				}
			});
         });
		
		//添加
		function add() {
			if (editRow != undefined) {
				$('#table10').datagrid('endEdit', editRow);
				$.messager.alert('优亿软件提示','您已经选择了添加按钮！','info',function(){
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
				 bindEnterInCloumn($('#table10'), editRow, 0);
			}
		}

		// 保存
		function save() {
			if (editRow == undefined) {
				$.messager.alert('优亿软件提示','你当前没有要保存的记录！','info',function(){
					$('#table10').datagrid('removeToolbarItem', '保存');
					$('#table10').datagrid('removeToolbarItem', '取消');
			    });
			}else
			{
				var isno=$('#table10').datagrid('validateRow',editRow);
				if(isno)
				{
					$('#table10').datagrid('endEdit', editRow);
				}else
				{
					 $.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(){
					 });
				}
			}
		}
		
		function del() {
			var selects = $('#table10').datagrid('getSelections');
			if (selects.length > 0) {
				if (selects.length == 1) {
					$.messager.confirm('优亿软件提示', '请确定是否删除该记录?', function(r) {
						if (r) {
							$.ajax( {
								type : "POST",
								url : "BasPartsRateAction_del.action",
								data : "partsId=" + selects[0].partsId,
								dataType : "json",
								success : function callback(r) {
								if(r.msg=="success")
								{
									$('#table10').datagrid('clearSelections');
								    $.messager.alert('优亿软件提示', '配件加价系数删除成功！', 'info', function() {
									$('#table10').datagrid( {
										url : 'BasPartsRateAction_view.action',
										loadMsg : '更新数据中......'
									});
								  });
								}else
								{
									$.messager.alert('优亿软件提示','对不起，配件加价系数删除失败！','info',function(){
								    });
								}
							  }
							});
						}
					});
				}
			}else{
				$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','info',function(){
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
						$.messager.alert('优亿软件提示','对不起，记录无法保存，请确认内容及格式是否正确！','info',function(r){
					    });
					}
				}
			} else {
				$.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','info',function(){
					cancel();
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
			exportEsuyUIExcelFile("PartsAddPriceSysCenter",null,'配件加价率信息'+getSystemTime());
		 }