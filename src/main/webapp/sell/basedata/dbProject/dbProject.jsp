<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>代办项目</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body class="easyui-layout">
	<script type="text/javascript">
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	 </script>
		 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/basedata/dbProject/dbProject.js"></script>
		 	<div class="easyui-layout" data-options="fit:true, border: false">
			 	<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addProject();">新增</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeProject()">删除</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editProject();">修改</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryProject();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
					<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
					<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a><!--
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
				--></div>
				<div region="center"  split="false" border="false">
					<div id="cc" class="easyui-layout" style="width:600px;height:500px;" fit="true" border="false">		
						<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:3px; background:#eee; height:56px;" border="false">
							<form id="proQueryForm" name="proQueryForm" method="post"  fit="true" >
								<table>
									 <tr>
										<!--<td>项目代码:</td>
										<td><input type="text" id="proCode" name="proCode"  size="10" style="background-color: #c0d8d8;"/></td>
										--><td>项目名称:</td>
										<td><input type="text" id="proName" name="proName"  style="background-color:#c0d8d8;" /></td>
									</tr>
								</table>
							 </form>
			   			</div>	   		
			   			<div id="projectData_id" region="center" style="background:#eee;" border="false">
			   			 	<table id="projectData" ></table>
			   			</div>   
					</div>	
		   		</div>
  	   </div>
	</body>
</html>