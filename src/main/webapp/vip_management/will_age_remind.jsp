<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会龄提醒</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/FBK_VIP/Vip_manag.js"></script>
  </head>
  <body class="easyui-layout">
	<div data-options="region:'north',title:'查询条件'" style="background:#eee;height:110px;">
		<table>
			<tr>
				<td style="width:60px">入会日期:</td>
				<td><input class="easyui-datebox" editable="false"/>至</td>
				<td><input class="easyui-datebox" editable="false"/></td>
				<td style="width:60px">车牌照:</td>
				<td ><input type="text"/></td>
				<td style="width:60px">会员编号:</td>
				<td><input type="text"/></td>
				<td style="width:60px">会员姓名:</td>	
				<td><input/></td>
				<td style="width:60px">VIN号:</td>
				<td colspan="3"><input style="width:290px"/></td>
				<td rowspan="3"><a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="select();">查询</a></td>
			</tr>
			<tr>
				<td style="width:60px">会员到期:</td>
				<td ><input class="easyui-datebox" editable="false"/>至</td>
				<td><input class="easyui-datebox" editable="false"/></td>
				<td style="width:60px">会员卡号:</td>
				<td><input/></td>
				<td style="width:60px">会员等级:</td>
				<td><select style="width:110px">
					<option>--请选择--</option>
				</select></td>
				<td style="width:60px">会员分组:</td>
				<td><select style="width:110px">
					<option>--请选择--</option>
				</select></td>
				<td style="width:60px">车辆品牌:</td>
				<td><select style="width:110px">
					<option>--请选择--</option>
				</select></td>
				<td style="width:60px">车辆型号:</td>
				<td><select style="width:110px">
					<option>--请选择--</option>
				</select></td>
			</tr> 
			<tr>
				<td style="width:60px">会员生日:</td>
				<td ><input class="easyui-datebox" editable="false"/>至</td>
				<td><input class="easyui-datebox" editable="false"/></td>
				<td style="width:60px">会员状态:</td>
				<td><select style="width:110px">
					<option>--请选择--</option>
					<option>正常</option>
					<option>挂失</option>
					<option>无效</option>
				</select></td>
				<td style="width:60px">联系电话:</td>
				<td><input/></td>
				<td style="width:60px">会龄:</td>
				<td><input/></td>
				<td style="width:60px">积分数:</td>
				<td><input style="width:50px"/>至<input style="width:50px"/></td>
				<td style="width:60px">客户区域:</td>
				<td>
					<select style="width:110px" class="easyui-combobox">
						<option>--请选择--</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
    <div data-options="region:'center',title:'会员列表'" style="background:#eee;height:50px;">
    	<table id="Will_age_remind_id"></table>
    </div>
  </body>
</html>
