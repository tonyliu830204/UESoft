<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>前台收银</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		var parame1 = '<%=Contstants.OPINIONFINISHED_TAG.UNFINISHED %>';
		var parame2 = '<%=Contstants.OPINIONFINISHED_TAG.FINISHED %>';
		var parame3 = '<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>';
		var parame4 = '<%=Contstants.PAIDWAY.PAYCASH %>';
		var tempId='<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEASRECEPTION %>';
		var tempId2='<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEASOLDMONEY %>';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/balancePage.js"></script>
  </head>

<body class="easyui-layout">
    <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
    <privilege:enable code="PROSCENIUMGATHERING_ADD">
	    <a id="add" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="saveGatheringBalance();">收款</a>
   </privilege:enable>
    <privilege:enable code="PROSCENIUMGATHERING_SEARCH">
	    <a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
   </privilege:enable>
	<privilege:enable code="PROSCENIUMGATHERING_CLEAR">
	    <a id="clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
   </privilege:enable>
	<privilege:enable code="PROSCENIUMGATHERING_REDO">
	    <a id="redo" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="modifyTransBalance();">转结算</a>
   </privilege:enable>
	<privilege:enable code="PROSCENIUMGATHERING_PRINT">
	    <a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
   </privilege:enable>
	<privilege:enable code="PROSCENIUMGATHERING_SET">
      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
   </privilege:enable>
   <privilege:enable code="PROSCENIUMGATHERING_EXPORT">
      	<a id="export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
   </privilege:enable>
		<span id="button"></span>
      </div>  
	<div region="center" style="background:#eee;" border="false">
			<div class="easyui-layout"
				style="overflow: hidden;width:100%;height:100%;" fit="true"
				border="false">
				<div region="north" title="查询条件"
					style="background:#eee;height:110px;overflow: hidden;" border="false">
					<form id="gatheringQueryForm">
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
				        			url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
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
								<td><input type="text" id="gatheringQueryCarId" name="carLicense" class="easyui-combobox" data-options="
									url : '${pageContext.request.contextPath }/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
									valueField : 'id',
									textField : 'text',
									mode : 'remote' "/></td>
								<td>VIN号:</td>
								<td><input type="text" id="carVin" maxlength="20" name="carVin"
									 class="easyui-combobox" data-options="
									url : '${pageContext.request.contextPath}/frtOptionsAction!findAllCarVin.action',
									valueField : 'text',
									textField : 'text',
									mode : 'remote'"/></td>
								<td>联系人:</td>
								<td><input type="text" name="linkMan" class="easyui-validatebox"></td>
								<td>联系人电话:</td>
								<td><input type="text" name="linkTel" class="easyui-validatebox"></td>
								<td>客户名称:</td>
								<td><input type="text" id="addCustomId" name="customId"
									class="easyui-combobox"
									data-options="
									url:'${pageContext.request.contextPath}/${pageContext.request.contextPath }/frtOptionsAction!findAllCustom.action', 
									validType:'isSelected[\'#addCustomId\']',
									invalidMessage : '请从下拉框中选择客户名称',
									valueField:'id',  
									textField:'text', 
								 	mode : 'remote'" /></td>
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
								<td><input type="text" id="addReptId" name="reptId" class="easyui-combobox"
									data-options="
									url : '${pageContext.request.contextPath }/frtOptionsAction!findAllReptype.action',
									valueField:'id',  
									textField:'text',
									validType:'isSelected[\'#addReptId\']',
									invalidMessage : '请从下拉框中选择维修类别',
									mode:'remote'  "/></td>
								<td>结算单分类:</td>
								<td><input type="text" id="addPreclearingClass" name="preclearingClass" class="easyui-combobox"
				        			data-options="
				        			url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSKEY %>',
				        			valueField : 'id',
				        			textField : 'text',
				        			validType:'isSelected[\'#addPreclearingClass\']',
									invalidMessage : '请从下拉框中选择结算单分类',
									mode:'remote'  "/>
								</td>
							</tr>
							</table>
					</form>
				</div>
				<div region="center" style="background:#EEEEEE;" border="false">
						<div class="easyui-layout"
							style="overflow: hidden;width:100%;height:100%;" fit="true"
							border="false">
							<div id="gatheringDatagrid_center" region="center" border="false" style="background:#eeeeee;">
									<table id="gatheringDatagrid"></table>
							</div>
							<div id="oldGatheringDatagrid_center" region="south" title="收款记录" style="height:240px;background:#eeeeee;" border="false">
									<table id="oldGatheringDatagrid"></table>
							</div>
						</div>
				</div>
						<!--<div  style="width:100%;height:60%;" border="false" >
							<table id="gatheringDatagrid"></table>
						</div>
						<div id="balancePage_hidden"  style="margin-left:-1px;position:relative;width:1px;height:538px;float:left;"
							onmouseout="loadOldBalance(false,'balancePage_oldGatheringDatagrid','oldGatheringDatagrid');"
								 onmouseover="loadOldBalance(true,'balancePage_oldGatheringDatagrid','oldGatheringDatagrid');">
						</div>
						<div style="width:100%;height:40%;">
							<table id="oldGatheringDatagrid">
						  	</table>
						</div>
					-->
				</div>
			</div>
