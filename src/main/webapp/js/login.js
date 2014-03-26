/**
* login.jsp 页面中使用到的JS定义文件
       前台验证用户名和密码是否为null、以及回车事件、 用户名--密码长度校验
       后台验证用户名密码是否正确 
*/
var cookieusername= "syuesoftusername";
var cookiepassword= "syuesoftpassword";
var cookiedomain="syuesoft.com";
var cookiepath="/";

//监听回车事件		
$(document).keypress(function(e) {
	if (e.which == 13) {
	     CheckForm();        
    }
});

//提交判断用户名是否合法  (点击按钮时 )
$(function(){
	$("input[id='checkbox_login']").removeAttr("checked");
	getCookie(); 
	//判断用户名称与密码输入是否合法
	$('#btn_login').click(function(){
        CheckForm();
    });
});
  
function CheckForm(){
	//用户名
	var namec = $('#userName');
    var name = namec.val();
    //密码
    var passwordc = $('#password');
    var password = passwordc.val();
	//校验用户名是否为空  
	if (jQuery.trim(name) == "") {
		massgerUserName="用户名不能为空";
		$('#NaMag').text(massgerUserName);
		namec.focus();
		var username=document.getElementById("userName");
		username.onkeydown=handleN;
		return false;
	}
	
	//校验密码是否为空
	if (jQuery.trim(password) == "") {
		$('#NaMag').text('');
		massgerPassword="密码不能为空";
		$('#NaMag').text(massgerPassword);
		passwordc.focus();
		var  userpassword=document.getElementById("password");
		userpassword.onkeydown=handleP;
		return false;
	}
	jsProbar();
	//最后提交表单
	$.ajax({
		   type: 'post',
		   dataType: 'json',
		   url: "login_doLogin.action",
		   data: $('#login_from').serialize(),
		   success: function(r){
		       if(r.success){
		    	   setCookie(name,password);
		    	   jsCloseProbar();
		    	   window.location.href="main.jsp";
		       }else{
		    	   jsCloseProbar();
		    	   alert(r.msg);
		       }
		   }
	});
}

function jsProbar() {
    $(document).ready(function () {
        $.blockUI({ message: '<h1><img src="images/loading.gif" /> 登录中，请稍后...</h1>' });
    });
}

function jsCloseProbar() {
    $(document).ready(function () {
        $.unblockUI();
    });
}

//移除错误信息
//键盘事件
function handleN(oEvent){
	 if(window.event)
		 oEvent=window.event;
	 if(jQuery.trim(name) == ""){
		 massgerUserName="用户名不能为空";
		 $('#NaMag').text(massgerUserName);
	     $('#userName').focus();
	 }	
	 $('#NaMag').text("");
}
		
function handleP(oEvent){
	 if(window.event)
		 oEvent=window.event;
	 if(jQuery.trim(password) == ""){
		 massgerUserName="密码不能为空";
		 $('#PaMag').text(massgerUserName);
	     $('#password').focus();
	 }	
	 $('#PaMag').text("");
}

var pwd="0123456789123456";
function getCookie(){
	var namec = $('#userName');
	namec.blur(function(){
		var name = namec.val();
		$('#password').val("");
		var username = $.cookie(cookieusername+name);
		var password = $.cookie(cookiepassword+name);
		if(username != null && password != null){
			password=Decrypt(password,pwd);
			$('#password').val(password);
			$("input[id='checkbox_login']").attr("checked","true");  
		}else{
			$("input[id='checkbox_login']").removeAttr("checked"); 
		}
	});
}

function setCookie(username, password){ //设置cookie  
    var checked = $("input[id='checkbox_login']:checked");//获取"是否记住密码"复选框  
    if(checked && checked.length > 0){ //判断是否选中了"记住密码"复选框 
    	password=Encrypt(password,pwd);
        $.cookie(cookieusername+username,username, {expires:7,path:cookiepath});//调用jquery.cookie.js中的方法设置cookie中的用户名  
        $.cookie(cookiepassword+username,password, {expires:7,path:cookiepath});//调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密  
    }else{   
        $.cookie(cookieusername+username, '', { expires: -1, path: cookiepath});
	    $.cookie(cookiepassword+username, '', { expires: -1, path: cookiepath});
    }    
}