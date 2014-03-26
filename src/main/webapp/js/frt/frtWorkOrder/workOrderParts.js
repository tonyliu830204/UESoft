function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
				//findCarLicenseFormat("frtWorkOrderPartsQueryCarId");
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		function initFrame(){
			runs();
		}
		setTimeout("LoadOk();", 200);
		
		function runs(){
			$('#frtWorkOrderPartsQueryPreclrTimeEnd').val(getSystemTime());
			loadPreClrTime1($('#frtWorkOrderPartsQueryPreclrTimeBegin'));
			//工单综合查询->工单->维修配件
			$frtWorkOrderPartsDatagrid = $('#frtWorkOrderPartsDatagrid');
			
			$frtWorkOrderPartsDatagrid.treegrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderParts.action',
				fit : true,
				pagination : true,
				fitColumns : false,
				rownumbers : true,
		        pageSize : 15,
				loadMsg : "数据加载中，请稍后……",
				idField:'receptionId',
				treeField:'partsId2',
				frozenColumns :[[
					{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'receptionId',title:'工单号',width:110,sortable:false}
				]],
				columns : [[ 
					{field:'interDate',title:'进厂日期',width:100,sortable:false},         
					{field:'preclrTime',title:'结算日期',width:100,sortable:false},         
					{field:'stfName',title:'接待员',width:60,sortable:false},         
					{field:'customName',title:'客户名称',width:60,sortable:false},         
					{field:'carLicense',title:'车辆牌照',width:70,sortable:false},         
					{field:'receptionDistance',title:'公里数',width:60,sortable:false},         
					{field:'carVin',title:'VIN号',width:140,sortable:false},         
					{field:'carRelationTel1',title:'联系电话',width:90,sortable:false},         
					{field:'customAddr',title:'客户地址',width:140,sortable:false},         
					{field:'partsId2',title:'配件代码',width:90,sortable:false},         
					{field:'partsName',title:'配件名称',width:80,sortable:false},
					{field:'posName',title:'配件部位',width:100,sortable:false},
					{field:'punitName',title:'单位',width:60,sortable:false},
					{field:'partsCount',title:'数量',width:60,sortable:false},
					{field:'partsPrice',title:'售价',width:60,sortable:false},
					{field:'partsAmount',title:'金额',width:60,sortable:false}, 
					{field:'stfName1',title:'领用人',width:60,sortable:false}, 
					{field:'partsFlg',title:'标识',width:30,sortable:false}, 
					{field:'claimsName',title:'索赔',width:60,sortable:false}, 
					{field:'notaxCast',title:'未税价格',width:60,sortable:false}, 
					{field:'taxCast',title:'含税价格',width:60,sortable:false},
					{field:'notaxCastAmont',title:'未税成本',width:60,sortable:false},    
					{field:'taxCastAmont',title:'含税成本',width:60,sortable:false}
				]],
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'frtWorkOrderAction!datagridFrtWorkOrderPartsByReceptionId.action?receptionId='+rowData.receptionId;
						$('#frtWorkOrderPartsDatagrid').treegrid("options").url = url;
						return true;
				}
			});
		}
		
		function queryFrtWorkOrderParts(){
			if($('#frtWorkOrderPartsQueryForm').form('validate')){
				var url="frtWorkOrderAction!datagridFrtWorkOrderParts.action?";
				url+=$('#frtWorkOrderPartsQueryForm').serialize();
				$('#frtWorkOrderPartsDatagrid').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		
		function clearFrtWorkOrderParts(){
			$('#frtWorkOrderPartsQueryForm').form('clear');
			var url="frtWorkOrderAction!datagridFrtWorkOrderParts.action?";
			url+=$('#frtWorkOrderPartsQueryForm').serialize();
			$('#frtWorkOrderPartsDatagrid').treegrid({
				url:url
			});
		} 
		
		function _exceptFrtWorkOrderParts(){
			showEditDialog("frtWorkOrderPartsDatagrid",null,"frtWorkOrderPartsDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderParts);
		}
		function _callbackExceptFrtWorkOrderParts(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"工单-维修配件"+getSystemTime());
		}