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
	loadOneData();
}

var loadOneData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.CLIENTANDREMIND %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.CLIENTANDREMIND.CLIENTPROPERTY%>" 
						
				    ){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.CLIENTANDREMIND.CLIENTTELL %>" 
							|| obj.ciName == "<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDATA %>" 
							|| obj.ciName == "<%=Contstants.CLIENTANDREMIND.CLIENTTRACKMANG %>" 
							|| obj.ciName == "<%=Contstants.CLIENTANDREMIND.LASTDAY %>" 
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
	<form id="parameterOne2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    <br>
	        <fieldset >
	             <legend>2.客户管理与提醒设置</legend>
	             <br>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                       <table>
	                             <tr>
				                    <td width="150"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTPROPERTY %>Lable"></lable></td>
				  					<td>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTPROPERTY %>Value" name="ciValues" type="text" class="easyui-combobox" style="width:140px;"
			 						       data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomArea.action'"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTPROPERTY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTPROPERTY %>CiId" name="ciCiIds" type="hidden"/>
			  						     
				  					</td>
				  					<td width="150"><lable id="<%=Contstants.CLIENTANDREMIND.RESERVEDAY %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.RESERVEDAY %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.RESERVEDAY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.RESERVEDAY %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="120"><lable id="<%=Contstants.CLIENTANDREMIND.BIRTHDAYREMIND %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.BIRTHDAYREMIND%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.BIRTHDAYREMIND %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.BIRTHDAYREMIND %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="120"><lable id="<%=Contstants.CLIENTANDREMIND.INSUREREMIND%>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.INSUREREMIND%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.INSUREREMIND %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.INSUREREMIND %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr>
	                              <tr>
				                    <td width="150"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTORDERDAY %>Lable"></lable></td>
				  					<td>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTORDERDAY %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTORDERDAY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTORDERDAY %>CiId" name="ciCiIds" type="hidden"/>
			  						     
				  					</td>
				  					<td width="150"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDAY %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDAY %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDAY%>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDAY %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="120"><lable id="<%=Contstants.CLIENTANDREMIND.FIRSTDAY%>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.FIRSTDAY%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.FIRSTDAY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.CLIENTANDREMIND.FIRSTDAY %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr>
	                       </table>
	                    </td>
	  				</tr>
	  				<tr>
	  				    <td>
	                       <table>
	                             <tr>
				  					<td width="250">
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTELL %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTTELL %>Lable"></lable>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTELL %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTELL %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 						<td width="280">
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDATA%>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDATA%>Lable"></lable>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDATA%>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKDATA%>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 						<td width="255">
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKMANG %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKMANG %>Lable"></lable>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKMANG %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.CLIENTTRACKMANG %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 					
				  					<td width="190">
			 						      <input id="<%=Contstants.CLIENTANDREMIND.LASTDAY%>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CLIENTANDREMIND.LASTDAY %>Lable"></lable>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.LASTDAY%>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.CLIENTANDREMIND.LASTDAY %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						
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