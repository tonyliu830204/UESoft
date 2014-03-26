$(function (){
 	$('#ssp_StUsePercharge_repcollTimeEnd').val(getSystemTime());
	loadPercharge($('#ssp_StUsePercharge_repcollTimeStart'));
	datagridStUsePerchargeTable = $('#StUsePerchargeTable');
	datagridStUsePerchargeTable.datagrid({
		url : 'StSellPerchargeAction_loadStUsePercharge.action',
		fit : true,
		fitColumns : true,
		sortName:'receptionId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [[{
			align : 'center',
			field : 'resvRealTime',
			title : '进厂日期',
			width : 120
		},{
			field : 'receptionId',
			title : '工单号',
			width : 100
		},{
			field : 'repcollId',
			title : '结算单号',
			width : 100
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100
		},{
			field : 'customName',
			title : '客户名称',
			width : 100
		},{
			field : 'repcollTime',
			title : '收款日期',
			width : 100
		},{
			field : 'repcollAmount',
			title : '结算金额',
			width : 100
		},{
			field : 'discountAmont',
			title : '抵扣金额',
			width : 120
		}]]
	});
});	