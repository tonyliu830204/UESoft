<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
	<head>
		<title>会员卡服务项目</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vipcard_server_project.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
			<div data-options="region:'north',collapsible:false" style="overflow: hidden;padding:3px;background:#eee;height:90px;" border="false"> 
				<privilege:enable code="CARDSERVICEADD">
					<a id="addBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd();">新增</a>
				</privilege:enable>
				<privilege:enable code="CARDSERVICEDELETE">
					<a id="removeBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
				</privilege:enable>
				<privilege:enable code="CARDSERVICEEDIT">
					<a id="editBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit();">修改</a>
				</privilege:enable>
				<privilege:enable code="CARDSERVICEQUERY">
					<a id="searchBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
				</privilege:enable>
				<privilege:enable code="CARDSERVICECLEAR">
					<a id="clearBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
				</privilege:enable>
				<form id="form_vip_card_service_project_condition_id" method="post">
					<fieldset style="height:40px">
					    <legend style="font-weight:bold">查询条件</legend>
						<table border="0" style="text-align: right">
							<tr>
								<td style="width:70px">会员卡号:</td>
								<td width="120">
								     <input name="vip_Number" type="text" style="width:120px" />
								</td>
								<td style="width:60px">套餐名称:</td>
								<td width="120">
								     <input name="meal_Name" type="text" style="width:120px" />
								</td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
			<div region="center" border="false" style="background: #eee">
				<table id="Vipcard_server_project_center_id"></table>
			</div>
		</div>
	</body>
</html>