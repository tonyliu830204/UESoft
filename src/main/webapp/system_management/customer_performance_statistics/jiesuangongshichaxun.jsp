<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>结算工时查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/customer_performance_statistics/jiesuangongshichaxun.js"></script>
  </head>
  
  <body>
   <div class="easyui-layout" border="false" fit="true"
		style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:85px;" border="false">
			<div>
			   	<privilege:enable code="ACHIEVETIME_SEARCH">
				    <a id="_search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryBalanceHoursQuery();">查询</a>
			   </privilege:enable>
				<privilege:enable code="ACHIEVETIME_CLEAR">
				    <a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearBalanceHoursQuery();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="ACHIEVETIME_EXPORT">
				    <a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptBalanceHoursQuery();">Excel导出</a>
			   </privilege:enable>
		   </div>
			<form id="balanceHoursQueryForm" method="post">
				<table border="0" style="text-align: right">
					<tr>
						<td>结算日期：</td>
						<td>
						 <input id="balanceHours_preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'balanceHours_preclrTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="balanceHours_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'balanceHours_preclrTimeBegin\',{d:0})}'})"/>
						</td>
						<td>车牌照：</td>
						<td>
							<input type="text"  style="width:140px;" name="carLicense" class="easyui-combobox"
						 data-options="
						 url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
						valueField : 'id',
						textField : 'text',
						mode : 'remote'"/>
						</td>
						
						<td>统计性质：</td>
						<td>
							<input type="text"  style="width:140px;" id="balanceHours_weaveWay" name="weaveWay" class="easyui-combobox" data-options="
							editable:false,
							data:[{'id':'false','text':'维修员'},{'id':'true','text':'接待员'}],
	   						valueField:'id',  
	   					    textField:'text'"/>
						</td>
						<td>维修人员：</td>
						<td>
							<input  name="stfId"  style="width:140px;" id="balanceHours_stfId"
								class="easyui-combobox"
								data-options="
								validType:'isSelected[\'#balanceHours_stfId\']',
								invalidMessage : '请从下拉框中选择维修员',
								mode : 'remote',
								url : 'basStuffClassAction!findEnterpriseMaintainArtificer.action',
								valueField:'id',  
								textField:'text' "
								/>
						</td>
					</tr>
					<tr>
						<td>维修项目：</td>
						<td><input name="itemName" type="text" style="width:296px"/></td>
						<td>工单号：</td>
						<td><input type="text" name="receptionId" style="width:140px;"/></td>
					</tr>
				</table>
			</form>
			<table style="margin-top:-40px;margin-left:572px;">
				<tr>
					<td>接待员：</td>
					<td>
						<input  name="receivePerson"  style="width:140px;" id="balanceHours_receivePerson"
							class="easyui-combobox"
							data-options="
							validType:'isSelected[\'#balanceHours_receivePerson\']',
							invalidMessage : '请从下拉框中选择接待员',
							mode : 'remote',
							url : 'basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
							valueField:'id',  
							textField:'text' "
							/>
					</td>
					<td>维修班组：</td>
					<td>
						<input type="text" id="balanceHours_serviceGroupId" name="serviceGroupId" class="easyui-combobox"
		  					 style="width: 140px;"
						data-options="
						url : 'frtOptionsAction!findAllRepairGroup.action',
						validType:'isSelected[\'#balanceHours_serviceGroupId\']',
						invalidMessage : '请从下拉框中选择维修班组',
						mode:'remote',
						valueField:'id',  
						textField:'text' "/>	
					</td>
				</tr>
			</table>
		</div>
		<div id="balanceHoursQueryDatagrid_center" region="center" border="false" style="background : #eee">
			<table id="balanceHoursQueryDatagrid"></table>
		</div>
	</div>
  </body>
</html>
