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
			url : 'combineAnalyseAction!findBalanceCountAnalyse.action',
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
function queryBalanceCountAnalyse(){
	if($('#balanceCountAnalyseQueryForm').form('validate')){
		var obj=startQuery();
		if(staticFlag!=true)
			staticFlag=true;
		$.ajax({
			type : 'post',
			url : 'combineAnalyseAction!findBalanceCountAnalyse.action',
			dataType : 'json',
			data:$('#balanceCountAnalyseQueryForm').serialize(),
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
	$('#balanceCountAnalyseQueryForm').form('clear');
} 
//Excel导出
function _exceptBalanceCountAnalyse(){
	showEditDialog("balanceCountAnalyse",null,"balanceCountAnalyse_center","开始导出","导出配置",0,_callbackBalanceCountAnalyse);
}
function _callbackBalanceCountAnalyse(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"结算台次分析"+getSystemTime());
}