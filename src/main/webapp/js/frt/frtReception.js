var frtReception_carId="";                                      //车编号
var frtReceptionCount=0;                                        //车牌照选择标识	
var staticResvId="";                                            //预订单编号
var staticRcptCarId="";                                         //预约单车编号
var staticRcptCarLicense="";                                    //预约单车牌照		
var staticFrtReceptionParts=null;                               //计划材料
var staticFrtReceptionItems=null;                               //计划项目
var staticFrtReceptionVehicleStructure=null;                    //车身状态
var staticFrtReceptionExpenseSituationOtherExpense=null;        //费用情况
var staticFrtReceptionDisabled=false;                           //判断表单组件禁用或启用	
var operation = false;                                          //编辑状态标志
var successtag = true;                                          //新增预约单、保险估价单转接车单成功标志
/**
 * 设置前台预计完工时间
 * @return
 */
var changeTimeEvent=function(){
	var interDate=$('#addInterDate').datetimebox('getValue');
	$.ajax({
		type : 'post',
		dataType : 'json',
		url : 'frtReceptionAction!getSetFinishTime.action?interDate='+interDate,
		success : function(r) {
			$('#addReceptionEndTime').datetimebox('setValue',r);
			$('#addExpDelCarTime').datetimebox('setValue',r);
		},
		error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   	}
	});
}

/**
 * 新增初始化
 * @return
 */
var add = function() {
	addButton();
	$('#receptionId').val('');
	operation =true;
	$('#frtReceptionAddForm').form('clear');	
	$('#addInterDate').datetimebox('setValue','{now}');
	$('#addReceptor').combobox('select',parame1);
	$('#frtReception_details_finComId').combobox('select','');
	changeTimeEvent();
	findDefaultClaimsCompanyId('frtReception_details_finComId');	
	findDefaultReceptionClass('frtReception_details_reptId');
	findCarLicenseFormat("frtReception_details_carId");
	if(operation){			
		$('#frtReception_vehicleStructure_clear').linkbutton('enable');	
		$('#frtReception_vehicleStructure_clear').linkbutton('enable');	
		$('#vehicleStructureDatagrid').datagrid('loadData', { total: 0, rows: [] });
		$('#frtReceptionPartsDatagrid').datagrid('loadData', { total: 0, rows: [] });
		$('#frtReceptionItemDatagrid').datagrid('loadData', { total: 0, rows: [] });
		$('#frtReceptionExpenseSituationOtherExpense').datagrid('loadData', { total: 0, rows: [] });
		$('#frtReceptionAdviceDatagrid').datagrid('loadData', { total: 0, rows: [] });
	}
}

var addButton = function() {
	if ($('#save').length == 0 && $('#cancel').length == 0) {
		staticFrtReceptionParts=null;
		staticFrtReceptionItems=null;
		staticFrtReceptionVehicleStructure=null;
		staticFrtReceptionExpenseSituationOtherExpense=null;
		var service = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="selectServiceWeave();">维修套餐</a>';
		var edit = '<a id="edit" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateDistance();">更改里程数</a>';
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="_save();">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_cancel(true);">取消</a>';
		$('#button').append(service).append(edit).append(save).append(cancel);
		$.parser.parse('#button');
		$('#add').linkbutton('disable');
		$('#remove').linkbutton('disable');
		$('#edit').linkbutton('disable');
		$('#print').linkbutton('disable');
		$('#set').linkbutton('disable');
		$('#redo').linkbutton('disable');
		$('#redo2').linkbutton('disable');
		requiredAsFormDiy();
	}
	if($('#carLicenseButton').length == 0){
		var carLicenseButton = '<a id="carLicenseButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-choice" plain="true" onclick="selectFrtResevation();"></a>';
		$('#button2').append(carLicenseButton);
		$.parser.parse('#button2');
	}
}
	
