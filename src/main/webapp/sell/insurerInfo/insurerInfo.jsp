<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>保险公司档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/insurerInfo/insurerInfo.js"></script>
  	<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    <privilege:enable code="INSURER_ADD">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addInsurerInfo();">新增</a>
	</privilege:enable>
	<privilege:enable code="INSURER_REMOVE">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeInsurerInfo()">删除</a>
	</privilege:enable>
	<privilege:enable code="INSURER_EDIT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInsurerInfo();">修改</a>
	</privilege:enable>
	<privilege:enable code="INSURER_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryInsurerInfo();">查询</a>
	</privilege:enable>
	<privilege:enable code="INSURER_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	</privilege:enable>
	<privilege:enable code="INSURER_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
	</privilege:enable>
	<privilege:enable code="INSURER_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
	</privilege:enable><!--
	<privilege:enable code="INSURER_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
    </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="insurerQueryForm" name="insurerQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
									<td width="100px">保险公司名称:</td>
									<td><input type="text" id="insName" name="insName"  size="10" style="background-color: #c0d8d8; width: 150px"/></td>
								</tr>
							</table>
			</form>
		    </div>
		    <div id="insurerInfo_div" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="insurerInfo"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
