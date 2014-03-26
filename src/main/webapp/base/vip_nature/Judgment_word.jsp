<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>判断保养词</title>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  </head>
  
  <body>
   <!-- 新增判断保养词-->
   <div id="Judgment_word_dialog_id" style="background: #eee"></div>
      <!-- 编辑判断保养词-->
   <div id="edit_Judgment_word_dialog_id" style="background: #eee"></div>
   
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="Judgment_word_id"></table>
		</div>
	</div>

  </body>
</html>
