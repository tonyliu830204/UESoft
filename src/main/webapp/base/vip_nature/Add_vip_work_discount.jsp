<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增会员工时折扣</title>
  </head>
  
  <body>
  		<form method="post">
			<table border="0"align="center">
				<tr>
				<td style="width:80px">优惠编号</td>
				<td><input type="text" style="width:120px"/></td>
				</tr>
				<tr>
				<td style="width:80px">会员卡类</td>
				<td><input type="text" style="width:120px"/></td>
				</tr>
				<tr>
				<td style="width:80px">优惠率</td>
				<td><input type="text" style="width:120px"/></td>
				</tr>
				<tr>
				<td style="width:80px">维修类别</td>
				<td><select style="width:120px">
				<option>定期保养</option>
				<option>一般修理</option>
				<option>首保</option>
				<option>索赔</option>
				<option>理赔</option>
				<option>事故</option>
				<option>钣金</option>
				<option>喷漆</option>
				<option>钣金喷漆</option>
				<option>返修</option>
				<option>免费检查</option>
				<option>PDI检测</option>
				<option>外车救援</option>
				<option>维修金储备</option>
				<option>装具</option>
				</select></td>
				</tr>
			</table>
		</form>
  </body>
</html>
