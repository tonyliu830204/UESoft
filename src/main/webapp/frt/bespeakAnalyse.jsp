<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>预约分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/bespeakAnalyse.js"></script>
  </head>
  
  <body >
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/claimantQuery.js"></script>
   	<div class="easyui-layout" fit="true" border="false">
   		<div region="north"  split="false" style="height:70px;background: #eee;" border="false">
		    <privilege:enable code="BESPEAKANALYSE_SEARCH">
		      	<a id='_search' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_search();">查询</a>
		   </privilege:enable>
			<privilege:enable code="BESPEAKANALYSE_CLEAR">
		      	<a id='_clear' href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
		   </privilege:enable>
			<privilege:enable code="BESPEAKANALYSE_PRINT">
		      	<a id="_print" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-print" plain="true">套打模板</a>
		   </privilege:enable>
			<privilege:enable code="BESPEAKANALYSE_SET">
		      	<a id="_set" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-set" plain="true">打印设置</a>
		   </privilege:enable>
			<privilege:enable code="BESPEAKANALYSE_EXPROT">
		      	<a id="_export" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
		   </privilege:enable>
		   <br/>
		   <form id="bespeakAnalyseQueryForm">
				<table>
					<tr>
						<td>登记日期:</td>
						<td colspan="3">
						<!--<input type="text" id="beginTime" name="beginTime" style="width:140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						<input type="text" id="endTime" name="endTime" style="width: 140px;"
						class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						-->
							<input id="beginTime" name="beginTime"  style="width:120px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/>
	                                              至<input id="endTime" name="endTime" style="width:120px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'})"/>
						</td>		
			  			<td>预约分类:</td>
					<td><input type="text" id="resvType" name="resvType" class="easyui-combobox"
					 style="width: 140px;"
						 data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.BESPEAKCLASS_TAG.BESPEAKCLASSKEY %>',
						valueField:'id',  
						textField:'text',
						validType:'isSelected[\'#resvType\']',
						invalidMessage : '请从下拉框中选择预约分类',
						mode:'remote' " />
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
		<div region="center" style="background:#eee;" border="false">    
	  		<div id="serviceTimeAnalyzeTabs" class="easyui-layout" fit="true"
				border="false">
				<div region="north" style="background:#eee;height:380px;" border="false" title="预约分析数据">
					<div id="bespeakAnalyseDatagrid_center" style="height:100%;">
						<table id="bespeakAnalyseDatagrid"></table>
					</div>
				</div>
				<div  region="center" border="false" ondblclick="maxImage();" style="width:1560px;">
					  <span id="analyseLoaderSnapMap" style="float:left;width:360px;height:360px;margin-top:100px;margin-left:100px;"></span>
					  <span id="snapMapImg" style="float:left;width:1200px;height:360px;"></span>
					  <span id="analyseLoaderCakeMap" style="float:left;width:360px;height:360px;margin-top:100px;margin-left:100px;"></span>
					  <span id="cakeMapImg" style="float:left;width:360px;height:360px;"></span>
				</div>
			</div>
	  	</div>
   	</div>
  </body>
</html>
