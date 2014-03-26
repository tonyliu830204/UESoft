<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellSystemParamSet/color.js"></script>
<script type="text/javascript"><!--

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
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.VENDERSET %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.SELLPARAM.ISMESSAGE %>" 
						 || obj.ciName == "<%=Contstants.SELLPARAM.BASEDATAADD %>" 
						   
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne5Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	     <br>
	        <fieldset >
	             <legend>5.厂家返利设置</legend>
	             <br/>           
	             <table  style="background:#eee;">
	                  <tr>
	                        <td width="160"><lable id="<%=Contstants.VENDERSET.VENDERONE %>Lable"></lable></td>
	  						<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERONE %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERONE %>Name" name="ciNames" type="hidden"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERONE %>CiId" name="ciCiIds" type="hidden"/>
						   	</td>
						 
						   	</tr>
						   	<tr>
						   	<td width="90"><lable id="<%=Contstants.VENDERSET.VENDERTWO %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERTWO %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERTWO %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERTWO %>CiId" name="ciCiIds" type="hidden"/>
	  					    </td>
	  					    </tr>
	  					    <tr>
	  					    <td width="160"><lable id="<%=Contstants.VENDERSET.VENDERTHREE %>Lable"></lable></td>
	  						<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERTHREE %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERTHREE %>Name" name="ciNames" type="hidden"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERTHREE %>CiId" name="ciCiIds" type="hidden"/>
						   	</td>
						   	</tr>
						   	<tr>
						   	<td width="90"><lable id="<%=Contstants.VENDERSET.VENDERFOUR %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERFOUR %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERFOUR %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERFOUR %>CiId" name="ciCiIds" type="hidden"/>
	  					    </td>
	  					    </tr>
	  					    <tr>
	  					    <td width="160"><lable id="<%=Contstants.VENDERSET.VENDERFIVE %>Lable"></lable></td>
	  						<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERFIVE %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERFIVE %>Name" name="ciNames" type="hidden"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERFIVE %>CiId" name="ciCiIds" type="hidden"/>
						   	</td>
						   	</tr>
						   	<tr>
						   	<td width="90"><lable id="<%=Contstants.VENDERSET.VENDERSIX %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERSIX %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERSIX %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDERSIX %>CiId" name="ciCiIds" type="hidden"/>
	  					    </td>
	  					    </tr>
	  					    <tr>
	  					    <td width="160"><lable id="<%=Contstants.VENDERSET.VENDERSEVEN %>Lable"></lable></td>
	  						<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDERSEVEN %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERSEVEN %>Name" name="ciNames" type="hidden"/>
						   		  <input id="<%=Contstants.VENDERSET.VENDERSEVEN %>CiId" name="ciCiIds" type="hidden"/>
						   	</td>
						   	</tr>
						   	<tr>
						   	<td width="90"><lable id="<%=Contstants.VENDERSET.VENDEREIGHT %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.VENDERSET.VENDEREIGHT %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDEREIGHT %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.VENDERSET.VENDEREIGHT %>CiId" name="ciCiIds" type="hidden"/>
	  					    </td>
		  					
	                    </tr>
	             </table>
	              <br/>
	        </fieldset>
		</div>
	</form>
</div>