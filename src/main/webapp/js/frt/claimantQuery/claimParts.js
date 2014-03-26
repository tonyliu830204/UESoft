$(function (){
	$('#claimantTimeEnd').val(getSystemTime());
	getStartTime($('#claimantTimeBegin'));
	//findCarLicenseFormat("frtWorkOrderClaimPartsQueryCarId");
	//索赔综合查询->索理赔配件查询
	$frtWorkOrderClaimPartsDatagrid = $('#frtWorkOrderClaimPartsDatagrid');
	
	$frtWorkOrderClaimPartsDatagrid.datagrid({
		url : 'frtWorkOrderAction!datagridFrtWorkOrderClaimParts.action',
		fit : true,
		fitColumns : true,
		pagination : true,
		singleSelect : true,
		rownumbers : true,
		columns : [[
			{field:'partsCode',title:'配件代码',width:60,sortable:true},
			{field:'partsName',title:'配件名称',width:100,sortable:true},
			{field:'partsCount',title:'数量',width:80,sortable:true},
			{field:'claimantAmount',title:'索赔金额',width:100,sortable:true},
			{field:'claimantTaxCost',title:'索赔成本(含税)',width:90,sortable:true},
			{field:'claimantNoTaxCost',title:'索赔成本(未税)',width:90,sortable:true},
			{field:'depotLocation',title:'库位',width:60,sortable:true}
		]]
	});
	
});