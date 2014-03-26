<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ page import="com.syuesoft.model.BasUsers"%>
<%
BasUsers user = (BasUsers) request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>供应商对账管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sell_financemanage/supplier_bill.js"></script>
    
    <script type="text/javascript">
		var mytitle;
    	var personId=<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId()%>;
    </script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
		 <privilege:enable code="SUPPLIERBILL_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbutton();">新增</a>
		</privilege:enable>
		 <privilege:enable code="SUPPLIERBILL_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteInfor();">删除</a>
		</privilege:enable>
		 <privilege:enable code="SUPPLIERBILL_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbuttom();">修改</a>
		</privilege:enable>
		 <privilege:enable code="SUPPLIERBILL_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="findinfomation();">查询</a>
		</privilege:enable>
		 <privilege:enable code="SUPPLIERBILL_EXPORT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="doExport();" >导出</a>
		</privilege:enable>
		 <privilege:enable code="SUPPLIERBILL_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClears();" plain="true">清空</a>
		</privilege:enable>
		<span id="addbut"></span>
		<span id="editbut"></span>
		
		</div>
		<div region="center" style="background:#eee;"  border="false">
				<div id="tbs" class="easyui-tabs" fit="true">
				<div title="对账单汇总" >
					 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:30px;" border="false">
						   <form id="form_supplier_bill_id">
						              对账日期:
								<input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountDate2\',{d:-1})}'})" name="accountDate" id="accountDate" style="width: 85px;"/> 至 
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'accountDate\',{d:1})}'})" name="accountDate2" id="accountDate2" style="width: 85px;"/>
							    对账单号:<input id="" name="accountCode" />
						    </form>
					   </div>
						<div id="datagrid_supplier_bill_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_supplier_bill_id"></table>
						</div>
						
				  	</div>
				</div>
				<div title="对账单明细" >
					<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
						   <form id="form_supplier_bill_detail_id">
						   <table>
						   	<tr>
						   		<td style="display: none"><input name="instorehouseId"/></td>
						   		<td style="display: none"><input name="accountId"/></td>
						   		<td>日期:</td>
						   		<td><input id="" name="instorehouseDate" style="width:160px;"/></td>
						   		<td> 单号:</td>
						   		<td><input id="" name="" /></td>
						   		
						   		<td>数量:</td>
						   		<td><input id="" name="number" readonly="readonly" style="background: #c0d8d8"/></td>
						   		<td> 入库单号:</td>
						   		<td><input id="" name="instorehouseCode" style="width:180px;" readonly="readonly" style="background: #c0d8d8"/></td>
						   		<td style="width: 25px;"><img id="imgid"  src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="showYdDlog('${pageContext.request.contextPath}/sell/sell_financemanage/choice_instorehouse_infor.jsp');"/></td>
						   		
						   	</tr>
						   	<tr>
						   		
						   		<td>金额:</td>
						   		<td><input id="tax" name="tax" readonly="readonly" style="background: #c0d8d8;width:160px;"/></td>
						   		<td>应付款:</td>
						   		<td><input id="accountBalance" name="accountBalance" readonly="readonly" style="background: #c0d8d8"/></td>
						   		<td>本次付款:</td>
						   		<td><input id="nowtax" name="nowtax" /></td>
						   		<td>经办人:</td>
						   		<td>
						   			<input id="accountPerson" name="accountPerson" style="width:180px;"
									class="easyui-combobox"	data-options="
									url : '${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
									disabled : true,
									valueField:'id',  
									textField:'text',
									multiple:false,
									mode:'remote',
									onLoadSuccess : function(){
										$(this).combobox('select',$(this).combobox('getText'));
									} "
									/>
						   		</td>
						   		<td></td>
						   	</tr>
						   	<tr>
						   		
						   		<td>发票编号及备注:</td>
						   		<td colspan="7"><input id="" name="remark" style="width:730px"/></td>
						   		
						   	</tr>
						   </table>
						    </form>
					   </div>
						<div id="datagrid_supplier_bill_detail_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_supplier_bill_detail_id"></table>
						</div>
						
				  	</div>
				</div>
				<div title="应付款查询" >
					<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:30px;" border="false">
						   <form id="form_find_due_id">
						              入库日期:
						        <input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'instorehouseDate2\',{d:-1})}'})" name="instorehouseDate" id="instorehouseDate" style="width: 85px;"/> 至 
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instorehouseDate\',{d:1})}'})" name="instorehouseDate2" id="instorehouseDate2" style="width: 85px;"/>
						    </form>
					   </div>
						<div id="datagrid_find_due_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_find_due_id"></table>
						</div>
						
				  	</div>
				</div>
				</div>
	  	
			</div>
		</div>
  </body>
</html>
