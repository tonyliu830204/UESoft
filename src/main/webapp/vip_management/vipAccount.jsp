<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员对账单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
    var sate = '<%=Contstants.AUDIT_TAG.AUDITYESS %>';
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/vip_management/vipAccount.js"></script>
  </head>
  <body>
      <div id="cc" class="easyui-layout"fit="true">  
        <div region="north" split="false" style="height:26px;" border="false">
             <div style="background: #eee;">
                <privilege:enable code="VIPACCOUNTADD">
	   				<a id="add" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd();">对账</a>
	   			</privilege:enable>
	   			<privilege:enable code="VIPACCOUNTDELETE">
	   				<a id="pay" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="doPayMent()">收款</a>
	   			</privilege:enable>
				<privilege:enable code="VIPACCOUNTQUERY">
					<a id="search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="doConditionSubmit();">查询</a>
				</privilege:enable>
				<privilege:enable code="VIPACCOUNTCLEAR">
					<a id="clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearForm();">清空</a>
				</privilege:enable>
				<privilege:enable code="VIPACCOUNTEXAMIN">
					<a id="examine" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-examine" plain="true" onclick="doShenhe();">审核</a>
				</privilege:enable>
				<privilege:enable code="VIPACCOUNTEXPORT">
					<a id="export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true">导出</a>
				</privilege:enable>
				<span id="editbutton"></span>
			 </div>
        </div>  
        <div region="center"  style="background:#eee;">
              <div id="tb_id" class="easyui-tabs" fit="true" border="false">
			    <div title="对账单汇总" style="display:block;" closable="false">  
			        <div id="cc" class="easyui-layout" fit="true">  
					    <div region="north" title="查询条件" split="false" style="overflow: hidden;padding:1px;height:55px;background:#eee;" border="false">
						    <form id="form_vipAccount">
							    <table>
							       <tr>
								       <td>对账时间范围:</td>
								       <td><input id="searchaccountStartDate" name="accountStartDate" style="width:150px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'searchaccountEndDate\',{d:-1})}'})"/></td>
								       <td>至</td>
								       <td><input id="searchaccountEndDate" name="accountEndDate" style="width:150px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'searchaccountStartDate\',{d:0})}',maxDate:'%y-%M-%d'})"/></td>
								       <td>对账日期 :</td>
			   						   <td><input id="searchaccountDate" name="accountDate" style="width:150px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'searchaccountDate2\',{d:-1})}'})"/></td>
								       <td>至</td>
								       <td><input id="searchaccountDate2" name="accountDate2" style="width:150px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'searchaccountDate\',{d:0})}',maxDate:'%y-%M-%d'})"/></td>
								       <td>注销情况:</td>
									   <td><input id="logout"  name="logout "  style="width:110px;" class="easyui-combobox" 
											data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.ZXQKF.ZXQKF %>',editable:false"/> 
									   </td>
							       </tr>
							    </table>
						    </form>
					    </div>  
					    <div region="center" id="vipAccountSummaryDiv" border="false" style="background:#eee;">
					        <table id="vipAccountSummary" border="0"></table>
					    </div>
					</div>  
			    </div>
			    <div title="对账单明细" closable="false" style="overflow:auto;display:block;">  
		            <div id="cc" class="easyui-layout"border="false" fit="true">  
					    <div region="north" title="" split="false" border="false"  style="overflow:hidden;padding:1px;height:30px;background:#eee;">
						   <form id="form_vipAccountDetail">
							     <table>
							        <input id="accountId" name="accountId" type="hidden" />
									<tr>
										<td>对账期:</td>
			   						    <td><input id="accountStartDate" name="accountStartDate" type="text" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'accountEndDate\',{d:-1})}'})" style="width:150px"/>至 </td>
									    <td><input id="accountEndDate" name="accountEndDate" type="text" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'accountStartDate\',{d:1})}',maxDate:'%y-%M-%d'})" style="width:150px"/></td>
									    <td>总储值额 :</td>
			   						    <td><input name="incomeAmount" style="background:#eee;" readonly="readonly"/></td>
			   						    <td>总收益额 :</td>
			   						    <td><input name="defrayAmount" style="background:#eee;" readonly="readonly"/></td>
			   						    <td>对账日期 :</td>
			   						    <td><input name="accountDate" style="background:#eee;width:150px" readonly="readonly"/></td>
									</tr>
								</table>
						    </form>
					    </div>  
					    <div region="center" spilt="false" border="false"  style="background:#eee;">
					         <table id="vipAccountDetail" border="0"></table>
					    </div>
			      </div>
		      </div>
		      <div title="对账单收款明细" >
		          <div id="cc" class="easyui-layout"border="false" fit="true">  
					    <div region="north" title="" split="false" border="false"  style="overflow:hidden;padding:1px;height:30px;background:#eee;">
						   <form id="form_vipAccountMoneyDetail">
							     <table>
							        <input name="detailId" type="hidden" />
							        <input name="accountId" type="hidden" />
							        <input name="refundAmount" type="hidden" />
							        <input name="receiptAccount" type="hidden" />
									<tr>
			   						    <td>应收款额:</td>
			   						    <td><input id="gatheringAccount" name="gatheringAccount" type="text" style="width:150px" readonly="readonly"/></td>
										<td>收款金额:</td>
			   						    <td><input id="detailgatheringAccount" name="detailgatheringAccount" type="text" style="width:150px" class="easyui-validatebox" data-options="required:true,validType:'intOrFloat',precision:2,groupSeparator:','"/>
			   						    </td>
			   						    <td>收款方式:</td>
										<td><input name="detailaccountWay" class="easyui-combobox" style="width:260px;"
											data-options="url : '${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.PAIDWAY.PAIDWAYKRY %>',
											valueField:'id',  
											textField:'text',
											mode:'remote',
											required:true,
											multiple:false  "/></td>
									</tr>
								</table>
						    </form>
					    </div>  
					    <div region="center" spilt="false" border="false"  style="background:#eee;">
					         <table id="vipAccountMoneyDetail" border="0"></table>
					    </div>
			      </div>
			  </div>
		   </div>
	    </div>
    </div>  
 </body>
</html>