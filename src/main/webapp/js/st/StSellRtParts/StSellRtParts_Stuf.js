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
       var ssrp_stfName=$('#ssrp_stfName').val();
       if(ssrp_stfName!=''){ 
              $('#ssrps_stfName').attr("value",ssrp_stfName);
              $.ajax({
				type : 'post',
				url : 'StSellOrderAction!searchPickingMemberByCondition.action',
				data : 'stfId='+$('#ssrps_stfId').val()+'&stfName='+$('#ssrps_stfName').val(),
				dataType : 'json',
				success : function(r){
			               $('#ssrps_stufTable').datagrid('loadData',r);
	   	               }
	          });
        }
      $('#sellmmStfName').val('');
	   }
   
       $(function(){
  		 $('#ssrps_stufTable').datagrid({
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
  			   $('#ssrp_stfId').val(rowData.stfId);
  			   $('#ssrp_stfName').val(rowData.stfName);
  			   ssrp_d2.dialog('close');
  		    }
	 });		
   })
   
   function ssrp_searchByStufCondition(){
	   $.ajax({
			type : 'post',
			url : 'StSellOrderAction!searchPickingMemberByCondition.action',
			data : "stfId="+$('#ssrps_stfId').val()+"&stfName="+$('#ssrps_stfName').val(),
			dataType : 'json',
			success : function(r){
		               $('#ssrps_stufTable').datagrid('loadData',r);
   	        }
       });
   }

   function ssrp_clearByStufCondition(){
	   $('#ssrp_stufForm').form('clear');
	   $('#ssrps_stufTable').datagrid('load');
   }