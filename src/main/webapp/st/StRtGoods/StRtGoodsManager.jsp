<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '退货单管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtGoods/strtgoodsmanager.js"></script>
  </head>
  
  <body>
      <div id="cc" class="easyui-layout"fit="true">  
        <div region="north" split="false" style="height:26px;" border="false">
             <div style="background: #eee;">
             <privilege:enable code="StRtGoodsAdd">
                  <a href="javascript:void(0);" id="srgm_addBtn" class="easyui-linkbutton"
								   iconCls="icon-add" plain="true" onclick="addPersonnel(1);">新增</a>
             </privilege:enable>
             <privilege:enable code="StRtGoodsDelete">
                 <a href="javascript:void(0);" id="srgm_delBtn" class="easyui-linkbutton"
						           iconCls="icon-remove" plain="true" onclick="deleteStRtGoods()">删除</a>
             </privilege:enable>
             <privilege:enable code="StRtGoodsSearch">
                <a href="javascript:void(0);" id="srgm_searchBtn" class="easyui-linkbutton" 
                       iconCls="icon-search" plain="true" onclick="searchByConditon();">查询</a>
             </privilege:enable>
             <privilege:enable code="StRtGoodsClear">
	             <a href="javascript:void(0);" id="srgm_clearBtn" class="easyui-linkbutton" 
	             iconCls="icon-cancel" plain="true" onclick="clearSearchByCondition();">清空</a>
             </privilege:enable>
             <privilege:enable code="StRtGoodsPrint">
                 <a href="javascript:void(0);" id="srgm_printBtn" class="easyui-linkbutton"
									iconCls="icon-print" onclick="addprint();" plain="true">打印</a>
             </privilege:enable>
             <privilege:enable code="StRtGoodsExport">
                  <a href="javascript:void(0);" id="srgm_exportBtn" class="easyui-linkbutton"
									iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
             </privilege:enable>
				 <span id="button"></span>		
								<%--<a href="javascript:void(0);" id="srgm_updateBtn" class="easyui-linkbutton"
									iconCls="icon-edit" plain="true" onclick="addPersonnel(2);">修改</a>
								--%>
			 </div>
        </div>  
        <div region="center"  style="background:#eee;">
              <div id="tt" class="easyui-tabs" fit="true" border="false">
			    <div title="退货单汇总" style="display:block;" closable="false">  
			        <div id="cc" class="easyui-layout" fit="true">  
					    <div region="north" title="查询条件" split="false" style="overflow: hidden;padding:1px;height:55px;background:#eee;" border="false">
						    <form id="srgm_StRtGoodsForm">
							    <table>
							      <tr>
							       <td>退货日期:</td>
							       <td><input  id="srgm_strtgmDateStart" name="strtgmDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'srgm_strtgmDateEnd\',{d:-1})}'})"/></td>
							       <td>至</td>
							       <td><input id="srgm_strtgmDateEnd" name="strtgmDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'srgm_strtgmDateStart\',{d:0})}'})"/></td>
							       <td>退货单号:</td>
							       <td><input id="srgm_strtgmId" name="strtgmId"/></td>
							       <td>供应商:</td>
							       <td><input type="hidden" id="srgm_relcampId" name="relcampId"/><input name="relcampName" id="srgm_relcampName"  style="background-color: #c0d8d8;" onfocus="this.select();add_Srgm_RelCamp();" readonly="readonly" /></td>
							       <td> <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="add_Srgm_RelCamp();"/></td>
							       <td>仓库名称:<input id="storeId" name="storeId" style="width:110px;" class="easyui-combobox"
															data-options="url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
														    editable : false,
															valueField:'id',
															panelHeight:130,
															textField:'text'"/>
									</td>
							      </tr>
							    </table>
						    </form>
					    </div>  
					    <div region="center" id="srgm_StRtGoodsMainTableDiv" border="false" style="background:#eee;">
					        <table id="srgm_StRtGoodsMainTable"></table>
					    </div>
					</div>  
			    </div>
			    <div title="退货单明细" closable="false" style="overflow:auto;display:block;">  
		            <div id="cc" class="easyui-layout"border="false" fit="true">  
					    <div region="north" title="查询条件" split="false" border="false"  style="overflow:hidden;padding:1px;height:105px;background:#eee;">
							   <form id="StRtGoodsDetailForm">		 
								   <table>
								     <tr>
								        <td>退货日期:</td>
								        <td><input id="strtgmDate" name="strtgmDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm'})" /></td>
								        <td>退货单号:</td>
								        <td><input id="strtgmId" name="strtgmId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								        <td>供应商:</td>
								        <td><input type="hidden" id="relcampId" name="relcampId"/><input id="relcampName" name="relcampName" class="easyui-validatebox" required="true" missingMessage="供应商必填"  onfocus="this.select();add_Srgd_RelCamp();" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								        <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="add_Srgd_RelCamp();"/></td>
								        <td>入库单:</td>
								        <td><input id="storageId" name="storageId" class="easyui-validatebox" required="true" missingMessage="入库单必填"  onfocus=" this.select();searchStGoodsStorageInfo();" style="background-color: #c0d8d8;"/></td>
								        <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="searchStGoodsStorageInfo()"/></td>
								        <td>经办人:</td>
								        <td><input id="manager" name="manager" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"   readonly="readonly" style="background-color: #c0d8d8;"/><input type="hidden" id="managerId" name="managerId"></td>
								     </tr>
								     <tr> 
								        <td>采购员:</td>
								        <td><input type="text"size="25" id="buyer" name="buyer"  readonly="readonly" style="background-color: #c0d8d8;width:140px;"/><input type="hidden" id="buyerId" name="buyerId" /></td>
								        <td>仓别:</td>
								        <td><input id="storeName" name="storeName" readonly="readonly" style="background-color: #c0d8d8;"/>
								            <input type="hidden" id="srgm_storeId" name="storeId"/>
								        </td>
								        <td>退货数量:</td>
								        <td><input id="totalNum" name="totalNum" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								        <td></td>
								        <td>含税成本额:</td>
								        <td><input id="strtgmSumCost" name="strtgmSumCost" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								        <td></td>
								         <td>未税成本额:</td>
								        <td><input id="strtgmSumNoCost" name="strtgmSumNoCost" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								        <td></td>
								     </tr>
								     <tr>
								        <td>备注:</td>
								        <td><input id="strtgmRemark" name="strtgmRemark" style="width:140px;" class="easyui-validatebox" validType="length[0,100]"/></td>
								     </tr>
								  </table>
							 </form>
					    </div>  
					    <div region="center" spilt="false" border="false"  style="background:#eee;">
					         <table id="srg_StRtGoodsDetailTable"></table>
					    </div>
			      </div>
		      </div>
		   </div>
	    </div>
    </div>  
 </body>
</html>
