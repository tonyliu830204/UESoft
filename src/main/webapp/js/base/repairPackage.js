var staticRepairPackageParts=null;
var staticRepairPackageItems=null;
var tabsName = "";
$(function (){
	//基本资料->维修套餐
	$repairPackage = $('#repairPackage');
	$repairPackage.datagrid({
		url : 'basRepairPackageAction!findAllRepairPackage.action',
		singleSelect : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		columns : [[
         {
			field : 'rpId',
			title : '套餐编号',
			width : 25,
			sortable : true
		}, {
			field : 'rpName',
			title : '套餐名称',
			width : 60,
			sortable : true
		},{
			field : 'itemTimeAmount',
			title : '工时费',
			width : 25,
			sortable : true
		},{
			field : 'partsAmount',
			title : '材料费',
			width : 25,
			sortable : true
		},{
			field : 'rpAmount',
			title : '套餐金额',
			width : 25,
			sortable : true
		}, {
			field : 'applicableBrands',
			title : '适合车型',
			width : 180,
			sortable : true,
			formatter : function (value,row,index){
				return row.applicableBrandsName;
			}
		}]],
		onDblClickRow : function(rowIndex, rowData){
			if($('#button').children('a').length == 0){
				$('#tt').tabs('select',1);
				load(rowData);
			}else{
				alertMsg('请先完成新增或修改操作！', 'warning');
			}
		}
	});
	$('#tt').tabs({   
		onSelect:function(title,index){  
			if(index ==0){
				tabsName = "repairPackageCenter";
			}else if(index ==1){
				$('#ee').tabs({   
					onSelect:function(title,index){  
						if(index ==0){
							tabsName = "repairPackagePartsCenter";	
						}else if(index ==1){
							tabsName = "repairPackageItemCenter";
						}
				    } 
				});
			}
	    } 
	});
});

var load = function (rowData){
	$('#repairPackageAddForm').form('load', rowData);
	 $('#applicableCarBrandValue').combobox({disabled:true});
	 $('#repairPackage_rpName').prop("disabled",true);
	if(rowData.applicableBrands != null){
	  setComboboxValues('applicableCarBrandValue',rowData.applicableBrands);
	 
	}
	loadPart(rowData);
    loadPackage(rowData);
}

var loadPart = function (rowData){
	//维修套餐->维修配件
	$repairPackageParts = $('#repairPackageParts');
	$repairPackageParts.datagrid({
		url : 'basRepairPackageAction!getSelectedParts.action',
		queryParams: {rpId: rowData != null ? rowData.rpId : null},
		singleSelect : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		columns : [[{
			field : 'partsId',
			title : '配件编码',
			width : 60
		},{
			field : 'partsName',
			title : '配件名称',
			width : 60
		},{
			field : 'punitId',
			title : '单位',
			width : 60,
			formatter : function (value,row,index){
				return row.punitName;
			}
		},{
			field : 'punitName',
			title : '单位',
			width : 60,
			hidden : true
		},{
			field : 'partsNum',
			title : '数量',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					min:0,
					required : true,
					missingMessage : "数量为必填项!"
				}
			}
		},{
			field : 'partsRepairPrice',
			title : '单价',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					min:0,
					required : true,
					missingMessage : "单价为必填项!"
				}
			}
		},{
			field : 'partsAmount',
			title : '金额',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					disabled : true,
					precision : 2,
					min:0
				}
			}
		},{
			field : 'storeId',
			title : '仓别',
			width : 60,
			formatter : function (value,row,index){
				return row.storeName;
			},
			hidden : true
		},{
			field : 'storeName',
			title : '仓别',
			width : 60,
			hidden : true
		}]],
		toolbar : [{
			id : 'repairPackagePartsAdd',
			text : '配件增加',
			iconCls : 'icon-add',
			disabled : true,
			handler : function (){
				var d = $('<div/>').dialog({
					modal:true,
					title : '配件选择',
					closable : true,
					href : projectPath+'base/repairPackage/addParts.jsp',
					//href :'frt/frtReception/details/addParts.jsp',
					width : 800,
					height : 560,
					buttons : [{
						text : '确定',
						iconCls : 'icon-add',
						handler : function (){
							staticRepairPackageParts=prosceniumAdd('selectedParts','repairPackageParts',staticRepairPackageParts);
							repairPackageAmount();
							d.dialog('close');								
						}
					}],
					onClose : function (){
				    	$(this).dialog('destroy');
				    }
				});						
			}
		},{
			id : 'repairPackagePartsRemove',
			text : '配件删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function (){
				var row = $('#repairPackageParts').datagrid('getSelected');
				if(row){
					staticRepairPackageParts = prosceniumDelete('repairPackageParts',row,staticRepairPackageParts);
					repairPackageAmount();
				}
			}
		}],
		onClickRow : function (rowIndex, rowData){
			if(!$('#repairPackagePartsAdd').linkbutton('options').disabled){
				partsEditorBindEvent($repairPackageParts, rowIndex);
			}
		},
		onLoadSuccess : function (data){
			staticRepairPackageParts=data;
		}
	});
	
}