//前台接车新增或者修改
var _save = function() {
	if(successtag == true){
		if(staticFrtReceptionVehicleStructure!=null){
			staticFrtReceptionVehicleStructure = prosceniumUpdate('vehicleStructureDatagrid',staticFrtReceptionVehicleStructure);
		}
		if(staticFrtReceptionParts!=null){
			staticFrtReceptionParts = prosceniumUpdate('frtReceptionPartsDatagrid',staticFrtReceptionParts);
		}
		if(staticFrtReceptionItems!=null){
			staticFrtReceptionItems = prosceniumUpdate('frtReceptionItemDatagrid',staticFrtReceptionItems);
		}
		if(staticFrtReceptionExpenseSituationOtherExpense!=null){
			staticFrtReceptionExpenseSituationOtherExpense = prosceniumUpdate('frtReceptionExpenseSituationOtherExpense',staticFrtReceptionExpenseSituationOtherExpense);
		}
		staticResvId="";
		staticRcptCarId="";
		staticRcptCarLicense="";
		var url = 'frtReceptionAction!';
		if($('#receptionId').val()!=null&&$('#receptionId').val()!=''){
			url += 'edit.action';
		}else{
			url += 'save.action';
		}
		var vehicle=null;
		if(staticFrtReceptionVehicleStructure==null){
			vehicle="";
		}else{
			vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
		}
		var items=null;
		if(staticFrtReceptionItems==null){
			items="";
		}else{
			items=JSON.stringify(staticFrtReceptionItems);
		}
		var parts=null;
		if(staticFrtReceptionParts==null){
			parts="";
		}else{
			parts=JSON.stringify(staticFrtReceptionParts);
		}
		var others=null;
		if(staticFrtReceptionExpenseSituationOtherExpense==null){
			others="";
		}else{
			others=JSON.stringify(staticFrtReceptionExpenseSituationOtherExpense);
		}
	  if($('#frtReceptionAddForm').form('validate')){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : url,
				data : $('#frtReceptionAddForm').serialize() +'&'+$('#frtReceptionExpenseSituationOtherExpense').serialize()+ '&vehicle='+vehicle+ '&parts='+parts+ '&items='+items+ '&others='+others,
				success : function(r) {
					if (r.success) {
						_cancel(false);
						window.close();
						$('#receptionTable').datagrid('load');
						//window.opener.win_close();
					}else
						alertMsg(r);
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
	}else{
		alertMsg('对不起，预约单或者保险估价单尚未加载完成！', 'warning');
		return;
	}
}

/**
 * 取消编辑
 * @param tag
 * @return
 */
var _cancel = function(tag) {
	if(tag!=null&&tag==true){
		$('#frtReceptionAddForm').form('clear');
		$('#addInterDate').datetimebox('setValue','{now}');
		$('#addReceptionEndTime').datetimebox('setValue','{now}');
		$('#addExpDelCarTime').datetimebox('setValue','{now}');
		$('#addReceptor').combobox('select',parame1);
		requiredAsForm(false,'frtReceptionAddForm');
		
		$('#frtReceptionExpenseSituationForm').form('load',{
				preclrWktimeAmount:'',
				preMprMatAmount:'',
				preclrManagementFee:'',
				otherAmount:'',
				amountTotal:''
			});
		//清空表格数据
		$('#vehicleStructureDatagrid').datagrid('loadData',{total:0,rows:[]});
		$('#frtReceptionExpenseSituationOtherExpense').datagrid('loadData',{total:0,rows:[]});
		$('#frtReceptionPartsDatagrid').datagrid('loadData',{total:0,rows:[]});
		$('#frtReceptionItemDatagrid').datagrid('loadData',{total:0,rows:[]});
		$('#frtReceptionAdviceDatagrid').datagrid('loadData',{total:0,rows:[]});
		//禁用按钮
		$('#frtReception_vehicleStructure_clear').linkbutton('disable');
		$('#frtReceptionExpenseSituationOtherExpense_add').linkbutton('disable');
		$('#frtReceptionExpenseSituationOtherExpense_remove').linkbutton('disable');
		$('#frtReception_parts_add').linkbutton('disable');
		$('#frtReception_parts_remove').linkbutton('disable');
		$('#frtReception_item_add').linkbutton('disable');
		$('#frtReception_item_remove').linkbutton('disable');
		$('#frtReception_item_diy').linkbutton('disable');
		$('#frtReception_service_add').linkbutton('disable');
		$('#frtReception_service_remove').linkbutton('disable');
		$('#frtReception_service_accept').linkbutton('disable');

		bespeakState('bespeak_off','bespeak_on','frtReception_details_resvId');
		for(var i=1;i<=64;i++){
		 	$('#remark'+i+'').empty();
		 }
	}else{
		$('#frtReception_vehicleStructure_clear').linkbutton('disable');
		$('#frtReceptionExpenseSituationOtherExpense_add').linkbutton('disable');
		$('#frtReceptionExpenseSituationOtherExpense_remove').linkbutton('disable');
		$('#frtReception_parts_add').linkbutton('disable');
		$('#frtReception_parts_remove').linkbutton('disable');
		$('#frtReception_item_add').linkbutton('disable');
		$('#frtReception_item_remove').linkbutton('disable');
		$('#frtReception_item_diy').linkbutton('disable');
		$('#frtReception_service_add').linkbutton('disable');
		$('#frtReception_service_remove').linkbutton('disable');
		$('#frtReception_service_accept').linkbutton('disable');
	}
	$('#button2').empty();
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
	$('#ok').linkbutton('enable');
	$('#ok1').linkbutton('enable');
	$('#redo2').linkbutton('enable');
}
	
//选择维修套餐
var selectServiceWeaveDialog;
var selectServiceWeave = function (){
	selectServiceWeaveDialog = $('<div/>').dialog({
		href : projectPath+'frt/frtReception/details/serviceWeave.jsp',
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
/**
 * 加载维修套餐计划材料 计划项目到接车单
 * @return
 */
var loadWeave=function (){
	var items=null;
	if(staticFrtReceptionItems==null){
		items="";
	}else{
		items=JSON.stringify(staticFrtReceptionItems);
	}
	var parts=null;
	if(staticFrtReceptionParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtReceptionParts);
	}
	var rowData = $('#selectServiceWeaveDatagrid').datagrid('getSelected');
    $.ajax({
		type : 'post',
		dataType : 'json',
		url : 'frtReceptionAction!findPartsListByRpId.action',
		data:'rpId='+rowData.rpId+'&parts='+parts,
		success : function(r) {
			$('#frtReceptionPartsDatagrid').datagrid('loadData', r);
			var data = $('#frtReceptionPartsDatagrid').datagrid('getData');
			staticFrtReceptionParts=data;
		}
	}).error(function(r) {
		alertMsg(r);
	});
	$.ajax({
	   type: 'post',
	   dataType: 'json',
	   url: 'frtReceptionAction!findItemListByRpId.action',
	   data:'rpId='+rowData.rpId+'&items='+items,
	   success: function(r){
   			$('#frtReceptionItemDatagrid').datagrid('loadData', r);
			var data = $('#frtReceptionItemDatagrid').datagrid('getData');
			staticFrtReceptionItems=data;
	   }
	}).error(function (r){
		alertMsg(r);
	});
	selectServiceWeaveDialog.dialog('close');
}

function requiredAsFormDiy(){
	$("#frtReceptionAddForm input").prop("disabled",false);
	$("#frtReceptionAddForm input[id=frtReception_details_customId]").prop("disabled",true);
	$("#frtReception_details_carId").combobox({required:true,disabled:false});
	$("#frtReception_details_carVin").combobox({required:true,disabled:false});
	$("#frtReception_details_customId").combobox({required:true,disabled:true});
	$("#frtReception_details_receptionMaintFlg").combobox({required:true,disabled:false});
	$("#frtReception_details_reptId").combobox({required:true,disabled:false});
	$("#frtReception_details_receptionInsurPer").combobox({required:false,disabled:false});
	$("#frtReception_details_receptionTechnician").combobox({required:false,disabled:false});
	$("#frtReception_details_finComId").combobox({required:true,disabled:false});
	$("#frtReception_details_valuables").combobox({required:true,disabled:false});
	$("#frtReception_details_repgrpId").combobox({required:false,disabled:false});
	$("#frtReception_details_repwkId").combobox({required:false,disabled:false});
	$("#addReceptor").combobox({required:true,disabled:true});
	$("#frtReception_details_fuelSituation").combobox({required:true,disabled:false});
	$("#frtReception_details_oldPieces").combobox({required:true,disabled:false});
	$("#frtReceptionAddForm textarea").prop("disabled",false);
	
	$("#addInterDate").datetimebox({required:true,disabled:true});
	$("#addReceptionEndTime").datetimebox({required:true,disabled:false});
	$("#addExpDelCarTime").datetimebox({required:true,disabled:false});
	
	$("#frtReception_details_resvId").validatebox({required:false});
	$("#frtReception_details_receptionRepPer").validatebox({required:false});
	$("#frtReception_details_propRepPer").validatebox({required:false});
	$("#frtReception_details_propPhone").validatebox({required:false});
	$("#frtReception_details_propTel").validatebox({required:false});
	
	$("#frtReception_details_receptionDistance").numberbox({required:true,disabled:false});
	$("#frtReception_details_receptionDistance").numberbox('validate');
	$("#frtReceptionAddForm input.easyui-numberbox").numberbox({required:true});
	$("#frtReceptionAddForm input.easyui-numberbox").numberbox('setValue','');
	$("#frtReceptionAddForm input.easyui-numberbox").numberbox("validate");
	
	$("#frtReception_details_resvId").validatebox('validate');
	$("#frtReception_details_receptionRepPer").validatebox('validate');
	$("#frtReception_details_propRepPer").validatebox('validate');
	$("#frtReception_details_propPhone").validatebox('validate');
	$("#frtReception_details_propTel").validatebox('validate');
	
	var frtReception_details_customId=document.getElementById('frtReception_details_customId');
	if(frtReception_details_customId!=null){
		frtReception_details_customId.style.background="#EBEBE4";
	}
}

function bespeakState (id1,id2,id3){
	var id=document.getElementById(id3);
	if(id==null){
		return;
	} 
	var value=id.value;
	if(value!=null&&value.length>0){
		document.getElementById(id1).style.display="none";
		document.getElementById(id2).style.display="block";
	}else{
		document.getElementById(id2).style.display="none";
		document.getElementById(id1).style.display="block";
	}
}

/**
 * 导出
 * 
 */
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"前台接车单"+getSystemTime());
}

function updateDistance(){
	var value=$('#frtReception_details_carId').combobox('getValue');
	if(value==""||value.length==0){
		alertMsg('对不起，请先选择车牌照！', 'warning');
		return;
	}
	$('#aa').dialog({
		buttons:[{
			text:'Ok',
			iconCls:'icon-ok',
			handler:function(){
				if($('#frtReceptionDistanceForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'frtReceptionAction!modifyDistance.action?carId='+value,
					   data: $('#frtReceptionDistanceForm').serialize(),
					   success: function(r){
						 if(r.success){
						 	$('#aa').dialog('close');
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
				$('#aa').dialog('close');
			}
		}]
	});
}
	
//转结算操作
function transFormBalanace(i){
	if(receptionId){
		$.messager.confirm('系统提示', '您确定要进行转结算操作?', function(r){
			if (r){
				$.ajax({
					type : 'post',
					url : 'frtReceptionAction!transFormBalanace.action?receptionStatus='+i,
					data : 'receptionId=' +receptionId,
					dataType : 'json',
					success : function callback(r) {
						if (r.success) {
							alertMsg(r);
							_cancel(true);
							//$('#frtReceptionSummaryDatagrid').datagrid('load');
						}else{
							alertMsg(r);
						}
					}
				});
			}
		});
	}else{
		alertMsg('对不起，请先选中要转换的记录！', 'warning');
	}
}

/**
 * 编辑初始化
 * @return
 */
var edit = function (){
	staticResvId="";
	staticRcptCarId="";
	staticRcptCarLicense="";
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!datagridReception.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
				if(r.rows[0].finStatus==parame2){
					alertMsg('对不起，此单已转车间，不能修改！', 'warning');
					return ;
				}
				addButton();
				$('#frtReceptionAddForm').form('load', r.rows[0]);
				$('#frtReceptionExpenseSituationForm').form('load',r.rows[0]);
				$('#vehicleStructureDatagrid').datagrid({
					url : 'frtReceptionAction!findvehicleStructureList.action?receptionId=' + r.rows[0].receptionId
				});
				$('#frtReceptionPartsDatagrid').datagrid({
					url : 'frtReceptionAction!findPartsByRcptId.action?receptionId=' + r.rows[0].receptionId
				});
				$('#frtReceptionItemDatagrid').datagrid({
					url : 'frtReceptionAction!findItemByRcptId.action?receptionId=' + r.rows[0].receptionId
				});
				$('#frtReceptionExpenseSituationOtherExpense').datagrid({
					url : 'frtReceptionAction!findCostByRcptId.action?receptionId=' + r.rows[0].receptionId
				});
				$('#frtReceptionAdviceDatagrid').datagrid({
					url : 'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' +r.rows[0].carId
				});
				operation = true;
				bespeakState('bespeak_off','bespeak_on','frtReception_details_resvId');
				$('#frtWorkOrderItemDatagrid').treegrid({
					url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+r.rows[0].carId
				});
				
		   }else
			   alertMsg('对不起，请先选中要修改的记录！', 'warning');
	    }
	});

}
	
//转车间
function transFormPlant(){
    $.ajax({
		type : 'post',
		url : 'frtReceptionAction!datagridReception.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			    if(r.rows[0].finStatus==parame2){
					alertMsg('此单以转车间，无需再次操作！', 'warning');
					return;
				}
				$.messager.confirm('系统提示', '您确定要进行转车间操作?', function(t){
					if (t){
						$.ajax({
							type : 'post',
							url : 'frtReceptionAction!transFormPlant.action',
							data : 'receptionId=' +r.rows[0].receptionId,
							dataType : 'json',
							success : function callback(r) {
								if (r.success) {
									alertMsg(r);
									_cancel(true);
									window.opener._query();
								}else{
									alertMsg(r);
								}
							}
						});
					 }
				 });
		   }else
			   alertMsg('对不起，请先选中要转换的记录！', 'warning');
	    }
	});
}

