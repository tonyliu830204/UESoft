$(function (){
	loadPercharge($('#perchargeDateBegin'));

    $('#curPercharge').numberbox({
    	required:true,
	    missingMessage:'本次收款必填',
        min:0,  
        precision:2  
    });  
	
	$('#StSellPercharge_datagrid').datagrid({
		url : 'StSellPerchargeAction!loadStSellPercharge.action',
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
        $('#perchargeId').val(rowData.perchargeId);
        $('#perchargeDate').val(rowData.perchargeDate);
        $('#ssp_carLicense').val(rowData.carLicense);
        $('#ssp_carId').val(rowData.carId);
        $('#ssp_cbrdName').val(rowData.cbrdName);
        $('#ssp_ctypeName').val(rowData.ctypeName);
        $('#ssp_customName').val(rowData.customName);
        $('#ssp_carRelationPerson').val(rowData.carRelationPerson);
        $('#ssp_carRelationTel1').val(rowData.carRelationTel1);
        $('#ssp_carRelationTel2').val(rowData.carRelationTel2);
        $('#curPercharge').val(rowData.curPercharge);
        $('#paymentId').combobox('setValue',rowData.paymentId);
        $('#chargeRemark').val(rowData.chargeRemark);
        $('#preclrInoiceType').combobox('setValue',rowData.preclrInoiceType);
        $('#preclrNo').val(rowData.preclrNo);
     }
	});
});	

var pre_ssp_d2;
function pre_ssp_CarLicense(){
	pre_ssp_d2 = $('<div/>');
	pre_ssp_d2.dialog({
		title: '车辆信息选择',
	    width: 600,
	    height: 403,
	    cache: false,
	    href: projectPath+'fin/StSellPercharge/StSellPercharge_CarLicense.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
 }