var loadPackage = function (rowData){
	//基本资料->维修套餐->维修项目
	$repairPackageItem = $('#repairPackageItem');
	$repairPackageItem.datagrid({
		url : 'basRepairPackageAction!getSelectedItem.action',
		queryParams: {rpId: rowData != null ? rowData.rpId : null},
		singleSelect : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		columns : [[{
			field : 'repitemId',
			title : '项目编号',
			width : 60
		}, {
			field : 'repitemName',
			title : '项目名称',
			width : 60
		},{
			field : 'repitemNum',
			title : '维修工时',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2,
					min : 1,
					missingMessage : "工时数为必填项!"
				}
			}
		},{
			field : 'repitemAmount',
			title : '工时费',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1,
					precision : 2,
					missingMessage : "工时费为必填项!"
				}
			}
		}]],
		toolbar : [{
			id : 'repairPackageItemAdd',
			text : '项目增加',
			iconCls : 'icon-add',
			disabled : true,
			handler : function (){
				var d = $('<div/>').dialog({
					modal:true,
					title : '维修项目选择',
					closable : true,
					href : projectPath+'base/repairPackage/addItem.jsp',
					width : 700,
					height : 560,
					buttons : [{
						text : '确定',
						iconCls : 'icon-add',
						handler : function (){
							staticRepairPackageItems=prosceniumAdd('selectedItem','repairPackageItem',staticRepairPackageItems);
						/*
							endEdit($('#selectedItem'));
							var effectRow = $('#selectedItem').datagrid('getData');
							$('#repairPackageItem').datagrid('loadData',effectRow);
							staticRepairPackageItems=$('#repairPackageItem').datagrid('getData');								
						*/
							repairPackageAmount();
							d.dialog('close');
						}
					}],
					onClose : function (){
				    	$(this).dialog('destroy');
				    }
				});
			}
		},{
			id : 'repairPackageItemRemove',
			text : '项目删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function (){
				var row = $('#repairPackageItem').datagrid('getSelected');
				if(row){
					staticRepairPackageItems = prosceniumDelete('repairPackageItem',row,staticRepairPackageItems);
					/*
					var index =$('#repairPackageItem').datagrid('getRowIndex', row);
					$('#repairPackageItem').datagrid('deleteRow', index);
					var effectRow = $('#repairPackageItem').datagrid('getData');
					$('#repairPackageItem').datagrid('loadData',effectRow);
					staticRepairPackageItems=$('#repairPackageItem').datagrid('getData');
					*/
					repairPackageAmount();
				}
			}
		}],
		onSelect : function (rowIndex, rowData){
			var ed = $(this).datagrid('getEditors', rowIndex);
			if(ed[0]){
				ed[0].target.select();
			}
		},
		onClickRow : function (rowIndex, rowData){
			if(!$('#repairPackageItemAdd').linkbutton('options').disabled){
				itemEditorBindEvent($repairPackageItem, rowIndex);
			}
		},
		onLoadSuccess : function (data){
			staticRepairPackageItems=data;
		}
	});	
}

