<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>车辆销售财务信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellInvoice/sellInvoice.js"></script>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-add" plain="true" onclick="addSellInvoice();">新增</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSellInvoice()">删除</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSellInvoice();">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="querySellInvoice();">查询</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearSearchCondition();">清空</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-examine" plain="true" id="_examine" onclick="examine_();">审核</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-print" plain="true" onclick="dopoint('sellInvoiceGrid','sellInvoiceGrid_div_id');">打印</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"  iconCls="icon-export" plain="true" onclick="doexcept('sellInvoiceGrid','sellInvoiceGrid_div_id');">导出</a>
	
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;background:#eee;height:60px;">
		    	<form id="invoiceQueryForm" name="sellInvoiceQueryForm" method="post"  fit="true" >
							<table>
							<tr>
								<td>销售日期：</td>
								<td>
									<input type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'xs_Car_Sel_Data2\',{d:-1})}'})"
									name="xs_Car_Sel_Data" id="xs_Car_Sel_Data" style="width: 110px;" />至</td>
								<td><input type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'xs_Car_Sel_Data\',{d:1})}'})"
									name="xs_Car_Sel_Data2" id="xs_Car_Sel_Data2" style="width: 110px;" />
								</td>
								<td>客户名称：</td>
								<td><input type="text" name="xs_Custom_Name" /></td>
								<td>业务员：</td>
								<td colspan="2"><input name="stf_Name" class="easyui-combobox" data-options="url : 'basStuffClassAction!findSellOperationPerson.action',
									valueField:'id',  
									textField:'text',
									multiple:false  " /></td>
								<td>VIN编号：</td>
								<td><input type="text" name="xs_Car_Vin_Number" /></td>
								<td>OCN码：</td>
								<td><input name="xs_Car_Ocn" /></td>
							</tr>
							<tr>
								
							</tr>
						</table>
			 </form>
		    </div>
		    <div id="sellInvoiceGrid_div_id" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="sellInvoiceGrid"></table>
		    </div>
		</div>
	</div>
  </body>
</html>
