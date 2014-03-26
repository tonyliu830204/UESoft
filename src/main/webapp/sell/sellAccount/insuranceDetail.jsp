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
	
	$selectionCar = $('#selectionCar');
		$selectionCar.datagrid({
				url : 'sellInsuranceAction!getInsuranceList.action?insurancePolicy=<%=accountTypeId%>',
				pagination : true,
				fit:true,
				sortOrder:'asc',
			    sortName:'insuranceId',
				singleSelect : true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],
				rownumbers : true,
				loadMsg : "数据加载中，请稍后……",
				columns : [ [ 
			 {
					field : 'dafeDate',
					title : '代保日期',
					width : 90,
					sortable : true
				}, {
					field : 'insuranceId',
					title : '记录编号',
					width : 80,
					sortable : true
				},{
					field : 'insurancePolicy',
					title : '保险单号',
					width : 120,
					sortable : true
				 }, {
					field : 'insurer',
					title : '保险公司id',
					width : 120,			
					sortable : true,
					hidden:true
				}, 
				{
					field : 'insurerName',
					title : '保险公司',
					width : 120,
					sortable : true
				}, {
					field : 'passiveSafeperson',
					title : '客户名称',
					width : 90,
					sortable : true
				},
				{
					field : 'trafficCharge',
					title : '交强险出单保费',
					width : 100,
					sortable : true
				},
				 {
					field : 'safeAmount',
					title : '保险费',
					width : 100,
					sortable : true
				},
				{
					field : 'vehiclevesselTax',
					title : '车船使用税',
					width : 100,
					sortable : true
				},
				{
					field : 'sum',
					title : '保费',
					width : 100,
					sortable : true
				},
				{
					field : 'safeCost',
					title : '保单保费',
					width : 100,
					sortable : true
				},
				{
					field : 'buysnessCost',
					title : '商业险返点',
					width : 100,
					sortable : true
				},
				{
					field : 'inCost',
					title : '交强险返点',
					width : 100,
					sortable : true
				},
				{
					field : 'safeAmount',
					title : '保险费',
					width : 100,
					sortable : true
				},
				{
					field : 'customCost',
					title : '客户付款',
					width : 100,					
					sortable : true
				},
				{
					field : 'stfName',
					title : '经办人',
					width : 120,
					sortable : true
				},
				{
					field : 'person',
					title : '经办人id',
					width : 110,
					hidden:true,
					sortable : true
				}
			  ]]
		});	
		  
	</script>
	<%
			}
 		%>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="center" title="保险单明细" style="background: #eee;" border="false" >
					<table id="selectionCar"></table>
			</div>
	</div>
	</body>
</html>