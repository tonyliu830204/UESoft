<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>库存量查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/inventoryQuery/inventory.js"></script>
		 <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    
		  	var sgsm_d1;
			function adddis2()
			{
			 sgsm_d1 = $('<div/>');
			 sgsm_d1.dialog({
				title: '请选择',   
			    width: 650,   
			    height:400,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor2.jsp',
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
			<privilege:enable code="INVENTY_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="INVENTY__CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="INVENTY__PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="INVENTY__EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
			</privilege:enable>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 140px;">
					<form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
							
								<td width="75px">
									入库日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="instorehouseDate" id="zhProjectDate1" style="width: 110px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 至&nbsp;&nbsp;
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="instorehouseDate2" id="zhProjectDate2" style="width: 110px;"/></td>
								<td>
									分销商:
								</td>
								<td colspan="3"><input id="dName2" name="distributorName" style="background-color:#c0d8d8; width: 290px" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8;"/>
								<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="adddis2();"/>
									<input name="distributorId" id="did2" type="hidden"  />
								</td> 
								
								<td>
								
								OCN码:
								</td>
								<td>
									<input type="text" name="carOcn"
										 style="width: 130px"/>
								
															
								</td>
								
								
						
								</tr>
								<tr>
								<td>
							 VIN码:
								</td>
								<td>
									<input type="text" name="carVinNumber" maxlength="17" style="width: 118px">
								
								
									品&nbsp;&nbsp;&nbsp;&nbsp;牌:
								
									<input type="text" id="car_Brand_id" name="carBrand" style="width:95px;" class="easyui-combobox" data-options="
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
									<td><input type="text" id="car_Model_id" name="carModelId" style="width:120px;"  class="easyui-combobox" 
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
													
								<td>内饰色:</td>
									<td><input type="text" id="interiorColor" name="carInteriorColor"  style="width:120px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR%>',
									valueField:'id',   
						    		textField:'text',
						    		validType:'isSelected[\'#interiorColor\']',
						    		invalidMessage : '请从下拉框中选择内饰色',
						    		mode:'remote' "/></td>
						    		<td>
										车身颜色:
									</td>
									<td>
										<input style="width: 130px" name="carColor" id="carColor" 
											class="easyui-combobox"
											data-options="
												url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
												multiple:false,
												valueField:'id',  
												textField:'text'
												" />
									</td>
								
								</tr>
								<tr>
								<td>
									合格证:
								</td>
							
								<td><input name="carCertificate" style="width:118px" />
									发动机:						
								<input type="text" name="carMotorNumber" style="width: 95px">
								</td>
											
							 <td >仓库:</td>							
							    <td><input type="text" name="warehouse" id="warehouse"  style="width:120px;"   class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',
								
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#warehouse\']',
								invalidMessage : '请从下拉框中选择仓库'"/></td>
						
							<td>车辆编号:</td>
							<td><input name="carCode" style="width: 120px"/></td>
							<td >代交寄车:</td>
									<td><input type="text" name="carAddress" id="car_type" class="easyui-combobox" style="width:130px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARTYPE%>',
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_type\']',
										invalidMessage : '请从下拉框中选择代交寄车'"></td>
							
								</tr>
								<tr>
								<td>
									供应商:							
								</td>
									<td><input type="text" name="xsSupplierId" id="car_supplierId" class="easyui-combobox" style="width:118px;" data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',				
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_supplierId\']',
										invalidMessage : '请从下拉框中选择供应商'">
								
								库&nbsp;&nbsp;&nbsp;&nbsp;龄:
								<input name="carInstorageAge"style="width: 42px"/>~<input name="carInstorageAge1"  style="width: 42px"/>
								   	
								   </td>
									
									
								<td colspan="4"><a href="javascript:void(0);" class="easyui-linkbutton"
							style="width: 120px; color: blue;" onclick="queryCarAge();">库龄报警查询</a></td>
								</tr>
						</table>
						</fieldset>
					</form>
				</div><blockquote><br></blockquote><div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="account" name="account"></table> 
				</div>
				
				<div data-options="region:'south',border:false"style="background: #eee;height: 60px">
				<form id="sumqu">
				<table style="color:purple;">
				<tr>
				<td style="width:120px;"><div style="font-size:16;color:purple;">库存数量:</div></td><td ><input name="num" id="num" readonly="readonly" style="180px"></td>
				<td style="width:90px;"><div style="font-size:16;color:purple;">库存金额:</div></td><td ><input name="costPrice" id="costPrice" readonly="readonly" style="180px"></td>
				<td style="width:120px;"><div style="font-size:16;color:purple;">预计销售金额:</div></td><td ><input name="salesPrice" id="salesPrice"readonly="readonly" style="180px"></td>
				</tr>
				</table>
				</form>
				
				</div>
			</div>
		</div>
	</body>
</html>
