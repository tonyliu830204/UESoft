$(function(){
	loadTable()
});	

//会员档案管理汇总   
function loadTable(){
	$('#vip_management_center_id').datagrid({
		url : projectPath+'VipRecordMessageAction!doFindAll.action',
		fit : true,
		fitColumns : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 20,
		pageList : [ 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'car_Id',
		sortName:'car_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'car_Id',
			title : '车辆编号',
			hidden : true
		},{
			field : 'vip_Id',
			title : '会员编号',
			hidden : true
		},{
			field : 'car_License',
			title : '车辆牌照',
			width : 70,
			sortable : true
		},{
			field : 'car_Vin',
			title : '车架号',
			width : 130,
			sortable : true
		},{
			field : 'cbrd_Name',
			title : '车辆品牌',
			width : 80,
			sortable : true
		},{
			field : 'ctype_Name',
			title : '车辆型号',
			width : 80,
			sortable : true
		},{
			field : 'cbrl_Name',
			title : '车辆款式',
			width : 80,
			sortable : true
		},{
			field : 'color_Name',
			title : '车身颜色',
			width : 65,
			sortable : true
		},{
			field : 'enterprise_name3',
			title : '车辆档案经办企业名称',
			width : 65,
			sortable : true
		},{
			field : 'custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
		},{
			field : 'custom_Sex',
			title : '性别',
			width : 40,
			sortable : true
		},{
			field : 'custom_Birthday',
			title : '出生年月',
			width : 130,
			sortable : true
		},{
			field : 'custom_Tel1',
			title : '联系电话',
			width : 90,
			sortable : true
		},{
			field : 'custom_Tel2',
			title : '固定电话',
			width : 90,
			sortable : true
		},{
			field : 'parea_Name',
			title : '所在区域',
			width : 70,
			sortable : true
		},{
			field : 'custom_Addr',
			title : '地址',
			width : 110,
			sortable : true
		},{
			field : 'vip_Number',
			title : '会员卡号',
			width : 100,
			sortable : true
		},{
			field : 'vip_Name',
			title : '会员名称',
			width : 100,
			sortable : true
		},{
			field : 'vip_Birthday',
			title : '会员生日',
			width : 130,
			sortable : true
		},{
			field : 'vip_Tel',
			title : '会员电话',
			width : 100,
			sortable : true
		},{
			field : 'vip_Level_Id',
			title : '会员等级编号',
			hidden : true
		},{
			field : 'vip_Level_Name',
			title : '会员等级',
			width : 80,
			sortable : true
		},{
			field : 'vip_Group_Id',
			title : '会员分组编号',
			hidden : true
		},{
			field : 'vip_Group_Name',
			title : '会员分组',
			width : 80,
			sortable : true
		},{
			field : 'vip_Status',
			title : '会员卡状态key',
			hidden : true
		},{
			field : 'vip_Status_value',
			title : '会员卡状态',
			width : 80,
			sortable : true
		},{
			field : 'join_Time',
			title : '入会日期',
			width : 130,
			sortable : true
		},{
			field : 'end_Time',
			title : '会员到期',
			width : 130,
			sortable : true
		},{
			field : 'vip_Age',
			title : '会龄/月',
			width : 60,
			sortable : true
		}, {
			field : 'vip_Balance',
			title : '卡内余额',
			width : 80,
			sortable : true
		},{
			field : 'vip_Integral',
			title : '可用积分',
			width : 80,
			sortable : true
		},{
			field : 'vip_Total_Integral',
			title : '累计积分',
			width : 80,
			sortable : true
		},{
			field : 'vip_Hobby',
			title : '会员爱好',
			width : 100,
			sortable : true
		},{
			field : 'custom_id',
			title : '客户id',
			hidden : true
		},{
			field : 'car_Motor_Id',
			title : '发动机号',
			hidden : true
		},{
			field : 'car_Buy_Date',
			title : '购车日期',
			hidden : true
		},{
			field : 'car_License_Date',
			title : '领证日期',
			hidden : true
		},{
			field : 'car_Basinsurance_Date',
			title : '交强险到期',
			hidden : true
		},{
			field : 'car_Businsurance_Date',
			title : '商业险到期',
			hidden : true
		},{
			field : 'car_Last_Repair_Date',
			title : '上次维修日期',
			hidden : true
		},{
			field : 'car_Last_Maint_Date',
			title : '上次保养日期',
			hidden : true
		},{
			field : 'custom_id',
			title : '客户编号',
			hidden : true
		},{
			field : 'cst_Name',
			title : '客户类型',
			hidden : true
		},{
			field : 'cstg_Name',
			title : '客户性质',
			hidden : true
		},{
			field : 'enterprise_id',
			title : '经办企业编号',
			hidden : true
		},{
			field : 'enterprise_id2',
			title : '使用集团企业编号',
			hidden : true
		},{
			field : 'enterprise_id3',
			title : '车档案所属企业编号',
			hidden : true
		}
	    ]]
	});
}
//会员入会将datagrid中的选中数据load给form表单
function doRegister(wid,formId,datagridId){
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
	    if(values[0].vip_Number==null){
			wid.dialog('open');
			clerFrom(formId);
			formId.form('load', values[0]);
			$("#add_vip_Balance").val("0.0");
			$("#add_vip_Integral").val("0");
  		}else{
  			alertMsg('对不起，该客户已是会员！', 'warning');
  		}
	}else{
		alertMsg('对不起，请先选中要操作的记录！', 'warning');
	}
}

