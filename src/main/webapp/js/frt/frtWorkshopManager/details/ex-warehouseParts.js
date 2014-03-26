function LoadOk() {
		if (document.readyState == "complete") {
			initFrame();
			frtWorkshopManagerDetailsFlag=true;
		} else {
			setTimeout("LoadOk();", 200);
		}
	}
	function initFrame() {
		frtWorkshopManagerExwarehousePartsRuns();
	}
	setTimeout("LoadOk();", 200);