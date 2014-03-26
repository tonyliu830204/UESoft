<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>索赔结算工时统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system_management/customer_performance_statistics/suopeijiesuangongshitongji.js"></script>
  </head>
  
  <body>
   <div class="easyui-layout" border="false" fit="true"
		style="width: 800px; height: 600px;">
		<div data-options="region:'north',collapsible : false" style="overflow: hidden;padding:3px; background:#eee; height:90px;" border="false">
			<div>
			   	<privilege:enable code="ACHIEVECLAIMS_SEARCH">
				    <a id="_search" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryClaimsHours();">查询</a>
			   </privilege:enable>
				<privilege:enable code="ACHIEVECLAIMS_CLEAR">
				    <a id="_clear" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearClaimsHours();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="ACHIEVECLAIMS_EXPORT">
				    <a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptClaimsHours();">Excel导出</a>
			   </privilege:enable>
		   </div>
			<form id="claimsHoursForm" method="post">
				<table border="0"  style="text-align: right">
					<tr>
						<td>索赔日期：</td>
						<td>
							 <input id="claimsHours_claimantmTimeBegin" name="claimantmTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimsHours_claimantmTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="claimsHours_claimantmTimeEnd" name="claimantmTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimsHours_claimantmTimeBegin\',{d:0})}'})"/>
						</td>
						<td>维修员：</td>
						<td>
							<input  name="stfId"  style="width:140px;" id="claimsHours_stfId"
								class="easyui-combobox"
								data-options="
								validType:'isSelected[\'#claimsHours_stfId\']',
								invalidMessage : '请从下拉框中选择维修员',
								mode : 'remote',
								url : 'basStuffClassAction!findEnterpriseMaintainArtificer.action',
								valueField:'id',  
								textField:'text' "
								/>
						</td>
						<td>付款：</td>
						<td>
							<input type="text"  style="width:140px;" id="claimsHours_balanceFlag" name="balanceFlag" class="easyui-combobox" data-options="
							editable:false,
							data:[{'id':'true','text':'已付款'},{'id':'false','text':'未付款'}],
	   						valueField:'id',  
	   					    textField:'text'"/>
						</td>
					</tr>
					<tr>
						<td>收款日期：</td>
						<td>
						 <input id="claimsHours_preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'claimsHours_preclrTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="claimsHours_preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'claimsHours_preclrTimeBegin\',{d:0})}'})"/>
						</td>
						<td>索赔厂商:</td>
    					<td colspan="3">
	    					<input type="text" id="claimsHours_finComId" name="finComId" class="easyui-combobox"
	    					 style="width: 326px;"		
	    					 data-options="
							url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimManufacturers.action',  
							validType:'isSelected[\'#claimsHours_finComId\']',
							invalidMessage : '请从下拉框中选择索赔厂商',
	    					 mode : 'remote',
							valueField:'id',  
							textField:'text' "/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="claimsHoursMainDatagrid_center" region="west" border="false" style ="width:300px;background : #eee">
			<table id="claimsHoursMainDatagrid"></table>
		</div>
		<div id="claimsHoursDetailDatagrid_center" region="center" border="false" style="background : #eee">
			<table id="claimsHoursDetailDatagrid"></table>
		</div>
	</div>
  </body>
</html>
