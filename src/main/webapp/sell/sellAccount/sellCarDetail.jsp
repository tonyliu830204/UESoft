<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆档案选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout" >
	 <%
		String accountTypeId=request.getParameter("accountTypeId");
		if(accountTypeId!=null){
	%>
	<script type="text/javascript">
		
		//销售单汇总
	$('#selectionCar').datagrid({
		url : 'carSellManageAction!findSellInfor.action?sell_Code=<%=accountTypeId%>',
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
			title : '编号',
			width : 80,
			sortable : true
			
		},
		{
			field : 'sell_Code',
			title : '销售单号',
			width : 80,
			sortable : true
			
		},{
			field : 'reserve_Code',
			title : '预订单编号',
			width : 100,
			sortable : true,
			hidden : true
			
		},{
			field : 'xs_Car_Sel_Data',
			title : '销售日期',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Custom_Name',
			title : '客户名称',
			width : 90,
			sortable : true
			
		},{
			field : 'stf_Name',
			title : '业务员',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Vin_Number',
			title : 'VIN编号',
			width : 135,
			sortable : true
		}, {
			field : 'xs_Car_Ocn',
			title : 'OCN码',
			width : 100,
			sortable : true
			
		},{
			field : 'xs_Car_Brand_Name',
			title : '车品牌',
			width : 100,
			sortable : true
			
		}, {
			field : 'xs_Model_Name',
			title : '车类型',
			width : 100,
			sortable : true
			
		}, {
			field : 'xs_Car_Sel_Transaction_Money',
			title : '成交价格',
			width : 100,
			sortable : true
		}, {
			field : 'xs_Car_Stf_Name',
			title : '经办人',
			width : 100,
			sortable : true
		}, {
			field : 'mobilephone',
			title : '电话',
			width : 100,
			sortable : true
		}
		]]
			
		});	  
	</script>
	<%
			}
 		%>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="center" title="销售单明细" style="background: #eee;" border="false" >
					<table id="selectionCar"></table>
			</div>
	</div>
	</body>
</html>