var partsEditorBindEvent = function (id, rowIndex){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
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
	ed[2].target.numberbox({
		onChange : function (newValue, oldValue){
			$('#repairPackage_partsAmount').val(accAdd(accSub($('#repairPackage_partsAmount').val(), oldValue), newValue).toFixed(2));
			sumRpAmount();
		}
	});
	ed[0].target.click(function (){
		ed[0].target.select();
	});
	ed[1].target.click(function (){
		ed[1].target.select();
	});
	ed[0].target.keydown(function (e){
		if(e.keyCode == '13'){
			ed[1].target.select();
		}
	});
	ed[1].target.keydown(function (e){
		if(e.keyCode == '13'){
			if(rowIndex < id.datagrid('getData').total - 1){
				partsEditorBindEvent(id, rowIndex + 1);
			}else{
				var ed = id.datagrid('getEditors', 0);
				partsEditorBindEvent(id, 0);
			}
		}
	});
}
var itemSum=0.00;
var itemEditorBindEvent = function (id, rowIndex){
	id.datagrid('beginEdit', rowIndex);
	var ed = id.datagrid('getEditors', rowIndex);
	itemSum=(ed[1].target.val()/ed[0].target.val());
	ed[0].target.select();
	ed[0].target.bind('keyup', function() {
		var num = ed[0].target.val();
		var price = itemSum;
		var amount = accMul(parseFloat(num), parseFloat(price));
		ed[1].target.numberbox('setValue', amount);
	});
	ed[1].target.select();
	ed[1].target.bind('keyup', function() {
		var num = ed[1].target.val();
		var price = itemSum;
		var amount =(parseFloat(num)/parseFloat(price));
		ed[0].target.numberbox('setValue', amount);
	});
	ed[1].target.numberbox({
		onChange : function (newValue, oldValue){
			$('#repairPackage_itemTimeAmount').val(accAdd(accSub($('#repairPackage_itemTimeAmount').val(), oldValue), newValue).toFixed(2));
			sumRpAmount();
		}
	});
}

var add = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}else{
		load("");
		//cancel();
		disable();
		addButton();
		$('#repairPackageAddForm').form('clear');
		$('#applicableCarBrandValue').combobox({disabled:false});
		$('#tt').tabs('select','套餐明细');
		//$('#applicableBrandsButton').linkbutton('enable');
		$('#repairPackagePartsAdd').linkbutton('enable');
		$('#repairPackagePartsRemove').linkbutton('enable');
		$('#repairPackagePartsAccept').linkbutton('enable');
		$('#repairPackageItemAdd').linkbutton('enable');
		$('#repairPackageItemRemove').linkbutton('enable');
		$('#repairPackageItemAccept').linkbutton('enable');
	}
}

