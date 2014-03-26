<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>积分赠送内容设定</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  </head>
  
  <body>
   <!-- 新增赠送内容 -->
   <div id="Score_present_set_dialog_id" style="background: #eee"></div>
     <!-- 编辑赠送内容 -->
   <div id="edit_Score_present_set_dialog_id" style="background: #eee"></div>
   
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="Score_present_set_id"></table>
		</div>
	</div>

  </body>
</html>
