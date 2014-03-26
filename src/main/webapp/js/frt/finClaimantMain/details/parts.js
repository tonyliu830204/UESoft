function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		if(staticReceptionId!=""){
			$('#finClaimantMainPartsDatagrid').datagrid({
				url : 'finClaimantMainAction!findPartsByReceptionId.action?receptionId='+staticReceptionId
			});
		}else{
			var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
			if(rowData){
				$('#finClaimantMainPartsDatagrid').datagrid({
					url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId='+rowData.claimantmId
				});
			}else{
				$('#finClaimantMainPartsDatagrid').datagrid({
					url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId=-1'
				});
			}
		}
		
		if($('#save').length != 0 && $('#cancel').length != 0){
			$('#finClaimantMainPartsDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtResevationParts=data;
					$('#finClaimantMain_parts_add').linkbutton('enable');
					$('#finClaimantMain_parts_remove').linkbutton('enable');
					$('#finClaimantMain_parts_accept').linkbutton('enable');
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);

	$(function (){
		//索赔结算单->索赔材料
		var url='';
		if(staticReceptionId!=""){
			url = 'finClaimantMainAction!findPartsByReceptionId.action?receptionId='+staticReceptionId;
		}else{
			var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
			if(rowData){
				url ='finClaimantMainAction!findPartsByFcmId.action?claimantmId='+rowData.claimantmId;
			}
		}
		$finClaimantMainPartsDatagrid = $('#finClaimantMainPartsDatagrid');
		$finClaimantMainPartsDatagrid.datagrid({
			url : url,
			singleSelect : true,
			fit : true,
			rownumbers : true,
			fitColumns: true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{field:'partsId',title:'配件编号',width:60},         
				{field:'partsName',title:'配件名称',width:60}
				,{
					field : 'punitId',
					title : '单位',
					width : 60,
					formatter : function (value,row,index){
						return row.punitName;
					},
					sortable : true
				},{
					field : 'punitName',
					title : '单位',
					width : 60,
					hidden : true
				},
				{
					field : 'partsNum',
					title : '数量',
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,
							required : true,
							min : 1,
							missingMessage : "配件数量为必填项!"
						}
					}
				},         
				{
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
				},         
				{
					field : 'partsAmount',
					title : '费用合计',
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
						    	var row = $finClaimantMainPartsDatagrid.datagrid('getSelected');
					    		var index = $finClaimantMainPartsDatagrid.datagrid('getRowIndex', row);
					    		var ed = $finClaimantMainPartsDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
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
					field : 'partsTaxCost',
					title : '含税成本价',
					width : 60,
					hidden : true
				},{
					field : 'partsNoTaxCost',
					title : '未税成本价',
					width : 60,
					hidden : true
				}            
			]]
			,
			toolbar : [
			   {
				   id : 'finClaimantMain_parts_add',
				   text : '配件增加',
				   iconCls : 'icon-add',
				   disabled : true,
				   handler : function (){
				   		var d = $('<div/>').dialog({
				   			modal:true,
							title : '配件选择',
							closable : true,
							href : projectPath+'frt/finClaimantMain/details/addParts.jsp',
							width : 900,
							height : 560,
							buttons : [{
								text : '确定',
								iconCls : 'icon-add',
								handler : function (){
									var tag=true;
									var rows=$('#selectedParts').datagrid('getRows');
									for ( var i = 0; i < rows.length; i++) {
										var flag=$('#selectedParts').datagrid('validateRow',i);
										if(!flag){
											tag=false;
											break;
										}
									}
									if(tag){
										staticFinClaimantMainParts=prosceniumAdd('selectedParts','finClaimantMainPartsDatagrid',staticFinClaimantMainParts);
										d.dialog('close');
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							}],
							onClose : function (){
						    	$(this).dialog('destroy');
						    }
				   		});
			   	   }
			   },{
				   id : 'finClaimantMain_parts_remove',
				   text : '配件删除',
				   iconCls : 'icon-remove',
				   disabled : true,
				   handler : function (){	 
						 var row = $('#finClaimantMainPartsDatagrid').datagrid('getSelected');
						if(row){
							staticFinClaimantMainParts = prosceniumDelete('finClaimantMainPartsDatagrid',row,staticFinClaimantMainParts);
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
					staticFinClaimantMainParts=data;
			   }
		});
	});