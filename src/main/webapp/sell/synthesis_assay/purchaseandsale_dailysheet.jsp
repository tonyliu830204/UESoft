<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆进销存日报表</title>
    <script type="text/javascript" src="<%=basePath %>sell/selljs/unite_assay.js"></script>
    <script type="text/javascript">
     var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    </script>
  	</head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true"  onclick="getTreeInfo($('#datagrid_purchaseandsale_dailysheet_id'),$('#form_purchaseandsale_dailysheet_id'),'purchaseandsaleDailysheetAction!getDailysheetParent.action');" >查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear($('#form_purchaseandsale_dailysheet_id'));">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_print' onclick=" _configPurchase('datagrid_purchaseandsale_dailysheet_id','datagrid_purchaseandsale_dailysheet_div')"; >打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="doExportPurchase('datagrid_purchaseandsale_dailysheet_id','datagrid_purchaseandsale_dailysheet_div');" >导出</a>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:70px;" border="false">
				<form id="form_purchaseandsale_dailysheet_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
						<tr>
							<td>统计日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'recordDate2\',{d:-1})}'})" name="recordDate" id="recordDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'recordDate\',{d:1})}'})" name="recordDate" id="recordDate2" style="width: 85px;"/></td>
							<td>车辆品牌:</td>
							<td><input type="text" id="car_Brand_id" name="carBrand"  class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath }/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',			    		
								onLoadSuccess : function(){
									
								},
				    			onSelect: function(rec){  
					            $('#car_Model_id').combobox('reload', '${pageContext.request.contextPath }/carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
					        } "
					        />
				        </td>
						<td>车辆型号:</td>
						<td><input type="text" id="car_Model_id" name="carModel"  class="easyui-combobox" 
						data-options="
						url:'${pageContext.request.contextPath }/carModelAction!findAllCarModel.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
							onLoadSuccess : function(){
							},
			    			onSelect:function(rec){
							} 
							"/>
					        </td>
						</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_purchaseandsale_dailysheet_div" region="center" border="false" >
					<table id="datagrid_purchaseandsale_dailysheet_id"></table>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
