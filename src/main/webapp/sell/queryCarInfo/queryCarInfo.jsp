<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆档案查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/queryCarInfo/queryCarInfo.js"></script>
  </head>
  <body >
     <div id="cc" class="easyui-layout" style="width:600px;height:400px;"  fit="true"  >
         
       <!-- 条件区域 -->
		    <div region="north"  split="false" style="background:#eee;height:125px;"  border="false"  >
		       <div id="dd" class="easyui-layout" style="width:600px;height: 120px;"   fit="true">
		             <div   region="north" style="overflow: hidden;padding:3px; background:#eee; height:30px;"  border="false" >
		                       <!-- 按钮区域 -->
		                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryCar();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearCondition();">清空</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" oclick="dopoint('queryCar','queryCar_div');">打印</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" oclick="doexcept('queryCar','queryCar_div');">导出</a>
		            </div> 
		            <div  region="center"  style="overflow: hidden;padding:3px; background:#eee; height:100px;" border="false">
		            <!-- 查询条件 -->
							<form id="carInfoQueryForm" name="carInfoQueryForm" method="post"  fit="true"  >
							<table >
								<tr>
									<td>VIN号码:</td>
									<td ><input type="text" name="queryVinNumber" style="width:120px;"></td>
									<td>OCN码:</td>
									<td><input type="text" name="carOcn" style="width:120px;"></td>
									<td>客户名称:</td>
									<td colspan="2"><input type="text" name="customName" style="width:200px;"></td>
									<td>入库日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'queryInstoreDate2\',{d:-1})}'})" name="queryInstoreDate" id="queryInstoreDate" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'queryInstoreDate\',{d:1})}'})" name="queryInstoreDate2" id="queryInstoreDate2" style="width: 110px;"/></td>
								</tr>
								<tr>
									
									<td>销售状态:</td>
									<td><input type="text" name="sellState" id="sellState" style="width:120px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.SELLSTATE.BASE_SELLSTATE%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#sellState\']',
										invalidMessage : '请从下拉框中选择销售状态' "></td>
									<td>内饰色:</td>
									<td><input type="text" name="carInteriorColor" id="carInteriorColor" style="width:120px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_ORNAMENTCOLOR%>',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#carInteriorColor\']',
										invalidMessage : '请从下拉框中选择内饰色' "></td>
									<td>分销商:</td>
									<td colspan="2"><input type="text" name="querySupplier" id="querySupplier" style="width:200px;" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath}/distributorAction!findAllInfo.action',   
										valueField:'id',   
										textField:'text',
										mode : 'remote',
										validType:'isSelected[\'#querySupplier\']',
										invalidMessage : '请从下拉框中选择外观色' "></td>		
									<td>销售日期:</td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'carSelData2\',{d:-1})}'})" name="carSelData" id="carSelData" style="width: 110px;"/> 至 </td>
									<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'carSelData\',{d:1})}'})" name="carSelData2" id="carSelData2" style="width: 110px;"/></td>	
								</tr>
								<tr>
								<td>业务员:</td> 
												<td><input id="personId"  name="personId" style="width: 120px;" class="easyui-combobox"  data-options="url:'${pageContext.request.contextPath}/basStuffClassAction!findSellOperationPerson.action',   
										    		valueField:'id',   
										    		textField:'text',
										    		mode : 'remote',
										    		validType:'isSelected[\'#stf\']',
					    							invalidMessage : '请从下拉框中选择业务员'" />  
												 </td> 		
									
									<td>车辆品牌:</td>
									<td><input type="text" id="queryBrand" name="queryBrand" class="easyui-combobox" style="width:120px;" data-options="
									url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
									valueField:'id',   
						    		textField:'text',
						    		mode:'remote',						    	
						    		validType:'isSelected[\'#queryBrand\']',
						    		invalidMessage : '请从下拉框中选择车辆品牌',
						    		onSelect: function(rec){  
						    			$(this).combobox('textbox').bind('keyup', function (){
						    				if($('#queryBrand').combobox('getValue') == '' || $('#queryBrand').combobox('getValue') != $('#queryBrand').combobox('getText')){
						    					$('queryBrand').combobox('reload', 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>');
						    				}
						    			});
							            $('queryCarModel').combobox('clear'); 
							            $('queryCarModel').combobox('reload', 'carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
							        } "/></td>
									<td>车辆型号:</td>
									<td colspan="2"><input type="text" id="queryCarModel" name="queryCarModel" class="easyui-combobox" style="width:200px;"  data-options="
										url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
										valueField:'id',   
							    		textField:'text',
							    		mode:'remote',						
							    		validType:'isSelected[\'#queryCarModel\']',
							    		invalidMessage : '请从下拉框中选择车辆型号'" 
								        /></td>
								</tr>
								
								
							</table>
			 </form>
		         </div>
	         </div>
 </div>
		           
	    	
 <!-- 展现数据list区域 -->	  
     
		    <div id="queryCar_div" region="center"  style="background:#eee;" border="false">
		           <table id="queryCar"></table>
		    </div>  
		    
	        <!-- 编辑区域 -->
			<div region="south" split="false" style="height:180px; background:#eee;"  border="false">
			   	<div id="tab_id" class="easyui-tabs"  border="false"  style="background:#eee;"   fit="true" >
			   		 <div title="基本信息"  >
				   	   	      <form  id="jBxx" name="jBxx" style="height:100%;width:100%;">
							     <table  style="background:#eee;margin-top: 5px"    >
										<tr>
											<td>车辆编号:</td>
											<td ><input type="text" name="carCode" style="width:120px;" readonly="readonly"></td>
											<td>选装件:</td>
											<td ><input type="text" name="" style="width:120px;" readonly="readonly"></td>
											<td>车身颜色:</td>
											<td ><input type="text" name="colorName" style="width:120px;" readonly="readonly"></td>
											<td>合格证号:</td>
											<td ><input type="text" name="carCertificate" style="width:120px;" readonly="readonly"></td>
											
										</tr>
										<tr>
											<td>VIN编码:</td>
											<td ><input type="text" name="carVinNumber" style="width:120px;" readonly="readonly"></td>
											<td>车牌照:</td>
											<td ><input type="text" name="carLicensePlate" style="width:120px;" readonly="readonly"></td>
											<td>内饰色:</td>
											<td ><input type="text" name="interiorName" style="width:120px;" readonly="readonly"></td>
											<td>商检单:</td>
											<td ><input type="text" name="carTradeCheckBill" style="width:120px;" readonly="readonly"></td>
											
										</tr>
										<tr>
											<td>建档日期:</td>
											<td ><input type="text" name="customInputdata" style="width:120px;" readonly="readonly"></td>
											<td>厂牌名称:</td>
											<td ><input type="text" name="carLicenseName" style="width:120px;" readonly="readonly"></td>
											<td>车辆品牌:</td>
											<td ><input type="text" name="carBrandName" style="width:120px;" readonly="readonly"></td>
											<td>生产日期:</td>
											<td ><input type="text" name="carMakeData" style="width:120px;" readonly="readonly"></td>
											
										</tr>
										<tr>
											<td>发动机:</td>
											<td ><input type="text" name="carMotorNumber" style="width:120px;" readonly="readonly"></td>
											<td>车型号:</td>
											<td ><input type="text" name="carModelName" style="width:120px;" readonly="readonly"></td>
											<td>产地:</td>
											<td ><input type="text" name="carProductionAddress" style="width:120px;" readonly="readonly"></td>
										</tr>	
								 </table>
						  </form>
			   			</div>
				   		<div title="销售信息" style="background:#eee;">
				   		      <form  id="otherInfo" name="jBxx" style="height:100%;width:100%;">
				   					<table style="margin-top: 5px "   >
				   						<tr>
				   							<td>保险员:</td>
				   							<td ><input type="text" name="insuranceAgent" style="width:120px;" readonly="readonly"></td>
					   						<td>客户名称:</td>
					   						<td ><input type="text" name="customName" style="width:120px;" readonly="readonly"></td>
					   						<td>入库日期:</td>	
					   						<td ><input type="text" name="instorehouseDate" style="width:120px;" readonly="readonly"></td>
				   						</tr>
				   						<tr>
				   							<td>投保期限:</td>
				   							<td ><input type="text" name="" style="width:120px;" readonly="readonly"></td>
				   							<td>销售日期:</td>
				   							<td ><input type="text" name="carSelData" style="width:120px;" readonly="readonly"></td>
				   							<td>业务员:</td>
				   							<td ><input type="text" name="" style="width:120px;" readonly="readonly"></td>
				   							<td>供应商:</td>
				   							<td ><input type="text" name="supplier" style="width:120px;" readonly="readonly"></td>
				   						</tr>
				   						<tr>
				   							<td>保险单号:</td>
				   							<td ><input type="text" name="insurancePolicy" style="width:120px;" readonly="readonly"></td>
				   							<td>销售价格:</td>
				   							<td ><input type="text" name="selTransactionMoney" style="width:120px;" readonly="readonly"></td>
				   							<td>保险公司:</td>
				   							<td ><input type="text" name="insurerName" style="width:120px;" readonly="readonly"></td>
				   							<td>分销商:</td>
				   							<td ><input type="text" name="distributorName" style="width:120px;" readonly="readonly"></td>
				   						</tr>
				   				    </table>
				   			</form>
				    	</div> 
			   		  
		  	    </div>
		   </div>
   </div>
  </body>
</html>
