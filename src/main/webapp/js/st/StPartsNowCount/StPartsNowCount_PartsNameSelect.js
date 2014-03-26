function   LoadOk(){
			     if(document.readyState   =="complete"){
		   initFrame();
		 }else{
		   setTimeout("LoadOk()",200);
		 }
   }
   setTimeout("LoadOk()",200);
   //判断页面初始化加载完成    执行
   function   initFrame()
   {
	    var partsName=$('#partsName').val();
	    if(partsName!=''&& partsName!=null){
	       $('#pnc_partsName').attr("value",partsName);
	       $.ajax({
				type : 'post',
				url : 'StPartsNowCountAction!searchPartsNameByCondition.action',
				data : $('#partsnoewcount_selectpartsform').serialize(),
				dataType : 'json',
				success : function(r){
			               $('#partsnoewcount_selectpartstable').datagrid('loadData',r);
	 	        }
	       });
	    }
	    $('#partsName').val('');
  } 
  $(function(){
	  $('#partsnoewcount_selectpartstable').datagrid({
			 url:'StPartsNowCountAction!loadPartsName.action',
			 idField : 'partsId',
			 pagination : true,
			 sortName:'partsId',
			 sortOrder:'asc',
			 fit : true,
			 pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 rownumbers : true,
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'配件ID',field:'partsId',width:100,sortable:true},
			 			{title:'配件名称',field:'partsName',width:100,sortable:true}
			        ]],
			 onDblClickRow:function(rowIndex , rowData){
			      $('#partsName').val(rowData.partsName);
			      pnc_d2.dialog('close');
			 }
	  });
 })
 
 function _query()
 {
	$.ajax({                     
		type : "POST",
		url : "StPartsNowCountAction!searchPartsNameByCondition.action",
		data : $('#partsnoewcount_selectpartsform').serialize(),
		dataType : "json",
		success : function callback(r){
		   $('#partsnoewcount_selectpartstable').datagrid('loadData',r);
	    }
	});
 }
 
 function _clear()
 {
    $('#partsnoewcount_selectpartsform').form('clear');
    $('#partsnoewcount_selectpartstable').datagrid('load');
 }