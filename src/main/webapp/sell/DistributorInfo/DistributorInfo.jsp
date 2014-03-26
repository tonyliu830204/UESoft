<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分销售商档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/DistributorInfo/DistributorInfo.js"></script>
     <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	 <privilege:enable code="DISTRIBUTOR_ADD">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addDistributorInfo();">新增</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_REMOVE">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeDistributorInfo()">删除</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_EDIT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDistributorInfo();">修改</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryDistributorInfo();">查询</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
	</privilege:enable>
	 <privilege:enable code="DISTRIBUTOR_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
	</privilege:enable>
	 <!--<privilege:enable code="DISTRIBUTOR_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
    </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="distributorInfoQueryForm" name="distributorInfoQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
									<td width="90px">分销商名称:</td>
									<td><input type="text" id="disName" name="disName"  size="10" style="background-color: #c0d8d8;width: 150px"/></td>
								</tr>
							</table>
			 </form>
		    </div>
		    <div id="distributorInfo_div" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="distributorInfo"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
