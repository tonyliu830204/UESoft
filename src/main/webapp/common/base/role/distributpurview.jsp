<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分布点权限管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR%>";
	    var systemType = "<%=Contstants.SYSTEMTYPE.SYSTEM%>";
	    var systemAll = "<%=Contstants.SYSTEMTYPE.ALL%>";
	    var employeeLevelKey="<%=Contstants.EMPLOYEELEVEL.EMPLOYEELEVELKEY %>";
	    var enterpriseAdmin="<%=Contstants.EMPLOYEELEVEL.ADMIN %>";
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/role/distributpurview.js"></script>
  </head>
  <body>
          <div id="cc" class="easyui-layout" fit="true" border="false">  
               <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
			    <!-- 按钮区域 -->
			    <privilege:enable code="DISTRIBUTPURVIEW_SEARCH">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  plain="true" id="searchRoleBtn" onclick="distributeSearch();" >查询</a>
			    </privilege:enable>
			    <privilege:enable code="DISTRIBUTPURVIEW_CLEAR">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"  plain="true" id="qcRoleBtn" onclick="distributeClear();">清空</a>
			    </privilege:enable>
			    <privilege:enable code="DISTRIBUTPURVIEW_EXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id="dcRoleBtn" onclick="distributeExport();">Excel导出</a>
			    </privilege:enable>
			    <privilege:enable code="DISTRIBUTPURVIEW_SAVE">
				    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" 
	              			plain="true" id="saveMenuDetailButton" onclick="saveMenuDetail();">保存</a>
			    </privilege:enable>
				<span id="button"></span><br/>
              </div>
			  <div region="center"  split="false" border="false">
                 <div id="cc" class="easyui-layout" fit="true" border="false">
                 	<div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:60px;" border="false">
						 <form id="distributeSearchForm">
                              <table>
	                               <tr>
	                                <td>员工名称:</td>
									<td><input type="text" id="stfName" name="stfName" style="width:90px;" /></td>
									<td>所属部门:</td>
									<td><input id="deptName" class="easyui-combobox" name="deptName" data-options="   
									         valueField: 'id',   
									         textField: 'text',  
									         url: 'basPersonnelInformationSetAction!findAllDept.action',
									         validType:'isSelected[\'#deptName\']',
    										 invalidMessage : '请从下拉框中选择部门'"/>
									</td>
									<td>职位工种:</td>
									<td><input type="text" id="stfZwgz" name="stfZwgz" style="width:90px;" /></td>
                                  </tr>
                              </table>                                                      
					     </form>
					</div> 
					<div id="distributpurview_center" data-options="region:'center',border:false" style="background:#eee;">  
				         <div id="dd" class="easyui-layout" fit="true" border="false">
						    <div id="distributPurview_center" data-options="region:'west',border:false" style="background:#eee;width:330px;">  
						          <table id="distributPurview" ></table>
						    </div> 
							<div id="distributPurviewUser_center" data-options="region:'center',border:false" style="background:#eee;">  
						        <table id="distributPurviewUser" ></table>
						    </div> 
						    <div id="distributPurviewMenuDetail_center" data-options="region:'east',border:true" style="background:#eee;width:410px;">
								<form id="RoleAddForm">
								    <input id="acheckeds" name="checkeds" type="hidden"/>
								    <input id="aselecteds" name="selecteds" type="hidden"/>
								    <input id="aenterpriseId" name="enterpriseId" type="hidden"/>
									<div region="center" fit="true" border="false">
									      <ul id="distributePurviewTree" class="easyui-tree" data-options="animate:true,dnd:true" style="width:100%;height:100%;"></ul>
									</div>
								</form>
						    </div> 
		                </div>
				    </div> 
                </div>
			</div>
		</div>
  </body>
</html>