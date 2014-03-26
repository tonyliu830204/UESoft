<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>车辆保单查询</title>
		<script type="text/javascript">

</script>
	</head>
	<body>
				<div class="easyui-layout" border="false" fit="true" 
					style="width: 800px; height: 600px;">
					<div region="north" title="条件筛选" split="false" border="false"
						style="height: 105px; background : #eee">
						<form id="ff" method="post">
							<table border="0"  align="center">
								<tr>
									<td style="width:60px"> 
										代保日期 
									</td>
									<td width="300">
										<input id="cc" id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>
										<input id="cc" id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>

									</td>
									<td style="width:60px">
										保险公司
									</td>
									<td colspan="3">
										<input type="text" style="width:210px"/><a href="javascript:void(0);" class="easyui-linkbutton" plain ="true" style="width:30px">...</a>
									</td>
									<td style="width:60px">
										客户名称
									</td>
									<td colspan="3">
										<input type="text" style="width:230px"/>
									</td>
									
									</tr>
									<tr>
									<td style="width:60px"> 
										保险到期 
									</td>
									<td width="300">
										<input id="cc" id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>
										<input id="cc" id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>

									</td>
									<td style="width:50px">
										车牌照
									</td>
									<td >
										<input style="width:80px"/>
									</td>
									<td style="width:60px">
										经办业务
									</td>
									<td>
										<input style="width:80px"/>
									</td>
										<td style="width:60px">
										排列方式
									</td>
									<td>
										<select style="width:80px">
											<option>
											</option>
										</select>
									</td>
										<td style="width:60px">
										保险分类
									</td>
									<td >
										<select style="width:80px">
											<option>
											</option>
										</select>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div region="center" border="false" style="background: #eee">
						<table id="Car_policy_find_center_id"></table>
					</div>
				</div>
	</body>
</html>
