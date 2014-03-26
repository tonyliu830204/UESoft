<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtInsurePrize/summary.js"></script>
<!-- 保险估价单汇总 -->
<div class="easyui-layout" fit="true" border="false">
	<div region="north" id="search"
		style="overflow: hidden;padding:3px; background: #eee; height: 90px;"
		border="false">
		<form id="frtInsurePrizeQueryForm">
			<table>
				<tr>
					<td>
					           预约登记:
					</td>
					<td colspan="3">
					    <!--<input type="text" id="resvRealTimeBegin" name="resvRealTimeBegin" class="easyui-datetimebox" style="width: 140px;"
								data-options="		editable : false "/> 
						~<input type="text" id="resvEnterTimeEnd" name="resvRealTimeEnd" class="easyui-datetimebox" style="width: 140px;"
								data-options="	editable : false "/>
					-->
					<input id="resvRealTimeBegin" name="resvRealTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'resvRealTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="resvRealTimeEnd" name="resvRealTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'resvRealTimeBegin\',{d:0})}'})"/>
					</td>
					<td>
					          预约编号:
					</td>
					<td>
					    <input type="text" name="resvId" />
					</td>
					<td>
					           车辆品牌:
			        </td>
					<td>
					    <input type="text" id="carArchives_cbrdId" name="cbrdId"
					     class="easyui-combobox" 
					     data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
					     validType:'isSelected[\'#carArchives_cbrdId\']',
						 invalidMessage : '请从下拉框中选择车辆品牌',
					     valueField:'id',
					     textField:'text',
					     mode:'remote' "/>
					</td>
					<td>
					          车辆牌照:
					</td>
					<td>
					    <input type="text" name="carLicense" id="frtInsurePrize_summary_carId"
					     class="easyui-combobox" 
					      data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
					     valueField : 'id',
					     textField : 'text',
					     mode : 'remote'"/>
					</td>
				</tr>
				<tr>
				    <td>
					         预约进店:
					</td>
					<td  colspan="3">
					   <!--<input type="text" id="resvEnterTimeBegin" name="resvEnterTimeBegin" 
					   class="easyui-datetimebox" style="width: 140px;"
							data-options="	editable : false "/> 
					   ~<input type="text" id="resvEnterTimeEnd" name="resvEnterTimeEnd" 
					   class="easyui-datetimebox" style="width: 140px;"
					data-options="	editable : false "/>
					-->
					<input id="resvEnterTimeBegin" name="resvEnterTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'resvEnterTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="resvEnterTimeEnd" name="resvEnterTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'resvEnterTimeBegin\',{d:0})}'})"/>
					</td>
					<td>
					         预约客户:
					</td>
					<td>
					   <input type="text" name="customName" id="customName"
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action',
					    validType:'isSelected[\'#customName\']',
						invalidMessage : '请从下拉框中选择预约客户',
					    valueField : 'id',
					    textField : 'text',
					    mode : 'remote'"/>
					</td>
					<td>预约状态:</td>
					<td>
					    <input type="text" name="resvStatus" id="resvStatus" 
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.BESPEAKSTATE_TAG.BESPEAKSTATEKEY %>',
					    validType:'isSelected[\'#resvStatus\']',
						invalidMessage : '请从下拉框中选择预约状态',
					    valueField:'id',
					    textField:'text',
					    mode : 'remote'"/>
					</td>
					<td>预约分类:</td>
					<td>
					    <input type="text" name="resvType" id="resvType" 
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.BESPEAKCLASS_TAG.BESPEAKCLASSKEY %>',
					    validType:'isSelected[\'#resvType\']',
						invalidMessage : '请从下拉框中选择预约分类',
					    valueField:'id',
					    textField:'text',
					    mode : 'remote'"/>
					</td>
				</tr>
				<tr>
				    <td>
					          维修类别:
					</td>
					<td>
					    <input type="text" name="reptId" id="reptId"
					     class="easyui-combobox" 
					     data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllReptype.action',
					     validType:'isSelected[\'#reptId\']',
						 invalidMessage : '请从下拉框中选择维修类别',
					     valueField:'id',
					     textField:'text',
					     mode : 'remote'"/>
					</td>
					
					<td>
					          接待员:
					</td>
					<td>
					    <input type="text" name="stfId" id="stfName" 
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
					    validType:'isSelected[\'#stfName\']',
						invalidMessage : '请从下拉框中选择接待员',
					    valueField : 'id',
					    textField : 'text',
					    mode : 'remote'"/>
					</td>
					<td>
					         车辆编号:
					</td>
					<td>
					    <input type="text" name="carId" onkeyup="checkDate();" />
					</td>
					<td>
					         预约工位:
					</td>
					<td>
					    <input type="text" name="repwkId" id="repwkId" 
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllRepairWork.action',
					    validType:'isSelected[\'#repwkId\']',
						invalidMessage : '请从下拉框中选择预约工位',
					    valueField : 'id',
					    textField : 'text',
					    mode:'remote'"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="frtInsurePrizeSummaryCenter" data-options="region:'center',border:false" style="background:#eee;">
		<table id="frtInsurePrizeSummaryDatagrid"></table>
	</div>
</div>