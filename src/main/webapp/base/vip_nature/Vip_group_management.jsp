<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%


%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>会员分组</title>
  </head>
  
  <body>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
  	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
				<privilege:enable code="GROPUMANAGEADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Vip_group_management_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="GROPUMANAGEDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove" iconCls="icon-remove" plain="true" onclick="doDelete($('#Vip_group_management_id'),'BasVipGruopAction!delete.action','BasVipGruopAction!findAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="GROPUMANAGEMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"  iconCls="icon-edit" plain="true" onclick="doUpdate($('#Vip_group_management_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="GROPUMANAGEEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnexport" iconCls="icon-export" plain="true" onclick="  _except('Vip_group_management_idCenter','会员分组信息');">导出</a>
				</privilege:enable>	
				<span id="saveOrCancelBtn"></span>
		</div>	
	<div id="Vip_group_management_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Vip_group_management_id"></table>
		</div>
	</div>

  </body>
</html>
