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
       var srp_stfName=$('#srp_stfName').val();
       if(srp_stfName!=''){ 
              $('#srpss_stfName').attr("value",srp_stfName);
              $.ajax({
				type : 'post',
				url : 'StSellOrderAction!searchPickingMemberByCondition.action',
				data : 'stfId='+$('#srpss_stfId').val()+'&stfName='+$('#srpss_stfName').val(),
				dataType : 'json',
				success : function(r){
			               $('#srp_stufTable').datagrid('loadData',r);
	   	               }
	          });
        }
      $('#srp_stfName').val('');
	   }

   
       $(function(){
  		 $('#srp_stufTable').datagrid({
		 url:'StSellOrderAction!findPartsStorehousePerson.action',
		 pagination:true,
	     fit:true,
	     pageSize : 10,
	     sortName:'stfId',
	     sortOrder:'asc',
	     singleSelect:true,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 idField : 'stfId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[ {
					title : '接待员编号',
					field : 'stfId',
					width : 100
				 },{
					title : '员工名称',
					field : 'stfName',
					width : 100
				 }
  			         ]],
  		    onDblClickRow:function(rowIndex, rowData){
  			   $('#srp_stfId').val(rowData.stfId);
  			   $('#srp_stfName').val(rowData.stfName);
  			   srp_d2.dialog('close');
  		    }
	 });		
   })
   
   function srp_searchByStufCondition(){
	   $.ajax({
			type : 'post',
			url : 'StSellOrderAction!searchPickingMemberByCondition.action',
			data : "stfId="+$('#srpss_stfId').val()+"&stfName="+$('#srpss_stfName').val(),
			dataType : 'json',
			success : function(r){
		               $('#srp_stufTable').datagrid('loadData',r);
   	        }
       });
   }
   function srp_clearByStufCondition(){
	   $('#srp_stufForm').form('clear');
	   $('#srp_stufTable').datagrid('load');
   }