//增加车身结构已选图标main
var loadIcons = function(){
	var vehicle=null;
	if(staticFrtReceptionVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
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
	if(staticFrtReceptionVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
	}
	$.ajax({
		type : 'post',
		url : 'frtResevationAction!removeVehicleStructure.action',
		data : 'stateId=' +i+ '&vehicle='+vehicle,
		dataType : 'json',
		success : function callback(r) {
			$('#vehicleStructureDatagrid').datagrid('loadData', r);
			var data = $('#vehicleStructureDatagrid').datagrid('getData');
			staticFrtReceptionVehicleStructure=data;
		}
	});
}

function showLastService(id){
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!findLastService.action',
		dataType : 'json',
		data:'carId='+id,
		success : function(r) {
			if(r.success){
				$.messager.show({
					width:500,
					height:400,
					title:'接车提醒信息',
					msg:r.obj,
					timeout:0,
					showType:'fade'
				});		
			}
		}
	});
}
	
function bespeakClew(id){
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!bespeakClew.action',
		dataType : 'json',
		data:'carId='+id,
		success : function(r) {
			if(r.success){
				$.messager.confirm('预约信息提示', '所选车辆含有预约信息'+r.obj+'，是否查看详情?', function(t){
					if (t){
						selectFrt(id);
					}
				});	
			}
		}
	});
}
	
