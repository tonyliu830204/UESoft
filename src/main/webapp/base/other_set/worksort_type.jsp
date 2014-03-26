<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>工时分类</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
  	<div class="easyui-layout" fit="true" border="false" style="width:500px;height:400px;background: #eee">
  		<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
			<privilege:enable code="WORKSORTADD">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#datagrid_worksort_type_id'));">新增</a>
				</privilege:enable>
				<privilege:enable code="WORKSORTDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove" iconCls="icon-remove" plain="true" onclick="doDelete($('#datagrid_worksort_type_id'),'${pageContext.request.contextPath}/basWorkhourSorAction!doDelete.action','${pageContext.request.contextPath}/basWorkhourSorAction!doFindAll.action');">删除</a>
				</privilege:enable>
				<privilege:enable code="WORKSORTMODIFY">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="doUpdate($('#datagrid_worksort_type_id'));">修改</a>
				</privilege:enable>
				<privilege:enable code="WORKSORTEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnsearch" iconCls="icon-export" plain="true" onclick=" _except('datagrid_worksort_center','工时分类信息');">导出</a>
					</privilege:enable>
				<span id="saveOrCancelBtn"></span>
	   </div>
   		<div id="datagrid_worksort_center" region="center" style="background:#eee;" border="false">
   		<table id="datagrid_worksort_type_id"></table>
   		</div>   
  	</div>
  </body>
</html>
