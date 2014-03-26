function LoadOk() {
	if (document.readyState == "complete") {
		bhRuns();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
var url_1='customerPerformanceStatisticsAction!findBalanceHoursQuery.action';
var url_2='customerPerformanceStatisticsAction!findBalanceHoursQueryForRecivePerson.action';
//结算工时查询
var bhRuns=function(){
	$('#balanceHoursQueryDatagrid').treegrid({
		url : url_1,
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		showFooter : true,
		animate:true,
		autoRowHeight:true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		idField : 'serviceGroupId',
		treeField : 'stfName',
		frozenColumns:[[ {
			field : 'serviceGroupId',
			title : '维修班组编号',
			width : 100,
			hidden : true
		}, {
			field : 'serviceGroupName',
			title : '维修班组',
			width : 100,
			sortable : true,
			styler:function(value,row){
				if (value==null){
					return 'background-color:#EEEEEE;';
				}
			}
		},{
			field : 'stfName',
			title : '员工姓名',
			width : 100,
			sortable : true,
			styler:function(value,row){
				if (value==null||value.length==0){
					return 'background-color:#EEEEEE;';
				}
			}
		}, {
			field : 'receptionId',
			title : '工单号',
			width : 180,
			sortable : true
		}
		]],
		columns : [ [{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		}, {
			field : 'reptName',
			title : '维修类别',
			width : 100,
			sortable : true
		}, {
			field : 'itemName',
			title : '维修项目',
			width : 320,
			sortable : true
		}, {
			field : 'itemTime',
			title : '结算工时',
			width : 100,
			sortable : true
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true
		}, {
			field : 'preclrTime',
			title : '结算日期',
			width : 120,
			sortable : true
		}
		]],
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			var url = 'customerPerformanceStatisticsAction!findBalanceHoursQueryChild.action?serviceGroupId='+rowData.serviceGroupId;
			url+='&receivePerson='+$('#balanceHours_receivePerson').combobox('getValue');
			url+='&'+$('#balanceHoursQueryForm').serialize();
			$('#balanceHoursQueryDatagrid').treegrid("options").url = url;
			return true;
		},
		rowStyler:function(row){
			if (row.serviceGroupId==null){
				return 'background-color:#80FF80;';
			}
		}
	});
}
var forRecivePerson=function(){
		$('#balanceHoursQueryDatagrid').treegrid({
		url : url_2,
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		showFooter : true,
		animate:true,
		autoRowHeight:true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		idField : 'stfId',
		treeField : 'receptionId',
		frozenColumns:[[ {
			field : 'stfId',
			title : '接待员编号',
			width : 100,
			hidden : true
		},{
			field : 'stfName',
			title : '接待员',
			width : 100,
			sortable : true
		}, {
			field : 'receptionId',
			title : '工单号',
			width : 180,
			sortable : true
		},{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		}
		]],
		columns : [ [ {
			field : 'reptName',
			title : '维修类别',
			width : 100,
			sortable : true
		}, {
			field : 'itemName',
			title : '维修项目',
			width : 320,
			sortable : true
		}, {
			field : 'itemTime',
			title : '结算工时',
			width : 100,
			sortable : true
		}, {
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true
		}, {
			field : 'preclrTime',
			title : '结算日期',
			width : 120,
			sortable : true
		}
		]],
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			var url = 'customerPerformanceStatisticsAction!findBalanceHoursQueryChildForRecivePerson.action?serviceGroupId='+$('#balanceHours_serviceGroupId').combobox('getValue');
			url+='&receivePerson='+rowData.stfId;
			url+='&'+$('#balanceHoursQueryForm').serialize();
			$('#balanceHoursQueryDatagrid').treegrid("options").url = url;
			return true;
		},
		rowStyler:function(row){
			if (row.serviceGroupId==null){
				return 'background-color:#EEEEEE;';
			}
		}
	});			
}

function queryBalanceHoursQuery(){
	var value=$('#balanceHours_weaveWay').combobox('getValue');
	if($('#balanceHoursQueryForm').form('validate')){
		if(value!=null&&value=='true'){
			url_2='customerPerformanceStatisticsAction!findBalanceHoursQueryForRecivePerson.action?serviceGroupId='+$('#balanceHours_serviceGroupId').combobox('getValue');
			url_2+='&receivePerson='+$('#balanceHours_receivePerson').combobox('getValue');
			url_2+='&'+$('#balanceHoursQueryForm').serialize();
			forRecivePerson();
			$('#balanceHoursQueryDatagrid').treegrid({
				onLoadSuccess:function(row,data){
			},
			onLoadError:function(arguments){
				alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			}
			});
		}else{
			url_1='customerPerformanceStatisticsAction!findBalanceHoursQuery.action?serviceGroupId='+$('#balanceHours_serviceGroupId').combobox('getValue');
			url_1+='&receivePerson='+$('#balanceHours_receivePerson').combobox('getValue');
			url_1+='&'+$('#balanceHoursQueryForm').serialize();
			bhRuns();
			$('#balanceHoursQueryDatagrid').treegrid({
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
function clearBalanceHoursQuery(){
 $('#balanceHoursQueryForm').form('clear');
}
function _exceptBalanceHoursQuery(){
	showEditDialog("balanceHoursQueryDatagrid",null,"balanceHoursQueryDatagrid_center","开始导出","导出配置",0,_callbackExceptBalanceHoursQuery);
}
function _callbackExceptBalanceHoursQuery(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"结算工时查询"+getSystemTime());
}