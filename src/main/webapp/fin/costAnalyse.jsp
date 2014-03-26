<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>成本分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/costAnalyse.js"></script>
  </head>
  <body>
  <div class="easyui-layout" fit="true" border="false">
  
  
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
       <privilege:enable code="FINANCECOSTANALYSE_SEARCH">
	      	<a href="javascript:void(0);" id="search" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="_query();">查询</a>
	   </privilege:enable>
    	<privilege:enable code="FINANCECOSTANALYSE_CLEAR">
	      	<a href="javascript:void(0);" id="clear" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
	   </privilege:enable>
    	<privilege:enable code="FINANCECOSTANALYSE_PRINT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="print" iconCls="icon-print" plain="true">套打模板</a>
	   </privilege:enable>
		<privilege:enable code="FINANCECOSTANALYSE_SET">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="set" iconCls="icon-set" plain="true">打印设置</a>
	   </privilege:enable> 
	   <privilege:enable code="FINANCECOSTANALYSE_EXPROT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" id="export" iconCls="icon-export" plain="true" onclick="_except();">Excel导出</a>
	   </privilege:enable>
    </div>
    	
		
	<div data-options="region:'center',border:false">
	<div class="easyui-layout" fit="true" border="false">
		<div title="查询条件" style="overflow: hidden;background:#eee;height:60px;" data-options="region:'north',border:false">
			<form id="costAnalyseQueryForm">
				<table>
					<tr>
						<td>结算时间:</td>
						<td>
							<input id="preclrTimeBegin" name="preclrTimeBegin"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'preclrTimeEnd\',{d:-1})}'})"/>
		                                              至<input id="preclrTimeEnd" name="preclrTimeEnd" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'preclrTimeBegin\',{d:0})}'})"/>
						</td>
						<td>车辆品牌: </td>
						<td>
						    <input type="text" name="cbrdId"
						     class="easyui-combobox" 
						     data-options="url:'${pageContext.request.contextPath}/frtOptionsAction!findCarBrand.action',
						     valueField:'id',
						     textField:'text',
						     mode:'remote'"/>
						</td>
						<td>维修类别:</td>
						<td><input type="text" name="reptId" class="easyui-combobox"
						data-options="
						editable : false,
						url : 'frtOptionsAction!findAllReptype.action',
						mode : 'remote',
						valueField:'id',  
						textField:'text' "/></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="costAnalyseDatagrid_center" style="background:#eee;" data-options="region:'center',border:false">
			<table id="costAnalyseDatagrid"></table>
		</div>
		</div>
	</div>
		
  </div>
  </body>
</html>
