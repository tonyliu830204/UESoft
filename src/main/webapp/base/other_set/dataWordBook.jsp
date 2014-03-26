<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>数据字典</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/dataWordBook.js"></script>
  	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div id="dataWordBook" region="center"  style="background:#eee;" border="false">
			<table id="dataWordBookMain"></table>
		</div>
	</div>
  </body>
</html>
