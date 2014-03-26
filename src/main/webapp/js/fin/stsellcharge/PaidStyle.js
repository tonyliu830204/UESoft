//添加
function st_sell_chargeitem_save(){
	$.ajax({
		type : 'POST',
		url : 'StSellChargeAction_addStSellChargeItem.action',
		data : 'paidDate='+$('#sscd_paidDate').val()+'&paidPerson='+$('#sscd_paidPerson').combobox('getText')+'&patment='+$('#sscd_patment').combobox('getText')
		+'&chargeId='+$('#sscd_chargeId').val()+'&paidAmount='+$('#sscd_PaidAmount').val()+'&remark='+$('#sscd_remark').val(),
		dataType : 'json',
		success : function(r){
		             // 刷新明细datagrid
	            	 $.ajax({
							type : 'POST',
							url : 'StSellChargeAction_loadStSellchargeItemByChargeId.action',
							data : 'chargeId='+$('#sscd_chargeId').val(),
							dataType : 'json',
							success : function(r){
	   		                	       $('#st_sell_chargeItem_Table').datagrid('loadData',r);
   		                    }
   		             });
   		             //刷新汇总表单已收金额合计数据
	            	 $.ajax({
	         			type : 'POST',
	         			url :'StSellChargeAction_searchStSellChargeByChargerId.action',
	         			data : 'chargeId='+$('#ssc_ChargeId').val(),
	         			dataType : 'json',
	         			success : function(r){
	         		       if(r!=null&&r!=''){
    	         		      var totalAmont=r.totalAmount;
                              $('#ssc_PaidAmount').val(totalAmont);
                              var preclrAmount=$('#sscd_PreclrAmount').val();
                              if(parseFloat(totalAmont) > parseFloat(preclrAmount)){
                            	  $('#ssc_PaidAmount').val(preclrAmount);
                              }
                              st_sell_chargeitem_cancelSave();
    	         		   }else{
    	         			  $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
        	         	   }
	         		    }
	         	    });
            }
    });
}

//取消添加
function st_sell_chargeitem_cancelSave(){
	ssc_d1.dialog('close');
}