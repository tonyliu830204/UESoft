var dbadd = 0;
var dbedit = 0;
var vipUpTitle = '';

$(function(){
	//会员卡升级汇总
	$('#datagrid_vip_shengji_id').datagrid({  
		url : projectPath+'vipCardUpgradeAction!doVipUpCollectFind.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		sortName:'upgrade_Id',
		sortOrder:'asc',
		idField : 'upgrade_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'upgrade_Id',
			title : '升级单号',
			width :100,
			sortable : true
		},{
			field : 'upgrade_Date',
			title : '升级日期',
			width :70,
			sortable : true
		},{
			field : 'managers',
			title : '经办人id',
			hidden : true
		},{
			field : 'managersName',
			title : '经办人',
			width :70,
			sortable : true
		},{
			field : 'audit_Situation',
			title : '审核情况key',
			hidden : true
		},{
			field : 'audit_SituationValue',
			title : '审核情况',
			width : 110,
			sortable : true
		},{
			field : 'audit_Date',
			title : '审核日期',
			width : 110,
			sortable : true
		},{
			field : 'audit_Managers',
			title : '审核经办id',
			hidden : true
		},{
			field : 'audit_ManagersName',
			title : '审核经办',
			width : 110,
			sortable : true
		}
		]],
		onDblClickRow : function(rowIndex, rowData){
		    datagridToForm(rowData);
		}	
	});
	
	//会员升级明细  待选列表
	$('#huiyuan_shengji_daixuan_liebiao_id').datagrid({
		url : projectPath+'vipCardUpgradeAction!doFindVipUpInfo.action',
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipId',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
				field : 'vip_Id',
				title : '会员编号',
				hidden : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width :150,
				sortable : true
			},{
				field : 'vip_name',
				title : '会员姓名',
				width :150,
				sortable : true
			},{
				field : 'car_License',
				title : '车辆牌照',
				width :150,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width :150,
				sortable : true
			},{
				field : 'oldVip_Level_Id',
				title : '会员等级id',
				hidden : true 
			},{
				field : 'oldVip_Level_Name',
				title : '会员等级',
				width :150,
				sortable : true
			},{
				field : 'vip_Group_Id',
				title : '会员组id',
				hidden : true 
			},{
				field : 'vip_Group_Name',
				title : '会员分组',
				width :150,
				sortable : true
			},{
				field : 'vip_Status',
				title : '会员状态key',
				hidden : true 
			},{
				field : 'vip_StatusName',
				title : '会员状态',
				width :150,
				sortable : true
			},{
				field : 'vip_Integral',
				title : '可用积分',
				width :150,
				sortable : true
			},{
				field : 'vip_Total_Integral',
				title : '累计积分',
				width :150,
				sortable : true
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
		    if(dbedit==1 || dbadd==1){
		    	var datagridId = $('#huiyuan_shengji_yixuan_liebiao_id');
				var rows = datagridId.datagrid('getRows');
				if(rows.length>0){
					for(var i = 0;  i < rows.length; i++){
						if(rows[i].vip_Id == rowData.vip_Id){
							return;
						}
					}
				}
				datagridId.datagrid('appendRow', rowData);
				//$(this).datagrid('deleteRow', rowIndex);
				dateAndBindEvent(datagridId, rowData);
			}
			
	}});
	
	//会员升级 已选列表 
	$('#huiyuan_shengji_yixuan_liebiao_id').datagrid({
		url : '',
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'upgrade_detail_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
				field : 'upgrade_detail_Id',
				title : '编号',
				hidden : true 
			},{
				field : 'upgrade_Id',
				title : '汇总编号',
				hidden : true 
			},{
				field : 'car_License',
				title : '车辆牌照',
				width :150,
				sortable : true
			},{
				field : 'car_Vin',
				title : 'VIN号',
				width :150,
				sortable : true
			},{
				field : 'vip_Number',
				title : '会员卡号',
				width :150,
				sortable : true
			},{
				field : 'vip_name',
				title : '会员姓名',
				width :150,
				sortable : true
			},{
				field : 'oldVip_Level_Id',
				title : '会员等级id',
				hidden : true 
			},{
				field : 'oldVip_Level_Name',
				title : '会员等级',
				width :150,
				sortable : true
			},{
				field : 'newVip_Level_Id',
				title : '会员卡升级',
				width :150,
				sortable : true,
				editor : {
					type : 'combobox',
					options : {
						url : projectPath+'VipRecordMessageAction!getBasVipLevel.action',
						required : true,
						valueField:'id',  
					    textField:'name'
					}
				},
				formatter : function (value, row, index){
					return row.newVip_Level_Name;
				}
			},{
				field : 'deduction_Integration',
				title : '扣除积分',
				width :150,
				sortable : true,
				editor :{
					type : 'numberbox',
					options :{
						required : false
				    }
				}
			},{
				field : 'gift_Points',
				title : '赠送积分',
				width :150,
				sortable : true,
				editor :{
					type : 'numberbox',
					options :{
						required : false
					}
				}
			},{
				field : 'vip_Integral',
				title : '可用积分',
				width :150,
				sortable : true
			},{
				field : 'vip_Total_Integral',
				title : '累计积分',
				width :150,
				sortable : true
			},{
				field : 'vip_Id',
				title : '会员编号',
				hidden : true 
			}
		]],
		onDblClickRow : function(rowIndex, rowData){
			//判断是否是新增 如果 是 则开启此功能 否则不开启
		    if(dbedit==1 || dbadd==1){
				var rows = $('#huiyuan_shengji_daixuan_liebiao_id').datagrid('getRows');
				if(rows.length>0){
					for(var i = 0;  i < rows.length; i++){
						if(rows[i].vip_Id == rowData.vip_Id){
							$(this).datagrid('deleteRow', rowIndex);
							return;
						}
					}
				}
				$(this).datagrid('deleteRow', rowIndex);
				$('#huiyuan_shengji_daixuan_liebiao_id').datagrid('appendRow', rowData);
		    }
		},
		onClickRow : function(rowIndex, rowData){
			if(dbedit==1 || dbadd==1){
			    $(this).datagrid('beginEdit',rowIndex)
			}
		},
		onLoadSuccess : function(data){
			if(dbedit == 1){
				$(this).datagrid('beginEdit', 0);
			}
		}
	});
	
	//会员卡升级
	$('#tab_vip_up_id').tabs({
		onSelect:function(title){
		  vipUpTitle = title;
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
//条件查询提交doConditionSubmit
function submitByCondition(){
	if(vipUpTitle=='会员卡升级汇总'){
		$('#datagrid_vip_shengji_id').datagrid('load',serializeObject($('#form_datagrid_vip_shengji_id')));
	}
	if(vipUpTitle=='会员卡升级明细'){
		$('#huiyuan_shengji_daixuan_liebiao_id').datagrid('load',serializeObject($('#form_datagrid_vip_shengji_detial_id')));
	}
}

//清空按钮
function doClear(){
	if(vipUpTitle=='会员卡升级汇总'){
		$('#form_datagrid_vip_shengji_id').form('clear');
		$('#datagrid_vip_shengji_id').datagrid('load',serializeObject($('#form_datagrid_vip_shengji_id')));
	}
	if(vipUpTitle=='会员卡升级明细'){
		$('#form_datagrid_vip_shengji_detial_id').form('clear');
		$('#huiyuan_shengji_daixuan_liebiao_id').datagrid('load',serializeObject($('#form_datagrid_vip_shengji_detial_id')));
	}
}

//双击行时 将积分汇总信息付给 明细的form表单 
function datagridToForm(rowData){
	$('#tab_vip_up_id').tabs('select','会员卡升级明细');
	var datagrid = $('#huiyuan_shengji_yixuan_liebiao_id');
	datagrid.datagrid('loadData', { total: 0, rows: [] });
	datagrid.datagrid({url : projectPath+'vipCardUpgradeAction!findInfoByVipUpId.action?upgrade_Id='+rowData.upgrade_Id});
}

function dateAndBindEvent(datagridId,rowData)
{
	var rowIndex = datagridId.datagrid('getRowIndex', rowData);
	datagridId.datagrid('beginEdit', rowIndex);
	var ed = datagridId.datagrid('getEditors', rowIndex);
	if(ed[0]){
		ed[0].target.select();
		ed[0].target.click(function (){
			ed[0].target.select();
		});
	}
	if(ed[1]){
		if(rowData.deduction_Integration==null||rowData.deduction_Integration==''){
			ed[1].target.numberbox('setValue', "0");
		}else{
			ed[1].target.numberbox('setValue', rowData.deduction_Integration);
		}
		ed[1].target.select();
		ed[1].target.click(function (){
			ed[1].target.select();
		});
	}
	if(ed[2]){
		if(rowData.gift_Points==null||rowData.gift_Points==''){
			ed[2].target.numberbox('setValue', "0");
		}else{
			ed[2].target.numberbox('setValue', rowData.gift_Points);
		}
		ed[2].target.select();
		ed[2].target.click(function (){
			ed[2].target.select();
		});
	}
	ed[2].target.bind('blur', function() {
		datagridId.datagrid('endEdit', rowIndex);
		datagridId.datagrid('acceptChanges');
	});
}

//点击新增按钮时触发显示 新增和取消按钮   
function dbAdd(){
	if(dbedit==0){
		dbadd = 1;
		var save = '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveOrUpdate(1);">保存</a>';
		var cancel = '<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
		if ($('#addbutton').children('a').length == 0) {
				$('#addbutton').append(save).append(cancel);
				$.parser.parse('#addbutton');
		}
		$('#tab_vip_up_id').tabs('select','会员卡升级明细');
		$('#huiyuan_shengji_yixuan_liebiao_id').datagrid('loadData', { total: 0, rows: [] });
		disable();
	}else{
		alertMsg('对不起，请先完成当前修改操作！', 'warning');
		return;
	}
}

//删除会员卡升级汇总信息
function doRemoveVipUp(){
	var value = $('#datagrid_vip_shengji_id').datagrid('getSelections');
	if(value.length==0){
	    alertMsg('对不起，请先选择要删除的记录！', 'warning');
	    return;
	}else if(value[0].audit_Situation == sate){
		alertMsg('对不起，该记录已审核不可删除！', 'warning');
		return;
	}else{
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
					type : 'POST',
					url : projectPath+'vipCardUpgradeAction!doRemoveVipUp.action',
					data : value[0],
					dataType : 'json',
					success : function(r){
					$('#datagrid_vip_shengji_id').datagrid('reload');
				}
				});
			}
		});
	}
}

