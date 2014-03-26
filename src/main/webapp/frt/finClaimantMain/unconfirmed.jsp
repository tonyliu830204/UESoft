<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/finClaimantMain/unconfirmed.js"></script>
<div class="easyui-layout" style="width: 800px; height: 600px;"
	fit="true" border="false">
	<div region="north"
		style="overflow: hidden;background:#eee; height:30px; padding: 3px;"
		border="false">
		<form id="unfinishedForm">
			结算日期:<input id="unfinished_claimantmTimeBegin" name="claimantmTimeBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'unfinished_claimantmTimeEnd\',{d:-1})}'})"/>
	                                              至<input id="unfinished_claimantmTimeEnd" name="claimantmTimeEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'unfinished_claimantmTimeBegin\',{d:0})}'})"/>
			索赔厂商:<input type="text" name="finComId" class="easyui-numberbox"/>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="uquery();">查询</a>
		</form>
	</div>
	<div region="center" style="background:#eee;" border="false">
		<table id="finClaimantMainUnfinishedDatagrid"></table>
	</div>
</div>
