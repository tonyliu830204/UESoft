    var frtWorkshopManagerDetailsFlag=false;
	function loadOk() {
		if (document.readyState == "complete") {
			/*if(staticFrtWorkkshopManagerDisabled==false){//判断表单组件禁用或启用    false为禁用
				requiredAsForm(false,'frtWorkshopManagerAddForm');//控制表单所有控件 可用不可用
				frtWorkkshopManagerDisabled(false);
			}*/
			initFrame();
		} else {
			setTimeout("loadOk();", 200);
		}
	}
	setTimeout("loadOk();", 200);
	function initFrame() {
		if(receptionId!=null&&receptionId!=''){
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'frtWorkshopManagerAction!datagridFrtWorkshop.action',
				   data: 'receptionId='+receptionId,
				   data: 'receptionId='+receptionId+'&flage='+f,
				   success: function(r){
				     	if(r.total>0){
				     		//frtWorkkshopManagerDisabled(true);
							$('#frtWorkshopManagerAddForm').form('load', r.rows[0]);
							changeStatus(r.rows[0]);
				     	}
				   }
			});
		}else{
			alertMsg('对不起，请选择相关单据！', 'warning');
		}
		$(function(){
			$('#frtWorkshopManagerDetailsTabs').tabs({
				onSelect:function(title){
					if(title =='出料显示'){
						if(frtWorkshopManagerDetailsFlag){
							frtWorkshopManagerExwarehousePartsRuns();
						}
					}
				}
			});
		});
	}
	
	//出料
	function frtWorkshopManagerExwarehousePartsRuns(){
		if(receptionId==''||receptionId==null){
			receptionId=-1;
		}
		$('#frtWorkshopManagerExwarehousePartsDatagrid').datagrid({
  			url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+receptionId,
  			singleSelect : true,
  			fit : true,
  			fitColumns : true,
  			rownumbers : true,
  			showFooter : true,
  			columns : [[
	        	{field:'carType',title:'车型',width:60,sortable:true},
	        	{field:'partsCode',title:'配件编码',width:60,sortable:true},
	        	{field:'partsName',title:'配件名称',width:60,sortable:true},
	        	{field:'punitName',title:'单位',width:30,sortable:true},
	        	{field:'partsCount',title:'数量',width:30,sortable:true},
	        	{field:'partsPrice',title:'单价',width:50,sortable:true},
	        	{field:'partsAmount',title:'金额',width:50,sortable:true},
	        	{field:'stfName',title:'领料员',width:60,sortable:true},
	        	{field:'storageId',title:'单号(出退预)',width:130,sortable:true},
	        	{field:'storageTime',title:'时间(出退预)',width:130,sortable:true},
	        	{field:'chargeName',title:'收费性质',width:60,sortable:true},
	        	{field:'claimsName',title:'索赔性质',width:60,sortable:true}
	        ]]
  	  	});
	}