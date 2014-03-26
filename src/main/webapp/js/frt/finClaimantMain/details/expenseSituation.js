function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		if(staticReceptionId!=""){
			$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
				url : 'finClaimantMainAction!findCostByReceptionId.action?receptionId=' +staticReceptionId
			});
		}else{
			var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
			if(rowData){
				$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
					url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=' + rowData.claimantmId
				});
			}else{
				$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
					url : 'finClaimantMainAction!findCostByFcmId.action?claimantmId=-1'
				});
			}
		}
		if ($('#save').length != 0 && $('#cancel').length != 0) {
			$('#finClaimantMainExpenseSituationOtherExpense').datagrid({
				onLoadSuccess : function (data){
					staticFinClaimantMainExpenseSituationOtherExpense=data;
					$('#finClaimantMainExpenseSituationOtherExpense_add').linkbutton('enable');
					$('#finClaimantMainExpenseSituationOtherExpense_remove').linkbutton('enable');
					$('#finClaimantMainExpenseSituationOtherExpense_accept').linkbutton('enable');
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);
	
	
	
	$(function (){
		//前台接车->费用情况 
		var url='';
		if(staticReceptionId!=""){
				url='finClaimantMainAction!findCostByReceptionId.action?receptionId=' +staticReceptionId;
		}else{
			var rowData = $('#finClaimantMainSummaryDatagrid').datagrid('getSelected');
			if(rowData){
					url ='finClaimantMainAction!findCostByFcmId.action?claimantmId=' + rowData.claimantmId;
			}
		}
		$finClaimantMainExpenseSituationOtherExpense = $('#finClaimantMainExpenseSituationOtherExpense');
		$finClaimantMainExpenseSituationOtherExpense.datagrid({
			url : url,
			singleSelect : true,
			rownumbers : true,
			fit : true,
			fitColumns : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[ {
						field : 'costName',
						title : '收费项目',
						width : 60,
						editor : {
							type : 'validatebox',
							options : {
								required : true,
								missingMessage : "收费项目为必填项!"
							}
						}
					} , {
						field : 'costAmount',
						title : '收费金额',
						width : 60,
						editor : {
							type : 'numberbox',
							options : {
								required : true,
								precision : 2,
								missingMessage : "收费金额为必填项!"
							}
						}
					},{
						field : 'costExplain',
						title : '收费说明',
						width : 60,
						editor : {
							type : 'text'
						}
					}]],
					toolbar : [{
						id : 'finClaimantMainExpenseSituationOtherExpense_add',
						text : '费用增加',
						iconCls : 'icon-add',
						disabled : true,
						handler : function() {
							var others=null;
							if(staticFinClaimantMainExpenseSituationOtherExpense==null){
								others="";
							}else{
								others=JSON.stringify(staticFinClaimantMainExpenseSituationOtherExpense);
							}
							$.ajax({
								type : 'post',
								url : 'finClaimantMainAction!addFrtReceptionCost.action',
								data : 'others='+others,
								dataType : 'json',
								success : function callback(r) {
									$('#finClaimantMainExpenseSituationOtherExpense').datagrid('loadData', r);
									var data = $('#finClaimantMainExpenseSituationOtherExpense').datagrid('getData');
									staticFinClaimantMainExpenseSituationOtherExpense=data;
									$('#finClaimantMainExpenseSituationOtherExpense').datagrid('beginEdit', data.total-1);
								}
							});
						}
					}, {
						id : 'finClaimantMainExpenseSituationOtherExpense_remove',
						text : '费用删除',
						iconCls : 'icon-remove',
						disabled : true,
						handler : function() {
							var row = $('#finClaimantMainExpenseSituationOtherExpense').datagrid('getSelected');
							if(row){
								staticFinClaimantMainExpenseSituationOtherExpense = prosceniumDelete('finClaimantMainExpenseSituationOtherExpense',row,staticFinClaimantMainExpenseSituationOtherExpense);
							}
						}
					}],
					onClickRow : function (rowIndex, rowData){
					   	if($('#save').length != 0 && $('#cancel').length != 0){
						   $(this).datagrid('beginEdit', rowIndex);
							var ed = $(this).datagrid('getEditors', rowIndex);
							ed[0].target.select();
							ed[0].target.bind('keyup', function() {
								var num = ed[0].target.val();
								var price = ed[1].target.val();
								var amount = accMul(parseFloat(num), parseFloat(price));
								ed[2].target.numberbox('setValue', amount);
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
						staticFinClaimantMainExpenseSituationOtherExpense=data;
				   }
				});
			});