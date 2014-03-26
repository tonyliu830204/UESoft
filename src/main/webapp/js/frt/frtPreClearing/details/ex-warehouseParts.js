$(function(){
	//出料
	var url="";
	if(receptionId){
		url='${pageContext.request.contextPath}/frtWorkshopManagerAction!datagridEmerge.action?receptionId='+receptionId;
	}else{
		url='${pageContext.request.contextPath}/frtWorkshopManagerAction!datagridEmerge.action?receptionId=-1';
	}
	var $frtPreClearingExwarehousePartsDatagrid = $('#frtPreClearingExwarehousePartsDatagrid');
	$frtPreClearingExwarehousePartsDatagrid.datagrid({
			url : url,
			singleSelect : true,
			fit : true,
			fitColumns : true,
			rownumbers : true,
			showFooter : true,
			columns : [[
        	{field:'carType',title:'车型',width:60,sortable:true},
        	{field:'partsCode',title:'配件编码',width:60,sortable:true},
        	{field:'partsName',title:'配件名称',width:60,sortable:true},
        	{field:'punitName',title:'单位',width:30,sortable:true},
        	{field:'partsCount',title:'数量',width:30,sortable:true},
        	{field:'partsPrice',title:'单价',width:50,sortable:true},
        	{field:'partsAmount',title:'金额',width:50,sortable:true},
        	{field:'stfName',title:'领料员',width:60,sortable:true},
        	{field:'storageId',title:'单号(出退预)',width:130,sortable:true},
        	{field:'storageTime',title:'时间(出退预)',width:130,sortable:true},
        	{field:'chargeName',title:'收费性质',width:60,sortable:true},
        	{field:'claimsName',title:'索赔性质',width:60,sortable:true}
        ]]
	  	});
})