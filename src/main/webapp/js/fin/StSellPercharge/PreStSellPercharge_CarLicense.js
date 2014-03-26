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
	    var carLicense=$('#pre_ssp_carLicense').val();
    if(carLicense!=''){ 
       $('#sspc_carLicense').attr("value",carLicense);
       $.ajax({
			type : 'post',
			url : 'StSellPerchargeAction_searchFrtCarInfoByCondition.action',
			data : "carLicense="+carLicense,
			dataType : 'json',
			success : function(r){
		               $('#pre_ssp_CarInfoTable').datagrid('loadData',r);
 	        }
       });
   }
   $('#pre_ssp_carLicense').val('');
  }  
  $(function(){
	  $('#pre_ssp_CarInfoTable').datagrid({
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
	         $('#pre_ssp_carId').val(rowData.carId);
		     $('#pre_ssp_carLicense').val(rowData.carLicense);
		     $('#pre_ssp_cbrdName').val(rowData.cbrdName);
		     $('#pre_ssp_ctypeName').val(rowData.ctypeName);
		     $('#pre_ssp_customName').val(rowData.customName);
		     $('#pre_ssp_carRelationPerson').val(rowData.carRelationPerson);
		     $('#pre_ssp_carRelationTel1').val(rowData.carRelationTel1);
		     $('#pre_ssp_carRelationTel2').val(rowData.carRelationTel2);
		     ssp_d2.dialog('close');
			 }
		    });
 })
 function sspc_searchByCondition()
 {
	$.ajax({           
		type : "POST",
	url : "StSellPerchargeAction_searchFrtCarInfoByCondition.action",
	data : 'carLicense='+$('#pre_sspc_carLicense').val(),
	dataType : "json",
	success : function callback(r) {
	   $('pre_#ssp_CarInfoTable').datagrid('loadData',r);
	    }
	});
}
function clearSearchCondition()
{
    $('#pre_ssp_carInfoForm').form('clear');
$('#pre_ssp_CarInfoTable').datagrid('load');
}