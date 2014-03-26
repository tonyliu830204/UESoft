<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
     var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR%>";
     var systemType = "<%=Contstants.SYSTEMTYPE.SYSTEM%>";
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/menu/menuInformation.js"></script>
  </head>
  <body>
          <div id="cc" class="easyui-layout" fit="true" border="false">  
               <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
			    <!-- 按钮区域 -->
			    <privilege:enable code="MENUADD">
                     <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"  id="addPMenuBtn" onclick="addPMenu();">新增</a>
			    </privilege:enable>
			    <privilege:enable code="MENUDELETE">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="deletePMenuBtn" onclick="deletePMenu();">删除</a>
			    </privilege:enable>
			    <privilege:enable code="MENUUPDATA">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="updatePMenuBtn" onclick="updatePMenu();">修改</a>
			    </privilege:enable>
			    <privilege:enable code="MENUEXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id="dcPMenuBtn" onclick="_except();">导出</a>
			    </privilege:enable>
				<span id="button"></span><br/>
              </div>
			  <div region="center"  split="false" border="false">
                  <div id="tt" class="easyui-tabs" fit="true" border="false">  
		                <div id="pmenu" title="菜单汇总" style="display:block;"  fit="true">
	                        <div id="cc" class="easyui-layout" fit="true" border="false">
								<div data-options="region:'center',border:false" style="background:#eee;">  
                                    <div id="mor_tabs" class="easyui-tabs" data-options="fit:true">  
							        <table id="menuInformation" name="menuInformation"></table>
							        </div>
							    </div> 
			                </div>
		                </div> 
				  </div> 
			</div>
		</div>
  </body>
</html>