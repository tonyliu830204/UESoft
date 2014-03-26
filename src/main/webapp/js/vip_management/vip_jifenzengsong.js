//定义标示 是否是点击修改查看的明细 如果不是则不能修改
var enterEdit = 0;
var enterAdd = 0; 
$(function(){
	//积分赠送汇总
	$('#Give_integral_project_management_id').datagrid({
		url : projectPath+'vipScorePresentManagementAction!getHzGiveIntegral.action',
		fit : true,
		fitColumns : false,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'give_Inte_Id',
		sortOrder:'asc',
		idField : 'give_Inte_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'give_Inte_Id',
				title : '赠送单号',
				hidden : true
			},{
				field : 'give_Inte_Date',
				title : '赠送日期',
				width :130,
				sortable : true
			},{
				field : 'shenhe_Qingkuang',
				title : '审核情况key',
				hidden : true
			},{
				field : 'shenhe_QingkuangName',
				title : '审核情况',
				width :70,
				sortable : true
			},{
				field : 'shenhe_Riqi',
				title : '审核日期',
				width :130,
				sortable : true
			},{
				field : 'shenhe_Yuan',
				title : '审核经办id',
				hidden : true
			},{
				field : 'shenhe_YuanName',
				title : '审核经办',
				width :70,
				sortable : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width : 70,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width : 130,
				sortable : true
			},{
				field : 'vip_Name',
				title : '会员名称',
				width : 100,
				sortable : true
			},{
				field : 'vip_Birthday',
				title : '出生年月',
				width : 130,
				sortable : true
			},{
				field : 'vip_Tel',
				title : '联系电话',
				width : 90,
				sortable : true
			},{
				field : 'join_Time',
				title : '入会日期',
				width : 130,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width : 100,
				sortable : true
			},{
				field : 'vip_Level_Name',
				title : '会员等级',
				width : 80,
				sortable : true
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width : 80,
				sortable : true
			},{
				field : 'vip_Status',
				title : '会员卡状态key',
				hidden : true
			},{
				field : 'vip_Status_value',
				title : '会员卡状态',
				width : 80,
				sortable : true
			},{
				field : 'end_Time',
				title : '会员到期',
				width : 130,
				sortable : true
			},{
				field : 'vip_Balance',
				title : '卡内余额',
				width : 85,
				sortable : true
			},{
				field : 'vip_Integral',
				title : '可用积分',
				width : 85,
				sortable : true
			},{
				field : 'vip_Total_Integral',
				title : '累计积分',
				width : 85,
				sortable : true
			},{
				field : 'give_Inte_Num',
				title : '本次赠送积分',
				width : 85,
				sortable : true
			},{
				field : 'operator',
				title : '经办人id',
				hidden : true
			},{
				field : 'operatorName',
				title : '经办人',
				width :70,
				sortable : true
			},{
				field : 'give_Inte_Note',
				title : '赠分备注',
				width : 100,
				sortable : true
			},{
				field : 'vip_Id',
				title : '会员编号',
				hidden : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			datagridToForm(rowData);
		}	
	});
	
	//待选积分赠送项目
	$('#Give_integral_project_list').datagrid({
		url : projectPath+'vipScorePresentManagementAction!getBasVipGiveIntegralProject.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		idField : 'give_Inte_Pro_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'give_Inte_Pro_Id',
				title : '编号',
				hidden : true
			},{
				field : 'give_Inte_Pro_Name',
				title : '积分赠送项目',
				width : 300,
				sortable : true
			},{
				field : 'give_Inte_Num',
				title : '赠送积分',
				width : 300,
				sortable : true
			},{
				field : 'shiji_Zengfen',
				title : '实际赠分',
				hidden : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			//判断是否是点击了修改，否则不可添加行
			if(enterEdit==1 || enterAdd ==1){
				if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
					var rows = $('#Give_integral_project_list2').datagrid('getRows');
					if(rows.length>0){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].give_Inte_Pro_Id == rowData.give_Inte_Pro_Id){
								$(this).datagrid('deleteRow', rowIndex);
								return;
							}
						}
					}
					$(this).datagrid('deleteRow', rowIndex);
					$('#Give_integral_project_list2').datagrid('appendRow',rowData);
				
					//每双击一行 获取赠送积分数 相加
					var value = $('#Give_integral_project_list2').datagrid('getData');
					var sum = 0;
					for(var i=0; i < value.rows.length;i++){
						sum = sum + parseFloat(value.rows[i].shiji_Zengfen.toString());
					}
					$('#giv_Inte_Num').val(""+sum+"");
				}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
			}
		}
	});
	
	
	//已选积分赠送项目    
	$('#Give_integral_project_list2').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		idField : 'give_Inte_Pro_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'give_Inte_Pro_Id',
				title : '编号',
				hidden : true
			} ,{
				field : 'give_Inte_Pro_Name',
				title : '积分赠送项目',
				width : 300
			} ,{
				field : 'give_Inte_Num',
				title : '赠送积分',
				width : 300
			},{
				field : 'shiji_Zengfen',
				title : '实际赠分',
				width : 300
			},{
				field : 'give_Inte_Id',
				title : '积分增送单号',
				hidden : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			if(enterEdit==1 || enterAdd==1){
				if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
				    var rows = $('#Give_integral_project_list').datagrid('getRows');
					if(rows.length>0){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].give_Inte_Pro_Id == rowData.give_Inte_Pro_Id){
								$(this).datagrid('deleteRow', rowIndex);
								return;
							}
						}
					}
					$(this).datagrid('deleteRow', rowIndex);
					$('#Give_integral_project_list').datagrid('appendRow', rowData);
					
					//每双击一行 获取赠送积分数 相减
					var value = $('#Give_integral_project_list2').datagrid('getData');
					var sum = 0;
					for(var i=0; i < value.rows.length;i++){
							sum = sum + parseFloat(value.rows[i].shiji_Zengfen.toString());
						
					}
					$('#give_Inte_Num').val(""+sum+"");
				}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
			}
	    },
		onClickRow : function(rowIndex, rowData){
			if(enterEdit==1){
				if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
				    $('#Give_integral_project_list2').datagrid('beginEdit',rowIndex);
				}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
			}
		}
	});
	
});

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

