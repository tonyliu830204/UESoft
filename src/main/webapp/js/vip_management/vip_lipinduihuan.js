var enterAdd = 0;
var enterEdit = 0;
var tbtitle = '礼品兑换汇总';
$(function(){
	//积分赠送汇总
	$('#datagrid_vip_lipinduihuan_id').datagrid({
		url : projectPath+'giftExchangeManagementAction!getGiftExchangeInfo.action',
		fit : true,
		fitColumns : false,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'exchange_Id',
		sortOrder:'asc',
		idField : 'exchange_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'exchange_Id',
				title : '兑换单号',
				hidden : true
			},{
				field : 'vip_Id',
				title : '会员编号',
				hidden : true
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
				width : 85,
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
				field : 'exchange_Date',
				title : '兑换日期',
				width : 130,
				sortable : true
			},{
				field : 'audit_Situation',
				title : '审核情况key',
				hidden : true
			},{
				field : 'audit_SituationName',
				title : '审核情况',
				width :70,
				sortable : true
			},{
				field : 'audit_Date',
				title : '审核日期',
				width : 130,
				sortable : true
			},{
				field : 'audit_Manager',
				title : '审核经办人id',
				hidden : true
			},{
				field : 'audit_ManagerName',
				title : '审核经办人',
				width :70,
				sortable : true
			},{
				field : 'exchange_User',
				title : '领用人',
				width :70,
				sortable : true
			},{
				field : 'Amount',
				title : '领用礼品总金额',
				hidden : true
			},{
				field : 'total_Score',
				title : '兑换积分',
				width :70,
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
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			datagridToForm(rowData);
		}	
	});
	
	//待选积分赠送项目
	$('#Present_exchange_management_id').datagrid({
		url : projectPath+'giftExchangeManagementAction!getExchangeable.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : 50,
		pageList : [ 50, 60, 70, 80, 90, 100],
		idField : 'project_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'project_Id',
				title : '礼品编码',
				hidden : true
			},{
				field : 'project_Name',
				title : '礼品名称',
				width : 280,
				sortable : true
			},{
				field : 'project_score',
				title : '礼品积分',
				width : 250,
				sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			//判断是否是点击了修改，否则不可添加行
			if(enterEdit==1 || enterAdd ==1){
				if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
					var datagridId = $('#Present_exchange_project_list');
					var rows = datagridId.datagrid('getRows');
					if(rows.length>0){
						for(var i = 0;  i < rows.length; i++){
							if(rows[i].project_Id == rowData.project_Id){
								alertMsg('抱歉，礼品【'+rows[i].project_Name+'】信息已经被选取！', 'warning');
								return;
							}
						}
					}
					//$(this).datagrid('deleteRow', rowIndex);
					datagridId.datagrid('appendRow',rowData);
					dateAndBindEvent(datagridId, rowData);
				}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
			}
		}
	});
	
	
	//已选积分赠送项目    
	$('#Present_exchange_project_list').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		idField : 'project_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'exchange_Detail_Id',
				title : '兑换明细编号',
				hidden : true
			},{
				field : 'project_Id',
				title : '礼品编码',
				hidden : true
			},{
				field : 'project_Name',
				title : '礼品名称',
				width : 80
			},{
				field : 'project_score',
				title : '兑换积分',
				width : 40,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true
					}
				}
			},{
				field : 'exchange_Quantity',
				title : '兑换数量',
				width : 40,
				sortable : true,
				editor : {
					type : 'numberbox',
					options : {
						required : true,
						missingMessage : '请输入兑换数量'
					}
				}
			},{
				field : 'total_Score',
				title : '所需积分',
				width : 40,
				sortable : true,
				editor : {
					type : 'numberbox',
					options : {
						disabled : true
					}
				}
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
		    if(enterEdit==1 || enterAdd ==1){
		    	if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
				    $(this).datagrid('deleteRow', rowIndex);
				    $('#Present_exchange_management_id').datagrid('appendRow', rowData);
		    	}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
		    }
		},
		onClickRow : function(rowIndex,rowData){
			if(enterEdit==1 || enterAdd ==1){
				if($("#vip_Number").val() != null && $("#vip_Number").val() != ""){
					$(this).datagrid('beginEdit',rowIndex);
					var edits = $(this).datagrid('getEditors', rowIndex);
					edits[1].target.select();
					edits[1].target.bind('blur', function() {
						var stoce = edits[0].target.val();
						var num = edits[1].target.val();
						edits[2].target.numberbox('setValue', parseFloat(stoce * num).toFixed(2));
						datagridId.datagrid('endEdit', rowIndex);
						datagridId.datagrid('acceptChanges');
					});
				}else{
					alertMsg('对不起，请刷会员卡！', 'warning');
				}
			}
		}
	});
	
	$('#tab_lipingduihuan_id').tabs({
		border:false,
		onSelect:function(title){
		    tbtitle = title;
		}
	});
});
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

