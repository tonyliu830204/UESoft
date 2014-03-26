$(function (){
	$('#s_fcr_claimantmTimeEnd').val(getSystemTime());
	loadPreClrTime($('#s_fcr_claimantmTimeStart'));
	fin_claims_receivablesTableDategrid = $('#fin_claims_receivablesTable');
	fin_claims_receivablesTableDategrid.datagrid({
		url : 'ReceivablesAction_loadFinClaimsReceivables.action',
		fit : true,
		fitColumns : true,
		sortName:'mrId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'relcampName',
			title : '索赔厂商',
			width : 75
		},{
			field : 'receptionId',
			title : '结算单号',
			width : 75
		},{
			field : 'customName',
			title : '客户名称',
			width : 75
		},{
			field : 'receLicense',
			title : '车牌照',
			width : 75
		},{
			field : 'claimantmId',
			title : '索赔单号',
			width : 75
		},{
			field : 'claimantmTime',
			title : '结算日期',
			width : 75
		},{
			field : 'claimantmAmount',
			title : '结算金额',
			width : 75
		},{
			field : 'crReceivables',
			title : '累计收款',
			width : 75
		},{
			field : 'preclrSumAmount',
			title : '应收款',
			width : 75
		},{
			field : 'claimantmInvoiceNo',
			title : '发票编号',
			width : 75
		},{
			field : 'preclrNo',
			title : '接车分布',
			width : 75
		},{
			field : 'stfName',
			title : '接待员',
			width : 75
		},{
			field : 'reptName',
			title : '维修类别',
			width : 75
		},{
			field : 'reptName1',
			title : '允许账龄',
			width : 75
		},{
			field : 'mrReceivables',
			title : '应收款账龄',
			width : 75
		},{
			field : 'mrReceivables1',
			title : '打印',
			width : 75
		},{
			field : 'carVin',
			title : 'VIN号',
			width : 75
		},{
			field : 'cbrdName',
			title : '车辆品牌',
			width : 75
		},{
			field : 'resvRealTime',
			title : '进厂时间',
			width : 75
		}]]
	});
});