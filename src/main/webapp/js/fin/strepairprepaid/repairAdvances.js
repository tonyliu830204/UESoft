$(function (){
    loadPercharge($('#pre_perchargeDateBegin'));
    
    $('#pre_curPercharge').numberbox({
    	required:true,
	    missingMessage:'本次收款必填',
        min:0,  
        precision:2  
    });  
    
	$pre_StSellPrePercharge_datagrid = $('#pre_StSellPrePercharge_datagrid');
	$pre_StSellPrePercharge_datagrid.datagrid({
		url : 'StSellPerchargeAction_loadPreStSellPercharge.action',
		fit : true,
		fitColumns : true,
		sortName:'perchargeId',
		sortOrder:'asc',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		pagination : true,
		singleSelect : true,
		checkOnSelect : false,
		selectOnCheck : false,
		columns : [[{
			align : 'center',
			field : 'perchargeId',
			title : '收款单号',
			width : 120,
			sortable : true
		},{
			field : 'perchargeDate',
			title : '收款日期',
			width : 100
		},{
			field : 'carId',
			title : '车牌照',
			width : 100,
			formatter : function (value,row,index){
				return row.carLicense;
			}
		},{
			field : 'carLicense',
			title : '车辆牌照',
			width : 100,
			hidden : true
		},{
			field : 'cbrdName',
			title : '车品牌',
			width : 100
		},{
			field : 'ctypeName',
			title : '车类型',
			width : 100
		},{
			field : 'vin',
			title : 'vin号',
			width : 120
		},{
			field : 'customName',
			title : '客户名称',
			width : 100
		},{
			field : 'paymentId',
			title : '付款方式',
			width : 100,
			hidden : true
		},{
			field : 'paymentName',
			title : '收款方式',
			width : 100
		},{
			field : 'stfId',
			title : '经办人',
			width : 100,
			formatter : function (value,row,index){
				return row.stfName;
			}
		},{
			field : 'stfName',
			title : '经办人',
			width : 100,
			hidden : true
		},{
			field : 'curPercharge',
			title : '本次预收款',
			width : 100
		},{
			field : 'purPercharge',
			title : '预收款结余',
			width : 100,
			hidden:true
		},{
			field : 'chargeRemark',
			title : '备注',
			width : 100
		},{
			field : 'carRelationPerson',
			title : '联系人',
			width : 100,
			hidden:true
		},{
			field : 'carRelationTel1',
			title : '电话一',
			width : 100,
			hidden:true
		},{
			field : 'carRelationTel2',
			title : '电话二',
			width : 100,
			hidden:true
		},{
			field : 'preclrInoiceType',
			title : '票据类型',
			width : 100,
			hidden:true
		},{
			field : 'preclrNo',
			title : '票据编号',
			width : 100,
			hidden:true
		}]],
     onClickRow:function(rowIndex, rowData){
        $('#pre_perchargeId').val(rowData.perchargeId);
        $('#pre_perchargeDate').val(rowData.perchargeDate);
        $('#pre_ssp_carLicense').val(rowData.carLicense);
        $('#pre_ssp_carId').val(rowData.carId);
        $('#pre_ssp_cbrdName').val(rowData.cbrdName);
        $('#pre_ssp_ctypeName').val(rowData.ctypeName);
        $('#pre_ssp_customName').val(rowData.customName);
        $('#pre_ssp_carRelationPerson').val(rowData.carRelationPerson);
        $('#pre_ssp_carRelationTel1').val(rowData.carRelationTel1);
        $('#pre_ssp_carRelationTel2').val(rowData.carRelationTel2);
        $('#pre_curPercharge').numberbox('setValue',rowData.curPercharge);
        $('#pre_paymentId').combobox('setValue',rowData.paymentId);
        $('#pre_chargeRemark').val(rowData.chargeRemark);
        $('#pre_preclrInoiceType').combobox('setValue',rowData.preclrInoiceType);
        $('#pre_preclrNo').val(rowData.preclrNo);
     }
	});
});	

var ssp_d2;
function ssp_CarLicense(){
	ssp_d2 = $('<div/>');
	ssp_d2.dialog({
		title: '车辆信息选择',
	    width: 600,
	    height: 403,
	    cache: false,
	    href: projectPath+'fin/StSellPercharge/PreStSellPercharge_CarLicense.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }