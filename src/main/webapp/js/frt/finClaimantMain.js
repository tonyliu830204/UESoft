var staticFinClaimantMainDisabled=false;//判断表单组件禁用或启用
var staticReceptionId="";
var staticFinClaimantMainParts=null;
var staticFinClaimantMainItems=null;
var staticFinClaimantMainExpenseSituationOtherExpense=null;

var _save = function() {
	if(staticFinClaimantMainParts!=null){
		staticFinClaimantMainParts = prosceniumUpdate('finClaimantMainPartsDatagrid',staticFinClaimantMainParts);
	}
	if(staticFinClaimantMainItems!=null){
		staticFinClaimantMainItems = prosceniumUpdate('finClaimantMainItemDatagrid',staticFinClaimantMainItems);
	}
	if(staticFinClaimantMainExpenseSituationOtherExpense!=null){
		staticFinClaimantMainExpenseSituationOtherExpense = prosceniumUpdate('finClaimantMainExpenseSituationOtherExpense',staticFinClaimantMainExpenseSituationOtherExpense);
	}
	staticReceptionId="";
	var data = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
	var url = 'finClaimantMainAction!';
		if(data){
			url += 'edit.action';
		}else{
			url += 'save.action';
		}
		var others=null;
		if(staticFinClaimantMainExpenseSituationOtherExpense==null){
			others="";
		}else{
			others=JSON.stringify(staticFinClaimantMainExpenseSituationOtherExpense);
		}
		var items=null;
		if(staticFinClaimantMainItems==null){
			items="";
		}else{
			items=JSON.stringify(staticFinClaimantMainItems);
		}
		var parts=null;
		if(staticFinClaimantMainParts==null){
			parts="";
		}else{
			parts=JSON.stringify(staticFinClaimantMainParts);
		}
		var preclrId=$('#finClaimantMain_detail_preclrId').val()
		var  receptionId=$('#finClaimantMain_detail_receptionId').val();
		var  claimantmId=$('#finClaimantMain_detail_claimantmId').val();
		if(preclrId==null||preclrId.length==0||receptionId==null||receptionId.length==0){
			alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			return;
		}
		if(data){
			if(claimantmId==null||claimantmId.length==0){
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
				return;
			}
		}
		if($('#finClaimantMainAddForm').form('validate')){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : url,
				data : $('#finClaimantMainAddForm').serialize()+'&others='+others+ '&parts='+parts+ '&items='+items,
				success : function(r) {
					if (r.success) {
						alertMsg(r);
						_cancel(false);
						$('#finClaimantMainSummaryDatagrid').datagrid('reload');
						if(data){
							var rowIndex=$('#finClaimantMainSummaryDatagrid').datagrid('getRowIndex',data);
							$('#finClaimantMainSummaryDatagrid').datagrid({
								onLoadSuccess:function(data){
									$('#finClaimantMainSummaryDatagrid').datagrid('selectRow',rowIndex);									
								}
							});
						}else{
							$('#finClaimantMainUnfinishedDatagrid').datagrid('reload');
						}
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
		}else{
			alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
		}
}

var _remove = function (){
	var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
	var index=findSelectRowIndex('finClaimantMainSummaryDatagrid',rowData);
	if(rowData){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType : 'json',
				   url: 'finClaimantMainAction!delete.action',
				   data: rowData,
				   success: function(r){
				      if(r.success){
				      	alertMsg(r);
				    	$('#finClaimantMainSummaryDatagrid').datagrid('load');
						$('#finClaimantMainUnfinishedDatagrid').datagrid('load',serializeObject($('#unfinishedForm')));
						setSelectRow('finClaimantMainSummaryDatagrid',index);
				      }else{
				      	alertMsg(r);
				      }
				   }
				});
			}
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
//索赔确认
function claimsValidate(flag){
	var data =$('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
	if(data){
		var url='finClaimantMainAction!modifyClaimsValidate.action?flag='+flag;
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			success : function callback(r) {
				if (r.success) {
					alertMsg(r);
					_cancel(true);
					$('#finClaimantMainSummaryDatagrid').datagrid('reload');
				}else{
					alertMsg(r);
				}
			}
		});
	}else{
		alertMsg('对不起，请先选中要转换的记录！', 'warning');
	}
}
//启用控件与校验
function requiredAsFormDiy(){
	$("#finClaimantMainAddForm input[id=finClaimantMain_detail_preclrId]").prop("disabled",false);
	$("#finClaimantMainAddForm input[id=finClaimantMain_detail_claimantmInvoiceNo]").prop("disabled",false);
	$("#finClaimantMainAddForm input[id=finClaimantMain_detail_receptionId]").prop("disabled",false);
	$("#finClaimantMainAddForm input[id=finClaimantMain_detail_claimantmId]").prop("disabled",false);
	$("#finClaimantMainAddForm input[id=finClaimantMain_detail_claimantmStatus]").prop("disabled",false);
	
	$("#finClaimantMain_detail_claimantmInvoiceType").combobox({required:true,disabled:false});
	$("#finClaimantMain_detail_claimantmClrStfId").combobox({required:true,disabled:true});
	$("#finClaimantMain_detail_claimantmCheckStfId").combobox({required:false,disabled:true});
	
	$("#finClaimantMain_detail_claimantmTime").datetimebox({required:true,disabled:true});
	$("#finClaimantMain_detail_claimantmInvoiceTime").datetimebox({required:true,disabled:false});
	$("#addExpDelCarTime").datetimebox({required:true,disabled:false});
	
	$("#frtReception_details_receptionRepPer").validatebox({required:false});
	$("#frtReception_details_propRepPer").validatebox({required:false});
	$("#frtReception_details_propPhone").validatebox({required:false});
	$("#frtReception_details_propTel").validatebox({required:false});
	
	//$("#frtReception_details_receptionDistance").numberbox({required:true,disabled:false});
	//$("#frtReception_details_receptionDistance").numberbox('validate');
	$("#finClaimantMainAddForm input.easyui-numberbox").numberbox({required:true,disabled:true});
	$("#finClaimantMainAddForm input.easyui-numberbox").numberbox('setValue','0');
	$("#finClaimantMainAddForm input.easyui-numberbox").numberbox("validate");
	
	$("#finClaimantMainAddForm textarea").prop("disabled",false);
	/*
	$("#frtReception_details_receptionRepPer").validatebox('validate');
	$("#frtReception_details_propRepPer").validatebox('validate');
	$("#frtReception_details_propPhone").validatebox('validate');
	$("#frtReception_details_propTel").validatebox('validate');
	*/
}
function _except(){
	showEditDialog("finClaimantMainSummaryDatagrid",null,"finClaimantMainSummaryCenter","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"索理赔结算单"+getSystemTime());
}

var add = function() {
	$('#finClaimantMainSummaryDatagrid').datagrid('unselectAll');
	staticFinClaimantMainDisabled=true;
	staticReceptionId="";
	$('#finClaimantMainAddForm').form('clear');
	$('#finClaimantMain_detail_claimantmTime').datetimebox('setValue','{now}');
	$('#finClaimantMain_detail_claimantmInvoiceTime').datetimebox('setValue','{now}');
	 $('#finClaimantMain_detail_claimantmClrStfId').combobox('select','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
	$('#finClaimantMainTabs').tabs('select', '索理赔帐单明细');
	addButton();
	$('#finClaimantMainPartsDatagrid').datagrid({
		url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId=-1',
		onLoadSuccess : function (data){
			$('#finClaimantMain_parts_add').linkbutton('enable');
			$('#finClaimantMain_parts_remove').linkbutton('enable');
		}
	});
	$('#finClaimantMainItemDatagrid').datagrid({
		url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=-1',
		onLoadSuccess : function (data){
			$('#finClaimantMain_item_add').linkbutton('enable');
			$('#finClaimantMain_item_remove').linkbutton('enable');
			$('#finClaimantMain_item_diy').linkbutton('enable');
		}
	});
	$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
		url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=-1',
		onLoadSuccess : function (data){
			$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('enable');
			$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('enable');
		}
	});
}
var addButton = function() {
	if ($('#save').length == 0 && $('#cancel').length == 0) {
		staticFinClaimantMainParts=null;
		staticFinClaimantMainItems=null;
		staticFinClaimantMainExpenseSituationOtherExpense=null;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel(true);">取消</a>';
		$('#button').append(save).append(cancel);
		$.parser.parse('#button');
		$('#add').linkbutton('disable');
		$('#remove').linkbutton('disable');
		$('#edit').linkbutton('disable');
		$('#search').linkbutton('disable');
		$('#clear').linkbutton('disable');
		$('#redo').linkbutton('disable');
		$('#print').linkbutton('disable');
		$('#set').linkbutton('disable');
		$('#export').linkbutton('disable');
		$('#ok1').linkbutton('disable');
		$('#ok').linkbutton('disable');
		 //requiredAsForm(true,'finClaimantMainAddForm');
		requiredAsFormDiy();
	}
	if($('#preClearingButton').length == 0){
		var preClearingButton = '<a id="preClearingButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" onclick="unconfirmed();"></a>';
		$('#button2').append(preClearingButton);
		$.parser.parse('#button2');
	}
}
function query(){
	//$('#finClaimantMainSummaryDatagrid').datagrid('unselectAll');
	if($('#finClaimantMainQueryForm').form('validate')){
		$('#finClaimantMainSummaryDatagrid').datagrid('load', serializeObject($('#finClaimantMainQueryForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
};
var _cancel = function(tag) {
	if(tag!=null&&tag==true){
		$('#finClaimantMainAddForm').form('clear');
		$('#finClaimantMain_detail_claimantmTime').datetimebox('setValue','{now}');
		$('#finClaimantMain_detail_claimantmInvoiceTime').datetimebox('setValue','{now}');
		$('#finClaimantMain_detail_claimantmClrStfId').combobox('select','<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>');
		 requiredAsForm(false,'finClaimantMainAddForm');
		 
		$('#finClaimantMainExpenseSituationForm').form('load',{
			preclrWktimeAmount:'',
			preMprMatAmount:'',
			preclrManagementFee:'',
			otherAmount:'',
			amountTotal:''
		});
		$('#finClaimantMainPartsDatagrid').datagrid({
			url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId=-1',
			onLoadSuccess : function (data){
				$('#finClaimantMain_parts_add').linkbutton('disable');
				$('#finClaimantMain_parts_remove').linkbutton('disable');
			}
		});
		$('#finClaimantMainItemDatagrid').datagrid({
			url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=-1',
			onLoadSuccess : function (data){
				$('#finClaimantMain_item_add').linkbutton('disable');
				$('#finClaimantMain_item_remove').linkbutton('disable');
				$('#finClaimantMain_item_diy').linkbutton('disable');
			}
		});
		$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
			url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=-1',
			onLoadSuccess : function (data){
				$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('disable');
				$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('disable');
			}
		});
		$('#finClaimantMainTabs').tabs('select', '索理赔账单汇总');	
	}else{
		$('#finClaimantMain_parts_add').linkbutton('disable');
		$('#finClaimantMain_parts_remove').linkbutton('disable');
		$('#finClaimantMain_item_add').linkbutton('disable');
		$('#finClaimantMain_item_remove').linkbutton('disable');
		$('#finClaimantMain_item_diy').linkbutton('disable');
		$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('disable');
		$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('disable');
	}
	$('#button').empty();
	$('#add').linkbutton('enable');
	$('#remove').linkbutton('enable');
	$('#edit').linkbutton('enable');
	$('#search').linkbutton('enable');
	$('#clear').linkbutton('enable');
	$('#redo').linkbutton('enable');
	$('#print').linkbutton('enable');
	$('#set').linkbutton('enable');
	$('#export').linkbutton('enable');
	$('#ok').linkbutton('enable');
	$('#ok1').linkbutton('enable');
}
var _edit = function (){
	staticReceptionId="";
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	var data = $finClaimantMainSummaryDatagrid.datagrid('getSelected');
	if(data){
		if(data.claimantmStatus=='<%=Contstants.AUDIT_TAG.AUDITYESS %>'){
			alertMsg('对不起，此信息已审核，不能修改！', 'warning');
			return;
		}
		staticFinClaimantMainDisabled=true;
		$('#finClaimantMainTabs').tabs('select', '索理赔帐单明细');
		addButton();
		$('#finClaimantMainAddForm').form('load', data);
		$('#finClaimantMainExpenseSituationForm').form('load',data);
		$('#finClaimantMainPartsDatagrid').datagrid({
			url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId=' + data.claimantmId
		});
		$('#finClaimantMainItemDatagrid').datagrid({
			url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=' + data.claimantmId
		});
		$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
			url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=' + data.claimantmId
		});
		$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
			onLoadSuccess : function (data){
			staticFinClaimantMainExpenseSituationOtherExpense=data;
				$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('enable');
				$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('enable');
			}
		});
		$('#finClaimantMainPartsDatagrid').datagrid({
			onLoadSuccess : function (data){
			staticFinClaimantMainParts=data;
				$('#finClaimantMain_parts_add').linkbutton('enable');
				$('#finClaimantMain_parts_remove').linkbutton('enable');
			}
		});
		$('#finClaimantMainItemDatagrid').datagrid({
			onLoadSuccess : function (data){
				staticFinClaimantMainItems=data;
				$('#finClaimantMain_item_add').linkbutton('enable');
				$('#finClaimantMain_item_remove').linkbutton('enable');
				$('#finClaimantMain_item_diy').linkbutton('enable');
			}
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
//转收银
	var settlement = function (){
		var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'finClaimantMainAction!isClaimsValidate.action',
			   data: rowData,
			   success: function(r){
			   		if(r.success){
			   			if(rowData.claimantmAmount>0 && $('#save').length == 0 && $('#cancel').length == 0){
							$.messager.confirm('系统提示', '您确定要进行转收银操作?', function(r){
								if (r){
									if(rowData.claimantmToMoney==TOMONEYNO){
										$.ajax({
										   type: 'post',
										   dataType: 'json',
										   url: 'finClaimantMainAction!transformMoney.action',
										   data: rowData,
										   success: function(r){
										     	alertMsg(r);
										     	_cancel(true);
										     	$('#finClaimantMainSummaryDatagrid').datagrid('reload');
										   }
										});
									}
								}
							});
						}else{
							alertMsg('不能转收银，索赔金额必须大于0！', 'warning');
						}		
			   		}else{
			   			alertMsg('不能转收银，请审核后再操作！', 'warning');
			   		}
			   }
			});
		}else{
			alertMsg('对不起，请先选中要操作的记录！', 'warning');
		}
	}
	function _clear(){
		$('#finClaimantMainQueryForm').form('clear');
		$('#finClaimantMainSummaryDatagrid').datagrid('load', serializeObject($('#finClaimantMainQueryForm')));
				
	}