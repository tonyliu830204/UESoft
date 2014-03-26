$(function(){
//会员卡储值
	$('#datagrid_vip_chongzhi_id').datagrid({
		url : projectPath+'VipRechargeAction!findAll.action',
		fit : true,
		fitColumns : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Rec_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'vip_Rec_Id',
			title : '储值单号',
			hidden : true
		}, {
			field : 'vip_Id',
			title : '会员id',
			hidden : true
		}, {
			field : 'car_License',
			title : '车辆牌照',
			width : 70,
			sortable : true
		}, {
			field : 'car_Vin',
			title : 'VIN号',
			width : 130,
			sortable : true
		}, {
			field : 'vip_Number',
			title : '会员卡号',
			width : 100,
			sortable : true
		}, {
			field : 'vip_Name',
			title : '会员名称',
			width : 100,
			sortable : true
		}, {
			field : 'vip_Birthday',
			title : '会员生日',
			width : 130,
			sortable : true
		}, {
			field : 'vip_Tel',
			title : '联系电话',
			width : 90,
			sortable : true
		}, {
			field : 'vip_Level_Name',
			title : '会员等级',
			width : 80,
			sortable : true
			
		}, {
			field : 'vip_Group_Name',
			title : '会员分组',
			width : 80,
			sortable : true
		
		}, {
			field : 'vip_Status',
			title : '会员卡状态key',
			hidden : true
		}, {
			field : 'vip_Status_value',
			title : '会员卡状态',
			width : 85,
			sortable : true
		}, {
			field : 'join_Time',
			title : '入会日期',
			width : 130,
			sortable : true
		}, {
			field : 'end_Time',
			title : '会员到期',
			width : 130,
			sortable : true
		}, {
			field : 'rec_Amount',
			title : '储值金额',
			width : 80,
			sortable : true
		}, {
			field : 'vip_Rec_Date',
			title : '储值日期',
			width : 130,
			sortable : true
		}, {
			field : 'give_Amount',
			title : '赠送金额',
			width : 80,
			sortable : true
		}, {
			field : 'give_Inte',
			title : '赠送积分',
			width : 80,
			sortable : true
		}, {
			field : 'operator',
			title : '经办人id',
			hidden : true
		}, {
			field : 'stf_Name',
			title : '经办人',
			width : 80,
			sortable : true
		}, {
			field : 'vip_Integral',
			title : '可以用积分',
			width : 80,
			sortable : true
		}, {
			field : 'vip_Balance',
			title : '会员卡余额',
			width : 80,
			sortable : true
		}, {
			field : 'vip_Total_Integral',
			title : '累计积分',
			width : 80,
			sortable : true
		}, {
			field : 'rec_PayType',
			title : '付款方式key',
			hidden : true
		}, {
			field : 'payMent_Name',
			title : '付款方式',
			width : 80,
			sortable : true
		}, {
			field : 'rec_Audit_Status',
			title : '审核状态key',
			hidden : true
		}, {
			field : 'rec_Audit_StatusValue',
			title : '审核状态',
			width : 85,
			sortable : true
		}, {
			field : 'rec_Audit_Date',
			title : '审核日期',
			width : 130,
			sortable : true
		}, {
			field : 'rec_Audit_Oper',
			title : '审核经办id',
			hidden : true
		}, {
			field : 'rec_Audit_OperValue',
			title : '审核经办',
			width : 85,
			sortable : true
		}
		]]
	});
});
var strType = null;   //操作类型

