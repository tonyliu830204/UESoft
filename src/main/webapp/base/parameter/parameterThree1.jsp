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
}

var loadThreeData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.JOINCARACC %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.JOINCARACC.MAINTAINCOURSE %>" 
						|| obj.ciName == "<%=Contstants.JOINCARACC.MAINTAINSPACEDAY %>" 
						|| obj.ciName == "<%=Contstants.JOINCARACC.NUMBERBIT %>"
				    ){
						$('#'+obj.ciName+'Value').numberbox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.JOINCARACC.WARNRECEIVABLE %>"
						|| obj.ciName == "<%=Contstants.JOINCARACC.BALMAKEUPODOWNNUM %>" 
						|| obj.ciName == "<%=Contstants.JOINCARACC.RECEIVABLECARRYBIT %>"
					){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else{
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
setTimeout("LoadThreeOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterThree1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>10.交车结算参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td colspan="2">
	                         <table>
			 					 <tr> 
				                    <td width="260"><lable id="<%=Contstants.JOINCARACC.NUMBERFORMAT %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERFORMAT %>Value" name="ciValues" type="text" class="easyui-validatebox" style="width:150px;"/>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERFORMAT %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERFORMAT %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr>
	                             <tr>
	                                <td width="260"><lable id="<%=Contstants.JOINCARACC.MAINTAINCOURSE %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINCOURSE %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:150px;"/>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINCOURSE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINCOURSE %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="170"><lable id="<%=Contstants.JOINCARACC.MAINTAINSPACEDAY %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINSPACEDAY %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:150px;"/>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINSPACEDAY %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.MAINTAINSPACEDAY %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr> 
							 </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2">
	                         <table>
	                             <tr>
			  						<td width="220">
			  						      <input id="<%=Contstants.JOINCARACC.WARNRECEIVABLE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.JOINCARACC.WARNRECEIVABLE %>Lable"></lable>
			  						      <input id="<%=Contstants.JOINCARACC.WARNRECEIVABLE %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.WARNRECEIVABLE %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						</td>
	                             </tr>
	                             <tr>
			  						<td width="230">
			  						      <input id="<%=Contstants.JOINCARACC.BALMAKEUPODOWNNUM %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.JOINCARACC.BALMAKEUPODOWNNUM %>Lable"></lable>
			  						      <input id="<%=Contstants.JOINCARACC.BALMAKEUPODOWNNUM %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.BALMAKEUPODOWNNUM %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERBIT %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:20px;"/><lable id="<%=Contstants.JOINCARACC.NUMBERBIT %>Lable"></lable>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERBIT %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.NUMBERBIT %>CiId" name="ciCiIds" type="hidden"/>
			  						</td>
			  							<td width="220">
			  						      <input id="<%=Contstants.JOINCARACC.RECEIVABLECARRYBIT %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.JOINCARACC.RECEIVABLECARRYBIT %>Lable"></lable>
			  						      <input id="<%=Contstants.JOINCARACC.RECEIVABLECARRYBIT %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.JOINCARACC.RECEIVABLECARRYBIT %>CiId" name="ciCheckCiIds" type="hidden"/>
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