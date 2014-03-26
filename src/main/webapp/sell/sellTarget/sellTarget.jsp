<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>销售指标设定</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellTarget/sellTarget.js"></script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'center',fit:true, border: false" >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'west',collapsed:false" style="width: 180px;background: #D2E0F2;">
					<ul id="tt" class="easyui-tree"></ul>
				</div>
				<div  data-options="region:'center',border:false" >
					<div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
						<privilege:enable code="SELLTARGET_ADD">
						<a id="_add" href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addSellTarget();">新增</a>
						</privilege:enable>
						<privilege:enable code="SELLTARGET_REMOVE">
						<a id="_remove"  href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSellTarget()">删除</a>
						</privilege:enable>
						<privilege:enable code="SELLTARGET_UPDATE">
						<a id="_update" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSellTarget();">修改</a>
						</privilege:enable>
						<privilege:enable code="SELLTARGET_PRINT">
						<a id="_print"  href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();" >打印</a>
						</privilege:enable>
						<privilege:enable code="SELLTARGET_EXPORT">
						<a id="_export"  href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
						</privilege:enable><!--
						<privilege:enable code="SELLTARGET_IMPORT">
						<a id="_import" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
					   </privilege:enable>
					--></div>
					<div id="sellTarget_div" data-options="region:'center',border:false" style="background:#eee;height: 650px;" >
						 <table id="sellTarget"></table>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>
