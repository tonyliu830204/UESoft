<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>车辆保单管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/sell/SellInsurance/sellInsurance.js"></script>
		<script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    var tbtitle;
		    </script>
		<script type="text/javascript">
		var tbtitle;
		var addDiv;
			function addSellInfo()
			{
			 addDiv = $('<div/>');
			addDiv.dialog({
				title: '请选择',   
			    width: 650,   
			    height:450,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/SellInsurance/sellCarList.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
				
			var sgsm_d1;
			function adddis()
			{
			 sgsm_d1 = $('<div/>');
			 sgsm_d1.dialog({
				title: '请选择',   
			    width:650,   
			    height: 450,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/SellInsurance/queryInsurerInfo.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
				var sgsm_d2;
			function adddis2()
			{
			 sgsm_d2 = $('<div/>');
			 sgsm_d2.dialog({
				title: '请选择',   
			    width:650,   
			    height: 450,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/SellInsurance/queryInsurerInfoS.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
</script>

	</head>
	<body>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="north" split="false"
				style="height: 30px; background: #eee;" border="false">
				<privilege:enable code="INSURANCE_ADD">
					<a id='_add' href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" id="_add"
						onclick="addPersonnel(1);">新增</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_REMOVE">
					<a id='_remove' href="javascript:void(0);"
						class="easyui-linkbutton" iconCls="icon-remove" plain="true"
						id="_remove" onclick="remove_()">删除</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_EDIT">
					<a id='_update' href="javascript:void(0);"
						class="easyui-linkbutton" iconCls="icon-edit" plain="true"
						id="_update" onclick="addPersonnel(2);">修改</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_SEARCH">
					<a id='_search' href="javascript:void(0);"
						class="easyui-linkbutton" iconCls="icon-search" plain="true"
						onclick="queryCarReserve();">查询</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_CLEAR">
					<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" plain="true"
						onclick="clearSearchCondition();">清空</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_EXAMINE">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-examine" plain="true" id="_examine"
						onclick="examineState();">审核</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_PRINT">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-print" plain="true" id="_print" onclick="_config();">打印</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_EXPORT">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-export" plain="true" id="_export"
						onclick="_except();">导出</a>
				</privilege:enable>
				<privilege:enable code="INSURANCE_ZJS">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconcls="icon-examine" plain="true" id="sellSum"
						onclick="sellSum();">转结算</a>
				</privilege:enable>
				<span id="saveOrCancelBtn"></span>
				<br />
			</div>
			<div region="center" split="false" border="false">
				<div id="tt" class="easyui-tabs" fit="true" border="false">
					<div title="保单汇总" style="display: block;" fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true" border="false">
							<div data-options="region:'north',title:'查询条件'"
								style="overflow: hidden; padding: 1px; background: #eee; height: 100px;"
								border="false">
								<form id="queryF">
									<fieldset>
										<table>
											<tr>
												<td width="75px">
													代保日期：
												</td>
												<td>
													<input type="text" class="Wdate"
														onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserveDete2\',{d:-1})}'})"
														name="dafeDate" id="reserveDete" style="width: 90px;" />
													至
												</td>
												<td>
													<input type="text" class="Wdate"
														onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserveDete\',{d:1})}'})"
														name="dafeDate2" id="reserveDete2" style="width: 90px;" />
												</td>



												<td>
													保险公司：
												</td>
												<td colspan="3">
													<input id="dName1" name="insurerName"
														style="background-color: #c0d8d8; width: 280px"
														onkeypress=" if(event.keyCode==13) { adddis(); return false;}"
														style="background-color:#c0d8d8;" />
													<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"
														onclick="adddis();" />
													<input name="insurer" id="did1" type="hidden" />
												</td>
												<td>
													保险单号：
												</td>
												<td colspan="2">
													<input type="text" name="insurancePolicy"
														style="width: 130px;" />
												</td>

											</tr>
											<tr>
												<td width="75px">
													车牌号：
												</td>
												<td colspan="2">
													<input type="text" name="carLicensePlate"
														style="width: 200px;" />
												</td>
												<td>
													客户：
												</td>
												<td>
													<input type="text" name="customName" style="width: 110px;" />
													<input type="hidden" name="customId" />
												</td>
												<td>
													VIN号：
												</td>

												<td>
													<input type="text" name="vin" style="width: 130px;" />
												</td>
												<td>
													保单分类：
												</td>
												<td>
													<input type="text" name="safeType" style="width: 130px;"
														class="easyui-combobox"
														data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SAFETYPE%>',
														valueField:'id',   
											    		textField:'text',
											    		mode : 'remote',
											    		validType:'isSelected[\'#safeType\']',
														invalidMessage : '请从下拉框中保单分类'" />
												</td>
											</tr>
										</table>
									</fieldset>
								</form>
							</div>
							<div id="qingdan" region="center" style="background: #eee;"
								border="false">
								<table id="allocateList" name="allocateList"></table>
							</div>
						</div>
					</div>
					<div title="保单明细" style="display: block;" closable="false"
						fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true">
							<div region="west" split="false"
								style="overflow: hidden; background: #eee; width: 600px;"
								border="false">
								<form id="StForm" method="post">
									<input type="hidden" id="insuranceId" name="insuranceId" />
									<br>
									<table>
										<tr>
											<td width="110px">
												代保日期：
											</td>
											<td>
												<input type="text" class="Wdate"   readonly="readonly"
													style="width: 150px;"
													name="dafeDate" id="allocateDate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd});" />
											</td>
											<td>
												车辆销售单号：
											</td>
											<td colspan="2">
												<input id="sellId" name="sell_code"
													style="background-color: #c0d8d8; width: 130px"
													disabled="disabled" readonly="readonly"
													class="easyui-validatebox"
													onkeypress=" if(event.keyCode==13) {  addSellInfo(); return false;}"
													style="background-color:#c0d8d8;" />
												<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="carSellImg" />
												<input type="hidden" name="xs_Car_Sel_Id" id="xs_Car_Sel_Id">

											</td>
										</tr>
										<tr>

											<td>


												保险单号：
											</td>
											<td colspan="4">
												<input type="text" class="easyui-validatebox" id="dNameqq"
													name="insurancePolicy" disabled="disabled"
													style="width: 425px;"
													data-options="validType:'multiple[\'length[0,30]\']'"
													maxlength="31" />
											</td>
										</tr>
										<tr>
											<td>
												保险公司：
											</td>
											<td colspan="3">

												<input id="dName2" name="insurerName"
													class="easyui-validatebox" disabled="disabled"
													readonly="readonly"
													style="background-color: #c0d8d8; width: 295px"
													onkeypress=" if(event.keyCode==13) { adddis2(); return false;}"
													style="background-color:#c0d8d8;" />
												<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="inImg" />
												<input name="insurerCodes" id="dcode" type="text"
													disabled="disabled" style="width: 110px" />

												<input name="insurer" id="did2" type="hidden" />
											</td>

										</tr>
										<tr>


											<td>
												被保险人：
											</td>
											<td>

												<input type="text" id="id1" name="customName"
													readonly="readonly" disabled="disabled" name="allocateType"
													style="width: 150px;" />

											</td>

											<td>

												组织代码：
											</td>
											<td>
												<input type="text" id="numbers" name="numbers"
													disabled="disabled" readonly="readonly"
													style="width: 150px;" />
											</td>
										</tr>
										<tr>

											<td>
												被保地址：
											</td>
											<td>

												<input type="text" name="customAddress" id="id2"
													style="width: 150px;" disabled="disabled"
													readonly="readonly" />
											</td>
											<td>
												身份证号码：
											</td>
											<td>
												<input type="text" id="id3" name="customCredentials"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>
										</tr>
										<tr>
											<td>
												联系电话：
											</td>
											<td>
												<input type="text" id="id4" name="customTelephone"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>
											<td>
												邮政编码：
											</td>
											<td>
												<input type="text" id="id13" name="customZipcode"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>


										</tr>
										<tr>
											<td>
												车辆牌照：
											</td>
											<td>
												<input type="text" id="id5" name="carLicensePlate"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>
											<td>
												联系人：
											</td>
											<td>
												<input type="text" id="contactsPerson" name="contactsPerson"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>


										</tr>

										<tr>
											<td>
												车身颜色：
											</td>
											<td>
												<input type="text" id="id6" name="carColorName"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>
											<td>
												已行驶里程：
											</td>
											<td>
												<input type="text" id="id10" name="distance"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>

										</tr>
										<tr>
											<td>
												车架号(VIN)：
											</td>
											<td>
												<input type="text" id="id7" name="vin" readonly="readonly"
													disabled="disabled" style="width: 150px;" />

											</td>
											<td>
												座位/吨位：
											</td>
											<td>
												<input type="text" id="id12" name="limitLoadNum"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>

										</tr>
										<tr>
											<td>
												发动机号：
											</td>
											<td>
												<input type="text" id="id8" name="engineNumber"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>
											<td>
												排量/功率：
											</td>
											<td>
												<input type="text" id="id11" name="outprint"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>


										</tr>

										<tr>
											<td>
												使用性质：
											</td>
											<td>
												<input type="text" id="id9" name="xsCustomApplicationN"
													readonly="readonly" disabled="disabled"
													style="width: 150px;" />

											</td>

											<td>
												保单分类：
											</td>
											<td>
												<input type="text" id="safeType" name="safeType"
													disabled="disabled" style="width: 150px;"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.SAFETYPE%>',
														
											    		valueField:'id',   
											    		textField:'text',
											    		mode : 'remote',
											    		validType:'isSelected[\'#safeType\']',
														invalidMessage : '请从下拉框中保单分类'" />

											</td>





										</tr>
										<tr>
											<td>
												保险期限：
											</td>
											<td>
												<input type="text" class="Wdate" style="width: 73px;"
													disabled="disabled" class="easyui-validatebox"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reDete2\',{d:-1})}'})"
													name="insurerStartDate" id="reDete" />


												<input type="text" class="Wdate" style="width: 73px;"
													disabled="disabled" class="easyui-validatebox"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'reDete\',{d:1})}'})"
													name="insurerEndDate" id="reDete2" />
											</td>

											<td>
												交强险返点：
											</td>
											<td>
												<input type="text" id="inCost" name="inCost"
													readonly="readonly" disabled="disabled"
													class="easyui-numberbox" precision="2" maxlength="12"
													style="width: 150px;" data-options="validType:'monery'" />

											</td>


										</tr>
										<tr>
											<td>
												登记年月：
											</td>
											<td>

												<input type="text" class="Wdate" disabled="disabled"
													style="width: 150px;"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'recordDate\',{d:1})}'})"
													name="recordDate" id="recordDate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd})" />

											</td>

											<td>
												商业险返点：
											</td>
											<td>
												<input type="text" id="buysnessCost" readonly="readonly"
													name="buysnessCost" class="easyui-numberbox" precision="2"
													disabled="disabled" style="width: 150px;"
													data-options="validType:'monery'" maxlength="12" />

											</td>



										</tr>
										<tr>
											<td>
												保险费：
											</td>
											<td>
												<input type="text" id="safeAmount" name="safeAmount"
													class="easyui-numberbox" precision="2" disabled="disabled"
													style="width: 150px;" data-options="validType:'monery'"
													maxlength="12" />

											</td>

											<td>
												交强险出单保费：
											</td>
											<td>
												<input type="text" id="rr" name="trafficCharge"
													class="easyui-numberbox" precision="2" disabled="disabled"
													style="width: 150px;" data-options="validType:'monery'"
													maxlength="12" />

											</td>

										</tr>
										<tr>
											<td>
												保单保费：
											</td>
											<td>
												<input type="text" id="safeCost" name="safeCost"
													class="easyui-numberbox" precision="2" data-options="validType:'monery'"
													disabled="disabled" style="width: 150px;" maxlength="12" />

											</td>

											<td>
												车船使用税：
											</td>
											<td>
												<input type="text" id="vehiclevesselTax"
													name="vehiclevesselTax"  class="easyui-numberbox" precision="2"
													disabled="disabled" style="width: 150px;"
													data-options="validType:'monery'" maxlength="12" />

											</td>

										</tr>
										<tr>
											<td>
												保险上交：
											</td>
											<td>
												<input type="text" id="ss" name="sum" disabled="disabled"
													style="width: 150px; color: purple;"  class="easyui-numberbox" precision="2"/>


											</td>
											<td>
												<a href="javascript:void(0);" class="easyui-linkbutton"
													style="width: 100px; color: blue;" onclick="countSum();">计算上交费用</a>
											</td>
										</tr>
										<tr>
											<td>
												客户付款：
											</td>
											<td>
												<input type="text" id="customCost" name="customCost"
													data-options="validType:'monery'" disabled="disabled"
													style="width: 150px;" class="easyui-numberbox" precision="2"
													maxlength="12" />

											</td>
											<td>
												客户返利：
											</td>
											<td>
												<input type="text" id="customReturncost"
													name="customReturncost" data-options="validType:'monery'"
													disabled="disabled" style="width: 150px;"
													 class="easyui-numberbox" precision="2" maxlength="12" />

											</td>
										</tr>
										<tr>
											<td>
												业务提成：
											</td>
											<td>
												<input type="text" id="extract" name="extract"
													data-options="validType:'monery'" disabled="disabled"
													style="width: 150px;"  class="easyui-numberbox" precision="2"
													maxlength="12" />

											</td>
											<td>
												保单利润：
											</td>
											<td>
												<input type="text" id="profit" name="profit"
													data-options="validType:'monery'" class="easyui-numberbox" precision="2"
													disabled="disabled" style="width: 150px;" maxlength="12" />

											</td>


										</tr>
										<tr>
											<td>
												保险员：
											</td>
											<td>
												<input type="text" id="insuranceAgent" name="insuranceAgent"
													disabled="disabled" style="width: 150px;" maxlength="10" />

											</td>
											<td>
												经办人：
											</td>
											<td>
												<input type="text" id="pp" name="person"
													class="easyui-combobox"
													value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
													style="background-color: #c0d8d8; width: 150px"
													data-options="
													url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#pp\']',
													invalidMessage : '请从下拉框中选择经办人'" />


											</td>



										</tr>

										<tr>
											<td>
												备注：
											</td>
											<td colspan="4">
												<textarea name="remark" disabled="disabled"
													class="easyui-validatebox"
													data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"
													style="width: 425px;resize:none;" maxlength="51"></textarea>


											</td>


										</tr>
									</table>
								</form>
							</div>

							<div id="mingxi" region="center" title="保险信息"
								style="background: #eee;" border="false">
								<table id="mingxiList" name="mingxiList"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
