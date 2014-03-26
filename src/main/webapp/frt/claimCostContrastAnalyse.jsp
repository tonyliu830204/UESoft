<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>索赔成本对比分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/claimCostContrastAnalyse.js"></script>
  </head>
  
  <body >
   	<div class="easyui-layout" fit="true" border="false">
   		<div region="north"  split="false" style="height:70px;background: #eee;" border="false">
		    <privilege:enable code="CLAIMCOSTANLAYSE_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_search();">查询</a>
		   </privilege:enable>
			<privilege:enable code="CLAIMCOSTANLAYSE_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="CLAIMCOSTANLAYSE_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		   </privilege:enable>
		   <br/>
		   <form id="claimCostContrastAnalyseForm">
				<table>
					<tr>
						<td>索赔日期:</td>
						<td>
							<input id="claimantmTimeBegin" name="claimantmTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimantmTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="claimantmTimeEnd" name="claimantmTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimantmTimeBegin\',{d:0})}'})"/>
						</td>		
			  			<td>索赔单号:</td>
						<td>
							<input type="text" id="claimantmId" name="claimantmId" style="width:140px;"/>
						</td>
					</tr>
				</table>
			</form>
	   </div>  
		<div  id="claimCostContrastAnalyseDatagrid_center" region="center" style="background:#eee;" border="false">
				<table id="claimCostContrastAnalyseDatagrid"></table>
	  	</div>
   	</div>
  </body>
</html>
