$(function (){
	var url='';
	if(receptionId!=null && receptionId!="" && receptionId!='null'){
		url='frtReceptionAction!findvehicleStructureList.action?receptionId='+receptionId;
	}else{
		url='frtReceptionAction!findvehicleStructureList.action?receptionId=-1';
	}
	//前台接车单->车身状况datagrid
	$vehicleStructureDatagrid = $('#vehicleStructureDatagrid');
	$vehicleStructureDatagrid.datagrid({
		url:url,
		singleSelect : true,
		rownumbers : true,
		fit : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		columns: [[
		    {field:'code',title:'部位',width:130},
		    {field:'name',title:'名称',width:130},
		    {field:'state',title:'状态描述',width:350,
		    	editor : {
					type : 'text'
				}
			}
		]],
		toolbar: [{
			id : 'frtReception_vehicleStructure_clear',
			text : '清空',
			disabled : true,
			iconCls: 'icon-cancel',
			handler: function(){
				var vehicle=null;
				if(staticFrtReceptionVehicleStructure==null){
					vehicle="";
				}else{
					vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
				}
			 	$.ajax({
					type : 'post',
					url : 'frtResevationAction!clearVehicleStructure.action',
					data : 'vehicle='+vehicle,
					dataType : 'json',
					success : function callback(r) {
						$('#vehicleStructureDatagrid').datagrid('loadData', r);
						var data = $('#vehicleStructureDatagrid').datagrid('getData');
						staticFrtReceptionVehicleStructure=data;
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
		   	}		  
		},
	   onLoadSuccess : function (data){
			loadIcons();
			if(operation==true  || recpetionFlag==true){
				staticFrtReceptionVehicleStructure=data;
				$('#frtReception_vehicleStructure_clear').linkbutton('enable');
			}
	   }
	});
});

//点击部位触发事件
function addRemark(i, remark) {
	if(operation==true  || recpetionFlag==true){
		var image = '<img src=\"images/sign_tick.png\" id=\"img'+i+'\"/>';
		var imgId = 'img' + i;
		if (remark.children('img').length == 0) {
			remark.append(image);
			$('#' + imgId).on('click', function() {
				clear(remark, i);
			});
			var vehicle=null;
			if(staticFrtReceptionVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
			}
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!addVehicleStructure.action',
				data : 'stateId=' + parseInt(i) + '&vehicle='+vehicle,
				dataType : 'json',
				success : function callback(r) {
					$('#vehicleStructureDatagrid').datagrid('loadData', r);
					var data = $('#vehicleStructureDatagrid').datagrid('getData');
					staticFrtReceptionVehicleStructure=data;
					$('#vehicleStructureDatagrid').datagrid('beginEdit', data.total-1);
				}
			});
		} else {
			clear(remark, i);
		}
	}
}