var url='';
var cancelBody = false;
var cancelCost = false;
var cancelDatum = false;
var cancelProject = false;
var cancelService = false;
var cancelStock = false;
var cancelArchives = false;
var cancelAdvise = false;

function LoadOk() {
	if (document.readyState == "complete") {
		if(staticFrtReceptionDisabled==false){
			changeTimeEvent();
			findDefaultClaimsCompanyId('frtReception_details_finComId');	
			findDefaultReceptionClass('frtReception_details_reptId');
		}
		reload();
		initFrame();
		addCarArchives();
		addCustomArchives();
		findFrtCarByCarVin();
		changeTimeEvent();
		
		$('#frtReceptionDetailsTabs').tabs({
			border:false,
			onSelect:function(title){
			    if(title == "车身状况"){
			    	if(!cancelBody){
				    	if(receptionId!=null && receptionId!="" && receptionId!='null'){
				    		url='frtReceptionAction!findvehicleStructureList.action?receptionId='+receptionId;
				    	}else{
				    		url='frtReceptionAction!findvehicleStructureList.action?receptionId=-1';
				    	}
						loadvehicle(url);
			    	}
			    }else if(title == "费用情况"){
			    	if(!cancelCost){
				    	if(receptionId!=null && receptionId!="" && receptionId!='null'){
				    		url=projectPath+'frtReceptionAction!findCostByRcptId.action?receptionId=' + receptionId;
				    	}else{
				    		url=projectPath+'frtReceptionAction!findCostByRcptId.action?receptionId=-1';
				    	}
				    	loadReception(url);
			    	}
			    }else if(title == "计划材料"){
			    	if(!cancelDatum){
				    	if(staticResvId!=null && staticResvId!="" && staticResvId!='null'){
				    		url=projectPath+'frtReceptionAction!findPartsByResvId.action?resvId=' + staticResvId;
				    	}else{
				    		if(receptionId){
				    			url=projectPath+'frtReceptionAction!findPartsByRcptId.action?receptionId=' +receptionId;
				    		}else{
				    			url=projectPath+'frtReceptionAction!findPartsByRcptId.action?receptionId=-1';
				    		}
				    	}
				    	loadPart(url);
			    	}
			    }else if(title == "计划项目"){
			    	if(!cancelProject){
				    	if(staticResvId!=null && staticResvId!="" && staticResvId!='null'){
				    		url=projectPath+'frtReceptionAction!findItemByResvId.action?resvId=' + staticResvId;
				    	}else{
				    		if(receptionId!=null && receptionId!="" && receptionId!='null'){
					    		url=projectPath+'frtReceptionAction!findItemByRcptId.action?receptionId=' +receptionId;
					    	}else{
					    		url=projectPath+'frtReceptionAction!findItemByRcptId.action?receptionId=-1';
					    	}
				    	}
				    	loadItem(url);
			    	}
			    }else if(title == "会员服务"){
			    	if(!cancelService){
			    		
			    	}
			    }else if(title == "配件库存"){
			    	if(!cancelStock){
			    	    loadPartsQuery();
			    	}
			    }else if(title == "维修档案"){
			    	if(!cancelArchives){
			    	    loadResevationItem('');
			    	}
			    }else if(title == "维修建议"){
			    	if(!cancelAdvise){
				    	if(carId!=null && carId!="" && carId!='null'){
			              url='frtReceptionAction!findFrtResvAdviceByCarId.action?carId='+carId;	
						}else{
							 url='frtReceptionAction!findFrtResvAdviceByCarId.action?carId=-1';	
						}
				    	loadMessage(url);
			    	}
			    }else{
			    	
			    }
			}
		});
	} else {
		setTimeout("LoadOk();", 200);
	}
}

function initFrame() {
	if($('#save').length != 0 && $('#cancel').length != 0){
		var carLicenseButton = '<a id="carLicenseButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" onclick="selectFrtResevation();"></a>';
		$('#button2').append(carLicenseButton);
		$.parser.parse('#button2');
	}
}
setTimeout("LoadOk();", 200);

function reload() {
	 if(receptionId!=null && receptionId!="" && receptionId!='null'){
		 $.ajax({
				type : 'post',
				url : 'frtReceptionAction!datagridReception.action',
				dataType : 'json',
				data:'receptionId='+receptionId,
				success : function(r) {
					$('#frtReceptionAddForm').form('clear');
					$('#frtReceptionExpenseSituationForm').form('clear');
					$('#frtReceptionAddForm').form('load', r.rows[0]);
					bespeakState('bespeak_off','bespeak_on','frtReception_details_resvId');
					$('#frtReceptionExpenseSituationForm').form('load',r.rows[0]);
				}
			});
	   }
}

