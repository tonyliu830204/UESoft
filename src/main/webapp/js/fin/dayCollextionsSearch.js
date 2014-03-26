function LoadOk() {
	if (document.readyState == "complete") {
		runs();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
var runs=function(){
	$('#preclrDateEnd').val(getSystemTime());
    getStartDate($('#preclrDateStart'));
	$('#dayCollextionsSearchDatagrid').treegrid({
		url : 'dayBusinessAction!loadPreclrDate.action',
		fit : true,
		fitColumns : false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		showFooter:true,
		idField : 'tempId',
		treeField : 'fcsDate',
		frozenColumns:[[{
			field : 'tempId',
			title : '标识列',
			width : 120,
			hidden:true
		},{
			field : '_parentId',
			title : '父级节点',
			width : 10,
			hidden:true
		},{
			field : 'fcsDate',
			title : '收款日期',
			width : 160
		},{
			field : 'paidResourcel',
			title : '收款来源',
			width : 110
		}             
		]],
		columns : [[{
			field : 'customName',
			title : '客户名称',
			width : 180
		},{
			field : 'carLicense',
			title : '车牌',
			width : 60
		},{
			field : 'receptionId',
			title : '工单号',
			width : 110
		},{
			field : 'InterDate',
			title : '进厂日期',
			width : 130
		},{
			field : 'preclrId',
			title : '结算单',
			width : 110
		},{
			field : 'preclrSumAmount',
			title : '结算额',
			width : 75
		},{
			field : 'fcsPaymentMoney',
			title : '现收款',
			width : 75
		},{
			field : 'gatheringWise',
			title : '付款方式',
			width : 75
		},{
			field : 'invoiceType',
			title : '票据类型',
			width : 80
		},{
			field : 'fcsRemark',
			title : '收款备注',
			width : 100
		},{
			field : 'preclrWkTimeAmount',
			title : '结算工费',
			width : 75
		},{
			field : 'preMprMatAmount',
			title : '结算材料费',
			width : 75
		},{
			field : 'preclrRealAmount',
			title : '应收金额',
			width : 75
		},{
			field : 'fcsId',
			title : '收款单号',
			width : 130
		},{
			field : 'servicePerson',
			title : '接待人员',
			width : 75
		},{
			field : 'reptName',
			title : '维修类别',
			width : 75
		},{
			field : 'preclrNo',
			title : '票据编号',
			width : 75
		},{
			field : 'balancePerson',
			title : '收款人员',
			width : 75
		}]],
		onloadSuccess: function (){
//		    delete $(this).treegrid('options').queryParams['fcsDate'];
		},
		onBeforeExpand : function(rowData){
			var options = $('#dayCollextionsSearchDatagrid').treegrid("options");
			if(rowData._parentId==null||rowData._parentId==''){
				options.treeField='paidResourcel';
				var url = 'dayBusinessAction!loadPaidResource.action?&fcsTempDate='+rowData.fcsDate;
				url+='&'+$('#dayBusinessForm').serialize();
				options.url = url;
				return true;
			}else{
				var parent=$('#dayCollextionsSearchDatagrid').treegrid("getParent",rowData.tempId);
				options.treeField='customName';
				var url = 'dayBusinessAction!loadDayPaid.action?fcsTempDate='+parent.fcsDate+'&classTempWay='+rowData.paidResourcel;
				url+='&'+$('#dayBusinessForm').serialize();
				options.url = url;
				return true;
			}
		  }
	  });
    }
    //条件查询
	function searchByCondition(){
		//runs();
		var url="dayBusinessAction!loadPreclrDate.action?";
		url+=$('#dayBusinessForm').serialize();
		$('#dayCollextionsSearchDatagrid').treegrid({
			url:url
		});
	}
	//清空表单数据
	function clearCondition(){
		$('#dayBusinessForm').form('clear');
	}
	function _except(){
		showEditDialog("dayCollextionsSearchDatagrid",null,"dayCollextionsSearchDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"日收款查询"+getSystemTime());
	}