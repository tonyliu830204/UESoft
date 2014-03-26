$(function (){
	$carArchivesDatagrid = $('#carArchivesDatagrid');
	$carArchivesDatagrid.datagrid({
		url : projectPath+'basCarArchivesAction!datagrid.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		rownumbers : true,
		frozenColumns :[[{
			 field:'carId',
			 title:'车辆编号',
			 width:80,
			 sortable:true
		},{
			 field:'carLicense',
			 title:'车辆牌照',
			 width:80,
			 sortable:true
		}]],
		columns : [[{
			 field:'carVin',
			 title:'VIN号',
			 width:80,
			 sortable:true
		},{
			 field:'carMotorId',
			 title:'发动机号',
			 width:80,
			 sortable:true
		},{
			 field:'cbrdId',
			 title:'车辆品牌',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.cbrdName;
			 }
		},{
		     field:'cbrdName',
			 title:'车辆品牌',
			 width:80,
			 hidden:true
		},{
			 field:'ctypeId',
			 title:'车辆型号',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.ctypeName;
			 }
		},{
			 field:'ctypeName',
			 title:'车辆型号',
			 width:80,
			 hidden:true
		},{
			 field:'carCstlName',
			 title:'车辆款式',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.cstlName;
			 }
		},{
			 field:'cstlName',
			 title:'车辆款式',
			 width:80,
			 hidden:true
		},{
			 field:'color',
			 title:'车身颜色',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.colorName;
			 }
		},{
			 field:'colorName',
			 title:'车身颜色',
			 width:80,
			 hidden:true
		},{
			 field:'carClass',
			 title:'动力系统',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.carClassName;
			 }
		},{
			 field:'slsId',
			 title:'经销商',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.slsName;
			 }
		},{
			 field:'slsName',
			 title:'经销商',
			 width:80,
			 hidden:true
		},{
			 field:'carBuyDate',
			 title:'购车日期',
			 width:80,
			 sortable:true
		},{
			 field:'carLicenseDate',
			 title:'领证日期',
			 width:80,
			 sortable:true
		},{
			 field:'carRealationLisence',
			 title:'驾驶证号',
			 width:80,
			 sortable:true
		},{
			 field:'allowCarTypeId',
			 title:'准驾车型',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.allowCarTypeName;
			 }
		},{
			 field:'allowCarTypeName',
			 title:'准驾车型',
			 width:80,
			 hidden:true
		},{
			 field:'carBasinsuranceDate',
			 title:'交强险到期',
			 width:80,
			 sortable:true
		},{
			 field:'carBusinsuranceDate',
			 title:'商业险到期',
			 width:80,
			 sortable:true
		},{
			 field:'relcampId',
			 title:'保险公司',
			 width:80,
			 sortable:true,
			 formatter : function (value,row,index){
				 return row.relcampName;
			 }
		},{
			 field:'relcampName',
			 title:'保险公司',
			 width:80,
			 hidden:true
		},{
			 field:'carAnnualDate',
			 title:'年检到期',
			 width:80,
			 sortable:true
		},{
			 field:'carExaminedDate',
			 title:'年审到期',
			 width:80,
			 sortable:true
		},{
			 field:'twoDimensionDate',
			 title:'二维到期',
			 width:80,
			 sortable:true
		},{
			 field:'carMaintInterva',
			 title:'保养间隔',
			 width:80,
			 sortable:true
		},{
			 field:'carFstInsuranceDate',
			 title:'首保日期',
			 width:80,
			 sortable:true
		},{
			 field:'createDate',
			 title:'建档日期',
			 width:80,
			 sortable:true
		},{
			 field:'carRecord',
			 title:'档案号',
			 width:80,
			 sortable:true
		},{
			 field:'customId',
			 title:'客户编号',
			 width:80,
			 sortable:true
		},{
			 field:'customName',
			 title:'客户名称',
			 width:80,
			 sortable:true
		},{
			 field:'customSex',
			 title:'性别',
			 width:80,
			 sortable:true,
			 formatter: function(value,row,index){
					return row.customSexName;
			 }
		},{
			field : 'natureId',
			title : '客户性质',
			width : 60,
			sortable : true,
			formatter: function(value,row,index){
				return row.natureName;
			}
		},{
			field : 'natureName',
			title : '客户性质',
			width : 60,
			hidden : true
		},{
			field : 'cstgId',
			title : '客户类型',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstgName;
			}
		},{
			field : 'cstgName',
			title : '客户类型',
			width : 80,
			hidden : true
		},{
			field : 'cstId',
			title : '客户分类',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.cstName;
			}
		},{
			field : 'cstName',
			title : '客户分类',
			width : 80,
			hidden : true
		},{
			field : 'pareaId',
			title : '所在区域',
			width : 80,
			sortable : true,
			formatter: function(value,row,index){
				return row.pareaName;
			}
		},{
			field : 'pareaName',
			title : '所在区域',
			width : 80,
			hidden : true
		},{
			field : 'customAddr',
			title : '地址',
			width : 120,
			sortable : true
		},{
			field : 'customTel1',
			title : '联系电话',
			width : 80,
			sortable : true
		},{
			field : 'customTel2',
			title : '固定电话',
			width : 80,
			sortable : true
		},{
			field : 'customEmail',
			title : '电子邮箱',
			width : 100,
			sortable : true
		},{
			 field:'carPostalCode',
			 title:'邮政编码',
			 width:80,
			 sortable:true
		},{
			 field:'carBirthday',
			 title:'出生年月',
			 width:80,
			 sortable:true
		},{
			 field:'carRealationIdentifyCd',
			 title:'身份证号',
			 width:80,
			 sortable:true
		},{
			field : 'vipNumber',
			title : '会员卡号',
			width : 100,
			sortable : true
			
		},{
			field : 'vipLevelId',
			title : '会员等级',
			width : 80,
			sortable : true,
			formatter : function (value, row, index){
				return row.vipLevelName;
			}
		},{
			field : 'vipLevelName',
			title : '会员等级',
			width : 80,
			hidden : true
		},{
			field : 'vipGruopId',
			title : '会员分组',
			width : 80,
			sortable : true,
			formatter : function (value, row, index){
				return row.vipGruopName;
			}
		},{
			field : 'vipGruopName',
			title : '会员分组',
			width : 80,
			hidden : true
		},{
			field : 'vipStatus',
			title : '会员卡状态',
			width : 80,
			sortable : true
		
		},{
			field : 'joinTime',
			title : '入会日期',
			width : 85,
			sortable : true
		
		},{
			field : 'endTime',
			title : '会员到期',
			width : 85,
			sortable : true
		},{
			field : 'vipAge',
			title : '会龄/月',
			width : 60,
			sortable : true
		},{
			field : 'vipBalance',
			title : '卡内余额',
			width : 80,
			sortable : true
		},{
			field : 'vipIntegral',
			title : '当前积分',
			width : 80,
			sortable : true
		},{
			field : 'vipHobby',
			title : '会员爱好',
			width : 100,
			sortable : true
		},{
			 field:'carLastRepairDate',
			 title:'最后维修日期',
			 width:80,
			 sortable:true
		},{
			 field:'carLastRepairDistance',
			 title:'最后维修里程',
			 width:80,
			 sortable:true
		},{
			 field:'carRepairCnt',
			 title:'维修次数',
			 width:80,
			 sortable:true
		},{
			 field:'carLastMaintDate',
			 title:'最后保养日期',
			 width:80,
			 sortable:true
		},{
			 field:'carLastMaintDistance',
			 title:'最后保养里程',
			 width:80,
			 sortable:true
		},{
			 field:'carMaintCnt',
			 title:'保养次数',
			 width:80,
			 sortable:true
		},{
			 field:'carNextMaintDistance',
			 title:'预计下次保养里程',
			 width:80,
			 sortable:true
		},{
			 field:'carNextMaintDate',
			 title:'预计下次保养日期',
			 width:80,
			 sortable:true
		},{
			 field:'carDistancePerDay',
			 title:'日均里程',
			 width:80,
			 sortable:true
		},{
			 field:'notIntoTheStoreDays',
			 title:'未进店天数',
			 width:80,
			 sortable:true
		},{
			 field:'carRemark',
			 title:'附加信息',
			 width:80,
			 sortable:true
		},{
			 field:'receptionRemark',
			 title:'工单备注',
			 width:80,
			 sortable:true
		}]],
		onDblClickRow:function(rowIndex,rowData){
 			showInfo(rowData);
 		}
	});
});
var showInfo=function(rowData){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '车辆档案查看',
		width : 800,
		height : 430,
		href : projectPath+'base/carArchives/addCarArchives.jsp?flag=true',
		buttons : [{
        	iconCls : 'icon-undo',
			text : '关闭',
			handler : function (){
        		d.dialog('close');
			}
        }],
        onClose : function (){
	    	$(this).dialog('destroy');
	    },
	    onLoad : function (){
	    	$('#carArchivesAddForm').form('load', rowData);
		    $('#carArchives_add_customId').combogrid('setValue',rowData.customId);
	    }
	});
}
var add = function (){
	var d = $('<div/>').dialog({
		modal:true,
		closable : true,
		title : '车辆档案新增',
		width : 870,
		height : 500,
		href : projectPath+'base/carArchives/addCarArchives.jsp?flag=false',
		buttons : [{
			iconCls : 'icon-save',
			text : '保存',
			handler : function (){
				if($('#carArchivesAddForm').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: projectPath+'basCarArchivesAction!save.action',
					   data: $('#carArchivesAddForm').serialize(),
					   success: function(r){
						 if(r.success){
							 $carArchivesDatagrid.datagrid('load');
							 d.dialog('close');
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
			}
        },{
        	iconCls : 'icon-undo',
			text : '取消',
			handler : function (){
        		d.dialog('close');
			}
        }],
        onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

var _remove = function (){
	var data = $carArchivesDatagrid.datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){
			if (r){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: projectPath+'basCarArchivesAction!remove.action',
				   data: 'carId=' + data.carId,
				   success: function(r){
					 if(r.success){
						 $carArchivesDatagrid.datagrid('load');
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
		});
	}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}

var edit = function (){
	if($('#save').length != 0 && $('#cancel').length != 0){
		return;
	}
	var data = $carArchivesDatagrid.datagrid('getSelected');
	if(data){
		var d = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '车辆档案修改',
			width : 800,
			height : 430,
			href : projectPath+'base/carArchives/addCarArchives.jsp?flag=true',
			buttons : [{
				iconCls : 'icon-save',
				text : '保存',
				handler : function (){
					if($('#carArchivesAddForm').form('validate')){
						$.ajax({
						   type: 'post',
						   dataType: 'json',
						   url: projectPath+'basCarArchivesAction!edit.action',
						   data: $('#carArchivesAddForm').serialize(),
						   success: function(r){
							 if(r.success){
								 $carArchivesDatagrid.datagrid('reload');
								 d.dialog('close');
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
				}
	        },{
	        	iconCls : 'icon-undo',
				text : '取消',
				handler : function (){
	        		d.dialog('close');
				}
	        }],
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $carArchivesDatagrid.datagrid('getSelected');
		    	$('#carArchivesAddForm').form('load', data);
		    	$('#carArchives_add_customId').combogrid('setValue',data.customId);
		    	
		    	var value1=$('#carArchives_add_cbrdId').combobox('getValue');
        		var value2=$('#carArchives_add_ctypeId').combobox('getValue');
        		var value3=$('#carArchives_add_carCstlId').combobox('getValue');
		    	if(value1!=null&&value1.length>0){
		    		$('#carArchives_add_ctypeId').combobox('clear');
		            $('#carArchives_add_carCstlId').combobox('clear');   
		            $('#carArchives_add_ctypeId').combobox('reload', 
	            		'frtOptionsAction!findCarType.action?cbrdId=' +value1);
	            		$('#carArchives_add_ctypeId').combobox('setValue',value2);
	            		if(value2!=null&&value2.length>0){
		            		$('#carArchives_add_carCstlId').combobox('clear');  
			            	$('#carArchives_add_carCstlId').combobox('reload', 
			            		'frtOptionsAction!findCarStyle.action?ctypeId=' + value2);
	            			$('#carArchives_add_carCstlId').combobox('setValue',value3); 
	            		}
		    	}
		    }
		});
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}

var query = function (){
	if($('#carArchivesQueryForm').form('validate')){
		$carArchivesDatagrid.datagrid('load', serializeObject($('#carArchivesQueryForm')));
	}else{
		alertMsg('对不起,请确认内容及格式是否正确！', 'warning');
	 }
	
}

var _clear = function (){
	$('#carArchivesQueryForm').form('clear');
	$('#carArchives_cbrdId').combobox('reload');
	$('#carArchives_ctypeId').combobox('reload','basCarArchivesAction!findCarType.action');
	$('#carArchives_cstlId').combobox('reload', 'basCarArchivesAction!findCarStyle.action');
	$('#carArchives_slsId').combobox('reload');
	$('#carArchives_natureId').combobox('reload');
	$('#carArchives_cstgId').combobox('reload');
	$('#carArchives_cstId').combobox('reload');
	$('#carArchives_pareaId').combobox('reload');
	$carArchivesDatagrid.datagrid('load', serializeObject($('#carArchivesQueryForm')));
}

var changeDialog;
var carChange = function (){
	var data = $carArchivesDatagrid.datagrid('getSelected');
	
	if(data){
		changeDialog = $('<div/>').dialog({
			modal:true,
			closable : true,
			title : '车辆变更',
			width : 380,
			height : 360,
			href : projectPath+'base/carArchives/carArchivesChange.jsp',
	        onClose : function (){
		    	$(this).dialog('destroy');
		    },
		    onLoad : function (){
		    	var data = $carArchivesDatagrid.datagrid('getSelected');
		    	$('#carArchives_changeBeforeForm').form('load', data);
		    }
		});
	}
}

var remindDialog;

var carRemind = function (){
	var data = $carArchivesDatagrid.datagrid('getSelected');
	if (data) {
		var d = $('<div/>').dialog({
							modal : true,
							closable : true,
							title : '新增维修建议',
							width : 850,
							height : 450,
							href : projectPath+'return_visit/rushToRepair.jsp',
							buttons : [
									{
										iconCls : 'icon-save',
										text : '保存',
										handler : function() {
											if ($('#frtResvAdviceForm').form('validate')) {
												$.ajax({
													   type: 'post',
													   dataType: 'json',
													   url: 'frtReceptionAction!addFrtResvAdvice.action',
													   data: $('#frtResvAdviceForm').serialize(),
													   success: function(r){
														 if(r.success){
															 d.dialog('close');
															 $carArchivesDatagrid.datagrid('reload');
															 alertMsg(r);															
														 }else{
														 	alertMsg(r);
														 }
													   }
													});
											}
										}
									}, {
										iconCls : 'icon-undo',
										text : '取消',
										handler : function() {
											d.dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								$("#scarLicense").val(data.carLicense);
								$("#scarId").val(data.carId);
							}
						});
	} else {
		alertMsg('对不起，请先选中要增加的记录！', 'warning');
	}
}
//导出
function _except(){
	showEditDialog("carArchivesDatagrid",null,"carArchivesDatagrid_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"车辆档案"+getSystemTime());
}