<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>交车结算</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	   var parame1 = '<%=Contstants.DOCUMENT_TAG.DOCUMENTState9 %>';
	   var receptionId="<%=request.getParameter("receptionId")%>";
	   var preclrId="<%=request.getParameter("preclrId")%>";
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtPreClearing.js"></script>
  </head>
  <body class="easyui-layout">
      <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
      <privilege:enable code="SETTLEACCOUNTS_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="remove" iconCls="icon-remove" plain="true" onclick="_remove();">删除</a>
	   </privilege:enable>
		<privilege:enable code="SETTLEACCOUNTS_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
	   </privilege:enable>
<%--		<privilege:enable code="SETTLEACCOUNTS_SEARCH">--%>
<%--	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="search" iconCls="icon-search" plain="true" onclick="query();">查询</a>--%>
<%--	   </privilege:enable>--%>
<%--		<privilege:enable code="SETTLEACCOUNTS_CLEAR">--%>
<%--	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>--%>
<%--	   </privilege:enable>--%>
		<privilege:enable code="SETTLEACCOUNTS_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="SETTLEACCOUNTS_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" onclick="addprint();" iconCls="icon-set" plain="true">打印</a>
	   </privilege:enable>
<!-- 
	   <privilege:enable code="SETTLEACCOUNTS_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
 -->
		<privilege:enable code="SETTLEACCOUNTS_REDO">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo" iconCls="icon-redo" plain="true" onclick="settlement();">转收银</a>
	   </privilege:enable>
		<privilege:enable code="SETTLEACCOUNTS_REDO2">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo2" iconCls="icon-redo" plain="true" onclick="modifyTransFormReceptionState('<%=Contstants.DOCUMENT_TAG.DOCUMENTState9 %>');">洗车</a>
	   </privilege:enable>
		<privilege:enable code="SETTLEACCOUNTS_REDO3">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo3" iconCls="icon-redo" plain="true" onclick="modifyTransFormReceptionState('<%=Contstants.DOCUMENT_TAG.DOCUMENTState10 %>');">待交车</a>
	   </privilege:enable>
		<privilege:enable code="SETTLEACCOUNTS_BACK">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="back" iconCls="icon-back" plain="true" onclick="_back();">返工</a>
	   </privilege:enable>
		<span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
		<div id="frtPreClearingTabs" class="easyui-tabs" data-options="fit:true,border:false">
<%--			<div id="frtPreClearingSummaryTab" data-options="fit:true,border:false,title:'结算单汇总',href:'${pageContext.request.contextPath}/frt/frtPreClearing/summary.jsp'">--%>
<%--			</div>--%>
			<div data-options="fit:true,border:false,title:'结算单明细',href:'${pageContext.request.contextPath}/frt/frtPreClearing/details.jsp'">
			</div>
		</div>
	</div>
  </body>
</html>
