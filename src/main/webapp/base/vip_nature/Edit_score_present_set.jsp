<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增常用短语</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/Edit_score_present_set.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">赠送编码</td>
				<td ><input type="text" style="width:100px" name="present_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">赠送名称</td>
				<td ><input type="text" style="width:100px" name="present_name"/></td>
				</tr>
				<tr>
					<td style="width:80px">消除积分</td>
					<td colspan="30" rowspan="10" >
						<textarea rows="10" cols="10" style="width:200px;height:40px" name="present_memo"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
