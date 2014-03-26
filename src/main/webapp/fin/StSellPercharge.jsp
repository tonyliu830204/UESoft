<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>维修储备金</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPercharge.js"></script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<privilege:enable code="PERCHARGEADD">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPersonnel(1);">新增</a> 
    	</privilege:enable> 
    	<privilege:enable code="PERCHARGEDELETE">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="deleteStSellPercharge();">删除</a> 
    	</privilege:enable>
    	<privilege:enable code="PERCHARGEEDIT"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="addPersonnel(2);">修改</a> 
    	</privilege:enable> 
    	<privilege:enable code="PERCHARGESEARCH">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="ssp_searchByCondition();">查询</a>  
    	</privilege:enable>
    	<privilege:enable code="PERCHARGECLEAR">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="ssp_clearSearchCondition();">清空</a>  
    	</privilege:enable>
    	<privilege:enable code="PERCHARGEPRINT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">打印</a>  
    	</privilege:enable>
    	<privilege:enable code="PERCHARGEEXPORT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>  
    	</privilege:enable>
    	<span id="button"></span>
    </div>  
    <div data-options="region:'center'" style="background:#eee;" data-options="border:false">
    	 <div id="fin_ar_tabs" class="easyui-tabs" style="background:#eee;" data-options="fit:true,border:false">  
		    <div data-options="title:'维修储备金',href:'${pageContext.request.contextPath}/fin/StSellPercharge/repairAdvances.jsp'">
		    </div>  
		    <div data-options="title:'储备金余额',href:'${pageContext.request.contextPath}/fin/StSellPercharge/stRecharge.jsp'">  
		    </div>  
		    <div data-options="title:'储备金使用记录',href:'${pageContext.request.contextPath}/fin/StSellPercharge/stUsePercharge.jsp'">  
		    </div>  
		</div>  
    </div>
  </body>
</html>
