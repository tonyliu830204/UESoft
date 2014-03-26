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
		url :"${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.EVALUATESE %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if( obj.ciName == "<%=Contstants.EVALUATESE.BILLPRICTTYPE %>" 
						|| obj.ciName == "<%=Contstants.EVALUATESE.CHANGECLAIMNATURE %>" 
						|| obj.ciName == "<%=Contstants.EVALUATESE.CHANGECOLLECTNATURE %>" 
					){
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
setTimeout("LoadThreeOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterThree4Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>13.预约估价单参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
					   	 <td width="150"><lable id="<%=Contstants.EVALUATESE.BILLPRICTTYPE %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.EVALUATESE.BILLPRICTTYPE %>Value" name="ciValues" style="width:120px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,
					   		 		  data:[{id:'servicePrice',text:'维修价格'},{id:'claimsPrice',text:'索赔价格'},{id:'sellPrice',text:'销售价格'}]"/>
					   		  <input id="<%=Contstants.EVALUATESE.BILLPRICTTYPE %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.EVALUATESE.BILLPRICTTYPE %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
					   	  <td width="150"><lable id="<%=Contstants.EVALUATESE.CHANGECLAIMNATURE %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.EVALUATESE.CHANGECLAIMNATURE %>Value" name="ciValues" style="width:120px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimsType.action'"/>
					   		  <input id="<%=Contstants.EVALUATESE.CHANGECLAIMNATURE %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.EVALUATESE.CHANGECLAIMNATURE %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
					   	  
					   	  <td width="150"><lable id="<%=Contstants.EVALUATESE.CHANGECOLLECTNATURE %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.EVALUATESE.CHANGECOLLECTNATURE %>Value" name="ciValues" style="width:120px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findAllRepairType.action'"/>
					   		  <input id="<%=Contstants.EVALUATESE.CHANGECOLLECTNATURE %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.EVALUATESE.CHANGECOLLECTNATURE %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
					   	  	 <td width="150"><lable id="<%=Contstants.EVALUATESE.RESEVATIONTIME %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.EVALUATESE.RESEVATIONTIME %>Value" class="easyui-numberbox" name="ciValues" style="width:120px;"/>
					   		  <input id="<%=Contstants.EVALUATESE.RESEVATIONTIME %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.EVALUATESE.RESEVATIONTIME %>CiId" name="ciCiIds" type="hidden"/><lable id="<%=Contstants.EVALUATESE.RESERVATIONTYPE %>Lable"></lable>
					   	  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>