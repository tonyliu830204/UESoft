var staticFlag=false;
		function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		function initFrame(){
			runs();
		}
		setTimeout("LoadOk();", 200);
		function runs(){
			$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceReceiveAnalyze.action',
					data:'',
					success : function(r) {
						eval(r);		
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   } 
				   	}
				});
		}
		
		function queryMaintenanceTrafficAnalysisServiceReceiveAnalyze(){
			if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
				if(staticFlag!=true)
					staticFlag=true;
				$('#maintenanceTrafficAnalysis_serviceReceiveAnalyze').datagrid('load',serializeObject($('#maintenanceTrafficAnalysisQueryForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function _clear(){
			$('#maintenanceTrafficAnalysisQueryForm').form('clear');
		} 
		function _exceptMaintenanceTrafficAnalysisServiceReceiveAnalyze(){
			showEditDialog("maintenanceTrafficAnalysis_serviceReceiveAnalyze",null,"maintenanceTrafficAnalysis_serviceReceiveAnalyze_center","开始导出","导出配置",0,_callbackMaintenanceTrafficAnalysisServiceReceiveAnalyze);
		}
		function _callbackMaintenanceTrafficAnalysisServiceReceiveAnalyze(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"维修类别接待员分析"+getSystemTime());
		}