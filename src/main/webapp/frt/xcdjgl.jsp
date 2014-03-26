<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>洗车登记管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/xcdjgl.js"></script>
  </head>
  
  <body class="easyui-layout" style="width:800px;height:600px;" fit="true" border="false">
    <div region="north" title="查询条件" style="background:#eee; height:100px;" border="false"></div>  
    <div region="center" style="background:#eee;" border="false">
    	<table id="xcdjgl"></table>
    </div> 
  </body>
</html>
