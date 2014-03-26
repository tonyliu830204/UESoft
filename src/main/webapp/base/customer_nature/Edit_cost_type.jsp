<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>修改技协费分类</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/Edit_cost_type.js"></script>
		<form  method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:80px">技协编码</td>
				<td><input type="text" name="jixie_id" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">技协名称</td>
				<td ><input type="text" name="jixie_name" style="width:100px"/></td>
				</tr>
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" name="jixie_memo" style="width:300px;height:50px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
