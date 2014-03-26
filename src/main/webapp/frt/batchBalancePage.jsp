<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!-- 前台收银-->
<script type="text/javascript">
     var parame1 = '<%=Contstants.OPINIONFINISHED_TAG.FINISHED %>';
     var parame2 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/batchBalancePage.js"></script>
<div id="batchBalancePage_showCustom" style="display:none;position:absolute;z-index:2;
		margin-top:624px;margin-left:1px;width:510px;background-color:EEFFFE;height:50px;">
	<form id="batchBalancePage_showCustom_form">
		<table style="min-width: 510px;">
			<tr>
				<td>客户名称</td>
				<td><input type="text" name="customName" readonly="readonly"></td>
				<td>联系电话</td>
				<td><input type="text" name="customTel" readonly="readonly"></td>
				<td>客户地址</td>
				<td><input type="text" name="customAddr" readonly="readonly"></td>
			</tr>
			<tr>
				<td>是否会员</td>
				<td>
					<input type="hidden" id="memberId" name="memberId">
					<center>
						<label id="isMember"></label>
					</center>
				</td>
				<td>会员分类</td>
				<td><input type="text" name="memberClass" readonly="readonly"></td>
				<td>会员等级</td>
				<td><input type="text" name="memberGrade" readonly="readonly"></td>
			</tr>
		</table>
	</form>
