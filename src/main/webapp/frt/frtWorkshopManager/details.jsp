<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 车间管理-工单明细 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtWorkshopManager/details.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div style="overflow: hidden;background: #eee; height: 270px;" data-options="region:'north',title:'汇总信息',border:false">
		<form id="frtWorkshopManagerAddForm">
			<table width="1150" border="0">
   				<tr>
   					<td>工单号:</td>
   					<td><input type="text" name="receptionId" id="frtWorkshopManager_detail_receptionId" readonly="readonly"
   					 class="easyui-validatebox"	 style="width: 140px;background:#EBEBE4;"
   					data-options="
   					disabled:true,
   					required : true,
   					missingMessage : '工单号为必填项' "/></td>
   					<td>车牌照:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_carId" name="carId" class="easyui-combobox"
   					 style="width: 140px;#EBEBE4;" readonly="readonly"
   					data-options="
   					disabled:true,
   					required : true,
   					editable : false,
   					mode : 'remote',
   					missingMessage : '车牌照为必填项' ,
   					url : 'frtOptionsAction!findAllCarLicense.action',
   					valueField : 'id',
   					textField : 'text' "/></td>
   					<td>客户名称:</td>
   					<td colspan="5"><input type="text" id="frtWorkshopManager_detail_customId" name="customId"
   					 class="easyui-combobox" style="width:610px;#EBEBE4;" readonly="readonly"
					data-options="
					disabled:true,
					required : true,
					editable : false,
					url : 'frtOptionsAction!findAllCustom.action',
					mode:'remote',
					missingMessage:'客户编号为必填项',
					valueField:'id',  
					textField:'text' "/></td>
   					
   				</tr>
   				<tr>
   					<td>里程数:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_receptionDistance" name="receptionDistance" class="easyui-validatebox" 
   					 style="width: 140px;"  onblur="checkoutDistance('frtWorkshopManager_detail_receptionDistance','frtWorkshopManager_detail_carId');"
   					data-options="
   					required : true,validType:'integer',
   					missingMessage:'里程数为必填项' "/></td>
   					<td>保养区分:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_receptionMaintFlg" name="receptionMaintFlg" class="easyui-combobox"
   					 style="width: 140px;"
						data-options="
						required : true,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.MAINTAIN_TAG.MAINTAINKEY %>',
						missingMessage:'保养区分为必填项',
						validType:'isSelected[\'#frtWorkshopManager_detail_receptionMaintFlg\']',
						invalidMessage : '请从下拉框中选择保养区分',
						valueField:'id',  
						textField:'text' "/></td>
					<td>索赔厂商:</td>
   					<td colspan="3"><input type="text" id="frtWorkshopManager_detail_finComId" name="finComId" class="easyui-combobox"
   					 style="width: 400px;" readonly="readonly"
					data-options="
					url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimManufacturers.action',
					required : true,
					disabled : true,
					editable : false,
					mode : 'remote',
					missingMessage : '索赔厂商为必填项',  
					valueField:'id',  
					textField:'text' "/></td>
					<td>三包人员:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_receptionInsurPer" name="receptionInsurPer" class="easyui-combobox"
   					 style="width: 140px;"
						data-options="
						mode : 'remote',
						url : '${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseEgisAvailPerson.action',
						required : true,
						editable : true,
						missingMessage : '三包人员为必填项',  
						validType:'isSelected[\'#frtWorkshopManager_detail_receptionInsurPer\']',
						invalidMessage : '请从下拉框中选择三包人员',
						valueField:'id',  
						textField:'text' "/></td>
   				</tr>
   				<tr>	
   					<td>
   					计划完工时间:
   					</td>
   					<td>
   						<input type="text" id="frtWorkshopManager_detail_receptionEndTime"  name="receptionEndTime" 
   					class="easyui-datetimebox" style="width: 140px;"
					value="{now}"
					data-options="
					required : true,
					editable : false,
					missingMessage: '计划完工时间为必填' "  />
   					</td>
   					<td>
   					预计交车时间:
   					</td>
   					<td>
   						<input type="text" id="frtWorkshopManager_detail_expDelCarTime" name="expDelCarTime" 
   					class="easyui-datetimebox" style="width: 140px;"
					value="{now}"
					data-options="
					required : true,
					editable : false,
					missingMessage: '预计交车时间为必填' " />
   					</td>
   					<td>维修技师:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_receptionTechnician" name="receptionTechnician" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					mode : 'remote',
					url : '${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseMaintainPerson.action',
					required : true,
					editable : true,
					missingMessage : '维修技师为必填项', 
					validType:'isSelected[\'#frtWorkshopManager_detail_receptionTechnician\']',
					invalidMessage : '请从下拉框中选择维修技师', 
					valueField:'id',  
					textField:'text' "/></td>
					<td>贵重物品:</td>
    					<td>
    					<input type="text" id="frtWorkshopManager_detail_valuables" name="valuables" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						mode : 'remote',
						url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.VALUABLESRES_TAG.VALUABLESRESKEY %>',
						required : true,
						editable : true,
						missingMessage : '贵重物品为必填项',  
						validType:'isSelected[\'#frtWorkshopManager_detail_valuables\']',
						invalidMessage : '请从下拉框中选择贵重物品', 
						valueField:'id',  
						textField:'text' "/>
						</td>
					<td>预约编号:</td>
   					<td><input type="text" name="resvId" readonly="readonly"  style="width: 140px;"/></td>
   				</tr>
   				<tr>
   					<td>保险送修人:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_receptionRepPer" name="receptionRepPer" class="easyui-validatebox" 
   					 style="width: 140px;"
   					data-options="
   					required : false,
					missingMessage : '保险送修人为必填项' "/></td>
   					<td>托修人:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_propRepPer" name="propRepPer" class="easyui-validatebox" 
   					 style="width: 140px;"
   					data-options="
   					required : false,
					missingMessage : '托修人为必填项' "/></td>
   					<td>托修人手机:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_propPhone" name="propPhone" class="easyui-validatebox" 
   					 style="width: 140px;"
   					data-options="
   					required : false,
					missingMessage : '托修人手机为必填项',validType:'mobile' "/></td>
   					<td>托修人电话:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_propTel" name="propTel" class="easyui-validatebox" 
   					 style="width: 140px;"
   					data-options="
   					required : false,
					missingMessage : '托修人电话为必填项',validType:'phone' "/></td>
					<td>维修类别:</td>
   					<td><input type="text" id="frtWorkshopManager_detail_reptId" name="reptId" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					required : true,
					editable : true,
					mode : 'remote',
					url : 'frtOptionsAction!findAllReptype.action',
					missingMessage:'维修类别为必填项',
					validType:'isSelected[\'#frtWorkshopManager_detail_reptId\']',
					invalidMessage : '请从下拉框中选择维修类别', 
					valueField:'id',  
					textField:'text' "/></td>
					
   				</tr>
   				<tr>
   					<td>燃油情况:</td>
   					<td>
					<input type="text" id="frtWorkshopManager_details_fuelSituation" name="fuelSituation" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.FUELTHING_TAG.FUELTHINGKEY %>',
					required : true,
					editable : true,
					missingMessage : '燃油情况为必填项',  
					validType:'isSelected[\'#frtWorkshopManager_details_fuelSituation\']',
					invalidMessage : '请从下拉框中选择燃油情况', 
					mode : 'remote',
					valueField:'id',  
					textField:'text' "/>
					</td>
					<td>旧件处理:</td>
   					<td>
					<input type="text" id="frtWorkshopManager_details_oldPieces" name="oldPieces" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.OLDPARTS_TAG.OLDPARTSKEY %>',
					required : true,
					editable : true,
					missingMessage : '旧件处理为必填项', 
					validType:'isSelected[\'#frtWorkshopManager_details_oldPieces\']',
					invalidMessage : '请从下拉框中选择旧件处理',  
					mode : 'remote',
					valueField:'id',  
					textField:'text' "/>
					</td>
   					<td>维修班组:</td>
   					<td><input type="text" id="frtWorkshopManager_details_repgrpId" name="repgrpId" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					required : true,
					editable : true,
					mode : 'remote',
					url : 'frtOptionsAction!findAllRepairGroup.action',
					missingMessage:'维修班组为必填项',
					validType:'isSelected[\'#frtWorkshopManager_details_repgrpId\']',
					invalidMessage : '请从下拉框中选择维修班组',
					valueField:'id',  
					textField:'text' "/></td>
   					<td>维修工位:</td>
   					<td><input type="text" id="frtWorkshopManager_details_repwkId" name="repwkId" class="easyui-combobox"
   					 style="width: 140px;"
					data-options="
					required : true,
					editable : true,
					mode : 'remote',
					url : 'frtOptionsAction!findAllRepairWork.action',
					missingMessage:'维修工位为必填项',
					validType:'isSelected[\'#frtWorkshopManager_details_repwkId\']',
					invalidMessage : '请从下拉框中选择维修工位',
					valueField:'id',  
					textField:'text' "/></td>
					<td>接待员:</td>
					<td>
						<input type="text" id="frtWorkshopManager_details_receptor" name="receptor" class="easyui-combobox"
	   					 style="width: 140px;" readonly="readonly"
						data-options="
						url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						disabled : true,
						editable : false,
						mode : 'remote',
						missingMessage : '接待员为必填项',   
						valueField:'id',  
						textField:'text' "/>
					</td>
   				</tr>
   				<tr>
   					<td>故障描述:</td>
   					<td colspan="4"><textarea rows="" cols="" name="problemDesc" 
   						style="width: 450px; height: 50px;"></textarea> </td>
   					<td>接车备注:</td>
   					<td colspan="4"><textarea rows="" cols="" name="receptionRemark" 
   						style="width: 450px; height: 50px;"></textarea></td>
   				</tr>
   				<tr>
   					<td colspan="10">
   					<span id="workshopButtons">
						<button type="button" id="pg" name="pg" 
						onclick="
						$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState2 %>');
						$('#pg').attr('disabled', 'true'); 
						if(staticIsCheckButton!='true'){
							$('#workshopButtons button[name!=pg]').removeAttr('disabled');
						}
						standReceptionStatus('pg');" 
						style="width: 80px;">派工</button>&nbsp;
   						<button type="button" id="kg" name="kg" 
   						onclick="
						$('#kg').attr('disabled', 'true');
						$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState3 %>');
   						if(staticIsCheckButton!='true'){
   							$('#workshopButtons button[name!=kg]').removeAttr('disabled'); 
   						}
   						standReceptionStatus('kg');" 
   						style="width: 80px;">开工</button>&nbsp;
   						<button type="button"  id="wxz" name="wxz" 
   						onclick="
   						$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState4 %>');
   						if(staticIsCheckButton!='true'){
   							$('#wxz').attr('disabled', 'true'); 
   							$('#workshopButtons button[name!=wxz]').removeAttr('disabled');
   						}else{
   							checkButtonDisabled($('#receptionStatus').val());
   						}
   						standReceptionStatus('wxz');" 
   						style="width: 80px;">维修中</button>&nbsp;
   						<button type="button" id="ddlj" name="ddlj" 
   						onclick="
   						$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState5 %>');
   						if(staticIsCheckButton!='true'){
   							$('#ddlj').attr('disabled', 'true'); 
   							$('#workshopButtons button[name!=ddlj]').removeAttr('disabled'); 
   						}else{
   							checkButtonDisabled($('#receptionStatus').val());
   						}
   						standReceptionStatus('ddlj');" 
   						style="width: 80px;">等待零件</button>&nbsp;
   						<button type="button" id="dddf" name="dddf" 
   						onclick="
   						$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState6 %>');
   						if(staticIsCheckButton!='true'){
   							$('#dddf').attr('disabled', 'true'); 
   							$('#workshopButtons button[name!=dddf]').removeAttr('disabled'); 
   						}else{
   							checkButtonDisabled($('#receptionStatus').val());
   						}
   						standReceptionStatus('dddf');" 
   						style="width: 80px;">等待答复</button>&nbsp;
   						<button type="button" id="zljc" name="zljc" 
   						onclick="javaScrpt:
	   					$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState7 %>');
   						if(staticIsCheckButton!='true'){
   							$('#zljc').attr('disabled', 'true'); 
	   						$('#workshopButtons button[name!=zljc]').removeAttr('disabled'); 
   						}else{
   							 $('#pg').attr('disabled', 'true');
							  $('#kg').attr('disabled', 'true'); 
							  $('#workshopButtons button[name=wxz]').removeAttr('disabled');
							  $('#workshopButtons button[name=ddlj]').removeAttr('disabled'); 
							  $('#workshopButtons button[name=dddf]').removeAttr('disabled');
							  $('#zljc').attr('disabled', 'true'); 
							   $('#wg').attr('disabled', 'true'); 
   						}
   						standReceptionStatus('zljc');" 
   						style="width: 80px;">质量检验</button>&nbsp;
   						<button type="button" id="wg" name="wg" 
   						onclick="javaScript:
	   					$('#receptionStatus').val('<%=Contstants.DOCUMENT_TAG.DOCUMENTState8 %>');
   						if(staticIsCheckButton!='true'){
   							$('#wg').attr('disabled', 'true'); 
	   						$('#workshopButtons button[name!=wg]').removeAttr('disabled'); 
   						}else{
   							checkButtonDisabled($('#receptionStatus').val());
   						}
   						standReceptionStatus('wg');" 
   						style="width: 80px;">完工</button>&nbsp;
   					</span>
   					<input type="hidden" id="receptionStatus" name="receptionStatus"/>
   						<input type="hidden" name="managementFee"/>
   						<input type="hidden" name="interDate"/>
   					</td>
   				</tr>
   			</table>
		</form>
	</div>
	<div style="background: #eee;" data-options="region:'center',border:false">
		<div id="frtWorkshopManagerDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div class="datagrid-toolbar" data-options="title:'车身状况',border:'false',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/details/vehicleStructure.jsp'">
				
			</div>
			<div data-options="title:'维修项目',border:'false',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/details/item.jsp'">

			</div>
			<div data-options="title:'维修配件',border:'false',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/details/parts.jsp'">
				
			</div>
			<div data-options="title:'配件库存',border:'false',href:'${pageContext.request.contextPath}/frt/frtPartsQuery1.jsp'"></div>
			<div data-options="title:'出料显示',border:'false',href:'${pageContext.request.contextPath}/frt/frtWorkshopManager/details/ex-warehouseParts.jsp'"></div>
		</div>
	</div>
</div>