//点击读卡读取会员信息
function getVipInfoByVipNO(){
	var formId = $('#form_Present_exchange_vipinfor');
	var form =  formId.form();
	var formvalue = serializeObject(form);
	$.ajax({
		type : 'POST',
		url : projectPath+'VipRechargeAction!readCard.action',
		data : formvalue,
	    dataType : 'json',
		success : function(data){
			if(data.success){
			    formId.form('clear');
				formId.form('load', data.obj[0]);
		    }else{
		    	alertMsg(data.msg, 'warning');
		    }
		}
	});
}

//点击新增按钮时触发显示 新增和取消按钮   
function dbAdd(){
	if(enterEdit==0){
		enterAdd = 1;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveOrUpdate(1);">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#addbutton').children('a').length == 0) {
				$('#addbutton').append(save).append(cancel);
				$.parser.parse('#addbutton');
		}
		$('#tab_lipingduihuan_id').tabs('select','礼品兑换明细');
		$('#form_Present_exchange_vipinfor').form('clear');
		$('#vip_Number').focus();
		$('#Present_exchange_management_id').datagrid("load");
		$('#Present_exchange_project_list').datagrid('loadData',{"rows":[],"total":0});
		disable();
	}else{
		alertMsg('对不起，请先完成当前修改操作！', 'warning');
	}
}

//点击修改按钮时触发
function dbEdit(){
	if(enterAdd==0){
		enterEdit = 1;
		var value = $('#datagrid_vip_lipinduihuan_id').datagrid('getSelections');
		if(value.length==0){
			alertMsg('对不起，请先选择要修改的记录！', 'warning');
			return;
		}else if(value[0].audit_Situation == sate){
			alertMsg('对不起，该记录已审核不可修改！', 'warning');
			return;
		}else{
			var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveOrUpdate(2);">保存</a>';
			var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
			if ($('#editbutton').children('a').length == 0) {
				$('#editbutton').append(save).append(cancel);
				$.parser.parse('#editbutton');
			}
			datagridToForm(value[0]);
			disable();
		}
	}else{
			alertMsg('对不起，请先完成当前新增操作！', 'warning');
	}
}

function doSaveOrUpdate(a){
	var url = '';
	var score =0;
	var datagridId = $('#Present_exchange_project_list');
	if(a==1){url=projectPath+'giftExchangeManagementAction!doAdd.action';}
	if(a==2){var vip_Id = $('#vip_Id').val();url=projectPath+'giftExchangeManagementAction!doUpdate.action?vip_Id='+vip_Id;}
	//获取文本框当前积分数
	var vt =  parseFloat($('#vip_Integral_id').val());
	//获取会员卡号
	var vipnumber = $('#vip_Number').val();
	//判断是否有会员信息
	if(vipnumber != ""){
		var rowValue = datagridId.datagrid('getData');
		if(rowValue.rows.length > 0){
			for(var i=0; i < rowValue.rows.length;i++){
				datagridId.datagrid('endEdit',i);
				if(rowValue.rows[i].total_Score!=null && rowValue.rows[i].total_Score != ""){
					score = score + parseFloat(rowValue.rows[i].total_Score.toString());
				}
			}
		}
		//判断兑换所需积分 是否超出当前积分
		if(score <= vt){
			var effectRow = datagridId.datagrid('getData');
			$('#projectItem').val(JSON.stringify(rowValue));
			if(effectRow){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: url,
				   data: serializeObject($("#form_Present_exchange_vipinfor")),
				   success: function(r){
					   alertMsg(r.msg, 'info');
					   if(r.success){
						    $('#Present_exchange_project_list').datagrid('acceptChanges');
						    $('#datagrid_vip_lipinduihuan_id').datagrid('reload');
							//保存成功后隐藏保存按钮
							$('#addbutton').empty();
							$('#editbutton').empty();
							$('#tab_lipingduihuan_id').tabs('select','礼品兑换汇总');
							//保存成功后将是否是修改的标示 改为0
							enterEdit = 0;
							enterAdd = 0;
							enable();
					   }
				   }
				});
			}
		}else{
			alertMsg('对不起，当前积分不足！', 'warning');
		}
	}else{
		alertMsg('对不起，无相关会员信息！请刷会员卡', 'warning');
	}
}

//取消
function cancel(){
	$('#addbutton').empty();
	$('#editbutton').empty();
	$('#tab_lipingduihuan_id').tabs('select','礼品兑换汇总');
	$('#Present_exchange_project_list').datagrid('rejectChanges');
	enterEdit = 0;
	enterAdd = 0;
	enable();
}

