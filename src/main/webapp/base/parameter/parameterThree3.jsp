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
	hearhCon();
}

var hearhCon = function(){
	$('#'+"<%=Contstants.NUMBERFRME.WOCKBILLSTATE %>").click(function(){
		var vilue = $('#'+'<%=Contstants.NUMBERFRME.WOCKBILLSTATE %>'+'Value').val();
		if(vilue != ""){
            
		}else{
			alert("请填写要变更的工单号");
		}
	});
}

var loadThreeData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.NUMBERFRME %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if( obj.ciName == "<%=Contstants.NUMBERFRME.RECEIPTFORMAT %>"){
						$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
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
	<form id="parameterThree3Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>12.单据编号格式参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	  					  <td width="120"><lable id="<%=Contstants.NUMBERFRME.RECEIPTFORMAT %>Lable"></lable></td>
  						  <td>
  						      <input id="<%=Contstants.NUMBERFRME.RECEIPTFORMAT %>Value" name="ciValues" style="width:120px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=receiptformat'"/>
					   		  <input id="<%=Contstants.NUMBERFRME.RECEIPTFORMAT %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.NUMBERFRME.RECEIPTFORMAT %>CiId" name="ciCiIds" type="hidden"/>
					   	  </td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>