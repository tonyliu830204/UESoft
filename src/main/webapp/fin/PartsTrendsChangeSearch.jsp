<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>配件综合查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/PartsTrendsChangeSearch.js"></script>
  </head>
   <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
        <privilege:enable code="PARTSTRENDSSEARCH">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="searchByCondition();">查询</a> 
    	</privilege:enable>
    	<privilege:enable code="PARTSTRENDSCLEAR"> 
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="clearSearchByCondition();">清空</a>  
    	</privilege:enable>
    	<privilege:enable code="PARTSTRENDSEXPORT">
    		<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="exception();">导出</a>
    	</privilege:enable>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">
		    	<div id="partsIntegratedQuery_tabs" class="easyui-tabs" data-options="fit:true,border:false"> 
		    	 <!-- 
				    <div title="出库退料查询">
				      <div class="easyui-layout" data-options="fit:true,border:false">
			            <div data-options="region:'north',border:false"
							style="height:53px;background:#eee;overflow: hidden;">
							<form id="StOutAndRtPartsSearchForm">
							   <table>
							    <tr>
							      <td>日期:</td>
							      <td><input id="ptcs_stomDateStart" name="ptcs_stomDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'ptcs_stomDateEnd\',{d:-1})}'})"/></td>
							      <td align="center">至</td>
							      <td><input id="ptcs_stomDateEnd" name="ptcs_stomDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'ptcs_stomDateStart\',{d:0})}'})"/></td>
							      <td>车品牌:</td>
							      <td><input id="ptcs_cbrdName" name="ptcs_cbrdName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarBrand.action',																   
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
							      <td>车型:</td>
							      <td><input id="ptcs_stypeName" name="ptcs_stypeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarType.action',
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>							  
							    </tr>
							     <tr>
							      <td>配件编码:</td>
							      <td colspan="3"><input id="ptcs_partsId" name="" style="width:243px;"/></td>
							      <td>配件名称:</td>
							      <td><input id="ptcs_partsName" name="ptcs_partsName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadPartsName.action',										    
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
							      <td>仓别:</td>
							      <td><input id="pcts_storeName" name="pcts_storeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadStorehouse.action',
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>						    
							    </tr>
							   </table>
							</form>
				        </div>
				        <div data-options="region:'center',border:false" style="background:#eee;">
				            <table id="StOutAndRtPartsSearchTable"></table>  
				        </div>
				           <div data-options="region:'south',border:false" style="height:30px;background:#eee;overflow: hidden;">
				            <table>
				               <tr>
				                 <td>销量合计:</td>
				                 <td><input id="sumItemCount" name="sumItemCount"/></td>
				                 <td>销量金额合计:</td>
				                 <td><input id="sumAmount" name="sumAmount"/></td>
				                 <td>成本金额合计:</td>
				                 <td><input id="sumCastAmount" name="sumCastAmount"/></td>
				               </tr>
				            </table>
						   </div>
				       </div>
				    </div>  
				    <div title="销售退料查询">  
				      <div class="easyui-layout" data-options="fit:true,border:false">
			            <div data-options="region:'north',border:false"
							style="height:53px;background:#eee;overflow: hidden;">
							<form id="SellPartsAndSellRtPartsSearchForm">
							   <table>
							    <tr>
							      <td>日期:</td>
							      <td><input id="spsrp_stomDateStart" name="spsrp_stomDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'spsrp_stomDateEnd\',{d:-1})}'})"/></td>
							      <td align="center">至</td>
							      <td><input id="spsrp_stomDateEnd" name="spsrp_stomDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'spsrp_stomDateStart\',{d:0})}'})"/></td>
							      <td>车品牌:</td>
							      <td><input id="spsrp_cbrdName" name="spsrp_cbrdName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarBrand.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	disabled:true,
																	textField:'text'"/></td>
							      <td>车型:</td>
							      <td><input id="spsrp_stypeName" name="spsrp_stypeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarType.action',
																    editable : false,
																	valueField:'id',disabled:true,
																	panelHeight:130,
																	textField:'text'"/></td>							     
							     </tr>
							     <tr>
							      <td>配件编码:</td>
							      <td colspan="3"><input id="spsrp_partsId" name="spsrp_partsId" style="width:243px;"/></td>
							      <td>配件名称:</td>
							      <td><input id="spsrp_partsName" name="spsrp_partsName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadPartsName.action',
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
							      <td>仓别:</td>
							      <td><input id="spsrp_storeName" name="spsrp_storeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadStorehouse.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>							   
							    </tr>
							   </table>
							</form>
				        </div>
				        <div data-options="region:'center',border:false" style="background:#eee;">
				            <table id="SellPartsAndSellRtPartsTable"></table>  
				        </div>
				       </div>
				    </div>  
				    <div title="入库退货查询">  
				        <div class="easyui-layout" data-options="fit:true,border:false">
			            <div data-options="region:'north',border:false"
							style="height:53px;background:#eee;overflow: hidden;">
							<form id="StStorageAndStRtGoodsSearchForm">
							   <table>
							    <tr>
							      <td>日期:</td>
							      <td><input id="sssrgs_stomDateStart" name="sssrgs_stomDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'sssrgs_stomDateEnd\',{d:-1})}'})"/></td>
							      <td align="center">至</td>
							      <td><input id="sssrgs_stomDateEnd" name="sssrgs_stomDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'sssrgs_stomDateStart\',{d:0})}'})"/></td>
							      <td>车品牌:</td>
							      <td><input id="sssrgs_cbrdName" name="sssrgs_cbrdName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarBrand.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	disabled:true,
																	textField:'text'"/></td>
							      <td>车型:</td>
							      <td><input id="sssrgs_stypeName" name="sssrgs_stypeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadCarType.action',
																    editable : false,
																	valueField:'id',disabled:true,
																	panelHeight:130,
																	textField:'text'"/></td>							    
							     </tr>
							     <tr>
							      <td>配件编码:</td>
							      <td colspan="3"><input id="sssrgs_partsId" name="sssrgs_partsId" style="width:243px;"/></td>
							      <td>配件名称:</td>
							      <td><input id="sssrgs_partsName" name="sssrgs_partsName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadPartsName.action',																   
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
							      <td>仓别:</td>
							      <td><input id="sssrgs_storeName" name="sssrgs_storeName" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadStorehouse.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>							     
							    </tr>
							   </table>
							</form>
				        </div>
				        <div data-options="region:'center',border:false" style="background:#eee;">
				            <table id="StStorageAndStRtGoodsTable"></table>  
				        </div>
				       </div>
				    </div>  --> 
				    <div title="库存动态查询">
				        <div class="easyui-layout" data-options="fit:true,border:false">
			            <div data-options="region:'north',border:false" style="height:53px;background:#eee;overflow: hidden;">
							<form id="partsDynamicTableSearchForm">
							   <table>
							    <tr>
							      <td>日期:</td>
							      <td><input id="dynamic_stomDateStart" name="stomDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'dynamic_stomDateEnd\',{d:-1})}'})"/></td>
							      <td align="center">至</td>
							      <td><input id="dynamic_stomDateEnd" name="stomDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'dynamic_stomDateStart\',{d:0})}'})"/></td>
							      <td>配件品牌:</td>
							      <td><input type="text" id="partsArchives_add_pbrdId" name="cbrdId" class="easyui-combobox" data-options="
									url : '${pageContext.request.contextPath}/basPartsArchivesAction!findPartsBrand.action',
									valueField:'id',  
					    			textField:'text',
					    			mode : 'remote',
					    			onSelect : function (record){
					    				$('#partsArchives_add_ptypeId').combobox('clear');
					    				$('#partsArchives_add_ptypeId').combobox('reload', '${pageContext.request.contextPath}/basPartsArchivesAction!findPartsType.action?pbrdId=' + record.id);
					    			} " style="width: 120px;"/></td>
							      <td>配件型号:</td>
							      <td><input type="text" id="partsArchives_add_ptypeId" name="stypeId" class="easyui-combobox" data-options="
									url:'${pageContext.request.contextPath}/basPartsArchivesAction!findPartsType.action',
									valueField:'id',  
					    			textField:'text',
					    			mode : 'remote'" style="width: 120px;"/></td>
							     </tr>
							     <tr>
							      <td>配件编码:</td>
							      <td colspan="3"><input id="dynamic_partsId" name="partsId" style="width:243px;"/></td>
							      <td>仓别:</td>
							      <td><input id="dynamic_storeName" name="storeId" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadStorehouse.action',
																    editable : false,
																	valueField:'id',
																	panelHeight:130,
																	textField:'text'"/></td>
							    </tr>
							   </table>
							</form>
				        </div>
				        <div id="partsDynamicTableDiv" data-options="region:'center',border:false" style="background:#eee;">
				            <table id="partsDynamicTable"></table>
				        </div>
				       </div>  
				    </div>  
		         </div>  
     </div>
  </body>
</html>
