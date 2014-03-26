$(function() {
	//前台接车->费用情况->其他费用明细
	var url="";
	if(preclrId){
		url='frtPreClearingAction!findPreCostById.action?preclrId=' + preclrId;
	}else{
		url='frtPreClearingAction!findPreCostById.action?preclrId=-1';
	}
	$frtPreClearingExpenseSituationOtherExpense = $('#frtPreClearingExpenseSituationOtherExpense');
	$frtPreClearingExpenseSituationOtherExpense.datagrid({
		url : url,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		columns : [ [ {
			field : 'costItem',
			title : '收费项目',
			width : 60,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "收费项目为必填项!"
				}
			}
		}, {
			field : 'costAmount',
			title : '收费金额',
			width : 60,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision : 2,
					missingMessage : "收费金额为必填项!"
				}
			}
		}, {
			field : 'costExplain',
			title : '收费说明',
			width : 60,
			sortable : true,
			editor : {
				type : 'text'
			}
		} ] ]
	});
});