var showDialog_ = function(rowDate){
	var d = $('<div/>').dialog({
		href : projectPath+"vip_management/vip_chongzhi_dialog.jsp",
		modal:true,
		closable : false,
		title : '会员卡储值',
		width : 600,
		height : 350,
		buttons : [{
				text : '确定',
				handler : function (){
				   doSubmit();
				   d.dialog('close');
				}
			},{
				text : '取消',
				handler : function (){
					d.dialog('close');
				}
			}
		],
	    onLoad : function (){
			if(rowDate != null){
				var formId = $('#form_Vip_management_member_part');
				$("#vip_Number").attr("readonly","readonly");
		  		$("#vip_Number").val(rowDate.vip_Number);
		  		getVipInfoByVipNO1(formId,rowDate.vip_Id, rowDate.vip_Rec_Id);
		  		var giveInte = rowDate.give_Inte;         //获取赠送积分
				var giveAmount = rowDate.give_Amount;     //获取赠送金额
				var recAmount = rowDate.rec_Amount;       //获取充值金额
				var vipBalance = rowDate.vip_Balance;     //获取会员余额
				var vipIntegral = rowDate.vip_Integral;   //获取会员可用积分
				var totalInte = rowDate.vip_Total_Integral; //累计积分
				$('#vip_Balance').val(vipBalance - recAmount - giveAmount);  //计算充值之前会员 余额
				$('#vip_Integral').val(vipIntegral - giveInte);              //计算充值之前会员可用积分
				$('#vip_Total_Integral').val(totalInte - giveInte);          //计算累计积分
			}
        },
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}   

//提交操作
function doSubmit(d){
	if(strType == "add"){
		url1 = projectPath+"VipRechargeAction!recharge.action";
	}
	if(strType == "edit"){
		url1 = projectPath+"VipRechargeAction!update.action";	
	}
	var formId = $('#form_Vip_management_member_part');
	var bool = formId.form('validate');
	if(bool){
		var form =  formId.form();
		var formvalue = serializeObject(form);
		if(formvalue.rec_Amount > 0){
			$.ajax({
				type : 'POST',
				url : url1,
				data : formvalue,
				dataType : 'json',
				success : function(r){
				    alertMsg(r.msg, 'info');
					if(r.success){
						doConditionSubmit();
					}
				}
			});
		}else{
			alertMsg('对不起，充值金额不能小于等于0', 'warning');
		}
	 }else{
	 	alertMsg('对不起，保存失败，请确认数据格式及内容是否正确！', 'warning');
	 }
}

function showdialog(){
	strType = "add";  //设置操作类型为新增
	unDisFrom();
	$("#vip_Number").removeAttr("readonly");
	showDialog_(null);
}

//点击修改按钮后
function doUpdate(){
	var datagridId = $('#datagrid_vip_chongzhi_id');
	strType = "edit";   //设置操作类型为编辑
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
  		if(values[0].rec_Audit_Status == sate){
  			alertMsg('对不起，该储值单已经被审核，不允许修改！', 'warning');
  			return;
	  	}else{
  		    showDialog_(values[0]);
	  	}
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
		return;
	}
}

//将form表单序列化为对象
function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
}
//点击读卡读取会员信息
function getVipInfoByVipNO(formId,url){
	var form =  formId.form();
	var formvalue = serializeObject(form);
	$.ajax({
		type : 'POST',
		url : url,
		data : formvalue,
	    dataType : 'json',
		success : function(data){
		    formId.form('clear');
		    if(data.success){
				formId.form('load', data.obj[0]);
				$("#rec_Amount").val(0);                                     //给赠送积分赋值
				$("#give_Inte").val(0);                                      //给赠送积分赋值
				$("#give_Amount").val(0);                                    //给赠送金额赋值
		    }else{
		    	alertMsg(data.msg, 'warning');
		    }
		}
	});
}

