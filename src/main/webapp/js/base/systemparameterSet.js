$(function (){
	companyInfomationSetDatagrid = $('#companyInfomationSetDatagrid');
	
	companyInfomationSetDatagrid.datagrid({
		url : 'basCompanyInformationSetAction!findParameterAll.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		fitColumns : true,
		rownumbers : true,
		showFooter : true,
		columns : [[{
					field : 'ciId',
					title : '参数编号',
					width : 60,
					sortable : true
				}, {
					field : 'ciName',
					title : '参数名称',
					width : 60,
					sortable : true
				}, {
					field : 'ciValue',
					title : '参数值',
					width : 150,
					sortable : true,
					formatter : function(value, row, index){
						if(row.ciValue == '1'){
							return '否';
						}else if(row.ciValue == '2'){
							return '是';
						}else{
							return row.ciValue;
						}
					}
				}, {
					field : 'ciType',
					title : '参数分类',
					width : 60,
					hidden : true
				}
          ]]
	});
	
	$('#companyInfomationSet_ciName').on('keyup', function (e){
		if(e.keyCode == '13'){
			query();
		}
	});
	
});

var editCompanyInformationSetDialog;

var cifSubmit = function (){
	var data = $('#companyInfomationSetEditForm').serialize();
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: 'basCompanyInformationSetAction!edit.action',
		   data: data,
		   success: function(r){
			 if(r.success){
				 companyInfomationSetDatagrid.datagrid('reload');
				 editCompanyInformationSetDialog.dialog('close');
			 }else{
				 alertMsg(r);
			 }
		   },
		   error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   }
		   }
		});
}

var edit = function (){
	var data = companyInfomationSetDatagrid.datagrid('getSelected');
	if(data){
		var url = projectPath+'base/companyInformationSet/editCompanyInformationSet.jsp';
		var width = 465;
		var height = 360;
		var val = $('#companyInformationSet_ciType').combobox('getValue');
		if(val == '3' || val == '4' || val == '5'){
			url = projectPath+'base/companyInformationSet/editCompanyInformationSet2.jsp';
			width = 250;
			height = 160;
		}
		editCompanyInformationSetDialog = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '公司信息设定',
			width : width,
			height : height,
			href : url,
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					var ciName = $('#companyInfomationSetEditForm input[name=ciName]').val();
					var ciValue = $('#companyInfomationSetEditForm textarea[name=ciValue]').val();
					
					switch (ciName){
						case '公司电话':
							if(!validateForm('phone', ciValue)){
								alertMsg('请输入有效的固定电话，如：029-88888888', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '公司传真':
							if(!validateForm('faxno', ciValue)){
								alertMsg('请输入有效的传真号码，如：029-88888888', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '公司网址':
							if(!validateForm('url', ciValue)){
								alertMsg('请输入有效的网址，如：http://www.123456.com', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '公司邮箱':
							if(!validateForm('email', ciValue)){
								alertMsg('请输入有效的电子邮箱，如：abc@126.com', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '邮编':
							if(!validateForm('zip', ciValue)){
								alertMsg('邮政编码为6位数字！', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '短信测试号码':
							if(!validateForm('mobile', ciValue)){
								alertMsg('手机号码为11位数字！', 'info');
							}else{
								cifSubmit();
							}
							break;
						case '短信计费长度':
							if(!validateForm('gtzerointeger', ciValue)){
								alertMsg('短信计费长度为大于零的数字！', 'info');
							}else{
								cifSubmit();
							}
							break;
						default :
							cifSubmit();
					}
				}
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
					editCompanyInformationSetDialog.dialog('close');
				}
	        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	$('#companyInfomationSetEditForm').form('load', data);
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var query = function (){
	companyInfomationSetDatagrid.datagrid('load', serializeObject($('#companyInfomationSetQueryForm')));
}

var _clear = function (){
	$('#companyInfomationSetQueryForm input[name=ciName]').val('');
	companyInfomationSetDatagrid.datagrid('load', serializeObject($('#companyInfomationSetQueryForm')));
}