<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>接车分部管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/rcptBranchManangement.js"></script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',border:false" style="padding:1px;height:27px;overflow:hidden;background:#eee;">
   		 <privilege:enable code="RCPTBRANCH_EDIT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
		 </privilege:enable>
    	 <privilege:enable code="RCPTBRANCH_SEARCH">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query();">查询</a>
		 </privilege:enable>  
    	 <privilege:enable code="RCPTBRANCH_CLEAR">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>
		 </privilege:enable>   
    	 <privilege:enable code="RCPTBRANCH_EXPORT">
	      	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'" onclick="_except();">Excel导出</a>
		 </privilege:enable>
    </div>
    <div data-options="region:'center',border:false" style="background:#eee;">
    	 <div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件',border:false" style="overflow:hidden;padding:3px;background:#eee;height:85px;">
		    	<form id="rcptBranchManangementQueryForm">
					<table>
						<tr>
							<td>工单号:</td>
							<td><input type="text" name="receptionId"/></td>
							<td>车辆牌照:</td>
							<td><input type="text" name="carLicense"/></td>
						</tr>
					</table>
				</form>
		    </div>  
		    <div id="rcptBranchManangementDatagrid_center" data-options="region:'center',border:false" style="background:#eee;">
		    	 <table id="rcptBranchManangementDatagrid"></table>
		    </div>  
		</div>  
    </div>  
  </body>
</html>
<div id="rcptBranchManangement"  title="更改接车分部" data-options="iconCls:'icon-add',modal:true"
						style="padding: 5px; width: 240px;height:120px;">
			<form id="rcptBranchManangementEditForm">
				<table>
					<tr>
						<td>请选择接车分部:</td>
						<td><input type="text" name="rcptBranch" class="easyui-combobox"
		        			data-options="
		        			required:true,
		        			editable:false,
		        			url : 'frtOptionsAction!findBaseListData.action?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>',
		        			valueField : 'id',
		        			textField : 'text' "/>
		        			<input type="hidden" id="ids" name="ids"/>
		        		</td>
					</tr>
				</table>		
			</form>
				
</div>
