<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellSystemParamSet/color.js"></script>
<script type="text/javascript">

function LoadTwoOk() {
	if (document.readyState == "complete") {
		initTwoFrame();
	} else {
		setTimeout("LoadTwoOk();", 1000);
	}
}

var initTwoFrame = function() {
	loadTwoData();
}

var loadTwoData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.NUMANDPRINT%>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.INDEMNITYS.CLAIMENABLE %>" 
											
					){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else{
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
setTimeout("LoadTwoOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterTwo1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	     <br>
			<fieldset >
	             <legend>6.编号及打印设置</legend>
	             <br>
	             <br>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td> 
				                    <td width="150"><lable id="<%=Contstants.NUMANDPRINT.PRINTNUM %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUM%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUM %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUM%>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="130"><lable id="<%=Contstants.NUMANDPRINT.PRINTNUMBEGIN%>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUMBEGIN %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUMBEGIN %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.PRINTNUMBEGIN %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="150"><lable id="<%=Contstants.NUMANDPRINT.SELLPRINT %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.NUMANDPRINT.SELLPRINT%>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:190px;"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.SELLPRINT %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.NUMANDPRINT.SELLPRINT%>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr>
	         
	             </table>
	              <br>
	               <br>
	        </fieldset>
		</div>
	</form>
</div>