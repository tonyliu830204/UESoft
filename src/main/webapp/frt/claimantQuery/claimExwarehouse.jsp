<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 索赔出库查询 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/claimantQuery/claimExwarehouse.js"></script>
<div class="easyui-layout"
	style="overflow: hidden;width:800px;height:600px;" fit="true"
	border="false">
	<div region="north" title="查询条件"
		style="background:#eee;height:80px;padding:3px;" border="false">
		<form id="frtWorkOrderclaimExwarehouseQueryForm">
		<table>
			<tr>
				<td>出库日期:</td>
				<td>
					<!--<input type="text" class="Wdate" id="stomDateBegin" name="stomDateBegin" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});" />至
					<input type="text" class="Wdate" name="stomDateEnd" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});" />	
				-->
					<input id="stomDateBegin" name="stomDateBegin"  style="width:140px;" class="Wdate"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,maxDate:'#F{$dp.$D(\'stomDateEnd\',{d:-1})}'})"/>
	                                              至<input id="stomDateEnd" name="stomDateEnd" style="width:140px;" class="Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowToday:false,errDealMode:1,minDate:'#F{$dp.$D(\'stomDateBegin\',{d:0})}'})"/>
				</td>
			</tr>
		</table>	
		</form>
	</div>
	<div id="frtWorkOrderclaimExwarehouseDatagrid_center" region="center" style="background:#eee;" border="false">
		<table id="frtWorkOrderclaimExwarehouseDatagrid"></table>
	</div>
</div>