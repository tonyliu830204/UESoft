	var addrow = undefined;
	var editrow = undefined;
	var editindex = undefined;
	var addbar = undefined;
	var editbar = undefined;
	var titles = undefined;
	var staticDatagridId=null;
	
$(function(){	
	//会员卡项目汇总      
	$('#tb_vip_service_project_id').datagrid({
		url : projectPath+'vipServiceProjectAction!doFind.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'meal_Id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'meal_Id',
			title : '编号',
			width : 40,
			sortable : true
			
		} ,{
			field : 'meal_Name',
			title : '套餐名称',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
			        required : true,
			        validType:'length[1,50]'
				}
			}
		} ,{
			field : 'note',
			title : '备注',
			width : 80,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : false,
					validType:'length[1,50]'
				}
			}
		}
		]],
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#tb_vip_service_project_id'),'vipServiceProjectAction!doAdd.action','vipServiceProjectAction!doUpdate.action','vipServiceProjectAction!doFind.action',rowIndex, rowData, changes)
		},
		onDblClickRow : function(rowIndex, rowData){
			dbclick(rowIndex, rowData);
		}	
	
	
	});
	
	//会员卡服务项目明细 
	$('#tb_vip_service_project_detial_id').datagrid({
		url :'',// projectPath+'vipServiceProjectAction!doDetailFind.action'
		pagination : true,
		fit : true,
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'd_Id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'd_Id',
			title : '明细编号',
			width : 40,
			hidden : true
			
		} , {
			field : 'meal_Id',
			title : '套餐编号',
			width : 40,
			sortable : true
		} ,{
			field : 'meal_Name',
			title : '套餐名称',
			width : 40,
			sortable : true,
			hidden : true
		},{
			field : 'meal_Context',
			title : '套餐内容',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
			        required : true,
			        validType:'length[1,50]'
				}
			}
		} ,{
			field : 'memo',
			title : '备注',
			width : 80,
			sortable : true,
			editor : {
				type : 'text',
				options : {
			        required : false,
			        validType:'length[1,50]'
				}
			}
		}
		]],/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			     doAdd($('#tb_vip_service_project_detial_id'));
		    }
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			    doDelete($('#tb_vip_service_project_detial_id'),'vipServiceProjectAction!doDetailDelete.action','vipServiceProjectAction!doDetailFind.action?meal_Id='+$("input[id='meal_Id']").val());
		    }
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			    doUpdate($('#tb_vip_service_project_detial_id'));
			    
			}
		}, {
			id : 'df',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
				doConditionSubmit($('#tb_vip_service_project_detial_id'),$('#form_vip_service_project_detial_id'),'vipServiceProjectAction!doDetailFind.action?meal_Id='+$("input[id='meal_Id']").val())
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
			    _except("tb_vip_service_project_detial_idCenter","会员服务套餐明细信息");
		   	}
		}],*/
		
		onAfterEdit : function(rowIndex, rowData, changes){
		    rowData.meal_Id = $("input[id='meal_Id']").val();
		    rowData.meal_Name = $("input[id='meal_Name']").val();
			afterSubmit1($('#tb_vip_service_project_detial_id'),'vipServiceProjectAction!doDetailAdd.action','vipServiceProjectAction!doDetailUpdate.action','vipServiceProjectAction!doDetailFind.action',rowIndex, rowData, changes)
		}//onAfterEdit结束
	});
	
	
	//会员等级管理
	$('#Vip_rating_management_center_id').datagrid({
		url : projectPath+'basViplevelManagementAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'viptypeId',
			title : '会员类型编号',
			width : 40,
			sortable : true
			
		} ,{
			field : 'viptypeName',
			title : '会员类型名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'projectDiscount',
			title : '配件折扣',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'workhoureDiscount',
			title : '工时折扣',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'scoreRate',
			title : '积分系数',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Vip_rating_management_center_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Vip_rating_management_center_id'),'basViplevelManagementAction_doDelete.action','basViplevelManagementAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Vip_rating_management_center_id'));
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_rating_management_center_id'),'basViplevelManagementAction_doAdd.action','basViplevelManagementAction_doUpdate.action','basViplevelManagementAction_doFindAll.action',rowIndex, rowData, changes)
		}//onAfterEdit结束
	
	
	});


	//会员信息
	$('#Vip_information_center_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ { 
			checkbox : true
		},{
			field : 'id1',
			title : '会员类型编号',
			width : 40,
			sortable : true
			
		} ,{
			field : 'id2',
			title : '会员类型名称',
			width : 100,
			sortable : true
		},{
			field : 'id3',
			title : '配件折扣',
			width : 100,
			sortable : true
			
		},{
			field : 'id4',
			title : '工时折扣',
			width : 100,
			sortable : true
			
		},{
			field : 'id5',
			title : '积分系数',
			width : 100,
			sortable : true
			
		},{
			field : 'id6',
			title : '会员编号',
			width : 100,
			sortable : true
		},{
			field : 'id7',
			title : '顾客编号',
			width : 100,
			sortable : true
		},{
			field : 'id8',
			title : '车辆品牌编号',
			width : 100,
			sortable : true
		},{
			field : 'id9',
			title : '车辆型号',
			width : 100,
			sortable : true
		},{
			field : 'id10',
			title : '车辆编号',
			width : 100,
			sortable : true
		},{
			field : 'id11',
			title : '消减积分',
			width : 100,
			sortable : true
		},{
			field : 'id12',
			title : '会员卡号',
			width : 100,
			sortable : true
		},{
			field : 'id13',
			title : '会员积分',
			width : 100,
			sortable : true
		},{
			field : 'id14',
			title : '会员爱好',
			width : 100,
			sortable : true
		},{
			field : 'id15',
			title : '联系电话',
			width : 100,
			sortable : true
		},{
			field : 'id16',
			title : '入会时间',
			width : 100,
			sortable : true
		},{
			field : 'id17',
			title : '到期时间',
			width : 100,
			sortable : true
		},{
			field : 'id18',
			title : '会员积分编号',
			width : 100,
			sortable : true
		},{
			field : 'id19',
			title : '赠送积分',
			width : 100,
			sortable : true
		},{
			field : 'id20',
			title : '兑换礼品',
			width : 100,
			sortable : true
		},{
			field : 'id21',
			title : '操作日期',
			width : 100,
			sortable : true
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		},*/{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			
			}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}]
	});
	//修改合计积分
	$('#edit_Sum_score_regulation_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 300,
		href : projectPath+'base/vip_nature/Edit_sum_score_regulation.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Sum_score_regulation_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Sum_score_regulation_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//合计积分规则  Sum_score_regulation_center_id
	var $Sum_score_regulation_center_id = $('#Sum_score_regulation_center_id');
		$Sum_score_regulation_center_id.datagrid({
		url : projectPath+'basSumscoreRegulationAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'sumscoreRegulationManner',
			title : '方式',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'sumscoreRegulationStartamount',
			title : '开始金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'sumscoreRegulationEndtamount',
			title : '结束金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		},{
			field : 'sumscoreRegulationBonusrate',
			title : '加分率',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		},{
			field : 'sumscoreRegulationAddtamount',
			title : '添加金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		},{
			field : 'sumscoreRegulationTyoe',
			title : '维修类别',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($Sum_score_regulation_center_id);
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($Sum_score_regulation_center_id,projectPath+'basSumscoreRegulationAction_doDelete.action',projectPath+'basSumscoreRegulationAction_doFindAll.action');
			
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($Sum_score_regulation_center_id);
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var name =  $('#Sum_score_regulation_form_id').find('[name=sumscoreRegulationManner]').val();
			$.ajax({
			type : 'POST',
			url : 'basSumscoreRegulationAction_doFindByName.action',
			data : 'basSumscoreRegulation.sumscoreRegulationManner='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$Sum_score_regulation_center_id.datagrid({
						url : 'basSumscoreRegulationAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
	
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
			onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($Sum_score_regulation_center_id,projectPath+'basSumscoreRegulationAction_doAdd.action',projectPath+'basSumscoreRegulationAction_doUpdate.action',projectPath+'basSumscoreRegulationAction_doFindAll.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
//修改合计积分方法
 function sumModify(){
			var $sumValue = $Sum_score_regulation_center_id.datagrid('getSelections')[0];
			if($sumValue){
				$('#edit_Sum_score_regulation_dialog_id').dialog({
					closed : false
				});
			}else{
				$.messager.show({
					title : '系统提示',
					msg : '请选择要编辑的行!',
					timeout : 2000
				});
			}
		}
//编辑
 $('#edit_Judgment_word_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 220,
		href : projectPath+'base/vip_nature/Edit_judgment_word.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Judgment_word_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Judgment_word_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
 //判断保养词
	$('#Judgment_word_id').datagrid({
		url : projectPath+'basJudgmentWordAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'wordId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'wordId',
			title : '关键词编号',
			width : 100,
			sortable : true
			
		} ,{
			field : 'wordName',
			title : '关键词名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
			
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Judgment_word_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Judgment_word_id'),projectPath+'basJudgmentWordAction_doDelete.action',projectPath+'basJudgmentWordAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Judgment_word_id'));
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var name =  $('#Judgment_word_form_id').find('[name=Judgment_word_name]').val();
			$.ajax({
			type : 'POST',
			url : 'basJudgmentWordAction_doFindByName.action',
			data : 'basJudgmentWordAction.viptypeName='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$('#Judgment_word_id').datagrid({
						url : 'basJudgmentWordAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
	
		
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#Judgment_word_id'),projectPath+'basJudgmentWordAction_doAdd.action',projectPath+'basJudgmentWordAction_doUpdate.action',projectPath+'basJudgmentWordAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	
	});
	//编辑保险险种
	$('#edit_Insurance_coverage_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 250,
		href : projectPath+'base/vip_nature/Edit_insurance_coverage.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Insurance_coverage_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Insurance_coverage_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//保险险种
	$('#Insurance_coverage_id').datagrid({
		url : projectPath+'insuranceTypeAction_doFind.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		sortName:'id',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'id',
			title : '编号',
			hidden : true,
			width : 40
			
		} ,{
			field : 'intype',
			title : '保险险种',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'multiple[\'characterDigit\',\'length[0,100]\']',
					missingMessage : '保险险种为必填项'
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					validType:'multiple[\'characterDigit\',\'length[0,200]\']'
				}
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Insurance_coverage_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Insurance_coverage_id'),'insuranceTypeAction_doDelete.action','insuranceTypeAction_doFind.action');
			
			}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Insurance_coverage_id'));
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Insurance_coverage_idCenter","保险险种信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Insurance_coverage_id'),projectPath+'insuranceTypeAction_doAdd.action',projectPath+'insuranceTypeAction_doUpdate.action',projectPath+'insuranceTypeAction_doFind.action',rowIndex, rowData, changes);
		}//onAfterEdit结束
	});
	//编辑赠送类容
	$('#edit_Score_present_set_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 230,
		href : projectPath+'base/vip_nature/Edit_score_present_set.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Score_present_set_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Score_present_set_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//积分赠送内容设定   //
	$('#Score_present_set_id').datagrid({
		url : projectPath+'basScoreContentsetAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'scoreContentsetId',
			title : '赠送编号',
			width : 100
		} ,{
			field : 'scoreContentsetName',
			title : '赠送名称',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'scoreContentsetDelete',
			title : '消除积分',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#Score_present_set_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#Score_present_set_id'),projectPath+'basScoreContentsetAction_doDelete.action',projectPath+'basScoreContentsetAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Score_present_set_id'));
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			//doFind($('#Score_present_set_id'),'#Score_present_set_form_id','basScoreContentsetAction_doFindByName.action');
			var name =  $('#Score_present_set_form_id').find('[name=Score_present_set_name]').val();
			$.ajax({
			type : 'POST',
			url : 'basScoreContentsetAction_doFindByName.action',
			data : 'basScoreContentset.scoreContentsetName='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$('#Score_present_set_id').datagrid({
						url : 'basScoreContentsetAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#Score_present_set_id'),projectPath+'basScoreContentsetAction_doAdd.action',projectPath+'basScoreContentsetAction_doUpdate.action',projectPath+'basScoreContentsetAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	
	});
	//编辑会员服务
	$('#edit_Vip_server_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 300,
		href : projectPath+'base/vip_nature/Edit_vip_server.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Vip_server_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Vip_server_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//会员服务
	var vipedit = undefined;
	var vipbar = undefined;
	var vip_edit_row = undefined;
	var vip_edit_bar = undefined;
	$('#vip_server_id').datagrid({
		url : projectPath+'basVipServiceAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rowNumber : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'vipId',
			title : '会员编号',
			width : 100
		} ,{
			field : 'vipName',
			title : '会员名称',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			editor : {
				type : 'text',
				options : {
					required : true
				}
			}
			
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#vip_server_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#vip_server_id'),projectPath+'basVipServiceAction_doDelete.action',projectPath+'basVipServiceAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#vip_server_id'));
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			doFind($('#vip_server_id'),'#Vip_server_form_id','basVipServiceAction_doFindByName.action');
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#vip_server_id'),projectPath+'basVipServiceAction_doAdd.action',projectPath+'basVipServiceAction_doUpdate.action',projectPath+'basVipServiceAction_doFindAll.action',rowIndex, rowData, changes);
	}
	});
	//会员材料折扣
	$('#vip_material_discount_id').datagrid({
		url : projectPath+'basVipmaterialDiscountAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'discountId',
			title : '优惠编号',
			width : 100
			
		} ,{
			field : 'vipCardType',
			title : '会员卡类',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'serviceType',
			title : '维修类别',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		},{
			field : 'discountRate',
			title : '优惠率',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		}
		]],

		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#vip_material_discount_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#vip_material_discount_id'),projectPath+'basVipmaterialDiscountAction_doDelete.action',projectPath+'basVipmaterialDiscountAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#vip_material_discount_id'));
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var id =  $('#Vip_work_discount_form_id').find('[name=Vip_work_discount_name]').val();
			$.ajax({
			type : 'POST',
			url : 'basVipmaterialDiscountAction_doFindByName.action',
			data : 'basVipworkDiscount.discountId='+id,
			dataType : 'json',
			success : function(r){
				if(r){
					$('#vip_material_discount_id').datagrid({
						url : 'basVipmaterialDiscountAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
	
		
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#vip_material_discount_id'),projectPath+'basVipmaterialDiscountAction_doAdd.action',projectPath+'basVipmaterialDiscountAction_doUpdate.action',projectPath+'basVipmaterialDiscountAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	
	});
	//编辑会员工时折扣
	$('#edit_Vip_work_discount_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 300,
		href : projectPath+'base/vip_nature/Edit_vip_work_discount.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Vip_work_discount_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Vip_work_discount_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//会员工时折扣
	$('#vip_work_discount_id').datagrid({
		url : projectPath+'basVipworkDiscountAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'discountId',
			title : '优惠编号',
			width : 100
			
		} ,{
			field : 'vipCardType',
			title : '会员卡类',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'serviceType',
			title : '维修类别',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		},{
			field : 'discountRate',
			title : '优惠率',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#vip_work_discount_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#vip_work_discount_id'),projectPath+'basVipworkDiscountAction_doDelete.action',projectPath+'basVipworkDiscountAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#vip_work_discount_id'));
		}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#vip_work_discount_id'),projectPath+'basVipworkDiscountAction_doAdd.action',projectPath+'basVipworkDiscountAction_doUpdate.action',projectPath+'basVipworkDiscountAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	
	});
	
	//修改常用语
	$('#edit_commonle_phrase_dialog_id').dialog({
		title : '编辑',
		closed : true,
		width : 400,
		height : 230,
		modal : true,
		closeable : true,
		href : projectPath+'base/vip_nature/Edit_commonle_phrase.jsp',
		buttons : [{
			text : '确定',
			handler : function(){
			$('#edit_commonle_phrase_dialog_id').dialog({
				closed : true
			});
		}
		},{
			text : '取消',
			handler : function(){
			$('#edit_commonle_phrase_dialog_id').dialog({
				closed : true
			});
		}
		}]
	});
	//常用短语
	$('#Commonle_phrase_id').datagrid({
		url : projectPath+'basUsedPhrasesAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'phrasesId',
		sortName:'phrasesId',
		sortOrder:'asc',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'phrasesId',
			title : '编号',
			hidden : true,
			width : 100
			
		} ,{
			field : 'phrassesName',
			title : '短语名称',
			sortable : true,
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,30]',
					missingMessage : '短语名称为必填项'
					
				}
			}
		},{
			field : 'memo',
			title : '备注',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
			        validType:'length[0,200]'
				}
			}
			
		}
		]],
		onAfterEdit : function(rowIndex, rowData, changes){
		   afterSubmit($('#Commonle_phrase_id'),projectPath+'basUsedPhrasesAction_doAdd.action',projectPath+'basUsedPhrasesAction_doUpdate.action',projectPath+'basUsedPhrasesAction_doFindAll.action',rowIndex, rowData, changes);
		}
	});
	
	//编辑会员卡分类
	$('#edit_vip_classification_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 230,
		href : projectPath+'base/vip_nature/Edit_vip_classification.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_vip_classification_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_vip_classification_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//会员卡分类 
	$('#vip_classification_id').datagrid({
		url : projectPath+'basVipClassificationAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipClassificationId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'vipClassificationId',
			title : '会员卡编号',
			width : 100
		} ,{
			field : 'vipClassificationName',
			title : '会员卡名称',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'vipClassificationDicount',
			title : '工时折扣',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		}, {
			field : 'vipClassificationCoefficient',
			title : '积分系数',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		
		}
		]],

		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			doAdd($('#vip_classification_id'));
		}
		}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
			doDelete($('#vip_classification_id'),projectPath+'basVipClassificationAction_doDelete.action',projectPath+'basVipClassificationAction_doFindAll.action');
		}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#vip_classification_id'));
		}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var name =  $('#Vip_classification_form_id').find('[name=vip_classification_name]').val();
			$.ajax({
			type : 'POST',
			url : 'basVipClassificationAction_doFindByName.action',
			data : 'basVipClassification.vipClassificationName='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$('#vip_classification_id').datagrid({
						url : 'basVipClassificationAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
		}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($('#vip_classification_id'),projectPath+'basVipClassificationAction_doAdd.action',projectPath+'basVipClassificationAction_doUpdate.action',projectPath+'basVipClassificationAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
	
	//材料积分规则
	var $Material_score_regulation_id = $('#Material_score_regulation_id');
	$Material_score_regulation_id.datagrid({
		url : projectPath+'basMaterialIntegralruleAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'materialIntegralruleId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'materialIntegralruleManner',
			title : '方式',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		} ,{
			field : 'materialIntegralruleStartamount',
			title : '开始金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'materialIntegralruleEndtamount',
			title : '结束金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'materialIntegralruleBonusrate',
			title : '加分率',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		
		}, {
			field : 'materialIntegralruleTyoe',
			title : '维修类别',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		}
		]],

			toolbar : [ {
				id : 'btnadd',
				text : '新增',
				iconCls : 'icon-add',
					handler : function (){
				doAdd($Material_score_regulation_id);
				}
			}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($Material_score_regulation_id,projectPath+'basMaterialIntegralruleAction_doDelete.action',projectPath+'basMaterialIntegralruleAction_doFindAll.action');
			}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($Material_score_regulation_id);
			}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var name =  $('#BasMaterialIntegralrule_form_id').find('[name=materialIntegralruleManner]').val();
			$.ajax({
			type : 'POST',
			url : 'basMaterialIntegralruleAction_doFindByName.action',
			data : 'basMaterialIntegralrule.materialIntegralruleManner='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$Material_score_regulation_id.datagrid({
						url : 'basMaterialIntegralruleAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
	}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($Material_score_regulation_id,projectPath+'basMaterialIntegralruleAction_doAdd.action',projectPath+'basMaterialIntegralruleAction_doUpdate.action',projectPath+'basMaterialIntegralruleAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
	//编辑工时积分规则
	$('#edit_Work_score_regulation_dialog_id').dialog({
		modal:true,
		closed : true,
		title : '编辑',
		closable : true,
		width : 380,
		height : 250,
		href : projectPath+'base/vip_nature/Edit_work_score_regulation.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$('#edit_Work_score_regulation_dialog_id').dialog({
					closed : true
				});
		}
		},{
			text : '取消',
			handler : function (){
			$('#edit_Work_score_regulation_dialog_id').dialog({
					closed : true
				});
			}
		}]
	
	});
	//工时积分规则
	var $work_score_regulation_id = $('#work_score_regulation_id');
	$work_score_regulation_id.datagrid({
		url : projectPath+'basWorkIntegralruleAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [ {
			field : 'workIntegralruleManner',
			title : '方式',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		} ,{
			field : 'workIntegralruleStartamount',
			title : '开始金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'workIntegralruleEndtamount',
			title : '结束金额',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'workIntegralruleBonusrate',
			title : '加分率',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		
		}, {
			field : 'workIntegralruleTyoe',
			title : '维修类别',
			width : 100,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
			
		}
		]],

			toolbar : [ {
				id : 'btnadd',
				text : '新增',
				iconCls : 'icon-add',
					handler : function (){
				doAdd($work_score_regulation_id);
				}
			}, {
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($work_score_regulation_id,projectPath+'basWorkIntegralruleAction_doDelete.action',projectPath+'basWorkIntegralruleAction_doFindAll.action');
			
		   	}
		}, {
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($work_score_regulation_id);
			}
		},/*'-',{
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
			var name =  $('#BasWorkIntegralrule_form_id').find('[name=workIntegralruleManner]').val();
			$.ajax({
			type : 'POST',
			url : 'basWorkIntegralruleAction_doFindByName.action',
			data : 'basWorkIntegralrule.workIntegralruleManner='+name,
			dataType : 'json',
			success : function(r){
				if(r){
					$work_score_regulation_id.datagrid({
						url : 'basWorkIntegralruleAction_doFindByName.action'
					});
				}else{
						$.messager.alert('提示','无符合此条件的信息!','warning');
				}
			}
		   });
	}
		},*/{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
		afterSubmit($work_score_regulation_id,projectPath+'basWorkIntegralruleAction_doAdd.action',projectPath+'basWorkIntegralruleAction_doUpdate.action',projectPath+'basWorkIntegralruleAction_doFindAll.action',rowIndex, rowData, changes);
	}//onAfterEdit结束
	});
	
	//扩展easyui datagrid的两个方法.动态添加和删除toolbar的项
	$.extend($.fn.datagrid.methods, {  
		addToolbarItem: function(jq, items){  
			return jq.each(function(){  
				var toolbar = $(this).parent().prev("div.datagrid-toolbar");
				for(var i = 0;i<items.length;i++){
					var item = items[i];
					if(item === "-"){
						toolbar.append('<div class="datagrid-btn-separator"></div>');
					}else{
						var btn=$("<a href=\"javascript:void(0)\"></a>");
						btn[0].onclick=eval(item.handler||function(){});
						btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));
					}
				}
				toolbar = null;
			});  
		},
		removeToolbarItem: function(jq, param){  
			return jq.each(function(){  
				var btns = $(this).parent().prev("div.datagrid-toolbar").children("a");
				var cbtn = null;
				if(typeof param == "number"){
					cbtn = btns.eq(param);
				}else if(typeof param == "string"){
					var text = null;
					btns.each(function(){
						text = $(this).data().linkbutton.options.text;
						if(text == param){
							cbtn = $(this);
							text = null;
							return;
						}
					});
				} 
				if(cbtn){
					var prev = cbtn.prev()[0];
					var next = cbtn.next()[0];
					if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
						$(prev).remove();
					}else if(next && next.nodeName == "DIV"){
						$(next).remove();
					}else if(prev && prev.nodeName == "DIV"){
						$(prev).remove();
					}
					cbtn.remove();	
					cbtn= null;
				}						
			});  
		} 				
	});
	//赠送积分项目管理（穆浪涛添加）
	$('#Vip_give_integral_project_id').datagrid({
		url : projectPath+'BasVipGiveIntegralProjectAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'givInteProId',
			title : '编号',
			width :200,
			hidden : true
		} ,{
			field : 'givInteProName',
			title : '积分赠送项目',
			width : 300,
			sortable : true,
			editor : {
				type:'validatebox',
	            options:{
	          		required:true,
	          		validType:'length[0,50]',
	          		missingMessage : "积分赠送项目为必填项"
	            }
			}
		},{
			field : 'givInteNum',
			title : '赠送积分',
			width : 300,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:true,
	          		validType:'length[0,11]',
	          		missingMessage : "赠送积分为必填项"
	            }
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_give_integral_project_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_give_integral_project_id'),"BasVipGiveIntegralProjectAction!delete.action","BasVipGiveIntegralProjectAction!findAll.action");
			}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_give_integral_project_id'));
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Vip_give_integral_project_idCenter","积分赠送项目信息");
		    }
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_give_integral_project_id'),projectPath+'BasVipGiveIntegralProjectAction!add.action',projectPath+'BasVipGiveIntegralProjectAction!update.action',projectPath+'BasVipGiveIntegralProjectAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//礼品管理（穆浪涛添加）
	var $Vip_present_management_id = $('#Vip_present_management_id');
	$Vip_present_management_id.datagrid({
		url : projectPath+'BasVipResentAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'pstId',
			title : '编号',
			width :100,
			sortable : true
		} ,{
			field : 'pstName',
			title : '礼品名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'pstInte',
			title : '所需积分',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox'
			}
		},{
			field : 'pstCount',
			title : '当前数量',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox'
			}
		},{
			field : 'pstUnit',
			title : '单位',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox'
			}
		} 
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_present_management_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   		doDelete($('#Vip_present_management_id'),projectPath+'BasVipResentAction!delete.action',projectPath+'BasVipResentAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_present_management_id'));
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}
		}],
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_present_management_id'),projectPath+'BasVipResentAction!add.action',projectPath+'BasVipResentAction!update.action',projectPath+'BasVipResentAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员分组管理（日穆浪涛添加）
	$('#Vip_group_management_id').datagrid({
		url : projectPath+'BasVipGruopAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipGruopId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipGruopId',
			title : '编号',
			width :500,
			hidden : true
		} ,{
			field : 'vipGruopName',
			title : '会员分组',
			width : 500,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[0,20]',
					missingMessage : '会员分组为必填项'
				}
			}
		} ,{
			field : 'vipGruopNote',
			title : '备注',
			width : 700,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : false,
					validType:'length[0,200]'
				}
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_group_management_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_group_management_id'),'BasVipGruopAction!delete.action','BasVipGruopAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
			doUpdate($('#Vip_group_management_id'));
			}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Vip_group_management_idCenter","会员分组信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_group_management_id'),projectPath+'BasVipGruopAction!add.action',projectPath+'BasVipGruopAction!update.action',projectPath+'BasVipGruopAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员赠送项目（穆浪涛添加）
	$('#Activities_discount_id').datagrid({
		url :  projectPath+'BasVipActivitiesDiscountAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipActDisId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipActDisId',
			title : '编号',
			width : 100,
			hidden : true
		} ,{
			field : 'vipActDisNane',
			title : '活动名称',
			width : 200,
			sortable : true,
			editor : {
				type:'validatebox',
	            options:{
	          		required:true,
	          		validType:'length[0,50]',
	          		missingMessage : "活动名称为必填项"
	            }
			}
		} ,{
			field : 'workDis',
			title : '工时折扣',
			width : 200,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:false,
	          		min:0,
	          		max:100
	            }
			}
		},{
			field : 'materialDis',
			title : '材料折扣',
			width : 200,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:false,
	          		min:0,
	          		max:100
	            }
			}
		},{
			field : 'totalDis',
			title : '合计折扣',
			width : 200,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:false,
	          		min:0,
	          		max:100
	            }
			}
		}
		]],
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Activities_discount_id'),projectPath+'BasVipActivitiesDiscountAction!add.action',projectPath+'BasVipActivitiesDiscountAction!update.action',projectPath+'BasVipActivitiesDiscountAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员赠送项目（穆浪涛添加）
	$('#Vip_give_project_id').datagrid({
		url : projectPath+'BasVipGiveProjectAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipGpId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipGpId',
			title : '编号',
			width :400,
			hidden : true
		} ,{
			field : 'vipGpName',
			title : '会员赠送项目',
			width : 500,
			sortable : true,
			editor : {
			 type:'validatebox',
             options:{
                  required:true,
                  validType:'length[0,20]',
                  missingMessage : "会员赠送项目为必填项"
             }
			}
		} ,{
			field : 'vipGpNote',
			title : '备注',
			width : 800,
			sortable : true,
			editor : {
				 type:'validatebox',
	             options:{
	                  required:false,
	                  validType:'length[0,200]'
	             }
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_give_project_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_give_project_id'),'BasVipGiveProjectAction!delete.action','BasVipGiveProjectAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_give_project_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
			     _except("Vip_give_project_idCenter","会员赠送项目信息");
		   	}
		}],*/onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_give_project_id'),projectPath+'BasVipGiveProjectAction!add.action',projectPath+'BasVipGiveProjectAction!update.action',projectPath+'BasVipGiveProjectAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员储值规则（穆浪涛添加）
	$('#Vip_recharge_give_regulation_id').datagrid({
		url : projectPath+'BasVipRechargeRegulationAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'recRulId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'recRulId',
			title : '编号',
			width :250,
			hidden : true
		} ,{
			field : 'recRulName',
			title : '起始金额',
			width : 350,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1,
					validType:'length[1,11]',
					missingMessage : "起始金额为必填项"
				}
			}
		} ,{
			field : 'recRulNameEnd',
			title : '结束金额',
			width : 350,
			sortable : true,
			editor : {
			type : 'numberbox',
			options : {
				required : true,
				min : 1,
				validType:'length[1,11]',
					missingMessage : "结束金额为必填项"
				}
			}
		}  ,{
			field : 'givInteRatio',
			title : '赠送金额',
			width : 350,
			sortable : true,
			editor : {
					type : 'numberbox',
					options : {
						min : 1,
						validType:'length[1,11]',
				    }
			}
		} ,{
			field : 'givAmountRatio',
			title : '赠送积分',
			width : 340,
			sortable : true,
			editor : {
					type : 'numberbox',
					options : {
						min : 1,
						validType:'length[1,11]',
				    }
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_recharge_give_regulation_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_recharge_give_regulation_id'),'BasVipRechargeRegulationAction!delete.action','BasVipRechargeRegulationAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_recharge_give_regulation_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Vip_recharge_give_regulation_idCenter","会员储值规则信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_recharge_give_regulation_id'),projectPath+'BasVipRechargeRegulationAction!add.action',projectPath+'BasVipRechargeRegulationAction!update.action',projectPath+'BasVipRechargeRegulationAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//星期积分规则（穆浪涛添加）
	$('#Vip_week_integral_regulation_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'id1',
			title : '星期',
			width :400,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "星期为必填项"
				}
			}
		} ,{
			field : 'id2',
			title : '积分系数',
			width : 500,
			sortable : true,
			editor : {
				type : 'validatebox'
			}
		} ,{
			field : 'id3',
			title : '备注',
			width : 800,
			sortable : true,
			editor : {
				type : 'validatebox'
			}
		}
		]],
		toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_week_integral_regulation_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   		doDelete($('#Vip_week_integral_regulation_id'),'','');
			}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_week_integral_regulation_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('导出');}
		}]
	});
	//会员活动项目（穆浪涛添加）
	$('#Vip_activities_project_id').datagrid({
		url : projectPath+'BasVipActivitiesProjectAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipActProId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipActProId',
			title : '编号',
			width :100,
			hidden : true
		} ,{
			field : 'vipActProName',
			title : '会员活动项目',
			width :100,
			sortable : true,
			editor : {
				type:'validatebox',
	            options:{
	          		required:true,
	          		validType:'length[0,50]',
	          		missingMessage : "会员活动项目为必填项"
	            }
			}
		} ,{
			field : 'vipActProDate',
			title : '活动开始时间',
			width :100,
			sortable : true,
			hidden: true
		},{
			field : 'vipActJoinPcount',
			title : '参与人数',
			width :100,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:true,
	          		validType:'length[0,4]',
	          		missingMessage : "参与人数为必填项"
	            }
			}
		},{
			field : 'vipActReward',
			title : '奖励积分',
			width :100,
			sortable : true,
			editor : {
				type:'numberbox',
	            options:{
	          		required:true,
	          		validType:'length[0,11]',
	          		missingMessage : "参与人数为必填项"
	            }
			}
		},{
			field : 'vipActProNote',
			title : '备注',
			width :100,
			sortable : true,
			editor : {
				type:'validatebox',
	            options:{
	          		required:false,
	          		validType:'length[0,200]'
	            }
			}
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_activities_project_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_activities_project_id'),'BasVipActivitiesProjectAction!delete.action','BasVipActivitiesProjectAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_activities_project_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("vipactivitiesprojectdiv","会员活动项信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_activities_project_id'),projectPath+'BasVipActivitiesProjectAction!add.action',projectPath+'BasVipActivitiesProjectAction!update.action',projectPath+'BasVipActivitiesProjectAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员折扣规则（穆浪涛添加）
	$('#Vip_discount_regulation_id').datagrid({
		url : projectPath+'BasVipDiscountRegulationAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipDisRegId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipDisRegId',
			title : '编号',
			width :100,
			hidden : true
		} ,{
			field : 'vipLevelId',
			title : '会员等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'BasVipDiscountRegulationAction!findVipLevel.action',
					required : true,
					mode:'remote',
					valueField:'vipLevelId',
					//validType:'isSelected[\'vipLevelId\']',
					textField:'vipLevelName',
					missingMessage : '会员等级为必选项',
					invalidMessage : '请从下拉框中选择客户类型',
					editable : true,
					onLoadSuccess : function(){
						$(this).combobox('textbox').select();
					}
				}
			},
			formatter: function(value,row,index){
				return row.vipLevelName;
			}
		} ,{
			field : 'reptypId',
			title : '维修类别',
			width : 100,
			sortable : true,
			editor : {
			type : 'combobox',
				options : {
					url : projectPath+'BasVipDiscountRegulationAction!findAllRepairType.action',
					required:true,
					mode:'remote',
					valueField:'reptId',  
					textField:'reptName',
					editable : true
				}
			},
			formatter: function(value,row,index){
				return row.reptypName;
			}
		},{
			field : 'workRegDiscount',
			title : '工时折扣',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					min : 1,
					validType:'length[0,10]',
				}
			}
		},{
			field : 'materialRegDiscount',
			title : '材料折扣',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					min : 1,
					validType:'length[0,10]',
				}
			}
		},{
			field : 'totalRegDiscount',
			title : '合计折扣',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					min : 1,
					validType:'length[0,10]',
				}
			}
		},{
			field : 'vipLevelName',
			title : '会员等级名称',
			width : 100,
			hidden : true,
			sortable : true
		},{
			field : 'reptypName',
			title : '维修分类名称',
			width : 100,
			hidden : true,
			sortable : true
		}
		]],
		/*toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_discount_regulation_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_discount_regulation_id'),'BasVipDiscountRegulationAction!delete.action','BasVipDiscountRegulationAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_discount_regulation_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Vip_discount_regulation_idCenter","会员折扣规则信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_discount_regulation_id'),projectPath+'BasVipDiscountRegulationAction!add.action',projectPath+'BasVipDiscountRegulationAction!update.action',projectPath+'BasVipDiscountRegulationAction!findAll.action',rowIndex, rowData, changes);
		},
		onSelect : function(rowIndex, rowData){
			
		}
	});
	//会员积分规则（穆浪涛添加）
	$('#Vip_integral_regulation_id').datagrid({
		url : projectPath+'BasVipIntegralRegulationAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipInteReg',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipInteReg',
			title : '编号',
			width :100,
			hidden : true
		} ,{
			id:'svipLevelId',
			field : 'vipLevelId',
			title : '会员等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'combobox',
				options : {
					url : projectPath+'BasVipDiscountRegulationAction!findVipLevel.action',
					required : true,
					mode:'remote',
					valueField:'vipLevelId',  
					textField:'vipLevelName',  
					missingMessage : '会员等级为必选项',
					editable : true,
					invalidMessage : '请从下拉框中选择车辆品牌',
					onLoadSuccess : function(){
						$(this).combobox('textbox').select();
					}
				}
			},
			formatter: function(value,row,index){
				return row.vipLevelName;
			}
		} ,{
			field : 'reptypId',
			title : '维修类别',
			width : 100,
			sortable : true,
			editor : {
				type : 'combobox',
					options : {
						url : projectPath+'BasVipDiscountRegulationAction!findAllRepairType.action',
						required:true,
						mode:'remote',
						valueField:'reptId',  
						textField:'reptName',
						editable : true
					}
				},
				formatter: function(value,row,index){
					return row.reptypName;
				}
		},{
			field : 'beginAmount',
			title : '积分起步金额',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					min : 0.1,
					precision : 2,
					validType:'length[0,10]',
				}
			}
		},{
			field : 'jfAmont',
			title : '积分单位',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
				validType:'length[0,10]',
			  }
			}
		},{
			field : 'score',
			title : '积分数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					min : 1,
					validType:'length[0,10]',
				}
			}
		},{
			field : 'vipLevelName',
			title : '会员等级名称',
			width : 100,
			hidden: true,
			sortable : true
		}
		,{
			field : 'reptypName',
			title : '维修分类名称',
			width : 100,
			hidden: true,
			sortable : true
		}
		]],
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_integral_regulation_id'),projectPath+'BasVipIntegralRegulationAction!add.action',projectPath+'BasVipIntegralRegulationAction!update.action',projectPath+'BasVipIntegralRegulationAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	//会员等级管理（穆浪涛添加）
	$('#Vip_level_management_id').datagrid({
		url : projectPath+'BasVipLevelAction!findAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vipLevelId',
		loadMsg : "数据加载中，请稍候……",
		columns : [ [{
			field : 'vipLevelId',
			title : '编号',
			width :200,
			hidden : true
		} ,{
			field : 'vipLevelName',
			title : '会员等级',
			width : 400,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					validType:'length[1,20]',
					missingMessage : "会员等级为必填项"
				}
			}
		} ,{
			field : 'vipLevelNote',
			title : '备注',
			width : 700,
			sortable : true,
			editor : {
				required : true,
				validType:'length[1,200]',
				type : 'validatebox'	
			}
		}
		]],
	/*	toolbar : [ {
			id : 'btnadd',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
				doAdd($('#Vip_level_management_id'));
			}
		},{
			id : 'btnremove',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
				doDelete($('#Vip_level_management_id'),'BasVipLevelAction!delete.action','BasVipLevelAction!findAll.action');
		   	}
		},{
			id : 'btnedit',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
				doUpdate($('#Vip_level_management_id'));
		   	}
		},{
			id : 'btnexport',
			text : '导出	',
			iconCls : 'icon-export',
			handler : function (){
			    _except("Vip_level_management_idCenter","会员等级信息");
		   	}
		}],*/
		onAfterEdit : function(rowIndex, rowData, changes){
			afterSubmit($('#Vip_level_management_id'),projectPath+'BasVipLevelAction!add.action',projectPath+'BasVipLevelAction!update.action',projectPath+'BasVipLevelAction!findAll.action',rowIndex, rowData, changes);
		}
	});
	
	
});

