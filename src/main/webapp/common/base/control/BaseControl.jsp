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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.BASE %>',
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.BASE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.BASE.BASEE %>" 
							 || obj.ciName == "<%=Contstants.BASE.BASEF %>" 
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
	<form id="parameterOne4Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>3.基础资料</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                      <td width="150">
  						      人事权限
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.BASE.BASEA %>Value" name="ciCheckValues" type="radio" style="width:20px;"><lable id="<%=Contstants.BASE.BASEA %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASEA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASEA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                      <td width="150">
  						      <input id="<%=Contstants.BASE.BASEB %>Value" name="ciCheckValues" type="radio" style="width:20px;"><lable id="<%=Contstants.BASE.BASEB %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASEB %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASEB %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.BASE.BASEC %>Value" name="ciCheckValues" type="radio" style="width:20px;"><lable id="<%=Contstants.BASE.BASEC %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASEC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASEC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.BASE.BASED %>Value" name="ciCheckValues" type="radio" style="width:20px;"><lable id="<%=Contstants.BASE.BASED %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASED %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASED %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
  						  <td colspan="5">
  						      <input id="<%=Contstants.BASE.BASEE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.BASE.BASEE %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASEE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASEE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
  						  <td colspan="5">
  						      <input id="<%=Contstants.BASE.BASEF %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.BASE.BASEF %>Lable"></lable>
  						      <input id="<%=Contstants.BASE.BASEF %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.BASE.BASEF %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>