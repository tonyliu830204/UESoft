<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 计划材料 -->
<script type="text/javascript">
	$(function (){
		//加装配件
		$partsDatagrid = $('#partsDatagrid');
		$partsDatagrid.datagrid({
			rownumbers : true,
			pagination:true,
		    fit:true,
		    singleSelect:true,
		    pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			fitColumns : true, 
			idField : 'detailId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{
					field : 'detailId',
					title : '配件明细编号',
					width : 60,
					hidden : true
				},{
					field : 'install_id',
					title : '加装编号',
					width : 60,
					hidden : true
				},{
					field : 'partsId',
					title : '配件编码',
					width : 60
				},{
					field : 'partsName',
					title : '配件名称',
					width : 60
				},{
					field : 'punitId',
					title : '单位编号',
					width : 60,
					hidden : true
				},{
					field : 'punitName',
					title : '单位',
					width : 60
				},{
					field : 'partsNum',
					title : '数量',
					width : 60
				},{
					field : 'partsRepairPrice',
					title : '单价',
					width : 60
				},{
					field : 'partsAmount',
					title : '金额',
					width : 60
				},{
					field : 'storeId',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'storeName',
					title : '仓别',
					width : 60,
					hidden : true
				},{
					field : 'posName',
					title : '部位名称',
					width : 60,
					hidden : true
				},{
					field : 'partsNowCount',
					title : '库存量',
					width : 60
				}]]
		});
	});

</script>
<table id="partsDatagrid"></table>