<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasPartsSell"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>配件销售分类</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/Partsproperties/PartsSalesClassfication.js"></script>
		<div id="cc" class="easyui-layout"
			style="width: 600px; height: 400px;" fit="true">
			<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="PARTSALEADD">
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="PARTSALEDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="PARTSALEMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="PARTSALEEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					<span id="saveOrCancelBtn"></span>
			</div>
			<div id="PartsSalesClassficationCenter" region="center" style="background: #eee;" border="false">
				<table id="table15"></table>
			</div>
		</div>
	</body>
</html>
