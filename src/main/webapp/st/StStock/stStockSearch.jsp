<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>进销存报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	var STOCKTYPE2 = "<%=Contstants.STOCKTYPE.STOCKTYPE2 %>";
	var STOCKCLASSIFY1 = "<%=Contstants.STOCKCLASSIFY.STOCKCLASSIFY1 %>";
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StStock/stStockSearch.js"></script>
  </head>
   <body>
      <div id="cc" class="easyui-layout" fit="true" border="false">
           <div region="north" split="false" style="height:26px;" border="false">
                <div  style="background:#eee;">
                     <a id="btn_search" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>  
    	             <a id="btn_cancel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">清空</a> 
    	             <a id="btn_export" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>
                </div>
           </div>
           <div region="center" style="background:#eee;" border="false" >
	           <div id="cc" class="easyui-layout" fit="true" border="false"> 
	                <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
						 <form id="stockTableSearchForm">
							  <table>
							        <tr>
							              <td>日期:</td>
									      <td>
									          <input id="stomDateStart" name="stomDateStart" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'stomDateEnd\',{d:-1})}'})"/>
									      </td>
									      <td align="center">至</td>
									      <td>
									          <input id="stomDateEnd" name="stomDateEnd" class="Wdate" onclick="WdatePicker({isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'stomDateStart\',{d:0})}'})"/>
									      </td>
							              <td>库存类别:</td>
									      <td>
									          <input id="stockType" name="stockType" style="width: 140px;" class="easyui-combobox" 
					   					       data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.STOCKTYPE.STOCKTYPE%>',panelHeight:75"/>
									      </td>
										  <td>仓别:</td>
									      <td>
									          <input id="storeId" name="storeId" class="easyui-combobox"  
									          data-options="valueField:'id',textField:'text',editable:false,url:'${pageContext.request.contextPath}/PartsTrendsChangeSearchAction_loadStorehouse.action',panelHeight:130"/>
						                  </td>
										  <td>分类方式:</td>
									      <td>
									          <input id="typeWay" name="typeWay" style="width: 140px;" class="easyui-combobox" 
					   					       data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.STOCKCLASSIFY.STOCKCLASSIFY%>',panelHeight:75"/>
									      </td>
							        </tr>
							  </table> 
						 </form>		       
					</div> 
					<div id="StStockTableDiv" data-options="region:'center',border:false" style="background:#eee;">
						 <table id="StStockTable"></table>
					</div>
			   </div>
		   </div>
      </div>
  </body>
</html>