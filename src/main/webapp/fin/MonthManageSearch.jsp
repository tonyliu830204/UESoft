<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>月经营查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/fin/MonthManageSearch.js"></script>
  </head>
  
  <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
      	<privilege:enable code="FINANCEMONTHMANAGE_SEARCH">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
   		</privilege:enable>
    	<privilege:enable code="FINANCEMONTHMANAGE_CLEAR">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="cancel();">清空</a>
   		</privilege:enable>
   		<privilege:enable code="FINANCEMONTHMANAGE_EXPORT">
	    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
   		</privilege:enable>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">
    	<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件'" style="background:#eee;height:55px;">
		      <form id="monthManageQueryForm">
		        <table>
		           <tr>
		             <td>结算日期:</td>
		             <td>
		             	<input id="beginTime" name="beginTime"  style="width:140px;" class="Wdate"
						 onclick="WdatePicker({dateFmt:'yyyy-MM',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'endTime\',{d:-1})}'})"/>
		                                              至<input id="endTime" name="endTime" style="width:140px;" class="Wdate"
		                 onclick="WdatePicker({dateFmt:'yyyy-MM',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'})"/>
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
		    <div data-options="region:'center',border:false" style="background:#eee;">
		    
		    	<div class="easyui-layout" style="width:800px; height:600px;" fit="true"
					border="false">
					<div region="north" style="background:#eee;height:380px;" border="false" title="月经营分析数据">
						<div id="monthManageSearchDatagrid_center" style="height:100%;">
							<table id="monthManageSearchDatagrid"></table>
						</div>
					</div>
					<div  region="center" border="false" ondblclick="maxImage();">
						  <span id="snapMapImg" style="width:1200px;height:360px;"></span>
						  <span id="cakeMapImg" style="width:360px;height:360px;"></span>
					</div>
					
				</div>
		    </div>  
		</div>  
    </div>
  </body>
</html>