//前台接车单->车身状况datagrid
function loadvehicle(url){
	$vehicleStructureDatagrid = $('#vehicleStructureDatagrid');
	$vehicleStructureDatagrid.datagrid({
		url:url,
		singleSelect : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns: [[
		    {field:'code',title:'部位',width:130},
		    {field:'name',title:'名称',width:130},
		    {field:'state',title:'状态描述',width:350,
		    	editor : {
					type : 'text'
				}
			}
		]],
		toolbar: [{
			id : 'frtReception_vehicleStructure_clear',
			text : '清空',
			disabled : true,
			iconCls: 'icon-cancel',
			handler: function(){
				var vehicle=null;
				if(staticFrtReceptionVehicleStructure==null){
					vehicle="";
				}else{
					vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
				}
			 	$.ajax({
					type : 'post',
					url : 'frtResevationAction!clearVehicleStructure.action',
					data : 'vehicle='+vehicle,
					dataType : 'json',
					success : function callback(r) {
						$('#vehicleStructureDatagrid').datagrid('loadData', r);
						var data = $('#vehicleStructureDatagrid').datagrid('getData');
						staticFrtReceptionVehicleStructure=data;
					}
				});
				 for(var i=1;i<=64;i++){
				 	$('#remark'+i+'').empty();
				 }
			}
		}]
		,onClickRow : function (rowIndex, rowData){
			if($('#save').length != 0 && $('#cancel').length != 0){
			   $(this).datagrid('beginEdit', rowIndex);
		   	}		  
		},
	   onLoadSuccess : function (data){
			loadIcons();
			if(operation==true  || recpetionFlag==true){
				staticFrtReceptionVehicleStructure=data;
				$('#frtReception_vehicleStructure_clear').linkbutton('enable');
			}
	   }
	});
}

//前台接车->费用情况 
function loadReception(url){
	$frtReceptionExpenseSituationOtherExpense = $('#frtReceptionExpenseSituationOtherExpense');
	$frtReceptionExpenseSituationOtherExpense.datagrid({
		url:url,
		singleSelect : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'costItem',
			title : '收费项目',
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "收费项目为必填项!"
				}
			}
		} , {
			field : 'costAmount',
			title : '收费金额',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2,
					missingMessage : "收费金额为必填项!"
				}
			}
		},{
			field : 'costExplain',
			title : '收费说明',
			width : 60,
			editor : {
				type : 'text'
			}
		}]],
		toolbar : [{
			id : 'frtReceptionExpenseSituationOtherExpense_add',
			text : '费用增加',
			iconCls : 'icon-add',
			disabled : true,
			handler : function() {
				var others=null;
				if(staticFrtReceptionExpenseSituationOtherExpense==null){
					others="";
				}else{
					others=JSON.stringify(staticFrtReceptionExpenseSituationOtherExpense);
				}
				$.ajax({
					type : 'post',
					url : projectPath+'frtReceptionAction!addFrtReceptionCost.action',
					data : 'others='+others,
					dataType : 'json',
					success : function callback(r) {
						$('#frtReceptionExpenseSituationOtherExpense').datagrid('loadData', r);
						var data = $('#frtReceptionExpenseSituationOtherExpense').datagrid('getData');
						staticFrtReceptionExpenseSituationOtherExpense=data;
						$('#frtReceptionExpenseSituationOtherExpense').datagrid('beginEdit', data.total-1);
					}
				});
			}
		}, {
			id : 'frtReceptionExpenseSituationOtherExpense_remove',
			text : '费用删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				var row = $('#frtReceptionExpenseSituationOtherExpense').datagrid('getSelected');
				if(row){
					staticFrtReceptionExpenseSituationOtherExpense = prosceniumDelete('frtReceptionExpenseSituationOtherExpense',row,staticFrtReceptionExpenseSituationOtherExpense);
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
		   if(operation==true  || recpetionFlag==true){
				staticFrtReceptionExpenseSituationOtherExpense=data;
				$('#frtReceptionExpenseSituationOtherExpense_add').linkbutton('enable');
				$('#frtReceptionExpenseSituationOtherExpense_remove').linkbutton('enable');
				$('#frtReceptionExpenseSituationOtherExpense_accept').linkbutton('enable');
			}
			
	   }
	});
}

