<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>会员等级管理</title>
  </head>
  
  <body>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  	<div class="easyui-layout" fit="true" border="false" style="width:500px;height:400px;background: #eee">
   
   		<div region="center" style="background:#eee;" border="false">
   		<table id="Vip_rating_management_center_id"></table>
   		</div>   
  	</div>
  </body>
</html>
