<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>入库单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript">
	var sgsm_d1;
	function addCarBrand()
	{
	 sgsm_d1 = $('<div/>');
	 sgsm_d1.dialog({
		title: '车品牌选择',   
	    width: 400,   
	    height: 300,
	    cache: false,    
	    href: '${pageContext.request.contextPath}/sell/carInfo/carBrandPop.jsp?key=carBrand',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
	var sgsm_d2;
	function addCarModel()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '车型号选择',   
	    width: 450,   
	    height: 300,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/carInfo/carModelPop.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
</script>	
  </head>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/instorehouseQuery/queryInstore.js"></script>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<privilege:enable code="INSTOREQUERY_SEARCH">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryHouse();">查询</a>
	</privilege:enable>
	<privilege:enable code="INSTOREQUERY_CLEAR">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearaa();">清空</a>
	</privilege:enable>
	<privilege:enable code="INSTOREQUERY_PRINT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="_config();">打印</a>
	</privilege:enable>
	<privilege:enable code="INSTOREQUERY_EXPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
	</privilege:enable>
	<!--<privilege:enable code="INSTOREQUERY_IMPORT">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true">导入</a>
  </privilege:enable>
    --></div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:110px;">
		    	
		    	
		    	<form id="carModelQueryForm" name="carModelQueryForm" method="post"  fit="true" >
							<table>
								 <tr>
							
								 	<td width="80px">入库日期:</td>
								 	<td style="text-align: left;" colspan="3"><input type="text" id="instoreStart" name="instoreStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'instoreEnd\')}'});"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="instoreEnd" name="instoreEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'instoreStart\')}'});"/></td>
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
									<td><input type="text" id="car_Model_id" name="carModelId" style="width:150px;"  class="easyui-combobox" 
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
								
								</tr>
								<tr>
									<td>拷车日期:</td>
									<td style="text-align: left;" colspan="3"><input type="text" id="copyStart" name="copyDateStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'copyEnd\')}'});"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="copyEnd" name="copyDateEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'instoreStart\')}'});"/></td>
									<td>VIN号码:</td>
									<td><input type="text" name="carVinNumber" style="width: 120px" class="easyui-validatebox" maxlength="17" ></td>
									<td>OCN码:</td>
									<td><input type="text"  name="carOcn"  style="width: 125px"></td>
									<td>仓库:</td>
									<td><input type="text" name="warehouse" id="warehouse" style="width: 150px" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#car_type\']',
										invalidMessage : '请从下拉框中选择仓库'"/></td>
								</tr>
								<tr>
									<td>入库单号:</td>
									<td><input type="text" name="instorehouseCodes" id="instorehouseCode" /></td>
									<td>采购员:</td>
									<td><input id="stfId"  name="stfId"  class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findCarStockPerson.action',   
													editable:false,
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote',
										    		validType:'isSelected[\'#stfId\']',
					    							invalidMessage : '请从下拉框中选择业务员'" />  
									</td> 		
									<td>审核情况:</td>
									<td><input type="text" name="examineState"  style="width: 120px" id="examineState" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT%>',
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
