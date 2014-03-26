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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.STOREHOUSE %>',
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.STOREHOUSE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.STOREHOUSE.STOREHOUSEB %>"){
							 $('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
						}else if(obj.ciName == "<%=Contstants.STOREHOUSE.STOREHOUSEA %>" 
							 || obj.ciName == "<%=Contstants.STOREHOUSE.STOREHOUSEC %>" 
							 || obj.ciName == "<%=Contstants.STOREHOUSE.STOREHOUSED %>"
						     || obj.ciName == "<%=Contstants.STOREHOUSE.STOREHOUSEE %>"
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
	<form id="parameterOne5Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>4.仓库管理</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEA %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STOREHOUSE.STOREHOUSEA %>Lable"></lable>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEA %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEA %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="100">
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEC %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STOREHOUSE.STOREHOUSEC %>Lable"></lable>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEC %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEC %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="150">
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSED %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STOREHOUSE.STOREHOUSED %>Lable"></lable>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSED %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSED %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
	                </tr>
	                <tr> 
	                      <td width="150">
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.STOREHOUSE.STOREHOUSEE %>Lable"></lable>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						  </td>
  						  <td width="100"><lable id="<%=Contstants.STOREHOUSE.STOREHOUSEB %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.STOREHOUSE.STOREHOUSEB %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
					   		   data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.OUTSTOCKPRICE.outstockprice %>'"/>
					   		  <input id="<%=Contstants.STOREHOUSE.STOREHOUSEB %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.STOREHOUSE.STOREHOUSEB %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
  						  <td width="150">
  						      
  						  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>