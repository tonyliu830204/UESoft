$(function (){
	$('#s_fmr_preclrTimeEnd').val(getSystemTime());
	loadPreClrTime($('#s_fmr_preclrTimeStart'));
    fin_maintenance_receivablesTableDategrid = $('#fin_maintenance_receivablesTable');
    fin_maintenance_receivablesTableDategrid.datagrid({
		url : 'ReceivablesAction_loadFinMaintenanceReceivables.action',
		fit : true,
		fitColumns : true,
		sortName:'mrId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'mrId',
			title : '应收款编号',
			width : 75,
			hidden : true
		},{
			field : 'customName',
			title : '客户名称',
			width : 75
		},{
			field : 'receptionId',
			title : '工单号',
			width : 75
		},{
			field : 'resvRealTime',
			title : '进厂日期',
			width : 75
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 75
		},{
			field : 'carBrand',
			title : '车品牌',
			width : 75
		},{
			field : 'carVin',
			title : 'VIN号',
			width : 75
		},{
			field : 'preclrId',
			title : '结算单号',
			width : 75
		},{
			field : 'preclrTime',
			title : '结算日期',
			width : 75
		},{
			field : 'preclrSumAmount',
			title : '结算金额',
			width : 75
		},{
			field : 'preclrNo',
			title : '发票编号',
			width : 75
		},{
			field : 'stfId',
			title : '接待员Id',
			width : 75,
			hidden:true
		},{
			field : 'stfName',
			title : '接待员',
			width : 75
		},{
			field : 'reptName',
			title : '维修类别',
			width : 75
		},{
			field : 'mrReceivables',
			title : '累计收款',
			width : 75
		}]]
	});
});