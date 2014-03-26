
var staticFrtResevationDisabled=false;//判断表单组件禁用或启用
var staticFrtResevationParts=null;
var staticFrtResevationItems=null;
var staticFrtResevationVehicleStructure=null;
var  frtReservationId=null;
var ds;
var staticRpId=null;
$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url:'baseAction!loadRevationTime.action',  
	   success: function(r){
			ds=r.ciValue;
	   },
	   error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   }
	   
	});
var add = function() {
	var enterTime= new Date();
	enterTime = enterTime.valueOf();           
	enterTime = enterTime + ds* 60 * 60 * 1000 ;
	enterTime= new Date(enterTime).format("yyyy-MM-dd hh:mm:ss");
	$('#frtResevationSummaryDatagrid').datagrid('unselectAll');
	staticFrtResevationDisabled=true;
	//明细页面第一次加载的时候设置默认预约时间
	$('#frtResevationTabs').tabs({
		onLoad: function(panel){
				$('#addResvEnterTime').datetimebox('setValue',enterTime);
		  }
		});
	$('#frtResevationTabs').tabs('select', '预约明细');
	addButton();
	$('#frtResevationAddForm').form('clear');
	$('#addResvRealTime').datetimebox('setValue','{now}');
	$('#addResvEnterTime').datetimebox('setValue',enterTime);
	$('#resevation_detail_stfId').combobox('setValue',parame1);
	findCarLicenseFormat("frtResevation_details_carLicense");
	findDefaultReceptionClass('frtResevation_details_reptId');
		$('#vehicleStructureDatagrid').datagrid({
			url : 'frtResevationAction!findvehicleStructureList.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_vehicleStructure_clear').linkbutton('enable');
			}
		});
		$('#frtResevationItemDatagrid').datagrid({
			url : 'frtResevationAction!findItemByResvId.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_item_add').linkbutton('enable');
				$('#frtResevation_item_remove').linkbutton('enable');
				$('#frtResevation_item_diy').linkbutton('enable');
			}
		});
		$('#frtResevationPartsDatagrid').datagrid({
			url : 'frtResevationAction!findPartsByResvId.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_parts_add').linkbutton('enable');
				$('#frtResevation_parts_remove').linkbutton('enable');
			}
		});
		findCarLicenseFormat("frtResevation_details_carLicense");
}
/**添加保存取消按钮*/
var addButton = function() {
	if ($('#save').length == 0 && $('#cancel').length == 0) {
		staticFrtResevationParts=null;
		staticFrtResevationItems=null;
		staticFrtResevationVehicleStructure=null;
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
		//requiredAsForm(true,'frtResevationAddForm');
		requriedAsFormDiy();
		//findCarLicenseFormat("frtResevation_details_carLicense");
	}
}
/**新增或修改*/
var _save = function() {
	if(staticFrtResevationVehicleStructure!=null){
		staticFrtResevationVehicleStructure = prosceniumUpdate('vehicleStructureDatagrid',staticFrtResevationVehicleStructure);
	}
	if(staticFrtResevationParts!=null){
		staticFrtResevationParts = prosceniumUpdate('frtResevationPartsDatagrid',staticFrtResevationParts);
	}
	if(staticFrtResevationItems!=null){
		staticFrtResevationItems = prosceniumUpdate('frtResevationItemDatagrid',staticFrtResevationItems);
	}
	var data = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
	var url = 'frtResevationAction!';
	if(data){
		url += 'edit.action';
	}else{
		url += 'save.action';
	}
	var vehicle=null;
	if(staticFrtResevationVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtResevationVehicleStructure);
	}
	var items=null;
	if(staticFrtResevationItems==null){
		items="";
	}else{
		items=JSON.stringify(staticFrtResevationItems);
	}
	var parts=null;
	if(staticFrtResevationParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtResevationParts);
	}
	
	if($('#frtResevationAddForm').form('validate')){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : $('#frtResevationAddForm').serialize() + '&' + $('#rushToRepairForm').serialize() + '&vehicle='+vehicle+ '&parts='+parts+ '&items='+items,
			success : function(r) {
				if (r.success) {
					alertMsg(r);
					_cancel(false);
					$('#frtResevationSummaryDatagrid').datagrid('reload');
					if(data){
						var rowIndex=$('#frtResevationSummaryDatagrid').datagrid('getRowIndex',data);
						$('#frtResevationSummaryDatagrid').datagrid({
							onLoadSuccess:function(data){
								$('#frtResevationSummaryDatagrid').datagrid('selectRow',rowIndex);									
							}
						});
					}else{
						frtReservationId=r.obj;
						
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
	data= $('#frtResevationSummaryDatagrid').datagrid('getSelected');
	if(data==null || data==""){
		if(frtReservationId){
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!findResevationById.action',
				dataType : 'json',
				data:'resvId='+frtReservationId,
				success : function(r) {
				   if(r.total>0){
					   data= r.rows[0];
				   }else{
					   alertMsg('对不起，记录不存在！', 'warning');
					    return ;
				   }  
			    }
			});
		}else{
			alertMsg('对不起，请先选中要删除的记录！', 'warning');
			return ;
		}
	}
	var index=findSelectRowIndex('frtResevationSummaryDatagrid',data);
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
					type : 'post',
					dataType : 'json',
					url : 'frtResevationAction!remove.action',
					data : 'resvId=' + data.resvId,
					success : function(r) {
						if (r.success) {
							alertMsg(r);
							_cancel(true);
							$('#frtResevationSummaryDatagrid').datagrid('load');
							setSelectRow('frtResevationSummaryDatagrid',index);							
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
	}
}

var detailData=null;
var edit = function (){
	var data=null;
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	data = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
	if(data==null || data==""){
		if(frtReservationId){
			$.ajax({
				type : 'post',
				url : 'frtResevationAction!findResevationById.action',
				dataType : 'json',
				data:'resvId='+frtReservationId,
				success : function(r) {
				   if(r.total>0){
					   data= r.rows[0];
				   }else{
					   alertMsg('对不起，记录不存在！', 'warning');
					    return ;
				   }  
			    }
			});
		}else{
			alertMsg('对不起，请先选中要修改的记录！', 'warning');
			return ;
		}
	}
	if(data){
		if(data.resvStatus != parame2){
			alertMsg('非预约中不能修改！', 'warning');
			return ;
		}
		staticFrtResevationDisabled=true;
		$('#rushToRepairForm').form('clear');
		$('#frtResevationTabs').tabs('select', '预约明细');
		addButton();
		detailData=data;
		$('#frtResevationAddForm').form('load', data);
		$('#rushToRepairForm').form('load', data);
		$('#vehicleStructureDatagrid').datagrid({
			url : 'frtResevationAction!findvehicleStructureList.action?resvId=' + data.resvId,
			onLoadSuccess : function (data){
			staticFrtResevationVehicleStructure=data;
			loadIcons();
			$('#frtResevation_vehicleStructure_clear').linkbutton('enable');
		  }
		});
		
		$('#frtResevationPartsDatagrid').datagrid({
			url : 'frtResevationAction!findPartsByResvId.action?resvId=' + data.resvId,
			onLoadSuccess : function (data){
			staticFrtResevationParts=data;
			$('#frtResevation_parts_add').linkbutton('enable');
			$('#frtResevation_parts_remove').linkbutton('enable');
		   }
		});
		
		$('#frtResevationItemDatagrid').datagrid({
			url : 'frtResevationAction!findItemByResvId.action?resvId=' + data.resvId,
			onLoadSuccess : function (data){
			staticFrtResevationItems=data;
			$('#frtResevation_item_add').linkbutton('enable');
			$('#frtResevation_item_remove').linkbutton('enable');
			 $('#frtResevation_item_diy').linkbutton('enable');
	     	}
		});
		
/*		$('#frtResevationPartsDatagrid').datagrid({
			onLoadSuccess : function (data){
				staticFrtResevationParts=data;
				$('#frtResevation_parts_add').linkbutton('enable');
				$('#frtResevation_parts_remove').linkbutton('enable');
			}
		});
		$('#frtResevationItemDatagrid').datagrid({
			onLoadSuccess : function (data){
				staticFrtResevationItems=data;
				$('#frtResevation_item_add').linkbutton('enable');
				$('#frtResevation_item_remove').linkbutton('enable');
				 $('#frtResevation_item_diy').linkbutton('enable');
			}
		});
		$('#vehicleStructureDatagrid').datagrid({
			onLoadSuccess : function (data){
				staticFrtResevationVehicleStructure=data;
				loadIcons();
				$('#frtResevation_vehicleStructure_clear').linkbutton('enable');
			}
		});*/
		var carId=$('#frtResevation_details_carLicense').combobox('getValue');
		if(carId==null||carId.length==0)
			carId=-1;
		$('#frtWorkOrderItemDatagrid').treegrid({
			url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+carId
		});
	}
}
//增加车身结构已选图标main
var loadIcons = function(){
	var vehicle=null;
	if(staticFrtResevationVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtResevationVehicleStructure);
	}
	$.ajax({
	   type : 'post',
	   dataType : 'json',
	   url: 'frtResevationAction!findAllIdVehicleStructure.action',
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
	if(staticFrtResevationVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtResevationVehicleStructure);
	}
	$.ajax({
		type : 'post',
		url : 'frtResevationAction!removeVehicleStructure.action',
		data : 'stateId=' +i+ '&vehicle='+vehicle,
		dataType : 'json',
		success : function callback(r) {
			$('#vehicleStructureDatagrid').datagrid('loadData', r);
			var data = $('#vehicleStructureDatagrid').datagrid('getData');
			staticFrtResevationVehicleStructure=data;
		}
	});
}

var _cancel = function(tag) {
	if(tag!=null&&tag==true){
		$('#frtResevationAddForm').form('clear');
		$('#addResvRealTime').datetimebox('setValue','{now}');
		$('#addResvEnterTime').datetimebox('setValue','{now}');
		$('#resevation_detail_stfId').combobox('setValue',parame1);
		$('#rushToRepairForm').form('clear');
		requiredAsForm(false,'frtResevationAddForm');
		$('#vehicleStructureDatagrid').datagrid({
			url : 'frtResevationAction!findvehicleStructureList.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_vehicleStructure_clear').linkbutton('disable');
			}
		});
		$('#frtResevationItemDatagrid').datagrid({
			url : 'frtResevationAction!findItemByResvId.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_item_add').linkbutton('disable');
				$('#frtResevation_item_remove').linkbutton('disable');
				$('#frtResevation_item_diy').linkbutton('disable');
			}
		});
		$('#frtResevationPartsDatagrid').datagrid({
			url : 'frtResevationAction!findPartsByResvId.action?resvId=-1',
			onLoadSuccess : function (data){
				$('#frtResevation_parts_add').linkbutton('disable');
				$('#frtResevation_parts_remove').linkbutton('disable');
			}
		});			
		 for(var i=1;i<=64;i++){
		 	$('#remark'+i+'').empty();
		 }
		$('#frtResevationTabs').tabs('select', '预约汇总');			
	}else{
		$('#frtResevation_vehicleStructure_clear').linkbutton('disable');
		$('#frtResevation_item_add').linkbutton('disable');
		$('#frtResevation_item_remove').linkbutton('disable');
		$('#frtResevation_item_diy').linkbutton('disable');
		$('#frtResevation_parts_add').linkbutton('disable');
		$('#frtResevation_parts_remove').linkbutton('disable');
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
	$("#frtResevation_details_carLicense").combobox({required:true,disabled:false});
	$("#frtResevation_details_resvVin").combobox({required:true,disabled:false});
	$("#frtResevation_details_reptId").combobox({required:true,disabled:false});
	$("#addResvEnterTime").datetimebox({required:true,disabled:false});
	$("#addResvRealTime").datetimebox({required:true,disabled:true});
	$("#frtResevation_details_customId").combobox({required:true,disabled:false});
	//$("#frtResevation_details_repwkId").combobox({required:true,disabled:false});	
	$("#frtResevation_details_resvStatus").combobox({required:true,disabled:false});
	
	$("#frtResevationAddForm input.easyui-numberbox").numberbox({required:true});
	$("#frtResevationAddForm input.easyui-numberbox").numberbox('setValue','');
	$("#frtResevationAddForm input.easyui-numberbox").numberbox("validate");
	
	$("#resevation_detail_stfId").combobox({required:true,disabled:true});
	$("#frtResevation_details_repgrpId").combobox({required:false,disabled:false});
	$("#frtResevation_details_carRealationSex").combobox({required:false,disabled:false});
	$("#frtResevation_details_resvType").combobox({required:true,disabled:false});
	
	$("#frtResevation_details_resvFixpel").validatebox({required:false});
	$("#frtResevation_details_resvFixtel").validatebox({required:false});
	$("#frtResevation_details_resvFixphone").validatebox({required:false});
	
	$("#frtResevation_details_resvFixpel").validatebox('validate');
	$("#frtResevation_details_resvFixtel").validatebox('validate');
	$("#frtResevation_details_resvFixphone").validatebox('validate');
	$("#frtResevationAddForm input").prop("disabled",false);
	$("#frtResevationAddForm textarea").prop("disabled",false);
}
function query(){
	//$('#frtResevationSummaryDatagrid').datagrid('unselectAll');
	if($('#frtResevationQueryForm').form('validate')){
		$('#frtResevationSummaryDatagrid').datagrid('load', serializeObject($('#frtResevationQueryForm')));
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
	
};

function _clear(){
	$('#frtResevationQueryForm').form('clear');	
	$('#frtResevationSummaryDatagrid').datagrid('load', serializeObject($('#frtResevationQueryForm')));
		
}
function blockandhidden(i){
	if(i==0){
		$('#resevationSelect').linkbutton('enable');
	}else if(1){
		$('#resevationSelect').linkbutton('disable');
	}
}
/**
 * 将预约单转换为接车单
 * @return
 */
var isFrtResevationCancel = function(){
	var  resevationData= $('#frtResevationSummaryDatagrid').datagrid('getSelected');
	if(resevationData){
		isFrtResevation(resevationData);
	}else{
		alertMsg('对不起，请先选中要转换的记录！', 'warning');
	}
}
//判断预约是否取消或者已经转前台
var isFrtResevation = function(data){
	if(data){
		if(data.resvStatus == parame2){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtResevationAction!findBespeakOffById.action',
				data:'resvId='+data.resvId,
				success : function(r) {
					if (r.success) {
						zh(data.resvId);
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
	    	 alertMsg('只有预约中的记录才能转前台接车!', 'warning');
         }
	}
}
//转换到前台接车
function zh(resvId){
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'frtResevationAction!switchFrt.action',
			data : 'resvId=' + resvId,
			success : function(r) {
				if (r.success) {
					alertMsg(r);
					_cancel(true);
					$('#frtResevationSummaryDatagrid').datagrid('load');							
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

//选择维修套餐
var selectServiceWeaveDialog;

var selectServiceWeave = function (){
	selectServiceWeaveDialog = $('<div/>').dialog({
		href : projectPath+'frt/frtResevation/details/serviceWeave.jsp',
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
			 	loadWeave();
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
var loadWeave=function (){
	var items=null;
	if(staticFrtResevationItems==null){
		items="";
	}else{
		items=JSON.stringify(staticFrtResevationItems);
	}
	var parts=null;
	if(staticFrtResevationParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtResevationParts);
	}
	 var rowData = $('#selectServiceWeaveDatagrid').datagrid('getSelected');
	   $.ajax({
		type : 'post',
		dataType : 'json',
		url : 'frtResevationAction!findPartsListByRpId.action',
		data: 'rpId='+rowData.rpId+'&parts='+parts,
		success : function(r) {
			$('#frtResevationPartsDatagrid').datagrid('loadData', r);
			var data = $('#frtResevationPartsDatagrid').datagrid('getData');
			staticFrtResevationParts=data;
		}
	}).error(function(r) {
		alertMsg(r);
	});
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtResevationAction!findItemListByRpId.action',
	   data: 'rpId='+rowData.rpId+'&items='+items,
	   success: function(r){
			$('#frtResevationItemDatagrid').datagrid('loadData', r);
			var data = $('#frtResevationItemDatagrid').datagrid('getData');
			staticFrtResevationItems=data;
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
	//
}
function _except(){
	showEditDialog("frtResevationSummaryDatagrid",null,"frtResevationSummaryCenter","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"预约申请单"+getSystemTime());
}
/**
 * 打印字段设置
 * 编辑、选择不同分组
 * @return
 */
function _config(){
	showEditDialog("frtResevationSummaryDatagrid",personnelSumTable,"frtResevationSummaryCenter","开始打印","打印配置",2,_print);
}
/**
 * 打印字段设置回调函数
 * 将选择的列打印
 * @return
 */
function _print(parentId,fieldNames){
	printEsuyUIPreview(parentId,fieldNames);
}
var resvId;
function _otherPrint(){
	var rows = $('#frtResevationSummaryDatagrid').datagrid('getSelections');//获得要选择上的行的对象
	if(rows.length > 0){
		 this.resvId = rows[0].resvId;
	     coloropen(personneltempletA,searchprintTemple);
	}else{
		 $.messager.alert('优亿软件提示','对不起,请先选中要打印记录 !','warning');
	}
}
/**
 * 套打模板打印回调函数
 * 对选择的模板打印
 * @param id
 * @return
 */
function searchprintTemple(id){
	$.ajax({
	 	   type: 'post',
	 	   dataType: 'html',
	 	   url:'frtResevationAction!print.action',
	 	   data:{printTempletId: id,resvId:this.resvId},
	 	   success: function(data){
			   showPrintDialog(data);
	 	   },
	 	   error : function (r){
	 		   if(r.readyState == '0' && r.status == '0'){
	 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
	 		   }
	 	   }
	 });
}
/**
 * 取消预约单
 * @return
 */
function cancel_draft(){
	var data= $('#frtResevationSummaryDatagrid').datagrid('getSelected');
	if(data){
		if(data){
			if(data.resvStatus == parame2){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'frtResevationAction!modifyIsBespeakOffById.action',
				data:'resvId='+data.resvId,
				success : function(r) {
					if (r.success) {
						alertMsg(r);
						$('#frtResevationSummaryDatagrid').datagrid('load');
					}else{
						zh(data.resvId);
					}
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   } 
			   	}
			});
	     }else{
	    	 alertMsg('只有预约中的记录才能取消!', 'warning');
	         }
		}
	}else{
		$.messager.alert('优亿软件提示','对不起,请先选中要取消的记录 !','warning');
	}
}