$(function(){
	
	//初试时间
 	$('#xs_Car_Sel_Data').val(getStartDate($('#xs_Car_Sel_Data')));
	$('#xs_Car_Sel_Data2').val(getSystemTime());
	
			$('#sellInvoiceGrid').datagrid({
				url : 'sellInvoiceAction!findSellInfor.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				fitColumns : false,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'id',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'id',
					title : '编号',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'invoiceCode',
					title : '开票单号',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceDate',
					title : '开票日期',
					width : 110,
					sortable : true
					
				},{
					field : 'invoicePersonN',
					title : '开票人',
					width : 100,
					sortable : true
					
				},{
					field : 'InvoicePerson',
					title : '开票人',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'personN',
					title : '收款人',
					width : 100,
					sortable : true
					
				},{
					field : 'person',
					title : '收款人',
					width : 100,
					sortable : true,
					hidden:true
				},{
					field : 'invoiceNumber',
					title : '发票号码',
					width : 100,
					sortable : true
					
				},{
					field : 'achievement',
					title : '业绩系数',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceParce',
					title : '开票金额',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceType',
					title : '票据类型',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceRemark',
					title : '开票备注',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceExaminName',
					title : '审核状态',
					width : 100,
					sortable : true
					
				},{
					field : 'invoiceExamin',
					title : '审核状态',
					sortable : true,
					hidden:true
					
				},{
					field : 'xs_Car_Sel_Id',
					title : '销售单号',
					width : 100,
					hidden:true,
					sortable : true
					
				},{
					field : 'sellCode',
					title : '销售单号',
					width : 100,
					sortable : true
					
				},{
					field : 'out_Id',
					title : '出库单号',
					width : 100,
					sortable : true,
					hidden : true
					
				},{
					field : 'reserve_Code',
					title : '预订单编号',
					width : 100,
					sortable : true,
					hidden : true
					
				},{
					field : 'xs_Car_Sel_Data',
					title : '销售日期',
					width : 100,
					sortable : true
					
				},{
					field : 'xs_Custom_Name',
					title : '客户名称',
					width : 100,
					sortable : true
					
				},{
					field : 'stf_Name',
					title : '业务员',
					width : 100,
					sortable : true
					
				},{
					field : 'xs_Car_Vin_Number',
					title : 'VIN编号',
					width : 100,
					sortable : true
				}, {
					field : 'xs_Car_Ocn',
					title : 'OCN码',
					width : 100,
					sortable : true
					
				}, {
					field : 'xs_Car_Brand',
					title : '车品牌',
					width : 100,
					sortable : true
					
				}, {
					field : 'xs_Model_Name',
					title : '车类型',
					width : 100,
					sortable : true
					
				}, {
					field : 'xs_Car_Sel_Transaction_Money',
					title : '成交价格',
					width : 100,
					sortable : true
				}/*, {
					field : 'STF_ID_PERSON',
					title : '经办人',
					width : 100,
					sortable : true
				}*/, {
					field : 'xs_Distributor_Name',
					title : '分销商',
					width : 100,
					sortable : true
				}/*, {
					field : 'examine',
					title : '审核情况',
					width : 100,
					sortable : true
				}*/, {
					field : 'limit_load_num',
					title : '限乘人数',
					width : 100,
					sortable : true
				}, {
					field : 'mobilephone',
					title : '电话',
					width : 100,
					sortable : true
				
				},{
					field : 'isInvoice',
					title : '是否开票',
					width : 100,
					sortable : true,
					hidden:true
					
				}
				]]
				});
		});
function addSellInvoice(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 600,
		height : 400,
		href : projectPath+'sell/sellInvoice/sellInvoiceEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#sellInvoiceEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/sellInvoiceAction!saveSellInvoice.action',
					   data: $('#sellInvoiceEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#sellInvoiceGrid').datagrid('load');
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
	        onClose : function (){
		    	$(this).dialog('destroy');
		    } ,
		    onLoad : function (){
		    	$("#sellImage").bind("click", addCarSel); 
		    }
		});
}
//删除
function removeSellInvoice(){
var data = $('#sellInvoiceGrid').datagrid('getSelected');
var index=findSelectRowIndex('sellInvoiceGrid',data);
	if(data){
		$.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'sellInvoiceAction!isRefundment.action',
			data : 'invoiceExamin='+data.invoiceExamin,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该信息已审核，不能删除！', 'warning');
						return;							
					}else{
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
				if (r){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'sellInvoiceAction!deleteSellInvoice.action',
					   data: data,
					   success: function(r){
						 if(r.success){
							    $('#sellInvoiceGrid').datagrid('clearSelections');
							    $('#sellInvoiceGrid').datagrid('load');
								setSelectRow('sellInvoiceGrid',index);
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
					}
				 }
				 }
				 });
			}else{
				alertMsg('对不起，请先选中要删除的记录！', 'warning');
			}		
		}
	
//修改
var editSellInvoice = function (){
	var data = $('#sellInvoiceGrid').datagrid('getSelected');
	if(data){
		$.ajax({
			type : 'POST',
		    dataType : 'json',
			url : 'sellInvoiceAction!isRefundment.action',
			data : 'invoiceExamin='+data.invoiceExamin,
			success : function(r){
				if(r.success){
					if(r.obj==true){
						alertMsg('该信息已审核，不能修改！', 'warning');
						return;							
					}else{
						var d = $('<div/>').dialog({
							modal:true,
							closable : true,
							title : '修改',
							width : 600,
							height : 400,
							href : projectPath+'sell/sellInvoice/sellInvoiceEdit.jsp',
							buttons : [{
								iconCls : 'icon-save',
								text : '保存',
								handler : function (){
									if($('#sellInvoiceEditForm').form('validate')){
										$.ajax({
										   type: 'post',
										   dataType: 'json',
										   url: 'sellInvoiceAction!updateSellInvoice.action',	   
										   data: $('#sellInvoiceEditForm').serialize(),
										   success: function(r){
											 if(r.success){
												 $('#sellInvoiceGrid').datagrid('reload');
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
					        onClose : function (){
						    	$(this).dialog('destroy');
						    },
						    onLoad : function (){
						    	var data = $('#sellInvoiceGrid').datagrid('getSelected');
						    	$('#sellInvoiceEditForm').form('load', data);
						    	$("#sellImage").unbind();
						    }
						});
					}
				 }
				 }
				 });
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
var querySellInvoice = function (){
	$('#sellInvoiceGrid').datagrid('unselectAll');
	$('#sellInvoiceGrid').datagrid('load', serializeObject($('#invoiceQueryForm')) );
	addInitDate($('#xs_Car_Sel_Data'),$('#xs_Car_Sel_Data2'));

}
function clearSearchCondition(){
	$('#invoiceQueryForm').form('clear');
	querySellInvoice();
}
//审核
function examine_(){
	var data = $('#sellInvoiceGrid').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要审核该条记录？', function(r){
			if (r){
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'sellInvoiceAction!updateExamin.action',
					   data: data,
					   success: function(r){
						 if(r.success){
							 $('#sellInvoiceGrid').datagrid('load');
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
	alertMsg('对不起，请先选中要审核的记录！', 'warning');
 }
}

	//导出
	function doexcept(dateGridId,parentId){
		var data =  $('#'+dateGridId+'').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"车辆销售财务信息管理"+getSystemTime());
	}
	//打印
	function dopoint(dateGridId,parentId){
		var data =  $('#'+dateGridId+'').datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
		showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
	}
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
	

