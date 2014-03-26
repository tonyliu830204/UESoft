<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<!-- 维修建议 -->
<script type="text/javascript">
   var PROCESSTATEYES = '<%=Contstants.PROCESSTATE_TAG.PROCESSTATEYES%>';
   var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details/rushToRepair.js"></script>
<table id="frtReceptionAdviceDatagrid"></table>

<div id="ee" title="增加维修建议" data-options="iconCls:'icon-save',modal:true"
	style="padding: 5px; width: 820px; height: 430px;">
	<!-- 维修建议 -->
	<form id="frtResvAdviceForm">
		<table align="left" width="800px;">
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
					
						<input type="text" name="procesState" id="sprocesState" class="easyui-combobox" style="width: 140px;"
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
<div id="ff" title="修改维修建议" data-options="iconCls:'icon-edit',modal:true"
	style="padding: 5px; width: 820px; height: 430px;">
	<!-- 维修建议 -->
	<form id="ufrtResvAdviceForm">
		<table align="left" width="800px;">
			<tr>
				<td>
					车辆牌照:
				</td>
				<td colspan="2">
				<input type="hidden" name="adviceId" id="uadviceId"/>
					<input type="hidden" name="carId" id="ucarId" />
					<input type="text" id="ucarLicense" name="carLicense" readonly="readonly"
						class="easyui-validatebox" style="width: 260px;" />
				</td>
				<td colspan="2">
					发布人员:
				</td>
				<td>
						<input type="text" name="writePerson"  id="uwritePerson"  class="easyui-combobox"
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
					<input type="text" name="adviceTime" id="uadviceTime"
						class="easyui-validatebox" style="width: 260px;"
						readonly="readonly"
						data-options="
						disabled : true,
						editable : false " />
				</td>

				<td colspan="2">
					维修分类:
				</td>
				<td>
					<input type="text" name="adviceClass" id="uadviceClass"
						style="width: 140px;" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
						valueField:'id', 
						disabled : true, 
						textField:'text' "/>
				</td>

			</tr>

			<tr>
				<td>
					维修建议:
				</td>
				<td colspan="5">
					<textarea id="uadviceContext" name="adviceContext" rows="20" readonly="readonly"
						cols="50" style="width: 700px; height: 160px;"></textarea>
				</td>
			</tr>

			<tr>
				<td>
					处理进度:
				</td>
				<td>
					
						<input type="text" name="procesState" id="uprocesState" class="easyui-combobox" style="width: 140px;"
						data-options="
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
					<input type="text" name="adviceTimeEnd" id="uadviceTimeEnd"
						readonly="readonly" class="easyui-datetimebox"
						 value="{now}"
						 data-options="
						 disabled : true,
						editable : false"
						style="width: 140px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					经办人:
				</td>
				<td>
					<input type="text" name="procesProson" id="uprocesProson"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						readonly="readonly" class="easyui-combobox"
						style="width: 140px;"
						data-options="
						disabled:true,
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
					<textarea id="uprocesContext" name="procesContext" rows="20"
						cols="50" style="width: 700px; height: 80px;"></textarea>
				</td>
		</table>

	</form>
</div>
<div id="dd" title="维修建议详情" data-options="iconCls:'icon-search',modal:true"
	style="padding: 5px; width: 820px; height: 430px;">
	<!-- 维修建议 -->
	<form id="sfrtResvAdviceForm">
		<table align="left" width="800px;">
			<tr>
				<td>
					车辆牌照:
				</td>
				<td colspan="2">
				<input type="hidden" name="adviceId" id="ssadviceId"/>
					<input type="hidden" name="carId" id="sscarId" />
					<input type="text" id="sscarLicense" name="carLicense" readonly="readonly"
						class="easyui-validatebox" style="width: 260px;" />
				</td>
				<td colspan="2">
					发布人员:
				</td>
				<td>
						<input type="text" name="writePerson"  id="sswritePerson"  class="easyui-combobox"
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
					<input type="text" name="adviceTime" id="ssadviceTime"
						class="easyui-validatebox" style="width: 260px;"
						readonly="readonly"
						data-options="
						disabled : true,
						editable : false " />
				</td>

				<td colspan="2">
					建议提醒:
				</td>
				<td>
					<input type="text" name="adviceClass" id="ssadviceClass"
						style="width: 140px;" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
						valueField:'id', 
						disabled : true, 
						textField:'text' "/>
				</td>

			</tr>

			<tr>
				<td>
					维修建议:
				</td>
				<td colspan="5">
					<textarea id="ssadviceContext" name="adviceContext" rows="20" readonly="readonly"
						cols="50" style="width: 700px; height: 160px;"></textarea>
				</td>
			</tr>

			<tr>
				<td>
					处理进度:
				</td>
				<td>
					
						<input type="text" name="procesState" id="ssprocesState" class="easyui-combobox" style="width: 140px;"
						data-options="
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
						valueField : 'id',
						textField : 'text',
						editable : false,
						disabled : true,
						missingMessage : '处理进度为必填项',
						mode : 'remote'"/></td>

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td>
					处理日期:
				</td>
				<td>
					<input type="text" name="adviceTimeEnd" id="ssadviceTimeEnd"
						readonly="readonly"
						  data-options="
						editable : false,
						disabled : true"
						style="width: 140px;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					经办人:
				</td>
				<td>
					<input type="text" name="procesProson" id="ssprocesProson"
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
					<textarea id="ssprocesContext" name="procesContext" rows="20" readonly="readonly"
						cols="50" style="width: 700px; height: 80px;"></textarea>
				</td>
		</table>
	</form>
</div>