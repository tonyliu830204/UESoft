<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增合计积分规则</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/Edit_vip_classification.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">会员卡编码</td>
				<td><input type="text" style="width:100px" name="vip_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">会员卡名称</td>
				<td><input type="text" style="width:100px" name="vip_name"/></td>
				</tr>
				<tr>
				<td style="width:80px">积分系数</td>
				<td><input type="text" style="width:100px" name="vip_score"/></td>
				</tr>
				<tr>
					<td style="width:80px">工时折扣</td>
					<td colspan="30" rowspan="10">
						<textarea rows="10" cols="10" style="width:200px;height:40px" name="vip_work"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
