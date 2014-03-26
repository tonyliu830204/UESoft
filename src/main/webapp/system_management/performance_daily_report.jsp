<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
	<head>
		<title>服务业绩日报表</title>

	</head>

	<body>
			<div class="easyui-layout" border="false" fit="true" style="width: 800px; height: 600px;">
					<div region="north" title="条件筛选" split="false" border="false" style="height: 65px; background: #eee;">
						<form id="ff" method="post">
							<table border="0" align="center">
							<tr>
								<td  style="width:60px">服务日期</td>
								<td >
								<input class="easyui-datetimebox" editable="false" style="width:100px" style="width:60px"/>
								<input class="easyui-datetimebox" editable="false" style="width:100px" style="width:60px"/>&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td  style="width:60px">服务人员</td>
								<td width="120">
								<input type="text" style="width:100px"/>
								</td>
								<td  style="width:60px">服务部门</td>
								<td width="120">
								<select style="width:100px">
									<option></option>
								</select>
								</td>
								<td style="width:60px">
									完成情况
								</td>
								<td>
									<select style="width:100px">
									<option></option>
								</select>
								</td>
							
							</tr>
							</table>
						</form>
					</div>
					<div region="center" border="false" style="background: #eee">
						<table id="Performance_daily_report_center_id"></table>
					</div>
				</div>
	</body>
</html>
