function LoadOk() {
	if (document.readyState == "complete") {
		runs();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
var url_1='customerPerformanceStatisticsAction!findServicePersonScore.action';
var url_2='customerPerformanceStatisticsAction!findServicePersonScoreForReceptionId.action';
//维修人员业绩统计
var runs=function(){
	$('#servicePersonDatagrid').treegrid({
		url : url_1,
		pagination : true,
		fit : true,
		rownumbers : true,
		showFooter : true,
		animate:true,
		autoRowHeight:true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		idField : 'stfId',
		treeField : 'receptionId',
		frozenColumns:[[{
			field : 'stfId',
			title : '员工编号',
			width : 100,
			hidden : true
		},{
			field : 'stfName',
			title : '员工姓名',
			width : 100,
			sortable : true
		}, {
			field : 'serviceGroupName',
			title : '维修班组',
			width : 100,
			sortable : true
		}, {
			field : 'receptionId',
			title : '工单号',
			width : 180,
			sortable : true
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true
		}
		]],
		columns : [ [{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		}, {
			field : 'carTypeName',
			title : '车类型',
			width : 100,
			sortable : true
		}, {
			field : 'itemTime',
			title : '施工工时',
			width : 100,
			sortable : true
		}, {
			field : 'itemAmount',
			title : '工时金额',
			width : 100,
			sortable : true
		}, {
			field : 'itemRebateAmount',
			title : '工时折扣',
			width : 100,
			sortable : true
		}, {
			field : 'itemFactAmount',
			title : '折扣后金额',
			width : 100,
			sortable : true
		}, {
			field : 'partsAmount',
			title : '配件金额',
			width : 100,
			sortable : true
		}, {
			field : 'sumAmount',
			title : '业绩',
			width : 100,
			sortable : true
		}, {
			field : 'taxAmount',
			title : '含税成本',
			width : 100,
			sortable : true
		}, {
			field : 'noTaxAmount',
			title : '未税成本',
			width : 100,
			sortable : true
		}, {
			field : 'itemName',
			title : '维修项目',
			width : 320,
			sortable : true
		}, {
			field : 'receivePerson',
			title : '接待员',
			width : 100,
			sortable : true
		}
		]],
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			var url = 'customerPerformanceStatisticsAction!findServicePersonScoreChild.action?stfId='+rowData.stfId;
			url+='&'+$('#servicePersonQueryForm').serialize();
			$('#servicePersonDatagrid').treegrid("options").url = url;
			return true;
		}
	});
}
var forReceptionId=function(){
	$('#servicePersonDatagrid').treegrid({
		url : url_2,
		pagination : true,
		fit : true,
		rownumbers : true,
		showFooter : true,
		animate:true,
		autoRowHeight:true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		idField : 'receptionId',
		treeField : 'stfName',
		frozenColumns : [[{
			field : 'receptionId',
			title : '工单号',
			width : 110,
			sortable : true
		},  {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		}, {
			field : 'carTypeName',
			title : '车类型',
			width : 100,
			sortable : true
		}]],
		columns : [ [{
			field : 'stfId',
			title : '员工编号',
			width : 100,
			hidden : true
		},{
			field : 'stfName',
			title : '员工姓名',
			width : 160,
			sortable : true
		}, {
			field : 'serviceGroupName',
			title : '维修班组',
			width : 100,
			sortable : true
		}, {
			field : 'itemTime',
			title : '施工工时',
			width : 100,
			sortable : true
		}, {
			field : 'itemAmount',
			title : '工时金额',
			width : 100,
			sortable : true
		}, {
			field : 'itemRebateAmount',
			title : '工时折扣',
			width : 100,
			sortable : true
		}, {
			field : 'itemFactAmount',
			title : '折扣后金额',
			width : 100,
			sortable : true
		}, {
			field : 'partsAmount',
			title : '配件金额',
			width : 100,
			sortable : true
		}, {
			field : 'sumAmount',
			title : '业绩',
			width : 100,
			sortable : true
		}, {
			field : 'taxAmount',
			title : '含税成本',
			width : 100,
			sortable : true
		}, {
			field : 'noTaxAmount',
			title : '未税成本',
			width : 100,
			sortable : true
		}, {
			field : 'itemName',
			title : '维修项目',
			width : 320,
			sortable : true
		}, {
			field : 'receivePerson',
			title : '接待员',
			width : 100,
			sortable : true
		}
		]],
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			var url = 'customerPerformanceStatisticsAction!findServicePersonScoreChildForReceptionId.action?receptionId='+rowData.receptionId;
			url+='&stfId='+$('#servicePerson_stfId').combobox('getValue');
			url+='&'+$('#servicePersonQueryForm').serialize();
			$('#servicePersonDatagrid').treegrid("options").url = url;
			return true;
		}
	});
}
function queryServicePerson(){
	var value=$('#servicePerson_weaveWay').combobox('getValue');
	if($('#servicePersonQueryForm').form('validate')){
		if(value!=null&&value=='true'){
			url_2='customerPerformanceStatisticsAction!findServicePersonScoreForReceptionId.action?stfId='+$('#servicePerson_stfId').combobox('getValue');
			url_2+='&'+$('#servicePersonQueryForm').serialize();
			forReceptionId();
			$('#servicePersonDatagrid').treegrid({
				onLoadSuccess:function(row,data){
			},
			onLoadError:function(arguments){
				alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			}
			});
		}else{
			url_1='customerPerformanceStatisticsAction!findServicePersonScore.action?stfId='+$('#servicePerson_stfId').combobox('getValue');
			url_1+='&'+$('#servicePersonQueryForm').serialize();		
			runs();
			$('#servicePersonDatagrid').treegrid({
				onLoadSuccess:function(row,data){
			},
			onLoadError:function(arguments){
				alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			}
			});
		}
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
function clearServicePerson(){
 $('#servicePersonQueryForm').form('clear');
}
function _exceptServicePerson(){
	showEditDialog("servicePersonDatagrid",null,"servicePersonDatagrid_center","开始导出","导出配置",0,_callbackExceptServicePerson);
}
function _callbackExceptServicePerson(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"维修人员业绩统计"+getSystemTime());
}