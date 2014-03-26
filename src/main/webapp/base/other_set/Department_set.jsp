<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>部门设置</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/Department_set.js"></script>
    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="DEPARTMENTADD">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd();">新增</a>
			</privilege:enable>
			<privilege:enable code="DEPARTMENTDELETE">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
			</privilege:enable>
			<privilege:enable code="DEPARTMENTMODIFY">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate();">修改</a>
			</privilege:enable>
			<privilege:enable code="DEPARTMENTEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"   iconCls="icon-export" plain="true" onclick="_except('department_set_idCenter','部门设置信息');">导出</a>
			</privilege:enable>
			<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="department_set_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="department_set_id"></table>
		</div>
	</div>
  </body>
</html>
