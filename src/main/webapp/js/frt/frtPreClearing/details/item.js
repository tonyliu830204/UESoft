/*function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		var rowData = $('#frtPreClearingSummaryDatagrid').datagrid('getSelected');
		if(rowData){
			$('#frtPreClearingItemDatagrid').datagrid({
				url : 'frtPreClearingAction!findPreItemById.action?preclrId=' + rowData.preclrId
			});
		}else{
			$('#frtPreClearingItemDatagrid').datagrid({
				url : 'frtPreClearingAction!findPreItemById.action?preclrId=-1'
			});
		}
		if($('#save').length != 0 && $('#cancel').length != 0){
			
			$('#frtPreClearing_item_add').linkbutton('enable');
			$('#frtPreClearing_item_remove').linkbutton('enable');
			$('#frtPreClearing_item_accept').linkbutton('enable');
			
		}
	}
	setTimeout("LoadOk();", 200);*/

	$(function (){
		//维修结算单->结算项目
		var url="";
		if(preclrId){
			url='frtPreClearingAction!findPreItemById.action?preclrId=' + preclrId;
		}else{
			url='frtPreClearingAction!findPreItemById.action?preclrId=-1';
		}
		$frtPreClearingItemDatagrid = $('#frtPreClearingItemDatagrid');
		$frtPreClearingItemDatagrid.datagrid({
			url : url,
			fit : true,
			singleSelect : true,
			rownumbers : true,
			fitColumns: true,
			columns : [[
				{field:'repitemId',title:'项目编号',width:60,sortable:true},         
				{field:'repitemName',title:'项目名称',width:60,sortable:true},
				{field:'wktimeNum',title:'维修工时',width:60,sortable:true},
				{field:'innerWktime',title:'内部工时',width:60,sortable:true},    
				{field:'wktimeAmount',title:'项目金额',width:60,sortable:true},         
				{field:'stfId',title:'操作员',width:60,sortable:true,hidden:true},         
				{field:'stfName',title:'操作员',width:60},
				{id:'claimsId',field:'claimsId',title:'索赔性质',width:60,
					formatter : function (value,row,index){
						return row.claimsName;
					}
				},
				{id:'reptypId',field:'reptypId',title:'收费性质',width:60,hidden:true}
			]]
		});
	});