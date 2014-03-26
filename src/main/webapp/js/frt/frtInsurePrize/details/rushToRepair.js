function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		var data = $('#frtInsurePrizeSummaryDatagrid').datagrid('getSelected');
		if(data){
			$('#frtInsurePrizeRushToRepairForm') .form('load',data);
		}
	}
	setTimeout("LoadOk();", 200);