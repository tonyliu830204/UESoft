        var staticIsCheckButton=true;//为true则启用限制
		var staticFrtWorkkshopManagerDisabled=false;//判断表单组件禁用或启用
		var staticFrtWorkshopManagerParts=null;
		var staticFrtWorkshopManagerItems=null;
		var staticFrtWorkshopManagerVehicleStructure=null;
		var staticFrtWorkshopManageDeatil=false;
		var showRepair=null;
		var opretion=false;
		$(function(){
			staticFrtWorkshopManageDeatil=true;//页面初始化后工单状态可修改状态
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'frtOptionsAction!findDefaultFrtWorkShopManagerFashion.action',
			   success: function(r){
					staticIsCheckButton=r;
			   }
			});
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'baseAction!loadShowrepairvehivle.action',//自动显示在修车辆信息
				   success: function(r){
					   showRepair=r;
				   }
				});
		});
		
		  //车间状态修改事件
		var edit = function (){
			if($('#save').length != 0 && $('#cancel').length != 0){
				return;
			}
			staticFrtWorkshopManageDeatil=false;
			opretion=true;
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'frtWorkshopManagerAction!datagridFrtWorkshop.action',
				   data: 'receptionId='+receptionId,
				   success: function(r){
				     	if(r.total>0){
				     		$('#frtWorkshopManagerTabs').tabs('select', '工单明细');
							addButton();
							$('#frtWorkshopManagerAddForm').form('load', r.rows[0]);
							changeStatus(r.rows[0]);
							
							$('#frtWorkshopManagerPartsDatagrid').datagrid({
								url : 'frtWorkshopManagerAction!findPartsByRcptId.action?receptionId=' + r.rows[0].receptionId
							});
							$('#frtWorkshopManagerItemDatagrid').datagrid({
								url : 'frtWorkshopManagerAction!findItemByRcptId.action?receptionId=' + r.rows[0].receptionId
							});
							$('#vehicleStructureDatagrid').datagrid({
								url : 'frtWorkshopManagerAction!findvehicleStructureList.action?receptionId='+r.rows[0].receptionId
							});
							$('#frtWorkshopManagerExwarehousePartsDatagrid').datagrid({
								url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId='+r.rows[0].receptionId
							});
							$('#frtWorkshopManagerPartsDatagrid').datagrid({
								onLoadSuccess : function (data){
									staticFrtWorkshopManagerParts=data;
									$('#frtWorkshopManager_parts_add').linkbutton('enable');
									$('#frtWorkshopManager_parts_remove').linkbutton('enable');
								}
							});
							$('#frtWorkshopManagerItemDatagrid').datagrid({
								onLoadSuccess : function (data){
									staticFrtWorkshopManagerItems=data;
									$('#frtWorkshopManager_item_add').linkbutton('enable');
									$('#frtWorkshopManager_item_remove').linkbutton('enable');
									 $('#frtWorkshopManager_item_diy').linkbutton('enable');
								}
							});
							$('#vehicleStructureDatagrid').datagrid({
								onLoadSuccess : function (data){
									staticFrtWorkshopManagerVehicleStructure=data;
									loadIcons();
									$('#frtWorkshopManager_vehicleStructure_clear').linkbutton('enable');
								}
							});
				     	}else
				     		alertMsg('对不起，请先选中要修改的记录！', 'warning');
				   }
			});
		}
		var addButton = function() {
			if ($('#save').length == 0 && $('#cancel').length == 0) {
				staticFrtWorkshopManagerParts=null;
				staticFrtWorkshopManagerItems=null;
				staticFrtWorkshopManagerVehicleStructure=null;
				var edit = '<a id="edit" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateDistance();">更改里程数</a>';
				var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel(true);">取消</a>';
				$('#button').append(edit).append(save).append(cancel);
				$.parser.parse('#button');
				$('#edit').linkbutton('disable');
				$('#search').linkbutton('disable');
				$('#clear').linkbutton('disable');
				$('#export').linkbutton('disable');
				$('#redo1').linkbutton('disable');
				$('#print').linkbutton('disable');
				$('#set').linkbutton('disable');
				$('#ok').linkbutton('disable');
				$('#ok1').linkbutton('disable');
				// requiredAsForm(true,'frtWorkshopManagerAddForm');
				requiredAsFormDiy();
				frtWorkkshopManagerDisabled(true);
				staticFrtWorkkshopManagerDisabled=true;
			}
		}
		function updateDistance(){
			var value=$('#frtWorkshopManager_detail_carId').combobox('getValue');
			if(value==null||value.length==0){
				alertMsg('对不起，请先选择车牌照！', 'warning');
				return;
			}
			$('#hh').dialog({
				buttons:[{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						if($('#frtWorkshopManagerDistanceForm').form('validate')){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'frtReceptionAction!modifyDistance.action?carId='+value,
							   data: $('#frtWorkshopManagerDistanceForm').serialize(),
							   success: function(r){
								 if(r.success){
								 	$('#hh').dialog('close');
									 alertMsg(r);
								 }else{
								 	alertMsg(r);
								 }
							   }
							});
						}else{
							alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
						}
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#hh').dialog('close');
					}
				}]
			});
		}
		var _save = function() {
			staticFrtWorkshopManageDeatil=false;
			if(staticFrtWorkshopManagerVehicleStructure!=null){			
				staticFrtWorkshopManagerVehicleStructure = prosceniumUpdate('vehicleStructureDatagrid',staticFrtWorkshopManagerVehicleStructure);
			}
			if(staticFrtWorkshopManagerParts!=null){
				staticFrtWorkshopManagerParts = prosceniumUpdate('frtWorkshopManagerPartsDatagrid',staticFrtWorkshopManagerParts);
			}
			if(staticFrtWorkshopManagerItems!=null){
				staticFrtWorkshopManagerItems = prosceniumUpdate('frtWorkshopManagerItemDatagrid',staticFrtWorkshopManagerItems);
			}
			var url = 'frtWorkshopManagerAction!';
			$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: 'frtWorkshopManagerAction!datagridFrtWorkshop.action',
				   data: 'receptionId='+receptionId,
				   success: function(r){
				     	if(r.total>0){
				     		url += 'edit.action';
				     	}
				     	var vehicle=null;
						if(staticFrtWorkshopManagerVehicleStructure==null){
							vehicle="";
						}else{
							vehicle=JSON.stringify(staticFrtWorkshopManagerVehicleStructure);
						}
						var items=null;
						if(staticFrtWorkshopManagerItems==null){
							items="";
						}else{
							items=JSON.stringify(staticFrtWorkshopManagerItems);
						}
						var parts=null;
						if(staticFrtWorkshopManagerParts==null){
							parts="";
						}else{
							parts=JSON.stringify(staticFrtWorkshopManagerParts);
						}
						if($('#frtWorkshopManagerAddForm').form('validate')){
							$.ajax({
								type : 'post',
								dataType : 'json',
								url : url,
								data : $('#frtWorkshopManagerAddForm').serialize() +'&vehicle='+vehicle+ '&parts='+parts+ '&items='+items,
								success : function (t) {
									if (t.success) {
										alertMsg(t);
										_cancel(false);
										window.opener.win_close();
									}else{
										alertMsg(t);
									}
								},
								error : function (t){
								   if(t.readyState == '0' && t.status == '0')
									   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
							    }
							});
						}else
							alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
				   }
			});
	    }
		var _cancel = function(tag) {
			if(tag!=null&&tag==true){
				$('#frtWorkshopManagerAddForm').form('clear');	
				 requiredAsForm(false,'frtWorkshopManagerAddForm');	
				 frtWorkkshopManagerDisabled(false);	
				$('#frtWorkshopManagerPartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findPartsByRcptId.action?receptionId=-1',
					onLoadSuccess : function (data){
						$('#frtWorkshopManager_parts_add').linkbutton('disable');
						$('#frtWorkshopManager_parts_remove').linkbutton('disable');
					}
				});
				$('#frtWorkshopManagerItemDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findItemByRcptId.action?receptionId=-1',
					onLoadSuccess : function (data){
						$('#frtWorkshopManager_item_add').linkbutton('disable');
						$('#frtWorkshopManager_item_remove').linkbutton('disable');
						$('#frtWorkshopManager_item_diy').linkbutton('disable');
					}
				});
				$('#vehicleStructureDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!findvehicleStructureList.action?receptionId=-1',
					onLoadSuccess : function (data){
						$('#frtWorkshopManager_vehicleStructure_clear').linkbutton('disable');
					}
				});	
				$('#frtWorkshopManagerExwarehousePartsDatagrid').datagrid({
					url : 'frtWorkshopManagerAction!datagridEmerge.action?receptionId=-1'
				});		
				for(var i=1;i<=64;i++){
				 	$('#remark'+i+'').empty();
				 }
				$('#frtWorkshopManagerTabs').tabs('select', '工单汇总');	
			}else{
				$('#frtWorkshopManager_parts_add').linkbutton('disable');
				$('#frtWorkshopManager_parts_remove').linkbutton('disable');
				$('#frtWorkshopManager_item_add').linkbutton('disable');
				$('#frtWorkshopManager_item_remove').linkbutton('disable');
				$('#frtWorkshopManager_item_diy').linkbutton('disable');
				$('#frtWorkshopManager_vehicleStructure_clear').linkbutton('disable');
			}
			$('#button').empty();
			$('#edit').linkbutton('enable');
			$('#search').linkbutton('enable');
			$('#clear').linkbutton('enable');
			$('#export').linkbutton('enable');
			$('#redo1').linkbutton('enable');
			$('#print').linkbutton('enable');
			$('#set').linkbutton('enable');
			$('#ok').linkbutton('enable');
			$('#ok1').linkbutton('enable');
		}
		
		
		//改变工单状态
		var changeStatus=function(rowData){
			var i = rowData.receptionStatus;
			if(staticIsCheckButton=="true"){
				checkButtonDisabled(i);
			}else{
				workshopButtonDisabled(i);
			}
		}
		//转前台操作
		function castProcenium(){
			if(receptionId){
				$.ajax({
					type : 'post',
					url : 'frtWorkshopManagerAction!modifyCastProcenium.action',
					data : 'receptionId=' +receptionId,
					dataType : 'json',
					success : function callback(r) {
						if (r.success) {
							alertMsg(r);
							_cancel(true);
							window.opener.win_close();
						}else
							alertMsg(r);
					}
				});
			}else
				alertMsg('对不起，请先选中要转换的记录！', 'warning');
		}
		
		//派工操作
		var standReceptionStatus=function(id){
			var receptionInsurPer=$('#frtWorkshopManager_detail_receptionInsurPer').combobox('getValue');
			var receptionTechnician=$('#frtWorkshopManager_detail_receptionTechnician').combobox('getValue');
			var repgrpId=$('#frtWorkshopManager_details_repgrpId').combobox('getValue');
			var repwkId=$('#frtWorkshopManager_details_repwkId').combobox('getValue');
	/*		if(receptionInsurPer==null||receptionInsurPer==''||receptionTechnician==null||receptionTechnician==''
				||repgrpId==null||repgrpId==''||repwkId==null||repwkId==''){
				alertMsg('对不起，工单信息不完整，<br>请先补充完整信息!', 'warning');
				return;
			}*/
			var status=$('#receptionStatus').val();
			if($('#frtWorkshopManagerAddForm').form('validate')){		
			if(staticFrtWorkshopManageDeatil==true){
				var receptionId=$('#frtWorkshopManager_detail_receptionId').val();
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'frtWorkshopManagerAction!modifyReceptionStatus.action',
					data : 'receptionId='+receptionId+'&receptionStatus='+status,
					success : function(r) {
						if(r.success){
							alertMsg(r);
							window.opener.win_close();
							if(!(status==state8)){
								if(staticIsCheckButton=="true"){
									checkButtonDisabled(status);
								}else{
									workshopButtonDisabled(status);
								}	
							}else{
								disabledAllButton();
							}
						}else{
							alertMsg(r);
							resizeButton(status);
						}
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   } 
				   	}
				});
			}
		  }else{
			  alertMsg('对不起，工单信息不完整，<br>请先补充完整信息!', 'warning');
			  resizeButton(status);
		  }
		}
		function resizeButton(status){
			var tagName=null;
			if(state2==status){
				tagName="pg";
			}else if(state3==status){
				tagName="kg";
			}else if(state4==status){
				tagName="wxz";
			}else if(state5==status){
				tagName="ddlj";
			}else if(state6==status){
				tagName="dddf";
			}else if(state7==status){
				tagName="zljc";
			}else if(state8==status){
				tagName="wg";
			}
			$('#workshopButtons button[name='+tagName+']').removeAttr('disabled');
		}
		//限制更改工单状态
		var checkButtonDisabled = function (i){
			if(i==state1){
				  $('#workshopButtons button[name=pg]').removeAttr('disabled');
				  $('#kg').attr('disabled', 'true');
				  $('#wxz').attr('disabled', 'true');
				  $('#ddlj').attr('disabled', 'true');
				  $('#dddf').attr('disabled', 'true');
				  $('#zljc').attr('disabled', 'true'); 
				  $('#wg').attr('disabled', 'true'); 
			}else if(i==state2){
				  $('#pg').attr('disabled', 'true'); 
				  $('#workshopButtons button[name=kg]').removeAttr('disabled');
				  $('#wxz').attr('disabled', 'true');
				  $('#ddlj').attr('disabled', 'true');
				  $('#dddf').attr('disabled', 'true');
				  $('#zljc').attr('disabled', 'true'); 
				  $('#wg').attr('disabled', 'true'); 
			}else if(i==state3){
				 $('#pg').attr('disabled', 'true');
				 $('#kg').attr('disabled', 'true'); 
				 $('#wg').attr('disabled', 'true'); 
				 $('#workshopButtons button[name=wxz]').removeAttr('disabled');
				 $('#workshopButtons button[name=ddlj]').removeAttr('disabled');
			     $('#workshopButtons button[name=dddf]').removeAttr('disabled');
			     $('#workshopButtons button[name=zljc]').removeAttr('disabled');
			}else if(i==state4){
				$('#pg').attr('disabled', 'true');
			    $('#kg').attr('disabled', 'true'); 
			    $('#workshopButtons button[name=ddlj]').removeAttr('disabled');
			    $('#workshopButtons button[name=dddf]').removeAttr('disabled');
			    $('#workshopButtons button[name=zljc]').removeAttr('disabled');
			    $('#wxz').attr('disabled', 'true'); 
			    $('#wg').attr('disabled', 'true'); 
			}else if(i==state5){
				$('#pg').attr('disabled', 'true');
			    $('#kg').attr('disabled', 'true'); 
			    $('#workshopButtons button[name=wxz]').removeAttr('disabled');
			    $('#workshopButtons button[name=dddf]').removeAttr('disabled');
			    $('#workshopButtons button[name=zljc]').removeAttr('disabled');
			    $('#ddlj').attr('disabled', 'true'); 
			    $('#wg').attr('disabled', 'true'); 
			}else if(i==state6){
				$('#pg').attr('disabled', 'true');
			    $('#kg').attr('disabled', 'true');
			    $('#workshopButtons button[name=wxz]').removeAttr('disabled');
			    $('#workshopButtons button[name=ddlj]').removeAttr('disabled');
			    $('#workshopButtons button[name=zljc]').removeAttr('disabled');
			    $('#dddf').attr('disabled', 'true');
			    $('#wg').attr('disabled', 'true');
			}else if(i==state7){
			    $('#pg').attr('disabled', 'true');
			    $('#kg').attr('disabled', 'true'); 
			    $('#workshopButtons button[name=wxz]').removeAttr('disabled');
			    $('#workshopButtons button[name=ddlj]').removeAttr('disabled'); 
			    $('#workshopButtons button[name=dddf]').removeAttr('disabled');
			    $('#zljc').attr('disabled', 'true'); 
			    $('#workshopButtons button[name=wg]').removeAttr('disabled');
			}else if(i==state8){
				$('#pg').attr('disabled', 'true');
				$('#workshopButtons button[name=kg]').removeAttr('disabled');
				$('#wxz').attr('disabled', 'true');
				$('#ddlj').attr('disabled', 'true');
				$('#dddf').attr('disabled', 'true');
				$('#zljc').attr('disabled', 'true'); 
				$('#wg').attr('disabled', 'true'); 
			}
		}
		
		//不限制更改工单状态
		var workshopButtonDisabled = function (i){
			if(i==state1){
				   $('#workshopButtons button').removeAttr('disabled');
			}else if(i==state2){
				$('#pg').attr('disabled', 'true'); 
				$('#workshopButtons button[name!=pg]').removeAttr('disabled');
			}else if(i==state3){
				 $('#kg').attr('disabled', 'true'); 
				 $('#workshopButtons button[name!=kg]').removeAttr('disabled');
			}else if(i==state4){
				$('#wxz').attr('disabled', 'true'); 
				$('#workshopButtons button[name!=wxz]').removeAttr('disabled');
			}else if(i==state5){
				$('#ddlj').attr('disabled', 'true');
				 $('#workshopButtons button[name!=ddlj]').removeAttr('disabled');
			}else if(i==state6){
				$('#dddf').attr('disabled', 'true');
				 $('#workshopButtons button[name!=dddf]').removeAttr('disabled');
			}else if(i==state7){
				 $('#zljc').attr('disabled', 'true');
				  $('#workshopButtons button[name!=zljc]').removeAttr('disabled');
			}
		}
		
		function disabledAllButton(){
			$('#pg').attr('disabled', 'true');
			$('#kg').attr('disabled', 'true');
			$('#wxz').attr('disabled', 'true');
			$('#ddlj').attr('disabled', 'true');
			$('#dddf').attr('disabled', 'true');
			$('#zljc').attr('disabled', 'true'); 
			$('#wg').attr('disabled', 'true'); 
	}
		
		//控制按钮可用不可用事件
		function requiredAsFormDiy(){
			$("#frtWorkshopManager_detail_carId").combobox({required:true,disabled:true});
			$("#frtWorkshopManager_detail_customId").combobox({required:true,disabled:true});
			$("#frtWorkshopManager_detail_receptionMaintFlg").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_detail_finComId").combobox({required:true,disabled:true});
			$("#frtWorkshopManager_detail_receptionInsurPer").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_detail_receptionTechnician").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_detail_valuables").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_detail_reptId").combobox({required:true,disabled:false});
			
			$("#frtWorkshopManager_details_fuelSituation").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_details_oldPieces").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_details_repgrpId").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_details_repwkId").combobox({required:true,disabled:false});
			$("#frtWorkshopManager_details_receptor").combobox({required:true,disabled:true});
			$("#frtWorkshopManagerAddForm textarea").prop("disabled",false);
			
			$("#frtWorkshopManager_detail_receptionEndTime").datetimebox({required:true,disabled:false});
			$("#frtWorkshopManager_detail_expDelCarTime").datetimebox({required:true,disabled:false});
			
			$("#frtWorkshopManagerAddForm input.easyui-numberbox").numberbox({required:true});
			$("#frtWorkshopManagerAddForm input.easyui-numberbox").numberbox('setValue','');
			$("#frtWorkshopManagerAddForm input.easyui-numberbox").numberbox("validate");
			
			$("#frtWorkshopManagerAddForm input").prop("disabled",false);
			$("#frtWorkshopManager_detail_receptionId").validatebox({required:true});
			$("#frtWorkshopManager_detail_receptionDistance").validatebox({required:true});
			$("#frtWorkshopManager_detail_receptionRepPer").validatebox({required:false});
			$("#frtWorkshopManager_detail_propRepPer").validatebox({required:false});
			$("#frtWorkshopManager_detail_propPhone").validatebox({required:false});
			$("#frtWorkshopManager_detail_propTel").validatebox({required:false});
			
			$("#frtWorkshopManager_detail_receptionId").validatebox('validate');
			$("#frtWorkshopManager_detail_receptionDistance").validatebox('validate');
			$("#frtWorkshopManager_detail_receptionRepPer").validatebox('validate');
			$("#frtWorkshopManager_detail_propRepPer").validatebox('validate');
			$("#frtWorkshopManager_detail_propPhone").validatebox('validate');
			$("#frtWorkshopManager_detail_propTel").validatebox('validate');
		}
		
		//禁用与启用工单状态按钮
		var frtWorkkshopManagerDisabled=function(flag){
			$('#pg').attr('disabled', ''+flag+'');
			$('#kg').attr('disabled', ''+flag+'');
			$('#wxz').attr('disabled', ''+flag+'');
			$('#ddlj').attr('disabled', ''+flag+'');
			$('#dddf').attr('disabled', ''+flag+'');
			$('#zljc').attr('disabled', ''+flag+'');
			$('#wg').attr('disabled', ''+flag+'');
			if(flag==true){
				$('#workshopButtons button[name=pg]').removeAttr('disabled');
				$('#workshopButtons button[name=kg]').removeAttr('disabled');
				$('#workshopButtons button[name=wxz]').removeAttr('disabled');
				$('#workshopButtons button[name=ddlj]').removeAttr('disabled');
				$('#workshopButtons button[name=dddf]').removeAttr('disabled');
				$('#workshopButtons button[name=zljc]').removeAttr('disabled');
				$('#workshopButtons button[name=wg]').removeAttr('disabled');
			}
		}
		//增加车身结构已选图标main
		var loadIcons = function(){
			var vehicle=null;
			if(staticFrtWorkshopManagerVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtWorkshopManagerVehicleStructure);
			}
			$.ajax({
			   type : 'post',
			   dataType : 'json',
			   url: 'frtInsurePrizeAction!findAllIdVehicleStructure.action',
			   data: 'vehicle='+vehicle,
			   success: function (r){
			   		var tempdata=r;
			   		if(tempdata!=null){
			   			var arrays=new Array();
				   		arrays=tempdata.split(",");
				   		for(var i=0;i<arrays.length;i++){
				   			loadSelectedIcon(arrays[i],$('#remark'+arrays[i]+''));
				   		}
			   		}
			   }	
			 });
		}
		//增加车身结构已选图标
		function loadSelectedIcon(i,remark){
			var image = '<img src=\"images/sign_tick.png\" id=\"img'+i+'\"/>';
			var imgId = 'img' + i;
			if (remark.children('img').length == 0) {
				remark.append(image);
				$('#' + imgId).on('click', function() {
					clear(remark, i);
				});
			}
		}