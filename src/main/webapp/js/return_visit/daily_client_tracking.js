$(function() {
	//日常客户跟踪
	$('#Daily_client_tracking_center_id').datagrid({
		url:'dailyclientTrackingAction!doFind.action',
		pagination : true,
		fit : true,
		pageSize : 10,
		newrap : false,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : true, // 自适应列宽 
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'carId',
			title : '车辆id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'adviceId',
			title : '维修建议id',
			width : 100,
			sortable : true,
			hidden : true
		} ,{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			sortable : true
		} ,{
			field : 'customName',
			title : '客户姓名',
			width : 100,
			sortable : true
		},{
			field : 'customTel',
			title : '手机号码',
			width : 100,
			sortable : true
		},{
			field : 'adviceContext',
			title : '维修建议',
			width : 100,
			sortable : true
		},{
			field : 'adviceTime',
			title : '建议日期',
			width : 100,
			sortable : true
		},{
			field : 'writePerson',
			title : '填写人',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
			return row.writePersonName;
			}
		},{
			field : 'procesState',
			title : '处理情况',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
			return row.procesStateName;
			}
		},{
			field : 'procesProson',
			title : '处理人',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
			return row.procesProsonName;
			}
		},{
			field : 'adviceTimeEnd',
			title : '处理日期',
			width : 100,
			sortable : true
		}
	  ]]
	});
});

function  queryAdvice(){
	$('#Daily_client_tracking_center_id').datagrid('unselectAll');
	$('#Daily_client_tracking_center_id').datagrid('load', serializeObject($('#richanggenzongtixing_form_id')));
}
function  doClear(){
	$('#Daily_client_tracking_center_id').datagrid('unselectAll');
	$('#richanggenzongtixing_form_id').form('clear');
	$('#Daily_client_tracking_center_id').datagrid('load', serializeObject($('#richanggenzongtixing_form_id')));
}
function addAdvice(){
	var data = $('#Daily_client_tracking_center_id').datagrid('getSelected');
	if (data) {
		var d = $('<div/>').dialog({
							modal : true,
							closable : true,
							title : '增加',
							width : 850,
							height : 450,
							href : projectPath+'return_visit/rushToRepair.jsp',
							buttons : [
									{
										iconCls : 'icon-save',
										text : '保存',
										handler : function() {
											if ($('#frtResvAdviceForm').form('validate')) {
												$.ajax({
													   type: 'post',
													   dataType: 'json',
													   url: 'frtReceptionAction!addFrtResvAdvice.action',
													   data: $('#frtResvAdviceForm').serialize(),
													   success: function(r){
														 if(r.success){
															 d.dialog('close');
															 $('#customer_genz1').datagrid('reload');
															 alertMsg(r);															
														 }else{
														 	alertMsg(r);
														 }
													   }
													});
											}
										}
									}, {
										iconCls : 'icon-undo',
										text : '取消',
										handler : function() {
											d.dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								$("#scarLicense").val(data.carLicense);
								$("#scarId").val(data.carId);
							}
						});
	} else {
		alertMsg('对不起，请先选中要增加的记录！', 'warning');
	}
}