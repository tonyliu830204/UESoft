<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车间部门</title>
  </head>
  
  <body>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
  	<div class="easyui-layout" fit="true" border="false" style="width:500px;height:400px;background: #eee">
		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="WORKSHOPADD">
					<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" id="btnadd"  plain="true" onclick="doAdd($('#Workshop_department_center_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="WORKSHOPDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" id="btnremove"  plain="true" onclick="doDelete($('#Workshop_department_center_id'),'${pageContext.request.contextPath}/basRepairGroupAction_doDelete.action','${pageContext.request.contextPath}/basRepairGroupAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="WORKSHOPMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" id="btnedit" plain="true" onclick="doUpdate($('#Workshop_department_center_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="WORKSHOPEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" id="btnexport"  plain="true" onclick="_except('Workshop_department_center','车间部门信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
   		<div id="Workshop_department_center" region="center" style="background:#eee;" border="false">
   		<table id="Workshop_department_center_id"></table>
   		</div>   
  	</div>
  </body>
</html>
