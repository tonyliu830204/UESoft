<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%@page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出库单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/instoreOut/instoreOut.js"></script>
	<script type="text/javascript">
		var sgsm_d2;
			function addCarSel()
			{
			 sgsm_d2 = $('<div/>');
			 sgsm_d2.dialog({
				title: '销售单选择',   
			    width: 700,   
			    height: 450,
			    cache: false,   
			    href: '${pageContext.request.contextPath}/sell/instoreOut/carSellInfoSelect.jsp',
			    modal: true,
			    onClose : function (){
			    	$(this).dialog('destroy');
			      }
			   });
			}
	
	</script>
	</head>
     <body>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
       <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
		<privilege:enable code="INSTOREOUT_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="_add" onclick="addRetreat(1);">新增</a>
		</privilege:enable>
		
		<!--<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="_remove" onclick="removeRetreat();">删除</a>
		-->
		<privilege:enable code="INSTOREOUT_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="_update"  onclick="addRetreat(2);">修改</a>
		</privilege:enable>
		<privilege:enable code="INSTOREOUT_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id="_search" onclick="queryRetreat();">查询</a>
		</privilege:enable>
		<privilege:enable code="INSTOREOUT_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>
		</privilege:enable>
		<privilege:enable code="INSTOREOUT_EXAMINE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" id="_examine" onclick="examine_();">审核</a>
		</privilege:enable>
		<privilege:enable code="INSTOREOUT_PRINT">
		<a id="_print" href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="outstorehousePrint();">打印</a>
		</privilege:enable>
		<privilege:enable code="INSTOREOUT_EXPORT">
		<a id="_export" href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="_except();">导出</a>
		</privilege:enable>
		<span id="saveOrCancelBtn"></span>
    </div>
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false">  
                <div title="出库单汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:70px;" border="false">
					 <form id="retreatQueryForm">
                       <table>
                        <tr>
                 			<td>出库日期:</td>
	                        <td style="text-align: left;" colspan="3"><input type="text" id="retreatDateStart" name="retreatDateStart" style="width: 95px" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'retreatDateEnd\')}'});"/>
									&nbsp;至&nbsp;&nbsp;
									<input type="text" id="retreatDateEnd" name="retreatDateEnd" class="Wdate"  style="width: 95px" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'retreatDateStart\')}'});"/></td>
							
							<td>出库单号:</td>
							<td><input type="text" name="retreatCode" /></td>
							<td>VIN号:</td>
							<td><input type="text" name="carVinNumber" maxlength="17"/></td>
							<td>审核情况:</td>
							<td><input type="text" name="examine" id="examineStateId" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ADUIT%>',
								editable:false,
					    		valueField:'id',   
					    		textField:'text',
					    		mode : 'remote',
					    		validType:'isSelected[\'#examineStateId\']',
								invalidMessage : '请从下拉框中选择审核情况'"/></td>	
							
                        </tr>
                       </table>                                                      
				     </form>
							</div>
						    <div id="retreat_id" region="center" style="background:#eee;" border="false">
						        <table id="retreat"></table>
						    </div>
	                </div>
               </div>  
		    <div title="出库单明细" style="display:block;" closable="false"  fit="true">  
		        <div id="tt" class="easyui-layout" fit="true">  
				  <div region="north" title="退车单汇总" split="false" style="overflow: hidden;background:#eee;height:90px;" border="false">  
				     <form id="retreatForm" method="post">	
				     	     <input type="hidden" id="detailsId" readonly="readonly" name="detailsId" style="width: 150px"  /> 
				     		<input type="hidden" id="enterprise_id" name="enterprise_id"/>
					        <table >
						        <tr>
							          <td style="width:80px">出库日期:</td>
			                          <td><input type="text" id="retreatDate"   name="retreatDate" style="width:150px" readonly="readonly" class="Wdate" /></td>
			                          <td style="width: 80px">出库单号:</td>
			                          <td><input type="text" id="retreatCode"  name="retreatCode" style="width: 185px;background-color:#c0d8d8" readonly="readonly" /></td>	
			                          <td style="width: 80px">车辆销售编号:</td>
			                          <td><input id="sellCode" name="sellCode"  readonly="readonly" style="background-color: rgb(192, 216, 216); width: 130px;" class="easyui-validatebox" maxlength="11" data-options="required:true,missingMessage:'销售单号为必填项' " onkeypress=" if(event.keyCode==13) { addCarSel(); return false;}">   
													 <img src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" id="carSellImg">
													 <input type="hidden" id="xs_Car_Sel_Id" name="xs_Car_Sel_Id"/></td>	
			                           <td style="width: 80px">经办人:</td>
			                          <td>
												<input type="text" id="stfName" name="personName" 
												value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfName()%>" 
												style="background-color:#c0d8d8;"disabled="disabled" readonly="readonly"/>
											<input type="hidden" id="zhPerson" name="person" 
												value="<%=((BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>" />
											</td>
								</tr>
								<tr>
									  
			                          
			                         					                    
			                          <td style="width: 80px">出库理由:</td>
			                          <td colspan="3"><input type="text"  name="context" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,50]\']'" style="width: 420px"/></td>
			                            <td ><input type="hidden" id="carId" name="carId" /></td>			                 
								    													                 
								    											
								</tr>
						</table>
				     </form>
				    </div>  
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    
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
