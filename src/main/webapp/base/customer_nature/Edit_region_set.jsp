<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>修改地区</title>
  </head>
  <body>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/Edit_region_set.js"></script>
		<form  method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:80px">区域编码</td>
				<td><input type="text" name="region_id" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">区域名称</td>
				<td ><input type="text" name="region_name" style="width:100px"/></td>
				</tr>
				<tr>
					<td style="width:80px">邮政编码</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" name="region_memo" style="width:300px;height:50px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
