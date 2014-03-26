<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>短信记录查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/sms_record_management.js"></script>
  </head>
  <body>
	 <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:90px;" border="false"> 
			<privilege:enable code="MESSAGERECORDDELETE">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete();">删除</a>
			</privilege:enable>
			<privilege:enable code="MESSAGERECORDQUERY">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSubmitByCondition();">查询</a>
			</privilege:enable>
			<privilege:enable code="MESSAGERECORDCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
			<form id="form_sms_record_management_id">
				 <fieldset style="height:40px">
					 <legend style="font-weight:bold">查询条件</legend>
					 <table style="text-align: right">
						  <tr>
							  <td>发送日期:</td>
							  <td ><input id="now_Send_Date" name="now_Send_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'now_Send_Date2\',{d:-1})}'})" style="width: 85px;"/> 至 </td>
							  <td><input id="now_Send_Date2" name="now_Send_Date1" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'now_Send_Date\',{d:1})}'})" style="width: 85px;"/></td>
							  <td>车牌照:</td>
							  <td><input type="text" name="car_License"/></td>
							  <td>底盘号:</td>
							  <td><input type="text" name="car_Vin"/></td>
						 </tr>
					 </table>
				 </fieldset>
			</form>
		</div>
		<div region="center"  style=" background:#eee" border="false">
			 <table id="SMS_record_management_center_id"></table>
		</div>
	 </div>
  </body>
</html>