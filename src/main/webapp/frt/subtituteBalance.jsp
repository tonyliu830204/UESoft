<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>代付收款</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	 var parame1 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
	 var parame2 = '<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>';
	 var parame3 = '<%=Contstants.OPINIONFINISHED_TAG.FINISHED %>';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/subtituteBalance.js"></script>
</head>
<body class="easyui-layout" onclick="validateIsAccordion();">&nbsp;&nbsp; &nbsp;
    <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
    <privilege:enable code="SUBSTITUTEBALANCE_ADD">
      	<a id="add" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveSubstituteBalance();">收款</a>
   </privilege:enable>
    <privilege:enable code="SUBSTITUTEBALANCE_SEARCH">
      	<a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
   </privilege:enable>
	<privilege:enable code="SUBSTITUTEBALANCE_CLEAR">
      	<a id="clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
   </privilege:enable>
	<privilege:enable code="SUBSTITUTEBALANCE_PRINT">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
   </privilege:enable>
	<privilege:enable code="SUBSTITUTEBALANCE_SET">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
   </privilege:enable>
   <privilege:enable code="SUBSTITUTEBALANCE_EXPORT">
      	<a id="export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
   </privilege:enable>
		<span id="button"></span>
      </div>  
	<div region="center" style="background:#eee;" border="false">
			<div class="easyui-layout"
				style="overflow: hidden;width:100%;height:100%;" fit="true"
				border="false">
				<div region="north" title="查询条件"
					style="background:#eee;height:110px;overflow:hidden;" border="false">
					<form id="substituteGatheringQueryForm">
						<table>
							<tr>
								<td>结算时间:</td>
								<td colspan="3">
									<input id="addGatheringBeginTime" name="gatheringBeginTime"  style="width:140px;" class="Wdate"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'addGatheringEndTime\',{d:-1})}'})"/>
					                                              至<input id="addGatheringEndTime" name="gatheringEndTime" style="width:140px;" class="Wdate"
					                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'addGatheringBeginTime\',{d:0})}'})"/>
								</td>
								<td>结算单号:</td>
								<td><input type="text" name="balanceId" class="easyui-validatebox"></td>
								<td>票据类型:</td>
								<td><input type="text" id="addInvoiceType" name="invoiceType" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
				        			valueField : 'id',
				        			textField : 'text',
				        			validType:'isSelected[\'#addInvoiceType\']',
									invalidMessage : '请从下拉框中选择票据类型',
									mode:'remote'  "/></td>
								<td>票据编号:</td>
								<td><input type="text" name="invoiceNo" class="easyui-validatebox"></td>
							</tr>
							<tr>
								<td>车牌照:</td>
								<td><input type="text" id="substituteGatheringQueryCarId" name="carId" class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarLicenseAsCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote' "/></td>
								<td>VIN号:</td>
								<td><input type="text" id="substituteGatheringQueryCarVin" id="carVin" maxlength="20" name="carVin"
									 class="easyui-combobox" data-options="
									url : 'frtOptionsAction!findAllCarVin.action',
									valueField : 'id',
									textField : 'text',
									validType:'isSelected[\'#substituteGatheringQueryCarVin\']',
									invalidMessage : '请从下拉框中选择VIN号',
									mode:'remote' "/></td>
								<td>联系人:</td>
								<td><input type="text" name="linkMan" class="easyui-validatebox"></td>
								<td>联系人电话:</td>
								<td><input type="text" name="linkTel" class="easyui-validatebox"></td>
								<td>客户名称:</td>
								<td><input type="text" id="substituteGatheringQueryCustomId" name="customId"
									class="easyui-combobox"
									data-options="
									url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action', 
									valueField:'id',  
									textField:'text', 
								 	validType:'isSelected[\'#substituteGatheringQueryCustomId\']',
									invalidMessage : '请从下拉框中选择客户名称',
									mode:'remote' " /></td>
							</tr>
							<tr>
							    <td>开票时间:</td>
								<td colspan="3">
								<input id="addinvoiceBeginTime" name="invoiceBeginTime"  style="width:140px;" class="Wdate"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'addinvoiceEndTime\',{d:-1})}'})"/>
					                                              至<input id="addinvoiceEndTime" name="invoiceEndTime" style="width:140px;" class="Wdate"
					                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'addinvoiceBeginTime\',{d:0})}'})"/>
								</td>
								<td>维修类别:</td>
								<td><input type="text" id="substituteGatheringQueryReptId" name="reptId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllReptype.action',
									valueField:'id',  
									textField:'text',
									validType:'isSelected[\'#substituteGatheringQueryReptId\']',
									invalidMessage : '请从下拉框中选择维修类别',
									mode:'remote'  "/></td>
								<td>结算单分类:</td>
								<td><input type="text" id="substituteGatheringQueryPreclearingClass" name="preclearingClass" class="easyui-combobox"
				        			data-options="
				        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSKEY %>',
				        			valueField : 'id',
				        			textField : 'text',
				        			validType:'isSelected[\'#substituteGatheringQueryPreclearingClass\']',
									invalidMessage : '请从下拉框中选择结算单分类',
									mode:'remote'  "/></td>
							</tr>
							</table>
					</form>
				</div>
				<div region="center" style="background:#eee;" border="false">
						<!--<div id="subtituteBalance_tabs" class="easyui-tabs"  data-options="border:false"
									 style="height:538px;background:#EEEEEE;">
							<div title="代付结算单" 
									 id="substituteGatheringDatagrid_tabs">
								<div  style="width:100%;height:100%;float:left;" border="false" >
									<table id="substituteGatheringDatagrid"></table>
								</div>
								<div id="subtituteBalance_hidden2"  style="margin-left:-1px;position:relative;width:1px;height:100%;float:left;"
									onmouseout="loadOldBalance(false,'subtituteBalance_oldGatheringDatagrid','oldSubstituteGatheringDatagrid');"
										 onmouseover="loadOldBalance(true,'subtituteBalance_oldGatheringDatagrid','oldSubstituteGatheringDatagrid');">
								</div>
							</div>
							<div title="代付批量结算单"
									id="firstSubstituteGatheringDatagrid_tabs" >
									<div  style="width:100%;height:100%;float:left;" border="false" >
										<table id="firstSubstituteGatheringDatagrid"></table>
									</div>
									<div id="subtituteBalance_hidden1"  style="margin-left:-1px;position:relative;width:1px;height:100%;float:left;"
										onmouseout="loadOldBalance(false,'subtituteBalance_oldGatheringDatagrid','oldSubstituteGatheringDatagrid');"
											 onmouseover="loadOldBalance(true,'subtituteBalance_oldGatheringDatagrid','oldSubstituteGatheringDatagrid');">
									</div>
							</div>
						</div>
						-->
						<div class="easyui-layout"
							style="overflow: hidden;width:100%;height:100%;" fit="true"
							border="false">
							<div id="substituteGatheringMainDatagrid_center" region="center" border="false" style="background:#eeeeee;">
									<table id="substituteGatheringMainDatagrid"></table>
							</div>
							<div region="south" title="详细信息" style="height:240px;background:#eeeeee;" border="false">
									<div id="substituteGatheringDatagrid_center"  style="width:60%;height:100%;float:left;" border="false" >
										<table id="substituteGatheringDatagrid"></table>
									</div>
									<div id="oldSubstituteGatheringDatagrid_center" style="width:40%;height:100%;">
										<table id="oldSubstituteGatheringDatagrid"></table>
									</div>
							</div>
						</div>
				</div>
			</div>
						    
	</div>
