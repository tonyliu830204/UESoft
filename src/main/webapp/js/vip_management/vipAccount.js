$(function(){
	loadSummary();
	loadDetail();
	loadMoneyDetail();
	
	$('#tb_id').tabs({
		border:false,
		onSelect:function(title){
		    if(title == "对账单明细"){
		    	$("#add").linkbutton('disable');
		    	$("#search").linkbutton('disable');
		    	$("#clear").linkbutton('disable');
		    	$("#pay").linkbutton('enable');
		    	$("#examine").linkbutton('enable');
		    }else if(title == "对账单收款明细"){
		    	$("#pay").linkbutton('enable');
		    	$("#examine").linkbutton('disable');
		    }else{
		    	$("#add").linkbutton('enable');
		    	$("#search").linkbutton('enable');
		    	$("#clear").linkbutton('enable');
		    	$("#pay").linkbutton('enable');
		    	$("#examine").linkbutton('enable');
		    }
		}
	});
});

function loadSummary(){
	$('#vipAccountSummary').datagrid({
		url : projectPath+'vipAccountAction!findAll.action',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'accountId',
		sortName:'accountId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'accountId',
				title : '对账编号',
				hidden : true
			},{
				field : 'accountStartDate',
				title : '对账开始日期',
				width : 130,
				sortable : true
			},{
				field : 'accountEndDate',
				title : '对账结束日期',
				width : 130,
				sortable : true
			},{
				field : 'incomeAmount',
				title : '总储值额',
				width : 130,
				sortable : true
			},{
				field : 'defrayAmount',
				title : '总收益额',
				width : 130,
				sortable : true
			},{
				field : 'accountPersonName',
				title : '对账经办人',
				width : 130,
				sortable : true
			},{
				field : 'accountDate',
				title : '对账日期',
				width : 130,
				sortable : true
			},{
				field : 'logout',
				title : '注销key',
				hidden : true
			},{
				field : 'logoutName',
				title : '注销情况',
				width : 130,
				sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			datagridDetail(rowData);
		}
	});
}

function loadDetail(){
	$('#vipAccountDetail').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'detailId',
		sortName:'detailId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'detailId',
				title : '明细编号',
				hidden : true
			},{
				field : 'accountId',
				title : '对账编号',
				hidden : true
			},{
				field : 'vipId',
				title : '会员编号',
				hidden : true
			},{
				field : 'logout',
				title : '注销key',
				hidden : true
			},{
				field : 'openterpriseId',
				title : '经办企业id',
				hidden : true
			},{
				field : 'openterpriseName',
				title : '经办企业名称',
				width : 130,
				sortable : true
			},{
				field : 'vipNumber',
				title : '会员卡号',
				width : 100,
				sortable : true
			},{
				field : 'vipName',
				title : '会员名',
				width : 130,
				sortable : true
			},{
				field : 'carLicense',
				title : '车辆牌照',
				width : 100,
				sortable : true
			},{
				field : 'carVin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'accountPersonName',
				title : '对账经办人',
				width : 130,
				sortable : true
			},{
				field : 'accountDate',
				title : '对账经办日期',
				width : 130,
				sortable : true
			},{
				field : 'storedValueAmount',
				title : '总储值额',
				width : 130,
				sortable : true
			},{
				field : 'expenditureAmount',
				title : '总收益额',
				width : 130,
				sortable : true
			},{
				field : 'refundAmount',
				title : '应退款额',
				width : 130,
				sortable : true
			},{
				field : 'receiptAccount',
				title : '应收款额',
				width : 130,
				sortable : true
			},{
				field : 'refundedAmount',
				title : '已退款额',
				width : 130,
				sortable : true
			},{
				field : 'receiptedAccount',
				title : '已收款额',
				width : 130,
				sortable : true
			},{
				field : 'finalStage',
				title : '是否结清key',
				hidden : true
			},{
				field : 'finalStageValue',
				title : '是否结清',
				width : 130,
				sortable : true
			},{
				field : 'recAuditOperName',
				title : '审核人',
				width : 130,
				sortable : true
			},{
				field : 'recAuditDate',
				title : '审核日期',
				width : 130,
				sortable : true
			},{
				field : 'recAuditStatus',
				title : '审核状态Id',
				hidden : true
			},{
				field : 'recAuditStatusName',
				title : '审核状态',
				width : 130,
				sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
		    $('#detailgatheringAccount').attr("readonly","readonly");
			datagridMoneyDetail(rowData);
		}
	});
}

