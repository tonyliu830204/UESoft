function LoadOk() {
	if (document.readyState == "complete") {
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame() {
	runs();
	preclrPartsForReceptionId();
	claimsParts();
}
setTimeout("LoadOk();", 200);
var runs=function(){
	$('#workOrderPartsTabs').tabs({   
		onSelect:function(title){
			if(title =='工单配件查询'){
				unbindAllButton();
				$("#_search").bind("click",queryWorkOrderParts);  
				$("#_clear").bind("click",clearWorkOrderParts);
				$("#_export").bind("click",_exportWorkOrderParts);
			}else if(title =='未确认索赔配件查询'){
				unbindAllButton();  
				$("#_search").bind("click",queryUnfinishClaimParts);  
				$("#_clear").bind("click",clearUnfinishClaimParts);
				$("#_export").bind("click",_exportUnfinishClaimParts);
			}
		}
	});
}
function unbindAllButton(){
	$("#_search").unbind();
	$("#_clear").unbind();
	$("#_export").unbind();
	$('#_search').linkbutton('enable');
	$('#_clear').linkbutton('enable');
	$('#_export').linkbutton('enable');
}
var queryWorkOrderParts=function(){
	var value=$('#workOrderParts_sortWay').combobox('getValue');
	if($('#workOrderPartsForm').form('validate')){
		if(value!=null&&value=='true'){
			preclrPartsForParts();
			var url="workOrderPartsQueryAction!loadFrtPreClearingForParts.action?";
			url+=$('#workOrderPartsForm').serialize();
			$('#workOrderPartsDatagrid').treegrid({
				url:url
			});
		}else{
			preclrPartsForReceptionId();
			var url="workOrderPartsQueryAction!loadFrtPreClearing.action?";
			url+=$('#workOrderPartsForm').serialize();
			$('#workOrderPartsDatagrid').treegrid({
				url:url
			});		
		}
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
var clearWorkOrderParts=function(){
	$('#workOrderPartsForm').form('clear');
}
function _exportWorkOrderParts(){
	showEditDialog("workOrderPartsDatagrid",null,"workOrderPartsDatagrid_center","开始导出","导出配置",0,_callbackExceptWorkOrderParts);
}
function _callbackExceptWorkOrderParts(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"工单配件查询"+getSystemTime());
}
var queryUnfinishClaimParts=function(){
	if($('#unfinishClaimPartsForm').form('validate')){
		$('#unfinishClaimPartsDatagrid').datagrid('load', serializeObject($('#unfinishClaimPartsForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
var clearUnfinishClaimParts=function(){
	$('#unfinishClaimPartsForm').form('clear');
}
function _exportUnfinishClaimParts(){
	showEditDialog("unfinishClaimPartsDatagrid",null,"unfinishClaimPartsDatagrid_center","开始导出","导出配置",0,_callbackExceptUnfinishClaimParts);
}
function _callbackExceptUnfinishClaimParts(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"未确认索赔配件查询"+getSystemTime());
}
var preclrPartsForReceptionId=function(){
	$('#workOrderPartsDatagrid').treegrid({
		url : 'workOrderPartsQueryAction!loadFrtPreClearing.action',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		idField : 'receptionId',
		treeField : 'ctypeName',
		columns : [[  
            {field:'_parentId',title:'编号',width:60,hidden : true},
		 	{field:'receptionId',title:'工单号',width:110,},
			{field:'preclrTime',title:'结算日期',width:130},
			{field:'carLicense',title:'车牌号',width:60,sortable:false},         
			{field:'ctypeName',title:'车类型',width:80,sortable:false},         
			{field:'stomId',title:'出退库单',width:110,sortable:false},        
			{field:'stomDate',title:'出退日期',width:130,sortable:false},         
			{field:'partsId',title:'配件编码',width:70,sortable:false},         
			{field:'partsName',title:'配件名称',width:70,sortable:false},         
			{field:'punitName',title:'单位',width:60,sortable:false},         
			{field:'partsLibrary',title:'库位',width:60,sortable:false},         
			{field:'claimsType',title:'索赔',width:90,sortable:false},         
			{field:'itemCount',title:'数量',width:100,sortable:false},         
			{field:'itemPrice',title:'销售价',width:60,sortable:false},
			{field:'itemAmount',title:'销售金额',width:60,sortable:false},   
			{field:'itemPriceAvage',title:'平均价',width:60,sortable:false},   
			{field:'taxCastamont',title:'成本合计',width:60,sortable:false},   
			{field:'taxCastamontAvage',title:'成本均价',width:60,sortable:false}				
		]],
		onloadSuccess: function (){
			//delete $(this).treegrid('options').queryParams['receptionId'];
		},
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			$('#workOrderParts_receptionId').val(rowData.receptionId);	
			var url = 'workOrderPartsQueryAction!loadStOutAndStRtPartsDetail.action?';
			url+=$('#workOrderPartsForm').serialize();
			$('#workOrderPartsDatagrid').treegrid("options").url = url;
			return true;
		}
	});
}
var preclrPartsForParts=function(){
	$('#workOrderPartsDatagrid').treegrid({
		url : 'workOrderPartsQueryAction!loadFrtPreClearingForParts.action',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		idField : 'partsId',
		treeField : 'receptionId',
		columns : [[  
            {field:'_parentId',title:'编号',width:60,hidden : true},
			{field:'partsId',title:'配件编码',width:70,sortable:false},         
			{field:'partsName',title:'配件名称',width:70,sortable:false},         
			{field:'punitName',title:'单位',width:60,sortable:false},         
			{field:'partsLibrary',title:'库位',width:60,sortable:false},         
			
		 	{field:'receptionId',title:'工单号',width:180,sortable:false},
			{field:'carLicense',title:'车牌号',width:60,sortable:false},        
			{field:'stomId',title:'出退库单',width:110,sortable:false},        
			{field:'stomDate',title:'出退日期',width:130,sortable:false},         
			{field:'claimsType',title:'索赔',width:90,sortable:false},         
			{field:'itemCount',title:'数量',width:100,sortable:false},         
			{field:'itemPrice',title:'销售价',width:60,sortable:false},
			{field:'itemAmount',title:'销售金额',width:60,sortable:false},  
			{field:'itemPriceAvage',title:'平均价',width:60,sortable:false},   
			{field:'taxCastamont',title:'成本合计',width:60,sortable:false},   
			{field:'taxCastamontAvage',title:'成本均价',width:60,sortable:false}				
		]],
		onloadSuccess: function (){
			//delete $(this).treegrid('options').queryParams['receptionId'];
		},
		onBeforeExpand : function (rowData){
			//动态设置展开查询的url
			$('#workOrderParts_partsId').val(rowData.partsId);	
			var url = 'workOrderPartsQueryAction!loadStOutAndStRtPartsDetailForParts.action?';
			url+=$('#workOrderPartsForm').serialize();
			$('#workOrderPartsDatagrid').treegrid("options").url = url;
			return true;
		}
	});
}
var claimsParts=function(){
		//未确认索赔配件信息查询
		$('#unfinishClaimPartsDatagrid').datagrid({
			url : 'workOrderPartsQueryAction!loadFinClaimantParts.action',
			fit : true,
			fitColumns : true,
			rownumbers : true,
			sortOrder:'asc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			pagination : true,
			singleSelect : true,
			columns : [[{
				field : 'claimantpIndex',
				title : '序号',
				width : 90,
				hidden : true
			},{
				field : 'partsId',
				title : '配件编码',
				width : 75
			},{
				field : 'partsName',
				title : '配件名称',
				width : 75
			},{
				field : 'claimantpCount',
				title : '数量',
				width : 75
			},{
				field : 'claimantpAmonut',
				title : '索赔金额',
				width : 75
			},{
				field : 'preclrTime',
				title : '结算时间',
				width : 130
			}]]
		});
	}