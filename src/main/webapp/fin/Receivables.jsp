<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>维修应收款</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/Receivables.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
    	<privilege:enable code="RECEIVABLESSEARCH">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="smr_searchByCondition();">查询</a>
    	</privilege:enable>
    	<privilege:enable code="RECEIVABLESCLEAR">  
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="smr_clearSearchByCondition();">清空</a>
    	</privilege:enable>
    	<privilege:enable code="RECEIVABLESSET">  
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'" onclick="_clear();">设置</a>  
    	</privilege:enable>
    	<privilege:enable code="RECEIVABLESPRINT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">打印</a>  
    	</privilege:enable>
    	<privilege:enable code="RECEIVABLESEXPORT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>  
    	</privilege:enable>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">  
        <div id="mor_tabs" class="easyui-tabs" data-options="fit:true">  
		    <div data-options="title:'维修应收款',href:'${pageContext.request.contextPath}/fin/Receivables/fin_maintenance_receivables.jsp'"></div>  
		    <div data-options="title:'索赔应收款',href:'${pageContext.request.contextPath}/fin/Receivables/fin_claims_receivables.jsp'"></div>  
		    <div data-options="title:'销售应收款',href:'${pageContext.request.contextPath}/fin/Receivables/st_sell_charge.jsp'"></div>  
		</div> 
    </div>
  </body>
</html>
