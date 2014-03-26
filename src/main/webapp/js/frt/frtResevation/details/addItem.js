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
		//width: 665,
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
			formatter : function (value,row,index){
				return row.repitemSeriesName;
			},
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
			copyDateAndBindEventAndTwo($selectedItem, index, rowData,sPrice);
		}
	});
	
	//已选维修项目
	$selectedItem = $('#selectedItem');
	
	$selectedItem.datagrid({
		url:'',
		//fit : true,
		height: 120,
		fitColumns : true,
		idField : 'repitemId',
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
				type : 'numberbox',
				options : {
			        min : 1
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
			title : '维修人员',
			width : 60,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'basStuffClassAction!findEnterpriseMaintainPerson.action',
					valueField:'id',   
				    textField:'text',
				    mode:'remote',
//				    onLoadSuccess : function (){
//				    	var data = $(this).combobox('getData');
//				    	if(data[0]){
//				    		$(this).combobox('setValue', data[0].id);
//				    		var index = $selectedItem.datagrid('getData').total - 1;
//				    		var ed = $selectedItem.datagrid('getEditor', {index:index,field:'stfName'});
//				    		ed.target.val(data[0].text);
//				    	}
//				    },
				    onSelect : function (record){
				    	var index = $selectedItem.datagrid('getData').total - 1;
			    		var ed = $selectedItem.datagrid('getEditor', {index:index,field:'stfName'});
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
				$selectionItem.datagrid('appendRow', rowData);
			}
		}
	});
	//$('#selectedItem').datagrid('loadData',staticFrtResevationItems);
}
var _query=function (){
	$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
}
var _clear=function (){
	$('#selectionItemForm').form('clear'); 
	$selectionItem.datagrid('load', serializeObject($('#selectionItemForm')));
}