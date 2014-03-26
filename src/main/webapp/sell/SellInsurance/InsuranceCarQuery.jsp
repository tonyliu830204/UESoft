<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆保险（首保）查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"	src="${pageContext.request.contextPath}/sell/SellInsurance/InsuranceCarQuery.js"></script>
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
			<privilege:enable code="INSURANCEC_SEARCH">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
			</privilege:enable>
			<privilege:enable code="INSURANCEC_CLEAR">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
			</privilege:enable>
			<privilege:enable code="INSURANCEC_PRINT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true"  id="_print" onclick="_config();">打印</a>
			</privilege:enable>
			<privilege:enable code="INSURANCEC_EXPORT">
			<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
		   </privilege:enable>
		</div>
		<div data-options="region:'center',border:false"
			style="background: #eee;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',title:'查询条件',border:false"
					style="overflow: hidden; background: #eee; height: 130px;">
					<form id="queryForm" name="carModelQueryForm" method="post"
						fit="true">
						 <fieldset>
						<table>
							<tr>
								
								<td>
								VIN码:
								</td>
								<td>
									<input type="text" name="vin" style="width: 150px">
															
								</td>
								<td>
									客户名称:
								</td>
								<td>
									<input type="text" name="customName" style="width: 150px">
								</td>
								<td>
							
								车辆牌照:
								</td>
								<td>
									<input type="text" name="carLicensePlate"
										 style="width: 120px"/>
								</td>
								<td>
								投保日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'zhProjectDate2\',{d:-1})}'})" name="insurerStartDate" id="zhProjectDate1" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'zhProjectDate1\',{d:1})}'})" name="insurerEndDate" id="zhProjectDate2" style="width: 110px;"/></td>
								
						
								</tr>
								<tr>
							 
					
								<td>
									品牌:
								</td>
									<td><input type="text" id="car_Brand_id" name="carBrandId" style="width:150px;" class="easyui-combobox" data-options="
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
									<td>发动机号:</td>
									<td><input name="carMotorNumber" style="width: 120px"/></td>
									<td>
									销售日期:
								</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'insurDate2\',{d:-1})}'})" name="carSellData" id="insurDate1" style="width: 110px;"/> 至 
									<input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'insurDate1\',{d:1})}'})" name="carSellData2" id="insurDate2" style="width: 110px;"/></td>
												
								
									
									
								</tr>
								<tr>
							
					
							<td>车辆编号:</td>
							<td><input name="carCode" style="width: 150px"/></td>
							<td>
								业务员：
							</td>
							<td>
							<input type="text" id="pp" name="person" class="easyui-combobox" 
								 style="background-color:#c0d8d8;width: 150px"
								data-options="url:'${pageContext.request.contextPath}/sellUtilAction!findUsers.action',  
									valueField:'id',   
									textField:'name',
									mode : 'remote',
									validType:'isSelected[\'#pp\']',
									invalidMessage : '请从下拉框中选择经办人'"  />
								

											</td>
							
								</tr>
						</table>
						</fieldset>
					</form>
				</div><blockquote><br>
				</blockquote>
				<!--<div region="west" split="true" title="车辆品牌型号" style="width:230px;">
				 <table id="carBrand" name="carBrand"></table> 
				</div>
				--><div  id="acc" data-options="region:'center',border:false"
					style="background: #eee;">
					 <table id="account" name="account"></table> 
				</div>
				
				<div data-options="region:'south',border:false"style="background: #eee;height: 170px">
					<div id="tab_id" class="easyui-tabs"  border="false"  style="background:#eee;"  fit="true" >
				   	    <!-- 基本信息 -->
				   	   	<div title="基本信息" > 
				<form id="sumqu">
				<table>
				<tr>
					<td style="width:80px;">车辆编号:</td><td ><input name="carCode" id="carCode" readonly="readonly"></td>
					<td>车品牌:</td><td ><input name="carBrand" id="carBrand" readonly="readonly"></td>
					<td >车型号:</td><td ><input name="carmodelN" id="carmodelN"readonly="readonly"></td>
					<td >车身颜色:</td><td ><input name="carColorName" id="carColorName"readonly="readonly"></td>
					
					<td >保险公司:</td><td colspan="3"><input name="insurerName" id="insurerName"readonly="readonly" style="width: 320px"></td>
				</tr>
					<tr>
					<td style="width:80px;">VIN号:</td><td ><input name="vin" id="vin" readonly="readonly"></td>
					<td>厂牌名称:</td><td ><input name="carLicenseName"  readonly="readonly"></td>
					<td >发动机号:</td><td ><input name="carMotorNumber" readonly="readonly"></td>
					<td >车牌照:</td><td ><input name="" readonly="readonly"></td>
					<td >合格证号:</td><td ><input name="" readonly="readonly"></td>
					<td >客户姓名:</td><td ><input name="customName" readonly="readonly" style="width: 150px"></td>
				</tr>
				<tr>
					<td style="width:80px;">保险单号:</td><td ><input name="insurancePolicy"  readonly="readonly"></td>
					<td>首保日期:</td><td ><input name="fristInsuranceData"  readonly="readonly"></td>
					<td >销售日期:</td><td ><input name="carSellData" readonly="readonly"></td>
					<td >销售价:</td><td ><input name="sellMoney" readonly="readonly"></td>
					<td >客户地址:</td><td colspan="3" ><input name="customAddress" readonly="readonly" style="width:320px"></td>
				</tr>
				<tr>
					<td style="width:80px;">投保日期:</td><td ><input name="insurerStartDate" readonly="readonly"></td>
					<td>投保到期日期:</td><td ><input name="insurerEndDate"  readonly="readonly"></td>
					<td >保险员:</td><td ><input name="insuranceAgent" readonly="readonly"></td>
					<td >手机:</td><td ><input name="customTelephone" readonly="readonly"></td>
					<td >电话:</td><td ><input name="customPhone" readonly="readonly"></td>
				</tr>
				</table>
				</form>
				</div>
				
				
				<div title="其他信息" style="background:#eee;">
				<form id="qt">
				<table>
					<tr>
						<td style="width:80px;">业务员:</td><td ><input name="stfName" style="width: 150px" readonly="readonly"></td>
						<td>备注:</td><td rowspan="4"><textarea name="remark"  style="width: 260px; height: 90px;resize:none;"></textarea>
						</td>
					
					</tr>
					<tr>
						<td >入库日期:</td><td ><input name="instorehouseDate" style="width: 150px" readonly="readonly"></td>
					</tr>
					<tr>
						<td >生产日期:</td><td ><input name="carMakeData" style="width: 150px" readonly="readonly"></td>
					</tr>
					<tr>
						<td >上报日期:</td><td ><input name="audit_date" style="width: 150px" readonly="readonly"></td>
										
					</tr>
				</table>
				</form>
						</div>
						</div>
						</div>
			</div>
		</div>
	</body>
</html>
