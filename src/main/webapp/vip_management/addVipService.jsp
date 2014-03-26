<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
   <form id="form_vip_card_service_project_id">
		<table>
		    <tr>
				<td>会员编号:</td>
				<td width="200">
					<input name="vip_Id" style="width:175px" class="easyui-combobox" data-options="url:'vipServiceAction!getVipId.action',mode:'remote',required:true,missingMessage:'会员卡号为必填项',valueField:'id',textField:'name',multiple:false"/>
				</td>
			</tr>
			<tr>
			    <td>套餐名称:</td>
				<td>
					<input name ="meal_Id" style="width:175px" class="easyui-combobox" data-options="url:'vipServiceAction!getMealName.action',mode:'remote',required:true,missingMessage:'套餐名称为必填项',valueField:'id',textField:'name',multiple:false"/>
				</td>
			</tr>
			<input name= "meal_RId" type="hidden"/>
		</table>
	</form>
</body>