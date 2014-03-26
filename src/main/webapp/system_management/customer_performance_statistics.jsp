<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>员工业绩统计</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/customer_performance_statistics.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" style="width: 800px; height: 600px;" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
			<privilege:enable code="ACHIEVEMENT_SEARCH">
			    <a id="_search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		   </privilege:enable>
			<privilege:enable code="ACHIEVEMENT_CLEAR">
			    <a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">清空</a>
		   </privilege:enable>
		   <privilege:enable code="ACHIEVEMENT_EXPORT">
			    <a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">Excel导出</a>
		   </privilege:enable>
			</div>
			<div style="background:#eee;" data-options="region:'center',border:false">
				<div id="parameterTabs" class="easyui-tabs" data-options="fit:true,border:false">
				<div id="parameterOne"
				data-options="fit:true,border:false,title:'维修人员业绩统计',href:'system_management/customer_performance_statistics/weixiuyejitongji.jsp'">
				</div>
				<div id="parameterTwo"
				data-options="fit:true,border:false,title:'结算工时查询',href:'system_management/customer_performance_statistics/jiesuangongshichaxun.jsp'">
				</div>
				<div id="parameterThree"
				data-options="fit:true,border:false,title:'索赔结算工时统计',href:'system_management/suopeijiesuangongshitongji.jsp'">
				</div>
				<div id="parameterFour"
				data-options="fit:true,border:false,title:'维修人员业绩统计汇总',href:'system_management/customer_performance_statistics/weixiuyejitongjihuizong.jsp'">
				</div>
				<div id="parameterFive"
				data-options="fit:true,border:false,title:'业务员业绩统计',href:'system_management/yewuyuanyejitongji.jsp'">
				</div>
				</div>
		</div>
			
		</div>
	</body>
</html>
