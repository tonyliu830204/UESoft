<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增合计积分规则</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/Edit_vip_server.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">会员编码</td>
				<td width="400"><input type="text" style="width:100px" name="vip_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">会员名称</td>
				<td width="400"><input type="text" style="width:100px" name="vip_name"/></td>
				</tr>
				
				<tr>
					<td style="width:80px">备注</td>
					<td colspan="30" rowspan="10" width="200">
					<textarea rows="20" cols="20" style="width:280px;height:80px" name="vip_memo"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
