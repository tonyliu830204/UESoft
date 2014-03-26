<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>结算单管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/sellAccount/sellAccount.js"></script>
		<script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">
			<privilege:enable code="SELLACCOUNT_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="SELLACCOUNT_REMOVE">
			<a href="javascript:void(0);" class="easyui-linkbutton" id="redo" iconCls="icon-remove" plain="true" onclick="isSum();">删除</a>
			</privilege:enable>
			<privilege:enable code="SELLACCOUNT_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="SELLACCOUNT_PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="SELLACCOUNT_EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
			</privilege:enable>	
			<privilege:enable code="SELLACCOUNT_ZSY">
			<a href="javascript:void(0);" class="easyui-linkbutton" id="acction" iconCls="icon-redo" plain="true" onclick="account();">转收银</a>
	        </privilege:enable>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 75px;">
					<form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td width="75px">
									结算日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountDate2\',{d:-1})}'})" name="accountDate" id="accountDate1" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'accountDate1\',{d:1})}'})" name="accountDate2" id="accountDate2" style="width: 110px;"/></td>
								<td>
									结算类型:
								</td>
								<td>
									<input type="text" id="accountType"  
									name="accountType" class="easyui-combobox"
									data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ACCOUNTTYPE%>',
									valueField:'id',   
									textField:'text',
									mode : 'remote',
									validType:'isSelected[\'#accountType\']',
									invalidMessage : '请从下拉框中选择结算类型'">
							
								</td>
								<td>
									结算单号:
								</td>
								<td>
									<input type="text" name="accountCode" style="width: 145px">
								</td>
								<td>
							
								结算类型编号:
								</td>
								<td>
									<input type="text" name="accountTypeId"
										 style="width: 132px"/>
								</td>
								
								
								
								
							</tr>
						</table>
						</fieldset>
					</form>
				</div>
				<div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="account" name="account"></table> 
				</div>
			</div>
		</div>
	</body>
</html>
