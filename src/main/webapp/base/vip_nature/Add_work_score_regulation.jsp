<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <title>新增工时积分规则</title>
  </head>
  <body>
  		<form method="post" >
			<table border="0"align="center" fit="true">
				<tr>
				<td style="width:80px">方式编码</td>
				<td><input type="text" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">开始金额</td>
				<td><input type="text" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">加分率</td>
				<td><input type="text" style="width:100px"/></td>
				</tr>
				<tr>
				<td style="width:80px">维修类别</td>
				<td><select style="width:100px">
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
						<textarea rows="10" cols="10" style="width:200px;height:40px"></textarea>
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
