<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>标准项目工时</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/other_set.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/other_set/Standard_project_man-hours.js"></script>
  		 <div id="cc" class="easyui-layout" style="width:800px ;height:600px;" fit="true" border="false" >
			<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				    <privilege:enable code="STANDARDADD">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="addStandard();">新增</a>
					</privilege:enable>
					<privilege:enable code="STANDARDDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="delStandard()">删除</a>
					</privilege:enable>
					<privilege:enable code="STANDARDMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit" iconCls="icon-edit" plain="true" onclick="updateStandard();">修改</a>
					</privilege:enable>
					<privilege:enable code="STANDARDOQUERY">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnsearch" iconCls="icon-search" plain="true" onclick="queryStandard();">查询</a>
					</privilege:enable>		
					<privilege:enable code="STANDARDCLEAR">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btncancel" iconCls="icon-cancel" plain="true" onclick="clearStandard();">清空</a>
					</privilege:enable>		
					<privilege:enable code="STANDARDEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport"  iconCls="icon-export" plain="true" onclick=" _except('Standard_project_manhours_center','标准项目工时信息');">导出</a>
					</privilege:enable>
<%--					 <a href="javascript:void(0);" id="spo_importBtn" class="easyui-linkbutton"--%>
<%--						iconCls="icon-import" plain="true" disable="false">Excel导入</a>--%>
					<span id="saveOrCancelBtn"></span>
			  </div>
		 <div data-options="region:'center',border:false" style="background:#eee;">
		 <div class="easyui-layout" data-options="fit:true,border:false"> 
			<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 80px;" border="false">
				<form id="Standard_project_form_id" method="post">
				<table>
					<tr>
  					<td>项目编号:</td>
						<td><input name="repitemId" id="repitemId_id" readonly="readonly" style="background: #c0d8d8"/></td>
						<td>项目名称:</td>
						<td><input   class="easyui-validatebox" data-options="required : true, missingMessage : '项目名称为必填项' " name="repitemName" id="repitemName_id" onkeyup="getCode($('#repitemName_id'),$('#repitemCode_id'))"/></td>
						<td>工时分类:</td>
						<td><input name="repitemSeries"/></td>
						<td>适合车型:</td>
						<td colspan="5"><input style="width:455px" id ="fitCar_id" name="fitCar" class="easyui-combobox" 
								data-options="
								url : '${pageContext.request.contextPath}/basPartsArchivesAction!findAllCarType.action',
								valueField:'id',  
								textField:'text',
								multiple:true,
								editable : true
								"/>
						</td> 
					</tr> 
				 </table>
				</form>
			</div>
			<div id="Standard_project_man_hours_center_id_div" region="center" style="background: #eee" border="false" >
				<table id="Standard_project_man_hours_center_id"></table>
			</div>
		  </div>
		</div>
	</div>
  </body>
</html>
