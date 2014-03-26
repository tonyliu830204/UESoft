<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>随车附件管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		var otherFlag=false;
	</script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/carInfo/carAttachmentManage/carAttachmentManage.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addSellInvoice();">新增</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSellInvoice()">删除</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSellInvoice();">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="querySellInvoice();">查询</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="dopoint('attachmentGrid','attachmentGrid_div_id');">打印</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="doexcept('attachmentGrid','attachmentGrid_div_id');" >导出</a>
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:70px;">
		    	<form id="invoiceQueryForm" name="sellInvoiceQueryForm" method="post"  fit="true" >
							<table>
							<tr>
							<td>VIN编号:</td>
								<td><input type="text" name="carVinNumber" /></td>
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
						<td><input type="text" id="car_Model_id" name="carModelId"  class="easyui-combobox" 
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
								<td>录/领/借/还件人:</td>
								<td><input type="text" name="send_persion" /></td>
								<td>经办日期:</td><td><input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'operator_date2\',{d:-1})}'})"
							name="operator_date" id="operator_date" style="width: 90px;"	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
								至
							<input type="text" class="Wdate"onclick="WdatePicker({minDate:'#F{$dp.$D(\'operator_date\',{d:1})}'})"
							name="operator_date2" id="operator_date2" style="width: 90px;" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
								</tr>
						
						</table>
			 </form>
		    </div>
		    <div  id="attachmentGrid_div_id"
		    data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="attachmentGrid"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
