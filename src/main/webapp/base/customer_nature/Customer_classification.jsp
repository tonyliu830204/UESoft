<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>客户类型</title>
  </head>
  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/customer_nature.js"></script>
  <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
		 <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="CLASSIFICATIONADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#customer_classification_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="CLASSIFICATIONDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"   iconCls="icon-remove" plain="true" onclick="doDelete($('#customer_classification_id'),'${pageContext.request.contextPath}/basCustomTypeAction_doDelete.action','${pageContext.request.contextPath}/basCustomTypeAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="CLASSIFICATIONMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#customer_classification_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="CLASSIFICATIONEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('customer_classification_idCenter','客户类型信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="customer_classification_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="customer_classification_id"></table>
		</div>
	</div>

  </body>
</html>