</div>
<body class="easyui-layout" onclick="validateIsAccordion();">
    <div region="north"  split="false" style="height:30px;background: #eee;" border="false"  onclick="closeBalance();">
    <a id="add" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="_add();">收款</a>
	<a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
	<a id="clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清除</a>
	<a id="print" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">打印</a>
	<a id="export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
		<span id="button"></span>
      </div>  
	<div region="center" style="background:#eee;" border="false"  onclick="closeBalance();">
			<div class="easyui-layout"
				style="overflow: hidden;width:100%;height:100%;" fit="true"
				border="false">
				<div region="north" title="查询条件"
					style="background:#eee;height:102px;padding:3px;" border="false">
					<form id="batchGatheringQueryForm">
						<table width="1230">
							<tr>
								<td>日期</td>
								<td colspan="3">
									<input type="text" id="addGatheringBeginTime" name="gatheringBeginTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									data-options="
									editable : false"  />至
									<input type="text" id="addGatheringEndTime" name="gatheringEndTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									value="{now}"
									data-options="
									editable : false"  />
								</td>
								<td>结算单号</td>
								<td><input type="text" name="balanceId" class="easyui-validatebox"></td>
								<td>票据类型</td>
								<td><input type="text" name="invoiceType" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
				        			valueField : 'id',
				        			textField : 'text' "/></td>
								<td>票据编号</td>
								<td><input type="text" name="invoiceNo" class="easyui-validatebox"></td>
								<td>开票时间</td>
								<td colspan="3">
								<input type="text" id="addinvoiceBeginTime" name="invoiceBeginTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									data-options="
									editable : false"  />至
									<input type="text" id="addinvoiceEndTime" name="invoiceEndTime" 
			    					class="easyui-datetimebox" style="width: 140px;"
									value="{now}"
									data-options="
									editable : false"  />
									</td>
							</tr>
							<tr>
								<td>车牌照</td>
								<td><input type="text" id="batchGatheringQueryCarId" name="carId" class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote' "/></td>
								<td>VIN号</td>
								<td><input type="text" id="carVin" maxlength="20" name="carVin"
									 class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarVin.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote'"/></td>
								<td>联系人</td>
								<td><input type="text" name="linkMan" class="easyui-validatebox"></td>
								<td>联系人电话</td>
								<td><input type="text" name="linkTel" class="easyui-validatebox"></td>
								<td>客户名称</td>
								<td><input type="text" name="customId"
									class="easyui-combobox"
									data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action', 
									valueField:'id',  
									textField:'text', 
								 	mode : 'remote'" /></td>
								<td>维修类别</td>
								<td><input type="text" name="reptId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllReptype.action',
									valueField:'id',  
									textField:'text' "/></td>
								<td>结算单分类</td>
								<td><input type="text" name="preclearingClass" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSKEY %>',
				        			valueField : 'id',
				        			textField : 'text' "/></td>
							</tr>
							</table>
					</form>
				</div>
				<div region="center" style="background:#EEEEEE;" border="false">
					<div style="width:100%;height:100%;">
						<div style="width:100%;height:538px;" border="false">
							<div id="batchBalancePage_accordion" class="easyui-accordion" data-options="fit:true,border:false"  style="background:#EEEEEE;">
								<div title="批量收款汇总" style="padding:0px;overflow:auto;">
									<div style="width:100%;height:100%;float:left;" border="false" >
											<table id="firstBatchGatheringDatagrid"></table>
									</div>
									<div id="batchBalancePage_hidden"  style="margin-left:-1px;position:relative;width:1px;height:100%;float:left;"
										onmouseout="loadOldBalance(false,'batchBalancePage_oldGatheringDatagrid','oldGatheringDatagrid');"
											 onmouseover="loadOldBalance(true,'batchBalancePage_oldGatheringDatagrid','oldGatheringDatagrid');">
									</div>
								</div>
								<div title="未收款单" data-options="selected:true" style="padding:0px;">
									<table  id="batchGatheringDatagrid"></table>
								</div>
							</div>
						</div>
						<div>
							 <form id="batchGatheringAddForm">
							<table border="0" width="1230" style="min-width: 1230px;">
								<tr>
									<td>客户名称</td>
									<td>
										<input type="hidden" name="customId"/>
										<input type="text" name="customName" readonly="readonly"  
											style="background-color:#EBEBE4;width: 140px;" class="easyui-validatebox" data-options="disabled:true"
													onmouseover="blockOrNone(true,'batchBalancePage_showCustom');"
													 onmouseout="blockOrNone(false,'batchBalancePage_showCustom');"/>
									</td>
									<td>收款单号</td>
									<td><input type="text" id="batchBalancePage_gatheringId" name="gatheringId"  class="easyui-validatebox"
										style="background-color:#EBEBE4;width: 140px;"
									  readonly="readonly"	data-options="required:true,disabled:true"></td>
									<td>收款日期</td>
									<td><input type="text" id="gatheringTime" name="gatheringTime" readonly="readonly"
										class="easyui-datetimebox" style="width: 140px;"
										value="{now}"
										data-options="required:true,disabled:true"></td>
									<td>收款方式</td>
									<td><input type="text" id="batchBalancePage_GatheringWise" name="GatheringWise" class="easyui-combobox"
										style="width: 140px;"
					        			data-options="
					        			required:true,
					        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
					        			valueField : 'id',
					        			textField : 'text' "/></td>
					        		<td>收银员:</td>
					       			<td><input type="text" id="stfId" name="stfId" class="easyui-combobox" readonly="readonly"
					       			style="width: 140px;"
									value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
									data-options="
									disabled:true,
									required:true,
									url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
									valueField:'id',  
									textField:'text' "/></td>
									<td>
										差额结算
									</td>
									<td>
										<input type="checkbox" id="differenceBalance" name="differenceBalance" value="<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>"
											/>
										<div style="display:none;">
											未收清<input type="checkbox" id="unAchieve" disabled="disabled" name="unAchieve" value="<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>">
										</div>
									</td>
								</tr>
								<!--<tr>
									<td>本次结算积分</td>
									<td><input type="text" name="balanceIntegral" readonly="readonly"></td>
									<td>总积分</td>
									<td><input type="text" name="sumIntegral" readonly="readonly"></td>
									<td>可使用积分</td>
									<td><input type="text" name="useIntegral" readonly="readonly"></td>
								</tr>
								--><tr>
									<td>结算单号</td>
									<td>
										<div style="background-color:#EBEBE4;">
											<textarea  rows="2" cols="18" style="width: 140px;"  id="tempPreclrIds"
											 name="tempPreclrIds" readonly="readonly"></textarea>
										</div>
										<div style="margin-left:8px;margin-top:-36px;
											position:absolute;z-index: 1;width:140px;height:36px;">
										</div>
									</td>
									<td>车辆牌照</td>
									<td>
										<div style="background-color:#EBEBE4;">
											<textarea rows="2" cols="18" style="width: 140px;" id="tempCarLicenses" 
											 name="tempCarLicenses" readonly="readonly"></textarea>
										</div>
										<div style="margin-left:10px;margin-top:-36px;position:absolute;
										z-index: 1;width:140px;height:36px;">
										</div>
									</td>
									<td>代付人</td>
									<td>
										<div  style="display: block" id="subFrtCustom">
											<input type="text" style="width: 140px;" id="substituteCustomIda" name="substituteCustomId" class="easyui-combobox"
											data-options="
											disabled:true,
											editable : false,
											url : 'frtOptionsAction!findAllCustom.action',
											missingMessage:'客户名称为必填项',
											valueField:'id',  
											textField:'text' "/>
										</div>
										<div style="display: none" id="subClaimant">
											<input type="text" style="width: 140px;" id="substituteCustomIdb" name="substituteClaimantId" class="easyui-combobox"
											data-options="
											disabled:true,
											editable : false,
											url : 'frtOptionsAction!findAllClaimManufacturers.action',
											missingMessage:'客户名称为必填项',
											valueField:'id',  
											textField:'text' "/>
									</td>
									<td>代付金额</td>
									<td>
										<input type="text" id="substituteMoney" name="substituteMoney" onkeyup="substitutePayment('preclrAmount','substituteMoney');"
										style="width: 140px;" class="easyui-numberbox" data-options="precision:2,groupSeparator:',',disabled:true"/>
									</td>
									<td colspan="2">
										<div  style="display: block" id="subFrtCustomRemove">
											<a id="subFrtCustomRemove_linkbutton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
												 onclick="removeSubStitute('substituteCustomIda','substituteMoney','preclrAmount');">取消代付人</a>										
										</div>
										<div  style="display: none" id="subClaimantRemove">
											<a id="subClaimantRemove_linkbutton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
												 onclick="removeSubStitute('substituteCustomIdb','substituteMoney','preclrAmount');">取消代付人</a>
										</div>
									</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>应收金额</td>
									<td><input type="text" id="preclrAmount" name="preclrAmount" readonly="readonly"
									style="background-color:#EBEBE4;width: 140px;"
									class="easyui-validatebox" data-options="required:true,disabled:true"></td>
									<td>付款金额</td>
									<td><input type="text" id="preclrPayAmount" name="preclrPayAmount" style="width: 140px;"
									class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','"
									 onkeyup="batchAccountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount');"></td>
									<td>找零</td>
									<td><input type="text" name="otherBalance" id="otherBalance" class="easyui-numberbox"
									 style="width: 140px;" readonly="readonly" data-options=" precision:2,groupSeparator:',', disabled:true"></td>
									 <td>实收金额</td>
									<td><input type="text" id="preclrRealAmount" name="preclrRealAmount" readonly="readonly" style="width: 140px;"
									class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:',', disabled:true"></td>
									
									<td colspan="2">
										<div  style="display: block" id="subFrtCustomAdd">
											<a id="subFrtCustomAdd_linkbutton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
												 onclick="addSubstitute('substituteCustomIda','substituteMoney');">添加代付人</a>
										</div>
										<div  style="display: none" id="subClaimantAdd">
											<a id="subClaimantAdd_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
												 onclick="addSubstitute('substituteCustomIdb','substituteMoney');">添加代付人</a>
										</div>
									</td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td>
										收款备注
									</td>
									<td colspan="15">
										<textarea rows="3" cols="196" style="width:1166px;" name="gatheringRemark"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										<input type="hidden" name="sumAmount" />
										<input type="hidden" name="bbgId" />
									</td>
								</tr>
							</table>
						  </form>
						</div>
					</div>		
				</div>
			</div>
						    
	</div>
</body>
<div id="batchBalancePage_oldGatheringDatagrid"
	 style="margin-left:800px;margin-top:132px;position:relative;z-index:1;
	 	background:EEEEEE; display:none;width:700px;height:538px;"
		 ondblclick="blockOrNone(false,'batchBalancePage_oldGatheringDatagrid');"
		 onmouseover="blockOrNone(true,'batchBalancePage_oldGatheringDatagrid');">
	<div style="width:700px;height:538px;">
		<table id="oldGatheringDatagrid">
	  	</table>
	</div>
</div>
