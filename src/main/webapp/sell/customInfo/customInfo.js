$(function(){
	$('#inputdataEnd').val(getSystemTime());
	 getStartDate($('#inputdataStart'));
	
	//person=$('#stf_id').val();
	$customInfo=$('#customInfo').datagrid({
		url:'${pageContext.request.contextPath}/customInfoAction!getPage.action',
		fit:true,
		pagination : true,
		rownumbers : true,
		sortOrder:'desc',
	    sortName:'customId',
		singleSelect : true,
		pageSize : 20,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				title : '编号',
				field : 'interviewId',
				hidden:true
		    },{
				title : '客户编号',
				field : 'customId',
				sortable : true,
				hidden:true
		    }, {
				title : '客户编号',
				field : 'xsCustomCode',
				width :130,
				sortable : true				
		    }, {
				title : '客户名称',
				field : 'xsCustomName',
				width : 100,
				sortable : true
			}, {
				title : '地址',
				field : 'xsCustomAddress',
				width : 100,
				sortable : true
			}, {
				title : '固定电话',
				field : 'xsCustomPhone',
				width : 100,
				sortable : true
			}, {
				title : '手机',
				field : 'xsCustomTelephone',
				width :100,
				sortable : true
			},{
				title : '联系人',
				field : 'xsContactsPerson',
				width : 100,
				sortable : true
			}, {
				title : '性别',
				field : 'xsCustomSex',
				width : 50,
				sortable : true,
				formatter : function (value,row,index){
				 return row.sexName;
				}			
			}, {
				title : '出生年月',
				field : 'xsCustomBirthday',
				width : 100,
				sortable : true
			}, {
				title : '收入情况',
				field : 'xsCustomIncome',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.incomeName;
				}	
			},{
				title : '学历程度',
				field : 'xsCustomDiploma',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.diplomaName;
				}
			}, {
				title : '客户来源',
				field : 'xsCustomSource',
				width :100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.sourceName;
				}
			}, {
				title : '建档日期',
				field : 'xsCustomInputdata',
				width : 100,
				sortable : true
			}, {
				title : '地区',
				field : 'xsCustomArea',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.areaName;
				}
			}, {
				title : '单位名称',
				field : 'xsCustomCompany',
				width : 100,
				sortable : true
			}, {
				title : '邮政编码',
				field : 'xsCustomZipcode',
				width : 100,
				sortable : true
			}, {
				title : '业务员',
				field : 'stfId',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.stfName;
				}
			}, {
				title : '证件号',
				field : 'xsCustomCredentials',
				width :100,
				sortable : true
			}, {
				title : '客户性质',
				field : 'xsCustomProperty',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.propertyName;
				}
			}, {
				title : '行业分类',
				field : 'xsCustomTrade',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.tradeName;
				}
			},   {
				title : '是否跟踪',
				field : 'xsCustomAfter',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.afterName;
				}
			},  {
				title : '潜在等级',
				field : 'xsCustomHideLevel',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.hideLevelName;
				}
			}, {
				title : '档案自编号',
				field : 'xsCustomNumber',
				width : 100,
				sortable : true
			}, {
				title : '对比车型',
				field : 'xsCustomContrastModel',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.contrastModelName;
				}
			}, {
				title : '选择理由',
				field : 'xsCustomReason',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.reasonName;
				}
			}, {
				title : '车辆用途',
				field : 'xsCustomApplication',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.applicationName;
				}
			}, {
				title : '其他分类',
				field : 'xsCustomOtherType',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.otherTypeName;
				}
			}, {
				title : '成交状态',
				field : 'xsCustomDeal',
				width : 100,
				sortable : true,
				formatter : function (value,row,index){
				 return row.customDealName;
				}
			},{
				title : '企业编号',
				field : 'enterpriseId',
				width : 100,
				hidden:true
			}
	        ]],onDblClickRow : function(rowIndex, rowData) {
				var cId=rowData.carId;			
				loalDel(cId);
			}
	});
			
});
function loalDel(cId){
	
	var d = $('<div/>').dialog({
		modal:true,
		closable :false,
		title : '查看客户信息',
		width : 800,
		height :500,
		href : projectPath+'sell/customInfo/customInfoEdit.jsp',
		cache: false, 
		buttons : [{
			 text : '关闭',
			 iconCls : 'icon-undo',						 
			 handler : function (){
				 d.dialog('close');
			}
       }],
			onClose : function (){
	    	d.dialog('destroy');
	      },onLoad : function() {
	    	 
	    	  $('#stf_id'). combobox({required:false});
	    	  	$("#jBxx input").prop("disabled", true);
	  			$("#jBxx select").prop("disabled", true);
	  			$("#jBxx textarea").prop("disabled",true);
	  			 
	  			$('#xsCustomName').validatebox({required:false});
	  			$('#customCredentials').validatebox({required:false});
	  			$('#xsCustomAddress').validatebox({required:false});
	  			$('#xsCustomTelephone').validatebox({required:false});
		    	  $('#xsCustomTelephone').validatebox('validate');
		    	  $('#xsCustomName').validatebox('validate');
		    	  $('#customCredentials').validatebox('validate');
		    	  $('#xsCustomAddress').validatebox('validate');
	    	
				var data = $('#customInfo')
				.datagrid('getSelected');
				$('#jBxx').form('load', data);
		
			}
	});
}


