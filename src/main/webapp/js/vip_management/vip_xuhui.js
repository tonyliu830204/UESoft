var addOrUpdate = 0;

$(function(){
	//会员续会  
	$('#datagrid_vip_xuhui_id').datagrid({
		url : projectPath+'VipRecordMessageAction!getVipInfo.action',
		fit : true,
		fitColumns : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Id',
		sortName:'vip_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'vip_Id',
			title : '会员编号',
			hidden : true
		},{
			field : 'vip_Number',
			title : '会员卡号',
			width : 100,
			sortable : true
		},{
			field : 'car_License',
			title : '车辆牌照',
			width : 100,
			sortable : true
		},{
			field : 'car_Vin',
			title : 'VIN号',
			width : 130,
			sortable : true
		},{
			field : 'vip_Name',
			title : '会员名称',
			width : 100,
			sortable : true
		},{
			field : 'vip_Birthday',
			title : '出生年月',
			width : 130,
			sortable : true
		},{
			field : 'vip_Tel',
			title : '联系电话',
			width : 100,
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
		},{
			field : 'vip_Level_Name',
			title : '会员等级',
			width : 100,
			sortable : true
		},{
			field : 'vip_Group_Name',
			title : '会员分组',
			width : 100,
			sortable : true
		},{
			field : 'vip_Status_value',
			title : '会员卡状态',
			width : 100,
			sortable : true
		},{
			field : 'vip_Integral',
			title : '可用积分',
			width : 100,
			sortable : true
		},{
			field : 'vip_Total_Integral',
			title : '累计积分',
			width : 100,
			sortable : true
		},{
			field : 'adjournment_Integral',
			title : '赠送积分',
			width : 100,
			sortable : true
		},{
			field : 'adjournment_ManageName',
			title : '续会经办人',
			width : 100,
			sortable : true
		},{
			field : 'adjournment_Date',
			title : '续会日期',
			width : 130,
			sortable : true
		},{
			field : 'adjournment_Memo',
			title : '续会理由',
			width : 240,
			sortable : true
		}
		]]
	});
});

function showdialog(a){
	addOrUpdate = a; 
	var d = $('#vip_mananement_vip_xuhui_dialog');
	var divfrom = $('#form_Vip_management_member_part');
	if(a==1){
		d.dialog('open');
	}
	if(a==2){
		var values = $('#datagrid_vip_xuhui_id').datagrid('getSelections');
  		if(values.length>0){
  			d.dialog('open');
  			divfrom.form('clear');
  			divfrom.form('load', values[0]);
  		}else{
			alertMsg('对不起，请先选择要续会的会员！', 'warning');
			return;
		}
	}
	$('#adjournment_Integral').val("0.0");
}

//条件查询提交doConditionSubmit
function doSubmitByCondition(){
	var datagridId = $('#datagrid_vip_xuhui_id');
	var formid = $('#form_vip_xuhui_id');
	var form =  formid.form();
	var formvalue = serializeObject(form);
	datagridId.datagrid('load',formvalue);	
}

function adjournmentSubmit(){
	var url1 = projectPath+'VipRecordMessageAction!doUpdateAdjournment.action';
	var form =  $('#form_Vip_management_member_part').form();
	var formvalue = serializeObject(form);	
	//编辑会员档案的时候不修改密码
	$.ajax({
		type : 'POST',
		url : url1,
		data : formvalue,
	    dataType : 'json',
		success : function(r){
		    alertMsg(r.msg, 'info');
			if(r.success){
				$('#vip_mananement_vip_xuhui_dialog').dialog('close');
				doSubmitByCondition();
			}
		}
   });
}

//清空
function doClear(){
	$('#form_vip_xuhui_id').form('clear');
	doSubmitByCondition();
}

function _exception(){
	showEditDialog("datagrid_vip_xuhui_id",null,"datagrid_vip_xuhui_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"会员续会"+getSystemTime());
}