var selectFrtResevationDialog;

/**
 * 选择预约/保险估价单
 * @return
 */
var selectFrtResevation = function (){
	selectFrt("");
}

var selectFrt=function(id){
	var url=projectPath+'frt/frtReception/details/selectFrtResevation.jsp?carId='+id;
	selectFrtResevationDialog = $('<div/>').dialog({
		href : url,
		modal:true,
		title : '选择预约/保险估价单',
		closable : true,
		width : 860,
		height : 460,
		buttons : [{
			 text : '确定',
			 iconCls : 'icon-save',
			 handler : function (){
			     loadFrtResevation(); 
			 }
       	},{
			 text : '取消',
			 iconCls : 'icon-undo',
			 handler : function (){
				 selectFrtResevationDialog.dialog('close');
			 }
       }],
       onClose : function (){
	    	$(this).dialog('destroy');
       }
	});
}

/**
 * //提取预约/保险估价单信息
 * @return
 */
var loadFrtResevation=function(){
		var rowData = $('#selectFrtResevationDatagrid').datagrid('getSelected');
   	   	selectFrtResevationDialog.dialog('close');
		$('#frtReceptionAddForm').form('load', rowData);
		staticResvId=rowData.resvId;
		staticRcptCarId=rowData.carId;
		staticRcptCarLicense=rowData.carLicense;
		$('#frtReceptionAddForm input[name=carVin]').val(rowData.carVin);
		$('#frtReceptionAddForm input[name=receptionDistance]').val(rowData.resvDistance);
		$('#frtReceptionAddForm input[name=propRepPer]').val(rowData.resvFixpel);
		$('#frtReceptionAddForm input[name=propTel]').val(rowData.resvFixtel);
		$('#frtReceptionAddForm input[name=propPhone]').val(rowData.resvFixphone);
        
		loadDatagrid(rowData);
		
		bespeakState('bespeak_off','bespeak_on','frtReception_details_resvId');
		$('#frtWorkOrderItemDatagrid').treegrid({
			url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+rowData.carId
		});
}

