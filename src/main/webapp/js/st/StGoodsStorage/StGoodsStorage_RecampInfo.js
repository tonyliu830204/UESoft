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
      var relcampName=$('#sgmsm_relcampName').val();
  if(relcampName!='')
  { 
     $('#sgmss_relcampName').val(relcampName);
      $.ajax({
		type : 'post',
		url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
		data : $('#stgoodstorage_basrelationcampform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stgoodstorage_basrelationcamptable').datagrid('loadData',r);
   	               }
          });
      }
  }
  
  $(function (){
      
   //供应商信息datagrid
   $('#stgoodstorage_basrelationcamptable').datagrid({
 url:'StGoodsStorageAction!loadBasRelationCampany.action',
 idField : 'relcampId',
 pagination:true,
 fit:true,
 singleSelect:true,
 pageSize : 10,
 sortOrder:'asc',
 sortName:'relcampId',
 rownumbers:true,
 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
 fitColumns : true,
 loadMsg : "数据加载中，请稍后……",
 columns : [ [ {title : '编号',field : 'relcampId',sortable:true,width : 50
			}, {title : '供应商',field : 'relcampName',sortable:true,width : 100
			}, {title : '联系电话',field : 'relcampTel1',sortable:true,width : 100
			}, {title : '联系人',field : 'relcampContact',sortable:true,width : 100
			} ]],
	onDblClickRow:function(rowIndex, rowData){    
			$('#sgmsm_relcampName').val(rowData.relcampName);
			sgsm_d1.dialog('close');
       }
    });
 })
 
 function _query()
 {
     $.ajax({
			type : 'post',
			url : 'StGoodsStorageAction!searchBasRelationCampanyByCondition.action',
			data : $('#stgoodstorage_basrelationcampform').serialize(),
			dataType : 'json',
			success : function(r){
		               $('#stgoodstorage_basrelationcamptable').datagrid('loadData',r);
	   	        }
		  });
	  } 	        
   
      function _clear()
      {
          $('#stgoodstorage_basrelationcampform').form('clear');
      $('#stgoodstorage_basrelationcamptable').datagrid('load');
  }