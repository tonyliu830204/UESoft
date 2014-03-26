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
	    var carLicense=$('#carLicense').val();
	    if(carLicense!=''){ 
	       $('#slo_carLicense').attr("value",carLicense);
	       $.ajax({
				type : 'post',
				url : 'StSellOrderAction!searchCarLicenseByCondition.action',
				data : "carLicense="+carLicense,
				dataType : 'json',
				success : function(r){
			               $('#slo_CarLicenseTable').datagrid('loadData',r);
	 	        }
	       });
	   }
  } 
  
  $(function(){
	  $('#slo_CarLicenseTable').datagrid({
			 url:'StSellOrderAction!loadCarLicense.action',
			 pagination:true,
		     fit:true,
		     sortName:'carLicense',
		     sortOrder:'asc',
		     pageSize : 10,
		     singleSelect:true,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 idField : 'carLicense',
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'车牌照',field:'carLicense',width:100},
			 			{title:'vin号',field:'carVin',width:100},
			 			{title:'发动机',field:'carMotorId',width:100},
			 			{title:'电话',field:'carRelationTel1',width:100},
			 			{title:'客户ID',field:'customId',width:100,hidden:true},
			 			{title:'客户名称',field:'customName',width:100},
			        ]],
			 onDblClickRow:function(rowIndex, rowData){
			     $('#carLicense').val(rowData.carLicense);
			     $('#sellcustomName').val(rowData.customName);
			     $('#sellcustomId').val(rowData.customId);
			     slo_d1.dialog('close');
			 }
		    });
 })
 
 //车牌照。VIN号，发动机号查询
 function searchByCondition()
 {
	$.ajax({                       //更新销售单汇总信息
		type : "POST",
		url : "StSellOrderAction!searchCarLicenseByCondition.action",
		data : 'carLicense='+$('#slo_carLicense').val()+'&carVin='+$('#slo_carVan').val()+'&carMotorId='+$('#slo_carMotorId').val(),
		dataType : "json",
		success : function callback(r) {
		   $('#slo_CarLicenseTable').datagrid('loadData',r);
	    }
	});
}
 
//车牌照。VIN号，发动机号查询
function clearSearchCondition()
{
    $('#slo_carLicenceForm').form('clear');
    $('#slo_CarLicenseTable').datagrid('load');
}