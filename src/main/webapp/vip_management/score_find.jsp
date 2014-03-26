<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>积分综合查询</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/score_find.js"></script>	
	</head>
	<body>
	<div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
	    <div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:135px;" border="false">
			<privilege:enable code="SCOREFINDQUERY">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
			</privilege:enable>
			<privilege:enable code="SCOREFINDCLEAR">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearForm();">清空</a>
			</privilege:enable>
			<privilege:enable code="SCOREFINDEXPORT">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
			</privilege:enable>
			<form id="form_north_condition_vip_mananement_id">
				<fieldset style="height:90px">
					<legend style="font-weight:bold">查询条件</legend>
					<table style="text-align:right">
						<tr>
							<td>车辆牌照:</td>
							<td><input type="text" name="car_License" style="width:130px;"/></td>
							<td>VIN号:</td>
							<td><input name="car_Vin" style="width:130px;"/></td>
							<td>会员姓名:</td>	
							<td><input name="vip_Name" style="width:130px;"/></td>
							<td>联系电话:</td>
							<td><input name="vip_Tel" style="width:130px;"/></td>
						</tr>
						<tr>
							<td>会员卡号:</td>
							<td><input name="vip_Number" style="width:130px;"/></td>
							<td>会员等级:</td>
							<td><input name="vip_Level_Id" class="easyui-combobox" style="width:130px;"
								data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
								valueField:'id',  
								textField:'name',
								multiple:false  "
								/>
							</td>  
							<td>会员分组:</td>
							<td><input name="vip_Group_Id" class="easyui-combobox" style="width:130px;"
								data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
								valueField:'id',  
								textField:'name',
								multiple:false  "
								/>
							</td>  
							<td>会员卡状态:</td>
							<td><input type="text" name="vip_Status" class="easyui-combobox" style="width:130px;"
							    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',   
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote' " />
							</td>
						</tr> 
						<tr>
							<td>积分数:</td>
							<td><input name="vip_Integral" style="width:56px"/> 至 <input name="vip_Integral1" style="width:56px"/></td>
							<td width="60px">&nbsp;&nbsp;会龄:</td>
							<td><input name="vip_Age" style="width:120px;"/>月</td>
						</tr>
					</table>
			    </fieldset>
		    </form>
		</div>
		<div region="center" style="background:#eee;" border="false">
			 <div id="tb_id" class="easyui-tabs" style="width:500px;height:250px;" fit="true">
			 		<div title="积分汇总" >
						<table id="datagrid_vip_jifenzonghechaxun_id" border="0"></table>
					</div>
					<div title="维修积分" >
						<table id="datagrid_vip_jifenzonghechaxun_id1" border="0"></table>
					</div>
					<div title="销售积分" >
						<table id="datagrid_vip_jifenzonghechaxun_id2" border="0"></table>
					</div>
					<div title="储值赠分" >
						<table id="datagrid_vip_jifenzonghechaxun_id3" border="0"></table>
					</div>
					<div title="赠送积分" >
						<table id="datagrid_vip_jifenzonghechaxun_id4" border="0"></table>
					</div>
					<div title="兑换积分" >
						<table id="datagrid_vip_jifenzonghechaxun_id5" border="0"></table>
					</div>
					<div title="会员卡升级/降级" >
						<table id="datagrid_vip_jifenzonghechaxun_id6" border="0"></table>
					</div>
					<div title="续会赠分" >
						<table id="datagrid_vip_jifenzonghechaxun_id7" border="0"></table>
					</div>
					<div title="退会扣分" >
						<table id="datagrid_vip_jifenzonghechaxun_id8" border="0"></table>
					</div>
			 </div>
		</div>
	</div>
  </body>
</html>