$('#tabs_service_project_id').tabs({
	onSelect:function(title){
		titles = title;
	}
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
//条件查询提交
function doConditionSubmit(datagridId,formid,url1){
	var form =  formid.form();
	var formvalue = serializeObject(form);
	datagridId.datagrid('load',formvalue);
	datagridId.datagrid({url : url1});	
}

//新增数据方法
function doAdd(id){
		if(addrow!=undefined){
			alertMsg('对不起,此系统只支持单行新增!', 'warning');
		}else if(editrow!=undefined){
			alertMsg('请先完成当前编辑!', 'warning');	
		}else{
			if(addrow == undefined){
				$('#btnadd').linkbutton('disable');
				$('#btnremove').linkbutton('disable');
				$('#btnedit').linkbutton('disable');
				$('#btnexport').linkbutton('disable');
				$('#df').linkbutton('disable');
			id.datagrid('insertRow',{
				index: 0,	
				row: {}
			});
			id.datagrid('beginEdit',0);
			editindex = 0;
			addrow = 0;
			bindEnterInCloumn(id, 0, 0);
			if(addbar==undefined){
				addButton(id);	
			}
		}else{
			id.datagrid('endEdit',addrow);
		}
	}
}

//新增数据方法
function doAdd1(id){
		if(addrow!=undefined){
			alertMsg('对不起,此系统只支持单行新增!', 'warning');
		}else if(editrow!=undefined){
			alertMsg('请先完成当前编辑!', 'warning');	
		}else{
			if(addrow == undefined){
				$('#btnadd').linkbutton('disable');
				$('#btnremove').linkbutton('disable');
				$('#btnedit').linkbutton('disable');
				$('#btnexport').linkbutton('disable');
				$('#df').linkbutton('disable');
			id.datagrid('insertRow',{
				index: 0,	
				row: {}
			});
			id.datagrid('beginEdit',0);
			editindex = 0;
			addrow = 0;
			 bindEnterInCloumn(id, 0, 0);
			if(addbar==undefined){
				addButton1(id);	
			}
		}else{
			id.datagrid('endEdit',addrow);
			//addrow = undefined;
		}
	}
}
var cancel=function(){
	staticDatagridId.datagrid('unselectAll');
	staticDatagridId.datagrid('rejectChanges');
	$('#saveOrCancelBtn').empty();
	$('#btnadd').linkbutton('enable');
	$('#btnremove').linkbutton('enable');
	$('#btnedit').linkbutton('enable');
	$('#btnexport').linkbutton('enable');
	$('#df').linkbutton('enable');
	editrow = undefined;
	addrow = undefined;
}

var cancel1=function(){
	staticDatagridId.datagrid('unselectAll');
	staticDatagridId.datagrid('rejectChanges');
	 $('#saveOrCancelBtn1').empty();
	$('#btnadd').linkbutton('enable');
	$('#btnremove').linkbutton('enable');
	$('#btnedit').linkbutton('enable');
	$('#btnexport').linkbutton('enable');
	$('#df').linkbutton('enable');
	editrow = undefined;
	addrow = undefined;
}

function save1(){
	if(staticDatagridId.datagrid('validateRow', editrow))
		staticDatagridId.datagrid('endEdit', editrow);
	else
		alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
}
function save(){
	if(staticDatagridId.datagrid('validateRow', addrow)){
		staticDatagridId.datagrid('endEdit', addrow);
	}	
	else{
		alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
	}
		
}

//按钮区域增加 保存和取消按钮
function  addButton(id,i){
	staticDatagridId=id;
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save1();">保存</a>';
	}else{
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	}
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">取消</a>';
	if ($('#saveOrCancelBtn').children('a').length == 0) {
		$('#saveOrCancelBtn').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn');
	}
}

//按钮区域增加 保存和取消按钮
function  addButton1(id,i){
	staticDatagridId=id;
	if(i==1){
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save1();">保存</a>';
	}else{
		var save= '<a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>';
	}
	var cancel='<a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel1();">取消</a>';
	if ($('#saveOrCancelBtn1').children('a').length == 0) {
		$('#saveOrCancelBtn1').append(save).append(cancel);
		$.parser.parse('#saveOrCancelBtn1');
	}
}

//删除方法
function doDelete(id,url1,url2){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作.', 'warning');
	}else if(editrow!=undefined){
		alertMsg('请先完成当前编辑!', 'warning');
	}else{
		var delrow = id.datagrid('getSelections');
		if(delrow.length>0){
			$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
				if(b){
					$.ajax({
						url : url1,
						data : delrow[0],
						success : function(data){
							var d = $.parseJSON(data);
							if(d.success){
								id.datagrid({url : url2});//刷新
								//清空选择
								id.datagrid('clearSelections');
							}else{
								id.datagrid('clearSelections');
								alertMsg('对不起，此数据已经被使用，不允许删除！', 'info');
							}
						}
					});
				}
			});
		}else{
			$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
		}
	}
}
//修改方法
function doUpdate(id){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作!', 'warning');
	}else if(editrow!=undefined){
		alertMsg('对不起,此系统只支持单行编辑操作!', 'warning');
	}else{
		var rows = id.datagrid('getSelections');
		if(rows.length > 0){
			if(editbar==undefined){
				$('#btnadd').linkbutton('disable');
				$('#btnremove').linkbutton('disable');
				$('#btnedit').linkbutton('disable');
				$('#btnexport').linkbutton('disable');
				$('#df').linkbutton('disable');
				}
				if(editrow != undefined){
					id.datagrid('endEdit',editrow);
				}
				if(editrow == undefined){
					var index = id.datagrid('getRowIndex',rows[0]);//
					editindex = index;
					id.datagrid('beginEdit',index);
					editrow = index;
					addButton(id,1);	
					id.datagrid('unselectAll');
					bindEnterInCloumn(id, index, 0);
				}
			}else{
				alertMsg('对不起，请先选中要修改的记录！', 'warning');
			}
		}
	}