//点击修改按钮式触发
function dbEdit(){
	if(dbadd==0){
		var value = $('#datagrid_vip_shengji_id').datagrid('getSelections');
		if(value.length==0){
			alertMsg('对不起，请先选择要修改的记录！', 'warning');
			return;
		}else if(value[0].audit_Situation == sate){
			alertMsg('对不起，该记录已审核不可修改！', 'warning');
			return;
		}else{
			dbedit = 1;
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
		return;
	}
}

//会员升级 审核
function doAudit(){
	var msg = "请确认是否要审核该条记录？";
	var value = $('#datagrid_vip_shengji_id').datagrid('getSelections');
	if(value.length==0){
	    alertMsg('对不起，请先选择要删除的记录！', 'warning');
	    return;
	}else if(value[0].audit_Situation == sate){
		alertMsg('对不起，该会员级别调整单已经被审核，不允许再操作！', 'warning');
	    return;
	}else{
		$.messager.confirm('优亿软件提示',msg,function(b){
			if(b){
				$.ajax({
					type : 'POST',
					url : projectPath+'vipCardUpgradeAction!doAudit.action',
					data : value[0],
					dataType : 'json',
					success : function(r){
					    alertMsg(r.msg, 'info'); 
					    if(r.success){
					         $('#datagrid_vip_shengji_id').datagrid('reload');
					    }
				    }
				});
			}
		});
	}
}


function doSaveOrUpdate(a){
	var url = '';
    //判断是否有会员信息
	var dategrid = $('#huiyuan_shengji_yixuan_liebiao_id');
	if(validateDatagrid('huiyuan_shengji_yixuan_liebiao_id')){
		var rowValue = dategrid.datagrid('getData');
		if(rowValue.rows.length > 0){
			for(var i=0; i < rowValue.rows.length;i++){
				var score = 0;
				dategrid.datagrid('endEdit',i);
				if(rowValue.rows[i].gift_Points!=null && rowValue.rows[i].gift_Points != "" ){
					score = parseFloat(rowValue.rows[i].vip_Integral.toString()) + parseFloat(rowValue.rows[i].gift_Points.toString());
				}else{
					score = parseFloat(rowValue.rows[i].vip_Integral.toString());
				}
				if(rowValue.rows[i].deduction_Integration!=null && rowValue.rows[i].deduction_Integration != "" ){
					score = score - parseFloat(rowValue.rows[i].deduction_Integration.toString());
				}
				if(score < 0){
					alertMsg('对不起，扣除积分太多会员积分不够，请认真确认！', 'warning');
					return;
				}
			}
			var effectRow;
			if(a==1){
				 url=projectPath+'vipCardUpgradeAction!doAddCardUpgrade.action';
				 effectRow = getChange(dategrid, null);
		    }
			if(a==2){
				 url=projectPath+'vipCardUpgradeAction!doEditCardUpgrade.action';
				 var value = $('#datagrid_vip_shengji_id').datagrid('getSelections');
				 effectRow = getChange(dategrid,value[0].upgrade_Id);
		    }
			if(effectRow){
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: url,
				   data: effectRow,
				   success: function(r){
					   alertMsg(r.msg, 'info'); 
					   if(r.success){
						   dategrid.datagrid('acceptChanges');
						   dategrid.datagrid('reload');
						   $('#datagrid_vip_shengji_id').datagrid('reload');
						   //保存成功后隐藏保存按钮
						   $('#addbutton').empty();
						   $('#editbutton').empty();
						   $('#tab_vip_up_id').tabs('select','会员卡升级汇总');
						   //保存成功后将是否是修改的标示 改为0
						   dbedit = 0;
						   dbadd = 0;
						   enable();
					   }
				   },
				   error : function (r){
					   alertMsg(r, 'warning');
					   return;
				   }
				});
			}
		}
	}else{
		alertMsg('对不起，格式不正确，请认真确认！', 'warning');
		return;
	}
}

//取消
function cancel(){
	$('#addbutton').empty();
	$('#editbutton').empty();
	$('#tab_vip_up_id').tabs('select','会员卡升级汇总');
	$('#huiyuan_shengji_yixuan_liebiao_id').datagrid('rejectChanges');
	dbedit = 0;
	dbadd = 0;
	enable();
}

//获取所有行数据
function getChange(id,value){
	if(id.datagrid('getChanges').length) {
		var inserted = id.datagrid('getChanges', 'inserted');
		var deleted = id.datagrid('getChanges', 'deleted');
		var updated = id.datagrid('getChanges', 'updated');
	
		var effectRow = new Object();
		if(inserted){
			effectRow['inserted'] = JSON.stringify(inserted);
		}
		if(deleted){
			effectRow['deleted'] = JSON.stringify(deleted);
		}
		if(updated){
			effectRow['updated'] = JSON.stringify(updated);
		}
		if(value != null){
			effectRow['upgrade_Id'] = value;
		}
		return effectRow;
	};
}

function _exception(){
	showEditDialog("datagrid_vip_shengji_id",null,"datagrid_vip_shengji_idDiv","开始导出","导出配置",0,_callbackExcept);
}

function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"会员退会"+getSystemTime());
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