function LoadOk() {
			if (document.readyState == "complete") {
				initFrame();
				//findCarLicenseFormat("frtWorkOrderItemQueryCarId");
			} else {
				setTimeout("LoadOk();", 200);
			}
		}
		function initFrame(){
			runs();
		}
		setTimeout("LoadOk();", 200);
		function runs(){
			$('#frtWorkOrderItemQueryPreclrTimeEnd').val(getSystemTime());
			loadPreClrTime1($('#frtWorkOrderItemQueryPreclrTimeBegin'));
			//工单综合查询->维修项目
			$frtWorkOrderItemDatagrid = $('#frtWorkOrderItemDatagrid');
			//treegrid
			$frtWorkOrderItemDatagrid.treegrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
				fit : true,
				fitColumns : true,
				loadMsg : "数据加载中，请稍后……",
				idField : 'receptionId',
				treeField : 'repitemName',
				columns : [[  
				 	{field:'_parentId',title:'编号',width:80,hidden : true},
					{field:'receptionId',title:'工单号',width:110},
					{field:'preclrId',title:'结算单号',width:110,sortable:false},         
					{field:'preclrTime',title:'结算日期',width:100,sortable:false},         
					{field:'stfName',title:'接待员',width:60,sortable:false},        
					{field:'customName',title:'客户名称',width:60,sortable:false},         
					{field:'carLicense',title:'车辆牌照',width:70,sortable:false},         
					{field:'ctypeName',title:'车型号',width:70,sortable:false},         
					{field:'receptionDistance',title:'维修里程',width:60,sortable:false},         
					{field:'linkMan',title:'联系人',width:60,sortable:false},         
					{field:'linkManTel',title:'联系电话',width:90,sortable:false},         
					{field:'repitemName',title:'维修项目',width:100,sortable:false},         
					{field:'wktimeNum',title:'工时',width:60,sortable:false},   
					{field:'wktimeAmount',title:'工时费',width:60,sortable:false},   
					{field:'stfName1',title:'维修员',width:60,sortable:false},   
					{field:'claimsName',title:'索赔',width:60,sortable:false}				
				]],
				onloadSuccess: function (){
					delete $(this).treegrid('options').queryParams['id'];
				},
				onBeforeExpand : function (rowData){
					//动态设置展开查询的url
						var url = 'frtWorkOrderAction!datagridFrtWorkOrderItemByReceptionId.action?receptionId='+rowData.receptionId;
						$('#frtWorkOrderItemDatagrid').treegrid("options").url = url;
						return true;
				}
			});
		}
		
		function queryFrtWorkOrderItem(){
			if($('#frtWorkOrderItemQueryForm').form('validate')){
				var url="frtWorkOrderAction!datagridFrtWorkOrderItem.action?";
				url+=$('#frtWorkOrderItemQueryForm').serialize();
				$('#frtWorkOrderItemDatagrid').treegrid({
					url:url
				});
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		}
		function clearFrtWorkOrderItem(){
			$('#frtWorkOrderItemQueryForm').form('clear');
			var url="frtWorkOrderAction!datagridFrtWorkOrderItem.action?";
			url+=$('#frtWorkOrderItemQueryForm').serialize();
			$('#frtWorkOrderItemDatagrid').treegrid({
				url:url
			});
		}
		
		function _exceptFrtWorkOrderItem(){
			showEditDialog("frtWorkOrderItemDatagrid",null,"frtWorkOrderItemDatagrid_center","开始导出","导出配置",0,_callbackExceptFrtWorkOrderItem);
		}
		function _callbackExceptFrtWorkOrderItem(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"工单-维修项目"+getSystemTime());
		}