function addCustomInfo() {
	var d = $('<div/>')
			.dialog(
					{
						modal : true,
						closable : true,
						title : '新增',
						width : 800,
						height : 500,
						href : projectPath+'sell/customInfo/customInfoEdit.jsp',
						buttons : [
								{
									iconCls : 'icon-save',
									text : '保存',
									handler : function() {
										if ($('#jBxx').form('validate')) {
											$("#custom_DealId").combobox("enable");
											$("#xsCustomInputdata").removeAttr("disabled");	
											$.ajax({
												type : 'post',
												dataType : 'json',
												url : 'customInfoAction!saveCustomInfo.action',
												data : $('#jBxx').serialize(),
												success : function(r) {
													if (r.success) {
														$('#customInfo').datagrid('reload');
														d.dialog('close');
													} else {
														alertMsg(r);
													}
												},
												error : function(r) {
													if (r.readyState == '0'	&& r.status == '0') {
														alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！','warning');
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
							$('#xsCustomInputdata').val(getSystemTime());
							
				   }
					});
}
// 删除
function deleteCustomInfo() {
	var data = $('#customInfo').datagrid('getSelected');
	var index=findSelectRowIndex('customInfo',data);
	if (data) {
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
			if (r) {
				$.ajax( {
					type : 'post',
					dataType : 'json',
					url : 'customInfoAction!deleteCustomInfo.action',
					data : data,
					success : function(r) {
						if (r.success) {
                              $('#customInfo').datagrid('clearSelections');
							  $('#customInfo').datagrid('load');
							  setSelectRow('customInfo',index);
						} else {
							alertMsg(r);
						}
					},
					error : function(r) {
						if (r.readyState == '0' && r.status == '0') {
							alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！',
									'warning');
						}
					}
				});
			}
		});
	} else {
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}
}
// 修改
var updateCustomInfo = function() {
	var data = $('#customInfo').datagrid('getSelected');
	if (data) {
		var d = $('<div/>').dialog(
						{
							modal : true,
							closable : true,
							title : '修改',
							width : 800,
							height : 500,
							href : projectPath+'sell/customInfo/customInfoEdit.jsp',
							buttons : [
									{
										iconCls : 'icon-save',
										text : '保存',
										handler : function() {
											if ($('#jBxx').form('validate')) {
												$("#custom_DealId").combobox("enable");
												$("#xsCustomInputdata").removeAttr("disabled");	
												$.ajax( {
															type : 'post',
															dataType : 'json',
															url : 'customInfoAction!updateCustomInfo.action',
															data : $('#jBxx').serialize(),
															success : function(r) {
																if (r.success) {
																	$('#customInfo').datagrid('reload');
																	d.dialog('close');
																} else {
																	alertMsg(r);
																}
															},
															error : function(r) {
																if (r.readyState == '0'
																		&& r.status == '0') {
																	alertMsg(
																			'对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！',
																			'warning');
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
								var data = $('#customInfo').datagrid('getSelected');
								$('#jBxx').form('clear');
								$('#jBxx').form('load', data);
								var credentials=$("#customCredentials").val();
								if(credentials!=null && credentials!=''){
									var len=credentials.length;
									 document.getElementById("customIdenLength").innerHTML=len;
								}
								
							}
						});
	} else {
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
	}
}
var _searchCustomInfo = function() {
	$('#customInfo').datagrid('unselectAll');
	$('#customInfo').datagrid('load', serializeObject($('#pisForm')));
	addInitDate($('#inputdataStart'),$('#inputdataEnd'));

}

function _clear() {
	$('#pisForm').form('clear');
	$('#customInfo').datagrid('load', serializeObject($('#pisForm')));
	addInitDate($('#inputdataStart'),$('#inputdataEnd'));

}
//导出
function _except(){
	var data =  $("#customInfo").datagrid('getData'); 
	 if(data.rows.length==0){
		 alertMsg('数据列表为空，不能导出！', 'warning');
		 return ;
	 }
	showEditDialog("customInfo",null,"customInfo_div","开始导出","导出配置",0,_callbackExcept);
	


}

function _callbackExcept(parentId,fieldNames){
exportEsuyUIExcelFile(parentId,fieldNames,"客户档案"+getSystemTime());
}


function _config(){
var data =  $("#customInfo").datagrid('getData'); 
 if(data.rows.length==0){
	 alertMsg('数据列表为空，不能打印！', 'warning');
	 return ;
 }
	showEditDialog("customInfo",personnelSumTable,"customInfo_div","开始打印","打印配置",2,_print);

}

function _print(parentId,fieldNames){
printEsuyUIPreview(parentId,fieldNames);
}	
	