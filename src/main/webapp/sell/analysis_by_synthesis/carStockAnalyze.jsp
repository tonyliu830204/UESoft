<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<html>
  <head>
    <title>车辆库存量分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    
    <script type="text/javascript">
    var tbtitle;
    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	</script>
    <script type="text/javascript"	src="${pageContext.request.contextPath}/sell/analysis_by_synthesis/carStockAnalyze.js"></script>

  </head>
   <body>
		  <div id="cc" class="easyui-layout"  fit="true">
				<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false"> 
						<!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doFindbyCondition();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doClear();">清空</a>
						--><a id='_print' href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_config();">打印</a>
						<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"iconCls="icon-export" plain="true"  onclick="_except();">导出</a>
			
				<!--<form id="kucun_form_id">
				   <fieldset style="height:45px">
						<legend style="font-weight:bold">查询条件</legend>
						
						<table style="text-align:right;" >
							<tr >
								<td >日期:</td>	
								<td ><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'car_sellDate2\',{d:-1})}'})" name="instorehouseDate" id="car_sellDate" style="width:85px"/> 至 </td>
								<td><input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'car_sellDate\',{d:1})}'})" name="instorehouseDate2" id="car_sellDate2" style="width:85px"/></td>
	
								
								
				         		</tr> 
				         		</table>
								</fieldset>
								</form>
								--></div>
								
								
						
				<div region="center" style="background:#eee;" border="false">
				
			 <div id="tt" class="easyui-tabs" style="width: 800px; height: 600px;" border="false"
					fit="true">
						<div title="车辆颜色分类"  border="false" id="ccid">
						<div class="easyui-layout" border="false" fit="true"
							>
							<div  id="carColor" region="center" border="false">
								<table id ="carColor_datagrid_id"></table>
							</div>
							
						</div>
					</div>
					<div title="车辆销售状态"  border="false" id="ccid">
						<div class="easyui-layout" border="false" fit="true"
							>
							<div  id="carSellState" region="center" border="false">
								<table id ="carSellState_datagrid_id"></table>
							</div>
							
						</div>
					</div>
					
					
					<div title="车辆分销状态"  border="false">
						<div class="easyui-layout" border="false" fit="true"
							style="width: 800px; height: 600px;">
							<div id="fenx" region="center" border="false">
								<table id="dis_datagrid_id"></table>
							</div>
						</div>
					</div>
						
				</div>
				</div>
				</div>	

  </body>
  
</html>
