$(function(){
	
	//初试时间
 	$('#queryStartDate').val(getStartDate($('#queryStartDate')));
	$('#queryStartDate2').val(getSystemTime());
	
			$('#sellReceipt').datagrid({
					url:'${pageContext.request.contextPath}/sellReceiptAction!getPage.action',
					fit:true,
					pagination : true,
					fitColumns : true,
					sortOrder:'asc',
				    sortName:'receiptId',
					singleSelect : true,
					pageSize : 10,
					pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
					rownumbers : true,
					loadMsg : "数据加载中，请稍后……",
					columns : [ [ {
							title : '编号',
							field : 'receiptId',
							hidden:true
						},{
							title : '票据编号',
							field : 'receiptCode',
							width : 150
					    }, {
							title : '出票银行',
							field : 'receiptBank',
							hidden:true
						}, {
							title : '出票银行',
							field : 'bankName',
							width : 150
						}, {
							title : '金额',
							field : 'receiptMoney',
							width : 100
						}, {
							title : '出票日期',
							field : 'receiptStartDate',
							width : 100
						}, {
							title : '到期日期',
							field : 'receiptEndDate',
							width : 100
						}, {
							title : '备注',
							field : 'remark',
							width : 100
						}
				        ]]
				});
		});
function addReceipt(){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '新增',
		width : 450,
		height : 300,
		href : projectPath+'sell/sellReceipt/sellReceiptEdit.jsp',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#receiptEditForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url:'${pageContext.request.contextPath}/sellReceiptAction!saveReceipt.action',
					   data: $('#receiptEditForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $('#sellReceipt').datagrid('load');
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
		 		   $('#receiptStartDate').val(getSystemTime());
		 		  var now= new Date();
		 		  $('#receiptEndDate').val(new Date(now.setMonth(now.getMonth()+4)).format("yyyy-MM-dd"));
		    }
		});
}
//删除
function removeReceipt(){
var data = $('#sellReceipt').datagrid('getSelected');
var index=findSelectRowIndex('sellReceipt',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'sellReceiptAction!deleteReceipt.action',
				   data: data,
				   success: function(r){
					 if(r.success){
						    $('#sellReceipt').datagrid('clearSelections');
						    $('#sellReceipt').datagrid('load');
							setSelectRow('sellReceipt',index);
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
var editReceipt = function (){
	var data = $('#sellReceipt').datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '修改',
			width : 450,
			height : 300,
			href : projectPath+'sell/sellReceipt/sellReceiptEdit.jsp',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#receiptEditForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'sellReceiptAction!updateReceipt.action',						   
						   data: $('#receiptEditForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $('#sellReceipt').datagrid('reload');
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
		    	var data = $('#sellReceipt').datagrid('getSelected');
		    	$('#receiptEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
	/**
	 * 查询
	 * @return
	 */
	var queryReceipt = function (){
		$('#sellReceipt').datagrid('unselectAll');
		$('#sellReceipt').datagrid('load', serializeObject($('#receiptQueryForm')));
 		addInitDate($('#queryStartDate'),$('#queryStartDate2'));

	}
	/**
	 * 清空
	 * @return
	 */
	function clearSearchCondition(){
		$('#receiptQueryForm').form('clear');
		queryReceipt();
			
	}
	function customArchivesKeyUp(id1,id2){
	var dataLength=document.getElementById(id1).value.length;
	document.getElementById(id2).innerHTML=dataLength;
	}
	function pickedFunc(){
	var date=new Date($('#receiptStartDate').val());
	var endDate=new Date(date.setMonth(date.getMonth()+4)).format("yyyy-MM-dd");
	 $('#receiptEndDate').val(endDate);
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
		exportEsuyUIExcelFile(parentId,fieldNames,"承兑汇票管理"+getSystemTime());
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
	//导入
	function doinport(dateGridId,parentId){
		showEditDialog(dateGridId,null,parentId,"开始导入","导入配置",3,null);
	}

