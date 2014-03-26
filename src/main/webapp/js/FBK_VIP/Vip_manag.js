$(function(){
	$('#tb_id').tabs({
		onSelect:function(title){
	        ttitle = title;
		}
	});
	
	// 赠消积分 明细
	$('#ZENG_XIAO_DETAIL').datagrid( {
		url : projectPath+'vipMessageAction_doFindAll.action',
		pagination : true,
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'number',
			title : '序号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true
				}
			}
		}, {
			field : 'carbrand',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'datetimebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'vipnumber',
			title : '会员号',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'vipname',
			title : '会员名称',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00
				}
			}
		}, {
			field : 'score',
			title : '积分数',
			width : 100,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00

				}
			}
		}
		] ],

		toolbar : [{
			id : 'btnsearch',
			text : '新增',
			iconCls : 'icon-add',
			handler : function (){
			$('#add').window('open');
			$('#score_management_datil_form_add').show();
			$('#score_management_datil_form_add').form('clear');
			$('#score_management_datil_form_add').appendTo('#add');
		}
		}, {
			id : 'btnselect',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function (){
		   	alert('!!!!!!');}
		}, {
			id : 'btnupdate',
			text : '修改',
			iconCls : 'icon-edit',
			handler : function (){
		   	alert('!!!!!!');}

		}, {
			id : 'btnsearch',
			text : '查询',
			iconCls : 'icon-search',
			handler : function (){
		   	alert('!!!!!!');}
		}, {
			id : 'btnpoint',
			text : '打印',
			iconCls : 'icon-print',
			handler : function (){
		   	alert('!!!!!!');}
		}, {
			id : 'btnexport',
			text : '导出',
			iconCls : 'icon-export',
			handler : function (){
		   	alert('!!!!!!');}

		}]

	});

	
	//会员卡服务项目
	/*$('#Vipcard_server_project_center_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'id1',
			title : '会员卡编号',
			width : 100,
			sortable : true
			
		} ,{
			field : 'id2',
			title : '入会时间',
			width : 100,
			sortable : true
			
		} , {
			field : 'id3',
			title : '服务项目名称',
			width : 100,
			sortable : true
			
		}, {
			field : 'id4',
			title : '备注',
			width : 100,
			sortable : true
		}
		]]
	});*/
	
	//会龄提醒（穆浪涛添加）
	$('#Will_age_remind_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			field : 'id1',
			title : '会员编号',
			width :100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id2',
			title : '会员姓名',
			width : 120,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id3',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id4',
			title : '会员卡号',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id5',
			title : '入会日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id6',
			title : '会员到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id7',
			title : '会员状态',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id8',
			title : '会员等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id9',
			title : '会员分组',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id10',
			title : '会龄',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id11',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id12',
			title : '车辆型号',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]]
	});
	//会员积分提醒（穆浪涛添加）
	$('#Vip_integral_remind_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			field : 'id1',
			title : '会员编号',
			width :100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id2',
			title : '会员姓名',
			width : 120,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id3',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id4',
			title : '会员卡号',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id5',
			title : '入会日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id6',
			title : '会员到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id7',
			title : '会员状态',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id8',
			title : '会员等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id9',
			title : '会员分组',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id10',
			title : '累计积分',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id11',
			title : '可用积分',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id12',
			title : '联系电话',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]]
	});
	//会员余额提醒（穆浪涛添加）
	$('#Vip_balance_remind_id').datagrid({
		url : '',
		pagination : true,
		fit : true,
		fitColumns : false,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		frozenColumns : [ [{
			field : 'id1',
			title : '会员编号',
			width :100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id2',
			title : '会员姓名',
			width : 120,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ,{
			field : 'id3',
			title : '车牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id4',
			title : '会员卡号',
			width : 80,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id5',
			title : '入会日期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id6',
			title : '会员到期',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id7',
			title : '会员状态',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id8',
			title : '会员等级',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id9',
			title : '会员分组',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id10',
			title : '卡片余额',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id11',
			title : '车辆牌照',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id12',
			title : '车辆型号',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		},{
			field : 'id13',
			title : '联系电话',
			width : 100,
			sortable : true,
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}
		]]
	});
});