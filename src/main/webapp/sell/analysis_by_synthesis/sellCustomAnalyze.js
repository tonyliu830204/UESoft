var staticFlag=false; 
	
$(function(){
	$('#sellCustomAnalyze').datagrid({
	url : 'sellCustomAnalyseAction!querySellCustomInfor.action',
	pagination : true,
	fit:true,
	fitColumns : true,
	sortOrder:'asc',
	sortName:'cuId',
	singleSelect : true,
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	rownumbers : true,
	loadMsg : "数据加载中，请稍后……",
	columns : [ [ 
            {
	            field : 'cuId',
				title : '编号',
				width : 60,
				hidden:true,
				sortable : true,
            },{

			field : 'cuName',
			title : '名称',
			width : 150,
			sortable : true,
			formatter : function(value,rowData,rowIndex){
			if(value==null||value==""){
				return "未知";
			}else{
				return value;	
			}
            }},{
				field : 'num',
				title : '数量',
				width : 100,
				sortable : true
            },{
				field : 'percent',
				title : '百分比',
				width : 130,
				sortable : true,
				formatter : function(value,rowData,rowIndex){
				if(value!=0){
					return value+"%";	
				}
				return value;				
            }}]], onLoadSuccess:function(rowData){
				if ($('#QueryForms').form('validate')){
					var data=$('#sellCustomAnalyze').datagrid('options');
					document.getElementById("cakeMapImg").innerHTML="<img src=\"sellCustomAnalyseAction!findAnalyzeCakeMap.action?page="+data.pageNumber+"&rows="+data.pageSize+"&"+$('#QueryForms').serialize()+"\"/>";
					//初试时间
					initSearchDate($('#xsCarSelData'),$('#xsCarSelData2'));
				 }else {
					 alertMsg('对不起，请输入正确的查询条件！', 'warning');
				 }		
			 }
			// onSelect:function(rowIndex,rowData){
			// if ($('#QueryForms').form('validate')){
		  	// 	document.getElementById("cakeMapImg").innerHTML="<img src=\"sellCustomAnalyseAction!findAnalyzeCakeMap.action?page=1&rows=1&enrolDate="+rowData.enrolDate+"&"+$('#QueryForms').serialize()+"\"/>";
			// }else {
			// 	alertMsg('对不起，请输入正确的查询条件！', 'warning');
			// }		
			//}
			//,onBeforeLoad:function(params){
			//if(staticFlag==true){
					//if($('#QueryForms').form('validate')){
						//document.getElementById("snapMapImg").innerHTML=
						//"<img src=\"maintenanceTrafficAnalysisAction!findServiceTimeAnalyzeSnapMap.action?page="+params.page+"&rows="+params.rows+"&"+
						//	$('#maintenanceTrafficAnalysisQueryForm').serialize()+" \" />";
						//document.getElementById("cakeMapImg").innerHTML=
						//"<img src=\"sellCustomAnalyseAction!findAnalyzeCakeMap.action?page="+params.page+"&rows="+params.rows+"&"+
						//	$('#QueryForms').serialize()+" \"/>";
				//	}else{
					//	alertMsg('对不起，请输入正确的查询条件！', 'warning');
					//}	
				//}
			//}
    });
					
	        });

		function queryReceiveAnalyze(){
			if($('#QueryForms').form('validate')){
//				$('#sellCustomAnalyze').datagrid('load',serializeObject($('#QueryForms')));
//				addInitDate($('#xsCarSelData'),$('#xsCarSelData2'));
				$('#sellCustomAnalyze').datagrid('load',serializeObject($('#QueryForms')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
			
		}
		function _clear(){
			$('#QueryForms').form('clear');
			$('#sellCustomAnalyze').datagrid('load',serializeObject($('#QueryForms')));
			$('#car_Brand_id').combobox('reload');
			$('#car_Model_id').combobox('reload','carModelAction!findAllCarModel.action');
		} 
		
		function _exceptMaintenanceTrafficAnalysisServiceReceiveAnalyze(){
			var data =  $("#sellCustomAnalyze").datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能导出！', 'warning');
				 return ;
			 }
			showEditDialog("sellCustomAnalyze",null,"sellCustomAnalyze_center","开始导出","导出配置",0,_callbackMaintenanceTrafficAnalysisServiceReceiveAnalyze);
		}
		function _callbackMaintenanceTrafficAnalysisServiceReceiveAnalyze(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"销售客户分析"+getSystemTime());
		}
		
		function _config(){

			var data =$('#sellCustomAnalyze').datagrid('getData'); 
			 if(data.rows.length==0){
				 alertMsg('数据列表为空，不能打印！', 'warning');
				 return ;
			 }
			showEditDialog("sellCustomAnalyze",personnelSumTable,"sellCustomAnalyze_center","开始打印","打印配置",2,_print);

	}
	/**
	 * 打印字段设置回调函数
	 * 将选择的列打印
	 * @return
	 */
	function _print(parentId,fieldNames){
		printEsuyUIPreview(parentId,fieldNames);
	}
