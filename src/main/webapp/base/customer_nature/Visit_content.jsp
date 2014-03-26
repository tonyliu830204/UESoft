<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>回访内容</title>
  </head>
  
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/customer_nature.js"></script>
		<div region="center"  style="background:#eee;" border="false">
			<table id="Visit_content_center_id"></table>
		</div>
  </body>
</html>
