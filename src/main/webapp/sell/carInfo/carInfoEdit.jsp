<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<!-- 新增车档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">

	$("#carVinNumber").validatebox({required:true});
	$('#carVinNumber').validatebox('validate');
	$("#carMotorNumber").validatebox({required:true});
	$('#carMotorNumber').validatebox('validate');
	$("#carOcn").validatebox({required:true});
	$('#carOcn').validatebox('validate');

	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
	//判断Vin号是否存在
	function isExistsVin(){
		$.ajax({
					type : 'post',
					dataType : 'json',
					url: 'carInfoAction!isUseVin.action',
					data : "carId="+$('#carId').val()+"&&carVinNumber="+$('#carVinNumber').val(),
					success : function(r) {
						if (r.success) {
						 if(r.obj==true){
							alertMsg("VIN号已存在！请重新输入！","warning");
							}
						}
					},
					error : function (r){
					   if(r.readyState == '0' && r.status == '0'){
						   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
					   }
				   	}
				});
	}
	
</script>
<form id="carInfoEditForm">
	<input type="hidden" id="carId" name="carId"/>
	<input type="hidden"  name="carCode"/>
	<input type="hidden"  name="carSellState"/>
	<input type="hidden"  name="carDistributState"/>
	<input type="hidden"  name="carAssembleData"/>
	<input type="hidden"  name="fixStatus"/>
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<table align="center" style="margin-top: 20px">
		<tr>
			<td width="60px">车辆品牌:</td>
			<td><input type="text" id="carArchives_add_cbrdId" name="carBrand" class="easyui-combobox" style="width:140px;" data-options="
			url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
			valueField:'id',   
    		textField:'text',
    		mode:'remote',
    		required:true,
    		missingMessage:'车辆品牌为必选项',
    		validType:'isSelected[\'#carArchives_add_cbrdId\']',
    		invalidMessage : '请从下拉框中选择车辆品牌',
    		onSelect: function(rec){ 
    			$(this).combobox('textbox').bind('keyup', function (){
    				if($('#carArchives_add_cbrdId').combobox('getValue') == '' || $('#carArchives_add_cbrdId').combobox('getValue') != $('#carArchives_add_cbrdId').combobox('getText')){
    					$('#carArchives_add_ctypeId').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id);
    				}
    			});
	            $('#carArchives_add_ctypeId').combobox('clear');
	            $('#carArchives_add_ctypeId').combobox('reload', 'carModelAction!findCarModelByBrand.action?isCancle=true&cbrdId=' + rec.id)  
	        } "/></td>
			<td width="75px">车辆型号:</td>
			<td><input type="text" id="carArchives_add_ctypeId" name="carModelId" class="easyui-combobox" style="width:140px;"  data-options="
			url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action?isCancle=true',
			valueField:'id',   
    		textField:'text',
    		mode:'remote',
    		required:true,
    		missingMessage:'车辆型号为必选项',
    		validType:'isSelected[\'#carArchives_add_ctypeId\']',
    		invalidMessage : '请从下拉框中选择车辆型号',
    		onSelect:function(rec){
				$.ajax({
					type : 'POST',
				    dataType : 'json',
					url : 'carBookAction!findCarTypeSellPriceOrControlPrice.action',
					data : 'flag=false&carModerId='+rec.id, 
					success : function(rs){
						if(rs.success){
							$('#sellingprice').numberbox('setValue',rs.obj);
							$('#sellingprice').numberbox('validate');
						}else{
							alertMsg(rs);
						}
					}
			   	});
			} "/>
					       
					       <input type="hidden" name="carPrice" id="sellingprice" class="easyui-numberbox" >
					       </td>
					
				<td width="75px">VIN号码:(<span id="vinNumberLength" style="color: rgb(255, 51, 0);">0</span>):</td>
				<td><input type="text" id="carVinNumber" maxlength="17" name="carVinNumber"  onblur="isExistsVin();" style="width:140px;" class="easyui-validatebox" data-options="required:true,missingMessage:'VIN号码为必填项' ,validType:'vinvalidator'" onkeyup="customArchivesKeyUp('carVinNumber','vinNumberLength');"></td>
	</tr>
	<tr>
				<td>发动机号:</td>
				<td><input type="text" id="carMotorNumber" name="carMotorNumber" style="width:140px;" maxlength="20" class="easyui-validatebox" data-options="required:true,missingMessage:'发动机号为必填项',validType:'novalidator'"></td>
				<td >OCN码:</td>
				<td><input type="text" id="carOcn" name="carOcn" style="width:140px;" maxlength="20" class="easyui-validatebox" data-options="required:true,missingMessage:'OCN码为必填项',validType:'novalidator'"></td>
				<td >外观色:</td>
				<td><input type="text" name="carColor" id="car_colorId" style="width:140px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARCOLOR%>',   
					required:true,
					missingMessage:'外观色为必填项',
					valueField:'id',   
					textField:'text',
					mode : 'remote',
					validType:'isSelected[\'#car_colorId\']',
					invalidMessage : '请从下拉框中选择外观色' "></td>
	</tr>
	<tr>
					<td>内饰色:</td>
					<td><input type="text" id="car_interiorColor" name="carInteriorColor" style="width:140px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR%>',
					required:true,
					missingMessage:'外观色为必填项',
					valueField:'id',   
					textField:'text',
					validType:'isSelected[\'#car_interiorColor\']',
					invalidMessage : '请从下拉框中选择内饰色',
					mode:'remote' "></td>
					<td >车牌照:</td>
					<td><input type="text" name="carLicensePlate" style="width:140px;" maxlength="20"></td>
					<td >厂牌名称:</td>
					<td><input type="text" name="carLicenseName" style="width:140px;" maxlength="20"></td>
	</tr>
	
		<tr>		
					<td>生产日期:</td>
					<td><input type="text" id="carMakeData" name="carMakeData" class="Wdate" onfocus="WdatePicker({});" style="width:140px;"></td>
					<td >选装情况:</td>
					<td><input type="text" name="carOptional" class="easyui-numberbox" style="width:140px;" maxlength="11"></td>
					<td >钥匙号:</td>
					<td><input type="text" name="carUnlockingKeyNumber" style="width:140px;" maxlength="20"></td>
			</tr>
			<tr>	
					
					<td >代交寄车:</td>
					<td><input type="text" name="carType" id="car_type" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARTYPE%>',
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#car_type\']',
						invalidMessage : '请从下拉框中选择代交寄车'"></td>
					<td>产地:</td>
					<td><input type="text" name="carProductionAddress" style="width:140px;" class="easyui-validatebox"></td>
					<td>商检单:</td>
					<td><input type="text" name="carTradeCheckBill" style="width:140px;" class="easyui-validatebox"></td>
				
				</tr>
				<tr>							
					<td >规格:</td>
					<td><input type="text" name="norms" id="norms" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CARNORMS%>',
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						invalidMessage : '请从下拉框中选择规格'"></td>
					<td>限乘人数:</td>
					<td><input type="text" name="carRideLimitNumber" style="width:140px;" class="easyui-numberbox" ></td>
					<td >工具包:</td>
					<td><input type="text" name="toolCase" id="toolCase" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.TOOLCASE%>',
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						invalidMessage : '请从下拉框中选择工具包'"></td>
					</tr>
					<tr>
					<td >证明文件:</td>
					<td><input name="proveFile" style="width:140px;"/></td>
					<td >认证书:</td>
					<td><input name="rzBook" style="width:140px;"/></td>
					<td >脚垫:</td>
					<td><input type="text" name="footd" id="footd" class="easyui-combobox" style="width:140px;" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.FOOTD%>',
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						invalidMessage : '请从下拉框中选择脚垫'"></td>
					</tr>
					
					<tr>
					<td >拷车日期:</td>
					<td><input type="text" name="carCopyData" style="width:140px;" class="Wdate" onfocus="WdatePicker({});"></td>
					<td>预计下线日期:</td>
					<td><input type="text" name="carForecastData" style="width:140px;" class="Wdate" onfocus="WdatePicker({});"></td>
					<td>终检日期:</td>
					<td><input type="text" name="carEndCheckData" style="width:140px;" class="Wdate"  onfocus="WdatePicker({});"></td>				
				</tr>
				<tr>
					
					<td>首保日期:</td>
					<td><input type="text" name="carFristInsuranceData" style="width:140px;" class="Wdate"  readonly="readonly" disabled="disabled"  ></td>
				</tr>
	</table>
</form>