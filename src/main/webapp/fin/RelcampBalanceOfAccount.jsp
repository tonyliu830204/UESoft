<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>供应商对账</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/relcampbalanceofaccount.js"></script>
  </head>
  <body>
    <div id="cc" class="easyui-layout" fit="true" border="false"> 
		        <div region="north"   border="false" split="false" style="height:30px;background: #eee;">
		        	<privilege:enable code="RELCAMPACCOUNTPAID">
			            <a href="javascript:void(0);"  class="easyui-linkbutton"
					    iconCls="icon-add" plain="true"  onclick="addPersonnel(1);">付款</a>
				    </privilege:enable>
				    <privilege:enable code="RELCAMPACCOUNTDELETE">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-remove" plain="true" onclick="del();">删除</a>
					</privilege:enable>
					<%--<a href="javascript:void(0);"  class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="addPersonnel(2);">修改</a>
					--%>
					<privilege:enable code="RELCAMPACCOUNTQUERY">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="rboam_serachCondition();">查询</a>
					</privilege:enable>
					<privilege:enable code="RELCAMPACCOUNTCLEAR">
			       		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="rboam_clearCondition();">清空</a>
			        </privilege:enable>
			        <privilege:enable code="RELCAMPACCOUNTPRINT">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-print" plain="true">打印</a>
					</privilege:enable>
					<privilege:enable code="RELCAMPACCOUNTEXPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-export" plain="true">导出</a>
					</privilege:enable>
					<privilege:enable code="RELCAMPACCOUNTIMPORT">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-import" plain="true">导入</a>
					</privilege:enable>
					<span id="saveOrCancelBtn"></span>
		        </div>
                <div region="center"  style="background:#eee;" split="false" border="false">   
                  <div id="RelcampBalanceOfAccountTab" class="easyui-tabs" fit="true"  border="false">
			          <div title="对账单汇总" style="display:block;" closable="false" fit="true"  border="false">  
	                   <div id="cc" class="easyui-layout" fit="true">  
					        <div region="north" split="false" style="background:#eee;height:25px;"  border="false">
					        <form id="RelcampBalanceOfAccountMainSearchForm">
						          <table>
						            <tr>
						              <td>供应商:</td>
						              <td><input id="s_rboam_relcampId" name="s_rboam_relcampId" style="background-color: #c0d8d8;" class="easyui-combobox"
										data-options="
										editable : false,
										url : 'RelcampBalanceOfAccountAction!loadRelcamp.action',
										valueField:'id',  
										textField:'text' "/></td>
						            </tr>
						          </table>
					          </form>
					        </div>
					        <div region="center" style="background:#eee;"  border="false">
					          <table id="RelcampBalanceOfAccountMainTable"></table>
					        </div>
					        
					  </div>
		              </div>  
		              <div title="对账单明细" style="overflow:false;" closable="false" border="false" fit="true">  
			              <div id="cc" class="easyui-layout" fit="true">  
					        <div region="north" split="false" style="background:#eee;height:30px;"  border="false">
					          <form id="RelcampBalanceOfAccountMainForm">
					             <table>
					               <tr>
					                 <td>供应商编号:</td>
					                 <td><input id="rboam_relcampId" name="relcampId"  style="background-color: #c0d8d8;" class="easyui-combobox"
											data-options="
											editable : false,
											url : 'RelcampBalanceOfAccountAction!loadRelcamp.action',
											valueField:'id',  
											textField:'text' "/></td>
									 <td>总数量:</td>
					                 <td><input id="rboam_sumTotalNum" name="sumTotalNum" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					                 <td>总金额:</td>
					                 <td><input id="rboam_sumTotalAmont" name="sumTotalAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					                 <td>总成本额:</td>
					                 <td><input id="rboam_sumTaxCount" name="sumTaxCount" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					                 <td>应付金额:</td>
					                 <td><input id="rboam_sumRecAmont" name="sumRecAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					                 <td>已付金额:</td>
					                 <td><input id="rboam_sumPaidAmont" name="sumPaidAmont" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					                 <td>余额</td>
					                 <td><input id="rboam_sumBalance" name="sumBalance" readonly="readonly" style="background-color: #c0d8d8;"/></td>
					               </tr>
					             </table>
					          </form>
					        </div>
					        <div region="center" style="background:#eee;"  border="false">
					          <table id="RelcampBalanceOfAccountTable"></table>
					        </div>
						  </div>
			          </div>
			          <div title="未对账查询" style="overflow:false;display:block;" closable="false" fit="true" border="false">  
			           <div id="cc" class="easyui-layout" fit="true">  
					        <div region="north" split="false" style="background:#eee;height:25px;"  border="false">
					          <form id="NoRelcampBalanceOfAccountMainSearchForm">
						          <table>
						            <tr>
						              <td>供应商:</td>
						              <td><input id="no_rboam_relcampId" name="no_rboam_relcampId" style="background-color: #c0d8d8;" class="easyui-combobox"
										data-options="
										editable : false,
										url : 'RelcampBalanceOfAccountAction!loadRelcamp.action',
										valueField:'id',  
										textField:'text' "/></td>
						            </tr>
						          </table>
					          </form>
					        </div>
					        <div region="center" style="background:#eee;"  border="false">
					          <table id="NoRelcampBalanceOfAccountTable"></table>
					        </div>
						</div>
			          </div>  
                  </div>  
          </div>  
      </div>
  </body>
</html>
