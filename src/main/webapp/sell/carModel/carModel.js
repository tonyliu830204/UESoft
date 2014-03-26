$(function(){
			$('#carModel').datagrid({
					url:'${pageContext.request.contextPath}/carModelAction!getPageModel.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder : 'desc',
				    sortName:'modelCode',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'modelId',
							width : 50,
							sortable:true,
							hidden:true
					    }, {
							title : '车型编码',
							field : 'modelCode',
							width : 150,
							sortable:true
					    }, {
							title : '车型名称',
							field : 'modelName',
							width : 100,
							sortable:true
						}, {
							title : '车品牌',
							field : 'carBrandName',
							width : 50,
							sortable:true
						}, {
							title : '成本价',
							field : 'modelCostPrice',
							width : 50,
							sortable:true
						}, {
							title : '销售价',
							field : 'modelSalesPrice',
							width : 50,
							sortable:true
						}, {
							title : '销售限价',
							field : 'modelSalesLimitPrice',
							width : 50,
							sortable:true
						}, {
							title : '二级限价',
							field : 'modelTwoSalesLimitPrice',
							width : 50,
							sortable:true
						}, {
							title : '三级限价',
							field : 'modelThreeSalesLimitPrice',
							width : 50,
							sortable:true
						}, {
							title : '是否取消型号',
							field : 'modelCancelModel',
							width : 50,
							sortable:true,
							formatter : function(value,rowData,rowIndex){
							if(value==2){
								return '<font color="red">'+"是"+'</font>';
							}else if(value==1){
								return "否";
							}
						}
							
						}, {
							title : '规格型号',
							field : 'modelNormsModel',
							width : 50,
							sortable:true
						}, {
							title : '备注',
							field : 'modelRemark',
							width : 80,
							sortable:true
						},{
							title : '企业编号',
							field : 'enterpriseId',
							width : 100,
							hidden:true
						}
				        ]],onDblClickRow : function(rowIndex, rowData) {
							var cId=rowData.carId;			
							loalDel(cId);
						}
				});
		});
function loalDel(cId){
	
	var d = $('<div/>').dialog({
		modal:true,
		closable :false,
		title : '查看明细信息',
		width : 570,
		height : 330,
		href : projectPath+'sell/carModel/carModelEdit.jsp',
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
	    	  $('#carBrand'). combobox({required:false});
	    	  $('#modelName'). combobox({required:false});
	    	  
	    	  	$("#carModelEditForm input").prop("disabled", true);
	  			$("#carModelEditForm select").prop("disabled", true);
	  			$("#carModelEditForm textarea").prop("disabled",true);
	  		
	    	
				var data = $('#carModel')
				.datagrid('getSelected');
				$('#carModelEditForm').form('load', data);
		
			}
	});
}
function addCarModel(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 570,
		height : 330,
		href : projectPath+'sell/carModel/carModelEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#carModelEditForm').form('validate')){
					var money1= $('#modelCostPrice').val();
					var sellMoney=$('#modelSalesPrice').val();
					var xj=$('#xj').val();
					if(xj==null||xj==''){
						$('#xj').numberbox('setValue',0);
					}
					if(parseFloat(sellMoney)<parseFloat(money1)){
						alert('销售价不能小于成本价！');	
						$('#modelSalesPrice').numberbox('setValue',money1);
						return;
					}
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/carModelAction!saveCarModel.action',
					   data: $('#carModelEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#carModel').datagrid('load');
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
		    }
		});
}
//删除
function removeCarModel(){
var data = $('#carModel').datagrid('getSelected');
var index=findSelectRowIndex('carModel',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'carModelAction!deleteCarModel.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						  $('#carModel').datagrid('clearSelections');
						  $('#carModel').datagrid('load');
						  setSelectRow('carModel',index);
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
var editCarModel = function (){
	var data = $('#carModel').datagrid('getSelected');
	if(data){
		var dd = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 570,
			height : 330,
			href : projectPath+'sell/carModel/carModelEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#carModelEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'carModelAction!updateCarModel.action',						   
						   data: $('#carModelEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#carModel').datagrid('reload');
								 dd.dialog('close');
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
	        		dd.dialog('close');
				}
	        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $('#carModel').datagrid('getSelected');
		    	$('#carModelEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryCarModel = function (){
		$('#carModel').datagrid('unselectAll');
		$('#carModel').datagrid('load', serializeObject($('#carModelQueryForm')) );
}
	function clearSearchCondition(){
		$('#carModelQueryForm').form('clear');
		$('#car_Brand_id').combobox('reload');
		$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		$('#carModel').datagrid('load', serializeObject($('#carModelQueryForm')) );
		
	}
	
	
	function _except(){
		var data =  $("#carModel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
			showEditDialog("carModel",null,"carModel_div","开始导出","导出配置",0,_callbackExcept);
			
		}

	
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"车辆型号资料"+getSystemTime());
	}


	/**
	 * 打印字段设置
	 * 编辑、选择不同分组
	 * @return
	 */
	function _config(){
		var data =  $("#carModel").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能打印！', 'warning');
			 return ;
		 }
			showEditDialog("carModel",personnelSumTable,"carModel_div","开始打印","打印配置",2,_print);
			
	
	}
	/**
	 * 打印字段设置回调函数
	 * 将选择的列打印
	 * @return
	 */
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}