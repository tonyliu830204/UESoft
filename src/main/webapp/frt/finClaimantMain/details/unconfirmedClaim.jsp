<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 未确认索赔工单 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain/details/unconfirmedClaim.js"></script>
<div class="easyui-layout" fit="true" border="false">
	<div region="north" style="background:#eee; height:30px;"
		border="false">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-export" plain="true">导出</a> 
			<!--<a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-ok" plain="true">确定</a>-->
	</div>
	<div region="center" style="background:#eee;" border="false">
		<table id="unconfirmedClaimDatagrid"></table>
	</div>
</div>