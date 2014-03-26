$(function(){
		 $('#endTime').val(getSystemTime());
		 getStartDate($('#startTime'));
	    /**消退单查询datagrid*/
		$('#StSellOrder_StRtSellOrderSearchTable').datagrid({
			 url:'',
			 pagination:true,
		     fit:true,
		     sortName:'changePriceId',
		     sortOrder:'asc',
		     pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true, 
			 idField : 'partsId',
			 singleSelect:true,
			 loadMsg : "数据加载中，请稍后……",
			 columns : [ [ {title : '配件编号',field : 'partsId',width : 50,sortable:true
				}, {title : '配件名称',field : 'partsName',width : 50,sortable:true
				}, {title : '库位',field : 'partsLibrary',width : 30,sortable:true
				}, {title : '单位',field : 'punitName',width : 50,sortable:true
				}, {title : '配件品牌',field : 'pbrdName',width : 50,sortable:true,hidden:true
				}, {title : '仓别',field : 'storeName', width : 50
				}, {title : '销售数量',field : 'selldCnt',width : 50,sortable:true
				}, {title : '销售单价',field : 'selldPrice',width : 50,sortable:true
				}, {title : '销售额',field : 'selldAmount',width : 50,sortable:true
				}, {title : '含税成本价',field : 'selldCostPrice',width : 50,sortable:true
				}, {title : '含税成本额',field : 'selldCostAmonut',width : 50,sortable:true
				}, {title : '适用车型',field : 'fitType', width : 50,hidden:true
		        }, {title : '仓别ID',field : 'storeId', width : 50,hidden:true}
			 ]]
		});
})

/**消退单查询**/
function searchByCondition(){
	$.ajax({
		type : "POST",
		url : 'StSellOrderStRtSellOrderSearchAction!loadStSellOrderStRtSellOrderSearchInfo.action',
		data : $('#StSellOrder_StRtSellOrderSearchForm').serialize(),
		dataType : "json",
		success : function callback(r) {
		   if(r.total>0)
			   $('#StSellOrder_StRtSellOrderSearchTable').datagrid('loadData',r);
		   else
			   $.messager.alert('优亿软件提示','抱歉，当前系统没有符合查询条件的记录！','warning',function(){});
		}
	});
}
/**销退单查询清空**/
function clearSearchByCondition(){
	 $('#StSellOrder_StRtSellOrderSearchForm').form('clear');
	 $('#StSellOrder_StRtSellOrderSearchTable').datagrid('loadData', {total:0,rows:[]});
	 $('#startTime').val(getSystemTime());
	 $('#endTime').val(getSystemTime());
}
