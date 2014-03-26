$(function() {
	// 跟踪管理汇总
	$('#customer_genz1').datagrid({
		url : 'customerGzManageAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : false,
		newrap : false,
		striped : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		rownumbers : true,
		singleSelect : true,
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'carLicense',
			title : '车牌照',
			width : 100,
			sortable : true
		},{
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100,
			sortable : true,
		},{
			field : 'customName',
			title : '客户名称',
			width : 100,
			sortable : true,
		},{
			field : 'customTel1',
			title : '客户电话',
			width : 100,
			sortable : true
		
		},{
			field : 'carVan',
			title : 'Vin号',
			width : 100,
			sortable : true
			
		} ,{
			field : 'rcptitemName',
			title : '维修项目',
			width : 100,
			sortable : true
			
		}, {
			field : 'ctypeName',
			title : '品牌-车型',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptionDistance',
			title : '公里数',
			width : 100,
			sortable : true
		
		}, {
			field : 'customLinkMan',
			title : '客户联系人',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptionId',
			title : '工单号',
			width : 100,
			sortable : true
			
		}/*, {
			field : '',
			title : '自编号',
			width : 100,
			sortable : true
			
		}*/, {
			field : 'interDate',
			title : '进厂日期',
			width : 100,
			sortable : true
		
		}, {
			field : 'preclrTime',
			title : '结算日期',
			width : 100,
			sortable : true
		
		}, {
			field : 'preclrId',
			title : '结算单号',
			width : 100,
			sortable : true
			
		}, {
			field : 'receptor',
			title : '前台接待',
			width : 100,
			sortable : true
			
		}, {
			field : 'carBuyDate',
			title : '购车日期',
			width : 100,
			sortable : true
			
		}, {
			field : 'propRepPer',
			title : '托修人',
			width : 100,
			sortable : true
			
		}, {
			field : 'propPhone',
			title : '托修人电话',
			width : 100,
			sortable : true
		}, {
			field : 'customAddr',
			title : '地址',
			width : 100,
			sortable : true
		}, {
			field : 'repgrpId',
			title : '维修班组',
			width : 100,
			sortable : true
		}, {
			field : 'satisfaction',
			title : '满意度',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '用户建议及意见',
			width : 100,
			sortable : true
		}*/, {
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true
		}, {
			field : 'preclrRealAmount',
			title : '实际收款',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '处理时间及结果',
			width : 100,
			sortable : true
		}*/, {
			field : 'color',
			title : '颜色',
			width : 100,
			sortable : true
		}, {
			field : 'carMotorId',
			title : '发动机号',
			width : 100,
			sortable : true
		}, {
			field : 'returnVisitMembers',
			title : '回访人',
			width : 100,
			sortable : true
		}, {
			field : 'callSituation',
			title : '通话情况',
			width : 100,
			sortable : true
		}, {
			field : 'reptName',
			title : '维修类别',
			width : 100,
			sortable : true
		}, {
			field : 'reciptReturnvisit',
			title : '接收回访',
			width : 100,
			sortable : true
		}/*, {
			field : '',
			title : '不满意处理员',
			width : 100,
			sortable : true
		}*/, {
			field : 'customSex',
			title : '性别',
			width : 100,
			sortable : true
		}, {
			field : 'complaintContent',
			title : '客户投诉内容',
			width : 100,
			sortable : true
		}, {
			field : 'handleResult',
			title : '投诉处理结果',
			width : 100,
			sortable : true
		}, {
			field : 'complaintType',
			title : '投诉类型',
			width : 100,
			sortable : true
		}, {
			field : 'complaintDegree',
			title : '投诉程度',
			width : 100,
			sortable : true 
		}, {
			field : 'preclrRemark',
			title : '结算备注',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'problemDesc',
			title : '故障描述',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'handleProgram',
			title : '处理方案',
			width : 100,
			sortable : true,
			hidden:true
		}, {
			field : 'serviceProposal',
			title : '维修建议',
			width : 100,
			sortable : true ,
			hidden:true
		}, {
			field : 'handlePerson',
			title : '投诉处理人',
			width : 100,
			sortable : true ,
			hidden:true
		},{
			field : 'carId',
			title : '车辆id',
			sortable : true,
			hidden:true
		},{
			field : 'collectId',
			title : '客户跟踪汇总id',
			sortable : true,
			hidden:true
		},{
			field : 'detailId',
			title : '客户跟踪汇总明细id',
			width : 100,
			sortable : true,
			hidden:true
		},{
			field : 'resvId',
			title : '预约编号',
			width : 100,
			sortable : true,
			hidden:true
		}] ],
		onDblClickRow : function(rowIndex, rowData){
			Dbclick(rowData);
		}
	});
	// 跟踪管理3DC调查
	var $customer_genz3 = $('#customer_genz3');
	$customer_genz3.datagrid({
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'dcNameId',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'dcId',
			title : 'ID',
			hidden : true
		}, {
			field : 'dcNameId',
			title : 'ID',
			hidden : true
		},{
			field : 'projectName',
			title : '项目名称',
			width : 200
		},{
			field : 'evaluateOne',
			title : '很好',
			width : 50,
			editor : {
			type:'text'
		 }	
		},{
			field : 'evaluateTwo',
			title : '好',
			width : 50,
			editor : {
			type:'text'
		 }	
		},{
			field : 'evaluateThree',
			title : '一般',
			width : 50,
			editor : {
			type:'text'
		 }	
		},{
			field : 'evaluateFour',
			title : '不好',
			width : 50,
			editor : {
			type:'text'
		 }	
		},{
			field : 'evaluateFive',
			title : '很不好',
			width : 50,
			editor : {
			type:'text'
		 }	
		},{
			field : 'score',
			title : '评分',
			width : 50,
			editor : {
				type : 'combobox',
				options : {
					url : 'frtOptionsAction!findBaseListData.action?key=score',
					mode : 'remote',
					valueField:'id',  
				    textField:'text',  
				}
			},
	     formatter: function(value,row,index){
					return row.scoreName;
		 }
		
		},{
			field : 'note',
			title : '备注信息',
			width : 300,
			editor : {
			type:'text'
		 }	
		}] ],
		
	onClickRow : function (rowIndex, rowData){
		$('#customer_genz3').datagrid('beginEdit',rowIndex);
		var ed = $customer_genz3.datagrid('getEditors', rowIndex);
		ed[0].target.click(function (){
			var valueOne=ed[0].target.val();
			ed[0].target.select();
			var a=null;
			if(valueOne==null || valueOne==''|| valueOne==undefined){
					a='√';
					ed[1].target.val('');
					ed[2].target.val('');
					ed[3].target.val('');
					ed[4].target.val('');
			}else{
				a='';
			}
			ed[0].target.val(a);
			valueOne=a;
		});
		
		ed[1].target.click(function (){
			var valueTwo=ed[1].target.val();
			ed[1].target.select();
			if(valueTwo==null || valueTwo==''|| valueTwo==undefined){
				ed[1].target.val('√');
				ed[0].target.val('');
				ed[2].target.val('');
				ed[3].target.val('');
				ed[4].target.val('');
				return;
			}else{
				ed[1].target.val('');
			}
		});
		
		
		ed[2].target.click(function (){
			var valueThree=ed[2].target.val();
			ed[2].target.select();
			if(valueThree==null || valueThree==''|| valueThree==undefined){
				ed[2].target.val('√');
				ed[0].target.val('');
				ed[1].target.val('');
				ed[3].target.val('');
				ed[4].target.val('');
				return;
			}else{
				ed[2].target.val('');
			}
			
		});
		
		ed[3].target.click(function (){
			var valueFour=ed[3].target.val();
			ed[3].target.select();
			if(valueFour==null || valueFour==''|| valueFour==undefined){
				ed[3].target.val('√');
				ed[0].target.val('');
				ed[2].target.val('');
				ed[1].target.val('');
				ed[4].target.val('');
				return;
			}else{
				ed[3].target.val('');
			}
			
		});
		
		ed[4].target.click(function (){
			var valueFive=ed[4].target.val();
			ed[4].target.select();
			if(valueFive==null || valueFive==''|| valueFive==undefined){
				ed[4].target.val('√');
				ed[0].target.val('');
				ed[2].target.val('');
				ed[1].target.val('');
				ed[3].target.val('');
				return;
			}else{
				ed[4].target.val('');
			}
			
		});
	
	}
	});
	// 跟踪管理历史满意度
	$customer_genz2 = $('#customer_genz2');
	$customer_genz2.datagrid( {
		pagination : true,
		fit : true,
		newrap : false,
		striped : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [ {
			field : 'returnVisitDate',
			title : '回访日期',
			width : 100
		}, {
			field : 'satisfaction',
			title : '满意度',
			hidden:true
		},{
			field : 'satisfactionName',
			title : '满意度',
			width : 100
		}, {
			field : 'complaintContent',
			title : '信息反馈',
			width : 100
		} ] ]
	});

});
//双击行时触发此方法
function Dbclick(rowData){
	$('#tt').tabs('select',1);
	$('#CustomerGzManage_form_south_id').form('load', rowData);
	$('#GD_WeiXiu_detail_form_id').form('load', rowData);
	$('#userReturn_infomation_form_id').form('load', rowData);
	$('#GD_WeiXiu_detail_form').form('load', rowData);
	$('#customer_genz2').datagrid({
		url : 'customerGzManageAction_doFindCollect.action',
		queryParams: {carId:rowData.carId}
	});
	$('#customer_genz3').datagrid({
		url : 'customerGzManageAction_doFindCarDcname.action',
		queryParams: {collectId:rowData.collectId}
	});
}

