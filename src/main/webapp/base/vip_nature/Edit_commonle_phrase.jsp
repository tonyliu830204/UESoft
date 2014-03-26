<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增常用短语</title>
  <script type="tex t/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/Edit_commonle_phrase.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">短语编码</td>
				<td><input type="text" style="width:100" name="commonle_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">短语名称</td>
				<td><input type="text" style="width:100" name="commonle_name"/></td>
				</tr>
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10">
						<textarea rows="10" cols="10" style="width:200px;height:40px" name="commonle_memo"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
