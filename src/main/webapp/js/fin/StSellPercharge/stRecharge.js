$(function (){
	datagrid = $('#StRechargeTable');
	datagrid.datagrid({
		url : 'StSellPerchargeAction_loadStRecharge.action',
		fit : true,
		fitColumns : true,
		sortName:'perchargeId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [[{
			align : 'center',
			field : 'rechargeId',
			title : '收款单号',
			width : 120,
			sortable : true,
			//hidden:true
		},{
			field : 'carId',
			title : '车辆牌照',
			width : 100,
			hidden:true
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100
		},{
			field : 'cbrdName',
			title : '车品牌',
			width : 100
		},{
			field : 'ctypeName',
			title : '车类型',
			width : 100
		},{
			field : 'customName',
			title : '客户名称',
			width : 100
		},{
			field : 'carRelationPerson',
			title : '联系人',
			width : 100
		},{
			field : 'carRelationTel1',
			title : '电话一',
			width : 120
		},{
			field : 'carRelationTel2',
			title : '电话二',
			width : 100
		},{
			field : 'amontMoney',
			title : '预存维修金',
			width : 100
		},{
			field : 'paymentName',
			title : '获赠维修金',
			width : 100
		},{
			field : 'stfId',
			title : '使用维修金',
			width : 100
		},{
			field : 'surplusMoney',
			title : '剩余维修金',
			width : 100
		}]],
     onClickRow:function(rowIndex, rowData){
			$.ajax({
				type : "POST",
				url : "StSellPerchargeAction_findStSellPerchargeById.action",
				data : 'rechargeId='+rowData.rechargeId,
				dataType : "json",
				success : function callback(r) {
     		    	    if(r.total>0){
     		    	    	YE_StSellPerchargeTable.datagrid('loadData',r)
     		    	    }else{
     		    	    	$.messager.alert('优亿软件提示',r.msg,'warning',function(){});
     		    	    }
	            }
	          });
			$.ajax({
				type : "POST",
				url : 'StSellPerchargeAction_loadStUsePercharge.action',
				data : 'rechargeId='+rowData.rechargeId,
				dataType : "json",
				success : function callback(r) {
  						if(r.total==0){
  							YE_StSellPerchargeTable1.datagrid('loadData',{total:0,rows:[]});
	  					}else if(r.total>0){
     		    	    	YE_StSellPerchargeTable1.datagrid('loadData',r)
     		    	    }
	            }
	          });
     }
	});

	YE_StSellPerchargeTable=$('#YE_StSellPerchargeTable');
	YE_StSellPerchargeTable.datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		sortName:'perchargeId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [[{
			align : 'center',
			field : 'perchargeType',
			title : '维修金来源',
			width : 120,
			sortable : true
		},{
			field : 'perchargeId',
			title : '单据编号',
			width : 100
		},{
			field : 'perchargeDate',
			title : '收款日期',
			width : 100
		},{
			field : 'curPercharge',
			title : '收款金额',
			width : 100
		},{
			field : 'preclrNo',
			title : '票据编号',
			width : 100
		},{
			field : 'paymentName',
			title : '付款方式',
			width : 100
		}]],
     onClickRow:function(rowIndex, rowData){
     }
	});
	
	YE_StSellPerchargeTable1=$('#YE_StSellPerchargeTable1');
	YE_StSellPerchargeTable1.datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		sortName:'perchargeId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [[{
			field : 'receptionId',
			title : '工单号',
			width : 100
		},{
			field : 'repcollTime',
			title : '使用时间',
			width : 100
		},{
			field : 'discountAmont',
			title : '使用金额',
			width : 100
		},{
			field : 'paymentName',
			title : '付款方式',
			width : 100
		}]],
     onClickRow:function(rowIndex, rowData){
     }
	});
});