<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>收费性质</title>
  </head>
  
  <body>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
  	<div class="easyui-layout" fit="true" border="false" style="width:500px;height:400px;background: #eee">
  		 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="CHANGESNATUREADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"   iconCls="icon-add" plain="true" onclick="doAdd($('#Charges_nature_center_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="CHANGESNATUREDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#Charges_nature_center_id'),'${pageContext.request.contextPath}/basRepairTypeAction_doDelete.action','${pageContext.request.contextPath}/basRepairTypeAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="CHANGESNATUREMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Charges_nature_center_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="CHANGESNATUREEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick="_except('Charges_nature_center','收费性质信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
   		<div id="Charges_nature_center" region="center" style="background:#eee;" border="false">
   		<table id="Charges_nature_center_id"></table>
   		</div>   
  	</div>
  </body>
</html>
