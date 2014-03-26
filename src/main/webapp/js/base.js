$(function (){
	
	//基础资料->工具管理
	$toolsManager = $('#toolsManager');
	
	$toolsManager.datagrid({
		url : 'basToolsManager_findAll.action',
		singleSelect : true,
		fit : true,
		pagination : true,
		fitColumns : true,
		rownumbers : true,
		showFooter : true,
		columns : [[
          {
			field : 'toolsId',
			title : '编号',
			sortable : true,
			width : 70
		}, {
			field : 'recordDate',
			title : '登记日期',
			width : 80,
			sortable : true,
			editor : {
				type : 'datebox',
				options : {
					required : true,
					editable : false,
					missingMessage : "登记日期为必填项!"
					}
				},
				formatter : function (value, rowData, rowIndex){
					return '<sapn title="' + value + '">' + value + '</span>';
				}
		}, {
			field : 'toolsName',
			title : '名称',
			sortable : true,
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "规格型号为必填项!"
				}
			}
		}, {
			field : 'toolsType',
			title : '规格型号',
			sortable : true,
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "规格型号为必填项!"
				}
			}
		}, {
			field : 'toolsUnit',
			title : '单位',
			width : 60,
			editor : {
			type : 'combobox',
			options : {
					url : 'basToolsManager_findAllPartsUnit.action',
					required : true,
					editable : false,
					valueField:'id',  
				    textField:'text',  
					missingMessage : '配件单位为必填项!'
				}
			},
			formatter: function(value,row,index){
				return row.punitName;
			}
		}, {
			field : 'toolsState',
			title : '状态',
			width : 60,
			editor : {
				type : 'combobox',
				options : {
					url:'common/json/toolsState.json',  
					valueField:'id',  
					textField:'text'
				}
			}
		}, {
			field : 'procurementPrice',
			title : '采购价',
			width : 60,
			sortable : true,
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					min : 1.00,
					precision : 2,
					max : 100000000.00,
					missingMessage : "采购价为必填项!"
				}
			}
		}, {
			field : 'supplier',
			title : '供应商',
			width : 60,
			editor : {
				type : 'combobox',
				options : {
					required : true,
					url : 'basToolsManager_findAllCampany.action',
					missingMessage:'供应商为必填项',
					valueField:'id',  
					textField:'text'
				}
			},
			formatter: function(value,row,index){
				return row.relcampName;
			}
		}, {
			field : 'linkman',
			title : '联系人',
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "联系人为必填项!"
				}
			}
		}, {
			field : 'telphone',
			title : '联系电话',
			width : 60,
			editor : {
				type : 'validatebox',
				options : {
					required : true,
					missingMessage : "联系电话为必填项!"
				}
			}
		}, {
			field : 'buyer',
			title : '采购员',
			width : 60,
			editor : {
			type : 'combobox',
				options : {
					required : true,
					url : 'basToolsManager_findAllStuff.action',
					missingMessage:'采购员为必填项',
					valueField:'id',  
					textField:'text'
				}
			},
			formatter: function(value,row,index){
				return row.stfName;
			}
		} ] ],
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler: function (){
				add($toolsManager, {});
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
			handler: function (){
				remove($toolsManager, 'basToolsManager_delete.action', 'basToolsManager_findAll.action');	
		}
		},{
			text:'修改',
			iconCls:'icon-edit',
			handler: function (){
				edit($toolsManager, 'basToolsManager_edit.action', 'basToolsManager_findAll.action');
			}
		},{
			text:'查询',
			iconCls:'icon-search',
			handler: function (){
				$toolsForm = $('#toolsForm').form();
				_search($toolsForm, $toolsManager);
			}
		},{
			text:'清空',
			iconCls:'icon-cancel',
			handler: function (){
				$('#toolsForm').form('clear');
			}
		},{
			text:'打印',
			iconCls:'icon-print',
			handler: function (){
				
			}
		},{
			text:'导出',
			iconCls:'icon-export',
			handler: function (){
				
			}
		}],
		onAfterEdit : function(rowIndex, rowData, changes) {
			onAfterEdit($toolsManager, 'basToolsManager_save.action' , 'basToolsManager_edit.action',rowIndex, rowData, changes);
		}
	});
	
	//基础资料->新增配件档案
	$mountingsBaseInfomationView = $('#mountingsBaseInfomationView');
	
	$mountingsBaseInfomationView.dialog({
		modal:true,
		closed : true,
		closable : true,
		title : '新增配件档案',
		width : 380,
		height : 240,
		buttons : [{
			text : '确定',
			handler : function (){
				if($('#addFrtPartsForm').form('validate')){
					var url = '';
					if($('#addFrtPartsForm input[name="changePriceId"]').val() == ''){
						url = 'basMountingsBaseInfomation_save';
					}else{
						url = 'basMountingsBaseInfomation_edit';
					}
					$.post(url, $('#addFrtPartsForm').serialize(), function (r){
						if(r.success){
							$mountingsBaseInfomation.datagrid('load');
							$mountingsBaseInfomationView.dialog('close');
							showMsg(r.msg, 'slide');
						}else{
							alertMsg(r.msg, 'error');
						}
					},'json');
				}
			}
        },{
			text : '取消',
			handler : function (){
        		$mountingsBaseInfomationView.dialog({
        			closed : true
        		});
			}
        }]
	});

	//基础资料->客户联系人
	$clientContacts = $('#clientContacts');
	
	$clientContacts.datagrid({
		url : 'common/json/default.json',
		singleSelect : true,
		fit : true,
		//pagination : true,
		fitColumns : true,
		rownumbers : true,
		frozenColumns : [[
	      {field:'id1',title:'姓名',width:60,sortable:true},
	      {field:'id2',title:'出生年月',width:60,sortable:true}
	      ]],
        columns : [[
	      {field:'id3',title:'客户手机',width:60,sortable:true},
	      {field:'id4',title:'客户固话',width:60,sortable:true},
	      {field:'id5',title:'手机',width:60,sortable:true},
	      {field:'id6',title:'传真号',width:60,sortable:true},
	      {field:'id7',title:'部门',width:60,sortable:true},
	      {field:'id8',title:'职位',width:60,sortable:true},
	      {field:'id9',title:'驾证审证年月',width:60,sortable:true},
	      {field:'id10',title:'人员类别',width:60,sortable:true},
	      {field:'id11',title:'会员卡号',width:60,sortable:true},
	      {field:'id12',title:'入会日期',width:60,sortable:true},
	      {field:'id13',title:'联系地址',width:60,sortable:true},
	      {field:'id14',title:'电子邮件',width:60,sortable:true},
	      {field:'id15',title:'备注',width:60,sortable:true}
	      ]]
	});
	
	//人事资料的设定
  $personnelInformationSet = $('#personnelInformationSet');
	
    	$personnelInformationSet.datagrid({
		url : 'basPersonnelInformationSetAction!findAll',
		singleSelect : true,
		fit : true,
		fitColumns : true,
		pagination : true,
		//rownumbers : true,
		columns : [[
         {
					field : 'stfId',
					title : '编号',
					width : 40,
					sortable : true
				}, {
					field : 'stfName',
					title : '姓名',
					width : 40,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "姓名为必填项!"
						}
					}
				}, {
					field : 'stfMark',
					title : '简码',
					width : 40,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "简码为必填项!"
						}
					}
				}, {
					field : 'stfDuty',
					title : '职务',
					width : 40,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "职务为必填项!"
						}
					}
				}, {
					field : 'deptId',
					title : '所属部门',
					width : 40,
					editor : {
						type : 'combobox',
						options : {
							url : 'basPersonnelInformationSetAction!findAllDept',
							required : true,
							editable : false,
							valueField:'id',  
						    textField:'text',  
							missingMessage : "客户区域为必填项!"
						}
					},
					formatter: function(value,row,index){
						return row.deptName;
					}
				},{
					field : 'deptName',
					title : '所属部门',
					width : 40,
					hidden : true
				}, {
					field : 'stfBirthday',
					title : '生日',
					width : 50,
					sortable : true,
					editor : {
						type : 'datebox',
						options : {
							required : true,
							missingMessage : "生日为必填项!"
						}
					}
				}, {
					field : 'stfWorkDate',
					title : '入职日期',
					width : 50,
					sortable : true,
					editor : {
						type : 'datebox',
						options : {
							required : true,
							missingMessage : "入职日期为必填项!"
						}
					}
				}, {
					field : 'stfPhone',
					title : '手机',
					width : 50,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "手机为必填项!"
						}
					}
				}, {
					field : 'stfTel',
					title : '电话',
					width : 50,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "电话为必填项!"
						}
					}
				}, {
					field : 'stfAddr',
					title : '地址',
					width : 50,
					sortable : true,
					editor : {
						type : 'validatebox',
						options : {
							required : true,
							missingMessage : "地址为必填项!"
						}
					}
				}, {
					field : 'stfRemark1',
					title : '备注一',
					width : 50,
					sortable : true,
					editor : {
						type : 'text'
					}
				}, {
					field : 'stfRemark2',
					title : '备注二',
					width : 50,
					sortable : true,
					editor : {
						type : 'text'
					}
				}
         ]]
    	});


	//维修套餐dialog
	$addServiceCombo = $('#addServiceCombo');
	
	$addServiceCombo.dialog({
		modal:true,
		closed : true,
		title : '新增维修套餐',
		closable : true,
		width : 600,
		height : 400,
		href : projectPath+'base/addServiceCombo.jsp',
		buttons : [{
			text : '确定',
			handler : function (){
			$addServiceCombo.dialog({
				closed : true
			});
		}
		},{
			text : '取消',
			handler : function (){
			$addServiceCombo.dialog({
				closed : true
			});
		}
		}]
	});
	
});

//添加时改变editor
function changeEditor(id, fieldName, msg) {
	id.datagrid('addEditor', {
		field : fieldName,
		editor : {
			type : 'validatebox',
			options : {
				required : true,
				missingMessage : msg
			}
		}
	});
}

function getServiceComboCost(){
	$.post('basServiceCombo_getCost', { }, function (r){
		if(r){
			$('#scMaterialCost').val(r[0]);
			$('#scTimeCost').val(r[1]);
		}
	}, 'json');
}