<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String  isInvoice=request.getParameter("isInvoice");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆销售单选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
	<script type="text/javascript">
 //判断页面初始化加载是否完成
   /*function   LoadOk(){
    	if(document.readyState   =="complete") {
  			 initFrame();
 		}else{
   		setTimeout("LoadOk()",200);
 		}
	}
   setTimeout("LoadOk()",200);
   
   //判断页面初始化加载完成    执行
function   initFrame(){
    var brandName=$('#carBrandName').val();
          if(brandName!='' && brandName!=null){ 
	         	 $('#dataValue').val(brandName);          
	           $.ajax({
					type : 'post',
					url : 'baseTagAction!findChildByValue.action',
					data : "childId="+$('#car_brandId').val()+"&dataValue="+brandName,
					dataType : 'json',
					success : function(r){
				               //$('#chiledData').datagrid('loadData',r);
				               query();
				 	               }
				        });
				       }
				      $('#carBrandName').val('');
				      $('#car_brandId').val('');
}*/
	var $chiledData;
	$(function() {
		$chiledData = $('#carSell');
		$chiledData.datagrid( {
		url : 'sellDbProjectAction!getInvoSellInfor.action?is_invoice='+<%=isInvoice%>,
		pagination : true,
		fit : true, 
		rownumbers : true,
		singleSelect : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'xs_Car_Sel_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Car_Sel_Id',
			title : '销售编号',
			width : 100,
			sortable : true,
			hidden:true
			
		},{
			field : 'sellCode',
			title : '销售单号',
			width : 100,
			sortable : true,
			hidden:true
			
		},{
			field : 'out_Id',
			title : '出库单号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'reserve_Code',
			title : '预订单编号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 120,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 130,
			sortable : true
		}, {
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 120,
			sortable : true
			
		}, {
			field : 'xs_Car_Brand',
			title : '车品牌',
			width : 90,
			sortable : true
			
		}, {
			field : 'xs_Model_Name',
			title : '车类型',
			width : 120,
			sortable : true
			
		}
		]],
			onDblClickRow : function(rowIndex, rowData) {
				$('#xs_Car_Sel_Id').val(rowData.xs_Car_Sel_Id);
				$('#sellCode').val(rowData.sellCode);
				sgsm_d2.dialog('close');
			}
		});
	});
	var query = function() {
		$('#carSell').datagrid('unselectAll');
		$('#carSell').datagrid('load', serializeObject($('#childQueryForm')));
	}
	function clearCondition() {
		$('#childQueryForm').form('clear');
		$('#carSell').datagrid('load',serializeObject($('#childQueryForm')));
		

	}
</script>

		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 63px;" border="false">
				<form id="childQueryForm">
					销售单号:<input type="text" id="xs_Car_Sel_Id" name="xs_Car_Sel_Id" style="background-color: #c0d8d8;" onkeyup="query();" />
					VIN号:<input type="text" id="xs_Car_Vin_Number" name="xs_Car_Vin_Number" style="background-color: #c0d8d8;" onkeyup="query();" />
					&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearCondition();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="carSell"></table>
			</div>
		</div>
	</body>
</html>