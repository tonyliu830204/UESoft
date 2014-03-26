$(function (){
	var selectFrtResevationDatagrid = $('#selectFrtResevationDatagrid');
	selectFrtResevationDatagrid.datagrid({
		url : url,
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns : false,
		fit : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'resvId',
			title : '预约编号',
			width : 110,
			sortable : true
		},{
			field : 'carId',
			title : '车辆编号',
			width : 60,
			sortable : true
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 80,
			sortable : true
		},{
			field : 'carVin',
			title : 'VIN号',
			width : 100,
			sortable : true
		},{
			field : 'carMotorId',
			title : '发动机号',
			width : 100,
			sortable : true
		},{
			field : 'customId',
			title : '客户名称',
			width : 60,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {

				}
			},
			formatter : function(value, row, index) {
				return row.customName;
			}
		},{
			field : 'customName',
			title : '客户名称',
			hidden : true
		},{
			field : 'resvEnterTime',
			title : '预约进店时间',
			width : 140,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {

				}
			},
			formatter : function(value, rowData, rowIndex) {
				return '<sapn title="' + value + '">' + value + '</span>';
			}
		},{
			field : 'reptId',
			title : '维修类别',
			width : 80,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {

				}
			},
			formatter : function(value, rowData, rowIndex) {
				return rowData.reptName;
			}
		},{
			field : 'reptName',
			title : '维修类别',
			width : 60,
			hidden : true
		},{
			field : 'repwkId',
			title : '维修工位',
			width : 80,
			sortable : true,
			formatter : function(value, row, index) {
				return row.repwkName;
			}
		},{
			field : 'repwkName',
			title : '维修工位',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'resvStatus',
			title : '预约状态',
			width : 60,
			sortable : true
		},{
			field : 'resvDistance',
			title : '里程数',
			width : 60,
			sortable : true
		},{
			field : 'resvRealTime',
			title : '预约登记时间',
			width : 140,
			sortable : true
		},{
			field : 'stfId',
			title : '接待员',
			width : 60,
			sortable : true,
			formatter : function(value, row, index) {
				return row.stfName;
			}
		},{
			field : 'stfName',
			title : '接待员',
			width : 60,
			sortable : true,
			hidden : true
		},{
			field : 'resvFixpel',
			title : '托修人',
			width : 60,
			sortable : true
		},{
			field : 'resvFixtel',
			title : '托修人电话',
			width : 80,
			sortable : true
		},{
			field : 'resvFixphone',
			title : '托修人手机',
			width : 80,
			sortable : true
		},{
			field : 'repgrpId',
			title : '维修班组',
			width : 80,
			sortable : true,
			formatter : function(value, row, index) {
				return row.repgrpName;
			}
		},{
			field : 'repgrpName',
			title : '维修班组',
			width : 80,
			sortable : true,
			hidden : true
		},{
			field : 'resvType',
			title : '预约分类',
			width : 60,
			sortable : true,
			formatter : function(value, row, index) {
				if (row.resvType == '1') {
					return '主动预约';
				} else {
					return '被动预约';
				}
			}
		},{
			field : 'resvRemark',
			title : '备注',
			width : 300
		},{
			field : 'resvFlg',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrServices',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrSatisfaction',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrReportTime',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrIdea',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrReplyTime',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrIsCome',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrReason',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrInTime',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrOutTime',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrCsr',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrReturnVisitTime',
			title : '',
			width : 50,
			hidden : true
		},{
			field : 'rtrRemarke',
			title : '',
			width : 50,
			hidden : true
		}]],
		onDblClickRow : function (rowIndex, rowData){
		isFrtResevationCancel();
		} 
	});
});

function query(){
	$('#selectFrtResevationDatagrid').datagrid('load', serializeObject($('#resvQueryForm')));
}
function clearResv(){
	$('#resvQueryForm').form('clear');
	$('#selectFrtResevationDatagrid').datagrid('load', serializeObject($('#resvQueryForm')));
}