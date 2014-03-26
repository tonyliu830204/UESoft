<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%
String sysType = new String(request.getParameter("sysType").toString().getBytes("ISO8859_1"), "UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>工作属性</title>
  </head>
  <body>
    <script type="text/javascript">
    	var staticSysType="<%=request.getParameter("sysType") %>";
    </script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/basJobProperty.js"></script>

    <div class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">
    		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="BASJOBADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"   iconCls="icon-add" plain="true" onclick="doAdd();">新增</a>
				</privilege:enable>
				<privilege:enable code="BASJOBDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
				</privilege:enable>
				<privilege:enable code="BASJOBMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate();">修改</a>
				</privilege:enable>
				<privilege:enable code="BASJOBEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick="_except('bas_Job_Property_idCenter','工作属性信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
	<div id="bas_Job_Property_idCenter" region="center"  style="background:#eee;" border="false">
	    <input type="hidden" id="systemId" name="systemId" value="<%=sysType%>">
		<table id="bas_Job_Property_id"></table>
	</div>
	</div>
  </body>
</html>
