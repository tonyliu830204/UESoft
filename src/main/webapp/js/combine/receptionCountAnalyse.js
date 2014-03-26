var staticFlag=false;
var formatLocalData=function(data){
	var string=JSON.stringify(data);
	string=string.replace(/"/g,'_');
	return string;
}
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
	$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'combineAnalyseAction!findReceptionCountAnalyse.action',
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
//查询
function queryReceptionCountAnalyse(){
	if($('#receptionCountAnalyseQueryForm').form('validate')){
		var obj=startQuery();
		if(staticFlag!=true)
			staticFlag=true;
		$.ajax({
			type : 'post',
			url : 'combineAnalyseAction!findReceptionCountAnalyse.action',
			dataType : 'json',
			data:$('#receptionCountAnalyseQueryForm').serialize(),
			success : function(r) {
				eval(r);
				endQuery(obj);
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   } 
		   	}
		});
	}else{
		alertMsg('对不起，请输入正确的查询条件！', 'warning');
	}
}
//清空
function _clear(){
	$('#receptionCountAnalyseQueryForm').form('clear');
} 
//Excel导出
function _exceptReceptionCountAnalyse(){
	showEditDialog("receptionCountAnalyse",null,"receptionCountAnalyse_center","开始导出","导出配置",0,_callbackReceptionCountAnalyse);
}
function _callbackReceptionCountAnalyse(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"接车台次分析"+getSystemTime());
}