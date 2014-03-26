<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>工具管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base.js"></script>
  </head>
  
  <body>
  <!-- 新增工具管理 -->
  <div id="addToolsManagerView"></div>
  
    <div class="easyui-layout" fit="true" border="false">
	    <div region="north" style="overflow: hidden;padding: 3px; background:#eee; height:30px;" border="false">
	    	<form id="toolsForm">
	    		编号:<input type="text" name="toolsId"/>
		    	名称:<input type="text" name="toolsName"/>
				状态:<input name="toolsState" class="easyui-combobox"
						data-options="
						url:'${pageContext.request.contextPath}/common/json/toolsState.json',  
						valueField:'id',  
						textField:'text'"/>
				</form>
	    </div>  
	    <div region="center" style="background:#eee;" border="false">
	    	<table id="toolsManager"></table>
	    </div>
    </div>
  </body>
</html>
