<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>客户投诉分类</title>
  </head>
  
  <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/customer_nature/customer_nature.js"></script>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
    	<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="COMPLAINTSADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Customer_complaints_classification_center_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="COMPLAINTSDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#Customer_complaints_classification_center_id'),'${pageContext.request.contextPath}/basCustomerComplaintsAction_doDelete.action','${pageContext.request.contextPath}/basCustomerComplaintsAction_doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="COMPLAINTSMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"   iconCls="icon-edit" plain="true" onclick="doUpdate($('#Customer_complaints_classification_center_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="COMPLAINTSEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('Customer_center','客户投诉分类信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="Customer_center" region="center"  style="background:#eee;" border="false">
			<table id="Customer_complaints_classification_center_id"></table>
		</div>
	</div>

  </body>
</html>
