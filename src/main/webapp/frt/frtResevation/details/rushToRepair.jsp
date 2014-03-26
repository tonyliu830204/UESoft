<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtResevation/details/rushToRepair.js"></script>
<!-- 抢修信息 -->
<form id="rushToRepairForm" >
	<table align="left">
		<tr>
			<td>服务项目:</td>
			<td><input type="text" name="rtrServices" 
				class="easyui-combobox"  style="width: 140px;"
				data-options="
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.SERVICEITEM_TAG.SERVICEITEMKEY %>',
						missingMessage : '服务项目为必填项',
						editable : false,
						valueField : 'id',
						textField : 'text' ">
			</td>
			<td>回访满意度:</td>
			<td><input type="text" name="rtrSatisfaction"
				class="easyui-combobox"  style="width: 140px;"
				data-options="
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.TALKBACKCONTENT_TAG.TALKBACKCONTENTKEY %>',
						missingMessage : '回访满意度为必填项',
						editable : false,
						valueField : 'id',
						textField : 'text' ">
			</td>
			<td></td>
		</tr>

		<tr>
			<td>报案时间:</td>
			<td><input type="text" name="rtrReportTime"
				class="easyui-datetimebox" style="width: 140px;"
				data-options="
						editable : false,
						missingMessage: '报案时间为必填' " />
			</td>
			<td>客户意见及建议:</td>
			<td rowspan="2" colspan="2"><textarea id="rtrIdea" rows="10"
					cols="10" name="rtrIdea" style="width: 260px; height: 60px;"></textarea>
			</td>
		</tr>

		<tr>
			<td>回复时间:</td>
			<td><input type="text" name="rtrReplyTime"
				class="easyui-datetimebox" style="width: 140px;"
				data-options="
							editable : false,
							missingMessage: '回复时间为必填' " />
			</td>
		</tr>

		<tr>
			<td>是否来厂维修:</td>
			<td><input type="text" name="rtrIsCome" class="easyui-combobox"  style="width: 140px;"
				data-options="
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.WHETHERSERVICECOME_TAG.WHETHERSERVICECOMEKEY %>',
						missingMessage : '是否来厂维修为必填项',
						editable : false,
						valueField : 'id',
						textField : 'text' ">
			</td>
			<td>备注:</td>
			<td rowspan="2" colspan="2"><textarea id="rtrRemarke"
					name="rtrRemarke" rows="10" cols="10"
					style="width: 260px; height: 60px;"></textarea>
			</td>
		</tr>

		<tr>
			<td>原因:</td>
			<td><input type="text" name="rtrReason"
				class="easyui-validatebox" style="width: 140px;"
				data-options="
						missingMessage: '原因为必填' " /></td>
		</tr>

		<tr>
			<td>进场维修时间:</td>
			<td><input type="text" name="rtrInTime"
				class="easyui-datetimebox" style="width: 140px;"
				data-options="
							editable : false,
							missingMessage: '进场维修时间为必填' " />
			</td>
			<td>回访员:</td>
			<td><input type="text" id="rtrCsr" name="rtrCsr"  style="width: 140px;"
				class="easyui-combobox" data-options="
				url : '${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseVisitingPerson.action',
				missingMessage : '回访员为必填项', 
				editable : false,
				valueField:'id',  
				textField:'text' " />
			</td>
		</tr>

		<tr>
			<td>出厂时间:</td>
			<td><input type="text" name="rtrOutTime"
				class="easyui-datetimebox" style="width: 140px;"
				data-options="
							editable : false,
							missingMessage: '出厂时间为必填项' " />
			</td>
			<td>回访时间:</td>
			<td><input type="text" name="rtrReturnVisitTime"
				class="easyui-datetimebox" style="width: 140px;"
				data-options="
							editable : false,
							missingMessage: '回访时间为必填项' " />
			</td>
		</tr>
	</table>
	
</form>