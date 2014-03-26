<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
  <title>活动折扣</title>
  </head>
  
  <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
    	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="DISCOUNTADD">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd" iconCls="icon-add" plain="true" onclick="doAdd($('#Activities_discount_id'));">新增</a>
					</privilege:enable>
					<privilege:enable code="DISCOUNTDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove" iconCls="icon-remove" plain="true" onclick="doDelete($('#Activities_discount_id'),'${pageContext.request.contextPath}/BasVipActivitiesDiscountAction!delete.action','${pageContext.request.contextPath}/BasVipActivitiesDiscountAction!findAll.action');">删除</a>
					</privilege:enable>
					<privilege:enable code="DISCOUNTMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Activities_discount_id'));">修改</a>
					</privilege:enable>
					<privilege:enable code="DISCOUNTEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('Activities_discount_idCenter','活动折扣信息');">导出</a>
					</privilege:enable>	
					<span id="saveOrCancelBtn"></span>
		</div>	
		<div id="Activities_discount_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Activities_discount_id"></table>
		</div>
	</div>
  </body>
</html>