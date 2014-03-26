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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.ARCHIVESSE %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if( obj.ciName == "<%=Contstants.ARCHIVESSE.DEFAULTAREA %>" ){
						 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
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
	<form id="parameterOne5Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>5.客户档案及车辆档案参数设置</legend>
	             <table  style="background:#eee;">
	                  <tr>
	                        <td width="160"><lable id="<%=Contstants.ARCHIVESSE.DEFAULTAREA %>Lable"></lable></td>
	  						<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.DEFAULTAREA %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
						   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtCustomAction!findAllCustomArea.action'"/>
						   		  <input id="<%=Contstants.ARCHIVESSE.DEFAULTAREA %>Name" name="ciNames" type="hidden"/>
						   		  <input id="<%=Contstants.ARCHIVESSE.DEFAULTAREA %>CiId" name="ciCiIds" type="hidden"/>
						   	</td>
						   	<td width="90"><lable id="<%=Contstants.ARCHIVESSE.MAINTAINDAY %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.MAINTAINDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MAINTAINDAY %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MAINTAINDAY %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
	                  </tr>
	                  <tr>
		  					<td width="105"><lable id="<%=Contstants.ARCHIVESSE.INSURANCEWARNDAY %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.INSURANCEWARNDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.INSURANCEWARNDAY %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.INSURANCEWARNDAY %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
	                  </tr>
	                  <tr>
		  					<td width="160"><lable id="<%=Contstants.ARCHIVESSE.FIRSTKILOMETRE %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTKILOMETRE %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTKILOMETRE %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTKILOMETRE %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
	                        <td width="105"><lable id="<%=Contstants.ARCHIVESSE.FIRSTWARNDAY %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTWARNDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTWARNDAY %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.FIRSTWARNDAY %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
		  				</tr>
  					    <tr>
 					        <td width="160"><lable id="<%=Contstants.ARCHIVESSE.MINAMBITDAY %>Lable"></lable></td>
		  					<td>
	  						      <input id="<%=Contstants.ARCHIVESSE.MINAMBITDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MINAMBITDAY %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MINAMBITDAY %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
		  					<td width="150">
	  						      ~<input id="<%=Contstants.ARCHIVESSE.MAXAMBITDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MAXAMBITDAY %>Name" name="ciNames" type="hidden"/>
	  						      <input id="<%=Contstants.ARCHIVESSE.MAXAMBITDAY %>CiId" name="ciCiIds" type="hidden"/>
		  					</td>
		  					<td width="60"><lable id="<%=Contstants.ARCHIVESSE.MAXAMBITDAY %>Lable"></lable></td>
	                    </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>