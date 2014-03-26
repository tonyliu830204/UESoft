var staticDisabled=false;//判断表单组件禁用或启用
var staticFrtPreClearingParts=null;
var addButton = function() {
	if ($('#save').length == 0 && $('#cancel').length == 0) {
		staticFrtPreClearingParts=null;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel(true);">取消</a>';
		$('#button').append(save).append(cancel);
		$.parser.parse('#button');
		$('#remove').linkbutton('disable');
		$('#edit').linkbutton('disable');
		$('#search').linkbutton('disable');
		$('#clear').linkbutton('disable');
		$('#redo').linkbutton('disable');
		$('#redo2').linkbutton('disable');
		$('#redo3').linkbutton('disable');
		$('#print').linkbutton('disable');
		$('#set').linkbutton('disable');
		$('#export').linkbutton('disable');
		$('#back').linkbutton('disable');
		requiredAsFormDiy(false);
		staticDisabled=true;
	}
}

//删除
var _remove = function (){
	$.ajax({
		type : 'post',
		url : 'frtPreClearingAction!datagridFrtPreClearing.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			   $.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(t){
					if (t){
						$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'frtPreClearingAction!isExist.action',
							   data: r.rows[0],
							   success: function(m){
							   		if(m.success){
							   			$.ajax({
					 						   type: 'post',
					 						   dataType : 'json',
					 						   url: 'frtPreClearingAction!remove.action',//销售,维修工单结算删除
					 						   data: r.rows[0],
					 						   success: function(k){
					 						      if(k.success){
					 						      	  alertMsg(k);
					 						      	  _cancel(true);
					 						      	  window.opener.win_close();
					 						      }else
					 						      	  alertMsg(k);
					 						    }
				 						 });
							   		}else{
							   			alertMsg(m);
							   		}
							   }
							});
		           }
				});
		   }else
			   alertMsg('对不起，请先选中要删除的记录！', 'warning');
	    }
	});
}

var  preclearOpretion=false;
//交车结算修改
var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	preclearOpretion=true;
	$.ajax({
		type : 'post',
		url : 'frtPreClearingAction!datagridFrtPreClearing.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			   $('#frtPreClearingTabs').tabs('select', '结算单明细');
				addButton();
				$('#frtPreClearingAddForm').form('load', r.rows[0]);
				$('#frtPreClearingExpenseSituationForm').form('load', r.rows[0]);
				$('#claimMoneyForm').form('load', r.rows[0]);
				
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					url: 'frtPreClearingAction!findPreCostById.action?preclrId=' + r.rows[0].preclrId
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					url : 'frtPreClearingAction!findPrePartsById.action?preclrId=' + r.rows[0].preclrId
				});
				
				$('#frtPreClearingItemDatagrid').datagrid({
					url : 'frtPreClearingAction!findPreItemById.action?preclrId=' + r.rows[0].preclrId
				});
				$('#frtPreClearingExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+r.rows[0].receptionId
				});
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					onLoadSuccess : function (data){
					}
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtPreClearingParts=data;
						$('#frtPreClearing_parts_accept').linkbutton('enable');					
					}
				});
				$('#frtPreClearingItemDatagrid').datagrid({
					onLoadSuccess : function (data){
					}
				});
				requiredAsFormDiy(true);
				frtPreClearingAddFormKeyUp();
				$('#frtWorkOrderItemDatagrid').treegrid({
					url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+r.rows[0].carId
				});
		   }else{
			   alertMsg('对不起，请先选中要修改的记录！', 'warning');
		   }
	    }
	});
}
var _cancel = function(tag) {
	if(tag!=null&&tag==true){
		$('#claimMoneyForm').form('clear');
		$('#frtPreClearingAddForm').form('clear');
		$('#addPreclrTime').datetimebox('setValue','{now}');
		$('#frtPreClearing_detail_preclrInvoiceTime').datetimebox('setValue','{now}');		

		 requiredAsForm(false,'frtPreClearingAddForm');
		 $("#frtPreClearing_claimMoney_finComId").combobox({required:false,disabled:true});
		$('#frtPreClearingExpenseSituationForm').form('load',{
			preclrWktimeAmount:'',
			preMprMatAmount:'',
			preclrManagementFee:'',
			preclrOtherAmount:'',
			preclrSumAmount:'',
			preclrRealAmount:''
		});
		$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
			url: 'frtPreClearingAction!findPreCostById.action?preclrId=-1',
			onLoadSuccess : function (data){
			}
		});
		$('#frtPreClearingPartsDatagrid').datagrid({
			url : 'frtPreClearingAction!findPrePartsById.action?preclrId=-1',
			onLoadSuccess : function (data){
				$('#frtPreClearing_parts_accept').linkbutton('disable');		
			}
		});
		
		$('#frtPreClearingItemDatagrid').datagrid({
			url : 'frtPreClearingAction!findPreItemById.action?preclrId=-1',
			onLoadSuccess : function (data){
			}
		});
		$('#frtPreClearingTabs').tabs('select', '结算单汇总');				
	}else{
		$('#frtPreClearing_parts_accept').linkbutton('disable');	
	}
	$('#button').empty();
	$('#remove').linkbutton('enable');
	$('#edit').linkbutton('enable');
	$('#search').linkbutton('enable');
	$('#clear').linkbutton('enable');
	$('#redo').linkbutton('enable');
	$('#redo2').linkbutton('enable');
	$('#redo3').linkbutton('enable');
	$('#print').linkbutton('enable');
	$('#set').linkbutton('enable');
	$('#export').linkbutton('enable');
	$('#back').linkbutton('enable');
}

