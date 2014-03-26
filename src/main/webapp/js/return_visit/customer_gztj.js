$(function(){
	$('#tt').tabs({   
		onSelect:function(title){  
		
		 tbtitle = title;
			
	    }   
	});
	// 客户跟踪项目统计
		$('#customer_statistics2').datagrid({
		url : 'programTrackAction_findSameThingAll.action',
		fit : true,
		fitColumns : true,
		singleSelect : true,
		rownumbers : true,
		idField : 'id',
		loadMsg : "数据加载中，请稍后……",
		columns : [ [
		            {field : 'dcNameId',title : '项目id',width : 100}, 
		            {field : 'serveyName',title : '项目名称',width : 100}, 
					{field : 'henhao',title : '很好(s1)',width : 100}, 
					{field : 'hao',title : '好(s2)',width : 100}, 
					{field : 'yiban',title : '一般(s3)',width : 100}, 
					{field : 'buhao',title : '不好(s4)',width : 100}, 
					{field : 'henbuhao',title : '很不好(s5)',width : 100}, 
					{field : 'manyilv',title : '满意率(s1+s2)',width : 100}, 
					{field : 'totals',title : 's=(s1+s2+s3+s4+s5)',width : 100} ] ]
	});
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'vTrackRecordAction!getAll.action',
			success : function(r) {
				eval(r);
			},
			error : function (r){
			   if(r.readyState == '0' && r.status == '0'){
				   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
			   } 
		   	}
		});
    	//初始化跟踪满意度统计
		$('#satisfaction_degree_id').datagrid({
			url : "vTrackRecordAction!getSatisfaction.action",
			type : 'POST',
			newrap : false,
			striped : true,
			fitColumns : true,
			fit : true,
			singleSelect : true,
			frozenColumns : [[{field : 'biaoti',title : '标题',width : 100}]],
			onLoadSuccess : function(data){
				if(data.total == '0'){
					var body = $(this).data().datagrid.dc.body2;
					body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">请选择回访日期查询跟踪满意度统计信息！</td></tr>');
					}
			}
	});
});

//跟踪满意度统计添加不固定列方法
function addColome(resault,formvalue){
	var str1 ="[[";
	var str2 = "";
	var array = new Array();
	array = resault.substring(18,resault.indexOf("}")).split(",");
	for ( var i = 0; i < array.length; i++) {
	var str = array[i].substring(5,12);
			str2 = str2 + ",{field : 'date"+str+"',title : '"+str+"',width : 80,sortable : true, align : 'center'}";
	}
	var newstr2 = str2.substring(1);
	var str3 = "]]";
	var laststr = str1 + newstr2 + str3;
		 options = {};
		 options.url = 'vTrackRecordAction!getSatisfaction.action?returnVisitDate='+formvalue.returnVisitDate;
		 options.columns = eval(laststr);
			 $('#satisfaction_degree_id').datagrid(options);
			 $('#satisfaction_degree_id').datagrid('reload');
} 
//条件查询提交
function doConditionSubmit(){
	$('#satisfaction_degree_id').datagrid('unselectAll');
	$('#satisfaction_degree_id').datagrid('load',serializeObject($('#customer_GzTj_form_id')));	
	var form =  $('#customer_GzTj_form_id').form();
	var formvalue = serializeObject(form);	
	if(tbtitle=="客户满意度统计"){
	//同时访问xxx方法获取要显示的月份以及月份对应的满意度统计信息
	$.ajax({
		type : 'POST', 
		url : 'vTrackRecordAction!getSatisfaction.action',
		data : formvalue,
		success : function(r){
				addColome(r,formvalue);
				/*var rndImg = document.getElementById('imgid');
						rndImg.setAttribute('src', 'vTrackRecordAction_getJfreeChart.action');*/
			}
		});
	}
}
	