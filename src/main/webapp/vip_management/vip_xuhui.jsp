<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员续会</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vip_xuhui.js"></script>
  </head>
  <body>
   	<%-- 会员续会 --%>
   	<div class="easyui-layout" style="width:550px;height:410px;border:0px;background:#eee;" fit="true">
   		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:110px;" border="false">
   			<privilege:enable code="RENEWALEDIT">
   				<a id="editBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="showdialog(2);">修改</a>
   			</privilege:enable>
   			<privilege:enable code="RENEWALQUERY">
   				<a id="searchBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doSubmitByCondition();">查询</a>
   			</privilege:enable>
   			<privilege:enable code="RENEWALCLEAR">
   				<a id="clearBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
			</privilege:enable>
			<privilege:enable code="RENEWALEXPORT">
				<a id="exportBut" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exception();">导出</a>
   			</privilege:enable>
   			<br/>
   			<form id="form_vip_xuhui_id">
   				<table style="text-align:right">
   					<tr>
   						<td>续会日期:</td>
   						<td><input id="adjournment_Date" name="adjournment_Date" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'adjournment_Date2\',{d:-1})}'})" style="width:120px;"/>至</td>
						<td><input id="adjournment_Date2" name="adjournment_Date2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'adjournment_Date\',{d:1})}'})" style="width:120px;"/></td>
   						<td>车辆牌照:</td>
   						<td><input name="car_License" style="width:120px;"/></td>
   						<td>VIN号:</td>
   						<td><input name="car_Vin" style="width:120px;"/></td>
	   					<td>会龄:</td>
					    <td><input name="vip_Age" style="width:110px;"/>月</td>	
   					</tr>
   					<tr>
   						<td>会员到期:</td>
   						<td><input id="end_Time" name="end_Time" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'end_Time2\',{d:-1})}'})" style="width:120px;"/>至</td>
						<td><input id="end_Time2" name="end_Time2" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'end_Time\',{d:1})}'})" style="width:120px;"/></td>
						<td>会员姓名:</td>
   						<td><input name="vip_Name" style="width:120px;"/></td>
   						<td>联系电话:</td>
   						<td><input name="vip_Tel" style="width:120px;"/></td>
						<td width="60px">会员卡号:</td>
						<td><input name="vip_Number" style="width:120px;"/></td>
					</tr>
   					<tr>
						<td>会员等级:</td>
						<td colspan="2"><input name="vip_Level_Id" class="easyui-combobox" style="width:260px;"
							data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipLevel.action',
							valueField:'id',  
							textField:'name',
							mode:'remote',
							multiple:false  "
							/>
						</td>  
						<td>会员分组:</td>
						<td><input name="vip_Group_Id" class="easyui-combobox" style="width:120px;"
							data-options="url : '${pageContext.request.contextPath}/VipRecordMessageAction!getBasVipGroup.action',
							valueField:'id',  
							textField:'name',
							mode:'remote',
							multiple:false  "
							/>
						</td>  
						<td>会员卡状态:</td>
						<td><input type="text" name="vip_Status" class="easyui-combobox" style="width:120px;"
						    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HYKZT.HYKZT %>',   
				    		valueField:'id',   
				    		textField:'text',
				    		mode : 'remote' " />
						</td>
   					</tr>
   				</table>
   			</form>
   		</div>
  		<div id="datagrid_vip_xuhui_idDiv" region ="center" style="background: #eee;border:false">
  			<table id="datagrid_vip_xuhui_id"></table>
		</div>
	</div>
	<!-- 续会明细弹出窗口 -->
	<div id="vip_mananement_vip_xuhui_dialog" style="width:650px;background:#eee" class="easyui-dialog" data-options="title:'会员续会',closed:true,modal : true,  buttons:[
		{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				adjournmentSubmit()
			}
		},{
			text:'取消',
			iconCls:'icon-undo',
			handler:function(){
				$('#vip_mananement_vip_xuhui_dialog').dialog('close');
			}
		}]"> 
		<form id="form_Vip_management_member_part">
			<fieldset>
				<legend style="font-weight:bold">会员信息</legend>
				<table  style="text-align:right">
					<tr>
						<td style="width : 60px">会员卡号:</td>
						<td><input name="vip_Number" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td >会员等级:</td>
						<td><input name="vip_Level_Name" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>	
						<td >会员卡状态:</td>
						<td><input name="vip_Status_value" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>	
					</tr>
					<tr>
						<td>入会日期:</td>
						<td><input id="txtjoin_Time" name="join_Time" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>会员到期:</td>
						<td><input id="txtend_Time" name="end_Time" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>卡内余额:</td>
						<td><input name="vip_Balance" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
					</tr>
					<tr>	
						<td>车辆牌照:</td>
						<td><input name="car_License" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>VIN号:</td>
						<td><input name="car_Vin" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>会员名称:</td>
						<td><input name="vip_Name" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
					</tr>
					<tr>
						<td>联系电话:</td>
						<td><input name="vip_Tel" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>可用积分:</td>
						<td><input name="vip_Integral" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
						<td>累计积分:</td>
						<td><input name="vip_Total_Integral" readonly="readonly" style="background: #c0d8d8;width:120px;"/></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend style="font-weight:bold">续会信息</legend>
				<table >
					<tr>
						<td>会员到期:</td>
						<td><input name="end_Time2" class="Wdate" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'txtend_Time\',{d:1})}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" data-options="required:true" style="width:214px;"/></td>
						<td>赠送积分:</td>
						<td><input id="adjournment_Integral" name="adjournment_Integral" style="width:214px;"/></td>
					</tr>
					<tr>
						<td>续会理由:</td>
						<td colspan="3">
							<textarea rows="2" cols="69" name="adjournment_Memo"></textarea>
						</td>
					</tr>
				</table>
			</fieldset>
	    </form>
	</div>
  </body>
</html>