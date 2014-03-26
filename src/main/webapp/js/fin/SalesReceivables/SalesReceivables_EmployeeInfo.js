//判断页面初始化加载是否完成
  function   LoadOk()
  {
	     if(document.readyState   =="complete")
     {
	   initFrame();
	 }else
	 {
	   setTimeout("LoadOk()",200);
		 }
  }
  setTimeout("LoadOk()",200);
  //判断页面初始化加载完成    执行
  function   initFrame()
  {
      var buyerName=$('#buyerName').val();
  if(buyerName!='')
  { 
     $('#stfName').attr("value",buyerName);
      $.ajax({
		type : 'post',
		url : 'StSellOrderAction_searchPickingMemberByCondition.action',
		data : "stfId="+$('#stfId').val()+"&stfName="+buyerName,
		dataType : 'json',
		success : function(r){
	               $('#EmployInfo').datagrid('loadData',r);
               }
      });
  }
  $('#buyer').val('');
	  }

   //采购人员信息
$(function(){
    $('#EmployInfo').datagrid({
		 url:projectPath+'StSellOrderAction_loadPickingMember.action',
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
		 columns : [[ {
					title : '接待员编号',field : 'stfId',sortable:true,width : 100
				       }, {
					title : '员工名称',field : 'stfName',sortable:true,width : 100
				       }
		            ]],
	      onDblClickRow:function(rowIndex, rowData){
	        var selections = $('#EmployInfo').datagrid('getSelections');
		    if (selections != null) {
				 $('#buyer').val(selections[0].stfId);
				 $('#buyerName').val(selections[0].stfName);
				 $('#spo_basStuffForm').form('clear');
				 d1.dialog('close');
		    }
	      }
	    }); 		
  })	 

  //采购单汇总：供应商信息选择：模糊查询 
  function searchBasStuff()
  {
    $.ajax({
		type : 'post',
		url : 'StSellOrderAction_searchPickingMemberByCondition.action',
		data : "stfId="+$('#stfId').val()+"&stfName="+$('#stfName').val(),
		dataType : 'json',
		success : function(r){
	               $('#EmployInfo').datagrid('loadData',r);
               }
    });
  } 	 
			  
  //采购单汇总：供应商信息选择：清空查询条件
  function clearSerachCondition()
  {
      $('#spo_basStuffForm').form('clear');
      $('#EmployInfo').datagrid('load');
  }