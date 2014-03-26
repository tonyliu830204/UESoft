<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 计划项目 -->
<script type="text/javascript">

	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 1000);
		}
	}
	function initFrame() {
		if($('#spo_saveBtn').length != 0 && $('#spo_cancelBtn').length != 0){
			$('#itemDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtReceptionItems=data;
				}
			});
			$('#item_add').linkbutton('enable');
			$('#item_remove').linkbutton('enable');
		}
	}

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
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0,
						required : true,
						missingMessage : "项目成本为必填项!"
					}
				}
			},{
				field : 'itemMoney',
				title : '项目金额',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2,
						min : 0,
						required : true,
						missingMessage : "项目金额为必填项!"
					}
				}
			},{
				field : 'remark',
				title : '备注',
				width : 50,
				sortable : true
			}]],
			toolbar : [{
					id : 'item_add',
					text : '项目新增',
					iconCls : 'icon-add',
					disabled : true,
					handler : function() {
						var d = $('<div/>').dialog({
							modal:true,
							title : '维修项目选择',
							closable : true,
							href : '${pageContext.request.contextPath}/sell/sellServicing/details/addItem.jsp',
							width : 900,
							height : 560,
							buttons : [{
								text : '确定',
								iconCls : 'icon-add',
								handler : function (){
									if(validateDatagrid('selectedItem')){
										staticFrtReceptionItems=prosceniumAdd2('selectedItem','itemDatagrid',staticFrtReceptionItems);
										d.dialog('close');
									}else{
										alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
									}
								}
							}],
							onClose : function (){
						    	$(this).dialog('destroy');
						    }
						});
					}
		      },{
				   id : 'item_remove',
				   text : '项目删除',
				   iconCls : 'icon-remove',
				   disabled : true,
				   handler : function (){	
					  var row = $('#itemDatagrid').datagrid('getSelected');
						if(row){
							staticFrtReceptionItems = prosceniumDelete('itemDatagrid',row,staticFrtReceptionItems);
						}
			   	   }
			 }],
			 onClickRow : function (rowIndex, rowData){
			    if($('#spo_saveBtn').length != 0 && $('#spo_cancelBtn').length != 0){
					$itemDatagrid.datagrid('beginEdit', rowIndex);
					var ed =$itemDatagrid.datagrid('getEditors', rowIndex);
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
				staticFrtReceptionItems=data;
		   }
		});
	});

	setTimeout("LoadOk();", 1000);
</script>
<table id="itemDatagrid"></table>