function updateCollect(){
	var data = $('#customer_genz1').datagrid('getSelected');
	//CustomerGzManage_form_south_id;
	//userReturn_infomation_form_id
	//GD_WeiXiu_detail_form
	//customer_genz
	if(data){
		$('#tt').tabs('select',1);
		Dbclick(data);
		//增加保存取消按钮
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="submitCollect();">保存</a>';
		var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#saveOrCancelBtn').children('a').length == 0) {
			$('#saveOrCancelBtn').append(save).append(cancel);
			$.parser.parse('#saveOrCancelBtn');
		}
		
	}else{
		alertMsg('对不起，请先选中要修改的记录！', 'warning');
		}
	}

function submitCollect(){
	//endEdit($("#customer_genz3"));
	var rows=$("#customer_genz3").datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		$("#customer_genz3").datagrid('endEdit',i);  
	}
    var effectRow = new Object();
    effectRow['collectInformation'] = JSON.stringify(serializeObject($('#CustomerGzManage_form_south_id')));
    effectRow['fbkDetail'] = JSON.stringify(serializeObject($('#userReturn_infomation_form_id')));
    effectRow['fbkDetail1'] = JSON.stringify(serializeObject($('#GD_WeiXiu_detail_form')));
    effectRow['visit3Dc'] = JSON.stringify(rows);
    if($('#CustomerGzManage_form_south_id').form('validate')){
					$.ajax({
					   type: 'post',
					   dataType: 'json',
					   url: 'customerGzManageAction!updateFbkCollect.action',
					   data: effectRow,
					   success: function(r){
						 if(r.success){
							 cancel();
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
function cancel(){
	$('#tt').tabs('select',0);
	$('#saveOrCancelBtn').empty();
	$('#customer_genz1').datagrid('unselectAll');
	 $('#customer_genz1').datagrid('reload');
}

function deleteCollect(){
	var data = $('#customer_genz1').datagrid('getSelected');
	if(data){
		$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r){

			$.ajax({
			   type: 'post',
			   dataType: 'json',
			   url: 'customerGzManageAction!deleteFbkCollect.action',
			   data: data,
			   success: function(r){
				 if(r.success){
					 cancel();
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
		});
}else{
		alertMsg('对不起，请先选中要删除的记录！', 'warning');
	}	
}

function doFind(){
	
}
function addAdvice(){
	var data = $('#customer_genz1').datagrid('getSelected');
	if (data) {
		var d = $('<div/>').dialog({
							modal : true,
							closable : true,
							title : '增加',
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
															 $('#customer_genz1').datagrid('reload');
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
								$("#sresvId").val(data.resvId);
							}
						});
	} else {
		alertMsg('对不起，请先选中要增加的记录！', 'warning');
	}
}