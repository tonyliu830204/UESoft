<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 工单综合查询->工单基本信息 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkOrder/base.js"></script>
<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
	border="false">
	<div region="north" title="查询条件"
		style="overflow: hidden;background:#eee; height:100px;" border="false">
		<form id="frtWorkOrderBaseQueryForm">
			<table>
				<tr>
					<td>进厂时间:</td>
					<td colspan="3">
					<!--<input type="text" id="interDateBegin" name="interDateBegin" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'interDateEnd\')}'})"/>
					<input type="text" id="interDateEnd" name="interDateEnd" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'interDateBegin\')}'})"/></td>
					-->
					<input id="interDateBegin" name="interDateBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'interDateEnd\',{d:-1})}'})"/>
		                                              至<input id="interDateEnd" name="interDateEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'interDateBegin\',{d:0})}'})"/>
		             </td>
					<td>VIN号:</td>
    					<td><input type="text" name="carVin"/></td>
    				<td>
					           车辆品牌:
			        </td>
					<td>
					    <input type="text" id="frtWorkOrderBaseQueryCbrdId" name="cbrdId" 
					    class="easyui-combobox" 
					    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
					    valueField:'id',
					    textField:'text',
					    validType:'isSelected[\'#frtWorkOrderBaseQueryCbrdId\']',
						invalidMessage : '请从下拉框中选择车辆品牌',
						mode:'remote'  "/>
					</td>
    			</tr>
				<tr>
					<td>工单号:</td>
					<td><input type="text" name="receptionId"/>
					</td>
					<td>车辆牌照:</td>
					<td><input type="text" id="frtWorkOrderBaseQueryCarId" name="carId" class="easyui-combobox"
					data-options="
					url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
					valueField : 'id',
					textField : 'text',
					mode : 'remote'"/></td>
					<td>客户名称:</td>
					<td><input type="text" id="frtWorkOrderBaseQueryCustomId" name="customId" class="easyui-combobox"
						data-options="
						url : 'frtOptionsAction!findAllCustom.action',
						valueField:'id',  
						textField:'text',
						validType:'isSelected[\'#frtWorkOrderBaseQueryCustomId\']',
						invalidMessage : '请从下拉框中选择客户名称',
						mode:'remote'  "/></td>
					<td>票据类型:</td>
	      			<td><input type="text"  id="frtWorkOrderBaseQueryPreclrInoiceType"  name="preclrInoiceType" class="easyui-combobox"
        			data-options="
					url : 'frtOptionsAction!findBaseListData.action\?key=billType',
					valueField:'id',  
					textField:'text',
					validType:'isSelected[\'#frtWorkOrderBaseQueryPreclrInoiceType\']',
					invalidMessage : '请从下拉框中选择票据类型',
					mode:'remote'  "/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="frtWorkOrderBaseDatagrid_center" region="center" style="background:#eee;" border="false">
		<table id="frtWorkOrderBaseDatagrid"></table>
	</div>
</div>