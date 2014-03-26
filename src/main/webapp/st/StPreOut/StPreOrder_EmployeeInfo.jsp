<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPreOut/StPreOrder_EmployeeInfo.js"></script>
<!-- 领用人选择 -->
<div id="cc" class="easyui-layout" fit="true" border="false">  
    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
	    <form id="stpreorder_employeeform">
		               领料员编号:<input type="text" id="spo_stfId" name="stfId" onkeyup="_query();"/>&nbsp;&nbsp;
			    领料员名称:<input type="text" id="spo_stfName" name="stfName" onkeyup="_query();"/>
			  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a> 
	    </form>
    </div>
    <div region="center" style="background:#eee;"  border="false">
       <table id="stpreorder_employeetable"></table>  
    </div>  
</div>