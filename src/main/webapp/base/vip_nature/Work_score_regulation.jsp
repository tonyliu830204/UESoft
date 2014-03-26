<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%


%>

<html>
  <head>
    <title>工时积分规则</title>
  </head>
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  <!--工时积分弹出窗口-->
  <div id="Work_score_regulation_dialog_id"  style="background:#eee"></div>
   <!--编辑工时积分弹出窗口-->
  <div id="edit_Work_score_regulation_dialog_id"  style="background:#eee"></div>
    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="work_score_regulation_id"></table>
		</div>
	</div>

  </body>
</html>
