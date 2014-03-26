<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.syuesoft.model.BasUsers"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '入库调价管理' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	</head>
  <body>
         <script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StGoodsStorage/stgoodsstoragemanager.js"></script>
         <div id="cc" class="easyui-layout" fit="true" border="false">  
		        <div region="north" split="false" style="height:26px;" border="false">
				     <div  style="background:#eee;">
				        <a href="javascript:void(0);" id="sgs_serachBtn" class="easyui-linkbutton" iconCls="icon-search" 
				           plain="true" onclick="searchByCondition_change();">查询</a>
				        <a href="javascript:void(0);"  id="sgs_clearBtn" class="easyui-linkbutton" iconCls="icon-cancel" 
				         plain="true" onclick="searchByCondition_clear();">清空</a>
						<a href="javascript:void(0);" id="sgs_changeBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="changePrice();">配件调价</a>
						<span id="changePriceButton"></span>
				     </div>
		        </div>
	            <div region="center" style="background:#eee;">
		         <div id="cc" class="easyui-layout" border="false" fit="true">  
				   <div region="center" style="background:#eee;" border="false" split="false">
					      <div id="cc" class="easyui-layout" border="false" fit="true">  
						    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:60px;" border="false">
						       <form id="StGoodsStorageSearchForm" >
							      <table>
								      <tr>
								        <td>入库单号:</td>
								        <td><input id="sgscp_storageId" name="sgscp_storageId"/></td>
								      </tr>
							       </table>
							   </form>
						    </div>  
			    			<div region="center" border="false" split="false" style="width:600px;height:300px;background:#eee;">
			    			   <table id="StGoodsStoregeChangePriceDetailTable"></table>
			    			</div>
						 </div>
				</div>     
	        </div>
	     </div>
	     </div>
    </body>
</html>
