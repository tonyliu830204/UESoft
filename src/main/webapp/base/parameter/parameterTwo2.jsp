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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.SCENESET %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.SCENESET.UPDATEREPAIRPRO %>" 
						|| obj.ciName == "<%=Contstants.SCENESET.SHOWREPAIRVEHIVLE %>"
					){
						$('#'+obj.ciName+'Value').val(obj.ciName);
						if(obj.ciValue == "checked"){
						    $('#'+obj.ciName+'Value').attr("checked",obj.ciValue);
						}
					}else{
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
setTimeout("LoadTwoOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterTwo2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>7.现场管理参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td  width="120">
  						      <input id="<%=Contstants.SCENESET.UPDATEREPAIRPRO %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SCENESET.UPDATEREPAIRPRO %>Lable"></lable>
  						      <input id="<%=Contstants.SCENESET.UPDATEREPAIRPRO %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.SCENESET.UPDATEREPAIRPRO %>CiId" name="ciCheckCiIds" type="hidden"/>
  						</td>
  						<td  width="200">
  						      <input id="<%=Contstants.SCENESET.SHOWREPAIRVEHIVLE %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.SCENESET.SHOWREPAIRVEHIVLE %>Lable"></lable>
  						      <input id="<%=Contstants.SCENESET.SHOWREPAIRVEHIVLE %>Name" name="ciCheckNames" type="hidden"/>
  						      <input id="<%=Contstants.SCENESET.SHOWREPAIRVEHIVLE %>CiId" name="ciCheckCiIds" type="hidden"/>
  						</td>
  					</tr>
  					<tr>
  						<td width="210"><lable id="<%=Contstants.SCENESET.CONTROLBALANCEWAY %>Lable"></lable></td>
  						<td>
  						      <input id="<%=Contstants.SCENESET.CONTROLBALANCEWAY %>Value" name="ciValues" style="width:190px;" class="easyui-combobox"
					   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=controlWay'"/>
					   		  <input id="<%=Contstants.SCENESET.CONTROLBALANCEWAY %>Name" name="ciNames" type="hidden"/>
					   		  <input id="<%=Contstants.SCENESET.CONTROLBALANCEWAY %>CiId" name="ciCiIds" type="hidden"/>
					   	</td>
	                </tr>
	             </table>
	        </fieldset>
		</div>
	</form>
</div>