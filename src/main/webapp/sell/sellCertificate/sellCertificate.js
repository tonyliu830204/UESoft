$(function(){
	
	/*//初试时间
 	$('#instorehouseDate').val(getStartDate($('#instorehouseDate')));
	$('#instorehouseDate2').val(getSystemTime());*/
			$('#sellCertificate').datagrid({
					url:'${pageContext.request.contextPath}/sellCertificateAction!getPage.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'certificateId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [{
						title : '车辆编号',
						field : 'carCode',
						width : 110,
						sortable : true,
						
					},{
						title : '车辆品牌编号',
						field : 'carBrand',
						hidden:true
					},{
						title : '车辆品牌',
						field : 'carBrandName',
						width : 80
					},{
						title : '车辆型号编号',
						field : 'carModelId',
						hidden:true
					},{
						title : '车辆型号',
						field : 'carModelName',
						width : 100
					},{
						title : '颜色编号',
						field : 'carColor',
						hidden:true
					},{
						title : '颜色',
						field : 'colorName',
						width : 80
					},{
						title : 'VIN号',
						field : 'carVinNumber',
						width : 130
					},{
						title : '销售状态',
						field : 'sellState',
						width : 90,
						hidden:true
					},{
						title : '销售状态',
						field : 'sellStateName',
						width : 90
					}, {
						title : '编号',
						field : 'certificateId',
						width : 50,
						hidden:true
					},{
						title : '车辆档案信息编号',
						field : 'xsCarId',
						hidden:true
				    }, {
						title : '承兑汇票编号',
						field : 'receiptId',
						hidden:true
					}, {
						title : '合格证',
						field : 'xsCarCertificate',
						width : 100
					}, {
						title : '合格证状态编号',
						field : 'xsCarCertificateState',
						hidden:true
					}, {
						title : '合格证状态',
						field : 'certificateStateName',
						width : 100
					}, {
						title : '赎回日期',
						field : 'redemptionDate',
						width : 100
					}, {
						title : '领证人',
						field : 'certificatePerson',
						width : 100
					}, {
						title : '领证日期',
						field : 'certificateDate',
						width : 100
					}, {
						title : '备注',
						field : 'remark',
						width : 100
					}, {
						title : '开票银行',
						field : 'receiptBank',
						hidden:true
					}, {
						title : '开票银行',
						field : 'bankName',
						width : 100
					}
					, {
						title : '开票日期',
						field : 'receiptStartDate',
						width : 100
					}
					, {
						title : '到期日期',
						field : 'receiptEndDate',
						width : 100
					}
					
			        ]]
				});
			
		});
//删除
function removeCertificate(){
var data = $('#sellCertificate').datagrid('getSelected');
var index=findSelectRowIndex('sellCertificate',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellCertificateAction!deleteCertificate.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						   $('#sellCertificate').datagrid('clearSelections');
						   $('#sellCertificate').datagrid('load');
						   setSelectRow('sellCertificate',index);
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
var editCertificate = function (){
	var data = $('#sellCertificate').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 550,
			height : 380,
			href : projectPath+'sell/sellCertificate/sellCertificateEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#certificateEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellCertificateAction!updateCertificate.action',						   
						   data: $('#certificateEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#sellCertificate').datagrid('reload');
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
		    	var data = $('#sellCertificate').datagrid('getSelected');
		    	
		    	$('#certificateEditForm').form('load', data);
		    	$('#redemptionDate').val(getSystemTime());
		    }
		});
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}
function certificate_(){
	var data = $('#sellCertificate').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '领证',
			width : 400,
			height : 260,
			href : projectPath+'sell/sellCertificate/getCertificateEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#certificateForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellCertificateAction!modifyCertificate.action',						   
						   data: $('#certificateForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#sellCertificate').datagrid('reload');
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
		    	var data = $('#sellCertificate').datagrid('getSelected');
		    	$('#certificateForm').form('load', data);
		    	$('#certificateDate').val(getSystemTime());
		    }
		});
	}else{
		alertMsg('对不起，请先选中要操作 的记录！', 'warning');
	}
}
	//查询
	var queryCertificate = function (){
		$('#sellCertificate').datagrid('unselectAll');
		$('#sellCertificate').datagrid('load', serializeObject($('#certificateQueryForm')));
		
	 	
	}
	//清空
	function clearfrom(){
	$('#certificateQueryForm').form('clear');
		$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'baseTagAction!getDataByChildDataKey.action',
			   data: 'pdataKey='+pkey+'&dataKey='+ckey,
			   success: function(r){
			   		$('#sellState').combobox('select',r);
			   		$('#sellCertificate').datagrid('load', serializeObject($('#certificateQueryForm')));
			   },
			   error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   }
			});
		$('#car_Brand_id').combobox('reload');
		$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		
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
	exportEsuyUIExcelFile(parentId,fieldNames,"合格证管理"+getSystemTime());
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


