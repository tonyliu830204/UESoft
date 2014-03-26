<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'system_log.jsp' starting page</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/system_log.js"></script>
  </head>
  <body>
   <div id="cc" class="easyui-layout" style="width: 800px; height: 600px;" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
			<privilege:enable code="SYSTEMLOGDELETE">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
			</privilege:enable>
			<privilege:enable code="SYSTEMLOGQUERY">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" 
						onclick="doConditionSubmit();">查询</a>
			</privilege:enable>
			<privilege:enable code="SYSTEMLOGCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" 
						onclick="doClear();">清空</a>
			</privilege:enable>
			<privilege:enable code="SYSTEMLOGEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" onclick="_except1();" plain="true">导出</a>
			</privilege:enable>
		</div>
		<div region="center"  style="background:#eee;">
			<div class="easyui-layout" style="width: 800px; height: 600px;" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
				<form id="form_system_log_id">
					<table style="text-align: right">
						<tr>
							<td>日期：</td>
							<td>
								<input id="beginTime" name="beginTime"  style="width:140px;" class="Wdate"
								 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/>
				                                              至<input id="endTime" name="endTime" style="width:140px;" class="Wdate"
				                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'})"/>
							</td>
							<td>IP地址：</td>
							<td><input name="ipName"/></td>
							<td>操作员：</td>
							<td>
								<input style="width:110px" name="userName"
								class="easyui-combobox"
								data-options="
								url : 'carInsuranceManageAction_getBasStuff.action',
								valueField:'id',  
								textField:'name',
								multiple:false  "
								/>
							</td>
							<td>模块：</td>
							<td><input name="moduleName"/></td>
							<td>功能：</td>
							<td><input name="content"/></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="center"  style="background:#eee;" id="datagridsystemlogid"  border="false">
				<table id="datagrid_system_log_id"></table>
			</div>
			</div>
		</div>
	</div>
  </body>
</html>
