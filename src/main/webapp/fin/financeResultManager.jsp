<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<jsp:include page="../common/inc.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>财务收支管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
   <body>
    <body class="easyui-layout">
      <div data-options="region:'north',border:false" style="height:32px;padding:3px;background:#eee;">
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">新增</a>  
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="_remove();">删除</a>  
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>  
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="_search();">查询</a>  
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="_clear();">清空</a>  
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-export'">导出</a>
    	<a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-examine'">审核</a>
    </div>  
    <div data-options="region:'center',border:false" style="background:#eee;">
		<div class="easyui-layout" data-options="fit:true,border:false">  
		    <div data-options="region:'north',title:'查询条件'" style="padding:3px;background:#eee;height:60px;">
		    	收款日期:<input type="text" class="Wdate" onclick="WdatePicker({readOnly:true});">
		    </div>  
		    <div data-options="region:'center',border:false" style="background:#eee;">
		    	<div id="financeResultManager_tabs" class="easyui-tabs" data-options="fit:true,border:false">  
				    <div title="收支汇总">
				        <table id=""></table>  
				    </div>  
				    <div title="收支明细">  
				        <table id=""></table>
				    </div>
				</div> 
		    </div>  
		</div>
    </div>
  </body>
</html>
