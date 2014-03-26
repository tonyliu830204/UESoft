function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
				findCarLicenseFormat("gatheringQueryCarId");
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		function initFrame(){
				runs();
				loadOld("-1");
				requirdAsFalse();
		}
		setTimeout("LoadOk();", 200);
		var money=null; 
		function runs(){

			$('#addGatheringEndTime').val(getSystemTime2());
			loadPreClrTime1($('#addGatheringBeginTime'));
						
				//前台收银->
			$gatheringDatagrid = $('#gatheringDatagrid');
			
			$gatheringDatagrid.datagrid({
				url : 'gatheringAction!datagridGathering.action',
				fit : true,
				fitColumns : true,
				pagination : true,
				singleSelect : true,
				rownumbers : true,
				columns : [[
					{field:'preclrId',title:'结算单号',width:100,sortable:false},
					{field:'preclrTime',title:'结算时间',width:122,sortable:false},
					{field:'customId',title:'客户编号',width:60,hidden:true},
					{field:'customName',title:'客户名称',width:60,sortable:false},
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
				 onClickRow:function(rowIndex,rowData){
				 	loadOld(rowData.preclrId);
				 }
			});
		}
		//显示使用记录
		function loadOld(id){
			$('#oldGatheringDatagrid').datagrid({
				url : 'gatheringAction!datagridGatheringOld.action?preclrId='+id,
				fit : true,
				fitColumns : true,
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
			if($('#gatheringQueryForm').form('validate')){
				$('#gatheringDatagrid').datagrid('load', serializeObject($('#gatheringQueryForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		};
		function _clear(){
			$('#gatheringQueryForm').form('clear');	
			$('#gatheringDatagrid').datagrid('load', serializeObject($('#gatheringQueryForm')));
		}
		//转结算
			var modifyTransBalance = function (){
			var rowData = $gatheringDatagrid.datagrid('getSelected');
				if(rowData){
					if(rowData.preclrAmount==rowData.arrearsAmount){
					
					
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: 'gatheringAction!findIsClaims.action',
						   data: rowData,
						   success: function(r){
						   		if(!r.success){
						   			$.messager.confirm('系统提示', '您确定要进行转结算操作?', function(r){
										if (r){
											$.ajax({
											   type: 'post',
											   dataType: 'json',
											   url: 'gatheringAction!modifyTransBalance.action',
											   data: rowData,
											   success: function(r){
											     	alertMsg(r);
											     	if(r.success){
												     	$('#gatheringDatagrid').datagrid('load');
											     	}
											   }
											});
										}
									});		
						   		}else{
						   			alertMsg('对不起，此单已索理赔，不能转结算！', 'warning');
						   		}
						   }
						});
					}else if(rowData.preclrAmount>rowData.arrearsAmount){
						alertMsg('对不起，此单已收款，不能转结算！', 'warning');
					}
				}else{
					alertMsg('对不起，请先选中要操作的记录！', 'warning');
				}
			}
			
			function _except(){
				$.messager.confirm('优亿软件提示', '选择导出收款信息(确认)或收款记录(取消)?', function(r){
					if (r){
						showEditDialog("gatheringDatagrid",null,"gatheringDatagrid_center","开始导出","导出配置",0,_callbackExcept);
					}else{
						showEditDialog("oldGatheringDatagrid",null,"oldGatheringDatagrid_center","开始导出","导出配置",0,_callbackExceptOld);	
					}
				});
			}
			function _callbackExcept(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"收款查询"+getSystemTime());
			}
			function _callbackExceptOld(parentId,fieldNames){
				exportEsuyUIExcelFile(parentId,fieldNames,"收款记录"+getSystemTime());
			}