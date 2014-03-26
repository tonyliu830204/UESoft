<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String flag = request.getParameter("flag"); %>
<!-- 新增车档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/hz2py.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/carArchives/addCarArchives.js"></script>
<form id="carArchivesAddForm">
	<table width="100%">
		<tr>
			<td>车辆编号:</td>
			<td><input type="text" name="carId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>车辆牌照:</td>
			<td><input type="text" id="carLicenseview" name="carLicense" class="easyui-validatebox" maxlength="20" 
				data-options="required:true,missingMessage:'车辆牌照为必填项'" onkeyup="$(this).val($(this).val().toUpperCase());"/></td>
			<td>VIN号(<span id="vinLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="addcarVin" name="carVin" class="easyui-validatebox"  onblur="isExistsVin();"
			data-options="required:true,missingMessage:'VIN号为必填项',validType:'fixedLength[17]',invalidMessage:'VIN号为17位'" maxlength="17"
			 onkeyup="$(this).val($(this).val().toUpperCase());dynamicShowLength('addcarVin','vinLength');" /></td>
			<td>发动机号(<span id="carMotorIdLength" style="color:#ff3300;">0</span>):</td>
			<td>
				<input type="text" id="addcarMotorId" name="carMotorId" maxlength="20"
				 onkeyup="dynamicShowLength('addcarMotorId','carMotorIdLength');"/>
			</td>
		</tr>
		<tr>
			<td>车辆品牌:</td>
			<td><input type="text" id="carArchives_add_cbrdId" name="cbrdId" class="easyui-combobox" onkeyup="alert('ss');" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findCarBrand.action',
			valueField:'id',   
    		textField:'text',
    		mode:'remote',
    		required:true,
    		missingMessage:'车辆品牌为必选项',
    		validType:'isSelected[\'#carArchives_add_cbrdId\']',
    		invalidMessage : '请从下拉框中选择车辆品牌',
    		onSelect: function(rec){
    			var cbrdId=-1;
    			if($('#carArchives_add_cbrdId').combobox('getValue') == '' || $('#carArchives_add_cbrdId').combobox('getValue') != $('#carArchives_add_cbrdId').combobox('getText')){
   					cbrdId=$('#carArchives_add_cbrdId').combobox('getValue');	
   				}
	            $('#carArchives_add_ctypeId').combobox('clear');
	            $('#carArchives_add_carCstlId').combobox('clear');   
	            $('#carArchives_add_ctypeId').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarType.action?cbrdId=' + cbrdId);  
	        },onChange: function(newValue,oldValue){  
	        	var cbrdId=-1;
	        	if($('#carArchives_add_cbrdId').combobox('getValue') == '' || $('#carArchives_add_cbrdId').combobox('getValue') != $('#carArchives_add_cbrdId').combobox('getText')){
   					cbrdId=$('#carArchives_add_cbrdId').combobox('getValue');
   				}
	            $('#carArchives_add_ctypeId').combobox('clear');
	            $('#carArchives_add_carCstlId').combobox('clear');   
	            $('#carArchives_add_ctypeId').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarType.action?cbrdId=' + cbrdId);  
	        } "/></td>
			<td>车辆型号:</td>
			<td><input type="text" id="carArchives_add_ctypeId" name="ctypeId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/basCarArchivesAction!findCarType.action',
			valueField:'id',   
    		textField:'text',
    		mode:'remote',
    		required:true,
    		missingMessage:'车辆型号为必选项',
    		validType:'isSelected[\'#carArchives_add_ctypeId\']',
    		invalidMessage : '请从下拉框中选择车辆型号',
    		onSelect:function(rec){
    			var carTypeId=-1;
    				if($('#carArchives_add_ctypeId').combobox('getValue') == '' || $('#carArchives_add_ctypeId').combobox('getValue') != $('#carArchives_add_ctypeId').combobox('getText')){
    					carTypeId=$('#carArchives_add_ctypeId').combobox('getValue');
    				}
	            $('#carArchives_add_carCstlId').combobox('clear');  
	            $('#carArchives_add_carCstlId').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action?ctypeId=' + carTypeId);  
	        },onSelect:function(newValue,oldValue){
    			var carTypeId=-1;
    				if($('#carArchives_add_ctypeId').combobox('getValue') == '' || $('#carArchives_add_ctypeId').combobox('getValue') != $('#carArchives_add_ctypeId').combobox('getText')){
    					carTypeId=$('#carArchives_add_ctypeId').combobox('getValue');
    				}
	            $('#carArchives_add_carCstlId').combobox('clear');  
	            $('#carArchives_add_carCstlId').combobox('reload', '${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action?ctypeId=' + carTypeId);  
	        }  "/></td>
			<td>车辆款式:</td>
			<td><input type="text" id="carArchives_add_carCstlId" name="carCstlName" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findCarStyle.action',
			valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#carArchives_add_carCstlId\']',
    		invalidMessage : '请从下拉框中选择车辆款式',
    		mode:'remote' "/></td>
			<td>车身颜色:</td>
			<td><input type="text" id="carArchives_add_color" name="color" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findCarColor.action',
			valueField:'id',   
    		textField:'text',
    		required:true,
    		missingMessage:'车身颜色为必选项',
    		validType:'isSelected[\'#carArchives_add_color\']',
    		invalidMessage : '请从下拉框中选择车身颜色',
    		mode:'remote' "/></td>
		</tr>
		<tr>
			<td>经销商:</td>
			<td><input type="text" id="carArchives_add_slsId" name="slsId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findCarSellers.action',
			valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#carArchives_add_slsId\']',
    		invalidMessage : '请从下拉框中选择经销商',
    		mode:'remote' "/></td>
			<td>购车日期:</td>
			<td><input type="text" name="carBuyDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>领证日期:</td>
			<td><input type="text" name="carLicenseDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>驾驶证号(<span id="carRealationLisenceLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="addcarRealationLisence" name="carRealationLisence" maxlength="20" 
			onkeyup="dynamicShowLength('addcarRealationLisence','carRealationLisenceLength');"/></td>
		</tr>
		<tr>
			<td>准驾车型:</td>
			<td><input type="text" id="carArchives_add_allowCarTypeId" name="allowCarTypeId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findAllowCarType.action',
			valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#carArchives_add_allowCarTypeId\']',
    		invalidMessage : '请从下拉框中选择准驾车型',  
    		mode:'remote' "/></td>
			<td>交强险到期:</td>
			<td><input type="text" name="carBasinsuranceDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>商业险到期:</td>
			<td><input type="text" name="carBusinsuranceDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>保险公司:</td>
			<td><input type="text" id="carArchives_add_relcampId" name="relcampId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath }/frtOptionsAction!findInsuranceCompany.action',
			valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#carArchives_add_relcampId\']',
    		invalidMessage : '请从下拉框中选择保险公司',
    		mode:'remote' "/></td>
		</tr>
		<tr>
			<td>年检到期:</td>
			<td><input type="text" name="carAnnualDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>年审到期:</td>
			<td><input type="text" name="carExaminedDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>二维到期:</td>
			<td><input type="text" name="twoDimensionDate" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>保养间隔:</td>
			<td><input type="text" name="carMaintInterva" class="easyui-numberbox"
			 data-options="requird:false,min:0,max:99999999,validType:'length[0,8]'"/></td>
		</tr>
		<tr>
			<td>建档日期:</td>
			<td><input type="text" name="createDate" class="easyui-datetimebox" value="{now}" data-options="disabled:true"/></td>
			<td>档案号(<span id="carRecordLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="addcarRecord" name="carRecord" maxlength="20" 
			onkeyup="dynamicShowLength('addcarRecord','carRecordLength');"/></td>
			<td>联系电话:</td>
			<td><input type="text" id="carArchives_add_customTel1" name="customTel1" maxlength="20"
				 readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>固定电话:</td>
			<td><input type="text" id="carArchives_add_customTel2" name="customTel2" 
				 readonly="readonly" style="background-color: #c0d8d8;"/></td>
		</tr>
		<tr>
			<td>性别:</td>
			<td><input type="text" style="background-color: #c0d8d8;" id="carArchives_add_carRealationSex" name="carRealationSex" class="easyui-combobox" data-options="
			disabled:true,
			url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.SEXTYPE.SEX %>',
			valueField:'id',
			textField:'text',
			required:false,
    		missingMessage:'性别为必选项',
			validType:'isSelected[\'#carArchives_add_carRealationSex\']',
    		invalidMessage : '请从下拉框中选择性别'
    		 "/></td>
			<td>地址:</td>
			<td><input type="text" id="carArchives_add_customAddr" name="customAddr" maxlength="100"
				 readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>出生年月:</td>
			<td><input type="text" id="carArchives_add_carBirthday" style="background-color: #c0d8d8;" name="carBirthday" readonly="readonly" class="Wdate" onfocus="WdatePicker({});"/></td>
			<td>身份证号码:</td>
			<td><input type="text" id="carArchives_add_carRealationIdentifyCd" style="background-color: #c0d8d8;" maxlength="18"
			 name="carRealationIdentifyCd" class="easyui-validatebox" data-options="disabled:true"/></td>
		</tr>
		<tr>
			 <td>客户编号:</td>
			<td><input type="text" id="carArchives_add_customId_view" name="customId" maxlength="20"
				  readonly="readonly" style="background-color: #c0d8d8;"/></td>
			<td>客户名称:</td>
			<td colspan="5"><input type="text" id="carArchives_add_customId"
				 name="customName"  style="width:520px;" maxlength="50"/>
		</tr>
		<tr>
			 <td>动力系统:</td>
			<td>
				<input type="text" style="background-color: #c0d8d8;" id="carArchives_add_carClass" name="carClass" class="easyui-combobox" data-options="
				required:true,
				disabled:false,
				url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.CARCLASS_TAG.CARCLASSKEY %>',
				valueField:'id',
				textField:'text',
				mode:'remote',
	    		missingMessage:'动力系统为必选项',
	    		validType:'isSelected[\'#carArchives_add_carClass\']',
	    		invalidMessage : '请从下拉框中选择动力系统'
	    		 "/>
			</td>
			<td>邮政编码:</td>
			<td>
				<input type="text" id="carArchives_add_carPostalCode"
					 maxlength="6"	name="carPostalCode" class="easyui-validatebox"
			 		data-options="validType:'zip'"/>
			</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">附加信息:</td>
			<td colspan="7" style="text-align: left;"><textarea id="carArchives_add_carRemark" name="carRemark" 
			onkeyup="showMaxLength('carArchives_add_carRemark',500);"
			rows="" cols="" style="width: 718px; height: 50px;"></textarea></td>
		</tr>
		<tr>
			<td style="vertical-align: top;">工单备注:</td>
			<td colspan="7" style="text-align: left;"><textarea id="carArchives_add_receptionRemark" name="receptionRemark"  
			onkeyup="showMaxLength('carArchives_add_receptionRemark',500);"
			rows="" cols="" style="width: 718px; height: 50px;"></textarea></td>
			
		</tr>
	</table>
</form>