//转收银
var settlement = function (){
	if(preclrId!=null){
		$.messager.confirm('系统提示', '您确定要进行转收银操作?', function(r){
			if (r){
				$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'frtPreClearingAction!doIsExist.action',
					   data: 'preclrId='+preclrId,
					   success: function(r){
					   		if(r.success){
					   			$.ajax({
									   type: 'post',
									   dataType: 'json',
									   url: 'frtPreClearingAction!transformMoney.action',
									   data: 'receptionId='+receptionId,
									   success: function(r){
									   		if(r.success){
										     	alertMsg(r);
										     	_cancel(true);
										     	window.opener.win_close();
									   		}else{
									   			alertMsg(r);
									   		}
									   }
								});
					   		}else{
					   			alertMsg(r);
					   		}
					   }
					});
			}
		});
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}

//保存
function _save(){
	var url = 'frtPreClearingAction!';
	$.ajax({
		type : 'post',
		url : 'frtPreClearingAction!datagridFrtPreClearing.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			    url += 'edit.action';
			    var parts=null;
				if(staticFrtPreClearingParts==null){
					parts="";
				}else{
					parts=JSON.stringify(staticFrtPreClearingParts);
				}
				if($('#frtPreClearingAddForm').form('validate')){
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : url,
						data : $('#claimMoneyForm').serialize()+'&'+$('#frtPreClearingAddForm').serialize()+'&parts='+parts,
						success : function(r) {
							if (r.success) {	
								alertMsg(r);
								_cancel(false);
								window.opener.win_close();
							}else
								alertMsg(r);
						},
						error : function (r){
						   if(r.readyState == '0' && r.status == '0')
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
					});
				}else
					alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
		   }
	    }
	});
	
}

//交车结算清空
function _clear(){
	$('#frtPreClearingQueryForm').form('clear');
	$('#frtPreClearingSummaryDatagrid').datagrid('load', serializeObject($('#frtPreClearingQueryForm')));
}


//交车结算查询
function query(){
	if($('#frtPreClearingQueryForm').form('validate')){
		$('#frtPreClearingSummaryDatagrid').datagrid('load', serializeObject($('#frtPreClearingQueryForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
};

//返工
function _back(){
	$.ajax({
		type : 'post',
		url : 'frtPreClearingAction!datagridFrtPreClearing.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			   $.messager.confirm('优亿软件提示', '请确认是否要返工？', function(t){
					if (t){
						$.ajax({
						   type: 'post',
						   dataType : 'json',
						   url: 'frtPreClearingAction!back.action',
						   data: r.rows[0],
						   success: function(r){
						      if(r.success){
						      		alertMsg(r);
						      		_cancel(true);
						      		window.opener.win_close();
						      }else
						    	  alertMsg('对不起，该数据已经被使用！', 'warning');
						   }
						});
					}
				});
		   }else{
			   alertMsg('对不起，请先选中要返工的记录！', 'warning');
		   }
	    }
	});
}

