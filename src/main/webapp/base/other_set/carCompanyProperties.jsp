<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<html>
  <head>
    <title>付款方式</title>
  </head>
  
  <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
    	<div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
	    	<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
					<privilege:enable code="CARCOMPANYADD">
							<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"   iconCls="icon-add" plain="true" onclick="doAdd($('#carCompanyProperties_id'));">新增</a>
						</privilege:enable>
						<privilege:enable code="CARCOMPANYDELETE">
							<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete($('#carCompanyProperties_id'),'${pageContext.request.contextPath}/basRelationCampanyAction!removeCarCompanyProperties.action','${pageContext.request.contextPath}/basRelationCampanyAction!findAllCarCompanyProperties.action');">删除</a>
						</privilege:enable>
						<privilege:enable code="CARCOMPANYMODIFY">
							<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#carCompanyProperties_id'));">修改</a>
						</privilege:enable>
						<span id="saveOrCancelBtn"></span>
				  </div>
			<div id="carCompanyProperties_Center" region="center"  style="background:#eee;" border="false">
				<table id="carCompanyProperties_id"></table>
			</div>
	</div>

  </body>
</html>
