<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>调退单查询</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/sell/allocateBack/allocateBack.js"></script>
		  <script type="text/javascript">
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		    </script>
		<script type="text/javascript">
		var tbtitle;
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
			
		var sgsm_d2;
		function adddis()
		{
		 sgsm_d2 = $('<div/>');
		 sgsm_d2.dialog({
			title: '请选择',   
		    width: 650,   
		    height:400,
		    cache: false,   
		    href: '${pageContext.request.contextPath}/sell/allocateManage/addDistributor.jsp',
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
				<privilege:enable code="ALLOCATEBACK_ADD">
				<a id='_add' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-add" plain="true"  onclick="addPersonnel(1);">新增</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_REMOVE">
				<a id='_remove' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"  onclick="remove_()" >删除</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_EDIT">
				<a id='_update' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true"    onclick="addPersonnel(2);">修改</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_SEARCH">
				<a id='_search' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-search" plain="true"  onclick="queryCarReserve($('#reserveDete'),$('#reserveDete2'));">查询</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_CLEAR">
				<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" plain="true"  onclick="clearSearchCondition($('#reserveDete'),$('#reserveDete2'));">清空</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_EXAMINE">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-examine" plain="true" id="_examine"
					onclick="examineState();">审核</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_PRINT">
				<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-print" plain="true" onclick="sellExchangeBackPrint();">打印</a>
				</privilege:enable>
				<privilege:enable code="ALLOCATEBACK_EXPORT">
				<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-export" plain="true" onclick="_except();">导出</a>
				</privilege:enable>
				<span id="saveOrCancelBtn"></span>
				<br />
			</div>
			<div region="center" split="false" border="false">
				<div id="tt" class="easyui-tabs" fit="true" border="false">
					<div title="调退单汇总" style="display: block;" fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true" border="false">
							<div data-options="region:'north',title:'查询条件'"
								style="overflow: hidden; padding: 1px; background: #eee; height:80px;"
								border="false">
								<form id="queryFrom">
								 <fieldset>
									<table>
										<tr>
											<td width="65px">
												调退日期:
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'reserveDete2\',{d:-1})}'})"
													name="backDate" id="reserveDete" style="width: 90px;"
												 />
												至
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'reserveDete\',{d:1})}'})"
													name="backDate2" id="reserveDete2" style="width: 90px;" 
													/>
											</td>
											<td>
												分销商:
											</td>
											<td><input id="dName2" name="xsDistributorName" style="background-color:#c0d8d8; width: 240px" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8;"/>
										<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="adddis2();"/>
										<input name="xsDistributorId" id="did2" type="hidden"  />
										</td> 
											
											<td>
												调退单号:
											</td>
											<td>
												<input type="text"  name="backCode" style="width: 130px;" />
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
					<div title="调退单明细" style="display: block;" closable="false"
						fit="true">
						<div id="cc" class="easyui-layout"
							style="width: 600px; height: 500px;" fit="true">
							<div region="north" title="条件查询" split="false"
								style="overflow: hidden; background: #eee; height: 100px;"
								border="false">
								<form id="StForm" method="post">
								
								 <fieldset>
								   <input type="hidden" name="examine">
								   <input type="hidden" name="balanceState">
								   <input type="hidden" name="backType">
								   <input type="hidden" id="enterprise_id" name="enterprise_id"/>
									<table>
										<tr>
											<td>
												调退日期:
											</td>
											<td>
												<input type="text" class="Wdate" readonly="readonly"
													
													name="backDate" id="allocateDate"  />
													</td>
													<td>
												调退单号:
											</td>
											<td>
												<input type="text" id="backCode" name="backCode"  style="width: 180px" disabled="disabled" readonly="readonly"/>
											</td>
											<td>
												分销商:
											</td>
											<td colspan="4"><input id="dName"  class="easyui-validatebox" disabled="disabled"   name="xsDistributorName" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8; width: 335px"/>
											<img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="DisImg"/>
											<input name="xsDistributorId" id="did" type="hidden"/>
											</td> 
											
										</tr>
										<tr>
									
											<td>
										
												调退金额:
											</td>
											<td>
												<input type="text" id="han" name="handbackAllocateAmount" disabled="disabled" readonly="readonly"/>
											</td>
											<td>
												经办人:
											</td>
											<td>
											<input type="text" id="backPerson" name="backPerson"    class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
												 style="width: 180px"
												data-options="
													url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#backPerson\']',
													invalidMessage : '请从下拉框中选择经办人'"  />	
								
											</td>
											
											
											
											<td>
												备注:
											</td>
											<td>
												<input type="text" id="remark" name="remark" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"  disabled="disabled" maxlength="51" style="width: 345px"/>	
											</td>
											<td><input type="hidden" id="backId" name="backId" />
											</td>
											
										</tr>
									</table>
									</fieldset>
								</form>
							</div>
							<div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">调退单增加</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">调退单删除</a>&nbsp;&nbsp;
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
