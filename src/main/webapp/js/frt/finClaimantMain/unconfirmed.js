$(function (){
		$('#unfinished_claimantmTimeEnd').val(getSystemTime());
		loadPreClrTime1($('#unfinished_claimantmTimeBegin'));
		//索赔结算单->未确认索赔工单
		$finClaimantMainUnfinishedDatagrid = $('#finClaimantMainUnfinishedDatagrid');
		
		$finClaimantMainUnfinishedDatagrid.datagrid({
			url : 'finClaimantMainAction!findIdeaByStatus.action',
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			fit : true,
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{field:'preclrId',title:'结算单号',width:110,sortable:false},
	  			{field:'carLicense',title:'车牌照',width:60,sortable:false},
	  			{field:'preclrTime',title:'结算时间',width:110,sortable:false},
	  			{field:'relcampId',title:'索赔厂商',width:110,sortable:false,hidden:true},
	  			{field:'relcampName',title:'索赔厂商',width:110,sortable:false},
	  			{field:'receptionId',title:'维修工单编号',width:110,sortable:false},
			    {field:'carVin',title:'VIN号',width:80,sortable:false},
			    {field:'customName',title:'客户名称',width:60,sortable:false},
			    {field:'receptionDistance',title:'里程数',width:60,sortable:false},
			    {field:'stfName',title:'接待员',width:60,sortable:false},
	  			{field:'claimantmPartsAmount',title:'材料费用合计',width:80,sortable:false,hidden:true},
	  			{field:'claimantmTimeAmount',title:'工时费用合计',width:80,sortable:false,hidden:true},
	  			{field:'ClaimantMainCost',title:'其他费用合计',width:80,sortable:false,hidden:true},
	  			{field:'claimantmAmount',title:'费用总合计',width:70,sortable:false,hidden:true},
	  			{field:'claimantmNoTaxCost',title:'成本费用(未税)',width:70,sortable:false,hidden:true},
	  			{field:'claimantmTaxCost',title:'费用总合计(含税)',width:70,sortable:false,hidden:true}
			]],
			onDblClickRow : function (rowIndex, rowData){
					loadPreClearing('finClaimantMainUnfinishedDatagrid',null);
			 }
		});
	});
	var uquery=function (){
		$finClaimantMainUnfinishedDatagrid.datagrid('load', serializeObject($('#unfinishedForm')));
	}