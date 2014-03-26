<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%
String type=Contstants.COLLIGATES.DEFAULTSHOWDAY;
String name=Contstants.PARAMETER_SET.COLLIGATES;
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '采购单管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPurOrder/stpurordermanager.js"></script>
  </head>
  <body>
             <div id="cc" class="easyui-layout" fit="true" border="false">  
             <!-- 按钮区域 -->
               <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
               <privilege:enable code="StPurOrderAdd">
					    <a href="javascript:void(0);" id="spo_addBtn" class="easyui-linkbutton"
						iconCls="icon-add" plain="true" onclick="addPersonnel(1);">新增</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderDelete">
			           <a href="javascript:void(0);" id="spo_delBtn" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true" onclick="deleteStPurOrder()">删除</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderUpdate">
			           <a href="javascript:void(0);" id="spo_updateBtn" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="addPersonnel(2);">修改</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderSearch">
			           <a href="javascript:void(0);" id="spo_searchBtn" class="easyui-linkbutton" 
					    iconCls="icon-search" plain="true" onclick="searchByCondition();">查询</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderClear">
			           <a href="javascript:void(0);" id="spo_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel"
		                plain="true" onclick="clearSearchCondition();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderPrint">
			            <a href="javascript:void(0);" id="spo_printBtn" class="easyui-linkbutton"
						iconCls="icon-print" plain="true"  onclick="addprint();">打印</a>
			   </privilege:enable>
			   <privilege:enable code="StPurOrderExport">
			            <a href="javascript:void(0);" id="spo_exportBtn" class="easyui-linkbutton"
						iconCls="icon-export" plain="true" onclick="_except1();">Excel导出</a>
			   </privilege:enable>
			   <a href="javascript:void(0);" id="spo_importBtn" class="easyui-linkbutton"
						iconCls="icon-import" plain="true"  onclick="importStpurorder();" >Excel导入</a>
			   <privilege:enable code="StPurOrderExamine">
			            <a href="javascript:void(0);" id="spo_examineBtn" class="easyui-linkbutton" 
			            plain="true" iconCls="icon-examine" onclick="examineButton();">审核</a>
			   </privilege:enable>
					    <span id="button"></span>
              </div>
			  <div region="center"  split="false" border="false">
                     <div id="tt" class="easyui-tabs" fit="true" border="false">  
		                 <div title="采购单汇总" style="display:block;"  fit="true">
		                        <div id="cc" class="easyui-layout" fit="true" border="false">
									    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
											 <form id="StPurOrderSearchForm"  method="post">
					                              <table>
						                             <tr>
						                                    <td>采购日期:</td>
							                                <td><input id="orderDateStart" name="orderDateStart"  style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'orderDateEnd\',{d:-1})}'})"/>
							                                                                                     至<input id="orderDateEnd" name="orderDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'orderDateStart\',{d:0})}'})"/></td>
							                                <td>到货日期:</td>
							                                <td><input id="deliveryDateStart" name="deliveryDateStart" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,maxDate:'#F{$dp.$D(\'deliveryDateEnd\',{d:-1})}'})"/>
							                                                                                     至<input id="deliveryDateEnd" name="deliveryDateEnd" style="width:90px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'deliveryDateStart\',{d:0})}'})"/></td>
							                                <td>采购单号:</td>
							                                <td><input id="stOrderId" name="orderId"/></td>
							                                <td>供应商:</td>
							                                <td><input id="stRelcampName" name="relcampName"  style="background-color:#c0d8d8;"/></td>
							                                <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="spom_openRecampPage()"/></td>
							                                
															<td>付讫:</td>
							                                <td><input id="st_paid" name="paid" style="width:60px;" class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.PAYMENTSTATE.PAYMENTSTATE %>',
															    editable : false,
																valueField:'id',
																panelHeight:130,
																panelWidth:60,
																textField:'text'"/></td> 
						                             </tr>
						                             <tr>
						                                   <td>车辆牌照:</td>
							                               <td><input id="" name="" style="width:195px;"/></td>
							                               <td>工单号:</td>
							                               <td><input id="" name="" style="width:195px;"/></td>
							                               <td>入库单号:</td>
							                               <td><input id="storageId" name="storageId"/></td>
							                               <td>订单类型:</td>
							                               <td><input id="st_classification" style="width:110px;" name="classification" class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.BASE_SELL.ORDERTYPE %>',
															    editable : false,
																valueField:'id',
																panelHeight:130,
																textField:'text'"/></td>
														   <td>&nbsp;</td>
							                               <td>审核:</td>
							                               <td><input id="st_examine" style="width:60px;" name="examine" class="easyui-combobox"
																data-options="url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.AUDIT_TAG.AUDITKEY %>',
															    editable : false,
																valueField:'id',
																panelWidth:60,
																panelHeight:130,
																textField:'text'"/>
							                               </td>
						                             </tr>
					                              </table>                                                      
										     </form>
										</div>
									    <div id="stPurOrderTablDiv" region="center" style="background:#eee;" border="false">
									        <table id="stPurOrderTable"></table>
									    </div>
				                </div>
		                </div>  
					    <div title="采购单明细" style="display:block;" closable="false"  fit="true">  
					        <div id="cc" class="easyui-layout" fit="true">  
							  <div region="north" title="条件查询" split="false" style="overflow: hidden;background:#eee;height:130px;" border="false">  
							     <form id="StPurOrderForm" method="post">		     
								    <table>
								       <tr>
								         <td>采购日期:</td>
								         <td><input id="orderDate" name="orderDate" readonly="readonly"  style="width:140px;"  class="Wdate"  onclick="WdatePicker({isShowToday:false,dateFmt:'yyyy-MM-dd HH:mm:ss',errDealMode:1,maxDate:'#F{$dp.$D(\'deliveryDate\',{d:-1})}'})"/></td>
								         <td>采购单号:</td>
								         <td><input id="orderId" name="orderId" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								         <td>供应商:</td>
								         <td><input id="relcampName2" name="relcampName2" class="easyui-validatebox" required="true" missingMessage="供应商必填"  onfocus="this.select();spomd_openRecampPage();"  style="background-color: #c0d8d8;"/><input type="hidden" id="relcampId" name="relcampId" readonly="readonly"/></td>
								         <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="spomd_openRecampPage();"/></td>
								         <td>采购员:</td>
								         <td><input id="buyerName" name="buyerName" class="easyui-validatebox" required="true" missingMessage="采购员必填"  readonly="readonly" onfocus="this.select();spom_openStfPage();" style="background-color:#c0d8d8;"/><input type="hidden" id="buyer" name="buyer"/></td>
								         <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="spom_openStfPage();"/></td>
								         <td>票据类型:</td>
								         <td><input id="notesType" style="width:110px;" name="notesType" class="easyui-combobox"
														data-options="url : 'baseAction!findBaseListData.action?key=<%=Contstants.RECEIPT_TAG.RECEIPTKEY %>',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text',
														mode:'remote'"/></td>
								         
								       </tr>
								       <tr>
								         <td>到货日期:</td>
								         <td><input id="deliveryDate" name="deliveryDate" readonly="readonly" style="width:140px;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,minDate:'#F{$dp.$D(\'orderDate\',{d:0})}'})"/></td>
								         <td>车辆牌照:</td>
								         <td><input id="" name="" style="background-color: #c0d8d8;"/></td>
								         <td>工单号:</td>
								         <td><input id="" name="" style="background-color: #c0d8d8;"/></td>
								         <td><img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick=""/></td>
								         <td>经办人:</td>
								         <td><input id="managerName" name="managerName" value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>"  style="background-color: #c0d8d8;" readonly="readonly"/><input type="hidden" id="manager" name="manager"/></td>
								         <td></td>
								         <td>订单类型:</td>
								         <td><input id="classification" style="width:110px;" name="classification" class="easyui-combobox"
														data-options="url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action?key=<%=Contstants.BASE_SELL.ORDERTYPE %>',
													    editable : false,
														valueField:'id',
														panelHeight:130,
														textField:'text'"/>
														<input type="hidden" id="classificationValue" name="classificationValue"/></td>
								       </tr>
								       <tr>
								         <td>采购数量:</td>
								         <td><input id="numTotal" name="numTotal" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								         <td>未税金额:</td>
								         <td><input id="notaxTotalamont" name="notaxTotalamont" readonly="readonly" style="background-color: #c0d8d8;"></td>
								         <td>含税金额:</td>
								         <td><input id="totalAmount" name="totalAmount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								         <td></td>
								         <td>税额:</td>
								         <td><input id="taxCount" name="taxCount" readonly="readonly" style="background-color: #c0d8d8;"></td>
								         <td></td>
								         <td>税率:</td>
								         <td><input id="taxRate" name="taxRate"  style="background-color: #c0d8d8;"><input type="hidden" id="taxRate1" name="taxRate1" class="easyui-numberbox" ></td>
								       </tr>
								       <tr>
								         <td>审核状态:</td>
								         <td><input id="examine" name="examine" readonly="readonly" style="background-color: #c0d8d8;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								         <td>付讫:</td>
									     <td><input id="paid" name="paid" readonly="readonly" style="background-color: #c0d8d8;"/></td>
								         <td>采购备注:</td>
									     <td colspan="7"><input type="text"id="orderRemark" style="width:480px;" name="orderRemark" class="easyui-validatebox" validType="length[0,100]"/></td>
								       </tr>
									</table>
							     </form>
							    </div>  
							    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
									       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="spod_addPartsBtn" plain="true" onclick="spomd_openAddPartsPage()">配件增加</a>&nbsp;&nbsp;
										   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="spod_delPartsBtn" plain="true"  onclick="deleteDetailParts()">配件删除</a>&nbsp;&nbsp;
