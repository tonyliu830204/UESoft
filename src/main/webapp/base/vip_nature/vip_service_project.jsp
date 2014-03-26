<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员服务套餐</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/vip_nature/vip_nature.js"></script>
    <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
				<div region="center"  style="background:#eee;">
					<div id="tabs_service_project_id" class="easyui-tabs" fit="true"  border="false">
						<div title="会员卡服务项目汇总" fit="true"  border="false">
							<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
									        <div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
											<privilege:enable code="VIPSERVICEADD">
												<a href="javascript:void(0);" class="easyui-linkbutton"  id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd($('#tb_vip_service_project_id'));">新增</a>
											</privilege:enable>
											<privilege:enable code="VIPSERVICEDELETE">
												<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"   iconCls="icon-remove" plain="true" onclick="doDelete($('#tb_vip_service_project_id'),'vipServiceProjectAction!doDelete.action','vipServiceProjectAction!doFind.action');">删除</a>
											</privilege:enable>
											<privilege:enable code="VIPSERVICEMODIFY">
												<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"  iconCls="icon-edit" plain="true" onclick="doUpdate($('#tb_vip_service_project_id'));">修改</a>
											</privilege:enable>
											<privilege:enable code="VIPSERVICEQUERY">
												<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit($('#tb_vip_service_project_id'),$('#form_vip_service_project_id'),'vipServiceProjectAction!doFind.action')">查询</a>
											</privilege:enable>	
<!--											<privilege:enable code="VIPSERVICEEXPORT">-->
<!--												<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick="  _except('tb_vip_service_project_idCenter','会员服务套餐汇总信息');">导出</a>-->
<!--											</privilege:enable>	-->
											<span id="saveOrCancelBtn"></span>
											</div>	
								            <div data-options="region:'center',border:false" style="background:#eee;">
											 <div class="easyui-layout" data-options="fit:true,border:false"> 
												<div data-options="region:'north',title:'查询条件'" style="overflow: hidden; padding: 1px; background: #eee; height: 55px;" border="false">
													<form id="form_vip_service_project_id">
																				<table style="text-align:right">
																   					<tr>
																   						<td>套餐编号:</td>
																   						<td><input name="meal_Id"/></td>
																   						<td>套餐名称:</td>
																   						<td><input name="meal_Name"/></td>
																					</tr>
														   						</table>
													</form>
												</div>
												<div region="center" style="background: #eee" border="false" >
														<table id="tb_vip_service_project_id"></table>
												</div>
											  </div>
											</div>
									</div>
								</div>
							<div title="会员卡服务项目明细" fit="true"  border="false">
							<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
									 <div data-options="region:'center',border:false" style="background:#eee;">
										<div class="easyui-layout" data-options="fit:true,border:false"> 
										        <div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
													<privilege:enable code="SERVICEPROADD">
														<a href="javascript:void(0);" class="easyui-linkbutton" id="btnadd"  iconCls="icon-add" plain="true" onclick="doAdd1($('#tb_vip_service_project_detial_id'));">新增</a>
													</privilege:enable>
													<privilege:enable code="SERVICEPRODELETE">
														<a href="javascript:void(0);" class="easyui-linkbutton" id="btnremove"  iconCls="icon-remove" plain="true" onclick="del();">删除</a>
													</privilege:enable>
													<privilege:enable code="SERVICEPROMODIFY">
														<a href="javascript:void(0);" class="easyui-linkbutton" id="btnedit"  iconCls="icon-edit" plain="true" onclick=" doUpdate1($('#tb_vip_service_project_detial_id'));">修改</a>
													</privilege:enable>
													<privilege:enable code="SERVICEPROEXPORT">
														<a href="javascript:void(0);" class="easyui-linkbutton" id="btnexport" iconCls="icon-export" plain="true" onclick="_except('tb_vip_service_project_detial_id_div','会员服务套餐明细信息');">导出</a>
													</privilege:enable>	
													<span id="saveOrCancelBtn1"></span>
										         </div>	
												<div id="tb_vip_service_project_detial_id_div" region="center" style="background: #eee" border="false" >
														 <input type="hidden" id="meal_Id" />
														 <input type="hidden" id="meal_Name" />
														 <table id="tb_vip_service_project_detial_id"></table>
												</div>
										</div>
								    </div>
									</div>
								</div>
							</div>
						</div>
					</div>
  </body>
</html>