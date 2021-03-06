<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>保险估价单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"><!--
	   var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
	   var parame2 = '<%=Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING%>';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtInsurePrize.js"></script>
  </head>
   <body class="easyui-layout">
       <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
       	<privilege:enable code="INSUREBILL_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="add" iconCls="icon-add" plain="true" onclick="add();">新增</a>
	   </privilege:enable>
       	<privilege:enable code="INSUREBILL_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="remove" iconCls="icon-remove" plain="true" onclick="_remove();">删除</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="search"iconCls="icon-search" plain="true" onclick="query();" 
			id="resevationSelect">查询</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模版</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton"  id="set"  iconCls="icon-set" plain="true">打印设置</a>
	   </privilege:enable>
	   <privilege:enable code="INSUREBILL_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
		<privilege:enable code="INSUREBILL_REDO">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo" iconCls="icon-redo" plain="true" onclick="isFrtResevationCancel();">转换</a>
	   </privilege:enable>
		<span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
		<div id="frtInsurePrizeTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div id="frtInsurePrizeSummaryTab"
			data-options="fit:true,border:false,title:'保险估价汇总',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/summary.jsp'">
			</div>
			<div
			data-options="fit:true,border:false,title:'保险估价明细',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/details.jsp'">
			</div>
		</div>
	</div>
  </body>
</html>