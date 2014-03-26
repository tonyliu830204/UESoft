<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>应收帐款统计</title>
	<script type="text/javascript">
 		var tbtitle
 	 	var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
    	var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    	var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/analysis_by_synthesis/shouldBill_query.js"></script>

  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doFindbyCondition();">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();" >清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();" >导出</a>
		<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_config();">打印</a>
		
		</div>
		<div region="center" style="background:#eee;"  border="false">
				<div id="tt" class="easyui-tabs" fit="true">
				<div title="应收款查询" >
					 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:35px;" border="false">
						   <form id="form_ys_bill_id">
						   <table>
						   <tr>
							   <td>创建日期:</td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountDate2\',{d:-1})}'})" name="date_" id="accountDate" style="width: 85px;"/> 至 
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'accountDate\',{d:1})}'})" name="date_two" id="accountDate2" style="width: 85px;"/>
							 </td>  
							  <td>客户姓名:<input  name="xs_Custom_Name" /></td>
							  <td>VIN号:<input id="" name="xs_Car_Vin_Number" /></td>
							   <td>付款方式:</td>
							<td>
								<input style="width:150px" id="received_Way_id" name="received_Way_id"
								class="easyui-combobox"
								data-options="
								url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PAYMENTWAY %>',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'"
								/>
								</td>
							</tr>	
							</table>
						    </form>
					   </div>
						<div id="datagrid_ys_bill_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_ys_bill_id"></table>
						</div>
						
				  	</div>
				</div>
				<div title="应收款欠款查询" >
					<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:35px;" border="false">
						   <form id="form_ye_bill_detail_id">
						   <table>
						   	<tr><td>创建日期:</td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'date2\',{d:-1})}'})" name="account_Date" id="date1" style="width: 85px;"/> 至 
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'date1\',{d:1})}'})" name="account_Date2" id="date2" style="width: 85px;"/>
							 </td>  
						   		<td>客户姓名:<input  name="xs_Custom_Name" /></td>
							  <td>VIN号:<input id="" name="xs_Car_Vin_Number" /></td>
						   	</tr>
						   	
						   </table>
						    </form>
					   </div>
						<div id="datagrid_ye_bill_detail_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_ye_bill_detail_id"></table>
						</div>
						
				  	</div>
				</div>
		
				</div>
	  	
			</div>
		</div>
  </body>
</html>
