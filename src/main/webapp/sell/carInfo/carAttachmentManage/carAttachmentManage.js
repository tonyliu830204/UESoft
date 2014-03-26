$(function(){

	$('#operator_date2').val(getSystemTime());
		 getStartDate($('#operator_date'));
	
			$('#attachmentGrid').datagrid({
				url : 'carInfoAction!getCarAttachment.action',
				pagination : true,
				fit : true,
				rownumbers : true,
				singleSelect : true,
				fitColumns : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				idField : 'carId',
				loadMsg : "数据加载中，请稍后……",
				columns : [[{
					field : 'carId',
					title : '编号',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'carCode',
					title : '车辆编号',
					width : 100,
					sortable : true
					
				},{
					field : 'carVinNumber',
					title : 'VIN号',
					width : 100,
					sortable : true
					
				},{
					field : 'carBrand',
					title : '品牌',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'carBrandName',
					title : '品牌',
					width : 100,
					sortable : true
					
				},{
					field : 'carModelId',
					title : '型号',
					width : 100,
					sortable : true,
					hidden:true
					
				},{
					field : 'carModelName',
					title : '型号',
					width : 130,
					sortable : true
					
				},{
					field : 'colorName',
					title : '颜色',
					width : 100,
					sortable : true
					
				},{
					field : 'dyCount',
					title : '点烟器数量',
					width : 100,
					sortable : true
					
				},{
					field : 'yhCount',
					title : '烟灰缸数量',
					width : 100,
					sortable : true
					
				}
				]],onDblClickRow: function(rowIndex, rowData) {
				var cId=rowData.carId;
				loalDel(cId);
				$('#operator_type').combobox({ required:false});
			}
				});
		});


function loalDel(cId){
	otherFlag=true;
	var d = $('<div/>').dialog({
		modal:true,
		closable :false,
		title : '查看明细信息',
		width : 750,
		height : 500,
		href : projectPath+'sell/carInfo/carAttachmentManage/carAttachmentManageEdit.jsp?carId='+cId,
		cache: false, 
		buttons : [{
			 text : '关闭',
			 iconCls : 'icon-undo',						 
			 handler : function (){
				 d.dialog('close');
			}
       }],
			onClose : function (){
	    	d.dialog('destroy');
	      },onLoad : function() {
	    	  $('#operator_type'). combobox({required:false});
	    	  $('#operator_type'). combobox({required:false});
	    	
	    	  	$("#carAttachmentEdit input").prop("disabled", true);
	  			$("#carAttachmentEdit select").prop("disabled", true);
	  			$("#carAttachmentEdit textarea").prop("disabled",true);
	      }
	  			
	});
}

function addSellInvoice(){
	var data = $('#attachmentGrid').datagrid('getSelected');
	otherFlag=false;
	if(data){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 750,
		height : 500,
		href : projectPath+'sell/carInfo/carAttachmentManage/carAttachmentManageEdit.jsp?carId='+data.carId,
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#carAttachmentEdit').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/carInfoAction!addAttachmentDel.action',
					   data: $('#carAttachmentEdit').serialize(),
					   success: function(r){
						 if(r.success){
							// alertMsg('保存成功！', 'info');
							 $('#attachmentGrid').datagrid('load');
							 $('#detaileList').datagrid('load');
							//d.dialog('close');
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
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    } ,
		    onLoad : function (){
		    	var data = $('#attachmentGrid').datagrid('getSelected');
		    	$('#carAttachmentEdit').form('load', data);	
		    	$('#operator_date_edit').val(getSystemTime());
		    }
		});
	}else{
		alertMsg('对不起，请先选中要新增的信息！', 'warning');
	}
}
//删除
function removeSellInvoice(){
	var data = $('#attachmentGrid').datagrid('getSelected');
	otherFlag=true;
	if(data){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '删除',
		width : 750,
		height : 500,
		href : projectPath+'sell/carInfo/carAttachmentManage/carAttachmentManageEdit.jsp?carId='+data.carId,
		buttons : [{
			iconCls : 'icon-save',
			text : '删除',
			handler : function (){
			var dd=$('#detaileList').datagrid('getSelected')
		    var index=findSelectRowIndex('detaileList',data);
			if(dd){
				if($('#carAttachmentEdit').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/carInfoAction!deleteAttachmentDel.action',
					   data: $('#carAttachmentEdit').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#detaileList').datagrid('clearSelections');
							 $('#attachmentGrid').datagrid('load');
							 $('#detaileList').datagrid('load');
							 setSelectRow('detaileList',index);
							 //alertMsg('删除成功！', 'info');
							
							//d.dialog('close');
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
			}else{
				alertMsg('对不起，请先选中要删除的附件明细信息！', 'warning');
			}
			}
        },{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    } ,
		    onLoad : function (){
		    	var data = $('#attachmentGrid').datagrid('getSelected');
		    	$('#carAttachmentEdit').form('load', data);	
		    }
		});
	}else{
		alertMsg('对不起，请先选中要删除的信息！', 'warning');
	}
}
//修改
var editSellInvoice = function (){
	otherFlag=true;
	var data = $('#attachmentGrid').datagrid('getSelected');
	if(data){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '修改',
		width : 750,
		height : 500,
		href : projectPath+'sell/carInfo/carAttachmentManage/carAttachmentManageEdit.jsp?carId='+data.carId,
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
			var dd=$('#detaileList').datagrid('getSelected')
			if(dd){
				if($('#carAttachmentEdit').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/carInfoAction!updateAttachmentDel.action',
					   data: $('#carAttachmentEdit').serialize(),
					   success: function(r){
						 if(r.success){
							 //alertMsg('修改成功！', 'info');
							 $('#attachmentGrid').datagrid('load');
							 $('#detaileList').datagrid('load');
							//d.dialog('close');
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
			}else{
				alertMsg('对不起，请先选中要修改的附件明细信息！', 'warning');
			}
			}
        },{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    } ,
		    onLoad : function (){
		    	var data = $('#attachmentGrid').datagrid('getSelected');
		    	$('#carAttachmentEdit').form('load', data);	
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的信息！', 'warning');
	}
}
	var querySellInvoice = function (){
		$('#attachmentGrid').datagrid('unselectAll');
		$('#attachmentGrid').datagrid('load', serializeObject($('#invoiceQueryForm')) );
		addInitDate($('#operator_date'),$('#operator_date2'));

		
	
	}
	function clearSearchCondition(){
		$('#invoiceQueryForm').form('clear');
		$('#attachmentGrid').datagrid('load', serializeObject($('#invoiceQueryForm')) );
		$('#car_Brand_id').combobox('reload');
		$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		addInitDate($('#operator_date'),$('#operator_date2'));

	
	}
	
	//导出
	function doexcept(dateGridId,parentId){
		showEditDialog(dateGridId,null,parentId,"开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案"+getSystemTime());
	}
	//打印
	function dopoint(dateGridId,parentId){
		showEditDialog(dateGridId,null,parentId,"开始打印","打印配置",2,_print);
	}
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
	