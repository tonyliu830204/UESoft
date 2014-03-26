<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户资料查询</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/sell/queryCustomInfo/queryCustomInfo.js"></script>
  </head>
  <body >
     <div id="cc" class="easyui-layout" style="width:600px;height:400px;"  fit="true"  > 
       <!-- 条件区域 -->
		    <div region="north"  split="false" style="background:#eee;height:125px;"  border="false"  >
		       <div id="dd" class="easyui-layout" style="width:600px;height: 120px;"   fit="true">
		             <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
		                <!-- 按钮区域 -->
		                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCust();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearCust();">清空</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="dopoint('queryCustom','queryFormCustom');">打印</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="doexcept('queryCustom','queryFormCustom');">导出</a>

		            </div> 
		            <div  region="center"  style="overflow: hidden;padding:3px; background:#eee; height:100px;" border="false">
		            <!-- 查询条件 -->
							<form id="queryFormCustom" name="carInfoQueryForm" method="post"  fit="true"  >
							<table >
								<tr>
									<td>客户名称:</td>
									<td ><input type="text" name="xsCustomName" style="width:120px;"></td>
									<td>客户电话:</td>
									<td><input type="text" name="xsCustomPhone" style="width:120px;"></td>
									<td>联系人:</td>
									<td><input type="text" name="xsContactsPerson" style="width:120px;"></td>
									
									<td>业务员:</td>
									<td><input type="text" id="stf_id" name="stfId" style="width:120px;"    class="easyui-combobox"  
									data-options="
										url : '${pageContext.request.contextPath}/basStuffClassAction!findSellOperationPerson.action',  
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#stf_id\']',
										invalidMessage : '请从下拉框中选择业务员'"  /></td>
									<td>职业:</td>
									<td><input type="text"  name="xsCustomOccupation" id="custom_occupation"  style="width:120px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_WORK%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_occupation\']',
										invalidMessage : '请从下拉框中选择行业'" /></td>	
										<td>成交状况:</td>
										<td><input type="text" name="xsCustomDeal" id="custom_DealId"  style="width:120px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_DEALSTATE%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_DealId\']',
										invalidMessage : '请从下拉框中选择成交状态'" /></td>	
								</tr>
								<tr>
									<td>客户性质:</td>
									<td><input type="text" name="xsCustomProperty" id="custom_nature" style="width:120px;"  class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CUSTOMNATURE%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_nature\']',
										invalidMessage : '请从下拉框客户性质'" /></td>	
									<td>收入:</td>
									<td><input type="text" name="xsCustomIncome" id="custom_incomId" style="width:120px;"    class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_INCOME%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_incomId\']',
										invalidMessage : '请从下拉框中选择收入情况'" /></td>
									<td>学历程度:</td>
									<td><input type="text" name="xsCustomDiploma" id="custom_diploma" style="width:120px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_EDUCATIONAL%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_diploma\']',
										invalidMessage : '请从下拉框中选择教育程度' " /></td>	
									<td>来源:</td>	
									<td><input type="text"  name="xsCustomSource" id="custom_sourceId" style="width:120px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_SOURCE%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_sourceId\']',
										invalidMessage : '请从下拉框中选择客户来源'" /></td>	
									<td>所在区域:</td>
									<td><input type="text" name="xsCustomArea" id="custom_AreaId"    style="width:120px;" 
										class="easyui-combobox"
										data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBase.action?baseKey=<%=Contstants.BASE_SELL.BASE_AREA%>',   								
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_AreaId\']',
										invalidMessage : '请从下拉框中选择所在区域'"
							          /></td> 	
							          <td>行业性质:</td>
										<td><input type="text"  name="xsCustomTrade" id="custom_trade"  style="width:120px;"   class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_TRADECLASSIFY%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#custom_trade\']',
										invalidMessage : '请从下拉框中选择行业'" /></td>
								</tr>
								<tr>
									<td>VIN号:</td>
									<td ><input type="text" name="carVinNumber" style="width:120px;"></td>
								    <td>OCN码:</td>
								    <td ><input type="text" name="carOcn" style="width:120px;"></td>
								    <td>建档日期:</td>
								 	<td style="text-align: left;" colspan="3">
								 		<input type="text" id="xsCustomInputdata" style="width:120px;"  name="xsCustomInputdata" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'xsCustomInputdata2\')}'});"/>
										&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="text" id="xsCustomInputdata2" style="width:120px;" name="xsCustomInputdata2" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'xsCustomInputdata\')}'});"/></td>
								</tr>
							</table>
			 </form>
		         </div>
	         </div>
 </div>    	
 <!-- 展现数据list区域 -->	  
		    <div region="center"  style="background:#eee;" border="false">
		           <table id="queryCustom"></table>
		    </div>  		    
	        <!-- 编辑区域 -->
			<div region="south" split="false" style="height:180px; background:#eee;"  border="false">
			   	<div id="tab_id" class="easyui-tabs"  border="false"  style="background:#eee;"   fit="true" >
			   		 <div title="基本信息"  >
				   	   	      <form  id="jBxx" name="jBxx" style="height:100%;width:100%;">
							     <table  style="background:#eee;margin-top: 5px"    >
										<tr>
											<td>编号:</td>
											<td ><input type="text" name="xsCustomCode" style="width:120px;"  readonly="readonly"></td>
											<td>所在区域:</td>
											<td ><input type="text" name="areaName" style="width:120px;"  readonly="readonly"></td>
											<td>客户性质:</td>
											<td ><input type="text" name="propertyName" style="width:120px;" readonly="readonly"></td>
											<td>收入:</td>
											<td ><input type="text" name="incomeName" style="width:120px;" readonly="readonly"></td>
											<td>职业:</td>
											<td ><input type="text" name="occupationName" style="width:120px;"   readonly="readonly"></td>
											<td>客户名:</td>
											<td colspan="3"><input type="text" name="xsCustomName" style="width:300px;"  readonly="readonly"></td>
										</tr>
										<tr>												
											<td>所在单位:</td>
											<td ><input type="text" name="xsCustomCompany" style="width:120px;"   readonly="readonly"></td>
											<td>学历:</td>
											<td ><input type="text" name="diplomaName" style="width:120px;"  readonly="readonly"></td>
											<td>来源:</td>
											<td ><input type="text" name="sourceName" style="width:120px;"   readonly="readonly"></td>
											<td>地址:</td>
											<td colspan="3"><input type="text" name="xsCustomAddress" style="width:290px;"  readonly="readonly"></td>
											<td>邮编:</td>
											<td ><input type="text" name="xsCustomZipcode" style="width:120px;"  readonly="readonly"></td>
											<td>成交状况:</td>
											<td ><input type="text" name="customDealName" style="width:120px;"  readonly="readonly"></td>
										</tr>
										<tr>
											<td>行业:</td>
											<td ><input type="text" name="tradeName" style="width:120px;"  readonly="readonly"></td>
											<td>成交几率:</td>
											<td ><input type="text" name="customDealName" style="width:120px;"  readonly="readonly"></td>
											<td>固定电话:</td>
											<td ><input type="text" name="xsCustomPhone" style="width:120px;"  readonly="readonly"></td>
											<td>手机:</td>
											<td ><input type="text" name="xsCustomTelephone" style="width:120px;"  readonly="readonly"></td>
											<td>联系人:</td>
											<td ><input type="text" name="xsContactsPerson" style="width:120px;"   readonly="readonly"></td>
											<td>证件号:</td>
											<td colspan="3"><input type="text" name="xsCustomCredentials" style="width:300px;"   readonly="readonly"></td>
											
										</tr>
										<tr>
											<td>性别:</td>
											<td ><input type="text" name="xsCustomSex" style="width:120px;"  readonly="readonly"></td>
											<td>出生年月:</td>
											<td ><input type="text" name="xsCustomBirthday" style="width:120px;"  readonly="readonly"></td>
											<td>业务员:</td>
											<td ><input type="text" name="stfName" style="width:120px;"   readonly="readonly"></td>	
											<td>其他:</td>
											<td colspan="8"><input type="text" name="xsCustomOther" style="width:638px;"  readonly="readonly"></td>						
										</tr>
								 </table>
						  </form>
			   			</div>		   		  
		  	    </div>
		   </div>
   </div>
  </body>
</html>
