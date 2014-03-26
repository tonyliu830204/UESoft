$(function(){
    //数据备份    
	$('#datagrid_databackup_id').datagrid({
		url : 'dBBackUpLoadAction!getBackupInfor.action',
		pagination:true,
	    fit:true,
	    singleSelect:true,
		sortOrder:'desc',
	    pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		fitColumns : true, 
		idField : 'Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [{
			field : 'Id',
			title : '编号',
			width : 100,
			sortable : true,
			hidden : true
		}, {
			field : 'data_Backup_Time',
			title : '备份时间',
			width : 100,
			sortable : true
		},{
			field : 'data_Back_Name',
			title : '文件名称',
			width : 100,
			sortable : true
		}
		]]
	
  	});
});
//数据库备份
function backup(){
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: projectPath+'dBBackUpLoadAction!backUp.action',
		   data: '',
		   success: function(r){
		      alertMsg(r.msg, 'info');
		      jsCloseProbar();
		      if(r.success){
				$('#datagrid_databackup_id').datagrid('reload');
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
}
//数据库还原
function restore(){
    var delrow = $('#datagrid_databackup_id').datagrid('getSelections');
	if(delrow.length>0){
		$.messager.confirm('优亿软件提示','请确认是否要还原为该备份？',function(b){
			if(b){
				$.ajax({
				   	type: 'post',
				   	dataType: 'json',
				   	url: projectPath+'dBBackUpLoadAction!restore.action',
				   	data: delrow[0],
				   	success: function(r){
						alertMsg(r.msg, 'info');
						jsCloseProbar();
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
			}
		});
	}else{
		$.messager.alert('优亿软件提示','对不起，请先选中还原的记录！','warning');
    }
}