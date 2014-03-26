$(function(){
			$('#supplierInfo').datagrid({
					url:'${pageContext.request.contextPath}/supplierInfoAction!getPageModel.action',
					fit:true,
					pagination : true,
					sortOrder:'asc',
				    sortName:'supplierId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [  {
							title : 'id',
							field : 'supplierId',
							hidden:true
					    },{
							title : '编号',
							field : 'supplierNumber',
							width : 120
					    }, {
							title : '名称',
							field : 'supplierName',
							width : 100
						}, {
							title : '地址',
							field : 'supplierAddress',
							width : 100
						}, {
							title : '电话',
							field : 'supplierTelephone',
							width : 100
						}, {
							title : '手机',
							field : 'supplierPhone',
							width : 100
						}, {
							title : '传真',
							field : 'supplierFax',
							width : 100
						}, {
							title : '联系人',
							field : 'supplierPerson',
							width : 100
						}, {
							title : '开户银行',
							field : 'supplierBank',
							width : 150,
							hidden:true
						}, {
							title : '开户银行',
							field : 'supplierBankName',
							width : 150
						}, {
							title : '银行账号',
							field : 'supplierBankCode',
							width : 100
						}, {
							title : '税号',
							field : 'supplierRevenue',
							width : 100
						}, {
							title : '开票电话',
							field : 'supplierMakeInvphone',
							width : 100
						}, {
							title : '允许挂账',
							field : 'supplierBuyerCredit',
							width : 100,
							hidden:true
						}, {
							title : '账面金额',
							field : 'supplierMoney',
							width : 100
						}, {
							title : '属性',
							field : 'supplierNature',
							width : 100,
							formatter : function (value,row,index){
							 return row.supplierNatureName;
							}
						}, {
							title : '助记码',
							field : 'supplierCode',
							width : 50
						}, {
							title : '备注',
							field : 'supplierRemark',
							width : 150
						},{
							title : '企业编号',
							field : 'enterprise_id',
							width : 100,
							hidden:true
						}
				        ]]
				});
		});
function addSupplierInfo(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 530,
		height : 440,
		href : projectPath+'sell/supplierInfo/supplierInfoEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#supplierInfoEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/supplierInfoAction!saveSupplierInfo.action',
					   data: $('#supplierInfoEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#supplierInfo').datagrid('load');
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
function removeSupplierInfo(){
var data = $('#supplierInfo').datagrid('getSelected');
var index=findSelectRowIndex('supplierInfo',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/supplierInfoAction!deleteSupplierInfo.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						  $('#supplierInfo').datagrid('clearSelections');
						  $('#supplierInfo').datagrid('load');
						  setSelectRow('supplierInfo',index);
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
var editSupplierInfo = function (){
	var data = $('#supplierInfo').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 530,
			height : 440,
			href : projectPath+'sell/supplierInfo/supplierInfoEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#supplierInfoEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'supplierInfoAction!updateSupplierInfo.action',						   
						   data: $('#supplierInfoEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#supplierInfo').datagrid('reload');
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
		    	var data = $('#supplierInfo').datagrid('getSelected');
		    	$('#supplierInfoEditForm').form('load', data);
		    	var revenue=$("#supplierRevenue").val();
				if(revenue!=null && revenue!=''){
					var len=revenue.length;
					 document.getElementById("revenueLength").innerHTML=len;
				}
				var bankCode=$("#supplierBankCode").val();
				if(bankCode!=null && bankCode!=''){
					var len=bankCode.length;
					 document.getElementById("bankCodeLength").innerHTML=len;
				}
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var querySupplierInfo = function (){
		$('#supplierInfo').datagrid('unselectAll');
		$('#supplierInfo').datagrid('load', serializeObject($('#supplierInfoQueryForm')) );
}
	function clearSearchCondition(){
		$('#supplierInfoQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'supplierInfoAction!getPageModel.action',
			data:$('#supplierInfoQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#supplierInfo').datagrid('load',r);
			}
	    });
		
	}
	
function _except(){
		var data =  $("#supplierInfo").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("supplierInfo",null,"supplierInfo_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"供应商档案"+getSystemTime());
}


function _config(){
	var data =  $("#supplierInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("supplierInfo",personnelSumTable,"supplierInfo_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}	  
