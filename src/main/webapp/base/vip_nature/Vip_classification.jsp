<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>会员卡分类</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  </head>
  
  <body>
   <!--新增会员卡分类弹出窗口-->
    	<div id="Add_vip_classification_dialog_id" style="background:#eee"></div>
   <!--新增会员卡分类弹出窗口-->
    	<div id="edit_vip_classification_dialog_id" style="background:#eee"></div>
    	
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="vip_classification_id"></table>
		</div>
	</div>

  </body>
</html>
