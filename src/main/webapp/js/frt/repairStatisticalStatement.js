$(function (){

		$('#frtWorkOrderItemQueryPreclrTimeEnd').val(getSystemTime2());
		loadPreClrTime1($('#frtWorkOrderItemQueryPreclrTimeBegin'));
		findCarLicenseFormat("frtWorkOrderRepaiStatisticalStatementQueryCarId");
		//维修业务统计报表
		var frtWorkOrderRepaiStatisticalStatementDatagrid = $('#frtWorkOrderRepaiStatisticalStatementDatagrid');
		
		frtWorkOrderRepaiStatisticalStatementDatagrid.datagrid({
			url : 'frtWorkOrderAction!datagridFrtWorkOrderRepaiStatisticalStatement.action',
			fit : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			frozenColumns : [[
				{field:'preclrTime',title:'结算日期',width:124,sortable:true},
				{field:'interDate',title:'进场日期',width:124,sortable:true},
				{field:'receptionId',title:'维修工单号',width:110,sortable:true},
				{field:'carVin',title:'VIN号',width:140,sortable:true},
				{field:'carLicense',title:'车牌照号',width:60,sortable:true}             
			    ]],
			columns : [[
				{field:'cbrdName',title:'车品牌',width:60,sortable:true},
				{field:'ctypeName',title:'车型号',width:60,sortable:true},
				{field:'receptionDistance',title:'里程数',width:60,sortable:true},
				{field:'reptName',title:'维修类别',width:60,sortable:true},
				{field:'customName',title:'客户名称',width:60,sortable:true},
				{field:'customAddr',title:'地址',width:150,sortable:true},
				{field:'customTel1',title:'联系电话',width:100,sortable:true},
				{field:'stfName',title:'接待人员',width:60,sortable:true},
				{field:'repitemName',title:'维修项目',width:240,sortable:true},
				{field:'preclrWktimeAmount',title:'工时费(RMB)',width:80,sortable:true},
				{field:'preclrPartsAmount',title:'配件费(RMB)',width:80,sortable:true},
				{field:'preclrOtherAmount',title:'其他费用(RMB)',width:80,sortable:true},
				{field:'preclrManagementFee',title:'管理费(RMB)',width:80,sortable:true},
				{field:'preclrRealAmount',title:'结算金额(RMB)',width:80,sortable:true}
			]]
		});
	});
	
	function  queryFrtWorkOrderRepaiStatisticalStatement(){
		$('#frtWorkOrderRepaiStatisticalStatementDatagrid').datagrid('unselectAll');
		if($('#frtWorkOrderRepaiStatisticalStatementQueryForm').form('validate')){
			$('#frtWorkOrderRepaiStatisticalStatementDatagrid').datagrid('load', serializeObject($('#frtWorkOrderRepaiStatisticalStatementQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	function clearFrtWorkOrderRepaiStatisticalStatement(){
		$('#frtWorkOrderRepaiStatisticalStatementQueryForm').form('clear');
		$('#frtWorkOrderRepaiStatisticalStatementDatagrid').datagrid('load', serializeObject($('#frtWorkOrderRepaiStatisticalStatementQueryForm')));
	} 
	function _except(){
		showEditDialog("frtWorkOrderRepaiStatisticalStatementDatagrid",null,"frtWorkOrderRepaiStatisticalStatementDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"前台配件查询"+getSystemTime());
	}