<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<!-- 维修建议 -->
<div id="ee" title="增加维修建议">
	<!-- 维修建议 -->
	<form id="frtResvAdviceForm">
		<table align="left" width="800px;" >
			<tr>
				<td>
					车辆牌照:
				</td>
				<td colspan="2">
					<input type="hidden" name="resvId" id="sresvId"/>
					<input type="hidden" name="carId" id="scarId" />
					<input type="text" name="carLicense" id="scarLicense" data-options="disabled:true"
						class="easyui-validatebox" style="width: 260px;" />
				</td>
				<td colspan="2">
					发布人员:
				</td>
				<td>
						<input type="text" name="writePerson"  id="swritePerson"  class="easyui-combobox"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						readonly="readonly"
						style="width: 140px;" 
						data-options="
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						editable : false,
						disabled :true,
						valueField:'id',  
						textField:'text' "/>
				</td>
			</tr>

			<tr>
				<td>
					发布日期:
				</td>
				<td colspan="2">
					<input type="text" name="adviceTime" id="sadviceTime"
						class="easyui-datetimebox" style="width: 260px;"
						readonly="readonly"  value="{now}"
						data-options="
						required : true,
						editable : false,
						missingMessage: '发布日期为必填' " />
				</td>

				<td colspan="2">
					维修分类:
				</td>
				<td>
					<input type="text" name="adviceClass" id="sadviceClass"
						style="width: 140px;" class="easyui-combobox"
						data-options="
						required : true,
						editable : false,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
						valueField:'id',  
						textField:'text' "/>
				</td>

			</tr>

			<tr>
				<td>
					维修建议:
				</td>
				<td colspan="5">
					<textarea id="sadviceContext" name="adviceContext" rows="20"
						cols="50" style="width: 700px; height: 160px;" 
						data-options="required : true"></textarea>
				</td>
			</tr>

			<tr>
				<td>
					处理进度:
				</td>
				<td>
					
						<input type="text" name="procesState" id="sprocesState" class="easyui-combobox" style="width: 195px;"
						data-options="
						required : true,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
						valueField : 'id',
						textField : 'text',
						editable : false,
						missingMessage : '处理进度为必填项',
						mode : 'remote'"/></td>

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td>
					处理日期:
				</td>
				<td>
					<input type="text" name="adviceTimeEnd" id="sadviceTimeEnd"
						readonly="readonly" class="easyui-datetimebox"
						 value="{now}"
						style="width: 140px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					经办人:
				</td>
				<td>
					<input type="text" name="procesProson" id="sprocesProson"
					value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						readonly="readonly" class="easyui-combobox"
						style="width: 140px;"  
						data-options="
					url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						editable : false,
						disabled : true,
						valueField:'id',  
						textField:'text' " />
				</td>
			</tr>
			<tr>
				<td>
					处理结果:
				</td>
				<td colspan="5">
					<textarea id="sprocesContext" name="procesContext" rows="20"
						cols="50" style="width: 700px; height: 80px;"></textarea>
				</td>
		</table>

	</form>
</div>
