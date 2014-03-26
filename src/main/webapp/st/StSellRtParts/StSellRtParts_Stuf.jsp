<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StSellRtParts/StSellRtParts_Stuf.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">
	    <div region="north" title="条件" split="false" style="height:55px;background:#eee;" border="false">
		    <form id="ssrp_stufForm">
			       接待员编号:<input id="ssrps_stfId" name="ssrps_stfId" onkeyup="ssrp_searchByStufCondition();"/>
			       接待员名称:<input id="ssrps_stfName" name="ssrps_stfName" onkeyup="ssrp_searchByStufCondition();"/>
			   <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="ssrp_clearByStufCondition();" plain="true">清空</a>
		    </form>
	    </div>
	    <div region="center" style="background:#eee;"  border="false">
	        <table id="ssrps_stufTable"></table>
	    </div>
</div>
