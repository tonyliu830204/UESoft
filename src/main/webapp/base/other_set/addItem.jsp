<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>加装项目</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/addItem.js"></script>
  	
  		<div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				<privilege:enable code="ADDITEMADD">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#add_project_id'));">新增</a>
					</privilege:enable>
					<privilege:enable code="ADDITEMDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="  doDelete($('#add_project_id'),'${pageContext.request.contextPath}/frtAddItmeAction!delete.action','${pageContext.request.contextPath}/frtAddItmeAction!findAll.action');">删除</a>
					</privilege:enable>
					<privilege:enable code="ADDITEMMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"  iconCls="icon-edit" plain="true" onclick="doUpdate($('#add_project_id'));">修改</a>
					</privilege:enable>
					<privilege:enable code="ADDITEMQUERY">
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnsearch" iconCls="icon-export" plain="true" onclick="_search($('#add_project_form_id'), $('#add_project_id'));">查询</a>
					</privilege:enable>		
					<privilege:enable code="ADDITEMCLEAR">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btncancel" iconCls="icon-cancel" plain="true" onclick="clearItem();">清空</a>
					</privilege:enable>		
					<privilege:enable code="ADDITEMEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					<span id="saveOrCancelBtn"></span>
			  </div>
		 <div data-options="region:'center',border:false" style="background:#eee;">
		 <div class="easyui-layout" data-options="fit:true,border:false"> 
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 50px;" border="false">
					<form id="add_project_form_id" method="post">
  				项目名称：<input type="text" style="width:100px" name="itemName"/>
  			</form>
			</div>
			<div id="add_project_id_div" region="center" style="background: #eee" border="false" >
					   <table id="add_project_id"></table>
			</div>
		  </div>
		</div>
	</div>
  </body>
</html>