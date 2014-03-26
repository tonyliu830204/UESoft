<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/sell/sellSystemParamSet/color.js"></script>
<script type="text/javascript">

function LoadOneOk() {
	if (document.readyState == "complete") {
		initOneFrame();
	} else {
		setTimeout("LoadOneOk();", 1000);
	}
}

var initOneFrame = function() {
	loadOneData();
}

var loadOneData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "sellParamSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.SELLPARAMETER_SET.SELLANDWAREHOUSE %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					 if(obj.ciName == "<%=Contstants.SELLANDWAREHOUSE.CARSELLWAREHOUSE %>" 
						|| obj.ciName == "<%=Contstants.SELLANDWAREHOUSE.PIRCESELL%>"
						|| obj.ciName == "<%=Contstants.SELLANDWAREHOUSE.CARSELLDD%>"
						|| obj.ciName == "<%=Contstants.SELLANDWAREHOUSE.ZHUANWEIX%>"
					){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else {
					     $('#'+obj.ciName+'Value').val(obj.ciValue);
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
setTimeout("LoadOneOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne3Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	    <br/>
	        <fieldset >
	             <legend>3.车辆销售及出库设置</legend>
	             <br>
	             <table  style="background:#eee;">
	               <tr>
				  					<td width="170">
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLWAREHOUSE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLANDWAREHOUSE.CARSELLWAREHOUSE%>Lable"></lable>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLWAREHOUSE %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLWAREHOUSE %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 						<td width="160">
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.PIRCESELL%>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLANDWAREHOUSE.PIRCESELL%>Lable"></lable>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.PIRCESELL%>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.PIRCESELL%>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 						<td width="200">
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLDD %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLANDWAREHOUSE.CARSELLDD %>Lable"></lable>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLDD %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.CARSELLDD %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						</td>
			 					
				  					<td width="185">
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.ZHUANWEIX%>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SELLANDWAREHOUSE.ZHUANWEIX %>Lable"></lable>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.ZHUANWEIX%>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.SELLANDWAREHOUSE.ZHUANWEIX %>CiId" name="ciCheckCiIds" type="hidden"/>
			 						
			 						</td>
			 					</tr>
	              </table>
	              <br>
	        </fieldset>
		</div>
	</form>
</div>