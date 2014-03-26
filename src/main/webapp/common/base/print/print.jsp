<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>模板套打页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" id="easyuiTheme" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" id="easyuiiconTheme" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/default.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Lodop/print.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Lodop/HashMap.js"></script>
  </head>
  
  <body>
      <div style="height:30px;background:#eee;width:100%;">
           <button id="print_Butt">打印</button>
      </div>
      <div id="printDiv" style="width:100%;height:95%;overflow:auto;">
		   
	  </div>
	  <div id="showhtml" style="width:0px;height:0px;display:none;">
	  </div>
  </body>
</html>