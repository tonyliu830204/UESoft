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
      var ssrp_sellmmId=$('#ssrp_sellmmId').val();
  if(ssrp_sellmmId!='')
  { 
     $('#ssrps_sellmmId').attr("value",ssrp_sellmmId);
     $.ajax({
			type : 'post',
			url : 'StSellRtPartsAction!searchStSellOrderByCondition.action',
			data : $('#ssrp_receptionForm').serialize(),
			dataType : 'json',
			success : function(r){
		       $('#ssrp_receptionTable').datagrid('loadData',r);
   	        }
      });
  }
  $('#ssrp_sellmmId').val('');
  }

$(function(){
	//销售信息显示
$('#ssrp_receptionTable').datagrid({
	 url:'StSellRtPartsAction!loadStSellOrder.action',
	 pagination:true,
     fit:true,
     pageSize : 10,
     sortName:'sellmmId',
     sortOrder:'asc',
	 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
	 fitColumns : true, 
	 idField : 'sellmmId',
	 loadMsg : "数据加载中，请稍后……",
     columns : [[{title : '销售工单号',field : 'sellmmId',width : 100
		}, {title : '车牌照',field : 'carLicense',width : 100
		}, {title : '销售日期',field : 'sellmmDate',width : 100
		}, {title : '客户名称',field : 'customName',width : 100
		}]],
	    onDblClickRow:function(rowIndex, rowData){
	     $('#ssrp_carLicense').val(rowData.carLicense);
	     $('#ssrp_sellmmId').val(rowData.sellmmId);
	     $('#ssrp_customName').val(rowData.customName);
	     ssrp_d1.dialog('close');
         $('#ssrp_strtpmSumCnt').val(0);
         $('#ssrp_strtpmAmont').val(0);
         $('#ssrp_strtpmCostAmont').val(0);
	     $.ajax({
				type : 'POST',
				url : 'StSellRtPartsAction!loadSelledParts.action',
				data : 'sellmmId='+$('#ssrp_sellmmId').val(),
				dataType : 'json',
				success : function(r){
					  $('#StSellPartsDetailTable').datagrid('loadData',r);
    	        }
	     });
       }
     });
})

function _query()
{
   $.ajax({
		type : 'post',
		url : 'StSellRtPartsAction!searchStSellOrderByCondition.action',
		data : $('#ssrp_receptionForm').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#ssrp_receptionTable').datagrid('loadData',r);
        }
   });
}

function _clear()
{
   $('#ssrp_receptionForm').form('clear');
   $('#ssrp_receptionTable').datagrid('load');
}