</body>

<div id="gatheringAdd"  title="收款" data-options="iconCls:'icon-add',modal:true"
						style="display:none;padding: 5px; width: 830px;height:320px;">
 	<div style="width: 850px;height:270px;">
 		<form id="gatheringAddForm" method="post">
		<table border="0" style="width:98%;height:270px;">
			<tr>
				<td>车辆牌照:</td>
				<td>
					<input type="text" id="balancePage_carLicense" name="carLicense" readonly="readonly"
						style="background-color:#EBEBE4;width:140px;" class="easyui-validatebox" data-options="disabled:true" 
								 onmouseover="blockOrNone(true,'balancePage_showCar');"
								 onmouseout="blockOrNone(false,'balancePage_showCar');"/>
				</td>
				<td>客户名称:</td>
				<td>
					<input type="text" name="customName" readonly="readonly"  
						style="background-color:#EBEBE4;width:140px;" class="easyui-validatebox" 
								onmouseover="blockOrNone(true,'balancePage_showCustom');"
								 onmouseout="blockOrNone(false,'balancePage_showCustom');"/>
				</td>
				<td>结算单号:</td>
				<td>
					<input type="text" id="preclrId" name="preclrId" readonly="readonly"  
						style="background-color:#EBEBE4;width:140px;" class="easyui-validatebox" data-options="disabled:true"
							onmouseover="blockOrNone(true,'balancePage_showPreclr');"
								 onmouseout="blockOrNone(false,'balancePage_showPreclr');"/>
				</td>
			</tr>
			<tr>
				<td>收款单号:</td>
				<td><input type="text" id="balancePage_gatheringId" name="gatheringId" readonly="readonly"  
				class="easyui-validatebox" style="background-color:#EBEBE4;width:140px;"></td>
				<td>收款日期:</td>
				<td><input type="text" id="gatheringTime" name="gatheringTime" readonly="readonly"
					class="easyui-datetimebox" style="width: 140px;"
					value="{now}"></td>
				<td>收银员:</td>
		     			<td>
		     				<div style="display:none;">
						     <input type="checkbox" class="easyui-validatebox" id="unAchieve" disabled="disabled">
					        </div>
		      			    <input type="text" id="stfId" name="stfId" class="easyui-combobox" disabled="disabled" style="width:140px;"
					                value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
					data-options="url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',valueField:'id',textField:'text' "/>
				</td>
			</tr>
			<tr>
			    <td>收款方式:</td>
				<td><input type="text" id="balancePage_GatheringWise" name="GatheringWise" class="easyui-combobox"
		      			style="width:140px;"
		      			data-options="
		      			required:true,
		      			url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY %>',
		      			valueField : 'id',
		      			textField : 'text',
		      			onSelect:function(record){
		      			    payType = record.id;
		      				if(payType==tempId){
		      				    $('#hiveUse').attr('readonly','readonly'); 
		      				    $('#preclrPayAmount').attr('readonly','readonly'); 
		      				    $('#beforeMoney').val('0.00');
		      				    $('#beforeMoney1').numberbox('setValue','0.00');
		      				    $('#hiveUse').numberbox('setValue','0.00');
		      				    $('#preclrPayAmount').numberbox('setValue','0.00');
		      				    $('#otherBalance').val('0.00');
	        		            $('#preclrRealAmount').val('0.00');
		      					$.ajax({
								   type: 'post',
								   dataType: 'json',
								   url: '${pageContext.request.contextPath}/gatheringAction!findReceptionBeforeBalance.action',
								   data: 'carLicense='+$('#balancePage_carLicense').val(),
								   success: function(r){
								   		if(r.beforeId==null||r.beforeId.length==0){
								   			$('#balancePage_GatheringWise').combobox('select','');
								   		}else{
								   			var amount=$('#preclrPayAmount').val();
								   			if(amount==null||amount.length==0){
								   				amount='0.00';
								   			}
								   			if(r.beforeMoney==null||r.beforeMoney.length==0){
								   				r.beforeMoney='0.00';
								   			}
								   			$('#beforeId').val(r.beforeId);
								   			$('#beforeMoney').val(r.beforeMoney);
								   			$('#beforeMoney1').removeAttr('readonly');
								   			accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount','hiveUse','hiveBalance','beforeMoney', 'beforeMoney1');
								   		}
								   }
							    });
		      				}else if(payType==tempId2){
		      				    $('#hiveUse').removeAttr('readonly');   
		      				    $('#beforeMoney1').attr('readonly','readonly');
		      				    $('#preclrPayAmount').attr('readonly','readonly');
		      				    $('#beforeMoney').val('0.00');
		      				    $('#beforeMoney1').numberbox('setValue','0.00');
		      				    $('#hiveUse').numberbox('setValue','0.00');
		      				    $('#preclrPayAmount').numberbox('setValue','0.00');
		      				    $('#otherBalance').val('0.00');
	        		            $('#preclrRealAmount').val('0.00'); 
		      				}else{
		      				    $('#preclrPayAmount').removeAttr('readonly'); 
		      				    $('#hiveUse').attr('disabled','readonly'); 
		      				    $('#beforeMoney1').attr('readonly','readonly'); 
		      				    $('#beforeMoney').val('0.00');
		      				    $('#beforeMoney1').numberbox('setValue','0.00');
		      				    $('#hiveUse').numberbox('setValue','0.00');
		      				    $('#preclrPayAmount').numberbox('setValue','0.00');
		      				    $('#otherBalance').val('0.00');
	        		            $('#preclrRealAmount').val('0.00');
		      				}
		      			} "/>
		      	</td>
		      	<td>找零:</td>
				<td><input type="text" id="otherBalance" name="otherBalance" readonly="readonly" style="background-color:#EBEBE4;width:140px;color:#FF0000;"></td>
				<td>实收金额:</td>
				<td><input type="text" id="preclrRealAmount" name="preclrRealAmount" readonly="readonly" style="background-color:#EBEBE4;width:140px;color:#FF0000;"></td>
		   </tr>
		   <tr>
		      	<td>储备金余额:</td>
				<td>
					<input type="hidden" name="hiveId"/>
					<input type="text" id="hiveBalance" name="hiveBalance" style="width:140px;" class="easyui-validatebox" data-options="validType:'intOrFloat',precision:2,groupSeparator:','" readonly="readonly">
				</td>
				<td>储备金抵扣金额:</td>
				<td><input type="text" id="hiveUse" name="hiveUse"  style="width:140px;" class="easyui-numberbox"  data-options="validType:'intOrFloat',precision:2" readonly="readonly"
				    onblur="accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount','hiveUse','hiveBalance','beforeMoney', 'beforeMoney1');" ></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>预收款余额:</td>
				<td>
					<input type="hidden" id="beforeId" name="beforeId"/>
		      		<input type="text" id="beforeMoney" name="beforeMoney" style="width:140px;" class="easyui-numberbox"  data-options="validType:'intOrFloat',precision:2,groupSeparator:','" readonly="readonly"/>
				</td>
				<td>预收款抵扣额额:</td>
				<td><input type="text" id="beforeMoney1" name="beforeMoney1" style="width:140px;" class="easyui-numberbox"  data-options="validType:'intOrFloat',precision:2" readonly="readonly"
				    onblur="accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount','hiveUse','hiveBalance','beforeMoney', 'beforeMoney1');" ></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>应收金额:</td>
				<td><input type="text" id="preclrAmount" name="preclrAmount" style="width:140px;" class="easyui-validatebox" data-options="validType:'intOrFloat',precision:2,groupSeparator:','" readonly="readonly"></td>
				<td>付款金额:</td>
				<td><input type="text" id="preclrPayAmount" name="preclrPayAmount" style="width:140px;" class="easyui-numberbox"  data-options="validType:'intOrFloat',precision:2" readonly="readonly"
				onblur="accountBalance('preclrAmount','preclrPayAmount','otherBalance','preclrRealAmount','hiveUse','hiveBalance','beforeMoney', 'beforeMoney1');"></td>
				<td></td>
				<td></td>
		    </tr>
		    <tr>
				<td>代付人:</td>
				<td>
					<div  style="display: block" id="subFrtCustom">
						<input type="text" id="substituteCustomIda" name="substituteCustomId" class="easyui-combobox" style="width:140px;"
						data-options="disabled:true,editable : false,url : '${pageContext.request.contextPath }/frtOptionsAction!findAllCustom.action',
						missingMessage:'客户名称为必填项',valueField:'id',textField:'text' "/>
					</div>
					<div style="display: none" id="subClaimant">
						<input type="text" id="substituteCustomIdb" name="substituteClaimantId" class="easyui-combobox" style="width:140px;"
						data-options="disabled:true,editable : false,url : '${pageContext.request.contextPath }/frtOptionsAction!findAllClaimManufacturers.action',
						missingMessage:'客户名称为必填项',valueField:'id',textField:'text' "/>
					</div>
				</td>
				<td>代付金额:</td>
				<td>
					<input type="text" id="substituteMoney" name="substituteMoney" style="width:140px;" onkeyup="substitutePayment('preclrAmount','substituteMoney');"
						class="easyui-numberbox" data-options="precision:2,groupSeparator:',',disabled:true"/>
				</td>
				<td>
					<div  style="display: block" id="subFrtCustomAdd" style="width:110px;">
						<a id="subFrtCustomAdd_linkbutton" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
							 onclick="addSubstitute('substituteCustomIda','substituteMoney');">添加代付人</a>
					</div>
					<div  style="display: none" id="subClaimantAdd" style="width:110px;">
						<a id="subClaimantAdd_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
							 onclick="addSubstitute('substituteCustomIdb','substituteMoney');">添加代付人</a>
						</div>
					</td>
				</td>
				<td>
					<div  style="display: block" id="subFrtCustomRemove" style="width:110px;">
						<a id="subFrtCustomRemove_linkbutton" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
							 onclick="removeSubStitute('substituteCustomIda','substituteMoney','preclrAmount');">取消代付人</a>										
					</div>
					<div  style="display: none" id="subClaimantRemove" style="width:110px;">
						<a id="subClaimantRemove_linkbutton" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
							 onclick="removeSubStitute('substituteCustomIdb','substituteMoney','preclrAmount');">取消代付人</a>
					</div>
				</td>
			</tr>
			<tr>
				<tr >
					<td>
						收款备注:
					</td>
					<td colspan="15">
						<textarea rows="3" cols="90" name="gatheringRemark"></textarea>
					</td>
				</tr>
			</table>
		  </form>	
 	</div> 
  	<div id="balancePage_showCar" style="display:none;position:relative;z-index:1;
			margin-top:-200px;margin-left:26px;width:510px;background-color:EEFFFE;height: 50px;">
		<form id="balancePage_showCar_form">
			<table style="min-width: 520px;">
				<tr>
					<td>车辆牌照:</td>
					<td><input type="text" name="carLicense" readonly="readonly"/></td>
					<td>VIN号:</td>
					<td><input type="text" name="carVinName" readonly="readonly"></td>
					<td>发动机号:</td>
					<td><input type="text" name="carMotorIdName" readonly="readonly"></td>
				</tr>
				<tr>
					<td>车辆品牌:</td>
					<td><input type="text" name="cbrdName" readonly="readonly"></td>
					<td>车辆型号:</td>
					<td><input type="text" name="ctypeName" readonly="readonly"></td>
					<td>车辆颜色:</td>
					<td><input type="text" name="colorName" readonly="readonly"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="balancePage_showCustom" style="display:none;position:relative;z-index:2;
			margin-top:-200px;margin-left:252px;width:510px;background-color:EEFFFE;height:50px;">
		<form id="balancePage_showCustom_form">
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
	<div id="balancePage_showPreclr" style="display:none;position:relative;z-index:3;
			margin-top:-200px;margin-left:252px;width:510px;background-color:EEFFFE;height:28px;">
		<form id="balancePage_showPreclr_form">
			<table border="0"  style="min-width: 520px;">
				<tr>
					<td>结算单号:</td>
					<td><input type="text" id="preclrId" name="preclrId" readonly="readonly"></td>
					<td>维修类别:</td>
					<td><input type="text" name="reptName" readonly="readonly"></td>
					<td>票据类型:</td>
					<td><input type="text" name="invoiceTypeName" readonly="readonly"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</html>