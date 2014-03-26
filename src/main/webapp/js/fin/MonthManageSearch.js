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
	$('#endTime').val(new Date().format('yyyy-MM'));
	var d=new Date();
	d.setMonth(d.getMonth()-5);
    $('#beginTime').val(d.format('yyyy-MM'));
	$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'timeManageAction!findMonthManage.action',
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
	$('#monthManageSearchDatagrid').datagrid('load',serializeObject($('#monthManageQueryForm')));
}
var cancel=function(){
	$('#monthManageQueryForm').form('clear');
}

function _except(){
	showEditDialog("monthManageSearchDatagrid",null,"monthManageSearchDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"月营业情况"+getSystemTime());
}