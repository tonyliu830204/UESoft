<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>My JSP 'StPurOrderManager.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		  <script type="text/javascript">
		    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    </script>
		<script type="text/javascript">
		
		var sgsm_d2;
		function adddis()
		{
			var id=$("#haha").val();
		
			if(id==null || id=="" || id==undefined ){
				$.messager.alert('提示','请先选择回访记录信息!','warning');
				return ;
			}
			sgsm_d2 = $('<div/>');
			sgsm_d2.dialog({
				title: '请选择',   
			    width: 620,   
			    height:420,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/repayManage/addresearchTable.jsp?id='+$("#haha").val(),
			    modal: true,
			    onClose : function (){
		    	     $(this).dialog('destroy');
		        }
		   });
		}
	</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/repayManage/repayManage.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="north" split="false"
				style="height: 30px; background: #eee;" border="false">
				<a id='_update' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" id="_update" onclick="update();">修改</a>
				<a id='_search' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="queryReserve();">查询</a>
				<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"
					onclick="clearSearchCondition();">清空</a>
				<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="_config();">打印</a>
				<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true" onclick="_except();">导出</a>
				
				<span id="saveOrCancelBtn"></span>
				<br />
			</div>
			<div region="center" split="false" border="false">
				<div id="tt" class="easyui-tabs" fit="true" border="false">
					<div title="销售单汇总" style="display: block;" fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true" border="false">
							<div data-options="region:'north',title:'查询条件'"
								style="overflow: hidden; padding: 1px; background: #eee; height: 125px;"
								border="false">
							<form id="queryaa">
								 <fieldset>
									<table>
										<tr>
											<td width="75px">
												销售日期:
											</td>
											<td style="text-align: left;" colspan="3">
												<input type="text" id="xsCarSelData" name="xsCarSelData"
													class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsCarSelData2\',{d:-1})}'});" />
												&nbsp;&nbsp;至&nbsp;&nbsp;
												<input type="text" id="xsCarSelData2" name="xsCarSelData2"
													class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsCarSelData\',{d:1})}'});" />
											</td>
											<td>
												客户姓名:
											</td>
											<td>
												<input type="text" name="xsCustomName" style="width: 130px"/>
											</td>
											<td>
												手机:
											</td>
											<td>
												<input type="text" name="xsCustomPhone" style="width: 110px">
											</td>
											<td>
												销售单号:
											</td>
											<td>
												<input type="text" name="sell_code" style="width:100px;"/>
											</td>

										</tr>
										<tr>

											<td width="75px">
												最近回访:
											</td>
											<td style="text-align: left;" colspan="3">
												<input type="text" id="consultActualDate2" name="consultActualDate"
													class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'consultActualDate2\',{d:-1})}'});" />
												&nbsp;&nbsp;至&nbsp;&nbsp;
												<input type="text" id="consultActualDate22" name="consultActualDate2"
													class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'consultActualDate\',{d:1})}'});" />
											</td>
											<!--<td>
												未回访进度:
											</td>
											<td>

												<input type="text" id="consulTRate2" style="width: 110px"
													name="consulTRate2" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/sellCoverAction!getInfo.action',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consulTRate2\']',
													invalidMessage : '请从下拉框中回访进度类型'">
											</td>
											--><td>
												最近回访进度:
											</td>
											<td>
												<input type="text" id="consulTRate" style="width: 130px"
													name="consulTRate" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/sellCoverAction!getInfo.action',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consulTRate\']',
													invalidMessage : '请从下拉框中回访进度类型'">
											</td>
											<td>
												通话:
											</td>
											<td>

												<input type="text" id="consultCallState"
													style="width: 110px" name="consultCallState"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CALLSTATE%>',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consultCallState\']',
													invalidMessage : '请从下拉框中通话类型'">

											</td>


										</tr>
										<tr>

											<td width="75px">
												上报日期:
											</td>
											<td style="text-align: left;" colspan="3">
												<input type="text" id="auditDate" name="auditDate"
													class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'auditDate2\',{d:-1})}'});" />
												&nbsp;&nbsp;至&nbsp;&nbsp;
												<input type="text" id="auditDate2" name="auditDate2"
													class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'auditDate\',{d:1})}'});" />
											</td>
											<td>
												VIN号:
											</td>


											<td>
												<input type="text" name="vinCode" style="width: 130px">
											</td>

											<td>
												业务员:
											</td>
											<td>

												<input name="stfId" class="easyui-combobox"
													readonly="readonly" style="width: 110px;"
													data-options="url : 'basStuffClassAction!findSellOperationPerson.action',
												valueField:'id',  
												textField:'text',
												mode : 'remote'" />
											</td>
											


										</tr>
									</table>
									</fieldset>
								</form>
							</div>
							<div id="qingdan" region="center" style="background: #eee;"
								border="false">
								<table id="hh" name="allocateList"></table>
							</div>
						</div>
					</div>

					<div title="售后回访管理" style="display: block;" closable="false"
						fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 900px;" fit="true">
							<div region="north" id="wowo" split="false"
								style="overflow: hidden; background: #eee; height: 300px;"
								border="false">
								<table id="mingxiList" name="mingxiList"></table>
							</div>

							<div id="mingxi" region="center" style="background: #eee;"
								border="false" split="false">

								<form id="StForm" >

									<table>
										<tr>
											<td>
												客户姓名:
											</td>
											<td>
												<input type="text" id="ids1" name="xsCustomName"
													style="width: 130px" readonly="readonly">
												<input type="hidden" id="ids11" name="customId" />

											</td>

											<td>
												手机号码:
											</td>
											<td>
												<input type="text" id="ids3" name="xsCustomTelephone"
												class="easyui-validatebox"  data-options="validType:'mobile',editable:false" 
												 maxlength="11"	style="width: 130px" disabled="disabled">
											</td>
											<td>
												固定电话:
											</td>
											<td>
												<input type="text" id="ids2" name="xsCustomPhone"
													style="width: 130px" disabled="disabled" class="easyui-validatebox" 
													data-options="validType:'phone',editable:false"  maxlength="12" >
											</td>
											<td>
												业务员:

											</td>
											<td>
												<input name="stfName" id="ids5" 
												 readonly="readonly"
													style="width: 130px;"
													/>
											</td>


											<td style="width: 60px" >
												联系地址:
											</td>
											<td>
												<input type="text" id="ids4" name="xsCustomAddress"
													style="width: 310px" disabled="disabled">
											</td>

										</tr>
										<tr>

											<td>

												合同号:
											</td>
											<td>
												<input type="text" id="ids9" name="pactCode"
													disabled="disabled" style="width: 130px; color: red"
													readonly="readonly">
											</td>

											<td>
												车辆牌照:
											</td>
											<td>
												<input type="text" id="ids6" name="carLicensePlate"
													style="width: 130px" readonly="readonly" disabled="disabled">
											</td>
											<td>
												车辆型号:
											</td>
											<td>
												<input type="text" id="ids7" name="carModelName"
													style="width: 130px" disabled="disabled"
													readonly="readonly">
											</td>
											<td>
												行驶里程:

											</td>
											<td>
												<input type="text" name="travelCourse" style="width: 130px"
													id="ll" disabled="disabled" class="easyui-numberbox" maxlength="9">
											</td>

											<td style="width: 60px">
												销售备注:
											</td>
											<td colspan="2" rowspan="2">
												<textarea name="xsCarSelRemark" id="ids8"
													disabled="disabled" readonly="readonly" class="easyui-validatebox" data-options="validType:'characterDigit'"
													style="width: 310px; height: 40px; color: blue;resize:none;" maxlength="50" ></textarea>
											</td>

										</tr>
										<tr>
											<td>
												计划日期:
											</td>
											<td>
												<input type="text" name="consultPlanDate"
													id="consultPlanDate" readonly="readonly"
													  style="width: 130px;"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'consultPlanDate\')}'});" />
											</td>
											<td>
												实际回访:
											</td>
											<td>
												<input type="text" id="consultActualDate"
													 name="consultActualDate" disabled="disabled"
													 class="Wdate" style="width: 130px;"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'consultActualDate\')}'});" />
											</td>
											<td>
												通话情况:
											</td>
											<td>
												<input type="text" id="consultCallState" disabled="disabled"
													style="width: 130px" name="consultCallState"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CALLSTATE%>',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consultCallState\']',
													invalidMessage : '请从下拉框中通话类型'">
											</td>


											<td>
												处理时间:
											</td>
											<td>
											<input type="text" id="disposeDate"
													 name="disposeDate" disabled="disabled"
													 class="Wdate" style="width: 130px;"
													onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'disposeDate\')}'});" />
											
											</td>


										</tr>
										<tr>
											<td>
												满意程度:
											</td>
											<td>

												<input type="text" id="consultDegree" disabled="disabled" 
													style="width: 130px" name="consultDegree"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CONSULTDEGREE%>',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consultDegree\']',
													invalidMessage : '请从下拉框中满意程度'">


											</td>
											<td>
												备注:
											</td>
											<td colspan="1" rowspan="3">
												<textarea name="remark"   class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"  
													style="width: 130px; height:60px" disabled="disabled" maxlength="51"> </textarea>
											</td>

											<td>
												领导意见:
											</td>
											<td colspan="3" rowspan="3">
												<textarea name="consultSuggest" id="ids10"  class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,100]\']'"  
													style="width: 318px; height: 60px" disabled="disabled" maxlength="101"> </textarea>
											</td>
											<td rowspan="2" >
												处理结果
												及领导批示:
											</td>
											<td colspan="2" rowspan="3">
												<textarea id="ldao" name="disposeResult" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,100]\']'"  
													style="width: 310px; height: 60px" disabled="disabled" maxlength="101" ></textarea>
											</td>

										</tr>
										<tr>
											<td>
												回访进度:
											</td>
											<td>
												<input type="text" id="consulTRate" disabled="disabled"
													style="width: 130px" name="consulTRate"
													class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/sellCoverAction!getInfo.action',
													
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#consulTRate\']',
													invalidMessage : '请从下拉框中回访进度类型'">

											</td>
											
											

										</tr>
										<tr></tr>
										<tr>
										
											<td>
												<input type="hidden" id="haha" name="consultId" />
											</td>
											<td colspan="12" rowspan="2" align="right">
												<a href="javascript:void(0);" class="easyui-linkbutton"
													style="width: 120px; color: blue;" onclick="adddis();">回访调查表</a>
											</td>
										</tr>


									</table>

								</form>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
