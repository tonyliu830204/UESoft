$(function (){
	$('#s_ssc_preclrDateEnd').val(getSystemTime());
	loadPreClrTime($('#s_ssc_preclrDateStart'));
	st_sell_chargeTableDategrid = $('#st_sell_chargeTable');
	st_sell_chargeTableDategrid.datagrid({
		url : 'ReceivablesAction_loadStSellCharge.action',
		fit : true,
		fitColumns : true,
		sortName:'mrId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		columns : [[{
			field : 'chargeId',
			title : '应收款编号',
			width : 75,
			hidden : true
		},{
			field : 'customName',
			title : '客户名称',
			width : 75
		},{
			field : 'sellmmId',
			title : '销售单号',
			width : 75
		},{
			field : 'sellmmDate',
			title : '销售日期',
			width : 75
		},{
			field : 'carBrand',
			title : '车辆品牌',
			width : 75
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 75
		},{
			field : 'carVin',
			title : 'VIN号',
			width : 75
		},{
			field : 'sellmmSumAmount',
			title : '销售金额',
			width : 75
		},{
			field : 'preclrSumAmount',
			title : '结算金额',
			width : 75
		},{
			field : 'paidAmount',
			title : '应收款',
			width : 75
		},{
			field : 'preclrNo',
			title : '发票编号',
			width : 75,
			hidden:true
		},{
			field : 'preFlg',
			title : '结算方式',
			width : 75,
			hidden:true
		},{
			field : 'sdfd',
			title : '允许账龄',
			width : 75
		},{
			field : 'wqr',
			title : '应收款账龄',
			width : 75,
			hidden:true
		},{
			field : 'ere',
			title : '打印',
			width : 75
		}]]
	});
});