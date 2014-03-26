<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants,com.syuesoft.model.BasUsers" %>
<script type="text/javascript">
   var PROCESSTATEYES = '<%=Contstants.PROCESSTATE_TAG.PROCESSTATEYES%>';
   var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtReception/details.js"></script>
<!-- 前台接车明细 -->
<div class="easyui-layout" data-options="fit:true,border:false" onclick="frtReceptionToteMoney();bespeakState('bespeak_off','bespeak_on','frtReception_details_resvId');">
	<div style="overflow: hidden;background: #eee; height: 220px;" data-options="region:'north',title:'汇总信息',border:false"  id="frtReceptionDiv">
		<form id="frtReceptionAddForm">
			<table width="1300"  border="0">
    				<tr>
    					<td>预约编号:</td>
    					<td><input type="text" name="resvId" id="frtReception_details_resvId"
    					  class="easyui-validatebox"	 style="width: 140px;background:#EBEBE4;"
    						 readonly="readonly"/></td>
    					<td><span id="button2"></span>车牌照:</td>
    					<td><input type="text" id="frtReception_details_carId"  name="carId" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						requried:true,  
						validType:'isSelected[\'#frtReception_details_carId\']',
						invalidMessage : '请从下拉框中选择车辆牌照',
						url : 'frtOptionsAction!findAllCarLicense.action?flag=true',
						valueField : 'id',
						textField : 'text',
						mode : 'remote',
						onSelect:function(record){
							var carId=$('#frtReception_details_carId').combobox('getValue');
							frtReception_carId=carId;
							if(carId==null||carId.length==0)
								carId=-1;
							showLastService(carId);
							bespeakClew(carId);
							addCarArchives();
							$('#frtWorkOrderItemDatagrid').treegrid({
								url : 'frtWorkOrderAction!datagridFrtWorkOrderItem.action?carId='+carId
							});
							 $('#frtReceptionAdviceDatagrid').datagrid({
								 url :  'frtReceptionAction!findFrtResvAdviceByCarId.action?carId='+carId,
								 onLoadSuccess : function (){
									 $('#frtReception_service_add').linkbutton('enable');
									 $('#frtReception_service_remove').linkbutton('enable');
									 $('#frtReception_service_accept').linkbutton('enable');
								 }
							 });
						},
						onChange:function(newValue, oldValue){
						if(frtReceptionCount==0){
							frtReception_carId=null;
						}else{
							frtReception_carId=newValue;
						}
						frtReceptionCount++;
					}"/></td>
    					<td>VIN号:</td>
    					<td><input type="text" id="frtReception_details_carVin" maxlength="20" name="carVin"
    					 style="width: 140px;"
    					 class="easyui-combobox" data-options="
						url : 'frtOptionsAction!findAllCarVin.action',
						requried:true,
						valueField : 'id',
						textField : 'text',
						missingMessage : 'VIN号为必填项', 
						mode : 'remote'"/></td>
    					
    					<td>客户名称:</td>
    					<td colspan="3"><input type="text" id="frtReception_details_customId" name="customId" class="easyui-combobox"
    					style="width:400px;background:#EBEBE4;" readonly="readonly"
						data-options="
						required : true,
						disabled:true,
						url : 'frtOptionsAction!findAllCustom.action',
						mode:'remote',
						missingMessage:'客户名称为必填项',
						valueField:'id',  
						textField:'text' "/></td>
						<td rowspan="7">
							<img id="bespeak_on" src="${pageContext.request.contextPath}/images/bespeak_on.png" style="width:100px;height:100px;display:none;"></img>
							<img id="bespeak_off" src="${pageContext.request.contextPath}/images/bespeak_off.png" style="width:100px;height:100px;"></img>
						</td>
    				</tr>
    				<tr>
    					<td>保养区分:</td>
    					<td><input type="text" id="frtReception_details_receptionMaintFlg" name="receptionMaintFlg" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						required : true,
						url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.MAINTAIN_TAG.MAINTAINKEY %>',
						missingMessage:'保养区分为必填项',
						validType:'isSelected[\'#frtReception_details_receptionMaintFlg\']',
						invalidMessage : '请从下拉框中选择保养区分',
						valueField:'id',  
						textField:'text' "/></td>
    					<td>维修类别:</td>
    					<td><input type="text" id="frtReception_details_reptId" name="reptId" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						required : true,
						editable : true,
						mode : 'remote',
						url : 'frtOptionsAction!findAllReptype.action',
						missingMessage:'维修类别为必填项',
						validType:'isSelected[\'#frtReception_details_reptId\']',
						invalidMessage : '请从下拉框中选择维修类别',
						valueField:'id',  
						textField:'text' "/></td>
						<td>三包人员:</td>
    					<td><input type="text" id="frtReception_details_receptionInsurPer" name="receptionInsurPer" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						required : false,
						mode : 'remote',
						url : '${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseEgisAvailPerson.action',
						missingMessage : '三包人员选必填项',  
						validType:'isSelected[\'#frtReception_details_receptionInsurPer\']',
						invalidMessage : '请从下拉框中选择三包人员',
						valueField:'id',  
						textField:'text' "/></td>
						<td>维修技师:</td>
    					<td><input type="text" id="frtReception_details_receptionTechnician" name="receptionTechnician" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						mode : 'remote',
						url : '${pageContext.request.contextPath}/basStuffClassAction!findEnterpriseMaintainArtificer.action',
						required : false,
						editable : true,
						missingMessage : '维修技师为选填项',  
						validType:'isSelected[\'#frtReception_details_receptionTechnician\']',
						invalidMessage : '请从下拉框中选择维修技师',
						valueField:'id',  
						textField:'text' "/></td>
						<td>里程数:</td>
    					<td><input type="text" id="frtReception_details_receptionDistance" name="receptionDistance"
    					 style="width: 140px;" onblur="checkoutDistance('frtReception_details_receptionDistance','frtReception_details_carId');"
    					  class="easyui-numberbox" 
    					data-options="
    					required : true,
    					validType:'maxLength[100]',
    					missingMessage:'里程数为必填项' "
    					/></td>
    				</tr>
    				<tr>
    					<td>
    					接待日期:
    					</td>
    					<td>
    						<input type="text" id="addInterDate" name="interDate"
							class="easyui-datetimebox" style="width: 140px;background:#EBEBE4;" readonly="readonly"
							value="{now}"
							data-options="
							disabled:true,
							required : true,
							editable : false,
							missingMessage: '接待日期为必填' " />
    					</td>
    					<td>
    					计划完工时间:
    					</td>
    					<td>
    						<input type="text" id="addReceptionEndTime" name="receptionEndTime" 
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
    						<input type="text" id="addExpDelCarTime" name="expDelCarTime" 
    					class="easyui-datetimebox" style="width: 140px;"
						value="{now}"
						data-options="
						required : true,
						editable : false,
						missingMessage: '预计交车时间为必填' " />
    					</td>
						<td>索赔厂商:</td>
    					<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					<input type="text" id="frtReception_details_finComId" name="finComId" class="easyui-combobox"
    					 style="width: 400px;"		
    					 data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimManufacturers.action',
						required : true,
						missingMessage : '索赔厂商编号为必填项',  
						validType:'isSelected[\'#frtReception_details_finComId\']',
						invalidMessage : '请从下拉框中选择索赔厂商',
    					 mode : 'remote',
						valueField:'id',  
						textField:'text' "/></td>
						
    				</tr>
    				<tr>
    					<td>保险送修人:</td>
    					<td><input type="text" id="frtReception_details_receptionRepPer" name="receptionRepPer" class="easyui-validatebox" 
    					 style="width: 140px;"
    					data-options="
    					required : false,
						missingMessage : '保险送修人为必填项' "/></td>
						<td>托修人:</td>
    					<td><input type="text" id="frtReception_details_propRepPer" name="propRepPer" class="easyui-validatebox"
    					 style="width: 140px;" 
    					data-options="
    					required : false,
						missingMessage : '托修人为必填项' "/></td>
    					<td>托修人手机:</td>
    					<td><input type="text" id="frtReception_details_propPhone" name="propPhone" class="easyui-validatebox"
    					 style="width: 140px;" 
    					data-options="
    					required : false,
						missingMessage : '托修人手机为必填项',validType:'mobile' "/></td>
    					<td>托修人电话:</td>
    					<td><input type="text" id="frtReception_details_propTel" name="propTel" class="easyui-validatebox"
    					 style="width: 140px;" 
    					data-options="
    					required : false,
						missingMessage : '托修人电话为必填项',validType:'phone' "/></td>
						<td>贵重物品:</td>
    					<td>
    					<input type="text" id="frtReception_details_valuables" name="valuables" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.VALUABLESRES_TAG.VALUABLESRESKEY %>',
						required : true,
						editable : false,
						mode:'remote',
						missingMessage : '有无贵重物品为必填项', 
						validType:'isSelected[\'#frtReception_details_valuables\']',
						invalidMessage : '请从下拉框中选择贵重物品', 
						valueField:'id',  
						textField:'text' "/>
						</td>
    				</tr>
    				<tr>
    					<td>维修班组:</td>
    					<td><input type="text" id="frtReception_details_repgrpId" name="repgrpId" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						required : false,
						url : 'frtOptionsAction!findAllRepairGroup.action',
						missingMessage:'维修班组为选填项',
						validType:'isSelected[\'#frtReception_details_repgrpId\']',
						invalidMessage : '请从下拉框中选择维修班组',
						mode:'remote',
						valueField:'id',  
						textField:'text' "/></td>
    					<td>维修工位:</td>
    					<td><input type="text" id="frtReception_details_repwkId" name="repwkId" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						required : false,
						url : 'frtOptionsAction!findAllRepairWork.action',
						mode:'remote',
						missingMessage:'维修工位为选填项',
						validType:'isSelected[\'#frtReception_details_repwkId\']',
						invalidMessage : '请从下拉框中选择维修工位',
						valueField:'id',  
						textField:'text' "/></td>
						<td>燃油情况:</td>
    					<td>
						<input type="text" id="frtReception_details_fuelSituation" name="fuelSituation" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.FUELTHING_TAG.FUELTHINGKEY %>',
						required : true,
						missingMessage : '燃油情况为必填项',  
						validType:'isSelected[\'#frtReception_details_fuelSituation\']',
						invalidMessage : '请从下拉框中选择燃油情况',
						valueField:'id',  
						textField:'text' "/>
						</td>
						<td>旧件处理:</td>
    					<td>
						<input type="text" id="frtReception_details_oldPieces" name="oldPieces" class="easyui-combobox"
    					 style="width: 140px;"
						data-options="
						url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.OLDPARTS_TAG.OLDPARTSKEY %>',
						required : true,
						missingMessage : '旧件处理为必填项',
						validType:'isSelected[\'#frtReception_details_oldPieces\']',
						invalidMessage : '请从下拉框中选择旧件处理',  
						valueField:'id',  
						textField:'text' "/>
						</td>
						<td>接待员:</td>
						<td><input type="text" id="addReceptor" name="receptor"class="easyui-combobox"
						 style="width: 140px;" readonly="readonly"
						value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
						data-options="
						disabled:true,
						url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
						required : true,
						editable : false,
						missingMessage : '接待员为必填项',  
						valueField:'id',  
						textField:'text' "/></td>
    				</tr>
    				<tr>
    					<td>接车备注:</td>
						<td colspan="4">
							<textarea id="frtReception_details_receptionRemark" rows="" cols="" name="receptionRemark"  
							style="width:460px;height:50px;"></textarea>
							
						</td>
						<td>故障描述:</td>
    					<td colspan="4">
    						<textarea rows="" cols="" name="problemDesc"
    						 style="width:460px;height:50px;"></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="hidden" name="receptionId" id="receptionId"/>
	    					<input type="hidden" name="interDate"/>
	    					<input type="hidden" name="receptionStatus"/>
	    					<input type="hidden" name="rcptBranch"/>
    					</td>
    				</tr>
    			</table>
		</form>
	</div>
	<div style="background: #eee;" data-options="region:'center',border:false">
		<div id="frtReceptionDetailsTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div data-options="title:'车身状况',border:'false'">
				<div class="easyui-layout" data-options="fit:true">
					<div style="width: 460px;" data-options="region:'west',title:'',border:false">
						<jsp:include page="/frt/carStructure.jsp"></jsp:include>
					</div>
					<div data-options="region:'center',title:'车身状况'">
						<table id="vehicleStructureDatagrid"></table>
					</div>
				</div>
			</div>
			<div class="datagrid-toolbar" data-options="title:'费用情况',border:'false'">
			    <div class="easyui-layout" fit="true" border="false">
					<div region="west" style="padding:30px;background:#eee;width: 360px;"
						border="false">
						<form id="frtReceptionExpenseSituationForm">
							<table>
								<tr>
									<th>收费项目</th>
									<th>金额</th>
									<th>说明</th>
								</tr>
								<tr>
									<td>工时费:</td>
									<td><input type="text" id="frtReceptionPreclrWktimeAmount"
										name="preclrWktimeAmount" readonly="readonly" />
									</td>
									<td><input type="text" value="工时×工时单价"  readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td>材料费:</td>
									<td><input type="text" id="frtReceptionPreMprMatAmount"
										name="preMprMatAmount" readonly="readonly"/>
									</td>
									<td><input type="text" value="附材料清单"  readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td>管理费:</td>
									<td><input type="text" id="frtReceptionPreclrManagementFee" name="preclrManagementFee"
										onkeyup="frtMoneyAmount();"  readonly="readonly" />
									</td>
									<td><input type="text"  readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td>其他费:</td>
									<td><input type="text" id="frtReceptionOtherAmount" name="otherAmount"
										readonly="readonly" />
									</td>
									<td><input type="text" value="收费项目总合"  readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td>费用合计:</td>
									<td><input type="text" id="frtReceptionAmountTotal" name="amountTotal"
										readonly="readonly" />
									</td>
									<td><input type="text" value="(1+2+3+4+5)"  readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td>实际收款:</td>
									<td><input type="text" id="frtReceptionActualCost" name="actualCost"
										readonly="readonly" />
									</td>
									<td><input type="text" value="费用合计减去优惠价"  readonly="readonly" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div region="center" style="background:#eee;" border="false">
						<div class="easyui-layout" fit="true" border="false">
							<div region="north" style="padding:3px;background:#eee;height:30px;"
								border="false">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>其他费用明细:</strong>
							</div>
							<div region="center" style="padding:3px;background:#eee;"
								border="false">
								<table id="frtReceptionExpenseSituationOtherExpense"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div data-options="title:'计划材料',border:'false'">
			    <table id="frtReceptionPartsDatagrid"></table>
			</div>
			<div data-options="title:'计划项目',border:'false'">
			    <table id="frtReceptionItemDatagrid"></table>
			</div>
			<div data-options="title:'会员服务',border:'false'">
			    <div class="easyui-layout" style="width:800px; height:600px" data-options="fit:true,border:false">
					<div title="查询条件" style="overflow: hidden;background:#eee; height:80px;" data-options="region:'north',border:false">
						<form id="">
							<table>
								<td></td>
								<td></td>
							</table>
						</form>
					</div>
					<div style="background:#eee;" data-options="region:'center',border:false">
						这是会员服务页面
					</div>
				</div>
			</div>
			<div data-options="title:'配件库存',border:'false'">
			    <div class="easyui-layout" fit="true" border="false">
				    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
				       <privilege:enable code="STAGEPARTSSEA_SEARCH">
					      	<a href="javascript:void(0);" id="search_parts" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="_queryPart();">查询</a>
					   </privilege:enable>
				       <privilege:enable code="STAGEPARTSSEA_CLEAR">
					      	<a href="javascript:void(0);" id="clear_parts" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clearPart();">清空</a>
					   </privilege:enable>
				    </div>
					<div data-options="region:'center',border:false">
						<div class="easyui-layout" fit="true" border="false">
								<div title="查询条件" style="overflow: hidden;background:#eee;height:50px;" data-options="region:'north',border:false">
									<form id="frtPartsQueryForm">
										<table>
											<tr>
												<td>配件编码:</td>
												<td><input type="text" name="partsId" class="easyui-numberbox"></td>
												<td>配件名称:</td>
												<td><input type="text" name="partsName"></td>
												<td>简码:</td>
												<td><input type="text" name="partsSimpleId"></td>
											</tr>
										</table>
									</form>
								</div>
								<div id="frtPartsQueryDatagrid_center" style="background:#eee;" data-options="region:'center',border:false">
									<table id="frtPartsQueryDatagrid"></table>
								</div>
						 </div>
					</div>
				</div>
			</div>
			<div data-options="title:'维修档案',border:'false'">
			    <div class="easyui-layout" style="width:800px; height:600px" data-options="fit:true,border:false">
					<div title="查询条件" style="overflow: hidden;background:#eee; height:80px;" data-options="region:'north',border:false">
						<form id="">
							<table>
								<td>车牌照:</td>
								<td><input type="text" name="carId" class="easyui-combobox"
									data-options="
										url : 'frtOptionsAction!findAllCarLicense.action',
										valueField : 'id',
										textField : 'text',
										mode : 'remote'" />
								</td>
							</table>
						</form>
					</div>
					<div style="background:#eee;" data-options="region:'center',border:false">
						<table id="frtResevationRepairArchivesDatagrid"></table>
					</div>
				</div>
			</div>
			<div data-options="title:'维修建议',border:'false'">
			    <table id="frtReceptionAdviceDatagrid"></table>
					<div id="ee" title="增加维修建议" data-options="iconCls:'icon-save',modal:true"
						style="padding: 5px; width: 820px; height: 430px;">
						<!-- 维修建议 -->
						<form id="frtResvAdviceForm">
							<table align="left" width="800px;">
								<tr>
									<td>
										车辆牌照:
									</td>
									<td colspan="2">
										<input type="hidden" name="resvId" id="sresvId"/>
										<input type="hidden" name="carId" id="scarId" />
										<input type="text" name="carLicense" id="scarLicense" data-options="disabled:true"
											class="easyui-validatebox" style="width: 260px;" />
									</td>
									<td colspan="2">
										发布人员:
									</td>
									<td>
											<input type="text" name="writePerson"  id="swritePerson"  class="easyui-combobox"
											value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
											readonly="readonly"
											style="width: 140px;" 
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled :true,
											valueField:'id',  
											textField:'text' "/>
									</td>
								</tr>
					
								<tr>
									<td>
										发布日期:
									</td>
									<td colspan="2">
										<input type="text" name="adviceTime" id="sadviceTime"
											class="easyui-datetimebox" style="width: 260px;"
											readonly="readonly"  value="{now}"
											data-options="
											required : true,
											editable : false,
											missingMessage: '发布日期为必填' " />
									</td>
					
									<td colspan="2">
										维修分类:
									</td>
									<td>
										<input type="text" name="adviceClass" id="sadviceClass"
											style="width: 140px;" class="easyui-combobox"
											data-options="
											required : true,
											editable : false,
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
											valueField:'id',  
											textField:'text' "/>
									</td>
					
								</tr>
					
								<tr>
									<td>
										维修建议:
									</td>
									<td colspan="5">
										<textarea id="sadviceContext" name="adviceContext" rows="20"
											cols="50" style="width: 700px; height: 160px;" 
											data-options="required : true"></textarea>
									</td>
								</tr>
					
								<tr>
									<td>
										处理进度:
									</td>
									<td>
										
											<input type="text" name="procesState" id="sprocesState" class="easyui-combobox" style="width: 140px;"
											data-options="
											required : true,
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
											valueField : 'id',
											textField : 'text',
											editable : false,
											missingMessage : '处理进度为必填项',
											mode : 'remote'"/></td>
					
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td>
										处理日期:
									</td>
									<td>
										<input type="text" name="adviceTimeEnd" id="sadviceTimeEnd"
											readonly="readonly" class="easyui-datetimebox"
											 value="{now}"
											style="width: 140px;" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										经办人:
									</td>
									<td>
										<input type="text" name="procesProson" id="sprocesProson"
										value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
											readonly="readonly" class="easyui-combobox"
											style="width: 140px;"  
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled : true,
											valueField:'id',  
											textField:'text' " />
									</td>
								</tr>
								<tr>
									<td>
										处理结果:
									</td>
									<td colspan="5">
										<textarea id="sprocesContext" name="procesContext" rows="20"
											cols="50" style="width: 700px; height: 80px;"></textarea>
									</td>
							</table>
					
						</form>
					</div>
					<div id="ff" title="修改维修建议" data-options="iconCls:'icon-edit',modal:true"
						style="padding: 5px; width: 820px; height: 430px;">
						<!-- 维修建议 -->
						<form id="ufrtResvAdviceForm">
							<table align="left" width="800px;">
								<tr>
									<td>
										车辆牌照:
									</td>
									<td colspan="2">
									<input type="hidden" name="adviceId" id="uadviceId"/>
										<input type="hidden" name="carId" id="ucarId" />
										<input type="text" id="ucarLicense" name="carLicense" readonly="readonly"
											class="easyui-validatebox" style="width: 260px;" />
									</td>
									<td colspan="2">
										发布人员:
									</td>
									<td>
											<input type="text" name="writePerson"  id="uwritePerson"  class="easyui-combobox"
											readonly="readonly"
											style="width: 140px;" 
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled :true,
											valueField:'id',  
											textField:'text' "/>
									</td>
								</tr>
					
								<tr>
									<td>
										发布日期:
									</td>
									<td colspan="2">
										<input type="text" name="adviceTime" id="uadviceTime"
											class="easyui-validatebox" style="width: 260px;"
											readonly="readonly"
											data-options="
											disabled : true,
											editable : false " />
									</td>
					
									<td colspan="2">
										维修分类:
									</td>
									<td>
										<input type="text" name="adviceClass" id="uadviceClass"
											style="width: 140px;" class="easyui-combobox"
											data-options="
											editable : false,
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
											valueField:'id', 
											disabled : true, 
											textField:'text' "/>
									</td>
					
								</tr>
					
								<tr>
									<td>
										维修建议:
									</td>
									<td colspan="5">
										<textarea id="uadviceContext" name="adviceContext" rows="20" readonly="readonly"
											cols="50" style="width: 700px; height: 160px;"></textarea>
									</td>
								</tr>
					
								<tr>
									<td>
										处理进度:
									</td>
									<td>
										
											<input type="text" name="procesState" id="uprocesState" class="easyui-combobox" style="width: 140px;"
											data-options="
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
											valueField : 'id',
											textField : 'text',
											editable : false,
											missingMessage : '处理进度为必填项',
											mode : 'remote'"/></td>
					
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td>
										处理日期:
									</td>
									<td>
										<input type="text" name="adviceTimeEnd" id="uadviceTimeEnd"
											readonly="readonly" class="easyui-datetimebox"
											 value="{now}"
											 data-options="
											 disabled : true,
											editable : false"
											style="width: 140px;" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										经办人:
									</td>
									<td>
										<input type="text" name="procesProson" id="uprocesProson"
											value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
											readonly="readonly" class="easyui-combobox"
											style="width: 140px;"
											data-options="
											disabled:true,
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled : true,
											valueField:'id',  
											textField:'text' " />
									</td>
								</tr>
								<tr>
									<td>
										处理结果:
									</td>
									<td colspan="5">
										<textarea id="uprocesContext" name="procesContext" rows="20"
											cols="50" style="width: 700px; height: 80px;"></textarea>
									</td>
							</table>
					
						</form>
					</div>
					<div id="dd" title="维修建议详情" data-options="iconCls:'icon-search',modal:true"
						style="padding: 5px; width: 820px; height: 430px;">
						<!-- 维修建议 -->
						<form id="sfrtResvAdviceForm">
							<table align="left" width="800px;">
								<tr>
									<td>
										车辆牌照:
									</td>
									<td colspan="2">
									<input type="hidden" name="adviceId" id="ssadviceId"/>
										<input type="hidden" name="carId" id="sscarId" />
										<input type="text" id="sscarLicense" name="carLicense" readonly="readonly"
											class="easyui-validatebox" style="width: 260px;" />
									</td>
									<td colspan="2">
										发布人员:
									</td>
									<td>
											<input type="text" name="writePerson"  id="sswritePerson"  class="easyui-combobox"
											readonly="readonly"
											style="width: 140px;" 
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled :true,
											valueField:'id',  
											textField:'text' "/>
									</td>
								</tr>
					
								<tr>
									<td>
										发布日期:
									</td>
									<td colspan="2">
										<input type="text" name="adviceTime" id="ssadviceTime"
											class="easyui-validatebox" style="width: 260px;"
											readonly="readonly"
											data-options="
											disabled : true,
											editable : false " />
									</td>
					
									<td colspan="2">
										建议提醒:
									</td>
									<td>
										<input type="text" name="adviceClass" id="ssadviceClass"
											style="width: 140px;" class="easyui-combobox"
											data-options="
											editable : false,
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.ADVICECLASS_TAG.ADVICECLASSKEY %>',
											valueField:'id', 
											disabled : true, 
											textField:'text' "/>
									</td>
					
								</tr>
					
								<tr>
									<td>
										维修建议:
									</td>
									<td colspan="5">
										<textarea id="ssadviceContext" name="adviceContext" rows="20" readonly="readonly"
											cols="50" style="width: 700px; height: 160px;"></textarea>
									</td>
								</tr>
					
								<tr>
									<td>
										处理进度:
									</td>
									<td>
										
											<input type="text" name="procesState" id="ssprocesState" class="easyui-combobox" style="width: 140px;"
											data-options="
											url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PROCESSTATE_TAG.PROCESSTATEKEY %>',
											valueField : 'id',
											textField : 'text',
											editable : false,
											disabled : true,
											missingMessage : '处理进度为必填项',
											mode : 'remote'"/></td>
					
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<td>
										处理日期:
									</td>
									<td>
										<input type="text" name="adviceTimeEnd" id="ssadviceTimeEnd"
											readonly="readonly"
											  data-options="
											editable : false,
											disabled : true"
											style="width: 140px;" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										经办人:
									</td>
									<td>
										<input type="text" name="procesProson" id="ssprocesProson"
											readonly="readonly" class="easyui-combobox"
											style="width: 140px;"
											data-options="
											url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
											required : true,
											editable : false,
											disabled : true,
											valueField:'id',  
											textField:'text' " />
									</td>
								</tr>
								<tr>
									<td>
										处理结果:
									</td>
									<td colspan="5">
										<textarea id="ssprocesContext" name="procesContext" rows="20" readonly="readonly"
											cols="50" style="width: 700px; height: 80px;"></textarea>
									</td>
							</table>
						</form>
					</div>
			</div>
		</div>
	</div>
</div>