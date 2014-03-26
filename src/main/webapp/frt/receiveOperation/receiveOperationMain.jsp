<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>接待员业绩统计汇总</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/receiveOperation/receiveOperationMain.js"></script>
  </head>
  
   <body>
    <div class="easyui-layout" fit="true" border="false">  
	     <div region="north"  split="false" style="height:30px;background: #eee;" border="false">
	       <privilege:enable code="RECEIVEOPERATIONMAIN_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_search();">查询</a>
		   </privilege:enable>
			<privilege:enable code="RECEIVEOPERATIONMAIN_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="RECEIVEOPERATIONMAIN_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		   </privilege:enable>
	     </div>
		 <div region="center"  split="false" border="false">
		       <div class="easyui-layout" fit="true">
				    <div region="north" title="查询条件" split="false" style="overflow: hidden;height:53px;background:#eee;" border="false">
					    <form id="receiveOperationMainForm">
					      <table>
								<tr>
								    <td>结算时间：</td>
									<td>
										<input id="preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
										 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrTimeEnd\',{d:-1})}'})"/>
						                                              至<input id="preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
						                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrTimeBegin\',{d:0})}'})"/>
									</td>
									<td>工单号：</td>
									<td><input type="text" name="receptionId" /></td>
								</tr>
							</table>
					    </form>
				    </div>
				    <div region="center" id="receiveOperationMainDatagrid_center" style="background:#eee;" border="false">
				      	<table id="receiveOperationMainDatagrid"></table>
				    </div>  
			   </div>
		 </div>  
	</div>
   </body>
</html>