//修改方法
function doUpdate1(id){
	if(addrow!=undefined){
		alertMsg('请先完成当前新增操作!', 'warning');
	}else if(editrow!=undefined){
		alertMsg('对不起,此系统只支持单行编辑操作!', 'warning');
	}else{
		var rows = id.datagrid('getSelections');
		if(rows.length > 0){
			if(editbar==undefined){
				$('#btnadd').linkbutton('disable');
				$('#btnremove').linkbutton('disable');
				$('#btnedit').linkbutton('disable');
				$('#btnexport').linkbutton('disable');
				$('#df').linkbutton('disable');
				}
				if(editrow != undefined){
					id.datagrid('endEdit',editrow);
				}
				if(editrow == undefined){
					var index = id.datagrid('getRowIndex',rows[0]);//
					editindex = index;
					id.datagrid('beginEdit',index);
					editrow = index;
					addButton1(id,1);	
					id.datagrid('unselectAll');
					bindEnterInCloumn(id, index, 0);
				}
			}else{
				alertMsg('对不起，请先选中要修改的记录！', 'warning');
			}
		}
	}
//查询的方法
function doFind(id,formid,url){
	var form =  $(formid).form();
	var formvalue = serializeObject(form);
		$.ajax({
		type : 'POST',
		url : url,
		data : formvalue,
		dataType : 'json',
		success : function(r){
			if(r){
				id.datagrid({
					url : url
				});
			}else{
					$.messager.alert('优亿软件提示','无符合此条件的信息!','warning');
			}
		}
	   });
}
//新增 修改后的 提交执行方法  
function afterSubmit(id,url1,url2,url3,rowIndex, rowData, changes){
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
	}
	if(updated.length>0){
		url = url2;
	}else{
		cancel();
	}
	if(inserted.length == 0 && updated.length==0){
		editrow = undefined;
	}
		//ajax提交新增内容
		$.ajax({
			type : 'POST',
			url : url,
			data : rowData,
			dataType : 'json',
			success : function (r){
				if(r.success){
					id.datagrid('acceptChanges');
					id.datagrid('clearSelections');
					cancel();
					if(inserted.length>0){
						id.datagrid('load');
					}
					if(updated.length>0){
						id.datagrid('reload');
					}
					addrow = undefined;
					addbar = undefined;
					editrow = undefined;
					cancel(id);
				}else{
					alertMsg(r.msg, 'info');
					id.datagrid('beginEdit', editindex);
				}
			}
		});
	}

