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
    
    <title>My JSP '入库单信息管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StGoodsStorage/stgoodsstoragemanager.js"></script>
   </head>
  <body>
         <div id="cc" class="easyui-layout" fit="true" border="false"> 
	        <div region="north" split="false" style="height:26px;" border="false">
			     <div  style="background:#eee;">
			     <privilege:enable code="StGoodsStorageAdd">
			       <a href="javascript:void(0);" id="sgs_addBtn" class="easyui-linkbutton" iconCls="icon-add" 
					plain="true" onclick="addPersonnel(1);">新增</a>
			     </privilege:enable>
			     <privilege:enable code="StGoodsStorageDelete">
			       <a href="javascript:void(0);" id="sgs_delBtn" class="easyui-linkbutton" iconCls="icon-remove" 
					plain="true" onclick="delStStorage()">删除</a>
			     </privilege:enable>
			     <privilege:enable code="StGoodsStorageSearch">
			       <a href="javascript:void(0);" id="sgs_serachBtn" class="easyui-linkbutton" iconCls="icon-search" 
					plain="true" onclick="searchByCondition_change();">查询</a>
			     </privilege:enable>
			     <privilege:enable code="StGoodsStorageClear">
			       <a href="javascript:void(0);"  id="sgs_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" 
					plain="true" onclick="clearSearchByCondition();">清空</a>
			     </privilege:enable>
			     <privilege:enable code="StGoodsStoragePrint">
			       <a href="javascript:void(0);" id="sgs_printBtn" class="easyui-linkbutton" iconCls="icon-print"
					 plain="true" onclick="addprint();">打印</a>
			     </privilege:enable>
			     <privilege:enable code="StGoodsStorageExport">
			       <a href="javascript:void(0);" id="sgs_exportBtn" class="easyui-linkbutton" iconCls="icon-export" 
					plain="true" onclick="_except();">Excel导出</a>
			     </privilege:enable>
			       <span id="button"></span>
			    </div>
	        </div>
	        <div region="center" style="background:#eee;" border="false" >
	         <div id="cc" class="easyui-layout" border="false" fit="true">  
			    <div region="center" style="background:#eee;" border="false" split="false">
				    <div id="tt" class="easyui-tabs" border="false"  fit="true">  
					    <div title="入库单汇总"  closable="false">  
						    <div id="cc" class="easyui-layout" border="false" fit="true">  
							    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
							       <form id="StGoodsStorageSearchForm" >
								      <table>
									      <tr>
									        <td>入库日期:</td>
									        <td><input id="sgmsm_storageDateStart"  name="storageDateStart" style="width:90px;" class="Wdate" ontextchanged="testaa();" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'sgmsm_storageDateEnd\',{d:-1})}'})" /></td>
									        <td>至</td>
									        <td><input id="sgmsm_storageDateEnd" name="storageDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'sgmsm_storageDateStart\',{d:0})}'})"/></td>
									        <td>入库单号:</td>
									        <td><input id="sgmsm_storageId" name="storageId"></td>
									        <td>采购单号:</td>
									        <td><input id="sgmsm_orderId" name="orderId"></td>
									        <td>供应商:</td>
									        <td><input id="sgmsm_relcampName" name="relcampName" onfocus="this.select();addRelcamp();" readonly="readonly" style="background-color:#c0d8d8;"/></td>
									        <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addRelcamp();"/></td>
									      </tr>
								      </table>
								   </form>
							    </div>  
				    			<div id="StGoodsStoregeMainTableDiv" region="center" border="false" split="false" style="background:#eee;">
				    			   <table id="StGoodsStoregeMainTable"></table>
				    			</div>
							</div>
			    		</div>
				        <div title="入库单明细" closable="false" fit="true" >
					      	<div id="cc" class="easyui-layout" border="false" fit="true">  
							       <div region="north" title="查询条件" split="false" style="overflow: hidden;padding:1px; height:130px;background:#eee;" border="false">
							            <form id="StGoodsStorageDetailForm" method="post" fit="true">
											<table>
											   <tr>
													<td>入库日期:</td>
													<td><input id="storageDate" name="storageDate" readonly="readonly" style="width:140px;"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" /></td>
													<td>入库单:</td>
													<td><input id="storageId" name="storageId" readonly="readonly" style="background-color:#c0d8d8;" size="10"/></td>
													<td>采购单:</td>
													<td><input id="orderId" name="orderId" onfocus="this.select();addStPurOrder();" readonly="readonly" style="background-color:#c0d8d8;"/></td>
													<td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addStPurOrder();"/></td>
													<td>供应商名称:</td>
													<td><input id="relcampName" name="relcampName"  readonly="readonly" style="background-color:#c0d8d8;"></td>
													<td><input type="hidden" id="relcampId" name="relcampId" readonly="readonly" style="background-color:#c0d8d8;"></td>
												</tr>
												<tr>
													<td>经办人:</td> 
													<td><input id="manager" name="manager" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  style="background-color:#c0d8d8;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
													<td>采购员:</td>
													<td><input id="buyerName" name="buyerName"  style="background-color:#c0d8d8;"><input type="hidden" id="buyer" name="buyer" size="15" readonly="readonly" style="background-color:#c0d8d8;"></td>
													<td>采购额:</td>
													<td><input id="totalAmount" name="totalAmount"   style="background-color:#c0d8d8;"></td>
													<td></td>
													<td>票据类型:</td>
													<td><input id="notesType" name="notesType"  style="background-color:#c0d8d8;"/></td>
													<td>付讫:</td>
													<td><input id="paid" name="paid" style="background-color:#c0d8d8;"/></td>
												</tr>
												<tr> 
													<td>发票号:</td> 
													<td colspan="3"><input id="bill" name="bill"   style="width:300px;" class="easyui-validatebox" validType="length[0,50]"></td>
													<td>验收人:</td>
													<td><input type="hidden" id="identifyman"  name="identifyman"><input id="identifymanName"  name="identifymanName" style="background-color:#c0d8d8;" onfocus="this.select();addBasStuff();" readonly="readonly" ></td>
													<td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="addBasStuff()"/></td>
													<td>仓别:</td>
													<td><input id="storeId" style="width:110px;" name="storeId"  class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllStorehouse.action',
															    editable : true,
																valueField:'id',
																panelHeight:130,
																required:true,
																mode:'remote',
																missingMessage:'仓别必填',
																textField:'text'"/></td>
													<td>税率:</td>
													<td><input id="taxRate" name="taxRate"   style="background-color:#c0d8d8;"></td>
												</tr>
												<tr>
										             <td>数量:</td>
										             <td><input id="totalNum" name="totalNum" style="background-color:#c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										             <td>未税额:</td>
										             <td><input id="notaxTotalamont"  name="notaxTotalamont"  style="background-color:#c0d8d8;"></td>
										             <td>税额:</td>
										             <td><input id="taxCount" name="taxCount" style="background-color:#c0d8d8;"></td>
										             <td></td>
										             <td>加价率:</td>
										             <td><input id="addpriceRate" style="width:110px;" name="addpriceRate"  class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.ADDPRICERATE.ADDPRICERATEKEY %>',
															    editable : false,
																valueField:'id',
																panelHeight:130,
																required:false,
																textField:'text'"/></td>
													 <td>备注:</td>
													 <td><input id="remark" name="remark" class="easyui-validatebox" validType="length[0,50]"/></td>
										         </tr>
											</table>
										</form>
							         </div>  
							         <div region="center" split="false" style="fit:true;background:#5b5b5b;" border="false">
								         <div id="cc" class="easyui-layout" closable="false" border="false" fit="true">  
										    <div region="center" split="false" border="false" style="overflow:false;fit:true;background:#eee;">
										         <table id="StGoodsStoregeDetailTable"></table>
										    </div>
										 </div>
								     </div> 
							     </div> 
						      </div> 
				           </div>
						</div>	 			 
			        </div>
		          </div>     
	      </div>
    </body>
</html>
