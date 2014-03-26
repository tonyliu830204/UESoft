<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<script type="text/javascript">
	/*	var sgsm_d2;
	function addCarModel()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '车档案选择',   
	    width: 450,   
	    height: 300,
	    cache: false,   
	    href: 'sell/sellServicing/carInfoPop.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}*/
</script>
<form id="servicingEditForm" style="margin-top: 20px;">
	<input type="hidden" id="xsCarModelId" name="xsCarModelId"/>
	<input type="hidden" id="detailsId" name="detailsId"/>
	<input type="hidden" id="detailsId" name="xsCarId"/>
	<input type="hidden" id="servicingId" name="servicingId"/>
	<input type="hidden" id="servicingPerson" name="servicingPerson" value="<%=user.getBasStuff().getStfId() %>"/>
	<table align="center">
		<tr>
			<td width="70px">底盘号:</td>
			<td><input type="text" name="xsCarVinNumber" id="xsCarVinNumber"  readonly="readonly" style="background-color: rgb(192, 216, 216); width: 162px" class="easyui-validatebox">
			</td>
			<td width="70px">维护日期:</td>
			<td><input type="text" name="servicingDate" id="servicingDate"  style="width: 130px"  readonly="readonly" /></td>
		<tr>
			<td width="70px">车辆型号:</td>
			<td colspan="3"><input type="text" name="xsModelName"  id="xsModelName"   readonly="readonly" style="background-color: rgb(192, 216, 216); width: 372px" class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td width="70px">维护项目:</td>
			<td colspan="3"><input type="text"  name="servicingProject" id="servicingProjectId" style="width:372px;"  class="easyui-combobox"  data-options="
						required:true,
						url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SERVICINGPRO%>',
						valueField:'id',   
				   		textField:'text',
				   		mode:'remote',
				   		validType:'isSelected[\'#servicingProjectId\']',
				   		invalidMessage : '请从下拉框中选择维护项目',
				   		editable:false
				   "/></td>
		</tr>
		<tr>
			<td width="70px">维护结果:</td>
			
			<td colspan="3"><textarea  name="servicingRemark" id="servicingRemark" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'" style="width: 372px;height: 40px;resize:none;"></textarea></td>
		</tr>
		<tr>
			<td width="70px">下次预计:</td>
			<td><input type="text" name="servicingNextdate" id="nextDate"  style="width: 162px"  readonly="readonly"  /></td>
		</tr>
	</table>
</form>