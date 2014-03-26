var staticFlag = false;
var formatLocalData=function(data){
	var string=JSON.stringify(data);
	string=string.replace(/"/g,'_');
	return string;
}
function LoadOk() {
	if (document.readyState == "complete") {
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame() {
	runs();
}
setTimeout("LoadOk();", 200);
function runs() {
	$('#balanceRateAnalyse').datagrid({
		url : 'combineAnalyseAction!findBalanceRateAnalyse.action',
		fit : true,
		fitColumns : true,
		pagination : true,
		singleSelect : true,
		rownumbers : true,
		showFooter : true,
		columns : [ [ {
			field : 'enterpriseId',
			title : '企业序号',
			width : 100,
			hidden : true
		}, {
			field : 'enterpriseName',
			title : '企业名称',
			width : 120,
			sortable : false
		}, {
			field : 'preclrTimeAmount',
			title : '结算工时费用 ',
			width : 80,
			sortable : false
		}, {
			field : 'preclrPartsAmount',
			title : '结算材料费用',
			width : 80,
			sortable : false
		}, {
			field : 'preclrOtherAmount',
			title : '结算其他费用',
			width : 80,
			sortable : false
		}, {
			field : 'preclrTaxCost',
			title : '结算含税成本 ',
			width : 80,
			sortable : false
		}, {
			field : 'preclrNoTaxCost',
			title : '结算未税成本 ',
			width : 80,
			sortable : false
		}, {
			field : 'preclrSumAmount',
			title : '结算总金额 ',
			width : 80,
			sortable : false
		}, {
			field : 'partsCompareTime',
			title : '料工比',
			width : 60,
			sortable : false
		} ] ],
		onLoadSuccess : function(data) {
			if (staticFlag == true) {
				analyseLoader('analyseLoader', 'poleMapImg');
				var stringData = formatLocalData(data);
				if (stringData.length > 10000) {
					var options = $('#balanceRateAnalyse').datagrid('options');
					document.getElementById("poleMapImg").innerHTML = "<img onload=\"analyseLoaderHidden('analyseLoader','poleMapImg');\" " +
							"src=\"combineAnalyseAction!findBalanceRateAnalysePoleMap.action?page="
							+ options.pageNumber+ "&rows="
							+ options.pageSize+ "&"+ $('#balanceRateAnalyseQueryForm').serialize() + " \"/>";
				} else {
					document.getElementById("poleMapImg").innerHTML = "<img onload=\"analyseLoaderHidden('analyseLoader','poleMapImg');\" " +
							"src=\"combineAnalyseAction!findBalanceRateAnalysePoleMap.action?rowsData="
							+ stringData+ "&"+ $('#balanceRateAnalyseQueryForm').serialize() + " \"/>";
				}
			}
		}
	});
}
// 查询
function queryBalanceRateAnalyse() {
	if ($('#balanceRateAnalyseQueryForm').form('validate')) {
		var obj=startQuery();
		staticFlag = true;
		$('#balanceRateAnalyse').datagrid('load',
				serializeObject($('#balanceRateAnalyseQueryForm')));
		endQuery(obj);
	} else {
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
// 清空
function _clear() {
	$('#balanceRateAnalyseQueryForm').form('clear');
}
// Excel导出
function _exceptBalanceRateAnalyse() {
	showEditDialog("balanceRateAnalyse", null, "balanceRateAnalyse_center",
			"开始导出", "导出配置", 0, _callbackBalanceRateAnalyse);
}
function _callbackBalanceRateAnalyse(parentId, fieldNames) {
	exportEsuyUIExcelFile(parentId, fieldNames, "结算费用分析" + getSystemTime());
}