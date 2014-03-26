function LoadOk() {
	if (document.readyState == "complete") {
		if(staticFinClaimantMainDisabled==false){
		 	requiredAsForm(false,'finClaimantMainAddForm');
		}else{
			staticFinClaimantMainDisabled=false;
		}
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame() {
	var data = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
	if(data){
		$('#finClaimantMainAddForm').form('load', data);
	}
	/*if($('#save').length != 0 && $('#cancel').length != 0){
		var preClearingButton = '<a id="preClearingButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" onclick="unconfirmed();"></a>';
		$('#button2').append(preClearingButton);
		$.parser.parse('#button2');
	}*/

}
setTimeout("LoadOk();", 200);

var unconfirmedClaimDialog;

var unconfirmed = function (){
	unconfirmedClaimDialog = $('<div/>').dialog({
		modal:true,
		title : '未确认索赔工单',
		closable : true,
		width : 820,
		height : 480,
		href : projectPath+'frt/finClaimantMain/details/unconfirmedClaim.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
				loadPreClearing('unconfirmedClaimDatagrid',unconfirmedClaimDialog);
			}
		},{
			text : '取消',
			handler : function (){
				unconfirmedClaimDialog.dialog('close');
			}
		}],
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}
function sumMoney(){
	var addPreclrSumAmount=0.00;
	var addClaimantmPartsAmount= $('#addClaimantmPartsAmount').val();
	var addClaimantmTimeAmount= $('#addClaimantmTimeAmount').val();
	var addClaimantmOtherAmount= $('#addClaimantmOtherAmount').val();
	var addClaimantmManagementFee= $('#addClaimantmManagementFee').val();
	addPreclrSumAmount=parseFloat(addClaimantmPartsAmount)+parseFloat(addClaimantmTimeAmount)
			+parseFloat(addClaimantmOtherAmount)+parseFloat(addClaimantmManagementFee);
	$('#addClaimantmAmount').val(addPreclrSumAmount);
}
function toteMoney(){
	if(staticFinClaimantMainParts!=null){
		staticFinClaimantMainParts = prosceniumUpdate('finClaimantMainPartsDatagrid',staticFinClaimantMainParts);
	}
	if(staticFinClaimantMainItems!=null){
		staticFinClaimantMainItems = prosceniumUpdate('finClaimantMainItemDatagrid',staticFinClaimantMainItems);
	}
	if(staticFinClaimantMainExpenseSituationOtherExpense!=null){
		staticFinClaimantMainExpenseSituationOtherExpense = prosceniumUpdate('finClaimantMainExpenseSituationOtherExpense',staticFinClaimantMainExpenseSituationOtherExpense);
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
	/*计算收费额*/
	$.ajax({
		type : 'post',
		url : 'finClaimantMainAction!totemoney.action',
		dataType : 'json',
		data :'others='+others+ '&parts='+parts+ '&items='+items,
		success : function(r) {
			$('#finClaimantMainPreclrWktimeAmount').val(r[0]);
			$('#finClaimantMainPreMprMatAmount').val(r[1]);
			$('#finClaimantMainPreclrManagementFee').val(r[2]);
			$('#finClaimantMainOtherAmount').val(r[3]);
			$('#finClaimantMainSumAmount').val(r[4]);
			$('#addClaimantmPartsAmount').val(r[1]);
			$('#addClaimantmTimeAmount').val(r[0]);
			$('#addClaimantmOtherAmount').val(r[3]);
			$('#addClaimantmManagementFee').val(r[2]);
			$('#claimantmTaxCost').val(r[5]);
			$('#claimantmNoTaxCost').val(r[6]);
			sumMoney();
		}
	});
}