<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆上报管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/carInfo/carUpInfo.js"></script>

	   <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	</head>
	<body>
		

		<div id="cc" class="easyui-layout"
			style="width: 800px; height: 600px;" fit="true" border="false">
			<div data-options="region:'north',border:false"
				style="padding: 1px; height: 27px; overflow: hidden; background: #eee;">

				<privilege:enable code="CARUP_SEARCH">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-search" plain="true" onclick="queryCarUp();">查询</a>
				</privilege:enable>
				<privilege:enable code="CARUP_CLEAR">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" plain="true"
						onclick="clearSearch();">清空</a>
				</privilege:enable>
				<privilege:enable code="CARUP_PRINT">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-print" plain="true" onclick="_config();">打印</a>
				</privilege:enable>
				<privilege:enable code="CARUP_EXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-export" plain="true" onclick="_except();">导出</a>
				</privilege:enable>
				<privilege:enable code="CARUP_SB">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-examine" plain="true" id="_examine"
						onclick="carUp();">上报</a>
				</privilege:enable>

			</div>
			<div data-options="region:'center',border:false"
				style="background: #eee;">
				<div class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'north',title:'查询条件'"
						style="overflow: hidden; padding: 1px; background: #eee; height: 100px;"
						border="false">
						<form id="carInfoQueryForm" name="carInfoQueryForm" method="post">
							<table>
								<tr>
								<td>销售日期:</td>
								<td >
								<input type="text" class="Wdate"
								onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserveDete2\',{d:-1})}'})"
								name="sellData" id="reserveDete" style="width: 100px;" />至
								<input type="text" class="Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserveDete\',{d:1})}'})"
								name="sellData2" id="reserveDete2" style="width: 100px;" 
								 />
								</td>
								<td>
									VIN号码:
								</td>
								<td>
									<input type="text" name="carVinNumber" maxlength="17" style="width: 120px;">
								</td>
								<td>
									品牌:
								</td>
									<td><input type="text" id="car_Brand_id" name="carBrand" style="width:120px;" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/${pageContext.request.contextPath }/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',			    		
						    		validType:'isSelected[\'#car_Brand_id\']',
						    		invalidMessage : '请从下拉框中选择车辆品牌',
						    		onSelect: function(rec){  
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#car_Brand_id').combobox('getValue') == '' || $('#car_Brand_id').combobox('getValue') != $('#car_Brand_id').combobox('getText')){
						    					$('#car_Model_id').combobox('reload', '${pageContext.request.contextPath }/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
						    				}
						    			});
							            $('#car_Model_id').combobox('clear');
							            $('#car_Model_id').combobox('reload', '${pageContext.request.contextPath }/carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
							        } "
							        />
							       
							        </td>
								<td>车型:</td>
									<td><input type="text" id="car_Model_id" name="carModelId" style="width:130px;"  class="easyui-combobox" 
									data-options="
									
									url:'${pageContext.request.contextPath}/${pageContext.request.contextPath }/carModelAction!findAllCarModel.action',
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
							        <td>供应商:</td>		
								<td><input type="text" name="supplierId"  id="car_supplierIds" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/${pageContext.request.contextPath }/supplierInfoAction!findAllSupplier.action',
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#car_supplierIds\']',
										invalidMessage : '请从下拉框中选择供应商'"/></td>
									
								</tr>
								<tr>
								<td>出库日期:</td>
								<td >
								<input type="text" class="Wdate"
								onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreat_date2\',{d:-1})}'})"
								name="retreat_date" id="retreat_date" style="width: 100px;" />至
								<input type="text" class="Wdate"
								onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreat_date\',{d:1})}'})"
								name="retreat_date" id="retreat_date2" style="width: 100px;" 
								 />
								</td>
								
								<td>
									出库单号:
								</td>
								<td>
									<input type="text" name="retreat_code" style="width: 120px;">
								</td>
								<td>
									客户姓名:
								</td>
								<td>
									<input type="text" name="customName" style="width: 120px;">
								</td>
								
								<td>上报情况:</td>
									<td>
										<input id="upType"  name="upType" class="easyui-combobox" style="width: 130px;"  
						            	data-options="
									    editable : false,
									    data:[{'id':'up','text':'已上报'},{'id':'noUp','text':'未上报'}],
										valueField:'id',
										textField:'text'
										"/>
									</td>	
									<td>
										上报人:
									</td>
									<td>
									<input type="text" id="upPerson" name="upPerson"  class="easyui-combobox"  
										 style="width: 140px"
										data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',  
									
											valueField:'id',   
											textField:'text',
											mode : 'remote',
											validType:'isSelected[\'#upPerson\']',
											invalidMessage : '请从下拉框中选择上报人'"  />	
									
									
									</td>

								</tr>
							</table>
						</form>
					</div>
					<div id="carUpInfo_div" region="center" style="background: #eee" border="false">
						<table id="carUpInfo"></table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>