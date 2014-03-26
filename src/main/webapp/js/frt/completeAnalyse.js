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

		$('#receptionEndTimeEnd').val(getSystemTime2());
		getStartTime($('#receptionEndTimeBegin'));
		$('#completeAnalyseDatagrid').datagrid({
			url : 'frtReceptionAction!completeAnalyse.action',
			fit : true,
			fitColumns:true,
			nowrap:false,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			showFooter:true,
			loadMsg : "数据加载中，请稍后……",
			columns :[[
				{field:'receptionId',title:'工单号',width:60,sortable:false},
				{field:'carLicense',title:'车辆牌照',width:60,sortable:false},         
				{field:'customName',title:'客户名称',width:60,sortable:false},         
				{field:'reptName',title:'维修类别',width:60,sortable:false},  
				{field:'interDate',title:'进厂时间',width:100,sortable:false},         
				{field:'receptionEndTime',title:'计划完工时间',width:100,sortable:false},
				{field:'receptionFactTime',title:'实际完工时间',width:100,sortable:false},
				{field:'differenceTime',title:'时间差(分钟)',width:60,sortable:false
					,formatter : function(value, row, index) {
						if(row.flag!=null&&row.flag==true)
							return "<font style='color:green;'>"+value+"</font>";
						else if(row.flag!=null&&row.flag==false)
							return "<font style='color:red;'>"+value+"</font>";
						else
							return "<font style='color:blue;'>"+value+"</font>";
					}
				},
				{field:'flag',title:'完工状态',width:60,sortable:false
					,formatter : function(value, row, index) {
						if(value!=null&&value==true)
							return "准时完工";
						else if(value!=null&&value==false)
							return "延迟完工";
						else
							return "未完工";
					}
				}
			]]
		});
	}
	var _search=function(){
		if($('#completeAnalyseQueryForm').form('validate')){
			$('#completeAnalyseDatagrid').datagrid('load',serializeObject($('#completeAnalyseQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _clear=function(){
		$('#completeAnalyseQueryForm').form('clear');
		$('#completeAnalyseDatagrid').datagrid('load', serializeObject($('#completeAnalyseQueryForm')));
	}
	function _except(){
		showEditDialog("completeAnalyseDatagrid",null,"completeAnalyseDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"完工分析"+getSystemTime());
	}