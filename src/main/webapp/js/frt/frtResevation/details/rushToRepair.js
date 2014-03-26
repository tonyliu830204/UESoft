function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		var data = $('#frtResevationSummaryDatagrid').datagrid('getSelected');
		if(data){
			//$('#frtResevationAddForm').form('load', data);
			$('#rushToRepairForm') .form('load',data);
		}
	}
	setTimeout("LoadOk();", 200);