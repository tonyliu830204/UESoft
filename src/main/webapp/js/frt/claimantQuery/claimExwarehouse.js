$(function (){
	var $frtWorkOrderclaimExwarehouseDatagrid = $('#frtWorkOrderclaimExwarehouseDatagrid');
	
	$frtWorkOrderclaimExwarehouseDatagrid.treegrid({
		url : 'frtWorkOrderAction!datagridFrtWorkOrderclaimExwarehouse.action',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fit : true,
		fitColumns : false,
		loadMsg : "数据加载中，请稍后……",
		idField : 'receptionId',
		treeField : 'stomId',
		frozenColumns : [[
			{field:'_parentId',title:'编号',width:80,hidden : true},
  			{field:'receptionId',title:'维修工单号',width:120,sortable:true},
  			{field:'stomId',title:'出仓单号',width:160,sortable:true},
  			{field:'stomTime',title:'开单日期',width:122,sortable:true},
  			{field:'carLicense',title:'车牌照号',width:60,sortable:true}
  		]],
  		columns : [[
  			{field:'carVin',title:'VIN号',width:140,sortable:true},
  			{field:'carMotorId',title:'发动机号',width:100,sortable:true},
  			{field:'cbrdName',title:'车品牌',width:60,sortable:true},
  			{field:'ctypeName',title:'车型号',width:60,sortable:true},
  			{field:'receptionDistance',title:'里程数',width:60,sortable:true},
  			{field:'carFstInsuranceDate',title:'保修起始日期',width:122,sortable:true},
  			{field:'customName',title:'客户名称',width:60,sortable:true},
  			{field:'customAddr',title:'地址',width:100,sortable:true},
  			{field:'customTel',title:'联系电话',width:100,sortable:true},
  			{field:'stfName',title:'接待人员',width:60,sortable:true},
  			{field:'depotCount',title:'出库数量',width:120,sortable:true},
  			{field:'partsTaxCost',title:'配件成本(含税)',width:90,sortable:true},
  			{field:'partsNoTaxCost',title:'配件成本(未税)',width:90,sortable:true},
  			{field:'costTaxAmount',title:'成本合计(含税)',width:90,sortable:true},
  			{field:'costNoTaxAmount',title:'成本合计(未税)',width:90,sortable:true},
  			{field:'partsId',title:'配件编码',width:60,sortable:true},
  			{field:'partsId2',title:'编码二',width:60,sortable:true},
  			{field:'partsName',title:'配件名称',width:60,sortable:true},
  			{field:'property',title:'属性',width:60,sortable:true}
  		]],
  		onloadSuccess: function (){
			//delete $(this).treegrid('options').queryParams['id'];
		},
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
				var url = 'frtWorkOrderAction!datagridFrtWorkOrderclaimExwarehouseByStomId.action?'+$('#frtWorkOrderclaimExwarehouseQueryForm').serialize()+'&receptionId='+rowData.receptionId;
				$('#frtWorkOrderclaimExwarehouseDatagrid').treegrid("options").url = url;
				return true;
		}
	});
});