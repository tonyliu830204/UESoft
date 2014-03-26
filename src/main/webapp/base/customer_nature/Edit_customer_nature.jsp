<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>修改技协费分类</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/Edit_customer_nature.js"></script>
		<form  method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:80px">性质编码</td>
				<td><input type="text" name="nature_id" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">性质名称</td>
				<td ><input type="text" name="nature_name" style="width:100px"/></td>
				</tr>
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" name="nature_memo" style="width:300px;height:50px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
