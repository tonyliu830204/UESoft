<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>业务员销售分析</title>
   <script type="text/javascript">
     var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
      var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
      </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/selljs/unite_assay.js"></script>
    <script type="text/javascript">
   
    </script>
  	</head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"  onclick="doFindbyCondition($('#form_workersell_assay_id'),'dayReportAssayAction!doWorkerSellAssay.action','workerassay',$('#datagrid_unite_assay_id'),'业务员');" >查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear($('#form_workersell_assay_id'));">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_print'   onclick=" _config('datagrid_unite_assay_id','datagrid_unite_assay_div')">打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export'  onclick="doExport('datagrid_unite_assay_id','datagrid_unite_assay_div',_callbackExceptFour);">导出</a>

		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:120px;" border="false">
				<form id="form_workersell_assay_id">
				<fieldset>
					<legend>查询条件</legend>
						<table style="text-align: right">
						<tr>
							<td>销售日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsSellDate2\',{d:-1})}'})" name="xsSellDate" id="xsSellDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsSellDate2\',{d:1})}'})" name="xsSellDate2" id="xsSellDate2" style="width: 85px;"/></td>
							<td>车辆品牌:</td>
							<td><input type="text" id="car_Brand_id" name="carBrand"  class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',			    		
								onLoadSuccess : function(){
									
								},
				    			onSelect: function(rec){  
					            $('#car_Model_id').combobox('reload', '${pageContext.request.contextPath }/carModelAction!findCarModelByBrand.action?cbrdId=' + rec.id)  
					        } "
					        />
				        
				        	</td>
							<td>车辆型号:</td>
							<td><input type="text" id="car_Model_id" name="carModel"  class="easyui-combobox" 
							data-options="
							url:'${pageContext.request.contextPath}/carModelAction!findAllCarModel.action',
							valueField:'id',   
				    		textField:'text',
				    		mode:'remote',
								onLoadSuccess : function(){
								},
				    			onSelect:function(rec){
								} 
								"/>
					        </td>
					        <td>代交及寄存:</td>
								<td>
								<input id="talk_Way_id" name="carType"
								class="easyui-combobox"
								data-options="url:'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARTYPE%>',
								valueField:'id',  
								textField:'text',
								multiple:false ,
								mode:'remote'"
								/>
								</td>
								
							</tr>
						<tr>
						<td>出库日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreatDate2\',{d:-1})}'})" name="retreatDate" id="retreatDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreatDate\',{d:1})}'})" name="retreatDate2" id="retreatDate2" style="width: 85px;"/></td>
						<td>分销商:</td>
							<td>
								<input name="xsDistributorId" 
								class="easyui-combobox"	data-options="
								url : 'sellUtilAction!findBussness.action',
								valueField:'id',  
								textField:'name',
								multiple:false,
								mode:'remote'  "
								/>
							</td>
							<td>客户名称:</td>
							<td><input name="xsCustomName"/></td>
							<td>合同号:</td>
							<td><input name="pact_code"/></td>
				       		
						</tr>
						<tr>
							<td>上报日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'upData2\',{d:-1})}'})" name="upData" id="upData" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'upData\',{d:1})}'})" name="upData2" id="upData2" style="width: 85px;"/></td>
							<td>满意度:</td>
							<td>
								<input name="consultDegree" 
									class="easyui-combobox"	data-options="
									url : '${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CONSULTDEGREE %>',
									valueField:'id',  
									textField:'text',
									multiple:false,
									mode:'remote'  "
									/>
							</td>
						
						<td>VIN号:</td>
						<td><input name="xsCarVinNumber" maxlength="17"/></td>
						<td colspan="2"> <input type="checkbox" name="noUp" value="yes"/></td>
						<td>只显示未上报</td>
						
						</tr>
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_unite_assay_div" region="center" border="false" >
					<table id="datagrid_unite_assay_id"></table>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