/**
 * 加载计划材料与计划项目 
 * @param rowData
 * @return
 */
function loadDatagrid(rowData){
	$('#vehicleStructureDatagrid').datagrid({
		url : 'frtReceptionAction!findvehicleStructureListByResvId.action?resvId='+rowData.resvId,
		onLoadSuccess : function (data){
		    successtag = successtag && true;
			staticFrtReceptionVehicleStructure=data;
			$('#frtReception_vehicleStructure_clear').linkbutton('enable');
			loadIcons();
		},
		onLoadError : function (){
			successtag = successtag && false;
		}
	});
	
	$('#frtReceptionPartsDatagrid').datagrid({
		url : 'frtReceptionAction!findPartsByResvId.action?resvId=' + rowData.resvId,
		onLoadSuccess : function (data){
		    successtag = successtag && true;
			staticFrtReceptionParts=data;
			$('#frtReception_parts_add').linkbutton('enable');
			$('#frtReception_parts_remove').linkbutton('enable');
		},
		onLoadError : function (){
			successtag = successtag && false;
		}
	});
	
	$('#frtReceptionItemDatagrid').datagrid({
		url : 'frtReceptionAction!findItemByResvId.action?resvId=' + rowData.resvId,
		onLoadSuccess : function (data){
		    successtag = successtag && true;
			staticFrtReceptionItems=data;
			$('#frtReception_item_add').linkbutton('enable');
			$('#frtReception_item_remove').linkbutton('enable');
			$('#frtReception_item_diy').linkbutton('enable');
		},
		onLoadError : function (){
			successtag = successtag && false;
		}
	});
	
	$('#frtReceptionAdviceDatagrid').datagrid({
		url : 'frtReceptionAction!findFrtResvAdviceByCarId.action?carId=' + rowData.carId,
		onLoadSuccess : function (data){
		    successtag = successtag && true;
			$('#frtReception_service_add').linkbutton('enable');
			$('#frtReception_service_remove').linkbutton('enable');
		},
		onLoadError : function (){
			successtag = successtag && false;
		}
	});
}
var frtReception_details_carId=null;
/**
 * 如果车辆档案不存在新增
 * @return
 */	
