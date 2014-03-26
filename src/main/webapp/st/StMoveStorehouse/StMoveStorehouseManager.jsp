<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP '移仓单管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StMoveStorehouse/stmovestorehousemanager.js"></script>
  </head>
  <body>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
           <div region="north"  split="false" style="height:30px;background: #eee;" border="false"> 
            <privilege:enable code="StMoveStorehouseAdd">
             <a href="javascript:void(0);" id="smsh_add" class="easyui-linkbutton"
				  iconCls="icon-add" plain="true" onclick="addPersonnel(1);">新增</a>
            </privilege:enable>
           <privilege:enable code="StMoveStorehouseDelete">
           <a href="javascript:void(0);" class="easyui-linkbutton"
				  iconCls="icon-remove" plain="true" id="smsh_delete" onclick="deleteStMoveStorehouse()">删除</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehouseUpdate">
            <a href="javascript:void(0);" class="easyui-linkbutton"
				  iconCls="icon-edit" plain="true" id="smsh_update" onclick="addPersonnel(2);">修改</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehouseSearch">
            <a href="javascript:void(0);" id="smsh_searchBtn" class="easyui-linkbutton" 
            iconCls="icon-search" plain="true" onclick="searchByCondition();">查询</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehouseClear">
            <a href="javascript:void(0);" id="smsh_clearBtn" class="easyui-linkbutton" 
            iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehousePrint">
             <a href="javascript:void(0);" id="smsh_printBtn" class="easyui-linkbutton"
				  iconCls="icon-print" plain="true">打印</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehouseExport">
             <a href="javascript:void(0);" id="smsh_exportBtn" class="easyui-linkbutton"
				  iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
            </privilege:enable>
            <privilege:enable code="StMoveStorehouseExamine">
               <a href="javascript:void(0);" id="smsh_examineBtn" class="easyui-linkbutton"
                plain="true" iconCls="icon-examine" onclick="examineButton();">审核</a>
            </privilege:enable>
			      <span id="button"></span>
           </div>
		   <div region="center"  split="false" border="false">
		       <div id="stMoveStorehouse_tab" class="easyui-tabs" fit="true">
				    <div title="移仓单汇总">  
				        <div id="cc" class="easyui-layout" fit="true">  
						    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" border="false">
							    <form id="stMoveStorehouseMainSearchForm">	
								   <table>
								      <tr>
								       <td>移仓日期:</td>
								       <td><input id="msdmDateStart"  name="msdmDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'msdmDateEnd\',{d:-1})}'})"/></td>
								       <td>至</td>
								       <td><input id="msdmDateEnd" name="msdmDateEnd"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'msdmDateStart\',{d:0})}'})"/></td>
								       <td>移仓单号:</td>
								       <td><input name="msdmId" /></td>
								       <td>经办人:</td>
								       <td><input id="sms_msdmManagerName" name="msdmManagerName" onfocus="this.select();addMananger();" readonly="readonly" style="background-color: #c0d8d8;" /></td>
								       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"  onclick="addMananger();" /></td>
								       <td>备注:</td>
								       <td><input name="msdmRemark" /></td>
								      </tr>
								    </table>
							    </form>		
						    </div>  
						    <div region="center" id="stMoveStorehouseMainTableDiv" style="background:#eee;" border="false">
						         <table id="stMoveStorehouseMainTable"></table>
						    </div>   
						</div>  
				    </div>  
				    <div title="移仓单明细" closable="false">  
				        <div id="cc" class="easyui-layout" fit="true">  
				             <div region="north" title="查询条件" split="false" style="overflow: hidden;height:80px;background:#eee;" border="false">
							    <form id="stMoveStorehouseMainForm">
				                  <table>
				                    <tr>
				                      <td>移仓日期:</td>
				                      <td><input type="text"id="msdmDate" name="msdmDate" style="width:140px;" class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
				                      <td>移仓单号:</td>
				                      <td><input id="msdmId" name="msdmId" readonly="readonly" style="background-color: #c0d8d8;width:115px;"/></td>
				                      <td>经办人:</td>
				                      <td><input id=msdmManagerName name="msdmManagerName" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  readonly="readonly" style="background-color: #c0d8d8;"/>
									  </td>
				                      <td>备注:</td>
				                      <td><input id="msdmRemark" name="msdmRemark" class="easyui-validatebox" validType="length[0,50]"/></td>
				                    </tr>
				                    <tr>
				                      <td>移仓数量:</td>
				                      <td><input id="msdmSumCnt" name="msdmSumCnt" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				                      <td>移仓金额:</td>
				                      <td><input id="msdmSumAmont" name="msdmSumAmont" readonly="readonly" style="background-color:#c0d8d8;width:115px;"/></td>
				                      <td>移出仓:</td>
				                      <td><input id="outStoreId" style="width:110px;" name="outStoreId" class="easyui-combobox"
															data-options="url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
														    editable : false,
															valueField:'id',
															panelHeight:130,
															required:true,
															missingMessage:'移出仓必填',
															textField:'text'"/>
									  </td>
				                      <td>接收仓:</td>
				                      <td><input id="inStoreId" style="width:110px;" name="inStoreId" class="easyui-combobox"
															data-options="url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
														    editable : false,
															valueField:'id',
															panelHeight:130,
															required:true,
															missingMessage:'接收仓必填',
															textField:'text'"/>
									  <!-- 隐藏域 -->
									  <input type="hidden" id="examinestate" name="examinestate"/>						
									  </td>
				                    </tr>
				                  </table>
						        </form>
						     </div>  
						     <div region="center" style="background:#eee;" border="false">
						       <table id="StMoveStorehouseDetailTable"></table>
						     </div>  
						      <div region="south" split="false" style="overflow: hidden;height:30px;background:#eee;" border="false">
						             <table>
						               <tr>
						                  <td>
				                           <a href="javascript:void(0)" iconCls="icon-add"  id="smsh_addParts" class="easyui-linkbutton"  onclick="isConditionAddParts();"  plain=true>配件添加</a>
				                           <a href="javascript:void(0)" iconCls="icon-remove" id="smsh_deleteParts"  class="easyui-linkbutton" onclick="deleteParts();" plain=true>配件删除</a>
				                          </td>
						               </tr>
						             </table>
						      </div>
						</div>  
				    </div>  
			        <div title="移仓单审核" closable="false">  
			                <div id="cc" class="easyui-layout" fit="true" border="false">  
						        <div region="west" split="false" style="overflow: hidden;width:600px;background:#eee;" border="false">
						        	<div id="cc" class="easyui-layout" fit="true" border="false">  
									    <div region="north" title="未审核移仓单汇总查询条件" split="false" style="overflow: hidden;height:85px;background:#eee;" border="false">
									      <form id="stMoveStorehouseMainUnExamineSearchForm">	
											   <table>
											      <tr>
												       <td>移仓日期:</td>
												       <td><input id="umsdmDateStart" name="msdmDateStart"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'umsdmDateEnd\',{d:-1})}'})"/></td>
												       <td>至</td>
												       <td><input id="umsdmDateEnd" name="msdmDateEnd"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'umsdmDateStart\',{d:0})}'})"/></td>
											           <td>移仓单号:</td>
												       <td><input name="msdmId" /></td>
												       <td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="unexamine_searchByCondition();">查询</a>
		                                                   <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="unexamine_clearSearchCondition();">清空</a>
		                                               </td>
											      </tr>
											      <tr>
												       <td>经办人:</td>
												       <td><input id="umsdmManagerName" name="msdmManagerName" style="width:85px;" onfocus="this.select();addMananger2();" readonly="readonly" style="background-color: #c0d8d8;" /></td>
												       <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png"  onclick="addMananger2();" /></td>
												       <td></td>
												       <td>备注:</td>
												       <td><input name="msdmRemark"/></td>
											       </tr>
											    </table>
										    </form>
									    </div>  
									    <div region="center" border="false" style="background:#eee;">
									      <table id="stMoveStorehouseUnExamineTable" ></table>
									    </div>  
									</div> 
						        </div>  
						        <div region="center" split="false" border="false" style=";background:#eee;">
						            <div id="cc" class="easyui-layout" fit="true" border="false">  
								        <div region="north" split="false" border="false" style="height:350px;background:#eee;">
								          <table id="stMoveOutParstTable"></table>
								        </div>  
								        <div region="center" border="false" style="background:#eee;">
								          <table id="stMoveInParstTable"></table>
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
