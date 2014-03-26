<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>礼品兑换</title>
    <script type="text/javascript">
    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_lipinduihuan.js"></script>
  </head>
  <body>
 	 <%-- 礼品兑换 --%>
	 <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">
		<div region="north"  split="true" style="height:35px;background:#eee;" border="false">
			<privilege:enable code="GIFTEXCHANGEADD">
				<a id="addBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAdd();">新增</a>
			</privilege:enable>
			<privilege:enable code="GIFTEXCHANGEDELETE">
				<a id="removeBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doRemove();">删除</a>
			</privilege:enable>
			<privilege:enable code="GIFTEXCHANGEEDIT">
				<a id="editBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEdit();">修改</a>
			</privilege:enable>
			<privilege:enable code="GIFTEXCHANGEQUERY">
				<a id="searchBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="submitByCondition();">查询</a>
			</privilege:enable>
		    <privilege:enable code="GIFTEXCHANGECLEAR">
				<a id="clearBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
		    <privilege:enable code="GIFTEXCHANGEEXPORT">
				<a id="exportBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
			</privilege:enable>
			<privilege:enable code="GIFTEXCHANGEEXAMIN">
				<a id="examineBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doAudit();">审核</a>
			</privilege:enable>
			<span id="addbutton"></span>
			<span id="editbutton"></span>
		</div>
		<div region="center"  style="background:#eee;">
			<div id="tab_lipingduihuan_id" class="easyui-tabs" fit="true"  border="false">
				<div title="礼品兑换汇总" fit="true"  border="false">
					<div class="easyui-layout" style="width:800px;height:600px;border:0px;background:#eee;" fit="true" >
						<div region="north"   style="height:105px;background:#eee;" border="false">
							<form id="form_vip_lipinduihuan_id">
								<fieldset style="height:70px">
									<legend style="font-weight:bold">查询条件</legend>
									<table style="text-align:right">
					   					<tr>
					   						<td>兑换日期:</td>
											<td><input name="exchange_Date" id="exchange_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'exchange_Date2\',{d:-1})}'})" style="width:120px"/> 至 </td>
											<td><input name="exchange_Date2" id="exchange_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'exchange_Date\',{d:1})}'})" style="width:120px"/></td>
					   						<td>车辆牌照:</td>
					   						<td><input name="car_License" style="width: 120px;"/></td>
					   						<td>VIN号:</td>
					   						<td><input name="car_Vin" style="width: 120px;"/></td>
						   					<td>会龄：</td>
										    <td><input name="vip_Age" style="width: 110px;"/>月</td>	
										    <td >会员卡状态:</td>
											<td>
												<input type="text" name="vip_Status" class="easyui-combobox" data-options="
												url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',
												mode:'remote',   
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote' " style="width: 120px;" />
											</td>
											<td >审核情况:</td>
											<td>
												<input name="audit_Situation"
													class="easyui-combobox"
													data-options="
													url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.AUDIT_TAG.AUDITKEY %>',
													mode:'remote',
													multiple:false,
													valueField:'id',   
									    		    textField:'text'  "
								    		        style="width: 120px;"
												/>
											</td>
								        </tr>
										<tr>
							 				<td>会员到期:</td>
											<td><input name="end_Time" id="end_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" style="width:120px"/> 至 </td>
											<td><input name="end_Time2" id="end_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" style="width:120px"/></td>
											<td>会员姓名:</td>
											<td><input name="vip_Name" style="width: 120px;"/></td>
											<td>联系电话:</td>
											<td><input name="vip_Tel" style="width: 120px;"/></td>
											<td width="60px">会员卡号:</td>
											<td><input name="vip_Number" style="width: 120px;"/></td>
											<td style="width:70px">会员等级:</td>
											<td><input name="vip_Level_Id"
												class="easyui-combobox"
												data-options="
												url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
												valueField:'id',  
												textField:'name',
												mode:'remote',
												multiple:false  "
												style="width: 120px;"
												/>
											</td>  
											<td style="width:70px">会员分组:</td>
											<td><input name="vip_Group_Id"
												class="easyui-combobox"
												data-options="
												url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
												valueField:'id',  
												textField:'name',
												mode:'remote',
												multiple:false  "
												style="width: 120px;"
												/>
											</td>
							   			</tr>
						   				<tr>
											<td>领用人:</td>
											<td colspan="2"><input name="exchange_User" style="width: 260px;"/></td>
						   				</tr>
					   				</table>
								</fieldset>
							</form>
						</div>
						<div id="datagrid_vip_lipinduihuan_idDiv" region="center"  style="background:#eee;" border="false"  >
							<table id="datagrid_vip_lipinduihuan_id"></table>
						</div>
				    </div>
			    </div>
			
				<div title="礼品兑换明细" fit="true"  border="false">
					<div class="easyui-layout" style="width:550px;height:410px;border:0px;background:#eee;" fit="true">
						<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:200px;" border="false">
							<fieldset style="height:115px">
								<legend style="font-weight:bold">会员信息</legend>
								<form id="form_Present_exchange_vipinfor">
									<table style="text-align:right">
									    <input id="exchange_Id" name="exchange_Id" type="hidden"/>
										<input id="vip_Id" name="vip_Id" type="hidden"/>
										<input id="projectItem" name="data" type="hidden"/>
										<tr>
											<td>兑换日期:</td>
											<td><input name="exchange_Date" class="Wdate" type="text" onfocus="WdatePicker()" style="width:120px;background: #c0d8d8"/></td>
											<td>会员卡号:</td>
											<td><input id="vip_Number" name="vip_Number" style="width:120px" data-options="required:true,missingMessage:'会员卡号必填'" class="easyui-validatebox" onchange="getVipInfoByVipNO()"/>
											<td>会员等级:</td>
											<td><input name="vip_Level_Name" style="width:120px;background:#c0d8d8" readonly="readonly"/></td>
											<td>会员分组:</td>
											<td><input name="vip_Group_Name" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td >会员卡状态:</td>
											<td><input name="vip_Status_value" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>会员到期:</td>
											<td><input name="end_Time" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>领用人:</td>
											<td><input name="exchange_User" style="width:120px;" />
											</td>
										</tr>
										<tr>
											<td>卡内余额:</td>
											<td><input name="vip_Balance" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>可用积分:</td>
											<td><input id="vip_Integral_id" name="vip_Integral" style="width:120px;background:#c0d8d8" readonly="readonly"/></td>
											<td >车辆牌照:</td>
											<td><input name="car_License" style="width:120px;background:#c0d8d8" readonly="readonly"/></td>
											<td >VIN号:</td>
											<td><input name="car_Vin" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>客户名称:</td>
											<td><input name="vip_Name" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>出生年月:</td>
											<td><input name="vip_Birthday" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
											<td>联系电话:</td>
											<td><input name="vip_Tel" style="width:120px;background:#c0d8d8" readonly="readonly" /></td>
										</tr>
										<tr>
											<td>兑换备注:</td>
											<td colspan="5"><input id="give_Inte_Note" name="give_Inte_Note" style="width:500px;"/></td>
										</tr>
									</table>
								</form>
							</fieldset>	
							<fieldset style="height:45px">
								<legend style="font-weight:bold">查询条件</legend>
								<form id="form_Present_exchange_condition_id">
									<table>
										<tr>
											<td>礼品名称:</td>
											<td><input name="project_Name"/></td>
										</tr>
									</table>
								</form>
							</fieldset>
						</div>
						<div data-options="region:'center',border:false" title="可兑换礼品库存" style="background:#eee;">
								<table id="Present_exchange_management_id"></table>
						</div>
						<div data-options="region:'south',border:false" title="已选礼品" style="height:280px;background:#eee;">
								<table id="Present_exchange_project_list"></table>
						</div>
		             </div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>