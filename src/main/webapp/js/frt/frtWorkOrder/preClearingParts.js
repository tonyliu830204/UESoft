function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
				findCarLicenseFormat("frtWorkOrderPreClearingPartsQueryCarId");
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		function initFrame(){
			runs();
		}
		setTimeout("LoadOk();", 200);
		var runs=function (){
			$('#preclrTimeEnd').val(getSystemTime2());
			loadPreClrTime1($('#preclrTimeBegin'));
			// 工单综合查询->结算单查询->材料清单
			$frtWorkOrderPreClearingPartsDatagrid = $('#frtWorkOrderPreClearingPartsDatagrid');
			
			$frtWorkOrderPreClearingPartsDatagrid.treegrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderPreClearingParts.action',
				pagination : true,
				rownumbers : true,
				fitColumns : true,
				singleSelect : true,
				fit : true,
				//title:'结算单号',
				idField:'preclrId',
				treeField:'preclrId',
				columns : [[
					{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'preclrId',title:'结算单号',width:110,sortable:true},
					{field:'preclrTime',title:'结算时间',width:100,sortable:true},
					{field:'partsId',title:'配件编号',width:60,sortable:true},
				    {field:'partsName',title:'配件名称',width:60,sortable:true},
				    {field:'punitName',title:'单位',width:60,sortable:true},
				    {field:'partsPrice',title:'单价',width:60,sortable:true},
				    {field:'sumCount',title:'配件数量',width:60,sortable:true},
				    {field:'preclrAmount',title:'金额',width:60,sortable:true},
				    {field:'relcampName',title:'索赔厂商',width:70,sortable:true},
				    {field:'reptName',title:'维修分类',width:70,sortable:true}
				]],
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'frtWorkOrderAction!datagridFrtWorkOrderPreClearingPartsByPreclrId.action?preclrId='+rowData.preclrId;
						$('#frtWorkOrderPreClearingPartsDatagrid').treegrid("options").url = url;
						return true;
				}
			});
		}
		
		function queryFrtWorkOrderPreClearingParts(){
			if($('#frtWorkOrderPreClearingPartsQueryForm').form('validate')){
				var url="frtWorkOrderAction!datagridFrtWorkOrderPreClearingParts.action?";
				url+=$('#frtWorkOrderPreClearingPartsQueryForm').serialize();
				$('#frtWorkOrderPreClearingPartsDatagrid').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearFrtWorkOrderPreClearingParts(){
			$('#frtWorkOrderPreClearingPartsQueryForm').form('clear');
			var url="frtWorkOrderAction!datagridFrtWorkOrderPreClearingParts.action?";
			url+=$('#frtWorkOrderPreClearingPartsQueryForm').serialize();
			$('#frtWorkOrderPreClearingPartsDatagrid').treegrid({
				url:url
			});
		}
		function _exceptFrtWorkOrderPreClearingParts(){
			showEditDialog("frtWorkOrderPreClearingPartsDatagrid",null,"frtWorkOrderPreClearingPartsDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderPreClearingParts);
		}
		function _callbackExceptFrtWorkOrderPreClearingParts(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"结算单-材料清单"+getSystemTime());
		}