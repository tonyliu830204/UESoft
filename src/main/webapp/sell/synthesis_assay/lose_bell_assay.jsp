<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>战败原因分析</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/potential_customer_assay.js"></script>
    <script type="text/javascript">
   	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
   
    </script>
  	</head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" 
		 onclick="doFindbyCondition($('#datagrid_lose_bell_assay_id'),$('#form_lose_bell_assay_id'),'potentialcustomerAssayAction!getJFreeChartJpgOfLoseBell.action','jfreemchart_lose_bell_id');" >查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear($('#datagrid_lose_bell_assay_id'),$('#form_lose_bell_assay_id'),'potentialcustomerAssayAction!getJFreeChartJpgOfLoseBell.action','jfreemchart_lose_bell_id');">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_print' onclick=" _config('datagrid_lose_bell_assay_id','datagrid_lose_bell_assay_div')"; >打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="doExport('datagrid_lose_bell_assay_id','datagrid_lose_bell_assay_div',_callbackExceptNine);" >导出</a>
		
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:60px;" border="false">
				<form id="form_lose_bell_assay_id">
				<fieldset>
					<legend>查询条件</legend>
						<table style="text-align: right">
						<tr>
							<td>建档日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCustomInputdate2\',{d:-1})}'})" name="xsCustomInputdate" id="xsCustomInputdate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCustomInputdate\',{d:1})}'})" name="xsCustomInputdate2" id="xsCustomInputdate2" style="width: 85px;"/></td>
							<td>放弃日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'loseDate2\',{d:-1})}'})" name="loseDate" id="loseDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'loseDate\',{d:1})}'})" name="loseDate2" id="loseDate2" style="width: 85px;"/></td>
							<td>业务员:</td>
							<td>
								<input name="stfId"
								class="easyui-combobox"	data-options="
								url : 'basStuffClassAction!findSellOperationPerson.action',
								valueField:'id',  
								textField:'text',
								multiple:false,
								mode:'remote'  "
								/>
							</td>
						</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_lose_bell_assay_div" region="center" border="false" >
					<table id="datagrid_lose_bell_assay_id"></table>
				</div>
				<div region="south" style="height:350px;" border="true" id="southdivid"> 
				<div style="text-align: center;background-color: #C0C0C0;" fit="true">
					<img id="jfreemchart_lose_bell_id"  src="potentialcustomerAssayAction!getJFreeChartJpgOfLoseBell.action"/>
				</div>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
