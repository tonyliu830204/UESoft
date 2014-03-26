$(function(){
	$('#claimantQueryTabs').tabs({   
		onSelect:function(title){
			if(title =='索赔-配件查询'){
				unbindAllButton();
				$("#_search").bind("click",queryFrtWorkOrderClaimParts);  
				$("#_clear").bind("click",clearFrtWorkOrderClaimParts);
				$("#_export").bind("click",_exportFrtWorkOrderClaimParts);
			}else if(title =='索赔-出库查询'){
				unbindAllButton();  
				$("#_search").bind("click",queryFrtWorkOrderclaimExwarehouse);  
				$("#_clear").bind("click",clearFrtWorkOrderclaimExwarehouse);
				$("#_export").bind("click",_exportFrtWorkOrderclaimExwarehouse);
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
function queryFrtWorkOrderClaimParts(){
	$('#frtWorkOrderClaimPartsDatagrid').datagrid('unselectAll');
	if($('#frtWorkOrderClaimPartsQueryForm').form('validate')){
		$('#frtWorkOrderClaimPartsDatagrid').datagrid('load', serializeObject($('#frtWorkOrderClaimPartsQueryForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearFrtWorkOrderClaimParts(){
	$('#frtWorkOrderClaimPartsQueryForm').form('clear');
	$('#frtWorkOrderClaimPartsDatagrid').datagrid('load', serializeObject($('#frtWorkOrderClaimPartsQueryForm')));
}
function queryFrtWorkOrderclaimExwarehouse(){
	if($('#frtWorkOrderclaimExwarehouseQueryForm').form('validate')){
		var url="frtWorkOrderAction!datagridFrtWorkOrderclaimExwarehouse.action?";
		url+=$('#frtWorkOrderclaimExwarehouseQueryForm').serialize();
		$('#frtWorkOrderclaimExwarehouseDatagrid').treegrid({
			url:url
		});
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearFrtWorkOrderclaimExwarehouse(){
	$('#frtWorkOrderclaimExwarehouseQueryForm').form('clear');
}

function _exportFrtWorkOrderClaimParts(){
	showEditDialog("frtWorkOrderClaimPartsDatagrid",null,"frtWorkOrderClaimPartsDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderClaimParts);
}
function _callbackExceptFrtWorkOrderClaimParts(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"索赔-配件查询"+getSystemTime());
}

function _exportFrtWorkOrderclaimExwarehouse(){
	showEditDialog("frtWorkOrderclaimExwarehouseDatagrid",null,"frtWorkOrderclaimExwarehouseDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderclaimExwarehouse);
}
function _callbackExceptFrtWorkOrderclaimExwarehouse(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"索赔-出库查询"+getSystemTime());
}