var tag=false;
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
		$('#endTime').val(getSystemTime());
		getStartDate($('#beginTime'));
		$('#bespeakAnalyseDatagrid').datagrid({
			url : 'bespeakAnalyseAction!findBespeakAnalyse.action',
			fit : true,
			fitColumns:true,
			nowrap:false,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			showFooter:true,
			loadMsg : "数据加载中，请稍后……",
			columns :[[
				{field:'enrolTime',title:'登记时间段',width:100,sortable:false},
				{field:'sumCount',title:'总数量',width:60,sortable:false},         
				{field:'successCount',title:'预约成功数量',width:60,sortable:false},         
				{field:'failCount',title:'预约失败数量',width:60,sortable:false},  
				{field:'continueCount',title:'预约中数量',width:60,sortable:false},         
				{field:'successRate',title:'预约成功率',width:60,sortable:false,
					formatter : function(value, row, index) {
						var temp=value*100;
						var temps=temp+"";
						if(!(temps.indexOf(".")==-1)&&(temps.indexOf(".")+3)<=(temps.length)){
							temp=temps.substring(0,(temps.indexOf(".")+3));
						}
						return temp+"%";
					}
				}
			]],
			onSelect:function(rowIndex, rowData){
				if($('#bespeakAnalyseQueryForm').form('validate')){
					analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');
					document.getElementById("cakeMapImg").innerHTML=
					"<img onload=\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\" src=\"bespeakAnalyseAction!findCakeMap.action?page=1&rows=1&enrolTime="+rowData.enrolTime+"&"+
					$('#bespeakAnalyseQueryForm').serialize()+" \"/>";
				}else{
					alertMsg('对不起，请输入正确的查询条件！', 'warning');
				}
			},
			onClickCell:function(rowIndex, field, value){
				if($('#bespeakAnalyseQueryForm').form('validate')){
					analyseLoader('analyseLoaderSnapMap', 'snapMapImg');
					var data=$('#bespeakAnalyseDatagrid').datagrid('options');
					document.getElementById("snapMapImg").innerHTML=
					"<img  onload=\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\" src=\"bespeakAnalyseAction!findSnapMap.action?page="+data.pageNumber+"&rows="+data.pageSize+"&"+
							$('#bespeakAnalyseQueryForm').serialize()+"&selectedField="+field+" \" />";
				}else{
					alertMsg('对不起，请输入正确的查询条件！', 'warning');
				}
				
			},
			onLoadSuccess:function(data){
				if(tag){
					if($('#bespeakAnalyseQueryForm').form('validate')){
						analyseLoader('analyseLoaderCakeMap', 'cakeMapImg');
						analyseLoader('analyseLoaderSnapMap', 'snapMapImg');
						var params=$('#bespeakAnalyseDatagrid').datagrid('options');
						document.getElementById("snapMapImg").innerHTML=
							"<img  onload=\"analyseLoaderHidden('analyseLoaderSnapMap','snapMapImg');\" src=\"bespeakAnalyseAction!findSnapMap.action?page="+params.pageNumber+"&rows="+params.pageSize+"&"+
							$('#bespeakAnalyseQueryForm').serialize()+" \" />";
						document.getElementById("cakeMapImg").innerHTML=
							"<img  onload=\"analyseLoaderHidden('analyseLoaderCakeMap','cakeMapImg');\" src=\"bespeakAnalyseAction!findCakeMap.action?page="+params.pageNumber+"&rows="+params.pageSize+"&"+
							$('#bespeakAnalyseQueryForm').serialize()+" \"/>";
					}else{
						alertMsg('对不起，请输入正确的查询条件！', 'warning');
					}
				}
			}
		});
	}
	var _search=function(){
		if($('#bespeakAnalyseQueryForm').form('validate')){
			tag=true;
			$('#bespeakAnalyseDatagrid').datagrid('load',serializeObject($('#bespeakAnalyseQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _clear=function(){
		$('#bespeakAnalyseQueryForm').form('clear');
	}
	function _except(){
		showEditDialog("bespeakAnalyseDatagrid",null,"bespeakAnalyseDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"预约分析"+getSystemTime());
	}