//前台接车单->计划材料
function loadPart(url){
	$frtReceptionPartsDatagrid = $('#frtReceptionPartsDatagrid');
	$frtReceptionPartsDatagrid.datagrid({
		url:url,
		singleSelect : true,
		fit : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[{
				field : 'partsId',
				title : '配件编号',
				width : 60
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
						missingMessage : "数量为必填项!"
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
						missingMessage : "单价为必填项!"
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
					    	var row = $frtReceptionPartsDatagrid.datagrid('getSelected');
				    		var index = $frtReceptionPartsDatagrid.datagrid('getRowIndex', row);
				    		var ed = $frtReceptionPartsDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
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
					    	var row = $frtReceptionPartsDatagrid.datagrid('getSelected');
				    		var index = $frtReceptionPartsDatagrid.datagrid('getRowIndex', row);
				    		var ed = $frtReceptionPartsDatagrid.datagrid('getEditor', {index:index,field:'claimsName'});
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
				id : 'frtReception_parts_add',
				text : '配件增加',
				iconCls : 'icon-add',
				disabled : true,
				handler : function() {
					var d = $('<div/>').dialog({
						modal : true,
						title : '配件选择',
						closable : true,
						href : projectPath+'frt/frtReception/details/addParts.jsp',
						width : 900,
						height : 560,
						buttons : [ {
							text : '确定',
							iconCls : 'icon-add',
							handler : function() {
								if(validateDatagrid('selectedParts')){
									staticFrtReceptionParts=prosceniumAdd('selectedParts','frtReceptionPartsDatagrid',staticFrtReceptionParts);
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
				id : 'frtReception_parts_remove',
				text : '配件删除',
				iconCls : 'icon-remove',
				disabled : true,
				handler : function() {
					var row = $('#frtReceptionPartsDatagrid').datagrid('getSelected');
					if(row){
						staticFrtReceptionParts = prosceniumDelete('frtReceptionPartsDatagrid',row,staticFrtReceptionParts);
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
			   if(operation==true  || recpetionFlag==true){
					staticFrtReceptionParts=data;
					$('#frtReception_parts_add').linkbutton('enable');
					$('#frtReception_parts_remove').linkbutton('enable');
				}
		   }
	});
}

//前台接车单->计划项目
function loadItem(url){
	$frtReceptionItemDatagrid = $('#frtReceptionItemDatagrid');
	$frtReceptionItemDatagrid.datagrid({
		url:url,
		fit : true,
		singleSelect : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[{
			field : 'repitemId',
			title : '项目编号',
			width : 60
		},{
			field : 'repitemName',
			title : '项目名称',
			width : 60,
			editor : {
				type : 'text'
			}
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
					disabled:false,
					required : true,
					precision : 2
				}
			}
		},{
			field : 'repitemNum',
			title : '标识列',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'stfId',
			title : '维修人员',
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
				    onSelect : function (record){
				    	var row = $frtReceptionItemDatagrid.datagrid('getSelected');
			    		var index = $frtReceptionItemDatagrid.datagrid('getRowIndex', row);
			    		var ed = $frtReceptionItemDatagrid.datagrid('getEditor', {index:index,field:'stfName'});
			    		ed.target.val(record.text);
				    }
				}
			},
			formatter : function (value,row,index){
				return row.stfName;
			}
		},{
			field : 'stfName',
			title : '维修人员',
			width : 60,
			hidden : true,
			editor : {
				type : 'text'
			}
		},{
			field : 'planStartTime',
			title : '计划开工时间',
			width : 60,
			editor : {
				type : 'datetimebox'
			}
		},{
			field : 'planComplTime',
			title : '计划完工时间',
			width : 60,
			editor : {
				type : 'datetimebox'
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
				    	var row = $frtReceptionItemDatagrid.datagrid('getSelected');
			    		var index = $frtReceptionItemDatagrid.datagrid('getRowIndex', row);
			    		var ed = $frtReceptionItemDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
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
					    	var row = $frtReceptionItemDatagrid.datagrid('getSelected');
				    		var index = $frtReceptionItemDatagrid.datagrid('getRowIndex', row);
				    		var ed = $frtReceptionItemDatagrid.datagrid('getEditor', {index:index,field:'claimsName'});
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
			field : 'repitemRemark',
			title : '备注',
			width : 60,
			editor : {
				type : 'text'
			}
		}]],
		toolbar : [{
			id : 'frtReception_item_add',
			text : '项目新增',
			iconCls : 'icon-add',
			disabled : true,
			handler : function() {
			var carId=$('#frtReception_details_carId').combobox('getValue');
			if( carId!=null && carId!=""){
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'baseAction!loadTaskPrice.action?lince='+carId,  
					   success: function(r){
						receptionPrice=r;
					   },
					   error : function (r){
						   if(r.readyState == '0' && r.status == '0'){
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
						   }
					   }
					});
					var d = $('<div/>').dialog({
						modal : true,
						title : '选择维修项目',
						closable : true,
						href : projectPath+'frt/frtReception/details/addItem.jsp',
						width : 900,
						height : 560,
						buttons : [{
							text : '确定',
							iconCls : 'icon-add',
							handler : function() {
								if(validateDatagrid('selectedItem')){
									staticFrtReceptionItems=prosceniumAdd('selectedItem','frtReceptionItemDatagrid',staticFrtReceptionItems);
									d.dialog('close');
								}else{
									alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
								}
							}
						}],
						onClose : function() {
							$(this).dialog('destroy');
						}
					});
				}else{
					 alertMsg('请先选择车牌照！', 'warning');
				}
			  }
			},{
			   id : 'frtReception_item_remove',
			   text : '项目删除',
			   iconCls : 'icon-remove',
			   disabled : true,
			   handler : function (){
				   var row = $('#frtReceptionItemDatagrid').datagrid('getSelected');
					if(row){
						staticFrtReceptionItems = prosceniumDelete('frtReceptionItemDatagrid',row,staticFrtReceptionItems);
					}
		   	   }
		   },{
			   id : 'frtReception_item_diy',
			   text : '自定义项目',
			   iconCls : 'icon-edit',
			   disabled : true,
			   handler : function (){
			   		var items=null;
					if(staticFrtReceptionItems==null){
						items="";
					}else{
						items=JSON.stringify(staticFrtReceptionItems);
					}
			   		$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'frtReceptionAction!addFrtReceptionItem.action',
					   data:'items='+items,
					   success: function callback(r){
							$('#frtReceptionItemDatagrid').datagrid('loadData', r);
							var data = $('#frtReceptionItemDatagrid').datagrid('getData');
							staticFrtReceptionItems=data;
							//frtReceptionToteMoney();
					   }
					});
		   	   }
		   }],
		onClickRow : function (rowIndex, rowData){
			if($('#save').length != 0 && $('#cancel').length != 0){
				copyDateAndBindEventAndThree($frtReceptionItemDatagrid, rowIndex, rowData);					
			}
		},
		 onLoadSuccess : function (data){
			if(operation==true  || recpetionFlag==true){
				staticFrtReceptionItems=data;
				$('#frtReception_item_add').linkbutton('enable');
				$('#frtReception_item_remove').linkbutton('enable');
				$('#frtReception_item_diy').linkbutton('enable');
			}
	   }
	});
}


//前台配件查询datagrid
function loadPartsQuery(){
 	$frtPartsQueryDatagrid = $('#frtPartsQueryDatagrid');
 	$frtPartsQueryDatagrid.datagrid({
 		url : 'frtPartsQueryAction!datagridFrtParts.action',
 		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [[
			{field:'partsId',title : '编码',width : 60,sortable:true},
			{field:'partsId2',title:'编码二',width : 60,sortable:true},
			{field:'ptypeName',title:'型号名称',width:60,sortable:true},
			{field:'partsName',title:'配件名称',width : 80,sortable:true},
			{field:'partsSimpleId',title:'简码',width : 50,sortable:true}
		]],
		columns : [[{
			field : 'posName',
			title : '部位名称',
			width : 80,
			sortable : true
		}, {
			field : 'stateName',
			title : '配件产地',
			width : 80,
			sortable : true
		}, {
			field : 'punitName',
			title : '配件单位',
			width : 60,
			sortable : true
		}, {
			field : 'partsLibrary',
			title : '配件库位',
			width : 60,
			sortable : true
		}, {
			field : 'fitPtype',
			title : '适用车型',
			width : 80,
			sortable : true
		}, {
			field : 'partsNowCount',
			title : '现有库存数量',
			width : 80,
			sortable : true
		}, {
			field : 'storeName',
			title : '所在仓库',
			width : 60,
			sortable : true
		}, {
			field : 'partsRepairPrice',
			title : '维修价',
			width : 60,
			sortable : true
		}, {
			field : 'partsSellPrice',
			title : '销售价',
			width : 60,
			sortable : true
		}, {
			field : 'partsPointPrice',
			title : '网点价',
			width : 60,
			sortable : true
		},{
			field : 'partsSpecialPrice',
			title : '特别价',
			width : 60,
			sortable : true
		},{
			field : 'partsClaimantPrice',
			title : '索赔价',
			width : 60,
			sortable : true
		},{
			field : 'partsLatestTaxprice',
			title : '最新入库含税价',
			width : 90,
			hidden : true
		},{
			field : 'partsLatestNotaxprice',
			title : '最新入库未税价',
			width : 90,
			hidden : true
		},{
			field : 'stockUpper',
			title : '库存上限',
			width : 60,
			hidden : true
		},{
			field : 'stockLower',
			title : '库存下限',
			width : 60,
			hidden : true
		}]]
 	});
}

//工单综合查询->维修项目
function loadResevationItem(url){
	$frtResevationRepairArchivesDatagrid = $('#frtResevationRepairArchivesDatagrid');
	$frtResevationRepairArchivesDatagrid.treegrid({
		url : url,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		fit : true,
		idField : 'receptionId',
		treeField : 'receptionId',
		columns : [ [ {
			field : 'receptionId',
			title : '工单号',
			width : 150,
			sortable : true
		}, {
			field : 'propRepPer',
			title : '托修人',
			width : 60,
			sortable : true
		}, {
			field : 'propPhone',
			title : '托修人手机',
			width : 90,
			sortable : true
		}, {
			field : 'propTel',
			title : '托修人电话',
			width : 90,
			sortable : true
		}, {
			field : 'repitemId',
			title : '项目编号',
			width : 60,
			sortable : true
		}, {
			field : 'rcptitemName',
			title : '项目名称',
			width : 100,
			sortable : true
		}, {
			field : 'rcptitemTime',
			title : '所需工时',
			width : 60,
			sortable : true
		}, {
			field : 'rcptitemInnerTime',
			title : '内部工时',
			width : 60,
			sortable : true
		}, {
			field : 'rcptitemAmout',
			title : '金额',
			width : 60,
			sortable : true
		}, {
			field : 'stfId',
			title : '维修员编号',
			width : 60,
			sortable : true
		}, {
			field : 'planStartTime',
			title : '计划开工时间',
			width : 140,
			sortable : true
		}, {
			field : 'planComplTime',
			title : '计划完工时间',
			width : 140,
			sortable : true
		}, {
			field : 'charge',
			title : '收费性质',
			width : 60,
			sortable : true
		}, {
			field : 'claimsType',
			title : '索赔分类',
			width : 60,
			sortable : true
		}, {
			field : 'receptionRemark',
			title : '备注',
			width : 60,
			sortable : true
		} ] ]
	});
}

//前台接车单->维修建议
function loadMessage(url){
	$frtReceptionAdviceDatagrid = $('#frtReceptionAdviceDatagrid');
	$frtReceptionAdviceDatagrid.datagrid({
		url:url,
		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'carId',
			title : '车辆牌照',
			hidden : true,
			sortable:true,
			width : 100
		},{
			field : 'carLicense',
			title : '车辆牌照',
			sortable:true,
			width : 100
		},{
			field : 'adviceTime',
			title : '发布日期',
			sortable:true,
			width : 120
		},{
			field : 'writePerson',
			title : '发布人员',
			width : 100,
			sortable:true,
			formatter: function(value,row,index){
				return row.writePersonName;
			}
		},{
			field : 'customName',
			title : '客户名称',
			sortable:true,
			width : 100
		},{
			field : 'adviceClass',
			title : '维修分类',
			sortable:true,
			width : 100,
			formatter: function(value,row,index){
				return row.adviceClassName;
			}
		},{
			field : 'adviceContext',
			title : '维修建议',
			width : 300
		},{
			field : 'procesState',
			title : '处理进度',
			width : 100,
			sortable:true,
			formatter: function(value,row,index){
				return row.procesStateName;
			}
		},{
			field : 'procesContext',
			title : '处理结果',
			width : 300
		},{
			field : 'adviceTimeEnd',
			title : '处理时间',
			sortable:true,
			width : 120
		},{
			field : 'procesProson',
			title : '经办人',
			width : 100,
			sortable:true,
			formatter: function(value,row,index){
				return row.procesProsonName;
			}
		}]],
		toolbar : [{
			id : 'frtReception_service_add',
			text : '新增',
			iconCls : 'icon-add',
			disabled : true,
			handler : function() {
					if(rowDataAndCarId==""){
				 		rowDataAndCarId=$('#frtReception_details_carId').combobox('getValue');
				 		rowDataAndCarLicense=$('#frtReception_details_carId').combobox('getText');
					 }
					$('#scarId').val(rowDataAndCarId);
					$('#scarLicense').val(rowDataAndCarLicense);
					$('#sresvId').val($('#frtReception_details_resvId').val());
					if($('#scarId').val()){
						if($('#sresvId').val()){
							opens(0);				
						}else{
							$.messager.confirm('优亿软件提示','预约单信息缺失，<br/>继续增加将不能通过预约单查询维修建议！',function(r){
								if(r){
									opens(0);
								}
							});
						}
					}else{
						$.messager.alert('优亿软件提示','车辆信息缺失，请刷新后重新操作！','warning',function(){});
					}
					
				}
			},{
			   id : 'frtReception_service_remove',
			   text : '删除',
			   iconCls : 'icon-remove',
			   disabled : true,
			   handler : function (){
				   var row = $frtReceptionAdviceDatagrid.datagrid('getSelected');
					if(row){
						$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
							if (r){
								$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'frtReceptionAction!deleteFrtResvAdvice.action',
								   data: row,
								   success: function(r){
									 if(r.success){
										alertMsg(r);
										 $('#frtReceptionAdviceDatagrid').datagrid('load');
									 }
								   }
								});
							}
						});
					}else{
						alertMsg('对不起，请先选中要删除的记录！', 'warning');
					}
		   	   }
		   },{
			   id : 'frtReception_service_accept',
			   text : '修改',
			   iconCls : 'icon-edit',
			   disabled : true,
			   handler : function (){
			   		opens(1);
		   	   }
		   }],
		   onDblClickRow : function (rowIndex, rowData){
				opens(2);
		   },
		   onLoadSuccess : function (data){
			   if(operation==true  || recpetionFlag==true){
					$('#frtReception_service_add').linkbutton('enable');
					$('#frtReception_service_remove').linkbutton('enable');
					$('#frtReception_service_accept').linkbutton('enable');
				}
		   } 
	});
}

