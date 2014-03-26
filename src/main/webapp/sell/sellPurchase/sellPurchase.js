$(function(){
	$('#queryPlanDate2').val(getSystemTime());
	 getStartDate($('#queryPlanDate'));
			$('#sellPurchase').datagrid({
					url:'${pageContext.request.contextPath}/sellPurchaseAction!getPager.action',
					fit:true,
					fitColumns : true,
					pagination : true,
					sortOrder:'desc',
				    sortName:'id',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [  {		
  			        	field : 'id',
  						title : '编号',
  						sortable : true,
  						hidden:true
  					},{		
  			        	field : 'purchaseId',
  						title : '采购编号',
  						width : 100,
  						sortable : true
  					},{		
  			        	field : 'sellDate',
  						title : '计划月份',
  						width : 50,
  						sortable : true,
  					},{
  						field : 'carBrand',
  						title : '车辆品牌',
  						width : 60,
  						sortable : true,
  						formatter: function(value,row,index){
  							return row.brandName;
  						}
  					},{
  						field : 'carModelId',
  						title : '车辆型号',
  						width : 60,
  						sortable : true,
  						formatter: function(value,row,index){
  							return row.modelName;
  						}
  					},{
  						field : 'carColor',
  						title : '颜色',
  						width : 60,
  						sortable : true,
						formatter: function(value,row,index){
							return row.colorName;
						}
  					},{
  						field : 'planNumber',
  						title : '计划采购数量',
  						width : 60,
  						sortable : true,
  					},{		
  			        	field : 'actualNumber',
  						title : '实际采购数量',
  						width : 60,
  						sortable : true,
  					},{		
  			        	field : 'remark',
  						title : '问题反馈',
  						width : 100,
  						sortable : true,
  					},{		
  			        	field : 'planPercent',
  						title : '完成百分比',
  						width : 60,
  						sortable : true
  					},{
  						title : '企业编号',
  						field : 'enterprise_id',
  						width : 100,
  						hidden:true
  					}

				        ]]
				});
		});
function addSellPurchase(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width :500,
		height : 320,
		width : 550,
		height : 300,
		href : projectPath+'sell/sellPurchase/sellPruchaseEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#sellEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/sellPurchaseAction!saveSellPurchase.action',
					   data: $('#sellEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#sellPurchase').datagrid('load');
							 d.dialog('close');
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
        	iconCls : 'icon-undo',
			text : '取消',
			handler : function (){
        		d.dialog('close');
			}
        }],
	        onLoad : function (){
	    	$("#sellEditForm input").prop("disabled", false);
	    	$("#remark").prop("disabled", false);
	    	$("#sellEditForm input.easyui-combobox").combobox("enable");
	    	$("#sellEditForm input.easyui-datebox").datebox("enable");
			},
	        onClose : function (){
		    	$(this).dialog('destroy');
		    }
		});
}
//删除
function removeSellPurchase(){
var data = $('#sellPurchase').datagrid('getSelected');
var index=findSelectRowIndex('sellPurchase',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/sellPurchaseAction!deleteSellPurchase.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						   $('#sellPurchase').datagrid('clearSelections');
						   $('#sellPurchase').datagrid('load');
						   setSelectRow('sellPurchase',index);
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
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}		
}
//修改
var editSellPurchase = function (){
	var data = $('#sellPurchase').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width :500,
			height : 320,
			href : projectPath+'sell/sellPurchase/sellPruchaseEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#sellEditForm').form('validate')){
						$("#sellEditForm input").prop("disabled", false);
						$("#remark").prop("disabled", false);
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellPurchaseAction!updateSellPurchase.action',						   
						   data: $('#sellEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#sellPurchase').datagrid('reload');
								 d.dialog('close');
							 }else{
								/*$("#sellEditForm input").prop("disabled", true);
							    $("#sellEditForm input.easyui-combobox").combobox("disable");
							    $("#sellEditForm input.easyui-datebox").datebox("disable");*/
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
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
	        		d.dialog('close');
				}
	        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	$("#actualNumber").prop("disabled", false);
		    	$("#remark").prop("disabled", true);
		    	$("#sellEditForm input.easyui-combobox").combobox("disable");
		    	$("#sellEditForm input.easyui-datebox").datebox("disable");
		    	var data = $('#sellPurchase').datagrid('getSelected');
		    	$('#sellEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var querySellPurchase = function (){
		$('#sellPurchase').datagrid('unselectAll');
		$('#sellPurchase').datagrid('load', serializeObject($('#sellQueryForm')) );
		addInitDate($('#queryPlanDate'),$('#queryPlanDate2'));
}
	function clearSearchCondition(){
			$('#sellQueryForm').form('clear');
			$('#sellPurchase').datagrid('load', serializeObject($('#sellQueryForm')) );
			addInitDate($('#queryPlanDate'),$('#queryPlanDate2'));
			$('#car_Brand_id').combobox('reload');
			$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action'); 
		
		
	}

	function _except(){
		var data =  $("#sellPurchase").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("sellPurchase",null,"sellPurchase_div","开始导出","导出配置",0,_callbackExcept);
	}

	function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"采购计划管理"+getSystemTime());
	}


	function _config(){
	var data =  $("#sellPurchase").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("sellPurchase",personnelSumTable,"sellPurchase_div","开始打印","打印配置",2,_print);

	}

	function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
	}