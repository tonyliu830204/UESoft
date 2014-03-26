<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>接车台次分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/combine/receptionCountAnalyse.js"></script>
  </head>
  <body>
  		 <div class="easyui-layout" style="width:800px; height:600px;" fit="true"
			border="false">
			 <div region="north"  split="false" style="height:70px;background: #eee;" border="false">
			    <privilege:enable code="COMBINEFRTCOUNT_SEARCH">
				    <a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryReceptionCountAnalyse();">查询</a>
			   </privilege:enable>
				<privilege:enable code="COMBINEFRTCOUNT_CLEAR">
				    <a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
			   </privilege:enable>
			   <privilege:enable code="COMBINEFRTCOUNT_EXPORT">
				    <a id='_export' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_exceptReceptionCountAnalyse();">Excel导出</a>
			   </privilege:enable>
			<br/>
			<form id="receptionCountAnalyseQueryForm">
				<table>
					<tr>
						<td>维修日期:</td>
						<td>
							 <input id="preclrBeginTime" name="preclrBeginTime"  style="width:100px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrEndTime\',{d:-1})}'})"/>
				                                              至<input id="preclrEndTime" name="preclrEndTime" style="width:100px;" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrBeginTime\',{d:0})}'})"/></td>
						<td>企业:</td>
						<td>
						    <input type="text" id="enterpriseId" name="enterpriseId"
						     class="easyui-combobox"  style="width:200px;"
						     data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findAllDistributeEnterprise.action',
						     editable:false,
						     valueField:'id',
						     textField:'text',
						     mode:'remote',multiple:true,separator:','"/>
						</td>
						<td>显示样式:</td>
						<td>
							<input id="is3D"  name="is3D" class="easyui-combobox" style="width: 140px;"  
			            	data-options="
						    editable : false,
						    data:[{'id':'false','text':'2D'},{'id':'true','text':'3D'}],
							valueField:'id',
							textField:'text'"/>
						</td>
					</tr>
				</table>
			</form>
		    </div> 
			<div region="center" style="background:#eee;" border="false" height="100%">
				<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
					border="false">
					<div region="north" style="background:#eee;height:380px;" border="false" title="接车台次分析数据">
						<div id="receptionCountAnalyse_center" style="height:100%;">
							<table id="receptionCountAnalyse"></table>
						</div>
					</div>
					<div  region="center" border="false" >
						  <span id="analyseLoader" style="width:400px;height:400px;margin-top:100px;margin-left:100px;"></span>
						  <span id="snapMapImg" style="width:1400px;height:360px;"></span>
					</div>
					
				</div>
			</div>
		</div>
  </body>
</html>