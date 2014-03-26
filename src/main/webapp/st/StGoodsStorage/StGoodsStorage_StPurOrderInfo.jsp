<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StGoodsStorage/StGoodsStorage_StPurOrderInfo.js"></script>
<!-- 采购单信息查询 -->
<div id="cc" class="easyui-layout" fit="true" border="false">  
    <div region="north" title="条件" split="false" style="height:53px;background:#eee;" border="false">
	    <form id="stgoodstorage_stpurorderform">
		    采购单号:<input type="text" id="sgmss_orderId" name="sgmss_orderId" onkeyup="_query();"/>&nbsp;&nbsp;
		  <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="_clear();" plain="true">清空</a>
	    </form>
    </div>
    <div region="center" style="background:#eee;"border="false">
        <table id="stgoodstorage_stpurordertable"></table>
    </div>
</div>