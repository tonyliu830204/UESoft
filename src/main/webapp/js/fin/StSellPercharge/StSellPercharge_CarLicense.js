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
	    var carLicense=$('#ssp_carLicense').val();
	    if(carLicense!=''){ 
	       $('#sspc_carLicense').attr("value",carLicense);
	       $.ajax({
				type : 'post',
				url : 'StSellPerchargeAction_searchFrtCarInfoByCondition.action',
				data : "carLicense="+carLicense,
				dataType : 'json',
				success : function(r){
			               $('#ssp_CarInfoTable').datagrid('loadData',r);
	 	        }
	       });
	   }
	   $('#ssp_carLicense').val('');
  }  
  $(function(){
	  $('#ssp_CarInfoTable').datagrid({
			 url:projectPath+'StSellPerchargeAction_loadFrtCarInfo.action',
			 pagination:true,
		     fit:true,
		     sortName:'carId',
		     sortOrder:'asc',
		     pageSize : 10,
		     singleSelect:true,
			 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
			 fitColumns : true,
			 idField : 'carId',
			 loadMsg : "数据加载中，请稍后……",
			 columns:[[ {title:'车辆编号',field:'carId',width:100},
			 			{title:'车牌照',field:'carLicense',width:100},
			 			{title:'车辆品牌',field:'cbrdName',width:100},
			 			{title:'车辆型号',field:'ctypeName',width:100},
			 			{title:'客户名称',field:'customName',width:100},
			 			{title:'联系人',field:'carRelationPerson',width:100},
			 			{title:'电话一',field:'carRelationTel1',width:100},
			 			{title:'电话二',field:'carRelationTel2',width:100}
			        ]],
			 onDblClickRow:function(rowIndex, rowData){
		         $('#ssp_carId').val(rowData.carId);
			     $('#ssp_carLicense').val(rowData.carLicense);
			     $('#ssp_cbrdName').val(rowData.cbrdName);
			     $('#ssp_ctypeName').val(rowData.ctypeName);
			     $('#ssp_customName').val(rowData.customName);
			     $('#ssp_carRelationPerson').val(rowData.carRelationPerson);
			     $('#ssp_carRelationTel1').val(rowData.carRelationTel1);
			     $('#ssp_carRelationTel2').val(rowData.carRelationTel2);
			     pre_ssp_d2.dialog('close');
			 }
		    });
 })
 function sspc_searchByCondition()
 {
	$.ajax({           
		type : "POST",
		url : "StSellPerchargeAction_searchFrtCarInfoByCondition.action",
		data : 'carLicense='+$('#sspc_carLicense').val(),
		dataType : "json",
		success : function callback(r) {
		   $('#ssp_CarInfoTable').datagrid('loadData',r);
	    }
	});
}
function clearSearchCondition()
{
    $('#ssp_carInfoForm').form('clear');
    $('#ssp_CarInfoTable').datagrid('load');
}