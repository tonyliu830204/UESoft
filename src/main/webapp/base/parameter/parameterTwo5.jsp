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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.FINANCEMANAGE %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.FINANCEMANAGE.WHETHERTAX %>"){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
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
	<form id="parameterTwo5Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>财务管理</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td  width="220">
  						      <input id="<%=Contstants.FINANCEMANAGE.WHETHERTAX %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.FINANCEMANAGE.WHETHERTAX %>Lable"></lable>
  						      <input id="<%=Contstants.FINANCEMANAGE.WHETHERTAX %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.FINANCEMANAGE.WHETHERTAX %>CiId" name="ciCheckCiIds" type="hidden"/>
  						</td>
	  				</tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>