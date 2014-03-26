$(function(){
	/*$('#ptcs_stomDateEnd').val(getSystemTime());
	getStartDate($('#ptcs_stomDateStart'));
	$('#spsrp_stomDateEnd').val(getSystemTime());
	getStartDate($('#spsrp_stomDateStart'));
	$('#sssrgs_stomDateEnd').val(getSystemTime());
	getStartDate($('#sssrgs_stomDateStart'));*/
	$('#dynamic_stomDateEnd').val(getSystemTime());
	getStartDate($('#dynamic_stomDateStart'));
	$('#partsIntegratedQuery_tabs').tabs({   
		onSelect:function(title, index){  
			/*if(index == 0){
				loadStOutAndRtPartsSearchTable();
			}else if(index == 1){
				loadSellPartsAndSellRtPartsTable();		
			}else if(index == 2){
				loadStStorageAndStRtGoodsTable()		
			}else if(index == 3){
				loadPartsDynamicTable();	
			}*/
		  if(index == 0){
			loadPartsDynamicTable();
	         } 
		}
	});
   /* $.ajax({
		type : 'POST',
		url :'PartsTrendsChangeSearchAction_caculateStOutAndRtParts.action',
		data : '',
		dataType : 'json',
		success : function(r){
    			$('#sumItemCount').val(r.sumItemCount);
    			$('#sumAmount').val(r.sumAmount);
    			$('#sumCastAmount').val(r.sumCastAmount);
	   }
    });*/
});

//出库退料查询信息
/*function loadStOutAndRtPartsSearchTable(){
    $('#StOutAndRtPartsSearchTable').datagrid({
    	 url:projectPath+'PartsTrendsChangeSearchAction_loadStOutAndRtPartsInfo.action',
	     fit:true,
	     singleSelect:true,
	     border:false,
	     sortName:'collectId',
		 sortOrder:'asc',
		 rownumbers : true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'collectId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
			{title : '序号',field : 'indexId',width : 80},
			{title : '配件编号',field : 'partsId',width : 80},
			{title : '配件名称',field : 'partsName',width : 80},
			{title : '库位',field : 'partsLibrary',width : 80},
			{title : '单位',field : 'punitName',width : 100},
			{title : '销量',field : 'itemCount',width : 80},
			{title : '销售金额',field : 'amount',width : 100},
			{title : '平均单价',field : 'avagePrice',width : 100},
			{title : '成本合计',field : 'castAmount',width : 80},
			{title : '平均成本',field : 'avageCastAmount',width : 100},
			{title : '成本百分比',field : 'remark2',width : 100},
			{title : '仓库名称',field : 'storeName',width : 100},
			{title : '出库退料日期',field : 'stomDate',width : 80},
			{title : '车辆品牌',field : 'cbrdName',width : 100},
			{title : '车型',field : 'stypeName',width : 100}
	     ]]
	});
}

//销售退料查询
function loadSellPartsAndSellRtPartsTable(){
    $('#SellPartsAndSellRtPartsTable').datagrid({
    	 url:projectPath+'PartsTrendsChangeSearchAction_loadSellPartsAndSellRtParts.action',
	     fit:true,
	     singleSelect:true,
	     border:false,
	     sortName:'collectId',
		 sortOrder:'asc',
		 rownumbers : true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'collectId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
			{title : '消退单号',field : 'strtpmId',width :130},
			{title : '序号',field : 'indexId',width : 80},
			{title : '配件编号',field : 'partsId',width : 80},
			{title : '配件名称',field : 'partsName',width : 80},
			{title : '库位',field : 'partsLibrary',width : 80},
			{title : '单位',field : 'punitName',width : 100},
			{title : '销量',field : 'itemCount',width : 80},
			{title : '销售金额',field : 'amount',width : 100},
			{title : '平均单价',field : 'avagePrice',width : 100},
			{title : '成本合计',field : 'castAmount',width : 80},
			{title : '平均成本',field : 'avageCastAmount',width : 100},
			{title : '成本百分比',field : 'remark2',width : 100},
			{title : '仓库名称',field : 'storeName',width : 100},
			{title : '出库退料日期',field : 'stomDate',width : 80},
			{title : '车辆品牌',field : 'cbrdName',width : 100},
			{title : '车型',field : 'stypeName',width : 100}
		 ]] 
	});
}

//入库退货查询
function loadStStorageAndStRtGoodsTable(){
    $('#StStorageAndStRtGoodsTable').datagrid({
    	 url:projectPath+'PartsTrendsChangeSearchAction_loadStStorageAndStRtGoods.action',
	     fit:true,
	     singleSelect:true,
	     border:false,
 	     sortName:'collectId',
		 sortOrder:'asc',
		 rownumbers : true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'collectId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
            {title : '入退单号',field : 'strtpmId',width :130},
	        {title : '序号',field : 'indexId',width : 80},
	        {title : '配件编号',field : 'partsId',width : 80},
	        {title : '配件名称',field : 'partsName',width : 80},
	        {title : '库位',field : 'partsLibrary',width : 80},
	        {title : '单位',field : 'punitName',width : 100},
	        {title : '数量',field : 'itemCount',width : 80},
	        {title : '金额',field : 'amount',width : 100},
	        {title : '均价',field : 'avagePrice',width : 100},
	        {title : '成本百分比',field : 'castAmount',width : 80},
	        {title : '日期',field : 'stomDate',width : 100},
	        {title : '仓库',field : 'storeName',width : 100},
	        {title : '车辆品牌',field : 'cbrdName',width : 100,hidden:true},
	        {title : '车型',field : 'stypeName',width : 100,hidden:true}
		 ]]
	});
}*/

