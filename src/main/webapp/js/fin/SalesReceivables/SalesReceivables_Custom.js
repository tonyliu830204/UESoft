function   LoadOk(){
	     if(document.readyState   =="complete"){
		   initFrame();
		 }else{
		   setTimeout("LoadOk()",200);
		 }
  }
  setTimeout("LoadOk()",200);
  //判断页面初始化加载完成    执行
  function   initFrame(){
      var sellcustomName=$('#sellcustomName').val();
      if(sellcustomName!=''){ 
          $('#slo_customName').attr("value",sellcustomName);
          $.ajax({
			type : 'post',
			url : 'StSellOrderAction_searchBasCustomByCondition.action',
			data : 'customId='+$('#slo_customId').val()+"&customName="+$('#slo_customName').val(),
			dataType : 'json',
			success : function(r){
		               $('#sle_customInfoTable').datagrid('loadData',r);
   	               }
          });
      }
      $('#sellcustomName').val('');
  }


$(function(){
	 //客户信息选择
	 $('#sle_customInfoTable').datagrid({
		 url:projectPath+'StSellOrderAction_loadBasCustom.action',
		 pagination:true,
	     fit:true,
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'customId',
		 loadMsg : "数据加载中，请稍后……",
		 columns:[[ {title:'客户编号',field:'customId',width:100},
		 			{title:'客户名称',field:'customName',width:100},
		         ]],
		 onDblClickRow:function(rowIndex, rowData){
             $('#sellcustomId').val(rowData.customId);
             $('#sellcustomName').val(rowData.customName);
             slo_d2.dialog('close');
		 }
	 });
})

 //客户编号，名称查询
function searchByCondition()
{
	var formvalue =serializeObject($('#CustomerSelectForm'));
	$.ajax({                       //更新销售单汇总信息
		type : "POST",
		url : "StSellOrderAction_searchBasCustomByCondition.action",
		data : 'customId='+$('#slo_customId').val()+"&customName="+$('#slo_customName').val(),
		dataType : "json",
		success : function callback(r) {
		   $('#sle_customInfoTable').datagrid('loadData',r);
	    }
	}); 
}

//客户信息全部显示
function clearSearchByCondition()
{
	$('#sle_customerSelectForm').form('clear');
	$('#sle_customInfoTable').datagrid('load');
}