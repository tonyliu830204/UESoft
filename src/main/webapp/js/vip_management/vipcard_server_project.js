$(function(){
	loadComboTree();
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
var d;
//提交
function doSubmit(url1){
	var formid = $('#form_vip_card_service_project_id');
	var form =  formid.form();
	var formvalue = serializeObject(form);
	$.ajax({
		type : 'POST',
		url : url1,
	    data : formvalue,
	    dataType : 'json',
		success : function(r){
		    alertMsg(r.msg, 'info');
			if(r.success){
				d.dialog('close');
				reLoadTreeGrid('Vipcard_server_project_center_id', 'form_vip_card_service_project_condition_id', projectPath+'vipServiceAction!findVipMeal.action', true, false);
			}
		}
   });
}

var showDialog = function(rowDate){
	d = $('<div/>').dialog({
		href : projectPath+"vip_management/addVipService.jsp",
		modal:true,
		closable : false,
		title : '编辑会员套餐',
		width : 350,
		height : 290,
		buttons : [{
				text : '确定',
				handler : function (){
				    if($('#form_vip_card_service_project_id').form('validate')){
				    	if(rowDate == null)
				    	     doSave(1);
				    	else
				    		 doSave(2);
					}
				}
			},{
				text : '取消',
				handler : function (){
					d.dialog('close');
				}
			}
		],
	    onLoad : function (){
		    $('#form_vip_card_service_project_id').form('clear');
		    if(rowDate != null){
		         $('#form_vip_card_service_project_id').form('load', rowDate);
		    }
        },
		onClose : function (){
	    	$(this).dialog('destroy');
	    }
	});
}

function loadComboTree()
{
	$('#Vipcard_server_project_center_id').treegrid({
		url : projectPath+'vipServiceAction!findVipMeal.action',
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'vip_Id',
		treeField : 'meal_Name',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'meal_Name',
			title : '服务项目名称',
			width : 60,
		},{
			field : 'meal_RId',
			title : '套餐关系编号',
			hidden : true
		},{
			field : 'meal_Id',
			title : '套餐编号',
			hidden : true
		},{
			field : 'vip_Id',
			title : '会员编号',
			hidden : true
		},{
			field : '_parentId',
			title : '编号',
			hidden : true
		},{
			field : 'vip_Number',
			title : '会员卡号',
			width : 60
		},{
			field : 'join_Time',
			title : '入会时间',
			width : 60
		}]],
		onBeforeExpand:function(rowData){
		    var url = projectPath+'vipServiceAction!getChildMenu.action?meal_Id=' + rowData.meal_Id+'&vip_Id='+rowData.vip_Id;
		    $('#Vipcard_server_project_center_id').treegrid("options").url = url;
			return true;
	    },onLoadSuccess:function(row, data){
	    	initTreeGridPager('Vipcard_server_project_center_id', projectPath+'vipServiceAction!findVipMeal.action', false, false);
	    }
	});
}

var doSave = function(a){
	if(a == "1"){
		if($('#form_vip_card_service_project_id').form('validate')){
			doSubmit(projectPath+'vipServiceAction!doAdd.action');
		}else{
			alertMsg('请确认信息填写是否完整！','warning');
		}
	}else{
		if($('#form_vip_card_service_project_id').form('validate')){
			doSubmit(projectPath+'vipServiceAction!doUpdate.action');
		}else{
			alertMsg('请确认信息填写是否完整！','warning');
		}
	}
}

var doAdd = function(){
	showDialog(null);
}

function doEdit(){
	var values = $('#Vipcard_server_project_center_id').treegrid('getSelections');
	if(values.length > 0){
		showDialog(values[0]);
	}else{
		alertMsg('请选择要修改的会员套餐！','warning');
	}
}

//删除方法
function doDelete(){
	var values = $('#Vipcard_server_project_center_id').treegrid('getSelections');
	if(values.length > 0){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
			if(b){
				$.ajax({
				    type: 'post',
                    dataType: 'json',
					url : projectPath+'vipServiceAction!doDelete.action?meal_RId='+values[0].meal_RId,
					success : function(r){
						alertMsg(r.msg, 'info');
						if(r.success){
							reLoadTreeGrid('Vipcard_server_project_center_id', 'form_vip_card_service_project_condition_id', projectPath+'vipServiceAction!findVipMeal.action', true, false);
						}
					}
				});
			}
		});
	}else{
		alertMsg('请选择要删除的会员套餐！','warning');
	}
}

//条件查询提交
function doConditionSubmit(){
	reLoadTreeGrid('Vipcard_server_project_center_id', 'form_vip_card_service_project_condition_id', projectPath+'vipServiceAction!findVipMeal.action', false, false);
} 

//清空
function doClear(){
	$('#form_vip_card_service_project_condition_id').form('clear');
	reLoadTreeGrid('Vipcard_server_project_center_id', 'form_vip_card_service_project_condition_id', projectPath+'vipServiceAction!findVipMeal.action', true, false);
}