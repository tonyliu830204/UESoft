<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增赠送内容</title>
  </head>
  
  <body>	
  <form method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:120px">新增编码</td>
				<td><input type="text" style="width:110"/></td>
				</tr>
				<tr>
				<td style="width:120px">新增名称</td>
				<td><input type="text" style="width:110"/></td>
				</tr>
				<tr>
					<td style="width:120px">消除积分</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" style="width:280px;height:80px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
