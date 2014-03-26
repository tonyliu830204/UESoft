$(function(){
	if(receptionId){
		url='frtWorkshopManagerAction!findPartsByRcptId.action?receptionId=' +receptionId;
	}else{
		url='frtWorkshopManagerAction!findPartsByRcptId.action?receptionId=-1' ;
	}
	$frtWorkshopManagerPartsDatagrid = $('#frtWorkshopManagerPartsDatagrid');
	$frtWorkshopManagerPartsDatagrid.datagrid({
		url : url,
		singleSelect : true,
		fit : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[{
					field : 'partsId',
					title : '配件编号',
					width : 60
				}, {
					field : 'partsIndex',
					title : '工单配件序号',
					hidden:true
				}, {
					field : 'partsName',
					title : '配件名称',
					width : 60
				}, {
					field : 'punitId',
					title : '单位',
					width : 60,
					formatter : function (value, row, index){
						return row.punitName;
					}
				}, {
					field : 'punitName',
					title : '单位',
					width : 60,
					hidden : true
				}, {
					field : 'partsNum',
					title : '数量',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 1,
							required : true,
							missingMessage : "配件数量为必填项!"
						}
					}
				}, {
					field : 'partsRepairPrice',
					title : '配件单价',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 0,
							required : true,
							missingMessage : '配件单价为必填项!'
						}
					}
				}, {
					field : 'partsAmount',
					title : '金额',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							min : 0,
							required : true,
							missingMessage : "金额为必填项!"
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
						    onSelect : function (record){
						    	var row = $frtWorkshopManagerPartsDatagrid.datagrid('getSelected');
					    		var index = $frtWorkshopManagerPartsDatagrid.datagrid('getRowIndex', row);
					    		var ed = $frtWorkshopManagerPartsDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
					    		ed.target.val(record.text);
						    }
						}
					},
					formatter : function (value, row, index){
						return row.reptypName;
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
						    onSelect : function (record){
						    	var row = $frtWorkshopManagerPartsDatagrid.datagrid('getSelected');
					    		var index = $frtWorkshopManagerPartsDatagrid.datagrid('getRowIndex', row);
					    		var ed = $selectedParts.datagrid('getEditor', {index:index,field:'claimsName'});
					    		ed.target.val(record.text);
						    }
						}
					},
					formatter : function (value, row, index){
						return row.claimsName;
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
					field : 'storeId',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'storeName',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'partsRemark',
					title : '备注',
					width : 60,
					editor : {
						type : 'text'
					}
				}]],
				toolbar : [{
					id : 'frtWorkshopManager_parts_add',
					text : '配件增加',
					iconCls : 'icon-add',
					disabled : true,
					handler : function() {
						var d = $('<div/>').dialog({
							modal : true,
							title : '配件选择',
							closable : true,
							href : projectPath+'frt/frtWorkshopManager/details/addParts.jsp',
							width : 900,
							height : 560,
							buttons : [ {
								text : '确定',
								iconCls : 'icon-add',
								handler : function() {
									if(validateDatagrid('selectedParts')){
										staticFrtWorkshopManagerParts=prosceniumAdd('selectedParts','frtWorkshopManagerPartsDatagrid',staticFrtWorkshopManagerParts);
										d.dialog('close');
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							} ],
							onClose : function() {
								$(this).dialog('destroy');
							}
						});
					}
				}, {
					id : 'frtWorkshopManager_parts_remove',
					text : '配件删除',
					iconCls : 'icon-remove',
					disabled : true,
					handler : function() {
						var row = $('#frtWorkshopManagerPartsDatagrid').datagrid('getSelected');
						if(row){
							staticFrtWorkshopManagerParts = prosceniumDelete('frtWorkshopManagerPartsDatagrid',row,staticFrtWorkshopManagerParts);
						}
					}
				}],
				onClickRow : function (rowIndex, rowData){
				   if($('#save').length != 0 && $('#cancel').length != 0){
					   $(this).datagrid('beginEdit', rowIndex);
						var ed = $(this).datagrid('getEditors', rowIndex);
						ed[0].target.select();
						ed[0].target.bind('keyup', function() {
							var num = ed[0].target.val();
							var price = ed[1].target.val();
							var amount = accMul(parseFloat(num), parseFloat(price));
							ed[2].target.numberbox('setValue', amount);
						});
						ed[1].target.bind('keyup', function() {
							var num = ed[0].target.val();
							var price = ed[1].target.val();
							var amount = accMul(parseFloat(num), parseFloat(price));
							ed[2].target.numberbox('setValue', amount);
						});
						ed[0].target.focus(function (){
							ed[0].target.select();
						});
						ed[1].target.focus(function (){
							ed[1].target.select();
						});
						ed[0].target.keydown(function (e){
							if(e.keyCode == '13'){
								ed[1].target.select();
							}
						});
				   }
			   },
			   onLoadSuccess : function (data){
					staticFrtWorkshopManagerParts=data;
			   },
			   onLoadSuccess : function (data){
				   if(opretion==true){
					    staticFrtWorkshopManagerParts=data;
						$('#frtWorkshopManager_parts_add').linkbutton('enable');
						$('#frtWorkshopManager_parts_remove').linkbutton('enable');
				   }					
				}
			});	
})
