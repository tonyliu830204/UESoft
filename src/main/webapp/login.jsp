<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>优亿汽车管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
  </head>
  
  <body id="userlogin_body">
		<div>
		</div>
		<div id="user_login">
			<dl>
				<dd id="user_top">
					<ul>
						<li class="user_top_l">
						</li>
						<li class="user_top_c">
						</li>
						<li class="user_top_r">
						</li>
					</ul>
					<dd id="user_main">
						<ul>
							<li class="user_main_l">
							
							</li>
							<li class="user_main_c">
							    <form id="login_from" name="login_from" method="post">
								<div class="user_main_box">
									<ul>
										<li class="user_main_text">
											用户名：
										</li>
										<li class="user_main_input">
											<input class="TxtUserNameCssClass" id="userName" name="userName" value="YG0011" maxLength=20>
										</li>
									</ul>
									<ul>
										<li class="user_main_text">
											密 码：
										</li>
										<li class="user_main_input">
											<input class="TxtPasswordCssClass" id="password" name="userPasswd"  value="123456" type="password" >
										</li>
									</ul>
									<ul>
										<li class="user_main_text">
											Cookie：
										</li>
										<li class="user_main_input">
											<input type="checkbox" id="checkbox_login" name="checkbox_login"/>记住我的登录状态
										</li>
									</ul>
									<ul>
										<li class="user_main_text">
											<font id="NaMag" color="red" style="font-size: 14px;"><span id="error" ><strong>${loginMessageN}</strong></span></font>
										</li>
									</ul>
								</div>
								</form>
							</li>
							<li class="user_main_r">
								<input class="IbtnEnterCssClass" id="btn_login" name="btn_login" type="image" src="images/user_botton.gif">
							</li>
						</ul>
						<dd id="user_bottom">
							<ul>
								<li class="user_bottom_l">
								</li>
								<li class="user_bottom_c">
								</li>
								<li class="user_bottom_r">
								</li>
							</ul>
						</dd>
			</dl>
		</div>
		<SPAN id="ValrUserName" style="DISPLAY: none; COLOR: red">
		</SPAN>
		<SPAN id="ValrPassword" style="DISPLAY: none; COLOR: red">
		</SPAN>
		<SPAN id="ValrValidateCode" style="DISPLAY: none; COLOR: red">
		</SPAN>
		<div id="ValidationSummary1" style="DISPLAY: none; COLOR: red">
		</div>
		<div>
		</div>
	</body>
</html>