//点击读卡读取会员信息
function getVipInfoByVipNO(){
	var formId = $('#form_Give_inte_project_list_detail');
	var form =  formId.form();
	var formvalue = serializeObject(form);
	$.ajax({
		type : 'POST',
		url : projectPath+'VipRechargeAction!readCard.action',
		data : formvalue,
	    dataType : 'json',
		success : function(data){
		    formId.form('clear');
			if(data.success){
				formId.form('load', data.obj[0]);
			}else{
				alertMsg(data.msg, 'warning');
			}
		}
	});
}

//点击新增按钮时触发显示 新增和取消按钮
function dbAdd(){
	//判断其他操作是否已完成
	if(enterEdit==0){
		enterAdd = 1;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveChoicedProject(1);">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#addbutton').children('a').length == 0) {
				$('#addbutton').append(save).append(cancel);
				$.parser.parse('#addbutton');
		}
		//选中积分增送明细项
		$('#tid').tabs('select','积分赠送明细');
		//如果是新增的话 先清空已选datagrid
		$('#Give_integral_project_list2').datagrid('loadData', { total: 0, rows: [] });
		$('#form_Give_inte_project_list_detail').form('clear');
		disable();
	}else{
		alertMsg('对不起，请先完成当前修改操作！', 'warning');
		return;
	}
}

//点击修改按钮式触发
function dbEdit(){
//判断是否完成了新增操作 否则不再添加按钮
	if(enterAdd==0){
		enterEdit = 1;
		var value = $('#Give_integral_project_management_id').datagrid('getSelections');
		if(value.length==0){
			alertMsg('对不起，请先选择要修改的记录！', 'warning');
			return;
		}else if(value[0].shenhe_Qingkuang != sate){
			if(value[0].give_Inte_Id != null){
				var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveChoicedProject(2);">保存</a>';
				var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
				if ($('#editbutton').children('a').length == 0) {
						$('#editbutton').append(save).append(cancel);
						$.parser.parse('#editbutton');
				}
				datagridToForm(value[0]);
				disable();
			}
		}else{
			alertMsg('对不起，该记录已审核不可修改！', 'warning');
			return;
		}
	}else{
		alertMsg('对不起，请先完成当前新增操作！', 'warning');
		return;
	}
}

//取消
function cancel(){
	$('#addbutton').empty();
	$('#editbutton').empty();
	$('#tid').tabs('select','积分赠送汇总');
	enterEdit = 0;
	enterAdd = 0;
	$('#Give_integral_project_list2').datagrid('loadData', { total: 0, rows: [] });
	enable();
}

