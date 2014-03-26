<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>新增合计积分规则</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/Edit_sum_score_regulation.js"></script>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">方式编码</td>
				<td width="400"><input type="text" style="width:100px" name="sum_id"/></td>
				</tr>
				<tr>
				<td style="width:80px">开始金额</td>
				<td width="400"><input type="text" style="width:100px" name="sum_startcount"/></td>
				</tr>
				<tr>
				<td style="width:80px">加分率</td>
				<td width="400"><input type="text" style="width:100px" name="sum_addscore"/></td>
				</tr>
				<tr>
				<td style="width:80px">添加金额</td>
				<td width="400"><input type="text" style="width:100px" name="sum_addcount"/></td>
				</tr>
				<tr>
				<td style="width:80px">维修类别</td>
				<td width="400"><select style="width:100px" name="sum_type">
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
				<tr>
					<td style="width:80px">结束金额</td>
					<td colspan="30" rowspan="10">
						<textarea rows="10" cols="10" style="width:200px;height:40px" name="sum_endcount"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
