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
		      var umsdmManagerName=$('#umsdmManagerName').val();
	          if(umsdmManagerName!=''){ 
	              $('#usms_stfName').attr("value",umsdmManagerName);
	              $.ajax({
					type : 'post',
					url : 'StSellOrderAction!searchPickingMemberByCondition.action',
					data : $('#stmovestorehouse_uemployeeform').serialize(),
					dataType : 'json',
					success : function(r){
				         $('#stmovestorehouse_uemployeetable').datagrid('loadData',r);
		   	        }
		        });
	          }
		  }
		  
          $(function(){
       	   $('#stmovestorehouse_uemployeetable').datagrid({
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
      	             $('#umsdmManagerName').val(rowData.stfName);
      	             sms_d3.dialog('close');
      			 }
       		   });
            });
	
	        //根据领料员ID和领料员名称查询
      	    function _query()
      	    {
      	        $.ajax({                       //更新销售单汇总信息
      				type : "POST",
      				url : "StSellOrderAction!searchPickingMemberByCondition.action",
      				data : $('#stmovestorehouse_uemployeeform').serialize(),
      				dataType : "json",
      				success : function callback(r) {
      				   $('#stmovestorehouse_uemployeetable').datagrid('loadData',r);
      			    }
      			});
      	    }
	
	      	    //领料员信息全查
      	    function _clear()
      	    {
      	    	$('#stmovestorehouse_uemployeeform').form('clear');
      	    	$('#stmovestorehouse_uemployeetable').datagrid('load');
      	    }