<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增部门</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/Edit_Department_set.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">部门编码</td>
				<td><input type="text" style="width:100" name="department_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">部门名称</td>
				<td><input type="text" style="width:100" name="department_name"/></td>
				</tr>
				
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" style="width:200px;height:40px" name="memo"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
