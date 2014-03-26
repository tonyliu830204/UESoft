<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>会员活动项</title>
  </head>
  
  <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
    	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="ACTIVITIESDPROADD">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Vip_activities_project_id'));">新增</a>
					</privilege:enable>
					<privilege:enable code="ACTIVITIESDPRODELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnremove" iconCls="icon-remove" plain="true" onclick="doDelete($('#Vip_activities_project_id'),'${pageContext.request.contextPath}/BasVipActivitiesProjectAction!delete.action','${pageContext.request.contextPath}/BasVipActivitiesProjectAction!findAll.action');">删除</a>
					</privilege:enable>
					<privilege:enable code="ACTIVITIESDPROMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Vip_activities_project_id'));">修改</a>
					</privilege:enable>
					<privilege:enable code="ACTIVITIESDPROEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick=" _except('vipactivitiesprojectdiv','会员活动项信息');">导出</a>
					</privilege:enable>	
					<span id="saveOrCancelBtn"></span>
		</div>	
		<div id="vipactivitiesprojectdiv" region="center"  style="background:#eee;" border="false">
			<table id="Vip_activities_project_id"></table>
		</div>
	</div>
  </body>
</html>
