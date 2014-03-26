<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆销售信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
	<script type="text/javascript">
	var $chiledData;
	$(function() {
		$chiledData = $('#carSell');
		$chiledData.datagrid( {
		url : 'sellZhProjectAction!getSellList.action',
		pagination : true,
		fit : true, 
		rownumbers : true,
		singleSelect : true,
		fitColumns :false,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
		idField : 'xs_Car_Sel_Id',
		loadMsg : "数据加载中，请稍后……",
		columns : [[{
			field : 'xs_Car_Sel_Id',
			title : '销售编号',
			width :80,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 100,
			sortable : true
			
		},{
		
			field : 'xs_Custom_Id',
			title : '客户名称',
			width : 100,
			sortable : true,
			hidden:true
			
		},{
			field : 'sellCode',
			title : '销售单号',
			width : 140,
			sortable : true
			
		},{
			field : 'carCode',
			title : '车辆编号',
			width : 130,
			sortable : true
			
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN号',
			width : 130,
			sortable : true
			
		},{
			field : 'xs_Model_Name',
			title : '车辆型号',
			width : 130,
			sortable : true
			
		},{
			field : 'detailsId',
			title : '入库明细编号',
			width : 130,
			sortable : true,
			hidden:true
		}, {
			field : 'xsCarId',
			title : '车档案编号',
			width : 120,
			sortable : true,
			hidden:true
			
		}
		]],
			onDblClickRow : function(rowIndex, rowData) {
				$('#xs_Car_Sel_Id').val(rowData.xs_Car_Sel_Id);
				$('#sellCode').val(rowData.sellCode);
				$('#detailsId').val(rowData.detailsId);
				$('#carId').val(rowData.xsCarId);
				sgsm_d2.dialog('close');
				  $.ajax({
        					type : 'POST',
        					url : 'instoreOutAction!getCarInfo.action',
        					data : "carId="+rowData.xsCarId,
        					dataType : 'json',
        					success : function(r){
            					  $('#instoreDel').datagrid('loadData',r);
        	    	        }
        		     });
			}
		});
	});
	var query = function() {
		$('#carSell').datagrid('unselectAll');
		$('#carSell')
				.datagrid('load', serializeObject($('#childQueryForm')));
	}
	function clearSearch() {
		$('#childQueryForm').find('input').val('');
	$('#childQueryForm').find('select').val('');
	$('#carSell').datagrid('unselectAll');
	$('#carSell').datagrid('load', serializeObject($('#childQueryForm')));

	}
</script>

		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 63px;" border="false">
				<form id="childQueryForm">
					销售单号:<input type="text" id="sellCode" name="sellCode" style="background-color: #c0d8d8;" onkeyup="query();" />
					VIN号:<input type="text" id="xs_Car_Vin_Number" name="xs_Car_Vin_Number" style="background-color: #c0d8d8;" onkeyup="query();" />
					&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearch();" plain="true">清空</a>
				</form>
			</div>
			<div region="center" style="background: #eee;" border="false">
				<table id="carSell"></table>
			</div>
		</div>
	</body>
</html>