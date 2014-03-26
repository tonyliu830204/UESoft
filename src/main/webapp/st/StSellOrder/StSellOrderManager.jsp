<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '销售单管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StSellOrder/stsellordermanager.js"></script>
  </head>
  
  <body>
     <div id="cc" class="easyui-layout" fit="true" border="false">  
        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
         <privilege:enable code="StSellOrderAdd">
         <a href="javascript:void(0);" id="slo_add" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addPersonnel(1);">新增</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderDelete">
         <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" id="slo_delete" onclick="deleteSellOrder()">删除</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderUpdate">
         <a href="javascript:void(0);" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" id="slo_update" onclick="addPersonnel(2);">修改</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderSearch">
         <a href="javascript:void(0);" id="slo_searchBtn" class="easyui-linkbutton" iconCls="icon-search" 
			  		plain="true" onclick="searchByCondition();">查询</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderClear">
         <a href="javascript:void(0);" id="slo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" 
	          		onclick="clearSearchByCondition();">清空</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderPrint">
          <a href="javascript:void(0);" id="slo_printBtn" class="easyui-linkbutton"
				iconCls="icon-print" plain="true">打印</a>
         </privilege:enable>
         <privilege:enable code="StSellOrderExport">
          <a href="javascript:void(0);" id="slo_exportBtn" class="easyui-linkbutton"
				iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
         </privilege:enable>
          <a href="javascript:void(0);" id="slo_preclrBtn" class="easyui-linkbutton"
				iconCls="icon-redo" plain="true" onclick="_changePreclr();">转到结算</a>
		 <span id="button"></span>
        </div>
        <div region="center"  style="background:#eee;" split="false" border="false">   
		   <div id="ssom_tabs" class="easyui-tabs" fit="true" border="false">  
		     <div title="销售单汇总" style="display:block;">  
		        <div id="cc" class="easyui-layout" fit="true" border="false">  
				    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" border="false">
					   <form id="stSellOrderSearchForm">
						    <table>
							    <tr>
							       <td>销售日期</td>
								   <td><input id="sellmmDateStart" name="sellmmDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'sellmmDateStart\',{d:-1})}'})"/></td>
								   <td>至</td>
								   <td><input id="sellmmDateEnd" name="sellmmDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'sellmmDateStart\',{d:0})}'})"/></td>
								   <td>车牌照</td>
								   <td><input name="carLicense"/></td>
								   <td>销售单号</td>
								   <td><input name="sellmmId"/></td>
							       <td>客户名称</td>
								   <td><input name="customName" style="width:110px;" class="easyui-combobox"
									data-options="url : '${pageContext.request.contextPath}/frtOptionsAction!findAllCustom.action',
									editable : false,
									valueField:'id',
									textField:'text' "/></td>
								   <td>备注</td>
								   <td><input name="sellmmRemark"/></td>
							    </tr>
						     </table>
					    </form>
				    </div>
				    <div region="center" id="stSellOrderSearchTableDiv" border="false" style="background:#eee;">
				        <table id="stSellOrderSearchTable"></table>
				    </div>  
				</div>  
		     </div>  
		     <div title="销售单明细" closable="false" fit="true">  
		        <div id="cc" class="easyui-layout" fit="true">  
				    <div region="north" split="false" style="overflow: hidden;height:80px;background:#eee;" border="false">
					     <form id="stSellOrderMainForm">
						    <table>
							    <tr>
							       <td>销售日期:</td> 
							       <td><input id="sellmmDate" name="sellmmDate" readonly="readonly"  style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
							       <td>销售单:</td>
							       <td><input id="sellmmId" name="sellmmId" readonly="readonly" style="background-color: #c0d8d8;"></td>
							       <td></td>
							       <td>车牌照:</td>
							       <td><input id="carLicense" name="carLicense"  onfocus="this.select();slo_CarLicenseSelect();"  style="background-color: #c0d8d8;"/></td>
							       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="slo_CarLicenseSelect();"/></td>
							       <td>客户名称:</td>
							       <td><input id="sellcustomName" name="sellcustomName" class="easyui-validatebox" required="true" missingMessage="客户名称必填"  readonly="readonly" onfocus="this.select();slo_CustomSelect()" style="background-color: #c0d8d8;"/>
							       <input type="hidden" id="sellcustomId" name="sellcustomId"  readonly="readonly" style="background-color: #c0d8d8;"/></td>
							       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="slo_CustomSelect();"/></td>
							       <td>备注:</td>
							       <td><input id="sellmmRemark" name="sellmmRemark" class="easyui-validatebox" validType="length[0,30]"/></td>
							       <td>票据类型:</td> 
								   <td><input id="preclrInoiceType" name="preclrInoiceType" class="easyui-combobox" 
								   data-options="url : '${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
							       editable : false,
								   valueField:'id',
								   required:true,
                                   missingMessage:'票据类型必填',
								   panelHeight:130,
								   textField:'text'"/></td>
							    </tr>
						        <tr>
						           <td>分类:</td>
						           <td><input id="psellId" name="psellId" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/StSellOrderAction!loadBasPartsSell.action',
												    editable : false,
													valueField:'id',
													required:true,
													missingMessage:'分类必填',
													textField:'text'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						           <td>领料员:</td>
						           <td><input id="sellmmStfName" name="sellmmStfName"  class="easyui-validatebox" required="true" missingMessage="领料员必填"  readonly="readonly" onfocus="this.select();slo_PickingMemberSelect();"  style="background-color: #c0d8d8;"/><input type="hidden" id="sellmmStfId" name="sellmmStfId" /></td>
						           <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="slo_PickingMemberSelect();"/></td>
						           <td>经办人:</td>
						           <td><input  id="sellManagerName" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  name="sellManagerName"  readonly="readonly" style="background-color: #c0d8d8;"/>
						           <input type="hidden" id="sellManager" name="sellManager" /></td>
						           <td></td>
						           <td>销价系数:</td>
						           <td><input id="priceQuotiety" name="priceQuotiety" onfocus="this.select();" onblur="identify();"/></td>
						           <td></td>
						           <td>票据编号:</td>
						           <td><input id="billType" name="billType" class="easyui-validatebox" validType="length[0,30]"/></td>
						           <td></td>
						        </tr>
						        <tr>
						           <td>数量:</td>
						           <td><input id="sellmmCnt" name="sellmmCnt" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						           <td>销售额:</td>
						           <td><input id="sellmmSumAmount" name="sellmmSumAmount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						           <td></td>
						           <td>成本额:</td>
						           <td><input id="sellmmSumCost" name="sellmmSumCost" readonly="readonly" style="background-color: #c0d8d8;"/></td>
						           <td></td>
						           <td>付款金额:</td>
						           <td><input id="" name=""/></td>
						           <td></td>
						           <td>价格选择</td>
						           <td><input id="priceType" style="width:110px;" name="priceType" class="easyui-combobox"
													    data-options="url : '${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.SELLPRICETYPE.SELLPRICETYPEKEY %>',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														mode:'remote',
														required:true,
														missingMessage:'价格选择必填',
														textField:'text'"/></td>
						        </tr>
						    </table>
					     </form>
				    </div>  
				    <div region="center" style="background:#eee;" border="false">
				         <table id="slo_selldOrderItemTable"></table>
				    </div>
				    <div region="south" split="false" style="height:30px;background:#eee;">
					    <table>
						    <tr>
						      <td colspan="7">
					             <a href="javascript:void(0)" id="slo_addParts" iconCls="icon-add"  class="easyui-linkbutton"  onclick="addParts();" plain="true">配件增加</a>
							     <a href="javascript:void(0)" id="slo_delParts" iconCls="icon-remove"  class="easyui-linkbutton" onclick="deleteParts();" plain="true">配件删除</a>
							  </td>
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
