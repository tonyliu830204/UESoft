<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasPartsUnit"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>My JSP 'Unit of meaturement.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/Partsproperties/PartsUnit.js"></script>
		<div id="cc" class="easyui-layout"
			style="width: 600px; height: 400px;" fit="true">
			<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="PARTSUNITADD">
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="PARTSUNITDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="PARTSUNITMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="PARTSUNITEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					<span id="saveOrCancelBtn"></span>
			</div>
			<div id="PartsUnitCenter" region="center" style="background: #eee;" border="false">
				<table id="table14"></table>
			</div>
			</div>
	</body>
</html>
