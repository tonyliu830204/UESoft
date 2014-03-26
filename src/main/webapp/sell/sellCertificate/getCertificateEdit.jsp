<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants" %>
    <form id="certificateForm" style="margin-top: 20px">
	<input type="hidden" id="certificateId" name="certificateId"/>
	<table align="center" >
		<tr>
			<td style="75px">合格证：</td>
			<td><input type="text" name="xsCarCertificate" style="width:175px;"  disabled="disabled" class="easyui-validatebox"
						data-options="	required : true,ediable:false"></td>
		</tr>
		<tr>
			<td style="75px">合格证状态：</td>
			<td><input type="text" name="xsCarCertificateState" id="certificateState"  disabled="disabled" style="width:175px" class="easyui-combobox" 
				data-options="url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CERTIFICATESTATE%>',
				required:true,			
				editable:false,
				valueField:'id',   
				textField:'text',
				mode : 'remote',
				validType:'isSelected[\'#certificateState\']',
				invalidMessage : '请从下拉框中选择合格证状态'"></td>
		</tr>
		<tr>
			<td style="75px">领证人：</td>
			<td><input type="text" name="certificatePerson" style="width:175px;" maxlength="20"></td>
		</tr>
		<tr>
			<td style="75px">领证日期：</td>
			<td><input type="text" name="certificateDate" id="certificateDate" style="width:175px " class="Wdate" onfocus="WdatePicker({});" ></td>
		</tr>
	</table>
</form>
