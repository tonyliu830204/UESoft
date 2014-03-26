<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/parameter/color.js"></script>
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
	loadOneFunction();
	loadOneData();
}
var loadOneFunction = function(){
	$("#<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>Value").click(function(){
		colorreplace('<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>PANEL');
		colorreplace('<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>PANEL');
		coloropen('<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>Value', '<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>PANEL');
	});
	$("#<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>Value").click(function(){
		colorreplace('<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>PANEL');
		colorreplace('<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>PANEL');
		coloropen('<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>Value', '<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>PANEL');
	});
	$("#<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>Value").click(function(){
		colorreplace('<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>PANEL');
		colorreplace('<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>PANEL');
		coloropen('<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>Value', '<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>PANEL');
	});
}
var loadOneData = function(){
	$.ajax({
		type : 'post',
		dataType : 'json',
		ifModified : false, 
		cache : false,
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.PARTSTOCESEARCH %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.PARTSTOCESEARCH.SHOWZEROPART %>"){
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
	<form id="parameterOne2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>2.配件库存查询参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                       <table>
	                             <tr>
				                    <td width="100"><lable id="<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>Lable"></lable></td>
				  					<td>
			 						      <input id="<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>Value" name="ciValues" type="text" readonly="readonly" class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>CiId" name="ciCiIds" type="hidden"/>
			  						      <div id="<%=Contstants.PARTSTOCESEARCH.FLOORALARMCOLOR %>PANEL" style="position:absolute;z-index:999;display:none;"></div>
				  					</td>
				  					<td width="100"><lable id="<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>Value" name="ciValues" type="text"  readonly="readonly"  class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>CiId" name="ciCiIds" type="hidden"/>
			  						      <div id="<%=Contstants.PARTSTOCESEARCH.UPPERALARMCOLOR %>PANEL" style="position:absolute;z-index:999;display:none;"></div>
				  					</td>
				  					<td width="100"><lable id="<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>Value" name="ciValues" type="text"  readonly="readonly"  class="easyui-validatebox" style="width:140px;"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>CiId" name="ciCiIds" type="hidden"/>
			  						      <div id="<%=Contstants.PARTSTOCESEARCH.ZEROALARMCOLOR %>PANEL" style="position:absolute;z-index:999;display:none;"></div>
				  					</td>
	                             </tr>
	                       </table>
	                    </td>
	  				</tr>
	  				<tr>
	  				    <td>
	                       <table>
	                             <tr>
			 						<td width="150">
			 						      <input id="<%=Contstants.PARTSTOCESEARCH.SHOWZEROPART %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.PARTSTOCESEARCH.SHOWZEROPART %>Lable"></lable>
			 						      <input id="<%=Contstants.PARTSTOCESEARCH.SHOWZEROPART %>Name" name="ciCheckNames" type="hidden"/>
			 						      <input id="<%=Contstants.PARTSTOCESEARCH.SHOWZEROPART %>CiId" name="ciCheckCiIds" type="hidden"/>
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