<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>修改技协费分类</title>
  </head>
  <body>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/Edit_customer_classification.js"></script>
		<form  method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:80px">类型编码</td>
				<td><input type="text" name="type_id" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">类型名称</td>
				<td ><input type="text" name="type_name" style="width:100px"/></td>
				</tr>
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" name="type_memo" style="width:300px;height:50px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
