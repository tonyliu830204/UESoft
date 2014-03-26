<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.syuesoft.model.BasUsers" %>
<%@ page import="com.syuesoft.contstants.Contstants"%>
<%@ taglib uri="/priveliege" prefix="privilege"%>
<%
BasUsers user = (BasUsers)request.getSession().getAttribute(Contstants.CUSTOMER);
%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<jsp:include page="${basePath}/common/inc.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'password.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/password.css"></link>
	<script type="text/javascript">
	  var parame1 = '<%=Contstants.PWCOMPLEXITY.LOWKEY %>';
	  var parame2 = '<%=Contstants.PWCOMPLEXITY.MEDKEY %>';
	  var parame3 = '<%=Contstants.PWCOMPLEXITY.HIKEY %>';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/base/password/password.js"></script>
  </head>
  <body>
      <div region="center"  style="background:#eee;" border="false">
           <p></p>
		   <form  id="password_" name="jBxx" style="height:100%;width:100%;">
	           <table  style="background:#eee; "    >
					  <tr>  
				             <input type="hidden" id="userId" name="userId" value="<%= user.getUserId()%>"/>
	   			             <td width="68">登录帐号:</td>
	 						 <td>
	 						      <input id="loginName" name="userName"  disabled="disabled" type="text" class="easyui-validatebox"	style="width:140px;" value="<%= user.getUserName()%>"/>
					         </td>
					         <td></td>
					  </tr> 
					  <tr>  
					         <td width="68">原密码:</td>
	 						 <td>
	 						      <input id="userPasswd" name="userPasswd" type="password" class="easyui-validatebox"	 
	 							    data-options="required:true,missingMessage:'原登录密码由6位以上的字符组合而成'" style="width:140px;"/>
					         </td>
					         <td></td>
					  </tr> 
					  <tr>  
	 						 <td width="68">新密码:</td>
	 						 <td>
	 						      <input id="userPasswd1" name="newuserPasswd" type="password" class="easyui-validatebox"	 
	 							    data-options="required:true,missingMessage:'新登录密码由6位以上的字符组合而成'" style="width:140px;" onkeyup="checkPassword(this.value,1);"/>
					         </td>
					         <td>
					              <table class="pwd-strength FCK__ShowTableBorders" cellSpacing="0" cellPadding="0" width="100%">
									 <tbody>
										<tr>
										    <td class="pwd-strength-box" id="pwdLow1">低</td>
										    <td class="pwd-strength-box" id="pwdMed1">中</td>
										    <td class="pwd-strength-box" id="pwdHi1">高</td>
										</tr>
									 </tbody>
								  </table>
					         </td>
					  </tr> 
					  <tr>  
					         <td width="68">确认密码:</td>
	 						 <td>
	 						      <input id="userPasswd2" name="checkuserPasswd" type="password" class="easyui-validatebox"	 
	 							    data-options='required:true,missingMessage:"确认登录密码由6位以上的字符组合而成"' validType="same['userPasswd1']" invalidMessage="两次输入密码不匹配" style="width:140px;" onkeyup="checkPassword(this.value,2);"/>
					         </td>
					         <td>
					              <table class="pwd-strength FCK__ShowTableBorders" cellSpacing="0" cellPadding="0" width="100%">
									 <tbody>
										<tr>
										    <td class="pwd-strength-box" id="pwdLow2">低</td>
										    <td class="pwd-strength-box" id="pwdMed2">中</td>
										    <td class="pwd-strength-box" id="pwdHi2">高</td>
										</tr>
									 </tbody>
								  </table>
					         </td>
					         <input id="passWordLeval" name="passWordLeval" type="hidden" />
					  </tr> 
					  <tr>  
					         <td></td>
					         <td>
					              <privilege:enable code="UPDATEPASSWORDSAVE">
					         	       <a id="save" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save();">保存</a>
					         	  </privilege:enable>
					         	  <privilege:enable code="UPDATEPASSWORDRESET">
			                           <a id="cancel" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancel();">重置</a>
			                      </privilege:enable>
					         </td>
					         <td></td>
					  </tr> 
				</table>
		    </form>
      </div>  
  </body>
</html>