//双击一行是触发
function dbclick(rowIndex, rowData){
	$('#tabs_service_project_id').tabs('select','会员卡服务项目明细');
	  if(titles=="会员卡服务项目明细"){
		  $('#tb_vip_service_project_detial_id').datagrid({url : projectPath+'vipServiceProjectAction!doDetailFind.action?meal_Id='+rowData.meal_Id});
		  $("input[id='meal_Id']").val(rowData.meal_Id);
		  $("input[id='meal_Name']").val(rowData.meal_Name);
	}
}

//新增 修改后的 提交执行方法  
function afterSubmit1(id,url1,url2,url3,rowIndex, rowData, changes){
	var inserted = id.datagrid('getChanges','inserted');
	var updated = id.datagrid('getChanges','updated');
	var url = '';
	if(inserted.length>0){
		url = url1;
	}
	if(updated.length>0){
		url = url2;
	}
	if(inserted.length == 0 && updated.length==0){
		editrow = undefined;
		id.datagrid('removeToolbarItem','保存');
		id.datagrid('removeToolbarItem','取消');
	}
		//ajax提交新增内容
		$.ajax({
			type : 'POST',
			url : url,
			data : rowData,
			dataType : 'json',
			success : function (r){
			//var r = $.parseJSON(data);
				if(r.success){
					id.datagrid('acceptChanges');
					id.datagrid('clearSelections');
					//id.datagrid({url : url3});
					$('#saveOrCancelBtn1').empty();
					id.datagrid('removeToolbarItem','保存');
					id.datagrid('removeToolbarItem','取消');
					cancel();
					if(inserted.length>0){
						id.datagrid('load');
					}
					if(updated.length>0){
						id.datagrid('reload');
					}
					addrow = undefined;
					addbar = undefined;
					editrow = undefined;
					cancel1();
					$('#saveOrCancelBtn1').empty();
				}else{
					//id.datagrid('rejectChanges');
					//alertMsg("对不起，记录无法保存，请确认内容及格式是否正确！", 'info');
					alertMsg(r.msg, 'info');
					id.datagrid('beginEdit', editindex);
					//addrow = undefined;
					//addbar = undefined;
				}
			}
		});
	}



//会员卡服务项目明细删除
function del() {
	  var data = $('#tb_vip_service_project_detial_id').datagrid('getSelected');
	  var index=findSelectRowIndex('tb_vip_service_project_detial_id',data);
	  if (data) {
			$.messager.confirm('优亿软件提示', '请确认是否要删除该条记录？', function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "vipServiceProjectAction!doDetailDelete.action",
						data : "d_Id=" + data.d_Id,
						dataType : "json",
						success : function callback(r){
							if(r.success)
							{
							    $('#tb_vip_service_project_detial_id').datagrid('clearSelections');
								$('#tb_vip_service_project_detial_id').datagrid('load');
								setSelectRow('tb_vip_service_project_detial_id',index);
							}else
								$.messager.alert('优亿软件提示',r.msg,'info',function(){
							});
						}
					});
		         }
			 });
   }else{
	   $.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
   }
}

/**
 * 
 * 导出excel
 * @return
 */
function _except(div,name){
	exportEsuyUIExcelFile(div,null,name+getSystemTime());
}