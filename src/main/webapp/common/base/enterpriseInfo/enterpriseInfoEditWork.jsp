<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants" %> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript">
	function customArchivesKeyUp(id1,id2){
		var dataLength=document.getElementById(id1).value.length;
		document.getElementById(id2).innerHTML=dataLength;
	}
</script>
<form id="enterpriseEditForm" style="margin-top: 5px">
	<input type="hidden" id="enterpriseId" name="enterpriseId"/>
	<input type="hidden" id="workId" name="workId"/>
	<table align="center" style="margin-top: 10px;">
		<tr>
			<td width="100">估价单注脚:</td>
			<td><input type="text" name="enterpriseGjFootnote" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'估价单注脚为必填项',validType:'character'" maxlength="50"/></td>
			<td width="100">派工单ISO编号:</td>
			<td><input type="text" name="enterpriseJSISONO" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'派工单ISO编号为必填项',validType:'character'" maxlength="50"/></td>	
		</tr>
		<tr>
			<td width="100">派工单抬头:</td>
			<td><input type="text" name="enterprisePgHead" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'派工单抬头为必填项',validType:'character'" maxlength="50"/></td>
			<td width="100">索赔单注脚:</td>
			<td><input type="text" name="enterpriseSpFootnot" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'索赔单注脚为必填项',validType:'character'" maxlength="50"/></td>	
		</tr>
		<tr>
			<td width="100">接车单注脚:</td>
			<td><input type="text" name="enterpriseJcFootnote" style="width:170px"  class="easyui-validatebox" data-options="missingMessage:'接车单注脚为必填项',validType:'character'" maxlength="50"/></td>
			<td width="100">结算单抬头:</td>
			<td><input type="text" name="enterpriseJsHead" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'结算单抬头为必填项',validType:'character'" maxlength="50"/></td>	
		</tr>
		<tr>
			<td width="100">最大登录上限:</td>
			<td><input type="text" name="enterpriseLoginLimit" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'最大登录上限为必填项',validType:'integer'" maxlength="10"/></td>
			<td width="100">可用短信条数:</td>
			<td><input type="text" name="enterpriseSMSLimit" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'可用短信条数为必填项',validType:'integer'" maxlength="10"/></td>	
		</tr>
		<tr>
			<td width="100">出库单ISO编号:</td>
			<td><input type="text" name="outboundnumber" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'出库单ISO编号为必填项',validType:'character'" maxlength="50"/></td>
			<td width="100">网点编号:</td>
			<td><input type="text" name="enterprisePorint" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'网点编号为必填项',validType:'character'" maxlength="50"/></td>	
		</tr>
		<tr>
			<td width="100">车档案备注格式:</td>
			<td><input type="text" name="enterpriseRemark" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'车档案备注格式为必填项',validType:'character'" maxlength="50"/></td>
			<td width="100">结算单注脚:</td>
			<td><input type="text" name="enterpriseJsFootnote" style="width:170px" class="easyui-validatebox" data-options="missingMessage:'结算单注脚为必填项',validType:'character'" maxlength="50"/></td>	
		</tr>
		<tr>
			<td width="100">最新文件目录:</td>
			<td colspan="3"><input type="file" name="enterprisePath" style="width:456px" /></td>	
		</tr>
	</table>
</form>