<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>参数设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
$(function($) {

		$("#sava").bind("click", function(){
			save();
		});
	});
	
	var save = function() {
		if($('#parameterOne1Form').form('validate') 
           && $('#parameterOne2Form').form('validate')
           && $('#parameterOne3Form').form('validate')
           && $('#parameterOne4Form').form('validate')
           && $('#parameterOne5Form').form('validate')				
		   && $('#parameterTwo1Form').form('validate')
		   && $('#parameterTwo2Form').form('validate')
		   && $('#parameterTwo3Form').form('validate')
		   && $('#parameterTwo4Form').form('validate')
		   && $('#parameterThree1Form').form('validate')
		   && $('#parameterThree2Form').form('validate')
		   && $('#parameterThree3Form').form('validate')
		   && $('#parameterThree4Form').form('validate')
		   && $('#parameterThree5Form').form('validate')
		){
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'sellParamSetAction!saveParam.action',
				data : $('#parameterOne1Form').serialize() + '&' + 
					   $('#parameterOne2Form').serialize() + '&' +
					   $('#parameterOne3Form').serialize() + '&' +
					   $('#parameterOne4Form').serialize() + '&' +
					   $('#parameterOne5Form').serialize() + '&' +
				       $('#parameterTwo1Form').serialize() + '&' + 
				       $('#parameterTwo2Form').serialize() + '&' + 
				       $('#parameterTwo3Form').serialize() + '&' + 
				       $('#parameterTwo4Form').serialize() + '&' + 
				       $('#parameterThree1Form').serialize() + '&' +
				       $('#parameterThree2Form').serialize() + '&' +
				       $('#parameterThree3Form').serialize() + '&' +
				       $('#parameterThree4Form').serialize() + '&' +
				       $('#parameterThree5Form').serialize(),
				success : function(r) {
					if (r.success) {
						$('#button').empty();
						alertMsg(r);
						$('#parameterTabs').tabs('select', '参数设置一');
					}else{
						alertMsg(r);
					}
				},
				error : function (r){
				   if(r.readyState == '0' && r.status == '0'){
					   alertMsg('对不起，无法连接服务器，请检查您的计算机硬件以及网络连接是否正常！', 'warning');
				   }
			   }
			});
		}else{
			alertMsg('对不起，记录无法保存，请确认内容及格式是否正确！', 'warning');
		}
	}
	</script>

  </head>
   <body class="easyui-layout">
       <div style="padding:3px; height:32px; background:#eee;" data-options="region:'north',border:false">
         <privilege:enable code="SYSTEMPARAM_SAVE">
       	  <a id="sava" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true">保存</a>
       	  </privilege:enable>
		  <span id="button"></span>
       </div>
       <div style="background:#eee;" data-options="region:'center',border:false">
		<div id="parameterTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div id="parameterOne1"
			data-options="fit:true,border:false,title:'综合参数设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterOne1.jsp'">
			</div>
			<div id="parameterOne2"
			data-options="fit:true,border:false,title:'客户管理与提醒设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterOne2.jsp'">
			</div>
			<div id="parameterOne3"
			data-options="fit:true,border:false,title:'车辆销售及出库设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterOne3.jsp'">
			</div>
			<div id="parameterOne4"
			data-options="fit:true,border:false,title:'短信账号设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterOne4.jsp'">
			</div>
			<div id="parameterOne5"
			data-options="fit:true,border:false,title:'厂家返利设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterOne5.jsp'">
			</div>
			<div id="parameterTwo1"
			data-options="fit:true,border:false,title:'编号及打印设置',href:'${pageContext.request.contextPath}/sell/sellSystemParamSet/parameterTwo1.jsp'">
			</div>
			<div id="parameterTwo2"
			data-options="fit:true,border:false,title:'密码安全策略',href:'${pageContext.request.contextPath}/base/parameter/parameterTwo4.jsp'">
			</div>
			
		
		
		</div>
	</div>
  </body>
</html>