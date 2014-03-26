<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>保险险种</title>
  </head>
  
  <body>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
 		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="INSURANCECOVERAGEADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#Insurance_coverage_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="INSURANCECOVERAGEDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"   iconCls="icon-remove" plain="true" onclick="doDelete($('#Insurance_coverage_id'),'${pageContext.request.contextPath}/insuranceTypeAction_doDelete.action','${pageContext.request.contextPath}/insuranceTypeAction_doFind.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="INSURANCECOVERAGEMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#Insurance_coverage_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="INSURANCECOVERAGEEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick="_except('Insurance_coverage_idCenter','保险险种信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
		<div id="Insurance_coverage_idCenter" region="center"  style="background:#eee;" border="false">
			<table id="Insurance_coverage_id"></table>
		</div>
	</div>

  </body>
</html>