//车辆品牌绑定回车事件
function addCarArchives()
{
	$('#frtReception_details_carId').combobox('textbox').on('keyup', function(e)
    {
       if (e.keyCode == 13)
    	   carLic();
    });
var  carLic = function()
     {
     	 var val1 = $('#frtReception_details_carId').combobox('getValue');
         var val2 = $('#frtReception_details_carId').combobox('getText');
         if(val1 == '' || val1 == val2){
  		   $.messager.confirm('系统提示', '您输入的车牌照不存在,是否进行车档案登记?', function(r){
      			if (r){
      				var d = $('<div/>').dialog({
      					href : projectPath+"frt/frtResevation/details/addCarArchives.jsp?carLicense="+val2,
      					modal:true,
      					closable : false,
      					title : '新增车辆档案',
      					width : 800,
      					height : 430,
      					buttons : [{
      						text : '确定',
      						handler : function (){
      							if($('#carArchivesAddForm').form('validate')){
      								$.ajax({
      									   type: 'post',
      									   dataType: 'json',
      									   url: 'basCarArchivesAction!save.action',
      									   data: $('#carArchivesAddForm').serialize(),
      									   success: function(r){
      										   if(r.success){
      										   	 $('#frtReception_details_carId').combobox('reload','frtOptionsAction!findAllCarLicense.action');
          										   $('#frtReception_details_carId').combobox('select', r.obj);
          										   $('#frtReception_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
        							     		   $('#frtReception_details_customId').combobox('select', $('#carArchives_add_customId_view').val());
          										   $('#frtReception_details_carVin').combobox('reload','frtOptionsAction!findAllCarVin.action');
          										   $('#frtReception_details_carVin').combobox('select',$('#addcarVin').val());
          										   document.getElementById('frtReception_details_receptionRemark').innerHTML=$('#carArchives_add_receptionRemark').val();
      											    d.dialog('close');
      											 	alertMsg(r);
      										   }else{
      											    alertMsg(r);
      										   }
      									   },
      									   error : function (r){
      										   if(r.readyState == '0' && r.status == '0'){
      											   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
      										   }
      									   }
      								});
      							}
      					}
      					},{
      						text : '取消',
      						handler : function (){
      							d.dialog('close');
      						}
      					}],
      				    onLoad : function (){
      				    	$('#frtResevation_details_carLicense').val($('#carLicenseview').val());
      			        	$('#carLicenseview').val(val2);
      			        },
      					onClose : function (){
      				    	d.dialog('destroy');
      				    }
      				});
      			}
      	   });
         }else{
      	  		$.post('frtResevationAction!getFrtCar.action', 
              	   { carId : val1 }, 
              	   function (r){
							$('#frtReception_details_customId').combobox('setValue', r.customId);
							$('#frtReception_details_carVin').combobox('setValue',r.carVin);
							$('input[name=propRepPer]').val(r.carRelationPerson);
							$('input[name=propTel]').val(r.carRelationTel1);
							$('input[name=propPhone]').val(r.carRelationTel2);
							document.getElementById('frtReception_details_receptionRemark').innerHTML=r.receptionRemark;
	                   },
	                   'json'
	           );
          }
    }
}

function addCustomArchives(){
	$('#frtReception_details_customId').combobox('textbox').on('keyup', function(e)
    {
          if (e.keyCode == 13)
          {
           var val1 = $('#frtReception_details_customId').combobox('getValue');
           var val2 = $('#frtReception_details_customId').combobox('getText');
           if(val1 == '' || val1 == val2){
       		   $.messager.confirm('系统提示', '您输入的客户名称不存在,是否新建客户档案?', function(r){
        			if (r){
        				var d = $('<div/>').dialog({
        					title: '新增客户档案',   
        					width : 800,
        					height : 380,
        				    cache: false,   
        				    href: projectPath+"frt/frtResevation/details/addCustomArchives.jsp?customId="+val2,   
        				    modal: true,
        				    onClose : function (){
        				    	$(this).dialog('destroy');
        				    },
        				    buttons:[{
        						text:'新增',
        						iconCls:'icon-add',
        						handler:function(){
        							$.ajax({
        							   type: 'post',
        							   dataType: 'json',
        							   url: 'frtCustomAction!saveCustom.action',
        							   data: $('#customArchivesAddForm').serialize()+"&carId="+$('#frtReception_details_carId').combobox('getValue'),
        							   success: function(r){
        							     	if(r.success){
        							     	    $('#frtReception_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
        							     		$('#frtReception_details_customId').combobox('select', r.obj);
        							     		d.dialog('close');
        							     	}else{
        							     		alertMsg(r);
        							     	}
        							   }
        							});
        						}
        				    },{
        						text:'取消',
        						iconCls:'icon-cancel',
        						handler:function(){
        							d.dialog('close');
        						}
        				    }],
        				    onLoad : function (){
        			        	$('#customArchives_add_customName').val(val2);
        			        	$('#customArchives_add_customJm').val(makePy(val2));
        			        }
        				});
        			}
        	   });
           }else{
           	$.post('frtResevationAction!findAllCustom.action', 
              	   { customId : val1 }, 
              	   function (r){
							$('#frtResevation_details_customId').combobox('setValue', r.customId);
	                   },
	                   'json'
	           );
           }
          }
    });
}
function findFrtCarByCarVin(){
	//显示VIN号关联信息
	$('#frtReception_details_carVin').combobox('textbox').on('keyup', function(e)
    {
          if (e.keyCode == 13)
          {
               var val1 = $('#frtReception_details_carVin').combobox('getText');
              	$.post('basCarArchivesAction!findFrtCarByVIN.action', 
                 	   { carVin : val1 }, 
                 	   function (r){
                 	   		$('#frtReception_details_carId').combobox('select', r.carId);
							$('#frtReception_details_customId').combobox('select', r.frtCustom.customId);
							$('input[name=carVin]').val(r.carVin);
							$('input[name=propRepPer]').val(r.carRelationPerson);
							$('input[name=propTel]').val(r.carRelationTel1);
							$('input[name=propPhone]').val(r.carRelationTel2);
							document.getElementById('frtReception_details_receptionRemark').innerHTML=r.receptionRemark;
	                   },
	                   'json'
	           );
          }
    });
}

function frtReceptionToteMoney(){
	if(staticFrtReceptionVehicleStructure!=null){
		staticFrtReceptionVehicleStructure = prosceniumUpdate('vehicleStructureDatagrid',staticFrtReceptionVehicleStructure);
	}
	if(staticFrtReceptionParts!=null){
		staticFrtReceptionParts = prosceniumUpdate('frtReceptionPartsDatagrid',staticFrtReceptionParts);
	}
	if(staticFrtReceptionItems!=null){
		staticFrtReceptionItems = prosceniumUpdate('frtReceptionItemDatagrid',staticFrtReceptionItems);
	}
	if(staticFrtReceptionExpenseSituationOtherExpense!=null){
		staticFrtReceptionExpenseSituationOtherExpense = prosceniumUpdate('frtReceptionExpenseSituationOtherExpense',staticFrtReceptionExpenseSituationOtherExpense);
	}
	var vehicle=null;
	if(staticFrtReceptionVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
	}
	var items=null;
	if(staticFrtReceptionItems==null){
		items="";
	}else{
		items=JSON.stringify(staticFrtReceptionItems);
	}
	var parts=null;
	if(staticFrtReceptionParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtReceptionParts);
	}
	var others=null;
	if(staticFrtReceptionExpenseSituationOtherExpense==null){
		others="";
	}else{
		others=JSON.stringify(staticFrtReceptionExpenseSituationOtherExpense);
	}
	/*计算收费额*/
	$.ajax({
			type : 'post',
			url : 'frtReceptionAction!totemoney.action',
			dataType : 'json',
			data:'&parts='+parts+ '&items='+items+ '&others='+others,
			success : function(r) {
				$('#frtReceptionPreclrWktimeAmount').val(r[0]);
				$('#frtReceptionPreMprMatAmount').val(r[1]);
				$('#frtReceptionPreclrManagementFee').val(r[2]);
				$('#frtReceptionOtherAmount').val(r[3]);
				$('#frtReceptionAmountTotal').val(r[4]);
			}
		});
}

//查询前台接车提醒信息
function showLastService(id){
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!findLastService.action',
		dataType : 'json',
		data:'carId='+id,
		success : function(r) {
			if(r.success){
				$.messager.show({
					width:500,
					height:400,
					title:'接车提醒信息',
					msg:r.obj,
					timeout:0,
					showType:'fade'
				});		
			}
		}
	});
}

