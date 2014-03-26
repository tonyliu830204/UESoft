<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '退料单管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtParts/strtpartsmanager.js"></script>
  </head>
  <body>
   <div id="cc" class="easyui-layout" fit="true" border="false">  
	     <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
			 <div style="background:#eee;">
				 <privilege:enable code="StRtPartsAdd">
				        <a href="javascript:void(0);" id="srpm_addBtn" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" onclick="addPersonnel();">新增</a>
				 </privilege:enable>
				 <privilege:enable code="StRtPartsDelete">
				        <a href="javascript:void(0);" id="srpm_deleteBtn" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="delStRtPartsMain()">删除</a>
				 </privilege:enable>
				 <privilege:enable code="StRtPartsSearch">
						<a href="javascript:void(0);"  id="srpm_searchBtn" class="easyui-linkbutton" 
						iconCls="icon-search" plain="true" onclick="searchByCondition();">查询</a>
				 </privilege:enable>
				 <privilege:enable code="StRtPartsClear">
				        <a href="javascript:void(0);" id="srpm_clearBtn" class="easyui-linkbutton" 
				        iconCls="icon-cancel" plain="true" onclick="clearSearchByCondition();">清空</a>
				 </privilege:enable>
				 <privilege:enable code="StRtPartsPrint">
				        <a href="javascript:void(0);" id="srpm_printBtn" class="easyui-linkbutton"
							iconCls="icon-print" plain="true">打印</a>
				 </privilege:enable>
				 <privilege:enable code="StRtPartsExport">
				        <a href="javascript:void(0);" id="srpm_exportBtn" class="easyui-linkbutton"
							iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
				 </privilege:enable>
			 <span id="button"></span>
			 </div>
	     </div>
		 <div region="center"  split="false" border="false">
		    <div id="tt" class="easyui-tabs" fit="true" border="false">  
		         <div title="工单退料单汇总" style="display:block;" closable="false" fit="true" border="false">  
			        <div id="cc" class="easyui-layout"  fit="true" border="false">  
					    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" cloasble="false" border="false">
						   <form id="StRtPartsSearchForm">
							    <table>
							      <tr>
							         <td>退料日期:</td>
							         <td><input id="strtpmDateStart" name="strtpmDateStart"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'strtpmDateqiEnd\',{d:-1})}'})"/></td>
							         <td>至</td>
							         <td><input id="strtpmDateqiEnd" name="strtpmDateqiEnd"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'strtpmDateStart\',{d:0})}'})"/></td>
							         <td>退料单号:</td>
							         <td><input name="strtpmId"/></td>
							         <td>工单号:</td>
							         <td><input name="receptionId"/></td>
							         <td>车牌照:</td>
							         <td><input name="carLicense"/></td>
							      </tr>
							    </table>
						    </form>
					    </div>
					    <div region="center" id="StRtPartsMainTableDiv" style="background:#eee;background:#eee;" aplit="false" border="false">
						   <table id="StRtPartsMainTable"></table>
					    </div>  
					</div>  
		         </div>
	             <div title="工单退料单明细" style="overflow:hidden;display:block;" border="false"  closable="false">  
	                <div id="cc" class="easyui-layout" fit="true" border="false">  
			         <div region="north" title="查询条件" split="false" style="overflow:hidden;height:105px;background:#eee;" border="false">
	                    <form id="StRtPartsMainForm">
					          <table>
					             <tr>
					               <td>退料日期:</td>
					               <td><input id="srp_strtpmDate" name="strtpmDate" style="width:140px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" /></td>
					               <td>退料单号:</td>
					               <td><input id="strtpmId" name="strtpmId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					               <td></td>
					               <td>工单号:</td>
					               <td><input id="srp_receptionId" name="receptionId" class="easyui-validatebox" required="true" missingMessage="工单号必填" onfocus="this.select();srp_receptionSelect();" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					               <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="srp_receptionSelect();"/></td>
					               <td>车牌:</td>
					               <td><input id="srp_carLicense" name="carLicense" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					             </tr>
					             <tr>
					               <td>客户名称:</td>
					               <td><input id="srp_customName" name="customName" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					               <td>退料人员:</td>
					               <td><input type="hidden" id="srp_stfId" name="stfId"/><input id="srp_stfName" name="srp_stfName" class="easyui-validatebox" required="true" missingMessage="退料人员必填" onfocus="this.select();srp_stufSelect();" style="background-color: #c0d8d8;"/></td>
					               <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="srp_stufSelect();"/></td>
					               <td>经办人员:</td>
					               <td><input id="srp_manager" name="manager"  value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;"/></td>
					               <td></td>
					               <td>备注:</td>
					               <td><input id="strtpmRemark" name="strtpmRemark" class="easyui-validatebox" validType="length[0,100]"/></td>
					             </tr>
					             <tr>
					               <td>数量:</td>
					               <td><input id="srp_strtpmSumCnt" name="strtpmSumCnt" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					               <td>金额:</td>
					               <td><input id="srp_strtpmAmont" name="strtpmAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					               <td></td>
					               <td>成本额:</td>
					               <td><input id="srp_strtpmCostAmont" name="strtpmCostAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					             </tr>
					          </table>
			            </form>
			     </div>  
			     <div region="center" style="background:#eee;" border="false">
			            <table id="StRtPartsDetailTable"></table>
			     </div>  
		    </div>  
          </div>  
        </div>
	  </div>
    </div>
  </body>
</html>
