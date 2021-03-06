<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>代办项目查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/sellDbProject/queryDbInfor.js"></script>
		 <script type="text/javascript">
	    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		


</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">
			<privilege:enable code="ALLOAUERY_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="ALLOAUERY_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="ALLOAUERY_PRINT">
			<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="ALLOAUERY_EXPORT">
			<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true" onclick="_except();">导出</a>
			</privilege:enable>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 100px;">
					<form id="carQueryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td width="70px">
									销售日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="xs_Car_Sel_Data" name="xs_Car_Sel_Data"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="xs_Car_Sel_Data2" name="xs_Car_Sel_Data2"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'});" />
								</td>
								<td>客户姓名:</td>
								<td><input type="text" name="xs_Custom_Name" style="width: 120px" /></td>
							
									</table>
						</fieldset>
					</form>
				</div>
				<div id="allocateTree" data-options="region:'center',border:false"
					style="background: #eee;">
					<table id="Tree" name="Tree"></table>
				</div>
			</div>
		</div>
	</body>
</html>
