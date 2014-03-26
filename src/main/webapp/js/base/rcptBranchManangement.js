$(function (){			
	$('#rcptBranchManangementDatagrid').datagrid({
		url : 'frtReceptionAction!rcptBranchDatagrid.action',
		fit : true,
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		idField:'receptionId',
			columns : [[{
				field:'ck',
				checkbox:true
			},{
				field : 'receptionId',
				title : '工单号',
				width : 80,
				sortable : true
			}, {
				field : 'carLicense',
				title : '车辆牌照',
				width : 70,
				sortable : true
			}, {
				field : 'customName',
				title : '客户名称',
				width : 80,
				sortable : true
			}, {
				field : 'reptName',
				title : '维修类别',
				width : 70,
				hidden : true
			}, {
				field : 'rcptBranch',
				title : '接车分部',
				width : 30
			}]],
			onLoadSuccess : function (data){
				$('#rcptBranchManangementDatagrid').datagrid('clearSelections');
			}
	});
});

var query = function (){
	$('#rcptBranchManangementDatagrid').datagrid('load', serializeObject($('#rcptBranchManangementQueryForm')));
}

var _clear = function (){
	$('#rcptBranchManangementQueryForm').form('clear');
	$('#rcptBranchManangementDatagrid').datagrid('load', serializeObject($('#rcptBranchManangementQueryForm')));
}
var edit=function(){
	var rows=$('#rcptBranchManangementDatagrid').datagrid('getSelections');
	if(rows==null||rows.length==0){
		 alertMsg('对不起，请先选择要修改的记录！', 'warning');
		 return;
	}
	var ids="";
	for ( var i = 0; i < rows.length; i++) {
		ids+=rows[i].receptionId+",";
	}
	ids=ids.substring(0,ids.length-1);
	$('#ids').val(ids);
	  $('#rcptBranchManangement').dialog({
		buttons:[{
			text:'Ok',
			iconCls:'icon-ok',
			handler:function(){
				if($('#rcptBranchManangementEditForm').form('validate')){
					$.ajax({
						type : 'post',
						dataType : 'json',
						url : 'frtReceptionAction!editRcptBranch.action',
						data: $('#rcptBranchManangementEditForm').serialize(),
						success : function(r) {
							if (r.success) {
								$('#rcptBranchManangementEditForm').form('clear');
								$('#rcptBranchManangement').dialog('close');
								alertMsg(r);
								$('#rcptBranchManangementDatagrid').datagrid('load');
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
		}},{
			text:'Cancel',
			handler:function(){
				$('#rcptBranchManangementEditForm').form('clear');
				$('#rcptBranchManangement').dialog('close');
			}
		}]
	});
}
//导出
function _except(){
	showEditDialog("rcptBranchManangementDatagrid",null,"rcptBranchManangementDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"接车分部管理"+getSystemTime());
}