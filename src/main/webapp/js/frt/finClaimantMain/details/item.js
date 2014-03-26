var claimantPrice=null;
function LoadOk() {
	if (document.readyState == "complete") {
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame() {
	if(staticReceptionId!=""){
		$('#finClaimantMainItemDatagrid').datagrid({
			url : 'finClaimantMainAction!findItemByReceptionId.action?receptionId=' +staticReceptionId
		});
	}else{
		var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			$('#finClaimantMainItemDatagrid').datagrid({
				url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=' +rowData.claimantmId
			});
		}else{
			$('#finClaimantMainItemDatagrid').datagrid({
				url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=-1'
			});
		}
	}
	if($('#save').length != 0 && $('#cancel').length != 0){
		$('#finClaimantMainItemDatagrid').datagrid({
			onLoadSuccess : function (data){
				staticFinClaimantMainItems=data;
				$('#finClaimantMain_item_add').linkbutton('enable');
				$('#finClaimantMain_item_remove').linkbutton('enable');
				$('#finClaimantMain_item_accept').linkbutton('enable');
				$('#finClaimantMain_item_diy').linkbutton('enable');
			}
		});
		
	}
}
setTimeout("LoadOk();", 200);

$(function (){
	//索赔结算单->索赔项目
	var url='';
	if(staticReceptionId!=""){
		url='finClaimantMainAction!findItemByReceptionId.action?receptionId=' +staticReceptionId;
	}else{
		var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
		if(rowData){
				url='finClaimantMainAction!findItemByFcmId.action?claimantmId=' +rowData.claimantmId;
		}
	}
	$finClaimantMainItemDatagrid = $('#finClaimantMainItemDatagrid');
	$finClaimantMainItemDatagrid.datagrid({
		url : url,
		fit : true,
		singleSelect : true,
		rownumbers : true,
		fitColumns: true,
		columns : [[
			{
				field:'repitemId',
				title:'项目编号',
				width:60,
				sortable:true
			},         
			{
				field:'repitemName',
				title:'项目名称',
				width:60,
				editor : {
					type : 'text'
				},
				sortable:true
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
					    missingMessage : '操作员为必填项！',
					    onSelect : function (record){
					    	var row = $finClaimantMainItemDatagrid.datagrid('getSelected');
				    		var index = $finClaimantMainItemDatagrid.datagrid('getRowIndex', row);
				    		var ed = $finClaimantMainItemDatagrid.datagrid('getEditor', {index:index,field:'stfName'});
				    		ed.target.val(record.text);
					    }
					}
				},
				formatter : function (value,row,index){
					return row.stfName;
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
					    	var row = $finClaimantMainItemDatagrid.datagrid('getSelected');
				    		var index = $finClaimantMainItemDatagrid.datagrid('getRowIndex', row);
				    		var ed = $finClaimantMainItemDatagrid.datagrid('getEditor', {index:index,field:'reptypName'});
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
			}
		]],
		toolbar : [
			{
				id : 'finClaimantMain_item_add',
				text : '项目增加',
				iconCls : 'icon-add',
				disabled : true,
				handler : function() {
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:projectPath+'baseAction!loadTaskPrice.action',  
					   success: function(r){
						claimantPrice=r;
					   },
					   error : function (r){
						   if(r.readyState == '0' && r.status == '0'){
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
						   }
					   }
					});
					var d = $('<div/>').dialog({
						modal:true,
						title : '维修项目选择',
						closable : true,
						href : projectPath+'frt/finClaimantMain/details/addItem.jsp',
						width : 900,
						height : 560,
						buttons : [{
							text : '确定',
							iconCls : 'icon-add',
							handler : function (){
								var tag=true;
								var rows=$('#selectedItem').datagrid('getRows');
								for ( var i = 0; i < rows.length; i++) {
									var flag=$('#selectedItem').datagrid('validateRow',i);
									if(!flag){
										tag=false;
										break;
									}
								}
								if(tag){
									staticFinClaimantMainItems=prosceniumAdd('selectedItem','finClaimantMainItemDatagrid',staticFinClaimantMainItems);
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
			   id : 'finClaimantMain_item_remove',
			   text : '项目删除',
			   iconCls : 'icon-remove',
			   disabled : true,
			   handler : function (){
					var row = $('#finClaimantMainItemDatagrid').datagrid('getSelected');
					if(row){
						staticFinClaimantMainItems = prosceniumDelete('finClaimantMainItemDatagrid',row,staticFinClaimantMainItems);
					}
		   	   }
		   },{
			   id : 'finClaimantMain_item_diy',
			   text : '自定义项目',
			   iconCls : 'icon-edit',
			   disabled : true,
			   handler : function (){
			   		var items=null;
					if(staticFinClaimantMainItems==null){
						items="";
					}else{
						items=JSON.stringify(staticFinClaimantMainItems);
					}
			   		$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'finClaimantMainAction!addFinClaimantMainItem.action',
					   data:'items='+items,
					   success: function callback(r){
							$('#finClaimantMainItemDatagrid').datagrid('loadData', r);
							var data = $('#finClaimantMainItemDatagrid').datagrid('getData');
							staticFinClaimantMainItems=data;
					   }
					});
		   	   }
		   }
		],
		onClickRow : function (rowIndex, rowData){
			if($('#save').length != 0 && $('#cancel').length != 0){
				copyDateAndBindEventAndThree($finClaimantMainItemDatagrid, rowIndex, rowData);					
			}
		},
		 onLoadSuccess : function (data){
			staticFinClaimantMainItems=data;
	   }
	});
});