function loadMoneyDetail(){
	$('#vipAccountMoneyDetail').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'detailMoneyId',
		sortName:'detailMoneyId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'detailMoneyId',
				title : '收款明细编号',
				hidden : true
			},{
				field : 'detailId',
				title : '对账单编号',
				hidden : true
			},{
				field : 'detailaccountWay',
				title : '收款方式key',
				hidden : true
			},{
				field : 'detailaccountType',
				title : '收款类型key',
				hidden : true
			},{
				field : 'detailaccountDate',
				title : '收款日期',
				width :150,
				sortable : true
			},{
				field : 'detailgatheringAccount',
				title : '收款金额',
				width :150,
				sortable : true
			},{
				field : 'refundAmount',
				title : '应退金额',
				width :150,
				sortable : true
			},{
				field : 'receiptAccount',
				title : '应收金额',
				width :150,
				sortable : true
			},{
				field : 'detailaccountPersonName',
				title : '收款人',
				width :150,
				sortable : true
			},{
				field : 'detailaccountWayName',
				title : '收款方式',
				width :150,
				sortable : true
			},{
				field : 'detailaccountTypeName',
				title : '收款类型',
				width :150,
				sortable : true
			},{
				field : 'detailremark',
				title : '备注',
				width :150,
				sortable : true
			}
		]]
	});
}

function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
}

function doAdd(){
	if ($('#editbutton').children('a').length == 0) {
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveChoicedProject();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		$('#editbutton').append(save).append(cancel);
		$.parser.parse('#editbutton');
		$('#tb_id').tabs('select','对账单明细');
		$.ajax({
			type : 'POST',
			url : projectPath+'vipAccountAction!getMaxAccountEndDate.action',
			dataType : 'json',
			success : function(r){
				if(r.success){
					$('#vipAccountDetail').datagrid('loadData', { total: 0, rows: [] });
					$('#form_vipAccountDetail').form('clear');
					$('#accountStartDate').val(r.obj);
				}
			}
		});
		$("#pay").linkbutton('disable');
    	$("#examine").linkbutton('disable');
	}else{
		alertMsg('对不起,请先完成当前编辑操作！', 'warning');
		return;
	}
}

function doSaveChoicedProject(){
	var accountStartDate = $('#accountStartDate').val();
	var accountEndDate = $('#accountEndDate').val();
	if(accountStartDate != null && accountEndDate != null){
		$.ajax({
			type : 'POST',
			url : projectPath+'vipAccountAction!doAdd.action',
			data : serializeObject($('#form_vipAccountDetail')),
		    dataType : 'json',
			success : function(r){
			    alertMsg(r.msg, 'info');
				if(r.success){
					$('#vipAccountDetail').datagrid('loadData', { total: 0, rows: [] });
					$('#form_vipAccountDetail').form('clear');
					$('#vipAccountSummary').datagrid('load', serializeObject($('#form_vipAccount')));
					$('#editbutton').empty();
				}
			}
	   });
	}else{
		alertMsg('对不起,请选择对账时间范围！', 'warning');
		return;
	}
}

function cancel(){
	$('#editbutton').empty();
	$('#vipAccountDetail').datagrid('loadData', { total: 0, rows: [] });
}

function datagridDetail(rowData){
	$('#tb_id').tabs('select','对账单明细');
	$('#form_vipAccountDetail').form('clear');
	$('#form_vipAccountDetail').form('load', rowData);
	$('#vipAccountDetail').datagrid({url : projectPath+'vipAccountAction!getAccountDetail.action?accountId='+rowData.accountId});
}

