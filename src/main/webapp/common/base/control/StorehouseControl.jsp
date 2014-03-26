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
	
	var t = $("#employee").combotree('tree');
	var n = t.tree('getSelected');
	if(n != null){
		$.ajax({
	 	    type:'POST',
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?employee='+n.id+'&ciType=1',
	 	    dataType:'json',
	 	    success : function(r) {
	 			
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
	<form id="parameterOne1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>1.业务员可操作的仓库设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table >
			 					 <tr> 
			  						  <td width="30" align="left">仓别</td>
			  						  <td align="left">
			  						        <input class="easyui-combobox"
			  						        id="storeHouse" 
											name="storeHouse"
											data-options="
													url:'${pageContext.request.contextPath}/StMoveStoreHouseAction!loadBasStorehouse.action',
													valueField:'id',
													textField:'text',
													multiple:true,
													panelHeight:'auto'
											"
											style="width:350px;"/>
								   	  </td>
			  				     </tr>
							 </table>
	                    </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>