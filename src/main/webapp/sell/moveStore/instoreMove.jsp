<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%@page import="com.syuesoft.model.BasUsers"%> 
 <%@ taglib prefix="s" uri="/struts-tags" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆移库管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
		   

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/moveStore/instoreMove.js"></script>
	<script type="text/javascript">
		var staticTag=false;
	</script>
	</head>
     <body>
       <div id="cc" class="easyui-layout" fit="true" border="false">  
       <div data-options="region:'north',border:false" style="padding: 1px; background: rgb(238, 238, 238) none repeat scroll 0% 0%; overflow: hidden; height: 27px; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"> 
		<privilege:enable code="INSTOREMOVE_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true" id="_add" onclick="addPersonnel(1);">新增</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove" plain="true" id="_remove" onclick="remove_()">删除</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_EDIT">
		<font face="Algerian"><a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" id="_update" onclick="addPersonnel(2);">修改</a></font>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" id="_search" onclick="queryForeordain();">查询</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" id="_clear" onclick="clearSearchCondition();">清空</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_EXAMINE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-examine" plain="true" id="_examine" onclick="examineState();">审核</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_PRINT">
		<a id="_print" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-print" plain="true" onclick="_config();">打印</a>   
		</privilege:enable>
		<privilege:enable code="INSTOREMOVE_EXPORT">
		<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-export" plain="true" onclick="_except();">导出</a>   
		</privilege:enable><!--
		<privilege:enable code="INSTOREMOVE_IMPORT">
		<a id="_import"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-import" plain="true" >导入</a>  
		</privilege:enable>
		--><span id="saveOrCancelBtn"></span>
	</div>
  <div region="center"  split="false" border="false">
             <div id="tt" class="easyui-tabs" fit="true" border="false">  
                <div title="移库单汇总" style="display:block;"  fit="true">
                       <div id="cc" class="easyui-layout" fit="true" border="false">
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:70px;" border="false">
					 <form id="moveQueryForm">
                       <table>
                        <tr>
                 			<td width="65px">移库日期:</td>
                 			<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({maxDate:'#F{$dp.$D(\'RetreatDateEnd\',{d:-1})}'})"
													name="retreatDateStart" id="retreatDateStart" style="width: 95px;" 
													 />
												至
											</td>
											<td>
												<input type="text" class="Wdate"
													onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreatDateStart\',{d:1})}'})"
													name="RetreatDateEnd" id="RetreatDateEnd" style="width: 95px;" 
													 />
											</td>
							<td>VIN号:</td>
							<td><input type="text" style="width: 125px;"  name="queryVinNumber" maxlength="17"></td>
							<td>目标仓:</td>		
							<td><input type="text" name="inInstorehouse" id="queryWarehouse" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',
												editable:false,
									    		valueField:'id',   
									    		textField:'text',
									    		mode : 'remote',
									    		validType:'isSelected[\'#queryWarehouse\']',
												invalidMessage : '请从下拉框中选择仓库'"></td>
                        </tr>
                       </table>                                                      
				     </form>
							</div>
						    <div id="instoreMove_div" region="center" style="background:#eee;" border="false">
						        <table id="instoreMove"></table>
						    </div>
	                </div>
               </div>  
		    <div title="移库单明细" style="display:block;" closable="false"  fit="true">  
		        <div id="tt" class="easyui-layout" fit="true">  
				  <div region="north" title="移库单汇总" split="false" style="overflow: hidden;background:#eee;height:80px;" border="false">  
				    <form id="instoreMoveForm" method="post">		  
				     	<input type="hidden" name="retreatId">
				     	<input type="hidden" name="examine">  
				     	<input type="hidden" id="enterprise_id" name="enterprise_id"/>    
					        <table> 
						        <tr>							       
			                          <td width="65">移库单号:</td> 
			                          <td><input type="text" id="retreatCode" name="retreatCode" style="width: 120px" readonly="readonly" style="background-color: rgb(192, 216, 216);"></td> 
			                          <td>移库日期:</td> 
			                          <td><input type="text" id="re" name="retreatDate"  readonly="readonly"></td>			                         
									 
									 <td>原来仓库:</td>							 
									    <td><input type="text" name="outInstorehouse" id="outInstorehouse" class="easyui-combobox" 
									    data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',					
							    		required:true,
							    		editable:false,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		validType:'isSelected[\'#inInstorehouse\']',
										invalidMessage : '请从下拉框中选择原来仓库',
										onChange:function(newValue,oldValue){
											staticTag=true;
											$('#inInstorehouse').combobox('clear');
											$('#inInstorehouse').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>');
										}"></td> 			
									 <td>目标仓库:</td>							 
									    <td><input type="text" name="inInstorehouse" id="inInstorehouse" class="easyui-combobox" 
									    data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.ATTACHFOUR%>',					
							    		required:true,
							    		valueField:'id',   
							    		textField:'text',
							    		mode : 'remote',
							    		editable:false,
							    		validType:'isSelected[\'#inInstorehouse\']',
										invalidMessage : '请从下拉框中选择目标仓库',
										onLoadSuccess:function(data){
											if(staticTag==true){
												var localData=data;
												var value=$('#outInstorehouse').combobox('getValue');
												for(var i=0;i<data.length;i++){
													if(data[i].id==value){
														localData.splice(i,1);
														$('#inInstorehouse').combobox('loadData',localData);
													}
												}
												staticTag=false;
											}
										}"></td> 
										
								</tr>
								<tr>
								<td>经办人:</td> 
									    <td><input type="text" name="person" id="www"   class="easyui-combobox"  value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>"
 												style="width: 120px"
												data-options="url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
													disabled:true,  
													valueField:'id',   
													textField:'text',
													mode : 'remote',
													validType:'isSelected[\'#www\']',
													invalidMessage : '请从下拉框中选择经办人'"  /></td> 
								<td>移库理由:</td> 
									<td colspan="3"><input type="text" name="context" class="easyui-validatebox" data-options="validType:'multiple[\'characterDigit\',\'length[0,30]\']'"style="width: 280px;"></td>		 
								</tr>															 
						</table> 
				     </form>
				    </div>  
				    <div region="south" split="false" style="overflow: hidden;background:#eee;height:30px;" border="false">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" id="addForeordain" plain="true" onclick="add_Foreordain()">移库增加</a>&nbsp;&nbsp;  
							<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconcls="icon-remove" id="deleteForeordain" onclick="delete_Foreordain()">移库删除</a>
					    </div>
					    <div id="instoreMoveDel_div" region="center" style="background:#eee;" border="false">
					         <table id="instoreMoveDel" >
					         </table>
					    </div>  
					</div> 
			 </div>  
			 </div> 
	</div>
</div>
  </body>
</html>