//查询车辆预约信息
function bespeakClew(id){
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!bespeakClew.action',
		dataType : 'json',
		data:'carId='+id,
		success : function(r) {
			if(r.success){
				$.messager.confirm('预约信息提示', '所选车辆含有预约信息'+r.obj+'，是否查看详情?', function(t){
					if (t){
						selectFrt(id);
					}
				});	
			}
		}
	});
}

//点击部位触发事件
function addRemark(i, remark) {
	if(operation==true  || recpetionFlag==true){
		var image = '<img src=\"images/sign_tick.png\" id=\"img'+i+'\"/>';
		var imgId = 'img' + i;
		if (remark.children('img').length == 0) {
			remark.append(image);
			$('#' + imgId).on('click', function() {
				clear(remark, i);
			});
			var vehicle=null;
			if(staticFrtReceptionVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
			}
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!addVehicleStructure.action',
				data : 'stateId=' + parseInt(i) + '&vehicle='+vehicle,
				dataType : 'json',
				success : function callback(r) {
					$('#vehicleStructureDatagrid').datagrid('loadData', r);
					var data = $('#vehicleStructureDatagrid').datagrid('getData');
					staticFrtReceptionVehicleStructure=data;
					$('#vehicleStructureDatagrid').datagrid('beginEdit', data.total-1);
				}
			});
		} else {
			clear(remark, i);
		}
	}
}

