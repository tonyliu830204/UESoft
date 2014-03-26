<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtParts/StRtParts_Stuf.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">
    <div region="north" title="条件" split="false" style="height:55px;background:#eee;" border="false">
	    <form id="srp_stufForm">
		       接待员编号:<input id="srpss_stfId" name="srpss_stfId" onkeyup="srp_searchByStufCondition();"/>
		       接待员名称:<input id="srpss_stfName" name="srpss_stfName" onkeyup="srp_searchByStufCondition();"/>
		   <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="srp_clearByStufCondition();" plain="true">清空</a>
	    </form>
    </div>
    <div region="center" style="background:#eee;"  border="false">
        <table id="srp_stufTable"></table>
    </div>
</div>
