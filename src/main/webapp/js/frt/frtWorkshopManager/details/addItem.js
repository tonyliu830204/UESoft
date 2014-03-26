function LoadOk() {
		if (document.readyState == "complete") {
			runs();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	setTimeout("LoadOk();", 200);
	var runs=function (){
		//待选维修项目		
		$selectionItem = $('#selectionItem');
		
		$selectionItem.datagrid({
			url : 'basRepairPackageAction!findAllSelectionItem.action',
			//fit : true,
			height: 225,
			//width: 865,
			fitColumns : true,
			idField : 'repitemId',
			singleSelect : true,
			rownumbers : true,
			pagination:true,
			columns : [[{
				field : 'repitemId',
				title : '项目编号',
				width : 60,
				sortable : true
			},{
				field : 'repitemName',
				title : '项目名称',
				width : 60,
				sortable : true
			},{
				field : 'repitemCode',
				title : '项目简码',
				width : 60,
				hidden : true
			},{
				field : 'repitemSeries',
				title : '工时分类',
				width : 60,
				sortable : true
			},{
				field : 'repitemAmount',
				title : '工时费',
				width : 60,
				sortable : true
			},{
				field : 'repitemTime',
				title : '维修工时',
				width : 60,
				sortable : true
			},{
				field : 'internalTime',
				title : '内部工时',
				width : 60,
				sortable : true
			},{
				field : 'claimTime',
				title : '索赔工时',
				width : 60,
				sortable : true
			},{
				field : 'fitCar',
				title : '适合车型',
				width : 60,
				formatter : function (value,row,index){
					return row.fitCarName;
				},
				sortable : true
			},{
				field : 'repitemRemark',
				title : '备注',
				width : 60,
				sortable : true
			},{
				field : 'repitemNum',
				title : '工时数',
				width : 60,
				hidden : true
			}]],
			onClickRow : function (rowIndex, rowData){
				$(this).datagrid('unselectRow', rowIndex);
			},
			onDblClickRow : function(rowIndex, rowData){
				var rows = $selectedItem.datagrid('getRows');
				if(rows.length){
					for(var i = 0;  i < rows.length; i++){
						if(rows[i].repitemId == rowData.repitemId){
							return;
						}
					}
				}
				$(this).datagrid('deleteRow', rowIndex);
				$selectedItem.datagrid('appendRow', rowData);
				var index = $selectedItem.datagrid('getRowIndex', rowData);
				copyDateAndBindEventAndTwo($selectedItem, index, rowData,workshopPrice);
			}
		});
		var flag=false;
		//已选维修项目
		$selectedItem = $('#selectedItem');
		
		$selectedItem.datagrid({
			url:'',
			//fit : true,
			height: 120,
			fitColumns : true,
			idField : 'repitemId',
			singleSelect:true,
			rownumbers : true,
			columns : [[{
				field : 'repitemId',
				title : '项目编号',
				width : 60
			},{
				field : 'repitemName',
				title : '项目名称',
				width : 60
			},{
				field : 'repitemTime',
				title : '维修工时',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						required : true,
						min : 1,
						missingMessage : "维修工时为必填项!"
					}
				}
			},{
				field : 'internalTime',
				title : '内部工时',
				width : 60,
				editor : {
					type : 'validatebox',
					options : {
						required : true,
						missingMessage : "内部工时为必填项!"
					}
				}
			},{
				field : 'repitemAmount',
				title : '工时费',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						disabled:true,
						required : true,
						precision : 2
					}
				}
			},{
				field : 'placeholder',
				title : '占位',
				width : 60,
				hidden : true,
				editor : {
					type : 'numberbox'
				}
			},{
				field : 'stfId',
				title : '操作员',
				width : 60,
				editor : {
					type : 'combobox',
					options : {
						url : projectPath+'basStuffClassAction!findEnterpriseMaintainPerson.action',
						valueField:'id',   
					    textField:'text',
					     mode:'remote',
					    required : true,
					    missingMessage : '维修人员为必填项！',
					    onLoadSuccess : function (){
					    	var data = $(this).combobox('getData');
					    	if(data[0]){
					    		$(this).combobox('setValue', data[0].id);
					    		var index = $('#selectedItem').datagrid('getData').total - 1;
					    		var ed = $('#selectedItem').datagrid('getEditor', {index:index,field:'stfName'});
					    		ed.target.val(data[0].text);
					    	}
					    },
					    onSelect : function (record){
					    	var index = $('#selectedItem').datagrid('getRowIndex',$('#selectedItem').datagrid('getSelected'));
				    		var ed =  $('#selectedItem').datagrid('getEditor', {index:index,field:'stfName'});
				    		ed.target.val(record.text);
				    		$(this).combobox('hidePanel');
					    },
					    onShowPanel:function(){
					    	flag=true;
					    },
					    onHidePanel:function(){
					    	flag=false;
					    }
					}
				}
			},{
				field : 'stfName',
				title : '操作员',
				width : 60,
				hidden : true,
				editor : {
					type : 'text'
				}
			},{
				field : 'planStartTime',
				title : '计划开工时间',
				width : 140,
				editor : {
					type : 'datetimebox',
					options : {
						required:true
					}
				}
			},{
				field : 'planComplTime',
				title : '计划完工时间',
				width : 140,
				editor : {
					type : 'datetimebox',
					options : {
						required:true
					}
				}
			},{
				field : 'reptypId',
				title : '收费性质',
				width : 60,
				editor : {
					type : 'combobox',
					options : {
						url : 'frtOptionsAction!findAllRepairType.action',
						valueField:'id',   
					    textField:'text',
					     mode:'remote',
					    required : true,
					    missingMessage : '收费性质为必填项！',
					    onLoadSuccess : function (){
					    	var data = $(this).combobox('getData');
					    	if(data[0]){
					    		$(this).combobox('setValue', data[0].id);
					    		var index = $('#selectedItem').datagrid('getData').total - 1;
					    		var ed = $('#selectedItem').datagrid('getEditor', {index:index,field:'reptypName'});
					    		ed.target.val(data[0].text);
					    	}
					    },
					    onSelect : function (record){
					    	var index = $('#selectedItem').datagrid('getRowIndex',$('#selectedItem').datagrid('getSelected'));
				    		var ed = $('#selectedItem').datagrid('getEditor', {index:index,field:'reptypName'});
				    		ed.target.val(record.text);
				    		$(this).combobox('hidePanel');
					    },
					    onShowPanel:function(){
					    	flag=true;
					    },
					    onHidePanel:function(){
					    	flag=false;
					    }
					}
				}
			},{
				field : 'reptypName',
				title : '收费性质',
				width : 60,
				hidden : true,
				editor : {
					type : 'text'
				}
			},{
				field : 'claimsId',
				title : '索赔分类',
				width : 60,
				editor : {
					type : 'combobox',
					options : {
						url : 'frtOptionsAction!findAllClaimsType.action',
						valueField:'id',   
					    textField:'text',
					     mode:'remote',
					    required : true,
					    missingMessage : '索赔分类为必填项！',
					    onLoadSuccess : function (){
					    	var data = $(this).combobox('getData');
					    	if(data[0]){
					    		$(this).combobox('setValue', data[0].id);
					    		var index = $('#selectedItem').datagrid('getData').total - 1;
					    		var ed = $('#selectedItem').datagrid('getEditor', {index:index,field:'claimsName'});
					    		ed.target.val(data[0].text);
					    	}
					    },
					    onSelect : function (record){
					    	var index = $('#selectedItem').datagrid('getRowIndex',$('#selectedItem').datagrid('getSelected'));
				    		var ed = $('#selectedItem').datagrid('getEditor', {index:index,field:'claimsName'});
				    		ed.target.val(record.text);
				    		$(this).combobox('hidePanel');
					    },
					    onShowPanel:function(){
					    	flag=true;
					    },
					    onHidePanel:function(){
					    	flag=false;
					    }
					}
				}
			},{
				field : 'claimsName',
				title : '索赔分类',
				width : 60,
				hidden : true,
				editor : {
					type : 'text'
				}
			},{
				field : 'repitemRemark',
				title : '备注',
				width : 60,
				editor : {
					type : 'text'
				}
			}]],
			onClickRow : function (rowIndex, rowData){
					/*
				$(this).datagrid('beginEdit', rowIndex);
				var ed = $(this).datagrid('getEditors', rowIndex);
				ed[0].target.bind('keyup', function() {
					var num = ed[0].target.val();
					var price = ed[2].target.val();
					var amount = accMul(parseFloat(num), parseFloat(price));
					ed[3].target.numberbox('setValue', amount);
				});
				*/
			},
			onDblClickRow : function(rowIndex, rowData){
				var ed = $(this).datagrid('getEditors', rowIndex);
				if(ed.length){
					$(this).datagrid('deleteRow', rowIndex);
					$('#selectedItem').datagrid('appendRow', rowData);
				}
			},
			onRowMouseoverMenu:function(e,row,rowData){
				if(flag==false){
					var index=$('#selectedItem').datagrid('getRowIndex',rowData);
					$('#selectedItem').datagrid('selectRow',index);						
				}
			},
			onRowMouseoutMenu:function(e,row,rowData){
				if(flag==false){
					var index=$('#selectedItem').datagrid('getRowIndex',rowData);
					$('#selectedItem').datagrid('unselectRow',index);
				}					
			}
		});
		//$('#selectedItem').datagrid('loadData',staticFrtWorkshopManagerItems);
	}
	var _query=function (){
		$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
	}
	var _clear=function (){
		$('#selectionItemForm').form('clear'); 
		$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
	}