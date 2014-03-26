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
	    var ptypeName=$('#ptypeName').val();
	    if(ptypeName!=''&& ptypeName!=null){
	       $('#pnc_ptypeName').attr("value",ptypeName);
	       $.ajax({
				type : 'post',
				url : 'StPartsNowCountAction!searchPartsTypeByCondition.action',
				data : $('#partsnowcount_partstypeform').serialize(),
				dataType : 'json',
				success : function(r){
			               $('#partsnowcount_partstypetable').datagrid('loadData',r);
	 	        }
	       });
	    }
	    $('#ptypeName').val('');
 } 
	
 $(function(){
	  $('#partsnowcount_partstypetable').datagrid({
			 url:'StPartsNowCountAction!loadPartsType.action',
			 idField : 'ptypeId',
			 pagination : true,
			 sortName:'ptypeId',
			 sortOrder:'asc',
			 fit : true,
			 pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 rownumbers : true,
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'型号ID',field:'ptypeId',width:100,sortable:true},
			 			{title:'型号名称',field:'ptypeName',width:100,sortable:true}
			        ]],
			 onDblClickRow:function(rowIndex , rowData){
			      $('#ptypeName').val(rowData.ptypeName);
			      pnc_d3.dialog('close');
			 }
	  });
 })
 function _query()
 {
	$.ajax({                       
		type : "POST",
		url : "StPartsNowCountAction!searchPartsTypeByCondition.action",
		data : $('#partsnowcount_partstypeform').serialize(),
		dataType : "json",
		success : function callback(r){
		   $('#partsnowcount_partstypetable').datagrid('loadData',r);
	    }
	});
 }
 function _clear()
 {
    $('#partsnowcount_partstypeform').form('clear');
    $('#partsnowcount_partstypetable').datagrid('load');
 }