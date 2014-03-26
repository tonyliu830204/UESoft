<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!-- 客户档案档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
$("#xsCustomName").validatebox({required:true});
	$('#xsCustomName').validatebox('validate');
	$("#customCredentials").validatebox({required:true});
	$('#customCredentials').validatebox('validate');
	$("#xsCustomTelephone").validatebox({required:true});
	$('#xsCustomTelephone').validatebox('validate');
	$("#xsCustomAddress").validatebox({required:true});
	$('#xsCustomAddress').validatebox('validate');
	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		if(dataLength<10){
		    dataLength="0"+dataLength;
		}
		document.getElementById(id2).innerHTML=dataLength;
	}
	//判断客户是否存在
	function isExistsCustom(){
		$.ajax({
					type : 'post',
					dataType : 'json',
					url: 'customInfoAction!isExtisCustomCard.action?customId='+$('#customId').val(),
					data : "xsCustomCredentials="+$('#customCredentials').val(),
					success : function(r) {
						if (r.success) {
						 if(r.obj==true){
							alertMsg("该客户身份证号已存在！请查看是否输入正确！","warning");
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
<div id="form_dialog_car_sell_manage_id" >
	<form id="jBxx" style="margin-top: 15px">
		<input type="hidden"  id="customId" name="customId">
		<input type="hidden" name="xsCustomCode">
		<input type="hidden" id="enterpriseId" name="enterpriseId"/>
		
		<fieldset>
			<legend>客户基本信息</legend>
			<table style="text-align: right"  >
				<tr>
						<td >客户名:</td>
						<td  colspan="3"><input type="text"  id="xsCustomName" name="xsCustomName" style="width:283px;" class="easyui-validatebox"
						 data-options="editable:false,required:true"   maxlength="20"/></td>
						 <td >性别:</td>
						<td><input  id="custom_sex" name="xsCustomSex"   class="easyui-combobox"  style="width:110px;"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SEX%>',
						required:true,
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_sex\']',
						invalidMessage : '请从下拉框中选择性别'" /></td>
						<td >出生年月:</td>
						<td><input type="text" name="xsCustomBirthday" class="Wdate" onfocus="WdatePicker({});" style="width:110px;"/></td>
					</tr>
					<tr>
						<td >身份证号码:(<span id="customIdenLength" style="color:#ff3318;">00</span>):</td>
						<td  colspan="3"><input type="text" id="customCredentials" maxlength="18" style="width:283px;"
						 name="xsCustomCredentials"  onblur="isExistsCustom();" class="easyui-validatebox" data-options="required:true,missingMessage:'证件号为必填项',validType:'idcard'"  
						 onkeyup="customArchivesKeyUp('customCredentials','customIdenLength');"/></td>	
						
						<td >所在区域:</td>
						<td><input type="text" name="xsCustomArea" id="custom_AreaId"    style="width:110px;" 
							class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBase.action?baseKey=<%=Contstants.BASE_SELL.BASE_AREA%>',   
							required:true,
							valueField:'id',   
							textField:'text',
							mode : 'remote',
							validType:'isSelected[\'#custom_AreaId\']',
							invalidMessage : '请从下拉框中选择所在区域',
							onSelect: function(rec){  
								$(this).combobox('textbox').bind('keyup', function (){
									if($('#custom_AreaId').combobox('getValue') == '' || $('#custom_AreaId').combobox('getValue') != $('#custom_AreaId').combobox('getText')){
										$('#custom_AreaId').combobox('reload', 'baseTagAction!findBase.action?baseKey=<%=Contstants.BASE_SELL.BASE_AREA%>');
									}
								}
									
								);
						  $('#zip').val(rec.dataKey);
						  
					       } "

				          /></td> 
					  	<td >固定电话:</td>
						<td><input type="text" name="xsCustomPhone"    class="easyui-validatebox" style="width:110px;"
						data-options="validType:'phone',editable:false"  maxlength="12" /></td>
					</tr>
					<tr>
						 <td >手机:</td>
						<td><input type="text" id="xsCustomTelephone"  name="xsCustomTelephone"   class="easyui-validatebox" style="width:120px;"
						 data-options="validType:'mobile',editable:false,required:true"  maxlength="11"/></td>
						  <td>联系人:</td>
					  	<td><input type="text" id="xsContactsPerson" name="xsContactsPerson" style="width:110px;"   class="easyui-validatebox"  maxlength="20"/></td>
					  	<td>联系人手机:</td>
					  	<td><input type="text" id="xsContactsPhone" name="xsContactsPhone" style="width:110px;"   data-options="validType:'mobile'"  class="easyui-validatebox"  maxlength="20"/></td>	
						<td >客户性质:</td>
						<td><input type="text" name="xsCustomProperty" id="custom_nature" style="width:110px;"  class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CUSTOMNATURE%>',   
							required:true,
							valueField:'id',   
							textField:'text',
							mode : 'remote',
							validType:'isSelected[\'#custom_nature\']',
							invalidMessage : '请从下拉框客户性质'" /></td>	
						
					</tr>
					<tr>
							<td >地址:</td>
							<td colspan="3"><input type="text" id="xsCustomAddress" name="xsCustomAddress"   style="width:283px;" class="easyui-validatebox"
							data-options="editable:false,required:true"   maxlength="50"/></td>
						   <td >潜在程度:</td>
						    <td><input type="text" name="xsCustomHideLevel"  id="custom_levaId" style="width:110px;"  class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/customLevaAction!findAllLeva.action',
						    required:true,   
							valueField:'id',   
							textField:'text',
							mode : 'remote',
							validType:'isSelected[\'#custom_levaId\']',
							invalidMessage : '请从下拉框中选择潜在等级'" /></td>	 
							<td>自编号:</td>
							<td><input type="text"  name="xsCustomNumber" maxlength="20" style="width:110px;"  class="easyui-validatebox"  data-options="validType:'character'" maxlength="20"  /></td>  
					</tr>
					<tr>
							
							 <td >邮编:</td>
					        <td><input type="text" name="xsCustomZipcode" id="zip"  readonly="readonly" data-options="editable:false,validType:'zip'"  maxlength="10"  style="width:120px;background-color:#c0d8d8;" /></td>
					</tr>
			</table>
			</fieldset>
		<fieldset>
			<legend>其他信息</legend>
			<table align="center" >
				<tr>
					<td>所在单位:</td>
					<td colspan="3"><input type="text" name="xsCustomCompany"  style="width:286px "   maxlength="50" /></td>
					<td>教育程度:</td>
					<td><input type="text" name="xsCustomDiploma" id="custom_diploma" style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_EDUCATIONAL%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_diploma\']',
						invalidMessage : '请从下拉框中选择教育程度' " /></td>	
				   <td>行业:</td>
					<td><input type="text"  name="xsCustomTrade" id="custom_trade"  style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_TRADECLASSIFY%>',   
					valueField:'id',   
					textField:'text',
					mode : 'remote',
					validType:'isSelected[\'#custom_trade\']',
					invalidMessage : '请从下拉框中选择行业'" /></td>	
				</tr>
				<tr>
					<td>收入情况:</td>
					<td><input type="text" name="xsCustomIncome" id="custom_incomId" style="width:110px;"    class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_INCOME%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_incomId\']',
						invalidMessage : '请从下拉框中选择收入情况'" /></td>
					<td>业务员:</td>
					<td><input type="text" id="stf_id" name="stfId" style="width:110px;"    class="easyui-combobox"  
					data-options="
						url : '${pageContext.request.contextPath}/basStuffClassAction!findSellOperationPerson.action',  
						required:true,
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#stf_id\']',
						invalidMessage : '请从下拉框中选择业务员'"  /></td>
					<td>成交状态:</td>
					<td><input type="text" name="xsCustomDeal" id="custom_DealId"  style="width:110px;" disabled="disabled"  class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_DEALSTATE%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_DealId\']',
						invalidMessage : '请从下拉框中选择成交状态',
						onLoadSuccess:function(){
							$.ajax({
							   type: 'post',
							   dataType: 'json',
							   url: 'baseTagAction!getDataByChildDataKey.action',
							   data: 'pdataKey=<%=Contstants.BASE_SELL.BASE_DEALSTATE %>&dataKey=<%=Contstants.BASE_SELL.SS %>',
							   success: function(r){
							   		$('#custom_DealId').combobox('select',r);
							   }
							});
						}
						"
						 /></td>	
				     <td>来源:</td>
					<td><input type="text"  name="xsCustomSource" id="custom_sourceId" style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SOURCE%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_sourceId\']',
						invalidMessage : '请从下拉框中选择客户来源'" /></td>	
				</tr>
				<tr>
					<td>代码证:</td>
					<td><input type="text" name="xsCustomCodeCard"  style="width:110px;"    data-options="editable:false" maxlength="20"   /></td>
					<td>跟踪:</td>							
					<td><input  id="custom_after" name="xsCustomAfter"  style="width:110px;"    class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.PAYMENTSTATE%>',
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_after\']',
						invalidMessage : '请从下拉框中选择'" /></td>	
					<td>用途:</td>
					<td><input type="text" name="xsCustomApplication" id="custom_applicationId"  style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARPURPOSE%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_applicationId\']',
						invalidMessage : '请从下拉框中选择车辆用途'" /></td>		
					<td>对比车型:</td>
					<td><input type="text" name="xsCustomContrastModel" id="custom_contrastModelId"  style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CONTRASTMODEL%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_contrastModelId\']',
						invalidMessage : '请从下拉框中选择对比车型'" /></td>			
				</tr>
				<tr>
					<td > 建档日期:</td>
					<td><input type="text" id="xsCustomInputdata" name="xsCustomInputdata" 
					class="Wdate"    onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"   disabled="disabled"  style="width:110px;" /></td>
					<td>其它分类:</td>
					<td ><input type="text" name="xsCustomOtherType" id="custom_otherType"  style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_OTHERCLASSIFY%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_otherType\']',
						invalidMessage : '请从下拉框中选择其它分类'" /></td>
					<td>选择理由:</td>
					<td><input type="text" name="xsCustomReason" id="custom_reason"  style="width:110px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CHOICECAUSE%>',   
						valueField:'id',   
						textField:'text',
						mode : 'remote',
						validType:'isSelected[\'#custom_reason\']',
						invalidMessage : '请从下拉框中选择选择理由'" /></td>	
				</tr>
				<tr>
					<td>其他:</td>
					<td colspan="8"><input type="text" name="xsCustomOther"  style="width:630px;height: 50px "  class="easyui-validatebox" data-options="validType:'character'"   /></td>			
				</tr>
			</table>
			</fieldset>
	</form>
</div>