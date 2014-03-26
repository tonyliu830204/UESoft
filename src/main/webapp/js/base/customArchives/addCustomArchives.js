$(function (){
    	$("#customEmail").mailAutoComplete();
    });
	function LoadOk() {
		if (document.readyState == "complete") {
		  if(customFlage){
			  findDefaultCustomAreaId("customArchives_add_pareaId");
		  }
		} else {
			setTimeout("LoadOk();", 200);
		}
	}

	setTimeout("LoadOk();", 200);
	
	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}