//点击修改按钮后
function doUpdate(wid,formId,datagridId){
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
		if(values[0].vip_Number != null && values[0].vip_Number != ""){
  			wid.dialog('open');
  			clerFrom(formId);
  			formId.form('load', values[0]);
		}else{
			alertMsg('对不起,该客户还不是会员无法修改！', 'warning');
		}
	}else{
		alertMsg('对不起,请先选中要操作的记录！', 'warning');
	}
}

//清空查询条件
function doEmpty(){
	$('#form_north_condition_vip_mananement_id').form('clear');
	doSearch($('#vip_management_center_id'),$('#form_north_condition_vip_mananement_id'),'VipRecordMessageAction!doFindAll');
}

//条件查询提交
function doSearch(datagridId,formid){
	var form =  formid.form();
	var formvalue = serializeObject(form);
	datagridId.datagrid('load',formvalue);	
}

//挂失
function doLossOf(wid,formId,datagridId){
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
		if(values[0].vip_Number != null && values[0].vip_Number != ""){
  			wid.dialog('open');
  			$("#Modify_vip_Number").removeAttr("readonly");
  			clerFrom(formId);
  			formId.form('load', values[0]);
  			$("#LossOf_vip_Number").val("");
  			$("#vip_OldNumber").val(values[0].vip_Number);
		}else{
			alertMsg('对不起,该客户还不是会员无法操作！', 'warning');
		}
	}else{
		alertMsg('对不起,请先选中要操作的记录！', 'warning');
	}
}

//注销
function doLogOut(){
	var datagridId = $('#vip_management_center_id');
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
		var data = values[0];
		if(data.vip_Number != null && data.vip_Number != ""){
			$.messager.confirm('优亿软件提示','请确认是否要注销该条记录？',function(b){
				if(b){
					data.vip_Status = "注销";
					$.ajax({
						type : 'POST',
						url : projectPath+'VipRecordMessageAction!doVipState.action',
						data : data,
					    dataType : 'json',
						success : function(r){
						    alertMsg(r.msg, 'info');
							if(r.success){
								datagridId.datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
							}
						}
				   });
				}
			});
		}else{
			alertMsg('对不起,该客户还不是会员无法操作！', 'warning');
		}
	}else{
		alertMsg('对不起,请先选中要操作的记录！', 'warning');
	}
}

//冻结
function doFreeze(){
	var datagridId = $('#vip_management_center_id');
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
		var data = values[0];
		if(data.vip_Number != null && data.vip_Number != ""){
			$.messager.confirm('优亿软件提示','请确认是否要冻结该条记录？',function(b){
				if(b){
					data.vip_Status = "冻结";
					$.ajax({
						type : 'POST',
						url : projectPath+'VipRecordMessageAction!doVipState.action',
						data : data,
					    dataType : 'json',
						success : function(r){
						    alertMsg(r.msg, 'info');
							if(r.success){
								datagridId.datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
							}
						}
				   });
				}
			});
		}else{
			alertMsg('对不起,该客户还不是会员无法操作！', 'warning');
		}
	}else{
		alertMsg('对不起,请先选中要操作的记录！', 'warning');
	}
}

