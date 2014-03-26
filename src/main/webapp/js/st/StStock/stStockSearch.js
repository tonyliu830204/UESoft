$(function(){
	var $StStockTable = $('#StStockTable');
	loadPartsDynamicTable($StStockTable);
	$("#stockType").combobox('setValue', STOCKTYPE2);
	$("#typeWay").combobox('setValue', STOCKCLASSIFY1);

	$("#btn_search").click( function () {
		reLoadTreeGrid('StStockTable', 'stockTableSearchForm', 'stStockAction!getStockSummary.action', false, false)
    });

	$("#btn_cancel").click( function () { 
	    $('#stockTableSearchForm').form('clear');
	    $("#stockType").combobox('setValue', STOCKTYPE2);
    	$("#typeWay").combobox('setValue', STOCKCLASSIFY1);
    	reLoadTreeGrid('StStockTable', 'stockTableSearchForm', 'stStockAction!getStockSummary.action', true, false)
	});
	$("#btn_export").click( function () { 
		exception();
	});
	
});

function loadPartsDynamicTable($StStockTable){
	$StStockTable.treegrid({
    	 url:'stStockAction!getStockSummary.action',
    	 fit : true,
 		 fitColumns : false, 
 		 pagination:true,
	     pageSize : 10,
	     pageList : [ 10, 20],
 		 idField : 'storeId',
 		 treeField : 'storeName',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
            {title : '开始日期',field : 'stomDateStart',width : 10, hidden : true},
            {title : '结束日期 ',field : 'stomDateEnd',width : 10, hidden : true},
            {title : '配件仓库',field : 'storeId',width : 120, hidden : true},
            {title : '配件型号',field : 'partsModelId',width : 120, hidden : true},
	        {title : '配件仓库/配件品牌',field : 'storeName',width : 120},
	        {title : '配件型号名称',field : 'partsModelName',width : 120},
	        {title : '配件单号',field : 'partsId',width : 120},
	        {title : '配件名称',field : 'partsName',width : 120},
	        {title : '上期库存量',field : 'priorCount',width : 170},
	        {title : '上期库存销售金额',field : 'priorSellAmount',width : 210},
	        {title : '上期库存成本金额',field : 'priorCostAmount',width : 210},
	        {title : '本期入库库存量',field : 'currentInCount',width : 170},
	        {title : '本期入库库存销售金额',field : 'currentInSellAmount',width : 230},
	        {title : '本期入库库存成本金额',field : 'currentInCostAmount',width : 230},
	        {title : '本期工单出库库存量',field : 'currentOutCount',width : 210},
	        {title : '本期工单出库库存销售金额',field : 'currentOutSellAmount',width : 240},
	        {title : '本期工单出库库存成本金额',field : 'currentOutCostAmount',width : 240},
	        {title : '本期销售单出库库存量',field : 'currentOutCount1',width : 230},
	        {title : '本期销售单出库库存销售金额',field : 'currentOutSellAmount1',width : 240},
	        {title : '本期销售单出库库存成本金额',field : 'currentOutCostAmount1',width : 240},
	        {title : '本期整车加装出库库存量',field : 'currentOutCount2',width : 170},
	        {title : '本期整车加装出库库存销售金额',field : 'currentOutSellAmount2',width : 240},
	        {title : '本期整车加装出库库存成本金额',field : 'currentOutCostAmount2',width : 240},
	        {title : '本期退货库存量',field : 'currentCancelCount',width : 170},
	        {title : '本期退货库存销售金额',field : 'currentCancelSellAmount',width : 230},
	        {title : '本期退货库存成本金额',field : 'currentCancelCostAmount',width : 230},
	        {title : '本期工单退料库存量',field : 'currentMaterialCount',width : 210},
	        {title : '本期工单退料存销售金额',field : 'currentMaterialSellAmount',width : 230},
	        {title : '本期工单退料存成本金额',field : 'currentMaterialCostAmount',width : 230},
	        {title : '本期销售单退料库存量',field : 'currentMaterialCount1',width : 200},
	        {title : '本期销售单退料存销售金额',field : 'currentMaterialSellAmount1',width : 230},
	        {title : '本期销售单退料存成本金额',field : 'currentMaterialCostAmount1',width : 230},
	        {title : '本期库存量',field : 'currentCheckCount',width : 200},
	        {title : '本期库存销售金额',field : 'currentCheckSellAmount',width : 230},
	        {title : '本期库存成本金额',field : 'currentCheckCostAmount',width : 230},
	        {title : '当前库存量',field : 'surplusCount',width : 200},
	        {title : '当前库存销售金额',field : 'surplusSellAmount',width : 230},
	        {title : '当前库存成本金额',field : 'surplusCostAmount',width : 230}
		 ]],
		 onBeforeLoad: function (param) {
		    jsProbar();
		 },
		 onLoadSuccess:function(data){
			 jsCloseProbar();
			 var root = $StStockTable.treegrid("getRoot");
			 $("#stomDateStart").val(root.stomDateStart);
			 $("#stomDateEnd").val(root.stomDateEnd);
			 initTreeGridPager('StStockTable', 'stStockAction!getStockSummary.action', false);
		 },
		 onLoadError: function () {
			 jsCloseProbar();
		 },
		 onBeforeExpand:function(rowData){
			 //动态设置展开查询的url
			 var url = 'stStockAction!getStockDetails.action?storeModelId='+rowData.storeId+'&stomDateStart='+rowData.stomDateStart+'&stomDateEnd='+rowData.stomDateEnd+'&stockType='+$("#stockType").combobox('getValue')+'&storeId='+$("#storeId").combobox('getValue')+'&typeWay='+$("#typeWay").combobox('getValue');
			 $StStockTable.treegrid("options").url = url;
			 return true;
		 }
	});
}


function exception(){
	showEditDialog("StStockTable",null,"StStockTableDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"进销存报表"+getSystemTime());
}