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
	    var pbrdName=$('#pbrdName').val();
	    if(pbrdName!=''&& pbrdName!=null){
	       $('#pnc_pbrdName').attr("value",pbrdName);
	       $.ajax({
				type : 'post',
				url : 'StPartsNowCountAction!searchPartsBrandByCondition.action',
				data : "pbrdName="+pbrdName,
				dataType : 'json',
				success : function(r){
			               $('#partsnowcount_partsbrandtable').datagrid('loadData',r);
	 	        }
	       });
	   }
	   $('#pbrdName').val('');
  } 
  
  $(function(){
	  $('#partsnowcount_partsbrandtable').datagrid({
			 url:'StPartsNowCountAction!loadPartsBrand.action',
			 idField : 'partsId',
			 pagination : true,
			 sortName:'cbrdId',
			 sortOrder:'asc',
			 fit : true,
			 pageSize : 10,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 rownumbers : true,
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'品牌ID',field:'cbrdId',width:100,sortable:true},
			 			{title:'品牌名称',field:'cbrdName',width:100,sortable:true}
			        ]],
			 onDblClickRow:function(rowIndex, rowData){
			      $('#pbrdName').val(rowData.cbrdName);
			      pnc_d1.dialog('close');
			 }
	  });
 })
 
function _query()
{
	$.ajax({                       //更新销售单汇总信息
		type : "POST",
		url : "StPartsNowCountAction!searchPartsBrandByCondition.action",
		data : 'pbrdId='+$('#pnc_pbrdId').val()+'&pbrdName='+$('#pnc_pbrdName').val(),
		dataType : "json",
		success : function callback(r) {
		   $('#partsnowcount_partsbrandtable').datagrid('loadData',r);
	    }
	});
}
 
function _clear()
{
    $('#partsnowcount_partsbrandform').form('clear');
    $('#partsnowcount_partsbrandtable').datagrid('load');
}