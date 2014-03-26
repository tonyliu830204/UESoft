$(function(){
	//短信记录查询
	$('#SMS_record_management_center_id').datagrid({
		url : projectPath+'smsRecordManagementAction!getSmsSended.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'info_detailId',
				title : '发送信息明细编号',
				hidden : true
			},{
				field : 'info_Id',
				title : '发送信息编号',
				hidden : true
			},{
				field : 'car_Id',
				title : '车辆编号',
				hidden : true
			},{
				field : 'now_Send_Date',
				title : '发送时间',
				width : 100,
				sortable : true
			},{
				field : 'custom_Tel1',
				title : '接收号码',
				width : 100,
				sortable : true
			},{
				field : 'car_License',
				title : '车牌照',
				width : 100,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 100,
				sortable : true
			},{
				field : 'custom_Name',
				title : '联系人',
				width : 100,
				sortable : true
			},{
				field : 'send_Content',
				title : '发送内容',
				width : 100,
				sortable : true
			},{
				field : 'smsState',
				title : '短信状态',
				width : 100,
				sortable : true
			}
		]]
	});
});

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
//条件查询提交doConditionSubmit
function doSubmitByCondition(){
	$('#SMS_record_management_center_id').datagrid('load',serializeObject($('#form_sms_record_management_id')));
}

//清空
function doClear(){
	$('#form_sms_record_management_id').form('clear');
	doSubmitByCondition();
}

//删除方法
function doDelete(id,url1,url2){
	var datagrid = $('#SMS_record_management_center_id');
	var delrow = datagrid.datagrid('getSelections');
	if(delrow.length>0){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
					url : 'smsRecordManagementAction!doDelete.action',
					data : delrow[0],
					dataType : 'json',
					success : function(data){
					    alertMsg(data.msg, 'info');
						if(data.success){
							doSubmitByCondition();
						}
				    }
				});
			}
		});
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
	}
}