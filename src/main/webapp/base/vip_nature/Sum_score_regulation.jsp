<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>合计积分规则</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  </head>
  
  <body>
   <!-- 新增 -->
   <div id="add_Sum_score_regulation_dialog_id" style="background: #eee"></div>
   <!-- 修改 -->
    <div id="edit_Sum_score_regulation_dialog_id" style="background: #eee"></div>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="Sum_score_regulation_center_id"></table>
		</div>
	</div>

  </body>
</html>
