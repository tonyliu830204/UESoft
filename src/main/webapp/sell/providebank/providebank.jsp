<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>贷款银行档案</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	</script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/providebank/providebank.js"></script>
  </head> 
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		<privilege:enable code="PROVIDEBANK_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addProvidebank();">新增</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeProvidebank()">删除</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editProvidebank();">修改</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryProvidebank();">查询</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_PRINT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
		</privilege:enable>
		<privilege:enable code="PROVIDEBANK_EXPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
		</privilege:enable>
		<!--<privilege:enable code="PROVIDEBANK_IMPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
       </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="providebankQueryForm" name="providebankQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
									<td width="90px">银行名称：</td>
									<td><input type="text" id="bankName" name="bankName"  size="10" style="background-color: #c0d8d8; width: 150px"/></td>
								</tr>
							</table>
			</form>
		    </div>
		    <div id="providebank_div" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="providebank"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
