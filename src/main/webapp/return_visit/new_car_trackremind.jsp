<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>新车跟踪提醒</title>
	</head>
	<body>
				<div class="easyui-layout" border="false" fit="true" 
					style="width: 800px; height: 600px;">
					<div region="north" split="false" border="false"
						style="height: 115px; background : #eee">
						<div>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true">车主及牌照修改</a>
						</div>
						<form id="ff" method="post">
							<table border="0"  align="center">
								<tr>
									<td style="width:60px"> 
										销售日期
									</td>
									<td colspan="3">
										<input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>
										<input id="txtDate" class="Wdate" type="text" onfocus="WdatePicker()" editable="false" style="width:60px"/>

									</td>
									<td style="width:60px">
										车辆牌照
									</td>
									<td >
										<input type="text" style="width:100px"/>
									</td>
									<td style="width:60px">
										跟踪情况
									</td>
									<td>
										<input type="text" style="width:100px"/>
									</td>
									<td style="width:60px">
										通话情况
									</td>
									<td>
										<input type="text" style="width:100px"/>
									</td>
									</tr>
									<tr>
									<td style="width:60px">
										客户信息
									</td>
									<td>
										<input type="text" style="width:100px"/>
									</td>		
									<td style="width:50px">
										VIN底盘
									</td>
									<td >
										<input style="width:100px"/>
									</td>
									<td style="width:50px">
										联系人
									</td>
									<td>
										<input style="width:100px"/>
									</td>
										<td style="width:30px">
										电话
									</td>
									<td>
										<input style="width:100px"/>
									</td>
										<td style="width:80px">
										首保跟踪员
									</td>
									<td >
										<select style="width:100px">
											<option>张三</option>
										</select>
									</td>
								</tr>
							</table>
						</form>
					</div>
				
					<div region="center" border="false" style="background: #eee">
						<div class="easyui-layout" style="width:500px;height:400px" fit="true" border="false">
							<div region="south" border="false" style="height : 150px;background: #eee">
								<table id="New_Car_trackremind_center_south_id"></table>
							</div>
							<div region="center" border="false" style="background: #eee">
								<table id="New_Car_trackremind_center_id"></table>
							</div>
						</div>
					</div>
						<div region="south" border="false" style="height : 160px;background: #eee">
					<form>
						<table>
							<tr>
								<td style="width:60px">跟踪日期</td>
								<td><input class="easyui-datetimebox"  editable="false" style="width:100px"/></td>
								<td style="width:60px">车主姓名</td>
								<td><input style="width:100px"/></td>
								<td style="width:60px">跟踪人员</td>
								<td><input style="width:100px"/></td>
								<td style="width:60px">车辆备注</td>
								<td><input style="width:100px"/></td>
							</tr>
							<tr>
								<td style="width:60px">跟踪单号</td>
								<td><input style="width:100px"/></td>
								<td style="width:60px">固定电话</td>
								<td><input style="width:100px"/></td>
								<td style="width:60px">手机号码</td>
								<td><input style="width:100px"/></td>
								<td style="width:30px">备注</td>
								<td><textarea></textarea></td>
							</tr>
						</table>
					</form>	
					</div>
				</div>
	</body>
</html>
