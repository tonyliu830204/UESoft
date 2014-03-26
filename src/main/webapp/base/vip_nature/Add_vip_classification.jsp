<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增会员卡分类</title>
  </head>
  
  <body>
  <form method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:150px">会员卡编码</td>
				<td><input type="text" style="width:120"/></td>
				</tr>
				<tr>
				<td style="width:150px">会员卡名称</td>
				<td><input type="text" style="width:120"/></td>
				</tr>
				<tr>
				<td style="width:150px">积分系数</td>
				<td><input type="text" style="width:120"/></td>
				
				</tr>
				<tr>
					<td style="width:100px">工时折扣</td>
					<td colspan="30" rowspan="10">
						<textarea rows="20" cols="20" style="width:250px;height:40px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
