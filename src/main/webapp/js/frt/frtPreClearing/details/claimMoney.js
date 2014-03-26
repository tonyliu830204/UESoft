function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		if(rowData){
			$('#claimMoneyForm') .form('load',rowData);
			changeSelectedState();
		}
	}
	setTimeout("LoadOk();", 200);
	function changeSelectedState(){
		var tag=document.getElementById("finTag").checked;
		if(tag){
			document.getElementById("finAllTag").disabled=false;
		}else{
			document.getElementById("finAllTag").disabled=true;
			document.getElementById("finAllTag").checked=false;
		}
	}