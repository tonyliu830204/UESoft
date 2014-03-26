function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	var commonitemurl=null;
	var commonpartsurl=null;
	function initFrame(){
		var rowData = $('#frtWorkOrderBaseDatagrid').datagrid('getSelected');
		if(rowData){
			$('#frtWorkOrderSettlementSituationItemDatagrid').datagrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId='+rowData.receptionId
			});
			commonitemurl='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId='+rowData.receptionId;
			$('#frtWorkOrderSettlementSituationPartsDatagrid').datagrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId='+rowData.receptionId
			});
			commonpartsurl='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId='+rowData.receptionId;
		}else{
			$('#frtWorkOrderSettlementSituationItemDatagrid').datagrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId=-1'
			});
			commonitemurl='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId=-1';
			$('#frtWorkOrderSettlementSituationPartsDatagrid').datagrid({
				url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId=-1'
			});
			commonpartsurl='frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId=-1';
		}
		runs();
	}
	setTimeout("LoadOk();", 200);
	function runs(){
		$(function (){
		//工单综合查询->结算情况->工时清单
		$frtWorkOrderSettlementSituationItemDatagrid = $('#frtWorkOrderSettlementSituationItemDatagrid');
		//var rowData=$('#frtWorkOrderBaseDatagrid').datagrid('getSelected');
		$frtWorkOrderSettlementSituationItemDatagrid.datagrid({
			//url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationItem.action?receptionId='+rowData.receptionId,
			url : commonitemurl,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'repitemId',title:'项目编号',width:60,sortable:true},         
				{field:'repitemName',title:'维修项目',width:60,sortable:true},         
				{field:'wktimeNum',title:'工时',width:60,sortable:true},         
				{field:'stfName',title:'维修员',width:60,sortable:true},         
				{field:'claimsName',title:'索赔',width:60,sortable:true},         
				{field:'receptionRemark',title:'备注',width:60,sortable:true}       
	         ]]
		});
		
		//工单综合查询->结算情况->配件清单
		$frtWorkOrderSettlementSituationPartsDatagrid = $('#frtWorkOrderSettlementSituationPartsDatagrid');
		
		$frtWorkOrderSettlementSituationPartsDatagrid.datagrid({
			//url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationParts.action?receptionId='+rowData.receptionId,
			url : commonpartsurl,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			columns : [[
				{field:'carPartName',title:'部位',width:60,sortable:true},         
				{field:'fitPtype',title:'车型',width:60,sortable:true},         
				{field:'partsId',title:'配件编码',width:60,sortable:true},         
				{field:'partsName',title:'配件名称',width:60,sortable:true},         
				{field:'punitName',title:'单位',width:60,sortable:true},         
				{field:'partsCount',title:'数量',width:60,sortable:true},         
				{field:'partsPrice',title:'单价',width:60,sortable:true},         
				{field:'partsAmount',title:'金额',width:60,sortable:true},
				{field:'partsFlag',title:'标识',width:60,hidden:true},    
				{field:'claimsName',title:'索赔',width:60,sortable:true}   
			]]
		});
		
		$.ajax({
			type : 'post',
			url : 'frtWorkOrderAction!datagridFrtWorkOrderSettlementSituationBalance.action?receptionId='+rowData.receptionId,
			dataType : 'json',
			success : function(r) {
				$('#preclrWktimeAmounts').val(r.preclrWktimeAmounts);
				$('#preMprMatAmounts').val(r.preMprMatAmounts);
				$('#preclrManagementFee').val(r.preclrManagementFee);
				$('#preclrOtherAmount').val(r.preclrOtherAmount);
				$('#preclrSumAmount').val(r.preclrSumAmount);
				$('#tempName1').val('工时折扣('+r.preclrWktimeAmount+'x'+r.preclrWktimeRate+')');
				$('#tempName2').val('材料折扣('+r.preMprMatAmount+'x'+r.preclrMaterialRate+')');
			}
		});
		
	});
	}