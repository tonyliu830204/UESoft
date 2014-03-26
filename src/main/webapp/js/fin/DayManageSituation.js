function LoadOk() {
	if (document.readyState == "complete") {
		runs();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
var runs=function(){
	$('#endTime').val(getSystemTime());
	getStartDate($('#beginTime'));
	$('#dayManageSituationDatagrid').datagrid({
 		url : 'timeManageAction!findDayBusinessThing.action',
 		singleSelect : true,
		fit : true,
		rowNumbers : true,
		showFooter : true,
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [[{
			field : 'bigheadId',
			title : '序号',
			hidden : true
		},{
			field : 'preclrTime',
			title : '结算时间',
			width : 130,
			sortable : false
		}, {
			field : 'preclrId',
			title : '结算单号',
			width : 110,
			sortable : false
		}, {
			field : 'carLicense',
			title : '车牌照',
			width : 70,
			sortable : false
		}, {
			field : 'carTypeName',
			title : '车型',
			width : 60,
			sortable : false
		}]],
		columns : [[ {
			field : 'reptName',
			title : '维修类别',
			width : 90,
			sortable : false
		}, {
			field : 'invoiceTypeName',
			title : '票据类型',
			width : 100,
			sortable : false
		}, {
			field : 'invoiceNumber',
			title : '票据编号',
			width : 60,
			sortable : false
		}, {
			field : 'customName',
			title : '客户名称',
			width : 140,
			sortable : false
		}, {
			field : 'stfName',
			title : '接待员',
			width : 70,
			sortable : false
		}, {
			field : 'itemsAmount',
			title : '工时费',
			width : 70,
			sortable : false
		},{
			field : 'partsAmount',
			title : '材料费',
			width : 70,
			sortable : false
		},{
			field : 'otherAmount',
			title : '其他',
			width : 70,
			sortable : false
		},{
			field : 'sumAmount',
			title : '收入合计',
			width : 70,
			sortable : false
		},{
			field : 'realAmount',
			title : '实际收款',
			width : 70,
			sortable : false
		},{
			field : 'preclrCost',
			title : '结算成本',
			width : 70,
			sortable : false
		},{
			field : 'partsWideRate',
			title : '配件毛利率',
			width : 60,
			sortable : false
		},{
			field : 'gain',
			title : '利润',
			width : 70,
			sortable : false
		},{
			field : 'preclrWideRate',
			title : '结算毛利率',
			width : 60,
			sortable : false
		},{
			field : 'receptionCost',
			title : '工单成本',
			width : 70,
			sortable : false
		},{
			field : 'preclrRemark',
			title : '结算备注',
			width : 100,
			sortable : false
		},{
			field : 'rcptBranch',
			title : '接车分部',
			width : 60,
			sortable : false
		},{
			field : 'items',
			title : '维修项目',
			width : 1100,
			sortable : false
		}]],
		rowStyler:function(index,row){
			if (row.customName==null){
				return 'background-color:#80FF80;';
			}
		},
		onLoadSuccess:function(data){
			if($('#dayManageSituationForm').form('validate')){
				document.getElementById("snapMapImg").innerHTML=
				"<img src=\"timeManageAction!findDayBusinessThingSnapMap.action?"+
					$('#dayManageSituationForm').serialize()+" \" />";
				document.getElementById("poleMapImg").innerHTML=
				"<img src=\"timeManageAction!findDayBusinessThingPoleMap.action?"+
					$('#dayManageSituationForm').serialize()+" \"/>";
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
 	});
}
var query=function(){
	if($('#dayManageSituationForm').form('validate')){
		$('#dayManageSituationDatagrid').datagrid('load',serializeObject($('#dayManageSituationForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
var cancel=function(){
	$('#dayManageSituationForm').form('clear');
}

function _except(){
	showEditDialog("dayManageSituationDatagrid",null,"dayManageSituationDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"日营业情况查询"+getSystemTime());
}