//库存动态查询
function loadPartsDynamicTable(){
	$('#partsDynamicTable').datagrid({
    	url:projectPath+'PartsTrendsChangeSearchAction_loadPartsDynamicDate.action',
	     fit:true,
	     singleSelect:true,
	     border:false,
	     sortName:'collectId',
		 sortOrder:'asc',
		 rownumbers : true,
		 fitColumns : true,
		 pagination:true,
		 pageSize : 10,
		 pageList : [ 10, 20],
		 idField : 'collectId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[
			{title : '开始日期',field : 'stomDateStart',width : 10, hidden : true},
			{title : '结束日期 ',field : 'stomDateEnd',width : 10, hidden : true},
	        {title : '配件编号',field : 'partsId',width : 80},
	        {title : '配件名称',field : 'partsName',width : 80},
	        {title : '仓库',field : 'storeName',width : 80},
	        {title : '库位',field : 'partsLibrary',width : 80},
	        {title : '配件品牌',field : 'cbrdName',width : 80},
	        {title : '配件型号',field : 'stypeName',width : 80},
	        {title : '上期库存量',field : 'priorPeriodCount',width : 100},
	        {title : '上期库存销售金额',field : 'priorPeriodSellAmount',width : 100},
	        {title : '上期库存成本金额',field : 'priorPeriodCostAmount',width : 100},
	        {title : '本期入库库存量',field : 'currentPeriodInCount',width : 100},
	        {title : '本期入库库存销售金额',field : 'currentPeriodInSellAmount',width : 100},
	        {title : '本期入库库存成本金额',field : 'currentPeriodInCostAmount',width : 100},
	        {title : '本期出库库存量',field : 'currentPeriodOutCount',width : 100},
	        {title : '本期出库库存销售金额',field : 'currentPeriodOutSellAmount',width : 100},
	        {title : '本期出库库存成本金额',field : 'currentPeriodOutCostAmount',width : 100},
	        {title : '本期库存量',field : 'currentCheckCount',width : 100},
	        {title : '本期库存销售金额',field : 'currentCheckSellAmount',width : 100},
	        {title : '本期库存成本金额',field : 'currentCheckCostAmount',width : 100},
	        {title : '当前库存量',field : 'surplusCount',width : 100},
	        {title : '当前库存销售金额',field : 'surplusSellAmount',width : 100},
	        {title : '当前库存成本金额',field : 'surplusCostAmount',width : 100}
		 ]],
		 onLoadSuccess:function(data){
			 var rows = $('#partsDynamicTable').datagrid("getRows");
			 if(rows.length > 0){
				 $("#dynamic_stomDateStart").val(rows[0].stomDateStart);
				 $("#dynamic_stomDateEnd").val(rows[0].stomDateEnd);
			 }
		 }
	});
}

