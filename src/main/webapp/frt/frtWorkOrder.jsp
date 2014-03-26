<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>工单-基本信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkOrder.js"></script>
  </head>
  <body class="easyui-layout">
    <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
    <privilege:enable code="COLLIGATE_BASE_SEARCH">
      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryFrtWorkOrderBase();">查询</a>
   </privilege:enable>
	<privilege:enable code="COLLIGATE_BASE_CLEAR">
      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFrtWorkOrderBase();">清空</a>
   </privilege:enable>
	<privilege:enable code="COLLIGATE_BASE_EXPROT">
      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptFrtWorkOrderBase();">Excel导出</a>
   </privilege:enable>
      </div>  
	<div region="center" style="background:#eee;" border="false">    
  		<div id="frtWorkOrderTabs" class="easyui-tabs" style="width:800px; height:490px;" fit="true" border="false">
		    <div data-options="title:'工单-基本信息',border:false,href:'${pageContext.request.contextPath}/frt/frtWorkOrder/base.jsp'">
		    	
		    </div>
		    <div data-options="title:'工单-结算情况',border:false,href:'${pageContext.request.contextPath}/frt/frtWorkOrder/settlementSituation.jsp'">
		    	
		    </div>
	   </div>
  	</div>
  </body>
</html>
