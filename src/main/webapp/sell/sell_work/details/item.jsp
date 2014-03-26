<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 计划项目 -->
<script type="text/javascript">
	$(function (){
		//预约申请单->计划项目
		//frtAddItmeAction!findAll.action
		$itemDatagrid = $('#itemDatagrid');
		$itemDatagrid.datagrid({
			url : 'carFixAction!findItem.action',
			queryParams: {install_id: $('#install_id').val()},
			pagination : true,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			idField : 'itemId',
			loadMsg : "数据加载中，请稍候……",
			columns : [ [{
				field : 'installItemId',
				title : '项目明细编号',
				width : 60,
				hidden : true
			},{
				field : 'install_id',
				title : '加装编号',
				width : 60,
				hidden : true
			},{
				field : 'itemId',
				title : '项目编号',
				width : 35,
				hidden : true
			},{
				field : 'itemName',
				title : '项目名称',
				width : 100,
				sortable : true
			},{
				field : 'itemCost',
				title : '项目成本',
				width : 60
			},{
				field : 'itemMoney',
				title : '项目金额',
				width : 60
			},{
				field : 'itemRemark',
				title : '备注',
				width : 50,
				sortable : true
			}]]
		});
	});
</script>
<table id="itemDatagrid"></table>