function LoadOk() {
			if (document.readyState == "complete") {
				carRuns();
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		setTimeout("LoadOk();", 200);
		function carRuns(){
			$maintenanceTrafficAnalysis_servicingCarAnalyze = $('#maintenanceTrafficAnalysis_servicingCarAnalyze');
			
			$maintenanceTrafficAnalysis_servicingCarAnalyze.treegrid({
				url : 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServicingCarAnalyze.action',
				fit : true,
				pagination : true,
				fitColumns : true,
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				idField:'cbrdId',
				treeField:'carLicense',
				frozenColumns :[[
					{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'cbrdId',title:'车辆品牌编号',width:80,hidden : true},
					{field:'cbrdName',title:'车辆品牌',width:110,sortable:false}
				]],
				columns : [[ 
					{field:'carLicense',title:'车辆牌照',width:100,sortable:false},         
					{field:'ctypeName',title:'车辆型号',width:100,sortable:false},         
					{field:'customName',title:'客户名称',width:60,sortable:false},
					{field:'carLastRepairDate',title:'最后维修日期',width:60,sortable:false},         
					{field:'carRepairCnt',title:'维修次数',width:70,sortable:false},         
					{field:'customAddr',title:'客户地址',width:60,sortable:false},         
					{field:'customTel1',title:'电话一',width:140,sortable:false},         
					{field:'customTel2',title:'电话二',width:90,sortable:false}
				]],
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServicingCarAnalyzeByCbrdId.action?'+$('#maintenanceTrafficAnalysisQueryForm').serialize()+'&_parentId='+rowData.cbrdId;
						$('#maintenanceTrafficAnalysis_servicingCarAnalyze').treegrid("options").url = url;
						return true;
				}
			});
		}
		
		function queryMaintenanceTrafficAnalysisServicingCarAnalyze(){
			if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
//				carRuns();
				var url="maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServicingCarAnalyze.action?";
				url+=$('#maintenanceTrafficAnalysisQueryForm').serialize();
				$('#maintenanceTrafficAnalysis_servicingCarAnalyze').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function _clear(){
			$('#maintenanceTrafficAnalysisQueryForm').form('clear');
		} 
		function _exceptMaintenanceTrafficAnalysisServicingCarAnalyze(){
			showEditDialog("maintenanceTrafficAnalysis_servicingCarAnalyze",null,"maintenanceTrafficAnalysis_servicingCarAnalyze_center","开始导出","导出配置",0,_callbackMaintenanceTrafficAnalysisServicingCarAnalyze);
		}
		function _callbackMaintenanceTrafficAnalysisServicingCarAnalyze(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"车辆在修情况分析"+getSystemTime());
		}