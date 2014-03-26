<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>剃度加价信息</title>
  </head>
  <body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/BasicData/Partsproperties/StageAddprice.js"></script>
  	<div class="easyui-layout" fit="true" border="false">
  			<div id="xi" region="north"  split="false" style="height:30px;background: #eee;" border="false">
					<privilege:enable code="STAGEADDPRICEADD">
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="add();">新增</a>
					</privilege:enable>
					<privilege:enable code="STAGEADDPRICEDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
					</privilege:enable>
					<privilege:enable code="STAGEADDPRICEMODIFY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update();">修改</a>
					</privilege:enable>
					<privilege:enable code="STAGEADDPRICEEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">导出</a>
					</privilege:enable>
					<span id="saveOrCancelBtn"></span>
			</div>
	  		<div id="PartsTypeCenter" region="center" style="background:#eee;" border="false">
	   			<table id="StageAddpriceTable"></table>
	   		</div>
   	</div>   
  </body>
</html>
