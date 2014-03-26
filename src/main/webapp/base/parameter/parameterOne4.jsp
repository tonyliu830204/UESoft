<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript">
<!--
function LoadOneOk() {
	if (document.readyState == "complete") {
		initOneFrame();
	} else {
		setTimeout("LoadOneOk();", 1000);
	}
}

var initOneFrame = function() {
	loadOneData();
}

var loadOneData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.INOUTDEPOT %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.INOUTDEPOT.INDEFRECEIPTTYPE %>"
					     || obj.ciName == "<%=Contstants.INOUTDEPOT.SELLBASEPRICE %>" 
						 || obj.ciName == "<%=Contstants.INOUTDEPOT.SELLADDPORT %>"
					){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.INOUTDEPOT.INOUTDATECHANGE %>"){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else {
					     $('#'+obj.ciName+'Value').numberbox('setValue', obj.ciValue);
					}
					$('#'+obj.ciName+'Name').val(obj.ciName);
					$('#'+obj.ciName+'Lable').html(obj.ciLable);
					$('#'+obj.ciName+'CiId').val(obj.ciId);
		        }
			}else{
				alertMsg("查询失败",'warning');
			}
		},
		error : function (r){
		   if(r.readyState == '0' && r.status == '0'){
			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
		   }
	   }
	});
}
setTimeout("LoadOneOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne4Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>4.入库、出库设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table>
				                <tr>
			 						<td width="110"><lable id="<%=Contstants.INOUTDEPOT.SERVICEDECIMAL %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.SERVICEDECIMAL %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.SERVICEDECIMAL %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.SERVICEDECIMAL %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="150"><lable id="<%=Contstants.INOUTDEPOT.SELLDECIMAL %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.SELLDECIMAL %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.SELLDECIMAL %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.SELLDECIMAL %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				                </tr>
				                <tr>
				  					<td width="130"><lable id="<%=Contstants.INOUTDEPOT.COSTDECIMAL %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.COSTDECIMAL %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.COSTDECIMAL %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INOUTDEPOT.COSTDECIMAL %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				                </tr>
				                <tr>
								   	<td width="150"><lable id="<%=Contstants.INOUTDEPOT.SELLBASEPRICE %>Lable"></lable></td>
			  						<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.SELLBASEPRICE %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action\?key=sellpricetype'"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.SELLBASEPRICE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.SELLBASEPRICE %>CiId" name="ciCiIds" type="hidden"/>
								   	</td>
				                    <td width="130"><lable id="<%=Contstants.INOUTDEPOT.INDEFRECEIPTTYPE %>Lable"></lable></td>
			  						<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.INDEFRECEIPTTYPE %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/baseAction!findBaseListData.action\?key=billType'"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.INDEFRECEIPTTYPE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.INDEFRECEIPTTYPE %>CiId" name="ciCiIds" type="hidden"/>
								   	</td>
				                </tr>
				                <tr>
								   	<td width="110"><lable id="<%=Contstants.INOUTDEPOT.SELLADDPORT %>Lable"></lable></td>
			  						<td>
			  						      <input id="<%=Contstants.INOUTDEPOT.SELLADDPORT %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action'"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.SELLADDPORT %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.INOUTDEPOT.SELLADDPORT %>CiId" name="ciCiIds" type="hidden"/>
								   	</td>
								   	<td width="150"></td>
			  						<td>
								   	</td>
				                    <td width="130"></td>
			  						<td>
								   	</td>
				                </tr>
				              </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                         <table>
								 <tr>
								      <td width="190">
			  						      <input id="<%=Contstants.INOUTDEPOT.INOUTDATECHANGE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.INOUTDEPOT.INOUTDATECHANGE %>Lable"></lable>
			 						      <input id="<%=Contstants.INOUTDEPOT.INOUTDATECHANGE %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.INOUTDEPOT.INOUTDATECHANGE %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
								 </tr>
							 </table>
	                    </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>