<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellSystemParamSet/color.js"></script>
<script type="text/javascript">

function LoadOneOk() {
	if (document.readyState == "complete") {
		initOneFrame();
	} else {
		setTimeout("LoadOneOk();", 1000);
	}
}

var initOneFrame = function() {
loadOneFunction();
	loadOneData();
}
var loadOneFunction = function(){
	$("#<%=Contstants.SELLPARAM.CARAGECOLOUR  %>Value").click(function(){
		colorreplace('<%=Contstants.SELLPARAM.CARAGECOLOUR  %>PANEL');
		colorreplace('<%=Contstants.SELLPARAM.CARAGECOLOUR  %>PANEL');
		coloropen('<%=Contstants.SELLPARAM.CARAGECOLOUR  %>Value', '<%=Contstants.SELLPARAM.CARAGECOLOUR %>PANEL');
	});
	
}

var loadOneData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.SELLPARAM %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.SELLPARAM.CARAGE%>" 
						
				    ){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.SELLPARAM.ISMESSAGE %>" 
						 || obj.ciName == "<%=Contstants.SELLPARAM.BASEDATAADD %>" 
						  || obj.ciName == "<%=Contstants.SELLPARAM.UPDATEUSERS %>"
						  || obj.ciName == "<%=Contstants.SELLPARAM.DBCOST %>" 
						  || obj.ciName == "<%=Contstants.SELLPARAM.COUNTZJ %>" 
						  || obj.ciName == "<%=Contstants.SELLPARAM.MIXCAR %>"  
					){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
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

--></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    <br>
			<fieldset >
	             <legend>1.综合参数设置</legend>
	             <br>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table >
			 					 <tr> 
			 					      <td width="130"><lable id="<%=Contstants.SELLPARAM.SELLPASSWORD %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.SELLPASSWORD %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.SELLPARAM.SELLPASSWORD %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.SELLPASSWORD %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						   <td width="130"><lable id="<%=Contstants.SELLPARAM.DATABACKUPPATH %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.DATABACKUPPATH %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.SELLPARAM.DATABACKUPPATH %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.DATABACKUPPATH%>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
								   	  <td width="180"><lable id="<%=Contstants.SELLPARAM.TAXRATE%>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.TAXRATE %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
								   		  <input id="<%=Contstants.SELLPARAM.TAXRATE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.SELLPARAM.TAXRATE %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
								   	 
			  				     </tr>
			  				     <tr>
			  						  <td width="100"><lable id="<%=Contstants.SELLPARAM.CARAGE %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGE %>Value" name="ciValues" class="easyui-combobox" style="width:140px;"
			  						     data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/baseTagAction!findBaseData.action?baseKey=<%=Contstants.BASE_SELL.CARAGEDATE%>'"/>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGE %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
								   	  <td width="180"><lable id="<%=Contstants.SELLPARAM.ZJDATA %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.ZJDATA %>Value" name="ciValues"   type="text" class="easyui-validatebox" style="width:140px;"/>
								   		  <input id="<%=Contstants.SELLPARAM.ZJDATA %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.SELLPARAM.ZJDATA %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
								   	  <td width="100"><lable id="<%=Contstants.SELLPARAM.STOCKORDER %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.STOCKORDER %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.SELLPARAM.STOCKORDER %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.STOCKORDER %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
								 </tr> 
								 <tr>
								      
			  						  <td width="130"><lable id="<%=Contstants.SELLPARAM.CARAGEDAY %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGEDAY %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGEDAY %>Name" name="ciNames" type="hidden"/> 
			  						      <input id="<%=Contstants.SELLPARAM.CARAGEDAY %>CiId" name="ciCiIds" type="hidden"/>
			  						  </td>
			  						   <td width="100"><lable id="<%=Contstants.SELLPARAM.CARAGECOLOUR %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGECOLOUR%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.SELLPARAM.CARAGECOLOUR%>Name" name="ciNames" type="hidden"/> 
			  						      <input id="<%=Contstants.SELLPARAM.CARAGECOLOUR %>CiId" name="ciCiIds" type="hidden"/>
			  						       <div id="<%=Contstants.SELLPARAM.CARAGECOLOUR %>PANEL" style="position:absolute;z-index:999;display:none;"></div>
			  						  </td>
			  						  
								 </tr>
							 </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                         <table>
	                             <tr>
	                                  
			  						  <td width="150">
			  						      <input id="<%=Contstants.SELLPARAM.ISMESSAGE%>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.ISMESSAGE %>Lable" ></lable>
			  						      <input id="<%=Contstants.SELLPARAM.ISMESSAGE%>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.ISMESSAGE%>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="170">
			  						      <input id="<%=Contstants.SELLPARAM.BASEDATAADD %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.BASEDATAADD %>Lable" ></lable>
			  						      <input id="<%=Contstants.SELLPARAM.BASEDATAADD%>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.BASEDATAADD %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="120">
			  						      <input id="<%=Contstants.SELLPARAM.UPDATEUSERS %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.UPDATEUSERS%>Lable"></lable>
			  						      <input id="<%=Contstants.SELLPARAM.UPDATEUSERS %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.UPDATEUSERS %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						   <td width="180">
			  						      <input id="<%=Contstants.SELLPARAM.DBCOST %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.DBCOST%>Lable"></lable>
			  						      <input id="<%=Contstants.SELLPARAM.DBCOST %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.DBCOST %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						    
			  						   <td width="150">
			  						      <input id="<%=Contstants.SELLPARAM.COUNTZJ %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.COUNTZJ%>Lable"></lable>
			  						      <input id="<%=Contstants.SELLPARAM.COUNTZJ %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.COUNTZJ %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						   
			  						   <td width="160">
			  						      <input id="<%=Contstants.SELLPARAM.MIXCAR %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLPARAM.MIXCAR%>Lable"></lable>
			  						      <input id="<%=Contstants.SELLPARAM.MIXCAR %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.SELLPARAM.MIXCAR %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						
								 </tr>
							 </table>
	                    </td>
	                </tr>
	             </table>
	             <br>
	        </fieldset>
		</div>
	</form>
</div>