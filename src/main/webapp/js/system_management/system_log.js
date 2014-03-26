$(function(){
//系统日志
	$('#datagrid_system_log_id').datagrid({
		url : 'systemLogAction!doFindLog.action',
		pagination : true,
		fit : true,
		singleSelect : false,
		rownumbers : true,
		fitColumns : true,
		pageSize : 20,
		pageList : [20, 30, 40, 50, 60, 70, 80 ],
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'id',
			title : '日志编号',
			width : 100,
			sortable : true,
			hidden : true
		},{
			field : 'userName',
			title : '用户名',
			width : 100,
			sortable : true
		},{
			field : 'ipName',
			title : 'IP地址',
			width : 100,
			sortable : true
		},{
			field : 'startTime',
			title : '时间',
			width : 100,
			sortable : true
		},{
			field : 'moduleName',
			title : '模块',
			width : 100,
			sortable : true
		},{
			field : 'content',
			title : '执行功能',
			width : 400,
			sortable : true
		}
		]]
  	});
});
//将form表单序列化为对象
function serializeObj(form){
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
function doConditionSubmit(){
	var form =  $('#form_system_log_id').form();
	var formvalue = serializeObj(form);
	$('#datagrid_system_log_id').datagrid('load',formvalue);
}
//清空
function doClear(){
	$('#form_system_log_id').form('clear');
}
//删除
function doDelete(){
	var id = "";
	var delrow = $('#datagrid_system_log_id').datagrid('getSelections');
	if(delrow.length>0){
		$.messager.confirm('优亿软件提示','请确认是否要删除该条记录？',function(b){
		//循环获取日志编号
		for ( var i = 0; i < delrow.length; i++) {
			id += delrow[i].id+","
		}
		var ids =  id.substring(0,id.lastIndexOf(","));
			if(b){
				$.ajax({
				 	type: 'post',
				   	dataType: 'json',
					url : 'systemLogAction!doDeleteLog.action?id='+ids,
					success : function(d){
					if(d.success){
						$('#datagrid_system_log_id').datagrid('reload');//刷新
						//清空选择
						$('#datagrid_system_log_id').datagrid('clearSelections');
					}else{
						alertMsg(d);
						$('#datagrid_system_log_id').datagrid('clearSelections');
					}
				}
				});
			}
		});
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中要删除的记录！','warning');
	}
}


function _except1(){
 	showEditDialog("datagrid_system_log_id",null,"datagridsystemlogid","开始导出","导出配置",0,_callbackExcept1);
}
 
 /**
  * 导出excel回调函数
  * 将筛选出的列导出到Excel文件
  * 只支持Microsoft Office,不支持WPS
  * @param parentId
  * @param fieldNames  要导出的列字段
  * @return
  */
 function _callbackExcept1(parentId,fieldNames){
 	exportEsuyUIExcelFile(parentId,fieldNames,"系统日志"+getSystemTime());
 }