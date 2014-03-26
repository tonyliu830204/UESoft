<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<jsp:include page="/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>销售毛利分析</title>
    <script type="text/javascript">
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	    var systemAll = "<%=Contstants.SYSTEMTYPE.ALL%>";
    </script>
  	</head>
  		<body>
  		    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellMlAnalyze.js"></script>
  		
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"  onclick="queryCarReserve();" >查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear();">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_print'  onclick="_config();">打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="doExport();" >导出</a>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:100px;" border="false">
				<form id="form_list_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
						<tr>
							<td>销售日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsSellDate2\',{d:-1})}'})" name="xsCarSelData" id="xsSellDate" style="width: 90px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsSellDate\',{d:1})}'})" name="xsCarSelData2" id="xsSellDate2" style="width: 90px;"/></td>
							<td>
								品牌:
							</td>
								<td><input type="text" id="car_Brand_id" name="carBrand" style="width:140px;" class="easyui-combobox" data-options="
								url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
								valueField:'id',   
					    		textField:'text',
					    		mode:'remote',			    		
					    		validType:'isSelected[\'#car_Brand_id\']',
					    		invalidMessage : '请从下拉框中选择车辆品牌',
					    		onSelect: function(rec){  
					    			$(this).combobox('textbox').bind('keyup', function (){
					    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
					    					$('#car_Model_id').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
					    				}
					    			});
						            $('#car_Model_id').combobox('clear');
						            $('#car_Model_id').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
						        } "
						        />
						       
						        </td>
							<td>车型:</td>
								<td><input type="text" id="car_Model_id" name="carModel" style="width:150px;"  class="easyui-combobox" 
								data-options="
								
								url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
								valueField:'id',   
					    		textField:'text',
					    		mode:'remote',
					    		validType:'isSelected[\'#car_Model_id\']',
					    		invalidMessage : '请从下拉框中选择车辆型号',
					    		onSelect:function(rec){
					    			$(this).combobox('textbox').bind('keyup', function (){
					    				if($('#car_Model_id').combobox('getValue') == '' || $('#car_Model_id').combobox('getValue') != $('#car_Model_id').combobox('getText')){
					    					$('#carArchives_add_carCstlId').combobox('reload', 'carModelAction!findAllCarModel.action');
					    				}
					    			});  
						         
						        } "/>
						        
						        
								</td>
					
					    <td>VIN号:</td>
						<td><input name="carVinNumber" style="width: 130px"/></td>   
								
							</tr>
						<tr>
						<td>出库日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreatDate2\',{d:-1})}'})" name="retreat_date" id="retreatDate" style="width: 90px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreatDate\',{d:1})}'})" name="retreat_date2" id="retreatDate2" style="width: 90px;"/></td>
						
							<td>客户名称:</td>
							<td><input name="cuName" style="width: 140px" /></td>
							
						
						<td>
							业务员:
						</td>
						<td>
						<input type="text" id="stf_id" name="xsCarStfId"  class="easyui-combobox" 
						 style="width: 150px"
							data-options="url:'sellUtilAction!findUsers.action',  
						
								valueField:'id',   
								textField:'name',
								mode : 'remote',
								validType:'isSelected[\'#stf_id\']',
								invalidMessage : '请从下拉框中选择经办人'"  />	
						</td>
				       		
						</tr>
						
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_center_div" region="center" border="false" >
					<table id="carAndCustomList"></table>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
