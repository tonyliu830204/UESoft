<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>销售单结算</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/StSellPreclr.js"></script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
    	<privilege:enable code="PRECLRADD">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPersonnel(1);">新增</a> 
    	</privilege:enable>
    	<privilege:enable code="PRECLRDELETE"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="deleteStSellPercharge();">删除</a>  
    	</privilege:enable>
    	<privilege:enable code="PRECLREDIT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="addPersonnel(2);">修改</a>  
    	</privilege:enable>
    	<privilege:enable code="PRECLRSEARCH">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="ssp_StSellPercharge_searchByCondition();">查询</a>  
    	</privilege:enable>
    	<privilege:enable code="PRECLRCLEAR">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="ssp_StSellPercharge_clearSearchByCondition();">清空</a> 
    	</privilege:enable>
    	<privilege:enable code="PRECLRPRINT"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'">打印</a> 
    	</privilege:enable>
    	<privilege:enable code="PRECLREXPORT"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>
    	</privilege:enable>
    	<privilege:enable code="PRECLRTOCASH">  
    		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-tocash" onclick="isChangePaid();">转收银</a>
    	</privilege:enable>
    	<span id="button"></span>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">  
        <div id="st_sell_preclr_tabs" class="easyui-tabs" data-options="fit:true">  
		    <div data-options="title:'销售单结算汇总'">
		      <div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',border:false"
					style="height:60px;background:#eee;overflow: hidden;">
					<form id="st_sell_preclr_main_form">
						<table>
							<tr>
								<td>结算日期:</td>
								<td><input id="sst_sspd_preclrDateStart" name="sst_sspd_preclrDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'s_fcr_claimantmTimeEnd\',{d:-1})}'})"/></td>
								<td>至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td><input id="sst_sspd_preclrDateEnd" name="sst_sspd_preclrDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'s_fcr_claimantmTimeStart\',{d:0})}'})"/></td>
								<td>结算单号:</td>
								<td><input id="sst_sspd_preclrId" name="sst_sspd_preclrId" style="width:150px;background-color: #c0d8d8;"/>
								</td>
								<td>客户名称:</td>
								<td><input id="sst_sspd_customId" name="sst_sspd_customId" style="background-color: #c0d8d8;"class="easyui-combobox"
									data-options="
									editable : false,
									url : 'workOrderPartsQueryAction!loadAllCustom.action',
									valueField:'id',  
									textField:'text' "/></td>
							</tr>
						    <tr>			
								<td>收款分类:</td>
								<td><input id="sst_sspd_classfication" name="sst_sspd_classfication" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/StSellPreclrAction_loadClassfication.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
								<td>是否转收银</td>
								<td><input id="sst_sspd_state" name="sst_sspd_state" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/StSellChargeAction_loadIsNoStOut.action',
																    editable :false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
								<td>销退单号:</td>
								<td><input id="sst_sspd_cerNo" name="st_sspd_cerNo" style="width:150px;"></td>	
							</tr>
						</table>
					</form>
				</div>
				<div data-options="region:'center',border:false" style="background:#eee;" border="false">
					<table id="st_sell_preclr_Main_Table"></table>
				</div>
			  </div>
		    </div>  
		    <div data-options="title:'销售单结算明细'">
		      <div class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'north',border:false"
						style="height:55px;background:#eee;overflow: hidden;">
					<form id="st_sell_preclr_detail_form">
						<table>
							<tr>
								<td>结算日期:</td>
								<td><input id="st_sspd_preclrDate" name="st_sspd_preclrDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
								<td>单号:</td>
								<td><input id="st_sspd_preclrId" name="st_sspd_preclrId" style="background-color: #c0d8d8;"/></td>
								<td>经办人:</td>
								<td><input id="st_sspd_manager" name="st_sspd_manager" style="background-color: #c0d8d8;"/></td>
								<td>客户名称:</td>
								<td><input id="st_sspd_customId" name="customName" style="width:110px;" class="easyui-combobox"
									data-options="url : 'frtOptionsAction!findAllCustom.action',
									editable : false,
									valueField:'id',  
									textField:'text' "/></td>
								<td>销退单号:</td>
								<td><input id="st_sspd_cerNo" name="st_sspd_cerNo" style="width:250px;"></td>	
							</tr>
							<tr>
								<td>数量:</td>
								<td><input id="st_sspd_preclrNum" name="st_sspd_preclrNum" style="background-color: #c0d8d8;width:140px;"/></td>
								<td>金额:</td>
								<td><input id="st_sspd_preclrAmount" name="st_sspd_preclrAmount" style="background-color: #c0d8d8;"/></td>
								<td>成本额:</td>
								<td><input id="st_sspd_preclrCostAmount" name="st_sspd_preclrCostAmount" style="background-color: #c0d8d8;"/></td>
								<td>收款分类:</td>
								<td><input id="st_sspd_classfication" name="st_sspd_classfication" class="easyui-combobox" 
								data-options="url : 'baseAction!findBaseListData.action?key=<%=Contstants.GATHECLASSFICAT.GATHECLASSFICATING %>',
							    editable : false,
								valueField:'id',
								panelHeight:130,
								textField:'text'"/></td>
							    <td>备注:</td>
							    <td><input id="st_sspd_remark" name="st_sspd_remark" style="width:250px;"/>
							        <input type="hidden" id="st_sspd_state" name="st_sspd_state"/>
							    </td>
							</tr>
						</table>
					</form>
				   </div>
				   <div data-options="region:'center',border:false" style="background:#eee;" border="false">
					<table id="st_sell_preclr_detail_Table"></table>
				   </div>
			  </div>
		    </div>  
		</div> 
    </div>
  </body>
</html>
