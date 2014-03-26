var rowData=null;
var initFrames=function () {
	if(preclrId != null && preclrId.length > 0 && preclrId != 'undefined'){
		 $.ajax({
			type : 'post',
			url : 'frtPreClearingAction!datagridFrtPreClearing.action',
			dataType : 'json',
			data:'preclrId='+preclrId,
			success : function(r) {
		 		rowData=r.rows[0];
				$('#frtPreClearingTabs').tabs('select', '结算单明细');
				$('#frtPreClearingAddForm').form('load', rowData);
				$('#frtPreClearingExpenseSituationForm').form('load', rowData);
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					url: 'frtPreClearingAction!findPreCostById.action?preclrId=' + preclrId
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					url : 'frtPreClearingAction!findPrePartsById.action?preclrId=' +preclrId
				});
				$('#frtPreClearingItemDatagrid').datagrid({
					url : 'frtPreClearingAction!findPreItemById.action?preclrId=' + preclrId
				});
				$('#frtPreClearingExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+rowData.receptionId
				});
				frtPreClearingAddFormKeyUp();
			}
		 });
	}else{
		 $.ajax({
			type : 'post',
			url : 'frtPreClearingAction!datagridFrtPreClearing.action',
			dataType : 'json',
			data:'receptionId='+receptionId,
			success : function(r) {
		 		rowData=r.rows[0];
		 		preclrId=rowData.preclrId;
				$('#frtPreClearingTabs').tabs('select', '结算单明细');
				$('#frtPreClearingAddForm').form('load', rowData);
				$('#frtPreClearingExpenseSituationForm').form('load', rowData);
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					url: 'frtPreClearingAction!findPreCostById.action?preclrId=' + rowData.preclrId
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					url : 'frtPreClearingAction!findPrePartsById.action?preclrId=' +rowData.preclrId
				});
				$('#frtPreClearingItemDatagrid').datagrid({
					url : 'frtPreClearingAction!findPreItemById.action?preclrId=' + rowData.preclrId
				});
				$('#frtPreClearingExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+receptionId
				});
				frtPreClearingAddFormKeyUp();
			}	
		 });
	}
}
var numverBit;
function LoadOk() {
	if (document.readyState == "complete") {
		if(staticDisabled==false){
			 requiredAsForm(false,'frtPreClearingAddForm');
		}else{
			staticDisabled=false;
		}
		$.ajax({
		    type: 'post',
		    dataType: 'json',
		    url:projectPath+'baseAction!loadNumberbit.action',  
		    success: function(r){ 
				numberBit=r.ciValue;
				$('#addPreclrOtherAmount').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreclrManagementFee').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreMprMatAmount').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreMprMatAmountOld').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreclrWktimeAmount').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreclrSumAmount').numberbox({  
			        precision:numberBit
			    }); 
				$('#addPreclrRealAmount').numberbox({  
			        precision:numberBit
			    }); 
				$('#frtPreClearing_detail_preclrNoTaxCost').numberbox({  
			        precision:numberBit
			    }); 
				$('#frtPreClearing_detail_preclrTaxCost').numberbox({  
			        precision:numberBit
			    });
				initFrames();
		    },
		    error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		    }
		});
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);