function getVipInfoByVipNO1(formId,vip_Id, vip_Rec_Id){
	var form =  formId.form();
	var formvalue = serializeObject(form);
	$.ajax({
		type : 'POST',
		url : projectPath+'VipRechargeAction!readCard.action',
		data : formvalue,
	    dataType : 'json',
		success : function(data){
		    if(data.success){
			    formId.form('clear');
				formId.form('load', data.obj[0]);
				$("#rec_Amount").val(0);                                     //给赠送积分赋值
				$("#give_Inte").val(0);                                      //给赠送积分赋值
				$("#give_Amount").val(0);                                    //给赠送金额赋值
				$.ajax({
					type : 'POST',
					url : projectPath+'VipRechargeAction!readVipRedInfo.action',
					data : "vip_Id="+vip_Id+"&vip_Rec_Id="+vip_Rec_Id,
				    dataType : 'json',
					success : function(data){
					    $("#rec_Amount").val("");                                   //给赠送金额赋值
				        $("#give_Amount").val("");                                   //给赠送金额赋值
				        $("#give_Inte").val("");                                   //给赠送金额赋值
				        $('#vip_chongzhi_paytype').combobox('setValue',"");      //给赠送金额赋值
				        $("#vip_Rec_Id").val("");
				    
					    $("#rec_Amount").val(data.rec_Amount);                                   //给赠送金额赋值
					    $("#give_Amount").val(data.give_Amount);                                   //给赠送金额赋值
					    $("#give_Inte").val(data.give_Inte);                                   //给赠送金额赋值
					    $('#vip_chongzhi_paytype').combobox('setValue',data.rec_PayType);      //给赠送金额赋值
					    $("#vip_Rec_Id").val(data.vip_Rec_Id);
					}
				});
		    }else{
		    	alertMsg(data.msg, 'warning');
		    }
		}
	});
}

function unDisFrom(){
	$("#form_Vip_management_member_part").find('input').val(''); 
	$("#form_Vip_management_member_part").find('select').val('');
	$("#form_Vip_management_member_part").find('textarea').val('');
}

//删除方法
function doDelete(){
	var datagridId = $('#datagrid_vip_chongzhi_id');
	var delrow = datagridId.datagrid('getSelections');
	if(delrow.length>0){
		if(delrow[0].rec_Audit_Status == sate){
			alertMsg('对不起，该储值单已经被审核，不允许删除！', 'warning');
  			return;
	  	}else{
  		    $.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
  		    	if(b){
  		    		$.ajax({
  		    			type : 'POST',
  		    			url : projectPath+'VipRechargeAction!delete.action',
  		    			data : delrow[0],
  		    			dataType : 'json',
  		    			success : function(r){
	  		    			alertMsg(r.msg, 'info');
	  		    			if(r.success){
	  		    				doConditionSubmit();
	  		    			}
	  		    		}
  		    		});
  		    	}
  		    });
	  	}
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
		return;
	}
}

//审核
function audit(datagridId){
	var datagridId = $('#datagrid_vip_chongzhi_id');
	var audRow = datagridId.datagrid('getSelections');
	if(audRow.length>0){
		if(audRow[0].rec_Audit_Status == sate){
			alertMsg('对不起，该储值单已经被审核，不允许再操作！', 'warning');
  			return;
	  	}else{
			$.ajax({
				type : 'POST',
				url : projectPath+"VipRechargeAction!auditRecharge.action",
				data : audRow[0],
				dataType : 'json',
				success : function(r){
				    alertMsg(r.msg, 'info');
					if(r.success){
						doConditionSubmit();
					}
				}
	  		});
	  	}
	}else{
  		alertMsg('对不起，请先选中要操作的记录！','warning');
  		return;
  	}
}

//条件查询提交
function doConditionSubmit(){
	var form =  $('#form_query_vip_chongzhi_id').form();
	var formvalue = serializeObject(form);
	$('#datagrid_vip_chongzhi_id').datagrid('load',formvalue);
	datagridId.datagrid('clearSelections');
}

//清空查询条件
function doEmpty(){
	var form =  $('#form_query_vip_chongzhi_id');
	form.form('clear');
	var formvalue = serializeObject(form);
	$('#datagrid_vip_chongzhi_id').datagrid('load',formvalue);
}

function _exception(){
	showEditDialog("datagrid_vip_chongzhi_id",null,"datagrid_vip_chongzhi_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"会员储值"+getSystemTime());
}