function addCarArchives()
{
	$('#frtReception_details_carId').combobox('textbox').on('keyup', function(e)
    {
       if (e.keyCode == 13)
       {
    	   carLic();
       }
    });
     carLic = function()
     {
     	 var val1 = $('#frtReception_details_carId').combobox('getValue');
         var val2 = $('#frtReception_details_carId').combobox('getText');
         if(val1 == '' || val1 == val2){
  		   $.messager.confirm('系统提示', '您输入的车牌照不存在,是否进行车档案登记?', function(r){
      			if (r){
      				var d = $('<div/>').dialog({
      					href : "frt/frtResevation/details/addCarArchives.jsp?carLicense="+val2,
      					modal:true,
      					closable : false,
      					title : '新增车辆档案',
      					width : 800,
      					height : 430,
      					buttons : [{
      						text : '确定',
      						handler : function (){
      							if($('#carArchivesAddForm').form('validate')){
      								$.ajax({
      									   type: 'post',
      									   dataType: 'json',
      									   url: 'basCarArchivesAction!save.action',
      									   data: $('#carArchivesAddForm').serialize(),
      									   success: function(r){
      										   if(r.success){
      										   	 $('#frtReception_details_carId').combobox('reload','frtOptionsAction!findAllCarLicense.action');
          										   $('#frtReception_details_carId').combobox('select', r.obj);
          										   $('#frtReception_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
        							     		   $('#frtReception_details_customId').combobox('select', $('#carArchives_add_customId_view').val());
          										   $('#frtReception_details_carVin').combobox('reload','frtOptionsAction!findAllCarVin.action');
          										   $('#frtReception_details_carVin').combobox('select',$('#addcarVin').val());
          										   document.getElementById('frtReception_details_receptionRemark').innerHTML=$('#carArchives_add_receptionRemark').val();
      											    d.dialog('close');
      											 	alertMsg(r);
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
      				    	$('#frtResevation_details_carLicense').val($('#carLicenseview').val());
      			        	$('#carLicenseview').val(val2);
      			        },
      					onClose : function (){
      				    	d.dialog('destroy');
      				    }
      				});
      			}
      	   });
         }else{
      	  		$.post('frtResevationAction!getFrtCar.action', 
              	   { carId : val1 }, 
              	   function (r){
							$('#frtReception_details_customId').combobox('setValue', r.customId);
							$('#frtReception_details_carVin').combobox('setValue',r.carVin);
							$('input[name=propRepPer]').val(r.carRelationPerson);
							$('input[name=propTel]').val(r.carRelationTel1);
							$('input[name=propPhone]').val(r.carRelationTel2);
							document.getElementById('frtReception_details_receptionRemark').innerHTML=r.receptionRemark;
	                   },
	                   'json'
	           );
	         $frtReceptionServiceItemDatagrid.datagrid({
				url : 'frtReceptionAction!serviceRecord.action?carId='+val1
			 });
          }
     }
}
/**
 * 客户档案不存在新增
 * @return
 */
function addCustomArchives(){
	$('#frtReception_details_customId').combobox('textbox').on('keyup', function(e)
    {
          if (e.keyCode == 13)
          {
           var val1 = $('#frtReception_details_customId').combobox('getValue');
           var val2 = $('#frtReception_details_customId').combobox('getText');
           if(val1 == '' || val1 == val2){
       		   $.messager.confirm('系统提示', '您输入的客户名称不存在,是否新建客户档案?', function(r){
        			if (r){
        				var d = $('<div/>').dialog({
        					title: '新增客户档案',   
        					width : 800,
        					height : 380,
        				    cache: false,   
        				    href: "frt/frtResevation/details/addCustomArchives.jsp?customId="+val2,   
        				    modal: true,
        				    onClose : function (){
        				    	$(this).dialog('destroy');
        				    },
        				    buttons:[{
        						text:'新增',
        						iconCls:'icon-add',
        						handler:function(){
        							$.ajax({
        							   type: 'post',
        							   dataType: 'json',
        							   url: 'frtCustomAction!saveCustom.action',
        							   data: $('#customArchivesAddForm').serialize()+"&carId="+$('#frtReception_details_carId').combobox('getValue'),
        							   success: function(r){
        							     	if(r.success){
        							     	    $('#frtReception_details_customId').combobox('reload','frtOptionsAction!findAllCustom.action');
        							     		$('#frtReception_details_customId').combobox('select', r.obj);
        							     		d.dialog('close');
        							     	}else{
        							     		alertMsg(r);
        							     	}
        							   }
        							});
        						}
        				    },{
        						text:'取消',
        						iconCls:'icon-cancel',
        						handler:function(){
        							d.dialog('close');
        						}
        				    }],
        				    onLoad : function (){
        			        	$('#customArchives_add_customName').val(val2);
        			        	$('#customArchives_add_customJm').val(makePy(val2));
        			        }
        				});
        			}
        	   });
           }else{
           	$.post('frtResevationAction!findAllCustom.action', 
              	   { customId : val1 }, 
              	   function (r){
							$('#frtResevation_details_customId').combobox('setValue', r.customId);
	                   },
	                   'json'
	           );
           }
          }
    });
}
/**
 * 车架号加载车辆档案
 * @return
 */
function findFrtCarByCarVin(){
	//显示VIN号关联信息
	$('#frtReception_details_carVin').combobox('textbox').on('keyup', function(e)
    {
          if (e.keyCode == 13)
          {
               var val1 = $('#frtReception_details_carVin').combobox('getText');
              	$.post('basCarArchivesAction!findFrtCarByVIN.action', 
                 	   { carVin : val1 }, 
                 	   function (r){
                 	   		$('#frtReception_details_carId').combobox('select', r.carId);
							$('#frtReception_details_customId').combobox('select', r.frtCustom.customId);
							$('input[name=carVin]').val(r.carVin);
							$('input[name=propRepPer]').val(r.carRelationPerson);
							$('input[name=propTel]').val(r.carRelationTel1);
							$('input[name=propPhone]').val(r.carRelationTel2);
							document.getElementById('frtReception_details_receptionRemark').innerHTML=r.receptionRemark;
	                   },
	                   'json'
	           );
          }
    });
}

function frtReceptionToteMoney(){
	if(staticFrtReceptionVehicleStructure!=null){
		staticFrtReceptionVehicleStructure = prosceniumUpdate('vehicleStructureDatagrid',staticFrtReceptionVehicleStructure);
	}
	if(staticFrtReceptionParts!=null){
		staticFrtReceptionParts = prosceniumUpdate('frtReceptionPartsDatagrid',staticFrtReceptionParts);
	}
	if(staticFrtReceptionItems!=null){
		staticFrtReceptionItems = prosceniumUpdate('frtReceptionItemDatagrid',staticFrtReceptionItems);
	}
	if(staticFrtReceptionExpenseSituationOtherExpense!=null){
		staticFrtReceptionExpenseSituationOtherExpense = prosceniumUpdate('frtReceptionExpenseSituationOtherExpense',staticFrtReceptionExpenseSituationOtherExpense);
	}
	var vehicle=null;
	if(staticFrtReceptionVehicleStructure==null){
		vehicle="";
	}else{
		vehicle=JSON.stringify(staticFrtReceptionVehicleStructure);
	}
	var items=null;
	if(staticFrtReceptionItems==null){
		items="";
	}else{
		items=JSON.stringify(staticFrtReceptionItems);
	}
	var parts=null;
	if(staticFrtReceptionParts==null){
		parts="";
	}else{
		parts=JSON.stringify(staticFrtReceptionParts);
	}
	var others=null;
	if(staticFrtReceptionExpenseSituationOtherExpense==null){
		others="";
	}else{
		others=JSON.stringify(staticFrtReceptionExpenseSituationOtherExpense);
	}
	/*计算收费额*/
	$.ajax({
			type : 'post',
			url : 'frtReceptionAction!totemoney.action',
			dataType : 'json',
			data:'&parts='+parts+ '&items='+items+ '&others='+others,
			success : function(r) {
				$('#frtReceptionPreclrWktimeAmount').val(r[0]);
				$('#frtReceptionPreMprMatAmount').val(r[1]);
				$('#frtReceptionPreclrManagementFee').val(r[2]);
				$('#frtReceptionOtherAmount').val(r[3]);
				$('#frtReceptionAmountTotal').val(r[4]);
			}
		});
}

var _remove = function (){
	$.ajax({
		type : 'post',
		url : 'frtReceptionAction!datagridReception.action',
		dataType : 'json',
		data:'receptionId='+receptionId,
		success : function(r) {
		   if(r.total>0){
			   if(r.rows[0].finStatus==parame2){
					alertMsg('对不起，此单已转车间，不能删除！', 'warning');
					return ;
				}
				$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(t){
					if (t){
						$.ajax({
							type : 'post',
							dataType : 'json',
							url : 'frtReceptionAction!remove.action',
							data : r.rows[0],
							success : function(r) {
								if (r.success) {
									alertMsg(r);
									_cancel(true);
									$('#receptionTable').datagrid('reload');
									window.opener.win_close();
								    //setSelectRow('frtReceptionSummaryDatagrid',index);
								}else
									alertMsg(r);
							}
						});
					}
				});
		   }else
			   alertMsg('对不起，请先选中要删除的记录！', 'warning');
	      }
	   });
}