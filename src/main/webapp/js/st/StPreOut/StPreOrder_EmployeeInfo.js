function   LoadOk(){
						     if(document.readyState   =="complete")
				   initFrame();
				 else
				   setTimeout("LoadOk()",200);
		  }
	      setTimeout("LoadOk()",200);
		  function   initFrame()
		  {
		      var stfName=$('#stfName').val();
              if(stfName!='')
              { 
	              $('#spo_stfName').attr("value",stfName);
	              $.ajax({
					type : 'POST',
					url : 'StPreOutAction!searchBasStuffByCondition.action',
					data : $('#stpreorder_employeeform').serialize(),
					dataType : 'json',
					success : function(r){
				               $('#stpreorder_employeetable').datagrid('loadData',r);
		   	               }
		          });
	          }
              $('#stfName').val('');
				  }
		
   //领用人员信息
$(function(){
    $('#stpreorder_employeetable').datagrid({
	 //url:'StPreOutAction!loadBasStuff.action',
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
	 columns : [[ {title : '领料员编号',field : 'stfId',sortable:true,width : 100
			       }, {title : '领料员名称',field : 'stfName',sortable:true,width : 100
			       }]],
      onDblClickRow:function(rowIndex, rowData){
        var selections = $('#stpreorder_employeetable').datagrid('getSelections');
	    if (selections != null) {
			 $('#spom_stfId').val(selections[0].stfId);
			 $('#stfName').val(selections[0].stfName);
			 $('#repgrpName').val(selections[0].repgrpName);
			 spo_d2.dialog('close');
	    }
      }
    }); 		
  })	 
  
  function _query()
  {
    $.ajax({
		type : 'POST',
		url : 'StPreOutAction!searchBasStuffByCondition.action',
		data : $('#stpreorder_employeeform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stpreorder_employeetable').datagrid('loadData',r);
               }
    });
  } 	 
		  
  function _clear()
  {
      $('#stpreorder_employeeform').form('clear');
      $('#stpreorder_employeetable').datagrid('load');
  }