<%--										   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true">在途</a>&nbsp;&nbsp;--%>
<%--										   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true">在途清空</a>&nbsp;&nbsp;--%>
<%--										   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true">价格清空</a>--%>
							    </div>
							    <div   id="st_orderItem_table" region="center" style="background:#eee;" border="false">
							         <table id="StOrderItemTable" name="StOrderItemTable"></table>
							    </div>  
							</div> 
					 </div>  
<%--				     <div title="批量采购转入"  style="display:block;" closable="false">  --%>
<%--				        <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">  --%>
<%--						    <div region="north"  title="条件查询"  split="false" style="height:95px;background:#eee;" border="false">--%>
<%--						    <table width="80%">--%>
<%--							    <tr>--%>
<%--									<td>配件代码</td>--%>
<%--									<td><input id="peijiandaima1name" name="peijiandaima1name"/><a href="javascript:void(0)" onclick="peijiandaimaselect()" class="easyui-linkbutton" plain="true" >...</a></td>--%>
<%--									<td>配件名称</td>--%>
<%--									<td><input id="" name=""/></td>--%>
<%--									<td>建议采购量计算方式</td>--%>
<%--									<td><select>--%>
<%--									     <option>下限-库存量-在途</option>--%>
<%--									     <option>上限-库存量-在途</option>--%>
<%--									    </select></td>--%>
<%--								</tr>--%>
<%--								<tr>--%>
<%--									<td>配件品牌</td>--%>
<%--									<td><input id="peijianpinpai1name" name="peijianpinpai1name"/><a href="javascript:void(0)" class="easyui-linkbutton" onclick="peijianpinpaiselect()" plain="true">...</a></td>--%>
<%--									<td>配件型号</td>--%>
<%--									<td><input id="peijianxinghao1name" name="peijainxinghao1name"/><a href="javascript:void(0)" class="easyui-linkbutton" onclick="peijianxinghaoselect()" plain="true">...</a></td>--%>
<%--									<td>排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;列&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</td>--%>
<%--									<td><select>--%>
<%--									     <option>配件编码</option>--%>
<%--									     <option>配件库位</option>--%>
<%--									    </select></td>--%>
<%--								</tr>--%>
<%--						    </table>--%>
<%--						    </div>  --%>
<%--						    <div region="south" split="false" style="height:50px;background:#eee;" border="false">--%>
<%--							    <table width="100%">--%>
<%--								    <tr>--%>
<%--									   <td>配件代码</td>--%>
<%--									   <td><input id="" name=""/></td>--%>
<%--									   <td>配件名称</td>--%>
<%--									   <td><input id="" name=""/></td>--%>
<%--									   <td>库存量</td>--%>
<%--									   <td><input id="" name=""/></td>--%>
<%--									   <td>成本价</td>--%>
<%--									   <td><input id="" name=""/></td>--%>
<%--									   <td>备注信息</td>--%>
<%--									   <td><input id="" name=""/></td>--%>
<%--								    </tr>--%>
<%--							    </table>--%>
<%--						    </div> --%>
<%--						    <div region="center" style="background:#eee;" border="false">--%>
<%--						    --%>
<%--							    <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">--%>
<%--								    <div region="east" border="false" split="false" style="width:140px;">--%>
<%--								        <div id="cc" class="easyui-layout" style="width:600px;height:400px;" border="false" fit="true">--%>
<%--								            <div region="north" split="false" style="height:20px;background:#eee;" border="true">--%>
<%--								                                                一年每月出库情况--%>
<%--								            </div> --%>
<%--										    <div region="center" style="background:#eee;" border="false">--%>
<%--										        <table id="table17"></table>--%>
<%--										    </div>  --%>
<%--										    <div region="south" split="false" style="height:58px;background:#eee;"  border="false">--%>
<%--										        <table width="100%">--%>
<%--												    <tr>--%>
<%--												      <td><a href="javascript:void(0)" class="easyui-linkbutton" plain="true">新增批量转入</a></td>--%>
<%--												    </tr>--%>
<%--												    <tr>--%>
<%--												       <td><a href="javascript:void(0)" class="easyui-linkbutton" plain="true">追加批量转入</a></td>--%>
<%--												    </tr>--%>
<%--											    </table>--%>
<%--										    </div> --%>
<%--										</div> --%>
<%--								    </div>  --%>
<%--								    <div region="center" border="false" style="background:#eee;">--%>
<%--								     <table id="table16"></table>--%>
<%--								    </div>  --%>
<%--								</div> --%>
<%--						    </div>  --%>
<%--						</div> --%>
<%--				    </div>  --%>
<%--				     <div title="配件资料批量调整"style="display:block;" closable="false">  --%>
<%--				       <div id="cc11" class="easyui-layout" style="width:600px;height:400px;" fit="true" border="false">  --%>
<%--					    <div region="north" title="查询条件" split="false" style="height:100px;background:#eee;" border="false">--%>
<%--					       <table width="80%">--%>
<%--							    <tr>--%>
<%--											<td rowrap>配件代码</td>--%>
<%--											<td rowrap><input id="" name=""/></td>--%>
<%--											<td rowrap>配件名称</td>--%>
<%--											<td rowrap><input id="" name=""/></td>--%>
<%--											<td rowrap>配件库位</td>--%>
<%--											<td rowrap><input id="" name=""/></td>--%>
<%--											<td><input type="checkbox" style="width:20px;">库改位</<input></td>--%>
<%--								</tr>--%>
<%--								<tr>--%>
<%--											<td rowrap>配件品牌</td>--%>
<%--											<td rowrap><input id="peijianpinpai1name1" name="peijianpinpai1name1"/><a href="javascript:void(0)" class="easyui-linkbutton" onclick="peijianpinpai1select1()" plain="true">...</a></td>--%>
<%--											<td rowrap>配件类型</td>--%>
<%--											<td rowrap><input id="peijianleixing1name" name="peijianleixing1name"/><a href="javascript:void(0)" onclick="peijianleixingselect()" class="easyui-linkbutton" plain="true">...</a></td>--%>
<%--											<td rowrap>排列顺序</td>--%>
<%--											<td rowrap><select>--%>
<%--											     <option>配件编码</option>--%>
<%--											     <option>配件库位</option>--%>
<%--											    </select></td>--%>
<%--											<td rowrap><input style="width:20px;" type="checkbox" >改销价</input></td>    --%>
<%--								</tr>--%>
<%--						    </table>--%>
<%--					    </div>  --%>
<%--					    <div region="center" split="false" style="background:#eee;" border="false">--%>
<%--						    <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">  --%>
<%--							    <div region="east" split="false" style="width:130px;">--%>
<%--									    <div id="cc" class="easyui-layout" style="width:600px;height:400px;" fit="true">  --%>
<%--										    <div region="north" split="false" style="height:30px;background:#eee;" border="false">--%>
<%--										                  一年每月出库情况--%>
<%--										    </div>  --%>
<%--										    <div region="south" split="false" style="height:60px;background:#eee;" border="false">--%>
<%--										      <table width="100%">--%>
<%--											      <tr>--%>
<%--											      		<td><input type="button" value="保存配件修改"/></td>--%>
<%--											      </tr>--%>
<%--											      <tr>--%>
<%--											       		<td><input size="13"/></td>--%>
<%--											      </tr>--%>
<%--										      </table>--%>
<%--										    </div>--%>
<%--										    <div region="center" split="false" border="false" style="background:#eee;">--%>
<%--										        <table id="table23"></table>--%>
<%--										    </div>  --%>
<%--										</div>--%>
<%--							    </div>  --%>
<%--							   --%>
<%--							    <div region="center" style="background:#eee;">--%>
<%--							        <table id="table22"></table>--%>
<%--							    </div>  --%>
<%--							</div>--%>
<%--					    </div>  --%>
<%--					    <div region="south" split="false" style="height:45px;background:#eee;" border="false">--%>
<%--					            <table width="100%">--%>
<%--								    <tr>--%>
<%--									    <td>配件代码</td>--%>
<%--									    <td><input id="" name=""/></td>--%>
<%--									    <td>配件名称</td>--%>
<%--									    <td><input id="" name=""/></td>--%>
<%--									    <td>库存量</td>--%>
<%--									    <td><input id="" name=""/></td>--%>
<%--									    <td>成本价</td>--%>
<%--									    <td><input id="" name=""/></td>--%>
<%--									    <td>备注信息</td>--%>
<%--									    <td><input id="" name=""/></td>--%>
<%--								    </tr>--%>
<%--							    </table>--%>
<%--					        </div>--%>
<%--		                </div>--%>
<%--				      </div>  --%>
				 </div> 
			</div>
		</div>
  </body>
</html>
