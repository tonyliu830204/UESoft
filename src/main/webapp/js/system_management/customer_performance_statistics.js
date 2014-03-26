$(function(){
	$('#parameterTabs').tabs({   
		onSelect:function(title){
			if(title =='维修人员业绩统计'){
				unbindAllButton();
				$("#_search").bind("click",queryServicePerson); 
				$("#_clear").bind("click",clearServicePerson);
				$("#_export").bind("click",_exceptServicePerson);
			}else if(title =='结算工时查询'){
				unbindAllButton();
				$("#_search").bind("click",queryBalanceHoursQuery);  
				$("#_clear").bind("click",clearBalanceHoursQuery);
				$("#_export").bind("click",_exceptBalanceHoursQuery);
			}else if(title =='索赔结算工时统计'){
				unbindAllButton();
				//$("#_search").bind("click",queryFrtWorkOrderParts);  
				//$("#_clear").bind("click",clearFrtWorkOrderParts);
				//$("#_export").bind("click",_exceptFrtWorkOrderParts);
			}else if(title =='维修人员业绩统计汇总'){
				unbindAllButton();
				$("#_search").bind("click",queryServicePersonMain);  
				$("#_clear").bind("click",clearServicePersonMain);
				$("#_export").bind("click",_exceptServicePersonMain);
				
			}else if(title =='业务员业务统计'){
				unbindAllButton();
				//$("#_search").bind("click",queryFrtWorkOrderPreClearingParts);  
				//$("#_clear").bind("click",clearFrtWorkOrderPreClearingParts);
				//$("#_export").bind("click",_exceptFrtWorkOrderPreClearingParts);
			}
		}
	});
});

function unbindAllButton(){
	$("#_search").unbind();
	$("#_clear").unbind();
	$("#_export").unbind();
	$('#_search').linkbutton('enable');
	$('#_clear').linkbutton('enable');
	$('#_export').linkbutton('enable');
}
function queryServicePersonMain(){
	if($('#servicePersonMainForm').form('validate')){
		$('#servicePersonMainDatagrid').datagrid('load', serializeObject($('#servicePersonMainForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearServicePersonMain(){
 $('#servicePersonMainForm').form('clear');
}
function _exceptServicePersonMain(){
	showEditDialog("servicePersonMainDatagrid",null,"servicePersonMainDatagrid_center","开始导出","导出配置",0,_callbackExceptServicePersonMain);
}
function _callbackExceptServicePersonMain(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"维修人员业务统计汇总"+getSystemTime());
}

function queryBalanceHoursQuery(){
	var value=$('#balanceHours_weaveWay').combobox('getValue');
	if($('#balanceHoursQueryForm').form('validate')){
		if(value!=null&&value=='true'){
			forRecivePerson();
			var url="customerPerformanceStatisticsAction!findBalanceHoursQueryForRecivePerson.action?";
			url+=$('#balanceHoursQueryForm').serialize();
			$('#balanceHoursQueryDatagrid').treegrid({
				url:url
			});	
		}else{
			bhRuns();
			var url="customerPerformanceStatisticsAction!findBalanceHoursQuery.action?";
			url+=$('#balanceHoursQueryForm').serialize();
			$('#balanceHoursQueryDatagrid').treegrid({
				url:url
			});	
		}
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearBalanceHoursQuery(){
 $('#balanceHoursQueryForm').form('clear');
}
function _exceptBalanceHoursQuery(){
	showEditDialog("balanceHoursQueryDatagrid",null,"balanceHoursQueryDatagrid_center","开始导出","导出配置",0,_callbackExceptBalanceHoursQuery);
}
function _callbackExceptBalanceHoursQuery(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"结算工时查询"+getSystemTime());
}

function queryServicePerson(){
	var value=$('#servicePerson_weaveWay').combobox('getValue');
	if($('#servicePersonQueryForm').form('validate')){
		if(value!=null&&value=='true'){
			forReceptionId();
			var url="customerPerformanceStatisticsAction!findServicePersonScoreForReceptionId.action?";
			url+=$('#servicePersonQueryForm').serialize();
			$('#servicePersonDatagrid').treegrid({
				url:url
			});	
		}else{
			runs();
			var url="customerPerformanceStatisticsAction!findServicePersonScore.action?";
			url+=$('#servicePersonQueryForm').serialize();
			$('#servicePersonDatagrid').treegrid({
				url:url
			});			
		}
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearServicePerson(){
 $('#servicePersonQueryForm').form('clear');
}
function _exceptServicePerson(){
	showEditDialog("servicePersonDatagrid",null,"servicePersonDatagrid_center","开始导出","导出配置",0,_callbackExceptServicePerson);
}
function _callbackExceptServicePerson(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"维修人员业绩统计"+getSystemTime());
}
