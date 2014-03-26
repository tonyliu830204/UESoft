<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<!-- 保险估价单明细 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtInsurePrize/details.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div style="overflow: hidden;background: #eee; height: 112px;" data-options="region:'north',title:'汇总信息',border:false">
		<form id="frtInsurePrizeAddForm">
			<table width="1320px;" border="0">
				<tr>
					<td>车牌照:</td>
					<td><input type="text" id="frtInsurePrize_details_carLicense" name="carId" class="easyui-combobox"
					 style="width: 140px;"
					data-options="	url : 'frtOptionsAction!findAllCarLicense.action?flag=true',
					required : true,
					valueField : 'id',
					textField : 'text',
					missingMessage : '车牌照为必填项', 
					validType:'isSelected[\'#frtInsurePrize_details_carLicense\']',
					invalidMessage : '请从下拉框中选择车辆牌照',
					mode : 'remote',
					onSelect:function(record){
						var carId=$('#frtInsurePrize_details_carLicense').combobox('getValue');
						frtInsurePrize_carLicense=carId;
						if(carId==null||carId.length==0)
							carId=-1;
						$('#frtWorkOrderItemDatagrid').treegrid({
							url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+carId
						});
					},
					onChange:function(newValue, oldValue){
						if(frtInsurePrizeCount==0){
							frtInsurePrize_carLicense=null;
						}else{
							frtInsurePrize_carLicense=newValue;
						}
						frtInsurePrizeCount++;
					}
					"/>
					</td>
					<td>VIN号:</td>
					<td><input type="text" id="frtInsurePrize_details_resvVin" maxlength="20"
					 name="carVin" class="easyui-combobox"  style="width: 140px;" 
					 data-options="	url : 'frtOptionsAction!findAllCarVin.action',
					required : true,
					valueField : 'id',
					textField : 'text',
					missingMessage : 'VIN号为必填项', 
					mode : 'remote'"/>
					</td>
					<td>维修类别:</td>
					<td><input type="text" id="frtInsurePrize_details_reptId" name="reptId" class="easyui-combobox"
						 style="width: 140px;"
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findAllReptype.action',
						required : true,
						editable : true,
						mode : 'remote',
						missingMessage : '维修类别为必填项',
						validType:'isSelected[\'#frtInsurePrize_details_reptId\']',
						invalidMessage : '请从下拉框中选择维修类别',
						valueField:'id',  
						textField:'text' " />
					</td>
					<td>预约进店时间:</td>
					<td><input type="text" id="frtInsurePrize_addResvEnterTime" name="resvEnterTime"
					class="easyui-datetimebox" style="width: 140px;"
						value="{now}"
						data-options="required : true,editable : false "/>
					</td>
					<td>客户名称:</td>
					<td colspan="2"><input type="text" id="frtInsurePrize_details_customId" style="width:350px;" name="customId"
						class="easyui-combobox"
						data-options="
						required : true,
						url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action',
						missingMessage : '客户编号为必填项',  
						validType:'isSelected[\'#frtInsurePrize_details_customId\']',
						invalidMessage : '请从下拉框中选择客户名称',
						valueField:'id',  
						textField:'text', 
					 	mode : 'remote'" />
					 	备注:
					</td>
				</tr>
				<tr>
					<td>维修工位:</td>
					<td><input type="text" id="frtInsurePrize_details_repwkId" name="repwkId" class="easyui-combobox"
						 style="width: 140px;"
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findAllRepairWork.action',
						required : true,
						editable : true,
						mode : 'remote',
						missingMessage : '维修工位为必填项', 
						validType:'isSelected[\'#frtInsurePrize_details_repwkId\']',
						invalidMessage : '请从下拉框中选择维修工位',
						valueField:'id',  
						textField:'text' " />
					</td>
					<td>里程数:</td>
					<td><input type="text" id="frtInsurePrize_details_resvDistance" name="resvDistance" maxlength="7"
						 style="width: 140px;"
						class="easyui-numberbox"
						data-options="
						required : true,
						min:0,max:9999999,precision:0,
						missingMessage : '里程数为必填项' " />
					</td>
					<td>预约登记时间:</td>
					<td><input type="text" id="frtInsurePrize_addResvRealTime" name="resvRealTime" style="width: 140px;" 
					value="{now}"
					class="easyui-datetimebox" style="width: 140px;"
				data-options="disabled:true,required : true, editable : false "/>
					</td>
					<td>接待员:</td>
					<td><input type="text" id="frtInsurePrize_detail_stfId" name="stfId" class="easyui-combobox"
						 style="width: 140px;"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						data-options="
						disabled:true,
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						editable : false,
						missingMessage : '接待员为必填项', 
						valueField:'id',  
						textField:'text' " />
					</td>
					<td>保险托修人:</td>
					<td><input type="text" id="frtInsurePrize_detail_repPer" name="resevationRepPer" class="easyui-validatebox"
					 style="width: 140px;"
						 data-options="
						required : true,
						missingMessage : '保险托修人为必填项' " />
						<input type="hidden" name="resvId" id="resvId">
						<input type="hidden" name="resvStatus">
					</td>
					<td rowspan="2"><textarea rows="" cols="" name="resvRemark"
							style="width: 220px; height: 46px;" 
							onkeyup="showMaxLength('carArchives_add_carRemark',200);"></textarea>
					</td>
				</tr>
				<tr>
					<td>托修人:</td>
					<td><input type="text" id="frtInsurePrize_details_resvFixpel" name="resvFixpel" maxlength="20"
					 style="width: 140px;"
						class="easyui-validatebox"
						data-options="
				required : false,
				missingMessage : '托修人为选填项' " />
					</td>
					<td>托修人电话:</td>
					<td><input type="text" id="frtInsurePrize_details_resvFixtel" name="resvFixtel" maxlength="20"
					 style="width: 140px;"
						class="easyui-validatebox"
						data-options="
				required : false,
				missingMessage : '托修人电话为选填项',validType:'phone' " />
					</td>
					<td>托修人手机:</td>
					<td><input type="text" id="frtInsurePrize_details_resvFixphone" name="resvFixphone" maxlength="20"
					 style="width: 140px;"
						class="easyui-validatebox"
						data-options="
				required : false,
				missingMessage : '托修人手机为选填项',validType:'mobile'" />
					</td>
					<td>托修人性别:</td>
					<td><input type="text" id="frtResevation_details_carRealationSex" name="carRealationSex"  class="easyui-combobox"
					 style="width: 140px;"
						 data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.SEXTYPE.SEX %>',
						required : false,
						editable : false,
						missingMessage : '托修人性别为选填项',  
						validType:'isSelected[\'#frtResevation_details_carRealationSex\']',
						invalidMessage : '请从下拉框中选择托修人性别',
						valueField:'id',  
						textField:'text' " />
					</td>
					<td>维修班组:</td>
					<td><input type="text" id="frtInsurePrize_details_repgrpId" name="repgrpId" class="easyui-combobox"  style="width: 140px;" 
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findAllRepairGroup.action',
						required : false,
						editable : true,
						mode : 'remote',
						missingMessage : '维修工班组为选填项', 
						validType:'isSelected[\'#frtInsurePrize_details_repgrpId\']',
						invalidMessage : '请从下拉框中选择维修班组', 
						valueField:'id',  
						textField:'text' " />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="background: #eee;" data-options="region:'center',border:false">
		<div id="frtInsurePrizeDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div data-options="title:'车身状况',border:'false',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/details/vehicleStructure.jsp'">
				
			</div>
			<div data-options="title:'计划材料',border:'false',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/details/parts.jsp'">
				
			</div>
			<div data-options="title:'计划项目',border:'false',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/details/item.jsp'">

			</div>
			<div class="datagrid-toolbar" data-options="title:'抢修信息',border:'false',href:'${pageContext.request.contextPath}/frt/frtInsurePrize/details/rushToRepair.jsp'" style="padding:10px;">
				
			</div>
			<div data-options="title:'维修档案',border:'false',href:'${pageContext.request.contextPath}/frt/repairArchives.jsp?carIdInput=frtInsurePrize_details_carLicense'"></div>
			
		</div>
	</div>
</div>
