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
			//工单综合查询->工单->维修配件
			$maintenanceTrafficAnalysis_serviceTimeAnalyze = $('#maintenanceTrafficAnalysis_serviceTimeAnalyze');
			
			$maintenanceTrafficAnalysis_serviceTimeAnalyze.datagrid({
				url : 'maintenanceTrafficAnalysisAction!findMaintenanceTrafficAnalysisServiceTimeAnalyze.action',
				fit : true,
				fitColumns:true,
				nowrap:false,
				singleSelect:true,
				pagination:true,
				rownumbers:true,
				showFooter:true,
				loadMsg : "数据加载中，请稍后……",
				columns :[[
					{field:'enrolDate',title:'登记时间',width:100,sortable:false},
					{field:'eightBefore',title:'08前',width:60,sortable:false},         
					{field:'eightAndNine',title:'08-09',width:60,sortable:false},         
					{field:'nineAndTen',title:'09-10',width:60,sortable:false},         
					{field:'tenAndEleven',title:'10-11',width:60,sortable:false},         
					{field:'elevenAndTwelve',title:'11-12',width:60,sortable:false},         
					{field:'tweleveAndThirteen',title:'12-13',width:60,sortable:false},         
					{field:'thirteenAndFourteen',title:'13-14',width:60,sortable:false},         
					{field:'fourteenAndFifteen',title:'14-15',width:60,sortable:false},         
					{field:'fifteenAndSixteen',title:'15-16',width:60,sortable:false},         
					{field:'sixteenAndSeventeen',title:'16-17',width:60,sortable:false},         
					{field:'seventeenAndEighteen',title:'17-18',width:60,sortable:false},
					{field:'enighteenAfter',title:'18后',width:60,sortable:false},
					{field:'sumCount',title:'累计',width:100,sortable:false}
				]],
				onSelect:function(rowIndex, rowData){
					if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
						analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');
						document.getElementById("cakeMapImg").innerHTML=
						"<img  onload=\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\" src=\"maintenanceTrafficAnalysisAction!findServiceTimeAnalyzeCakeMap.action?page=1&rows=1&enrolDate="+rowData.enrolDate+"&"+
						$('#maintenanceTrafficAnalysisQueryForm').serialize()+" \"/>";					
					}else{
						alertMsg('对不起，请输入正确的查询条件！', 'warning');
					}
				},
				onClickCell:function(rowIndex, field, value){
					if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
						analyseLoader('analyseLoaderSnapMap', 'snapMapImg');
						var data=$maintenanceTrafficAnalysis_serviceTimeAnalyze.datagrid('options');
						document.getElementById("snapMapImg").innerHTML=
						"<img  onload=\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\" src=\"maintenanceTrafficAnalysisAction!findServiceTimeAnalyzeSnapMap.action?page="+data.pageNumber+"&rows="+data.pageSize+"&"+
						$('#maintenanceTrafficAnalysisQueryForm').serialize()+"&selectedField="+field+" \" />";
					}else{
						alertMsg('对不起，请输入正确的查询条件！', 'warning');
					}
				},
				onLoadSuccess:function(data){
					if(staticFlag==true){
						if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
							analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');
							analyseLoader('analyseLoaderSnapMap', 'snapMapImg');
							var params=$maintenanceTrafficAnalysis_serviceTimeAnalyze.datagrid('options');
							document.getElementById("snapMapImg").innerHTML=
							"<img onload=\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\" src=\"maintenanceTrafficAnalysisAction!findServiceTimeAnalyzeSnapMap.action?page="+params.pageNumber+"&rows="+params.pageSize+"&"+
								$('#maintenanceTrafficAnalysisQueryForm').serialize()+" \" />";
							document.getElementById("cakeMapImg").innerHTML=
							"<img onload=\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\" src=\"maintenanceTrafficAnalysisAction!findServiceTimeAnalyzeCakeMap.action?page="+params.pageNumber+"&rows="+params.pageSize+"&"+
								$('#maintenanceTrafficAnalysisQueryForm').serialize()+" \"/>";
						}else{
							alertMsg('对不起，请输入正确的查询条件！', 'warning');
						}	
					}
				}
			});
		}
		
		function queryMaintenanceTrafficAnalysisServiceTimeAnalyze(){
			if($('#maintenanceTrafficAnalysisQueryForm').form('validate')){
				if(staticFlag!=true)
					staticFlag=true;
				$('#maintenanceTrafficAnalysis_serviceTimeAnalyze').datagrid('load',serializeObject($('#maintenanceTrafficAnalysisQueryForm')));		
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function _clear(){
			$('#maintenanceTrafficAnalysisQueryForm').form('clear');
		} 
		function _exceptMaintenanceTrafficAnalysisServiceTimeAnalyze(){
			showEditDialog("maintenanceTrafficAnalysis_serviceTimeAnalyze",null,"maintenanceTrafficAnalysis_serviceTimeAnalyze_center","开始导出","导出配置",0,_callbackMaintenanceTrafficAnalysisServiceTimeAnalyze);
		}
		function _callbackMaintenanceTrafficAnalysisServiceTimeAnalyze(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"维修时间段分析"+getSystemTime());
		}