<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.PASSWORDSE %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.PASSWORDSE.PWCOMPLEXITY %>"){
						$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else{
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
setTimeout("LoadTwoOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterTwo4Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>9.密码安全策略</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td width="80"><lable id="<%=Contstants.PASSWORDSE.PASSWORDLENGTH %>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLENGTH %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLENGTH %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLENGTH %>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  					<td width="90"><lable id="<%=Contstants.PASSWORDSE.PASSWORDLIMITDAY %>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITDAY %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITDAY %>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  					<td width="130"><lable id="<%=Contstants.PASSWORDSE.PASSWORDLIMITNUM %>Lable"></lable></td>
	  					<td>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITNUM %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITNUM %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.PASSWORDSE.PASSWORDLIMITNUM %>CiId" name="ciCiIds" type="hidden"/>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td width="80"><lable id="<%=Contstants.PASSWORDSE.PWCOMPLEXITY %>Lable"></lable></td>
  						<td>
  						      <input id="<%=Contstants.PASSWORDSE.PWCOMPLEXITY %>Value" name="ciValues" style="width:190px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=complexity'"/>
					   		  <input id="<%=Contstants.PASSWORDSE.PWCOMPLEXITY %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.PASSWORDSE.PWCOMPLEXITY %>CiId" name="ciCiIds" type="hidden"/>
					   	</td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>