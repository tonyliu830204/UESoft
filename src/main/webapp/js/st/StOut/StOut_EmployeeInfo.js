//判断页面初始化加载是否完成
  function   LoadOk(){
	     if(document.readyState   =="complete")
		   initFrame();
		 else
		   setTimeout("LoadOk()",200);
  }
  setTimeout("LoadOk()",200);
  
  //判断页面初始化加载完成    执行
  function   initFrame()
  {
      var pickingMemberName=$('#pickingMemberName').val();
      if(pickingMemberName!='')
      { 
          $('#so_pickingMemberName').attr("value",pickingMemberName);
          $.ajax({
				type : 'post',
				url : 'StSellOrderAction!searchPickingMemberByCondition.action',
				data : $('#stout_employeeform').serialize(),
				dataType : 'json',
				success : function(r){
			         $('#tout_employeetable').datagrid('loadData',r);
	   	        }
           });
      }
  }

$(function(){
    $('#tout_employeetable').datagrid({
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
		 columns : [[{title : '领料员编号',field : 'stfId',sortable:true,width : 100
				    }, {title : '领料员名称',field : 'stfName',sortable:true,width : 100
				    }]],
	      onDblClickRow:function(rowIndex, rowData){
	        var selections = $('#tout_employeetable').datagrid('getSelections');
		    if (selections != null) {
				 $('#pickingMember').val(selections[0].stfId);
				 $('#pickingMemberName').val(selections[0].stfName);
				 $('#stout_employeeform').form('clear');
				 so_d1.dialog('close');
		    }
	      }
	    }); 		
    })	 

    function _query()
    {
   	    $.ajax({
			type : 'post',
			url : 'StSellOrderAction!searchPickingMemberByCondition.action',
			data :$('#stout_employeeform').serialize(),
			dataType : 'json',
			success : function(r){
		               $('#tout_employeetable').datagrid('loadData',r);
   	               }
        });
    }
		  
    function _clear(){
      $('#stout_employeeform').form('clear');
      $('#tout_employeetable').datagrid('load');
    }