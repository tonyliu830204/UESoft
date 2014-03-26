<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>调拨单管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		    <script type="text/javascript">
    //var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	var tbtitle;
	var sgsm_d2;
	function adddis()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '请选择',   
	    width:650,   
	    height:400,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
	
	var sgsm_d1;
	function adddis2()
	{
	 sgsm_d1 = $('<div/>');
	 sgsm_d1.dialog({
		title: '请选择',   
	    width: 650,   
	    height:400,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor2.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/allocateManage/allocateManage.js"></script>
	</head>
	<body>
		<div id="cc" class="easyui-layout" fit="true" border="false">
			<div region="north" split="false"
				style="height: 30px; background: #eee;" border="false">
				<privilege:enable code="ALLOCATE_ADD">
				<a id='_add' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-add" plain="true"  onclick="addPersonnel(1);">新增</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_REMOVE">
				<a id='_remove' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"  onclick="remove_()" >删除</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_EDIT">
				<a id='_update' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true"   onclick="addPersonnel(2);">修改</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_SEARCH">
				<a id='_search' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-search" plain="true"  onclick="queryCarReserve();">查询</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_CLEAR">
				<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition();">清空</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_EXAMINE">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-examine" plain="true" id="_examine"
					onclick="examineButton();">审核</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_PRINT">
				<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="sellExchangePrint();">打印</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATE_EXPORT">
				<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true"  onclick="_except();">导出</a>
				</privilege:enable>
				<span id="saveOrCancelBtn"></span>
				<br />
			</div>
			<div region="center" split="false" border="false">
				<div id="tt" class="easyui-tabs" fit="true" border="false">
					<div title="调拨单汇总" style="display: block;" fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true" border="false">
							<div data-options="region:'north',title:'查询条件'"
								style="overflow: hidden; padding: 1px; background: #eee; height: 70px;"
								border="false">
								<form id="queryForm" name="queryForm" method="post">
								 <fieldset>
									<table>
										<tr>
											<td width="65px">
												调拨日期:
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserveDete2\',{d:-1})}'})"
													name="allocateDate" id="reserveDete" style="width: 95px;" 
													 />
												至
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserveDete\',{d:1})}'})"
													name="allocateDate2" id="reserveDete2" style="width: 95px;" 
													 />
											</td>
											
											<td>
												分销商:
											</td>
											<td><input id="dName" name="xsDistributorName" style="background-color:#c0d8d8; width: 180px";" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8;"/>
										<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="adddis();"/>
										<input name="xsDistributorId" id="did" type="hidden"  />
										</td> 
											
											<td>
												调拨单号:
											</td>
											<td>
												<input type="text" id="pqueryValue" name="allocatecode" />
											</td>
											
											<td>
												VIN号:
											</td>
											<td>
												<input type="text" id="pquery" name="xsCarVinNum" style="width: 135px" maxlength="17"/>
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
					<div title="调拨单明细" style="display: block;" closable="false"
						fit="true" onClick="sumMoney(false);">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true">
							<div region="north"  split="false" 
								style="overflow: hidden; background: #eee; height: 80px;"
								border="false">
								<form id="StForm" method="post" >
								 <fieldset>
								 <input type="hidden" name="allocateType">
								<input type="hidden" id="enterprise_id" name="enterprise_id"/>
									<table >
										<tr align="right">
										<td>
												日期:
											</td>
											<td>
												<input type="text"  readonly="readonly"
													name="allocateDate" id="allocateDate"  />
													</td>
													<td>
												调拨单:
											</td>
											<td>
												<input type="text" id="allocatecode" name="allocatecode"   readonly="readonly" disabled="disabled" size="10" />
											</td>
											
											<td>
												分销商:
											</td >
											<td colspan="4">
												<input id="dName2" class="easyui-validatebox" readonly="readonly" disabled="disabled" name="xsDistributorName" style="background-color:#c0d8d8; width: 275px"  onkeypress=" if(event.keyCode==13) { adddis2(); return false;}"  />
												<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"  id="DisImg"/>
												<input name="xsDistributorId" id="did2" type="hidden"/>
												</td> 
											
											<td>
												发票及备注:
											</td>
											<td colspan="2" rowspan="2">
												<textarea  id="remark" name="remark" disabled="disabled" class="easyui-validatebox" 
													data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"  
														maxlength="51" style="width: 200px;resize:none;"></textarea>
												
											</td>
						
											
										</tr>
										<tr>
										
											<td>
												调拨额:
											</td>
											<td>
												<input type="text" id="allocateAmount" name="allocateAmount" class="easyui-numberbox" precision="2" readonly="readonly" disabled="disabled" />
											</td>
											<td>
												提车人:
											</td>
											<td>
												<input type="text" id="consignee" name="consignee" class="easyui-validatebox" value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName() %>"
												data-options="validType:'multiple[\'characterDigit\',\'length[0,15]\']'" disabled="disabled" maxlength="15"/>
											</td>
											<td>
												经办人:
											</td>
											<td><input type="text" name="allocatePerson" id="allocatePerson"   class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>"
 												style="width: 140px"
												data-options="url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#allocatePerson\']',
													invalidMessage : '请从下拉框中选择经办人'"  /></td> 
													<td>仓库:</td>							
										    <td>
										    <input type="text" name="warehouse" id="warehouse" disabled="disabled"    class="easyui-combobox"  style="width: 110px"
										    data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',
												
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote',
									    		validType:'isSelected[\'#warehouse\']',
												invalidMessage : '请从下拉框中选择仓库'"/>
												</td>
													
											
											<td><input type="hidden" id="allocateId" name="allocateId" />
													</td>
													<td><input type="hidden" id="costprice"  class="easyui-numberbox" precision="2" name="costprice" />
													</td>
											
											
										</tr>
									</table>
									</fieldset>
								</form>
							</div>
							<div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">调拨单增加</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">调拨单删除</a>&nbsp;&nbsp;
					    </div>
							<div id="mingxi" region="center" style="background: #eee;"
								border="false">
								<table id="mingxiList" name="mingxiList"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
