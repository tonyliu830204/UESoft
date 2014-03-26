<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>

<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>接待员业绩统计明细</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/receiveOperation/receiveOperationDetail.js"></script>
  </head>
  
   <body>
    <div id="cc" class="easyui-layout" fit="true" border="false">  
	     <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
	        <privilege:enable code="RECEIVEOPERATIONDETAIL_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_search();">查询</a>
		   </privilege:enable>
			<privilege:enable code="RECEIVEOPERATIONDETAIL_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="RECEIVEOPERATIONDETAIL_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		   </privilege:enable>
	     </div>
		 <div region="center"  split="false" border="false">
		       <div id="cc" class="easyui-layout" fit="true">
				    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" border="false">
					    <form id="receiveOperationDetailForm">
					      <table>
								<tr>
								    <td>结算时间：</td>
									<td>
										<input id="preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
										 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrTimeEnd\',{d:-1})}'})"/>
						                                              至<input id="preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
						                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrTimeBegin\',{d:0})}'})"/>
									</td>
									<td>接待员：</td>
									<td><input type="text" id="receiveOperation_stfId" name="stfId" class="easyui-combobox"
										data-options="
										url:'${pageContext.request.contextPath}/basStuffClassAction!findAllStuffOfEnterpriseInfo.action',
										validType:'isSelected[\'#receiveOperation_stfId\']',
										invalidMessage : '请从下拉框中选择接待员',
										mode:'remote', 
										valueField:'id',  
										textField:'text' "/></td>
									<td>接车分布：</td>
									<td><input type="text" id="receiveOperation_rcptBranch" name="rcptBranch"  class="easyui-combobox" 
										    data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>',
										    validType:'isSelected[\'#receiveOperation_rcptBranch\']',
											invalidMessage : '请从下拉框中选择接车分部',
										    valueField:'id',
										    textField:'text',
										    mode:'remote'"/>
									</td>
									<td>车牌照:</td>
									<td><input type="text"  name="carLicense" class="easyui-combobox"
									 data-options="
									 url:'${pageContext.request.contextPath}/frtOptionsAction!findAllCarLicenseAsCarLicense.action',
									 valueField : 'id',
									 textField : 'text',
									 mode : 'remote'"/></td>
									<td>维修类别:</td>
									<td><input type="text" id="receiveOperation_reptId" name="reptId" class="easyui-combobox"
									data-options="
									url : 'frtOptionsAction!findAllReptype.action',
									validType:'isSelected[\'#receiveOperation_reptId\']',
									invalidMessage : '请从下拉框中选择维修类别',
									mode:'remote' ,
									valueField:'id',  
									textField:'text' "/></td>
								</tr>
							</table>
					    </form>
				    </div>
				    <div region="center" id="receiveOperationDetailDatagrid_center" style="background:#eee;" border="false">
				      <table id="receiveOperationDetailDatagrid"></table>
				    </div>  
			   </div>
		 </div>  
	</div>
   </body>
</html>
