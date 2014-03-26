$(function (){
	$('#preclrTimeEnd').val(getSystemTime());
	getStartDate($('#preclrTimeBegin'));
 	$('#costAnalyseDatagrid').datagrid({
 		url : 'manageInstanceAction!findCostAanalyse.action',
 		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		showFooter:true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'cbrdName',
			title : '车品牌',
			width : 60,
			sortable : true
		}, {
			field : 'receptionCount',
			title : '接车台次',
			width : 60,
			sortable : true
		}, {
			field : 'preclrCount',
			title : '结算台次',
			width : 60,
			sortable : true
		}, {
			field : 'preclrSumAmount',
			title : '结算金额',                                                                                   
			width : 60,                                                                                       
			sortable : true                                                                                   
		}, {                                                                                                  
			field : 'preclrNoTaxAmount',                                                                                 
			title : '结算成本(未税)',                                                                                   
			width : 90,
			sortable : true
		}, {                                                  
			field : 'preclrTaxAmount',                        
			title : '结算成本(含税)',                               
			width : 90,                                       
			sortable : true                                   
		}, {                                                  
			field : 'claimantCount',
			title : '索赔台次',
			width : 60,
			sortable : true
		}, {
			field : 'claimantSumAmount',
			title : '索赔金额',
			width : 60,
			sortable : true
		}, {
			field : 'claimantNoTaxAmount',
			title : '索赔成本(未税)',
			width : 90,
			sortable : true
		}, {
			field : 'claimantTaxAmount',
			title : '索赔成本(含税)',
			width : 90,
			sortable : true
		}, {                                                                                                  
			field : 'unPreclrNoTaxAmount',                                                                                 
			title : '未结算成本(未税)',                                                                                   
			width : 90,
			sortable : true
		}, {                                                  
			field : 'unPreclrTaxAmount',                        
			title : '未结算成本(含税)',                               
			width : 90,                                       
			sortable : true                                   
		}]]
 	});
});		

var _query = function (){
	$('#costAnalyseDatagrid').datagrid('load', serializeObject($('#costAnalyseQueryForm')));
}

var _clear = function (){
	$('#costAnalyseQueryForm').form('clear');
}
function _except(){
	showEditDialog("costAnalyseDatagrid",null,"costAnalyseDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"成本分析"+getSystemTime());
}