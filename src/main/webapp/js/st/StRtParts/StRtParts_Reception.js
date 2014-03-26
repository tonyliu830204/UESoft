function   LoadOk(){
	  if(document.readyState =="complete") 
		   initFrame();
	  else
		   setTimeout("LoadOk()",1000);
}
  
setTimeout("LoadOk()",1000);
  
function  initFrame(){
      var receptionId=$('#srp_receptionId').val();
      if(receptionId!=''){ 
         $('#srps_receptionId').attr("value",receptionId);
         _query();
      }
}

$(function(){
	//工单信息显示
	$('#srp_receptionTable').datagrid({
		 url:projectPath+'StRtPartsAction!loadFrtReception.action',
		 pagination:true,
	     fit:true,
	     pageSize : 10,
	     sortName:'receptionId',
	     sortOrder:'asc',
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
		 idField : 'id',
		 loadMsg : "数据加载中，请稍后……",
	     columns : [ [  {title : '工单号',field : 'receptionId',width : 130
			}, {title : '车牌',field : 'carLicense',width : 100
			}, {title : '车类型',field : 'ctypeName',width : 100
			}, {title : '日期',field : 'resvRealTime',width : 100
			}, {title : '客户名称',field : 'customName',width : 100
			}, {title : '前台接待',field : 'manager',width : 100
			}]],
		    onDblClickRow:function(rowIndex, rowData){
		     $('#srp_carLicense').val(rowData.carLicense);
		     $('#srp_receptionId').val(rowData.receptionId);
		     $('#srp_customName').val(rowData.customName);
		     srp_d1.dialog('close');
	         $('#srp_strtpmSumCnt').val(0);
	         $('#srp_strtpmAmont').val(0);
	         $('#srp_strtpmCostAmont').val(0);
		     $.ajax({
					type : 'POST',
					url : 'StRtPartsAction!loadParts.action',
					data : 'receptionId='+rowData.receptionId,
					dataType : 'json',
					success : function(r){
		    	        $('#StRtPartsDetailTable').datagrid('loadData',r);
	    	        }
		     });
	      }
	 });
});
    
function _query(){
   $.ajax({
		type : 'post',
		url : 'StRtPartsAction!searchFrtReceptionByCondition.action',
		data : $('#srp_receptionForm').serialize(),
		dataType : 'json',
		success : function(r){
	         $('#srp_receptionTable').datagrid('loadData',r);
        }
   });
}

function _clear(){
   $('#srp_receptionForm').form('clear');
   $('#srp_receptionTable').datagrid('load');
}