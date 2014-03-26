$(function() {
	$('#claimantmTimeEnd').val(getSystemTime());
	loadPreClrTime1($('#claimantmTimeBegin'));
	findCarLicenseFormat("finClaimant_summary_carId");
	//索赔结算单->索理赔账单汇总
	$finClaimantMainSummaryDatagrid = $('#finClaimantMainSummaryDatagrid');
	
	$finClaimantMainSummaryDatagrid.datagrid({
		url : 'finClaimantMainAction!datagridFinClaimantMain.action',
		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : false,
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [[
		    {field:'claimantmId',title:'索赔单号',width:110,sortable:true},
		    {field:'preclrId',title:'结算单号',width:110,sortable:true},
		    {field:'receptionId',title:'维修工单编号',width:110,sortable:true},
		    {field:'carLicense',title:'车牌照',width:60,sortable:true},
		    {field:'carVin',title:'VIN号',width:140,sortable:true},
		    {field:'carMotorId',title:'底盘号',width:80,sortable:true},
		    {field:'customName',title:'客户名称',width:60,sortable:true}
		]],
		columns : [[
		    {field:'claimantmTime',title:'结算时间',width:136,sortable:true},
  			{field:'claimantmInvoiceType',title:'票据类型',width:100,sortable:true,formatter: function(value,row,index){
				return row.claimantmInvoiceTypeName;
			}},
  			{field:'claimantmInvoiceTime',title:'开票时间',width:136,sortable:true},
  			{field:'claimantmInvoiceNo',title:'发票编号',width:80,sortable:true},
  			{field:'claimantmClrStfId',title:'结算员',width:70,sortable:true,formatter: function(value,row,index){
	  				return row.claimantmClrStfName;
	  		}},
	  		{field:'moneyStatusName',title:'索赔结算状态',width:70,sortable:true},
  			{field:'claimantmPartsAmount',title:'材料费用合计',width:80,sortable:true},
  			{field:'claimantmTimeAmount',title:'工时费用合计',width:80,sortable:true},
  			{field:'claimantmOtherAmount',title:'其他费用合计',width:80,sortable:true},
  			{field:'claimantmManagementFee',title:'管理费用',width:80,sortable:true},
  			{field:'claimantmAmount',title:'费用总合计',width:70,sortable:true},
  			{field:'claimantmNoTaxCost',title:'未税成本',width:70,sortable:true},
  			{field:'claimantmTaxCost',title:'含税成本',width:70,sortable:true},
  			{field:'claimantmStatus',title:'审核状态',width:60,sortable:true,formatter: function(value,row,index){
				return row.claimantmStatusName;
				
			}},
  			{field:'claimantmCheckStfId',title:'审核人员',width:80,sortable:true,formatter: function(value,row,index){
	  				return row.claimantmCheckStfName;
	  			}},
  			{field:'claimantmRemark',title:'备注',width:130},
  			{field:'claimantmToMoney',title:'转收银',hidden:true}
  			
  		]],
  		onDblClickRow : function (rowIndex, data){
  			staticFinClaimantMainParts=null;
			staticFinClaimantMainItems=null;
			staticFinClaimantMainExpenseSituationOtherExpense=null;
  			staticReceptionId="";
  			$('#finClaimantMainAddForm').form('clear');
			$('#finClaimantMainExpenseSituationForm').form('clear');
  			$('#finClaimantMainTabs').tabs('select', '索理赔帐单明细');
			$('#finClaimantMainAddForm').form('load', data);
			$('#finClaimantMainExpenseSituationForm').form('load',data);
			$('#finClaimantMainPartsDatagrid').datagrid({
				url : 'finClaimantMainAction!findPartsByFcmId.action?claimantmId=' + data.claimantmId
			});
			$('#finClaimantMainItemDatagrid').datagrid({
				url : 'finClaimantMainAction!findItemByFcmId.action?claimantmId=' + data.claimantmId
			});
			$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
				url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=' + data.claimantmId
			});
			$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
				onLoadSuccess : function (data){
					staticFinClaimantMainExpenseSituationOtherExpense=data;
				}
			});
			$('#finClaimantMainPartsDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFinClaimantMainParts=data;
				}
			});
			$('#finClaimantMainItemDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFinClaimantMainItems=data;
				}
			});
		}
	});
});
var loadPreClearing=function(id,id2){
	$('#finClaimantMainAddForm').form('clear');
	$('#finClaimantMain_detail_claimantmTime').datetimebox('setValue','{now}');
	$('#finClaimantMain_detail_claimantmInvoiceTime').datetimebox('setValue','{now}');
	 $('#finClaimantMain_detail_claimantmClrStfId').combobox('select',parame1);
	var rowData = $('#'+id+'').datagrid('getSelected');
	if(id2==null||id2.length==0){
		$('#finClaimantMainTabs').tabs('select', '索理赔帐单明细');
	}else{
		id2.dialog('close');
	}
	$('#finClaimantMainAddForm').form('load', rowData);
	staticReceptionId=rowData.receptionId;
	$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
		url : 'finClaimantMainAction!findCostByReceptionId.action?receptionId=' +rowData.receptionId
	});
	$('#finClaimantMainPartsDatagrid').datagrid({
		url : 'finClaimantMainAction!findPartsByReceptionId.action?receptionId='+rowData.receptionId
	});
	$('#finClaimantMainItemDatagrid').datagrid({
		url : 'finClaimantMainAction!findItemByReceptionId.action?receptionId=' +rowData.receptionId+'&flag=true'
	});
	$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
		onLoadSuccess : function (data){
			staticFinClaimantMainExpenseSituationOtherExpense=data;
			$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('enable');
			$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('enable');
			$('#finClaimantMainExpenseSituationOtherExpense_accept').linkbutton('enable');
		}
	});
	$('#finClaimantMainItemDatagrid').datagrid({
		onLoadSuccess : function (data){
			staticFinClaimantMainItems=data;
			$('#finClaimantMain_item_add').linkbutton('enable');
			$('#finClaimantMain_item_remove').linkbutton('enable');
			$('#finClaimantMain_item_accept').linkbutton('enable');	
			$('#finClaimantMain_item_diy').linkbutton('enable');		
		}
	});
	$('#finClaimantMainPartsDatagrid').datagrid({
		onLoadSuccess : function (data){
			staticFinClaimantMainParts=data;
			$('#finClaimantMain_parts_add').linkbutton('enable');
			$('#finClaimantMain_parts_remove').linkbutton('enable');
			$('#finClaimantMain_parts_accept').linkbutton('enable');			
		}
	});
}