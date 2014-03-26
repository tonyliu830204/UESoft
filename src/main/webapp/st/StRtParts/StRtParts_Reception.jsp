<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/st/StRtParts/StRtParts_Reception.js"></script>
<div id="cc" class="easyui-layout" fit="true" border="false">  
    <div region="north" title="查询条件" split="false" style="height:55px;background:#eee;" border="false">
		<form id="srp_receptionForm">
			<table>
				<tr>
					<td>工单号:</td>
					<td><input id="srps_receptionId" name="receptionId" onkeyup="_query();"/></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="_clear();" plain="true">清空</a>   
					</td>
				</tr>
			</table>
		</form>
    </div>
    <div region="center" style="background:#eee;" border="false" >
       <table id="srp_receptionTable"></table>
    </div>
</div>