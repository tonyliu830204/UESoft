function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		//$('#frtInsurePrizeTabs').tabs('select', '保险估价明细');
		//$('#frtInsurePrizeTabs').tabs('select', '保险估价汇总');
	}
	setTimeout("LoadOk();", 200);
	$(function() {
		$('#resvRealTimeEnd').val(getSystemTime());
		getStartTime($('#resvRealTimeBegin'));
		findCarLicenseFormat("frtInsurePrize_summary_carId");
		//保险估价单->保险估价汇总datagrid
		$('#frtInsurePrizeSummaryDatagrid').datagrid({
			url : 'frtInsurePrizeAction!datagridFrtResevation.action',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns : false,
			showFooter : true,
			fit : true,
			loadMsg : "数据加载中，请稍后……",
			frozenColumns : [ [ {
				field : 'resvId',
				title : '预约编号',
				width : 110,
				sortable : true
			}, {
				field : 'carId',
				title : '车辆编号',
				width : 70,
				sortable : true
			}, {
				field : 'carLicense',
				title : '车辆牌照',
				width : 70,
				sortable : true
			}, {
				field : 'carVin',
				title : 'VIN号',
				width : 140,
				sortable : true
			}, {
				field : 'carMotorId',
				title : '发动机号',
				width : 100,
				sortable : true
			}, {
				field : 'customId',
				title : '客户名称',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.customName;
				}
			}, {
				field : 'customName',
				title : '客户名称',
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'resvEnterTime',
				title : '预约进店时间',
				width : 140,
				sortable : true
			}, {
				field : 'reptId',
				title : '维修类别',
				width : 80,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return rowData.reptName;
				}
			}, {
				field : 'reptName',
				title : '维修类别',
				width : 60,
				hidden : true
			}, {
				field : 'repwkId',
				title : '维修工位',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.repwkName;
				}
			}, {
				field : 'repwkName',
				title : '维修工位',
				width : 80,
				sortable : true,
				hidden : true
			}, {
				field : 'resvStatus',
				title : '预约状态',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.resvStatusName;
				}
			}, {
				field : 'resvDistance',
				title : '里程数',
				width : 60,
				sortable : true
			}, {
				field : 'resvRealTime',
				title : '预约登记时间',
				width : 140,
				sortable : true
			}, {
				field : 'stfId',
				title : '接待员',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.stfName;
				}
			}, {
				field : 'stfName',
				title : '接待员',
				width : 60,
				sortable : true,
				hidden : true
			}, {
				field : 'resvFixpel',
				title : '托修人',
				width : 60,
				sortable : true
			}, {
				field : 'resvFixtel',
				title : '托修人电话',
				width : 90,
				sortable : true
			}, {
				field : 'resvFixphone',
				title : '托修人手机',
				width : 90,
				sortable : true
			}, {
				field : 'repgrpId',
				title : '维修班组',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.repgrpName;
				}
			}, {
				field : 'repgrpName',
				title : '维修班组',
				width : 80,
				sortable : true,
				hidden : true
			}, {
				field : 'resevationRepPer',
				title : '保险托修人',
				width : 60,
				sortable : true
			}, {
				field : 'resvRemark',
				title : '备注',
				width : 300
			}, {
				field : 'rtrServices',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrSatisfaction',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReportTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrIdea',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReplyTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrIsCome',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReason',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrInTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrOutTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrCsr',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReturnVisitTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrRemarke',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrId',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrServices',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrSatisfaction',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReportTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrIdea',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReplyTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrIsCome',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrReason',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrInTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrOutTime',
				title : '',
				width : 50,
				hidden : true
			}, {
				field : 'rtrCsr',
				title : '',
				width : 50,
				hidden : true
			}] ],
			onDblClickRow : function(rowIndex, data) {
				staticFrtInsurePrizeParts=null;
				staticFrtInsurePrizeItems=null;
				staticFrtInsurePrizeVehicleStructure=null;
				//$('#resevationSelect').linkbutton('disable');
				$('#frtInsurePrizeAddForm').form('clear');
				$('#frtInsurePrizeRushToRepairForm').form('clear');
				$('#frtInsurePrizeTabs').tabs('select', '保险估价明细');
				$('#frtInsurePrizeAddForm').form('load', data);
				$('#frtInsurePrizeRushToRepairForm').form('load', data);
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId=' + data.resvId
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=' + data.resvId
				});
				
				$('#frtInsurePrizeItemDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findItemByResvId.action?resvId=' + data.resvId
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeParts=data;
					}
				});
				$('#frtInsurePrizeItemDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeItems=data;
					}
				});
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeVehicleStructure=data;
						loadIcons();
					}
				});
				var carId=$('#frtInsurePrize_details_carLicense').combobox('getValue');
				if(carId==null||carId.length==0)
					carId=-1;
				$('#frtWorkOrderItemDatagrid').treegrid({
					url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+carId
				});
			}
		});
	});