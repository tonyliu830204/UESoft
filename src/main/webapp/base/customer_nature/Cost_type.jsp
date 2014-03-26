<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>技协费分类</title>
  </head>
  
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/customer_nature.js"></script>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
	    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="COSTTYOEADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Cost_type_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="CCOSTTYOEDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick=" doDelete($('#Cost_type_id'),'${pageContext.request.contextPath}/basCountassociationTypeAction_doDelete.action','${pageContext.request.contextPath}/basCountassociationTypeAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="COSTTYOEMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Cost_type_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="COSTTYOEEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnexport" iconCls="icon-export" plain="true" onclick="  _except('Cost_type_idCenter','技协费分类信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="Cost_type_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Cost_type_id"></table>
		</div>
	</div>

  </body>
</html>
