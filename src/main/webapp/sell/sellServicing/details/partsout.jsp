<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 计划材料 -->
<script type="text/javascript">
    var effectRowData;
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
			idField : 'outId',
			loadMsg : "数据加载中，请稍后……",
			columns : [[
				{
					field : 'outId',
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
					width : 60,
					editor : {
						type : 'numberbox',
						options : {
							precision : 2,	
							required : true,
							min : 1,
							missingMessage : "数量为必填项!"
						}
					}
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
					width : 60
				},{
					field : 'posName',
					title : '部位名称',
					width : 60,
					hidden : true
				},{
					field : 'partsNowCount',
					title : '库存量',
					width : 60
				}]],
			toolbar : [
			   {
				   id : 'parts_remove',
				   text : '配件删除',
				   iconCls : 'icon-remove',
				   disabled : true,
				   handler : function (){
					  var row = $('#partsDatagrid').datagrid('getSelected');
						if(row){
							staticFrtResevationParts = prosceniumDelete('partsDatagrid',row,staticFrtResevationParts);
						}
			   	   }
			   }],
			   onClickRow : function (rowIndex, rowData){
				   if($('#spo_saveBtn').length != 0 && $('#spo_cancelBtn').length != 0){
					    $partsDatagrid.datagrid('beginEdit', rowIndex);
						var ed =$partsDatagrid.datagrid('getEditors', rowIndex);
						ed[0].target.select();
						ed[0].target.bind('keyup', function() {
							var num = ed[0].target.val();
							if(rowData && rowData.partsNowCount){
								if(num > rowData.partsNowCount){
									alertMsg('数量不能大于库存数', 'warning');
									ed[0].target.numberbox('setValue', rowData.partsNowCount);
									var price = ed[1].target.val();
									var amount = accMul(parseFloat(num), parseFloat(price));
									ed[2].target.numberbox('setValue', amount);
									ed[0].target.select();
								}
							}
						});
						ed[1].target.bind('keyup', function() {
							var num = ed[0].target.val();
							var price = ed[1].target.val();
							var amount = accMul(parseFloat(num), parseFloat(price));
							ed[2].target.numberbox('setValue', amount);
						});
						ed[0].target.focus(function (){
							ed[0].target.select();
						});
						ed[1].target.focus(function (){
							ed[1].target.select();
						});
						ed[0].target.keydown(function (e){
							if(e.keyCode == '13'){
								ed[1].target.select();
							}
						});
				   }
			   },
			   onLoadSuccess : function (data){
					staticFrtResevationParts=data;
			   }
		});
	});

</script>
<table id="partsDatagrid"></table>