<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript">
<!--
function LoadThreeOk() {
	if (document.readyState == "complete") {
		initThreeFrame();
	} else {
		setTimeout("LoadThreeOk();", 1000);
	}
}

var initThreeFrame = function() {
	loadThreeData();
}

var loadThreeData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url :"${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.OTHERPARAMETER %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.OTHERPARAMETER.FIRSTMAINTAIN %>"
						|| obj.ciName == "<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>"
						|| obj.ciName == "<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>"
						|| obj.ciName == "<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>"){
						$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if( obj.ciName == "<%=Contstants.OTHERPARAMETER.WORKSHOPVALIDATE %>"){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
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
setTimeout("LoadThreeOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterThree5Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>13.其他参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                      <td width="110"><lable id="<%=Contstants.OTHERPARAMETER.WORKSHOPVALIDATE %>Lable"></lable></td>
  						  <td>
					   		   <input id="<%=Contstants.OTHERPARAMETER.WORKSHOPVALIDATE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;">
  						      <input id="<%=Contstants.OTHERPARAMETER.WORKSHOPVALIDATE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.OTHERPARAMETER.WORKSHOPVALIDATE %>CiId" name="ciCheckCiIds" type="hidden"/>
					   	  </td>
					   	  <td width="180"><lable id="<%=Contstants.OTHERPARAMETER.BALANCETIMESECT %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANCETIMESECT %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANCETIMESECT %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANCETIMESECT %>CiId" name="ciCiIds" type="hidden"/>
  						  </td>
  						  <td width="180"><lable id="<%=Contstants.OTHERPARAMETER.BALANACCOUNTSECT %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANACCOUNTSECT %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANACCOUNTSECT %>Name" name="ciNames" type="hidden"/>
  						      <input id="<%=Contstants.OTHERPARAMETER.BALANACCOUNTSECT %>CiId" name="ciCiIds" type="hidden"/>
  						  </td>
  						  <td width="150" ><font style="color:red;"><lable id="<%=Contstants.OTHERPARAMETER.FIRSTMAINTAIN %>Lable"></lable></font></td>
  						  <td>
  						      <input id="<%=Contstants.OTHERPARAMETER.FIRSTMAINTAIN %>Value" name="ciValues" style="width:120px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findAllReptype.action'"/>
					   		  <input id="<%=Contstants.OTHERPARAMETER.FIRSTMAINTAIN %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.OTHERPARAMETER.FIRSTMAINTAIN %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
	                </tr>
	                <tr> 
         				   <td width="100"><lable id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>Lable"></lable></td>
						  <td>
							  <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
				   		         data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>'"/>
				   	          <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>Name" name="ciNames" type="hidden"/>
				   		     <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSBRAND %>CiId" name="ciCiIds" type="hidden"/>
				          </td>
				          <td width="100"><lable id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>Lable"></lable></td>
						  <td>
							  <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
				   		         data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>'"/>
				   	          <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>Name" name="ciNames" type="hidden"/>
				   		     <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSTYPE %>CiId" name="ciCiIds" type="hidden"/>
				          </td>
				          <td width="100"><lable id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>Lable"></lable></td>
						  <td>
							  <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
				   		         data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>'"/>
				   	          <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>Name" name="ciNames" type="hidden"/>
				   		     <input id="<%=Contstants.OTHERPARAMETER.DEFAULTPARTSUNIT %>CiId" name="ciCiIds" type="hidden"/>
				          </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>