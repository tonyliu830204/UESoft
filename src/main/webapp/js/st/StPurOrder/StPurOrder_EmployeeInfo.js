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
    var buyerName=$('#buyerName').val();
if(buyerName!=null&&buyerName!='')
{ 
    $('#stfName').val(buyerName);
    $.ajax({
		type : 'post',
		url : 'StSellOrderAction!searchPickingMemberByCondition.action',
		data : $('#stpurorder_employeeform').serialize(),
		dataType : 'json',
		success : function(r){
	               $('#stpurorder_employeetable').datagrid('loadData',r);
  	               }
       });
    }
 }

//采购人员信息
$(function(){
    $('#stpurorder_employeetable').datagrid({
		 url:'StPurOrderAction!loadPurOrderMember.action',
		 pagination:true,
	     fit:true,
	     singleSelect:true,
	     sortOrder:'asc',
	     sortName:'stfId',
	     pageSize : 10,
		 pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		 fitColumns : true, 
		 rownumbers:true,
		 idField : 'stfId',
		 loadMsg : "数据加载中，请稍后……",
		 columns : [[ {
					title : '采购员编号',field : 'stfId',sortable:true,width : 100
				       }, {
					title : '采购员名称',field : 'stfName',sortable:true,width : 100
				       }
		            ]],
	      onDblClickRow:function(rowIndex, rowData){
	        var selections = $('#stpurorder_employeetable').datagrid('getSelections');
		    if (selections != null) {
				 $('#buyer').val(selections[0].stfId);
				 $('#buyerName').val(selections[0].stfName);
				 $('#stpurorder_employeeform').form('clear');
				 d1.dialog('close');
		    }
	      }
	}); 		
  })	 

  function _query(){
//   	    $.ajax({
//			type : 'post',
//			url : 'StSellOrderAction!searchPickingMemberByCondition.action',
//			data : $('#stpurorder_employeeform').serialize(),
//			dataType : 'json',
//			success : function(r){
//		               $('#stpurorder_employeetable').datagrid('loadData',r);
//   	        }
//        });
	 $('#stpurorder_employeetable').datagrid('load', serializeObject($('#stpurorder_employeeform')));
  } 	 
			  
  function _clear()
  {
      $('#stpurorder_employeeform').form('clear');
      $('#stpurorder_employeetable').datagrid('load');
  }