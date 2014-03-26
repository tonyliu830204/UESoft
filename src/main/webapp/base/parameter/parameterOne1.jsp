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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.COLLIGATES %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.COLLIGATES.INTERFACE %>" 
						 || obj.ciName == "<%=Contstants.COLLIGATES.ZEROPARTSHOW %>" 
						 || obj.ciName == "<%=Contstants.COLLIGATES.ACCESSORIESWAY %>"
				    ){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.COLLIGATES.DEFAULTSHOWDAY %>" 
						 || obj.ciName == "<%=Contstants.COLLIGATES.ACCESSORIESRATE %>" 
						 || obj.ciName == "<%=Contstants.COLLIGATES.TASKTIMEPRICE %>" 
					){
						 $('#'+obj.ciName+'Value').numberbox('setValue', obj.ciValue);
					}else {
					     $('#'+obj.ciName+'Value').val(obj.ciValue);
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
	<form id="parameterOne1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>1.综合参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table >
			 					 <tr> 
			 					      <td width="130"><lable id="<%=Contstants.COLLIGATES.COLPASSWORD %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.COLPASSWORD %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.COLPASSWORD %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.COLPASSWORD %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="100"><lable id="<%=Contstants.COLLIGATES.INTERFACE %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.INTERFACE %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.COLLIGATES.INTERFACE %>'"/>
								   		  <input id="<%=Contstants.COLLIGATES.INTERFACE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.COLLIGATES.INTERFACE %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
								   	  <td width="180"><lable id="<%=Contstants.COLLIGATES.ZEROPARTSHOW %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.ZEROPARTSHOW %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.STOCKZEROPART.STOCKZEROPARTKEY %>'"/>
								   		  <input id="<%=Contstants.COLLIGATES.ZEROPARTSHOW %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.COLLIGATES.ZEROPARTSHOW %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
			  				     </tr>
			  				     <tr>
			  						  <td width="130"><lable id="<%=Contstants.COLLIGATES.DEFAULTSHOWDAY %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.DEFAULTSHOWDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.DEFAULTSHOWDAY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.DEFAULTSHOWDAY %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="100"><lable id="<%=Contstants.COLLIGATES.ACCESSORIESWAY %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.ACCESSORIESWAY %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:80,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.ACCESSORIESWAY.ACCESSORIESWAYKEY %>'"/>
								   		  <input id="<%=Contstants.COLLIGATES.ACCESSORIESWAY %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.COLLIGATES.ACCESSORIESWAY %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
								   	  <td width="100"><lable id="<%=Contstants.COLLIGATES.VEHICLELICENSE %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.VEHICLELICENSE %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.VEHICLELICENSE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.VEHICLELICENSE %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
								 </tr> 
								 <tr>
								      <td width="130"><lable id="<%=Contstants.COLLIGATES.ACCESSORIESRATE %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.ACCESSORIESRATE %>Value" name="ciValues" type="text" class="easyui-numberbox" data-options="precision:2" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.ACCESSORIESRATE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.ACCESSORIESRATE %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="100"><lable id="<%=Contstants.COLLIGATES.TASKTIMEPRICE %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.TASKTIMEPRICE %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.TASKTIMEPRICE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.TASKTIMEPRICE %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						   <td width="100"><lable id="<%=Contstants.COLLIGATES.DBBACKUPPATH %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.COLLIGATES.DBBACKUPPATH %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.COLLIGATES.DBBACKUPPATH %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.COLLIGATES.DBBACKUPPATH %>CiId" name="ciCiIds" type="hidden"/>
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