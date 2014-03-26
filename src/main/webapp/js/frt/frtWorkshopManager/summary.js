    var loadFlag=false;
	function LoadOk() {
		if (document.readyState == "complete") {
			if(showRepair!=null && showRepair=='checked'){
				findCarLicenseFormat("frtWorkshopManager_summary_carId");//查找默认车牌照格式
				$('#interDateEnd').val(getSystemTime2());
				getStartTime($('#interDateBegin'));
				loadFlag=true;
			}else{
				loadFlag=false;
				i=0;
				}
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		$('#frtWorkshopManagerTabs').tabs('select', '工单明细');
		$('#frtWorkshopManagerTabs').tabs('select', '工单汇总');
		loadWorkshopDatagrid();
		
	}
	setTimeout("LoadOk();", 200);
	var loadWorkshopDatagrid=function() {
		//车间管理->工单汇总
		var frtWorkshopManagerSummaryDatagrid = $('#frtWorkshopManagerSummaryDatagrid');
		
		frtWorkshopManagerSummaryDatagrid.datagrid({
			url : 'frtWorkshopManagerAction!datagridFrtWorkshop.action?param='+loadFlag,
			singleSelect : true,
			pagination : true,
			fit : true,
			rownumbers : true,
			loadMsg : "数据加载中，请稍后……",
			frozenColumns : [[
	  			{field:'receptionId',title:'工单号',width:110,sortable:true},
	  			{field:'resvId',title:'预约编号',width:110,sortable:true},
	  			{field:'carId',title:'车牌照',width:60,sortable:true, formatter : function (value, row, index){
	  				return row.carLicense;
	  			}},
	  			{field:'carVin',title:'VIN号',width:140,sortable:true},
	  			{field:'carMotorId',title:'发动机号',width:80,sortable:true},
	  			{field:'customId',title:'客户名称',width:60,sortable:true,formatter: function(value,row,index){
	  				return row.customName;
	  			}},
	  		  ]],
	  		columns:[[  
	  		    {field:'reptId',title:'维修类别',width:80,sortable:true,formatter: function(value,row,index){
	  				return row.reptName;
	  			}},
	  		    {field:'reptName',title:'维修类别',width:80,sortable:true,hidden:true},
	              {field:'receptionDistance',title:'里程数',width:60,sortable:true},  
	              {field:'receptionMaintFlg',title:'保养区分',width:60,sortable:true,formatter: function(value,row,index){
	  				return row.receptionMaintFlgName;
	  			}},  
	              {field:'receptionStatus',title:'工单状态',width:60,sortable:true,formatter: function(value,row,index){
		  				return row.receptionStatusName;
		  			}},  
	              {field:'repgrpId',title:'维修班组',width:70,sortable:true,formatter: function(value,row,index){
	  				return row.repgrpName;
	  			}},  
	              {field:'repgrpName',title:'维修班组',width:70,sortable:true,hidden:true},  
	              {field:'repwkId',title:'维修工位',width:70,sortable:true,formatter: function(value,row,index){
	  				return row.repwkName;
	  			}},  
	              {field:'repwkName',title:'维修工位',width:70,sortable:true,hidden:true}, 
	              {field:'receptor',title:'接待员',width:60,sortable:true,formatter: function(value,row,index){
		  				return row.receptorName;
		  			}},
	              {field:'interDate',title:'进厂时间',width:140,sortable:true},  
	              {field:'receptionEndTime',title:'计划完工时间',width:140,sortable:true},
	              {field:'expDelCarTime',title:'预计交车时间',width:140,sortable:true},
	              {field:'receptionTechnician',title:'维修技师',width:60,sortable:true,formatter: function(value,row,index){
		  				return row.receptionTechnicianName;
		  			}},
	              {field:'receptionInsurPer',title:'三包人员',width:60,sortable:true,formatter: function(value,row,index){
		  				return row.receptionInsurPerName;
		  			}},
	              {field:'receptionRepPer',title:'保险送修人',width:70,sortable:true},
	              {field:'propRepPer',title:'托修人',width:60,sortable:true},
	              {field:'propPhone',title:'托修人手机',width:90,sortable:true},
	              {field:'propTel',title:'托修人电话',width:90,sortable:true},
	               {field:'valuables',title:'贵重物品',width:70,sortable:true,formatter: function(value,row,index){
					return row.valuablesName;
					}},
		            {field:'fuelSituation',title:'燃油情况',width:60,sortable:true,formatter: function(value,row,index){
						return row.fuelSituationName;
					}},
		            {field:'oldPieces',title:'旧件处理',width:60,formatter: function(value,row,index){
						return row.oldPiecesName;
					}},
	              {field:'finComId',title:'索赔厂商',width:80,sortable:true,formatter: function(value,row,index){
	  				return row.relcampName;
	  			}},
	              {field:'problemDesc',title:'故障描述',width:200},
	              {field:'receptionRemark',title:'接车备注',width:200} 
	           ]],
	         onDblClickRow : function (rowIndex, rowData){
	         	staticFrtWorkshopManageDeatil=true;
	      		staticFrtWorkshopManagerParts=null;
				staticFrtWorkshopManagerItems=null;
				staticFrtWorkshopManagerVehicleStructure=null;
				$('#frtWorkshopManagerAddForm').form('clear');
				$('#frtWorkshopManagerTabs').tabs('select', '工单明细');
				$('#frtWorkshopManagerAddForm').form('load', rowData);
				changeStatus(rowData);
				
				$('#frtWorkshopManagerPartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findPartsByRcptId.action?receptionId=' + rowData.receptionId
				});
				$('#frtWorkshopManagerItemDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findItemByRcptId.action?receptionId=' + rowData.receptionId
				});
				$('#vehicleStructureDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findvehicleStructureList.action?receptionId='+rowData.receptionId
				});
				$('#frtWorkshopManagerExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+rowData.receptionId
				});
				$('#frtWorkshopManagerPartsDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtWorkshopManagerParts=data;
					}
				});
				$('#frtWorkshopManagerItemDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtWorkshopManagerItems=data;
					}
				});
				$('#vehicleStructureDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtWorkshopManagerVehicleStructure=data;
						loadIcons();
					}
				});
			},
			onLoadSuccess : function (data){
				if(loadFlag==false){
					loadFlag=true;
					frtWorkshopManagerSummaryDatagrid.datagrid('options').url='frtWorkshopManagerAction!datagridFrtWorkshop.action?param='+loadFlag;
				}
			}
		});
	};