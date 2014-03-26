<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
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
    <title>前台接车</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	    var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
	    var parame2 = '<%=Contstants.TOWORKSHOP_TAG.TOWORKSHOPYES%>';
	    var receptionId="<%=request.getParameter("receptionId")%>";
	    var carId="<%=request.getParameter("carId")%>";
	    var  recpetionFlag=true;
	    function _cancel1(){
		    window.close();
		 }
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception.js"></script>
  </head>
  <body class="easyui-layout">
       <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
       <privilege:enable code="STAGECAR_ADD">
	      <a href="javascript:void(0);" class="easyui-linkbutton" id="save" iconCls="icon-save" plain="true" onclick="_save();">保存</a>
	  </privilege:enable>
		<privilege:enable code="STAGECAR_REMOVE">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="cancel" iconCls="icon-cancel" plain="true" onclick="_cancel1();">取消</a>
	   </privilege:enable>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
			<div id="frtReceptionTabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div data-options="fit:true,border:false,title:'接车明细',href:'frt/frtReception/details.jsp'">
				</div>
			</div>
	   </div>
  </body>
</html>
<div id="aa" title="更改里程数" data-options="iconCls:'icon-edit',modal:true"
	style="padding: 5px; width: 200px; height: 110px;">
	<form id="frtReceptionDistanceForm">
			里程数:<input type="text" name="receptionDistance" id="frtReceptionDistance"
			 data-options="required:true,missingMessage: '里程数为必填',min:0,max:99999999,validType:'integer' "
				class="easyui-numberbox" style="width: 100px;" />
	</form>
</div>