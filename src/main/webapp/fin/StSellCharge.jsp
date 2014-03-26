<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>销售应收款</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellCharge.js"></script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
    	<privilege:enable code="SELLCHARGEPAID">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPaid();">付款</a>
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGEDELETE">  
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="deleteStSellCharge();">删除</a>  
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGEQUERY">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="sscds_searchByCondition();">查询</a> 
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGECLEAR"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="sscds_clearSearchCondition();">清空</a>  
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGEPRINT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">打印</a> 
    	</privilege:enable>
        <privilege:enable code="SELLCHARGEEXPORT"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>  
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGEEXAMIN">
    		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-examine" onclick="examineButton();">审核</a>
    	</privilege:enable>
    	<privilege:enable code="SELLCHARGECANCLEEXAMIN">
    		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-examine" onclick="cancelExamineButton();">取消审核</a>
    	</privilege:enable>
    	<span id="button"></span>
    </div>  
    <div data-options="region:'center'" style="background:#eee;" data-options="border:false">
    	 <div id="st_sell_chargeMain_tabs" class="easyui-tabs" style="background:#eee;" data-options="fit:true,border:false">
		     <div title="销售应收款汇总">
		        <div class="easyui-layout" data-options="fit:true,border:false">
		            <div data-options="region:'north',border:false"
						style="height:30px;background:#eee;overflow: hidden;">
						<form id="st_sell_chargeMain_from">
							<table>
								 <tr>
								    <td>审核日期:</td>
									<td><input id="sscds_verifyDate" name="sscds_verifyDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
									<td>销售应收款汇总编号:</td>
									<td><input id="sscds_chargeId" name="sscds_chargeId" /></td>
									<td>销售结算单编号:</td>
									<td><input id="sscds_preclrId" name="sscds_preclrId"/></td>	
									<td>是否出库:</td>
									<td><input id="sscds_stockOut" name="sscds_stockOut" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/StSellChargeAction_loadIsNoStOut.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
									<td>审核状态:</td>
									<td><input id="sscds_verifyState" name="sscds_verifyState" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/StSellChargeAction_loadExamine.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
								</tr>
							</table>
						</form>
				    </div>
				    <div data-options="region:'center',border:false" style="background:#eee;">
					    <table id="st_sell_chargeMain_Table"></table>
				    </div>
			     </div>
		    </div>
		    <div title="销售应收款明细">  
		     <div class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'north',border:false"
						style="height:80px;background:#eee;overflow: hidden;">
					<form id="st_sell_preclr_detail_form">
						<table>
							<tr>
								<td>销售收款单编号:</td>
								<td><input id="ssc_ChargeId" name="ssc_ChargeId" readonly="readonly" readonly="readonly"  style="width:125px;background-color: #c0d8d8;"/></td>
								<td>应收款金额:</td>
								<td><input id="ssc_PreclrAmount" name="ssc_PreclrAmount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								<td>已收款金额合计:</td>
								<td><input id="ssc_PaidAmount" name="ssc_PaidAmount" readonly="readonly" style="width:140px;background-color: #c0d8d8;"/></td>
								<td>结算单编号:</td>
								<td><input id="ssc_preclrId" name="ssc_preclrId" readonly="readonly" style="width:150px;background-color: #c0d8d8;"/></td>	
							</tr>
							<tr>
							    <td>客户名称:</td>
								<td><input id="ssc_CustomName" name="ssc_CustomName" readonly="readonly" style="width:125px;background-color: #c0d8d8;"></td>
								<td>收款分类:</td>
								<td><input id="ssc_Classfication" name="ssc_Classfication" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								<td>审核日期:</td>
								<td><input id="ssc_VerifyDate" name="ssc_VerifyDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>	
							    <td>审核状态:</td>
								<td><input id="ssc_state" name="ssc_state" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>	
							</tr>
							<tr>
							   <td>
							      <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="cancelPaid();">删除付款明细</a>
							   </td>
							 </tr>
						</table>
					  </form>
				    </div>
				   <div data-options="region:'center',border:false" style="background:#eee;">
					  <table id="st_sell_chargeItem_Table"></table>
				   </div>
			 </div>
		    </div>
		</div>  
    </div>
  </body>