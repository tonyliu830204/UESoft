<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%@page import="com.syuesoft.model.BasUsers"%> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
    var typeTag=0;
    var loginLevel = "<%=Contstants.EMPLOYEELEVEL.ADMINISTRATOR %>";
    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
    </script>
    <title>入库单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	</head>
     <body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/foreordain/foreordainInfo.js"></script>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
       <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		 <privilege:enable code="INSTOREHOUSE_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="_add" onclick="addPersonnel(1);">新增</a>
		</privilege:enable>
		 <privilege:enable code="INSTOREHOUSE_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="_remove" onclick="remove_()">删除</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="_update"  onclick="addPersonnel(2);">修改</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="_search" onclick="queryForeordain();">查询</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_EXAMINE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" id="_examine" onclick="examineState();">审核</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_PRINT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" id="_print" plain="true" onclick="instorehousePrint();" >打印</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_IMPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-import" id="_import" plain="true"  onclick="importDel()" >导入</a>
		</privilege:enable>
		<privilege:enable code="INSTOREHOUSE_EXPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" id="_export" plain="true"  onclick="_except();">导出</a>
		</privilege:enable>
		<span id="saveOrCancelBtn"></span>
    </div>
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false">  
                <div title="入库单汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:80px;width: 1800px" border="false">
					 <form id="foreordainQueryForm">
                       <table>
                        <tr>
                 			<td>入库日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'servicingNextdate2\',{d:-1})}'})" name="queryInstoreDate" id="servicingNextdate" style="width: 100px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'servicingNextdate\',{d:1})}'})" name="queryInstoreDate2" id="servicingNextdate2" style="width: 100px;"/></td>
							<td>入库单:</td>
							<td><input type="text" name="queryInstoreCode" /></td>
				
							 <td>VIN号码:</td>
							<td colspan="2"><input type="text" name="queryVinNumber"  style="width: 150px" maxlength="17" ></td>	
								
                        </tr>
                        <tr>
                        	<td>供货商:</td>		
							<td colspan="2"><input type="text" name="querySupplier"  id="car_supplierIds" class="easyui-combobox" style="width:220px;" data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#car_supplierIds\']',
								invalidMessage : '请从下拉框中选择供应商'"/></td>
							<td>审核情况:</td>
							<td><input type="text" name="queryExamine" id="examineStateid" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT%>',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#examineStateid\']',
								invalidMessage : '请从下拉框中选择审核情况'"/></td>	
								<td>车型号:</td>
							<td><input type="text" name="queryCarModel" id="car_modeId" style="width: 150px"  class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#car_modeId\']',
								invalidMessage : '请从下拉框中选择车型号'"/></td>
								</tr>
                       </table>                                                      
				     </form>
							</div>
						    <div id="ForeordainTable_div" region="center" style="background:#eee;" border="false">
						        <table id="ForeordainTable"></table>
						    </div>
	                </div>
               </div>  
		    <div title="入库单明细" style="display:block;" closable="false"  fit="true" onClick="balanceMoney(false);">  
		        <div id="tt" class="easyui-layout" fit="true">  
				  <div region="north" title="入库单汇总" split="false" style="overflow: hidden;background:#eee;height:110px;width: 1800px" border="false">  
				     <form id="StPurOrderForm" method="post">		 
					     	<input  type="hidden" name="instorehouseId"/>  
					     	<input  type="hidden" name="examineState"/> 
					     	<input type="hidden"  name="state"  id="state" />  
					     	<input type="hidden" id="enterprise_id" name="enterprise_id"/>  
					        <table >
						        <tr>
							          <td style="width:70px" >入库日期:</td>
			                          <td ><input type="text" id="instorehouseDate" style="width:130px;"   name="instorehouseDate" 
			                          		class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" /></td>
			                          <td style="width:70px">入库单编号:</td>
			                          <td ><input type="text" id="instorehouseCode"   name="instorehouseCode" style="width:130px;background-color:#c0d8d8"  readonly="readonly" /></td>
			                          <td style="width:70px">发票类型:</td>							    
									  <td ><input type="text" name="invoiceType" id="invoiceType" style="width:130px;"    class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.INVOICETYPE%>',
										required:true,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#invoiceType\']',
										invalidMessage : '请从下拉框中选择发票类型',
										onChange:function(oldValue,newValue){
											comboxChange();
										}
										"/></td>
									 <td style="width:70px">经办人姓名:</td>
									 <td>
											<input type="text" id="stf" name="stfId"    class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
												style="width: 140px"
												data-options="url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',  
													disabled:true,
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#stf\']',
													invalidMessage : '请从下拉框中选择经办人'"  />	
								
											</td>
									 	
									 <td style="width:70px">仓库:</td>							
									    <td><input type="text" name="warehouse" id="warehouse"  style="width:130px;"   class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#warehouse\']',
										invalidMessage : '请从下拉框中选择仓库'"/></td>
								</tr>	
								<tr>
									<td style="width:70px">采购员:</td>
							        <td><input id="purchaser"  name="purchaser"  class="easyui-combobox"  style="width:130px;"  data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findCarStockPerson.action',   
												editable:false,
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote',
									    		validType:'isSelected[\'#purchaser\']',
				    							invalidMessage : '请从下拉框中选择业务员'" /> </td> 		
				    				<td style="width:70px">运输车辆:</td>
				    			 	<td ><input type="text" name="transportVehicles" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,10]\']'"  style="width:130px;" /></td>	
				    			 	<td style="width:70px">数量:</td>
								    <td><input type="text"  name="number"  id="number" readonly="readonly"   style="background-color:#c0d8d8;width:130px"/></td>					 
									 <td style="width:70px">含税额合计:</td>
								    <td ><input type="text" id="totalTax" name="totalTax"  readonly="readonly"  class="easyui-numberbox" precision="2" style="background-color:#c0d8d8;width:140px"/></td>									                               
								    <td style="width:70px">未税额合计:</td>
								    <td ><input type="text" id="totalNotax" name="totalNotax"  readonly="readonly" class="easyui-numberbox" precision="2" style="background-color:#c0d8d8;width:130px"/></td>															
								</tr>
								<tr>					    
								 	<td style="width:70px">供应商:</td>
								   	<td><input type="text" name="supplierId" id="car_supplierId" class="easyui-combobox" style="width:130px;" data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',				
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#car_supplierId\']',
										invalidMessage : '请从下拉框中选择供应商'"></td>
								    <td style="width:70px">单据编号:</td>
									<td colspan="2"><input type="text"  name="receipt" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,20]\']'" style="width:203px" /></td>		
									<td style="width:70px">发票及备注:</td>
									<td colspan="2"><input type="text"  name="remark" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'"  style="width: 203px" /></td>												
								</tr>
						</table>
				     </form>
				    </div>  
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">入库增加</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">入库删除</a>&nbsp;&nbsp;
					    </div>
					    <div id="ForeordainDetilTable_div" region="center" style="background:#eee;" border="false">
					         <table id="ForeordainDetilTable" >
					         </table>
					    </div>  
					</div> 
			 </div>  
			 </div> 
	</div>
</div>
  </body>
</html>
