<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>退厂单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/carBackQuery/carBackQuery.js"></script>

  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<privilege:enable code="CARBACKQUERY_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryHouse();">查询</a>
	</privilege:enable>
	<privilege:enable code="CARBACKQUERY_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearaa();">清空</a>
	</privilege:enable>
	<privilege:enable code="CARBACKQUERY_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
	</privilege:enable>
	<privilege:enable code="CARBACKQUERY_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
	</privilege:enable>
	<!--<privilege:enable code="CARBACKQUERY_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
   </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:110px;">
		    	<form id="carModelQueryForm" name="carModelQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
								 	<td width="75px">退厂日期:</td>
								 	<td style="text-align: left;" colspan="3"><input type="text" id="reDateStart" name="reDateStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'repyDateEnd\',{d:-1})}'});"/>
									&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="repyDateEnd" name="repyDateEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'reDateStart\',{d:1})}'});"/></td>
									<td>供应商:</td>
									<td><input type="text" name="supplierId" style="width: 120px" id="car_supplierId" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#car_supplierId\']',
										invalidMessage : '请从下拉框中选择供应商'"/></td>
												<td>
									品牌:
								</td>
									<td><input type="text" id="car_Brand_id" name="carBrand" style="width:125px;" class="easyui-combobox" data-options="
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
									<td><input type="text" id="car_Model_id" name="querymodel" style="width:150px;"  class="easyui-combobox" 
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
							      
								</tr>
								<tr>
									<td>入库日期:</td>
									<td style="text-align: left;" colspan="3">
									<input type="text" id="instoreStart" name="instoreStart"  class="Wdate" 	onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instoreEnd\',{d:-1})}'});"/>
									&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="text" id="instoreEnd" name="instoreEnd" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instoreStart\',{d:1})}'});"/></td>
									<td>VIN号码:</td>
									<td><input type="text" name="carVinNumber"  style="width: 120px" maxlength="17"></td>
									<td>OCN码:</td>
									<td><input type="text"  name="carOcn"  style="width: 125px"></td>
									<td>经办人:</td>
									<td><input id="stfId"  name="person" style="width: 150px" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',   
													editable:false,
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote',
										    		validType:'isSelected[\'#stfId\']',
					    							invalidMessage : '请从下拉框中选择业务员'" />  
									</td> 	
								</tr>
								<tr>
									<td>退车单号:</td>
									<td><input type="text" name="RetreatCode" id="instorehouseCode" /></td>
										
									<td>审&nbsp;&nbsp;核:</td>
									<td><input type="text" name="examine" id="examineState" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT%>',
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#examineState\']',
										invalidMessage : '请从下拉框中选择审核情况'"/></td>	
									
								</tr>
							</table>
			 </form>
		    </div>
		    <div id="instoreTree_div" data-options="region:'center',border:false" style="background:#eee;">
		    	     <table id="instoreTree" name="instoreTree"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
