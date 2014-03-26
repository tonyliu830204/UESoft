<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>客户回访项目</title>
  </head>
  
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/customer_nature.js"></script>
    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
    	<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="CUSTOMVISITPROADD">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnadd" iconCls="icon-add" plain="true" onclick="doAdd($('#customer_visit_project_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMVISITPRODELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#customer_visit_project_id'),'${pageContext.request.contextPath}/fbkDcserveyNameAction!doDelete.action','${pageContext.request.contextPath}/fbkDcserveyNameAction!doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMVISITPROMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"  iconCls="icon-edit" plain="true" onclick="doUpdate($('#customer_visit_project_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="CUSTOMVISITPROEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('customer_visit_project_idCenter','客户回访项目信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="customer_visit_project_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="customer_visit_project_id"></table>
		</div>
	</div>

  </body>
</html>
