$(function (){
	$('#preclrTimeEnd').val(getSystemTime());
	getStartDate($('#preclrTimeBegin'));
 	$('#carBalanceUntangleDatagrid').datagrid({
 		url : 'manageInstanceAction!findCarBalanceUntangle.action',
 		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'carLicense',
			title : '车辆牌照',
			width : 60,
			sortable : true
		}, {
			field : 'ctypeName',
			title : '车辆型号',
			width : 60,
			sortable : true
		}, {
			field : 'vipId',
			title : '会员号',
			width : 110,
			sortable : true
		}, {
			field : 'customName',
			title : '客户名称',
			width : 60,
			sortable : true
		}, {
			field : 'customTel1',
			title : '电话号码',
			width : 90,
			sortable : true
		}, {
			field : 'preclrSumAmount',
			title : '结算金额',
			width : 60,
			sortable : true
		}, {
			field : 'receptionCount',
			title : '接车台次',
			width : 60,
			sortable : true
		}, {
			field : 'customIden',
			title : '身份证号',
			width : 140,
			sortable : true
		}, {
			field : 'customBirthday',
			title : '出生日期',
			width : 80,
			sortable : true
		}, {
			field : 'customAddr',
			title : '地址',
			width : 120,
			sortable : true
		},{
			field : 'relcmapName',
			title : '保险公司',
			width : 100,
			sortable : true
		},{
			field : 'lastDistance',
			title : '最近行驶里程',
			width : 80,
			sortable : true
		},{
			field : 'lastInterDate',
			title : '最近进厂时间',
			width : 130,
			sortable : true
		}]]
 	});
});		

var _query = function (){
	$('#carBalanceUntangleDatagrid').datagrid('load', serializeObject($('#carBalanceUntangleQueryForm')));
}

var _clear = function (){
	$('#carBalanceUntangleQueryForm').form('clear');
}
function _except(){
	showEditDialog("carBalanceUntangleDatagrid",null,"carBalanceUntangleDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆结算排行"+getSystemTime());
}