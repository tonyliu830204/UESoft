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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.MSN %>',
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType=<%=Contstants.OPERATIONALCONTROL.MSN %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.MSN.MSNA %>" 
							 || obj.ciName == "<%=Contstants.MSN.MSNB %>" 
							 || obj.ciName == "<%=Contstants.MSN.MSNC %>" 
							 || obj.ciName == "<%=Contstants.MSN.MSND %>"
						     || obj.ciName == "<%=Contstants.MSN.MSNE %>" 
						     || obj.ciName == "<%=Contstants.MSN.MSNF %>" 
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
	<form id="parameterOne2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>2.短信发送</legend>
	             <table  style="background:#eee;" align="left">
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.MSN.MSNA %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSNA %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSNA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSNA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.MSN.MSNB %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSNB %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSNB %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSNB %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.MSN.MSNC %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSNC %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSNC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSNC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
  						  <td width="150">
  						      <input id="<%=Contstants.MSN.MSND %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSND %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSND %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSND %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                      <td width="150">
  						      <input id="<%=Contstants.MSN.MSNE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSNE %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSNE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSNE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.MSN.MSNF %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.MSN.MSNF %>Lable"></lable>
  						      <input id="<%=Contstants.MSN.MSNF %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.MSN.MSNF %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>