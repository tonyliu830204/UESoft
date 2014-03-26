<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'StPurOrderManager.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/carAllocateWork/carAllocateJS.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="north" split="false"
				style="height: 30px; background: #eee;" border="false">
					<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true" onclick="queryCarReserve();">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-cancel" plain="true" id="qc" onclick="_clear();">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-print" plain="true" onclick="dopoint('carAllocateList','qingdan');">打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-export" plain="true" onclick="doexcept('carAllocateList','qingdan');">导出</a>
			
				<br />
			</div>
			<div region="center" split="false" border="false">
				<div id="tt" class="easyui-tabs" fit="true" border="false">
					<div title="客户订单信息" style="display: block;" fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true" border="false">
							<div data-options="region:'north',title:'查询条件'"
								style="overflow: hidden; padding: 1px; background: #eee; height: 100px;"
								border="false">
								<form id="CarReserveForm" name="CarReserveForm" method="post">
										<fieldset>
											<table>
												<tr>
													<td width="75px"> 
														预订日期：
													</td>
													<td>
														<input type="text" class="Wdate"
															onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserveDete2\',{d:-1})}'})"
															name="reserveDete" id="reserveDete" style="width: 95px;" />
														至
													</td>
													<td>
														<input type="text" class="Wdate"
															onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserveDete\',{d:1})}'})"
															name="reserveDete2" id="reserveDete2"
															style="width: 95px;" />
													</td>


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
													
													

													<td colspan="2">
														<a href="javascript:void(0);" class="easyui-linkbutton"
															style="width: 120px" onclick="expireWarn();">预订单交期提醒</a>
													</td>
												</tr>
											<tr>
													<td width="75px">
														预交日期：
													</td>
													<td>
														<input type="text" class="Wdate"
															onclick="WdatePicker({maxDate:'#F{$dp.$D(\'predictTakeDate2\',{d:-1})}'})"
															name="predictTakeDate" id="predictTakeDate"
															style="width: 95px;" />
														至
													</td>
													<td>
													<input type="text" class="Wdate"
															onclick="WdatePicker({minDate:'#F{$dp.$D(\'predictTakeDate\',{d:1})}'})"
															name="predictTakeDate2" id="predictTakeDate2"
															style="width: 95px;" />
													</td>
													<td>
														业务员:
													</td>
													<td>
														<input name="stfId" class="easyui-combobox" style="width: 140px;"
															data-options="url : 'sellUtilAction!findUsers.action',
														valueField:'id',  
														textField:'name',
														mode : 'remote',
														multiple:false  " />
													</td>
													<td>
														客户姓名:
													</td>
													<td>
														<input type="text" name="customName"  style="width: 150px;"/>
														
													</td>
											</tr>
											</table>
										</fieldset>
									</form>
							</div>
							<div id="qingdan" region="center" style="background: #eee;"
								border="false">
								<table id="carAllocateList" name="carAllocateList"></table>
							</div>
						</div>
					</div>
					<div title="车辆档案信息" style="display: block;" closable="false"
						fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true">
							<div region="north"  split="false"
								style="overflow: hidden; background: #eee; height: 115px;"
								border="false">
								<form id="Car_f" method="post">
									 <fieldset>	
												<table>
													<tr>
													
														<td>
															品牌:
														</td>
														<td>
															<strong><input type="text" id="queryBrands" readonly="readonly" disabled="disabled"
																	name="carBrand" class="easyui-combobox"
																	data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
																editable:false,
																valueField:'id',   
																textField:'text',
																mode : 'remote',
																validType:'isSelected[\'#queryBrands\']',
																invalidMessage : '请从下拉框中选择车辆品牌'">
															</strong>
														</td>
														<td>
															类型：
														</td>
														<td>
															<input type="text" id="queryModels" name="carModel" readonly="readonly" disabled="disabled"
																class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
																editable:false,
																valueField:'id',   
																textField:'text',
																mode : 'remote',
																validType:'isSelected[\'#queryModels\']',
																invalidMessage : '请从下拉框中选择车辆型号'" />
														</td>
														<td>
															颜色：
														</td>
														<td>
															<input style="width: 110px" name="carColor" id="carColor" disabled="disabled"
																class="easyui-combobox"
																data-options="
																	url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
																	multiple:false,
																	valueField:'id',  
																	textField:'text'
																	" />
														</td>
														<td>
															VIN码：
														</td>
														<td>
															<input type="text" name="vinCode" id="vinCode" readonly="readonly" style="color: red; width: 150px" maxlength="17"/>
														</td>
													

														<td>
															<a href="javascript:void(0);" class="easyui-linkbutton"
																style="width: 90px" onclick="distribution(1)";>车辆分配</a>
														</td>
														<td>
															<a href="javascript:void(0);" class="easyui-linkbutton"
																style="width: 90px" onclick="cancledistribution(2);">取消分配</a>

														</td>
													</tr>
													<tr>
													<td>
															预付：
														</td>
														<td>
															<input type="text" name="paymentMoney" readonly="readonly" />
														</td>
															
														<td>
															销价：
														</td>
														<td>
															<input type="text" name="sellingprice" readonly="readonly" />
														</td>
														
														<td>
															客户要求：
														</td>
														<td>
															<input type="text" name="customOpinion" readonly="readonly"/>
														</td>
														<td>
															备注：
														</td>
														<td>
															<input type="text" name="remark"  style="width: 150px" readonly="readonly"/>
														</td>
														<td>
															<input type="hidden" id="reserveId" name="reserveId" />
															<input type="hidden" id="carid" name="carId" />
															<input type="hidden" id="reCode" name="reserveCode" />
														</td>
													
													</tr>
												</table>
												</fieldset>
											
										</form>
										<form id="Cars" method="post">
											
												<table bgcolor="gray ">
													<tr>
														<td>
														品牌：
													</td>
														<td><input type="text" id="queryBrand"	name="carBrand"  style="width:140px;" class="easyui-combobox" data-options="
														url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
														valueField:'id',   
											    		textField:'text',
											    		mode:'remote',			    		
											    		validType:'isSelected[\'#queryBrand\']',
											    		invalidMessage : '请从下拉框中选择车辆品牌',
											    		onSelect: function(rec){  
											    			$(this).combobox('textbox').bind('keyup', function (){
											    				if($('#queryBrand').combobox('getValue') == '' || $('#queryBrand').combobox('getValue') != $('#queryBrand').combobox('getText')){
											    					$('#queryModel').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
											    				}
											    			});
												            $('#queryModel').combobox('clear');
												            $('#queryModel').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
												        } "
												        />
												       
												        </td>
													
														<td>
															类型：
														</td>
														<td>
															<input type="text" id="queryModel" name="carModel"
																class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
																editable:false,
																valueField:'id',   
																textField:'text',
																mode : 'remote',
																validType:'isSelected[\'#queryModel\']',
																invalidMessage : '请从下拉框中选择车辆型号'" />
														</td>
														<td>
															颜色：
														</td>
														<td>
															<input style="width: 110px" name="carColor" id="carColor"
																class="easyui-combobox"
																data-options="
														url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR %>',
														multiple:false,
														valueField:'id',  
														textField:'text'
														" />
														</td>
														<td  width="160">
															VIN码：
														</td>
														<td>
															<input type="text" name="carVinNumber" id="vinCodse" class="easyui-validatebox" data-options="validType:'fixedLength[17]',invalidMessage:'VIN号为17位'"  style="width: 140px"  maxlength="17"/>
														</td>
													

														<td colspan="5" align="right">
															<a href="javascript:void(0);" class="easyui-linkbutton"
																style="width: 110px" onclick="queryCar();">定位查找</a>
														</td>
													</tr>
												</table>
											
										</form>
										
							</div>
							<div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						   
					    </div>
							<div id="mingxi" region="center" style="background: #eee;"
								border="false">
								<table id="carList" name="carList"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
