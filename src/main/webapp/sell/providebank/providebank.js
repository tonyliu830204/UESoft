$(function(){
			$('#providebank').datagrid({
					url:'${pageContext.request.contextPath}/providebankAction!getPageModel.action',
					fit:true,
					pagination : true,
					sortOrder:'asc',
				    sortName:'providebankId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [{
							title : '编号',
							field : 'providebankId',
							hidden:true
					    }, {
							title : '编号',
							field : 'providebankCode',
							width : 100
					    }, {
							title : '名称',
							field : 'providebankName',
							width : 100
						}, {
							title : '地址',
							field : 'providebankAddr',
							width : 100
						}, {
							title : '电话一',
							field : 'providebankPhone',
							width : 100
						}, {
							title : '手机',
							field : 'providebankTelephone',
							width : 100
						}, {
							title : '传真',
							field : 'providebankFax',
							width : 100
						}, {
							title : '联系人',
							field : 'providebankPerson',
							width : 50
						}, {
							title : '开户银行',
							field : 'providebankBank',
							width : 100
						}, {
							title : '银行账号',
							field : 'providebankNumber',
							width : 100
						}, {
							title : '税号',
							field : 'providebankTaxnumber',
							width : 100
						}, {
							title : '开票电话',
							field : 'providebankInvtelephone',
							width : 100
						}, {
							title : '贷款利率',
							field : 'providebankLoanrate',
							width : 100
						}, {
							title : '贷款年限',
							field : 'providebankLimit',
							width : 100
						}, {
							title : '备注',
							field : 'providebankRemark',
							width : 100
						},{
							title : '企业编号',
							field : 'enterprise_id',
							width : 100,
							hidden:true
						}
				        ]]
				});
		});
function addProvidebank(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 490,
		height : 390,
		href : projectPath+'sell/providebank/providebankEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#providebankEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/providebankAction!saveProvidebank.action',
					   data: $('#providebankEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#providebank').datagrid('load');
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
function removeProvidebank(){
var data = $('#providebank').datagrid('getSelected');
var index=findSelectRowIndex('providebank',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/providebankAction!deleteProvidebank.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						  $('#providebank').datagrid('clearSelections');
						  $('#providebank').datagrid('load');
							setSelectRow('providebank',index);
						
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
var editProvidebank = function (){
	var data = $('#providebank').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 490,
			height : 390,
			href : projectPath+'sell/providebank/providebankEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#providebankEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'providebankAction!updateProvidebank.action',						   
						   data: $('#providebankEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#providebank').datagrid('reload');
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
		    	var data = $('#providebank').datagrid('getSelected');
		    	$('#providebankEditForm').form('load', data);
		    	var taxnumber=$("#providebankTaxnumber").val();
				if(taxnumber!=null && taxnumber!=''){
					var len=taxnumber.length;
					 document.getElementById("taxnumberLength").innerHTML=len;
				}
				var bankNumber=$("#providebankNumber").val();
				if(bankNumber!=null && bankNumber!=''){
					var len1=bankNumber.length;
					 document.getElementById("providebankLength").innerHTML=len1;
				}
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryProvidebank = function (){
		$('#providebank').datagrid('unselectAll');
		$('#providebank').datagrid('load', serializeObject($('#providebankQueryForm')) );
}
	function clearSearchCondition(){
		$('#providebankQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'providebankAction!getPageModel.action',
			data:$('#providebankQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#providebank').datagrid('load',r);
			}
	    });
		
	}
	
	function _except(){
		var data =  $("#providebank").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("providebank",null,"providebank_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"贷款银行档案"+getSystemTime());
}


function _config(){
	var data =  $("#providebank").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("providebank",personnelSumTable,"providebank_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}	  
