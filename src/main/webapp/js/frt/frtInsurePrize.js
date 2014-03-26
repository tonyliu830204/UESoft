var staticFrtInsurePrizeDisabled=false;//判断表单组件禁用或启用
		var staticFrtInsurePrizeParts=null;
		var staticFrtInsurePrizeItems=null;
		var staticFrtInsurePrizeVehicleStructure=null;
		var  staticRpId=null;
		var frtInsureId=null;
		var add = function() {
				$('#frtInsurePrizeSummaryDatagrid').datagrid('unselectAll');
				staticFrtInsurePrizeDisabled=true;
				$('#frtInsurePrizeTabs').tabs('select', '保险估价明细');
				addButton();				
				$('#frtInsurePrizeAddForm').form('clear');
				$('#frtInsurePrize_addResvRealTime').datetimebox('setValue','{now}');
				$('#frtInsurePrize_addResvEnterTime').datetimebox('setValue','{now}');
				$('#frtInsurePrize_detail_stfId').combobox('setValue',parame1);
				findCarLicenseFormat("frtInsurePrize_details_carLicense");
				findDefaultReceptionClass('frtInsurePrize_details_reptId');
				
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_vehicleStructure_clear').linkbutton('enable');
					}
				});
				$('#frtInsurePrizeItemDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findItemByResvId.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_item_add').linkbutton('enable');
						$('#frtInsurePrize_item_remove').linkbutton('enable');
						$('#frtInsurePrize_item_diy').linkbutton('enable');
					}
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_parts_add').linkbutton('enable');
						$('#frtInsurePrize_parts_remove').linkbutton('enable');
					}
				});
				findCarLicenseFormat("frtInsurePrize_details_carLicense");
		}
		/**添加保存取消按钮*/
		var addButton = function() {
			if ($('#save').length == 0 && $('#cancel').length == 0) {
				staticFrtInsurePrizeParts=null;
				staticFrtInsurePrizeItems=null;
				staticFrtInsurePrizeVehicleStructure=null;
				var send = '<a id="send" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-send" plain="true" onclick="sendMsg();">短信发送</a>';
				var service = '<a id="service" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="selectServiceWeave();">维修套餐</a>';
				var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel(true);">取消</a>';
				$('#button').append(send).append(service).append(save).append(cancel);
				$.parser.parse('#button');
				$('#add').linkbutton('disable');
				$('#remove').linkbutton('disable');
				$('#edit').linkbutton('disable');
				$('#search').linkbutton('disable');
				$('#clear').linkbutton('disable');
				$('#redo').linkbutton('disable');
				$('#print').linkbutton('disable');
				$('#set').linkbutton('disable');
				$('#export').linkbutton('disable');
				//requiredAsForm(true,'frtInsurePrizeAddForm');
				requriedAsFormDiy();
				findCarLicenseFormat("frtResevation_details_carLicense");
			}
		}
		/**新增或修改*/
		var _save = function() {
			if(staticFrtInsurePrizeVehicleStructure!=null){
				staticFrtInsurePrizeVehicleStructure = prosceniumUpdate('frtInsurePrizeVehicleStructureDatagrid',staticFrtInsurePrizeVehicleStructure);
			}
			if(staticFrtInsurePrizeParts!=null){
				 staticFrtInsurePrizeParts = prosceniumUpdate('frtInsurePrizePartsDatagrid',staticFrtInsurePrizeParts);
			}
			if(staticFrtInsurePrizeItems!=null){
				 staticFrtInsurePrizeItems = prosceniumUpdate('frtInsurePrizeItemDatagrid',staticFrtInsurePrizeItems);
			}
			var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
			var url = 'frtInsurePrizeAction!';
			if(data){
				url += 'edit.action';
			}else{
				url += 'save.action';
			}
			var vehicle=null;
			if(staticFrtInsurePrizeVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtInsurePrizeVehicleStructure);
			}
			var items=null;
			if(staticFrtInsurePrizeItems==null){
				items="";
			}else{
				items=JSON.stringify(staticFrtInsurePrizeItems);
			}
			var parts=null;
			if(staticFrtInsurePrizeParts==null){
				parts="";
			}else{
				parts=JSON.stringify(staticFrtInsurePrizeParts);
			}
			
			if($('#frtInsurePrizeAddForm').form('validate')){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : url,
					data : $('#frtInsurePrizeAddForm').serialize() + '&' + $('#frtInsurePrizeRushToRepairForm').serialize() + '&vehicle='+vehicle+ '&parts='+parts+ '&items='+items,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							_cancel(false);
							$('#frtInsurePrizeSummaryDatagrid').datagrid('reload');
							if(data){
								var rowIndex=$('#frtInsurePrizeSummaryDatagrid').datagrid('getRowIndex',data);
								$('#frtInsurePrizeSummaryDatagrid').datagrid({
									onLoadSuccess:function(data){
										$('#frtInsurePrizeSummaryDatagrid').datagrid('selectRow',rowIndex);									
									}
								});
							}else{
								frtInsureId=r.obj;
								
							}
						}else{
							alertMsg(r);
						}
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}else{
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			}
		}
		
		var _remove = function (){
			var data=null;
			 data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
			 if(data==null || data==""){
					if((frtInsureId)){
						$.ajax({
							type : 'post',
							url : 'frtInsurePrizeAction!findInsurePrizeById.action',
							dataType : 'json',
							data:'resvId='+frtInsureId,
							success : function(r) {
							   if(r.total>0){
								   data= r.rows[0];
							   }else{
								   alertMsg('对不起，记录不存在！', 'warning');
								    return ;
							   }  
						    }
						});
					}
				}
			var index=findSelectRowIndex('frtInsurePrizeSummaryDatagrid',data);
			if(data){
				$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
					if (r){
						$.ajax({
							type : 'post',
							dataType : 'json',
							url : 'frtInsurePrizeAction!remove.action',
							data : 'resvId=' + data.resvId,
							success : function(r) {
								if (r.success) {
									alertMsg(r);
									_cancel(true);
									$('#frtInsurePrizeSummaryDatagrid').datagrid('load');
									setSelectRow('frtInsurePrizeSummaryDatagrid',index);								
								}else{
									alertMsg(r);
								}
							},
							error : function (r){
							   if(r.readyState == '0' && r.status == '0'){
								   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
							   }
						   }
						});
					}
				});
			}else{
				alertMsg('对不起，请先选中要删除的记录！', 'warning');
			}
		}
		
		
		 function edit(){
			if($('#save').length != 0 && $('#cancel').length != 0){
				return;
			}
			var data=null;
			 data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
			 if(data==null || data==""){
					if((frtInsureId)){
						$.ajax({
							type : 'post',
							url : 'frtInsurePrizeAction!findInsurePrizeById.action',
							dataType : 'json',
							data:'resvId='+frtInsureId,
							success : function(r) {
							   if(r.total>0){
								   data= r.rows[0];
							   }else{
								   alertMsg('对不起，记录不存在！', 'warning');
								    return ;
							   }  
						    }
						});
					}
				}
			if(data){
				staticFrtInsurePrizeDisabled=true;
				$('#frtInsurePrizeRushToRepairForm').form('clear');
				$('#frtInsurePrizeTabs').tabs('select', '保险估价明细');
				addButton();
				$('#frtInsurePrizeAddForm').form('load', data);
				$('#frtInsurePrizeRushToRepairForm').form('load', data);
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId=' + data.resvId
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=' + data.resvId
				});
				
				$('#frtInsurePrizeItemDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findItemByResvId.action?resvId=' + data.resvId
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeParts=data;
						$('#frtInsurePrize_parts_add').linkbutton('enable');
						$('#frtInsurePrize_parts_remove').linkbutton('enable');
					}
				});
				$('#frtInsurePrizeItemDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeItems=data;
						$('#frtInsurePrize_item_add').linkbutton('enable');
						$('#frtInsurePrize_item_remove').linkbutton('enable');
						 $('#frtInsurePrize_item_diy').linkbutton('enable');
					}
				});
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					onLoadSuccess : function (data){
						staticFrtInsurePrizeVehicleStructure=data;
						loadIcons();
						$('#frtInsurePrize_vehicleStructure_clear').linkbutton('enable');
					}
				});
				var carId=$('#frtInsurePrize_details_carLicense').combobox('getValue');
				if(carId==null||carId.length==0)
					carId=-1;
				$('#frtWorkOrderItemDatagrid').treegrid({
					url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+carId
				});
			}else{
				alertMsg('对不起，请先选中要修改的记录！', 'warning');
			}
		}
		//增加车身结构已选图标main
		var loadIcons = function(){
			var vehicle=null;
			if(staticFrtInsurePrizeVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtInsurePrizeVehicleStructure);
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
		//移除标记
		function clear(remark, i) {
			remark.empty();
			var vehicle=null;
			if(staticFrtInsurePrizeVehicleStructure==null){
				vehicle="";
			}else{
				vehicle=JSON.stringify(staticFrtInsurePrizeVehicleStructure);
			}
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!removeVehicleStructure.action',
				data : 'stateId=' +i+ '&vehicle='+vehicle,
				dataType : 'json',
				success : function callback(r) {
					$('#frtInsurePrizeVehicleStructureDatagrid').datagrid('loadData', r);
					var data = $('#frtInsurePrizeVehicleStructureDatagrid').datagrid('getData');
					staticFrtInsurePrizeVehicleStructure=data;
				}
			});
		}
		var _cancel = function(tag) {
			if(tag!=null&&tag==true){
				$('#frtInsurePrizeAddForm').form('clear');
				$('#frtInsurePrize_addResvRealTime').datetimebox('setValue','{now}');
				$('#frtInsurePrize_addResvEnterTime').datetimebox('setValue','{now}');
				$('#frtInsurePrize_detail_stfId').combobox('setValue',parame1);
				$('#frtInsurePrizeRushToRepairForm').form('clear');
				requiredAsForm(false,'frtInsurePrizeAddForm');
				$('#frtInsurePrizeVehicleStructureDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findvehicleStructureList.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_vehicleStructure_clear').linkbutton('disable');
					}
				});
				$('#frtInsurePrizeItemDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findItemByResvId.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_item_add').linkbutton('disable');
						$('#frtInsurePrize_item_remove').linkbutton('disable');
						$('#frtInsurePrize_item_diy').linkbutton('disable');
					}
				});
				$('#frtInsurePrizePartsDatagrid').datagrid({
					url : 'frtInsurePrizeAction!findPartsByResvId.action?resvId=-1',
					onLoadSuccess : function (data){
						$('#frtInsurePrize_parts_add').linkbutton('disable');
						$('#frtInsurePrize_parts_remove').linkbutton('disable');
					}
				});
				for(var i=1;i<=64;i++){
				 	$('#remark'+i+'').empty();
				}
				$('#frtInsurePrizeTabs').tabs('select', '保险估价汇总');
			}else{
				$('#frtInsurePrize_vehicleStructure_clear').linkbutton('disable');
				$('#frtInsurePrize_item_add').linkbutton('disable');
				$('#frtInsurePrize_item_remove').linkbutton('disable');
				$('#frtInsurePrize_item_diy').linkbutton('disable');
				$('#frtInsurePrize_parts_add').linkbutton('disable');
				$('#frtInsurePrize_parts_remove').linkbutton('disable');
			}
			$('#button').empty();
			$('#add').linkbutton('enable');
			$('#remove').linkbutton('enable');
			$('#edit').linkbutton('enable');
			$('#search').linkbutton('enable');
			$('#clear').linkbutton('enable');
			$('#redo').linkbutton('enable');
			$('#print').linkbutton('enable');
			$('#set').linkbutton('enable');
			$('#export').linkbutton('enable');
		};
		function requriedAsFormDiy(){
			$("#frtInsurePrize_details_carLicense").combobox({required:true,disabled:false});
			$("#frtInsurePrize_details_resvVin").combobox({required:true,disabled:false});
			$("#frtInsurePrize_details_reptId").combobox({required:true,disabled:false});
			$("#frtInsurePrize_addResvEnterTime").datetimebox({required:true,disabled:false});
			$("#frtInsurePrize_addResvRealTime").datetimebox({required:true,disabled:true});
			$("#frtInsurePrize_details_customId").combobox({required:true,disabled:false});
			$("#frtInsurePrize_details_repwkId").combobox({required:true,disabled:false});	
			$("#frtInsurePrize_details_resvStatus").combobox({required:true,disabled:false});
			
			$("#frtInsurePrizeAddForm input.easyui-numberbox").numberbox({required:true});
			$("#frtInsurePrizeAddForm input.easyui-numberbox").numberbox('setValue','');
			$("#frtInsurePrizeAddForm input.easyui-numberbox").numberbox("validate");
			
			$("#frtInsurePrize_detail_stfId").combobox({required:true,disabled:true});
			$("#frtInsurePrize_details_repgrpId").combobox({required:false,disabled:false});
			$("#frtInsurePrize_details_resvType").combobox({required:true,disabled:false});
			
			$("#frtInsurePrize_details_resvFixpel").validatebox({required:false});
			$("#frtInsurePrize_details_resvFixtel").validatebox({required:false});
			$("#frtInsurePrize_details_resvFixphone").validatebox({required:false});
			$("#frtInsurePrize_detail_repPer").validatebox({required:true});
			
			$("#frtInsurePrize_details_resvFixpel").validatebox('validate');
			$("#frtInsurePrize_details_resvFixtel").validatebox('validate');
			$("#frtInsurePrize_details_resvFixphone").validatebox('validate');
			$("#frtInsurePrize_detail_repPer").validatebox('validate');
			$("#frtInsurePrizeAddForm input").prop("disabled",false);
			$("#frtInsurePrizeAddForm textarea").prop("disabled",false);
		}
		function query(){
			//$('#frtInsurePrizeSummaryDatagrid').datagrid('unselectAll');
			if($('#frtInsurePrizeQueryForm').form('validate')){
				$('#frtInsurePrizeSummaryDatagrid').datagrid('load', serializeObject($('#frtInsurePrizeQueryForm')));
			}else{
				alertMsg('对不起，请输入正确的查询条件！', 'warning');
			}
		};
		
		function _clear(){
			$('#frtInsurePrizeQueryForm').form('clear');
			$('#frtInsurePrizeSummaryDatagrid').datagrid('load', serializeObject($('#frtInsurePrizeQueryForm')));		
		}
		function blockandhidden(i){
			if(i==0){
				$('#resevationSelect').linkbutton('enable');
			}else if(1){
				$('#resevationSelect').linkbutton('disable');
			}
		}
		var isFrtResevationCancel = function(){
			var  insureData= $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
			if(insureData){
				isFrtResevation(insureData);
			}else  if(frtInsureId){
				$.ajax({
					type : 'post',
					url : 'frtInsurePrizeAction!findInsurePrizeById.action',
					dataType : 'json',
					data:'resvId='+frtInsureId,
					success : function(r) {
					   if(r.total>0){
						   isFrtResevation(r.rows[0]);
					   }else
						   alertMsg('对不起，记录不存在！', 'warning');
				    }
				});
			}else{
				alertMsg('对不起，请先选中要转换的记录！', 'warning');
			}
		}
		
		//判断预约是否取消
		var isFrtResevation = function(rowData){
			if(rowData){
				if(rowData.resvStatus == parame2){
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : 'frtResevationAction!modifyIsBespeakOffById.action',
						data:'resvId='+rowData.resvId,
						success : function(r) {
							if (r.success) {
								alertMsg(r);
								$('#frtInsurePrizeSummaryDatagrid').datagrid('load');
							}else{
								zh(rowData.resvId);
							}
						},
						error : function (r){
						   if(r.readyState == '0' && r.status == '0'){
							   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
						   } 
					   	}
					});
				}else{
			    	 alertMsg('只有预约中的记录才能转前台接车!', 'warning');
		         }	
			}else{
				alertMsg('对不起，请先选中要转换的记录！', 'warning');
			}
		}
		//转换到前台接车
		function zh(resvId){
			if(resvId){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'frtInsurePrizeAction!switchFrt.action',
					data : 'resvId=' + resvId,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							_cancel(true);
							$('#frtInsurePrizeSummaryDatagrid').datagrid('load');							
						}else{
							alertMsg(r);
						}
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   }
				});
			}
		}
		
		//选择维修套餐
		var selectServiceWeaveDialog;
		
		var selectServiceWeave = function (){
			selectServiceWeaveDialog = $('<div/>').dialog({
				href : projectPath+'frt/frtInsurePrize/details/serviceWeave.jsp',
				modal:true,
				title : '选择维修套餐',
				closable : true,
				width : 600,
				height : 400,
				buttons : [
		           {
					 text : '确定',
					 iconCls : 'icon-save',
					 handler : function (){
							loadWeave(1);
					}
			       },{
					 text : '取消',
					 iconCls : 'icon-undo',
					 handler : function (){
						 selectServiceWeaveDialog.dialog('close');
					}
		       }],
		       onClose : function (){
			    	$(this).dialog('destroy');
		       }
			});
		}
		var loadWeave=function (tag){
			var items=null;
			if(staticFrtInsurePrizeItems==null){
				items="";
			}else{
				items=JSON.stringify(staticFrtInsurePrizeItems);
			}
			var parts=null;
			if(staticFrtInsurePrizeParts==null){
				parts="";
			}else{
				parts=JSON.stringify(staticFrtInsurePrizeParts);
			}
			 var rowData = $('#selectServiceWeaveDatagrid').datagrid('getSelected');
        	   $.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtInsurePrizeAction!findPartsListByRpId.action',
				data: 'rpId='+rowData.rpId+'&parts='+parts,
				success : function(r) {
					$('#frtInsurePrizePartsDatagrid').datagrid('loadData', r);
					var data = $('#frtInsurePrizePartsDatagrid').datagrid('getData');
					staticFrtInsurePrizeParts=data;
				}
			}).error(function(r) {
				alertMsg(r);
			});
			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'frtInsurePrizeAction!findItemListByRpId.action',
			   data: 'rpId='+rowData.rpId+'&items='+items,
			   success: function(r){
					$('#frtInsurePrizeItemDatagrid').datagrid('loadData', r);
					var data = $('#frtInsurePrizeItemDatagrid').datagrid('getData');
					staticFrtInsurePrizeItems=data;
			   }
			}).error(function (r){
				alertMsg(r);
			});
			selectServiceWeaveDialog.dialog('close');
		}
		/**短信发送*/
		function sendMsg(){
			var d = $('<div/>').dialog({
				href : projectPath+"frt/sendMessage.jsp",
				modal:true,
				closable : false,
				title : '短信提醒',
				width : 400,
				height : 360,
				buttons : [{
					text : '确定',
					handler : function (){
						if($('#carArchivesAddForm').form('validate')){
							$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: '',
								   data: $('#carArchivesAddForm').serialize(),
								   success: function(r){
									   if(r.success){
										  
										    d.dialog('close');
									   }else{
										    alertMsg(r);
									   }
								   },
								   error : function (r){
									   if(r.readyState == '0' && r.status == '0'){
										   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
									   }
								   }
							});
						}
				}
				},{
					text : '取消',
					handler : function (){
						d.dialog('close');
					}
				}],
			    onLoad : function (){
			    	//$('#frtResevation_details_carLicense').val($('#carLicenseview').val());
		        	//$('#carLicenseview').val(val2);
		        },
				onClose : function (){
			    	d.dialog('destroy');
			    }
			});
		}
		function _except(){
			showEditDialog("frtInsurePrizeSummaryDatagrid",null,"frtInsurePrizeSummaryCenter","开始导出","导出配置",0,_callbackExcept);
		}
		function _callbackExcept(parentId,fieldNames){
			exportEsuyUIExcelFile(parentId,fieldNames,"保险估价单"+getSystemTime());
		}