</body>
</html>
	<div id="substituteGatheringAdd" title="收款" data-options="iconCls:'icon-add',modal:true"
									style="display:none;padding: 5px; width: 710px;height:280px;">
		 <div style="width: 690px;height:200px;">
		 	<form id="substituteGatheringAddForm">
				<table  border="0" style="width: 690px;height:200px;">
					<tr>
						<td>客户名称:</td>
						<td>
							<input type="hidden" name="customId"/>
							<input type="text" name="customName" readonly="readonly"   style="width:140px;background-color:#EBEBE4;"
								 class="easyui-validatebox" data-options="disabled:true"
										onmouseover="blockOrNone(true,'subtituteBalance_showCustom');"
										 onmouseout="blockOrNone(false,'subtituteBalance_showCustom');"/>
						</td>
						<td>收款单号:</td>
						<td><input type="text" id="subtituteBalance_gatheringId" name="gatheringId" readonly="readonly"    style="width:140px;background-color:#EBEBE4;"
						class="easyui-validatebox" data-options="required:true,disabled:true"></td>
						<td>收款日期:</td>
						<td><input type="text" id="gatheringTime" name="gatheringTime" readonly="readonly"
							class="easyui-datetimebox" style="width: 140px;"
							value="{now}"
							data-options="required:true,disabled:true"></td>
					</tr>
					<tr>
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
						<td>代付收款单号:</td>
						<td><input type="text" id="substituteBalance_sspId" name="sspId" readonly="readonly"    style="width:140px;background-color:#EBEBE4;"
						class="easyui-validatebox" data-options="required:true,disabled:true"></td>
						<td>应收金额:</td>
						<td><input type="text" id="preclrAmount" name="preclrAmount" readonly="readonly"
						  style="width:140px;background-color:#EBEBE4;" class="easyui-validatebox" data-options="required:true,disabled:true"></td>
					</tr>
					<tr>
						<td>收款方式:</td>
						<td><input type="text" id="subtituteBalance_GatheringWise" name="GatheringWise" class="easyui-combobox"
						 style="width:140px;"
		        			data-options="
		        			required:true,
		        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
		        			valueField : 'id',
		        			textField : 'text' "/></td>
						<td>储备金余额:</td>
						<td>
						<input type="hidden" name="hiveId"/>
						<input type="text" id="hiveBalance"  style="width:140px;background-color:#EBEBE4;" name="hiveBalance" readonly="readonly">
						</td>
						<td>储备金使用:</td>
						<td><input type="text" id="hiveUse" name="hiveUse" style="width:140px;"
							class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','"
							onkeyup="opinionMoney('hiveUse','hiveBalance','preclrPayAmount','preclrAmount');"></td>	
					</tr>
					
					<tr>
						<td>付款金额:</td>
						<td><input type="text" id="preclrPayAmount" name="preclrPayAmount"  style="width:140px;"
						class="easyui-validatebox" data-options="required:true,precision:2,groupSeparator:','"
						 onblur="accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount','hiveUse');"></td>
						<td>找零:</td>
						<td><input type="text" name="otherBalance" id="otherBalance" class="easyui-numberbox"
						  style="width:140px;" readonly="readonly" data-options=" precision:2,groupSeparator:',', disabled:true"></td>
						<td>实收金额:</td>
						<td><input type="text" id="preclrRealAmount" name="preclrRealAmount" readonly="readonly"
						  style="width:140px;" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:',', disabled:true"></td>
					</tr>
					<tr>
						<td>
							收款备注:
						</td>
						<td colspan="7">
							<textarea rows="4" style="width:620px;" name="gatheringRemark"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<div style="display:none;">
								未收清<input type="checkbox" class="easyui-validatebox" id="unAchieve" disabled="disabled">
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
				--></table>
			  </form>
		 </div>
	  	<div id="subtituteBalance_showCustom" style="display:none;position:relative;z-index:1;
			margin-top:-170px;margin-left:8px;width:510px;background-color:EEFFFE;height:50px;">
			<form id="subtituteBalance_showCustom_form">
				<table style="min-width: 520px;">
					<tr>
						<td>客户名称:</td>
						<td><input type="text" name="customName" readonly="readonly"></td>
						<td>联系电话:</td>
						<td><input type="text" name="customTel" readonly="readonly"></td>
						<td>客户地址:</td>
						<td><input type="text" name="customAddr" readonly="readonly"></td>
					</tr>
					<tr>
						<td>是否会员:</td>
						<td>
							<input type="hidden" id="memberId" name="memberId">
							<center>
								<label id="isMember"></label>
							</center>
						</td>
						<td>会员分类:</td>
						<td><input type="text" name="memberClass" readonly="readonly"></td>
						<td>会员等级:</td>
						<td><input type="text" name="memberGrade" readonly="readonly"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

<!--<div id="subtituteBalance_oldGatheringDatagrid"
	 style="margin-left:800px;margin-top:132px;position:relative;z-index:1;
	 	background:EEEEEE; display:none;width:700px;height:538px;"
		 ondblclick="blockOrNone(false,'subtituteBalance_oldGatheringDatagrid');"
		 onmouseover="blockOrNone(true,'subtituteBalance_oldGatheringDatagrid');">
	<div style="width:700px;height:538px;">
		<table id="oldSubstituteGatheringDatagrid">
	  	</table>
	</div>
</div>
-->