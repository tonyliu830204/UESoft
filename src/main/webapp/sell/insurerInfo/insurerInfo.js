$(function(){
			$('#insurerInfo').datagrid({
					url:'${pageContext.request.contextPath}/insurerInfoAction!getPageLeva.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'insurerId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'insurerId',
							hidden:true
					    }, {
							title : '编号',
							field : 'insurerCode',
							width : 150
					    }, {
							title : '名称',
							field : 'insurerName',
							width : 100
						}, {
							title : '地址',
							field : 'insurerAddress',
							width : 100
						}, {
							title : '联系电话',
							field : 'insurerPhone',
							width : 120
						}, {
							title : '手机',
							field : 'insurerTelephone',
							width : 120
						}, {
							title : '传真',
							field : 'insurerFax',
							width : 100
						}, {
							title : '联系人',
							field : 'insurerPerson',
							width : 100
						}, {
							title : '开户银行',
							field : 'insurerBeak',
							width : 100
						}, {
							title : '银行账号',
							field : 'insurerBeaknumber',
							width : 100
						}, {
							title : '税号',
							field : 'insurerTaxnumber',
							width : 100
						}, {
							title : '开票电话',
							field : 'insurerInvoicetelephone',
							width : 120
						}, {
							title : '商业险返点',
							field : 'insurerBusinessinsurer',
							width : 100
						}, {
							title : '交强险返点',
							field : 'insurerStronginsurer',
							width : 100
						},  {
							title : '属性',
							field : 'insurerProperty',
							width : 50,
							hidden:true
						},{
							title : '备注',
							field : 'insurerRemark',
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
function addInsurerInfo(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 500,
		height : 400,
		href :  projectPath+'sell/insurerInfo/insurerInfoEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#insurerEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/insurerInfoAction!saveInsurerInfo.action',
					   data: $('#insurerEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#insurerInfo').datagrid('load');
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
		    	$('#insurerBusinessinsurer').val(0);
		    	$('#insurerStronginsurer').val(0);
		    }
		});
}
//删除
function removeInsurerInfo(){
var data = $('#insurerInfo').datagrid('getSelected');
var index=findSelectRowIndex('insurerInfo',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/insurerInfoAction!deleteInsurerInfo.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						    $('#insurerInfo').datagrid('clearSelections');
						    $('#insurerInfo').datagrid('load');
							setSelectRow('insurerInfo',index);
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
var editInsurerInfo = function (){
	var data = $('#insurerInfo').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 500,
			height : 400,
			href :  projectPath+'sell/insurerInfo/insurerInfoEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#insurerEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'insurerInfoAction!updateInsurerInfo.action',						   
						   data: $('#insurerEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#insurerInfo').datagrid('reload');
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
		    	var data = $('#insurerInfo').datagrid('getSelected');
		    	$('#insurerEditForm').form('load', data);
		    	var taxnumber=$("#insurerTaxnumber").val();
				if(taxnumber!=null && taxnumber!=''){
					var len=taxnumber.length;
					 document.getElementById("taxnumberLength").innerHTML=len;
				}
				var beaknumbe=$("#insurerBeaknumber").val();
				if(beaknumbe!=null && beaknumbe!=''){
					var len1=beaknumbe.length;
					 document.getElementById("beaknumberLength").innerHTML=len1;
				}
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryInsurerInfo = function (){
		$('#insurerInfo').datagrid('unselectAll');
		$('#insurerInfo').datagrid('load', serializeObject($('#insurerQueryForm')) );
}
	function clearSearchCondition(){
		$('#insurerQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'insurerInfoAction!getPageLeva.action',
			data:$('#insurerQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#insurerInfo').datagrid('load',r);
			}
	    });
		
	}
	
function _except(){
		var data =  $("#insurerInfo").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("insurerInfo",null,"insurerInfo_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"保险公司档案"+getSystemTime());
}


function _config(){
	var data =  $("#insurerInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("insurerInfo",personnelSumTable,"insurerInfo_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}