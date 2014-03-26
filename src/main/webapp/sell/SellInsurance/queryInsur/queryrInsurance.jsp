<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆保单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/SellInsurance/queryInsur/queryInsurance.js"></script>
		 <script type="text/javascript">
	    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	    </script>
		<script type="text/javascript">
			var sgsm_d1;
			function adddis()
			{
			 sgsm_d1 = $('<div/>');
			 sgsm_d1.dialog({
				title: '请选择',   
			    width:650,   
			    height: 450,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/SellInsurance/queryInsurerInfo.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}

</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">

			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
		
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>

			<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="_config();">打印</a>
			
			<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true" onclick="_except();">导出</a>
			
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 80px;">
					<form id="carQueryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td width="70px">
									销售日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="carSellData1" name="carSellData"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'carSellData2\',{d:-1})}'});" />
									至
									<input type="text" id="carSellData2" name="carSellData2"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'carSellData1\',{d:1})}'});" />
								</td>
								<td width="100px">
									保险起始日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="insurerStartDate" name="insurerStartDate"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'insurerStartDate1\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="insurerStartDate1" name="insurerStartDate1"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'insurerStartDate\',{d:1})}'});" />
								</td>
								
								<td width="100px">
									保险到期日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="insurerEndDate" name="insurerEndDate"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'insurerEndDate1\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="insurerEndDate1" name="insurerEndDate1"
										class="Wdate" style="width: 100px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'insurerStartDate\',{d:1})}'});" />
								</td>
					
								<td width="70px">
									客户姓名:
								</td>
								<td>
								<input style="width:110px"  id="" name="customName" />
								</td>
								
							
							</tr>
							
						</table>
						</fieldset>
					</form>
				</div>
				<div id="insurance" data-options="region:'center',border:false"
					style="background: #eee;">
					<table id="insuranceTable" name="insurance"></table>
				</div>
			</div>
		</div>
	</body>
</html>
