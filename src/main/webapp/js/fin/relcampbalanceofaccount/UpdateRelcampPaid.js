//添加
function relcampbalanceofaccount_save(){
	$.ajax({
		type : 'POST',
		url : 'RelcampBalanceOfAccountAction!add.action',
		data : 'accountDate='+$('#accountDate').val()+'&relcampId='+$('#relcampId').combobox('getValue')+'&receiptId='+$('#receiptId').combobox('getText')
		+'&stfId='+$('#rboa_stfId').combobox('getValue')+'&accountReceipt='+$('#accountReceipt').combobox('getValue')+'&operType='+$('#operType').combobox('getValue')
		+'&recAmount='+$('#rboa_recAmount').val()+'&paidAmount='+$('#paidAmount').val()+'&vendorRemark='+$('#vendorRemark').val(),
		dataType : 'json',
		success : function(r){
              if(r.success){
                 $('#RelcampBalanceOfAccountDetailForm').form('clear');
                 $('#RelcampBalanceOfAccountTable').datagrid('load');
                 rboa_d1.dialog('close');
              }else{
            	 $.messager.alert('优亿软件提示','供应商对账添加失败！','warning',function(){});
              }
              
        }
    });
}

//取消添加
function st_sell_chargeitem_cancelSave(){
	ssc_d1.dialog('close');
}