function dateAndBindEvent(datagridId,rowData)
{
	var rowIndex = datagridId.datagrid('getRowIndex', rowData);
	datagridId.datagrid('beginEdit', rowIndex);
	var ed = datagridId.datagrid('getEditors', rowIndex);
	if(ed[1]){
		if(rowData.exchange_Quantity==null||rowData.exchange_Quantity==''){
			ed[1].target.numberbox('setValue', '1');
			ed[2].target.numberbox('setValue', rowData.project_score);
		}else{
			ed[1].target.numberbox('setValue', rowData.exchange_Quantity);
			ed[2].target.numberbox('setValue', parseFloat(rowData.project_score * rowData.exchange_Quantity).toFixed(2));
		}
		ed[1].target.select();
		ed[1].target.click(function (){
			ed[1].target.select();
		});
	}
	ed[1].target.bind('blur', function() {
		var stoce = ed[0].target.val();
		var num = ed[1].target.val();
		ed[2].target.numberbox('setValue', parseFloat(stoce * num).toFixed(2));
		datagridId.datagrid('endEdit', rowIndex);
		datagridId.datagrid('acceptChanges');
	});
}

//双击行时 将积分汇总信息付给 明细的form表单 
function datagridToForm(rowData){
	$('#tab_lipingduihuan_id').tabs('select','礼品兑换明细');
	$('#form_Present_exchange_vipinfor').form('load', rowData);
	$('#Present_exchange_project_list').datagrid('loadData',{"rows":[],"total":0});
	$('#Present_exchange_management_id').datagrid("load");
	$('#Present_exchange_project_list').datagrid({url : projectPath+'giftExchangeManagementAction!getGiftExchangeDetail.action?exchange_Id='+rowData.exchange_Id});
}

//礼品兑换 审核
function doAudit(){
	var msg = "请确认是否要审核该条记录？";
	var rowvalues = $('#datagrid_vip_lipinduihuan_id').datagrid('getSelections');
	if(rowvalues.length>0){
		if(rowvalues[0].audit_Situation == sate){
			alertMsg('对不起，该礼品兑换单已经被审核，不允许再操作！', 'warning');
  			return;
	    }else{
			$.messager.confirm('优亿软件提示',msg,function(b){
				if(b){
					$.ajax({
						type : 'POST',
						url : projectPath+'giftExchangeManagementAction!doAudit.action',
					    data : rowvalues[0],
					    dataType : 'json',
						success : function(r){
						   alertMsg(r.msg, 'info');
						   if(r.success){
							    $('#datagrid_vip_lipinduihuan_id').datagrid('reload');
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

//删除礼品兑换汇总信息
function doRemove(){
	var value = $('#datagrid_vip_lipinduihuan_id').datagrid('getSelections');
	if(value.length==0){
	    alertMsg('对不起，请先选择要删除的记录！', 'warning');
	    return;
	}else if(value[0].audit_Situation==sate){
		alertMsg('对不起，该记录已审核不可删除！', 'warning');
		return;
	}else{
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
					type : 'POST',
					url : projectPath+'giftExchangeManagementAction!doDelete.action',
					data : value[0],
					dataType : 'json',
					success : function(r){
					alertMsg(r.msg, 'info');
						if(r.success){
							$('#datagrid_vip_lipinduihuan_id').datagrid('reload');
						}
					}
				});
			}
		});
	}
}

//清空按钮
function doClear(){
	if(tbtitle=='礼品兑换汇总'){
		$('#form_vip_lipinduihuan_id').form('clear');
		$('#datagrid_vip_lipinduihuan_id').datagrid('load',serializeObject($("#form_vip_lipinduihuan_id")));
	}
	if(tbtitle=='礼品兑换明细'){
		$('#form_Present_exchange_condition_id').form('clear');
		$('#Present_exchange_management_id').datagrid('load',serializeObject($('#form_Present_exchange_condition_id')));
	}
}

//条件查询提交doConditionSubmit
function submitByCondition(){
	if(tbtitle=='礼品兑换汇总'){
		$('#datagrid_vip_lipinduihuan_id').datagrid('load',serializeObject($("#form_vip_lipinduihuan_id")));
	}
	if(tbtitle=='礼品兑换明细'){
		$('#Present_exchange_management_id').datagrid('load',serializeObject($('#form_Present_exchange_condition_id')));
	}	
}

function _exception(){
	showEditDialog("datagrid_vip_lipinduihuan_id",null,"datagrid_vip_lipinduihuan_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"礼品兑换"+getSystemTime());
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