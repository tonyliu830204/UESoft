<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>车辆流失回访提醒</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/Customer_server.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/return_visit/window_carlost.js"></script>
  </head>
 <body>
    
 		<div class="easyui-layout" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden; background:#eee; height:60px;" border="false">
			<form id="form_carlost_return_id">
				<table>
					<tr>
						<td>提醒分类：</td>
						<td><input name="group_Name" value="流失回访" readonly="readonly"/>
							<input id="carid"  name="car_Id" style="display: none" />
						</td>
						<td>车辆牌照：</td>
						<td><input name="car_License" readonly="readonly"/></td>
						
						<td>提醒日期：</td>
						<td><input id="txtDate" class="Wdate" type="text" name="tx_Date" style="width:110px;" onfocus="WdatePicker()" /></td>
						<td>联系人：</td>
						<td><input name=""/></td>
						<td>电话一：</td>
						<td><input name="custom_Tel1" readonly="readonly"/></td>
						
						<td>电话二：</td>
						<td><input name="custom_Tel2" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>客户名称：</td>
						<td><input name="custom_Name" readonly="readonly"/></td>
						
						
						<td>最后维修日期：</td>
						<td><input name ="car_Last_Repair_Date" readonly="readonly"/></td>
						<td>下次跟踪日期：</td>
						<td><input id="tx_Return_Visit_Date" class="Wdate" type="text" name="tx_Return_Visit_Date" style="width:110px;" onfocus="WdatePicker()" /></td>
						<td>跟踪结果：</td>
						<td><input name="tx_Resault"/></td>
						<td>提醒内容：</td>
						<td colspan="3" rowspan="1"><textarea name="visit_Content" style="width : 280px;height : 30px; resize : none;"></textarea></td>
						
						<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="doSaveSubmit($('#datagrid_carlost_return_id'),$('#form_carlost_return_id'),'customerCareAction!doSave');">保存</a></td>
					</tr>
				</table>
			</form>
			
		</div>
		<div region="center" title="历史回访记录" style="background:#eee;" border="false">
			<table id="datagrid_carlost_return_id"></table>
		</div>
		<div region="south" title="历史维修记录" style="height:300px;background:#eee;"  border="false">
			<table id="datagrid_carlost_repair_id"></table>
		</div>
	</div>
	</body>
</html>