function datagridMoneyDetail(rowData){
	$('#tb_id').tabs('select','对账单收款明细');
	$('#form_vipAccountMoneyDetail').form('clear');
	$('#form_vipAccountMoneyDetail').form('load', rowData);
	if(rowData.refundAmount != '0.0'){
		$('#gatheringAccount').val(rowData.refundAmount - rowData.refundedAmount);
	}else{
		$('#gatheringAccount').val(rowData.receiptAccount - rowData.receiptedAccount);
	}
	$('#detailgatheringAccount').val('0.0');
	$('#vipAccountMoneyDetail').datagrid({url : projectPath+'vipAccountAction!getAccountMoneyDetail.action?detailId='+rowData.detailId});
}

function doConditionSubmit(){
	$('#vipAccountSummary').datagrid('load', serializeObject($('#form_vipAccount')));
}

function clearForm(){
	$('#form_vipAccount').form('clear');
	$('#vipAccountSummary').datagrid('load', serializeObject($('#form_vipAccount')));
}

function doPayMent(){
	var value = $('#vipAccountDetail').datagrid('getSelections');
	if(value.length==0){
		alertMsg('对不起，请先选择要收款的记录！', 'warning');
		return;
	}else if(value[0].recAuditStatus == sate){
		if ($('#editbutton').children('a').length == 0) {
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSavePay();">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancelPay();">取消</a>';
			$('#editbutton').append(save).append(cancel);
			$.parser.parse('#editbutton');
			datagridMoneyDetail(value[0]);
			$('#detailgatheringAccount').removeAttr("readonly");
		}else{
			alertMsg('对不起,请先完成当前编辑操作！', 'warning');
			return;
		}
	}else{
		alertMsg('对不起，该记录还未审核不可收款！', 'warning');
		return;
	}
}

function doShenhe(){
	var value = $('#vipAccountDetail').datagrid('getSelections');
	if(value.length==0){
		alertMsg('对不起，请先选择要审核的记录！', 'warning');
		return;
	}else if(value[0].recAuditStatus != sate){
		$.ajax({
			type : 'POST',
			url : projectPath+'vipAccountAction!doUpdateState.action',
			data : value[0],
		    dataType : 'json',
			success : function(r){
			    alertMsg(r.msg, 'info');
				if(r.success){
					$('#vipAccountDetail').datagrid({url : projectPath+'vipAccountAction!getAccountDetail.action?accountId='+$('#accountId').val()});
				}
			}
	   });
	}else{
		alertMsg('对不起，该记录已审核不可重复审核！', 'warning');
		return;
	}
}

function doSavePay(){
	var receiptAccount = $('#receiptAccount').val();
    var detailgatheringAccount = $('#detailgatheringAccount').val();
    if(detailgatheringAccount > receiptAccount){
    	alertMsg('实付金额不能大于应付金额！', 'warning');
    	return;
    }else if(detailgatheringAccount == '0.0'){
    	alertMsg('请输入付款金额！', 'warning');
    	return;
    }else{
    	$.ajax({
    		type : 'POST',
    		url : projectPath+'vipAccountAction!doPayMent.action',
    		data : serializeObject($('#form_vipAccountMoneyDetail')),
    	    dataType : 'json',
    		success : function(r){
    		    alertMsg(r.msg, 'info');
    			if(r.success){
    				$('#tb_id').tabs('select','对账单明细');
    				$('#vipAccountDetail').datagrid({url : projectPath+'vipAccountAction!getAccountDetail.action?accountId='+$('#accountId').val()});
    				$('#vipAccountMoneyDetail').datagrid({url : projectPath+'vipAccountAction!getAccountMoneyDetail.action?accountId='+$('#detailId').val()});
    				$('#editbutton').empty();
    			}
    		}
       });
    }
}

function cancelPay(){
	$('#editbutton').empty();
}