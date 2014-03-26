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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.INDEMNITYS %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.INDEMNITYS.CLAIMMANUCODE %>"){
						$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.INDEMNITYS.CLAIMENABLE %>"){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else{
					    $('#'+obj.ciName+'Value').numberbox('setValue', obj.ciValue);
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
	<form id="parameterTwo1Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>6.索赔管理设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table >
			 					 <tr> 
				                    <td width="80"><lable id="<%=Contstants.INDEMNITYS.CLAIMTIMEPRICE %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMTIMEPRICE %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:140px;"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMTIMEPRICE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMTIMEPRICE %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="80"><lable id="<%=Contstants.INDEMNITYS.CLAIMMANGERRATE %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANGERRATE %>Value" name="ciValues" type="text" class="easyui-numberbox" data-options="precision:2" style="width:140px;"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANGERRATE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANGERRATE %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                                <td width="80"><lable id="<%=Contstants.INDEMNITYS.CLAIMMANUCODE %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANUCODE %>Value" name="ciValues"  style="width:400px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:80,url:'${pageContext.request.contextPath}/frtOptionsAction!findAllClaimManufacturers.action'"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANUCODE %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMMANUCODE %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr> 
							 </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                         <table>
	                             <tr>
								   	<td width="80">
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMENABLE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.INDEMNITYS.CLAIMENABLE %>Lable" style="width:190px;"></lable>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMENABLE %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.INDEMNITYS.CLAIMENABLE %>CiId" name="ciCheckCiIds" type="hidden"/>
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