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
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/SellInsurance/queryCarInsurance.js"></script>
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
			<privilege:enable code="CINSURANCE_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="CINSURANCE_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="CINSURANCE_PRINT">
			<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="CINSURANCE_EXPORT">
			<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true" onclick="_except();">导出</a>
			</privilege:enable>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 100px;">
					<form id="carQueryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								<td width="70px">
									代保日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="instoreStart" name="dafeDate"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instoreEnd\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="instoreEnd" name="dafeDate2"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'instoreStart\',{d:1})}'});" />
								</td>
								<td width="60px">
												保险公司:
											</td>
											<td colspan="3">
												<input id="dName1" name="insurerName"
													style="background-color:#c0d8d8; width: 280px"
													onkeypress=" if(event.keyCode==13) { adddis(); return false;}"
													style="background-color:#c0d8d8;" />
												<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"
													onclick="adddis();" />
												<input name="insurerId" id="did1" type="hidden" />
											</td>
					
								<td width="70px">
									客户姓名:
								</td>
								<td>
								<input style="width:130px"  id="customName" name="customName" />
								</td>
								
							
							</tr>
							<tr>
							   <td width="100px">
									保险起始日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="insurerStartDate" name="insurerStartDate"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'insurerStartDate1\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="insurerStartDate1" name="insurerStartDate1"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'insurerStartDate\',{d:1})}'});" />
								</td>
							   <td width="100px">
									保险到期日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="insurerEndDate" name="insurerEndDate"
										class="Wdate" style="width: 135px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'insurerEndDate1\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="insurerEndDate1" name="insurerEndDate1"
										class="Wdate" style="width: 125px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'insurerEndDate\',{d:1})}'});" />
								</td>
													
								
							

								<td width="60px">
									VIN号码:
								</td>
								<td>
									<input type="text" name="vin"	 style="width: 130px"/>
								</td>

								<td >
									车牌照:
								</td>
								<td>
									<input type="text" name="carLicensePlate" style="width: 118px">
								</td>

							

								
								<!--<td width="60px">
									排列:
								</td>
								<td>
									<input type="text" name="sortState" id="sortState"
										style="width: 125px" class="easyui-combobox"
										data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SORTSTATE%>',
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#sortState\']',
										invalidMessage : '请从下拉框中选择排列方式'" />
								</td>
							--></tr>
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
