var taskPrice;
$.ajax({
   type: 'post',
   dataType: 'json',
   url:'baseAction!loadTaskPrice.action',  
   success: function(r){
	taskPrice=r
   },
   error : function (r){
	   if(r.readyState == '0' && r.status == '0'){
		   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
	   }
   }
   
});
$(function (){
	//待选维修项目		
	$selectionItem = $('#selectionItem');
	
	$selectionItem.datagrid({
		url :'basRepairPackageAction!findAllSelectionItem.action',
		//fit : true,
		height: 225,
		//width: 865,
		fitColumns : true,
		idField : 'repitemId',
		singleSelect : true,
		rownumbers : true,
		//pagination:true,
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
			if(taskPrice){
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
				$('#selectedItemTotal').html('共' + $selectedItem.datagrid('getData').total + '条记录');
				var index = $selectedItem.datagrid('getRowIndex', rowData);
				itemcopyDateAndBindEvent($selectedItem, index,rowData.repitemTime);
			}else{
				alertMsg('对不起，请先设置工时单价！', 'warning');
			}
			
		}
	});
	var itemcopyDateAndBindEvent=function(id,rowIndex,repitemTime){
		id.datagrid('beginEdit', rowIndex);
		var ed = id.datagrid('getEditors', rowIndex);
		if(repitemTime!=null && repitemTime!=''){
				ed[0].target.numberbox('setValue', repitemTime);
			}else{
				//ed[0].target.numberbox('setValue', '0.00');
				}
		ed[0].target.select();
		ed[0].target.bind('keyup', function() {
			var num = ed[0].target.val();
			ed[1].target.numberbox('setValue', accMul(ed[0].target.val(), parseFloat(taskPrice)));
		});
		ed[1].target.select();
		ed[1].target.numberbox('setValue', accMul(ed[0].target.val(), parseFloat(taskPrice)));
		ed[1].target.bind('keyup', function() {
			var num = ed[1].target.val();
			var amount =(parseFloat(num)/parseFloat(taskPrice));
			ed[0].target.numberbox('setValue', amount);
		});
	}
	//已选维修项目
	$selectedItem = $('#selectedItem');
	
	$selectedItem.datagrid({
		//url : parojectPat+'basRepairPackageAction!findAllSelectedItem.action',
		url:"",
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
			field : 'repitemNum',
			title : '维修工时',
			width : 60,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 0,
					precision : 2,
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
					min : 0,
					precision : 2,
					missingMessage : "工时费为必填项!"
				}
			}
		}]],
		onClickRow : function (rowIndex, rowData){
			$(this).datagrid('unselectRow', rowIndex);
		},
		onDblClickRow : function(rowIndex, rowData){
			var ed = $(this).datagrid('getEditors', rowIndex);
			if(ed.length){
				$(this).datagrid('deleteRow', rowIndex);
				$('#selectedItemTotal').html('共' + $(this).datagrid('getData').total + '条记录');
				$selectionItem.datagrid('appendRow', rowData);
			}
		},
		onLoadSuccess : function (data){
			$('#selectedItemTotal').html('共' + data.total + '条记录');
		}
	});
	//$selectedItem.datagrid('loadData',staticRepairPackageItems);
});