var tag=false;
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
				url : 'serviceDestClerkAnalyseAction!findServiceClassAnalyse.action',
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
	var _search=function(){
		if($('#serviceClassAnalyseQueryForm').form('validate')){
			tag=true;
			$('#serviceClassAnalyseDatagrid').datagrid('load',serializeObject($('#serviceClassAnalyseQueryForm')));
		}else{
			alertMsg('对不起，请输入正确的查询条件！', 'warning');
		}
	}
	var _clear=function(){
		$('#serviceClassAnalyseQueryForm').form('clear');
	}
	function _except(){
		showEditDialog("serviceClassAnalyseDatagrid",null,"serviceClassAnalyseDatagrid_center","开始导出","导出配置",0,_callbackExcept);
	}
	function _callbackExcept(parentId,fieldNames){
		exportEsuyUIExcelFile(parentId,fieldNames,"维修类别分析"+getSystemTime());
	}