/*洗车或待交车*/
function modifyTransFormReceptionState(i){
	if(preclrId){
		    var id=preclrId.substring(0,2);
		    var showName="待交车";
			if(i==parame1)
				 showName="洗车";
		    if(id=='JS'){
				$.messager.confirm('优亿软件提示', '请确认是否要'+showName+'？', function(r){
					if (r){
						$.ajax({
							type : 'post',
							url : 'frtPreClearingAction!modifyTransFormReceptionState.action',
							data : 'receptionId=' +receptionId +'&receptionStatus='+i,
							dataType : 'json',
							success : function callback(r) {
								if (r.success) {
									alertMsg(r);
									_cancel(true);
									window.opener.win_close();
								}else
									alertMsg(r);
							}
						});
					}
				});
			}else
				alertMsg('抱歉，【'+showName+'】功能只针对于接车工单存在，销售结算不具备此功能！', 'warning');

	}else
		alertMsg('对不起，请先选中要转换的记录！', 'warning');
}

//页面控件改变为可用不可用
function requiredAsFormDiy(tag){
if(tag==false){
	$("#frtPreClearingAddForm input").prop("disabled",false);
	$("#frtPreClearing_detail_carId").combobox({required:true,disabled:true});
	$("#frtPreClearing_detail_preclrInoiceType").combobox({required:true,disabled:false});
	$("#frtPreClearing_detail_stfId").combobox({required:true,disabled:true});
	$("#frtPreClearing_detail_reptId").combobox({required:true,disabled:true});
	$("#frtPreClearing_claimMoney_finComId").combobox({required:true,disabled:false});
}else{
	$("#frtPreClearingAddForm input[id=frtPreClearing_detail_preclrNo]").prop("disabled",false);
	$("#frtPreClearingAddForm input[id=addPreclrMaterialRate]").prop("disabled",false);
	$("#frtPreClearingAddForm input[id=addPreclrWktimeRate]").prop("disabled",false);
	$("#frtPreClearingAddForm input[id=addPreclrMaterialRateAsSum]").prop("disabled",false);
	$("#frtPreClearingAddForm input[id=addPreclrWktimeRateAsSum]").prop("disabled",false);

	
	$("#frtPreClearingAddForm input.easyui-numberbox").numberbox("validate");
	$("#frtPreClearingAddForm textarea").prop("disabled",false);
	
	$("#frtPreClearing_detail_preclrInvoiceTime").datetimebox({required:false,disabled:false});
	$("#addPreclrTime").datetimebox({required:true,disabled:true});
	$("#frtPreClearing_detail_carVin").validatebox({required:true});
	$("#frtPreClearing_detail_customName").validatebox({required:true});
	$("#frtPreClearing_detail_preclrId").validatebox({required:true});
	$("#preClearing_receptionId").validatebox({required:true});
	
	$("#frtPreClearing_detail_carVin").validatebox('validate');
	$("#frtPreClearing_detail_customName").validatebox('validate');
	$("#frtPreClearing_detail_preclrId").validatebox('validate');
	$("#preClearing_receptionId").validatebox('validate');
	document.getElementById('frtPreClearing_detail_preclrId').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_carId').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_carVin').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_customName').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_carRelationTel1').style.background="#EBEBE4";
	document.getElementById('preClearing_receptionId').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_stfId').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_reptId').style.background="#EBEBE4";
	document.getElementById('addPreclrOtherAmount').style.background="#EBEBE4";
	document.getElementById('addPreclrManagementFee').style.background="#EBEBE4";
	document.getElementById('addPreclrTime').style.background="#EBEBE4";
	document.getElementById('addPreMprMatAmount').style.background="#EBEBE4";
	document.getElementById('addPreMprMatAmountOld').style.background="#EBEBE4";
	document.getElementById('addPreclrWktimeAmount').style.background="#EBEBE4";
	document.getElementById('addPreclrSumAmount').style.background="#EBEBE4";
	document.getElementById('addPreclrSumRate').style.background="#EBEBE4";
	document.getElementById('addPreclrRealAmount').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_preclrNoTaxCost').style.background="#EBEBE4";
	document.getElementById('frtPreClearing_detail_preclrTaxCost').style.background="#EBEBE4";
	document.getElementById('addPreclrRealAmount').style.background="#EBEBE4";
	}
}

function addprint(){
    var preclrId=$('#frtPreClearing_detail_preclrId').val();
    alert(preclrId);
    if(preclrId!=''&&preclrId!=null)
   	 window.open(projectPath+'print/frtpreclearing.jsp?preclrId='+preclrId,'demo',"fullscreen=1")
	 else
		 $.messager.alert('优亿软件提示','请选择要打印的退货单记录！','warning');
 }

//交车结算_except导出
function _except(){
	showEditDialog("frtPreClearingSummaryDatagrid",null,"frtPreClearingSummaryCenter","开始导出","导出配置",0,_callbackExcept);
}

//交车结算_except导出   取消
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"交车结算单"+getSystemTime());
}