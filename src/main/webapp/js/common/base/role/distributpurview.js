$(function (){
	distributPurview();
	distributPurviewUser();
});
var distributPurview = function(){
	$('#distributPurview').datagrid({
		url : projectPath+'distrubtPurviewAction!distrubtPurviewDataGrid.action',
		fit : true,
		singleSelect:true,
		rownumbers : true,
		loadMsg : "数据加载中，请稍后……",
		idField : 'enterpriseId',
		columns : [[{
			field : 'enterpriseId',
			title : '企业序号',
			width : 60,
			hidden : true
		},{
			field : 'enterpriseName',
			title : '公司名称',
			width : 300
		}]],
		onClickRow:function(rowIndex,rowData){
			$('#distributPurviewUser').datagrid('unselectAll');
		}
	});
}
var distributPurviewUser = function(){
	$('#distributPurviewUser').datagrid({
		url : projectPath+'distrubtPurviewAction!findDistrubtPurviewUsers.action',
		pagination:true,
		singleSelect:true,
		fitColumns:true,
		fit : true,
		rownumbers : true,
		autoRowHeight:true,
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'stfId',
			title : '员工序号',
			width : 60,
			hidden : true
		},{
			field : 'stfName',
			title : '员工名称',
			width : 60,
			sortable : true
		},{
			field : 'deptName',
			title : '部门名称',
			width : 60
		},{
			field : 'stfZwgz',
			title : '职位工种',
			width : 60
		}]],
		onClickRow:function(rowIndex,rowData){
			var row=$('#distributPurview').datagrid('getSelected');
			$('#aselecteds').val(rowData.stfId);
			$('#aenterpriseId').val(row.enterpriseId);
			loadtree(row.enterpriseId,rowData.stfId);
		}
	});
}

var saveMenuDetail=function(){
	var row=$('#distributPurview').datagrid('getSelected');
	var row2=$('#distributPurviewUser').datagrid('getSelected');
	if(row){
		if(row2){
			if($('#RoleAddForm').form('validate')){
		    	getChecked();
				$.ajax({
				   type: 'post',
				   dataType: 'json',
				   url: projectPath+'roleAction!saveDistributePurviewMenuRoleTree.action',
				   data: $('#RoleAddForm').serialize(),
				   success: function(r){
					   jsCloseProbar();
					   if(r.success){
						   alertMsg(r);
					   }else{
						    alertMsg(r);
					   }
				   },
				   error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   },
				   beforeSend:function(e){
					   jsProbar();
				   }
				});
			}else{
				alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
			}
		}
	}
}

function loadtree(enterpriseId, stfId){
	var url="";
	if(enterpriseId!= null&&stfId!=null){
		url = projectPath+'roleAction!distributePurviewMenuRoleTree.action?enterpriseId='+enterpriseId+'&selecteds='+stfId;
	}
	 jsProbar();
	$('#distributePurviewTree').tree({   
        checkbox: true,   
        url: url,
        onLoadSuccess : function(node, data){
		    //expandAll();
		 	jsCloseProbar();
	    }
    });
}

function expandAll(){
	var tree_ = $('#distributePurviewTree');
	var node = tree_.tree('getSelected');
	if (node != null){
		tree_.tree('expandAll', node.target);
	} else {
		tree_.tree('expandAll');
	}
}
function getChecked(){
    var nodes = $('#distributePurviewTree').tree('getCheckeds');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') 
			s += ',';
		s += nodes[i].id;
	}
	$("#acheckeds").val(s);
}
var distributeSearch=function(){
	if($('#distributeSearchForm').form('validate')){
		$('#distributPurviewUser').datagrid('load', serializeObject($('#distributeSearchForm')));
	}
}
var distributeClear=function(){
	$('#distributeSearchForm').form('clear');
}
var distributeExport=function(){
	showEditDialog("distributPurviewUser",null,"distributPurviewUser_center","开始导出","导出配置",0,_callbackExcept);
}
function _callbackExcept(parentId,fieldNames){
	exportEsuyUIExcelFile(parentId,fieldNames,"分布点权限管理"+getSystemTime());
}