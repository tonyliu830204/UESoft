<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '库存量查询' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPartsNowCount/stpartsnowcountmananger.js"></script>
  </head>
  
   <body>
    <div id="cc" class="easyui-layout" fit="true" border="false">  
	     <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
	        <privilege:enable code="StPartsNowCountSearch">
	        	 <a href="javascript:void(0);" class="easyui-linkbutton" id="pnc_search" iconCls="icon-search" plain="true" onclick="pnc_searchByCondition();">查询</a>
	        </privilege:enable>
	        <privilege:enable code="StPartsNowCountClear">
	       		 <a href="javascript:void(0);" class="easyui-linkbutton" id="pnc_clear" iconCls="icon-cancel" plain="true" onclick="pnc_clearSearchByCondition();">清空</a>
	        </privilege:enable>
	        <privilege:enable code="StPartsNowCountPriceChange">
	        	 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-change" plain="true" onclick="addPersonnel();">价格变更</a>
	        </privilege:enable>
	        <span id="pnc_button"></span>
	     </div>
		 <div region="center"  split="false" border="false">
		       <div id="cc" class="easyui-layout" fit="true">
				    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:87px;background:#eee;" border="false">
					    <form id="pnc_PartsNowCountForm">
					      <table>
						      <tr>
						        <td>配件编号一:</td>
						        <td><input id="partsId" name="partsId"/></td>
						        <td>配件名称:</td>
						        <td><input id="partsName"  name="partsName" onkeypress=" if(event.keyCode==13) { partsNameSelect(); return false;}" style="background-color: #c0d8d8;"/></td>
						        <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="partsNameSelect();" /></td>
						        <td>配件部位:</td>
						        <td><input id="posName" style="width:110px;" name="posName" class="easyui-combobox"
													data-options="url:'${pageContext.request.contextPath}/StPartsNowCountAction!posNameSearchByCondition.action',
												    editable : false,
													valueField:'id',
													panelHeight:120,
													textField:'text'"/>
								</td>
								<td></td>
						        <td>库位:</td>
						        <td><input id="partsLibrary" name="partsLibrary"/></td>
						        <td>入库积压:</td>
						        <td><input  id="pnc_storageDateStart" style="width:90px;" name="pnc_strtgmDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'pnc_storageDateEnd\',{d:-1})}'})"/></td>
						        <td>至</td>
						        <td><input id="pnc_storageDateEnd"  style="width:90px;" name="pnc_strtgmDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'pnc_storageDateStart\',{d:0})}'})"/></td>
<%--<td colspan="2"><input type="checkbox" id="partsNowCountZero" name="partsNowCountZero" style="width:15px;">为零显示</td>--%>
						      </tr>
					          <tr>
					            <td>配件编号二:</td>
					            <td><input id="partsId2"  name="partsId2" /></td>
					            <td>配件品牌:</td>
					            <td><input id="pbrdName" name="pbrdName" onkeypress=" if(event.keyCode==13) { partsBrandSelect(); return false;}" style="background-color: #c0d8d8;"/></td>
					            <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="partsBrandSelect();" /></td>
					            <td>配件型号:</td>
					            <td><input id="ptypeName" name="ptypeName" onkeypress=" if(event.keyCode==13) { partsTypeSelect(); return false;}" style="background-color: #c0d8d8;"/></td>
					            <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="partsTypeSelect();" /></td>
					            <td>仓别:</td>
					            <td><input id="storeName" style="width:110px;" name="storeName" class="easyui-combobox"
															data-options="url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
														    editable : false,
															valueField:'id',
															panelHeight:130,
															textField:'text'"/>
								</td>
								<td>出库积压:</td>
								<td><input id="pnc_strtgmDateStart"  style="width:90px;" name="pnc_storageDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'pnc_strtgmDateEnd\',{d:-1})}'})"/></td>
								<td>至</td>
								<td><input id="pnc_strtgmDateEnd"  style="width:90px;" name="pnc_storageDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'pnc_strtgmDateStart\',{d:0})}'})"/></td>
								<td>查询方式:</td>
						        <td><select id="pnc_searchStyle" name="searchStyle">
						                <option selected="selected"></option>
									    <option value="低下限库存">低下限库存</option>
									    <option value="超上限库存">超上限库存</option>
									    <option value="库存量为零">库存量为零</option>
								    </select></td>
					          </tr>
					      </table>
					    </form>
				    </div>
				    <div region="center" id="pnc_partsNowCountTableDiv" style="background:#eee;" border="false">
				      <table id="pnc_partsNowCountTable"></table>
				    </div>
   					<div data-options="region:'south',border:false"style="background: #eee;height: 30px">
						<form id="collectPartsChangePriceForm">
							<table>
								<tr>
								    <td style="width:120px;">配件种类:</td><td ><input name="classfication" id="classfication" readonly="readonly"></td>
									<td style="width:120px;">库存数量:</td><td ><input name="sumCount" id="sumCount" readonly="readonly"></td>
									<td style="width:80px;">含税金额:</td><td ><input name="sumTaxPrice" id="sumTaxPrice" readonly="readonly"></td>
									<td style="width:100px;">未税金额:</td><td ><input name="sumNoTaxPrice" id="sumNoTaxPrice"readonly="readonly"></td>
								</tr>
							</table>
						</form>
					</div> 
			   </div>
		 </div>  
	</div>
   </body>
</html>
