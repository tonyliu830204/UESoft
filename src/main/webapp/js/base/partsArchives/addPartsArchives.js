function LoadOk() {
	if (document.readyState == "complete") {
		if(marker!="no"){
			tag=true;
		}
		var rowData=$('#partsArchivesDatagrid').datagrid('getSelected');
		if(rowData!=null&&rowData.fitPtype != null&&rowData.fitPtype.length>0){
			if(tag==true){
		 		setComboboxValues('partsArchives_add_fitPtype',rowData.fitPtype);
			}
		}
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
	var flag1=true;
if(flag!=null&&flag=="true"){
	var rowData=$('#partsArchivesDatagrid').datagrid('getSelected');
	$("#partsArchivesAddForm").form('load',rowData);
	if(!(marker!=null&&marker=="true")){
		$('#appendTranslaction').linkbutton({disabled:true});
		$('#cancelTranslaction').linkbutton({disabled:true});
	}
	$('#partsArchives_add_stockUpper').numberbox({disabled:flag1});
	$('#partsArchives_add_stockLower').numberbox({disabled:flag1});
	$('#partsArchives_add_partsSpecialPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsRepairPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsSellPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsClaimantPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsPointPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsLatestTaxprice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsLatestNotaxprice').numberbox({disabled:flag1});
}else{
	$('#appendTranslaction').linkbutton({disabled:flag1});
	$('#cancelTranslaction').linkbutton({disabled:flag1});
	$('#partsArchives_add_stockUpper').numberbox({disabled:flag1});
	$('#partsArchives_add_stockLower').numberbox({disabled:flag1});
	$('#partsArchives_add_partsSpecialPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsRepairPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsSellPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsClaimantPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsPointPrice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsLatestTaxprice').numberbox({disabled:flag1});
	$('#partsArchives_add_partsLatestNotaxprice').numberbox({disabled:flag1});
}
$(function (){
	$('#partsArchives_add_partsName').validatebox().on('keyup', function (){
		var value = makePy($('#partsArchives_add_partsName').val());
		$('#partsArchives_add_partsSimpleId').val(value);
	});
});
//禁用与启用调价信息
var appendOrCancelTranslation=function (flag){
	if(flag==false){
		document.getElementById('translationFlag').value=true;
	}else if(flag==true){
		document.getElementById('translationFlag').value=false;
	}
	$('#partsArchives_add_stockUpper').numberbox({disabled:flag});
	$('#partsArchives_add_stockLower').numberbox({disabled:flag});
	$('#partsArchives_add_partsSpecialPrice').numberbox({disabled:flag});
	$('#partsArchives_add_partsRepairPrice').numberbox({disabled:flag});
	$('#partsArchives_add_partsSellPrice').numberbox({disabled:flag});
	$('#partsArchives_add_partsClaimantPrice').numberbox({disabled:flag});
	$('#partsArchives_add_partsPointPrice').numberbox({disabled:flag});
	$('#partsArchives_add_partsLatestTaxprice').numberbox({disabled:flag});
	$('#partsArchives_add_partsLatestNotaxprice').numberbox({disabled:flag});
}