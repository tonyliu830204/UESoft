function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
			findCarLicenseFormat("substituteGatheringQueryCarId");
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame(){
			main();
			runs("-1");
			loadOld("-1");
	}
	setTimeout("LoadOk();", 200);
	var main=function(){
		$('#addGatheringEndTime').val(getSystemTime2());
		loadPreClrTime1($('#addGatheringBeginTime'));
		$('#substituteGatheringMainDatagrid').datagrid({
			url : 'gatheringAction!datagridSubstituteGatheringMain.action',
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'customId',title:'代付人编号',width:140,hidden:true},
				{field:'customName',title:'代付人',width:60,sortable:false},
				{field:'customTel',title:'联系方式',width:100,sortable:false},
				{field:'customAddr',title:'地址',width:100,sortable:false},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
				{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
				{field:'arrearsAmount',title:'欠款',width:60,sortable:false},
				{field:'unAchieve',title:'付清状态',width:60,sortable:false,
					formatter: function(value,row,index){
						if(value==parame1){
							return "付清";								
						}else if(value==parame2){
							return "未付款";
						}else{
							return "未付清";
						}
					}
				}
			]],
			onClickRow : function (rowIndex, rowData){
				
				var params=rowData.customId+'&'+$('#substituteGatheringQueryForm').serialize();
				runs(params);
			}
		});
	}
	var money=null; 
	function runs(customId){
		$substituteGatheringDatagrid = $('#substituteGatheringDatagrid');
		$substituteGatheringDatagrid.datagrid({
			url : 'gatheringAction!datagridSubstituteGathering.action?tempCustomId='+customId,
			fit : true,
			fitColumns : true,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'sspId',title:'代付结算编号',width:140,sortable:false},
				{field:'createTime',title:'创建时间',width:122,sortable:false},
				{field:'customName',title:'代付人',width:60,sortable:false},
				{field:'carLicense',title:'车牌照',width:70,sortable:false},
				{field:'preclrInoiceTypeName',title:'票据类型',width:100,sortable:false},
				{field:'preclrInvoiceTime',title:'开票时间',width:130,sortable:false},
				{field:'preclrNo',title:'票据编号',width:60,sortable:false},
				{field:'preclrAmount',title:'结算金额',width:60,sortable:false},
				{field:'cumulativeAmount',title:'累计收款',width:60,sortable:false},
				{field:'arrearsAmount',title:'欠款',width:60,sortable:false},
				{field:'unAchieve',title:'付清状态',width:60,sortable:false,
					formatter: function(value,row,index){
						if(value==parame1){
							return "付清";								
						}else if(value==parame2){
							return "未付款";
						}else{
							return "未付清";
						}
					}
				}
			]],
			onClickRow : function (rowIndex, rowData){
				loadOld(rowData.sspId);
			}
		});
	}
	//显示使用记录
	function loadOld(id1){
		var url='gatheringAction!datagridSubstituteGatheringOld.action?sspId='+id1;
		$('#oldSubstituteGatheringDatagrid').datagrid({
			url : url,
			fit : true,
			fitColumns : false,
			pagination : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'gatheringId',title:'收款单号',width:130,sortable:false},
				{field:'paymentTime',title:'收款时间',width:124,sortable:false},
				{field:'payableAmount',title:'应付金额',width:60,sortable:false},
				{field:'paymentAmount',title:'已付金额',width:60,sortable:false},
				{field:'arrearsAmount',title:'未付金额',width:60,sortable:false},
				{field:'gatheringWise',title:'付款方式',width:60,sortable:false},
				{field:'stfName',title:'收银员',width:70,sortable:false},
				{field:'gatheringRemark',title:'收款备注',width:100}
			]]
		});
	}
	function _query(){
		//$substituteGatheringDatagrid.datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		//$firstSubstituteGatheringDatagrid.datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		if($('#substituteGatheringQueryForm').form('validate')){
			$('#substituteGatheringMainDatagrid').datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	};
	
	function _clear(){
		$('#substituteGatheringQueryForm').form('clear');	
		$('#substituteGatheringMainDatagrid').datagrid('load', serializeObject($('#substituteGatheringQueryForm')));
	}
	function _except(){
		$.messager.confirm('优亿软件提示', '选择导出代付收款明细(确认)或收款记录(取消)?', function(r){
			if (r){
				showEditDialog("substituteGatheringDatagrid",null,"substituteGatheringDatagrid_center","开始导出","导出配置",0,_callbackExcept);
			}else{
				showEditDialog("oldSubstituteGatheringDatagrid",null,"oldSubstituteGatheringDatagrid_center","开始导出","导出配置",0,_callbackExceptOld);	
			}
		});
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"代付收款明细"+getSystemTime());
	}
	function _callbackExceptOld(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"收款记录"+getSystemTime());
	}