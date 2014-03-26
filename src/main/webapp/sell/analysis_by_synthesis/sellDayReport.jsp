<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<jsp:include page="/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>销售日报表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/analysis_by_synthesis/sellDayReport.js"></script>
    <script type="text/javascript">
	    var personnelSumTable = "<%=Contstants.PRINTTABLEKEY.personnelSumTable %>";
	    var personneltempletA = "<%=Contstants.TEMPLETPRTINGKEY.personneltempletA %>";
	    var systemAll = "<%=Contstants.SYSTEMTYPE.ALL%>";
    </script>
  	</head>
  		<body>
  		<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:35px;" border="false">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"  onclick="queryCarReserve();" >查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" id='_clear' onclick="doFormClear();">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true" id='_export' onclick="dopoint('carAndCustomList','datagrid_center_div');" >打印</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" id='_export' onclick="doexcept('carAndCustomList','datagrid_center_div');" >导出</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remind" plain="true" id="hide" onclick="_hideColumn();">隐藏列</a>
		</div>
		<div region="center" style="background:#eee;"  border="false">
		
			<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
			<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:100px;" border="false">
				<form id="form_list_id">
				<fieldset>
					<legend>查询条件</legend>
					<table style="text-align: right">
						<tr>
							<td>销售日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xsSellDate2\',{d:-1})}'})" name="xsSellDate" id="xsSellDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xsSellDate\',{d:1})}'})" name="xsSellDate2" id="xsSellDate2" style="width: 85px;"/></td>
							<td>车辆品牌:</td>
							<td><input type="text" id="car_Brand_id" name="carBrand"  class="easyui-combobox" data-options="
							url:'${pageContext.request.contextPath }/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.BASE_CARBRAND%>',
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
						url:'${pageContext.request.contextPath }/carModelAction!findAllCarModel.action',
						valueField:'id',   
			    		textField:'text',
			    		mode:'remote',
							onLoadSuccess : function(){
							},
			    			onSelect:function(rec){
							} 
							"/>
					        </td>
					    <td>VIN号:</td>
						<td><input name="xsCarVinNumber" style="width: 130px"/></td>   
								
							</tr>
						<tr>
						<td>出库日期:</td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'retreatDate2\',{d:-1})}'})" name="retreatDate" id="retreatDate" style="width: 85px;"/> 至 </td>
							<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'retreatDate\',{d:1})}'})" name="retreatDate2" id="retreatDate2" style="width: 85px;"/></td>
						
							<td>客户名称:</td>
							<td><input name="xsCustomName"/></td>
							<td>出库分类:</td>
						<td>
							<input style="width:110px" id="xs_Car_Sel_Type"  name="xs_Car_Sel_Type"
							class="easyui-combobox"
							data-options="
							url : 'baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.OUTSTORAGE %>',
							multiple:false,
							valueField:'id',  
							textField:'text',
			    			mode:'remote',	
			    			editable:false"
							/>
						</td>
						<td>
							销售顾问:
						</td>
						<td>
						<input type="text" id="stf_id" name="stfId"  class="easyui-combobox" 
						 style="width: 130px"
							data-options="url:'sellUtilAction!findUsers.action',  
						
								valueField:'id',   
								textField:'name',
								mode : 'remote',
								validType:'isSelected[\'#stf_id\']',
								invalidMessage : '请从下拉框中选择经办人'"  />	
						
						
						</td>
				       		
						</tr>
						
					</table>
					</fieldset>
				</form>		
				</div>
				<div id="datagrid_center_div" region="center" border="false" >
					<table id="carAndCustomList"></table>
				</div>
			</div>
		</div>
	</div>	
  </body>
</html>
