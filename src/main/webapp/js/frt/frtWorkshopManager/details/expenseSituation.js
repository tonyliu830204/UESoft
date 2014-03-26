function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		if ($('#save').length != 0 && $('#cancel').length != 0) {
			$('#frtReceptionExpenseSituationOtherExpense').datagrid({
				onLoadSuccess : function (){
					$('#frtReceptionExpenseSituationOtherExpense_add').linkbutton('enable');
					$('#frtReceptionExpenseSituationOtherExpense_remove').linkbutton('enable');
					$('#frtReceptionExpenseSituationOtherExpense_accept').linkbutton('enable');
				}
			});	
		}
	}
	setTimeout("LoadOk();", 200);

	$(function() {
		//前台接车->费用情况->其他费用明细
		$frtReceptionExpenseSituationOtherExpense = $('#frtReceptionExpenseSituationOtherExpense');

		$frtReceptionExpenseSituationOtherExpense.datagrid({
					url : '',
					fit : true,
					fitColumns : true,
					singleSelect : true,
					rownumbers : true,
					columns : [ [ {
						field : 'costItem',
						title : '收费项目',
						width : 60,
						sortable : true,
						editor : {
							type : 'validatebox',
							options : {
								required : true,
								missingMessage : "收费项目为必填项!"
							}
						}
					}, {
						field : 'costAmount',
						title : '收费金额',
						width : 60,
						sortable : true,
						editor : {
							type : 'numberbox',
							options : {
								required : true,
								precision : 2,
								missingMessage : "收费金额为必填项!"
							}
						}
					}, {
						field : 'costExplain',
						title : '收费说明',
						width : 60,
						sortable : true,
						editor : {
							type : 'text'
						}
					} ] ],
					toolbar : [
							{
								id : 'frtReceptionExpenseSituationOtherExpense_add',
								text : '费用增加',
								iconCls : 'icon-add',
								disabled : true,
								handler : function() {
									$frtReceptionExpenseSituationOtherExpense
											.datagrid(
													'addEditor',
													{
														field : 'costItem',
														title : '收费项目',
														width : 60,
														sortable : true,
														editor : {
															type : 'validatebox',
															options : {
																required : true,
																missingMessage : "收费项目为必填项!"
															}
														}
													});
									add(
											$frtReceptionExpenseSituationOtherExpense,
											{});
								}
							},
							'-',
							{
								id : 'frtReceptionExpenseSituationOtherExpense_remove',
								text : '费用删除',
								iconCls : 'icon-remove',
								disabled : true,
								handler : function() {
									remove(
											$frtReceptionExpenseSituationOtherExpense,
											'frtReception_deleteRctpCost',
											'frtReception_findAllRcptCostByList');

									$(
											'#frtReceptionExpenseSituationOtherExpense')
											.datagrid(
													{
														onLoadSuccess : function(
																data) {
															$(
																	'#frtReceptionExpenseSituationOtherExpense_add')
																	.linkbutton(
																			'enable');
															$(
																	'#frtReceptionExpenseSituationOtherExpense_remove')
																	.linkbutton(
																			'enable');
															$(
																	'#frtReceptionExpenseSituationOtherExpense_edit')
																	.linkbutton(
																			'enable');
														}
													});

									rcptAmount();
								}
							},
							'-',
							{
								id : 'frtReceptionExpenseSituationOtherExpense_accept',
								text : '提交更改',
								iconCls : 'icon-edit',
								disabled : true,
								handler : function() {
									$frtReceptionExpenseSituationOtherExpense
											.datagrid('removeEditor',
													'costItem');
									edit(
											$frtReceptionExpenseSituationOtherExpense,
											'frtReception_editRcptCost',
											'frtReception_findAllReptCostByList');
								}
							} ],
					onAfterEdit : function(rowIndex, rowData, changes) {
						onAfterEdit($frtReceptionExpenseSituationOtherExpense,
								'frtReception_saveRcptCost',
								'frtReception_editRcptCost', rowIndex, rowData,
								changes);

						$frtReceptionExpenseSituationOtherExpense.datagrid({
							url : 'frtReception_findAllRcptCostByList.action'
						});

						$('#frtReceptionExpenseSituationOtherExpense_add')
								.linkbutton('enable');
						$('#frtReceptionExpenseSituationOtherExpense_remove')
								.linkbutton('enable');
						$('#frtReceptionExpenseSituationOtherExpense_edit')
								.linkbutton('enable');

						rcptAmount();
					}
				});
	});