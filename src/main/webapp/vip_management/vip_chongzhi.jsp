<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员卡储值</title>
    <script type="text/javascript">
    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_chongzhi.js"></script>
  </head>
  <body>
   <%--  会员充值 --%>
   	<div class="easyui-layout" style="width:550px;height:410px;border:0px;background:#eee;" fit="true">
   		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:85px;" border="false">
   			<privilege:enable code="RECHARGEADD">
   				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showdialog();">新增</a>
   			</privilege:enable>
   			<privilege:enable code="RECHARGEDELETE">
   				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doDelete()">删除</a>
   			</privilege:enable>
   			<privilege:enable code="RECHARGEMODIFY">
   				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doUpdate()">修改</a>
   			</privilege:enable>
   			<privilege:enable code="RECHARGEQUERY">
   				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit()">查询</a>
   			</privilege:enable>
   			<privilege:enable code="RECHARGECLEAR">
   				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doEmpty()">清空</a>
   			</privilege:enable>
   			<privilege:enable code="RECHARGEEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
			</privilege:enable>
			<privilege:enable code="RECHARGEEXAMIN">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="audit()">审核</a>
   			</privilege:enable>
   			<br/>
   			<form id="form_query_vip_chongzhi_id">
   				<table style="text-align:right">
   					<tr>
   						<td>储值日期:</td>
						<td ><input type="text" id="vip_Rec_Date" name="vip_Rec_Date" class="Wdate" style="width:150px" onclick="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'vip_Rec_Date2\')}'});"/> 至 </td>
						<td><input type="text" id="vip_Rec_Date2" name="vip_Rec_Date2" class="Wdate" style="width:150px" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'vip_Rec_Date\')}'});"/></td>
   						<td>车辆牌照:</td>
   						<td><input type="text" name="car_License" style="width:120px"/></td>
   						<td>VIN号:</td>
   						<td><input type="text" name="car_Vin" style="width:120px"/></td>
   						<td>会员卡状态:</td>
						<td>
							<input type="text" name="vip_Status" class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',   
				    		valueField:'id',   
				    		textField:'text',
				    		mode : 'remote' " style="width:150px" />
						<td style="width:70px">审核情况:</td>
						<td>
							<input style="width:150px" name="rec_Audit_Status"
							class="easyui-combobox"
							data-options="
							url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.AUDIT_TAG.AUDITKEY %>',
							multiple:false,
							mode:'remote',
							valueField:'id',   
			    		    textField:'text'  "
							/>
						</td>	
		   			</tr>
		   			<tr>
		   			    <td>会员到期:</td>
						<td><input type="text" id="end_Time_createDateBegin" name="end_Time" class="Wdate" style="width:150px" onclick="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'end_Time_createDateEnd\')}'});"/> 至 </td>
						<td><input type="text" id="end_Time_createDateEnd" name="end_Time2" class="Wdate" style="width:150px" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'end_Time_createDateBegin\')}'});"/></td>
		   				<td>会员姓名:</td>
   						<td><input type="text" name="vip_Name" style="width:120px"/></td>
   						<td>联系电话:</td>
   						<td><input type="text" name="vip_Tel" style="width:120px"/></td>
						<td>会员卡号:</td>
						<td><input type="text" name="vip_Number" style="width:150px"/></td>
						<td>会员等级:</td>
						<td><input style="width:150px" name="vip_Level_Id"
							class="easyui-combobox"
							data-options="
							url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
							valueField:'id',  
							textField:'name',
							mode:'remote',
							multiple:false  "
							/>
						</td>  
   					</tr>
   				</table>
   			</form>
   		</div>
  		<div id="datagrid_vip_chongzhi_idDiv" region ="center" style="background: #eee;border:false">
  			<table id="datagrid_vip_chongzhi_id"></table>
		</div>
	</div>
  </body>
</html>