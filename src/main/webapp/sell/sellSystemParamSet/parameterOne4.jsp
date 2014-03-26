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
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.NOTEIDSET %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.NOTEIDSET.NOTEGALLERY  %>" ){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
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
	<form id="parameterOne4Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    <br/>
	        <fieldset >
	             <legend>4.短信账号设置</legend>
	              <br/>
	             <table  style="background:#eee;">
	             
	                <tr> 
	                    <td>
	                         <table>
				                <tr>
			 						<td width="110"><lable id="<%=Contstants.NOTEIDSET.NOTEGALLERY %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEGALLERY %>Value" name="ciValues" class="easyui-combobox" style="width:150px;"/>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEGALLERY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEGALLERY %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					</tr>
				  					<tr>
				  					<td width="150"><lable id="<%=Contstants.NOTEIDSET.ACCOUNTNUMBER %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NOTEIDSET.ACCOUNTNUMBER %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:150px;"/>
			  						      <input id="<%=Contstants.NOTEIDSET.ACCOUNTNUMBER %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NOTEIDSET.ACCOUNTNUMBER %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					</tr>
				  					<tr>
				  					<td width="130"><lable id="<%=Contstants.NOTEIDSET.NOTEPASSWORD %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEPASSWORD  %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:150px;"/>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEPASSWORD  %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NOTEIDSET.NOTEPASSWORD  %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				                </tr>
				                <tr>
				  					<td width="110"><lable id="<%=Contstants.NOTEIDSET.TESTTELL%>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NOTEIDSET.TESTTELL %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:150px;"/>
			  						      <input id="<%=Contstants.NOTEIDSET.TESTTELL %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NOTEIDSET.TESTTELL %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					</tr>
				  					<tr>
				                    <td width="150"><lable id="<%=Contstants.NOTEIDSET.TELLNUM %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NOTEIDSET.TELLNUM %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:150px;"/>
			  						      <input id="<%=Contstants.NOTEIDSET.TELLNUM %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NOTEIDSET.TELLNUM %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				                   
				                </tr>
				                
							 </table>
	                    </td>
	                </tr>
	             </table>
	             <br/>
	             <span style="color: red;width: 180px;height: 80px;font-size:15;" >  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
	             			注：发送短信时请先上网，并保证上网流畅</span>
	             
	        </fieldset>
		</div>
	</form>
</div>