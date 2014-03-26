<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>供应商档案</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/supplierArchives.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
       <privilege:enable code="SUPPLIERARCHIVES_ADD">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增</a>
	   </privilege:enable>
    	<privilege:enable code="SUPPLIERARCHIVES_REMOVE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>
	   </privilege:enable>  
    	<privilege:enable code="SUPPLIERARCHIVES_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
	   </privilege:enable>  
    	<privilege:enable code="SUPPLIERARCHIVES_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
	   </privilege:enable>  
    	<privilege:enable code="SUPPLIERARCHIVES_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   </privilege:enable>    
    	<privilege:enable code="SUPPLIERARCHIVES_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
	   </privilege:enable>  
<!-- 
    	<privilege:enable code="SUPPLIERARCHIVES_CHANGE">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-change'" onclick="supplierChange();">变更</a>
	   </privilege:enable>
	    -->
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:58px;">
		    	<form id="supplierArchivesQueryForm">
		    		<table>
		    			<tr>
		    				<td>供应商名称:</td>
		    				<td><input type="text" name="relcampName"/></td>
		    				<td>地址:</td>
							<td><input type="text" name="relcampAddr" /></td>
							<td>联系人:</td>
							<td><input type="text" name="relcampContact" /></td>
							<td>联系电话:</td>
							<td><input type="text" name="relcampTel1"/></td>
							<td>固定电话:</td>
							<td><input type="text" name="relcampTel2"/></td>
							<td>备注:</td>
							<td><input type="text" name="relcampRemark1" /></td>
		    			</tr>
		    		</table>
		    	</form>
		    </div>
		    <div id="supplierArchivesDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="supplierArchivesDatagrid"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
