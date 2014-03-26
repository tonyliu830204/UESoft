function smr_searchByCondition()
 {
	 var tabName=$('#mor_tabs').tabs('getSelected');
     var tab = tabName.panel('options').title;
     if(tab=='维修应收款'){
    	 $.ajax({
			type : 'POST',  
			url : 'ReceivablesAction_searchFinMaintenanceReceivablesByCondition.action',
			data :$('#fin_maintenance_receivables_form').serialize(),
			dataType : 'json',
			success : function(r){
	 				if(r.total>0){
		 				fin_maintenance_receivablesTableDategrid.datagrid('loadData',r);
	 	 			}else{
	 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
	 	 	 		}
            }
         }); 	
     }else if(tab=='索赔应收款'){
    	 $.ajax({
			type : 'POST',  
			url : 'ReceivablesAction_searchFinClaimsReceivablesByCondition.action',
			data :'claimantmTimeStart='+$('#s_fcr_claimantmTimeStart').val()+
			      '&claimantmTimeEnd='+$('#s_fcr_claimantmTimeEnd').val()+
	 				'&claimantmAmount='+$('#s_fcr_claimantmAmount').val()+
	 				'&stfName='+$('#s_fcr_stfName').combobox('getText')+
	 				'&resvRealTimeStart='+$('#s_fcr_resvRealTimeStart').val()+
	 				'&resvRealTimeEnd='+$('#s_fcr_resvRealTimeEnd').val()+
	 				'&receptionId='+$('#s_fcr_receptionId').val()+
	 				'&relcampName='+$('#s_fcr_relcampName').combobox('getText')+
	 				'&receLicense='+$('#s_fcr_receLicense').combobox('getText')+
	 				'&carVin='+$('#s_fcr_carVin').val()+
	 				'&cbrdName='+$('#s_fcr_cbrdName').combobox('getText'),
			dataType : 'json',
			success : function(r){
	 				if(r.total>0){
	 					fin_claims_receivablesTableDategrid.datagrid('loadData',r);
	 	 			}else{
	 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
	 	 	 		}
            }
         }); 	
     }else if(tab=='销售应收款'){
    	 $.ajax({
				type : 'POST',  
				url : 'ReceivablesAction_searchStSellChargeByCondition.action',
				data :'sellmmDateStart='+$('#s_ssc_sellmmDateStart').val()+
				    '&sellmmDateEnd='+$('#s_ssc_sellmmDateEnd').val()+
	 				'&preclrSumAmount='+$('#s_ssc_PreclrSumAmount').val()+
	 				'&carBrand='+$('#s_ssc_carBrand').combobox('getText')+
	 				'&preclrDateStart='+$('#s_ssc_preclrDateStart').val()+
	 				'&preclrDateEnd='+$('#s_ssc_preclrDateEnd').val()+
	 				'&customName='+$('#s_ssc_customName').combobox('getText')+
	 				'&carLicense='+$('#s_ssc_carLicense').combobox('getText')+
	 				'&cerNo='+$('#s_ssc_cerNo').val()+
	 				'&carVin='+$('#s_ssc_carVin').val(),
				dataType : 'json',
				success : function(r){
	 				if(r.total>0){
	 					st_sell_chargeTableDategrid.datagrid('loadData',r);
	 	 			}else{
	 	 				$.messager.alert('优亿软件提示','对不起，没有找到你要查询的记录！','warning',function(){});
	 	 	 		}
	            }
	     }); 	
     }
 }

 function smr_clearSearchByCondition()
 {
	 var tabName=$('#mor_tabs').tabs('getSelected');
     var tab = tabName.panel('options').title;
     if(tab=='维修应收款'){
    	 $('#fin_maintenance_receivables_form').form('clear');
         fin_maintenance_receivablesTableDategrid.datagrid('load');
     }else if(tab=='索赔应收款'){
    	 $('#fin_claims_receivables_form').form('clear');
    	 fin_claims_receivablesTableDategrid.datagrid('load');
     }else if(tab=='销售应收款'){
    	 $('#st_sell_charge_form').form('clear');
    	 st_sell_chargeTableDategrid.datagrid('load');
     }
 }