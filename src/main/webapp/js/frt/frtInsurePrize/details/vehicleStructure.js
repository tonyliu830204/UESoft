//保险估价单->车身状况datagrid
	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
	var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		if(data){
			$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
				url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId='+data.resvId
			});
		}else{
			$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
				url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId=-1'
			});
		}
		if($('#save').length != 0 && $('#cancel').length != 0){
			$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtInsurePrizeVehicleStructure=data;
					$('#frtInsurePrize_vehicleStructure_accept').linkbutton('enable');
					$('#frtInsurePrize_vehicleStructure_clear').linkbutton('enable');
					loadIcons();
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);
	
	$(function (){
		//保险估价单->车身状况datagrid
		var url='';
		var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		if(data){
			url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId='+data.resvId;
		}
		$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
			url : url,
			singleSelect : true,
			rownumbers : true,
			fit : true,
			fitColumns : true,
			loadMsg : "数据加载中，请稍后……",
			columns: [[
			    {field:'code',title:'部位',width:130,sortable:true},
			    {field:'name',title:'名称',width:130,sortable:true},
			    {field:'state',title:'状态描述',width:350,sortable:true,editor : {
					type : 'text',
				}}
			]],
			toolbar: [{
				id : 'frtInsurePrize_vehicleStructure_clear',
				text : '清空',
				disabled : true,
				iconCls: 'icon-cancel',
				handler: function(){
					var vehicle=null;
					if(staticFrtInsurePrizeVehicleStructure==null){
						vehicle="";
					}else{
						vehicle=JSON.stringify(staticFrtInsurePrizeVehicleStructure);
					}
				 	$.ajax({
						type : 'post',
						url: 'frtInsurePrizeAction!clearVehicleStructure.action',
						data : 'vehicle='+vehicle,
						dataType : 'json',
						success : function callback(r) {
							$('#frtInsurePrizeVehicleStructureDatagrid').datagrid('loadData', r);
							var data = $('#frtInsurePrizeVehicleStructureDatagrid').datagrid('getData');
							staticFrtInsurePrizeVehicleStructure=data;
						}
					});
					 for(var i=1;i<=64;i++){
					 	$('#remark'+i+'').empty();
					 }
				}
			}]
			,onClickRow : function (rowIndex, rowData){
			   if($('#save').length != 0 && $('#cancel').length != 0){
				   $(this).datagrid('beginEdit', rowIndex);
					var ed = $(this).datagrid('getEditors', rowIndex);
					ed[0].target.select();
					ed[0].target.bind('keyup', function() {
						var num = ed[0].target.val();
						var price = ed[1].target.val();
						var amount = accMul(parseFloat(num), parseFloat(price));
						ed[2].target.numberbox('setValue', amount);
					});
					ed[1].target.bind('keyup', function() {
						var num = ed[0].target.val();
						var price = ed[1].target.val();
						var amount = accMul(parseFloat(num), parseFloat(price));
						ed[2].target.numberbox('setValue', amount);
					});
					ed[0].target.focus(function (){
						ed[0].target.select();
					});
					ed[1].target.focus(function (){
						ed[1].target.select();
					});
					ed[0].target.keydown(function (e){
						if(e.keyCode == '13'){
							ed[1].target.select();
						}
					});
			   }
		   },
		    onLoadSuccess : function (data){
				staticFrtInsurePrizeVehicleStructure=data;
		   }
		});
	});
	//点击部位触发事件
	function addRemark(i, remark) {
		var image = '<img src=\"images/sign_tick.png\" id=\"img'+i+'\"/>';
		var imgId = 'img' + i;
		if (remark.children('img').length == 0) {
			remark.append(image);
			$('#' + imgId).on('click', function() {
				clear(remark, i);
			});
			var vehicle=null;
			if(staticFrtInsurePrizeVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtInsurePrizeVehicleStructure);
			}
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!addVehicleStructure.action',
				data : 'stateId=' + parseInt(i) + '&vehicle='+vehicle,
				dataType : 'json',
				success : function callback(r) {
					$('#frtInsurePrizeVehicleStructureDatagrid').datagrid('loadData', r);
					var data = $('#frtInsurePrizeVehicleStructureDatagrid').datagrid('getData');
					staticFrtInsurePrizeVehicleStructure=data;
					$('#frtInsurePrizeVehicleStructureDatagrid').datagrid('beginEdit', data.total-1);
				}
			});
		} else {
			clear(remark, i);
		}
	}