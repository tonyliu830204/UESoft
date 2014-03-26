$(function (){

		$('#interDateEnd').val(getSystemTime());
		getStartTime($('#interDateBegin'));
		findCarLicenseFormat("frtWorkOrderBaseQueryCarId");
		// 工单综合查询->工单基本信息datagrid
		$frtWorkOrderBaseDatagrid = $('#frtWorkOrderBaseDatagrid');
		$frtWorkOrderBaseDatagrid.treegrid({
			url : 'frtWorkOrderAction!datagridFrtWorkOrderBase.action',
			pagination : true,
			fit : true,
			rownumbers : true,
			animate:true,
			autoRowHeight:true,
			loadMsg : "数据加载中，请稍后……",
			idField : 'receptionId',
			treeField : 'preclrAndClaimant',
			frozenColumns : [[
				{field:'_parentId',title:'编号',width:80,hidden : true},
	  			{field:'receptionId',title:'工单号',width:120,sortable:false},  
	  			{field:'carLicense',title:'车牌号',width:60,sortable:false},
	            {field:'customName',title:'客户名称',width:60,sortable:false
	           }]],
	  		columns:[[  
	          {field:'interDate',title:'进厂日期',width:124,sortable:false},
	          {field:'ctypeName',title:'车型',width:60,sortable:false},
	          {field:'receptionDistance',title:'里程',width:60,sortable:false},
	          {field:'reptName',title:'类别',width:60,sortable:false},
	          {field:'stfName',title:'接待员',width:60,sortable:false},
	          {field:'preclrAndClaimant',title:'结算、索赔单号',width:300,sortable:false},
	          {field:'preclrTime',title:'结算日期',width:124,sortable:false},
	          {field:'stfName1',title:'结算员',width:60,sortable:false},
	          {field:'carVin',title:'VIN号',width:130,sortable:false},
	          {field:'carMotorId',title:'发动机号',width:100,sortable:false},
	          {field:'cbrdName',title:'车辆品牌',width:60,sortable:false},
	          {field:'colorName',title:'车身颜色',width:60,sortable:false},
	          {field:'tempData2',title:'动力系统',width:60,sortable:false},
	          {field:'carBuyDate',title:'购车日期',width:124,sortable:false},
	          {field:'carAcceptBack',title:'接受回访',width:60,sortable:false},
	          {field:'carLicenseDate',title:'办证日期',width:124,sortable:false},
	          {field:'carLastRepairDate',title:'最后维修日期',width:124,sortable:false},
	          {field:'carRepairCnt',title:'维修次数',width:124,sortable:false},
	          {field:'carLastRepairDistance',title:'最后里程',width:60,sortable:false},
	          {field:'carBasinsuranceDate',title:'交强险期',width:124,sortable:false},
	          {field:'carBusinsuranceDate',title:'保险到期',width:124,sortable:false},
	          {field:'relcampName',title:'保险公司',width:100,sortable:false},
	          {field:'customAddr',title:'客户地址',width:100,sortable:false},
	          {field:'customTel1',title:'客户电话一',width:100,sortable:false},
	          {field:'customTel2',title:'客户电话二',width:100,sortable:false},
	          {field:'linkMan',title:'客户联系人',width:60,sortable:false},
	          {field:'carRealationPerson',title:'驾驶姓名',width:60,sortable:false},
	          {field:'carRealationTel1',title:'电话1',width:100,sortable:false},
	          {field:'vipNumber',title:'会员卡号',width:100,sortable:false},
	          {field:'endTime',title:'会员到期',width:124,sortable:false},
	          {field:'vipBirthday',title:'会员生日',width:124,sortable:false},
	          {field:'customEmail',title:'E-Mail',width:100,sortable:false},
	          {field:'carPostalCode',title:'邮政编码',width:100,sortable:false},
	          {field:'carId',title:'车辆编号',width:100,sortable:false},
	          {field:'slsName',title:'购买地',width:100,sortable:false},
	          {field:'carRecord',title:'自编号',width:100,sortable:false},
	          {field:'createDate',title:'新增日期',width:124,sortable:false},
	          {field:'carRemark',title:'备注',width:100,sortable:false},
	          {field:'carExaminedDate',title:'年审到期',width:124,sortable:false},
	          {field:'carAnnualDate',title:'年检到期',width:124,sortable:false},
	          {field:'carLastMaintDate',title:'最后保养日期',width:124,sortable:false},
	          {field:'carLastMaintDistance',title:'最后保养里程',width:80,sortable:false},
	          {field:'carMaintCnt',title:'最后保养次数',width:80,sortable:false},
	          {field:'carNextMaintDate',title:'预计下次保养日期',width:124,sortable:false},
	          {field:'carNextMaintDistance',title:'预计下次保养里程',width:100,sortable:false},
	          {field:'carDistancePerDay',title:'日均里程',width:60,sortable:false},
	          {field:'timeLimit',title:'未来厂天数',width:100,sortable:false},
	          {field:'preclrType',title:'结算单',width:100,hidden:true},
	          {field:'claimantType',title:'索赔单',width:100,hidden:true}
	          ]],
			onBeforeExpand : function (rowData){
				//动态设置展开查询的url
					var url = 'frtWorkOrderAction!datagridFrtWorkOrderBaseByReceptionId.action?receptionId='+rowData.receptionId;
					$('#frtWorkOrderBaseDatagrid').treegrid("options").url = url;
					return true;
			},
	        onDblClickRow : function (row){
	        	var tagId="";
				var preclrAndClaimant=row.preclrAndClaimant;
				//alert(preclrAndClaimant);
				for(var i=0;i<preclrAndClaimant.length;i++){
					if(!(preclrAndClaimant.charAt(i)>='0'&&preclrAndClaimant.charAt(i)<='9')){
						tagId=tagId+preclrAndClaimant.charAt(i);
					}
				}
				var urlParts=null;
				var urlItems=null;
				var urlBalance=null;
				if(tagId==row.preclrType){
					urlParts='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?preclrId=' + preclrAndClaimant;
					urlItems='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?preclrId=' + preclrAndClaimant;
					urlBalance='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationBalance.action?preclrId='+preclrAndClaimant;
				}else if(tagId==row.claimantType){
					urlParts='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?claimantmId=' + preclrAndClaimant;
					urlItems='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?claimantmId=' + preclrAndClaimant;
					urlBalance='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationBalance.action?claimantmId='+preclrAndClaimant;
				}else{
					urlParts='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId=' + row.receptionId;
					urlItems='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId=' + row.receptionId;
					urlBalance='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationBalance.action?receptionId='+row.receptionId;
				}	        	
	        	$('#frtWorkOrderTabs').tabs('select', '工单-结算情况');
	        	
	        	$('#frtWorkOrderSettlementSituationPartsDatagrid').datagrid({
					url : urlParts
				});
	        	
				$('#frtWorkOrderSettlementSituationItemDatagrid').datagrid({
					url : urlItems
				});
				$.ajax({
					type : 'post',
					url : urlBalance,
					dataType : 'json',
					success : function(r) {
						if(r==null){
							$('#preclrWktimeAmounts').val('0.00');
							$('#preMprMatAmounts').val('0.00');
							$('#preclrManagementFee').val('0.00');
							$('#preclrOtherAmount').val('0.00');
							$('#preclrSumAmount').val('0.00');
							$('#tempName1').val('工时折扣(0.00x0.00)');
							$('#tempName2').val('材料折扣(0.00x0.00)');
						}else{
							$('#preclrWktimeAmounts').val(r.preclrWktimeAmounts);
							$('#preMprMatAmounts').val(r.preMprMatAmounts);
							$('#preclrManagementFee').val(r.preclrManagementFee);
							$('#preclrOtherAmount').val(r.preclrOtherAmount);
							$('#preclrSumAmount').val(r.preclrSumAmount);
							$('#tempName1').val('工时折扣('+r.preclrWktimeAmount+'x'+r.preclrWktimeRate+')');
							$('#tempName2').val('材料折扣('+r.preMprMatAmount+'x'+r.preclrMaterialRate+')');
						}
					}
				});
	        }
		});
	});