    
	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		loadPartsQuery();
	}
	setTimeout("LoadOk();", 200);
function loadPartsQuery(){
	//前台配件查询datagrid
	 	$frtPartsQueryDatagrid = $('#frtPartsQueryDatagrid');
	 	$frtPartsQueryDatagrid.datagrid({
	 		url : 'frtPartsQueryAction!datagridFrtParts.action',
	 		singleSelect : true,
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [[
			{field:'partsId',title : '编码',width : 60,sortable:true},
			{field:'partsId2',title:'编码二',width : 60,sortable:true},
			{field:'ptypeName',title:'型号名称',width:60,sortable:true},
			{field:'partsName',title:'配件名称',width : 80,sortable:true},
			{field:'partsSimpleId',title:'简码',width : 50,sortable:true}
		  ]],
			columns : [[{
				field : 'posName',
				title : '部位名称',
				width : 80,
				sortable : true
			}, {
				field : 'stateName',
				title : '配件产地',
				width : 80,
				sortable : true
			}, {
				field : 'punitName',
				title : '配件单位',
				width : 60,
				sortable : true
			}, {
				field : 'partsLibrary',
				title : '配件库位',
				width : 60,
				sortable : true
			}, {
				field : 'fitPtype',
				title : '适用车型',
				width : 80,
				sortable : true
			}, {
				field : 'partsNowCount',
				title : '现有库存数量',
				width : 80,
				sortable : true
			}, {
				field : 'storeName',
				title : '所在仓库',
				width : 60,
				sortable : true
			}, {
				field : 'partsRepairPrice',
				title : '维修价',
				width : 60,
				sortable : true
			}, {
				field : 'partsSellPrice',
				title : '销售价',
				width : 60,
				sortable : true
			}, {
				field : 'partsPointPrice',
				title : '网点价',
				width : 60,
				sortable : true
			},{
				field : 'partsSpecialPrice',
				title : '特别价',
				width : 60,
				sortable : true
			},{
				field : 'partsClaimantPrice',
				title : '索赔价',
				width : 60,
				sortable : true
			},{
				field : 'partsLatestTaxprice',
				title : '最新入库含税价',
				width : 90,
				hidden : true
			},{
				field : 'partsLatestNotaxprice',
				title : '最新入库未税价',
				width : 90,
				hidden : true
			},{
				field : 'stockUpper',
				title : '库存上限',
				width : 60,
				hidden : true
			},{
				field : 'stockLower',
				title : '库存下限',
				width : 60,
				hidden : true
			}]]
	 	});
}
		var _query = function (){
			$frtPartsQueryDatagrid.datagrid('load', serializeObject($('#frtPartsQueryForm')));
		}
		
		var _clear = function (){
			$('#frtPartsQueryForm').form('clear');
			$frtPartsQueryDatagrid.datagrid('load', serializeObject($('#frtPartsQueryForm')));
		}
		
		function _except(){
			showEditDialog("frtPartsQueryDatagrid",null,"frtPartsQueryDatagrid_center","开始导出","导出配置",0,_callbackExcept);
		}
		
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"前台配件查询"+getSystemTime());
		}
		
		