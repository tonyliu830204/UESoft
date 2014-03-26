<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 会员充值弹出窗口 -->
<div id="vip_mananement_vip_chongzhi_dialog" style="width:600px;background:#eee"> 
	<form id="form_Vip_management_member_part">
	    <fieldset>
		    <legend style="font-weight:bold">会员卡信息</legend>
			<table  style="text-align:right">
				<tr>
					<td style="width : 60px">会员卡号:</td>
					<td><input id="vip_Number" name="vip_Number" style="width:115px" data-options="required:true,missingMessage:'会员卡号必填'" class="easyui-validatebox" onchange="getVipInfoByVipNO($('#form_Vip_management_member_part'),'${pageContext.request.contextPath}/VipRechargeAction!readCard.action')"/>
					</td>
					<td >会员等级:</td>
					<td><input name="vip_Level_Name" readonly="readonly" style="background: #c0d8d8;"/></td>	
					<td >会员卡状态:</td>
					<td><input name="vip_Status_value" readonly="readonly" style="background: #c0d8d8;width:125px"/></td>	
				</tr>
				<tr>
				<td>会员到期:</td>
					<td><input  name="end_Time" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>卡内余额:</td>
					<td><input id="vip_Balance" name="vip_Balance" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>累计积分:</td>
					<td><input id="vip_Total_Integral" name="vip_Total_Integral" readonly="readonly" style="background: #c0d8d8;width:125px"/></td>
				</tr>
				<tr>	
					<td>车辆牌照:</td>
					<td><input name="car_License" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>VIN号:</td>
					<td colspan=3"><input name="car_Vin" readonly="readonly" style="background: #c0d8d8;width:310px"/></td>
					
				</tr>
				<tr>
					<td>客户名称:</td>
					<td><input name="vip_Name" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>会员生日:</td>
					<td colspan=3"><input name="vip_Birthday" readonly="readonly" style="background: #c0d8d8;width:310px"/></td>
				</tr>
				<tr>
					<td>联系电话:</td>
					<td><input name="vip_Tel" readonly="readonly" style="background: #c0d8d8;"/></td>
					<td>入会日期:</td>
					<td colspan=3"><input  name="join_Time" readonly="readonly" style="background: #c0d8d8;width:310px"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
		    <legend style="font-weight:bold">会员卡储值</legend>
			<table >
				<tr>
					<td>&nbsp;&nbsp;&nbsp;储值金额:</td>
					<td>￥<input id="rec_Amount" name="rec_Amount" style="width:115px" value="0" data-options="required:true,missingMessage:'储值金额必填'" class="easyui-validatebox" validType="monery" invalidMessage="储值金额格式不正确,请输入正确金额" maxlength="12"/></td>
					<td>付款方式:</td>
					<td>
						<input style="width:115px" id="vip_chongzhi_paytype" name="rec_PayType"
							class="easyui-combobox"
							data-options="
							url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
							valueField:'id',  
							textField:'text',
							mode:'remote',
							validType:'isSelected[\'#vip_chongzhi_paytype\']',
							required:true,
   							invalidMessage : '请从下拉框中选择付款方式',
							multiple:false  "
						/>
					</td>
					<td>赠送金额:</td>
					<td>￥<input id="give_Amount" name="give_Amount" value="0" style="width:115px" class="easyui-validatebox" validType="monery" invalidMessage="赠送金额格式不正确,请输入正确金额"/></td>
				</tr>
				<tr>
					<td>赠送积分:</td>
					<td><input id="give_Inte" name="give_Inte" value="0" style="width:115px" class="easyui-validatebox" validType="intOrFloat" invalidMessage="赠送积分格式不正确,请输入正确金额"/></td>
					<td>储值备注:</td>
					<td colspan="3">
						<input  name="vip_Rec_Note"  style="width:100%" />
					</td>
					<input id="vip_Rec_Id" name="vip_Rec_Id" type="hidden">
				</tr>
			</table>
		</fieldset>
	</form>
</div>