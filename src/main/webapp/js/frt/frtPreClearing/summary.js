var num;
	function LoadOk() {
		if (document.readyState == "complete") {
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url:'baseAction!loadNumberbit.action',  
				   success: function(r){
					num=r.ciValue;
					initFrame();	
				   },
				   error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				   
			});
			
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		$('#frtPreClearingTabs').tabs('select', '结算单明细');
		$('#frtPreClearingTabs').tabs('select', '结算单汇总');
		loadSummaryDatagrid();
	}
	setTimeout("LoadOk();", 200);
	
	function loadSummaryDatagrid() {
		$('#preclrTimeEnd').val(getSystemTime2());
		loadPreClrTime1($('#preclrTimeBegin'));/*默认结算查询包时分秒*/		
		findCarLicenseFormat("frtPreClearing_summary_carId");//查找默认车牌照格式
		//交车结算datagrid
		$frtPreClearingSummaryDatagrid = $('#frtPreClearingSummaryDatagrid');
		$frtPreClearingSummaryDatagrid.datagrid({
			url : 'frtPreClearingAction!datagridFrtPreClearing.action',
			singleSelect : true,
			pagination : true,
			fit : true,
			rownumbers : true,
			fitColumns : false,
			loadMsg : "数据加载中，请稍后……",
			frozenColumns : [ [ {
				field : 'preclrId',
				title : '结算单号',
				width : 110,
				sortable : true
			}, {
				field : 'receptionId',
				title : '维修工单号',
				width : 110,
				sortable : true
			}, {
				field : 'carId',
				title : '车牌照',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.carLicense;
				}
			},{
				field : 'carVin',
				title : 'VIN码',
				hidden : true,
				width : 80
			},{	
				field : 'carRelationTel1',
				title : '电话号码',
				hidden : true,
				width : 80
			},{	
				field : 'reptName',
				title : '维修类别',
				sortable : true,
				width : 80
			}, {
				field : 'carMotorId',
				title : '发动机号',
				width : 80,
				sortable : true
			}, {
				field : 'customName',
				title : '客户名称',
				width : 60,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'preclrTime',
				title : '结算时间',
				width : 140,
				sortable : true
			}, {
				field : 'preclrInoiceType',
				title : '票据类型',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.tempName1;
				}
			}, {
				field : 'preclrInvoiceTime',
				title : '开票时间',
				width : 140,
				sortable : true
			}, {
				field : 'preclrNo',
				title : '票据编号',
				width : 100,
				sortable : true
			}, {
				field : 'preMprMatAmount',
				title : '材料费用合计(未折扣)',
				width : 130,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preMprMatAmount.toFixed(num);
				}
			}, {
				title : '材料费用合计(已折扣)',
				field : 'preMprMatAmountOld',
				width : 130,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preMprMatAmountOld.toFixed(num);
				}
			}, {
				field : 'preclrMaterialRate',
				title : '材料折扣率',
				width : 70,
				sortable : true
			}, {
				field : 'preclrWktimeAmount',
				title : '工时费用合计',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrWktimeAmount.toFixed(num);
				}
			}, {
				field : 'preclrWktimeRate',
				title : '工时折合扣率',
				width : 80,
				sortable : true
			}, {
				field : 'preclrOtherAmount',
				title : '其他费用',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrOtherAmount.toFixed(num);
				}
			}, {
				field : 'preclrSumAmount',
				title : '总费用合计',
				width : 70,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrSumAmount.toFixed(num);
				}
			}, {
				field : 'preclrSumRate',
				title : '合计折扣率',
				width : 70,
				sortable : true
			}, {
				field : 'preclrRealAmount',
				title : '实际费用',
				width : 60,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrRealAmount.toFixed(num);
				}
			}, {
				field : 'preclrNoTaxCost',
				title : '成本费用(未税)',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrNoTaxCost.toFixed(num);
				}
			}, {
				field : 'preclrTaxCost',
				title : '成本费用(含税)',
				width : 100,
				sortable : true,
				formatter : function(value, row, index) {
					return row.preclrTaxCost.toFixed(num);
				}
			},{	
				field : 'stfId',
				title : '接待员',
				width : 80,
				sortable : true,
				formatter : function(value, row, index) {
					return row.stfName;
				}
			}, {
				field : 'preclrInstr',
				title : '结算说明',
				width : 60
			}, {
				field : 'preclrRemark',
				title : '备注',
				width : 120
			}, {
				field : 'preclrManagementFee',
				title : '管理费',
				width : 120,
				hidden : true
			} , {
				field : 'finComId',
				title : '索赔厂商编号',
				width : 120,
				hidden : true
			} , {
				field : 'finTag',
				title : '索理赔',
				width : 120,
				hidden : true
			} , {
				field : 'finAllTag',
				title : '全额索理赔',
				width : 120,
				hidden : true
			}, {
				field : 'preclrToMoney',
				title : '转收银',
				hidden : true
			}  ] ],
			onDblClickRow : function(rowIndex, rowData) {
				staticFrtPreClearingParts=null;
				$('#frtPreClearingAddForm').form('clear');
				$('#frtPreClearingExpenseSituationForm').form('clear');
				$('#claimMoneyForm').form('clear');
				$('#frtPreClearingTabs').tabs('select', '结算单明细');
				$('#frtPreClearingAddForm').form('load', rowData);
				$('#frtPreClearingExpenseSituationForm').form('load', rowData);
				//$('#addPreclrRealAmount').numberbox('setValue',rowData.preclrRealAmount);
				$('#claimMoneyForm').form('load', rowData);
				
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					url: 'frtPreClearingAction!findPreCostById.action?preclrId=' + rowData.preclrId
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					url : 'frtPreClearingAction!findPrePartsById.action?preclrId=' + rowData.preclrId
				});
				
				$('#frtPreClearingItemDatagrid').datagrid({
					url : 'frtPreClearingAction!findPreItemById.action?preclrId=' + rowData.preclrId
				});
				$('#frtPreClearingExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+rowData.receptionId
				});
				$('#frtPreClearingExpenseSituationOtherExpense').datagrid({
					onLoadSuccess : function (data){
					}
				});
				$('#frtPreClearingPartsDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtPreClearingParts=data;					
					}
				});
				$('#frtPreClearingItemDatagrid').datagrid({
					onLoadSuccess : function (data){			
					}
				});
				//frtPreClearingAddFormKeyUp();
				$('#frtWorkOrderItemDatagrid').treegrid({
					url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+rowData.carId
				});
			}
		});
	}
	
		/*计算收费额*/
	function tote(){
		$.ajax({
				type : 'post',
				url : 'frtReceptionAction!totemoney.action',
				dataType : 'json',
				success : function(r) {
					$('#preclrWktimeAmount').val(r[0]);
					$('#preMprMatAmount').val(r[1]);
					$('#preclrManagementFee').val(r[2]);
					$('#otherAmount').val(r[3]);
					$('#amountTotal').val(r[4]);
					//$('#frtReceptionExpenseSituationOtherExpense').datagrid('loadData', r);
					//var data = $('#frtReceptionExpenseSituationOtherExpense').datagrid('getData');
					
				}
			});
	}