<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>索赔综合查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/claimantQuery.js"></script>
  </head>
  <body >
   	<div class="easyui-layout" fit="true" border="false">
   		<div region="north"  split="false" style="height:30px;background: #eee;" border="false">
	    <privilege:enable code="CLAIMANTCOLLIGATEQUERY_SEARCH">
	      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查询</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMANTCOLLIGATEQUERY_CLEAR">
	      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">清空</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMANTCOLLIGATEQUERY_PRINT">
	      	<a id="_print" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMANTCOLLIGATEQUERY_SET">
	      	<a id="_set" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true">打印设置</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMANTCOLLIGATEQUERY_EXPROT">
	      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">Excel导出</a>
	   </privilege:enable>
	      </div>  
		<div region="center" style="background:#eee;" border="false">    
	  	<div id="claimantQueryTabs" class="easyui-tabs" style="width:800px; height:490px;" fit="true" border="false">
	    <div data-options="title:'索赔-配件查询',broder:false,href:'${pageContext.request.contextPath}/frt/claimantQuery/claimParts.jsp'">
	    </div>
	  	<div data-options="title:'索赔-出库查询',broder:false,href:'${pageContext.request.contextPath}/frt/claimantQuery/claimExwarehouse.jsp'">
	  	</div>
	  	</div>
	  	</div>
   	</div>
  </body>
</html>
