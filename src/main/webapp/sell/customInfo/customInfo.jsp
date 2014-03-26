<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%@ page import="com.syuesoft.model.BasUsers"%>
<jsp:useBean id="now" class="java.util.Date" />
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户档案</title>
      <script type="text/javascript">
		  
		    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
		    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	</script>
	   <script type="text/javascript">
		function customArchivesKeyUp(id1,id2){
			var dataLength=document.getElementById(id1).value.length;
			document.getElementById(id2).innerHTML=dataLength;
		}
	  </script>
  </head>

  <body>
  <script type="text/javascript" src="${pageContext.request.contextPath}/sell/customInfo/customInfo.js"></script>
   <div id="cc" class="easyui-layout" style="width:600px;height:400px;"  fit="true"  >
         
       <!-- 条件区域 -->
		    <div region="north"  split="false" style="background:#eee;height:95px;"  border="false"  >
		       <div id="dd" class="easyui-layout" style="width:600px;height: 90px;"   fit="true">
		             <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
		                       <!-- 按钮区域 -->
		                       <privilege:enable code="CUSTOMARCHIVES_ADD">
		                         	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true" id="add" onclick="addCustomInfo();">新增</a> 
								</privilege:enable>	
							    <privilege:enable code="CUSTOMARCHIVES_REMOVE">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove" plain="true" id="delete" onclick="deleteCustomInfo();">删除</a> 
								</privilege:enable>
								 <privilege:enable code="CUSTOMARCHIVES_EDIT">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit" plain="true" id="update" onclick="updateCustomInfo();">修改</a> 
								 </privilege:enable>
								 <privilege:enable code="CUSTOMARCHIVES_SEARCH">	
									<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" id="search" onclick="_searchCustomInfo();">查询</a> 
								 </privilege:enable>
								 <privilege:enable code="CUSTOMARCHIVES_CANCEL">	
									<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain="true" id="qc" onclick="_clear();">清空</a>
								</privilege:enable>	
								
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-print" plain="true" onclick="_config();">打印</a>
							
								<a href="javascript:void(0);" class="easyui-linkbutton"
									iconCls="icon-export" plain="true" onclick="_except();">导出</a>
				
									<span id="button"></span><br> 
		            </div> 
		            <div  region="center"  style="overflow: hidden;padding:3px; background:#eee; height:50px;" border="false">
		            <!-- 查询条件 -->
							  <form id="pisForm">
								    <table> 
											<tr> 
												<td>客户编号:</td> 
												<td><input type="text" name="code" style="width: 120px;" /></td> 
												<td>客户名称:</td> 
												<td><input type="text" name="name" style="width: 120px;" /></td> 
												<td>电话:</td> 
												<td><input  name="phone" style="width: 120px;" />  
												</td> 
												<td>联系人:</td> 
												<td><input   name="receiptor" style="width: 120px;"  /> 
												</td> 
													
											</tr>
											<tr>
												<td>业务员:</td> 
												<td><input id="stf"  name="stf" style="width: 120px;" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findSellOperationPerson.action',   
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote',
										    		validType:'isSelected[\'#stf\']',
					    							invalidMessage : '请从下拉框中选择业务员'" />  
												 </td> 		
												<td>成交状态:</td> 
												<td><input id="dealState" name="dealState " style="width: 120px;"  class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_DEALSTATE%>',   
													editable:false,
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote',
										    		validType:'isSelected[\'#dealState\']',
					    							invalidMessage : '请从下拉框中选择成交状态'"/>  
													</td> 						
												<td>建档日期:</td> 
												<td style="text-align: left;" colspan="3"><input type="text" id="inputdataStart" name="inputdataStart" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'inputdataEnd\')}'});"/>
												&nbsp;至&nbsp;&nbsp;<input type="text" id="inputdataEnd" name="inputdataEnd" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'inputdataStart\')}'});"/></td>
											</tr> 
									  </table> 
					    	 </form>
		            </div>
	         </div>
 </div>
		           
	    	
 <!-- 展现数据list区域 -->	  
     
		    <div id="customInfo_div" region="center"  style="background:#eee;" border="false">
		           <table id="customInfo"></table>
		    </div>  
		    
	        <!-- 编辑区域 -->
			
   </div>
  </body>
</html>