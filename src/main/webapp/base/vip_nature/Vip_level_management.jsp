<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>

<html>
  <head>
    <title>会员等级</title>
  </head>
  
  <body>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
  <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
  	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
				<privilege:enable code="LEVELMANAGEADD">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnadd" iconCls="icon-add" plain="true" onclick="doAdd($('#Vip_level_management_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="LEVELMANAGEDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#Vip_level_management_id'),'BasVipLevelAction!delete.action','BasVipLevelAction!findAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="LEVELMANAGEMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Vip_level_management_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="LEVELMANAGEEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('Vip_level_management_idCenter','会员等级信息');">导出</a>
				</privilege:enable>	
				<span id="saveOrCancelBtn"></span>
		</div>	
	<div id="Vip_level_management_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Vip_level_management_id"></table>
		</div>
	</div>

  </body>
</html>
