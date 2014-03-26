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
		url : "${pageContext.request.contextPath}/basCompanyInformationSetAction!searchParameterOne.action?t="+Math.random()+"&ciType="+"<%=Contstants.PARAMETER_SET.STGCARPARA %>",
		success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					if(obj.ciName == "<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>"){
					   	setComboboxValues(''+obj.ciName+'Value',obj.ciValue);
					}else if(obj.ciName == "<%=Contstants.STGCARPARA.DEFAULTSERTYPE %>"
					    || obj.ciName == "<%=Contstants.STGCARPARA.DEFAULTRCPTBRANCH %>"
					){
						$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
					}else if( obj.ciName == "<%=Contstants.STGCARPARA.AUOTSHOWSERCAR %>" 
						|| obj.ciName == "<%=Contstants.STGCARPARA.SERPSTANDARDTIME %>" 
						|| obj.ciName == "<%=Contstants.STGCARPARA.DISTANCELOSSORI %>" 
					){
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
setTimeout("LoadThreeOk();", 1000);
//-->
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterThree2Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
	        <fieldset >
	             <legend>11.前台接车参数设置</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                         <table >
			 					 <tr> 
				                    <td width="170"><lable id="<%=Contstants.STGCARPARA.WORKORDERFORMAT %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.STGCARPARA.WORKORDERFORMAT %>Value" name="ciValues" type="text" class="easyui-numberbox"   style="width:80px;"/>
			  						      <input id="<%=Contstants.STGCARPARA.WORKORDERFORMAT %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.STGCARPARA.WORKORDERFORMAT %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
				  					<td width="170"><lable id="<%=Contstants.STGCARPARA.FAINSHTIMES %>Lable"></lable></td>
				  					<td>
			  						      <input id="<%=Contstants.STGCARPARA.FAINSHTIMES %>Value" name="ciValues" type="text" class="easyui-numberbox" style="width:80px;"/>
			  						      <input id="<%=Contstants.STGCARPARA.FAINSHTIMES %>Name" name="ciNames" type="hidden"/>
			  						      <input id="<%=Contstants.STGCARPARA.FAINSHTIMES %>CiId" name="ciCiIds" type="hidden"/>
				  					</td>
	                             </tr> 
							 </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                         <table>
	                             <tr>
								   	<td width="170"><lable id="<%=Contstants.STGCARPARA.DEFAULTSERTYPE %>Lable"></lable></td>
			  						<td>
			  						      <input id="<%=Contstants.STGCARPARA.DEFAULTSERTYPE %>Value" name="ciValues" style="width:80px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findAllReptype.action'"/>
								   		  <input id="<%=Contstants.STGCARPARA.DEFAULTSERTYPE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.STGCARPARA.DEFAULTSERTYPE %>CiId" name="ciCiIds" type="hidden"/>
								   	</td>
	                             </tr>
							 </table>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                         <table>
	                             <tr>
			  						  <td width="230">
			  						      <input id="<%=Contstants.STGCARPARA.AUOTSHOWSERCAR %>Value" name="ciCheckValues"   type="checkbox" style="width:20px;">
			  						      <lable id="<%=Contstants.STGCARPARA.AUOTSHOWSERCAR %>Lable"></lable>
			  						      <input id="<%=Contstants.STGCARPARA.AUOTSHOWSERCAR %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.STGCARPARA.AUOTSHOWSERCAR %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
	                             </tr>
	                             <tr>
			  						  <td width="230">
			  						      <input id="<%=Contstants.STGCARPARA.SERPSTANDARDTIME %>Value" name="ciCheckValues"  type="checkbox" style="width:20px;">
			  						      <lable id="<%=Contstants.STGCARPARA.SERPSTANDARDTIME %>Lable"></lable>
			  						      <input id="<%=Contstants.STGCARPARA.SERPSTANDARDTIME %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.STGCARPARA.SERPSTANDARDTIME %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
	                                  <td width="230">
			  						      <input id="<%=Contstants.STGCARPARA.DISTANCELOSSORI %>Value" name="ciCheckValues"  type="checkbox" style="width:20px;">
			  						      <lable id="<%=Contstants.STGCARPARA.DISTANCELOSSORI %>Lable"></lable>
			  						      <input id="<%=Contstants.STGCARPARA.DISTANCELOSSORI %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.STGCARPARA.DISTANCELOSSORI %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
	                             </tr>
	                             <tr>
			  						  <!-- 接车提醒参数 -->
			  						 <td width="170"><lable id="<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>Lable"></lable></td>
			  						 <td>
			  						      <input id="<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>Value" style="width:190px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,separator:',',multiple:true, mode : 'remote',
								   		   onSelect:function(record){
								   		   		document.getElementById('tempId').value=$('#<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>Value').combobox('getValues');
								   		   },
								   		   url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action?key=<%=Contstants.FRTRECEPTIONCLEW.FRTRECEPTIONCLEWKEY %>'"/>
								   		  <input id="tempId" name="ciValues" type="hidden"/>
								   		  <input id="<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.STGCARPARA.FRTRECEPTIONCLEW %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
								   	  <!-- 接车分部参数 -->
								   	  <td width="170"><lable id="<%=Contstants.STGCARPARA.DEFAULTRCPTBRANCH %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.STGCARPARA.DEFAULTRCPTBRANCH %>Value" name="ciValues" style="width:190px;" class="easyui-combobox"
								   		   data-options="editable : false,valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.RCPTBRANCH.RCPTBRANCHKEY %>'"/>
								   		  <input id="<%=Contstants.STGCARPARA.DEFAULTRCPTBRANCH %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.STGCARPARA.DEFAULTRCPTBRANCH %>CiId" name="ciCiIds" type="hidden"/>
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