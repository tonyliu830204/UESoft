function LoadOk() {
			if (document.readyState == "complete") {
				serverLogRuns();
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
		var serverLogRuns=function (){
			//工单综合查询->工单->维修配件
			$maintenanceTrafficAnalysis_serviceLog = $('#maintenanceTrafficAnalysis_serviceLog');
			
			$maintenanceTrafficAnalysis_serviceLog.treegrid({
				url : 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceLog.action',
				fit : true,
				pagination : true,
				fitColumns : false,
				rownumbers : true,
		        pageSize : 15,
				loadMsg : "数据加载中，请稍后……",
				idField:'interDate',
				treeField:'interDate',
				frozenColumns :[[
					{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'interDate',title:'进厂日期',width:200,sortable:false}
				]],
				columns : [[ 
					{field:'receptionId',title:'工单号',width:130,sortable:false},         
					{field:'carLicense',title:'车牌号',width:80,sortable:false},         
					{field:'customName',title:'客户名称',width:60,sortable:false},         
					{field:'carTypeName',title:'车辆型号',width:60,sortable:false},         
					{field:'stfName',title:'接待员',width:60,sortable:false},         
					{field:'reptName',title:'维修类别',width:60,sortable:false},         
					{field:'customTel',title:'电话',width:100,sortable:false},         
					{field:'carMotorId',title:'发动机号',width:100,sortable:false},        
					{field:'carVin',title:'VIN号',width:140,sortable:false},         
					{field:'receptionDistance',title:'里程',width:60,sortable:false},         
					{field:'carBuyDate',title:'购买日期',width:130,sortable:false},
					{field:'receptionItems',title:'维修项目',width:240,sortable:false},
					{field:'recommonendPerson',title:'托修人',width:60,sortable:false},
					{field:'recommonendTel',title:'联系电话',width:100,sortable:false},
					{field:'recommonendAddr',title:'地址',width:160,sortable:false}
				]],
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){		
							var url = 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceLogByInterDate.action?';
							url+=$('#maintenanceTrafficAnalysisQueryForm').serialize()+'&_parentId='+rowData.interDate;
							$('#maintenanceTrafficAnalysis_serviceLog').treegrid("options").url = url;
							return true;
						}else{
							alertMsg('对不起，请输入正确的查询条件！', 'warning');
							return false;
						}
				}
			});
		}
		
		function queryMaintenanceTrafficAnalysisServiceLog(){
			if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
				//serverLogRuns();
				var url="maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceLog.action?";
				url+=$('#maintenanceTrafficAnalysisQueryForm').serialize();
				$('#maintenanceTrafficAnalysis_serviceLog').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function _clear(){
			$('#maintenanceTrafficAnalysisQueryForm').form('clear');
		} 
		function _exceptMaintenanceTrafficAnalysisServiceLog(){
			showEditDialog("maintenanceTrafficAnalysis_serviceLog",null,"maintenanceTrafficAnalysis_serviceLog_center","开始导出","导出配置",0,_callbackMaintenanceTrafficAnalysisServiceLog);
		}
		function _callbackMaintenanceTrafficAnalysisServiceLog(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"维修记录查询"+getSystemTime());
		}