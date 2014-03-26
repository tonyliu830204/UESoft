<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StPurOrder/StPurOrder_RecampInfo1.js"></script>
<!--采购单汇总   供应商窗体 -->
<div id="cc1" class="easyui-layout" fit="true" border="false">   
  <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
   <form id="stpurorder_basrelationcampanymainform">
               编号:<input id="relcampId11" name="relcampId"  onkeyup="_query();"/>&nbsp;&nbsp;
	    供应商:<input id="relcampName11" name="relcampName" onkeyup="_query();"/>
	  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
    </form>
   </div>
   <div region="center" style="background:#eee;" border="false">
     <table id="stpurorder_basrelationcampanymaintable"></table> 
   </div>
</div>

