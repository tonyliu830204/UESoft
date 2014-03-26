$(function(){
			$('#distributorInfo').datagrid({
					url:'${pageContext.request.contextPath}/distributorAction!getPageModel.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'distributorId',
					singleSelect : true,
					pageSize : 20,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'distributorId',
							hidden:true	
					    },{
							title : '编码',
							field : 'distributorCode',
							width : 150
					    }, {
							title : '分销商名称',
							field : 'distributorName',
							width : 100
						}, {
							title : '简称',
							field : 'distributorMark',
							width : 50
						}, {
							title : '地址',
							field : 'distributorAddr',
							width : 100
						}, {
							title : '固定电话',
							field : 'distributorTelephone',
							width : 100
						}, {
							title : '手机',
							field : 'distributorPhone',
							width : 100
						}, {
							title : '联系人',
							field : 'distributorPerson',
							width : 50
						}, {
							title : '开户银行',
							field : 'distributorBeak',
							width : 100
						}, {
							title : '银行账号',
							field : 'distributorBeaknumber',
							width : 100
						}, {
							title : '税号',
							field : 'distributorTaxnumber',
							width : 100
						}, {
							title : '开票电话',
							field : 'distributorInvoicetelephone',
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
function addDistributorInfo(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 490,
		height : 390,
		href : projectPath+'sell/DistributorInfo/DistributorInfoEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#distributorInfoEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/distributorAction!saveDistributorInfo.action',
					   data: $('#distributorInfoEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#distributorInfo').datagrid('load');
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
function removeDistributorInfo(){
var data = $('#distributorInfo').datagrid('getSelected');
var index=findSelectRowIndex('distributorInfo',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'${pageContext.request.contextPath}/distributorAction!deleteDistributorInfo.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						   $('#distributorInfo').datagrid('clearSelections');
							$('#distributorInfo').datagrid('load');
							setSelectRow('distributorInfo',index);
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
var editDistributorInfo = function (){
	var data = $('#distributorInfo').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 490,
			height : 390,
			href : projectPath+'sell/DistributorInfo/DistributorInfoEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#distributorInfoEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'distributorAction!updateDistributorInfo.action',						   
						   data: $('#distributorInfoEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#distributorInfo').datagrid('reload');
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
		    	var data = $('#distributorInfo').datagrid('getSelected');
		    	$('#distributorInfoEditForm').form('load', data);
		    	var taxnumber=$("#distributorTaxnumber").val();
				if(taxnumber!=null && taxnumber!=''){
					var len=taxnumber.length;
					 document.getElementById("taxnumberLength").innerHTML=len;
				}
				var beaknumber=$("#distributorBeaknumber").val();
				if(beaknumber!=null && beaknumber!=''){
					var len1=beaknumber.length;
					 document.getElementById("distributorBeakLength").innerHTML=len1;
				}
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	var queryDistributorInfo = function (){
		$('#distributorInfo').datagrid('unselectAll');
		$('#distributorInfo').datagrid('load', serializeObject($('#distributorInfoQueryForm')) );
}
	function clearSearchCondition(){
		$('#distributorInfoQueryForm').form('clear');
		$.ajax({                                           
			type : 'POST',
			url : 'distributorAction!getPageModel.action',
			data:$('#distributorInfoQueryForm').serialize(),
			dataType : 'json',
			success : function(r){
				$('#distributorInfo').datagrid('load',r);
			}
	    });
		
	}
	
function _except(){
		var data =  $("#distributorInfo").datagrid('getData'); 
		 if(data.rows.length==0){
			 alertMsg('数据列表为空，不能导出！', 'warning');
			 return ;
		 }
		showEditDialog("distributorInfo",null,"distributorInfo_div","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"分销商档案"+getSystemTime());
}


function _config(){
	var data =  $("#distributorInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能打印！', 'warning');
		 return ;
	 }
		showEditDialog("distributorInfo",personnelSumTable,"distributorInfo_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}	  
