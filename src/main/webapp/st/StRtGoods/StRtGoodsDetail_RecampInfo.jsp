<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtGoods/StRtGoodsDetail_RecampInfo.js"></script>
<!--供应商信息选择 -->
<div id="cc" class="easyui-layout" fit="true" border="false">   
     <div data-options="region:'north',title:'查询条件'" style="overflow: hidden;padding:1px; background:#eee; height:53px;" border="false">
	    <form id="strtgoods_basrecampanydetailform">
		               编号:<input id="srgd_relcampId" name="relcampId" onkeyup="_query();"/>&nbsp;&nbsp;
			     供应商:<input id="srgds_relcampName" name="relcampName" onkeyup="_query();"/>
			  &nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
	    </form>
    </div>
    <div region="center" style="background:#eee;" border="false">
      <table id="strtgoods_basrecampanydetailtable"></table> 
    </div>
</div>