function searchByCondition(){
	 var tabName=$('#partsIntegratedQuery_tabs').tabs('getSelected');
	 var tab = tabName.panel('options').title;
/*	 if(tab=='出库退料查询'){
		 $.ajax({
				type : 'POST',
				url :'PartsTrendsChangeSearchAction_searchStOutAndRtPartsInfoByCondition.action',
				data : 'stomDateStart='+$('#ptcs_stomDateStart').val()+'&stomDateEnd='+$('#ptcs_stomDateEnd').val()+'&cbrdName='
				+$('#ptcs_cbrdName').combobox('getText')+'&stypeName='+$('#ptcs_stypeName').combobox('getText')+'&partsId='
				+$('#ptcs_partsId').val()+'&partsName='+$('#ptcs_partsName').combobox('getText')+'&storeName='+$('#pcts_storeName').combobox('getText'),
				dataType : 'json',
				success : function(r){
				   if(r.total>=0){
				       $('#StOutAndRtPartsSearchTable').datagrid('loadData',r);
				   }else{
					   $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
				   }
			   }
		    });
	 }else if(tab=='销售退料查询'){
		 $.ajax({
				type : 'POST',
				url :'PartsTrendsChangeSearchAction_searchSellPartsAndSellRtPartsByCondition.action',
				data : 'stomDateStart='+$('#spsrp_stomDateStart').val()+'&stomDateEnd='+$('#spsrp_stomDateEnd').val()+'&partsId='
				+$('#spsrp_partsId').val()+'&partsName='+$('#spsrp_partsName').combobox('getText')+'&storeName='+$('#spsrp_storeName').combobox('getText'),
				dataType : 'json',
				success : function(r){
				   if(r.total>=0){
				       $('#SellPartsAndSellRtPartsTable').datagrid('loadData',r);
				   }else{
					   $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
				   }
			   }
		  });
	 }else if(tab=='入库退货查询'){
		 $.ajax({
				type : 'POST',
				url :'PartsTrendsChangeSearchAction_searchStStorageAndStRtGoodsByCondition.action',
				data : 'stomDateStart='+$('#sssrgs_stomDateStart').val()+'&stomDateEnd='+$('#sssrgs_stomDateEnd').val()+'&partsId='
				+$('#sssrgs_partsId').val()+'&partsName='+$('#sssrgs_partsName').combobox('getText')+'&storeName='+$('#sssrgs_storeName').combobox('getText'),
				dataType : 'json',
				success : function(r){
				   if(r.total>=0){
				       $('#StStorageAndStRtGoodsTable').datagrid('loadData',r);
				   }else{
					   $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
				   }
			   }
		  });
	 }else */if(tab=='库存动态查询'){
		 var $StStockTable = $('#partsDynamicTable');
		 var options = $StStockTable.datagrid('options');
		 var obj = serializeObject($('#partsDynamicTableSearchForm'));
		 obj.page = options.pageNumber;
		 obj.rows = options.pageSize;
		 $.ajax({
				type : 'POST',
				url :'PartsTrendsChangeSearchAction_loadPartsDynamicDate.action',
				data : obj ,
				dataType : 'json',
				success : function(r){
				   if(r.total>=0){
					   $StStockTable.datagrid('loadData',r);
				   }else{
					   $.messager.alert('优亿软件提示',r.msg,'warning',function(){});
				   }
			   }
		  });
	 }
}

function clearSearchByCondition(){
	 var tabName=$('#partsIntegratedQuery_tabs').tabs('getSelected');
	 var tab = tabName.panel('options').title;
	/* if(tab=='出库退料查询'){
	    $('#StOutAndRtPartsSearchForm').form('clear');
	    $('#StOutAndRtPartsSearchTable').datagrid('load');
	 }else if(tab=='销售退料查询'){
		$('#SellPartsAndSellRtPartsSearchForm').form('clear');
	    $('#SellPartsAndSellRtPartsTable').datagrid('load');
	 }else if(tab=='入库退货查询'){
		$('#StStorageAndStRtGoodsSearchForm').form('clear');
	    $('#StStorageAndStRtGoodsTable').datagrid('load');
	 }else */if(tab=='库存动态查询'){
		$('#partsDynamicTableSearchForm').form('clear');
		$('#partsDynamicTable').datagrid('load');
    }
}

function exception(){
	showEditDialog("partsDynamicTable",null,"partsDynamicTableDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"库存动态查询"+getSystemTime());
}