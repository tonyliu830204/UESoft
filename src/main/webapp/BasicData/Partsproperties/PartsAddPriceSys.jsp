<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>My JSP 'AccessoriesAddPriceSys.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/Partsproperties/PartsAddPriceSys.js"></script>
		<div id="cc" class="easyui-layout"
			style="width: 600px; height: 400px;" fit="true">
			<div id="PartsAddPriceSysCenter" region="center" style="background: #eee;" border="false">
				<table id="table10" width="100%"></table>
			</div>
		</div>
	</body>
</html>