//保存赠送积分到相应的单号下面
function doSaveChoicedProject(a){
	var vip_Number = $('#vip_Number').val();
	if(vip_Number != null && "" != vip_Number){
		var sum = 0;
		var rowValue = $('#Give_integral_project_list2').datagrid('getData');
		var  shiji_Zengfen = "";
		$('#projectItem').val(JSON.stringify(rowValue));
		for(var i=0; i < rowValue.rows.length;i++){
			$('#Give_integral_project_list2').datagrid('endEdit',i);
			if(rowValue.rows[i].shiji_Zengfen!=null && rowValue.rows[i].shiji_Zengfen != ""){
				sum = sum + parseFloat(rowValue.rows[i].shiji_Zengfen.toString());
			}else{
				sum = sum + parseFloat(rowValue.rows[i].give_Inte_Num.toString());
			}
		}
		$('#sum').val(sum);
		var url = '';
		if(a==1){
			url = projectPath+'vipScorePresentManagementAction!doAddRelationTable.action';
		}
		if(a==2){
			url = projectPath+'vipScorePresentManagementAction!doUpdateRelationTable.action';
		}
		$.ajax({
			type : 'POST',
			url : url,
		    data : serializeObject($('#form_Give_inte_project_list_detail')),
		    dataType : 'json',
			success : function(r){
			    alertMsg(r.msg, 'info');
			    if(r.success){
					$('#Give_integral_project_management_id').datagrid('load',serializeObject($('#form_Give_inte_project_list')));
					$('#tid').tabs('select','积分赠送汇总');
					$('#addbutton').empty();
					$('#editbutton').empty();
					enterEdit = 0;
					enterAdd = 0;
					enable();
				}
			}
		});
	}else{
		alertMsg('对不起，记录无法保存，无相关会员信息！', 'warning');
	}
}

//双击行时 将积分汇总信息付给 明细的form表单 
function datagridToForm(rowData){
	$('#tid').tabs('select','积分赠送明细');
	$('#form_Give_inte_project_list_detail').form('clear');
	$('#form_Give_inte_project_list_detail').form('load', rowData);
	$('#Give_integral_project_list2').datagrid({url : projectPath+'vipScorePresentManagementAction!getGiveIntegralByVipId.action?give_Inte_Id='+rowData.give_Inte_Id});
}

//删除积分增送记录
function doRemove(){
	var value = $('#Give_integral_project_management_id').datagrid('getSelections');
	if(value.length==0){
		alertMsg('对不起，请先选择要删除的记录！', 'warning');
		return;
	}else if(value[0].shenhe_Qingkuang != sate){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
					type : 'POST',
					url : projectPath+'vipScorePresentManagementAction!doRemove.action',
					data : value[0],
				    dataType : 'json',
					success : function(r){
					    alertMsg(r.msg, 'info');
				        if(r.success){
					         $('#Give_integral_project_management_id').datagrid('load',serializeObject($('#form_Give_inte_project_list')));
				        }
					}
			   });
			}
		});
	}else{
		alertMsg('对不起，该记录已审核不可删除！', 'warning');
		return;
	}
}

//点击审核时将积分赠送的单号传给action 根据单号将未审核的记录改为已审核
function doShenhe(){
	var msg = "请确认是否要审核该条记录？";
	var rowvalues = $('#Give_integral_project_management_id').datagrid('getSelections');
	if(rowvalues.length>0){
		if(rowvalues[0].shenhe_Qingkuang == sate){
			alertMsg('对不起，该积分赠送单已经被审核，不允许再操作！', 'warning');
			return;
	    }else{
			$.messager.confirm('优亿软件提示',msg,function(b){
				if(b){
					$.ajax({
						type : 'POST',
						url : projectPath+'vipScorePresentManagementAction!doShenhe.action',
					    data : rowvalues[0],
					    dataType : 'json',
						success : function(r){
							alertMsg(r.msg, 'info');
					        if(r.success){
						        $('#Give_integral_project_management_id').datagrid('reload');
					        }
						}
				   });
				}
			});
	    }
	}else{
		alertMsg('对不起，请先选中要审核的记录！', 'warning');
		return;
	}
}

//条件查询提交doConditionSubmit
function doSubmitByCondition(datagridId,formid,url1){
	var form =  formid.form();
	var formvalue = serializeObject(form);
	datagridId.datagrid('load',formvalue);
}

function doClear(){
	$('#form_Give_inte_project_list').form('clear');
    $('#Give_integral_project_management_id').datagrid('load',serializeObject($('#form_Give_inte_project_list')));
}

function _exception(){
	showEditDialog("Give_integral_project_management_id",null,"Give_integral_project_management_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"会员积分赠送"+getSystemTime());
}

function disable(){
	$('#addBut').linkbutton('disable', true);
	$('#removeBut').linkbutton('disable', true);
	$('#editBut').linkbutton('disable', true);
	$('#searchBut').linkbutton('disable', true);
	$('#clearBut').linkbutton('disable', true);
	$('#exportBut').linkbutton('disable', true);
	$('#examineBut').linkbutton('disable', true);
}

function enable(){
	$('#addBut').linkbutton('enable', true);
	$('#removeBut').linkbutton('enable', true);
	$('#editBut').linkbutton('enable', true);
	$('#searchBut').linkbutton('enable', true);
	$('#clearBut').linkbutton('enable', true);
	$('#exportBut').linkbutton('enable', true);
	$('#examineBut').linkbutton('enable', true);
}