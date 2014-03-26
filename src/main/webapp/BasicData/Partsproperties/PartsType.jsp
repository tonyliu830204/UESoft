<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>配件型号资料</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Partsproperties/PartState.js"></script>
  	<div class="easyui-layout" fit="true" border="false">
  			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:3px; background:#eee; height:56px;" border="false">
				<form id="mtiForm">
					配件品牌:<input type="text" name="pbrdId" class="easyui-combobox" data-options="
   					url : 'basMountingsTypeInfo_findAllPartsBrand.action',
					valueField:'id',  
				    textField:'text'"/>
   					&nbsp;&nbsp;配件型号:<input type="text" name="ptypeName"/>
   				</form>
			</div>  
	  		<div id="PartsTypeCenter" region="center" style="background:#eee;" border="false">
	   			<table id="mountingsTypeInfo"></table>
	   		</div>
   		</div>   
  </body>
</html>
