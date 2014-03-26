<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员升级</title>
    <script type="text/javascript">
    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_shengji.js"></script>
  </head>
  <body>
    <%-- 会员升级 --%>
     <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
			<div region="north"  split="true" style="height:35px;background:#eee;">
				<privilege:enable code="LEVALCHANGEADD">
					<a id="addBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAdd();">新增</a>
				</privilege:enable>
				<privilege:enable code="LEVALCHANGEDELETE">
					<a id="removeBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doRemoveVipUp();">删除</a>
				</privilege:enable>
				<privilege:enable code="LEVALCHANGEEDIT">
					<a id="editBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEdit();">修改</a>
				</privilege:enable>
			    <privilege:enable code="LEVALCHANGEQUERY">
					<a id="searchBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitByCondition();">查询</a>
				</privilege:enable>
				<privilege:enable code="LEVALCHANGECLEAR">
					<a id="clearBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
				</privilege:enable>
				<privilege:enable code="LEVALCHANGEEXPORT">
					<a id="exportBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
				</privilege:enable>
				<privilege:enable code="LEVALCHANGEEXAMIN">
					<a id="examineBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doAudit();">审核</a>
				</privilege:enable>
				<span id="addbutton"></span>
				<span id="editbutton"></span>
			</div>
			<div region="center"  style="background:#eee;">
				<div id="tab_vip_up_id" class="easyui-tabs" fit="true"  border="false">
					<div title="会员卡升级汇总" fit="true"  border="false">
						<div class="easyui-layout" style="width:800px;height:600px;border:0px;background:#eee;" fit="true" >
							<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:60px;" border="false">
								<form id="form_datagrid_vip_shengji_id">
									<fieldset style="height:40px">
										<legend style="font-weight:bold">查询条件</legend>
										<table style="text-align:right">
						   					<tr>
						   						<td>升级日期:</td>
												<td><input id="upgrade_Date" name="upgrade_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'upgrade_Date2\',{d:-1})}'})" style="width: 120px;"/> 至 </td>
												<td><input id="upgrade_Date2" name="upgrade_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'upgrade_Date\',{d:1})}'})" style="width: 120px;"/></td>
												<td>审核情况:</td>
												<td>
													<input name="audit_Situation" class="easyui-combobox" style="width: 120px;"
														data-options="url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.AUDIT_TAG.AUDITKEY %>',
														multiple:false,
														mode:'remote',
														valueField:'id',   
										    		    textField:'text'  "
													/>
												</td>
											</tr>
						   				</table>
								     </fieldset>
								</form>
							</div>
							<div id="datagrid_vip_shengji_idDiv" region="center"  style="background:#eee;" border="false"  >
								<table id="datagrid_vip_shengji_id"></table>
							</div>
						</div>
					</div>
					<div title="会员卡升级明细" fit="true"  border="false">
						<div class="easyui-layout" style="width:550px;height:410px;border:0px;background:#eee;" fit="true">
							<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:115px;" border="false">
								<form id="form_datagrid_vip_shengji_detial_id">
									<fieldset style="height:95px">
										<legend style="font-weight:bold">查询条件</legend>
										<table style="text-align:right">
						   					<tr>
						   					    <td>会员到期:</td>
						   						<td><input name="end_Time" id="end_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" style="width:120px"/> 至 </td>
											    <td><input name="end_Time2" id="end_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" style="width:120px"/></td>
												<td>会员卡号:</td>
												<td><input name="vip_Number" style="width: 140px;"/></td>
						   						<td>车辆牌照:</td>
						   						<td><input name="car_License" style="width: 120px;"/></td>
						   						<td>VIN号:</td>
						   						<td><input name="car_Vin" style="width: 120px;"/></td>
												<td>会龄：</td>
												<td><input name="vip_Age" style="width: 120px;"/>月</td>	
											</tr>
											<tr>
												<td>会员名称:</td>
						   						<td colspan="2"><input name="vip_name" style="width:260px;"/></td>
						   						<td>累计积分:</td>
												<td ><input  name="vip_Total_Integral" style="width:60px;"/> 至 <input name="vip_Total_Integral2" style="width:60px;"/></td>
												<td>会员等级:</td>
												<td><input name="vip_Level_Id" class="easyui-combobox" style="width: 120px;"
													data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
													valueField:'id',  
													textField:'name',
													mode:'remote',
													multiple:false  "
													/>
												</td>  
												<td>会员分组:</td>
												<td><input name="vip_Group_Id" class="easyui-combobox" style="width: 120px;"
													data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
													valueField:'id',  
													textField:'name',
													mode:'remote',
													multiple:false  "
													/>
												</td>  
												<td>会员卡状态:</td>
												<td><input type="text" name="vip_Status" class="easyui-combobox" style="width: 130px;"
												    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',   
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote' " />
												</td>
								   			</tr>
								   			<tr>
								   			    <td>联系电话:</td>
						   						<td colspan="2"><input name="vip_Tel" style="width:260px;"/></td>
								   				<td >可用积分:</td>
												<td ><input name="vip_Integral" style="width: 60px;"/> 至 <input name="vip_Integral2" style="width: 60px;"/></td>
						   				    </tr>
					   				    </table>
									</fieldset>
								</form>
							</div>
							<div region="center" title="待选列表" style="background:#eee; border : 0">
								<table id="huiyuan_shengji_daixuan_liebiao_id"></table>
							</div>
							<div region="south" title="已选列表" style="height:300px;background:#eee;border : 0">
								<table id="huiyuan_shengji_yixuan_liebiao_id"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
  </body>
</html>