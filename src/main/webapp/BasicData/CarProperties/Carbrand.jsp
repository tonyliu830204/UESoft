<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		
		<title>My JSP 'Carbrand.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		      var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString() %>';
		</script>
	</head>
	<body>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/CarProperties/Carbrand.js"></script>
		<div id="cc" class="easyui-layout"
			style="width: 600px; height: 400px;" fit="true" border="false">
		 	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="CARBRANDADD">
						<a id="add_but" href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="CARBRANDDELETE">
						<a id="del_but" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="CARBRANDMODIFY">
						<a id="edit_but" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="CARBRANDEXPORT">
						<a id="out_but" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					<privilege:enable code="CARBRANDIMAGE">
						<a id="update_but" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="showWindow();">更改图标</a>
					</privilege:enable>		
					<span id="saveOrCancelBtn"></span>
			</div>
			<div id="CarbrandCenter" region="center" style="background: #eee;" border="false">
				<table id="table10"></table>
			</div>
		</div>
		<img src="${pageContext.request.contextPath}/images/loading.gif" id="loading" style="display: none;">
		<div id="aa" title="选择品牌图片" data-options="iconCls:'icon-edit',modal:true" style="padding: 1px; width: 300px; height: 400px;">
			<form name="demoForm" id="demoForm" method="post" enctype="multipart/form-data" action="">
			      <table style="width:100%; top:0px;left:0px;right:0px.button:0px;">
				      <tr>
				          <td style="width:80px;">
				                                         选择上传图片: 
				          </td>
				      </tr>
				      <tr>
				          <td style="left:0px;">
				               <input id="file" name="file" type="file" style="width:100%;height:40px;" accept="image/*" onchange="showImg();"/>
				          </td>
				      </tr>
				  </table>
			</form>
			<div>
			进度： <span id="bytesRead"></span> / <span id="bytesTotal"></span>
			</div>
			<div id="imgs"></div>
		</div>
	</body>
</html>