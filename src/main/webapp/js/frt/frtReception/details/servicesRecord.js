function LoadOk() {
		if (document.readyState == "complete") {
		initFrame();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
function initFrame() {
	var carId=$('#frtReception_details_carId').combobox('getValue');
	if(carId==""){
		carId=-1;
	}else{
		runs(carId);
	}
}
setTimeout("LoadOk();", 200);
function runs(carId){
	$(function (){
		 $frtReceptionServiceItemDatagrid = $('#frtReceptionServiceItemDatagrid');
		
		$frtReceptionServiceItemDatagrid.datagrid({
			url : 'frtReceptionAction!serviceRecord.action?carId='+carId,
			singleSelect : true,
			pagination : true,
			fit : true,
			rownumbers : true,
			fitColumns : true,
			loadMsg : "数据加载中，请稍后……",
			columns : [[{
					field : 'planStartTime',
					title : '维修日期',
					width : 130
				}, {
					field : 'receptionDistance',
					title : '维修里程数',
					width : 80
				}, {
					field : 'repitemName',
					title : '维修项目',
					width : 100,
				}, {
					field : 'repitemAmount',
					title : '维修金额',
					width : 60
				},{
					field : 'stfName',
					title : '维修人员',
					width : 60
				}]]
			});
    });
}