//解冻
function doNnfreeze(){
	var datagridId = $('#vip_management_center_id');
	var values = datagridId.datagrid('getSelections');
	if(values.length>0){
		var data = values[0];
		if(data.vip_Number != null && data.vip_Number != ""){
			$.messager.confirm('优亿软件提示','请确认是否要解冻该条记录？',function(b){
				if(b){
					data.vip_Status = "解冻";
					$.ajax({
						type : 'POST',
						url : projectPath+'VipRecordMessageAction!doVipState.action',
						data : data,
					    dataType : 'json',
						success : function(r){
						    alertMsg(r.msg, 'info');
							if(r.success){
								datagridId.datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
							}
						}
				   });
				}
			});
		}else{
			alertMsg('对不起,该客户还不是会员无法操作！', 'warning');
		}
	}else{
		alertMsg('对不起,请先选中要操作的记录！', 'warning');
	}
}

//情况form表单
function clerFrom(form){
	form.find('input').val(''); 
	form.find('select').val('');
	form.find('textarea').val('');
}

//会员卡挂失提交
function doLossOfSubmit(formId,url1,wId){
	var bool = formId.form('validate');
	if(bool){
		var form =  formId.form();
		var formvalue = serializeObject(form);	
		if(formvalue.vip_Number != null){
			if(formvalue.vip_Number != formvalue.vip_OldNumber){
				$.ajax({
					type : 'POST',
					url : url1,
					data : formvalue,
				    dataType : 'json',
					success : function(r){
					    alertMsg(r.msg, 'info');
						if(r.success){
							wId.dialog('close');
							$('#vip_management_center_id').datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
						}
					}
			    });
			}else{
				alertMsg('对不起,会员卡号一致不能挂失', 'warning');
			}
		}else{
			alertMsg('对不起,会员卡号不能为空', 'warning');
		}
	 }else{
	 	alertMsg('对不起，保存失败，请确认数据格式及内容是否正确！', 'warning');
	 }
}

//修改会员信息时获取form表单数据传给后台action
function doSubmit(formId,url1,wId){
	var bool = formId.form('validate');
	if(bool){
		var form =  formId.form();
		var formvalue = serializeObject(form);	
		if(formvalue.vip_Number != null){
			$.ajax({
				type : 'POST',
				url : url1,
				data : formvalue,
			    dataType : 'json',
				success : function(r){
				    alertMsg(r.msg, 'info');
					if(r.success){
						wId.dialog('close');
						$('#vip_management_center_id').datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
					}
				}
		   });
		}else{
			alertMsg('对不起,会员卡号不能为空', 'warning');
		}
	 }else{
	 	alertMsg('对不起，保存失败，请确认数据格式及内容是否正确！', 'warning');
	 }
}

//会员入会获取form表单数据传给后台action
function doVipSubmit(formId,url,wId,datagridId){
	var bool = formId.form('validate');
	if(bool){
		var form =  formId.form();
		var formvalue = serializeObject(form);	
		//编辑会员档案的时候不修改密码
		if(formvalue.vip_Number != null){
			if(formvalue.vip_Password != "" || formvalue.vip_confirmPassword != ""){
				if(formvalue.vip_Password == formvalue.vip_confirmPassword){
					$.ajax({
						type : 'POST',
						url : url,
						data : formvalue,
					    dataType : 'json',
						success : function(r){
						    alertMsg(r.msg, 'info');
							if(r.success){
								wId.dialog('close');
								datagridId.datagrid('load',serializeObject($("#form_north_condition_vip_mananement_id")));
							}
						}
				   });
				}else{
					alertMsg('对不起,两次密码不一致', 'warning');
				}
			}else{
				alertMsg('对不起,密码不能为空', 'warning');
			}
		}else{
			alertMsg('对不起,会员卡号不能为空', 'warning');
		}
	 }else{
	 	alertMsg('对不起，保存失败，请确认数据格式及内容是否正确！', 'warning');
	 }
}

//点击修改新增的时候明细被选中
function checked(){
	$('#tid').tabs('select','积分赠送明细');
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

function _exception(){
	showEditDialog("vip_management_center_id",null,"vip_management_center_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"会员档案"+getSystemTime());
}