<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增保养关键词</title>
  </head>
  
  <body>	
  <form method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:100px">关键词编码</td>
				<td ><input type="text" style="width:100"/></td>
				</tr>
				<tr>
				<td style="width:100px">关键词名称</td>
				<td ><input type="text" style="width:100"/></td>
				</tr>
				<tr>
					<td style="width:100px">备注</td>
					<td colspan="30" rowspan="10" >
						<textarea rows="20" cols="20" style="width:260px;height:80px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
