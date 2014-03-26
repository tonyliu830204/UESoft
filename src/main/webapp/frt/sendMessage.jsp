<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%


%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%><jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>短信发送</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <script type="text/javascript">
		
		
	</script>
		<form id="frtPartsQueryForm">
			<table width="">
				<tr>
					<td>手机号码:</td>
					<td><input type="text" name="resvFixpel" maxlength="20"
					 style="width: 260px;"	class="easyui-validatebox"
						data-options="required : true,validType:'mobile',missingMessage : '手机号码为必填项' " /></td>
				</tr>
				<tr>
					<td>发送内容:</td>
					<td><textarea  id="customArchives_add_customRemark2" name="customRemark2" 
				onkeyup="showMaxLength('customArchives_add_customRemark2',500);"
					rows="" cols=""  style="width: 260px; height: 240px;"></textarea></td>
				</tr>
			</table>
		</form>
  </body>
</html>
