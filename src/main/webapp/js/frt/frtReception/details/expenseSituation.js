$(function (){
	var url='';
	if(receptionId!=null && receptionId!="" && receptionId!='null'){
		url=projectPath+'frtReceptionAction!findCostByRcptId.action?receptionId=' + receptionId;
	}else{
		url=projectPath+'frtReceptionAction!findCostByRcptId.action?receptionId=-1';
	}
	//前台接车->费用情况 
	$frtReceptionExpenseSituationOtherExpense = $('#frtReceptionExpenseSituationOtherExpense');
	$frtReceptionExpenseSituationOtherExpense.datagrid({
		url:url,
		singleSelect : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[ {
			field : 'costItem',
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
			id : 'frtReceptionExpenseSituationOtherExpense_add',
			text : '费用增加',
			iconCls : 'icon-add',
			disabled : true,
			handler : function() {
				var others=null;
				if(staticFrtReceptionExpenseSituationOtherExpense==null){
					others="";
				}else{
					others=JSON.stringify(staticFrtReceptionExpenseSituationOtherExpense);
				}
				$.ajax({
					type : 'post',
					url : projectPath+'frtReceptionAction!addFrtReceptionCost.action',
					data : 'others='+others,
					dataType : 'json',
					success : function callback(r) {
						$('#frtReceptionExpenseSituationOtherExpense').datagrid('loadData', r);
						var data = $('#frtReceptionExpenseSituationOtherExpense').datagrid('getData');
						staticFrtReceptionExpenseSituationOtherExpense=data;
						$('#frtReceptionExpenseSituationOtherExpense').datagrid('beginEdit', data.total-1);
					}
				});
			}
		}, {
			id : 'frtReceptionExpenseSituationOtherExpense_remove',
			text : '费用删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				var row = $('#frtReceptionExpenseSituationOtherExpense').datagrid('getSelected');
				if(row){
					staticFrtReceptionExpenseSituationOtherExpense = prosceniumDelete('frtReceptionExpenseSituationOtherExpense',row,staticFrtReceptionExpenseSituationOtherExpense);
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
		   if(operation==true  || recpetionFlag==true){
				staticFrtReceptionExpenseSituationOtherExpense=data;
				$('#frtReceptionExpenseSituationOtherExpense_add').linkbutton('enable');
				$('#frtReceptionExpenseSituationOtherExpense_remove').linkbutton('enable');
				$('#frtReceptionExpenseSituationOtherExpense_accept').linkbutton('enable');
			}
			
	   }
	});
});