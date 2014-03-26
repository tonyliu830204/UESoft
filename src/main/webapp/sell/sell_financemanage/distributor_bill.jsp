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
    <title>分销商对账管理</title>    
    <script type="text/javascript">
   	var sgsm_d2;
	function adddis()
	{
	 sgsm_d2 = $('<div/>');
	 sgsm_d2.dialog({
		title: '请选择',   
	    width:650,   
	    height:400,
	    cache: false,   
	    href: '${pageContext.request.contextPath}/sell/sell_financemanage/addDistributor.jsp',
	    modal: true,
	    onClose : function (){
	    	$(this).dialog('destroy');
	      }
	   });
	}
    
 
		
		//弹出窗口
			var dlog;
			function showYdDlog(){
				var id=$("#did").val();
				if(id==null || id=="" || id==undefined){
				$.messager.alert('提示','请先选择分销商!','warning');
				return ;
				}
				dlog = $('<div/>');
				dlog.dialog({
					title: '调拨单信息',   
				    width: 750,
				    height: 423,
				    cache: false,
				   	href: '${pageContext.request.contextPath}/sell/sell_financemanage/choice_iallocate_infor.jsp?payment=false&id='+$("#did").val(),
				    modal: true,
				    onClose : function (){
				    	$(this).dialog('destroy');
				    }
				});
			 }
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sell/sell_financemanage/distributor_bill.js"></script>
  </head>
  		<body>
  		<div class="easyui-layout" style="width:900px;height:800px;" border="false" fit="true">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:30px;" border="false">
		<privilege:enable code="DISBILL_ADD">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dbAddbutton();">新增</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_REMOVE">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteInfor();">删除</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_EDIT">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dbEditbuttom();">修改</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_SEARCH">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="findinfomation();">查询</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_PRINT">
		<a id='_print' href="javascript:void(0);" class="easyui-linkbutton"	iconCls="icon-print" plain="true" onclick="_config();">打印</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_EXPORT">
		<a id='_export' href="javascript:void(0);" class="easyui-linkbutton"iconCls="icon-export" plain="true"  onclick="_except();">导出</a>
		</privilege:enable>
		<privilege:enable code="DISBILL_CLEAR">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear($('#form_supplier_bill_id'),$('#datagrid_distributor_bill_id'));" plain="true">清空</a>
		</privilege:enable>
		<span id="addbut"></span>
		<span id="editbut"></span>
		
		</div>
		<div region="center" style="background:#eee;"  border="false">
				<div id="tbs" class="easyui-tabs" fit="true">
				<div title="对账单汇总" >
					 <div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:50px;" border="false">
						<br/>
						   <form id="form_supplier_bill_id">
						            &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;对账日期:
								<input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountDate2\',{d:-1})}'})" name="accountDate" id="accountDate" style="width: 85px;"/> 至 
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'accountDate\',{d:1})}'})" name="accountDate" id="accountDate2" style="width: 85px;"/>
							    &nbsp; &nbsp;  对账单号:<input id="" name="accountCode"  style="width: 130px"/>
						    </form>
					   </div>
						<div id="datagrid_supplier_bill_div" region="center" style="background:#eee;" border="false">
							<table id="datagrid_distributor_bill_id"></table>
						</div>
						
				  	</div>
				</div>
				<div title="对账单明细" >
					<div class="easyui-layout" style="width:1000px;height:800px;" border="false" fit="true">
						<div data-options="region:'north'" style="overflow: hidden;padding:1px; background:#eee; height:80px;" border="false">
						   <form id="form_supplier_bill_detail_id">
						   <table>
						   	<tr>
						   		<td style="display: none"><input name="accountId"/></td>
						   		<td>日期:</td>
						        <td><input  type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountDate\')}'});" name="accountDate" id="acDate" style="width: 130px;"/> 	</td>
						 
						   		<td> 单号:</td>
						   		<td><input id="accountCode" name="accountCode" /></td>
						   		
						   		<td>数量:</td>
						   		<td><input id="" name="num" readonly="readonly" style="background: #c0d8d8"/></td>
				   				<td>
									分销商：
								</td>
									<td><input id="dName" name="xsDistributorName" style="background-color:#c0d8d8; width: 200px";" onkeypress=" if(event.keyCode==13) { adddis(); return false;}"  style="background-color:#c0d8d8;"/>
									<img id="imgid2" src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="adddis();"/>
									<input name="allocateId" id="did" type="hidden"  />
										</td> 
						   		<td> 调拨单号:</td>
						   		<td><input name="allocatecode" style="width:130px;" readonly="readonly"  onkeypress=" if(event.keyCode==13) { showYdDlog(); return false;}" style="background: #c0d8d8"/>
						   		<img id="imgid"  src="${pageContext.request.contextPath}/js/easyui/themes/icons/choice.png" onclick="showYdDlog();"/>
						   		<input name="instorehouseId" id="instorehouseId" type="hidden"  />
						   		</td>
						   		
						   	</tr>
						   	<tr>
						   		
						   		<td>金额:</td>
						   		<td><input id="tax" class="easyui-numberbox" data-options="validType:'monery'" name="accountSun" readonly="readonly" style="background: #c0d8d8;width:130px;"/></td>
						   		<td>应付款:</td>
						   		<td><input id="accountBalance"  class="easyui-numberbox" data-options="validType:'monery'" name="accountBalance" readonly="readonly" style="background: #c0d8d8"/></td>
						   		<td>本次付款:</td>
						   		<td><input id="nowtax" class="easyui-numberbox"  data-options="validType:'monery'" name="accountMoney" /></td>
						   		<td>经办人:</td>
						   		<td>
						   			<input id="personId" name="accountPerson" style="width:215px;" value="<%=((BasUsers)session.getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId() %>"
									class="easyui-combobox"	data-options="
									url : 'sellUtilAction!findUsers.action',
									disabled : true,
									valueField:'id',  
									textField:'name',
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
						   		<td colspan="7"><input id="" name="remark" style="width:770px"/></td>
						   		
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
								<input  type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'instorehouseDate\',{d:1})}'})" name="instorehouseDate" id="instorehouseDate2" style="width: 85px;"/>
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
