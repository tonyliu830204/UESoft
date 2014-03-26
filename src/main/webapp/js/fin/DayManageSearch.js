function LoadOk() {
	if (document.readyState == "complete") {
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame(){
	runs();
}
setTimeout("LoadOk();", 200);
function runs(){
	$('#endTime').val(getSystemTime());
	getStartDate($('#beginTime'));
	$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'timeManageAction!findDayManage.action',
			data:'',
			success : function(r) {
				eval(r);		
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   } 
		   	}
		});
}

var query=function(){
	$('#dayManageSearchDatagrid').datagrid('load',serializeObject($('#dayManageQueryForm')));
}
var cancel=function(){
	$('#dayManageQueryForm').form('clear');
}

function _except(){
	showEditDialog("dayManageSearchDatagrid",null,"dayManageSearchDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"日经营情况"+getSystemTime());
}