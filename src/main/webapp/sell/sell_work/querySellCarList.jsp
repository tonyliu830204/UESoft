<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>销售单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/selljs/querySellCarList.js"></script>
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
		    height: 400,
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
					style="overflow: hidden; background: #eee; height: 120px;">
					<form id="carQueryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
						<tr>
								<td width="70px">
									出库日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="retreat_dateStart" name="retreat_date"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreat_dateEnd\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="retreat_dateEnd" name="retreat_date2"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreat_dateStart\',{d:1})}'});" />
								</td>
								<td width="60px">
									客户姓名:
								</td>
								<td >
									<input type="text" name="xs_Custom_Name" style="width: 125px"/>
								</td>
								<td width="60px">

										品牌:
									</td>
										<td><input type="text" id="car_Brand_id" name="xs_Car_Brand" style="width:120px;" class="easyui-combobox" data-options="
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
									<td  width="60px">车型:</td>
										<td><input type="text" id="car_Model_id" name="queryCarModel" style="width:120px;"  class="easyui-combobox" 
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
										
										<td width="60px">
											颜色：
										</td>
										<td>
											<input style="width: 120px" name="xs_Car_Color" id="carColor" 
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
								<td width="70px">
									销售日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="xs_Car_Sel_Data" name="xs_Car_Sel_Data"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="xs_Car_Sel_Data2" name="xs_Car_Sel_Data2"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'});" />
								</td>
								<td width="60px">
									VIN号:
								</td>
								<td>
									<input type="text" id="car_Vin_Number" name="car_Vin_Number" style="width:125px;"/>
								</td>
								<td width="60px">
									分销商：
								</td>
								<td colspan="3">
									<input id="dName2" name="xs_Distributor_Name" 
										onkeypress=" if(event.keyCode==13) { adddis2(); return false;}"
										style="background-color: #c0d8d8;width: 295px" />
									<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"
										onclick="adddis2();" />
									<input name="xs_Distributor_Id" id="did2" type="hidden" />
								</td>
								
									<td width="60px">
											部门:</td>
									<td><input id="deptIdF" class="easyui-combobox" name="deptId" style="width:120px;" 
									data-options="   
											         valueField: 'id',   
											         textField: 'text',  
											         url: 'basPersonnelInformationSetAction!findAllDept.action',
											      	 mode : 'remote'"/>
											      	 </td>
										
								
							
							</tr>
							<tr>
								<td width="70px">
									开票日期:
								</td>
								<td style="text-align: right;" colspan="3">
									<input type="text" id="invoice_date" name="invoice_date"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'invoice_date2\',{d:-1})}'});" />
									&nbsp;&nbsp;至&nbsp;&nbsp;
									<input type="text" id="invoice_date2" name="invoice_date2"
										class="Wdate" style="width: 110px"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'invoice_date\',{d:1})}'});" />
								</td>
								<td width="60px">代交寄车:</td>
								<td><input type="text" name="xs_car_type" id="car_type" class="easyui-combobox" style="width:125px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARTYPE%>',
									valueField:'id',   
									textField:'text',
									mode : 'remote',
									validType:'isSelected[\'#car_type\']',
									invalidMessage : '请从下拉框中选择代交寄车'"></td>
							

								<td >客户性质:</td>
									<td><input type="text" name="xs_Custom_Property" id="custom_nature" style="width:120px;"  class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CUSTOMNATURE%>',   
									
									valueField:'id',   
									textField:'text',
									mode : 'remote',
									validType:'isSelected[\'#custom_nature\']',
									invalidMessage : '请从下拉框客户性质'" /></td>	

								<td>经办人:</td>
								<td><input type="text" id="stf_ids" name="stf_Id" style="width:120px;"    class="easyui-combobox" 
								data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',  
									valueField:'id',   
									textField:'text',
									mode : 'remote',
									validType:'isSelected[\'#stf_ids\']',
									invalidMessage : '请从下拉框中选择业务员'"  />
								</td>
						
								</tr>
						</table>
						</fieldset>
					</form>
				</div>
				<div id="allocateTree" data-options="region:'center',border:false"
					style="background: #eee;">
					<table id="sellCarListTree" name="sellCarListTree"></table>
				</div>
			</div>
		</div>
	</body>
</html>
