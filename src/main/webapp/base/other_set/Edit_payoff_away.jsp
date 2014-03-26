<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>编辑</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/Edit_payoff_away.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">付款编码</td>
				<td><input type="text" style="width:100" name="payoff_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">付款名称</td>
				<td><input type="text" style="width:100" name="payoff_name"/></td>
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
