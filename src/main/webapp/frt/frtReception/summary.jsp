<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 前台接车汇总 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/summary.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="north" id="search"
		style="overflow: hidden;padding:3px; background: #eee; height: 60px;"
		border="false">
		<form id="frtReceptionQueryForm">
			<table style="width:1200">
				<tr>
					<td>工单号:</td>
					<td><input type="text" name="receptionId"/></td>
					<td>预约编号:</td>
					<td><input type="text" name="resvId"/></td>
					<td>车牌照:</td>
					<td><input type="text" id="reception_summary_carId" name="carId" class="easyui-combobox"
					 data-options="
					 url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
					valueField : 'id',
					textField : 'text',
					mode : 'remote'"/></td>
					<td>客户名称:</td>
					<td><input type="text" name="customName"/></td>
					<td>维修类别:</td>
					<td><input type="text" id="reception_summary_reptId" name="reptId" class="easyui-combobox"
					data-options="
					url : 'frtOptionsAction!findAllReptype.action',
					validType:'isSelected[\'#reception_summary_reptId\']',
					invalidMessage : '请从下拉框中选择维修类别',
					mode : 'remote',
					valueField:'id',  
					textField:'text' "/></td>
					<td>工单状态:</td>
					<td><input type="text" id="reception_summary_receptionStatus" name="receptionStatus" class="easyui-combobox"
					data-options="
					url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.DOCUMENT_TAG.DOCUMENTKEY %>',
					validType:'isSelected[\'#reception_summary_receptionStatus\']',
					invalidMessage : '请从下拉框中选择工单状态',
					valueField:'id',  
					textField:'text',
					 mode : 'remote'"/></td>
				</tr>
				<tr>
					<td>进厂时间:</td>
					<td colspan="3">
					<!--<input type="text" id="interDateBegin" name="interDateBegin" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'interDateEnd\')}'})"/>
					<input type="text" id="interDateEnd" name="interDateEnd" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'interDateBegin\')}'})"/>
					-->
					<input id="interDateBegin" name="interDateBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'interDateEnd\',{d:-1})}'})"/>
	                                              至<input id="interDateEnd" name="interDateEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'interDateBegin\',{d:0})}'})"/>
					</td>
					<td>计划完工时间:</td>
					<td colspan="3">
					<!--<input type="text" id="receptionEndTimeBegin" name="receptionEndTimeBegin" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'receptionEndTimeEnd\')}'})"/>
					<input type="text" id="receptionEndTimeEnd" name="receptionEndTimeEnd" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'receptionEndTimeBegin\')}'})"/>
					-->
					<input id="receptionEndTimeBegin" name="receptionEndTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'receptionEndTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="receptionEndTimeEnd" name="receptionEndTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'receptionEndTimeBegin\',{d:0})}'})"/>
					</td>
					<td>预计交车时间:</td>
					<td colspan="3">
					<!--<input type="text" id="expDelCarTimeBegin" name="expDelCarTimeBegin" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'expDelCarTimeEnd\')}'})"/>
					<input type="text" id="expDelCarTimeEnd" name="expDelCarTimeEnd" style="width: 140px;"
					class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'expDelCarTimeBegin\')}'})"/>
					-->
					<input id="expDelCarTimeBegin" name="expDelCarTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'expDelCarTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="expDelCarTimeEnd" name="expDelCarTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'expDelCarTimeBegin\',{d:0})}'})"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="frtReceptionSummaryCenter" region="center" style="background: #eee;" border="false">
		<table id="frtReceptionSummaryDatagrid"></table>
	</div>
</div>