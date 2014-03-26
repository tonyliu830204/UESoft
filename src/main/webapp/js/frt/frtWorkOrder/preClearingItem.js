function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
				findCarLicenseFormat("frtWorkOrderPreClearingItemQueryCarId");
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
			// 工单综合查询->结算单查询->工时清单
			$frtWorkOrderPreClearingItemDatagrid = $('#frtWorkOrderPreClearingItemDatagrid');
			
			$frtWorkOrderPreClearingItemDatagrid.treegrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderPreClearingItem.action',
				pagination : true,
				rownumbers : true,
				fitColumns : true,
				singleSelect : true,
				fit : true,
				title:'结算单号',
				idField:'preclrId',
				treeField:'preclrId',
				columns : [[
					{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'preclrId',title:'结算单号',width:110,sortable:true},
					{field:'preclrTime',title:'结算时间',width:100,sortable:true},	
					{field:'repitemId',title:'维修项目编号',width:60,sortable:true},
				    {field:'repitemName',title:'维修项目名称',width:60,sortable:true},
				    {field:'sumCount',title:'工时',width:60,sortable:true},
				    {field:'preclrAmount',title:'金额',width:60,sortable:true},
				    {field:'stfName',title:'维修人员',width:60,sortable:true},
				    {field:'relcampName',title:'索赔厂商',width:60,sortable:true},
				    {field:'reptName',title:'维修分类',width:60,sortable:true}
				]],
				onloadSuccess: function (){
					delete $(this).treegrid('options').queryParams['id'];
				},
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'frtWorkOrderAction!datagridFrtWorkOrderPreClearingItemByPreclrId.action?preclrId='+rowData.preclrId;
						$('#frtWorkOrderPreClearingItemDatagrid').treegrid("options").url = url;
						return true;
				}
			});
		}
		function queryFrtWorkOrderPreClearingItem(){
			if($('#frtWorkOrderPreClearingItemQueryForm').form('validate')){
				var url="frtWorkOrderAction!datagridFrtWorkOrderPreClearingItem.action?";
				url+=$('#frtWorkOrderPreClearingItemQueryForm').serialize();
				$('#frtWorkOrderPreClearingItemDatagrid').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearFrtWorkOrderPreClearingItem(){
			$('#frtWorkOrderPreClearingItemQueryForm').form('clear');
			var url="frtWorkOrderAction!datagridFrtWorkOrderPreClearingItem.action?";
			url+=$('#frtWorkOrderPreClearingItemQueryForm').serialize();
			$('#frtWorkOrderPreClearingItemDatagrid').treegrid({
				url:url
			});
		}
		function _exceptFrtWorkOrderPreClearingItem(){
			showEditDialog("frtWorkOrderPreClearingItemDatagrid",null,"frtWorkOrderPreClearingItemDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderPreClearingItem);
		}
		function _callbackExceptFrtWorkOrderPreClearingItem(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"结算单-工时清单"+getSystemTime());
		}