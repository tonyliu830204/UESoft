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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.EXAMINE %>',
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.EXAMINE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.EXAMINE.EXAMINEA %>" 
							 || obj.ciName == "<%=Contstants.EXAMINE.EXAMINEB %>" 
							 || obj.ciName == "<%=Contstants.EXAMINE.EXAMINEC %>"
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
	<form id="parameterOne3Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>2.申请审核</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.EXAMINE.EXAMINEA %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.EXAMINE.EXAMINEA %>Lable"></lable>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.EXAMINE.EXAMINEB %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.EXAMINE.EXAMINEB %>Lable"></lable>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEB %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEB %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.EXAMINE.EXAMINEC %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.EXAMINE.EXAMINEC %>Lable"></lable>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.EXAMINE.EXAMINEC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>