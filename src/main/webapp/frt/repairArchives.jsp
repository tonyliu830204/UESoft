<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 维修档案 -->
<script type="text/javascript">
   var tag = '<%=request.getParameter("carIdInput") %>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/repairArchives.js"></script>
<div class="easyui-layout" style="width:800px; height:600px" fit="true"
	border="false">
	<div region="center" style="background:#eee;" border="false">
		<table id="frtWorkOrderItemDatagrid"></table>
	</div>
</div>