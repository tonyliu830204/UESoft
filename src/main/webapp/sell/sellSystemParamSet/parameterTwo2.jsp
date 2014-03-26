<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellSystemParamSet/color.js"></script>
<script type="text/javascript">
<!--
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
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.PAWSAFE%>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.PAWSAFE.PAWCOMPLEXITY %>"){
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
setTimeout("LoadTwoOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterTwo2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    <br/>
	        <fieldset >
	             <legend>7.密码安全策略</legend>
	              <br/>
	             <table  style="background:#eee;">
	                <tr> 
	                 
	                    <td width=120"><lable id="<%=Contstants.PAWSAFE.PAWLENGTH %>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PAWSAFE.PAWLENGTH %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLENGTH %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLENGTH %>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  					<td width="90"><lable id="<%=Contstants.PAWSAFE.PAWLIMITDAY %>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITDAY %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITDAY %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITDAY %>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  					<td width="130"><lable id="<%=Contstants.PAWSAFE.PAWLIMITNUM%>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITNUM %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:140px;"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITNUM%>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PAWSAFE.PAWLIMITNUM%>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td width="120"><lable id="<%=Contstants.PAWSAFE.PAWCOMPLEXITY %>Lable"></lable></td>
  						<td>
  						      <input id="<%=Contstants.PAWSAFE.PAWCOMPLEXITY %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
					   		   data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=complexity'"/>
					   		  <input id="<%=Contstants.PAWSAFE.PAWCOMPLEXITY %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.PAWSAFE.PAWCOMPLEXITY %>CiId" name="ciCiIds" type="hidden"/>
					   	</td>
	                </tr>
	             </table>
	              <br/>
	        </fieldset>
		</div>
	</form>
</div>