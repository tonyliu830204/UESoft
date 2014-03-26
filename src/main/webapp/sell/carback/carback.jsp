<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>退厂单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/carback/carback.js"></script>
	</head>
     <body>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
       <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		<privilege:enable code="CARBACk_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="_add" onclick="addRetreat(1);">新增</a>
		</privilege:enable>
		<!--<privilege:enable code="CARBACk_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="_remove" onclick="removeRetreat();">删除</a>
		</privilege:enable>	-->
		<privilege:enable code="CARBACk_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="_update"  onclick="addRetreat(2);">修改</a>
		</privilege:enable>
		<privilege:enable code="CARBACk_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="_search" onclick="queryRetreat();">查询</a>
		</privilege:enable>
		<privilege:enable code="CARBACk_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="CARBACk_EXAMINE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" id="_examine" onclick="examine_();">审核</a>
		</privilege:enable>
		<privilege:enable code="CARBACk_PRINT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" id="_print"  onclick="sellSendBackCarPrint()";>打印</a>
		</privilege:enable>
		<privilege:enable code="CARBACk_EXPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" id="_export" onclick="_except();">导出</a>
		</privilege:enable>
		<!--<privilege:enable code="CARBACk_IMPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-import" plain="true" id="_import">导入</a>
		</privilege:enable>
		--><span id="saveOrCancelBtn"></span>
    </div>
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false">  
                <div title="退厂单汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:85px;" border="false">
					 <form id="retreatQueryForm">
                       <table>
                        <tr>
                 			<td width="65px">退车日期:</td>
	                        <td style="text-align: left;" colspan="3"><input type="text" id="retreatDateStart"  style="width: 100px" name="retreatDateStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'retreatDateEnd\')}'});"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="text" id="retreatDateEnd" name="retreatDateEnd"   style="width: 110px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'retreatDateStart\')}'});"/></td>
							<td>供应商:</td>
							<td><input type="text" name="querySupplier"  id="querySupplier" class="easyui-combobox" style="width:138px;" data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#querySupplier\']',
								invalidMessage : '请从下拉框中选择供应商'"/></td>
							
							 <td>VIN号码:</td>
							<td colspan="2"><input type="text" name="queryVinNumber" maxlength="17" class="easyui-validatebox" style="width: 150px"  ></td>					
                        </tr>
                        <tr>
                       
							<td>审核情况:</td>
							<td><input type="text" name="queryExamine" style="width: 100px" id="examineStateId" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT%>',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#examineStateId\']',
								invalidMessage : '请从下拉框中选择审核情况'"/></td>	
                         <td>退车单号:</td>
							<td><input type="text" name="queryRetreatCode" /></td>
                        
                        </tr>
                       
                       </table>                                                      
				     </form>
							</div>
						    <div id="retreat_id" region="center" style="background:#eee;" border="false">
						        <table id="retreat"></table>
						    </div>
	                </div>
               </div>  
		    <div title="退厂单明细" style="display:block;" closable="false"  fit="true">  
		        <div id="tt" class="easyui-layout" fit="true">  
				  <div region="north" title="退车单汇总" split="false" style="overflow: hidden;background:#eee;height:80px;" border="false">  
				     <form id="retreatForm" method="post">	
				     	<input type="hidden" name="retreatId"/>	 
				     		<input type="hidden" name="examine"/>	
				     		<input type="hidden" name="instorehouseType"/>	
				     		<input type="hidden" name="person"/>
				     		<input type="hidden" id="enterprise_id" name="enterprise_id"/>	 
					        <table >
						        <tr>
						        
							          <td style="width:70px">退车日期:</td>
			                          <td><input type="text" id="retreatDate"   name="retreatDate" style="width:125px" class="Wdate" onfocus="WdatePicker({});" /></td>
			                          <td >退车单编号:</td>
			                          <td><input type="text" id="retreatCode"  name="retreatCode" style="width: 125px;background-color:#c0d8d8" readonly="readonly" /></td>	
			                          <td>供货商:</td>		
									  <td><input type="text" name="xsSupplierId"  id="xs_supplier_id" class="easyui-combobox" style="width:160px;" data-options="url:'${pageContext.request.contextPath}/supplierInfoAction!findAllSupplier.action',
										required:true,
										editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#xs_supplier_id\']',
										invalidMessage : '请从下拉框中选择供应商'"/></td>
			                         			                 
								      </tr>	
								     <tr>
			                  		<td style="width: 70px">
										经办人:
									</td>
									<td>
									<input type="text" id="backPerson" name="person"    class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
										 style="width: 125px" disabled="disabled"
										data-options="
											url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action', 
											valueField:'id',   
											textField:'text',
											mode : 'remote',
											validType:'isSelected[\'#backPerson\']',
											invalidMessage : '请从下拉框中选择经办人'"  />	
						
									</td>
			                         <td >退车理由:</td>
			                          <td colspan="3"><input type="text"  name="context" style="width: 340px" maxlength="255" class="easyui-validatebox" data-options="validType:'characterDigit'" /></td>
			                        
			                      												                 
								    											
								</tr>
						</table>
				     </form>
				    </div>  
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">退车增加</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">退车删除</a>&nbsp;&nbsp;			
					    </div>
					    <div id="instoreDel_id" region="center" style="background:#eee;" border="false">
					         <table id="instoreDel" >
					         </table>
					    </div>  
					</div> 
			 </div>  
			 </div> 
	</div>
</div>
  </body>
</html>
