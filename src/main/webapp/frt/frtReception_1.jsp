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
	    var  recpetionFlag=false;
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception.js"></script>
  </head>
  <body class="easyui-layout" >
       <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
       <privilege:enable code="STAGECAR_ADD">
	      <a href="javascript:void(0);" class="easyui-linkbutton" id="add" iconCls="icon-add" plain="true" onclick="add();">新增</a>
	  </privilege:enable>
		<privilege:enable code="STAGECAR_REMOVE">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="remove" iconCls="icon-remove" plain="true" onclick="_remove();">删除</a>
	   </privilege:enable>
		<privilege:enable code="STAGECAR_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="edit" iconCls="icon-edit" plain="true" onclick="edit();">修改</a>
	   </privilege:enable>
		<privilege:enable code="STAGECAR_REDO">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="redo" iconCls="icon-redo" plain="true" onclick="transFormPlant();">转到车间</a>
	   </privilege:enable>
		<privilege:enable code="STAGECAR_REDO2">
	      	<a href="javascript:void(0)" id="redo2" class="easyui-menubutton" data-options="menu:'#mm3',iconCls:'icon-redo'">转到结算</a>
	   </privilege:enable>
	   <span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
			<div id="frtReceptionTabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div data-options="fit:true,border:false,title:'接车明细',href:'frt/frtReception/details.jsp'">
				</div>
			</div>
		</div>
		<div id="mm3" style="width:150px;">
			<div onclick="transFormBalanace('<%=Contstants.DOCUMENT_TAG.DOCUMENTState9 %>');"><input type="text" class="easyui-combobox" value="<%=Contstants.DOCUMENT_TAG.DOCUMENTState9 %>"
					data-options="
					disabled:true,
					editable : false,
					url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.DOCUMENT_TAG.DOCUMENTKEY %>',
					valueField:'id',  
					textField:'text' "/></div>
			<div class="menu-sep"></div>
			<div onclick="transFormBalanace('<%=Contstants.DOCUMENT_TAG.DOCUMENTState10 %>');"><input type="text" class="easyui-combobox" value="<%=Contstants.DOCUMENT_TAG.DOCUMENTState10 %>"
					data-options="
					disabled:true,
					editable : false,
					url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.DOCUMENT_TAG.DOCUMENTKEY %>',
					valueField:'id',  
					textField:'text' "/></div>
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