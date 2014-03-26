<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/role.css"></link>
	<script type="text/javascript">
	    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR%>";
	    var systemType = "<%=Contstants.SYSTEMTYPE.SYSTEM%>";
	    var systemAll = "<%=Contstants.SYSTEMTYPE.ALL%>";
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/role/roleInformation.js"></script>
  </head>
  <body>
          <div id="cc" class="easyui-layout" fit="true" border="false">  
               <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
			    <!-- 按钮区域 -->
			    <privilege:enable code="AUTHADD">
                	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"  id="addRoleBtn" >新增</a>
			    </privilege:enable>
			    <privilege:enable code="AUTHDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="deleteRoleBtn" >删除</a>
			    </privilege:enable>
			    <privilege:enable code="AUTHUPDATA">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="updateRoleBtn" >修改</a>
			    </privilege:enable>
			    <privilege:enable code="AUTHSEARCH">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  plain="true" id="searchRoleBtn" >查询</a>
			    </privilege:enable>
			    <privilege:enable code="AUTHCLEAR">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" id="qcRoleBtn">清空</a>
			    </privilege:enable>
			    <privilege:enable code="AUTHEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id="dcRoleBtn">导出</a>
			    </privilege:enable>
			    <privilege:enable code="SEARCHEMPAUTH">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-help" plain="true" id="searchUserRoleBtn">查看员工角色</a>
			    </privilege:enable>
			    <privilege:enable code="SETDEFAULTROLE"><!-- SETDEFAULTROLE -->
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true" id="setDefaultRoleBtn">设置默认角色</a>
			    </privilege:enable>
				<span id="button"></span><br/>
              </div>
			  <div region="center"  split="false" border="false">
                  <div id="tt" class="easyui-tabs" fit="true" border="false">  
		                <div id="Role" title="角色汇总" style="display:block;"  fit="true">
	                        <div id="cc" class="easyui-layout" fit="true" border="false">
							    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
									 <form id="roleSearchForm">
			                              <table>
				                               <tr>
				                                    <td>角色名称:</td>
												    <td><input type="text" id="roleName" name="roleName" style="width:90px;" /></td>
			                                  </tr>
			                              </table>                                                      
								     </form>
								</div> 
								<div data-options="region:'center',border:false" style="background:#eee;">  
							          <table id="roleInformation" name="roleInformation"></table>
							    </div> 
			                </div>
		                </div> 
		                <div id="RoleUser" title="员工角色信息" style="display:block;"  fit="true">
	                        <div id="dd" class="easyui-layout" fit="true" border="false">
							    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
									 <form id="pisForm" name="pisForm" method="post"  fit="true" >
									   <table>
												<tr>
													<td>编号:</td>
													<td><input type="text" name="stfYid" style="width: 90px;" /></td>
													<td>姓名:</td>
													<td><input type="text" name="stfName"  style="width: 90px;" id="stfNameF" /></td>
													<td>系统用户:</td>
													<td>  <input id="stfYesF"  name="stfYes"  style="width: 50px;" class="easyui-combobox" data-options="
																	 valueField: 'id',   
															         textField: 'text',
															         panelHeight : 75,
															         url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=stfYes',
															         editable: false
													"/> 
													</td>
													<td>所属部门:</td>
													<td><input id="deptIdF" class="easyui-combobox" name="deptId" data-options="   
															         valueField: 'id',   
															         textField: 'text',  
															         url: '${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllDept.action',
															         editable: false"/>
													</td>
													<td>维修班组:</td>
													<td><input  id="repgrpIdF" class="easyui-combobox" name="repgrpId" style="width: 85px;" data-options="   
															         valueField: 'id',   
															         textField: 'text',  
															         url: '${pageContext.request.contextPath}/basPersonnelInformationSetAction!findAllCJDept.action',
															         editable: false "  /> 
													 </td>
													<td>注销情况:</td>
														<td><input id="stfZxqkF"  name="stfZxqk"  style="width: 75px;" class="easyui-combobox" 
														data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=ZxqkF',editable:false"/> 
															</td>
													 </tr>
											  </table>
									  	 </form>
								</div> 
								<div data-options="region:'center',border:false" style="background:#eee;">  
								     <table id="personnelInformationSet"></table>
							    </div> 
			                </div>
		                </div> 
				  </div> 
			</div>
		</div>
  </body>
</html>