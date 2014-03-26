<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StGoodsStorage/StGoodsStorage_EmployeeInfo.js"></script>
<!-- 验收员选择 -->
<div id="cc" class="easyui-layout"  fit="true" border="false">  
    <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
	    <form id="stgoodstorage_employeeform">
		               编号:<input id="sgsm_stfId" name="stfId" onkeyup="_query();"/>&nbsp;&nbsp;
			    采购员名称:<input id="sgsm_stfName" name="stfName" onkeyup="_query();"/>
			  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a> 
	    </form>
    </div>
    <div region="center" style="background:#eee;"  border="false">
       <table id="stgoodstorage_employeetable"></table>  
    </div>  
</div>