var _queryPart = function (){
	$('#frtPartsQueryDatagrid').datagrid('load', serializeObject($('#frtPartsQueryForm')));
}

var _clearPart = function (){
	$('#frtPartsQueryForm').form('clear');
	_queryPart();
}

var rowDataAndCarId="";
var rowDataAndCarLicense="";
var opens=function(i){
		if(i==0){
			 if (rowDataAndCarId) {
				 $('#serviceCarId').val(rowDataAndCarId) ;						  
			 }else {
				 $.messager.alert('优亿软件提示','对不起，操作出现异常，请刷新后再尝试！','warning',function(){});
				 return;
			 }		
			 $('#sadviceTime').datetimebox('setValue','{now}');
			 $('#sadviceTimeEnd').datetimebox('setValue','{now}');
			$(function(){
				$('#ee').dialog({
					buttons:[{
						text:'Ok',
						iconCls:'icon-ok',
						handler:function(){
							if($('#frtResvAdviceForm').form('validate')){
								$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: 'frtReceptionAction!addFrtResvAdvice.action',
								   data: $('#frtResvAdviceForm').serialize(),
								   success: function(r){
									 if(r.success){
									 	 closes(0);
										 alertMsg(r);
										 $('#frtReceptionAdviceDatagrid').datagrid({
											 url :  'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' +rowDataAndCarId,
											 onLoadSuccess : function (){
												 $('#frtReception_service_add').linkbutton('enable');
												 $('#frtReception_service_remove').linkbutton('enable');
												 $('#frtReception_service_accept').linkbutton('enable');
											 }
										 });
									 }else{
									 	alertMsg(r);
									 }
								   }
								});
							}else{
								alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
							}
						}
					},{
						text:'Cancel',
						handler:function(){
							closes(0);
						}
					}]
				});
			});					
		}else if(i==1){
			 var selected = $('#frtReceptionAdviceDatagrid').datagrid('getSelected');
			 if (selected) {	
			 	$('#ucarId').val(selected.carId);
			 	$('#ucarLicense').val(selected.carLicense);	
			 	$('#uadviceId').val(selected.adviceId);	
			 	$('#uadviceContext').val(selected.adviceContext);	
			 	$('#uadviceTime').val(selected.adviceTime);
			 	$('#uwritePerson').combobox('setValue',selected.writePerson);	
			 	$('#uprocesContext').val(selected.procesContext);
			 	$('#uprocesState').combobox('setValue',selected.procesState);	
			 	$('#uadviceClass').combobox('setValue',selected.adviceClass);
			 }else {
				 $.messager.alert('优亿软件提示','对不起，请先选中要修改的记录！','warning',function(){});
				 return;
			 }	
			 if($('#uprocesState').combobox('getValue')==PROCESSTATEYES){
			 	$.messager.alert('优亿软件提示','维修建议已处理，不能更改！','warning',function(){});
			 	return;
			 }
			 $('#uadviceTimeEnd').datetimebox('setValue','{now}');
			$(function(){
				$('#ff').dialog({
					title:'修改',
					buttons:[{
						text:'Ok',
						iconCls:'icon-ok',
						handler:function(){
							if($('#ufrtResvAdviceForm').form('validate')){
									$.ajax({
									   type: 'post',
									   dataType: 'json',
									   url: 'frtReceptionAction!updateFrtResvAdvice.action',
									   data: $('#ufrtResvAdviceForm').serialize(),
									   success: function(r){
										 if(r.success){
										 		closes(1);
											 alertMsg(r);
											 $('#frtReceptionAdviceDatagrid').datagrid({
												 url :  'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' +rowDataAndCarId,
												 onLoadSuccess : function (){
													 $('#frtReception_service_add').linkbutton('enable');
													 $('#frtReception_service_remove').linkbutton('enable');
													 $('#frtReception_service_accept').linkbutton('enable');
												 }
											 });
										 }else{
										 	alertMsg(r);
										 }
									   }
									});
							}else{
								alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
							}
						}
					},{
						text:'Cancel',
						handler:function(){
							closes(1);
						}
					}]
				});
			});					
		}else if(i==2){
			 var selected = $('#frtReceptionAdviceDatagrid').datagrid('getSelected');
			 if (selected) {
			 	$('#sscarId').val(selected.carId);
			 	$('#sscarLicense').val(selected.carLicense);	
			 	$('#ssadviceId').val(selected.adviceId);	
			 	$('#ssadviceContext').val(selected.adviceContext);	
			 	$('#ssadviceTime').val(selected.adviceTime);
				$('#ssadviceTimeEnd').val(selected.adviceTimeEnd);
			 	$('#sswritePerson').combobox('setValue',selected.writePerson);
			 	$('#ssprocesContext').val(selected.procesContext);
			 	$('#ssprocesState').combobox('setValue',selected.procesState);
			 	$('#ssprocesProson').combobox('setValue',selected.procesProson);
			 	$('#ssadviceClass').combobox('setValue',selected.adviceClass);
			}
			$(function(){
				$('#dd').dialog({
					buttons:[{
						text:'Ok',
						iconCls:'icon-ok',
						handler:function(){
							closes(2);
						}
					},{
						text:'Cancel',
						handler:function(){
						closes(2);
						}
					}]
				});
			});
	}
}
var closes=function(i){
		if(i==0){
			$('#ee').dialog('close');
			$('#sadviceClass').combobox('setValue','');
			$('#sadviceContext').val('');
			$('#sprocesState').combobox('setValue','');
			$('#sprocesContext').val('');
		}else if(i==1){
			$('#ff').dialog('close');
			$('#ufrtResvAdviceForm').form('clear');
			$('#uadviceTimeEnd').datetimebox('setValue','{now}');
			$('#uprocesProson').combobox('setValue',parame1);
			
		}else if(i==2){
			$('#dd').dialog('close');
			$('#sfrtResvAdviceForm').form('clear');
		}
}