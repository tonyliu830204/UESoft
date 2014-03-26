<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.syuesoft.contstants.Contstants"%>
<!-- 新增客户档案 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hz2py.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mailAutoComplete-3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frt/frtResevation/details/addCustomArchives.js"></script>
<form id="customArchivesAddForm">
	<table>
		<tr>
			<td width="58">客户简码:</td>
			<td><input type="text" id="customArchives_add_customJm" name="customJm" readonly="readonly" maxlength="20" style="width: 100px;" /></td>
			<td width="58">客户名称:</td>
			<td colspan="5"><input type="text" id="customArchives_add_customName" name="customName" class="easyui-validatebox"
			 data-options="required:true,missingMessage:'客户名称为必填项'"  style="width:493px;" maxlength="50"
			 onkeyup="$('#customArchives_add_customJm').val(makePy($('#customArchives_add_customName').val()));" /></td>			
		</tr>
		<tr>
			<td width="58">建档日期:</td>
			<td><input type="text" name="createDate"  class="easyui-datetimebox" value="{now}"
				readonly="readonly" data-options="disabled:true" style="width: 100px;" /></td>
			<td width="58">客户性质:</td>
			<td><input type="text" id="customArchives_add_addnatureId" name="natureId" class="easyui-combobox" data-options="
			required:true,
			url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomNature.action',   
    		valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#customArchives_add_addnatureId\']',
    		invalidMessage : '请从下拉框中选择客户性质',
    		mode : 'remote' " 
    		style="width: 100px;" /></td>
			<td width="58">客户分类:</td>
			<td><input type="text" id="customArchives_add_cstgId" name="cstgId" class="easyui-combobox" data-options="
			required:true,
			url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomGroup.action',   
    		valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#customArchives_add_cstgId\']',
    		invalidMessage : '请从下拉框中选择客户类型',
    		mode : 'remote' " 
    		style="width: 100px;" /></td>
			<td width="58">客户类型:</td>
			<td><input type="text" id="customArchives_add_cstId" name="cstId" class="easyui-combobox" data-options="
			required:true,
			url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomType.action',   
    		valueField:'id',   
    		textField:'text',
    		validType:'isSelected[\'#customArchives_add_cstId\']',
    		invalidMessage : '请从下拉框中选择客户分类',
    		mode : 'remote' " 
    		style="width: 100px;" /></td>
		</tr>
		<tr>
			<td width="58">所在区域:</td>
			<td><input type="text" id="customArchives_add_pareaId" name="pareaId" class="easyui-combobox" data-options="
			url:'${pageContext.request.contextPath}/frtOptionsAction!findCustomArea.action',   
    		valueField:'id',   
    		textField:'text',
    		required:true,
    		missingMessage:'所在区域为必选项',
    		validType:'isSelected[\'#customArchives_add_pareaId\']',
    		invalidMessage : '请从下拉框中选择所在区域',
    		mode : 'remote' " 
    		style="width: 100px;" /></td>
			<td width="58">地址:</td>
			<td colspan="5"><input type="text" id="customArchives_add_customAddr" name="customAddr" maxlength="100"
				style="width:493px;" class="easyui-validatebox" data-options="required:true,missingMessage:'地址为必填项'"/></td>
			
		</tr>
		<tr>
			
			<td width="58">电子邮箱:</td>
			<td><input type="text" id="customEmail" name="customEmail" class="easyui-validatebox" maxlength="50" 
				data-options="required:false,validType:'email'" style="width: 100px;"/></td>
			<td width="58">开户银行:</td>
			<td><input type="text" name="bankOfDeposit" maxlength="50" style="width: 100px;"/></td>
			<td width="58">银行账号(<span id="bankAccountLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="customArchives_add_bankAccount" name="bankAccount" 
				onkeyup="customArchivesKeyUp('customArchives_add_bankAccount','bankAccountLength');" maxlength="50" style="width: 100px;"/></td>
			<td width="58">税号(<span id="taxIdLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="customArchives_add_taxId" name="taxId"
			 onkeyup="customArchivesKeyUp('customArchives_add_taxId','taxIdLength');"	 maxlength="50" style="width: 100px;"></td>
		</tr>
		<tr>
			<td width="58">开票地址:</td>
			<td><input type="text" id="customArchives_add_invoicingAddress" name="invoicingAddress" maxlength="100" style="width: 100px;"/></td>
			<td width="58">开票电话:</td>
			<td><input type="text" name="invoicingTel" class="easyui-validatebox" maxlength="20" data-options="required:false,validType:'phone'" style="width: 100px;"/></td>			
			<td width="81">公司联系人:</td>
			<td><input type="text" name="linkman" maxlength="20" style="width: 100px;"/></td>
			<td width="88">公司联系人电话:</td>
			<td><input type="text" name="linkmanTel" class="easyui-validatebox" maxlength="20" data-options="validType:'mobile'" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td width="58">联系电话:</td>
			<td><input type="text" id="customArchives_add_customTel1" name="customTel1" maxlength="20"
			 class="easyui-validatebox" data-options="required:true,missingMessage:'联系电话为必填项',validType:'mobile'" style="width: 100px;"/></td>
			<td width="58">固定电话:</td>
			<td><input type="text" id="customArchives_add_customTel2" name="customTel2" class="easyui-validatebox" 
				data-options="required:false,validType:'phone'"  maxlength="20" style="width: 100px;"/></td>
			<td width="58">传真:</td>
			<td><input type="text" name="fax" class="easyui-validatebox" data-options="validType:'faxno'" maxlength="20" style="width: 100px;"/></td>
			<td width="58">性别:</td>
			<td><input type="text"id="customArchives_add_customSex" name="customSex" class="easyui-combobox" data-options="
				url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.SEXTYPE.SEX %>',
				valueField:'id',
				textField:'text',
				required:false,
	    		missingMessage:'性别为选填项',
				validType:'isSelected[\'#customArchives_add_customSex\']',
	    		invalidMessage : '请从下拉框中选择性别'"
	    		style="width: 100px;"/></td>
		</tr>
		<tr>
    		<td width="58">出生年月:</td>
			<td><input type="text" id="customArchives_add_customBirthday" 
				name="customBirthday" class="Wdate" onfocus="WdatePicker({});" style="width: 100px;" /></td>
			<td width="81">身份证号码(<span id="customIdenLength" style="color:#ff3300;">0</span>):</td>
			<td><input type="text" id="customArchives_add_customIden" maxlength="18"
			 name="customIden" class="easyui-validatebox" data-options="required:false,validType:'idcard'" 
			 onkeyup="customArchivesKeyUp('customArchives_add_customIden','customIdenLength');" style="width: 100px;"/></td>
			 <td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="58">备注一:</td>
			<td colspan="7">
				<textarea  id="customArchives_add_customRemark1" name="customRemark1" 
				onkeyup="showMaxLength('customArchives_add_customRemark1',500);"
					rows="" cols=""  style="width: 600px; height: 50px;"></textarea>
				</td>
		</tr>
		<tr>
			<td width="58">备注二:</td>
			<td colspan="7">
					<textarea  id="customArchives_add_customRemark2" name="customRemark2" 
				onkeyup="showMaxLength('customArchives_add_customRemark2',500);"
					rows="" cols=""  style="width: 600px; height: 50px;"></textarea>
			</td>		
		</tr>
	</table>
</form>