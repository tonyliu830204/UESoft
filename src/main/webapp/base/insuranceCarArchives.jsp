<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>保险(汽厂)档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/insuranceCarArchives.js"></script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<privilege:enable code="INSYRANCEARCHIVES_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增</a>
	   	</privilege:enable>
    	<privilege:enable code="INSYRANCEARCHIVES_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>
	   	</privilege:enable>  
    	<privilege:enable code="INSYRANCEARCHIVES_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	   	</privilege:enable>    
    	<privilege:enable code="INSYRANCEARCHIVES_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	   	</privilege:enable>      
    	<privilege:enable code="INSYRANCEARCHIVES_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   	</privilege:enable>        
    	<privilege:enable code="INSYRANCEARCHIVES_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	   	</privilege:enable>
<!-- 
	   	<privilege:enable code="INSYRANCEARCHIVES_CHANGE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'"    >变更</a>
	   </privilege:enable> -->
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="insuranceCarArchivesQueryForm">
		    		<table>
		    			<tr>
		    				<td>保险公司及汽车厂商:</td>
		    				<td><input type="text" name="relcampName"/></td>
		    			</tr>
		    		</table>
		    	</form>
		    </div>
		    <div id="insuranceCarArchivesDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="insuranceCarArchivesDatagrid"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
