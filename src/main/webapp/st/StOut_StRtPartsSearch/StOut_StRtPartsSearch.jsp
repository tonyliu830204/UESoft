
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>入退单查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StOut_StRtPartsSearch/StOut_StRtPartsSearch.js"></script>
  </head>
  
   <body>
    <div id="cc" class="easyui-layout" fit="true" border="false">  
		       <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
			        <privilege:enable code="StPartsNowCountSearch">
		        	 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchByCondition();">查询</a>
			        </privilege:enable>
			        <privilege:enable code="StPartsNowCountClear">
			       		 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchByCondition();">清空</a>
			        </privilege:enable>
			        <span id="pnc_button"></span>
		       </div>
               <div region="center"  style="background:#eee;" split="false" border="false">   
                 <div id="st_out_rtparts_tabs" class="easyui-tabs" fit="true"  border="false">
			           <div title="出退查询汇总" style="display:block;" closable="false" fit="true"  border="false">  
			            <div id="cc1" class="easyui-layout" fit="true">
					      <div region="north" title="查询条件" split="false" style="overflow: hidden;height:85px;background:#eee;" border="false">
					       <form id="StOut_StRtPartsSearchForm">
					        <table>
					          <tr>
					            <td>入退日期:</td>
					            <td><input id="startTime" name="startTime" readonly="readonly" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/></td>
					            <td>至</td>
					            <td><input id="endTime" name="endTime" readonly="readonly" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'startTime\',{d:0})}'})"/></td>
					            <td>配件品牌:</td>
					            <td><input id="pbrdId" name="pbrdId" class="easyui-combobox" data-options="
								url : 'basPartsArchivesAction!findPartsBrand.action',
								valueField:'id',  
				    			textField:'text',
				    			required:false,
				    			mode : 'remote',
				    			validType:'isSelected[\'#pbrdId\']',
								invalidMessage : '请从下拉框中选择配件品牌',
				    			onSelect : function (record){
				    				$('typeId').combobox('clear');
				    				$('typeId').combobox('reload', 'basPartsArchivesAction!findPartsType.action?pbrdId=' + record.id);
				    			}"/></td>
					            <td></td>
					            <td>配件型号:</td>
					            <td><input id="typeId" name="typeId" class="easyui-combobox" data-options="
								url : 'basPartsArchivesAction!findPartsType.action',
								valueField:'id',  
				    			textField:'text',
				    			required:false,
				    			validType:'isSelected[\'#typeId\']',
								invalidMessage : '请从下拉框中选择配件型号',
				    			mode :'remote'"/></td>
					            <td>索赔</td>
					            <td><input id="claimsName" style="width:110px;" name="claimsName"  class="easyui-combobox"
								data-options="url:'${pageContext.request.contextPath}/StOutAction!loadClaimsType.action',
							    editable : false,
								valueField:'id',
								panelHeight:130,
								textField:'text'"/></td>
					          </tr>
					          <tr>
					            <td>配件编号:</td>
					            <td><input id="partsId" name="partsId" style="width:90px;" /></td>
					            <td></td>
					            <td></td>
					            <td>配件名称:</td>
					            <td><input id="partsName" name="partsName"/></td>
					            <td></td>
					            <td>仓别:</td>
					            <td><input id="storeId" style="width:110px;" name="storeId"  class="easyui-combobox"
																		data-options="url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
																	    editable : false,
																		valueField:'id',
																		panelHeight:130,
																		textField:'text'"/></td>
				                <td>备注：</td>
					            <td><input id="outItemRemark" name="outItemRemark"/></td>
					          </tr>
					       </table>
				      </form>
					      </div>
					      <div region="center" id="pnc_partsNowCountTableDiv" style="background:#eee;" border="false">
					         <table id="StOut_StRtPartsSearchTable"></table>
					      </div>  
			            </div>
			           </div>
                       <div title="出退查询明细" style="overflow:false;" closable="false" border="false" fit="true">  
                         <div id="cc2" class="easyui-layout" fit="true">
                          <div region="north" title="查询条件" split="false" style="overflow: hidden;height:85px;background:#eee;" border="false">
					        <form id="StOut_StRtPartsDetailForm">
					         <table>
					          <tr>
					            <td>入退日期:</td>
					            <td><input  name="startTime" readonly="readonly"/></td>
					            <td>至</td>
					            <td><input  name="endTime" readonly="readonly"/></td>
					            <td>配件名称:</td>
					            <td><input name="partsName" readonly="readonly"/></td>
					            <td></td>
					            <td>库位:</td>
					            <td><input name="partsLibrary" readonly="readonly"/></td>
					            <td>单位</td>
					            <td><input  name="punitName" readonly="readonly"/></td>
					            <td>配件品牌</td>
					            <td><input  name="pbrdName" readonly="readonly"/></td>
					          </tr>
					          <tr>
					            <td>仓别:</td>
					            <td><input  name="storeName" readonly="readonly"/></td>
					            <td></td>
					            <td></td>
					            <td>数量:</td>
					            <td><input  name="itemCount" readonly="readonly"/></td>
					            <td></td>
					            <td>入退含税成本价:</td>
					            <td><input  name="taxPrice" readonly="readonly"/></td>
					            <td>入退含税金额：</td>
					            <td><input  name="taxCastamont" readonly="readonly"/></td>
					            <td>索赔分类：</td>
					            <td><input  name="claimsName" readonly="readonly"/></td>
					          </tr>
					         </table>
					        </form>
					      </div>
					      <div region="center" id="pnc_partsNowCountTableDiv" style="background:#eee;" border="false">
					        <table id="StOut_StRtPartsDetailSearchTable"></table>
					      </div>  
			            </div>
                       </div>
                 </div>
               </div>
        </div>
   </body>
</html>
