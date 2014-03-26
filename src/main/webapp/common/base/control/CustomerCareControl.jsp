<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript">
<!--
function LoadOneOk() {
	if (document.readyState == "complete") {
		init();
	} else {
		setTimeout("LoadOneOk();", 1000);
	}
}

function init() {
	$.ajax({
 	    type:'POST',
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.CUSTOMERCARE %>',
 	    dataType:'json',
 	    success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					$('#'+obj.ciName+'Name').val(obj.ciName);
					$('#'+obj.ciName+'Lable').html(obj.ciLable);
					$('#'+obj.ciName+'CiId').val(obj.ciId);
					$('#'+obj.ciName+'Value').val(obj.ciName);
		        }
		        initOneFrame();
			}
 		},
 		error : function (r){
 		   if(r.readyState == '0' && r.status == '0'){
 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
 		   }
 	    }
 	});
}

var initOneFrame = function() {
	
	var t = $("#employee").combotree('tree');
	var n = t.tree('getSelected');
	if(n != null){
		$.ajax({
	 	    type:'POST',
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.CUSTOMERCARE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREA %>" 
							 || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREB %>" 
							 || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREC %>"
						     || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCARED %>"
						     || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREE %>"
						     || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREF %>"
						     || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREG %>"
						     || obj.ciName == "<%=Contstants.CUSTOMERCARE.CUSTOMCAREH %>"
						){
							if(obj.ciValue == "checked"){
							    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
							}
						}else {
						     $('#'+obj.ciName+'Value').val(obj.ciValue);
						}
			        }
				}
	 		},
	 		error : function (r){
	 		   if(r.readyState == '0' && r.status == '0'){
	 			   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
	 		   }
	 	    }
	 	});
	}
}


setTimeout("LoadOneOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne8Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>7.折扣及其他</legend>
	             <table  style="background:#eee;">
                    <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREA %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREA %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREB %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREB %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREB %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREB %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREC %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREC %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCARED %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCARED %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCARED %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCARED %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREE %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREF %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREF %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREF %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREF %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREG %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREG %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREG %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREG %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREH %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREH %>Lable"></lable>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREH %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.CUSTOMERCARE.CUSTOMCAREH %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>