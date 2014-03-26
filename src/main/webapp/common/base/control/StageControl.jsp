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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.STAGE %>',
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.STAGE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.STAGE.THESTAGEA %>" 
							 || obj.ciName == "<%=Contstants.STAGE.THESTAGEB %>" 
							 || obj.ciName == "<%=Contstants.STAGE.THESTAGEC %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGED %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEE %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEF %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEG %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEH %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEI %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEJ %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEK %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEL %>"
						     || obj.ciName == "<%=Contstants.STAGE.THESTAGEM %>"
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
	<form id="parameterOne6Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>5.前台管理</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEA %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEA %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEB %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEB %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEB %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEB %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEC %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEC %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGED %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGED %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGED %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGED %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEE %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEF %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEF %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEF %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEF %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEG %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEG %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEG %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEG %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEH %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEH %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEH %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEH %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEI %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEI %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEI %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEI %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEJ %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEJ %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEJ %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEJ %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEK %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEK %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEK %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEK %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEL %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEL %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEL %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEL %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STAGE.THESTAGEM %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STAGE.THESTAGEM %>Lable"></lable>
  						      <input id="<%=Contstants.STAGE.THESTAGEM %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STAGE.THESTAGEM %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						  </td>
  						  <td width="150">
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>