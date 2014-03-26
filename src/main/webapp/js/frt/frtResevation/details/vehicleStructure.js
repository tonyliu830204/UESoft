   //预约申请单->车身状况datagrid
	function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
	var data = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
		if(data){
			$vehicleStructureDatagrid.datagrid({
				url : 'frtResevationAction!findvehicleStructureList.action?resvId='+data.resvId
			});
		}else{
			$vehicleStructureDatagrid.datagrid({
				url : 'frtResevationAction!findvehicleStructureList.action?resvId=-1'
			});
		}
		if($('#save').length != 0 && $('#cancel').length != 0){
			$('#vehicleStructureDatagrid').datagrid({
				onLoadSuccess : function (data){
					staticFrtResevationVehicleStructure=data;
					loadIcons();
					$('#frtResevation_vehicleStructure_accept').linkbutton('enable');
					$('#frtResevation_vehicleStructure_clear').linkbutton('enable');
				}
			});
		}
	}
	setTimeout("LoadOk();", 200);
	
	$(function (){
		
		//预约申请单->车身状况datagrid
		var url='';
		var data = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
		if(data){
			url='frtResevationAction!findvehicleStructureList.action?resvId='+data.resvId;
		}
		$vehicleStructureDatagrid = $('#vehicleStructureDatagrid');
		$vehicleStructureDatagrid.datagrid({
			url: url,
			singleSelect : true,
			rownumbers : true,
			fit : true,
			fitColumns : true,
			loadMsg : "数据加载中，请稍后……",
			columns: [[
			    {field:'code',title:'部位',width:130,sortable:true},
			    {field:'name',title:'名称',width:130,sortable:true},
			    {field:'state',title:'状态描述',width:350,sortable:true,
			    	editor : {
						type : 'text'
					}
				}
			]],
			toolbar: [{
				id : 'frtResevation_vehicleStructure_clear',
				text : '清空',
				disabled : true,
				iconCls: 'icon-cancel',
				handler: function(){
					var vehicle=null;
					if(staticFrtResevationVehicleStructure==null){
						vehicle="";
					}else{
						vehicle=JSON.stringify(staticFrtResevationVehicleStructure);
					}
				 	$.ajax({
						type : 'post',
						url : 'frtResevationAction!clearVehicleStructure.action',
						data : 'vehicle='+vehicle,
						dataType : 'json',
						success : function callback(r) {
							$('#vehicleStructureDatagrid').datagrid('loadData', r);
							var data = $('#vehicleStructureDatagrid').datagrid('getData');
							staticFrtResevationVehicleStructure=data;
						}
					});
					 for(var i=1;i<=64;i++){
					 	$('#remark'+i+'').empty();
					 }
				}
			}],
			onClickRow : function (rowIndex, rowData){
			   if($('#save').length != 0 && $('#cancel').length != 0){
				   $(this).datagrid('beginEdit', rowIndex);
			   }
		   },
		   onLoadSuccess : function (data){
				staticFrtResevationVehicleStructure=data;
		   }
		});
	});
	
	//点击部位触发事件
	function addRemark(i, remark) {
		var image = '<img src=\"../images/sign_tick.png\" id=\"img'+i+'\"/>';
		var imgId = 'img' + i;
		if (remark.children('img').length == 0) {
			remark.append(image);
			$('#' + imgId).on('click', function() {
				clear(remark, i);
			});
			var vehicle=null;
			if(staticFrtResevationVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtResevationVehicleStructure);
			}
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!addVehicleStructure.action',
				data : 'stateId=' + parseInt(i) + '&vehicle='+vehicle,
				dataType : 'json',
				success : function callback(r) {
					$('#vehicleStructureDatagrid').datagrid('loadData',r);
					var data = $('#vehicleStructureDatagrid').datagrid('getData');
					staticFrtResevationVehicleStructure=data;
					var index=data.total;
					if(index>0){
						$('#vehicleStructureDatagrid').datagrid('beginEdit', index-1);
					}
				}
			});
		} else {
			clear(remark, i);
		}
	}