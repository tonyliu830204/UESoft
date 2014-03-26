<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>索理赔结算单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	var TOMONEYNO = "<%=Contstants.TOMONEY_TAG.TOMONEYNO%>";
	</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain.js"></script>
  </head>
  <body class="easyui-layout">
       <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
       <privilege:enable code="CLAIMBILL_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="add" iconCls="icon-add" plain="true" onclick="add();">新增</a>
	   </privilege:enable>
       <privilege:enable code="CLAIMBILL_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="remove" iconCls="icon-remove" plain="true" onclick="_remove();">删除</a>
	   </privilege:enable>	
		<privilege:enable code="CLAIMBILL_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true" onclick="_edit();">修改</a>
	   </privilege:enable>	
		<privilege:enable code="CLAIMBILL_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="search" iconCls="icon-search" plain="true" onclick="query();">查询</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMBILL_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMBILL_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMBILL_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
	   </privilege:enable>
	   <privilege:enable code="CLAIMBILL_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMBILL_REDO">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo" iconCls="icon-redo" plain="true" onclick="settlement();">转收银</a>
	   </privilege:enable>
		<privilege:enable code="CLAIMBILL_OK">
	      	<a href="javascript:void(0)" id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" plain="true" onclick="claimsValidate(true);">审核</a>
	   </privilege:enable>
	  	<privilege:enable code="CLAIMBILL_OK1">
	      	<a href="javascript:void(0)" id="ok1" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" plain="true" onclick="claimsValidate(false);">取消审核</a>
	   </privilege:enable>
		<span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
		<div id="finClaimantMainTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div id="finClaimantMainSummaryTab" data-options="fit:true,border:false,title:'索理赔账单汇总',href:'${pageContext.request.contextPath}/frt/finClaimantMain/summary.jsp'">
				
			</div>
			<div data-options="fit:true,border:false,title:'索理赔帐单明细',href:'${pageContext.request.contextPath}/frt/finClaimantMain/details.jsp'">
				
			</div>
			<div data-options="fit:true,border:false,title:'未确认索理赔工单',href:'${pageContext.request.contextPath}/frt/finClaimantMain/unconfirmed.jsp'">
				
			</div>
		</div>
	</div>
  </body>
</html>