<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%


%>
<html>
	<head>
		<title>礼品管理</title>
	</head>

	<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="Vip_present_management_id"></table>
		</div>
	</div>
	</body>
</html>
