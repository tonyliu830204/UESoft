function LoadOk() {
		if (document.readyState == "complete") {
			runs();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	setTimeout("LoadOk();", 200);
	var runs=function (){
		$('#claimantmTimeEnd').val(getSystemTime());
		getStartTime($('#claimantmTimeBegin'));
		$('#claimCostContrastAnalyseDatagrid').treegrid({
			url : 'finClaimantMainAction!claimCostContrastAnalyse.action',
			fit : true,
			fitColumns:true,
			nowrap:false,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			showFooter:true,
			animate:true,
			autoRowHeight:true,
			loadMsg : "数据加载中，请稍后……",
			idField : 'claimantmId',
			treeField : 'partsName',
			columns :[[
				{field:'claimantmId',title:'索赔单号',width:110,sortable:false},
				{field:'claimantmTime',title:'索赔结算时间',width:120,sortable:false},
				{field:'partsName',title:'索赔配件',width:120,sortable:false,
					formatter : function(value, row, index) {
						if(row.claimantpCount==0){
							return row.partsName+"(<font style='color:red;'>未索赔</font>)";
						}else{
							return row.partsName;
						}
					}
				},         
				{field:'claimantpCount',title:'索赔数量',width:120,hidden:true},
				{field:'claimantmNoTaxCost',title:'索赔实际未税成本',width:100,sortable:false},
				{field:'claimantmTaxCost',title:'索赔实际含税成本',width:100,sortable:false},
				{field:'preclrNoTaxCost',title:'索赔结算未税成本',width:100,sortable:false},
				{field:'preclrTaxCost',title:'索赔结算含税成本',width:100,sortable:false},
				{field:'differenceNoTaxCost',title:'未税成本差额',width:100,sortable:false},
				{field:'differenceTaxCost',title:'含税成本差额',width:100,sortable:false}
			]],
			onBeforeExpand : function (rowData){
				//动态设置展开查询的url
				$('#claimantmId').val(rowData.claimantmId);
				var url = 'finClaimantMainAction!claimCostContrastAnalyseChild.action?';
				url+=$('#claimCostContrastAnalyseForm').serialize();
				$('#claimCostContrastAnalyseDatagrid').treegrid("options").url = url;
				return true;
			}
		});
	}
	var _search=function(){
		if($('#claimCostContrastAnalyseForm').form('validate')){
			//runs();
			var url="finClaimantMainAction!claimCostContrastAnalyse.action?";
			url+=$('#claimCostContrastAnalyseForm').serialize();
			$('#claimCostContrastAnalyseDatagrid').treegrid({
				url:url
			});	
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _clear=function(){
		$('#claimCostContrastAnalyseForm').form('clear');
		var url="finClaimantMainAction!claimCostContrastAnalyse.action?";
		url+=$('#claimCostContrastAnalyseForm').serialize();
		$('#claimCostContrastAnalyseDatagrid').treegrid({
			url:url
		});	
		//$('#claimCostContrastAnalyseDatagrid').datagrid('load', serializeObject($('#claimCostContrastAnalyseForm')));
	}
	function _except(){
		showEditDialog("claimCostContrastAnalyseDatagrid",null,"claimCostContrastAnalyseDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"索赔成本对比分析"+getSystemTime());
	}