function frtPreClearingAddFormKeyUp(){
	var addPreclrRealAmount=null;
	var addPreMprMatAmountOld= $('#addPreMprMatAmountOld').val();    //材料费用合计(已折扣)
	var addPreMprMatAmount= $('#addPreMprMatAmount').val();          //材料费用合计(未折扣)
	var addPreclrMaterialRate= $('#addPreclrMaterialRate').val();    //材料费用合计折扣率
	var addPreclrWktimeRate= $('#addPreclrWktimeRate').val();        //工时折合扣率
	var addPreclrWktimeAmount= $('#addPreclrWktimeAmount').val();    //工时费用合计
	var addPreclrOtherAmount= $('#addPreclrOtherAmount').val();      //其他费用
	var addPreclrManagementFee= $('#addPreclrManagementFee').val();  //管理费
	var addPreclrSumAmount=$('#addPreclrSumAmount').val();           //总费用合计 
	
	//总费用合计 = 材料费用合计(未折扣) + 工时费用合计 + 其他费用 + 管理费
	addPreclrSumAmount= parseFloat(addPreMprMatAmount)+ parseFloat(addPreclrWktimeAmount) + parseFloat(addPreclrOtherAmount) + parseFloat(addPreclrManagementFee);
	$('#addPreclrSumAmount').numberbox('setValue',addPreclrSumAmount);
	$('#frtPreClearingPreclrSumAmount').val(addPreclrSumAmount);
	
	//总费用合计 = 材料费用合计(已折扣) * 材料费用合计折扣率 + 工时费用合计 * 工时折合扣率 + 其他费用 + 管理费
	addPreclrRealAmount=parseFloat(addPreMprMatAmountOld*(addPreclrMaterialRate/100))+ parseFloat(addPreclrWktimeAmount*(addPreclrWktimeRate/100))+parseFloat(addPreclrOtherAmount)+parseFloat(addPreclrManagementFee);
	$('#addPreclrRealAmount').numberbox('setValue',addPreclrRealAmount);     //实际费用
	$('#frtPreClearingPreclrRealAmount').val(addPreclrRealAmount);
	
	// 合计折扣率 = 实际费用 / 总费用合计
	var addPreclrSumRate=100.00;
	if(!(addPreclrRealAmount=="0"||addPreclrSumAmount=="0"))
		addPreclrSumRate=(parseFloat(addPreclrRealAmount)/parseFloat(addPreclrSumAmount))*100;
	if(addPreclrSumRate.toString().indexOf('.')<=0){
		$('#addPreclrSumRate').numberbox('setValue',addPreclrSumRate);
	}else{
		$('#addPreclrSumRate').numberbox('setValue',addPreclrSumRate.toString().substring(0, parseFloat(addPreclrSumRate.toString().indexOf('.'))+parseFloat(3)));
	}
	
	//材料优惠金额
	$('#addPreclrMaterialRateAsSum').numberbox('setValue',(addPreMprMatAmountOld*(1-addPreclrMaterialRate/100)));
	
	//工时优惠金额
	$('#addPreclrWktimeRateAsSum').numberbox('setValue',(addPreclrWktimeAmount*(1-addPreclrWktimeRate/100)));
}
function frtPreClearingToteMoney(){
	var parts=null;
	if(staticFrtPreClearingParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtPreClearingParts);
	}
	/*计算收费额*/
	$.ajax({
			type : 'post',
			url : 'frtPreClearingAction!totemoney.action',
			data : 'preclrId='+$('#frtPreClearing_detail_preclrId').val()+'&parts='+parts,
			dataType : 'json',
			success : function(r) {
				$('#frtPreClearingPreclrWktimeAmount').val(r[0]);
				$('#frtPreClearingPreMprMatAmount').val(r[1]);
				$('#addPreMprMatAmountOld').numberbox('setValue',r[1]);
				$('#frtPreClearingPreclrManagementFee').val(r[2]);
				$('#frtPreClearingPreclrOtherAmount').val(r[3]);
				$('#frtPreClearingPreclrSumAmount').val(r[4]);
				$('#frtPreClearingPreclrRealAmount').val(r[5]);
				frtPreClearingAddFormKeyUp();
			}
		});
}
var materialRateOrSumOnBlur=function(){
		var addPreMprMatAmountOld=$('#addPreMprMatAmountOld').val();
		if(addPreMprMatAmountOld!=""&&addPreMprMatAmountOld==0.00){
			alertMsg('材料费用合计为0.00，不能进行优惠！', 'warning');
			$('#addPreclrMaterialRateAsSum').numberbox('setValue',0.00);
			return;
		}
		var addPreclrMaterialRateAsSum= $('#addPreclrMaterialRateAsSum').val();
		if(parseFloat(addPreclrMaterialRateAsSum)>parseFloat(addPreMprMatAmountOld)){
			alertMsg('优惠额不能大于合计金额！', 'warning');
			$('#addPreclrMaterialRateAsSum').numberbox('setValue',addPreMprMatAmountOld);
			addPreclrMaterialRateAsSum= $('#addPreclrMaterialRateAsSum').val();
		}
		var addPreclrMaterialRate=parseFloat(addPreclrMaterialRateAsSum)/parseFloat(addPreMprMatAmountOld);
		$('#addPreclrMaterialRate').numberbox('setValue',parseFloat(addPreclrMaterialRate)*100);
		frtPreClearingAddFormKeyUp();
}
var wktimeRateOrSumOnBlur=function(){
		var addPreclrWktimeAmount=$('#addPreclrWktimeAmount').val();
		if(addPreclrWktimeAmount!=""&&addPreclrWktimeAmount==0.00){
			alertMsg('材料费用合计为0.00，不能进行优惠！', 'warning');
			$('#addPreclrWktimeRateAsSum').numberbox('setValue',0.00);
			return;
		}
		var addPreclrWktimeRateAsSum= $('#addPreclrWktimeRateAsSum').val();
		if(parseFloat(addPreclrWktimeRateAsSum)>parseFloat(addPreclrWktimeAmount)){
			alertMsg('优惠额不能大于合计金额！', 'warning');
			$('#addPreclrWktimeRateAsSum').numberbox('setValue',addPreclrWktimeAmount);
			addPreclrWktimeRateAsSum= $('#addPreclrWktimeRateAsSum').val();
		}
		var addPreclrWktimeRate=parseFloat(addPreclrWktimeRateAsSum)/parseFloat(addPreclrWktimeAmount);
		$('#addPreclrWktimeRate').numberbox('setValue',parseFloat(addPreclrWktimeRate)*100);
		frtPreClearingAddFormKeyUp();
}