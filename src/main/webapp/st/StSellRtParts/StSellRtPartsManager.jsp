<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '销售退料管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StSellRtParts/stsellrtpartsmanager.js"></script>
  </head>
  
  <body>
     <div id="cc" class="easyui-layout" fit="true" border="false">
        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
         <privilege:enable code="StSellRtPartsAdd">
          <a href="javascript:void(0);" id="slo_add" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addPersonnel();">新增</a>
         </privilege:enable>
          <privilege:enable code="StSellRtPartsDelete">
           <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" id="slo_delete" onclick="delStSellRtPartsMain()">删除</a>
         </privilege:enable>
          <privilege:enable code="StSellRtPartsSearch">
          <a href="javascript:void(0);" id="slo_searchBtn" class="easyui-linkbutton" iconCls="icon-search" 
			       plain="true" onclick="searchByCondition();">查询</a>
         </privilege:enable>
          <privilege:enable code="StSellRtPartsClear">
             <a href="javascript:void(0);" id="slo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" 
	               onclick="clearSearchByCondition();">清空</a>
         </privilege:enable>
          <privilege:enable code="StSellRtPartsPrint">
          <a href="javascript:void(0);" id="slo_printBtn"  class="easyui-linkbutton"
				iconCls="icon-print" plain="true">打印</a>
         </privilege:enable>
          <privilege:enable code="StSellRtPartsExport">
           <a href="javascript:void(0);" id="slo_exportBtn" class="easyui-linkbutton"
				iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
         </privilege:enable>
			  <span id="button"></span>
        </div>
        <div region="center"  style="background:#eee;" split="false" border="false">   
		    <div id="tt" class="easyui-tabs" fit="true" border="false">  
			    <div title="销售退料单汇总" style="display:block;" closable="false" fit="true" border="false">  
			       <div id="cc" class="easyui-layout"  fit="true" border="false">  
					    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" cloasble="false" border="false">
						    <form id="StSellRtPartsSearchForm">
							    <table>
							      <tr>
							        <td>退料日期:</td>
							        <td><input id="strtpmDateStart" name="strtpmDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'strtpmDateqiEnd\',{d:-1})}'})"/></td>
							        <td>至</td>
							        <td><input id="strtpmDateqiEnd" name="strtpmDateqiEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'strtpmDateStart\',{d:0})}'})"/></td>
							        <td>退料单号:</td>
							        <td><input  name="strtpmId"/></td>
							        <td>销售单号:</td>
							        <td><input  name="receptionId"/></td>
							        <td>车牌照:</td>
							        <td><input  name="carLicense"/></td>
							      </tr>
							    </table>
						    </form>
					    </div>
					    <div region="center" id="StSellRtPartsMainTableDiv" style="background:#eee;background:#eee;" aplit="false" border="false">
						   <table id="StSellRtPartsMainTable"></table>
					    </div>  
					</div>  
			    </div>
			    <div title="销售退料单明细" style="overflow:false;display:block;" border="false"  closable="false">  
			         <div id="cc" class="easyui-layout" fit="true" border="false">  
					    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:105px;background:#eee;" border="false">
				             <form id="StSellRtPartsMainFrom">
						        <table>
						          <tr>
						            <td>退料日期:</td>
						            <td><input id="ssrp_strtpmDate" name="strtpmDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm'})"/></td>
						            <td>退料单号:</td>
						            <td><input id="ssrp_strtpmId" name="strtpmId" style="background-color: #c0d8d8;"/></td>
						            <td></td>
						            <td>销售单号:</td>
						            <td><input id="ssrp_sellmmId" name="receptionId"  class="easyui-validatebox" required="true" missingMessage="销售单号必填" onfocus="this.select();ssrp_sellRecepSelect();" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						            <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="ssrp_sellRecepSelect();"/></td>
						            <td>车牌:</td>
						            <td><input id="ssrp_carLicense" name="carLicense" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						          </tr>
						          <tr>
						            <td>客户名称:</td>
						            <td><input id="ssrp_customName" name="customName" readonly="readonly" style="width:125px;background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						            <td>退料人员:</td>
						            <td><input type="hidden" id="ssrp_stfId" name="stfId"/><input id="ssrp_stfName" name="ssrp_stfName"  class="easyui-validatebox" required="true" missingMessage="退料人员必填" onfocus="this.select();ssrp_stufSelect();"  style="background-color: #c0d8d8;"/></td>
						            <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="ssrp_stufSelect();"/></td>
						            <td>经办人员:</td>
						            <td><input id="ssrp_manager" name="manager" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;"/></td>
						            <td></td>
						            <td>备注:</td>
						            <td><input id="ssrp_strtpmRemark" name="strtpmRemark" class="easyui-validatebox" validType="length[0,100]"/></td>
						          </tr>
						          <tr>
						            <td>数量:</td>
						            <td><input id="ssrp_strtpmSumCnt" name="strtpmSumCnt" readonly="readonly" style="background-color: #c0d8d8;width:125px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						            <td>含税成本额:</td>
						            <td><input id="ssrp_strtpmAmont" name="strtpmAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						            <td></td>
						            <td>销售额:</td>
						            <td><input id="ssrp_strtpmCostAmont" name="strtpmCostAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						          </tr>
						        </table>
						    </form>
					    </div>  
					    <div region="center" style="background:#eee;" border="false">
					            <table id="StSellPartsDetailTable"></table>
					    </div>
					 </div>
			    </div>  
		    </div>
		  </div>
	 </div>
  </body>
</html>
