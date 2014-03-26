function LoadOk() {
	if (document.readyState == "complete") {
		showStoreDatagrid();
	} else {
		setTimeout("LoadOk();", 200);
	}
}
setTimeout("LoadOk();", 200);
var showStoreDatagrid=function(){
	$('#showStoreDatagrid').datagrid({
		url : 'distrubtPurviewAction!findAllStoreOfUser.action',
		singleSelect : true,
		pagination : true,
		fit : true,
		sortOrder:'desc',
	    sortName:'interDate',
		rownumbers : true,
		fitColumns : true,
		loadMsg : "数据加载中，请稍后……",
		idField:'receptionId',
		frozenColumns : [[{
			title : '企业序号',
			field : 'enterpriseId',
			hidden:true
	    }, {
			title : '企业编号',
			field : 'enterpriseCode',
			width : 150
	    }, {
			title : '企业名称',
			field : 'enterpriseName',
			width : 100
		}, {
			title : '企业简称',
			field : 'enterpriseJm',
			width : 100
		}, {
			title : '企业地址',
			field : 'enterpriseAddress',
			width : 120
		}, {
			title : '邮政编码',
			field : 'enterpriseZipcode',
			width : 120
		},  {
			title : '电话',
			field : 'enterpriseTelephone',
			width : 120
		}, {
			title : '传真',
			field : 'enterpriseFax',
			width : 100
		}, {
			title : '企业法人',
			field : 'enterprisePerson',
			width : 100
		}
         ]],
         onDblClickRow : function (rowIndex, data){
			reStart();
		 }
	});
}
var _query=function(){
	$('#showStoreDatagrid').datagrid('load',serializeObject($('#showStoreSearchForm')));
}
var _clear=function(){
	$('#showStoreSearchForm').form('clear');
}