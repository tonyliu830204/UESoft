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
	      var relcampName=$('#stRelcampName').val();
          if(relcampName!=null&&relcampName!='') { 
              $('#relcampName11').val(relcampName);
              $.ajax({
					type : 'post',
					url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
					data : $('#stpurorder_basrelationcampanymainform').serialize(),
					dataType : 'json',
					success : function(r){
			               $('#stpurorder_basrelationcampanymaintable').datagrid('loadData',r);
		   	        }
	          });
          }
	  }
                  
 $(function(){
         $('#stpurorder_basrelationcampanymaintable').datagrid({
		 url:'StGoodsStorageAction!loadBasRelationCampany.action',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     sortOrder:'asc',
	     sortName:'relcampId',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true,
		 rownumbers:true,
		 idField : 'id',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[ {title : '编号',field : 'relcampId',sortable:true,width : 50
			}, {title : '供应商',field : 'relcampName',sortable:true,width : 100
			}, {title : '联系电话',field : 'relcampTel1',sortable:true,width : 100
			}, {title : '联系人',field : 'relcampContact',sortable:true,width : 100
		}]],onDblClickRow:function(rowIndex, rowData){
           var selections = $('#stpurorder_basrelationcampanymaintable').datagrid('getSelections');
		   if (selections != null) {
				$('#stRelcampName').val(selections[0].relcampName);
				$('#stpurorder_basrelationcampanymainform').form('clear');
				$('#stpurorder_basrelationcampanymaintable').datagrid('unselectAll');
				d2.dialog('close');
			   }
	         }
	    });
   })	
			  
//采购单汇总：供应商信息选择：模糊查询 
function _query()
{
    $.ajax({
		type : 'post',
	url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
	data : $('#stpurorder_basrelationcampanymainform').serialize(),
	dataType : 'json',
	success : function(r){
           $('#stpurorder_basrelationcampanymaintable').datagrid('loadData',r);
        }
    });
} 	 
				  
//采购单汇总：供应商信息选择：清空查询条件
function _clear()
{
  $('#stpurorder_basrelationcampanymainform').form('clear');
  $('#stpurorder_basrelationcampanymaintable').datagrid('load');
}