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
      var sellmmStfName=$('#sms_msdmManagerName').val();
  if(sellmmStfName!=''){ 
      $('#sms_stfName').attr("value",sellmmStfName);
      $.ajax({
		type : 'post',
		url : 'StSellOrderAction!searchPickingMemberByCondition.action',
		data : $('#stmovestorehouse_employeeform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stmovestorehouse_employeetable').datagrid('loadData',r);
   	               }
          });
    }
   }
  
  $(function(){
   $('#stmovestorehouse_employeetable').datagrid({
	 url:'StSellOrderAction!loadPickingMember.action',
	 pagination:true,
     fit:true,
     singleSelect:true,
	 fitColumns : true,
	 pageSize : 10,
     sortName:'stfId',
	 sortOrder:'asc',
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 idField : 'stfId',
	 loadMsg : "数据加载中，请稍后……",
	 columns:[[ {title:'领料人员编号',field:'stfId',width:100},
	 			{title:'领料人员名称',field:'stfName',width:100}
	        ]],
	 onDblClickRow:function(rowIndex, rowData){
            $('#sms_msdmManagerName').val(rowData.stfName);
            sms_d2.dialog('close');
		 }
	 });
  });

    //根据领料员ID和领料员名称查询
function _query()
{
    $.ajax({                       //更新销售单汇总信息
		type : "POST",
		url : "StSellOrderAction!searchPickingMemberByCondition.action",
		data : $('#stmovestorehouse_employeeform').serialize(),
		dataType : "json",
		success : function callback(r) {
		   $('#stmovestorehouse_employeetable').datagrid('loadData',r);
	    }
	});
}

    //领料员信息全查
function _clear()
{
	$('#stmovestorehouse_employeeform').form('clear');
	$('#stmovestorehouse_employeetable').datagrid('load');
}