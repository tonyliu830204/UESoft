
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasRepairType"%>
<%@page import="com.syuesoft.model.Reptype"%>
<%@page import="com.syuesoft.model.BasClaimsType"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasStorehouse"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<title>预出库管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPreOut/stpreoutmanager.js"></script>
	</head>
	
	<body>
	   <div id="cc" class="easyui-layout" fit="true" border="false">  
          <div region="north"  split="false" style="height:30px;background: #eee;" border="false"> 
             <privilege:enable code="StPreOutAdd">
              <a href="javascript:void(0);" id="smsh_addBtn" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addPersonnel(1);">新增</a>
             </privilege:enable>
              <privilege:enable code="StPreOutDelete">
              <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" id="smsh_deleteBtn" onclick="deleteStPreOut()">删除</a>
             </privilege:enable>
              <privilege:enable code="StPreOutUpdate">
               <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" id="smsh_updateBtn" onclick="addPersonnel(2);">修改</a>
             </privilege:enable>
              <privilege:enable code="StPreOutSearch">
                <a href="javascript:void(0);" id="smsh_searchBtn" class="easyui-linkbutton" 
                iconCls="icon-search" plain="true" onclick="searchByCondition();">查询</a>
             </privilege:enable>
              <privilege:enable code="StPreOutClear">
              <a href="javascript:void(0);" id="smsh_clearBtn" class="easyui-linkbutton" 
              iconCls="icon-cancel" plain="true" onclick="clearSearchByCondition();">清空</a>
             </privilege:enable>
             <privilege:enable code="StPreOutPrint">
                <a href="javascript:void(0);" id="smsh_printBtn" class="easyui-linkbutton"
				iconCls="icon-print" plain="true">打印</a>
             </privilege:enable>
              <privilege:enable code="StPreOutExport">
               <a href="javascript:void(0);" id="smsh_exportBtn" class="easyui-linkbutton"
				iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
             </privilege:enable>
			    <span id="button"></span>
          </div>
		  <div region="center"  split="false" border="false">
			<div id="tt" class="easyui-tabs" fit="true" border="false">
				<div title="预出单汇总" style="display: block;" border="false" closable="false" fit="true">
					<div id="cc" class="easyui-layout" fit="true" border="false">
						<div region="north" title="条件" split="false"
							style="overflow: hidden;padding:3px;height:53px; background: #eee;" border="false">
							<form id="stPreOutMainSearchForm">
							预出日期:<input id="stpremTimeStart" name="stpremTimeStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'stpremTimeEnd\',{d:-1})}'})"/>
							至:<input id="stpremTimeEnd" name="stpremTimeEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'stpremTimeStart\',{d:0})}'})"/>
							预出单号:<input name="stpremId" />
							工单号:<input name="receptionId" />
							处理方式:<input name="stpremFlg" class="easyui-combobox"
										data-options="url:'${pageContext.request.contextPath}/StPreOutAction!loadOpenBillStyle.action',
									    editable : false,
										valueField:'id',
										panelHeight:130,
										textField:'text'"/>
							</form>
						</div>
						<div region="center" id="StPreOutMainDiv" style="background: #eee;" border="false">
							<table id="StPreOutMain"></table>
						</div>
					</div>
				</div>
				<div title="预出单明细" border="false" closable="false">
					<div id="cc" class="easyui-layout"  border="false" fit="true">
						<div region="north" split="false" style="overflow: hidden;height:105px; background: #eee;" border="false">
							<form id="StPreOutMainForm">
							 <table>
								 <tr> 
								    <td>日期:</td> 
								    <td><input id="stpremTime" name="stpremTime" style="width:130px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								    <td>预出单号:</td>
								    <td><input id="stpremId" name="stpremId" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td>车牌照:</td>
								    <td><input id="carLicense" name="carLicense" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td></td>
								    <td>工单号:</td>
								    <td><input id="receptionId" name="receptionId" class="easyui-validatebox" required="true" missingMessage="工单号必填" onfocus="this.select();addFrtReception();" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addFrtReception();"/></td>
								    <td>维修类别:</td>
								    <td><input id="reptName" name="reptName" style="background-color: #c0d8d8;" /></td>
								 </tr>
								 <tr>
								    <td>经办人:</td>
								    <td><input id="manager" name="manager" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								    <td>处理方式:</td>
								    <td><input name="stpremFlg" id="stpremFlg" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/StPreOutAction!loadOpenBillStyle.action',
												    editable : false,
													valueField:'id',
													required:true,
													missingMessage:'处理方式必填',
													panelHeight:130,
													textField:'text'"/></td>
								    <td>领料员:</td>
								    <td><input id="stfName" style="background-color: #c0d8d8;"  class="easyui-validatebox" required="true" missingMessage="领料员必填" name="stfName" onfocus="this.select();addBasStuff();"/><input type="hidden" id="spom_stfId" name="stfId"/></td>
								    <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addBasStuff();"/></td>
								    <td>车间:</td>
								    <td><input id="repgrpName" name="repgrpName" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td></td>
								    <td>备注信息:</td>
								    <td><input id="stomRemark" name="stomRemark" class="easyui-validatebox" validType="length[0,100]"/></td>
								 </tr>
								 <tr>
								    <td>数量:</td>
								    <td><input id="numTotal" name="numTotal" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								    <td>预出金额:</td>
								    <td><input id="stpremSumAmount" name="stpremSumAmount" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td>成本额:</td>
								    <td><input id="stpremSumCost" name="stpremSumCost" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td></td>
								    <td>付款:</td>
								    <td><input id="" name=""/></td>
								    <td></td>
								    <td>销价系数:</td>
								    <td><input id="" name=""/></td>
								 </tr>
								 <tr>
								    <td>车型:</td>
								    <td><input id="ctypeName" name="ctypeName" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								    <td>VIN编号:</td>
								    <td><input id="carVan" name="carVan" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td>客户:</td>
								    <td><input id="customName" name="customName" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								    <td></td>
							        <td>接待:</td>
							        <td><input id="reptName" name="reptName" disabled="disabled" style="background-color: #c0d8d8;" /></td>
							        <td></td>
							        <td>业务员:</td>
							        <td><input id="" name="" disabled="disabled" style="background-color: #c0d8d8;" /></td>
								 </tr>
							 </table>
							</form>
						</div>
						<div region="center" style="background: #eee;" aplit="false" border="false">
							<table id="StPreOutDetailTable"></table>
						</div>
						<div region="south" split="false" style="height:30px;background:#eee;" border="false">
						    <table>
							    <tr>
							      <td colspan="7">
							      <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="addFrtReception();">计划材料</a><%--
										    <a href="javascript:void(0)" iconCls="icon-add"  class="easyui-linkbutton" plain="true" id="spo_addParts" onclick="addParts();">配件增加</a>
										    <a href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton" plain="true" id="spo_deleteParts" onclick="deleteParts();">配件删除</a>
										    <a href="javascript:void(0)" iconCls="icon-tip" class="easyui-linkbutton" plain="true">配件库存</a>
										    <a href="javascript:void(0)" iconCls="icon-tip" class="easyui-linkbutton" plain="true">维修套餐</a>
								  --%></td>
							    </tr>
						    </table>
				        </div>  
					</div>
				</div>
			</div>
	      </div>
       </div>
	</body>
</html>
