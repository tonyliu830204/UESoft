<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车间管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		var state1='<%=Contstants.DOCUMENT_TAG.DOCUMENTState1 %>';
		var state2='<%=Contstants.DOCUMENT_TAG.DOCUMENTState2 %>';
		var state3='<%=Contstants.DOCUMENT_TAG.DOCUMENTState3 %>';
		var state4='<%=Contstants.DOCUMENT_TAG.DOCUMENTState4 %>';
		var state5='<%=Contstants.DOCUMENT_TAG.DOCUMENTState5 %>';
		var state6='<%=Contstants.DOCUMENT_TAG.DOCUMENTState6 %>';
		var state7='<%=Contstants.DOCUMENT_TAG.DOCUMENTState7 %>';
		var state8='<%=Contstants.DOCUMENT_TAG.DOCUMENTState8 %>';
		var state9='<%=Contstants.DOCUMENT_TAG.DOCUMENTState9 %>';
		var state10='<%=Contstants.DOCUMENT_TAG.DOCUMENTState10 %>';
	</script>	
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkshopManager.js"></script>
  </head>
  <body class="easyui-layout">
      <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
      <privilege:enable code="WORKSHOPMAN_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
	   </privilege:enable>
		<privilege:enable code="WORKSHOPMAN_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="search"iconCls="icon-search" plain="true" onclick="query();">查询</a>
	   </privilege:enable>
		<privilege:enable code="WORKSHOPMAN_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="clear" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
	   </privilege:enable>
		<privilege:enable code="WORKSHOPMAN_REDO1">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo1" iconCls="icon-redo" plain="true" onclick="castProcenium();">转前台</a>
	   </privilege:enable>
	   <privilege:enable code="WORKSHOPMAN_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="WORKSHOPMAN_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true" >打印设置</a>
	   </privilege:enable>
	   <privilege:enable code="WORKSHOPMAN_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
		<span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
		<div id="frtWorkshopManagerTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div id="frtWorkshopManagerSummaryTab" data-options="fit:true,border:false,title:'工单汇总',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/summary.jsp'">
			</div>
			<div data-options="fit:true,border:false,title:'工单明细',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/details.jsp'">
			</div>
		</div>
	</div>
  </body>
</html>
<div id="hh" title="更改里程数" data-options="iconCls:'icon-save',modal:true"
	style="padding: 5px; width: 200px; height: 110px;">
	<form id="frtWorkshopManagerDistanceForm">
			里程数:<input type="text" name="receptionDistance" id="frtReceptionDistance"
			 data-options="required:true,missingMessage: '里程数为必填',min:0,max:99999999,validType:'integer' "
				class="easyui-numberbox" style="width: 100px;" />
	</form>
</div>