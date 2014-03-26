<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!-- 前台收银-->
<script type="text/javascript">
    var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/substituteBatchBalance.js"></script>
<div id="substituteBatchBalance_showCustom" style="display:none;position:absolute;z-index:2;
		margin-top:624px;margin-left:1px;width:510px;background-color:EEFFFE;height:50px;">
	<form id="substituteBatchBalance_showCustom_form">
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
<body class="easyui-layout">
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
					<form id="batchSubstituteGatheringQueryForm">
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
								<td><input type="text" id="batchSubstituteGatheringQueryCarId" name="carId" class="easyui-combobox" data-options="
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
				<div region="center" style="background:#eee;" border="false">
					<div style="width:100%;">
						<div id="substituteBatchBalance_tabs" class="easyui-tabs"  data-options="border:false"
									 style="height:538px;background:#EEEEEE;">
							<div title="批量代付收款汇总" 
									 id="firstBatchSubstituteGatheringDatagrid_tabs">
								<div  style="width:100%;height:100%;float:left;" border="false" >
									<table id="firstBatchSubstituteGatheringDatagrid"></table>
								</div>
								<div id="substituteBatchBalance_hidden1"  style="margin-left:-1px;position:relative;width:1px;height:100%;float:left;"
									onmouseout="loadOldBalance(false,'substituteBatchBalance_oldGatheringDatagrid','oldBatchSubstituteGatheringDatagrid');"
										 onmouseover="loadOldBalance(true,'substituteBatchBalance_oldGatheringDatagrid','oldBatchSubstituteGatheringDatagrid');">
								</div>
							</div>
							<div title="代付结算单"
									id="batchSubstituteGatheringDatagrid_tabs" >
									<div  style="width:100%;height:100%;float:left;" border="false" >
										<table id="batchSubstituteGatheringDatagrid"></table>
									</div>
							</div>
							<div title="代付批量结算单"
									id="secondSubstituteGatheringDatagrid_tabs" >
									<div  style="width:100%;height:100%;float:left;" border="false" >
										<table id="secondSubstituteGatheringDatagrid"></table>
									</div>
							</div>
						</div>
						
						<div>
							 <form id="batchSubstituteGatheringAddForm">
							<table  border="0" width="1230" style="min-width: 1230px;">
								<tr>
									<td>客户名称</td>
									<td>
										<input type="hidden" name="customId"/>
										<input type="text" name="customName" readonly="readonly"  
											style="background-color:#EBEBE4;width:140px;" class="easyui-validatebox" data-options="disabled:true"
													onmouseover="blockOrNone(true,'substituteBatchBalance_showCustom');"
													 onmouseout="blockOrNone(false,'substituteBatchBalance_showCustom');"/>
									</td>
									<td>收款单号</td>
									<td><input type="text" id="substituteBatchBalance_gatheringId" name="gatheringId"
									  class="easyui-validatebox"  readonly="readonly" style="background-color:#EBEBE4;width:140px;"
										data-options="required:true,disabled:true"></td>
									<td>收款日期</td>
									<td><input type="text" id="gatheringTime" name="gatheringTime" readonly="readonly"
										class="easyui-datetimebox" style="width: 140px;"
										value="{now}"
										data-options="required:true,disabled:true"></td>
									<td>收款方式</td>
									<td><input type="text" id="substituteBatchBalance_GatheringWise" name="GatheringWise"
										 class="easyui-combobox" style="width:140px;"
					        			data-options="
					        			required:true,
					        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
					        			valueField : 'id',
					        			textField : 'text' "/></td>
					        		<td>收银员:</td>
					       			<td><input type="text" id="stfId" name="stfId" class="easyui-combobox" readonly="readonly"
					       			style="width:140px;"
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
										<input type="checkbox" id="differenceBalance" name="differenceBalance" value="<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>"/>
										<div style="display:none;">
											未收清<input type="checkbox" id="unAchieve" disabled="disabled" name="unAchieve" value="<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>">
										</div>			
									</td>
								</tr>
								
								<tr>
									<td>结算单号</td>
									<td>
										<div style="background-color:#EBEBE4;">
											<textarea  rows="2" cols="18" id="tempPreclrIds" style="width:140px;"
											 name="tempPreclrIds" readonly="readonly"></textarea>
										</div>
										<div style="margin-left:4px;margin-top:-36px;position:absolute;
												z-index: 1;width:140px;height:36px;">
										</div>
									</td>
									<td>应收金额</td>
									<td><input type="text" id="preclrAmount" name="preclrAmount" readonly="readonly" style="width:140px;"
									class="easyui-validatebox" data-options="required:true,disabled:true"></td>
									<td>付款金额</td>
									<td><input type="text" id="preclrPayAmount" name="preclrPayAmount" style="width:140px;"
									class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','"
									 onkeyup="batchAccountBalance('preclrAmount','preclrPayAmount','otherBalance');batchRealMoney('preclrAmount','preclrPayAmount','preclrRealAmount');"></td>
									<td>找零</td>
									<td><input type="text" name="otherBalance" id="otherBalance" class="easyui-numberbox" style="width:140px;"
									 readonly="readonly" data-options=" precision:2,groupSeparator:',', disabled:true"></td>
									<td>实收金额</td>
									<td><input type="text" id="preclrRealAmount" name="preclrRealAmount" readonly="readonly" style="width:140px;"
									class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:',', disabled:true"></td>
									
									<td>批量代付单号</td>
									<td><input type="text" id="substituteBatchBalance_sspId" name="sspId" readonly="readonly"  
									 style="background-color:#EBEBE4;width:140px;" class="easyui-validatebox" data-options="disabled:true"></td>
								</tr>
								<tr >
									<td>
										收款备注
									</td>
									<td colspan="12">
										<textarea rows="5" style="width:1170px;" name="gatheringRemark"></textarea>
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
							--></table>
						  </form>
						</div>
					</div>		
				</div>
			</div>
						    
	</div>
</body>

<div id="substituteBatchBalance_oldGatheringDatagrid"
	 style="margin-left:800px;margin-top:132px;position:relative;z-index:1;
	 	background:EEEEEE; display:none;width:700px;height:538px;"
		 ondblclick="blockOrNone(false,'substituteBatchBalance_oldGatheringDatagrid');"
		 onmouseover="blockOrNone(true,'substituteBatchBalance_oldGatheringDatagrid');">
	<div style="width:700px;height:538px;">
		<table id="oldBatchSubstituteGatheringDatagrid">
	  	</table>
	</div>
</div>