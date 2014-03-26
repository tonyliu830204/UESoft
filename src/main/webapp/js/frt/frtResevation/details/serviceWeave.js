$(function (){
	var selectServiceWeaveDatagrid = $('#selectServiceWeaveDatagrid');
	selectServiceWeaveDatagrid.datagrid({
		url : 'frtReceptionAction!serviceWeave.action',
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		fit : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'rpId',
			title : '套餐编号',
			width : 100,
			sortable : true,
		},{
			field : 'rpName',
			title : '套餐名称',
			width : 100,
			sortable : true
		},{
			field : 'itemTimeAmount',
			title : '套餐工时',
			width : 100,
			sortable : true
		},{
			field : 'partsAmount',
			title : '维修材料费',
			width : 100
		}]],
		onDblClickRow : function (rowIndex, rowData){
		        staticRpId=rowData.rpId;
				loadWeave();
		} 
	});
});

function _selectServiceWeaveQuery(){
	$('#selectServiceWeaveDatagrid').datagrid('load',serializeObject($('#selectServiceWeaveQueryForm')));
}
function _selectServiceWeaveClear(){
	$('#selectServiceWeaveQueryForm').form('clear');
}