var _remove = function (){
	var row = $('#repairPackage').datagrid('getSelected');
	if(row){
	//alert(":"+row.rpId+":"+row.rpName+":");
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function (r){
			if(r){
				$.ajax({
					type: 'post',
					dataType: 'json',
					url: 'basRepairPackageAction!remove.action?rpId='+row.rpId+'&rpName='+row.rpName,
					success: function (r){
						if(r.success){
							alertMsg(r);
                            $('#repairPackage').datagrid('load');
						}
					}
				});
			}
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}

var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	var row = $repairPackage.datagrid('getSelected');
	if(row){
		$('#applicableCarBrandValue').combobox({disabled:false});
		load(row);
		disable();
		addButton();
		$('#tt').tabs('select','套餐明细');
		//$('#applicableBrandsButton').linkbutton('enable');
		$('#repairPackagePartsAdd').linkbutton('enable');
		$('#repairPackagePartsRemove').linkbutton('enable');
		$('#repairPackagePartsAccept').linkbutton('enable');
		$('#repairPackageItemAdd').linkbutton('enable');
		$('#repairPackageItemRemove').linkbutton('enable');
		$('#repairPackageItemAccept').linkbutton('enable');
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var queryRp = function (){
	$('#repairPackage').datagrid('load', serializeObject($('#repairPackageQueryForm')));
}

var save = function (){
	if($('#repairPackageAddForm').form('validate')){
		if(staticRepairPackageParts!=null){
			staticRepairPackageParts = prosceniumUpdate('repairPackageParts',staticRepairPackageParts);
		}
		if(staticRepairPackageItems!=null){
			staticRepairPackageItems = prosceniumUpdate('repairPackageItem',staticRepairPackageItems);
		}
		var row = $('#repairPackage').datagrid('getSelected');
		var url = 'basRepairPackageAction!';
		if(row){
			url += 'edit.action';
		}else{
			url += 'save.action';
		}
		var items=null;
		if(staticRepairPackageItems==null){
			items="";
		}else{
			items=JSON.stringify(staticRepairPackageItems);
		}
		var parts=null;
		if(staticRepairPackageParts==null){
			parts="";
		}else{
			parts=JSON.stringify(staticRepairPackageParts);
		}
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: $('#repairPackageAddForm').serialize()+'&parts='+parts+'&items='+items,
			success: function (r){
				if(r.success){
			   		 alertMsg(r);
					$('#button').empty();
					//$('#applicableBrandsButton').linkbutton('disable');
					$('#repairPackagePartsAdd').linkbutton('disable');
					$('#repairPackagePartsRemove').linkbutton('disable');
					$('#repairPackagePartsAccept').linkbutton('disable');
					$('#repairPackageItemAdd').linkbutton('disable');
					$('#repairPackageItemRemove').linkbutton('disable');
					$('#repairPackageItemAccept').linkbutton('disable');
					$('#repairPackageAddForm').form('clear');
					load("");
					$('#tt').tabs('select','套餐汇总');
					$('#repairPackage').datagrid('load');
				}
				enable();
			}
		});
	}
}

var cancel = function (){
	$('#button').empty();
	//$('#applicableBrandsButton').linkbutton('disable');
	$('#repairPackagePartsAdd').linkbutton('disable');
	$('#repairPackagePartsRemove').linkbutton('disable');
	$('#repairPackagePartsAccept').linkbutton('disable');
	$('#repairPackageItemAdd').linkbutton('disable');
	$('#repairPackageItemRemove').linkbutton('disable');
	$('#repairPackageItemAccept').linkbutton('disable');
	$('#repairPackageAddForm').form('clear');
	load("");
	enable();
	$('#tt').tabs('select','套餐汇总');
	$('#repairPackage').datagrid('load');
}

var addButton = function (){
	var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if($('#button').children('a').length == 0){
		$('#button').append(save).append(cancel);
		$.parser.parse('#button');
	}
}

var disable = function (){
	 $('#add').linkbutton('disable');
	 $('#delete').linkbutton('disable');
	 $('#edit').linkbutton('disable');
	 $('#search').linkbutton('disable');
	 $('#cancelbut').linkbutton('disable');
	 $('#export').linkbutton('disable');
	$('#repairPackage_rpName').validatebox({required:true});
	$('#repairPackage_rpName').validatebox('validate');
	 $("#repairPackageAddForm a").linkbutton('enable');
	 
	 $("#repairPackageAddForm input").prop("disabled", false);
	 $("#repairPackageAddForm select").prop("disabled", false);
	 $("#repairPackageAddForm textarea").prop("disabled", false);
	 
}

var enable = function (){
	 $('#add').linkbutton('enable');
	 $('#delete').linkbutton('enable');
	 $('#edit').linkbutton('enable');
	 $('#search').linkbutton('enable');
	 $('#cancelbut').linkbutton('enable');
	 $('#export').linkbutton('enable');
	$('#repairPackage_rpName').validatebox({required:false});
	$('#repairPackage_rpName').validatebox('validate');
	 $("#repairPackageAddForm a").linkbutton('disable');
	 $("#repairPackageAddForm input").prop("disabled", true);
	 $("#repairPackageAddForm select").prop("disabled", true);
	 $("#repairPackageAddForm textarea").prop("disabled",true);
}

/*计算金额-后台计算*/
var repairPackageAmount = function (){
	var parts=JSON.stringify(staticRepairPackageParts);
	var items=JSON.stringify(staticRepairPackageItems);
	$.ajax({
		type: 'post',
		dataType: 'json',
		url: 'basRepairPackageAction!amount.action?parts='+parts+'&items='+items,
		success: function (r){
				$('#repairPackage_partsAmount').val(r[0].toFixed(2));
				$('#repairPackage_itemTimeAmount').val(r[1].toFixed(2));
				$('#repairPackage_rpAmount').val(r[2].toFixed(2));
		}
	});
}
/*计算金额-前台计算*/
var sumRpAmount = function (){
		var partsAmount=$('#repairPackage_partsAmount').val();
		var itemTimeAmount=$('#repairPackage_itemTimeAmount').val();
		$('#repairPackage_rpAmount').val(accAdd(partsAmount,itemTimeAmount).toFixed(2));
}
/**
 * 
 * 导出excel
 * @return
 */
function _except(){
	exportEsuyUIExcelFile(tabsName,null,"维修套餐"+getSystemTime());
}