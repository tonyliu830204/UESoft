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
 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchParameter.action?ciType=<%=Contstants.OPERATIONALCONTROL.REBATE %>',
 	    dataType:'json',
 	    success : function(r) {
			if (r != null) {
				for (var i = 0; i < r.rows.length; i++) {
					var obj = r.rows[i];
					$('#'+obj.ciName+'Name').val(obj.ciName);
					$('#'+obj.ciName+'Lable').html(obj.ciLable);
					$('#'+obj.ciName+'CiId').val(obj.ciId);
					if(obj.ciPutType == '3'){
					   $('#'+obj.ciName+'Value').val(obj.ciName);
					}
		        }
		        initOneFrame();

		        $("#<%=Contstants.REBATE.DISCOUNTA %>add").click(function() {
			         var note = $('#<%=Contstants.REBATE.DISCOUNTA %>Value');
				     var n = note.val();
				     var num = parseInt(n) + 1;
					 note.val(num);
				});
		        $("#<%=Contstants.REBATE.DISCOUNTA %>jian").click(function() {
		        	  var note = $('#<%=Contstants.REBATE.DISCOUNTA %>Value');
				      var n = note.val();
				      var num = parseInt(n) - 1;
				      if (num == 0) {
					      alert("不能为0!");
					      return
					  }
					  note.val(num);
				});

		        $("#<%=Contstants.REBATE.DISCOUNTB %>add").click(function() {
		            var note = $('#<%=Contstants.REBATE.DISCOUNTB %>Value');
					var n = note.val();
					var num = parseInt(n) + 1;
					note.val(num);
				});
		        $("#<%=Contstants.REBATE.DISCOUNTB %>jian").click(function() {
		        	    var note = $('#<%=Contstants.REBATE.DISCOUNTB %>Value');
						var n = note.val();
						var num = parseInt(n) - 1;
						if (num == 0) {
							alert("不能为0!");
							return
						}
						note.val(num);
				});

		        $("#<%=Contstants.REBATE.DISCOUNTC %>add").click(function() {
		            var note = $('#<%=Contstants.REBATE.DISCOUNTC %>Value');
					var n = note.val();
					var num = parseInt(n) + 1;
					note.val(num);
				});
		        $("#<%=Contstants.REBATE.DISCOUNTC %>jian").click(function() {
		        	    var note = $('#<%=Contstants.REBATE.DISCOUNTC %>Value');
						var n = note.val();
						var num = parseInt(n) - 1;
						if (num == 0) {
							alert("不能为0!");
							return
						}
						note.val(num);
				});

		        $("#<%=Contstants.REBATE.DISCOUNTD %>add").click(function() {
		            var note = $('#<%=Contstants.REBATE.DISCOUNTD %>Value');
					var n = note.val();
					var num = parseInt(n) + 1;
					note.val(num);
				});
		        $("#<%=Contstants.REBATE.DISCOUNTD %>jian").click(function() {
		        	    var note = $('#<%=Contstants.REBATE.DISCOUNTD %>Value');
						var n = note.val();
						var num = parseInt(n) - 1;
						if (num == 0) {
							alert("不能为0!");
							return
						}
						note.val(num);
				});

		        $("#<%=Contstants.REBATE.DISCOUNTE %>add").click(function() {
		            var note = $('#<%=Contstants.REBATE.DISCOUNTE %>Value');
					var n = note.val();
					var num = parseInt(n) + 1;
					note.val(num);
				});
		        $("#<%=Contstants.REBATE.DISCOUNTE %>jian").click(function() {
		        	    var note = $('#<%=Contstants.REBATE.DISCOUNTE %>Value');
						var n = note.val();
						var num = parseInt(n) - 1;
						if (num == 0) {
							alert("不能为0!");
							return
						}
						note.val(num);
				});
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
	 	    url : '${pageContext.request.contextPath}/controlRoleAction!searchWorkParameter.action?employee='+n.id+'&ciType='+'<%=Contstants.OPERATIONALCONTROL.REBATE %>',
	 	    dataType:'json',
	 	    success : function(r) {
				if (r != null) {
					for (var i = 0; i < r.rows.length; i++) {
						var obj = r.rows[i];
						if(obj.ciName == "<%=Contstants.REBATE.DISCOUNTF %>"
						){
							$('#'+obj.ciName+'Value').combobox('setValue', obj.ciValue);
						}else if(obj.ciName == "<%=Contstants.REBATE.DISCOUNTG %>" 
							 || obj.ciName == "<%=Contstants.REBATE.DISCOUNTH %>" 
							 || obj.ciName == "<%=Contstants.REBATE.DISCOUNTI %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTJ %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTK %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTL %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTM %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTN %>"
						     || obj.ciName == "<%=Contstants.REBATE.DISCOUNTO %>"
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
<style>
	.button {
		list-style: none;
		width: 15px;
		height: 22px
	}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="parameterOne7Form">
	    <div data-options="region:'center',border:false" style="background:#eee;">
			<fieldset >
	             <legend>6.折扣及其他</legend>
	             <table  style="background:#eee;">
	                <tr> 
	                    <td>
	                        <table>
	                              <tr>
	                                   <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTA %>Lable"></lable></td>
			  						   <td>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTA %>jian" value="-" />
			  						      <input id="<%=Contstants.REBATE.DISCOUNTA %>Value" name="ciValues" value="0" type="text" class="easyui-validatebox" style="width:140px;" readonly="readonly"/>
								   		  <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTA %>add" value="+" />
								   		  <input id="<%=Contstants.REBATE.DISCOUNTA %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTA %>CiId" name="ciCiIds" type="hidden"/>
								   	   </td>
			  						   <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTB %>Lable"></lable></td>
			  						   <td>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTB %>jian" value="-" />
			  						      <input id="<%=Contstants.REBATE.DISCOUNTB %>Value" name="ciValues" value="0" type="text" class="easyui-validatebox" style="width:140px;" readonly="readonly"/>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTB %>add" value="+" />
								   		  <input id="<%=Contstants.REBATE.DISCOUNTB %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTB %>CiId" name="ciCiIds" type="hidden"/>
								   	   </td>
			  						   <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTC %>Lable"></lable></td>
			  						   <td>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTC %>jian" value="-" />
			  						      <input id="<%=Contstants.REBATE.DISCOUNTC %>Value" name="ciValues" value="0" type="text" class="easyui-validatebox" style="width:140px;" readonly="readonly"/>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTC %>add" value="+" />
								   		  <input id="<%=Contstants.REBATE.DISCOUNTC %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTC %>CiId" name="ciCiIds" type="hidden"/>
								   	   </td>
	                              </tr>
				                  <tr> 
				                      <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTD %>Lable"></lable></td>
			  						  <td>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTD %>jian" value="-" />
			  						      <input id="<%=Contstants.REBATE.DISCOUNTD %>Value" name="ciValues" value="0" type="text" class="easyui-validatebox" style="width:140px;" readonly="readonly"/>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTD %>add" value="+" />
								   		  <input id="<%=Contstants.REBATE.DISCOUNTD %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTD %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
			  						  <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTE %>Lable"></lable></td>
			  						  <td>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTE %>jian" value="-" />
			  						      <input id="<%=Contstants.REBATE.DISCOUNTE %>Value" name="ciValues" value="0" type="text" class="easyui-validatebox" style="width:140px;" readonly="readonly"/>
			  						      <input type="button" class="button" id="<%=Contstants.REBATE.DISCOUNTE %>add" value="+" />
								   		  <input id="<%=Contstants.REBATE.DISCOUNTE %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTE %>CiId" name="ciCiIds" type="hidden"/>
								   	  </td>
			  						  <td width="100"><lable id="<%=Contstants.REBATE.DISCOUNTF %>Lable"></lable></td>
			  						  <td>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTF %>Value" name="ciValues" style="width:140px;" class="easyui-combobox"
								   		   data-options="valueField:'id',textField:'text',panelHeight:75,url:'${pageContext.request.contextPath}/frtOptionsAction!findBaseListData.action\?key=<%=Contstants.HELPCOST.helpCost %>'"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTF %>Name" name="ciNames" type="hidden"/>
								   		  <input id="<%=Contstants.REBATE.DISCOUNTF %>CiId" name="ciCiIds" type="hidden"/>
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
			  						      <input id="<%=Contstants.REBATE.DISCOUNTG %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTG %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTG %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTG %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTH %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTH %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTH %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTH %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTI %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTI %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTI %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTI %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
				                </tr>
				                <tr> 
				                      <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTJ %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTJ %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTJ %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTJ %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTK %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTK %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTK %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTK %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTL %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTL %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTL %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTL %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
				                </tr>
				                <tr> 
				                      <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTM %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTM %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTM %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTM %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTN %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTN %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTN %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTN %>CiId" name="ciCheckCiIds" type="hidden"/>
			  						  </td>
			  						  <td width="150">
			  						      <input id="<%=Contstants.REBATE.DISCOUNTO %>Value" name="ciCheckValues" type="checkbox" style="width:20px;"><lable id="<%=Contstants.REBATE.DISCOUNTO %>Lable"></lable>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTO %>Name" name="ciCheckNames" type="hidden"/>
			  						      <input id="<%=Contstants.REBATE.DISCOUNTO %>CiId" name="ciCheckCiIds" type="hidden"/>
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