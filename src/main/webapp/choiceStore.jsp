<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 切换企业 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/choiceStore.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="overflow:hidden;background:#eee;height:80px;">
	    	<form id="showStoreSearchForm" method="post">
	    		<fieldset style="height:62px">
	    		<legend><strong>查询条件</strong></legend>
				<table>
					<tr>
						<td>公司名称:</td>
						<td><input type="text" name="enterpriseName"/></td>
						<td>公司简称:</td>
						<td><input type="text" name="enterpriseJm"/></td>
						<td>企业法人:</td>
						<td><input type="text" name="enterprisePerson"/></td>
						<td align="center">
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="_query();">查询</a>
							<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="_clear();">清空</a>
						</td>
					</tr>
				</table>
				</fieldset>
			</form>
	</div> 
	<div data-options="region:'center',border:false" style="background:#eee;" border="false">
	    <table id="showStoreDatagrid"></table>
	</div>
</div>
<div id="making" title=""  data-options="iconCls:'icon-reload',modal:true"
	style="padding: 5px; width: 200px; height: 40px;">
	<font size="26">操作中，请稍候...</font>
</div>