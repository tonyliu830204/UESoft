$(function(){
	load();
	$('#infomation_send_manag_dialg_id').dialog({
		closed : true
	});
	loadXu();
});

function load(){
	//短信发送管理
	$('#datagrid_infomation_send_manag_id').datagrid({
		url : projectPath+'infomationSendManageAction!doFind.action',
		fit : true,
		pagination : true,
		fitColumns : false,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'car_Id',
		sortName:'car_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'car_Id',
				title : '车辆编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车牌照',
				width : 100,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 100,
				sortable : true
			},{
				field : 'ctype_Name',
				title : '车型号',
				width : 100,
				sortable : true
			},{
				field : 'cbrd_Name',
				title : '车型号',
				width : 100,
				sortable : true
			},{
				field : 'custom_Name',
				title : '客户名称',
				width : 100,
				sortable : true
			},{
				field : 'parea_Name',
				title : '所在区域',
				width : 100,
				sortable : true
			},{
				field : 'custom_Tel1',
				title : '联系电话',
				width : 100,
				sortable : true
			},{
				field : 'car_Buy_Date',
				title : '购车日期',
				width : 100,
				sortable : true
			},{
				field : 'car_Last_Maint_Date',
				title : '保养日期',
				width : 100,
				sortable : true
			},{
				field : 'car_Annual_Date',
				title : '年检到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Exanined_Date',
				title : '年审到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Businsurance_Date',
				title : '商业险到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Basinsurance_Date',
				title : '交强险到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Last_Repair_Distance',
				title : '最后维修里程',
				width : 100,
				sortable : true
			}
		]]
	});
}

function loadXu(){
	//短信发送管理已选发送列表 datagrid_infomation_send_manag_dialg_id
	$('#datagrid_infomation_send_manag_dialg_id').datagrid({
		url : '',
		fit : true,
		pagination : true,
		fitColumns : false,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'car_Id',
		sortName:'car_Id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'car_Id',
				title : '车辆编号',
				hidden : true
			},{
				field : 'car_License',
				title : '车牌照',
				width : 100,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 100,
				sortable : true
			},{
				field : 'ctype_Name',
				title : '车型号',
				width : 100,
				sortable : true
			},{
				field : 'cbrd_Name',
				title : '车型号',
				width : 100,
				sortable : true
			},{
				field : 'custom_Name',
				title : '客户名称',
				width : 100,
				sortable : true
			},{
				field : 'parea_Name',
				title : '所在区域',
				width : 100,
				sortable : true
			},{
				field : 'custom_Tel1',
				title : '联系电话',
				width : 100,
				sortable : true
			},{
				field : 'car_Buy_Date',
				title : '购车日期',
				width : 100,
				sortable : true
			},{
				field : 'car_Last_Maint_Date',
				title : '保养日期',
				width : 100,
				sortable : true
			},{
				field : 'car_Annual_Date',
				title : '年检到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Exanined_Date',
				title : '年审到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Businsurance_Date',
				title : '商业险到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Basinsurance_Date',
				title : '交强险到期',
				width : 100,
				sortable : true
			},{
				field : 'car_Last_Repair_Distance',
				title : '最后维修里程',
				width : 100,
				sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
		
			$(this).datagrid('deleteRow', rowIndex);
		
		}
	});	
}

//将form表单序列化为对象
function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']]=o[this['name']]+","+this['value'];
		}else{
			o[this['name']]=this['value'];
		}
	});
	return o;
} 

//条件查询提交
function doConditionSubmit(){
	$('#datagrid_infomation_send_manag_id').datagrid('load',serializeObject($('#form_infomation_send_manage_id')));
} 

//清空
function doClear(){
	$('#form_infomation_send_manage_id').form('clear');
	doConditionSubmit();
}

//点击短信发送时将已加载的数据 添加到已选择列表中
function sendinformation(){
	//加载空的连接清除已选列表的数据
	$('#datagrid_infomation_send_manag_dialg_id').datagrid('loadData',{"rows":[],"total":0});
	
	$('#infomation_send_manag_dialg_id').dialog('open');
	//获取已加载的数据
	var values = $('#datagrid_infomation_send_manag_id').datagrid('getSelections');
	if(values.length>0){
		for ( var i = 0; i < values.length; i++) {
			$('#datagrid_infomation_send_manag_dialg_id').datagrid('appendRow',values[i]);
		}
	}
}

//当定时发送框被选中的时候触发此方法
function selected(){
	$('#dingshifasong_id').removeAttr("disabled");
}

function selected2(){
	$('#dingshifasong_id').val('');
	$('#dingshifasong_id').attr("disabled","disabled");
}

function sendout(){
	//获取文本域短信内容
	var send_Content = $('#send_Content_Id').val();
	//获取定时发送时间
	var other_Send_Date = $('#dingshifasong_id').val();
	//获取测试号码
	var test_Number = $('#test_Number_Id').val();
	if(send_Content!=""){
		$.messager.confirm('优亿软件提示','请确定短信内容！',function(a){
		    if(a){
		    	//获取进度条
				$.messager.progress({
					title : '短信发送',
					msg : '短信发送中，请稍后...',
					text : '',
					interval : 300
				});
				var url1 = 'infomationSendManageAction!smsSend.action?send_Content='+send_Content+'&other_Send_Date='+other_Send_Date+'&test_Number='+test_Number;
				var effectRow = getChange($('#datagrid_infomation_send_manag_dialg_id'));
				if(effectRow){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: url1,
					   data: effectRow,
					   success: function(r){
						   alert(r.msg);
						   $.messager.progress('close');
					   }
					});
				}
			}
	    }); 
	}else{
		$.messager.alert('优亿软件提示','发送内容不能为空！','info'); 
		return;
	}
}

//获取所有行数据
function getChange(id){
	var inserted = id.datagrid('getData');
	var effectRow = new Object();
	if(inserted){
		effectRow['inserted'] = JSON.stringify(inserted);
	}
	return effectRow;
}