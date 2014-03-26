<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>积分赠送管理</title>
    <script type="text/javascript">
    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_jifenzengsong.js"></script>
	</head>
  <body>
	<%-- 积分赠送 --%>
	<div class="easyui-layout" style="width:50%px;height:40%px;border:0px;background:#eee;" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
			<privilege:enable code="INTEGRALGIFTADD">
				<a id="addBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAdd();">新增</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTDELETE">
				<a id="removeBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doRemove();">删除</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTMODIFY">
				<a id="editBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEdit();">修改</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTQUERY">
				<a id="searchBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSubmitByCondition($('#Give_integral_project_management_id'),$('#form_Give_inte_project_list'),'vipScorePresentManagementAction!getHzGiveIntegral');">查询</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTCLEAR">
				<a id="clearBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTEXPORT">
				<a id="exportBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
			</privilege:enable>
			<privilege:enable code="INTEGRALGIFTEXAMIN">
				<a id="examineBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doShenhe();">审核</a>
			</privilege:enable>
			<span id="addbutton"></span>
			<span id="editbutton"></span>
		</div>
		<div region="center"  style="background:#eee;">
		    <div id="tid" class="easyui-tabs" fit="true"  border="false">
			    <div title="积分赠送汇总" fit="true"  border="false">
					<div class="easyui-layout" style="border:0px;background:#eee;" fit="true" >
						<div region="north"   style="height:110px;background:#eee;" border="false">
							<form id="form_Give_inte_project_list">
							      <fieldset style="height:90px">
								     <legend style="font-weight:bold">查询条件</legend>
								     <table style="text-align:right">
			   					         <tr>
					   						<td style="width:70px">赠送日期:</td>
					   						<td><input name="give_Inte_Date" id="give_Inte_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'give_Inte_Date2\',{d:-1})}'})" style="width:120px"/> 至 </td>
											<td><input name="give_Inte_Date2" id="give_Inte_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'give_Inte_Date\',{d:1})}'})" style="width:120px"/></td>
					   						<td style="width:70px">车辆牌照:</td>
					   						<td><input name="car_License" type="text" style="width:120px"/></td>
					   						<td style="width:70px">VIN号:</td>
					   						<td><input name="car_Vin" type="text" style="width:120px"/></td>
						   					<td style="width:70px">会龄：</td>
											<td><input name="vip_Age" type="text" style="width:110px"/>月</td>	
											<td style="width:70px">审核情况:</td>
											<td>
												<input style="width:120px" name="shenhe_Qingkuang"
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
					   						<td style="width:70px">会员到期:</td>
					   						<td><input name="end_Time" id="end_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" style="width:120px"/> 至 </td>
											<td><input name="end_Time2" id="end_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" style="width:120px"/></td>
											<td style="width:70px">会员姓名:</td>
					   						<td><input name="vip_Name" type="text" style="width:120px"/></td>
					   						<td style="width:70px">联系电话:</td>
					   						<td><input name="custom_Tel1" type="text" style="width:120px"/></td>
											<td style="width:70px">会员卡号:</td>
											<td><input name="vip_Number" type="text" style="width:120px"/></td>
											<td style="width:70px">会员卡状态:</td>
											<td>
												<input type="text" name="vip_Status" class="easyui-combobox" data-options="
												url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>', 
												mode:'remote',  
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote' " style="width: 120px;" />
											</td>
					   					</tr>
					   					<tr>
											<td style="width:70px">会员等级:</td>
											<td colspan="2"><input name="vip_Level_Id"
												class="easyui-combobox"
												data-options="
												url : 'VipRecordMessageAction!getBasVipLevel.action',
												mode:'remote',
												valueField:'id',  
												textField:'name',
												multiple:false  "
												style="width:260px"
												/>
											</td>  
											<td style="width:70px">会员分组:</td>
											<td><input name="vip_Group_Id"
												class="easyui-combobox"
												data-options="
												url : 'VipRecordMessageAction!getBasVipGroup.action',
												mode:'remote',
												valueField:'id',  
												textField:'name',
												multiple:false  "
												style="width:120px"
												/>
											</td>
					   					</tr>
			   				        </table>
							    </fieldset>
						    </form>
						</div>
						<div id="Give_integral_project_management_idDiv" region="center" style="background:#eee;" border="false">
							<table id="Give_integral_project_management_id"></table>
						</div>
					</div>
				</div>
				<div title="积分赠送明细" fit="true"  border="false">
					<div class="easyui-layout" style="width:800px;height:600px;border:0px;background:#eee;" fit="true" border="false">
						<div region="north"   style="height:120px;" border="false">
							<form id="form_Give_inte_project_list_detail">
								<fieldset>
								     <legend style="font-weight:bold">会员信息</legend>
								     <input id="vipId" name="vip_Id" type="hidden"/>
								     <input id="giveInteId" name="give_Inte_Id" type="hidden"/>
								     <input id="projectItem" name="projectItem" type="hidden"/>
								     <input id="sum" name="sum" type="hidden"/>
								     <table style="text-align:right">
										<tr>
											<td>赠送日期:</td>
											<td><input class="Wdate" type="text" onfocus="WdatePicker()" name="give_Inte_Date" id="give_Inte_Date_id" style="width:120px;background:#c0d8d8" value=""/></td>
											<td>会员卡号:</td>
											<td><input id="vip_Number" name="vip_Number" style="width:120px" data-options="required:true,missingMessage:'会员卡号必填'" class="easyui-validatebox" onchange="getVipInfoByVipNO()"/>
											<td>会员等级:</td>
											<td><input name="vip_Level_Name" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
											<td>会员分组:</td>
											<td><input name="vip_Group_Name" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
											<td >会员卡状态:</td>
											<td><input name="vip_Status_value" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
										</tr>
										<tr>
										    <td>会员名称:</td>
											<td><input name="vip_Name" style="width:120px;background: #c0d8d8" readonly="readonly" /></td>
											<td>会员到期:</td>
											<td><input name="end_Time" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
											<td>卡内余额:</td>
											<td><input name="vip_Balance" style="width:120px;background:#c0d8d8" readonly="readonly"/></td>
											<td>出生年月:</td>
											<td><input name="vip_Birthday" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
											<td>联系电话:</td>
											<td><input name="vip_Tel" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td>总积分:</td>
											<td><input id="vip_Total_Integral" name="vip_Total_Integral" style="background:#c0d8d8;width:120px;" readonly="readonly" /></td>
											<td>可用积分:</td>
											<td><input name="vip_Integral" style="background:#c0d8d8;width:120px;" readonly="readonly"/></td>
											<td >车辆牌照:</td>
											<td><input name="car_License" style="background:#c0d8d8;width:120px;" readonly="readonly" /></td>
											<td >VIN号:</td>
											<td><input name="car_Vin" style="background:#c0d8d8;width:120px;" readonly="readonly" /></td>
											<td >备注:</td>
											<td><input name="give_Inte_Note" style="background:#c0d8d8;width:120px;" /></td>
										</tr>
									</table>
							    </fieldset>
						    </form>
						</div>
						<div region="center"  style="background:#eee;" fit="true" border="false">
							<fieldset style="height:400px">
								<legend style="font-weight:bold">待选积分赠送项目</legend>
								<table id="Give_integral_project_list"></table>
							</fieldset>	
						</div>
						<div region="south"  style="background:#eee; height:300px;"  border="false">
							<fieldset style="height:260px">
								<legend style="font-weight:bold">已选积分赠送项目</legend>
								<table id="Give_integral_project_list2"></table>
							</fieldset>	
						</div>
					</div>
				</div>
		    </div>
        </div>
     </div>
  </body>
</html>