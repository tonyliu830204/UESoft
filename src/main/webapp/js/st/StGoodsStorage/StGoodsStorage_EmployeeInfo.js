//判断页面初始化加载是否完成
  function   LoadOk(){
	     if(document.readyState   =="complete")
	   initFrame();
	 else
	   setTimeout("LoadOk()",200);
  }
  setTimeout("LoadOk()",200);
  //判断页面初始化加载完成    执行
  function   initFrame(){
      var identifymanName=$('#identifymanName').val();
  if(identifymanName!=''){ 
     $('#sgsm_stfName').attr("value",identifymanName);
      $.ajax({
		type : 'post',
		url : 'StSellOrderAction!searchPickingMemberByCondition.action',
		data : $('#stgoodstorage_employeeform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stgoodstorage_employeetable').datagrid('loadData',r);
   	               }
          });
      }
  }
  $(function(){
    $('#stgoodstorage_employeetable').datagrid({
		 url:'StSellOrderAction!loadPickingMember.action',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     sortOrder:'asc',
	     sortName:'stfId',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
		 rownumbers:true,
		 idField : 'stfId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[{title : '接待员编号',field : 'stfId',sortable:true,width : 100
				   }, {title : '员工名称',field : 'stfName',sortable:true,width : 100
				   }]],
	      onDblClickRow:function(rowIndex, rowData){
	        var selections = $('#stgoodstorage_employeetable').datagrid('getSelections');
		    if (selections != null) {
				 $('#identifyman').val(selections[0].stfId);
				 $('#identifymanName').val(selections[0].stfName);
				 $('#stgoodstorage_employeeform').form('clear');
				 sgsm_d3.dialog('close');
		    }
	      }
	    }); 		
  })	 
  
  function _query(){
    $.ajax({
		type : 'post',
		url : 'StSellOrderAction!searchPickingMemberByCondition.action',
		data : $('#stgoodstorage_employeeform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stgoodstorage_employeetable').datagrid('loadData',r);
   	               }
        });
	  } 	 
  
	  function _clear(){
	      $('#stgoodstorage_employeeform').form('clear');
      $('#stgoodstorage_employeetable').datagrid('load');
  }