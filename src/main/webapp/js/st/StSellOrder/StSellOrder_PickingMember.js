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
       var sellmmStfName=$('#sellmmStfName').val();
       if(sellmmStfName!=''){ 
              $('#slo_stfName').attr("value",sellmmStfName);
              $.ajax({
				type : 'post',
				url : 'StSellOrderAction!searchPickingMemberByCondition.action',
				data :  $('#slo_pickingMemberForm').serialize(),
				dataType : 'json',
				success : function(r){
			               $('#slo_pickingMemberTable').datagrid('loadData',r);
	   	               }
	          });
        }
   }
   $(function(){
	   $('#slo_pickingMemberTable').datagrid({
			 url:'StSellOrderAction!loadPickingMember.action',
			 pagination:true,
		     fit:true,
		     pageSize : 10,
		     singleSelect:true,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 idField : 'stfId',
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'领料人员编号',field:'stfId',width:100},
			 			{title:'领料人员名称',field:'stfName',width:100}
			        ]],
			 onDblClickRow:function(rowIndex, rowData){
	             $('#sellmmStfId').val(rowData.stfId);
	             $('#sellmmStfName').val(rowData.stfName);
	             slo_d3.dialog('close');
			 }
		 });
    });
    function _query()
    {
    	$.ajax({                       //更新销售单汇总信息
			type : "post",
			url : "StSellOrderAction!searchPickingMemberByCondition.action",
			data : $('#slo_pickingMemberForm').serialize(),
			dataType : "json",
			success : function callback(r) {
			   $('#slo_pickingMemberTable').datagrid('loadData',r);
		    }
		}); 
    }
    function _clear()
    {
    	$('#slo_pickingMemberForm').form('clear');
    	$('#slo_pickingMemberTable').datagrid('load');
    }