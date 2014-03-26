$(function() {
	    //洗车登记管理
		$xcdjgl = $('#xcdjgl');
		
		$xcdjgl.datagrid({
			url : '',
			fit : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
		    columns : [[
				{field:'id1',title:'工单号',width:60,sortable:true},
				{field:'id2',title:'车牌号',width:60,sortable:true},
				{field:'id3',title:'客户名称',width:60,sortable:true},
				{field:'id4',title:'联系电话',width:60,sortable:true},
				{field:'id5',title:'指定接待人',width:60,sortable:true}
		      ]]
		});
    });