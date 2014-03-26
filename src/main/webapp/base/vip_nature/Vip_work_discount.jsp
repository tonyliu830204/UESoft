<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%


%>

<html>
  <head>
    <title>会员工时折扣</title>
  </head>
  
  <body>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
   <!-- 新增会员工时折扣 -->
   <div id="Vip_work_discount_dialog_id" style="background:#eee"></div>
      <!-- 编辑会员工时折扣 -->
   <div id="edit_Vip_work_discount_dialog_id" style="background:#eee"></div>
   
    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div region="center"  style="background:#eee;" border="false">
			<table id="vip_work_discount_id"></table>
		</div>
	</div>

  </body>
</html>
